package HW02_3;
import java.io.*;
import java.io.File;
import java.util.HashSet;


public class FileScanner extends Thread  {

    private String fileToScan;
    //TODO: Initialize the start value of the counter
    private static Long counter = new Long(0);

    public FileScanner (String fileToScan) {
        this.fileToScan=fileToScan;
        //TODO: Increment the counter on every creation of FileScanner object
        synchronized (this) {
            counter++;
        }
    }

    public static void printInfo(File file)  {

        /*
         * TODO: Print the info for the @argument File file, according to the requirement of the task
         * */
        if(!file.exists()) {
            System.out.println("Ne postoi file-ot");
            return;
        }
        if(file.isDirectory()) {
            long size= getDirectorySize(file);
            System.out.println("dir: "+ file.getAbsolutePath()+" "+size);
        }
        else if(file.isFile()) {
            System.out.println("file: "+file.getAbsolutePath()+" "+file.length());
        }

    }

    private static long getDirectorySize(File file) {
        if(!file.isDirectory())
            return 0;
        long length = 0;
        for(File f: file.listFiles()) {
            if(f.isFile())
                length += f.length();
            else if(f.isDirectory())
                length += getDirectorySize(f);
        }
        return length;

    }

    public static Long getCounter () {
        return counter;
    }


    public void run() {

        //TODO Create object File with the absolute path fileToScan.
        File file = new File(fileToScan);

        //TODO Create a list of all the files that are in the directory file.
        File [] files = null;
        if(file.isDirectory()) {
            printInfo(file);
            files = file.listFiles();
        }
        HashSet<Thread> niski = new HashSet<>();

        for (File f : files) {

            /*
             * TODO If the File f is not a directory, print its info using the function printInfo(f)
             * */
            if(!f.isDirectory()) {
                printInfo(f);
            }

            /*
             * TODO If the File f is a directory, create a thread from type FileScanner and start it.
             * */
            else {
                Thread t = new FileScanner(f.getAbsolutePath());
                niski.add(t);
                t.start();
                try {
                    t.join();
                    System.out.println("Thread has finished");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //TODO: wait for all the FileScanner-s to finish

        }


    }

    public static void main (String [] args) throws InterruptedException {
        String FILE_TO_SCAN = "C:\\Users\\rotkiv\\IdeaProjects\\lab2\\src";

        //TODO Construct a FileScanner object with the fileToScan = FILE_TO_SCAN
        FileScanner fileScanner = new FileScanner(FILE_TO_SCAN);

        //TODO Start the thread from type FileScanner
        fileScanner.start();
        //TODO wait for the fileScanner to finish
        fileScanner.join();
        //TODO print a message that displays the number of thread that were created
        System.out.println("Number of threads created: " + counter);

    }
}

