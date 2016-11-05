import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelOutput;
import org.jcsp.util.Buffer;

/**
 * Created by rd019985 on 05/11/2016.
 */
public class SendEvenIntsProcess implements CSProcess {
    private ChannelOutput outEven;
    private ChannelOutput outOdd;
    private ChannelOutput outOverFlow;

    private Buffer buffer = new Buffer(20);

    public SendEvenIntsProcess(ChannelOutput outEven, ChannelOutput outOdd, ChannelOutput outOverFlow){
        this.outEven = outEven;
        this.outOdd = outOdd;
        this.outOverFlow = outOverFlow;
    }

    @Override
    public void run() {
        for(int i = 0; i <= 100; i++){
            buffer.put(new Integer(i));

            if(buffer.getState() == 2) {
                if(Integer.parseInt(buffer.startGet().toString()) % 2 == 0){
                    System.out.println("Out channel" + outEven);
                    outEven.write(buffer.get());
                } else {
                    System.out.println("Out channel" + outOdd);
                    outOdd.write(buffer.get());
                }
            }
        }

        while(buffer.startGet() != null){
            System.out.println("Out channel" + outOverFlow);
            outOverFlow.write(buffer.get());
        }
    }
}
