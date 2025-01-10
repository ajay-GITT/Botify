import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame implements ActionListener {
    public JButton playPauseButton;
    public JButton skipButton;
    public JLabel searchBarBackground;
    public JLabel searchBarBg;
    public JTextField searchTextField;

    public boolean musicPlaying = false;
    public MusicPlayer musicPlayer;

    public UI() {
        setLayout(null);
        setSize(1250, 925);
        setVisible(true);

        musicPlayer = new MusicPlayer();

        Color customColour = new Color(30, 31, 34);
        getContentPane().setBackground(Color.black);

        JLabel logoLabel = new JLabel(createImageIcon("botify_logo.png")); // Replace with the logo file name
        logoLabel.setBounds(10, 10, 100, 80); // Adjust position and size of the logo
        add(logoLabel);

        searchBarBackground = new JLabel();
        searchBarBackground.setBounds(400, 20, 500, 100);
        searchBarBackground.setLayout(null);
        add(searchBarBackground);

        searchTextField = new JTextField();
        searchTextField.setOpaque(false);
        searchTextField.setBackground(new Color(0, 0, 0, 0));
        searchTextField.setForeground(Color.WHITE);
        searchTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        searchTextField.setBorder(null);
        searchTextField.setBounds(0, 0, 500, 100); // Adjusted bounds for proper overlap
        searchBarBackground.add(searchTextField);

        searchBarBg = new JLabel(createImageIcon("search_bar_bg.png"));
        searchBarBg.setBounds(0, 0, 500, 100); // Adjust the bounds for the image
        searchBarBackground.add(searchBarBg);

        JPanel bottomBar = new JPanel();
        bottomBar.setBounds(0, 775, 1300, 150);
        bottomBar.setBackground(Color.black);
        add(bottomBar);

        playPauseButton = new JButton();
        playPauseButton.setBounds(575, 800, 1, 50);
        playPauseButton.setIcon(createImageIcon("play_icon.png"));
        playPauseButton.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        playPauseButton.setContentAreaFilled(false);
        playPauseButton.addActionListener(this);

        skipButton = new JButton();
        skipButton.setBounds(800, 800, 100, 50);
        skipButton.setIcon(createImageIcon("skip_icon.png"));
        skipButton.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        skipButton.setContentAreaFilled(false);
        skipButton.addActionListener(this);

        bottomBar.add(playPauseButton);
        bottomBar.add(skipButton);

        add(bottomBar);


        // Daily mix panels
        JLabel dailyMixesLabel = new JLabel("Made For You");
        dailyMixesLabel.setFont(new Font("Arial", Font.BOLD, 27));
        dailyMixesLabel.setForeground(Color.WHITE); // Set text color
        dailyMixesLabel.setBounds(185, 145, 280, 30);

        JLabel dailyMixesLabel2 = new JLabel("Set tha' mood");
        dailyMixesLabel2.setFont(new Font("Arial", Font.BOLD, 27));
        dailyMixesLabel2.setForeground(Color.WHITE); // Set text color
        dailyMixesLabel2.setBounds(185, 450, 280, 30);

        // Daily Mix Backgrounds
        add(dailyMixesLabel);
        add(dailyMixesLabel2);


        String[] dailyMixFiles = {"dailymix1.png", "dailymix2.png", "dailymix3.png", "dailymix4.png"};
        int mixesX = 192;
        int yPositionDailyMix = 220;
        int yPositionHottestHits = 520;
        int MixesWidth = 196;
        int MixesHeight = 194;
        int MixingSpacing = 255;

        for (int i = 0; i < dailyMixFiles.length; i++) {
            JButton dailyMixButton = new JButton("");
            dailyMixButton.setBounds(mixesX + (i * MixingSpacing), yPositionDailyMix, MixesWidth, MixesHeight);
            dailyMixButton.setIcon(createImageIcon(dailyMixFiles[i]));
            dailyMixButton.setBorderPainted(false);
            dailyMixButton.setFocusPainted(false);
            dailyMixButton.setOpaque(false);

            add(dailyMixButton);
        }

        String[] hottestHitsFiles = {"hottesthits1.png", "hottesthits2.png", "hottesthits3.png", "hottesthits4.png"};

        for (int i = 0; i < hottestHitsFiles.length; i++) {
            JButton hottestHitsButton = new JButton();
            hottestHitsButton.setBounds(mixesX + (i * MixingSpacing), yPositionHottestHits, MixesWidth, MixesHeight);
            hottestHitsButton.setIcon(createImageIcon(hottestHitsFiles[i]));
            hottestHitsButton.setBorderPainted(false);
            hottestHitsButton.setFocusPainted(false);
            hottestHitsButton.setOpaque(false);

            add(hottestHitsButton);
        }

        int dailyMixX = 149;
        int dailyMixY = 165;
        int dailyMixWidth = 280;
        int dailyMixHeight = 300;
        int spacing = 255;


        for (int i = 0; i < 4; i++) {
            RoundedSquarePanel dailyMix = new RoundedSquarePanel(Color.BLACK, 200);
            dailyMix.setBounds(dailyMixX + (i * spacing), dailyMixY, dailyMixWidth, dailyMixHeight);
            add(dailyMix);
        }


        for (int i = 0; i < 4; i++) {
            RoundedSquarePanel dailyMix = new RoundedSquarePanel(Color.BLACK, 200);
            dailyMix.setBounds(dailyMixX + (i * spacing), dailyMixY + 300, dailyMixWidth, dailyMixHeight);
            add(dailyMix);
        }

        // Main panel
        RoundedSquarePanel mainPanelRounded = new RoundedSquarePanel(new Color(45, 47, 51), 500);
        mainPanelRounded.setBounds(120, 95, 1100, 690);
        add(mainPanelRounded);

        // Side panel
        RoundedSquarePanel sidePanelRounded = new RoundedSquarePanel(new Color(45, 47, 51), 300);
        sidePanelRounded.setBounds(-5, 100, 150, 690);
        add(sidePanelRounded);

    }

    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = UI.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.out.println("Image not found at path: " + path);
            return new ImageIcon();
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
            setOpaque(false);
            setPreferredSize(new Dimension(size, size));
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            g2.setColor(colour);

            int width = getWidth() - 50;
            int height = getHeight() - 50;
            g2.fillRoundRect(25, 25, width, height, 100, 100);
        }
    }

    public static void main(String[] args) {
        new UI();
    }
}