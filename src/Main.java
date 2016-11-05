import consumers.ReadEvenIntsProcess;
import org.jcsp.lang.*;

public class Main {

    public static void main(String[] args) {
        One2OneChannel chan = Channel.one2one();

        new Parallel(
                new CSProcess[]{
                        new SendEvenIntsProcess(chan.out(), chan.out(), chan.out()),
                        new ReadEvenIntsProcess(chan.in())
                }
        ).run();
    }
}
