import java.util.*;

public class Main {
    public static void main(String[] args) {
        boolean isPlayerXTurn = true;
        HashMap<Integer, String> gamingMap = createMap();
        printMap(gamingMap);
        getUserInput(gamingMap, isPlayerXTurn);
    }


    public static void findWinnerFromMap(HashMap<Integer, String> gamingMap) {

        int[][] winningCombinations = {
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9},
                {1, 4, 7}, {2, 5, 8}, {3, 6, 9},
                {1, 5, 9}, {3, 5, 7}
        };

        for (int[] win3Numbers : winningCombinations){
            String firstSymbol = gamingMap.get(win3Numbers[0]);
            String secondSymbol = gamingMap.get(win3Numbers[1]);
            String thirdSymbol = gamingMap.get(win3Numbers[2]);

            if (!firstSymbol.equals(" ") && firstSymbol.equals(secondSymbol) && secondSymbol.equals(thirdSymbol)){
                System.out.println("Víťazom je hráč s symbolom: " + firstSymbol);
                printMap(gamingMap);
                System.exit(0);
            }
        }
    }


    public static boolean mapIsFull(HashMap<Integer, String> gamingMap) {

        if (gamingMap.containsValue(" ")) {
            return false;
        } else {
            return true;
        }
    }


    public static void getUserInput(HashMap<Integer, String> gamingMap, boolean isPlayerXTurn) {

        Scanner scanner = new Scanner(System.in);
        int numberFromUser = 0;
        while (true) {
            try {
                if (isPlayerXTurn) {
                    System.out.println("Hráč X, zadaj číslo pozície na mape.");
                } else {
                    System.out.println("Hráč 0, zadaj číslo pozície na mape.");
                }
                numberFromUser = scanner.nextInt();
                if (numberFromUser >= 1 && numberFromUser < 10) {
                    checkIfPositionIsFreeAndWrite(gamingMap, numberFromUser, isPlayerXTurn);
                } else {
                    System.out.println("Číslo musí byť medzi 1 a 9. Skús znova!");
                }
            } catch (Exception e) {
                System.out.println("input nieje číslo!");
                scanner.next();
            }
        }
    }


    public static void checkIfPositionIsFreeAndWrite(HashMap<Integer, String> gamingMap, Integer numberFromUser, boolean isPlayerXTurn) {

        if (Objects.equals(gamingMap.get(numberFromUser), " ")) {
            if (isPlayerXTurn) {
                gamingMap.put(numberFromUser, "X");
                findWinnerFromMap(gamingMap);
                isPlayerXTurn = !isPlayerXTurn;
                printMap(gamingMap);
                if (mapIsFull(gamingMap)) {
                    System.out.println("Mapa je plná, hra skončila remízou.");
                    System.exit(0);
                } else {
                    getUserInput(gamingMap, isPlayerXTurn);
                }
            } else {
                gamingMap.put(numberFromUser, "0");
                findWinnerFromMap(gamingMap);
                isPlayerXTurn = !isPlayerXTurn;
                printMap(gamingMap);
                if (mapIsFull(gamingMap)) {
                    System.out.println("Mapa je plná, hra skončila remízou.");
                    System.exit(0);
                } else {
                    getUserInput(gamingMap, isPlayerXTurn);
                }
            }
        } else {
            System.out.println("Pozícia na mape je obsadená. Zadaj iné číslo pozície.");
            getUserInput(gamingMap, isPlayerXTurn);
        }
    }


    public static HashMap<Integer, String> createMap() {

        HashMap<Integer, String> gamingMap = new HashMap<>();
        for (int i = 1; i <= 9; i++) {
            gamingMap.put(i, " ");
        }
        return gamingMap;
    }


    public static void printMap(HashMap<Integer, String> gamingMap) {

        int orderOfNumberInRow = 0;
        int RowNumber = 0;
        StringBuilder row = new StringBuilder();
        for (Map.Entry<Integer, String> entry : gamingMap.entrySet()) {
            row.append(entry.getValue());
            orderOfNumberInRow += 1;
            if (orderOfNumberInRow != 3) {
                row.append("|");
            }
            if (orderOfNumberInRow == 3) {
                RowNumber += 1;
                System.out.println(row);
                if (RowNumber < 3) {
                    System.out.println("-----");
                }
                row.setLength(0);
                orderOfNumberInRow = 0;
            }
        }
    }

}


