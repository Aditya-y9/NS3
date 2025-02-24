import java.util.*;

public class VigenereCipher {
    static Map<Integer, Integer> gaps = new HashMap<>();

    static void printMatTable(char[][] mat) {
        System.out.println("\n\nTable for this Vigenere cipher is:\n");
        for (char[] row : mat) {
            for (char c : row) {
                System.out.print(c + "   ");
            }
            System.out.println();
        }
        System.out.println("\n\n");
    }

    static void generate(char[][] mat) {
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                mat[i][j] = (char) ('A' + (i + j) % 26);
            }
        }
    }

    static List<String> findRepeatedSequences(String text, int minLength, int maxLength) {
        List<String> repeatedSequences = new ArrayList<>();

        for (int length = minLength; length <= maxLength; length++) {
            Map<String, Integer> sequenceCount = new HashMap<>();

            for (int i = 0; i <= text.length() - length; i++) {
                String sequence = text.substring(i, i + length);
                sequenceCount.put(sequence, sequenceCount.getOrDefault(sequence, 0) + 1);
            }

            for (Map.Entry<String, Integer> entry : sequenceCount.entrySet()) {
                if (entry.getValue() > 1) {
                    repeatedSequences.add(entry.getKey());
                }
            }
        }
        return repeatedSequences;
    }

    static List<String> checkForRepeated(String message) {
        int minLength = 3, maxLength = 20;
        List<String> repeatedSequences = findRepeatedSequences(message, minLength, maxLength);

        if (repeatedSequences.isEmpty()) {
            System.out.println("\n\nNo repeated sequences found.");
        } else {
            System.out.println("\n\nRepeated sequences found:");
            System.out.println("\n\nSequences \t Size:");
            for (String sequence : repeatedSequences) {
                System.out.println(sequence + "\t\t\t" + sequence.length());
            }
        }
        return repeatedSequences;
    }

    static Map<String, List<Integer>> findDistancesBetweenSequences(List<String> repeatedSequences, String message) {
        Map<String, List<Integer>> mp = new HashMap<>();
        for (String seq : repeatedSequences) {
            List<Integer> distances = new ArrayList<>();
            for (int i = 0; i <= message.length() - seq.length(); i++) {
                if (message.substring(i, i + seq.length()).equals(seq)) {
                    distances.add(i);
                }
            }
            mp.put(seq, distances);
        }
        return mp;
    }

    static void findgap(List<Integer> arr) {
        for (int i = 1; i < arr.size(); i++) {
            int gap = arr.get(i) - arr.get(i - 1);
            System.out.print(gap + " ");
            gaps.put(gap, gaps.getOrDefault(gap, 0) + 1);
        }
    }

    static void findMostCommonDivisorOfGaps() {
        Map<Integer, Integer> mp = new HashMap<>();
        for (int gap : gaps.keySet()) {
            for (int i = 2; i <= gap; i++) {
                if (gap % i == 0) {
                    mp.put(i, mp.getOrDefault(i, 0) + 1);
                }
            }
        }
        System.out.println("\n\nNo. of common divisors of gaps");
        System.out.println("\n\nSize of GAP \t count of such gaps");
        int maxCount = Collections.max(mp.values());
        for (Map.Entry<Integer, Integer> entry : mp.entrySet()) {
            if (entry.getKey() <= 20) {
                System.out.println(entry.getKey() + "  " + entry.getValue());
            }
        }
        System.out.println("\n\nMost common divisor among the found gap sizes:");
        for (Map.Entry<Integer, Integer> entry : mp.entrySet()) {
            if (entry.getValue() == maxCount) {
                System.out.println(entry.getKey() + " \n\n");
            }
        }
    }

    public static void main(String[] args) {
        char[][] mat = new char[26][26];
        generate(mat);
        printMatTable(mat);

        String message = "CVJTNAFENMCDMKBXFSTKLHGSOJWHOFUISFYFBEXEINFIMAYSSDYYIJNPWTOKFRHWVWTZFXHLUYUMSGVDURBWBIVXFAFMYFYXPIGBHWIFHHOJBEXAUNFIYLJWDKNHGAOVBHHGVINAULZFOFUQCVFBYNFTYGMMSVGXCFZFOKQATUIFUFERQTEWZFOKMWOJYLNZBKSHOEBPNAYTFKNXLBVUAXCXUYYKYTFRHRCFUYCLUKTVGUFQBESWYSSWLBYFEFZVUWTRLLNGIZGBMSZKBTNTSLNNMDPMYMIUBVMTLOBJHHFWTJNAUFIZMBZLIVHMBSUWLBYFEUYFUFENBRVJVKOLLGTVUZUAOJNVUWTRLMBATZMFSSOJQXLFPKNAULJCIOYVDRYLUJMVMLVMUKBTNAMFPXXJPDYFIJFYUWSGVIUMBWSTUXMSSNYKYDJMCGASOUXBYSMCMEUNFJNAUFUYUMWSFJUKQWSVXXUVUFFBPWBCFYLWFDYGUKDRYLUJMFPXXEFZQXYHGFLACEBJBXQSTWIKNMORNXCJFAIBWWBKCMUKIVQTMNBCCTHLJYIGIMSYCFVMURMAYOBJUFVAUZINMATCYPBANKBXLWJJNXUJTWIKBATCIOYBPPZHLZJJZHLLVEYAIFPLLYIJIZMOUDPLLTHVEVUMBXPIBBMSNSCMCGONBHCKIVLXMGCRMXNZBKQHODESYTVGOUGTHAGRHRMHFREYIJIZGAUNFZIYZWOUYWQZPZMAYJFJIKOVFKBTNOPLFWHGUSYTLGNRHBZSOPMIYSLWIKBANYUOYAPWZXHVFUQAIATYYKYKPMCEYLIRNPCDMEIMFGWVBBMUPLHMLQJWUGSKQVUDZGSYCFBSWVCHZXFEXXXAQROLYXPIUKYHMPNAYFOFHXBSWVCHZXFEXXXAIRPXXGOVHHGGSVNHWSFJUKNZBESHOKIRFEXGUFVKOLVJNAYIVVMMCGOFZACKEVUMBATVHKIDMVXBHLIVWTJAUFFACKHCIKSFPKYQNWOLUMYVXYYKYAOYYPUKXFLMBQOFLACKPWZXHUFJYGZGSTYWZGSNBBWZIVMNZXFIYWXWBKBAYJFTIFYKIZMUIVZDINLFFUVRGSSBUGNGOPQAILIFOZBZFYUWHGIRHWCFIZMWYSUYMAUDMIYVYAWVNAYTFEYYCLPWBBMVZZHZUHMRWXCFUYYVIENFHPYSMKBTMOIZWAIXZFOLBSMCHHNOJKBMBATZXXJSSKNAULBJCLFWXDSUYKUCIOYJGFLMBWHFIWIXSFGXCZBMYMBWTRGXXSHXYKZGSDSLYDGNBXHAUJBTFDQCYTMWNPWHOFUISMIFFVXFSVFRNA";  // Truncated for brevity
        System.out.println("\n\nCiphertext to be decrypted is given to us:\n\n" + message);

        List<String> repeatedSequences = checkForRepeated(message);
        Map<String, List<Integer>> distance = findDistancesBetweenSequences(repeatedSequences, message);

        for (Map.Entry<String, List<Integer>> entry : distance.entrySet()) {
            System.out.print(entry.getKey() + " ");
            for (int x : entry.getValue()) {
                System.out.print(x + " ");
            }
            System.out.print("\t\tThe distance between consecutive gaps are: ");
            findgap(entry.getValue());
            System.out.println();
        }
        System.out.println("\n\n");
        for (Map.Entry<Integer, Integer> entry : gaps.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
        findMostCommonDivisorOfGaps();

        int n = 6;
        System.out.println("\n\nfor n=6 Columnwise nth character of the cipher message\n");
        String[] toDecode = new String[n];
        Arrays.fill(toDecode, "");
        for (int i = 0; i < message.length(); i++) {
            toDecode[i % n] += message.charAt(i);
        }
        for (int i = 0; i < toDecode.length; i++) {
            System.out.println((i + 1) + "\t" + toDecode[i] + "\n");
        }

        String KEY = "BRUTUS";
        StringBuilder decipheredMessage = new StringBuilder();
        int pos = 0;
        for (int i = 0; i < message.length(); i++) {
            int row = KEY.charAt(pos % 6) - 'A';
            int col = 0;
            char cipher = message.charAt(i);
            for (int j = 0; j < 26; j++) {
                if (mat[row][j] == cipher) {
                    col = j;
                    break;
                }
            }
            decipheredMessage.append((char) ('A' + col));
            pos++;
        }
        System.out.println("\nKEY is \n\n" + KEY);
        System.out.println("\nFinal Deciphered message:\n\n" + decipheredMessage);
    }
}
