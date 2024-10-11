package com.wavemaker.file.pojo;

import java.util.Objects;

public class StringSearchResult {
    private String stringName;
    private String fileName;
    private String filePath;
    private int lineNumber;
    private int linePosition;

    public String getStringName() {
        return stringName;
    }

    public void setStringName(String stringName) {
        this.stringName = stringName;
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
        StringSearchResult that = (StringSearchResult) o;
        return lineNumber == that.lineNumber && linePosition == that.linePosition && Objects.equals(stringName, that.stringName) && Objects.equals(fileName, that.fileName) && Objects.equals(filePath, that.filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stringName, fileName, filePath, lineNumber, linePosition);
    }

    @Override
    public String toString() {
        return "StringSearchResult{" +
                "stringName='" + stringName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", lineNumber=" + lineNumber +
                ", linePosition=" + linePosition +
                '}';
    }
}
