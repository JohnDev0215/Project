package io.sentry;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.Reader;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public interface T {
    Object a(Reader reader, Class cls);

    String b(ConcurrentHashMap concurrentHashMap);

    C1489n1 c(BufferedInputStream bufferedInputStream);

    Object d(BufferedReader bufferedReader, Class cls, C1454c c1454c);

    void e(C1489n1 c1489n1, OutputStream outputStream);

    void f(Object obj, BufferedWriter bufferedWriter);
}
