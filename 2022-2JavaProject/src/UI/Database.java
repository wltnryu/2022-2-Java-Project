package UI;

import java.sql.*;

public class Database {
	Connection con = null;
	Statement stmt = null;
	String url = "jdbc:mysql://localhost/JavaProject?serverTimezome=Asia/Seoul";
	String user = "root";
	String passwd = "wlehd1004-";
	
	static int total_income = 0;
	
	Database(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, passwd);
			stmt = con.createStatement();
			//System.out.println("MySQL 서버 연동 성공");
		} catch(Exception e) {
			System.out.println("MySQL 서버 연동 실패 > " + e.toString());
		}
	}

	public void totalIncome(int income) {
		total_income = total_income + income;
	}
	
	public static int getTotal_income() {
		return total_income;
	}
	
	/* 로그인 정보 확인 */
	boolean logincheck(String _i, String _p) {
		boolean flag = false;
		
		String id = _i;
		String pw = _p;
		
		try {
			String checkingStr = "SELECT password FROM userInfo WHERE id='" + id + "'";
			ResultSet result = stmt.executeQuery(checkingStr);
			
			int count = 0;
			while(result.next()) {
				if(pw.equals(result.getString("password"))) {
					flag = true;
					//System.out.println("로그인 성공");
				}
				
				else {
					flag = false;
					System.out.println("로그인 실패");
				}
				count++;
			}
		} catch(Exception e) {
			flag = false;
			System.out.println("로그인 실패 > " + e.toString());
		}
		
		return flag;
	}
	
	
	boolean joinCheck(String _i, String _p) {
		boolean flag = false;
		
		String id = _i;
		String pw = _p;
			
		try {
			String insertStr = "INSERT INTO userInfo VALUES('" + id + "', '" + pw + "', 'N')";
			stmt.executeUpdate(insertStr);
				
			flag = true;
			System.out.println("회원가입 성공");
		} catch(Exception e) {
			flag = false;
			System.out.println("회원가입 실패 > " + e.toString());
		}
			
		return flag;
	}
	
	boolean howManySeat() {
		boolean flag = false;
		
		String state = "Y";
		int count = 0;
		
		try {
			
			String stateStr = "SELECT state FROM seatManage";
			ResultSet result = stmt.executeQuery(stateStr);
		
			while(result.next()) {
				if(state.equals(result.getString("state"))) {
					count++;
					//System.out.println("좌석 예약 가능");
				}
			}
		} catch(Exception e) {
			System.out.println("불러오기 실패 > " + e.toString());
			flag = false;
		}
		
		if(count > 0) flag = true;
		else flag = false;
		
		return flag;
	}
	
	
	boolean checkSeat(int seatNum) {
		boolean flag = false;
		
		int num = seatNum + 1;
		String state = "N";
		
		try {
			
			String stateStr = "SELECT state FROM seatManage WHERE seatNum= " + num;
			ResultSet result = stmt.executeQuery(stateStr);
			
			int count = 0;
			while(result.next()) {
				if(state.equals(result.getString("state"))) {
					flag = true;
					//System.out.println("좌석 예약 가능");
				}
				
				else {
					flag = false;
					//System.out.println("상태 확인 실패");
				}
				count++;
			}
		} catch(Exception e) {
			System.out.println("불러오기 실패 > " + e.toString());
			flag = false;
		}
		
		return flag;
	}
	
	
	void changeSeat(String btnName) {
		
		int num = Integer.valueOf(btnName);
		
		try {
			String changeStr = "UPDATE seatManage SET state  = 'Y' WHERE seatNum = " + num;
			stmt.executeUpdate(changeStr);
			
			System.out.println("좌석 예약 성공");
			
		} catch(Exception e) {
			System.out.println("좌석 예약 실패 > " + e.toString());
		}
		
	}
	
	
	void resetSeat() {
		try {
			String resetStr = "UPDATE seatManage SET state  = 'N'";
			stmt.executeUpdate(resetStr);
			
			System.out.println("좌석 리셋 성공");
			
		} catch(Exception e) {
			System.out.println("좌석 리셋 실패 > " + e.toString());
		}
	}
	
	
	boolean checkState(String btnName) {		
		boolean flag = false;
		
		String name = btnName;
		String state = "N";
		
		try {
			
			String stateStr = "SELECT state FROM book_list WHERE name='" + name + "'";
			ResultSet result = stmt.executeQuery(stateStr);
			
			int count = 0;
			while(result.next()) {
				if(state.equals(result.getString("state"))) {
					flag = true;
					System.out.println("상태 확인 완료");
				}
				
				else {
					flag = false;
					System.out.println("상태 확인 실패");
				}
				count++;
			}
				
		} catch(Exception e) {
			System.out.println("불러오기 실패 > " + e.toString());
			flag = false;
		}
		
		return flag;
	}
	
	
	void changeState(String btnName) {
		
		String name = btnName;
		
		try {
				String changeStr = "UPDATE book_list SET state  = 'Y' WHERE name = '" + name + "'";
				stmt.executeUpdate(changeStr);
			
				System.out.println("상태 변경 완료");
			
		} catch(Exception e) {
			System.out.println("변경 실패 > " + e.toString());
		}
		
	}
}
