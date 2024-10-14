package THF_Encryption.Crypter;

import THF_Encryption.Hash.FlexHash;


public class A_Crypt {

    public static byte[] obfuscatory(byte[] input, byte[] psw){
        byte[] out = new byte[input.length];
        for (int n = 0; n < input.length; n++){
            out[n] = (byte) (input[n] + psw[n]);
        }
        return out;
    }


    public static void main(String[] args) throws Exception {
        int e_mul = 2;
        byte[] array = "Hello Wiolder".getBytes();
        byte[] hash  = FlexHash.Flex(array, 24);
        System.out.println();
    }
}
