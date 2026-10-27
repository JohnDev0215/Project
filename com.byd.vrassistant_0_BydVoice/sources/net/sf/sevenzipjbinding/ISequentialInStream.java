package net.sf.sevenzipjbinding;

import java.io.Closeable;

/* JADX INFO: loaded from: classes3.dex */
public interface ISequentialInStream extends Closeable {
    int read(byte[] bArr) throws SevenZipException;
}
