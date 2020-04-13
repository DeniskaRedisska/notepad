import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Menu {
    public static void options() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        NotePad notePad = new NotePad();
        while (true) {
            System.out.println("options:");
            System.out.println("1: add note");
            System.out.println("2: read notes");
            System.out.println("3: read sorted");
            System.out.println("4: search");
            System.out.println("5: exit");
            choice = scanner.nextInt();
            try {
                switch (choice) {

                    case 1:
                        notePad.createNote();
                        break;
                    case 2:
                        notePad.shownNotes();
                        break;
                    case 3:
                        notePad.showSorted();
                        break;
                    case 4:
                        int selectParam;
                        System.out.println("search by:");
                        System.out.println("1: date\n2: email\n3: theme\n4: message");
                        Scanner scanner1=new Scanner(System.in);
                        selectParam = scanner.nextInt();
                        String selectSubject = scanner1.nextLine();
                        switch (selectParam) {
                            case 1:
                                System.out.println( notePad.search("Date", selectSubject));;
                                break;
                            case 2:
                                System.out.println(notePad.search("Email", selectSubject));;
                                break;
                            case 3:
                                System.out.println(notePad.search("Theme", selectSubject));
                                break;
                            case 4:
                                System.out.println(notePad.search("Message", selectSubject));
                                break;
                        }
                        break;
                    case 5:
                        return;
                }
            } catch (IOException | ParseException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
