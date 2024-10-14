package THF_Encryption.Cypher;

public class T_Cypher {
    /**
     * Defines a Cypher and Substitution method that encrypts or decrypts a byte array by repeatedly applying array substitution based on the given parameter c.
     * @param text byte array
     * @param itr amount of iterations
     * @return cyphered text*/
    public static byte[] TreeCypher(byte[] text, long itr) throws Exception {
        int s = 2,n = 0;
        while (s < text.length){ s*=2; n++;}
        if (itr > 0) return ArraySubstitution(text, n);
        if (itr < 0) return ArrayDeSubstitution(text, n);
        return text;
    }
    private static byte[] ArraySubstitution(byte[] text,int size) throws Exception {
        int l = text.length;
        int[] ea = T_Indexation.TreeArray(size);
        byte[] out = new byte[l];
        for (int n = 0; n < l; n++){
            out[ea[n]] = text[n];
        }
        return out;
    }
    private static byte[] ArrayDeSubstitution(byte[] text,int size) throws Exception {
        int l = text.length;
        int[] ea = T_Indexation.TreeArray(size);
        byte[] out = new byte[l];
        for (int n = 0; n < l; n++){
            out[n] = text[ea[n]];
        }
        return out;
    }
}
