package com.amazon.device.ads;

import java.io.File;

/* JADX INFO: compiled from: FileHandlerFactory.java */
/* JADX INFO: loaded from: classes2.dex */
class DefaultFileHandlerFactory implements FileHandlerFactory {
    DefaultFileHandlerFactory() {
    }

    @Override // com.amazon.device.ads.FileHandlerFactory
    public FileInputHandler createFileInputHandler(File file, String str) {
        FileInputHandler fileInputHandler = new FileInputHandler();
        fileInputHandler.setFile(file, str);
        return fileInputHandler;
    }

    @Override // com.amazon.device.ads.FileHandlerFactory
    public FileOutputHandler createFileOutputHandler(File file, String str) {
        FileOutputHandler fileOutputHandler = new FileOutputHandler();
        fileOutputHandler.setFile(file, str);
        return fileOutputHandler;
    }
}
