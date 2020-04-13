import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Note {
    private String theme;
    private String email;
    private String message;
    private Date date;

    public Note(String theme, String email, String message) {
        this.theme = theme;
        this.email = email;
        this.message = message;
        date = new Date();
    }

    public Note(String all) throws ParseException {
        Pattern pattern = Pattern.compile("Date:.*\n");
        Matcher matcher = pattern.matcher(all);
        if (matcher.find()) {
            String dateStr = all.substring(matcher.start(), matcher.end());
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy", Locale.ENGLISH);
            date = sdf.parse(dateStr.substring(" Date: ".length() - 1));
        }
        this.email = Parser.parseItem(" Email: ", all);
        this.theme = Parser.parseItem(" Theme: ", all);
        this.message = Parser.parseItem(" Message: ", all);
    }

    public String getTheme() {
        return theme;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {

        return date;
    }

    @Override
    public String toString() {
        return "Note:" + '\n' +
                " Date: " + date + '\n' +
                " Email: " + email + '\n' +
                " Theme: " + theme + '\n' +
                " Message: " + message + '\n';
    }
}