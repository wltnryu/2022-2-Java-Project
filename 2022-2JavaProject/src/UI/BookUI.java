package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.sql.*;
import java.util.Date;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.FileReader;
import java.io.BufferedReader;

import java.io.FileWriter;
import java.io.BufferedWriter;

import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class BookUI extends JFrame implements ActionListener {
	
	private Container c = getContentPane();
	private JScrollPane scrollPane; //스크롤 팬
	Database db = new Database();
	
	ImageIcon main = new ImageIcon("image/main.png");
	Image img = main.getImage();  //ImageIcon을 Image로 변환.
	Image mainIcon = img.getScaledInstance(60, 45, java.awt.Image.SCALE_SMOOTH);

	ImageIcon icon = new ImageIcon(mainIcon); //Image로 ImageIcon 생성
	
	ImageIcon bookIcon = new ImageIcon("image/도서 대출 타이틀.png");
	Image bookimg = bookIcon.getImage();  //ImageIcon을 Image로 변환.
	Image bookicon = bookimg.getScaledInstance(400, 22, java.awt.Image.SCALE_SMOOTH);
	ImageIcon bicon = new ImageIcon(bookicon); //Image로 ImageIcon 생성
	
	//전체 화면 구성 요소 ******************
	private JPanel panel = new JPanel(); //전체 고정 패널
	private JPanel fixedTitle = new JPanel(); //맨 위 고정 타이틀 패널
	private JButton appTitle = new JButton(icon); //고정 타이틀 - 추후 버튼으로 수정 예정***
		
	private JPanel titlePanel = new JPanel(); //고정 타이틀 바로 아래 버튼 클릭 후 나오는 타이틀
	
	//도서 대출 화면 구성 요소 ****************
	private JPanel bookPanel = new JPanel(); //도서 대출 메인 화면 패널 > 클릭 후 반응
	private JPanel BscrollPanel = new JPanel(); //스크롤 팬 부착 패널 > BorderLayout
	private JPanel bookBtnPanel = new JPanel();  //책 버튼 부착 패널 - 이 패널을 스크롤 패널에 부착
		
	private JPanel[] bookInfoPanel = new JPanel[20];
	
	String[] book = {"지구에서 한아뿐", "1차원이 되고 싶어", "우리가 빛의 속도로 갈 수 없다면", "여행의 이유", 
						"데미안", "달러구트 꿈 백화점", "목소리를 드릴게요", "지적 대화를 위한 넓고 얕은 지식 1",
						"칵테일, 러브, 좀비", "어린이라는 세계", "불편한 편의점", "물고기는 존재하지 않는다",
						"방금 떠나온 세계", "영어 필사 100일의 기적", "혼자가 혼자에게", "트렌드 코리아 2023",
						"아버지의 해방일지", "불편한 편의점 2", "세상의 마지막 기차역", "원씽"};
	
	String[] writer = {"정세랑", "박상영", "김초엽", "김영하", "헤르만 헤세",
					"이미예", "정세랑", "채사장", "조예은", "김소영",
					"김호연", "룰루 밀러", "김초엽", "리아", "이병률",
					"김난도 외", "정지아", "김호연", "무라세다케시", "게리 켈러"};
	
	private JLabel[] bookName = new JLabel[20];
	private JLabel[] bookWriter = new JLabel[20];
	private String bookState;
		
	JButton[] bookBtn = new JButton[20];
		
	private JLabel bookLabel = new JLabel(bicon);
	
	Operator o = null;
	
	public BookUI(Operator _o) {
		o = _o;
		
		setTitle("[새싹 도서관] 도서 대출");
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
		
		//도서 대출 화면 구성 - 첫 화면 *****************
		//메인 화면에서 도서 대출 버튼 클릭 후 화면 전환된 패널 = bookPanel
		bookPanel.setLayout(new BorderLayout());
		titlePanel.setLayout(new BorderLayout()); //searchPanel + bookLabel
		bookBtnPanel.setLayout(new GridLayout(0,1)); //버튼 부착은 한 개의 열로
		
		for(int i = 0; i<20; i++) {
			bookInfoPanel[i] = new JPanel();
			bookInfoPanel[i].setLayout(new GridLayout(2,1)); //텍스트 두 개 붙일 패널
		}
				
		titlePanel.setBackground(Color.YELLOW);
		titlePanel.setOpaque(true);
		titlePanel.add(bookLabel, BorderLayout.NORTH); //bookLabel이 위쪽
				
		bookPanel.add(titlePanel, BorderLayout.NORTH); //titlePanel을 도서 대출 전체 패널에 부착
		
		JTextField tf = new JTextField(20);
		for(int i=0; i<20; i++) {
			// 총 20 개의 버튼을 등록
			bookWriter[i] = new JLabel(writer[i]);
			bookWriter[i].setHorizontalAlignment(JLabel.CENTER);
			
			bookBtn[i] = new JButton(book[i]);
			bookBtn[i].setFont(new Font("Arial", 3, 15));
			bookInfoPanel[i].add(bookBtn[i]);
			bookInfoPanel[i].add(bookWriter[i]);
			
			TitledBorder TB = new TitledBorder(new LineBorder(Color.GRAY));
			
			bookInfoPanel[i].setBorder(TB);
			
			bookBtnPanel.add(bookInfoPanel[i]);
			
			bookBtn[i].addActionListener(new ActionListener() { //책 버튼을 클릭했을 때 대출 안내 이미지 출력
				public void actionPerformed(ActionEvent e) {
					JButton b = (JButton)e.getSource();
					
					String btnName ="";
					boolean flag = false;
					
					int result = JOptionPane.showConfirmDialog(null, "대출하시겠습니까?", "대출 확인", JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.CLOSED_OPTION)
						tf.setText("Just Closed without Selection");
					
					else if(result == JOptionPane.YES_OPTION) {
						tf.setText("대출");
						
						for(int i = 0; i<20; i++) {
							btnName = book[i];
							
							if(b.getText().equals(btnName)) {
								if(db.checkState(btnName) == true) flag = true;
								break;
							}
						}
						
						if(flag) {
							db.changeState(btnName);
							JOptionPane.showMessageDialog(null, "대출되었습니다.");
						} else {
							System.out.println("대출 실패 > 이미 대출된 도서");
							JOptionPane.showMessageDialog(null, "이미 대출된 도서입니다.");
						}
							
					}
						
					else	
						tf.setText("취소");
				}
			});
		}
				
			BscrollPanel.setLayout(new BorderLayout()); //스크롤팬을 등록하기 위해서는 반드시 BorderLayout
			BscrollPanel.add(bookBtnPanel); //책 버튼이 부착된 패널을 scrollPanel에 부착
			scrollPane = new JScrollPane(BscrollPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(4, 4, 400, 600);
			bookPanel.add(scrollPane); //스크롤 패널이 부착된 스크롤 팬을 북 패널에 등록
				
			panel.add(bookPanel, BorderLayout.CENTER); //컨테이너에 홈 화면 bookPanel 추가 "패널 변동 포인트"
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
        	o.BU.setVisible(false);
        }
    }
}
