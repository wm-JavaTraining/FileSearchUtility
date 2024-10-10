package com.wavemaker.file.pojo;

import java.util.Objects;

public class WordSearchResult implements Comparable<WordSearchResult> {
    String word;
    String fileName;
    String filePath;
    int lineNumber;
    int  linePosition;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
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
        WordSearchResult that = (WordSearchResult) o;
        return lineNumber == that.lineNumber && linePosition == that.linePosition && Objects.equals(word, that.word) && Objects.equals(fileName, that.fileName) && Objects.equals(filePath, that.filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, fileName, filePath, lineNumber, linePosition);
    }

    @Override
    public String toString() {
        return "WordSearchResult{" +
                "word='" + word + '\'' +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", lineNumber=" + lineNumber +
                ", linePosition=" + linePosition +
                '}';
    }



    @Override
    public int compareTo(WordSearchResult obj) {
        return this.fileName.compareToIgnoreCase(obj.fileName);
    }
}
