package gestion;

import java.util.Scanner;

public class gestionSTK{

    // Déclaration des tableaux pour stocker les informations des produits
    static int[] codesProduits = new int[100];
    static String[] nomsProduits = new String[100];
    static int[] quantites = new int[100];
    static double[] prix = new double[100];
    static int nombreDeProduits = 0; // Variable pour suivre le nombre de produits dans le stock

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix;

        // Boucle principale du programme
        do {
            printMenu();
            System.out.print("Entrez votre choix: ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Pour consommer le saut de ligne restant

            switch (choix) {
                case 1:
                    // Ajouter un produit
                    System.out.print("Entrez le code produit: ");
                    int code = scanner.nextInt();
                    scanner.nextLine(); // Consommer le saut de ligne
                    System.out.print("Entrez le nom du produit: ");
                    String nom = scanner.nextLine();
                    System.out.print("Entrez la quantité: ");
                    int quantite = scanner.nextInt();
                    System.out.print("Entrez le prix unitaire: ");
                    double prixUnitaire = scanner.nextDouble();
                    ajouterProduit(code, nom, quantite, prixUnitaire);
                    break;
                case 2:
                    // Modifier un produit
                    System.out.print("Entrez le code du produit à modifier: ");
                    int codeModif = scanner.nextInt();
                    scanner.nextLine(); // Consommer le saut de ligne
                    System.out.print("Entrez le nouveau nom du produit: ");
                    String nouveauNom = scanner.nextLine();
                    System.out.print("Entrez la nouvelle quantité: ");
                    int nouvelleQuantite = scanner.nextInt();
                    System.out.print("Entrez le nouveau prix unitaire: ");
                    double nouveauPrix = scanner.nextDouble();
                    modifierProduit(codeModif, nouveauNom, nouvelleQuantite, nouveauPrix);
                    break;
                case 3:
                    // Supprimer un produit
                    System.out.print("Entrez le code du produit à supprimer: ");
                    int codeSuppr = scanner.nextInt();
                    supprimerProduit(codeSuppr);
                    break;
                case 4:
                    // Afficher les produits
                    afficherProduits();
                    break;
                case 5:
                    // Rechercher un produit
                    System.out.print("Entrez le nom du produit à rechercher: ");
                    String nomRecherche = scanner.nextLine();
                    rechercherProduit(nomRecherche);
                    break;
                case 6:
                    // Calculer la valeur totale du stock
                    calculerValeurStock();
                    break;
                case 0:
                    // Quitter
                    System.out.println("Au revoir!");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez essayer à nouveau.");
            }
        } while (choix != 0);

        scanner.close();
    }

    // Méthode pour afficher le menu
    public static void printMenu() {
        System.out.println("\n--- Gestion de Stock ---");
        System.out.println("1. Ajouter un produit");
        System.out.println("2. Modifier un produit");
        System.out.println("3. Supprimer un produit");
        System.out.println("4. Afficher les produits");
        System.out.println("5. Rechercher un produit");
        System.out.println("6. Calculer la valeur totale du stock");
        System.out.println("0. Quitter");
    }

    // Méthode pour ajouter un produit
    public static void ajouterProduit(int code, String nom, int quantite, double prix) {
        if (nombreDeProduits < 100) {
            codesProduits[nombreDeProduits] = code;
            nomsProduits[nombreDeProduits] = nom;
            quantites[nombreDeProduits] = quantite;
            nombreDeProduits++;
            System.out.println("Produit ajouté avec succès!");
        } else {
            System.out.println("Le stock est plein. Impossible d'ajouter un produit.");
        }
    }

    // Méthode pour modifier un produit
    public static void modifierProduit(int code, String nouveauNom, int nouvelleQuantite, double nouveauPrix) {
        for (int i = 0; i < nombreDeProduits; i++) {
            if (codesProduits[i] == code) {
                nomsProduits[i] = nouveauNom;
                quantites[i] = nouvelleQuantite;
                prix[i] = nouveauPrix;
                System.out.println("Produit modifié avec succès!");
                return;
            }
        }
        System.out.println("Produit non trouvé.");
    }

    // Méthode pour supprimer un produit
    public static void supprimerProduit(int code) {
        for (int i = 0; i < nombreDeProduits; i++) {
            if (codesProduits[i] == code) {
                for (int j = i; j < nombreDeProduits - 1; j++) {
                    codesProduits[j] = codesProduits[j + 1];
                    nomsProduits[j] = nomsProduits[j + 1];
                    quantites[j] = quantites[j + 1];
                    prix[j] = prix[j + 1];
                }
                nombreDeProduits--;
                System.out.println("Produit supprimé avec succès!");
                return;
            }
        }
        System.out.println("Produit non trouvé.");
    }

    // Méthode pour afficher la liste des produits
    public static void afficherProduits() {
        if (nombreDeProduits == 0) {
            System.out.println("Aucun produit en stock.");
        } else {
            System.out.println("\n--- Liste des produits en stock ---");
            for (int i = 0; i < nombreDeProduits; i++) {
                System.out.println("Code: " + codesProduits[i] + ", Nom: " + nomsProduits[i] + ", Quantité: " + quantites[i] + ", Prix: " + prix[i]);
            }
        }
    }

    // Méthode pour rechercher un produit
    public static void rechercherProduit(String nom) {
        boolean trouve = false;
        for (int i = 0; i < nombreDeProduits; i++) {
            if (nomsProduits[i].equalsIgnoreCase(nom)) {
                System.out.println("Produit trouvé : Code: " + codesProduits[i] + ", Nom: " + nomsProduits[i] + ", Quantité: " + quantites[i] + ", Prix: " + prix[i]);
                trouve = true;
                break;
            }
        }
        if (trouve) {
            System.out.println("Produit non trouvé.");
        }
    }

    // Méthode pour calculer la valeur totale du stock
    public static void calculerValeurStock() {
        double total = 0;
        for (int i = 0; i < nombreDeProduits; i++) {
            total += quantites[i] * prix[i];
        }
        System.out.println("La valeur totale du stock est : " + total);
    }
}

