//20225494 이보나

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Final_CarGame extends JPanel implements ActionListener, KeyListener {
    private int KittyX;
    private int KittyY;
    private int KittySpeed;
    private int CloudWidth;
    private int CloudHeight;
    private ArrayList<Rectangle> Clouds;
    private ArrayList<Rectangle> anotherClouds;
    private Timer timer;
    private boolean isGameOver;
    private int remain;
    private Random random;
    private Image cloudImage;
    private Image kittyImage;
    private Image anotherCloudImage;
    private Image GamebackgroundImage;
  
    public static void main(String[] args) {
        JFrame frame = new JFrame("KITTY'S ADVENTURE");
        JComboBox<String> levelComboBox = new JComboBox<>(new String[]{"Level1", "Level2"});
        JButton startButton;
        JButton stopButton;

//----- 패널1: 메인 화면--------------------------------------------------------------------------------------------------
        JPanel panel1 = new JPanel(new BorderLayout()); // BorderLayout 사용
        ImageIcon kittyImage = new ImageIcon("키티배경1_.jpg");
        
        JLabel kittyLabel = new JLabel(kittyImage);
        

        JPanel buttonsPanel = new JPanel();
        startButton = new JButton("게임 시작");
        stopButton = new JButton("게임 종료");
        buttonsPanel.add(startButton);
        buttonsPanel.add(stopButton);
        buttonsPanel.add(levelComboBox);
        
        frame.add(panel1, BorderLayout.CENTER); // panel1을 중앙에 추가
        panel1.add(kittyLabel, BorderLayout.CENTER);
        panel1.add(buttonsPanel, BorderLayout.NORTH);
        
        
//----- 패널2: CarGame 화면-----------------------------------------------------------------------------------------------
        Final_CarGame game = new Final_CarGame();
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.add(game, BorderLayout.CENTER);

//------패널1-> 패널2 전환----------------------------------------------------------------------------------------------------
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel1.setVisible(false); // 패널1 숨김
                panel2.setVisible(true);  // 패널2 보임
                game.requestFocus(); // 포커스를 CarGame 패널로 설정

                String selectedLevel = (String) levelComboBox.getSelectedItem();
                if ("Level1".equals(selectedLevel)) {
                    game.initGameLevel1(); // 레벨 1 초기화 메서드 호출
                } else if ("Level2".equals(selectedLevel)) {
                    game.initGameLevel2(); // 레벨 2 초기화 메서드 호출
                }
                // panel1 내의 컴포넌트 숨김
                startButton.setVisible(false);
                stopButton.setVisible(false);
                levelComboBox.setVisible(false);
            }
        });
        // stop 버튼을 누르면 게임 종료
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // 초기 상태에서는 패널1을 보이고 패널2를 숨깁니다.
        panel1.setVisible(true);
        panel2.setVisible(false);

        frame.add(panel1);
        frame.add(panel2);
        frame.setSize(700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new CardLayout());
        frame.setVisible(true);
    }
    
    public Final_CarGame() {
        KittyX = 200;
        KittyY = 300;
        KittySpeed = 10;
        CloudWidth = 700;
        CloudHeight = 600;
        Clouds = new ArrayList<>();
        anotherClouds = new ArrayList<>();

        isGameOver = false;
        remain = 10; // 초기 점수 설정
        random = new Random();
        timer = new Timer(20, this);
        timer.start();
        addKeyListener(this);
        setFocusable(true);
        requestFocus(); // 포커스를 요청합니다.
        
        cloudImage = new ImageIcon("파란구름.png").getImage();
        kittyImage = new ImageIcon("키티.png").getImage();
        GamebackgroundImage = new ImageIcon("하늘.jpg").getImage();
        anotherCloudImage = new ImageIcon("먹구름.png").getImage();
    }
        
    public void initGameLevel1() {
    	remain=10;
        Clouds.clear(); // 모든 장애물 제거
        anotherClouds.clear(); // 레벨 1에서 다른 구름 이미지를 초기화
        // 레벨 1에 필요한 초기화 작업 수행
        for (int i = 0; i < 8; i++) {
            int obsX = random.nextInt(CloudHeight - 30);
            int obsY = random.nextInt(CloudHeight - 30);
            Clouds.add(new Rectangle(obsX, obsY, 30, 30));
            
        }
        for (int i = 0; i < 4; i++) {
            int anotherCloudX = random.nextInt(CloudWidth - 70);
            int anotherCloudY = random.nextInt(CloudHeight - 70);
            anotherClouds.add(new Rectangle(anotherCloudX, anotherCloudY, 70, 70));
        }
    }

    public void initGameLevel2() {
    	remain=10;
        Clouds.clear(); // 모든 장애물 제거
        anotherClouds.clear(); // 레벨 2에서 다른 구름 이미지를 초기화
        // 레벨 2에 필요한 초기화 작업 수행
        for (int i = 0; i < 10; i++) {
            int obsX = random.nextInt(CloudHeight - 30);
            int obsY = random.nextInt(CloudHeight - 30);
            Clouds.add(new Rectangle(obsX, obsY, 30, 30));
            
        }
        for (int i = 0; i < 6; i++) {
            int anotherCloudX = random.nextInt(CloudWidth - 70);
            int anotherCloudY = random.nextInt(CloudHeight - 70);
            anotherClouds.add(new Rectangle(anotherCloudX, anotherCloudY, 70, 70));
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(GamebackgroundImage, 0, 0, CloudWidth, CloudHeight, this);
        

        // 장애물 그리기
        for (Rectangle obs : Clouds) {
            g.drawImage(cloudImage, obs.x, obs.y, 90, 65, this);
        }
        for (Rectangle anotherCloud : anotherClouds) {
            g.drawImage(anotherCloudImage, anotherCloud.x, anotherCloud.y, 90, 65, this);
        }

        // 키티 그리기
        g.drawImage(kittyImage, KittyX, KittyY, 95, 85, this);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Remain: " + remain, 10, 30);
        
        if (isGameOver) {
        	g.setColor(Color.YELLOW);
        	g.fillRect(150, 220, 425, 140);
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 60));
            g.drawString("~ Game Over ~", 150, CloudHeight / 2);
        }
    }
        
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isGameOver) {
        	//파란구름 이동 업데이트
            for (int i = 0; i < Clouds.size(); i++) {
                Rectangle obs = Clouds.get(i);
                obs.x -= KittySpeed; // 오른쪽에서 왼쪽으로 이동
                if (obs.x + obs.width < 0) {
                    Clouds.remove(i);
                    int obsY = random.nextInt(CloudHeight - 30);
                    Clouds.add(new Rectangle(CloudWidth - 30, obsY, 30, 30));
                }
            }
            
            // 먹구름 이동 업데이트
            for (int i = 0; i < anotherClouds.size(); i++) {
                Rectangle anotherCloud = anotherClouds.get(i);
                anotherCloud.x -= KittySpeed; // 오른쪽에서 왼쪽으로 이동
                if (anotherCloud.x + anotherCloud.width < 0) {
                    anotherClouds.remove(i);
                    int anotherCloudY = random.nextInt(CloudHeight - 70);
                    anotherClouds.add(new Rectangle(CloudWidth - 70, anotherCloudY, 70, 70));
                }
            }
            
            checkCollision();
        }
        
        repaint();
    }


    private void checkCollision() {
        Rectangle car = new Rectangle(KittyX, KittyY, 50, 30);

        for (int i = 0; i < Clouds.size(); i++) {
            Rectangle Cloud = Clouds.get(i);
            if (car.intersects(Cloud)) {
                // 장애물과 충돌할 때 점수 감소
                if (remain > 0) {
                    remain--;
                }
                // 충돌한 장애물 제거
                Clouds.remove(i);
                i--;
            }
        }
        
        for (int i = 0; i < anotherClouds.size(); i++) {
            Rectangle anotherCloud = anotherClouds.get(i);
            if (car.intersects(anotherCloud)) {
                // 장애물과 충돌할 때 점수 감소
                if (remain > 0) {
                    remain--;
                }
                // 충돌한 장애물 제거
                anotherClouds.remove(i);
                i--;
            }
        }
        

        if (remain <= 0) {
        	remain=0;
            isGameOver = true;
            timer.stop();
            //showGameOverDialog();
            
        }
    }




    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            if (KittyX > 0) { // 왼쪽으로 이동할 때 패널 왼쪽 경계를 벗어나지 않도록 확인
                KittyX -= KittySpeed;
            }
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            if (KittyX + 70 < CloudWidth) { // 오른쪽으로 이동할 때 패널 오른쪽 경계를 벗어나지 않도록 확인
                KittyX += KittySpeed;
            }
        } else if (keyCode == KeyEvent.VK_UP) {
            if (KittyY > 0) { // 위로 이동할 때 패널 위쪽 경계를 벗어나지 않도록 확인
                KittyY -= KittySpeed;
            }
        } else if (keyCode == KeyEvent.VK_DOWN) {
            if (KittyY + 70 < CloudHeight) { // 아래로 이동할 때 패널 아래쪽 경계를 벗어나지 않도록 확인
                KittyY += KittySpeed;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    	
    }
}
