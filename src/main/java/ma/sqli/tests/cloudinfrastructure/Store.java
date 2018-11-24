package ma.sqli.tests.cloudinfrastructure;

import java.util.ArrayList;

class CreateStoreException extends Exception{
    public CreateStoreException(){
        System.out.println("this store already exist !");
    }
}



public class Store {
    String name;
    private ArrayList<String> files;
    private double usedDisk;

    public Store(String name) throws CreateStoreException{
        if (name.equals("-1")) throw new CreateStoreException();
        else {
            this.name = name;
            files = new ArrayList<String>();
            usedDisk = 0;
        }
    }
    public void addFile(String nameFile){
        this.files.add(nameFile);
        usedDisk += 0.1;
    }
    public void empty(){
        files = new ArrayList<String>();
        usedDisk = 0;
    }

    public ArrayList<String> getFiles() {
        return files;
    }

    public double getUsedDisk() {
        return usedDisk;
    }

    public String getName() {
        return name;
    }

    public String showStore(){
        String storeContent = this.getName()+":";
        if (files.size()==0) {
            storeContent = storeContent + "empty";
        }
        else{
            storeContent = storeContent + files.get(0);
            for(int i=1; i<files.size(); i++){
                storeContent = storeContent + ", " +files.get(i);
            }
        }
        return storeContent;
    }

    //private Boolean isEmpty;
}
