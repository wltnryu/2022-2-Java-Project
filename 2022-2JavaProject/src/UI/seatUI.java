package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class seatUI extends JFrame implements ActionListener {
	
	private Container c = getContentPane();
	
	ImageIcon main = new ImageIcon("image/main.png");
	Image img = main.getImage();  //ImageIcon을 Image로 변환.
	Image mainIcon = img.getScaledInstance(60, 45, java.awt.Image.SCALE_SMOOTH);

	ImageIcon icon = new ImageIcon(mainIcon); //Image로 ImageIcon 생성
	
	//전체 화면 구성 요소 ******************
	private JPanel panel = new JPanel(); //전체 고정 패널
	private JPanel fixedTitle = new JPanel(); //맨 위 고정 타이틀 패널
	private JButton appTitle = new JButton(icon); //고정 타이틀 - 추후 버튼으로 수정 예정***
		
	private JPanel titlePanel = new JPanel(); //고정 타이틀 바로 아래 버튼 클릭 후 나오는 타이틀

	//좌석 배치 화면 구성 요소 ****************
	private JPanel seatPanel = new JPanel(); //좌석 배치 메인 화면 패널 > 클릭 후 반응
	private JPanel floorPanel = new JPanel(); //3, 4층 버튼 배치 패널 (GridLayout 예정)
	private JPanel noticePanel = new JPanel(); //공지사항 사진 배치할 패널 (아무 사진이나 대체)
	private JPanel floorNot = new JPanel();
		
	ImageIcon fourth = new ImageIcon("image/4층.png");
	Image fourthimg = fourth.getImage();  //ImageIcon을 Image로 변환.
	Image fourthicon = fourthimg.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
	ImageIcon ficon = new ImageIcon(fourthicon); //Image로 ImageIcon 생성
	
	ImageIcon third = new ImageIcon("image/3층.png");
	Image thirdimg = third.getImage();  //ImageIcon을 Image로 변환.
	Image thirdicon = thirdimg.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
	ImageIcon ticon = new ImageIcon(thirdicon); //Image로 ImageIcon 생성
	
	private JButton thirdFBtn = new JButton(ticon); //3층 연결 버튼 > 버튼 클릭 후 thirdFloor Panel과 연결
	private JButton fourthFBtn = new JButton(ficon); //4층 연결 버튼 > 버튼 클릭 후 fourthFloor Panel과 연결
	
	ImageIcon seatIcon = new ImageIcon("image/열람실 예약 타이틀.png");
	Image seatimg = seatIcon.getImage();  //ImageIcon을 Image로 변환.
	Image seaticon = seatimg.getScaledInstance(400, 22, java.awt.Image.SCALE_SMOOTH);
	ImageIcon sicon = new ImageIcon(seaticon); //Image로 ImageIcon 생성
	
	ImageIcon notice = new ImageIcon("image/공지사항.png");
	private JLabel seatLabel = new JLabel(sicon);
	
	Operator o = null;
	
	public seatUI(Operator _o) {
		o = _o;
		
		setTitle("2019110883");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		c.setLayout(new BorderLayout());
		panel.setLayout(new BorderLayout());
		
		//고정 요소 추가 *****************************
		fixedTitle.setLayout(new BorderLayout());
		fixedTitle.setBackground(new Color(197, 225, 165));
		fixedTitle.setOpaque(true);
		appTitle.setBackground(new Color(197, 225, 165));
		appTitle.setOpaque(true);
		appTitle.setBorderPainted(false);
		fixedTitle.add(appTitle, BorderLayout.WEST);
		panel.add(fixedTitle, BorderLayout.NORTH);
		
		//좌석 배치 화면 구성 ************************
		seatPanel.setLayout(new BorderLayout());
		titlePanel.setLayout(new BorderLayout());
		floorPanel.setLayout(new GridLayout(0,2));
		floorNot.setLayout(new BorderLayout());
		noticePanel.setLayout(new GridLayout(0,1));
				
		titlePanel.setBackground(Color.YELLOW);
		titlePanel.setOpaque(true);
		titlePanel.add(seatLabel, BorderLayout.NORTH);
				
		seatPanel.add(titlePanel, BorderLayout.NORTH);
		floorPanel.add(thirdFBtn);
		floorPanel.add(fourthFBtn);
				
		floorNot.add(floorPanel, BorderLayout.NORTH);
		floorNot.add(noticePanel, BorderLayout.CENTER);
				
		Image img2 = notice.getImage();
		Image noticeImg = img2.getScaledInstance(380, 480, Image.SCALE_SMOOTH);
		ImageIcon changeIcon2 = new ImageIcon(noticeImg);
		JLabel not = new JLabel(changeIcon2); //이미지 크기 조절
		noticePanel.add(not, BorderLayout.NORTH); //homePanel 레이아웃 상단에 이미지 추가
				
		seatPanel.add(floorNot, BorderLayout.CENTER);
		panel.add(seatPanel, BorderLayout.CENTER);
		c.add(panel);
		
		appTitle.addActionListener(this);
		thirdFBtn.addActionListener(this);
		fourthFBtn.addActionListener(this);
		
		//필수 요소
		setSize(400, 700);
		setLocationRelativeTo(null);
		//setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
        if(e.getSource() == appTitle) {
        	o.hU.setVisible(true);
        	o.sU.setVisible(false);
        	
        } else if(e.getSource() == thirdFBtn) {
        	o.tF.setVisible(true);
        	o.sU.setVisible(false);
        	
        } else if(e.getSource() == fourthFBtn) {
        	o.fF.setVisible(true);
        	o.sU.setVisible(false);
        }
    }

}
