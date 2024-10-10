package com.wavemaker.file.service.impl;

import com.wavemaker.file.factory.FileRepositoryGlobalInstance;
import com.wavemaker.file.pojo.CharacterSearchResult;
import com.wavemaker.file.pojo.StringSearchResult;
import com.wavemaker.file.pojo.WordSearchResult;
import com.wavemaker.file.repository.FileMethodsRepository;
import com.wavemaker.file.service.FileMethodsService;

import java.util.List;

public class FileMethodServiceImpl implements FileMethodsService {
    private FileMethodsRepository fileMethodsRepository;

    public FileMethodServiceImpl() {
        this.fileMethodsRepository = FileRepositoryGlobalInstance.getFileRepositoryGlobalInstance();
    }

    @Override
    public boolean readInFile() {
        return fileMethodsRepository.readInFile();

    }

    @Override
    public boolean writeInFile(String text) {
        return fileMethodsRepository.writeInFile(text);
    }


    @Override
    public List<CharacterSearchResult> getCharacterSearchDetailsInFile(char charSearch) {
        return fileMethodsRepository.getCharacterSearchDetailsInFile(charSearch);
    }

    @Override
    public List<WordSearchResult> getWordSearchDetailsInFile(String wordSearch) {
        return fileMethodsRepository.getWordSearchDetailsInFile(wordSearch);
    }

    @Override
    public List<StringSearchResult> getStringSearchDetailsInFile(String stringSearch) {
        return fileMethodsRepository.getStringSearchDetailsInFile(stringSearch);
    }

    @Override
    public List<WordSearchResult> getWordSearchDetailsInAllFilesInDirectoryAndInAllSubDirectory(String wordSearch, String directoryPath) {
        return fileMethodsRepository.getWordSearchDetailsInAllFilesInDirectoryAndInAllSubDirectory(wordSearch, directoryPath);
    }
}
