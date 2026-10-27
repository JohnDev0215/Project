package org.bouncycastle.est;

import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface Source<T> {
    void close();

    InputStream getInputStream();

    OutputStream getOutputStream();

    T getSession();
}
