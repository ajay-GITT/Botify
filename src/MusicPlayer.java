import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class MusicPlayer {
    private Clip currentSong; // the Clip class basically allows you to start and stop audio
    private boolean isPaused = false;
    private boolean justSkipped = false;
    private long clipPosition = 0;
    private SongQueue songQueue;
    private String currentSongPath;


    public MusicPlayer() {
        songQueue = new SongQueue();

        // Fill up the song queue:
        String folderPath = "src/Songs";
        File folder = new File(folderPath);


        // Check if the folder exists and is a directory
        if (folder.exists() && folder.isDirectory()) {
            // Get all files in the folder
            File[] allFiles = folder.listFiles();

            if (allFiles != null) {
                for (int i = 0; i < allFiles.length; i++) {
                    // Check if the file ends with .wav
                    if (allFiles[i].getName().toLowerCase().endsWith(".wav")) {
                        songQueue.addSong(allFiles[i].getAbsolutePath()); // Add the full path
                    }
                }
            }
        } else {
            System.out.println("The specified folder does not exist or is not a directory.");
        }

        if (!songQueue.isEmpty()) {
            currentSongPath = songQueue.getNextSong();
        }
    }

    public void playAudio() {
        try {
            if (currentSong == null || !currentSong.isOpen() || justSkipped) {
                currentSong = AudioSystem.getClip(); // Checks the src for a song
                currentSong.open(AudioSystem.getAudioInputStream(new File(currentSongPath))); // gets the file path, and then opens it for audio reading
            }

            if (isPaused) {
                currentSong.setMicrosecondPosition(clipPosition); // gets the paused posistion, and then runs it from there
                isPaused = false; // sets to false
            }

            currentSong.start();


        } catch (Exception exception) { // if any type of exception happens, run the dialog box
            JOptionPane.showMessageDialog(null, "Error playing audio: " + exception.getMessage());
        }

    }

    public void pauseAudio() {
        if (currentSong != null && currentSong.isRunning()) { // If its not null (meaning it is initalized to something), and the current song is CURRENTLY RUNNING
            clipPosition = currentSong.getMicrosecondPosition(); // It actually stores the paused posision
            currentSong.stop(); // and then DOES NOT run from there
            isPaused = true; // sets isPaused to true
        }
    }


    public void skipSong() {
        justSkipped = true;
        currentSongPath = songQueue.getNextSong();
        clipPosition = 0;
        pauseAudio();
        isPaused = false;
        playAudio();
    }

}