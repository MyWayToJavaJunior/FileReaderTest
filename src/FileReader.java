import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileReader {
    private static ExecutorService executorService;
    private static String filename = "/home/kirill/out.txt";

    public static void main(String[] args) {
        //executorService = Executors.newFixedThreadPool(20);
        executorService = Executors.newSingleThreadExecutor();
        //executorService = Executors.newCachedThreadPool();


        File f = new File(filename).getAbsoluteFile();
        ResultSaver.setFilename(f);


        Path root = Paths.get(args[0]);
        DirectoryStream<Path> stream = null;
        try {
            stream = Files.newDirectoryStream(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int i = 0;
        long starttime = System.nanoTime();
        for (Path p : stream) {
            Runnable r = new ReadDir(p, i);
            executorService.submit(r);
            i++;
        }
        executorService.shutdown();
        while (true) {
            if(executorService.isTerminated()) {
                long endtime = System.nanoTime();
                System.out.println("Time: " + (endtime - starttime)/(1000*1000*1000d));
                return;
            }
        }
    }
}
