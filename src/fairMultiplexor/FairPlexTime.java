package fairMultiplexor;

import org.jcsp.lang.*;

/**
 * Created by rd019985 on 05/11/2016.
 */
public class FairPlexTime implements CSProcess {
    private AltingChannelInput[] in;
    private ChannelOutput out;
    private long timeout;

    public FairPlexTime(AltingChannelInput[] in, ChannelOutput out, long timeout) {
        this.in = in;
        this.out = out;
        this.timeout = timeout;
    }

    @Override
    public void run() {
        final Guard[] guards = new Guard[in.length + 1];
        System.arraycopy(in, 0, guards, 0, in.length);

        final CSTimer tim = new CSTimer();
        final int timerIndex = in.length;
        guards[timerIndex] = tim;

        final Alternative alt = new Alternative(guards);

        boolean running = true;

        tim.setAlarm(tim.read() + timeout);

        while(running){
            final int index = alt.fairSelect();
            if(index == timerIndex){
                running = false;
            } else {
                out.write((in[index].read()));
            }
            System.out.println ("\n\r\tFairPlexTime: timed out ... poisoning all channels ...");
            for (int i = 0; i < in.length; i++) {
                in[i].poison (42);                       // assume: channel immunity < 42
            }
            out.poison (42);
        }
    }
}
