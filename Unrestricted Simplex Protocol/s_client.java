import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class s_client {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost",9999);

        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);

        //DataInputStream dis = new DataInputStream(System.in);

        System.out.println("Enter the message");
        String msg = br.readLine();

        ArrayList<byte[]> frames = encode(msg);
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        dos.write(frames.size());
        for (int i =0;i<frames.size();i++) {
            System.out.println("Sending Frame - " + (i + 1) + " : " + Arrays.toString((frames.get(i))));
            dos.write(frames.get(i));
        }
        dos.close();
        s.close();
    }

    static ArrayList<byte[]> encode(String msg) {
        int index = 0;;
        ArrayList<byte[]> frames = new ArrayList<>();
        while(!msg.isEmpty()) {
            byte[] frame = new byte[4];
            for (int i = 0; i < 4; i++) {
                if (msg.isEmpty())
                    break;
                frame[i] = (byte) msg.charAt(0);
                msg = msg.substring(msg.indexOf(frame[i])+1);

            }
            frames.add(index,frame);
            index++;
        }
        return frames;
    }

}