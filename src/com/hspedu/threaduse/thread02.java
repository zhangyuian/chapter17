package com.hspedu.threaduse;

/**
 * @author: bytedance
 * @date: 2022/1/19
 * @description:
 */
public class thread02 {
    public static void main(String[] args) {
//        Dog dog = new Dog();
//        //dog.start(); 这里不能调用start
//        //创建了 Thread 对象，把 dog对象（实现Runnable），放入Thread
//        Thread thread = new Thread(dog);
//        thread.start();

        Tiger tiger = new Tiger();
        ThreadProxy threadProxy = new ThreadProxy(tiger);
        threadProxy.start();

    }
}

class Animal {}
class Tiger extends  Animal implements Runnable {

    @Override
    public void run() {
        System.out.println("老虎嗷嗷叫....");
    }
}

//线程代理类，模拟了一个极简的
class ThreadProxy implements Runnable {

    private Runnable target = null;//属性，类型是 Runnable

    @Override
    public void run() {
        if(target != null) {
            target.run();//动态绑定
        }
    }//你可以把 Proxy 类当做 Thread

    public ThreadProxy(Runnable target) {
        this.target = target;
    }

    public void start() {
        start0();//这个方法是真正实现多线程的方法
    }

    public void start0() {
        run();
    }
}

class Dog implements Runnable {

    int count = 0;
    @Override
    public void run() { //普通方法，并不会真正启动一个多线程
        while(true) {
            System.out.println("小狗汪汪叫..hi" + (++count) + Thread.currentThread().getName());

            //休眠1秒
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(count == 10) {
                break;
            }
        }
    }
}
