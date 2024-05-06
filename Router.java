class Router {

    /*public Semaphore semaphore;
    int maxNumberOfConnections;
    int connectionNumber = 0;
    ArrayList<Integer> connectionsDevices;

    public Router(int maxNumberOfConnections) {
        this.maxNumberOfConnections = maxNumberOfConnections;
        connectionsDevices = new ArrayList<Integer>();
        semaphore = new Semaphore(maxNumberOfConnections);
        for (int i=0; i< maxNumberOfConnections; i++) {
            connectionsDevices.add(i);
        }
    }

    public int Occupy(Device d) throws InterruptedException{
        semaphore.wait(d);
        for (int i=0; i<maxNumberOfConnections; i++) {
            if (connectionsDevices.get(i) == 0) {
                connectionsDevices.set(i,1);
                connectionNumber = i+1;
                break;
            }
        }
        return connectionNumber;

    }
    public void Release(Device d) {
        connectionsDevices.set(connectionNumber-1,0);
        semaphore.signal();
    }
}
    */
/*
    public boolean[] IsConnected;
    public int MaxConnection, ConnectedDevices;
    public Semaphore semaphore;

    Router(int MaxConnection) {
        this.MaxConnection = MaxConnection;
        semaphore = new Semaphore(MaxConnection);
        IsConnected = new boolean[MaxConnection];
    }

    public synchronized int Occupy(Device device) throws InterruptedException {
        semaphore.wait(device);
        for (int i = 0; i < MaxConnection; i++) {
            if (!IsConnected[i] == false) {
                ConnectedDevices++;
                device.connectionNumber = i + 1;
                IsConnected[i] = true;
                break;
            }
        }
        return device.connectionNumber;

    }

    public synchronized void Release(Device device) {
        ConnectedDevices--;
        IsConnected[device.connectionNumber - 1] = false;
        notify();
        System.out.println("Connection " + device.connectionNumber + ": " + device.name + " Logged out");
        semaphore.signal();
    }*/

    public boolean[] IsConnected;
    public int MaxConnection, ConnectedDevices;
    public Semaphore semaphore;

    Router(int MaxConnection) {
        this.MaxConnection = MaxConnection;
        semaphore = new Semaphore(MaxConnection);
        IsConnected = new boolean[MaxConnection];
    }

    public int Occupy(Device device) throws InterruptedException {
        semaphore.wait(device);
        for (int i = 0; i < MaxConnection; i++) {
            if (!IsConnected[i]) {
                ConnectedDevices++;
                device.connectionNumber = i + 1;
                IsConnected[i] = true;
                break;
            }
        }
        semaphore.signal();  // Release the semaphore after acquiring a connection
        return device.connectionNumber;
    }

    public void Release(Device device) {
        ConnectedDevices--;
        IsConnected[device.connectionNumber - 1] = false;
        System.out.println("Connection " + device.connectionNumber + ": " + device.name + " Logged out");
        semaphore.signal();
    }
}

