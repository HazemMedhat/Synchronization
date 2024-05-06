import java.io.IOException;
class Device extends Thread{

    public String name,type; // name of the device and type of it
    public Router router;
    public int connectionNumber;

    public Device (String name,String type, Router router) {
        this.name = name;
        this.type = type;
        this.router = router;
        connectionNumber = 1 ;
    }

    @Override
    public void run() {
        try {
            router.semaphore.wait(this);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            connectionNumber = router.Occupy(this);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Connection " + connectionNumber + ": " + name + " Occupied");
        try {
            perform_Online_Activity();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        router.Release(this);
        router.semaphore.signal();

    }

    private void deviceConnect() throws InterruptedException {

        connectionNumber = router.Occupy(this);
    }

    private void perform_Online_Activity() throws InterruptedException {

        System.out.println("Connection " + connectionNumber + ": " + name+ " performs online activity");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void devicelougout() throws IOException {

        System.out.println("Connection " + connectionNumber + ": " + name+ " Logged out ");
        router.Release(this);
    }
}
