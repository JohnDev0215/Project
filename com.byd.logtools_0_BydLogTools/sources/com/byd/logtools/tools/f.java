package com.byd.logtools.tools;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: compiled from: FileIOHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class f {
    public static void a(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }
}
