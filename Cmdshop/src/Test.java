import java.io.InputStream;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        boolean bo=true;
        while (bo) {
            //File file=new File("D:\\JAVAlx\\shangcheng\\src\\users.xlsx");
            InputStream in = Class.forName("Test").getResourceAsStream("/users.xlsx");
            InputStream is = Class.forName("Test").getResourceAsStream("/product.xlsx");
            ReadUserExcel readExcel = new ReadUserExcel();
            User[] users = readExcel.readExcel(in);

            System.out.println("请输入用户名");
            Scanner sc = new Scanner(System.in);
            String username = sc.next();

            System.out.println("请输入密码");
            String password = sc.next();

            for (User user : users) {
                if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    System.out.println("登录成功");
                    ReadProductExcel readProductExcel=new ReadProductExcel();
                    Product[] products=readProductExcel.getAllProduct(is);
                    for(Product product:products){
                        System.out.println(product.getpId());
                        System.out.println(product.getpName());
                        System.out.println(product.getPrice());
                        System.out.println(product.getpDesc());
                    }
                    bo=false;
                    System.out.println("请输入商品Id");
                    String ID=sc.next();
                    Product[] carts=new Product[3];
                    int cart=0;
                    is=null;
                    is=Class.forName("Test").getResourceAsStream("/product.xlsx");
                    Product product=readProductExcel.getProductById(ID,is);
                    if(product!=null){
                        carts[cart++]=product;
                    }
                    System.out.println("继续购买商品请按1");
                    System.out.println("查看购物车请按2");
                    int choose=sc.nextInt();
                    if(choose==1){
                        is=null;
                        is = Class.forName("Test").getResourceAsStream("/product.xlsx");
                        readExcel = new ReadUserExcel();
                        products=readProductExcel.getAllProduct(is);
                        for(Product p:products){
                            System.out.println(p.getpId());
                            System.out.println(p.getpName());
                            System.out.println(p.getPrice());
                            System.out.println(p.getpDesc());
                        }
                        System.out.println("请输入商品Id");
                        ID=sc.next();
                        is=null;
                        is = Class.forName("Test").getResourceAsStream("/product.xlsx");
                        product=readProductExcel.getProductById(ID,is);
                        if(product!=null){
                            carts[cart++]=product;
                        }
                    }else if(choose==2){
                    }
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