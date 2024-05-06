class Semaphore {
/*
    protected int value ;

    public Semaphore(int val) {

        this.value = val;

    }

    public synchronized void wait(Device device) throws InterruptedException {
        value-- ;
        if (value < 0){
            System.out.println(device.name + " (" + device.type + ")" + " arrived and waiting");
            try {
                wait() ;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            System.out.println(device.name +" (" + device.type + ")" +" arrived");

        }
        device.router.Occupy(device);
    }

    public synchronized void signal() {

        value++ ;
        if (value <= 0) {
            notify() ;
        }

    }*/

    protected int availableConnections;

    public Semaphore(int initialConnections) {
        this.availableConnections = initialConnections;
    }

    public synchronized void wait(Device device) throws InterruptedException {
        while (availableConnections <= 0) {
            System.out.println(device.name + " (" + device.type + ")" + " arrived and waiting");
            wait();
        }
        availableConnections--;
        System.out.println(device.name + " (" + device.type + ")" + " arrived");
        device.router.Occupy(device);
    }

    public synchronized void signal() {
        availableConnections++;
        notify();
    }
}