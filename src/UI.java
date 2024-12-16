import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame implements ActionListener {
    public JButton playPauseButton;
    public JButton skipButton;
    public JTextField searchTextField;
    public JLabel searchBarBackground;
    public boolean musicPlaying = false; // Keeps track of the state (play or pause)
    public boolean skipButtonPressed = false;
    public MusicPlayer musicPlayer;

    public UI() {
        setLayout(null);
        setSize(1250, 925);
        setVisible(true);

        musicPlayer = new MusicPlayer();

        Color customColour = new Color(30, 31, 34);
        getContentPane().setBackground(customColour);

        // Create the top bar for the search bar
        JPanel topBar = new JPanel();
        topBar.setBounds(0, 0, 1250, 100);
        topBar.setLayout(null); // Allow custom placement of components
        topBar.setBackground(Color.black); // Match the frame background
        add(topBar);

        // Create a search bar background (use an image if available)
        searchBarBackground = new JLabel(createImageIcon("search_bar_bg.png")); // Replace with your image path
        searchBarBackground.setBounds(425, 20, 400, 60); // Adjust these values for proper alignment
        searchBarBackground.setLayout(new BorderLayout()); // Allow adding components like text fields
        topBar.add(searchBarBackground);

        // Create a text field
        searchTextField = new JTextField();
        searchTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        searchTextField.setHorizontalAlignment(JTextField.CENTER);
        searchTextField.setBorder(null); // Remove default border for cleaner look
        searchBarBackground.add(searchTextField, BorderLayout.CENTER);

        // Create the bottom bar background
        JPanel bottomBar = new JPanel();
        bottomBar.setBounds(0, 775, 1300, 150);
        bottomBar.setBackground(Color.BLACK);
        add(bottomBar);

        // Create play/pause button and set its position
        playPauseButton = new JButton();
        playPauseButton.setBounds(575, 800, 1, 50); // Adjust the button position and size
        playPauseButton.setIcon(createImageIcon("play_icon.png"));
        playPauseButton.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        playPauseButton.setContentAreaFilled(false); // Make button background transparent
        playPauseButton.addActionListener(this);

        // Create Skip Button
        skipButton = new JButton();
        skipButton.setBounds(800, 800, 1, 50); // Adjust the button position and size
        skipButton.setIcon(createImageIcon("skip_icon.png"));
        skipButton.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        skipButton.setContentAreaFilled(false); // Make button background transparent
        skipButton.addActionListener(this);

        bottomBar.add(playPauseButton); // Add the button to the panel
        bottomBar.add(skipButton);

        add(bottomBar);
    }

    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = UI.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            return null;
        }
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();

        if (source == skipButton) {
            musicPlayer.skipSong();
        }

        if (source == playPauseButton) {
            if (musicPlaying) {
                musicPlayer.pauseAudio();
                playPauseButton.setIcon(createImageIcon("play_icon.png"));
            } else {
                musicPlayer.playAudio();
                playPauseButton.setIcon(createImageIcon("pause_icon.png"));
            }
            musicPlaying = !musicPlaying;
        }
    }

    public static void main(String[] args) {
        new UI();
    }
}
