import java.io.Console;
import java.util.Scanner;

public class Kazuate {
    public static void main(String[] args) {
        Console console = System.console();
        int answer = -1;

        // コンソールが使用可能か確認（IDEなどでは null になることがある）
        if (console != null) {
            while (true) {
                try {
                    char[] input = console.readPassword("正解の2桁の正の整数を入力してください（表示されません）: ");
                    String inputStr = new String(input);
                    answer = Integer.parseInt(inputStr);

                    if (answer >= 10 && answer <= 99) {
                        break;
                    } else {
                        System.out.println("2桁の正の整数を入力してください。");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("数値として認識できません。もう一度入力してください。");
                }
            }
        } else {
            System.out.println("警告: コンソールが利用できません。IDEでは入力が隠せないことがあります。");
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("正解の2桁の正の整数を入力してください: ");
                answer = scanner.nextInt();
                if (answer >= 10 && answer <= 99) {
                    break;
                } else {
                    System.out.println("2桁の正の整数を入力してください。");
                }
            }
        }

        final int MAX_ATTEMPTS = 5;
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n数あてゲーム開始！（2桁の正の整数を当ててください）");

        for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
            System.out.print("予想した数を入力してください（" + attempt + "回目）: ");
            int guess = scanner.nextInt();

            if (guess < 10 || guess > 99) {
                System.out.println("2桁の正の整数を入力してください。");
                attempt--; // 無効な入力はカウントしない
                continue;
            }

            if (guess == answer) {
                System.out.println("当たり！");
                return;
            } else {
                int diff = Math.abs(guess - answer);
                if (guess > answer) {
                    System.out.print("設定された数より大きいです。");
                } else {
                    System.out.print("設定された数より小さいです。");
                }

                if (diff >= 20) {
                    System.out.println("（差が20以上あります）");
                } else {
                    System.out.println();
                }
            }

            if (attempt == MAX_ATTEMPTS) {
                System.out.println("ゲームオーバー！正解は " + answer + " でした。");
            }
        }

        scanner.close();
    }
}
