public class Main {
    public static void main(String[] args) {
        IDandPassw idandpassw = new IDandPassw();
        LoginPage loginpage = new LoginPage(idandpassw.getLoginInfo());
    }
}
