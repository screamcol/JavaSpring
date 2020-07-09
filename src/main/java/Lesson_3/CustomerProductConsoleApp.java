package Lesson_3;

import Lesson_3.customer.Customer;
import Lesson_3.product.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CustomerProductConsoleApp {
    private static SessionFactory factory = new Configuration()
            .configure("configs/customer_product/hibernate.cfg.xml")
            .buildSessionFactory();

    private static Session session = null;

    public static void main(String[] args) {
        String queryFrom;
        String goodsName;
        String customerName;
        PrepareData.forcePrepareData();

        try {
            System.out.println("To get info which goods buy customer, write 'get products [customer_name]'\n" +
                    "To get info which clients buy particular goods write 'get customers [goods_name]'\n" +
                    "To delete Product or Customer enter 'delete product [product_title]', 'delete customer [customer_name]'");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                String input = reader.readLine();

                if (input.equals("end")) break;

                String[] inputStr = input.split(" ");
                if (inputStr.length != 3) {
                    System.out.println("Enter 3 word divided with space like in example. Try again");
                    continue;
                }

                if (inputStr[0].startsWith("delete")) {
                    if (inputStr[1].equals("product")) {
                        deleteProductByTitle(inputStr[2]);
                        continue;
                    } else if (inputStr[1].equals("customer")) {
                        deleteCustomerByName(inputStr[2]);
                        continue;
                    }
                } else if (inputStr[0].startsWith("get")) {
                    queryFrom = inputStr[1];
                    if (queryFrom.equals("products")) {
                        customerName = inputStr[2];
                        getProductsByCustomer(customerName);
                        continue;
                    } else if(queryFrom.equals("customers")) {
                        goodsName = inputStr[2];
                        getCustomersByProduct(goodsName);
                        continue;
                    }
                }
                System.out.println("You forget to specify what you want to get [goods or customers]");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }

    public static void getProductsByCustomer(String customerName) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Customer customer = session.createQuery("SELECT c FROM Customer c WHERE c.name = :name", Customer.class)
                .setParameter("name", customerName)
                .getSingleResult();
        System.out.println("Customer: " + customer);
        System.out.println("Products: ");
        for (Product p : customer.getProducts()) {
            System.out.println("product - " + p.getTitle() + " price - " + p.getPrice());
        }
        session.getTransaction().commit();
    }

    public static void getCustomersByProduct(String goodsTitle) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Product product = session.createQuery("SELECT p FROM Product p WHERE p.title = :title", Product.class)
                .setParameter("title", goodsTitle)
                .getSingleResult();
        System.out.println("Product: " + product);
        System.out.println("Customers: ");
        for (Customer c : product.getCustomers()) {
            System.out.println(c.getName());
        }
        session.getTransaction().commit();
    }

    public static void deleteProductByTitle(String title) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Product product = session.createQuery("SELECT p FROM Product p WHERE p.title = :title", Product.class)
                .setParameter("title", title)
                .getSingleResult();
        session.delete(product);
        session.getTransaction().commit();
    }

    public static void deleteCustomerByName(String name) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Customer customer = session.createQuery("SELECT c FROM Customer c WHERE c.name = :name", Customer.class)
                .setParameter("name", name)
                .getSingleResult();
        session.delete(customer);
        session.getTransaction().commit();
    }
}
