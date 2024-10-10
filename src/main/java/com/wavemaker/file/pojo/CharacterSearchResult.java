package com.wavemaker.file.pojo;

import java.util.Objects;

public class CharacterSearchResult {
    char charToSearch;
    String fileName;
    String filePath;
    int lineNumber;
    int  linePosition;

    public char getCharToSearch() {
        return charToSearch;
    }

    public void setCharToSearch(char charToSearch) {
        this.charToSearch = charToSearch;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getLinePosition() {
        return linePosition;
    }

    public void setLinePosition(int linePosition) {
        this.linePosition = linePosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterSearchResult that = (CharacterSearchResult) o;
        return charToSearch == that.charToSearch && lineNumber == that.lineNumber && linePosition == that.linePosition && Objects.equals(fileName, that.fileName) && Objects.equals(filePath, that.filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(charToSearch, fileName, filePath, lineNumber, linePosition);
    }

    @Override
    public String toString() {
        return "CharacterSearchResult{" +
                "charToSearch=" + charToSearch +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", lineNumber=" + lineNumber +
                ", linePosition=" + linePosition +
                '}';
    }
}
