package f;

import java.io.IOException;
import java.io.InputStream;
import okio.ByteString;

/* JADX INFO: compiled from: BufferedSource.java */
/* JADX INFO: loaded from: classes.dex */
public interface e extends q {
    long B(byte b2) throws IOException;

    byte[] C(long j) throws IOException;

    boolean D(long j, ByteString byteString) throws IOException;

    long E() throws IOException;

    InputStream F();

    String G() throws IOException;

    c a();

    boolean b(long j) throws IOException;

    short j() throws IOException;

    ByteString o(long j) throws IOException;

    long p(p pVar) throws IOException;

    byte readByte() throws IOException;

    void readFully(byte[] bArr) throws IOException;

    int readInt() throws IOException;

    short readShort() throws IOException;

    void skip(long j) throws IOException;

    String t() throws IOException;

    void u(long j) throws IOException;

    int x() throws IOException;

    boolean z() throws IOException;
}
