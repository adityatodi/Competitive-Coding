import java.util.Scanner;

public class AmazonDecoder {
    public static String decoder(String s) {
        int n = s.length();
        int val = 0;
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<n; i++) {
            if (Character.isDigit(s.charAt(i)))
                val = val*10 + ((int)s.charAt(i) - 48) ;
            else {
                System.out.println(val);
                while(val > 0) {
                    sb.append(s.charAt(i));
                    val--;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Encoded String: ");
        String s = sc.nextLine();
        System.out.println("Decoded String\n" + decoder(s));
    }
}