import java.util.*;


public class Crypto {
    public static void main(String[] args) {

        System.out.println("Methods of encrypting and decrypting text");

        Scanner input = new Scanner(System.in);
        System.out.print("Write text: ");
        String text = input.nextLine();

        //Part 1 - Normalize Text
        System.out.println(normalizeText(text));

        //Part 2 - Caesar Cipher
        System.out.print("Enter shift value: ");
        int shiftValue = input.nextInt();
        System.out.println(caesarify(text, shiftValue));

        //Part 3 - Code Groups
        System.out.print("Enter size to make code groups: ");
        int groupSize = input.nextInt();
        text = normalizeText(text);
        String codeGroupedString = groupify(caesarify(text,shiftValue), groupSize);

        //Part 4 - Putting it all together
        System.out.println("Encrypting string... ");
        String cypherText = encryptString(codeGroupedString,shiftValue,groupSize);
        System.out.println(cypherText);

        //Part 5 - Hacker Problem - Decrypt
        ungroupify(codeGroupedString);
        System.out.println("Decrypting string...");
        String plainText = decryptString(cypherText,shiftValue);
        System.out.println(plainText);
    }

    public static String normalizeText(String t){

        //Removes all the spaces from your text
        // & Remove any punctuation (. , : ; ’ ” ! ? ( ) )
       String temp = t.replaceAll("[^\\p{L}\\p{N}]", "");

        //Turn all lower-case letters into upper-case letters
        temp = temp.toUpperCase();
        return temp;
    }

    public static String caesarify(String t, int sValue){
        String temp = shiftAlphabet(sValue);
        //Encrypting normalized text to encrypted key
        temp = t.replace(t,temp);
        return temp;
    }

    public static String shiftAlphabet(int shift) {
        int start = 0;
        if (shift < 0) {
            start = (int) 'Z' + shift + 1;
        } else {
            start = 'A' + shift;
        }
        String result = "";
        char currChar = (char) start;

        for(; currChar <= 'Z'; ++currChar) {
            result = result + currChar;
        }
        if(result.length() < 26) {
            for(currChar = 'A'; result.length() < 26; ++currChar) {
                result = result + currChar;
            }
        }
        return result;
    }
    

   public static String groupify(String text, int number) {
       StringBuilder sb = new StringBuilder();
       for (int i = 0; i < text.length(); i++) {
           if (i != 0 && i % number == 0) {
               sb.append(' ');
           }
           sb.append(text.charAt(i));
       }
       int pad = text.length() % number;
       for (int i = 0; i < pad; i++) {
           sb.append('X');
       }
       return sb.toString();
   }


    public static String encryptString(String t, int sValue, int n)
    {
        String temp = "";
        temp = normalizeText(t);
        temp = caesarify(t,sValue);
        temp = groupify(t,n);

        return temp;
    }

    public static String ungroupify(String groupedStr){
        String temp = groupedStr.replaceAll("[\\sx]", "");
        return temp;
    }

    public static String decryptString(String t, int sValue){

        return ungroupify(t);
    }
}

