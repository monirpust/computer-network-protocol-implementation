
import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class s_server {
    public static void main(String[] args) throws Exception{
        ServerSocket ss = new ServerSocket(9999);
        Socket s = ss.accept();
        DataInputStream dis = new DataInputStream(s.getInputStream());
        int frameSize = dis.read();
        System.out.println("Frame size : "+frameSize);
        ArrayList<byte[]> frames = new ArrayList<>();
        for(int i=0;i<frameSize;i++) {
            byte[] frame = new byte[4];
            dis.read(frame);
            frames.add(frame);
            System.out.println("Received Frame ("+(i+1)+"/"+frameSize+")");
        }

        String msg = new String(decode(frames));
        System.out.print("Client  : "+msg);
        dis.close();
        s.close();
        ss.close();
    }

    static char[] decode(ArrayList<byte[]> frames) {
        int index = 0;
        char[] msg = new char[1024];
        for (byte[] frame : frames) {
            for (int j = 0; j < 4; j++) {
                msg[index] = (char) frame[j];
                index++;
            }
        }
        return msg;
    }

}