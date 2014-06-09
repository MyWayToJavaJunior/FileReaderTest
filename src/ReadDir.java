import java.io.IOException;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadDir implements Runnable {
    private Path path;
    private int thread;

    public ReadDir(Path path, int thread) {
        this.path = path;
        this.thread = thread;
    }

    @Override
    public void run() {
            FileVisitor<Path>  visitor = new FileTreeProcessor(thread);
            try {
                Files.walkFileTree(path, visitor);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
