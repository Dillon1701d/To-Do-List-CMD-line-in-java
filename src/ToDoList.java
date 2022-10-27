import java.io.FileNotFoundException;
import java.util.*;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
public class ToDoList {
    Scanner input = new Scanner(System.in);
    Collection<String> theList = new ArrayList<>();


    //adds a need entry in the for of a string
    public void addEntry(String newEntry) {
        theList.add(newEntry);

    }
    //removes an entry from the list, user will be displayed the list, and they will enter the number beside the item they wish to remove.
    public boolean removeEntry(int entryPos) {

        int i = 1;
        for(String element:theList){
            if(i == entryPos){
                theList.remove(element);
                return true;
            }
            i++;
        }
        return false;

    }


    //will loop through each item in the list/collection and print it out.
    public void display() {
        int i = 1;
        for(String element: theList){
            System.out.println(i +"."+element);
            i++;
        }
    }

    //attempts to open previously list and add its contents to theList collection of strings.

    public void openList(String fileName){
        try{
            File ListFile = new File(fileName);
            Scanner readFile = new Scanner(ListFile);
            while(readFile.hasNextLine()){
                theList.add(readFile.nextLine());
            }
            readFile.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        this.display();
    }

    //Saves list to a .txt, will return true if successful and a unique filename has been chosen, will return false if
    //one already exists with the chosen name, prevent users from exiting the program.
    //extra option give too user if they wish to overwrite a filename that already exists.
    public boolean saveList() {
        System.out.println("Please enter the name you wish to save this list under:");
        String filename = input.nextLine();
        try{
            File newListFile = new File (filename);
            if (newListFile.createNewFile()) {
                FileWriter writeListToFile = new FileWriter(filename);
                for(String element: theList){
                    writeListToFile.write(element+"\n");
                }
                writeListToFile.close();
                System.out.println("List saved" + newListFile.getName());
                System.out.println("It has been saved under:" + newListFile.getPath());
                return true;
            }else{
                System.out.println("Would you like to overwrite the current list called " + filename+ "? y/n");
                if(input.nextLine().equals("y")){
                    FileWriter writeListToFile = new FileWriter(filename);
                    for(String element: theList){
                        writeListToFile.write(element+"\n");
                    }
                    writeListToFile.close();
                    System.out.println("List saved" + newListFile.getName());
                    System.out.println("It has been saved under:" + newListFile.getPath());
                    return true;
                }
            }

        }catch(IOException e){
            System.out.println(e);
        }
        System.out.println("A file already exists with that name, please try a different name.");
        return false;
    }

    //core menu loop of the program.
    public void menu(){
        boolean running = true;
        while(running) {
            System.out.println("--------------------To-Do-List------------------");
            System.out.println("1.Add an item to the list.");
            System.out.println("2.Remove an item for the list.");
            System.out.println("3.Open previously Saved List");
            System.out.println("4.Exit and save list");
            System.out.println("(Input the number for the action you wish to take)");
            System.out.println("-----------------------List----------------------");
            try {
                Scanner menuinput = new Scanner(System.in);
                int choice = menuinput.nextInt();
                switch (choice) {
                    case (1) -> {
                        System.out.println("Enter in a task to add to the list.");
                        this.addEntry(input.nextLine());
                        this.display();
                    }
                    case (2) -> {
                        System.out.println("Enter the number beside the item you wish to remove.");
                        if(!this.removeEntry(input.nextInt())) {
                            System.out.println("List item not found.");
                        }
                        this.display();
                    }
                    //remove
                    case (3) -> {
                        System.out.println("Please enter the exact name of the list you wish to open");
                        this.openList(input.nextLine());
                    }
                    case (4) -> {
                        if(this.saveList()) running = false;
                    }
                    default -> System.out.println("That was not a valid choice please try again.");
                }
            }catch(InputMismatchException e){
                System.out.println(e);
                System.out.println("Please try again");
            }
        }
    }



    public static void main(String[] args) {

        ToDoList list = new ToDoList();
        list.menu();

    }


}