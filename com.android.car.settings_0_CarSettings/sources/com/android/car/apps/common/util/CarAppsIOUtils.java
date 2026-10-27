package com.android.car.apps.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes5.dex */
public class CarAppsIOUtils {
    private static final int DEFAULT_BUFFER_SIZE = 4096;

    public static void copy(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[4096];
        while (true) {
            int n = input.read(buffer);
            if (-1 != n) {
                output.write(buffer, 0, n);
            } else {
                return;
            }
        }
    }
}
