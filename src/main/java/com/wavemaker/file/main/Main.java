package com.wavemaker.file.main;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.wavemaker.file.pojo.CharacterSearchResult;
import com.wavemaker.file.pojo.StringSearchResult;
import com.wavemaker.file.pojo.WordSearchResult;
import com.wavemaker.file.service.FileMethodsService;
import com.wavemaker.file.service.impl.FileMethodServiceImpl;

public class Main {
    private static FileMethodsService fileMethodsService = new FileMethodServiceImpl();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nFile Search Utility");
            System.out.println("1. Read File");
            System.out.println("2.  Write File");
            System.out.println("3. Search a given Character in a file and get Character details in file");
            System.out.println("4. Search a given Word in a file and get the Word details in file");
            System.out.println("5. Search a given String in a file and get the String details in file");
            System.out.println("6. string Search in all files in directory and sub directories");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();


            switch (choice) {
                case 1:
                    System.out.println(fileMethodsService.readInFile());
                    break;
                case 2:
                    System.out.println("enter text to write in file");
                    String text = " " + scanner.nextLine();
                    System.out.println(fileMethodsService.writeInFile(text));

                    break;

                case 3:
                    System.out.println("enter character to search in file");
                    char charSearch = scanner.nextLine().charAt(0);
                    List<CharacterSearchResult> listOfCharacterSearch = fileMethodsService.getCharacterSearchDetailsInFile(charSearch);
                    for (CharacterSearchResult characterSearchResult : listOfCharacterSearch) {
                        System.out.println(characterSearchResult);
                    }
                    System.out.println("total occurences of " + charSearch + " in file is = " + listOfCharacterSearch.size());
                    break;
                case 4:
                    System.out.println("enter word  to search in file");
                    String wordSearch = scanner.nextLine();
                    List<WordSearchResult> listOfWordSearchResult = fileMethodsService.getWordSearchDetailsInFile(wordSearch);
                    Collections.sort(listOfWordSearchResult);
                    for (WordSearchResult wordSearchResult : listOfWordSearchResult) {
                        System.out.println(wordSearchResult);
                    }
                    System.out.println("total occurences of " + wordSearch + " in file is = " + listOfWordSearchResult.size());
                    break;

                case 5:
                    System.out.println("enter string to search in file");
                    String stringSearch = scanner.nextLine();
                    List<StringSearchResult> listOfStringSearchResult = fileMethodsService.getStringSearchDetailsInFile(stringSearch);
                    for (StringSearchResult stringSearchResult : listOfStringSearchResult) {
                        System.out.println(stringSearchResult);
                    }
                    System.out.println("total occurences of " + stringSearch + " in file is = " + listOfStringSearchResult.size());
                    break;
                case 6:
                    String directoryPath = "/home/roopaa_500382/Downloads/FileSearchUtility/directory";
                    Path path = Paths.get(directoryPath);
                    if (Files.exists(path) && Files.isDirectory(path)) {
                        System.out.println("The directory exists: " + directoryPath);
                        System.out.println("enter string  to search in all files present in directories");
                        stringSearch = scanner.nextLine();
                        List<WordSearchResult> listOfWordSearchResultInAllFiles = fileMethodsService.getWordSearchDetailsInAllFilesInDirectoryAndInAllSubDirectory(stringSearch, directoryPath);
                        Collections.sort(listOfWordSearchResultInAllFiles);
                        for (WordSearchResult wordSearchResult : listOfWordSearchResultInAllFiles) {
                            System.out.println(wordSearchResult);
                        }
                        System.out.println("total occurences of " + stringSearch + " in file is = " + listOfWordSearchResultInAllFiles.size());

                    } else {
                        System.out.println("The directory does not exist: " + directoryPath);
                    }
                    break;

                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }


    }
}
