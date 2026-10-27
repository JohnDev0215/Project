package com.amazon.device.ads;

import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
interface FileHandlerFactory {
    FileInputHandler createFileInputHandler(File file, String str);

    FileOutputHandler createFileOutputHandler(File file, String str);
}
