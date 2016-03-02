package team.migz.and.virgil.notepad;

import java.io.*;

/**
 * Created by migz on 3/2/16.
 */
public class Utils {

    public static String readAsString(File file) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line;

        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        return sb.toString();
    }

}
