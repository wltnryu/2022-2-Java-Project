package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class homeUI extends JFrame implements ActionListener {
	
	private Container c = getContentPane();
	
	ImageIcon main = new ImageIcon("image/main.png");
	Image img = main.getImage();  //ImageIcon을 Image로 변환.
	Image mainIcon = img.getScaledInstance(60, 45, java.awt.Image.SCALE_SMOOTH);
	ImageIcon icon = new ImageIcon(mainIcon); //Image로 ImageIcon 생성
	
	//전체 화면 구성 요소 ******************
	private JPanel panel = new JPanel(); //전체 고정 패널
	private JPanel fixedTitle = new JPanel(); //맨 위 고정 타이틀 패널
	private JButton appTitle = new JButton(icon); //고정 타이틀 - 추후 버튼으로 수정 예정***
	
	ImageIcon log = new ImageIcon("image/로그아웃.png");
	Image logimg = log.getImage();  //ImageIcon을 Image로 변환.
	Image logIcon = logimg.getScaledInstance(60, 45, java.awt.Image.SCALE_SMOOTH);
	ImageIcon licon = new ImageIcon(logIcon); //Image로 ImageIcon 생성
	private JButton logout = new JButton(licon);
	
	//메인 화면 구성 요소 ******************
	//실행했을 때 가장 먼저 뜨는 화면의 구성 요소
	private JPanel homePanel = new JPanel(); //메인 화면 패널
	private JPanel buttonPanel = new JPanel(); //메인 화면 세 가지 버튼 부착 패널
	
	ImageIcon library = new ImageIcon("image/library.png"); //메인 화면 이미지 아이콘
	
	ImageIcon book = new ImageIcon("image/도서 대출.png");
	Image bookimg = book.getImage();  //ImageIcon을 Image로 변환.
	Image bookIcon = bookimg.getScaledInstance(400, 100, java.awt.Image.SCALE_SMOOTH);
	ImageIcon bicon = new ImageIcon(bookIcon); //Image로 ImageIcon 생성
	
	ImageIcon drink = new ImageIcon("image/음료 주문.png");
	Image drinkimg = drink.getImage();  //ImageIcon을 Image로 변환.
	Image drinkIcon = drinkimg.getScaledInstance(400, 100, java.awt.Image.SCALE_SMOOTH);
	ImageIcon dicon = new ImageIcon(drinkIcon); //Image로 ImageIcon 생성
	
	ImageIcon seat = new ImageIcon("image/열람실 예약.png");
	Image seatimg = seat.getImage();  //ImageIcon을 Image로 변환.
	Image seatIcon = seatimg.getScaledInstance(400, 100, java.awt.Image.SCALE_SMOOTH);
	ImageIcon sicon = new ImageIcon(seatIcon); //Image로 ImageIcon 생성
	
	private JButton bookBtn = new JButton(bicon);
	private JButton drinkBtn = new JButton(dicon);
	private JButton seatBtn = new JButton(sicon); //메인 화면 세 가지 버튼
	
	Operator o = null;	
	
	public homeUI(Operator _o) {
		o = _o;
		
		setTitle("[새싹 도서관] 메인 화면");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		c.setLayout(new BorderLayout());
		panel.setLayout(new BorderLayout());
		fixedTitle.setLayout(new BorderLayout());
		
		//고정 요소 추가 *****************************
		fixedTitle.setBackground(new Color(197, 225, 165));
		fixedTitle.setOpaque(true);
		appTitle.setBackground(new Color(197, 225, 165));
		appTitle.setBorderPainted(false);
		appTitle.setOpaque(true);
		logout.setBackground(new Color(197, 225, 165));
		logout.setBorderPainted(false);
		logout.setOpaque(true);
		fixedTitle.add(appTitle, BorderLayout.WEST);
		fixedTitle.add(logout, BorderLayout.EAST);
		panel.add(fixedTitle, BorderLayout.NORTH);
		
		//메인 화면 구성 *****************************
		//실행했을 때 가장 먼저 뜨는 화면
		homePanel.setLayout(new BorderLayout()); //타이틀 제외한 밑부분 Panel은 BorderLayout으로 구성
		buttonPanel.setLayout(new GridLayout(0,1)); //button을 모아둔 buttonPanel은 동일한 버튼 크기를 위해 GridLayout으로 구성
		
		Image img = library.getImage();
		Image libImg = img.getScaledInstance(400, 300, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(libImg);
		JLabel lib = new JLabel(changeIcon); //이미지 크기 조절
		homePanel.add(lib, BorderLayout.NORTH); //homePanel 레이아웃 상단에 이미지 추가
		
		buttonPanel.add(bookBtn);
		buttonPanel.add(drinkBtn);
		buttonPanel.add(seatBtn); //세 개의 버튼을 buttonPanel에 추가
		homePanel.add(buttonPanel, BorderLayout.CENTER); //이미지 아래-레이아웃 중앙-에 buttonPanel 추가

		panel.add(homePanel, BorderLayout.CENTER); //컨테이너에 홈 화면 homePanel 추가 "패널 변동 포인트"
		c.add(panel);
		
		bookBtn.addActionListener(this);
        drinkBtn.addActionListener(this);
        seatBtn.addActionListener(this);
        logout.addActionListener(this);
		
        
		//필수 요소
		setSize(400, 700);
		setLocationRelativeTo(null);
		//setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
        if(e.getSource() == bookBtn) {
            o.BU.setVisible(true);
            o.hU.setVisible(false);
            
        } else if(e.getSource() == drinkBtn) {
        	o.dU.setVisible(true);
        	o.hU.setVisible(false);
        	
        } else if(e.getSource() == seatBtn) {
        	o.sU.setVisible(true);
        	o.hU.setVisible(false);
        } else if(e.getSource() == logout) {
        	o.mf.setVisible(true);
        	o.hU.setVisible(false);
        }
    }
}
