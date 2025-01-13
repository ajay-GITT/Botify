import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UI extends JFrame implements ActionListener {
    public JButton playPauseButton;
    public JButton skipButton, prevButton;
    public JButton homeButton;
    public JButton ClockButton;

    // Containers for buttons of specific songs for each page:
    private ArrayList<ArrayList<JButton>> allPagePlayPauseButtons = new ArrayList<ArrayList<JButton>>();
    // Containers for song paths corresponding to the above buttons for each page:
    private ArrayList<SongDirectory> songDirectories = new ArrayList<SongDirectory>();

    // Page Indicator:
    private int pageView;
    // Last Button Indicator:
    private JButton lastButtonPressed = null;

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
        playPauseButton.setBorder(BorderFactory.createEmptyBorder());
        playPauseButton.setContentAreaFilled(false);
        playPauseButton.addActionListener(this);

        // Skip Button:
        skipButton = new JButton();
        skipButton.setBounds(800, 800, 100, 50);
        skipButton.setIcon(createImageIcon("skip_icon.png"));
        skipButton.setBorder(BorderFactory.createEmptyBorder());
        skipButton.setContentAreaFilled(false);
        skipButton.addActionListener(this);

        // Prev Button:
        prevButton = new JButton();
        prevButton.setBounds(400, 800, 100, 50);
        prevButton.setIcon(createImageIcon("prev_icon.png"));
        prevButton.setBorder(BorderFactory.createEmptyBorder(
        ));
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
        DailyMix1_Text.setBounds(290, 183, 300, 50);


        JLabel DailyMix1_Text2 = new JLabel("Public Playlist");
        DailyMix1_Text2.setFont(new Font("Arial", Font.BOLD, 17));
        DailyMix1_Text2.setForeground(Color.GRAY);
        DailyMix1_Text2.setBounds(290, 150, 300, 50);

        JLabel DailyMix1_Text3 = new JLabel("Featuring: Future, Metroboomin, Drake, & More!");
        DailyMix1_Text3.setFont(new Font("Arial", Font.BOLD, 14));
        DailyMix1_Text3.setForeground(Color.GRAY);
        DailyMix1_Text3.setBounds(292, 218, 500, 50);

        JLabel DailyMix1_Text4 = new JLabel("5 songs, 16m 25s");
        DailyMix1_Text4.setFont(new Font("Arial", Font.BOLD, 14));
        DailyMix1_Text4.setForeground(Color.GRAY);
        DailyMix1_Text4.setBounds(292, 236, 300, 50);

        firstPage.add(DailyMix1_Text);
        firstPage.add(DailyMix1_Text2);
        firstPage.add(DailyMix1_Text3);
        firstPage.add(DailyMix1_Text4);

        // Song one info
        JLabel DailyMix1_TextInfo = new JLabel("Superhero (Heroes & Villains)");
        DailyMix1_TextInfo.setFont(new Font("Arial", Font.BOLD, 20));
        DailyMix1_TextInfo.setForeground(Color.WHITE);
        DailyMix1_TextInfo.setBounds(290, 382, 500, 50);

        JLabel DailyMix1_TextInfo2 = new JLabel("Future, Metroboomin");
        DailyMix1_TextInfo2.setFont(new Font("Arial", Font.ITALIC, 15));
        DailyMix1_TextInfo2.setForeground(Color.GRAY);
        DailyMix1_TextInfo2.setBounds(290, 405, 500, 50);

        JLabel DailyMix1_TextInfo3 = new JLabel("3:02");
        DailyMix1_TextInfo3.setFont(new Font("Arial", Font.PLAIN, 16));
        DailyMix1_TextInfo3.setForeground(Color.GRAY);
        DailyMix1_TextInfo3.setBounds(1021, 404, 500, 50);

        firstPage.add(DailyMix1_TextInfo);
        firstPage.add(DailyMix1_TextInfo2);
        firstPage.add(DailyMix1_TextInfo3);

        // Song Two info
        JLabel DailyMix1_TextInfo_song2 = new JLabel("LIL DEMON");
        DailyMix1_TextInfo_song2.setFont(new Font("Arial", Font.BOLD, 20));
        DailyMix1_TextInfo_song2.setForeground(Color.WHITE);
        DailyMix1_TextInfo_song2.setBounds(290, 482, 500, 50);

        JLabel DailyMix1_TextInfo2_song2 = new JLabel("Future");
        DailyMix1_TextInfo2_song2.setFont(new Font("Arial", Font.ITALIC, 15));
        DailyMix1_TextInfo2_song2.setForeground(Color.GRAY);
        DailyMix1_TextInfo2_song2.setBounds(290, 505, 500, 50);

        JLabel DailyMix1_TextInfo3_song2 = new JLabel("2:19");
        DailyMix1_TextInfo3_song2.setFont(new Font("Arial", Font.PLAIN, 16));
        DailyMix1_TextInfo3_song2.setForeground(Color.GRAY);
        DailyMix1_TextInfo3_song2.setBounds(1021, 490, 500, 50);

        firstPage.add(DailyMix1_TextInfo_song2);
        firstPage.add(DailyMix1_TextInfo2_song2);
        firstPage.add(DailyMix1_TextInfo3_song2);

        //Song three info
        JLabel DailyMix1_TextInfo_song3 = new JLabel("Headlines");
        DailyMix1_TextInfo_song3.setFont(new Font("Arial", Font.BOLD, 20));
        DailyMix1_TextInfo_song3.setForeground(Color.WHITE);
        DailyMix1_TextInfo_song3.setBounds(290, 582, 500, 50);

        JLabel DailyMix1_TextInfo2_song3 = new JLabel("Drake");
        DailyMix1_TextInfo2_song3.setFont(new Font("Arial", Font.ITALIC, 15));
        DailyMix1_TextInfo2_song3.setForeground(Color.GRAY);
        DailyMix1_TextInfo2_song3.setBounds(290, 605, 500, 50);

        JLabel DailyMix1_TextInfo3_song3 = new JLabel("3:55");
        DailyMix1_TextInfo3_song3.setFont(new Font("Arial", Font.PLAIN, 16));
        DailyMix1_TextInfo3_song3.setForeground(Color.GRAY);
        DailyMix1_TextInfo3_song3.setBounds(1021, 585, 500, 50);

        firstPage.add(DailyMix1_TextInfo_song3);
        firstPage.add(DailyMix1_TextInfo2_song3);
        firstPage.add(DailyMix1_TextInfo3_song3);

        // Song four
        JLabel DailyMix1_TextInfo_song4 = new JLabel("No Role Modelz");
        DailyMix1_TextInfo_song4.setFont(new Font("Arial", Font.BOLD, 20));
        DailyMix1_TextInfo_song4.setForeground(Color.WHITE);
        DailyMix1_TextInfo_song4.setBounds(290, 682, 500, 50);

        JLabel DailyMix1_TextInfo2_song4 = new JLabel("J. Cole");
        DailyMix1_TextInfo2_song4.setFont(new Font("Arial", Font.ITALIC, 15));
        DailyMix1_TextInfo2_song4.setForeground(Color.GRAY);
        DailyMix1_TextInfo2_song4.setBounds(290, 705, 500, 50);

        JLabel DailyMix1_TextInfo3_song4 = new JLabel("4:52");
        DailyMix1_TextInfo3_song4.setFont(new Font("Arial", Font.PLAIN, 16));
        DailyMix1_TextInfo3_song4.setForeground(Color.GRAY);
        DailyMix1_TextInfo3_song4.setBounds(1021, 685, 500, 50);

        firstPage.add(DailyMix1_TextInfo_song4);
        firstPage.add(DailyMix1_TextInfo2_song4);
        firstPage.add(DailyMix1_TextInfo3_song4);

        // Song Five
        JLabel DailyMix1_TextInfo_song5 = new JLabel("WAIT FOR U");
        DailyMix1_TextInfo_song5.setFont(new Font("Arial", Font.BOLD, 20));
        DailyMix1_TextInfo_song5.setForeground(Color.WHITE);
        DailyMix1_TextInfo_song5.setBounds(290, 782, 500, 50);

        JLabel DailyMix1_TextInfo2_song5 = new JLabel("Future, Drake, Tems");
        DailyMix1_TextInfo2_song5.setFont(new Font("Arial", Font.ITALIC, 15));
        DailyMix1_TextInfo2_song5.setForeground(Color.GRAY);
        DailyMix1_TextInfo2_song5.setBounds(290, 805, 500, 50);

        JLabel DailyMix1_TextInfo3_song5 = new JLabel("3:09");
        DailyMix1_TextInfo3_song5.setFont(new Font("Arial", Font.PLAIN, 16));
        DailyMix1_TextInfo3_song5.setForeground(Color.GRAY);
        DailyMix1_TextInfo3_song5.setBounds(1021, 785, 500, 50);

        firstPage.add(DailyMix1_TextInfo_song5);
        firstPage.add(DailyMix1_TextInfo2_song5);
        firstPage.add(DailyMix1_TextInfo3_song5);


        JButton DM1_1 = new JButton();
        DM1_1.setBounds(200, 362, 100, 100);
        DM1_1.setIcon(createImageIcon("superhero.png"));
        DM1_1.setBorder(BorderFactory.createEmptyBorder());
        DM1_1.setContentAreaFilled(false);
        DM1_1.addActionListener(this);

        JButton DM1_2 = new JButton();
        DM1_2.setBounds(200, 462, 100, 100);
        DM1_2.setIcon(createImageIcon("lildemons.png"));
        DM1_2.setBorder(BorderFactory.createEmptyBorder());
        DM1_2.setContentAreaFilled(false);
        DM1_2.addActionListener(this);

        JButton DM1_3 = new JButton();
        DM1_3.setBounds(200, 562, 100, 100);
        DM1_3.setIcon(createImageIcon("headlines.png"));
        DM1_3.setBorder(BorderFactory.createEmptyBorder());
        DM1_3.setContentAreaFilled(false);
        DM1_3.addActionListener(this);

        JButton DM1_4 = new JButton();
        DM1_4.setBounds(200, 662, 100, 100);
        DM1_4.setIcon(createImageIcon("norolemodelz.png"));
        DM1_4.setBorder(BorderFactory.createEmptyBorder());
        DM1_4.setContentAreaFilled(false);
        DM1_4.addActionListener(this);

        JButton DM1_5 = new JButton();
        DM1_5.setBounds(200, 762, 100, 100);
        DM1_5.setIcon(createImageIcon("zoots.png"));
        DM1_5.setBorder(BorderFactory.createEmptyBorder());
        DM1_5.setContentAreaFilled(false);
        DM1_5.addActionListener(this);


        // Song Directory for Daily mix 1
        SongDirectory dailyMix1 = new SongDirectory("Daily Mix1");
        songDirectories.add(dailyMix1);

        firstPage.add(DM1_1);
        firstPage.add(DM1_2);
        firstPage.add(DM1_3);
        firstPage.add(DM1_4);
        firstPage.add(DM1_5);


        // Page Two:
        JPanel secondPage = pages.get(1);

        JLabel DailyMix2_Text = new JLabel("Daily Mix 2");
        DailyMix2_Text.setFont(new Font("Arial", Font.BOLD, 45));
        DailyMix2_Text.setForeground(Color.WHITE);
        DailyMix2_Text.setBounds(290, 183, 300, 50);

        JLabel DailyMix2_Text2 = new JLabel("Public Playlist");
        DailyMix2_Text2.setFont(new Font("Arial", Font.BOLD, 17));
        DailyMix2_Text2.setForeground(Color.GRAY);
        DailyMix2_Text2.setBounds(290, 150, 300, 50);


        JLabel DailyMix2_Text3 = new JLabel("Featuring: Drake, Travis Scott, Post Malone, & More!");
        DailyMix2_Text3.setFont(new Font("Arial", Font.BOLD, 14));
        DailyMix2_Text3.setForeground(Color.GRAY);
        DailyMix2_Text3.setBounds(292, 218, 500, 50);

        JLabel DailyMix2_Text4 = new JLabel("5 songs, 16m 35s");
        DailyMix2_Text4.setFont(new Font("Arial", Font.BOLD, 14));
        DailyMix2_Text4.setForeground(Color.GRAY);
        DailyMix2_Text4.setBounds(292, 236, 300, 50);

        // Song one Dailymix2
        JLabel DailyMix2_TextInfo_song = new JLabel("One Dance");
        DailyMix2_TextInfo_song.setFont(new Font("Arial", Font.BOLD, 20));
        DailyMix2_TextInfo_song.setForeground(Color.WHITE);
        DailyMix2_TextInfo_song.setBounds(290, 382, 500, 50);

        JLabel DailyMix2_TextInfo2_song = new JLabel("Drake, Wizkid");
        DailyMix2_TextInfo2_song.setFont(new Font("Arial", Font.ITALIC, 15));
        DailyMix2_TextInfo2_song.setForeground(Color.GRAY);
        DailyMix2_TextInfo2_song.setBounds(290, 405, 500, 50);

        JLabel DailyMix2_TextInfo3_song = new JLabel("2:53");
        DailyMix2_TextInfo3_song.setFont(new Font("Arial", Font.PLAIN, 16));
        DailyMix2_TextInfo3_song.setForeground(Color.GRAY);
        DailyMix2_TextInfo3_song.setBounds(1021, 404, 500, 50);

        secondPage.add(DailyMix2_TextInfo_song);
        secondPage.add(DailyMix2_TextInfo2_song);
        secondPage.add(DailyMix2_TextInfo3_song);

        // Song two Dailymix2
        JLabel DailyMix2_TextInfo_song2 = new JLabel("one of wun");
        DailyMix2_TextInfo_song2.setFont(new Font("Arial", Font.BOLD, 20));
        DailyMix2_TextInfo_song2.setForeground(Color.WHITE);
        DailyMix2_TextInfo_song2.setBounds(290, 482, 500, 50);

        JLabel DailyMix2_TextInfo2_song2 = new JLabel("Gunna");
        DailyMix2_TextInfo2_song2.setFont(new Font("Arial", Font.ITALIC, 15));
        DailyMix2_TextInfo2_song2.setForeground(Color.GRAY);
        DailyMix2_TextInfo2_song2.setBounds(290, 505, 500, 50);

        JLabel DailyMix2_TextInfo3_song2 = new JLabel("2:24");
        DailyMix2_TextInfo3_song2.setFont(new Font("Arial", Font.PLAIN, 16));
        DailyMix2_TextInfo3_song2.setForeground(Color.GRAY);
        DailyMix2_TextInfo3_song2.setBounds(1021, 490, 500, 50);

        secondPage.add(DailyMix2_TextInfo_song2);
        secondPage.add(DailyMix2_TextInfo2_song2);
        secondPage.add(DailyMix2_TextInfo3_song2);

        // Song three Dailymix2
        JLabel DailyMix2_TextInfo_song3 = new JLabel("MY EYES");
        DailyMix2_TextInfo_song3.setFont(new Font("Arial", Font.BOLD, 20));
        DailyMix2_TextInfo_song3.setForeground(Color.WHITE);
        DailyMix2_TextInfo_song3.setBounds(290, 582, 500, 50);

        JLabel DailyMix2_TextInfo2_song3 = new JLabel("Travis Scott");
        DailyMix2_TextInfo2_song3.setFont(new Font("Arial", Font.ITALIC, 15));
        DailyMix2_TextInfo2_song3.setForeground(Color.GRAY);
        DailyMix2_TextInfo2_song3.setBounds(290, 605, 500, 50);

        JLabel DailyMix2_TextInfo3_song3 = new JLabel("4:11");
        DailyMix2_TextInfo3_song3.setFont(new Font("Arial", Font.PLAIN, 16));
        DailyMix2_TextInfo3_song3.setForeground(Color.GRAY);
        DailyMix2_TextInfo3_song3.setBounds(1021, 585, 500, 50);

        secondPage.add(DailyMix2_TextInfo_song3);
        secondPage.add(DailyMix2_TextInfo2_song3);
        secondPage.add(DailyMix2_TextInfo3_song3);

        // Song four Dailymix2
        JLabel DailyMix2_TextInfo_song4 = new JLabel("Solo");
        DailyMix2_TextInfo_song4.setFont(new Font("Arial", Font.BOLD, 20));
        DailyMix2_TextInfo_song4.setForeground(Color.WHITE);
        DailyMix2_TextInfo_song4.setBounds(290, 682, 500, 50);

        JLabel DailyMix2_TextInfo2_song4 = new JLabel("Future");
        DailyMix2_TextInfo2_song4.setFont(new Font("Arial", Font.ITALIC, 15));
        DailyMix2_TextInfo2_song4.setForeground(Color.GRAY);
        DailyMix2_TextInfo2_song4.setBounds(290, 705, 500, 50);

        JLabel DailyMix2_TextInfo3_song4 = new JLabel("4:25");
        DailyMix2_TextInfo3_song4.setFont(new Font("Arial", Font.PLAIN, 16));
        DailyMix2_TextInfo3_song4.setForeground(Color.GRAY);
        DailyMix2_TextInfo3_song4.setBounds(1021, 685, 500, 50);

        secondPage.add(DailyMix2_TextInfo_song4);
        secondPage.add(DailyMix2_TextInfo2_song4);
        secondPage.add(DailyMix2_TextInfo3_song4);

        // Song five Dailymix2
        JLabel DailyMix2_TextInfo_song5 = new JLabel("Congratulations");
        DailyMix2_TextInfo_song5.setFont(new Font("Arial", Font.BOLD, 20));
        DailyMix2_TextInfo_song5.setForeground(Color.WHITE);
        DailyMix2_TextInfo_song5.setBounds(290, 782, 500, 50);

        JLabel DailyMix2_TextInfo2_song5 = new JLabel("Post Malone, Quavo");
        DailyMix2_TextInfo2_song5.setFont(new Font("Arial", Font.ITALIC, 15));
        DailyMix2_TextInfo2_song5.setForeground(Color.GRAY);
        DailyMix2_TextInfo2_song5.setBounds(290, 805, 500, 50);

        JLabel DailyMix2_TextInfo3_song5 = new JLabel("3:40");
        DailyMix2_TextInfo3_song5.setFont(new Font("Arial", Font.PLAIN, 16));
        DailyMix2_TextInfo3_song5.setForeground(Color.GRAY);
        DailyMix2_TextInfo3_song5.setBounds(1021, 785, 500, 50);

        secondPage.add(DailyMix2_TextInfo_song5);
        secondPage.add(DailyMix2_TextInfo2_song5);
        secondPage.add(DailyMix2_TextInfo3_song5);


        // Song Directory for Daily mix 2
        SongDirectory dailyMix2 = new SongDirectory("Daily Mix2");
        songDirectories.add(dailyMix2);

        secondPage.add(DailyMix2_Text);
        secondPage.add(DailyMix2_Text2);
        secondPage.add(DailyMix2_Text3);
        secondPage.add(DailyMix2_Text4);

        JButton DM2_1 = new JButton();
        DM2_1.setBounds(200, 362, 100, 100);
        DM2_1.setIcon(createImageIcon("onedance.png"));
        DM2_1.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        DM2_1.setContentAreaFilled(false);
        DM2_1.addActionListener(this);

        JButton DM2_2 = new JButton();
        DM2_2.setBounds(200, 462, 100, 100);
        DM2_2.setIcon(createImageIcon("oneofwun.png"));
        DM2_2.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        DM2_2.setContentAreaFilled(false);
        DM2_2.addActionListener(this);

        JButton DM2_3 = new JButton();
        DM2_3.setBounds(200, 562, 100, 100);
        DM2_3.setIcon(createImageIcon("myeyes.png"));
        DM2_3.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        DM2_3.setContentAreaFilled(false);
        DM2_3.addActionListener(this);

        JButton DM2_4 = new JButton();
        DM2_4.setBounds(200, 662, 100, 100);
        DM2_4.setIcon(createImageIcon("solo.png"));
        DM2_4.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        DM2_4.setContentAreaFilled(false);
        DM2_4.addActionListener(this);

        JButton DM2_5 = new JButton();
        DM2_5.setBounds(200, 762, 100, 100);
        DM2_5.setIcon(createImageIcon("congrats.png"));
        DM2_5.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        DM2_5.setContentAreaFilled(false);
        DM2_5.addActionListener(this);

        secondPage.add(DM2_1);
        secondPage.add(DM2_2);
        secondPage.add(DM2_3);
        secondPage.add(DM2_4);
        secondPage.add(DM2_5);


        // Third Page:
        JPanel thirdPage = pages.get(2);

        JLabel DailyMix3_Text = new JLabel("Daily Mix 3");
        DailyMix3_Text.setFont(new Font("Arial", Font.BOLD, 45));
        DailyMix3_Text.setForeground(Color.WHITE);
        DailyMix3_Text.setBounds(290, 183, 300, 50);

        JLabel DailyMix3_Text2 = new JLabel("Public Playlist");
        DailyMix3_Text2.setFont(new Font("Arial", Font.BOLD, 17));
        DailyMix3_Text2.setForeground(Color.GRAY);
        DailyMix3_Text2.setBounds(290, 150, 300, 50);


        JLabel DailyMix3_Text3 = new JLabel("Featuring: Karan Aujila, AP Dhillon, Diljit Dosanjh, & More!");
        DailyMix3_Text3.setFont(new Font("Arial", Font.BOLD, 14));
        DailyMix3_Text3.setForeground(Color.GRAY);
        DailyMix3_Text3.setBounds(292, 218, 500, 50);

        JLabel DailyMix3_Text4 = new JLabel("5 songs, 12m 6s");
        DailyMix3_Text4.setFont(new Font("Arial", Font.BOLD, 14));
        DailyMix3_Text4.setForeground(Color.GRAY);
        DailyMix3_Text4.setBounds(292, 236, 300, 50);

        // Song one Dailymix3
        JLabel DailyMix3_TextInfo_song = new JLabel("Excuses");
        DailyMix3_TextInfo_song.setFont(new Font("Arial", Font.BOLD, 20));
        DailyMix3_TextInfo_song.setForeground(Color.WHITE);
        DailyMix3_TextInfo_song.setBounds(290, 382, 500, 50);

        JLabel DailyMix3_TextInfo2_song = new JLabel("AP Dhillon, Gurinder Gill");
        DailyMix3_TextInfo2_song.setFont(new Font("Arial", Font.ITALIC, 15));
        DailyMix3_TextInfo2_song.setForeground(Color.GRAY);
        DailyMix3_TextInfo2_song.setBounds(290, 405, 500, 50);

        JLabel DailyMix3_TextInfo3_song = new JLabel("2:56");
        DailyMix3_TextInfo3_song.setFont(new Font("Arial", Font.PLAIN, 16));
        DailyMix3_TextInfo3_song.setForeground(Color.GRAY);
        DailyMix3_TextInfo3_song.setBounds(1021, 404, 500, 50);

        thirdPage.add(DailyMix3_TextInfo_song);
        thirdPage.add(DailyMix3_TextInfo2_song);
        thirdPage.add(DailyMix3_TextInfo3_song);

        // Song two Dailymix3
        JLabel DailyMix3_TextInfo_song2 = new JLabel("8 ASLE");
        DailyMix3_TextInfo_song2.setFont(new Font("Arial", Font.BOLD, 20));
        DailyMix3_TextInfo_song2.setForeground(Color.WHITE);
        DailyMix3_TextInfo_song2.setBounds(290, 482, 500, 50);

        JLabel DailyMix3_TextInfo2_song2 = new JLabel("Sukha, Chani Nattan, Prodgk");
        DailyMix3_TextInfo2_song2.setFont(new Font("Arial", Font.ITALIC, 15));
        DailyMix3_TextInfo2_song2.setForeground(Color.GRAY);
        DailyMix3_TextInfo2_song2.setBounds(290, 505, 600, 50);

        JLabel DailyMix3_TextInfo3_song2 = new JLabel("2:41");
        DailyMix3_TextInfo3_song2.setFont(new Font("Arial", Font.PLAIN, 16));
        DailyMix3_TextInfo3_song2.setForeground(Color.GRAY);
        DailyMix3_TextInfo3_song2.setBounds(1021, 490, 500, 50);

        thirdPage.add(DailyMix3_TextInfo_song2);
        thirdPage.add(DailyMix3_TextInfo2_song2);
        thirdPage.add(DailyMix3_TextInfo3_song2);

        // Song three Dailymix3
        JLabel DailyMix3_TextInfo_song3 = new JLabel("Wavy");
        DailyMix3_TextInfo_song3.setFont(new Font("Arial", Font.BOLD, 20));
        DailyMix3_TextInfo_song3.setForeground(Color.WHITE);
        DailyMix3_TextInfo_song3.setBounds(290, 582, 500, 50);

        JLabel DailyMix3_TextInfo2_song3 = new JLabel("Karan Aujila");
        DailyMix3_TextInfo2_song3.setFont(new Font("Arial", Font.ITALIC, 15));
        DailyMix3_TextInfo2_song3.setForeground(Color.GRAY);
        DailyMix3_TextInfo2_song3.setBounds(290, 605, 600, 50);

        JLabel DailyMix3_TextInfo3_song3 = new JLabel("2:41");
        DailyMix3_TextInfo3_song3.setFont(new Font("Arial", Font.PLAIN, 16));
        DailyMix3_TextInfo3_song3.setForeground(Color.GRAY);
        DailyMix3_TextInfo3_song3.setBounds(1021, 585, 500, 50);

        thirdPage.add(DailyMix3_TextInfo_song3);
        thirdPage.add(DailyMix3_TextInfo2_song3);
        thirdPage.add(DailyMix3_TextInfo3_song3);

        // Song four Dailymix3
        JLabel DailyMix3_TextInfo_song4 = new JLabel("Naina");
        DailyMix3_TextInfo_song4.setFont(new Font("Arial", Font.BOLD, 20));
        DailyMix3_TextInfo_song4.setForeground(Color.WHITE);
        DailyMix3_TextInfo_song4.setBounds(290, 682, 500, 50);

        JLabel DailyMix3_TextInfo2_song4 = new JLabel("Diljit Dosanjh");
        DailyMix3_TextInfo2_song4.setFont(new Font("Arial", Font.ITALIC, 15));
        DailyMix3_TextInfo2_song4.setForeground(Color.GRAY);
        DailyMix3_TextInfo2_song4.setBounds(290, 705, 600, 50);

        JLabel DailyMix3_TextInfo3_song4 = new JLabel("3:00");
        DailyMix3_TextInfo3_song4.setFont(new Font("Arial", Font.PLAIN, 16));
        DailyMix3_TextInfo3_song4.setForeground(Color.GRAY);
        DailyMix3_TextInfo3_song4.setBounds(1021, 685, 500, 50);

        thirdPage.add(DailyMix3_TextInfo_song4);
        thirdPage.add(DailyMix3_TextInfo2_song4);
        thirdPage.add(DailyMix3_TextInfo3_song4);

        // Song five Dailymix3
        JLabel DailyMix3_TextInfo_song5 = new JLabel("Jee Ni Lagda");
        DailyMix3_TextInfo_song5.setFont(new Font("Arial", Font.BOLD, 20));
        DailyMix3_TextInfo_song5.setForeground(Color.WHITE);
        DailyMix3_TextInfo_song5.setBounds(290, 782, 500, 50);

        JLabel DailyMix3_TextInfo2_song5 = new JLabel("Karan Aujila, Ikky");
        DailyMix3_TextInfo2_song5.setFont(new Font("Arial", Font.ITALIC, 15));
        DailyMix3_TextInfo2_song5.setForeground(Color.GRAY);
        DailyMix3_TextInfo2_song5.setBounds(290, 805, 600, 50);

        JLabel DailyMix3_TextInfo3_song5 = new JLabel("2:19");
        DailyMix3_TextInfo3_song5.setFont(new Font("Arial", Font.PLAIN, 16));
        DailyMix3_TextInfo3_song5.setForeground(Color.GRAY);
        DailyMix3_TextInfo3_song5.setBounds(1021, 785, 500, 50);

        thirdPage.add(DailyMix3_TextInfo_song5);
        thirdPage.add(DailyMix3_TextInfo2_song5);
        thirdPage.add(DailyMix3_TextInfo3_song5);

        // Song Directory for Daily mix 3
        SongDirectory dailyMix3 = new SongDirectory("Daily Mix3");
        songDirectories.add(dailyMix3);

        thirdPage.add(DailyMix3_Text);
        thirdPage.add(DailyMix3_Text2);
        thirdPage.add(DailyMix3_Text3);
        thirdPage.add(DailyMix3_Text4);

        JButton DM3_1 = new JButton();
        DM3_1.setBounds(200, 362, 100, 100);
        DM3_1.setIcon(createImageIcon("excuses.png"));
        DM3_1.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        DM3_1.setContentAreaFilled(false);
        DM3_1.addActionListener(this);

        JButton DM3_2 = new JButton();
        DM3_2.setBounds(200, 462, 100, 100);
        DM3_2.setIcon(createImageIcon("8asle.png"));
        DM3_2.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        DM3_2.setContentAreaFilled(false);
        DM3_2.addActionListener(this);

        JButton DM3_3 = new JButton();
        DM3_3.setBounds(200, 562, 100, 100);
        DM3_3.setIcon(createImageIcon("wavy.png"));
        DM3_3.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        DM3_3.setContentAreaFilled(false);
        DM3_3.addActionListener(this);

        JButton DM3_4 = new JButton();
        DM3_4.setBounds(200, 662, 100, 100);
        DM3_4.setIcon(createImageIcon("naina.png"));
        DM3_4.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        DM3_4.setContentAreaFilled(false);
        DM3_4.addActionListener(this);

        JButton DM3_5 = new JButton();
        DM3_5.setBounds(200, 762, 100, 100);
        DM3_5.setIcon(createImageIcon("aujila.png"));
        DM3_5.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        DM3_5.setContentAreaFilled(false);
        DM3_5.addActionListener(this);

        thirdPage.add(DM3_1);
        thirdPage.add(DM3_2);
        thirdPage.add(DM3_3);
        thirdPage.add(DM3_4);
        thirdPage.add(DM3_5);


        // Fourth Page:
        JPanel fourthPage = pages.get(3);

        JLabel DailyMix4_Text = new JLabel("Daily Mix 4");
        DailyMix4_Text.setFont(new Font("Arial", Font.BOLD, 45));
        DailyMix4_Text.setForeground(Color.WHITE);
        DailyMix4_Text.setBounds(290, 183, 300, 50);

        JLabel DailyMix4_Text2 = new JLabel("Public Playlist");
        DailyMix4_Text2.setFont(new Font("Arial", Font.BOLD, 17));
        DailyMix4_Text2.setForeground(Color.GRAY);
        DailyMix4_Text2.setBounds(290, 150, 300, 50);


        JLabel DailyMix4_Text3 = new JLabel("Featuring: PARTYNEXTDOOR, Frank Ocean, Bruno Mars, & More!");
        DailyMix4_Text3.setFont(new Font("Arial", Font.BOLD, 14));
        DailyMix4_Text3.setForeground(Color.GRAY);
        DailyMix4_Text3.setBounds(292, 218, 500, 50);

        JLabel DailyMix4_Text4 = new JLabel("5 songs, 17m 53s");
        DailyMix4_Text4.setFont(new Font("Arial", Font.BOLD, 14));
        DailyMix4_Text4.setForeground(Color.GRAY);
        DailyMix4_Text4.setBounds(292, 236, 300, 50);

        // Song one Dailymix4
        JLabel DailyMix4_TextInfo = new JLabel("Die With A Smile");
        DailyMix4_TextInfo.setFont(new Font("Arial", Font.BOLD, 20));
        DailyMix4_TextInfo.setForeground(Color.WHITE);
        DailyMix4_TextInfo.setBounds(290, 382, 500, 50);

        JLabel DailyMix4_TextInfo2 = new JLabel("Lady Gaga, Bruno Mars");
        DailyMix4_TextInfo2.setFont(new Font("Arial", Font.ITALIC, 15));
        DailyMix4_TextInfo2.setForeground(Color.GRAY);
        DailyMix4_TextInfo2.setBounds(290, 405, 500, 50);

        JLabel DailyMix4_TextInfo3 = new JLabel("4:11");
        DailyMix4_TextInfo3.setFont(new Font("Arial", Font.PLAIN, 16));
        DailyMix4_TextInfo3.setForeground(Color.GRAY);
        DailyMix4_TextInfo3.setBounds(1021, 404, 500, 50);

        fourthPage.add(DailyMix4_TextInfo);
        fourthPage.add(DailyMix4_TextInfo2);
        fourthPage.add(DailyMix4_TextInfo3);

        // Song two Dailymix4
        JLabel DailyMix4_TextInfo_song2 = new JLabel("Pink + White");
        DailyMix4_TextInfo_song2.setFont(new Font("Arial", Font.BOLD, 20));
        DailyMix4_TextInfo_song2.setForeground(Color.WHITE);
        DailyMix4_TextInfo_song2.setBounds(290, 482, 500, 50);

        JLabel DailyMix4_TextInfo2_song2 = new JLabel("Frank Ocean");
        DailyMix4_TextInfo2_song2.setFont(new Font("Arial", Font.ITALIC, 15));
        DailyMix4_TextInfo2_song2.setForeground(Color.GRAY);
        DailyMix4_TextInfo2_song2.setBounds(290, 505, 500, 50);

        JLabel DailyMix4_TextInfo3_song2 = new JLabel("3:04");
        DailyMix4_TextInfo3_song2.setFont(new Font("Arial", Font.PLAIN, 16));
        DailyMix4_TextInfo3_song2.setForeground(Color.GRAY);
        DailyMix4_TextInfo3_song2.setBounds(1021, 490, 500, 50);

        fourthPage.add(DailyMix4_TextInfo_song2);
        fourthPage.add(DailyMix4_TextInfo2_song2);
        fourthPage.add(DailyMix4_TextInfo3_song2);

        // Song three Dailymix4
        JLabel DailyMix4_TextInfo_song3 = new JLabel("Dreamin");
        DailyMix4_TextInfo_song3.setFont(new Font("Arial", Font.BOLD, 20));
        DailyMix4_TextInfo_song3.setForeground(Color.WHITE);
        DailyMix4_TextInfo_song3.setBounds(290, 582, 500, 50);

        JLabel DailyMix4_TextInfo2_song3 = new JLabel("PARTYNEXTDOOR");
        DailyMix4_TextInfo2_song3.setFont(new Font("Arial", Font.ITALIC, 15));
        DailyMix4_TextInfo2_song3.setForeground(Color.GRAY);
        DailyMix4_TextInfo2_song3.setBounds(290, 605, 500, 50);

        JLabel DailyMix4_TextInfo3_song3 = new JLabel("2:27");
        DailyMix4_TextInfo3_song3.setFont(new Font("Arial", Font.PLAIN, 16));
        DailyMix4_TextInfo3_song3.setForeground(Color.GRAY);
        DailyMix4_TextInfo3_song3.setBounds(1021, 585, 500, 50);

        fourthPage.add(DailyMix4_TextInfo_song3);
        fourthPage.add(DailyMix4_TextInfo2_song3);
        fourthPage.add(DailyMix4_TextInfo3_song3);

        // Song four Dailymix4
        JLabel DailyMix4_TextInfo_song4 = new JLabel("Her Way");
        DailyMix4_TextInfo_song4.setFont(new Font("Arial", Font.BOLD, 20));
        DailyMix4_TextInfo_song4.setForeground(Color.WHITE);
        DailyMix4_TextInfo_song4.setBounds(290, 682, 500, 50);

        JLabel DailyMix4_TextInfo2_song4 = new JLabel("PARTYNEXTDOOR");
        DailyMix4_TextInfo2_song4.setFont(new Font("Arial", Font.ITALIC, 15));
        DailyMix4_TextInfo2_song4.setForeground(Color.GRAY);
        DailyMix4_TextInfo2_song4.setBounds(290, 705, 500, 50);

        JLabel DailyMix4_TextInfo3_song4 = new JLabel("3:36");
        DailyMix4_TextInfo3_song4.setFont(new Font("Arial", Font.PLAIN, 16));
        DailyMix4_TextInfo3_song4.setForeground(Color.GRAY);
        DailyMix4_TextInfo3_song4.setBounds(1021, 685, 500, 50);

        fourthPage.add(DailyMix4_TextInfo_song4);
        fourthPage.add(DailyMix4_TextInfo2_song4);
        fourthPage.add(DailyMix4_TextInfo3_song4);

        // Song five Dailymix4
        JLabel DailyMix4_TextInfo_song5 = new JLabel("Angel Numbers/Ten Toes");
        DailyMix4_TextInfo_song5.setFont(new Font("Arial", Font.BOLD, 20));
        DailyMix4_TextInfo_song5.setForeground(Color.WHITE);
        DailyMix4_TextInfo_song5.setBounds(290, 782, 600, 50);

        JLabel DailyMix4_TextInfo2_song5 = new JLabel("Chris Brown");
        DailyMix4_TextInfo2_song5.setFont(new Font("Arial", Font.ITALIC, 15));
        DailyMix4_TextInfo2_song5.setForeground(Color.GRAY);
        DailyMix4_TextInfo2_song5.setBounds(290, 805, 500, 50);

        JLabel DailyMix4_TextInfo3_song5 = new JLabel("5:06");
        DailyMix4_TextInfo3_song5.setFont(new Font("Arial", Font.PLAIN, 16));
        DailyMix4_TextInfo3_song5.setForeground(Color.GRAY);
        DailyMix4_TextInfo3_song5.setBounds(1021, 785, 500, 50);

        fourthPage.add(DailyMix4_TextInfo_song5);
        fourthPage.add(DailyMix4_TextInfo2_song5);
        fourthPage.add(DailyMix4_TextInfo3_song5);


        // Song Directory for Daily mix 4
        SongDirectory dailyMix4 = new SongDirectory("Daily Mix4");
        songDirectories.add(dailyMix4);

        fourthPage.add(DailyMix4_Text);
        fourthPage.add(DailyMix4_Text2);
        fourthPage.add(DailyMix4_Text3);
        fourthPage.add(DailyMix4_Text4);

        JButton DM4_1 = new JButton();
        DM4_1.setBounds(200, 362, 100, 100);
        DM4_1.setIcon(createImageIcon("Die.png"));
        DM4_1.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        DM4_1.setContentAreaFilled(false);
        DM4_1.addActionListener(this);

        JButton DM4_2 = new JButton();
        DM4_2.setBounds(200, 462, 100, 100);
        DM4_2.setIcon(createImageIcon("Frankocean.png"));
        DM4_2.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        DM4_2.setContentAreaFilled(false);
        DM4_2.addActionListener(this);

        JButton DM4_3 = new JButton();
        DM4_3.setBounds(200, 562, 100, 100);
        DM4_3.setIcon(createImageIcon("Dreamin.png"));
        DM4_3.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        DM4_3.setContentAreaFilled(false);
        DM4_3.addActionListener(this);

        JButton DM4_4 = new JButton();
        DM4_4.setBounds(200, 662, 100, 100);
        DM4_4.setIcon(createImageIcon("Herway.png"));
        DM4_4.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        DM4_4.setContentAreaFilled(false);
        DM4_4.addActionListener(this);

        JButton DM4_5 = new JButton();
        DM4_5.setBounds(200, 762, 100, 100);
        DM4_5.setIcon(createImageIcon("Chrisbrown.png"));
        DM4_5.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        DM4_5.setContentAreaFilled(false);
        DM4_5.addActionListener(this);


        fourthPage.add(DM4_1);
        fourthPage.add(DM4_2);
        fourthPage.add(DM4_3);
        fourthPage.add(DM4_4);
        fourthPage.add(DM4_5);


        // Fifth Page:
        JPanel fifthPage = pages.get(4);

        JLabel HottestHits_Text = new JLabel("Latest Releases");
        HottestHits_Text.setFont(new Font("Arial", Font.BOLD, 45));
        HottestHits_Text.setForeground(Color.WHITE);
        HottestHits_Text.setBounds(290, 183, 500, 50);

        JLabel HottestHits_Text2 = new JLabel("Public Playlist");
        HottestHits_Text2.setFont(new Font("Arial", Font.BOLD, 17));
        HottestHits_Text2.setForeground(Color.GRAY);
        HottestHits_Text2.setBounds(290, 150, 300, 50);


        JLabel HottestHits_Text3 = new JLabel("Featuring: Travis Scott, Lil Baby, Playboi Carti, & More!");
        HottestHits_Text3.setFont(new Font("Arial", Font.BOLD, 14));
        HottestHits_Text3.setForeground(Color.GRAY);
        HottestHits_Text3.setBounds(292, 218, 500, 50);

        JLabel HottestHits_Text4 = new JLabel("5 songs, 17m 53s");
        HottestHits_Text4.setFont(new Font("Arial", Font.BOLD, 14));
        HottestHits_Text4.setForeground(Color.GRAY);
        HottestHits_Text4.setBounds(292, 236, 300, 50);


        // Song Directory for Latest Releases
        SongDirectory latestReleases = new SongDirectory("Latest Releases");
        songDirectories.add(latestReleases);

        fifthPage.add(HottestHits_Text);
        fifthPage.add(HottestHits_Text2);
        fifthPage.add(HottestHits_Text3);
        fifthPage.add(HottestHits_Text4);

        JButton LR_1 = new JButton();
        LR_1.setBounds(200, 362, 100, 100);
        LR_1.setIcon(createImageIcon("winningspeech.png"));
        LR_1.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        LR_1.setContentAreaFilled(false);
        LR_1.addActionListener(this);

        JButton LR_2 = new JButton();
        LR_2.setBounds(200, 462, 100, 100);
        LR_2.setIcon(createImageIcon("itsup.png"));
        LR_2.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        LR_2.setContentAreaFilled(false);
        LR_2.addActionListener(this);

        JButton LR_3 = new JButton();
        LR_3.setBounds(200, 562, 100, 100);
        LR_3.setIcon(createImageIcon("notlikeus.png"));
        LR_3.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        LR_3.setContentAreaFilled(false);
        LR_3.addActionListener(this);

        JButton LR_4 = new JButton();
        LR_4.setBounds(200, 662, 100, 100);
        LR_4.setIcon(createImageIcon("lilbaby.png"));
        LR_4.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        LR_4.setContentAreaFilled(false);
        LR_4.addActionListener(this);

        JButton LR_5 = new JButton();
        LR_5.setBounds(200, 762, 100, 100);
        LR_5.setIcon(createImageIcon("myeyes.png"));
        LR_5.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        LR_5.setContentAreaFilled(false);
        LR_5.addActionListener(this);

        fifthPage.add(LR_1);
        fifthPage.add(LR_2);
        fifthPage.add(LR_3);
        fifthPage.add(LR_4);
        fifthPage.add(LR_5);

        // Sixth Page:
        JPanel sixthPage = pages.get(5);

        JLabel HottestHits2_Text = new JLabel("Beast Mode");
        HottestHits2_Text.setFont(new Font("Arial", Font.BOLD, 45));
        HottestHits2_Text.setForeground(Color.WHITE);
        HottestHits2_Text.setBounds(290, 183, 300, 50);

        JLabel HottestHits2_Text2 = new JLabel("Public Playlist");
        HottestHits2_Text2.setFont(new Font("Arial", Font.BOLD, 17));
        HottestHits2_Text2.setForeground(Color.GRAY);
        HottestHits2_Text2.setBounds(290, 150, 300, 50);


        JLabel HottestHits2_Text3 = new JLabel("Featuring: XXX, XXX, XXX, & More!");
        HottestHits2_Text3.setFont(new Font("Arial", Font.BOLD, 14));
        HottestHits2_Text3.setForeground(Color.GRAY);
        HottestHits2_Text3.setBounds(292, 218, 500, 50);

        JLabel HottestHits2_Text4 = new JLabel("5 songs, 17m 53s");
        HottestHits2_Text4.setFont(new Font("Arial", Font.BOLD, 14));
        HottestHits2_Text4.setForeground(Color.GRAY);
        HottestHits2_Text4.setBounds(292, 236, 300, 50);

        // Song Directory for Beast Mode
        SongDirectory beastMode = new SongDirectory("Beast Mode");
        songDirectories.add(beastMode);

        JButton BM_1 = new JButton();
        BM_1.setBounds(200, 362, 100, 100);
        BM_1.setIcon(createImageIcon("glock.png"));
        BM_1.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        BM_1.setContentAreaFilled(false);
        BM_1.addActionListener(this);

        JButton BM_2 = new JButton();
        BM_2.setBounds(200, 462, 100, 100);
        BM_2.setIcon(createImageIcon("purecoke.png"));
        BM_2.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        BM_2.setContentAreaFilled(false);
        BM_2.addActionListener(this);

        JButton BM_3 = new JButton();
        BM_3.setBounds(200, 562, 100, 100);
        BM_3.setIcon(createImageIcon("astroworld.png"));
        BM_3.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        BM_3.setContentAreaFilled(false);
        BM_3.addActionListener(this);

        JButton BM_4 = new JButton();
        BM_4.setBounds(200, 662, 100, 100);
        BM_4.setIcon(createImageIcon("Toreup.png"));
        BM_4.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        BM_4.setContentAreaFilled(false);
        BM_4.addActionListener(this);

        JButton BM_5 = new JButton();
        BM_5.setBounds(200, 762, 100, 100);
        BM_5.setIcon(createImageIcon("nonstop.png"));
        BM_5.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        BM_5.setContentAreaFilled(false);
        BM_5.addActionListener(this);


        sixthPage.add(BM_1);
        sixthPage.add(BM_2);
        sixthPage.add(BM_3);
        sixthPage.add(BM_4);
        sixthPage.add(BM_5);


        sixthPage.add(HottestHits2_Text);
        sixthPage.add(HottestHits2_Text2);
        sixthPage.add(HottestHits2_Text3);
        sixthPage.add(HottestHits2_Text4);


        // Seventh Page:
        JPanel seventhPage = pages.get(6);

        JLabel HottestHits3_Text = new JLabel("Hot Hits R&B");
        HottestHits3_Text.setFont(new Font("Arial", Font.BOLD, 45));
        HottestHits3_Text.setForeground(Color.WHITE);
        HottestHits3_Text.setBounds(290, 183, 500, 50);

        JLabel HottestHits3_Text2 = new JLabel("Public Playlist");
        HottestHits3_Text2.setFont(new Font("Arial", Font.BOLD, 17));
        HottestHits3_Text2.setForeground(Color.GRAY);
        HottestHits3_Text2.setBounds(290, 150, 300, 50);


        JLabel HottestHits3_Text3 = new JLabel("Featuring: XXX, XXX, XXX, & More!");
        HottestHits3_Text3.setFont(new Font("Arial", Font.BOLD, 14));
        HottestHits3_Text3.setForeground(Color.GRAY);
        HottestHits3_Text3.setBounds(292, 218, 500, 50);

        JLabel HottestHits3_Text4 = new JLabel("5 songs, 17m 53s");
        HottestHits3_Text4.setFont(new Font("Arial", Font.BOLD, 14));
        HottestHits3_Text4.setForeground(Color.GRAY);
        HottestHits3_Text4.setBounds(292, 236, 300, 50);

        // Song Directory for hot hits
        SongDirectory hotHits = new SongDirectory("Hot Hits");
        songDirectories.add(hotHits);

        JButton HH_1 = new JButton();
        HH_1.setBounds(200, 362, 100, 100);
        HH_1.setIcon(createImageIcon("Morning.png"));
        HH_1.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        HH_1.setContentAreaFilled(false);
        HH_1.addActionListener(this);

        JButton HH_2 = new JButton();
        HH_2.setBounds(200, 462, 100, 100);
        HH_2.setIcon(createImageIcon("kehlani.png"));
        HH_2.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        HH_2.setContentAreaFilled(false);
        HH_2.addActionListener(this);

        JButton HH_3 = new JButton();
        HH_3.setBounds(200, 562, 100, 100);
        HH_3.setIcon(createImageIcon("madeforme.png"));
        HH_3.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        HH_3.setContentAreaFilled(false);
        HH_3.addActionListener(this);

        JButton HH_4 = new JButton();
        HH_4.setBounds(200, 662, 100, 100);
        HH_4.setIcon(createImageIcon("timeless.png"));
        HH_4.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        HH_4.setContentAreaFilled(false);
        HH_4.addActionListener(this);

        JButton HH_5 = new JButton();
        HH_5.setBounds(200, 762, 100, 100);
        HH_5.setIcon(createImageIcon("saturn.png"));
        HH_5.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        HH_5.setContentAreaFilled(false);
        HH_5.addActionListener(this);

        seventhPage.add(HH_1);
        seventhPage.add(HH_2);
        seventhPage.add(HH_3);
        seventhPage.add(HH_4);
        seventhPage.add(HH_5);

        seventhPage.add(HottestHits3_Text);
        seventhPage.add(HottestHits3_Text2);
        seventhPage.add(HottestHits3_Text3);
        seventhPage.add(HottestHits3_Text4);

        // Eigth Page:
        JPanel eigthPage = pages.get(7);

        JLabel HottestHits4_Text = new JLabel("South Asian Gems");
        HottestHits4_Text.setFont(new Font("Arial", Font.BOLD, 45));
        HottestHits4_Text.setForeground(Color.WHITE);
        HottestHits4_Text.setBounds(290, 183, 500, 50);

        JLabel HottestHits4_Text2 = new JLabel("Public Playlist");
        HottestHits4_Text2.setFont(new Font("Arial", Font.BOLD, 17));
        HottestHits4_Text2.setForeground(Color.GRAY);
        HottestHits4_Text2.setBounds(290, 150, 300, 50);


        JLabel HottestHits4_Text3 = new JLabel("Featuring: XXX, XXX, XXX, & More!");
        HottestHits4_Text3.setFont(new Font("Arial", Font.BOLD, 14));
        HottestHits4_Text3.setForeground(Color.GRAY);
        HottestHits4_Text3.setBounds(292, 218, 500, 50);

        JLabel HottestHits4_Text4 = new JLabel("5 songs, 17m 53s");
        HottestHits4_Text4.setFont(new Font("Arial", Font.BOLD, 14));
        HottestHits4_Text4.setForeground(Color.GRAY);
        HottestHits4_Text4.setBounds(292, 236, 300, 50);

        // Song Directory for hot hits
        SongDirectory southAsianGems = new SongDirectory("South Asian Gems");
        songDirectories.add(southAsianGems);

        JButton SAG_1 = new JButton();
        SAG_1.setBounds(200, 362, 100, 100);
        SAG_1.setIcon(createImageIcon("tujh.png"));
        SAG_1.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        SAG_1.setContentAreaFilled(false);
        SAG_1.addActionListener(this);

        JButton SAG_2 = new JButton();
        SAG_2.setBounds(200, 462, 100, 100);
        SAG_2.setIcon(createImageIcon("hayyoda.png"));
        SAG_2.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        SAG_2.setContentAreaFilled(false);
        SAG_2.addActionListener(this);

        JButton SAG_3 = new JButton();
        SAG_3.setBounds(200, 562, 100, 100);
        SAG_3.setIcon(createImageIcon("jimmiki.png"));
        SAG_3.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        SAG_3.setContentAreaFilled(false);
        SAG_3.addActionListener(this);

        JButton SAG_4 = new JButton();
        SAG_4.setBounds(200, 662, 100, 100);
        SAG_4.setIcon(createImageIcon("chut.png"));
        SAG_4.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        SAG_4.setContentAreaFilled(false);
        SAG_4.addActionListener(this);

        JButton SAG_5 = new JButton();
        SAG_5.setBounds(200, 762, 100, 100);
        SAG_5.setIcon(createImageIcon("fazat.png"));
        SAG_5.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        SAG_5.setContentAreaFilled(false);
        SAG_5.addActionListener(this);

        eigthPage.add(SAG_1);
        eigthPage.add(SAG_2);
        eigthPage.add(SAG_3);
        eigthPage.add(SAG_4);
        eigthPage.add(SAG_5);

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
            iconImages.setBorder(BorderFactory.createEmptyBorder());
            iconImages.setContentAreaFilled(false);
            iconImages.addActionListener(this);

            // Clock
            ClockButton = new JButton();
            ClockButton.setBounds(1012, 372, 50, 50);
            ClockButton.setIcon(createImageIcon("clock_icon.png"));
            ClockButton.setBorder(BorderFactory.createEmptyBorder());
            ClockButton.setContentAreaFilled(false);
            ClockButton.addActionListener(this);

            current_page.add(ClockButton);

            // HomeButton:
            homeButton = new JButton(createImageIcon("botify_logo.png"));
            homeButton.setBounds(10, 10, 100, 80);
            homeButton.setBorderPainted(false);
            homeButton.setContentAreaFilled(false);
            homeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    goToMainPage(current_page);
                }
            });

            // Individual Play/Pause Buttons:
            int playPause_y = 362; // Initial y position

            ArrayList<JButton> pagePlayButtons = new ArrayList<JButton>();
            for (int j = 0; j < 5; j++) {
                JButton playPausePg = new JButton();
                playPausePg.setBounds(100, playPause_y, 100, 100); // Set bounds with playPause1_y
                playPausePg.setIcon(createImageIcon("play_1.png"));
                playPausePg.setBorder(BorderFactory.createEmptyBorder());
                playPausePg.setContentAreaFilled(false);
                playPausePg.addActionListener(this);
                current_page.add(playPausePg);
                pagePlayButtons.add(playPausePg);

                playPause_y += 100; // Increment playPause1_y for the next button
            }
            allPagePlayPauseButtons.add(pagePlayButtons);

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

            // Main Panel
            RoundedSquarePanel CoverArtMainPanel = new RoundedSquarePanel(new Color(45, 47, 51), 500);
            CoverArtMainPanel.setBounds(10, 340, 1200, 545);


            // Adding
            current_page.add(iconImages);
            current_page.add(homeButton);
            current_page.add(PlaylistPanel);
            current_page.add(CoverArtMainPanel);
            current_page.add(CoverArtTopPanel);

            // Default Page View is Home (-1):
            pageView = -1;

        }
    }


    private void showPage(JPanel page) {
        mainPanel.setVisible(false);
        blackPage.setVisible(false);
        page.setVisible(true);
        pageView = pages.indexOf(page);  // From pages 1-8 (0-7, inclusive)
    }

    private void goToMainPage(JPanel page) {
        blackPage.setVisible(false);
        mainPanel.setVisible(true);
        page.setVisible(false);
        pageView = -1;
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

        // Home Page (pageView == -1)
        /*
         * - pageView != -1 means not on home page
         * - pagePlayPauseButtons is a 2D ArrayList containing ArrayLists of JButton objects
         * - The inner arraylist represents the collection of JButton objects for individual pages
         * - The if-condition intends to check if the source (i.e. the button being clicked) is indeed
         *   part of the particular page's buttons (this means the user isn't going to click on some button
         *   (in the home page and suddenly the button on the cover art page is activated, or something strange).
         *
         * Overall: Checks if the playPauseButton is activated (the same one as on the home page)
         *          or if the individual buttons to each cover art page is being activated.
         */
        if (source == playPauseButton || (pageView != -1 && allPagePlayPauseButtons.get(pageView).contains(source))) {

            // If this section is reached, then the above condition guarantees that <source> is a JButton object
            JButton currentButton = (JButton) source;  // <source> is also a JButton, cast it to one
            // <source> is the button that is currently pressed

            // Grab the specific list from <allPagePlayPauseButtons> (list) according to <pageView>
            // and put that into the <buttonsOnCurrentPage>
            ArrayList<JButton> buttonsOnCurrentPage;
            if (source == playPauseButton) {
                buttonsOnCurrentPage = new ArrayList<>();
                buttonsOnCurrentPage.add(playPauseButton);
            } else {
                buttonsOnCurrentPage = allPagePlayPauseButtons.get(pageView);  // to avoid multiple .get() calls.
            }

            // When the user intends to play another song before pausing the current:
            if (lastButtonPressed != null && lastButtonPressed != currentButton) {
                // Reaching here means pressing a new button (attempting to play a different song)
                musicPlayer.pauseAudio();  // The music player should pause its current song
            }

            if (musicPlaying) { // When the player is playing
                musicPlayer.pauseAudio();  // Pause the player
                playPauseButton.setIcon(createImageIcon("play_icon.png"));
                if (pageView != -1) {  // pageView != -1 means not on home page (aka on cover art page)
                    for (int i = 0; i < buttonsOnCurrentPage.size(); i++) { // if you are not on home page, and you pause, change all icons to play icon.
                        buttonsOnCurrentPage.get(i).setIcon(createImageIcon("play_1.png"));
                    }
                }
            } else {  // When the player is paused
                musicPlayer.playAudio();  // Play the player
                playPauseButton.setIcon(createImageIcon("pause_icon.png"));
                if (pageView != -1) {
                    for (int i = 0; i < buttonsOnCurrentPage.size(); i++) {
                        buttonsOnCurrentPage.get(i).setIcon(createImageIcon("play_1.png"));
                    }
                    currentButton.setIcon(createImageIcon("pause_1.png"));
                    // Add specific songs to the queue:
                    SongQueue currentSongQueue = musicPlayer.getSongQueue();  // Grab the song queue from the music player
                    currentSongQueue.clearSongQueue(); // wipes the queue
                    int songIndex = buttonsOnCurrentPage.indexOf(currentButton);  // Retrieve the position of the button
                    // from the buttons list (from the current page)
                    ArrayList<String> songsOnPage = songDirectories.get(pageView).getSongs();  // Retrieve the list of song paths
                    // Retrieve the spliced sub-ArrayList, starting from where the user intends to play songs: (e.g. song 2 to song 5)
                    ArrayList<String> songsToAdd = new ArrayList<String>(songsOnPage.subList(songIndex, songsOnPage.size()));
                    currentSongQueue.addSongs(songsToAdd); // Adds the songs to add to the queue
                    musicPlayer.skipSong();  // Immediately start playing the first song as enqueued
                }
            }

            musicPlaying = !musicPlaying;
            lastButtonPressed = currentButton;
        }
    }

    public static void main(String[] args) {
        new UI();
    }
}