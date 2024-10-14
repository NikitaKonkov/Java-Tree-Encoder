package THF_Encryption;


import static THF_Encryption.Cipher.T_Cipher.TreeCypher;
import static THF_Encryption.Instruments.Instruments.*;

public class EncodingEngine {


    public static void main(String[] args) throws Exception {
        String text = "A B C D E F G H I J K";
        String psw  = "1234abcd";

        byte[] raw = text.getBytes(); // 1
        printa(raw);

        raw = Fuser(raw,Signer(text)); // 2
        printa(raw);

        raw = Disassembler(raw);
        printa(raw);

        raw = Extender(raw);
        printa(raw);

        raw = TreeCypher(raw, 1);
        printa(raw);

        raw = TreeCypher(raw, -1);
        printa(raw);

        raw = Assembler(raw);
        printa(raw);

        text = Filter(raw);
        System.out.println(text);
    }
}
