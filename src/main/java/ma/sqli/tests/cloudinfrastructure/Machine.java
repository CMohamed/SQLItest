package ma.sqli.tests.cloudinfrastructure;

class MachineStateException extends Exception{
    public MachineStateException(){
        System.out.println("this machine is already running!");
    }
}

public class Machine {
    private String name;
    private String os;
    private double diskSize;
    private double memory;
    private String stat;

    public Machine(String name, String os, double diskSize, double memory) {
        this.name = name;
        this.os = os;
        this.diskSize = diskSize;
        this.memory = memory;
        this.stat = "inactive";
    }

    public Machine(){}

    public void start() throws MachineStateException{
        if (this.stat.equals("running")){
            throw new MachineStateException();
        }
        else {
            this.stat = "running";
        }
    }
    public void stop(){
        this.stat = "stopped";
    }

    public String getName() {
        return name;
    }

    public String getOs() {
        return os;
    }

    public double getDiskSize() {
        return diskSize;
    }

    public double getMemory() {
        return memory;
    }

    public String getStat() {
        return stat;
    }

    public String showMachine(){
        return this.getName()+":"+this.getStat();
    }
}
