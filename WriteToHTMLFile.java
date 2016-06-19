import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;

public class WriteToHTMLFile {

    String filename;
    String linetext;
    String pwd = System.getProperty("user.dir");
    public WriteToHTMLFile(String filename) {
        this.filename = filename;
    }

    public void clearFile() {
        try{
            String dirName = pwd+"/html/";
            File dir = new File (dirName);
            File actualFile = new File (dir, filename);
            FileWriter fw = new FileWriter(actualFile, false);
            PrintWriter pw = new PrintWriter(fw);
            pw.print("");
            pw.flush();
            pw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertLine(String linetext) {
        try{
            String dirName = pwd+"/html/";
            File dir = new File (dirName);
            File actualFile = new File (dir, filename);
            FileWriter fw = new FileWriter(actualFile, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(linetext);
            pw.flush();
            pw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
