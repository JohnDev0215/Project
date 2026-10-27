package com.byd.data.collect.subscribe.present;

import android.content.Context;
import com.byd.data.common.d.a;
import com.byd.data.common.entiry.extension.EvExtData;
import com.byd.data.common.entiry.extension.ExtensionAlarm;
import com.byd.data.common.entiry.extension.ExtensionItem;
import com.byd.data.common.entiry.gson.DataCollectBean;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ExtensionPresent extends BasePresent<Byte> {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private ExtensionAlarm f3267f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private EvExtData f3268g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private byte f3269h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private byte f3270i;
    private ByteBuffer[] j;
    private final byte k;
    private ByteBuffer l;

    public ExtensionPresent(DataCollectBean dataCollectBean) {
        super(dataCollectBean);
        this.k = (byte) 10;
        this.f3267f = dataCollectBean.extensionAlarm;
    }

    private void u(byte[] bArr, ExtensionItem extensionItem) {
        ByteBuffer[] byteBufferArr;
        byte b2;
        ByteBuffer byteBuffer;
        if (extensionItem == null) {
            return;
        }
        byte[] bArr2 = extensionItem.array;
        if (bArr2.length == 0 || bArr2.length % 2 == 1 || (byteBufferArr = this.j) == null || (b2 = extensionItem.id) >= byteBufferArr.length || (byteBuffer = byteBufferArr[b2]) == null) {
            return;
        }
        byteBuffer.clear();
        if (extensionItem.size == 1) {
            byteBuffer.put(bArr, bArr2[0], 1);
            return;
        }
        for (int i2 = 0; i2 < bArr2.length - 1; i2 += 2) {
            int i3 = i2 + 1;
            if (byteBuffer.remaining() < (bArr2[i3] - bArr2[i2]) + 1) {
                return;
            }
            byteBuffer.put(bArr, bArr2[i2], (bArr2[i3] - bArr2[i2]) + 1);
        }
    }

    private void x(byte b2, ByteBuffer byteBuffer) {
        byteBuffer.put(b2).putInt((int) (System.currentTimeMillis() / 1000)).putInt(this.f3266e);
        byte b3 = 0;
        while (true) {
            ByteBuffer[] byteBufferArr = this.j;
            if (b3 >= byteBufferArr.length) {
                return;
            }
            ByteBuffer byteBuffer2 = byteBufferArr[b3];
            if (byteBuffer2 != null) {
                byteBuffer.put(byteBuffer2.array());
            }
            b3 = (byte) (b3 + 1);
        }
    }

    public void A(short s, byte b2) {
        if (this.f3267f.chargeStop.canId == s && b2 != this.f3270i) {
            this.f3270i = b2;
        } else if (s == com.byd.data.common.server.b.h(null).g().vehicleData.changeState.canId && b2 != this.f3269h) {
            this.f3269h = b2;
        }
        p(this.f3267f.chargeStop.a(), this.f3270i == 3 || this.f3269h == 4);
    }

    public void C(int i2, int i3) {
        this.f3266e = i2 | (((1 << i3) - 1) & this.f3266e);
    }

    @Override // com.byd.data.collect.subscribe.present.BasePresent
    public void c() {
        super.c();
        this.f3267f = null;
        this.f3268g = null;
        this.l = null;
        this.j = null;
    }

    @Override // com.byd.data.collect.subscribe.present.BasePresent
    protected int d() {
        EvExtData evExtData = this.f3265d.evExtData;
        this.f3268g = evExtData;
        return evExtData.extList.size();
    }

    @Override // com.byd.data.collect.subscribe.present.BasePresent
    public byte[] g(Context context, boolean z, boolean z2) {
        byte bMin = (byte) Math.min(Math.ceil((((double) this.l.position()) + 1.0d) / ((double) this.f3268g.totalSize)), 10.0d);
        if (this.l.position() == 0) {
            this.l.put((byte) (com.byd.data.common.server.b.h(context).o & 255));
            this.l.put((byte) 1).put((byte) (this.f3265d.c() ? 3 : 2)).put((byte) (this.f3265d.c() ? 2 : 1)).putShort((short) -15534).put(z2 ? bMin : (byte) 10);
        } else {
            if (((bMin >= 10 ? 0 : 1) & (z2 ? 1 : 0)) != 0) {
                this.l.put(6, bMin);
            }
        }
        x(bMin, this.l);
        if (this.l.position() != this.l.capacity() && !z2) {
            if (bMin > 0 && bMin % 3 == 0) {
                o();
            }
            return new byte[0];
        }
        if (n()) {
            int i2 = this.f3268g.totalSize + 9;
            byte[] bArr = new byte[i2];
            System.arraycopy(this.l.array(), this.l.position() - i2, bArr, 0, i2);
            d.a.a.f.b.b.a.h(this.f3262a, "extensionData nine =" + c.a.a.g.d.f(bArr));
        }
        com.byd.data.common.server.e.a aVarO = com.byd.data.collect.server.i.o(context);
        byte[] bArrArray = this.l.array();
        if (bMin < 10) {
            int iPosition = this.l.position();
            byte[] bArr2 = new byte[iPosition];
            System.arraycopy(this.l.array(), 0, bArr2, 0, iPosition);
            bArrArray = bArr2;
        }
        this.l.clear();
        String strI = aVarO.i();
        if (z) {
            bArrArray = aVarO.b(bArrArray, strI);
        }
        return b(strI, bArrArray, z, a.EnumC0065a.GB_EXTRA_FILE_DATA).array();
    }

    @Override // com.byd.data.collect.subscribe.present.BasePresent
    protected synchronized void m(DataCollectBean dataCollectBean) {
        if (this.f3268g == null) {
            this.f3268g = dataCollectBean.evExtData;
        }
        List<ExtensionItem> list = this.f3268g.extList;
        this.j = new ByteBuffer[list.size()];
        for (int i2 = 0; i2 < list.size(); i2++) {
            ExtensionItem extensionItem = list.get(i2);
            extensionItem.id = (byte) i2;
            this.j[i2] = i(extensionItem.size);
        }
        Collections.sort(list);
        dataCollectBean.evExtData.canArray = null;
        this.l = ByteBuffer.allocate(((this.f3268g.totalSize + 9) * 10) + 7);
        com.byd.data.common.i.f.g(this.f3262a, "initBuffer is end size = " + list.size());
    }

    public void t(short s, byte b2, byte[] bArr, boolean z) {
        if (1083 == s) {
            return;
        }
        if (z || !(842 == s || 1089 == s)) {
            ExtensionItem extensionItemA = this.f3268g.a(s, b2);
            if (!(extensionItemA == null) || !com.byd.data.common.i.f.f3426a) {
                u(bArr, extensionItemA);
                return;
            }
            d.a.a.f.b.b.a.d(this.f3262a, Integer.toHexString(s) + " searchItem item is null");
        }
    }

    public void w(byte[] bArr, short s) {
        t(s, (byte) 0, bArr, this.f3265d.c());
    }

    public void z(byte b2) {
        ByteBuffer[] byteBufferArr;
        ByteBuffer byteBuffer;
        if (this.f3268g == null || (byteBufferArr = this.j) == null || (byteBuffer = byteBufferArr[0]) == null) {
            return;
        }
        byteBuffer.put(0, b2);
    }
}
