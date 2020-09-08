import java.io.*;
import java.util.*;

public class task27 {

    static File file = null;
    //defining a file variable for further uses

    static BufferedReader reader = null;
    //defining a BufferedReader variable for further uses

    static ArrayList<String> QuestionSheet = new ArrayList<>();
    static ArrayList<String> AnswerSheet = new ArrayList<>();
    //the storage of questions and answers once they are read off from the document


    private static String PushQuestion (int QuestionNumber) {

        return QuestionSheet.get(QuestionNumber);
        //returning the question
        //the answer will be stored on the next line of the sentence

    } //Give the question out as a string

    private static boolean TakeAnswer (int QuestionNumber, String GivenAnswer) {

        if (GivenAnswer.equals(AnswerSheet.get(QuestionNumber))) {
            return true; //return true if the answer is exact same as it is listed since it is mcq
        } else {
            return false;
        }

    } //TakeAnswer processes the user inputted answer and give the result to main

    public static void main (String args[]) {

        ToUpperCase a = new ToUpperCase();
        //turn letters to upper case

        String FileDirectory = "/Volumes/SHARE/ComputerScience/CompSciJavaChallenge/task27/resource";
        //the directory that stores the question
    
        try {
            file = new File(FileDirectory + "/task.txt");
            reader = new BufferedReader(new FileReader(file));
            //get the file hooked up to BufferedReader
            //the file and reader variable is shared among the entire class
            //creating a new file or claiming a file for use
            //under the folder where the program is stored
            if (file.exists() == false) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
            //check if the file pre-exists or it was created
            for(int i = 0; i < 3; i++) {
                QuestionSheet.add(reader.readLine());
                AnswerSheet.add(reader.readLine());
            }//getting the questions and answers
        } catch (IOException e) {
            System.out.println("an error occured");
            e.printStackTrace();
            //in case error occurs
        }

        

        String GivenAnswer = "";
        //a string to store the answer given by the user

        boolean[] checklist = {false, false, false};
        //a boolean array that store if the answer is true or false

        int TotalScore = 0;
        //this variable stores the score that the player has scored
        
        int MaxScore = 0;
        //the highest score across all users

        Scanner screen = new Scanner(System.in);
        //the scanner that is used to take user input

        for (int QuestionNumber = 0; QuestionNumber < 3; QuestionNumber++ ) {

            System.out.println("Your " + ( QuestionNumber + 1 ) + " question is: " + PushQuestion(QuestionNumber));
            //print the question that is pulled from the document

            GivenAnswer = screen.nextLine();
            GivenAnswer = a.action(GivenAnswer);
            //get the user's answer
            
            checklist[QuestionNumber] = TakeAnswer(QuestionNumber, GivenAnswer);
            System.out.println(checklist[QuestionNumber]);
            //print out && store the answer in the array

        }

        try {
            File content = new File(FileDirectory + "/checklist.txt");
            //creating a new file or claiming a file for use
            //under the folder where the program is stored
            if (content.exists() == false) {
                content.createNewFile();
                System.out.println("File created: " + content.getName());
            } else {
                System.out.println("File already exists.");
            }
            //check if the file pre-exists or it was created
            FileWriter WritingFiles = new FileWriter(content, true);
            Scanner ScoreReader = new Scanner(content);
            //a scanner to read the file
            while(ScoreReader.hasNextLine()) {
                try {
                    MaxScore = Integer.parseInt(ScoreReader.nextLine());
                } catch (Exception e) {
                    continue;
                }
            }
            for (int QuestionNumber = 0; QuestionNumber < 3; QuestionNumber++) {
                if (String.valueOf(checklist[QuestionNumber]).equals("true")) {
                    TotalScore += 1;
                }
                WritingFiles.write(String.valueOf(checklist[QuestionNumber]) + " ");
            } // write every element of checklist to file as string
            WritingFiles.write(TotalScore);
            //print total score at last on the same line
            if (TotalScore > MaxScore) {
                WritingFiles.write("\n" + TotalScore + "\n");
                System.out.println("You owns the highest score for the test");
            } else if (TotalScore == MaxScore) {
                WritingFiles.write("\n" + MaxScore + "\n");
                System.out.println("Someone has been here before you");
            } else {
                WritingFiles.write("\n" + MaxScore + "\n");

                System.out.println("Someone has done better than this");
            }
            //to see if a new record is set
            WritingFiles.close();
            //writing the content into the file
        } catch (IOException e) {
            System.out.println("an error occured");
            e.printStackTrace();
            //in case error occurs
        }

        screen.close();
        //closing the scanner

    }
}

