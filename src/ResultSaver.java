import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class ResultSaver {
    private static File file;
    private static FileChannel channel;
    private static FileOutputStream fileOutputStream;

    public static void setFilename(File f) {
        file = f;
        try {
            System.out.println(f.getAbsolutePath());
            fileOutputStream = new FileOutputStream(file);
            channel = fileOutputStream.getChannel();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static synchronized void save(String s, int thread) {
        try {
            FileLock lock = channel.lock();
            String result = "Thread: " + thread + " data: " + s + "\n";
            channel.write(ByteBuffer.wrap(result.getBytes()));
            lock.release();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
