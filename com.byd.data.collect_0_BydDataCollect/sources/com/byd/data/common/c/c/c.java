package com.byd.data.common.c.c;

import com.byd.data.common.entiry.cloud.CloudConfig;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: ICloudSaveCallBack.java */
/* JADX INFO: loaded from: classes.dex */
public interface c extends com.byd.data.common.c.a<CloudConfig> {
    void E(short s, byte b2, byte[] bArr);

    String c();

    ByteBuffer e(long j);

    void p(com.byd.data.common.server.e.a aVar, int i2, Byte[] bArr, long j);

    int q();

    ByteBuffer v(int i2, long j);
}
