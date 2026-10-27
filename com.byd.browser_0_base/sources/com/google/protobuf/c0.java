package com.google.protobuf;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: compiled from: Internal.java */
/* JADX INFO: loaded from: classes.dex */
public final class c0 {
    public static final Charset a;
    public static final Charset b;
    public static final byte[] c;

    /* JADX INFO: compiled from: Internal.java */
    public interface a {
        int b();
    }

    /* JADX INFO: compiled from: Internal.java */
    public interface b {
        boolean a(int i);
    }

    /* JADX INFO: compiled from: Internal.java */
    public interface c extends e<Integer> {
    }

    /* JADX INFO: compiled from: Internal.java */
    public interface d extends e<Long> {
    }

    /* JADX INFO: compiled from: Internal.java */
    public interface e<E> extends List<E>, RandomAccess {
        void e();

        e<E> h(int i);

        boolean o();
    }

    static {
        Charset.forName("US-ASCII");
        a = Charset.forName("UTF-8");
        b = Charset.forName("ISO-8859-1");
        byte[] bArr = new byte[0];
        c = bArr;
        ByteBuffer.wrap(bArr);
        g.h(bArr, 0, 0, false);
    }

    public static int a(boolean z) {
        return z ? 1231 : 1237;
    }

    public static int b(long j) {
        return (int) (j ^ (j >>> 32));
    }

    public static r0 c(Object obj, Object obj2) {
        return ((r0) obj).h().G((r0) obj2).d();
    }
}
