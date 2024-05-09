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
		String metodoPagamento, risposta;
		float contInseriti, resto, credChiavetta;
		System.out.println("inserisci credito chiavetta"); // approssimazione di un credito pre esistente della chiavetta
		credChiavetta = sc.nextFloat();
		sc.nextLine();

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
		do {
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
								System.out.println((prezzo[i] - contInseriti) + "€ rimanenti"); // aggiunta monete rimanenti
								float tempCont = sc.nextFloat();
								sc.nextLine();
								contInseriti = contInseriti + tempCont;

							}
							if (contInseriti > prezzo[i]) { // erogazione resto
								resto = contInseriti - prezzo[i];
								System.out.println("il resto è : " + resto);
								System.out.println("ritirare bevanda");
								qntBevande[i]--; // riduzione stock bevanda
							} else {
								System.out.println("ritirare bevanda");
							qntBevande[i]--; // riduzione stock bevanda
							}

						} else {
							while (credChiavetta < prezzo[i]) { // credito insufficiente procedura per ricaricare chiavetta
								System.out.println("Credito insufficiente");
								System.out.println("Ricaricare chiavetta inserire contanti");
								float tempCred = sc.nextFloat();
								sc.nextLine();
								credChiavetta = credChiavetta + tempCred;
							}

							System.out.println("ritirare bevanda"); // credito sufficiente,riduzione stock bevanda
							qntBevande[i]--;
							credChiavetta = credChiavetta - prezzo[i];
						}

					} else
						System.out.println("Bevanda esaurita");

				}
			}
			if (trovato == false)
				System.out.println("Bevanda non disponibile"); // bevanda non disponibile

			System.out.println("vuoi acquistare altri prodotti? si/no");
			risposta = sc.nextLine();

		} while (risposta.equals("si")); // check per il ciclo
		sc.close();
	}
}
