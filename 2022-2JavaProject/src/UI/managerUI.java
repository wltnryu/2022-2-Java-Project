package UI;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class managerUI extends JFrame implements ActionListener {
	
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
	
	ImageIcon log = new ImageIcon("image/로그아웃.png");
	Image logimg = log.getImage();  //ImageIcon을 Image로 변환.
	Image logIcon = logimg.getScaledInstance(60, 45, java.awt.Image.SCALE_SMOOTH);
	ImageIcon licon = new ImageIcon(logIcon); //Image로 ImageIcon 생성
	private JButton logout = new JButton(licon);
	
	private JPanel managePanel = new JPanel();
	private JPanel manageBtn = new JPanel(); // 관리 버튼 세 개 부착
	private JPanel titlePanel = new JPanel(); //고정 타이틀 바로 아래 버튼 클릭 후 나오는 타이틀
	private JPanel plusPanel = new JPanel(); //empty 공간 확보 위해 붙이는 패널

	ImageIcon manager = new ImageIcon("image/관리자 타이틀.png");
	Image manageimg = manager.getImage();  //ImageIcon을 Image로 변환.
	Image manageIcon = manageimg.getScaledInstance(400, 22, java.awt.Image.SCALE_SMOOTH);
	ImageIcon micon = new ImageIcon(manageIcon); //Image로 ImageIcon 생성
	private JLabel managerLabel = new JLabel(micon);
	
	//관리 버튼 세 개
	ImageIcon reset = new ImageIcon("image/좌석 리셋 버튼.png");
	Image resetimg = reset.getImage();  //ImageIcon을 Image로 변환.
	Image resetIcon = resetimg.getScaledInstance(390, 100, java.awt.Image.SCALE_SMOOTH);
	ImageIcon rIcon = new ImageIcon(resetIcon); //Image로 ImageIcon 생성
	private JButton resetBtn = new JButton(rIcon);
	
	ImageIcon remain = new ImageIcon("image/잔여 좌석 확인 버튼.png");
	Image remainimg = remain.getImage();  //ImageIcon을 Image로 변환.
	Image remainIcon = remainimg.getScaledInstance(390, 100, java.awt.Image.SCALE_SMOOTH);
	ImageIcon ricon = new ImageIcon(remainIcon); //Image로 ImageIcon 생성
	private JButton remainBtn = new JButton(ricon);
	
	ImageIcon total = new ImageIcon("image/매출 확인 버튼.png");
	Image totalimg = total.getImage();  //ImageIcon을 Image로 변환.
	Image totalIcon = totalimg.getScaledInstance(390, 100, java.awt.Image.SCALE_SMOOTH);
	ImageIcon ticon = new ImageIcon(totalIcon); //Image로 ImageIcon 생성
	private JButton totalBtn = new JButton(ticon);
	
	ImageIcon notice = new ImageIcon("image/매니저 공지사항.png");
	Image noticeimg = notice.getImage();  //ImageIcon을 Image로 변환.
	Image noticeIcon = noticeimg.getScaledInstance(400, 300, java.awt.Image.SCALE_SMOOTH);
	ImageIcon nicon = new ImageIcon(noticeIcon); //Image로 ImageIcon 생성
	
	Operator o = null;
	
	public managerUI(Operator _o) {
		o = _o;
		
		setTitle("[새싹 도서관] 관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		c.setLayout(new BorderLayout());
		panel.setLayout(new BorderLayout());
		fixedTitle.setLayout(new BorderLayout());
		managePanel.setLayout(new BorderLayout());
		manageBtn.setLayout(new GridLayout(0,1));
		plusPanel.setLayout(new GridLayout(2,1));
		
		//고정 요소 추가 *****************************
		fixedTitle.setBackground(new Color(197, 225, 165));
		fixedTitle.setOpaque(true);
		appTitle.setBackground(new Color(197, 225, 165));
		appTitle.setOpaque(true);
		appTitle.setBorderPainted(false);
		logout.setBackground(new Color(197, 225, 165));
		logout.setOpaque(true);
		logout.setBorderPainted(false);
		fixedTitle.add(appTitle, BorderLayout.WEST);
		fixedTitle.add(logout, BorderLayout.EAST);
		panel.add(fixedTitle, BorderLayout.NORTH);
		
		//좌석 배정 화면 구성 *************************
		titlePanel.setLayout(new BorderLayout());
		titlePanel.setBackground(Color.YELLOW);
		titlePanel.setOpaque(true);
		titlePanel.add(managerLabel, BorderLayout.NORTH);
		
		manageBtn.add(remainBtn);
		manageBtn.add(totalBtn);
		manageBtn.add(resetBtn);
		
		JLabel label = new JLabel(nicon);
		plusPanel.add(label);
		plusPanel.add(manageBtn);
		
		managePanel.add(titlePanel, BorderLayout.NORTH);
		managePanel.add(plusPanel, BorderLayout.CENTER);
		
		panel.add(managePanel);
		c.add(panel);
		
		remainBtn.addActionListener(this);
        totalBtn.addActionListener(this);
        
        totalBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int money = db.getTotal_income();
				String moneySt = String.valueOf(money);
				String dialog = "현재 매출액은 " + moneySt + "원입니다.";
				
				JOptionPane.showMessageDialog(null, dialog);
			}
		});
        
        JTextField tf = new JTextField(10);
        resetBtn.addActionListener(new ActionListener() { //음료 버튼을 클릭했을 때 주문 안내 이미지 출력
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource();
				
				int result = JOptionPane.showConfirmDialog(null, "좌석을 리셋하시겠습니까?", "좌석 리셋", JOptionPane.YES_NO_OPTION);
				
				if(result == JOptionPane.CLOSED_OPTION)
					tf.setText("취소되었습니다.");
				else if(result == JOptionPane.YES_OPTION) {
					tf.setText("리셋");
					db.resetSeat();
					
				} else	
					tf.setText("취소");
			}
		});
        
        
        appTitle.addActionListener(this);
        logout.addActionListener(this);
        
        //필수 요소
      	setSize(400, 700);
      	setLocationRelativeTo(null);
      	//setVisible(true);
        
	}
	
	public void actionPerformed(ActionEvent e) {
        if(e.getSource() == logout) {
        	o.mf.setVisible(true);
        	o.mU.setVisible(false);
        } else if(e.getSource() == remainBtn) {
        	o.cS.setVisible(true);
        	o.mU.setVisible(false);
        }
    }

}
