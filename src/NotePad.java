import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NotePad {
    private final String FILEPATH = "Notes.txt";

    private void writeToFile(Note note) throws IOException {
        File newFile = new File(FILEPATH);
        BufferedWriter writer = new BufferedWriter(new FileWriter(newFile, true));
        writer.write(note.toString() + System.getProperty("line.separator"));
        writer.close();
    }
    public void shownNotes() throws IOException {
        Path path = Paths.get(FILEPATH);
        Scanner scanner = new Scanner(path);
        while(scanner.hasNextLine()){
            System.out.println(scanner.nextLine());
        }
    }

    public void createNote() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String email = scanner.nextLine();
        Pattern pattern = Pattern.compile("\\w+@[^\\W,\\d]+\\.[^\\W,\\d]");
        Matcher matcher = pattern.matcher(email);
        String validatedEmail = "";
        if (matcher.find()) {
            validatedEmail = email;
        } else {
            System.out.println("invalid e-mail");
            return;
        }
        String theme = scanner.nextLine();
        String message = scanner.nextLine();
        Note note = new Note(theme, validatedEmail, message);
        writeToFile(note);
    }

    public List<Note> search(String param, String text) throws IOException, ParseException {
        Path path = Paths.get(FILEPATH);
        Scanner scanner = new Scanner(path);
        Pattern pattern = Pattern.compile("^\\s*$");
        Pattern pattern1 = Pattern.compile(param + ":.*" + text);
        List<Note> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                String token = scanner.nextLine();
                Matcher matcher = pattern.matcher(token);
                if (!matcher.find()) {
                    sb.append(token).append("\n");
                } else {
                    break;
                }
            }
            Matcher matcher1 = pattern1.matcher(sb.toString());
            if (matcher1.find()) {
                list.add(new Note(sb.toString()));
            }
        }
        return list;
    }

    //sort from recent to old
    public void showSorted() throws IOException, ParseException {
        List<Note> list = search("", "");
        list.sort(((note1, note2) -> {
            if (note1.getDate().equals(note2.getDate())) return 0;
            return note1.getDate().before(note2.getDate()) ? 1 : -1;
        }));
        System.out.println(list);
    }
}
