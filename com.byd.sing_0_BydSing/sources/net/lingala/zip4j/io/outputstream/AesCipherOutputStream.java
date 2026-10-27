package net.lingala.zip4j.io.outputstream;

import java.io.IOException;
import java.io.OutputStream;
import net.lingala.zip4j.crypto.AESEncrypter;
import net.lingala.zip4j.model.ZipParameters;

/* JADX INFO: loaded from: classes.dex */
public class AesCipherOutputStream extends CipherOutputStream<AESEncrypter> {
    private byte[] pendingBuffer;
    private int pendingBufferLength;

    public AesCipherOutputStream(ZipEntryOutputStream zipEntryOutputStream, ZipParameters zipParameters, char[] cArr, boolean z) {
        super(zipEntryOutputStream, zipParameters, cArr, z);
        this.pendingBuffer = new byte[16];
        this.pendingBufferLength = 0;
    }

    private void writeAesEncryptionHeaderData(AESEncrypter aESEncrypter) throws IOException {
        writeHeaders(aESEncrypter.getSaltBytes());
        writeHeaders(aESEncrypter.getDerivedPasswordVerifier());
    }

    @Override // net.lingala.zip4j.io.outputstream.CipherOutputStream
    public void closeEntry() throws IOException {
        int i = this.pendingBufferLength;
        if (i != 0) {
            super.write(this.pendingBuffer, 0, i);
            this.pendingBufferLength = 0;
        }
        writeHeaders(getEncrypter().getFinalMac());
        super.closeEntry();
    }

    @Override // net.lingala.zip4j.io.outputstream.CipherOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        write(new byte[]{(byte) i});
    }

    @Override // net.lingala.zip4j.io.outputstream.CipherOutputStream
    public AESEncrypter initializeEncrypter(OutputStream outputStream, ZipParameters zipParameters, char[] cArr, boolean z) throws IOException {
        AESEncrypter aESEncrypter = new AESEncrypter(cArr, zipParameters.getAesKeyStrength(), z);
        writeAesEncryptionHeaderData(aESEncrypter);
        return aESEncrypter;
    }

    @Override // net.lingala.zip4j.io.outputstream.CipherOutputStream, java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // net.lingala.zip4j.io.outputstream.CipherOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        int i4 = this.pendingBufferLength;
        int i5 = 16 - i4;
        if (i2 >= i5) {
            System.arraycopy(bArr, i, this.pendingBuffer, i4, i5);
            byte[] bArr2 = this.pendingBuffer;
            super.write(bArr2, 0, bArr2.length);
            int i6 = 16 - this.pendingBufferLength;
            int i7 = i2 - i6;
            this.pendingBufferLength = 0;
            if (i7 != 0 && (i3 = i7 % 16) != 0) {
                System.arraycopy(bArr, (i7 + i6) - i3, this.pendingBuffer, 0, i3);
                this.pendingBufferLength = i3;
                i7 -= i3;
            }
            super.write(bArr, i6, i7);
            return;
        }
        System.arraycopy(bArr, i, this.pendingBuffer, i4, i2);
        this.pendingBufferLength += i2;
    }
}
