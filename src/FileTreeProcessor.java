import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import static java.nio.file.FileVisitResult.*;

public class FileTreeProcessor extends SimpleFileVisitor<Path> {
    private int thread;

    public FileTreeProcessor(int thread) {
        super();
        this.thread = thread;
    }

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attr) {
        if (attr.isRegularFile()) {
            try {
                ParseFile.parse(Files.readAllLines(file, Charset.defaultCharset()), thread);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return CONTINUE;
    }


    @Override
    public FileVisitResult postVisitDirectory(Path dir,
                                              IOException exc) {
        System.out.format("Directory: %s%n", dir);
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file,
                                           IOException exc) {
        System.err.println(exc);
        return CONTINUE;
    }

}
