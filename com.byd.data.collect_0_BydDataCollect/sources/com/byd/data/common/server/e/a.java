package com.byd.data.common.server.e;

import android.content.Context;
import java.io.File;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: IFileOperator.java */
/* JADX INFO: loaded from: classes.dex */
public interface a {
    Context a();

    byte[] b(byte[] bArr, String str);

    void c(ByteBuffer byteBuffer, String str, byte b2, long j, boolean z);

    void d();

    void e(ByteBuffer byteBuffer, byte b2, int i2, boolean z);

    void f(ByteBuffer byteBuffer, int i2, long j, boolean z);

    void g();

    void h(byte[] bArr, boolean z);

    String i();

    byte[] j(byte[] bArr, String str);

    void k(String str, c cVar);

    void l();

    File m(boolean z);

    void n(boolean z, c cVar);
}
