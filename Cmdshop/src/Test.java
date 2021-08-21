import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        boolean bo=true;
        while (bo) {
            //File file=new File("D:\\JAVAlx\\shangcheng\\src\\users.xlsx");
            InputStream in = Class.forName("Test").getResourceAsStream("/users.xlsx");
            ReadExcel readExcel = new ReadExcel();
            User[] users = readExcel.readExcel(in);

            System.out.println("请输入用户名");
            Scanner sc = new Scanner(System.in);
            String username = sc.next();

            System.out.println("请输入密码");
            String password = sc.next();

            for (User user : users) {
                if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    System.out.println("登录成功");
                    bo=false;
                    break;
                } else {
                    System.out.println("用户名或者密码错误");
                    break;
                }
             /*for(User user:users){
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());
            System.out.println(user.getAddress());
            System.out.println(user.getPhone());
            }
            */
            }
        }
    }
}