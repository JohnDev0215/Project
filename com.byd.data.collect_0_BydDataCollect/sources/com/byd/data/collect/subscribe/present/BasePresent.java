package com.byd.data.collect.subscribe.present;

import android.content.Context;
import com.byd.data.common.d.a;
import com.byd.data.common.entiry.gson.BaseEntity;
import com.byd.data.common.entiry.gson.DataCollectBean;
import com.byd.data.common.entiry.gson.DataEntity;
import java.lang.Number;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class BasePresent<T extends Number> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final String f3262a = getClass().getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected ConcurrentHashMap<T, ByteBuffer> f3263b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected boolean f3264c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected DataCollectBean f3265d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected volatile int f3266e;

    public BasePresent(DataCollectBean dataCollectBean) {
        this.f3265d = dataCollectBean;
        if (this.f3263b == null && (this instanceof NsPresenter)) {
            this.f3263b = new ConcurrentHashMap<>(d());
        }
        this.f3264c = dataCollectBean.bigEnd;
        m(dataCollectBean);
    }

    protected ByteBuffer b(String str, byte[] bArr, boolean z, a.EnumC0065a enumC0065a) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bArr.length + 20 + 3 + 2);
        byteBufferAllocate.put((byte) 35).put((byte) 35).put(enumC0065a.a()).put(a.h.ORDER.a()).put(str.getBytes(StandardCharsets.UTF_8)).put((z ? a.i.AES_128 : a.i.NOT_ENCRYPT).a()).putShort((short) bArr.length).put(bArr).put(com.byd.data.common.i.d.a(byteBufferAllocate.array(), byteBufferAllocate.capacity() - 1));
        return byteBufferAllocate;
    }

    public void c() {
        this.f3265d = null;
        ConcurrentHashMap<T, ByteBuffer> concurrentHashMap = this.f3263b;
        if (concurrentHashMap == null || concurrentHashMap.size() == 0) {
            return;
        }
        this.f3263b.clear();
        this.f3263b = null;
    }

    protected abstract int d();

    public byte e(byte[] bArr, DataEntity dataEntity) {
        int i2;
        byte b2 = dataEntity.endIndex;
        byte b3 = dataEntity.startIndex;
        byte b4 = 0;
        try {
            if (!(b2 > b3)) {
                int i3 = bArr[b3] & 255;
                byte b5 = dataEntity.bitStart;
                i2 = (i3 >> b5) & ((2 << (dataEntity.bitEnd - b5)) - 1);
            } else if (this.f3264c) {
                int i4 = bArr[b2] & 255;
                byte b6 = dataEntity.bitEnd;
                i2 = (((bArr[b3] & 255) >> dataEntity.bitStart) << (b6 + 1)) | (i4 & ((2 << b6) - 1));
            } else {
                int i5 = bArr[b2] & 255 & (255 >> (7 - dataEntity.bitEnd));
                byte b7 = dataEntity.bitStart;
                i2 = ((bArr[b3] & 255) >> b7) | (i5 << (8 - b7));
            }
            b4 = (byte) i2;
            return b4;
        } catch (Exception e2) {
            e2.printStackTrace();
            return b4;
        }
    }

    public int f(byte[] bArr, DataEntity dataEntity) {
        int i2;
        int i3;
        try {
            if (this.f3264c) {
                byte b2 = dataEntity.endIndex;
                i2 = (((2 << dataEntity.bitEnd) - 1) & bArr[b2]) | ((bArr[b2 - 1] & 255) << 8) | ((bArr[b2 - 2] & 255) << 16);
                i3 = (bArr[dataEntity.startIndex] & 255) >> dataEntity.bitStart;
            } else {
                byte b3 = dataEntity.startIndex;
                i2 = ((bArr[b3] & 255) >> dataEntity.bitStart) | ((bArr[b3 + 1] & 255) << 8) | ((bArr[b3 + 2] & 255) << 16);
                i3 = bArr[dataEntity.endIndex] & (255 >> (7 - dataEntity.bitEnd));
            }
            return (i3 << 24) | i2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public abstract byte[] g(Context context, boolean z, boolean z2);

    public short h(byte[] bArr, DataEntity dataEntity) {
        int i2;
        short s = 0;
        boolean z = Math.abs(dataEntity.endIndex - dataEntity.startIndex) > 1;
        try {
            if (this.f3264c) {
                s = (short) (((bArr[dataEntity.startIndex] & 255) >> dataEntity.bitStart) << 8);
                i2 = bArr[dataEntity.endIndex] & 255 & ((2 << dataEntity.bitEnd) - 1);
            } else {
                byte b2 = dataEntity.startIndex;
                int i3 = bArr[b2] & 255;
                byte b3 = dataEntity.bitStart;
                s = (short) (0 | (i3 >> b3));
                int i4 = (255 >> (7 - dataEntity.bitEnd)) & bArr[dataEntity.endIndex] & 255;
                if (z) {
                    int i5 = 8 - b3;
                    int i6 = bArr[b2 + 1] & 255;
                    s = (short) (s | (((255 >> i5) & i6) << i5));
                    i2 = ((i6 >> b3) | (i4 << i5)) << 8;
                } else {
                    i2 = i4 << 8;
                }
            }
            return (short) (i2 | s);
        } catch (Exception e2) {
            e2.printStackTrace();
            return s;
        }
    }

    protected ByteBuffer i(int i2) {
        if (i2 <= 0) {
            return ByteBuffer.wrap(new byte[0]);
        }
        byte[] bArr = new byte[i2];
        Arrays.fill(bArr, (byte) -1);
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.rewind();
        return byteBufferWrap;
    }

    protected int j(byte[] bArr, int i2, int i3) {
        int i4;
        int i5;
        try {
            if (this.f3264c) {
                i4 = (bArr[i2] & 255) << 8;
                i5 = bArr[i3] & 255;
            } else {
                i4 = bArr[i2] & 255;
                i5 = (bArr[i3] & 255) << 8;
            }
            return i5 | i4;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    protected ByteBuffer k(BaseEntity baseEntity) {
        return i(baseEntity.totalSize).put(baseEntity.type);
    }

    protected ByteBuffer l(BaseEntity baseEntity, int i2) {
        return i(i2).put(baseEntity.type);
    }

    protected abstract void m(DataCollectBean dataCollectBean);

    protected boolean n() {
        return com.byd.data.common.server.b.e().q();
    }

    protected void o() {
        if (!n() || this.f3266e <= 0) {
            return;
        }
        String binaryString = Integer.toBinaryString(this.f3266e);
        StringBuilder sb = new StringBuilder();
        sb.append(this instanceof NsPresenter ? "ns" : "ext");
        sb.append("_warn code =");
        String string = sb.toString();
        if (binaryString.length() <= 4) {
            com.byd.data.common.i.f.g(this.f3262a, string + binaryString);
            return;
        }
        byte length = (byte) (binaryString.length() % 4);
        byte[] bytes = binaryString.getBytes();
        int length2 = binaryString.length() + (binaryString.length() / 4);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(length2 - (length == 0 ? 1 : 0));
        for (int i2 = 0; i2 < bytes.length; i2 += length) {
            if (i2 != 0 || length <= 0) {
                length = 4;
            }
            byteBufferAllocate.put(bytes, i2, length);
            if (byteBufferAllocate.remaining() < 4) {
                com.byd.data.common.i.f.g(this.f3262a, string + new String(byteBufferAllocate.array()));
                return;
            }
            byteBufferAllocate.put((byte) 32);
        }
    }

    public synchronized void p(int i2, boolean z) {
        if (z) {
            this.f3266e = (1 << i2) | this.f3266e;
        } else {
            this.f3266e = (~(1 << i2)) & this.f3266e;
        }
    }

    public void q(int i2, int i3, int i4, a.e eVar) {
        ByteBuffer byteBuffer = this.f3263b.get(Integer.valueOf(eVar.ordinal()));
        if (byteBuffer == null) {
            return;
        }
        if (i3 == 1) {
            byteBuffer.put(i4, (byte) i2);
        } else if (i3 == 2) {
            byteBuffer.putShort(i4, (short) i2);
        } else {
            if (i3 != 4) {
                return;
            }
            byteBuffer.putInt(i4, i2);
        }
    }

    public void r(int i2, DataEntity dataEntity, a.e eVar) {
        q(i2, dataEntity.d(), dataEntity.a(), eVar);
    }

    public void s(byte[] bArr, DataEntity dataEntity, a.e eVar) {
        ByteBuffer byteBuffer = this.f3263b.get(Integer.valueOf(eVar.ordinal()));
        if (byteBuffer == null) {
            return;
        }
        byte bD = dataEntity.d();
        if (bD == 1) {
            byteBuffer.put(dataEntity.a(), e(bArr, dataEntity));
        } else if (bD == 2) {
            byteBuffer.putShort(dataEntity.a(), h(bArr, dataEntity));
        } else {
            if (bD != 4) {
                return;
            }
            byteBuffer.putInt(dataEntity.a(), f(bArr, dataEntity));
        }
    }
}
