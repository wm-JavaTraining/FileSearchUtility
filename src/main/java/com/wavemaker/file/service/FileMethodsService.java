package com.wavemaker.file.service;

import com.wavemaker.file.pojo.CharacterSearchResult;
import com.wavemaker.file.pojo.StringSearchResult;
import com.wavemaker.file.pojo.WordSearchResult;

import java.util.List;

public interface FileMethodsService {
    public boolean readInFile();

    public boolean writeInFile(String text);

    public List<CharacterSearchResult> getCharacterSearchDetailsInFile(char charSearch);

    public List<WordSearchResult> getWordSearchDetailsInFile(String wordSearch);

    public List<StringSearchResult> getStringSearchDetailsInFile(String stringSearch);

    public List<WordSearchResult> getWordSearchDetailsInAllFilesInDirectoryAndInAllSubDirectory(String wordSearch, String directoryPath);


}
