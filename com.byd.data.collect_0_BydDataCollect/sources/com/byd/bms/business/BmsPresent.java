package com.byd.bms.business;

import android.util.SparseArray;
import com.byd.bms.a.a;
import com.byd.bms.a.b;
import com.byd.data.common.entiry.gson.DataCollectBean;
import com.byd.data.common.entity.bms.QueryDataBean;
import com.byd.data.common.entity.bms.QueryResult;
import com.byd.data.common.http.ApiRetrofit;
import com.byd.data.common.i.d;
import com.byd.data.common.i.f;
import com.byd.data.common.i.g;
import com.byd.data.common.present.BaseCloudPresent;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class BmsPresent extends BaseCloudPresent<b, QueryResult, QueryDataBean> implements a {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f3124h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private ByteBuffer f3125i;
    private int j;
    private byte k;
    private short l;
    private byte m;
    private byte n;
    private SparseArray<Short> o;
    private volatile short p;
    private short q;
    protected byte r;

    public BmsPresent(b bVar) {
        super(bVar);
        this.f3124h = "single_time";
        this.p = (short) -1;
        X(com.byd.data.common.server.b.e().g());
        int iA = g.a("single_time");
        int iCurrentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        boolean z = iA == -1 || iA < iCurrentTimeMillis;
        this.j = z ? 0 : iA;
        f.g(this.f3438b, z + ",spTime=" + iA + ",hashCode=" + hashCode() + ",curT=" + iCurrentTimeMillis);
    }

    private int W() {
        int i2 = this.m & 255;
        if (i2 < 1 || (this.p & 65535) < 1) {
            return 0;
        }
        return i2;
    }

    private void X(DataCollectBean dataCollectBean) {
        if (dataCollectBean == null) {
            f.g(this.f3438b, "gb config parse not begin or failure");
            return;
        }
        boolean zC = dataCollectBean.c();
        byte[] bArr = new byte[zC ? dataCollectBean.vehicleData.totalSize : 10];
        Arrays.fill(bArr, (byte) -1);
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        this.f3125i = byteBufferWrap;
        byteBufferWrap.put((byte) (zC ? 1 : 128));
    }

    private boolean Y(int i2) {
        if (this.o != null || i2 <= 0) {
            return false;
        }
        this.o = new SparseArray<>(i2);
        this.r = (byte) (this.r & 1);
        return true;
    }

    private void Z() {
        if (!super.f() || (this.r & 2) == 2) {
            return;
        }
        com.byd.data.common.g.a.i().g().o((byte) 2);
        this.r = (byte) (this.r | 2);
        f.b(this.f3438b, " set notifyBmsPeriodStart == TWO");
    }

    @Override // com.byd.bms.a.a
    public boolean B() {
        int iW;
        return this.f3442f != 0 && (iW = W()) >= 1 && ((long) this.j) <= ((System.currentTimeMillis() / 1000) + ((long) iW)) + 6 && !Y(iW) && this.o.size() >= iW + 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.bms.a.a
    public void D(byte[] bArr, boolean z) {
        int i2 = !z ? 1 : 0;
        int i3 = bArr[i2 + 4] & 255;
        short s = (short) (((bArr[i2 + 6] & 255) << 8) | (bArr[i2 + 5] & 255));
        byte b2 = this.n;
        int i4 = b2 & 255;
        if ((i3 <= 220) != false && (this.o != null) != false) {
            if (!z) {
                this.n = (byte) Math.max(i4, i3);
            }
            this.o.put(i3, Short.valueOf(s));
        } else if (i3 == 230) {
            if (((!z ? 1 : 0) & (this.m == 0 ? 1 : 0) & (i4 > 0 ? 1 : 0)) != 0) {
                this.m = b2;
            }
            this.q = s;
        } else if (i3 == 231) {
            if (((!z ? 1 : 0) & (this.m == 0 ? 1 : 0) & (i4 > 0 ? 1 : 0)) != 0) {
                this.m = b2;
            }
            this.p = s;
        }
        Z();
    }

    @Override // com.byd.data.common.present.BaseCloudPresent
    protected int K() {
        return 2;
    }

    @Override // com.byd.data.common.present.BaseCloudPresent
    public boolean O() {
        return !h((byte) 0);
    }

    @Override // com.byd.data.common.present.BaseCloudPresent
    protected void S(ApiRetrofit apiRetrofit) {
        apiRetrofit.f().b().C(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.byd.data.common.present.BaseCloudPresent
    /* JADX INFO: renamed from: V, reason: merged with bridge method [inline-methods] */
    public void H(QueryResult queryResult) {
        if (queryResult == null) {
            return;
        }
        queryResult.setResult(new QueryDataBean(queryResult));
    }

    @Override // com.byd.bms.a.a
    public ByteBuffer a(int i2, ByteBuffer byteBuffer) {
        return ByteBuffer.allocate(i2 + 7).put((byte) 35).put((byte) 35).put((byte) 1).put((byte) 1).putShort((short) i2).put(byteBuffer.array());
    }

    @Override // com.byd.data.common.present.BaseCloudPresent, com.byd.data.common.c.a
    public void b(byte b2) {
        super.b(b2);
        if (b2 == 3 && this.r >= 0) {
            this.r = (byte) 0;
            f.g(this.f3438b, "reset notifyDataFull =" + ((int) this.r));
        }
        if (b2 == -1) {
            this.r = (byte) 0;
        }
    }

    @Override // com.byd.data.common.present.BaseCloudPresent, com.byd.data.common.c.a
    public void d() {
        super.d();
        ByteBuffer byteBuffer = this.f3125i;
        if (byteBuffer != null) {
            byteBuffer.clear();
            this.f3125i = null;
        }
        SparseArray<Short> sparseArray = this.o;
        if (sparseArray != null) {
            sparseArray.clear();
            this.o = null;
        }
    }

    @Override // com.byd.data.common.present.BaseCloudPresent, com.byd.data.common.c.a
    public boolean f() {
        return h((byte) 1) && super.f() && ((QueryDataBean) this.f3442f).countryCode == ApiRetrofit.h().g().countryCode;
    }

    @Override // com.byd.bms.a.a
    public void g(short s) {
        this.l = s;
    }

    @Override // com.byd.bms.a.a
    public boolean h(byte b2) {
        V v = this.f3443g;
        boolean z = false;
        if (v == 0 || !((b) v).E()) {
            return false;
        }
        boolean z2 = !super.O();
        if (!(b2 == 1) || !z2) {
            return z2;
        }
        if (this.f3442f != 0 && 6 <= this.f3439c && ((QueryDataBean) this.f3442f).flagType == 1) {
            z = true;
        }
        return z;
    }

    @Override // com.byd.data.common.c.a
    public int l(boolean z) {
        V v;
        P p = this.f3442f;
        if (p == 0 || (v = this.f3443g) == 0) {
            return 60;
        }
        return (int) Math.ceil((((double) ((QueryDataBean) p).uploadCycle) * 1000.0d) / ((double) ((b) v).B(false)));
    }

    @Override // com.byd.bms.a.a
    public void m(int i2, int i3, int i4) {
        P p;
        if (this.f3125i == null || this.f3439c < 6 || (p = this.f3442f) == 0 || ((QueryDataBean) p).flagType <= 0 || i2 >= this.f3125i.capacity()) {
            return;
        }
        if (i4 == 1) {
            this.f3125i.put(i2, (byte) i3);
        } else if (i4 == 2) {
            this.f3125i.putShort(i2, (short) i3);
        } else if (i4 == 4) {
            this.f3125i.putInt(i2, i3);
        }
    }

    @Override // com.byd.bms.a.a
    public boolean n() {
        return this.m == -1;
    }

    @Override // com.byd.bms.a.a
    public void o(int i2, boolean z) {
        int i3 = this.m & 255;
        if ((i3 <= 0 || i3 == 255) && i2 != 0) {
            if (z) {
                this.m = (byte) i2;
                if (i2 == 255) {
                    return;
                }
            }
            Y(i2);
        }
    }

    @Override // com.byd.bms.a.a
    public int r(int i2) {
        return (((QueryDataBean) this.f3442f).uploadCycle * 1000) % i2;
    }

    @Override // com.byd.bms.a.a
    public ByteBuffer s(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3) {
        byteBuffer.put((byte) -125).put(this.k).putShort(this.l).put(byteBuffer2.array(), 0, byteBuffer2.position()).put(byteBuffer3.array(), 0, byteBuffer3.position()).put(d.a(byteBuffer.array(), byteBuffer.position()));
        return byteBuffer;
    }

    @Override // com.byd.bms.a.a
    public ByteBuffer t(int i2, ByteBuffer byteBuffer) {
        ByteBuffer byteBufferA = a(i2, byteBuffer);
        int i3 = this.m & 255;
        if (byteBufferA.remaining() < ((i3 + 3) << 1)) {
            return byteBufferA;
        }
        byteBufferA.put((byte) -124).putShort(this.q).putShort(this.p).put(this.m);
        for (int i4 = 0; i4 < i3; i4++) {
            byteBufferA.putShort(this.o.get(i4).shortValue());
        }
        byteBufferA.put(d.a(byteBufferA.array(), byteBufferA.position()));
        this.o.clear();
        this.o = null;
        return byteBufferA;
    }

    @Override // com.byd.bms.a.a
    public ByteBuffer u() {
        return this.f3125i;
    }

    @Override // com.byd.bms.a.a
    public void w(byte b2) {
        this.k = b2;
    }

    @Override // com.byd.bms.a.a
    public byte x() {
        byte bF = F();
        f.g(this.f3438b, "reqBmsConfig is end step =" + ((int) bF));
        return bF;
    }

    @Override // com.byd.bms.a.a
    public void y() {
        if (!f() || (this.r & 1) == 1) {
            return;
        }
        com.byd.data.common.g.a.i().g().o((byte) 1);
        this.r = (byte) (this.r | 1);
        f.b(this.f3438b, " set notifyBmsPeriodStart == ONE");
    }

    @Override // com.byd.bms.a.a
    public int z() {
        int iW = W();
        SparseArray<Short> sparseArray = this.o;
        int size = sparseArray == null ? 0 : sparseArray.size();
        f.b(this.f3438b, "countTemp= " + iW + ",size == " + size);
        if (iW < 1) {
            return 0;
        }
        int iCurrentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        if (size < iW) {
            return 0;
        }
        int i2 = iCurrentTimeMillis + 86400;
        this.j = i2;
        g.d("single_time", i2);
        return (iW + 3) << 1;
    }
}
