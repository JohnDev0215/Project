package com.byd.bms.a;

import com.byd.data.common.entity.bms.QueryResult;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: IBmsPresent.java */
/* JADX INFO: loaded from: classes.dex */
public interface a extends com.byd.data.common.c.a<QueryResult> {
    boolean B();

    void D(byte[] bArr, boolean z);

    ByteBuffer a(int i2, ByteBuffer byteBuffer);

    void g(short s);

    boolean h(byte b2);

    void m(int i2, int i3, int i4);

    boolean n();

    void o(int i2, boolean z);

    int r(int i2);

    ByteBuffer s(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3);

    ByteBuffer t(int i2, ByteBuffer byteBuffer);

    ByteBuffer u();

    void w(byte b2);

    byte x();

    void y();

    int z();
}
