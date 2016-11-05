import org.jcsp.lang.*;
import org.jcsp.util.Buffer;

/**
 * Created by rd019985 on 05/11/2016.
 */
public class SendEvenIntsProcess implements CSProcess {
    private AltingChannelOutput out0 = null;
    private AltingChannelOutput out1 = null;

    private Buffer buffer = new Buffer(20);

    public SendEvenIntsProcess(final AltingChannelOutput out0, final AltingChannelOutput out1){
        this.out0 = out0;
        this.out1 = out1;
    }

    @Override
    public void run() {
        final Guard[] altChans = {out0, out1};
        final Alternative alt = new Alternative(altChans);

        while(true){
            switch (alt.select()){
                case 0:
                    out0.write(new Integer(0));
                    System.out.println("out0 written");
                    break;
                case 1:
                    out1.write(new Integer(1));
                    System.out.println("out1 written");
                    break;
            }
        }
    }

}
