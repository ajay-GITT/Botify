import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame implements ActionListener {
    public JButton playPauseButton;
    public JButton skipButton;
    public JTextField searchTextField;
    public JLabel searchBarBackground;
    public boolean musicPlaying = false; //  track the state (playing or pause)
    public MusicPlayer musicPlayer;

    public UI() {
        setLayout(null);
        setSize(1250, 925);
        setVisible(true);

        musicPlayer = new MusicPlayer();

        Color customColour = new Color(30, 31, 34);
        getContentPane().setBackground(Color.black);

        // Create the top bar for the search bar
        JPanel topBar = new JPanel();
        topBar.setBounds(0, 0, 1250, 100);
        topBar.setLayout(null); // Allow custom placement of components
        topBar.setBackground(Color.black); // Match the frame background
        add(topBar);

        JLabel logoLabel = new JLabel(createImageIcon("botify_logo.png")); // Replace with the logo file name
        logoLabel.setBounds(10, 10, 100, 80); // Adjust position and size of the logo
        topBar.add(logoLabel);

        // Create a search bar background (use an image if available)
        searchBarBackground = new JLabel();
        searchBarBackground.setBounds(425, 20, 400, 60);
        searchBarBackground.setLayout(new BorderLayout());
        topBar.add(searchBarBackground);

        // Create a text field
        searchTextField = new JTextField();
        searchTextField.setBackground(new Color(240, 240, 240));
        searchTextField.setFont(new Font("Arial", Font.PLAIN, 16));
//        searchTextField.setBorder(null); // Remove default border for cleaner look
        searchBarBackground.add(searchTextField, BorderLayout.CENTER);

        // Create the bottom bar background
        JPanel bottomBar = new JPanel();
        bottomBar.setBounds(0, 775, 1300, 150);
        bottomBar.setBackground(Color.black);
        add(bottomBar);

        // Create play/pause button and set its position
        playPauseButton = new JButton();
        playPauseButton.setBounds(575, 800, 1, 50);
        playPauseButton.setIcon(createImageIcon("play_icon.png"));
        playPauseButton.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1)); // adds/removes outline of hitbox
        playPauseButton.setContentAreaFilled(false); // transparent bg
        playPauseButton.addActionListener(this); // adds to actionlistener to allow it to work

        // Create Skip Button
        skipButton = new JButton();
        skipButton.setBounds(800, 800, 100, 50);
        skipButton.setIcon(createImageIcon("skip_icon.png"));
        skipButton.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        skipButton.setContentAreaFilled(false); // Make button background transparent
        skipButton.addActionListener(this);

        bottomBar.add(playPauseButton); // Add the button to the panel
        bottomBar.add(skipButton);

        add(bottomBar);

        // Main panels with rounded corners
        RoundedSquarePanel mainPanelRounded = new RoundedSquarePanel(new Color(45, 47, 51), 500);
        mainPanelRounded.setBounds(120, 95, 1100, 690); // Adjust size and position
        add(mainPanelRounded);

        RoundedSquarePanel sidePanelRounded = new RoundedSquarePanel(new Color(45, 47, 51), 300);
        sidePanelRounded.setBounds(-5, 100, 150, 690); // Adjust size and position
        add(sidePanelRounded);
    }

    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = UI.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.out.println("Image not found at path: " + path); // Detailed message
            return new ImageIcon(); // Return an empty image
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

    public class RoundedSquarePanel extends JPanel {
        public Color colour;
        public int squareSize;
        public RoundedSquarePanel(Color colour, int size) {
            this.colour = colour;
            this.squareSize = size;
            setOpaque(false); // Makes the background of the panel transparent
            setPreferredSize(new Dimension(size, size)); // Set the size of the panel (adjust this as needed)
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;


            g2.setColor(colour);

            int width = getWidth() - 50;  // makes the squares width based on panel size
            int height = getHeight() - 50;  // makes the square height based on panel size
            g2.fillRoundRect(25, 25, width, height, 100, 100); // actually draws the sqaure
        }
    }

    public static void main(String[] args) {
        new UI();
    }
}
