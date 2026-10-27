package cn.hutool.core.io.h;

import c.a.a.c.d;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.g;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: compiled from: StreamCopier.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends a<InputStream, OutputStream> {
    public b(int i, long j, g gVar) {
        super(i, j, gVar);
    }

    private long c(InputStream inputStream, OutputStream outputStream, byte[] bArr, g gVar) throws IOException {
        int i;
        long j = this.f2929b;
        if (j <= 0) {
            j = Long.MAX_VALUE;
        }
        long j2 = 0;
        while (j > 0 && (i = inputStream.read(bArr, 0, a(j))) >= 0) {
            outputStream.write(bArr, 0, i);
            if (this.f2931d) {
                outputStream.flush();
            }
            long j3 = i;
            j -= j3;
            j2 += j3;
            if (gVar != null) {
                gVar.a(this.f2929b, j2);
            }
        }
        return j2;
    }

    public long b(InputStream inputStream, OutputStream outputStream) {
        d.j(inputStream, "InputStream is null !", new Object[0]);
        d.j(outputStream, "OutputStream is null !", new Object[0]);
        g gVar = this.f2930c;
        if (gVar != null) {
            gVar.start();
        }
        try {
            long jC = c(inputStream, outputStream, new byte[a(this.f2929b)], gVar);
            outputStream.flush();
            if (gVar != null) {
                gVar.b();
            }
            return jC;
        } catch (IOException e2) {
            throw new IORuntimeException(e2);
        }
    }
}
