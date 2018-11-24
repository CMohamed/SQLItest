package ma.sqli.tests.cloudinfrastructure;

import java.util.ArrayList;

/*
class CreateStoreException extends Exception{
    public CreateStoreException(){
        System.out.println("this store already exist !");
    }
}
/*
class MachineStateException extends Exception{
    public MachineStateException(){
        System.out.println("Vous essayez d'instancier une classe Ville avec un nombre d'habitants négatif !");
    }
}
*/


public class CloudInfrastructure {

    private ArrayList<Store> stores;
    private ArrayList<Machine> machines;


    public CloudInfrastructure(){
        stores = new ArrayList<Store>();
        machines = new ArrayList<Machine>();
    }

    public int findStoreByName(String nameStore){
        for(int i=0; i<stores.size(); i++){
            if (stores.get(i).getName().equals(nameStore)) return i;
        }
        return -1;
    }

    public void createStore (String storeName){
        int index = this.findStoreByName(storeName);
        if (index != -1) {
            storeName = "-1";
        }
        try{
            Store newStore = new Store(storeName);
            stores.add(newStore);
        }catch (CreateStoreException e){

        }/*
        else {
            Store newStore = new Store(storeName);
            stores.add(newStore);
        }*/

    }


    public void uploadDocument(String storeName, String fileName){
        int index = this.findStoreByName(storeName);
        Store selectedStore = this.stores.get(index);
        selectedStore.addFile(fileName);
    }
    public void uploadDocument(String storeName, String... filesNames){
        int index  = this.findStoreByName(storeName);
        Store selectedStore = this.stores.get(index);
        for(String file : filesNames){
            selectedStore.addFile(file);
        }
    }
    public void emptyStore(String nameStore){
        int index = this.findStoreByName(nameStore);
        this.stores.get(index).empty();
    }
    public void deleteStore(String nameStore){
        int index = this.findStoreByName(nameStore);
        this.stores.remove(index);
    }

    public String listStores(){
        String listStore = "";
        if (stores.size()!=0){
            listStore = stores.get(0).showStore();
            for(int i=1; i<stores.size(); i++){
                listStore = listStore + "||" + stores.get(i).showStore();
            }
        }
        return listStore;
    }

    public void createMachine(String name, String os, String diskSize, String memory){
        Machine newMachine = new Machine(name, os, Double.parseDouble(diskSize.substring(0,diskSize.length()-2)), Double.parseDouble(memory.substring(0,diskSize.length()-3)));
        this.machines.add(newMachine);
    }
    public int findMachineByName(String nameMachine){
        for(int i=0; i<this.machines.size(); i++){
            if (this.machines.get(i).getName().equals(nameMachine)) return i;
        }
        return -1;
    }
    public void startMachine(String nameMachine){
        int index = this.findMachineByName(nameMachine);
        try{
            this.machines.get(index).start();
        }catch (MachineStateException e){

        }

    }
    public void stopMachine(String nameMachine){
        int index = this.findMachineByName(nameMachine);
        this.machines.get(index).stop();
    }

    public String listMachines(){
        String listMachine = "";
        if (machines.size()!=0){
            listMachine = machines.get(0).showMachine();
            for(int i = 1; i<machines.size(); i++){
                listMachine = listMachine + "||" + machines.get(i).showMachine();
            }
        }
        return listMachine;
    }

    public double usedDisk(String name){
        int index = this.findMachineByName(name);
        if (index != -1){
            return this.machines.get(index).getDiskSize();
        }
        else{
            index = this.findStoreByName(name);
            return this.stores.get(index).getUsedDisk();
        }
    }
    public double usedMemory(String nameMachine){
        int index = this.findMachineByName(nameMachine);
        Machine selectedMachine = this.machines.get(index);
        if (selectedMachine.getStat().equals("running")) return selectedMachine.getMemory();
        else return 0;
    }
    public double globalUsedDisk(){
        double globalUsedDisk=0;
        for(int i=0; i<machines.size(); i++){
            globalUsedDisk = globalUsedDisk + machines.get(i).getDiskSize();
        }
        for(int i=0; i<stores.size(); i++){
            globalUsedDisk = globalUsedDisk + stores.get(i).getUsedDisk();
        }
        return globalUsedDisk;
    }
    public double globalUsedMemory(){
        double globalUsedMemory = 0;
        for(int i=0; i<machines.size(); i++){
            globalUsedMemory = globalUsedMemory + this.usedMemory(machines.get(i).getName());
        }
        return globalUsedMemory;
    }

}
