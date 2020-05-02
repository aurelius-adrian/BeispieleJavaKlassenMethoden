import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class main {

    private static LinkedList<Auto> karten = new LinkedList<>();
    private static int[] spielerEins, spielerZwei;
    private static int spielerAmZug = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        standardKarten();
        System.out.println("Möchten Sie neue Karten einfügen?\nGeben sie 'y' für Ja und 'N' für Nein ein:");
        String input = scanner.nextLine();

        if (!input.equals("") && !input.equals("N") && !input.equals("n")) {
            kartenEingabe();
        }

        kartenInitialisieren();

        System.out.printf("Die erste karte von Spieler Eins wird angezeigt, mit ENTER bestätigen:");
        scanner.nextLine();

        boolean gewonnen = false;
        int[] index;
        while (!gewonnen) {
            index = naechstesElement();

            kartenAnzeigen(spielerAmZug, index);

            System.out.printf("geben Sie die Nummer der zu vergleichenden Eigenschaft ein:"); //"\n1: Marke \n2: Name \n3: Maximale Geschwindigkeit \n4: Maximale Leistung \n5: Gewicht \n6: Hubraum \n7: Beschleunigung\n"

            int eigenschaft = 1;
            boolean eingabeEigenschaft = true;
            while (eingabeEigenschaft) {
                try {
                    eigenschaft = scanner.nextInt();
                    if (eigenschaft > 7 || eigenschaft < 1) {
                        throw new UnerlaubteEingabeException("");
                    }
                    eingabeEigenschaft = false;
                } catch (InputMismatchException x) {
                    System.out.printf("Input Mismatch Exception: wrong data type, expected int.\nBitte geben sie eine Zahl ein!");
                } catch (UnerlaubteEingabeException y) {
                    System.out.printf("Unerlaubte Eingabe, bitte geben Sie eine Zahl zwischen 1 und 7 an");
                }
            }
            System.out.printf("\n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n");
            //kartenAnzeigen(2, index);
            gewonnen = vergleich(eigenschaft, index);
            if (gewonnen == true) break;
            System.out.printf("\n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n");
        }
    }

    private static void standardKarten() {
        karten = new LinkedList<>(Arrays.asList(
                new Auto("Mercedes", "W124", 195f, 132f, 15000f, 2.3f, 10f),
                new Auto("Mercedes", "300 SEL", 221f, 250f, 1740f, 6.3f, 6.5f),
                new Auto("Glas", "Goggomobil 250", 80f, 14f, 480f, 0.2f, 100.8f),
                new Auto("Renault", "R8", 133f, 43, 750f, 1.1f, 12.9f),
                new Auto("Alfa Romeo", "1750", 180f, 115f, 959f, 1.7f, 11.9f),
                new Auto("Cadillac", "68", 190f, 340f, 2014f, 7.0f, 16.9f),
                new Auto("VW", "1300", 122f, 40f, 730f, 1.2f, 13.9f),
                new Auto("Jaguar", "S", 189f, 213, 1620f, 3.4f, 10.9f),
                new Auto("BMW", "2000", 168f, 100, 1040f, 1.9f, 16f),
                new Auto("BMW", "E32", 220f, 184, 1600f, 2.9f, 10.8f)));
    }

    private static void kartenInitialisieren() {
        LinkedList<Auto> tmp = new LinkedList<>();
        while (!karten.isEmpty()) {
            int x = (int) (StrictMath.random() * karten.size());
            tmp.add(karten.get(x));
            karten.remove(x);
        }
        karten = tmp;

        if (karten.size() % 2 != 0) {
            System.out.printf("Die Anzahl der Karten ist nicht gerade, das erste Element wird gelöscht.");
            karten.removeFirst();
        }

        spielerEins = new int[karten.size()];
        spielerZwei = new int[karten.size()];
        for (int i = 0; i < karten.size(); i++) {
            spielerEins[i] = -2;
            spielerZwei[i] = -2;
        }

        for (int i = 0; i < karten.size(); i++) {
            if (i < karten.size() / 2) {
                spielerEins[naechstesFreiesElement()[0]] = i;
            } else {
                spielerZwei[naechstesFreiesElement()[1]] = i;
            }
        }
    }

    private static void kartenAnzeigen(int option, int[] index) {
        Scanner scanner = new Scanner(System.in);
        if (option == 0) {
            System.out.printf(
                            "| 1 |" + karten.get(spielerEins[index[0]]).getMarke() + '\n' +
                            "| 2 |" + karten.get(spielerEins[index[0]]).getName() + '\n' +
                            "| 3 |" + karten.get(spielerEins[index[0]]).getMaximaleGeschwindigkeit() + '\n' +
                            "| 4 |" + karten.get(spielerEins[index[0]]).getMaximaleLeistungPS() + '\n' +
                            "| 5 |" + karten.get(spielerEins[index[0]]).getGewicht() + '\n' +
                            "| 6 |" + karten.get(spielerEins[index[0]]).getHubraum() + '\n' +
                            "| 7 |" + karten.get(spielerEins[index[0]]).getBeschleunigung() + '\n'); //"Um fortzufahren bitte Eingabe drücken:"
            //scanner.nextLine();
        } else if (option == 1) {
            System.out.printf(
                            "| 1 |" + karten.get(spielerZwei[index[1]]).getMarke() + '\n' +
                            "| 2 |" + karten.get(spielerZwei[index[1]]).getName() + '\n' +
                            "| 3 |" + karten.get(spielerZwei[index[1]]).getMaximaleGeschwindigkeit() + '\n' +
                            "| 4 |" + karten.get(spielerZwei[index[1]]).getMaximaleLeistungPS() + '\n' +
                            "| 5 |" + karten.get(spielerZwei[index[1]]).getGewicht() + '\n' +
                            "| 6 |" + karten.get(spielerZwei[index[1]]).getHubraum() + '\n' +
                            "| 7 |" + karten.get(spielerZwei[index[1]]).getBeschleunigung() + '\n'); //"Um fortzufahren bitte Eingabe drücken:"
            //scanner.nextLine();
        } else if (option == 2) {
            System.out.printf(
                            "|" + karten.get(spielerEins[index[0]]).getMarke() + "\t \t \t |" + karten.get(spielerZwei[index[1]]).getMarke() + '\n' +
                            "|" + karten.get(spielerEins[index[0]]).getName() + "\t \t \t |" + karten.get(spielerZwei[index[1]]).getName() + '\n' +
                            "|" + karten.get(spielerEins[index[0]]).getMaximaleGeschwindigkeit() + "\t \t \t |" + karten.get(spielerZwei[index[1]]).getMaximaleGeschwindigkeit() + '\n' +
                            "|" + karten.get(spielerEins[index[0]]).getMaximaleLeistungPS() + "\t \t \t |" + karten.get(spielerZwei[index[1]]).getMaximaleLeistungPS() + '\n' +
                            "|" + karten.get(spielerEins[index[0]]).getGewicht() + "\t \t \t |" + karten.get(spielerZwei[index[1]]).getGewicht() + '\n' +
                            "|" + karten.get(spielerEins[index[0]]).getHubraum() + "\t \t \t |" + karten.get(spielerZwei[index[1]]).getHubraum() + '\n' +
                            "|" + karten.get(spielerEins[index[0]]).getBeschleunigung() + "\t \t \t |" + karten.get(spielerZwei[index[1]]).getBeschleunigung() + '\n' + "Um fortzufahren bitte Eingabe drücken:");
            scanner.nextLine();
            System.out.printf("\n");
        }
    }

    private static boolean vergleich(int eigenschaft, int[] index) {
        Scanner scanner = new Scanner(System.in);
        boolean gewonnen = false;
        switch (eigenschaft) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                if (karten.get(spielerEins[index[0]]).getMaximaleGeschwindigkeit() > karten.get(spielerZwei[index[1]]).getMaximaleGeschwindigkeit()) {
                    gewonnen = gewinn(index, 0);
                    if (gewonnen == true) break;
                    System.out.printf("Spieler Eins hat die Runde gewonnen!\n");
                    scanner.nextLine();
                    spielerAmZug = 0;
                } else if (karten.get(spielerZwei[index[1]]).getMaximaleGeschwindigkeit() > karten.get(spielerEins[index[0]]).getMaximaleGeschwindigkeit()) {
                    gewonnen = gewinn(index, 1);
                    if (gewonnen == true) break;
                    System.out.printf("Spieler Zwei hat die Runde gewonnen!\n");
                    scanner.nextLine();
                    spielerAmZug = 1;
                } else {
//                        Gleichstand Progammieren!!!!!
                }
                break;
            case 4:
                if (karten.get(spielerEins[index[0]]).getMaximaleLeistungPS() > karten.get(spielerZwei[index[1]]).getMaximaleLeistungPS()) {
                    gewonnen = gewinn(index, 0);
                    if (gewonnen == true) break;
                    System.out.printf("Spieler Eins hat die Runde gewonnen!\n");
                    scanner.nextLine();
                    spielerAmZug = 0;
                } else if (karten.get(spielerZwei[index[1]]).getMaximaleLeistungPS() > karten.get(spielerEins[index[0]]).getMaximaleLeistungPS()) {
                    gewonnen = gewinn(index, 1);
                    if (gewonnen == true) break;
                    System.out.printf("Spieler Zwei hat die Runde gewonnen!\n");
                    scanner.nextLine();
                    spielerAmZug = 1;
                } else {
//                        Gleichstand Progammieren!!!!!
                }
                break;
            case 5:
                if (karten.get(spielerEins[index[0]]).getGewicht() > karten.get(spielerZwei[index[1]]).getGewicht()) {
                    gewonnen = gewinn(index, 0);
                    if (gewonnen == true) break;
                    System.out.printf("Spieler Eins hat die Runde gewonnen!\n");
                    scanner.nextLine();
                    spielerAmZug = 0;
                } else if (karten.get(spielerZwei[index[1]]).getGewicht() > karten.get(spielerEins[index[0]]).getGewicht()) {
                    gewonnen = gewinn(index, 1);
                    if (gewonnen == true) break;
                    System.out.printf("Spieler Zwei hat die Runde gewonnen!\n");
                    scanner.nextLine();
                    spielerAmZug = 1;
                } else {
//                        Gleichstand Progammieren!!!!!
                }
                break;
            case 6:
                if (karten.get(spielerEins[index[0]]).getHubraum() > karten.get(spielerZwei[index[1]]).getHubraum()) {
                    gewonnen = gewinn(index, 0);
                    if (gewonnen == true) break;
                    System.out.printf("Spieler Eins hat die Runde gewonnen!\n");
                    scanner.nextLine();
                    spielerAmZug = 0;
                } else if (karten.get(spielerZwei[index[1]]).getHubraum() > karten.get(spielerEins[index[0]]).getHubraum()) {
                    gewonnen = gewinn(index, 1);
                    if (gewonnen == true) break;
                    System.out.printf("Spieler Zwei hat die Runde gewonnen!\n");
                    scanner.nextLine();
                    spielerAmZug = 1;
                } else {
//                        Gleichstand Progammieren!!!!!
                }
                break;
            case 7:
                if (karten.get(spielerEins[index[0]]).getBeschleunigung() > karten.get(spielerZwei[index[1]]).getBeschleunigung()) {
                    gewonnen = gewinn(index, 0);
                    if (gewonnen == true) break;
                    System.out.printf("Spieler Eins hat die Runde gewonnen!\n");
                    scanner.nextLine();
                    spielerAmZug = 0;
                } else if (karten.get(spielerZwei[index[1]]).getBeschleunigung() > karten.get(spielerEins[index[0]]).getBeschleunigung()) {
                    gewonnen = gewinn(index, 1);
                    if (gewonnen == true) break;
                    System.out.printf("Spieler Zwei hat die Runde gewonnen!\n");
                    scanner.nextLine();
                    spielerAmZug = 1;
                } else {
//                        Gleichstand Progammieren!!!!!
                }
                break;
            default:
                System.out.printf("Eingabe Unerlaubt, Programm bricht ab.\n");
                System.exit(0);
        }
        return gewonnen;
    }

    private static boolean gewinn(int[] index, int gewinner) {
        if (gewinner == 0) {
            spielerEins[naechstesFreiesElement()[0]] = spielerEins[index[0]];
            int tmpIndex = naechstesFreiesElement()[0];
            if (tmpIndex < 0) {
                System.out.printf("Spieler Eins hat das Spiel Gewonnen!");
                spielerEins[index[0]] = spielerZwei[index[1]];
                spielerZwei[index[1]] = -3;
                return true;
            } else {
                spielerEins[tmpIndex] = spielerZwei[index[1]];
                spielerEins[index[0]] = -3;
                spielerZwei[index[1]] = -3;
            }
        } else if (gewinner == 1) {
            spielerZwei[naechstesFreiesElement()[1]] = spielerZwei[index[1]];
            int tmpIndex = naechstesFreiesElement()[1];
            if (tmpIndex < 0) {
                System.out.printf("Spieler Zwei hat das Spiel Gewonnen!");
                spielerZwei[index[1]] = spielerEins[index[0]];
                spielerEins[index[0]] = -3;
                return true;
            } else {
                spielerZwei[tmpIndex] = spielerEins[index[0]];
                spielerZwei[index[1]] = -3;
                spielerEins[index[0]] = -3;
            }
        }
        return false;
    }

    private static int[] naechstesElement() {
        int[] stelle = new int[]{-1, -1};
        try {
            for (int i = 0; i < spielerEins.length; i++) {
                if (spielerEins[i] != -2 && spielerEins[i] != -3) {
                    stelle[0] = i;
                    break;
                }
            }

            for (int i = 0; i < spielerZwei.length; i++) {
                if (spielerZwei[i] != -2 && spielerZwei[i] != -3) {
                    stelle[1] = i;
                    break;
                }
            }
        } catch (NullPointerException x) {
            System.out.printf("Spieler inventar noch nicht initialisiert.");
            return new int[]{-2, -2};
        }
        return stelle;
    }

    private static int[] naechstesFreiesElement() {
        int[] stelle = new int[]{-1, -1};
        try {
            for (int i = 0; i < spielerEins.length; i++) {
                if (spielerEins[i] == -2) {
                    stelle[0] = i;
                    break;
                } else if (spielerEins[i] == -3 && (stelle[0] == -1 || i < stelle[0])) {
                    stelle[0] = i;
                }
            }

            for (int i = 0; i < spielerZwei.length; i++) {
                if (spielerZwei[i] == -2) {
                    stelle[1] = i;
                    break;
                } else if (spielerZwei[i] == -3 && (stelle[1] == -1 || i < stelle[1])) {
                    stelle[1] = i;
                }
            }
        } catch (NullPointerException x) {
            System.out.printf("Spieler inventar noch nicht initialisiert.");
            return new int[]{-2, -2};
        }
        return stelle;
    }

    private static void kartenEingabe() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
    }
}