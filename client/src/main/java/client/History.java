package client;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.awt.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class History implements Serializable {
    File file;
    String name;
    Controller controller;

    public History(String name){
        file = new File("history_" + name);
    }
    public void add(String msg) throws IOException {
        FileWriter fw = new FileWriter(file,true);
        fw.write(msg + "\n");
        fw.flush();
        fw.close();
    }
    public void read(Controller controller) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String x = "";
        int lines = 0;
       Queue<String> queue = new LinkedList<>();
        while((x = br.readLine()) != null){
                queue.offer(x);
                lines++;
        }
        if(lines > 100){
            for (int i = 0; i <lines-100 ; i++) {
                queue.poll();
            }
            for (int i = 0; i <100 ; i++) {
                controller.addHistory( queue.poll() + "\n");
            }
        }else{
            for (int i = 0; i <lines ; i++) {
                controller.addHistory(queue.poll() + "\n");
            }
        }
        fr.close();
        br.close();

    }
    public boolean isCreate(Controller controller){
        return file.exists();
    }
}
