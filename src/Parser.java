import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    static public String parseItem(String item, String all) {
        Pattern pattern = Pattern.compile(item.trim() + ".*\n");
        Matcher matcher = pattern.matcher(all);
        if (matcher.find()) {
            String token = all.substring(matcher.start(), matcher.end() - 1);
            return token.substring(item.length() - 1);
        } else {
            return null;
        }
    }
}
