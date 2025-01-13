import java.util.ArrayList;
import java.io.File;

public class SongDirectory {
    private ArrayList<String> songs;

    public SongDirectory(String folderName) {
        songs = new ArrayList<String>();

        // Fill up the list of songs:
        String folderPath = "src/Songs/ALL SONGS/" + folderName;
        File folder = new File(folderPath);

        // Check if the folder exists and is a directory
        if (folder.exists() && folder.isDirectory()) {
            // Get all files in the folder
            File[] allFiles = folder.listFiles();

            if (allFiles != null) {
                for (int i = 0; i < allFiles.length; i++) {
                    // Check if the file ends with .wav
                    if (allFiles[i].getName().toLowerCase().endsWith(".wav")) {
                        songs.add(allFiles[i].getAbsolutePath()); // Add the full path
                    }
                }
            }
        } else {
            System.out.println("The specified folder does not exist or is not a directory.");
        }
    }

    public ArrayList<String> getSongs() {
        return songs;
    }
}
