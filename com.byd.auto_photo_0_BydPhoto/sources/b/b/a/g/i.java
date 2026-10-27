package b.b.a.g;

import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class i implements Closeable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f623a = 1179403647;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final FileChannel f624b;

    public i(File file) {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("File is null or does not exist");
        }
        this.f624b = new FileInputStream(file).getChannel();
    }

    private long j(d dVar, long j, long j2) {
        for (long j3 = 0; j3 < j; j3++) {
            e eVarB = dVar.b(j3);
            if (eVarB.f619a == 1) {
                long j4 = eVarB.f621c;
                if (j4 <= j2 && j2 <= eVarB.d + j4) {
                    return (j2 - j4) + eVarB.f620b;
                }
            }
        }
        throw new IllegalStateException("Could not map vma to file offset!");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f624b.close();
    }

    public d k() throws IOException {
        this.f624b.position(0L);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        if (r(byteBufferAllocate, 0L) != 1179403647) {
            throw new IllegalArgumentException("Invalid ELF Magic!");
        }
        short sN = n(byteBufferAllocate, 4L);
        boolean z = n(byteBufferAllocate, 5L) == 2;
        if (sN == 1) {
            return new g(z, this);
        }
        if (sN == 2) {
            return new h(z, this);
        }
        throw new IllegalStateException("Invalid class type!");
    }

    public List<String> l() throws IOException {
        long j;
        c cVarA;
        this.f624b.position(0L);
        ArrayList arrayList = new ArrayList();
        d dVarK = k();
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(dVarK.f616a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = dVarK.f;
        int i = 0;
        if (j2 == 65535) {
            j2 = dVarK.c(0).f622a;
        }
        long j3 = 0;
        while (true) {
            if (j3 >= j2) {
                j = 0;
                break;
            }
            e eVarB = dVarK.b(j3);
            if (eVarB.f619a == 2) {
                j = eVarB.f620b;
                break;
            }
            j3++;
        }
        if (j == 0) {
            return Collections.unmodifiableList(arrayList);
        }
        ArrayList arrayList2 = new ArrayList();
        long j4 = 0;
        do {
            cVarA = dVarK.a(j, i);
            long j5 = cVarA.f614a;
            if (j5 == 1) {
                arrayList2.add(Long.valueOf(cVarA.f615b));
            } else if (j5 == 5) {
                j4 = cVarA.f615b;
            }
            i++;
        } while (cVarA.f614a != 0);
        if (j4 == 0) {
            throw new IllegalStateException("String table offset not found!");
        }
        long j6 = j(dVarK, j2, j4);
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList.add(q(byteBufferAllocate, ((Long) it.next()).longValue() + j6));
        }
        return arrayList;
    }

    protected void m(ByteBuffer byteBuffer, long j, int i) throws IOException {
        byteBuffer.position(0);
        byteBuffer.limit(i);
        long j2 = 0;
        while (j2 < i) {
            int i2 = this.f624b.read(byteBuffer, j + j2);
            if (i2 == -1) {
                throw new EOFException();
            }
            j2 += (long) i2;
        }
        byteBuffer.position(0);
    }

    protected short n(ByteBuffer byteBuffer, long j) throws IOException {
        m(byteBuffer, j, 1);
        return (short) (byteBuffer.get() & 255);
    }

    protected int o(ByteBuffer byteBuffer, long j) throws IOException {
        m(byteBuffer, j, 2);
        return byteBuffer.getShort() & 65535;
    }

    protected long p(ByteBuffer byteBuffer, long j) throws IOException {
        m(byteBuffer, j, 8);
        return byteBuffer.getLong();
    }

    protected String q(ByteBuffer byteBuffer, long j) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            long j2 = 1 + j;
            short sN = n(byteBuffer, j);
            if (sN == 0) {
                return sb.toString();
            }
            sb.append((char) sN);
            j = j2;
        }
    }

    protected long r(ByteBuffer byteBuffer, long j) throws IOException {
        m(byteBuffer, j, 4);
        return ((long) byteBuffer.getInt()) & 4294967295L;
    }
}
