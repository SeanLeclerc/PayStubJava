
//=========================================
//Programmeur : Sean Leclerc
//Fichier : LeclercSeanD01.java
//Date : Nov 4, 2016
//Description : Devoir01 
//=========================================

import java.io.*;

//============================================================== 12951 A16 ===
public class LeclercSeanD01 {

	// VOUS NE DEVEZ PAS TOUCHER AU CODE DE CETTE MÉTHODE
	// ---------------------------------------------------------- 12951 A16 ---
	public static void main(String[] args)
	// ---------------------------------------------------------- 12951 A16 ---
	{
		String input = lireFichier();
		String output = fabriquerRapport(input);
		ecrireFichier(output);
	}

	// VOUS NE DEVEZ PAS TOUCHER AU CODE DE CETTE MÉTHODE
	// ---------------------------------------------------------- 12951 A16 ---
	private static String lireFichier()
	// ---------------------------------------------------------- 12951 A16 ---
	{
		String texte = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader("input.txt"));
			String ligne;
			while ((ligne = br.readLine()) != null) {
				texte += ligne + "\n";
			}
			br.close();
		} catch (IOException ioe) {
		}
		return texte;
	}

	// VOUS NE DEVEZ PAS TOUCHER AU CODE DE CETTE MÉTHODE
	// ---------------------------------------------------------- 12951 A16 ---
	private static void ecrireFichier(String output)
	// ---------------------------------------------------------- 12951 A16 ---
	{
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("output.txt"));
			pw.print(output);
			pw.close();
		} catch (IOException ioe) {

		}
	}

	// CETTE MÉTHODE EST LA SEULE QUE VOUS DEVEZ MODIFIER
	// ------------------------------------------------------------------------
	private static String fabriquerRapport(String input)
	// ------------------------------------------------------------------------
	{ // Déclaration des variables.
		String output = "";
		double tauxHoraire = 15.65;
		double tauxAugmentation = 0.015;
		double totalPayeReg = 0.0, totalPayeSupp = 0.0, totalPayeTotal = 0.0;
		// Banniere avec nom,date et jours de la semaine.
		java.util.Calendar c = java.util.Calendar.getInstance();

		String date = String.format("%1$tY-%1$tm-%1$te %1$tH:%1$tM", c);

		output += "================================================================================================\n";

		output += "Devoir(01) : Rapport de charge salariale                          Comptable : Sean Leclerc\n";

		output += "Date       : " + date + "\n";

		output += "================================================================================================\n";

		output += "                           A   L    M    M    J    V    S    D       Reg.      Supp.    Salaire\n";

		output += "================================================================================================\n";
		// Nous utilisont la chaine input pour consevoir la chaine output voulu.

		while (!input.isEmpty()) {
			// Chercher la prochaine ligne a l'aide du changement de ligne(\n).
			String ligne;                              //Initialisation de la variable "ligne" qui va permettre d'analyser le segement.
			int indice = input.indexOf("\n");          //Trouver le changement de ligne.
			if (indice != 1) {                         //Si "indice" est vrai, il y a un changement de ligne(/n).
				ligne = input.substring(0, indice);    //"Ligne" prend la valeur de 0 a indice, donc la ligne.
				input = input.substring(indice + 1);   //"input" perd sa valeur pour permettre de continuer.
			} else {                                   //===================================================================================================
				ligne = input;                         // Seulement si "indice"==1 qui signifie qu'il est a la dermiere ligne et peut doc aller a "else" et
				input = "";                            //fin de l'analysation de des lignes.
			}                                          //===================================================================================================
			// Variables, séparation des données.
			int nbAnnée = 0, nbJours = 0;
			double nbHeures = 0.0, nbSupp = 0.0, nbHrsTotal = 0.0, nbSuppFin = 0.0, nbHrsTotalFds = 0.0, payeReg = 0.0,
					payeSupp = 0.0, payeTotal = 0.0;
			String sTmps = "";
			// Trouver nom
			indice = ligne.indexOf(",");                //Trouver la virgule a l'aide de l'indice.
			String nom = ligne.substring(0, indice);    //Variable nom est la premiere section.
			if (nom.length() > 25) {                    //Si la longueur de "nom" est plus que 25 charactere,
				nom = nom.substring(0, 22) + "...";     //on ajoute "...".
			}                                           //Fin de la séeparation du nom.
			//Trouver le nombre d'année de la personne et attribué le montant de plus si nécessaire. 
			ligne = ligne.substring(indice + 1);                                           //Permet de d'ajouter une nouvelle valeur car ligne a perdu la sienne.
			indice = ligne.indexOf(",");                                                   //Trouver la virgule pour séparer "nbAnnée".
			nbAnnée = Integer.parseInt(ligne.substring(0, indice));						   //Transformer la valeur en "Int" qui est de 0 a "indice".
			double tauxIndividuel = tauxHoraire * Math.pow(1 + tauxAugmentation, nbAnnée); //Calcule pour le "tauxIndividuel".
			ligne = ligne.substring(indice + 1);                                           //"Ligne" perd sa valeur...
            //Calcul pour le nombre d'heures et le nombre d'heures supplémetaire.
			while (!ligne.isEmpty()) {                  //Lorsque que "ligne" n'est pas vide on continu.
				nbJours++;                              //Augemente le jours de +1 pour faire la semaine au complet.
				indice = ligne.indexOf(",");            //Trouver le nombre de jours a l'aide de la virgule.
				if (indice != -1) {                                             //=======================================================================================================
					nbHeures = Double.parseDouble(ligne.substring(0, indice));  //Seulement lorseque "indice" negale pas a -1, on donne une valeur a "nbHeures" pour ensuite la conserver.
					ligne = ligne.substring(indice + 1);                        //========================================================================================================

				} else {                                //Seulement si indice est égale a -1 qui signifie la fin de la ligne.
					nbHeures = Double.parseDouble(ligne);
					ligne = "";
				}
				//Fin de semaine
				sTmps += String.format("%1$5.1f", nbHeures);
				if (nbJours > 5 && nbHeures > 0) {      //Si le nombre de jours est plus que 5, donc la fin de semaine, pour ajouter l'argent de plus(20$).
					payeSupp += 20;              
					if (nbHeures > 10) {                //Ajouter le nombre d'heures nécéssaire pour la fin de semaine.
						nbHrsTotalFds += 8;
						nbSuppFin += 3;
						nbSuppFin += (nbHeures - 10) * 2;
					} else if (nbHeures > 8) {          //Heures supplémentaires
						nbHrsTotalFds += 8;
						nbSuppFin += (nbHeures - 8) * 1.5;

					} else {
						nbHrsTotalFds += nbHeures;
					}
                //Semaine si on conclu que se n'est pas la fin de semaine.
				} else {
					if (nbHeures > 10) {
						nbHrsTotal += 8;
						nbSupp += 3;
						nbSupp += (nbHeures - 10) * 2;
					} else if (nbHeures > 8) {          //Heures supplémentaire.
						nbHrsTotal += 8;
						nbSupp += (nbHeures - 8) * 1.5; //Temps supplémentaire, on ajoute 1.5.
					} else {
						nbHrsTotal += nbHeures;
					}
				}

			}
            //Calcul de toutes les payes, variables.
			payeReg = nbHrsTotal * tauxIndividuel + nbHrsTotalFds * (tauxIndividuel + 1);
			payeSupp += nbSupp * tauxIndividuel + nbSuppFin * (tauxIndividuel + 1);
			payeTotal = payeReg + payeSupp;
			totalPayeReg += payeReg;
			totalPayeSupp += payeSupp;
			totalPayeTotal += payeTotal;
			output += String.format("%1$-25s%2$3d", nom, nbAnnée);
			output += sTmps;
			output += String.format("%1$10.2f$%2$10.2f$%3$10.2f$", payeReg, payeSupp, payeTotal) + "\n";

		}
		//Afficher le résultat en utilisant "output" pour le bon format.
		output += "================================================================================================\n";
		output += String.format("Totaux : %1$65.2f$%2$10.2f$%3$10.2f$\n", totalPayeReg, totalPayeSupp, totalPayeTotal);
		output += "================================================================================================";

		return output;                                     //Retourne "output".
	}

}// Fin de la classe
