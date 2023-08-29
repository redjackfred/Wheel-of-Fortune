import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.lang.StringBuilder;
import java.util.Scanner;

public class WheelOfFortuneMain {
    public static void main(String[] args) {

        WheelOfFortuneMain wheelOfFortune = new WheelOfFortuneMain();

        List<String> phraseList=null;
        // Get the phrase from a file of phrases
        try {
            phraseList = Files.readAllLines(Paths.get("phrases.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }

        // Get a random phrase from the list
        Random rand = new Random();
        int r= rand.nextInt(3); // gets 0, 1, or 2
        String phrase = phraseList.get(r);
        System.out.println(phrase);

        // Replace all letters from the phrase with *
        int asterisksNum = 0;
        StringBuilder sb = new StringBuilder(phrase);
        for(int i=0;i<sb.length();i++){
            if(Character.isLetter(sb.charAt(i))){
                sb.setCharAt(i,'*');
                asterisksNum++;
            }
        }

        // Scan input from user
        int wrongLimit = 8;
        int succeedCounts = 0;
        boolean succeed;
        while(wrongLimit>0 && succeedCounts!=asterisksNum) {
            succeed = false;
            System.out.println(sb);
            System.out.print("Please guess one letter:");
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();

            for(int i=0;i<phrase.length();i++){
                // If succeed
                if(Character.toLowerCase(phrase.charAt(i))==Character.toLowerCase(s.charAt(0))){
                    sb.setCharAt(i,Character.toLowerCase(s.charAt(0)));
                    succeedCounts++;
                    succeed = true;
                }
            }

            if(!succeed) {
                System.out.println("You miss, you have "+wrongLimit+" chance"+((wrongLimit==1)?"":"s")+" left.");
                wrongLimit--;
            }
        }

        if(wrongLimit==0) System.out.println("You Lose...");
        else System.out.println("You Win!");

    }

}