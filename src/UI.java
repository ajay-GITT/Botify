import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UI extends JFrame implements ActionListener {
    public JButton playPauseButton;
    public JButton skipButton, prevButton;
    public JLabel searchBarBackground;
    public JLabel searchBarBg;
    public JTextField searchTextField;
    public JButton homeButton;

    public boolean musicPlaying = false;
    public MusicPlayer musicPlayer;
    private JPanel mainPanel;
    private JPanel blackPage;

    // List of pages of cover art:
    private ArrayList<JPanel> pages = new ArrayList<JPanel>();
    private ArrayList<String> icon_paths = new ArrayList<String>();

    public UI() {
        setLayout(null);
        setSize(1250, 925);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        musicPlayer = new MusicPlayer();

        Color customColour = new Color(30, 31, 34);
        getContentPane().setBackground(Color.black);

        mainPanel = new JPanel(null);
        mainPanel.setBounds(0, 0, getWidth(), getHeight());
        mainPanel.setBackground(Color.BLACK);
        add(mainPanel);

        blackPage = new JPanel(null);
        blackPage.setBounds(0, 0, getWidth(), getHeight());
        blackPage.setBackground(Color.BLACK);
        blackPage.setVisible(false);
        add(blackPage);

        JLabel logoLabel = new JLabel(createImageIcon("botify_logo.png")); // Replace with the logo file name
        logoLabel.setBounds(10, 10, 100, 80); // Adjust position and size of the logo
        mainPanel.add(logoLabel);



        JPanel bottomBar = new JPanel();
        bottomBar.setBounds(0, 775, 1300, 150);
        bottomBar.setBackground(Color.black);
        mainPanel.add(bottomBar);

        playPauseButton = new JButton();
        playPauseButton.setBounds(575, 800, 1, 50);
        playPauseButton.setIcon(createImageIcon("play_icon.png"));
        playPauseButton.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        playPauseButton.setContentAreaFilled(false);
        playPauseButton.addActionListener(this);

        // Skip Button:
        skipButton = new JButton();
        skipButton.setBounds(800, 800, 100, 50);
        skipButton.setIcon(createImageIcon("skip_icon.png"));
        skipButton.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        skipButton.setContentAreaFilled(false);
        skipButton.addActionListener(this);

        // Prev Button:
        prevButton = new JButton();
        prevButton.setBounds(400, 800, 100, 50);
        prevButton.setIcon(createImageIcon("prev_icon.png"));
        prevButton.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        prevButton.setContentAreaFilled(false);
        prevButton.addActionListener(this);



        bottomBar.add(prevButton);
        bottomBar.add(playPauseButton);
        bottomBar.add(skipButton);

        JLabel dailyMixesLabel = new JLabel("Made For You");
        dailyMixesLabel.setFont(new Font("Arial", Font.BOLD, 27));
        dailyMixesLabel.setForeground(Color.WHITE);
        dailyMixesLabel.setBounds(185, 145, 280, 30);
        mainPanel.add(dailyMixesLabel);
        // Page for "Made For You":

        JLabel dailyMixesLabel2 = new JLabel("Set tha' mood");
        dailyMixesLabel2.setFont(new Font("Arial", Font.BOLD, 27));
        dailyMixesLabel2.setForeground(Color.WHITE);
        dailyMixesLabel2.setBounds(185, 450, 280, 30);
        mainPanel.add(dailyMixesLabel2);

        String[] dailyMixFiles = {"dailymix1.png", "dailymix2.png", "dailymix3.png", "dailymix4.png"};
        int mixesX = 192;
        int yPositionDailyMix = 220;
        int yPositionHottestHits = 520;
        int MixesWidth = 196;
        int MixesHeight = 194;
        int MixingSpacing = 255;

        for (int i = 0; i < dailyMixFiles.length; i++) {
            icon_paths.add(dailyMixFiles[i]);

            JButton dailyMixButton = new JButton("");
            dailyMixButton.setBounds(mixesX + (i * MixingSpacing), yPositionDailyMix, MixesWidth, MixesHeight);
            dailyMixButton.setIcon(createImageIcon(dailyMixFiles[i]));
            dailyMixButton.setBorderPainted(false);
            dailyMixButton.setFocusPainted(false);
            dailyMixButton.setOpaque(false);

            JPanel page = new JPanel(null);
            page.setBounds(0, 0, getWidth(), getHeight());
            page.setBackground(Color.BLACK);
            page.setVisible(false);
            add(page);
            pages.add(page);

            dailyMixButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
//                    showBlackPage();
                    showPage(page);
                }
            });
            mainPanel.add(dailyMixButton);
        }

        String[] hottestHitsFiles = {"hottesthits1.png", "hottesthits2.png", "hottesthits3.png", "hottesthits4.png"};

        for (int i = 0; i < hottestHitsFiles.length; i++) {
            icon_paths.add(hottestHitsFiles[i]);

            JButton hottestHitsButton = new JButton();
            hottestHitsButton.setBounds(mixesX + (i * MixingSpacing), yPositionHottestHits, MixesWidth, MixesHeight);
            hottestHitsButton.setIcon(createImageIcon(hottestHitsFiles[i]));
            hottestHitsButton.setBorderPainted(false);
            hottestHitsButton.setFocusPainted(false);
            hottestHitsButton.setOpaque(false);

            JPanel page = new JPanel(null);
            page.setBounds(0, 0, getWidth(), getHeight());
            page.setBackground(Color.BLACK);
            page.setVisible(false);
            add(page);
            pages.add(page);

            hottestHitsButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    showPage(page);
                }
            });
            mainPanel.add(hottestHitsButton);
        }

        // Background Panels for Cover Art
        int dailyMixX = 149;
        int dailyMixY = 165;
        int dailyMixWidth = 280;
        int dailyMixHeight = 300;
        int spacing = 255;

        for (int i = 0; i < 4; i++) {
            RoundedSquarePanel dailyMix = new RoundedSquarePanel(Color.BLACK, 200);
            dailyMix.setBounds(dailyMixX + (i * spacing), dailyMixY, dailyMixWidth, dailyMixHeight);
            mainPanel.add(dailyMix);
        }


        for (int i = 0; i < 4; i++) {
            RoundedSquarePanel dailyMix = new RoundedSquarePanel(Color.BLACK, 200);
            dailyMix.setBounds(dailyMixX + (i * spacing), dailyMixY + 300, dailyMixWidth, dailyMixHeight);
            mainPanel.add(dailyMix);
        }

        RoundedSquarePanel mainPanelRounded = new RoundedSquarePanel(new Color(45, 47, 51), 500);
        mainPanelRounded.setBounds(120, 95, 1100, 690);
        mainPanel.add(mainPanelRounded);

        RoundedSquarePanel sidePanelRounded = new RoundedSquarePanel(new Color(45, 47, 51), 300);
        sidePanelRounded.setBounds(-5, 100, 150, 690);
        mainPanel.add(sidePanelRounded);


        // Page One: (Custom stuff that is specific to page one)
        JPanel firstPage = pages.get(0);

        JLabel DailyMix1_Text = new JLabel("Daily Mix 1");
        DailyMix1_Text.setFont(new Font("Arial", Font.BOLD, 45));
        DailyMix1_Text.setForeground(Color.WHITE);
        DailyMix1_Text.setBounds(290,183,300,50);


        JLabel DailyMix1_Text2 = new JLabel("Public Playlist");
        DailyMix1_Text2.setFont(new Font("Arial", Font.BOLD, 17));
        DailyMix1_Text2.setForeground(Color.GRAY);
        DailyMix1_Text2.setBounds(290,150,300,50);

        JLabel DailyMix1_Text3 = new JLabel("Featuring: Future, Metroboomin, Drake, & More!");
        DailyMix1_Text3.setFont(new Font("Arial", Font.BOLD, 14));
        DailyMix1_Text3.setForeground(Color.GRAY);
        DailyMix1_Text3.setBounds(292,218,500,50);

        JLabel DailyMix1_Text4 = new JLabel("5 songs, 16m 25s");
        DailyMix1_Text4.setFont(new Font("Arial", Font.BOLD, 14));
        DailyMix1_Text4.setForeground(Color.GRAY);
        DailyMix1_Text4.setBounds(292,236,300,50);


        firstPage.add(DailyMix1_Text);
        firstPage.add(DailyMix1_Text2);
        firstPage.add(DailyMix1_Text3);
        firstPage.add(DailyMix1_Text4);


        // Page Two:
        JPanel secondPage = pages.get(1);

        JLabel DailyMix2_Text = new JLabel("Daily Mix 2");
        DailyMix2_Text.setFont(new Font("Arial", Font.BOLD, 45));
        DailyMix2_Text.setForeground(Color.WHITE);
        DailyMix2_Text.setBounds(290,183,300,50);

        JLabel DailyMix2_Text2 = new JLabel("Public Playlist");
        DailyMix2_Text2.setFont(new Font("Arial", Font.BOLD, 17));
        DailyMix2_Text2.setForeground(Color.GRAY);
        DailyMix2_Text2.setBounds(290,150,300,50);


        JLabel DailyMix2_Text3 = new JLabel("Featuring: Gunna, Travis Scott, Post Malone, & More!");
        DailyMix2_Text3.setFont(new Font("Arial", Font.BOLD, 14));
        DailyMix2_Text3.setForeground(Color.GRAY);
        DailyMix2_Text3.setBounds(292,218,500,50);

        JLabel DailyMix2_Text4 = new JLabel("5 songs, 16m 35s");
        DailyMix2_Text4.setFont(new Font("Arial", Font.BOLD, 14));
        DailyMix2_Text4.setForeground(Color.GRAY);
        DailyMix2_Text4.setBounds(292,236,300,50);


        secondPage.add(DailyMix2_Text);
        secondPage.add(DailyMix2_Text2);
        secondPage.add(DailyMix2_Text3);
        secondPage.add(DailyMix2_Text4);


        // Third Page:
        JPanel thirdPage = pages.get(2);

        JLabel DailyMix3_Text = new JLabel("Daily Mix 3");
        DailyMix3_Text.setFont(new Font("Arial", Font.BOLD, 45));
        DailyMix3_Text.setForeground(Color.WHITE);
        DailyMix3_Text.setBounds(290,183,300,50);

        JLabel DailyMix3_Text2 = new JLabel("Public Playlist");
        DailyMix3_Text2.setFont(new Font("Arial", Font.BOLD, 17));
        DailyMix3_Text2.setForeground(Color.GRAY);
        DailyMix3_Text2.setBounds(290,150,300,50);


        JLabel DailyMix3_Text3 = new JLabel("Featuring: Karan Aujila, AP Dhillon, Diljit Dosanjh, & More!");
        DailyMix3_Text3.setFont(new Font("Arial", Font.BOLD, 14));
        DailyMix3_Text3.setForeground(Color.GRAY);
        DailyMix3_Text3.setBounds(292,218,500,50);

        JLabel DailyMix3_Text4 = new JLabel("5 songs, 12m 6s");
        DailyMix3_Text4.setFont(new Font("Arial", Font.BOLD, 14));
        DailyMix3_Text4.setForeground(Color.GRAY);
        DailyMix3_Text4.setBounds(292,236,300,50);


        thirdPage.add(DailyMix2_Text);
        thirdPage.add(DailyMix2_Text2);
        thirdPage.add(DailyMix2_Text3);
        thirdPage.add(DailyMix2_Text4);


        // Fourth Page:
        JPanel fourthPage = pages.get(3);

        JLabel DailyMix4_Text = new JLabel("Daily Mix 4");
        DailyMix4_Text.setFont(new Font("Arial", Font.BOLD, 45));
        DailyMix4_Text.setForeground(Color.WHITE);
        DailyMix4_Text.setBounds(290,183,300,50);

        JLabel DailyMix4_Text2 = new JLabel("Public Playlist");
        DailyMix4_Text2.setFont(new Font("Arial", Font.BOLD, 17));
        DailyMix4_Text2.setForeground(Color.GRAY);
        DailyMix4_Text2.setBounds(290,150,300,50);


        JLabel DailyMix4_Text3 = new JLabel("Featuring: PARTYNEXTDOOR, Frank Ocean, Bruno Mars, & More!");
        DailyMix4_Text3.setFont(new Font("Arial", Font.BOLD, 14));
        DailyMix4_Text3.setForeground(Color.GRAY);
        DailyMix4_Text3.setBounds(292,218,500,50);

        JLabel DailyMix4_Text4 = new JLabel("5 songs, 17m 53s");
        DailyMix4_Text4.setFont(new Font("Arial", Font.BOLD, 14));
        DailyMix4_Text4.setForeground(Color.GRAY);
        DailyMix4_Text4.setBounds(292,236,300,50);


        fourthPage.add(DailyMix4_Text);
        fourthPage.add(DailyMix4_Text2);
        fourthPage.add(DailyMix4_Text3);
        fourthPage.add(DailyMix4_Text4);


        // Fifth Page:
        JPanel fifthPage = pages.get(4);

        JLabel HottestHits_Text = new JLabel("Latest Releases");
        HottestHits_Text.setFont(new Font("Arial", Font.BOLD, 45));
        HottestHits_Text.setForeground(Color.WHITE);
        HottestHits_Text.setBounds(290,183,500,50);

        JLabel HottestHits_Text2 = new JLabel("Public Playlist");
        HottestHits_Text2.setFont(new Font("Arial", Font.BOLD, 17));
        HottestHits_Text2.setForeground(Color.GRAY);
        HottestHits_Text2.setBounds(290,150,300,50);


        JLabel HottestHits_Text3 = new JLabel("Featuring: Travis Scott, Lil Baby, Playboi Carti, & More!");
        HottestHits_Text3.setFont(new Font("Arial", Font.BOLD, 14));
        HottestHits_Text3.setForeground(Color.GRAY);
        HottestHits_Text3.setBounds(292,218,500,50);

        JLabel HottestHits_Text4 = new JLabel("5 songs, 17m 53s");
        HottestHits_Text4.setFont(new Font("Arial", Font.BOLD, 14));
        HottestHits_Text4.setForeground(Color.GRAY);
        HottestHits_Text4.setBounds(292,236,300,50);


        fifthPage.add(HottestHits_Text);
        fifthPage.add(HottestHits_Text2);
        fifthPage.add(HottestHits_Text3);
        fifthPage.add(HottestHits_Text4);


        // Sixth Page:
        JPanel sixthPage = pages.get(5);

        JLabel HottestHits2_Text = new JLabel("Beast Mode");
        HottestHits2_Text.setFont(new Font("Arial", Font.BOLD, 45));
        HottestHits2_Text.setForeground(Color.WHITE);
        HottestHits2_Text.setBounds(290,183,300,50);

        JLabel HottestHits2_Text2 = new JLabel("Public Playlist");
        HottestHits2_Text2.setFont(new Font("Arial", Font.BOLD, 17));
        HottestHits2_Text2.setForeground(Color.GRAY);
        HottestHits2_Text2.setBounds(290,150,300,50);


        JLabel HottestHits2_Text3 = new JLabel("Featuring: XXX, XXX, XXX, & More!");
        HottestHits2_Text3.setFont(new Font("Arial", Font.BOLD, 14));
        HottestHits2_Text3.setForeground(Color.GRAY);
        HottestHits2_Text3.setBounds(292,218,500,50);

        JLabel HottestHits2_Text4 = new JLabel("5 songs, 17m 53s");
        HottestHits2_Text4.setFont(new Font("Arial", Font.BOLD, 14));
        HottestHits2_Text4.setForeground(Color.GRAY);
        HottestHits2_Text4.setBounds(292,236,300,50);


        sixthPage.add(HottestHits2_Text);
        sixthPage.add(HottestHits2_Text2);
        sixthPage.add(HottestHits2_Text3);
        sixthPage.add(HottestHits2_Text4);


        // Seventh Page:
        JPanel seventhPage = pages.get(6);

        JLabel HottestHits3_Text = new JLabel("Hot Hits R&B");
        HottestHits3_Text.setFont(new Font("Arial", Font.BOLD, 45));
        HottestHits3_Text.setForeground(Color.WHITE);
        HottestHits3_Text.setBounds(290,183,500,50);

        JLabel HottestHits3_Text2 = new JLabel("Public Playlist");
        HottestHits3_Text2.setFont(new Font("Arial", Font.BOLD, 17));
        HottestHits3_Text2.setForeground(Color.GRAY);
        HottestHits3_Text2.setBounds(290,150,300,50);


        JLabel HottestHits3_Text3 = new JLabel("Featuring: XXX, XXX, XXX, & More!");
        HottestHits3_Text3.setFont(new Font("Arial", Font.BOLD, 14));
        HottestHits3_Text3.setForeground(Color.GRAY);
        HottestHits3_Text3.setBounds(292,218,500,50);

        JLabel HottestHits3_Text4 = new JLabel("5 songs, 17m 53s");
        HottestHits3_Text4.setFont(new Font("Arial", Font.BOLD, 14));
        HottestHits3_Text4.setForeground(Color.GRAY);
        HottestHits3_Text4.setBounds(292,236,300,50);


        seventhPage.add(HottestHits3_Text);
        seventhPage.add(HottestHits3_Text2);
        seventhPage.add(HottestHits3_Text3);
        seventhPage.add(HottestHits3_Text4);

        // Eigth Page:
        JPanel eigthPage = pages.get(7);

        JLabel HottestHits4_Text = new JLabel("South Asian Gems");
        HottestHits4_Text.setFont(new Font("Arial", Font.BOLD, 45));
        HottestHits4_Text.setForeground(Color.WHITE);
        HottestHits4_Text.setBounds(290,183,500,50);

        JLabel HottestHits4_Text2 = new JLabel("Public Playlist");
        HottestHits4_Text2.setFont(new Font("Arial", Font.BOLD, 17));
        HottestHits4_Text2.setForeground(Color.GRAY);
        HottestHits4_Text2.setBounds(290,150,300,50);


        JLabel HottestHits4_Text3 = new JLabel("Featuring: XXX, XXX, XXX, & More!");
        HottestHits4_Text3.setFont(new Font("Arial", Font.BOLD, 14));
        HottestHits4_Text3.setForeground(Color.GRAY);
        HottestHits4_Text3.setBounds(292,218,500,50);

        JLabel HottestHits4_Text4 = new JLabel("5 songs, 17m 53s");
        HottestHits4_Text4.setFont(new Font("Arial", Font.BOLD, 14));
        HottestHits4_Text4.setForeground(Color.GRAY);
        HottestHits4_Text4.setBounds(292,236,300,50);


        eigthPage.add(HottestHits4_Text);
        eigthPage.add(HottestHits4_Text2);
        eigthPage.add(HottestHits4_Text3);
        eigthPage.add(HottestHits4_Text4);




        // Everything that must be repeated through all eight pages
        for (int i = 0; i < pages.size(); i++) {
            JPanel current_page = pages.get(i);

            JButton iconImages = new JButton();
            iconImages.setBounds(80, 110, 200, 200);
            iconImages.setIcon(createImageIcon(icon_paths.get(i)));
            iconImages.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
            iconImages.setContentAreaFilled(false);
            iconImages.addActionListener(this);

//             HomeButton:
//            homeButton = new JButton(createImageIcon("home_icon.png"));
//            homeButton.setBounds(800, 220, 30, 30);
//            homeButton.setBorderPainted(false);
//            homeButton.setContentAreaFilled(false);
//            homeButton.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    goToMainPage();
//                }
//            });

            // Top Panel
            RoundedSquarePanel CoverArtTopPanel = new RoundedSquarePanel(new Color(45, 47, 51), 500);
            CoverArtTopPanel.setBounds(10, 60, 1200, 300);


            // playlist panel:
            int playlistPanelY = 350;
            RoundedSquarePanel PlaylistPanel = null;
            for (int j = 0; j < 5; j++) {
                PlaylistPanel = new RoundedSquarePanel(Color.BLACK, 200);
                PlaylistPanel.setBounds(50, playlistPanelY, 1100, 125);
                current_page.add(PlaylistPanel);
                playlistPanelY = playlistPanelY + 100;
            }
            current_page.add(CoverArtTopPanel);

            // Main Panel
            RoundedSquarePanel CoverArtMainPanel = new RoundedSquarePanel(new Color(45, 47, 51), 500);
            CoverArtMainPanel.setBounds(10, 340, 1200, 545);
            current_page.add(CoverArtMainPanel);


            // Logo
            JLabel logoLabel_2 = new JLabel(createImageIcon("botify_logo.png"));
            logoLabel_2.setBounds(10, 10, 100, 80);

            // Adding
            current_page.add(logoLabel_2);
            current_page.add(iconImages);
//            current_page.add(homeButton);
            current_page.add(PlaylistPanel);
            current_page.add(CoverArtTopPanel);


        }

    }


    private void showPage(JPanel page) {
        mainPanel.setVisible(false);
        blackPage.setVisible(false);
        page.setVisible(true);
    }

    private void goToMainPage() {
        blackPage.setVisible(false);
        mainPanel.setVisible(true);
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

        if (musicPlaying) {
            if (source == skipButton) {
                musicPlayer.skipSong();
            } else if (source == prevButton) {
                musicPlayer.prevSong();
            }
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