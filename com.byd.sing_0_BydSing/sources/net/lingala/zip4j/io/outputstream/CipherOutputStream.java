package net.lingala.zip4j.io.outputstream;

import java.io.IOException;
import java.io.OutputStream;
import net.lingala.zip4j.crypto.Encrypter;
import net.lingala.zip4j.model.ZipParameters;

/* JADX INFO: loaded from: classes.dex */
public abstract class CipherOutputStream<T extends Encrypter> extends OutputStream {
    private T encrypter;
    private ZipEntryOutputStream zipEntryOutputStream;

    public CipherOutputStream(ZipEntryOutputStream zipEntryOutputStream, ZipParameters zipParameters, char[] cArr, boolean z) {
        this.zipEntryOutputStream = zipEntryOutputStream;
        this.encrypter = (T) initializeEncrypter(zipEntryOutputStream, zipParameters, cArr, z);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.zipEntryOutputStream.close();
    }

    public void closeEntry() {
        this.zipEntryOutputStream.closeEntry();
    }

    public T getEncrypter() {
        return this.encrypter;
    }

    public long getNumberOfBytesWrittenForThisEntry() {
        return this.zipEntryOutputStream.getNumberOfBytesWrittenForThisEntry();
    }

    public abstract T initializeEncrypter(OutputStream outputStream, ZipParameters zipParameters, char[] cArr, boolean z);

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.zipEntryOutputStream.write(i);
    }

    public void writeHeaders(byte[] bArr) throws IOException {
        this.zipEntryOutputStream.write(bArr);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        this.zipEntryOutputStream.write(bArr);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.encrypter.encryptData(bArr, i, i2);
        this.zipEntryOutputStream.write(bArr, i, i2);
    }
}
