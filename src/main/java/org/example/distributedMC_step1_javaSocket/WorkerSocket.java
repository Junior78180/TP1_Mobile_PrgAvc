package org.example.distributedMC_step1_javaSocket;

import java.io.*;
import java.net.*;

/**
 * Worker is a server. It computes PI by Monte Carlo method and sends
 * the result to Master.
 */
public class WorkerSocket {
    static int port = 25545; //default port
    private static boolean isRunning = true;

    /**
     * compute PI locally by MC and sends the number of points
     * inside the disk to Master.
     */
    public static void main(String[] args) throws Exception {

        if (!("".equals(args[0]))) {
            port = Integer.parseInt(args[0]);
        }
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);
        Socket socket = serverSocket.accept();

        // BufferedReader bRead for reading message from Master
        BufferedReader bRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // PrintWriter pWrite for writing message to Master
        PrintWriter pWrite = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        String str;
        while (isRunning) {
            str = bRead.readLine();          // read message from Master
            if (!(str.equals("END"))) {
                int totalCount = Integer.parseInt(str);
                System.out.println("Server receives totalCount = " + totalCount);

                // MonteCarlo
                int insideCircle = 0;
                for (int i = 0; i < totalCount; i++) {
                    double x = Math.random();
                    double y = Math.random();
                    if ( x * x + y * y <= 1) {
                        insideCircle++;
                    }
                }

                pWrite.println(insideCircle);         // send number of points in quarter of disk
            } else {
                isRunning = false;
            }
        }
        bRead.close();
        pWrite.close();
        socket.close();
    }
}