package THF_Encryption.Cypher;

public class S_Cypher {
    public static byte[] ShuffleCypher(byte[] text, String key, boolean isForward) {
        int[] alphabet = new int[ text.length];
        for (int i = 0; i < text.length; i++) {
            alphabet[i] = key.charAt(i % key.length());
        }
        if (isForward) {
            for (int c = 0; c < text.length; c++) {
                swap(text, c, alphabet[c] % text.length);
            }
        } else {
            for (int c = text.length - 1; c >= 0; c--) {
                swap(text, c, alphabet[c] % text.length);
            }
        }
        return text;
    }
    private static void swap(byte[] array, int i, int j) {
        byte temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    public static void main(String[] args) {
        System.out.println();
    }
}
