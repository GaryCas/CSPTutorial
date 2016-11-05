package consumers;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelInput;

/**
 * Created by rd019985 on 05/11/2016.
 */
public class ReadEvenIntsProcess implements CSProcess {
    private ChannelInput in;

    public ReadEvenIntsProcess(ChannelInput in) {
        this.in = in;
    }

    @Override
    public void run() {
        while(true){
            Integer d = (Integer) in.read();
            System.out.println("Read:" + d.intValue() + " " + in);
        }
    }
}
