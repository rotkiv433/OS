package HW02_1;

import java.io.IOException;

public class TwoThreads {

    public static class ThreadAB implements Runnable {

        private String name1;
        private String name2;
        private String izbor;


        public ThreadAB(String izbor) {
            this.izbor = izbor;
        }

        @Override
        public void run() {
            try {
                if(izbor.equals("azbuka")) {
                    for(char i = 'A'; i<='Z';i++) {
                        System.out.println(i);
                    }
                }
                else if(izbor.equals("broevi")) {
                    for(int i=1;i<=26;i++) {
                        System.out.println(i);
                    }
                }
            }catch (Exception e) {}
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new ThreadAB("azbuka"));
        Thread t2 = new Thread(new ThreadAB("broevi"));
        t1.start();
        t2.start();

    }

}
