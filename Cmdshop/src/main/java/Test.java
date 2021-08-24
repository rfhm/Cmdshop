import org.apache.xmlbeans.soap.SchemaWSDLArrayType;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Test {
    static Product carts[] = new Product[3];//创建购物车（用数组模拟）
    static int count = 0;

    public static void main(String[] args) throws ClassNotFoundException {
        boolean bo = true;
        while (bo) {
            System.out.println("请输入用户名");
            Scanner sc = new Scanner(System.in);
            String username = sc.next();
            System.out.println("请输入密码");
            String password = sc.next();

            //File file=new File("D:\\JAVAlx\\shangcheng\\src\\users.xlsx");
            InputStream in = Class.forName("Test").getResourceAsStream("/users.xlsx");
            ReadUserExcel readExcel = new ReadUserExcel();
            User[] users = readExcel.readExcel(in);
            for (int i = 0; i < users.length; i++) {
                if (username.equals(users[i].getUsername()) && password.equals(users[i].getPassword())) {
                    bo = false;
                    shopping(sc);
                    while (true) {
                        System.out.println("查看购物车请按1");
                        System.out.println("继续购买商品请按2");
                        System.out.println("结账请按3");
                        System.out.println("退出系统请按4");
                        int choose = sc.nextInt();
                        if (choose == 1) {
                            viewCarts();
                        } else if (choose == 2) {
                            shopping(sc);
                        }else if(choose==3){
                            Order order=new Order();
                            order.setUser(users[i]);//关联用户
                            Product products[]=new Product[count];
                            for(int j=0;j<carts.length;j++){
                                if(carts[j]!=null){
                                    products[j]=carts[j];
                                }
                            }
                            order.setProducts(products);//关联商品
                            Map<Integer,Integer> ammount=new HashMap<Integer,Integer>();
                            ammount.put(11111,2);
                            ammount.put(22222,1);
                            order.setAmmount(ammount);
                            CreateOrder.createOrder(order);

                        }else if (choose == 4) {
                            break;
                        }
                    }
                    break;
                } else {
                    System.out.println("用户名或者密码错误");
                    break;
                }
            }
        }
    }

    public static void viewCarts() {
        System.out.println("当前购物车商品:");
        for (Product product : carts) {
            if (product != null) {
                System.out.print(product.getpId());
                System.out.print("\t" + product.getpName());
                System.out.print("\t" + product.getPrice());
                System.out.println("\t\t" + product.getpDesc());
            }
        }
    }

    public static void shopping(Scanner sc) throws ClassNotFoundException {
        InputStream is = Class.forName("Test").getResourceAsStream("/product.xlsx");
        ReadProductExcel readProductExcel = new ReadProductExcel();
        Product[] products = readProductExcel.getAllProduct(is);
        for (Product product : products) {
            System.out.print(product.getpId());
            System.out.print("\t\t" + product.getpName());
            System.out.print("\t\t" + product.getPrice());
            System.out.println("\t\t" + product.getpDesc());
        }

        System.out.println("请输入商品ID，把该商品加入购物车：");
        String pId = sc.next();
        ReadProductExcel readProductExcel1 = new ReadProductExcel();
        is = null;
        is = Class.forName("Test").getResourceAsStream("/product.xlsx");//  /表示的就是classpath
        Product product = readProductExcel1.getProductById(pId, is);
        if (product != null) {
            carts[count++] = product;
        }
    }
}