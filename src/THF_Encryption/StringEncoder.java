package THF_Encryption;


public class StringEncoder {
    static int size = 0;
    static int len = 0;

    public static byte[] ArraySubstitution(byte[] text){
        int[] ea = TreeIndexation.TreeArray(size-1);
        byte[] out = new byte[len];
        for (int n = 0; n < len; n++){
            out[ea[n]] = text[n];
        }
        return out;
    }
    public static byte[] ArrayDeSubstitution(byte[] text){
        int[] ea = TreeIndexation.TreeArray(size-1);
        byte[] out = new byte[len];
        for (int n = 0; n < len; n++){
            out[n] = text[ea[n]];
        }
        return out;
    }
    /**
     * Converts each character of the text, into two hex values.
     *
     * @param text for converting
     * @return hex int array
     */
    public static byte[] Hex(String text) {
        int s = text.length();
        byte[] out = new byte[ s * 2 ];
        for (int i = 0; i < s*2; i+=2) {
            out[i] = (byte) (text.charAt(i/2)/10);
            out[i+1] = (byte) (text.charAt(i/2)%10);
        }
        return out;
    }

    /**
     * Extends the given Hex text while (len < (2 ^ n))
     *
     * @param text for extending
     * @return extended text
     */
     public static byte[] Extender(byte[] text) {
         int s = 1;
         int n = 0;
         int l = text.length;
         while (s < l){
             s *= 2;
             n++;
         }
         len = (s - 1);
         size = n;
         if(l == s){
             text = Fuse(text, new byte[]{0}); //
             Extender(text);
         }
         return Fuse(text,FlexHash.Flex(text,s-l));
     }

    public static String unHex(byte[] arr){
        StringBuilder out = new StringBuilder();
        for (int n = 0; n < arr.length-1; n+=2){
            out.append((char) (arr[n]*10+arr[n+1]));
        }
        return out.toString();
    }

    public static void main(String[] args) {
        String text = "This is a test message and it is obfuscated by the THF encrypter.";
        byte[] res = Extender(Hex(text));
        System.out.println(unHex(res));

        for (int n = 0; n < 1572869; n++){
            res = ArraySubstitution(res);
        }

        System.out.println(unHex(res));

        for (int n = 0; n < 1572869; n++){
            res = ArrayDeSubstitution(res);
        }
        System.out.println(unHex(res));

    }
    public static void CleanUp(String text){


    }
    public static byte[] Fuse(byte[] a, byte[] b) {
        byte[] r = new byte[a.length + b.length];
        System.arraycopy(a, 0, r, 0, a.length);
        System.arraycopy(b, 0, r, a.length, b.length);
        return r;
    }

}
