package org.extra.relinker.a;

import androidx.exifinterface.media.ExifInterface;
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
import org.extra.relinker.a.c;

/* JADX INFO: loaded from: classes3.dex */
public class f implements Closeable, c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f3974a = 1179403647;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final FileChannel f3975b;

    public f(File file) {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("File is null or does not exist");
        }
        this.f3975b = new FileInputStream(file).getChannel();
    }

    public c.b a() throws IOException {
        this.f3975b.position(0L);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        if (e(byteBufferAllocate, 0L) != 1179403647) {
            throw new IllegalArgumentException("Invalid ELF Magic!");
        }
        short sA = a(byteBufferAllocate, 4L);
        boolean z = a(byteBufferAllocate, 5L) == 2;
        if (sA == 1) {
            return new d(z, this);
        }
        if (sA == 2) {
            return new e(z, this);
        }
        throw new IllegalStateException("Invalid class type!");
    }

    public List<String> b() throws IOException {
        long j;
        c.a aVarA;
        this.f3975b.position(0L);
        ArrayList arrayList = new ArrayList();
        c.b bVarA = a();
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(bVarA.f3963a ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = bVarA.f3968f;
        int i = 0;
        if (j2 == 65535) {
            j2 = bVarA.a(0).f3973a;
        }
        long j3 = 0;
        while (true) {
            if (j3 >= j2) {
                j = 0;
                break;
            }
            c.AbstractC0107c abstractC0107cA = bVarA.a(j3);
            if (abstractC0107cA.f3969a == 2) {
                j = abstractC0107cA.f3970b;
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
            aVarA = bVarA.a(j, i);
            long j5 = aVarA.f3961a;
            if (j5 == 1) {
                arrayList2.add(Long.valueOf(aVarA.f3962b));
            } else if (j5 == 5) {
                j4 = aVarA.f3962b;
            }
            i++;
        } while (aVarA.f3961a != 0);
        if (j4 == 0) {
            throw new IllegalStateException("String table offset not found!");
        }
        long jA = a(bVarA, j2, j4);
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList.add(d(byteBufferAllocate, ((Long) it.next()).longValue() + jA));
        }
        return arrayList;
    }

    public long c(ByteBuffer byteBuffer, long j) throws IOException {
        a(byteBuffer, j, 8);
        return byteBuffer.getLong();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f3975b.close();
    }

    public String d(ByteBuffer byteBuffer, long j) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            long j2 = 1 + j;
            short sA = a(byteBuffer, j);
            if (sA == 0) {
                return sb.toString();
            }
            sb.append((char) sA);
            j = j2;
        }
    }

    public long e(ByteBuffer byteBuffer, long j) throws IOException {
        a(byteBuffer, j, 4);
        return ((long) byteBuffer.getInt()) & 4294967295L;
    }

    private long a(c.b bVar, long j, long j2) {
        for (long j3 = 0; j3 < j; j3++) {
            c.AbstractC0107c abstractC0107cA = bVar.a(j3);
            if (abstractC0107cA.f3969a == 1) {
                long j4 = abstractC0107cA.f3971c;
                if (j4 <= j2 && j2 <= abstractC0107cA.f3972d + j4) {
                    return (j2 - j4) + abstractC0107cA.f3970b;
                }
            }
        }
        throw new IllegalStateException("Could not map vma to file offset!");
    }

    public short a(ByteBuffer byteBuffer, long j) throws IOException {
        a(byteBuffer, j, 1);
        return (short) (byteBuffer.get() & ExifInterface.MARKER);
    }

    public void a(ByteBuffer byteBuffer, long j, int i) throws IOException {
        byteBuffer.position(0);
        byteBuffer.limit(i);
        long j2 = 0;
        while (j2 < i) {
            int i2 = this.f3975b.read(byteBuffer, j + j2);
            if (i2 == -1) {
                throw new EOFException();
            }
            j2 += (long) i2;
        }
        byteBuffer.position(0);
    }

    public int b(ByteBuffer byteBuffer, long j) throws IOException {
        a(byteBuffer, j, 2);
        return byteBuffer.getShort() & 65535;
    }
}
