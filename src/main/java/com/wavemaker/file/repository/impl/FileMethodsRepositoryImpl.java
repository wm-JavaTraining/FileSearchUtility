package com.wavemaker.file.repository.impl;

import com.wavemaker.file.exception.FileReadException;
import com.wavemaker.file.exception.FileWriteException;
import com.wavemaker.file.pojo.CharacterSearchResult;
import com.wavemaker.file.pojo.StringSearchResult;
import com.wavemaker.file.pojo.WordSearchResult;
import com.wavemaker.file.repository.FileMethodsRepository;
import com.wavemaker.file.util.FileCreateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileMethodsRepositoryImpl implements FileMethodsRepository {
    private static final String FILE_PATH = "example.txt";
    private static File file;
    private static final Logger logger = LoggerFactory.getLogger(FileMethodsRepositoryImpl.class);

    public FileMethodsRepositoryImpl() {
        file = FileCreateUtil.createFileIfNotExists(FILE_PATH);
    }

    @Override
    public boolean readInFile() {
        BufferedReader bufferedReader = null;
        boolean isRead = false;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);

            }
            isRead = true;

        } catch (IOException e) {

            throw new FileReadException("Error reading details from file", 500);
        } finally {
            closeBufferedReader(bufferedReader);
        }
        return isRead;

    }

    @Override
    public boolean writeInFile(String text) {
        boolean isWrite = false;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            bufferedWriter.write(text);
            isWrite = true;

        } catch (IOException e) {

            throw new FileWriteException("Error writing details from file", 500);
        } finally {
            closeBufferedWriter(bufferedWriter);
        }
        return isWrite;
    }

    @Override
    public List<CharacterSearchResult> getCharacterSearchDetailsInFile(char charSearch) {
        CharacterSearchResult characterSearchResult = null;
        List<CharacterSearchResult> listOfcharacterSearchResult = new ArrayList<>();
        BufferedReader bufferedReader = null;
        int currentLineNumber = 0;
        int position = 0;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                currentLineNumber++;
                position = 0;
                for (char ch : line.toCharArray()) {
                    position++;
                    if (ch == charSearch) {
                        characterSearchResult = new CharacterSearchResult();
                        characterSearchResult.setCharToSearch(charSearch);
                        characterSearchResult.setFileName(file.getName());
                        characterSearchResult.setFilePath(file.getPath());
                        characterSearchResult.setLineNumber(currentLineNumber);
                        characterSearchResult.setLinePosition(position);
                        listOfcharacterSearchResult.add(characterSearchResult);
                    }
                }

            }
        } catch (IOException e) {
            throw new FileReadException("Error reading details from file", 500);
        } finally {
            closeBufferedReader(bufferedReader);
        }

        return listOfcharacterSearchResult;
    }

    @Override
    public List<WordSearchResult> getWordSearchDetailsInFile(String wordSearch) {
        List<WordSearchResult> listOfWordSearchResult = new ArrayList<>();
        if (wordSearch.isEmpty()) {
            return listOfWordSearchResult;
        }
        getWordDetailsInFile(file, wordSearch, listOfWordSearchResult);
        return listOfWordSearchResult;
    }

    @Override
    public List<StringSearchResult> getStringSearchDetailsInFile(String stringSearch) {
        StringSearchResult stringSearchResult = null;
        List<StringSearchResult> listOfStringSearchResult = new ArrayList<>();
        int currentLineNumber = 0;
        BufferedReader bufferedReader = null;
        if (stringSearch.isEmpty()) {
            return listOfStringSearchResult;
        }
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                currentLineNumber++;
                int index = 0;
                String lineContent = line.toLowerCase();
                String searchString = stringSearch.toLowerCase();
                while ((index = lineContent.indexOf(searchString, index)) != -1) {
                    stringSearchResult = new StringSearchResult();
                    stringSearchResult.setStringName(stringSearch);
                    stringSearchResult.setFileName(file.getName());
                    stringSearchResult.setFilePath(file.getPath());
                    stringSearchResult.setLineNumber(currentLineNumber);
                    stringSearchResult.setLinePosition(index + 1);
                    listOfStringSearchResult.add(stringSearchResult);
                    index++;

                }
            }
        } catch (IOException e) {
            throw new FileReadException("Error reading details from file", 500);
        } finally {
            closeBufferedReader(bufferedReader);
        }
        return listOfStringSearchResult;
    }

    @Override
    public List<WordSearchResult> getWordSearchDetailsInAllFilesInDirectoryAndInAllSubDirectory(String wordSearch, String directoryPath) {
        List<WordSearchResult> listOfWordSearchResult = new ArrayList<>();
        try {
            searchWordInAllFilesAndDirectory(Paths.get(directoryPath), wordSearch, listOfWordSearchResult);
        } catch (IOException e) {
            System.err.println("Error reading directory: " + e.getMessage());
        }
        return listOfWordSearchResult;

    }

    private void searchWordInAllFilesAndDirectory(Path directory, String wordSearch, List<WordSearchResult> listOfWordSearchResult) throws IOException {
        try {
            for (Path path : Files.walk(directory).toList()) {
                if (Files.isRegularFile(path)) {
                    getWordDetailsInFile(new File(String.valueOf(path)), wordSearch, listOfWordSearchResult);
                }

            }
        } catch (IOException e) {
            System.err.println("Error reading directory: " + e.getMessage());
        }

    }

    private void getWordDetailsInFile(File file, String wordSearch, List<WordSearchResult> listOfWordSearchResult) {
        int currentLineNumber = 0;
        int position = 0;
        BufferedReader bufferedReader = null;
        WordSearchResult wordSearchResult = null;
        if (wordSearch.isEmpty()) {
            return;
        }
        try {
            bufferedReader = new BufferedReader(new FileReader(String.valueOf(file)));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                currentLineNumber++;
                position = 0;
                String lineContent = line.toLowerCase();
                String searchWord = wordSearch.toLowerCase();
                while ((position = lineContent.indexOf(searchWord, position)) != -1) {
                    wordSearchResult = new WordSearchResult();
                    wordSearchResult.setWord(wordSearch);
                    wordSearchResult.setFileName(file.getName());
                    wordSearchResult.setFilePath(file.getPath());
                    wordSearchResult.setLineNumber(currentLineNumber);
                    wordSearchResult.setLinePosition(position + 1);
                    listOfWordSearchResult.add(wordSearchResult);
                    position++;

                }
            }
        } catch (IOException e) {
            throw new FileReadException("Error reading details from file", 500);
        } finally {
            closeBufferedReader(bufferedReader);
        }

    }

    private void closeBufferedReader(BufferedReader reader) {
        if (reader != null) {
            try {
                reader.close();
                logger.debug("BufferedReader closed successfully");
            } catch (IOException e) {
                logger.error("Failed to close BufferedReader", e);
            }
        }
    }

    private void closeBufferedWriter(BufferedWriter writer) {
        if (writer != null) {
            try {
                writer.close();
                logger.debug("BufferedWriter closed successfully");
            } catch (IOException e) {
                logger.error("Failed to close BufferedWriter", e);
            }
        }
    }
}
