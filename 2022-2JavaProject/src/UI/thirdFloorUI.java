package UI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class thirdFloorUI extends JFrame implements ActionListener {
	
	private Container c = getContentPane();
	String seatNumber;
	
	SeatManage seatMan = new SeatManage();
	Database db = new Database();
	
	ImageIcon main = new ImageIcon("image/main.png");
	Image img = main.getImage();  //ImageIcon을 Image로 변환.
	Image mainIcon = img.getScaledInstance(60, 45, java.awt.Image.SCALE_SMOOTH);

	ImageIcon icon = new ImageIcon(mainIcon); //Image로 ImageIcon 생성
	
	ImageIcon third = new ImageIcon("image/열람실 예약 3층.png");
	Image thirdimg = third.getImage();  //ImageIcon을 Image로 변환.
	Image thirdicon = thirdimg.getScaledInstance(400, 22, java.awt.Image.SCALE_SMOOTH);
	ImageIcon ticon = new ImageIcon(thirdicon); //Image로 ImageIcon 생성
	
	//전체 화면 구성 요소 ******************
	private JPanel panel = new JPanel(); //전체 고정 패널
	private JPanel fixedTitle = new JPanel(); //맨 위 고정 타이틀 패널
	private JButton appTitle = new JButton(icon); //고정 타이틀
		
	private JPanel titlePanel = new JPanel(); //고정 타이틀 바로 아래 버튼 클릭 후 나오는 타이틀

	//좌석 화면 구성 요소 ******************
	private JPanel thirdPanel = new JPanel(); //전체 패널
	private JLabel floorName = new JLabel(); //3층 텍스트
	private JPanel seatPanel1 = new JPanel(); //1~10번 자리
	private JPanel seatPanel2 = new JPanel(); //11~20번 자리
	private JPanel totalSeat = new JPanel(); //seatPanel1, 2 부착
	private JPanel totalPanel = new JPanel(); //잔여 좌석 패널
	private JPanel realTotal = new JPanel(); //텍스트 + 버튼
	JPanel empty = new JPanel(); //비어있는 칸

	private JLabel Info = new JLabel("  잔여 좌석: ");
	
	int s = 20;
	
	private JLabel total = new JLabel("20"); //확인 위해 임의 설정 ****************
	private JButton back = new JButton("뒤로가기");
	
	private JLabel seatLabel = new JLabel(ticon);
	
	boolean register = false;
	
	//좌석 버튼 설정
	JButton[] seat = new JButton[20];
	String[] seatNum = {"1", "2", "3", "4", "5",
						  "6", "7", "8", "9", "10",
						  "11", "12", "13", "14", "15",
						  "16", "17", "18", "19", "20"}; //좌석 번호
	
	int count = 0;
	Operator o = null;
	
	public thirdFloorUI(Operator _o) {
		o = _o;

		setTitle("[새싹 도서관] 3층 열람실");
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
		
		//좌석 배정 화면 구성 *************************
		thirdPanel.setLayout(new BorderLayout());
		titlePanel.setLayout(new BorderLayout());
		seatPanel1.setLayout(new GridLayout(2,5));
		seatPanel2.setLayout(new GridLayout(2,5));
		totalSeat.setLayout(new GridLayout(3,1));
		realTotal.setLayout(new BorderLayout());
						
		titlePanel.setBackground(Color.YELLOW);
		titlePanel.setOpaque(true);
		titlePanel.add(seatLabel, BorderLayout.NORTH);
						
		total.setFont(new Font("Arial", 3, 20)); //색깔 조정
		totalPanel.add(Info, BorderLayout.WEST);
		totalPanel.add(total, BorderLayout.CENTER);
		totalPanel.add(back, BorderLayout.EAST);
						
		thirdPanel.add(titlePanel, BorderLayout.NORTH); //titlePanel을 음료 주문 상단에 부착
		thirdPanel.add(totalPanel, BorderLayout.SOUTH); //totalPanel을 음료 주문 하단에 부착
		
		JTextField tf = new JTextField(10);
		for(int i=0; i<10; i++) {
			// 총 10 개의 버튼을 등록 (1~10)
			seat[i] = new JButton(seatNum[i]);
			seat[i].setFont(new Font("Arial", 3, 20));
			
			if(db.checkSeat(i) == true) {
				seat[i].setBackground(Color.green);
				seat[i].setOpaque(true);
				seat[i].setBorderPainted(false);
				count++;
			} else {
				seat[i].setBackground(Color.red);
				seat[i].setOpaque(true);
				seat[i].setBorderPainted(false);
			}
			
			seatPanel1.add(seat[i]);
			
			seat[i].addActionListener(new ActionListener() { //음료 버튼을 클릭했을 때 주문 안내 이미지 출력
				public void actionPerformed(ActionEvent e) {
					JButton b = (JButton)e.getSource();
					
					int result = JOptionPane.showConfirmDialog(null, "좌석을 예약하시겠습니까?", "좌석 배정", JOptionPane.YES_NO_OPTION);
					
					if(result == JOptionPane.CLOSED_OPTION)
						tf.setText("예약을 취소하였습니다.");
					else if(result == JOptionPane.YES_OPTION) {
						tf.setText("예약");
						
						for(int i = 1; i<=10; i++) {
							String number = String.valueOf(i);
							
							if(db.howManySeat() == false) {
								if(b.getText().equals(number)) {
									if(register == false) {
										b.setBackground(Color.red);
										b.setOpaque(true);
										b.setBorderPainted(false);
										register = true;
										
										db.changeSeat(b.getText());
										
										s = s - 1;
										String st = String.valueOf(s);
										total.setText(st);

										break;
									}
								}
							} else {
								JOptionPane.showMessageDialog(null, "더이상 예약할 수 없습니다.");
								break;
							}
						}
						
					} else	
						tf.setText("취소");
				}
			});
		}
		
		for(int i=10; i<20; i++) {
			// 총 10 개의 버튼을 등록 (11~20)
			seat[i] = new JButton(seatNum[i]);
			seat[i].setFont(new Font("Arial", 3, 20));
			
			if(db.checkSeat(i) == true) {
				seat[i].setBackground(Color.green);
				seat[i].setOpaque(true);
				seat[i].setBorderPainted(false);
				count++;
			} else {
				seat[i].setBackground(Color.red);
				seat[i].setOpaque(true);
				seat[i].setBorderPainted(false);
			}
			
			seatPanel2.add(seat[i]);
			
			seat[i].addActionListener(new ActionListener() { //음료 버튼을 클릭했을 때 주문 안내 이미지 출력
				public void actionPerformed(ActionEvent e) {
					JButton b = (JButton)e.getSource();
					
					int result = JOptionPane.showConfirmDialog(null, "좌석을 예약하시겠습니까?", "좌석 배정", JOptionPane.YES_NO_OPTION);
					
					if(result == JOptionPane.CLOSED_OPTION)
						tf.setText("예약을 취소하였습니다.");
					else if(result == JOptionPane.YES_OPTION) {
						tf.setText("예약");
						
						for(int i = 10; i<=20; i++) {
							String number = String.valueOf(i);
							
							if(db.howManySeat() == false) {
								if(b.getText().equals(number)) {
									if(register == false) {
										b.setBackground(Color.red);
										b.setOpaque(true);
										b.setBorderPainted(false);
										register = true;
										
										db.changeSeat(b.getText());
										
										s = s - 1;
										String st = String.valueOf(s);
										total.setText(st);

										break;
									}
								}
							} else {
								JOptionPane.showMessageDialog(null, "더이상 예약할 수 없습니다.");
								break;
							}
						}
						
					}
					else	
						tf.setText("취소");
				
				}
			});
		}
		
		String countSt = String.valueOf(count);
		total.setText(countSt);
		
		totalSeat.add(seatPanel1);
		totalSeat.add(empty);
		totalSeat.add(seatPanel2);
		realTotal.add(floorName, BorderLayout.NORTH);
		realTotal.add(totalSeat, BorderLayout.CENTER);
		
		thirdPanel.add(realTotal, BorderLayout.CENTER);
		
		panel.add(thirdPanel);
		c.add(panel);
		
		appTitle.addActionListener(this);
		back.addActionListener(this);
		
		//필수 요소
		setSize(400, 700);
		setLocationRelativeTo(null);
		//setVisible(true);

	}
	
	public void actionPerformed(ActionEvent e) {
        if(e.getSource() == appTitle) {
        	o.hU.setVisible(true);
        	o.tF.setVisible(false);
    		panel.revalidate();
    		panel.repaint();
        	
        } else if(e.getSource() == back) {
        	o.sU.setVisible(true);
        	o.tF.setVisible(false);
    		panel.revalidate();
    		panel.repaint();

        }
    }
}
