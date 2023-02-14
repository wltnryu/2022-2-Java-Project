package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class drinkUI extends JFrame implements ActionListener {
	
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
		
	private JPanel titlePanel = new JPanel(); //고정 타이틀 바로 아래 버튼 클릭 후 나오는 타이틀
	
	//음료 주문 화면 구성 요소 ****************
	private JPanel drinkPanel = new JPanel();//음료 주문 메인 화면 패널 > 클릭 후 반응
	private JPanel drinkBtnPanel = new JPanel(); //음료 버튼 부착 패널 - 이 패널을 스크롤 패널에 부착
	private JPanel totalPanel = new JPanel(); //화면 하단 금액 텍스트
		
	private JLabel moneyGuide = new JLabel("  금액: ");
	
	int money = 0;
	
	private JLabel total = new JLabel("0"); //확인 위해 금액 설정 ****************
		
	JButton pay = new JButton("결제");
	JButton[] drink = new JButton[7];
	String[] drinkText = {"아메리카노","카페라떼","카페모카", "아이스티", "카라멜마끼아또", "바닐라라떼", "우유"}; //음료 예비 목록
	int[] drinkPrice = {4000, 4500, 5000, 3500, 5000, 5000, 3000};
		
	ImageIcon drinkIcon = new ImageIcon("image/음료 주문 타이틀.png");
	Image drinkimg = drinkIcon.getImage();  //ImageIcon을 Image로 변환.
	Image drinkicon = drinkimg.getScaledInstance(400, 22, java.awt.Image.SCALE_SMOOTH);
	ImageIcon dicon = new ImageIcon(drinkicon); //Image로 ImageIcon 생성
	
	private JLabel drinkLabel = new JLabel(dicon);
	
	Operator o = null;
	
	public drinkUI(Operator _o) {
		o = _o;
		
		setTitle("[새싹 도서관] 음료 주문");
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
		
		//음료 주문 화면 구성 *************************
		//선택 취소를 구현할 수 있을까?
		drinkPanel.setLayout(new BorderLayout());
		titlePanel.setLayout(new BorderLayout());
		drinkBtnPanel.setLayout(new GridLayout(0,1)); //버튼 부착은 한 개의 열로
		totalPanel.setLayout(new BorderLayout());
				
		titlePanel.setBackground(Color.YELLOW);
		titlePanel.setOpaque(true);
		titlePanel.add(drinkLabel, BorderLayout.NORTH);
				
		total.setFont(new Font("Arial", 3, 20)); //색깔 조정
		totalPanel.add(moneyGuide, BorderLayout.WEST);
		totalPanel.add(total, BorderLayout.CENTER);
		totalPanel.add(pay, BorderLayout.EAST);
				
		drinkPanel.add(titlePanel, BorderLayout.NORTH); //titlePanel을 음료 주문 상단에 부착
		drinkPanel.add(totalPanel, BorderLayout.SOUTH); //totalPanel을 음료 주문 하단에 부착
		
		
		JTextField tf = new JTextField(10);
		for(int i=0; i<7; i++) {
			// 총 7 개의 버튼을 등록
			int price = drinkPrice[i];
			String menu = drinkText[i] + " " + price;
			drink[i] = new JButton(menu);
			drink[i].setFont(new Font("Arial", 3, 20));
			drinkBtnPanel.add(drink[i]);
			drinkPanel.add(drinkBtnPanel);
					
			drink[i].addActionListener(new ActionListener() { //음료 버튼을 클릭했을 때 주문 안내 이미지 출력
				public void actionPerformed(ActionEvent e) {
					JButton b = (JButton)e.getSource();
					
					int result = JOptionPane.showConfirmDialog(null, "주문하시겠습니까?", "주문 확인", JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.CLOSED_OPTION)
						tf.setText("주문이 취소되었습니다.");
					else if(result == JOptionPane.YES_OPTION) {
						tf.setText("주문");
						
						if(b.getText().equals("아메리카노 4000")) {
							money = money + 4000;
							String m = String.valueOf(money);
							total.setText(m);
						}
						else if(b.getText().equals("카페라떼 4500")) {
							money = money + 4500;
							String m = String.valueOf(money);
							total.setText(m);
						}
						else if(b.getText().equals("카페모카 5000")) {
							money = money + 5000;
							String m = String.valueOf(money);
							total.setText(m);
						}
						else if(b.getText().equals("아이스티 3500")) {
							money = money + 3500;
							String m = String.valueOf(money);
							total.setText(m);
						}
						else if(b.getText().equals("카라멜마끼아또 5000")) {
							money = money + 5000;
							String m = String.valueOf(money);
							total.setText(m);
						}
						else if(b.getText().equals("바닐라라떼 5000")) {
							money = money + 5000;
							String m = String.valueOf(money);
							total.setText(m);
						}
						else if(b.getText().equals("우유 3000")) {
							money = money + 3000;
							String m = String.valueOf(money);
							total.setText(m);
						}
					}
					else	
						tf.setText("취소");
				}
			});
		}
			
		pay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "결제하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.CLOSED_OPTION)
					tf.setText("결제가 취소되었습니다.");
				else if(result == JOptionPane.YES_OPTION) {
					tf.setText("결제");
					JOptionPane.showMessageDialog(null, "결제가 완료되었습니다.");
					total.setText("0");
					db.totalIncome(money);
				}
				else {
					tf.setText("취소");
					JOptionPane.showMessageDialog(null, "결제가 취소되었습니다.");
				}
			}
		});
		
		panel.add(drinkPanel);
		c.add(panel);
		
		appTitle.addActionListener(this);
		
		//필수 요소
		setSize(400, 700);
		setLocationRelativeTo(null);
		//setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
        if(e.getSource() == appTitle) {
        	o.hU.setVisible(true);
        	o.dU.setVisible(false);
        }
    }

}
