package UI;

public class Operator {
	
	Database db = null;
	MainFrame mf = null;
	JoinFrame jf = null;
	homeUI hU = null;
	thirdFloorUI tF = null;
	fourthFloorUI fF = null;
	seatUI sU = null;
	drinkUI dU = null;
	BookUI BU = null;
	
	managerUI mU = null;
	checkSeat cS = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Operator opt = new Operator();
		opt.db = new Database();
		opt.mf = new MainFrame(opt);
		opt.jf = new JoinFrame(opt);
		opt.hU = new homeUI(opt);
		opt.tF = new thirdFloorUI(opt);
		opt.fF = new fourthFloorUI(opt);
		opt.sU = new seatUI(opt);
		opt.dU = new drinkUI(opt);
		opt.BU = new BookUI(opt);
		opt.mU = new managerUI(opt);
		opt.cS = new checkSeat(opt);
	}

}
