import java.util.ArrayList;

public class SongQueue {
    ArrayList<String> songs;

    public SongQueue() {
        songs = new ArrayList<String>();
    }

    public boolean isEmpty() {
        if (songs.isEmpty()) { // If the song has no elements(meaning no songs in the queue), then return true, meaning the method is true.
            return true;
        } else {
            return false; // Otherwise, there are songs in the queue
        }
    }

    public void addSong(String songName) {
        songs.add(songName);
    }

    public void addSongs(ArrayList<String> songs) {
        this.songs.addAll(songs);
    }

    public void addSongToFront(String songName) {
        songs.add(0, songName);
    }

    public void clearSongQueue() {
        songs.clear();
    }

    public String getNextSong() {
        if (isEmpty()) {
            return ""; // If the list has no elements, exit the method.
        } else {
            String nextSong = songs.get(0); // Get the first song in the queue and then puts it into the nextSong variable
            songs.remove(0); // Remove the first song from the queue
            return nextSong; // Return the song we just obtained to where we call the method
        }
    }



    public String toString() {
        return songs.toString();
    }

}
