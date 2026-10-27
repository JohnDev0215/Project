package net.lingala.zip4j.io.inputstream;

import net.lingala.zip4j.crypto.StandardDecrypter;
import net.lingala.zip4j.model.LocalFileHeader;

/* JADX INFO: loaded from: classes.dex */
public class ZipStandardCipherInputStream extends CipherInputStream<StandardDecrypter> {
    public ZipStandardCipherInputStream(ZipEntryInputStream zipEntryInputStream, LocalFileHeader localFileHeader, char[] cArr, int i, boolean z) {
        super(zipEntryInputStream, localFileHeader, cArr, i, z);
    }

    private byte[] getStandardDecrypterHeaderBytes() {
        byte[] bArr = new byte[12];
        readRaw(bArr);
        return bArr;
    }

    @Override // net.lingala.zip4j.io.inputstream.CipherInputStream
    public StandardDecrypter initializeDecrypter(LocalFileHeader localFileHeader, char[] cArr, boolean z) {
        return new StandardDecrypter(cArr, localFileHeader.getCrc(), localFileHeader.getLastModifiedTime(), getStandardDecrypterHeaderBytes(), z);
    }
}
