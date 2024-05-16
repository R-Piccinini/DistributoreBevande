package org.italy.generation;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int nBevande = 2;
		float prezzo[] = new float[nBevande];
		int codiceBevande[] = new int[nBevande];
		int qntBevande[] = new int[nBevande];
		int i = 0;
		String metodoPagamento, risposta, selezioneProgramma;
		float contInseriti, resto, credChiavetta, incasso = 0;
		boolean chiaveInserita = false;

		System.out.println("Benvenuto per favore inserire lo stock iniziale della macchina");

		for (i = 0; i < nBevande; i++) { // creazione dell'inventario della macchina
			System.out.println("inserire codice bevanda:");
			codiceBevande[i] = sc.nextInt();
			System.out.println("inserire prezzo bevanda:");
			prezzo[i] = sc.nextFloat();
			sc.nextLine();
			System.out.println("inserire quantità disponibile bevanda:");
			qntBevande[i] = sc.nextInt();
			sc.nextLine();
		}

			System.out.println("inserisci credito chiavetta"); // approssimazione di un credito pre esistente della
			// chiavetta
			credChiavetta = sc.nextFloat();
			sc.nextLine();

		

		do {

			while (chiaveInserita) {
				System.out.println(
						"pannello amministratore \n Selezione programma:(ritiro incassi),(diagnostica),(modifica prezzi),(modifica qnt)");
				selezioneProgramma = sc.nextLine();
				while (!(selezioneProgramma.equals("ritiro incassi") || selezioneProgramma.equals("diagnostica")
						|| selezioneProgramma.equals("modifica prezzi") || selezioneProgramma.equals("modifica qnt"))) {
					System.out.println("(ritiro incassi),(diagnostica),(modifica prezzi),(modifica qnt)");
					selezioneProgramma = sc.next();
				}
				if (selezioneProgramma.equals("ritiro incassi")) {
					System.out.println("Incasso: " + incasso);
					incasso = 0;
				} else if (selezioneProgramma.equals("diagnostica")) {
					boolean guasto = false; // approssimazione della diagnostica
					if (guasto)
						System.out.println("errore rilevato");
					else
						System.out.println("nessun errore");

				} else if (selezioneProgramma.equals("modifica prezzi")) {
					System.out.println("selezione codice bevanda");
					int n = sc.nextInt();
					sc.nextLine();
					System.out.println("inserire prezzo aggiornato");
					int z = sc.nextInt();
					sc.nextLine();
					prezzo[n] = z;
				} else if (selezioneProgramma.equals("modifica qnt")) {
					System.out.println("selezione codice bevanda");
					int c = sc.nextInt();
					sc.nextLine();
					System.out.println("inserire qnt rifornita");
					int v = sc.nextInt();
					sc.nextLine();
					qntBevande[c] = qntBevande[c] + v;
				}
				System.out.println("Per uscire rimuovere chiavetta \n" + "Premere INVIO per continuare");
				sc.nextLine();

			}

			// parte utente

			System.out.println("Selezionare bevanda:"); // selezione prodotto
			boolean trovato = false;
			int selezione = sc.nextInt();
			sc.nextLine();
			for (i = 0; i < codiceBevande.length; i++) {
				if (codiceBevande[i] == selezione) { // bevanda esistente
					trovato = true;
					if (qntBevande[i] > 0) {
						System.out.println("costo " + prezzo[i] + "€");
						System.out.println("selezionare contanti o chiavetta"); // selezione metodo di pagamento
						metodoPagamento = sc.nextLine();
						while (!(metodoPagamento.equals("contanti") || metodoPagamento.equals("chiavetta"))) {
							System.out.println("selezionare contanti o chiavetta");
							metodoPagamento = sc.nextLine();
						}
						if (metodoPagamento.equals("contanti")) {
							System.out.println("inserire contanti");
							contInseriti = sc.nextFloat();
							sc.nextLine();
							while (contInseriti < prezzo[i]) {
								System.out.println((prezzo[i] - contInseriti) + "€ rimanenti"); // aggiunta monete
																								// rimanenti
								float tempCont = sc.nextFloat();
								sc.nextLine();
								contInseriti = contInseriti + tempCont;

							}
							if (contInseriti > prezzo[i]) { // erogazione resto
								resto = contInseriti - prezzo[i];
								System.out.println("il resto è : " + resto);
								System.out.println("ritirare bevanda");
								qntBevande[i]--; // riduzione stock bevanda
								incasso = incasso + prezzo[i] - resto;

							} else {
								System.out.println("ritirare bevanda");
								qntBevande[i]--; // riduzione stock bevanda
								incasso = incasso + prezzo[i];
							}

						} else {
							while (credChiavetta < prezzo[i]) { // credito insufficiente procedura per ricaricare
																// chiavetta
								System.out.println("Credito insufficiente");
								System.out.println("Ricaricare chiavetta inserire contanti");
								float tempCred = sc.nextFloat();
								sc.nextLine();
								credChiavetta = credChiavetta + tempCred;
							}

							System.out.println("ritirare bevanda"); // credito sufficiente,riduzione stock bevanda
							qntBevande[i]--;
							incasso = incasso + prezzo[i];
							credChiavetta = credChiavetta - prezzo[i];
						}

					} else
						System.out.println("Bevanda esaurita");

				}
			}
			if (trovato == false)
				System.out.println("Bevanda non disponibile"); // bevanda non disponibile

			/*
			 * System.out.println("vuoi acquistare altri prodotti? si/no"); risposta =
			 * sc.nextLine();
			 */

		} while (true); // check per il ciclo
	}
}
