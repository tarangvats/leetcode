package daily.september24;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BraceExpansionII {
    private final String expression;
    private int index;

    public BraceExpansionII(String expression) {
        this.expression = expression;
        this.index = 0;
    }

    public List<String> expand() {
        Set<String> result = parseList();
        List<String> answer = new ArrayList<>(result);
        Collections.sort(answer);
        return answer;
    }

    private Set<String> parseList() {
        Set<String> result = new HashSet<>();

        while (index < expression.length()) {
            Set<String> product = new HashSet<>();
            product.add("");

            while (index < expression.length() && expression.charAt(index) != ',' && expression.charAt(index) != '}') {
                Set<String> atom = parseAtom();
                Set<String> next = new HashSet<>();

                for (String prefix : product) {
                    for (String suffix : atom) {
                        next.add(prefix + suffix);
                    }
                }

                product = next;
            }

            result.addAll(product);

            if (index < expression.length() && expression.charAt(index) == ',') {
                index++;
                continue;
            }

            break;
        }

        return result;
    }

    private Set<String> parseAtom() {
        char ch = expression.charAt(index);

        if (ch == '{') {
            index++;
            Set<String> group = parseList();
            if (index < expression.length() && expression.charAt(index) == '}') {
                index++;
            }
            return group;
        }

        index++;
        return new HashSet<>(Collections.singleton(String.valueOf(ch)));
    }

    public static List<String> braceExpansionII(String expression) {
        BraceExpansionII parser = new BraceExpansionII(expression);
        return parser.expand();
    }

    public static void main(String[] args) {
        System.out.println(braceExpansionII("{a,b}{c,{d,e}}"));
        System.out.println(braceExpansionII("{{a,z},a{b,c},{ab,z}}"));
    }
}
