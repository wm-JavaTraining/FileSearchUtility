package com.wavemaker.file.factory;

import com.wavemaker.file.repository.FileMethodsRepository;
import com.wavemaker.file.repository.impl.FileMethodsRepositoryImpl;

public class FileRepositoryGlobalInstance {
    private static FileMethodsRepository fileMethodsRepository = null;

    public static  FileMethodsRepository getFileRepositoryGlobalInstance(){
        if (fileMethodsRepository == null) {
            synchronized (FileRepositoryGlobalInstance.class) {
                if (fileMethodsRepository == null) {
                    fileMethodsRepository = new FileMethodsRepositoryImpl();
                }
            }
        }
        return fileMethodsRepository;
    }
}
