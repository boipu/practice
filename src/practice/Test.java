package practice;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    public  Test x;

    Optional<String> bookName;
    public Test(Optional<String> name){
        bookName= name;
    }

    public Optional<String> getName(){
        return bookName;
    }

    public static void main(String[] args) {
//
//    new Thread(new practice.Test("Wallace")).start();
//    new Thread(new practice.Test("Gromit")).start();

//       for(int i=0 ; i<5 ; i++){
//           new Incre().start();
//           new Decre().start();
//       }
//       System.out.print(c);

//        practice.Test a = new practice.Test();
//        practice.Test b = new practice.Test();
//
//        a.x=b;
//        b.x= a;
//        a = new practice.Test();
//        b=a;
//
//        try{
//            int ans = 1/0;
//        } catch (Exception e1){
//            System.out.print(e1);
//        } catch (ArithmeticException e2){
//            System.out.print(e2);
//        }
        Test t = new Test(Optional.ofNullable(null));
        Optional<String> name = t.getName();
        name.ifPresent(System.out::println);
    }

//    private String name;
//    public practice.Test(String name){
//        this.name=name;
//    }
//
//    @Override
//    public void run() {
//       message(1);
//       message(2);
//    }
//
//    private synchronized void message(int n){
//        System.out.print(name + "-"+ n + " ");
//    }

    private static AtomicInteger c = new AtomicInteger(0);

    static class Decre extends Thread{
        public void run(){
            c.decrementAndGet();
        }
    }

    static class Incre extends Thread{
        public void run(){
            c.incrementAndGet();
        }
    }
}

