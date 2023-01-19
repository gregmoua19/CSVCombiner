/**
 * @author Greg Moua
 * @date 1/17/2023
 * PMG Coding Challenge
 */

import java.util.*;
import java.io.*;

public class combiner {
    public static void main(String[] args) throws IOException {
        run(args);
    }

    /**
     * 
     * @param args command line input 
     * @throws IOException
     */
    protected static void run(String[] args) throws IOException{
        String fileName = "";
        String hash_email = "";
        String category = "";
        String [] parts = new String [2];
        File counterFile = new File("timesRun.txt");
        if(!counterFile.exists()) {
            counterFile.createNewFile();
        }
        
        FileReader fileReader = new FileReader(counterFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Scanner scanner;

        int timesRun = bufferedReader.read() + 1;
        fileReader.close();
        FileWriter fileWriter = new FileWriter(counterFile, false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        fileWriter.write(timesRun);
        bufferedWriter.close();

        fileWriter = new FileWriter("combined" + timesRun + ".csv", true);
        bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("email_hash,category,filename\n");
        bufferedWriter.flush();
        
        for(int i = 0 ; i < args.length;i++) {


            fileName = findPath(args[i]);
            try {

                scanner = new Scanner(new File(args[i])).useDelimiter(",");
                scanner.nextLine();

                while(scanner.hasNextLine()) {
                    try {
                    String total = scanner.nextLine();
                    parts = total.split(",");
                    hash_email = parts[0];
                    category = parts[1];

                    bufferedWriter.write(hash_email + "," + category + "," + fileName + "\n");
                    } catch(ArrayIndexOutOfBoundsException e) {
                        System.out.println(fileName + " has incorrect formatting");
                        break;
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println(fileName + " cannot be found!");
                continue;
            } catch (Exception e) {
                System.out.println(e.toString());
                System.out.println("Error with " + fileName + " please make sure it is formatted correctly!");
                continue;
            }
        }
        bufferedWriter.close();
    }

    /**
     * 
     * @param string the potential full file path
     * @return filename
     */
    protected static String findPath(String string) {
        String path = "";
        int index = string.length()-1;
        while(index > 0 && (string.charAt(index) != '/' && string.charAt(index) != '\\')) {
            path = String.valueOf(string.charAt(index)) + path;
            index--;
        }
        return path;
    }
}
