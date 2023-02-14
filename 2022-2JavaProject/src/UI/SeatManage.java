package UI;

public class SeatManage {
	boolean thirdTable[] = new boolean [20];
	boolean fourthTable[] = new boolean[20];
	
	boolean check = false;
	
	public void clear() { 
		for(int i = 0; i < 20; i++) {
			thirdTable[i] = false;
			fourthTable[i] = false;
		}
		
	}
	
	/*
	public void print() {
		System.out.println();
		System.out.println("[현재 좌석 상태]");
        for (int i = 0; i < setTable.length; i++) {
            for (int j = 0; j < setTable[i].length; j++) {
                // 해당좌석이 예약이 되었는지 보여주기
                if (setTable[i][j] == true) {
                    System.out.print("C["+(i+1)+(j+1)+"] ");
                } else {
                    System.out.print("V["+(i+1)+(j+1)+"] ");
                }
            }
            System.out.println();
        }
       System.out.println();
	}
	*/
	
	boolean setTSeat(String number) {
		int num = Integer.valueOf(number);
		
		if (thirdTable[num] == false || check == false) {
			thirdTable[num] = true;
			System.out.println("선택되었습니다.");
			check = true;
			return true;
		}
		else{
			System.out.println("이미 선택했습니다.");
			return false;
		}
	}
	
	boolean setFSeat(String number) {
		int num = Integer.valueOf(number);
		
		if(fourthTable[num] == false || check == false) {
			fourthTable[num] = true;
			System.out.println("선택되었습니다.");
			check = true;
			return true;
		}
		else {
			System.out.println("이미 선택했습니다.");
			return false;
		}
	}
	
	/*
	void releaseSeat(int x, int y) {
		if (setTable[x-1][y-1] == true) {
			setTable[x-1][y-1] = false;
			System.out.println("해제되었습니다.");
			System.out.println();
		}
	}
	*/

};
