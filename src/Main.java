import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static ArrayList<String> pairs = new ArrayList<>();
    public static ArrayList<Character> openings = new ArrayList<>();
    public static ArrayList<Character> closings = new ArrayList<>();

    public static void main(String[] args) {
        pairs.add("()");
        pairs.add("[]");
        pairs.add("{}");
        openings.add('(');
        openings.add('[');
        openings.add('{');
        closings.add(')');
        closings.add(']');
        closings.add('}');

        Scanner s = new Scanner(System.in);

        int stringCount = s.nextInt();
        s.nextLine();

        for (int i = 0; i < stringCount; i++) {
            String line = clean(s.nextLine());

            if (line.length() == 0){
                System.out.println("YES");
                continue;
            }
            if (line.length() % 2 > 0) {
                System.out.println("NO");
                continue;
            }
            loopSolution(line);
            stackSolution(line);
        }

    }

    private static void loopSolution(String line) {
        for (int j = 0; j < line.length()-1; j += 1) {

            if (pairs.contains(line.substring(j, j+2))) {
                StringBuilder newLineBuilder = new StringBuilder();
                if (j > 0) {
                    newLineBuilder.append(line, 0, j);
                }
                if (j < line.length()-2) {
                    newLineBuilder.append(line, j+2, line.length());
                }
                line = newLineBuilder.toString();
                if (j == 0) j--;
                else j-=2;
            }
        }
        if (line.length() > 0) System.out.println("NO");
        else System.out.println("YES");
    }

    private static void stackSolution(String line) {
        Stack<Character> stack = new Stack<>();

        for (char c : line.toCharArray()) {
            if (openings.contains(c)) stack.push(c);
            else if (closings.contains(c)) {
                if (!pairs.contains(stack.pop().toString() + c)) {
                    System.out.println("NO");
                    return;
                }
            }
        }
        System.out.println("YES");

    }

    private static String clean(String line) {
        Pattern p = Pattern.compile("([{}\\[\\]()])");
        Matcher m = p.matcher(line);
        StringBuilder cleanedBuilder = new StringBuilder();
        while (m.find()) cleanedBuilder.append(m.group());
        return cleanedBuilder.toString();
    }

}
