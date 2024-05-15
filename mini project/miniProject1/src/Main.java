
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);

        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        System.out.println("Please pick the type of list to use, input 1 for ArrayList or 2 for LinkedList: ");
        int arrayListChoice = scan.nextInt();
        while(arrayListChoice != 1 && arrayListChoice != 2) {
            System.out.println("Please pick a number: 1 or 2.");
            scan.nextInt();
        }
        boolean usingArrayList = (arrayListChoice == 1);

        System.out.println("Please pick the main data structure to use, input 1 for UnsortedCallDB or 2 SortedCallDB: ");
        int dataStructureType = scan.nextInt();
        while(dataStructureType != 1 && dataStructureType != 2) {
            System.out.println("Please pick a number: 1 or 2.");
            scan.nextInt();
        }
        boolean dataStructureTypeBoolean = (dataStructureType == 1);
        System.out.println("Please specify the path to the text file containing the calls that will be loaded: (format should be ./data_files/calls_xk.csv");


        String filePath = scan.next();
        java.io.File file = new File(filePath);
        while (!file.exists()) {
            System.out.println("File does not exist. Please specify a different path: ");
            filePath = scan.next();
            file = new File(filePath);
        }
        callDB db;
        if(dataStructureTypeBoolean) {
            db = new UnsortedCallDB(usingArrayList);
        }else {
            db = new SortedCallDB(usingArrayList);
        }
        scan = new Scanner(file);
        long startTime1 = System.nanoTime();
        int callCount = 0;
        while(scan.hasNext()) {
            String call = scan.nextLine();
            String[] stringArray = call.split(",");
            Call newCall = new Call(stringArray[0], stringArray[1], stringArray[2], stringArray[3], stringArray[4]);
            db.index_call(newCall);
            callCount++;
        }
        long endTime1 = System.nanoTime();
        double elapsedTimeInSeconds1 = (endTime1 - startTime1) * 1e-9;
        System.out.println(String.format("Elapsed Time: %.6f seconds.", elapsedTimeInSeconds1));
        System.out.println("Total number of calls in the file: " + callCount);
        if(db instanceof SortedCallDB) {
            System.out.println("Unique source numbers found: " + ((SortedCallDB) db).uniqueSourceNumberCount());
            ((SortedCallDB) db).printPhoneCallList();
        }

        scan = new Scanner(System.in);
        System.out.println("Please select an option: (1) List all calls from number or (2) exit: ");
        int scanOption = scan.nextInt();
        scan.nextLine();
        while(scanOption != 2) {
            if(scanOption == 1) {
                System.out.println("Please enter a phone number in the format: (xx)-xx-xxxxx");
                String phoneNumberScanner = scan.nextLine();
                phoneNumberScanner = phoneNumberScanner.replace("\n", "").replace("\r", "");
                long startTime = System.nanoTime();
                List<Call> phoneNumberSearch = db.search_calls(phoneNumberScanner);
                long endTime = System.nanoTime();
                System.out.println(phoneNumberSearch);
                double elapsedTimeInSeconds = (endTime - startTime) * 1e-9;
                System.out.println(String.format("Elapsed Time: %.6f seconds.", elapsedTimeInSeconds));
                System.out.println("Please select an option: (1) List all calls from number or (2) exit: ");


            } else {
                System.out.println("Please select another option.");
            }
            scanOption = scan.nextInt();
            scan.nextLine();

        }

    }


}

