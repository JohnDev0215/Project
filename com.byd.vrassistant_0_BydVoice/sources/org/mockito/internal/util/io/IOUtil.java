package org.mockito.internal.util.io;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.LinkedList;
import org.mockito.exceptions.base.MockitoException;

/* JADX INFO: loaded from: classes4.dex */
public class IOUtil {
    public static void writeText(String str, File file) throws Throwable {
        PrintWriter printWriter = null;
        try {
            try {
                PrintWriter printWriter2 = new PrintWriter(new FileWriter(file));
                try {
                    printWriter2.write(str);
                    close(printWriter2);
                } catch (Exception e) {
                    e = e;
                    printWriter = printWriter2;
                    throw new MockitoException("Problems writing text to file: " + file, e);
                } catch (Throwable th) {
                    th = th;
                    printWriter = printWriter2;
                    close(printWriter);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public static Collection<String> readLines(InputStream inputStream) {
        LinkedList linkedList = new LinkedList();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line == null) {
                    return linkedList;
                }
                linkedList.add(line);
            } catch (IOException e) {
                throw new MockitoException("Problems reading from: " + inputStream, e);
            }
        }
    }

    public static void closeQuietly(Closeable closeable) {
        try {
            close(closeable);
        } catch (MockitoException unused) {
        }
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                throw new MockitoException("Problems closing stream: " + closeable, e);
            }
        }
    }
}
