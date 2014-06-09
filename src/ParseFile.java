import java.util.List;

public class ParseFile {
    public static void parse(List<String> input, int thread) {
        for (String s : input) {
            if (!s.isEmpty()) {
                ResultSaver.save(s, thread);
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
