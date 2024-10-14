package THF_Encryption.Cipher;

import THF_Encryption.Hash.FlexHash;

import static THF_Encryption.Instruments.Instruments.printa;


public class A_Cipher {

    public static byte[] obfuscatory(byte[] input, byte[] psw){
        byte[] out = new byte[input.length];
        for (int n = 0; n < input.length; n++){
            out[n] = (byte) (input[n] + psw[n]);
        }
        return out;
    }
    public static byte[] deobfuscatory(byte[] input, byte[] psw){
        byte[] out = new byte[input.length];
        for (int n = 0; n < input.length; n++){
            out[n] = (byte) (input[n] - psw[n]);
        }
        return out;
    }

    public static void main(String[] args) throws Exception {
        int e_mul = 2;
        byte[] array = "Hello Wiolder".getBytes();
        byte[] hash  = FlexHash.Flex(array, 24);
        System.out.println();
        printa(array);
        printa(obfuscatory(array,hash));
        printa(deobfuscatory(obfuscatory(array,hash),hash));
    }
}
