package com.byd.engineermode.ui.functiontest.logs;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: compiled from: FileIOHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class a {
    public static void a(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
