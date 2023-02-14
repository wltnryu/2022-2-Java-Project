package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class checkSeat extends JFrame implements ActionListener {
	
	private Container c = getContentPane();
	Database db = new Database();
	
	ImageIcon main = new ImageIcon("image/main.png");
	Image img = main.getImage();  //ImageIcon을 Image로 변환.
	Image mainIcon = img.getScaledInstance(60, 45, java.awt.Image.SCALE_SMOOTH);

	ImageIcon icon = new ImageIcon(mainIcon); //Image로 ImageIcon 생성
	
	//전체 화면 구성 요소 ******************
	private JPanel panel = new JPanel(); //전체 고정 패널
	private JPanel fixedTitle = new JPanel(); //맨 위 고정 타이틀 패널
	private JButton appTitle = new JButton(icon); //고정 타이틀 - 추후 버튼으로 수정 예정***
	
	ImageIcon log = new ImageIcon("image/새로고침.png");
	Image logimg = log.getImage();  //ImageIcon을 Image로 변환.
	Image logIcon = logimg.getScaledInstance(60, 45, java.awt.Image.SCALE_SMOOTH);
	ImageIcon licon = new ImageIcon(logIcon); //Image로 ImageIcon 생성
	private JButton repaint = new JButton(licon);
	
	private JPanel managePanel = new JPanel();
	private JPanel titlePanel = new JPanel(); //고정 타이틀 바로 아래 버튼 클릭 후 나오는 타이틀
	
	ImageIcon manager = new ImageIcon("image/관리자 타이틀.png");
	Image manageimg = manager.getImage();  //ImageIcon을 Image로 변환.
	Image manageIcon = manageimg.getScaledInstance(400, 22, java.awt.Image.SCALE_SMOOTH);
	ImageIcon micon = new ImageIcon(manageIcon); //Image로 ImageIcon 생성
	private JLabel managerLabel = new JLabel(micon);
	
	private JPanel seatPanel = new JPanel(); //자리

	//좌석 버튼 설정
		JButton[] seat = new JButton[40];
		String[] seatNum = {"1", "2", "3", "4", "5",
							"6", "7", "8", "9", "10",
							"11", "12", "13", "14", "15",
							"16", "17", "18", "19", "20",
							"21", "22", "23", "24", "25",
							"26", "27", "28", "29", "30",
							"31", "32", "33", "34", "35",
							"36", "37", "38", "39", "40"}; //좌석 번호
	
	Operator o = null;
	
	checkSeat(Operator _o){
		o = _o;
		
		setTitle("[새싹 도서관] 좌석 확인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		c.setLayout(new BorderLayout());
		panel.setLayout(new BorderLayout());
		fixedTitle.setLayout(new BorderLayout());
		managePanel.setLayout(new BorderLayout());
		seatPanel.setLayout(new GridLayout(0,5));
		
		//고정 요소 추가 *****************************
		fixedTitle.setBackground(new Color(197, 225, 165));
		fixedTitle.setOpaque(true);
		appTitle.setBackground(new Color(197, 225, 165));
		appTitle.setOpaque(true);
		appTitle.setBorderPainted(false);
		repaint.setBackground(new Color(197, 225, 165));
		repaint.setOpaque(true);
		repaint.setBorderPainted(false);
		fixedTitle.add(appTitle, BorderLayout.WEST);
		fixedTitle.add(repaint, BorderLayout.EAST);
		panel.add(fixedTitle, BorderLayout.NORTH);
		
		//좌석 배정 화면 구성 *************************
		titlePanel.setLayout(new BorderLayout());
		titlePanel.setBackground(Color.YELLOW);
		titlePanel.setOpaque(true);
		titlePanel.add(managerLabel, BorderLayout.NORTH);
		
		managePanel.add(titlePanel, BorderLayout.NORTH);
		
		for(int i=0; i<40; i++) {
			// 총 10 개의 버튼을 등록 (1~10)
			seat[i] = new JButton(seatNum[i]);
			seat[i].setFont(new Font("Arial", 3, 20));
			
			if(db.checkSeat(i) == true) {
				seat[i].setBackground(Color.white);
				seat[i].setOpaque(true);
				seat[i].setBorderPainted(false);

			} else {
				seat[i].setBackground(Color.gray);
				seat[i].setOpaque(true);
				seat[i].setBorderPainted(false);
			}
			
			seatPanel.add(seat[i]);
		}
		
		managePanel.add(seatPanel, BorderLayout.CENTER);
		
		appTitle.addActionListener(this);
		repaint.addActionListener(this);
		
		panel.add(managePanel);
		c.add(panel);
        
        //필수 요소
      	setSize(400, 700);
      	setLocationRelativeTo(null);
	}
	
	public void actionPerformed(ActionEvent e) {
        if(e.getSource() == repaint) {
        	for(int i=0; i<40; i++) {
        		if(db.checkSeat(i) == true) {
    				seat[i].setBackground(Color.white);
    				seat[i].setOpaque(true);
    				seat[i].setBorderPainted(false);

    			} else {
    				seat[i].setBackground(Color.gray);
    				seat[i].setOpaque(true);
    				seat[i].setBorderPainted(false);
    			}
        	}
        	
        } else if (e.getSource() == appTitle) {
        	o.mU.setVisible(true);
        	o.cS.setVisible(false);
        }
    }
}
