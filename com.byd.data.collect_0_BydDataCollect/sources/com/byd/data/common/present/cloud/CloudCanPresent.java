package com.byd.data.common.present.cloud;

import android.text.TextUtils;
import c.a.a.g.d;
import com.byd.data.common.c.c.a;
import com.byd.data.common.c.c.c;
import com.byd.data.common.d.a;
import com.byd.data.common.entiry.cloud.CloudConfig;
import com.byd.data.common.entiry.cloud.config.CanCollectConfig;
import com.byd.data.common.entiry.cloud.config.CommonConfig;
import com.byd.data.common.entiry.cloud.event.CanBean;
import com.byd.data.common.entiry.cloud.event.EventCanId;
import com.byd.data.common.entiry.cloud.period.CycleCanId;
import com.byd.data.common.http.ApiRetrofit;
import com.byd.data.common.i.f;
import com.byd.data.common.i.g;
import com.byd.data.common.present.BaseCloudPresent;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CloudCanPresent extends BaseCloudPresent<a, CloudConfig, CanCollectConfig> implements c {
    public CloudCanPresent(a aVar) {
        super(aVar);
    }

    private void W(EventCanId eventCanId, int i2) {
        LinkedList<Integer> linkedList = eventCanId.timeQueue;
        LinkedList<byte[]> linkedList2 = eventCanId.queue;
        if (linkedList.isEmpty() || eventCanId.peekIndex >= i2) {
            return;
        }
        boolean z = linkedList.size() == 1;
        eventCanId.peekIndex = linkedList.pop().intValue();
        int iIntValue = z ? 0 : linkedList.peek().intValue();
        if (i2 <= (iIntValue > 0 ? iIntValue : eventCanId.peekIndex)) {
            eventCanId.timeQueue.addFirst(Integer.valueOf(eventCanId.peekIndex));
            return;
        }
        if (linkedList2.size() > 1) {
            linkedList2.removeFirst();
        }
        if (iIntValue > 0) {
            eventCanId.peekIndex = iIntValue;
        }
        W(eventCanId, i2);
    }

    private ByteBuffer X(int i2, long j, int i3, Byte[] bArr, List<? extends CanBean> list) {
        boolean zM = M();
        ByteBuffer byteBufferY = Y(i2, i3, j + ((long) (j % 1000 == 0 ? a0() : 0)), false, zM);
        for (int i4 = 0; i4 < bArr.length - 1; i4++) {
            byte bByteValue = bArr[i4].byteValue();
            if (bByteValue >= 0 && bByteValue < list.size()) {
                CanBean canBean = list.get(bByteValue);
                if (canBean.d()) {
                    EventCanId eventCanId = (EventCanId) canBean;
                    synchronized (eventCanId.Lock) {
                        W(eventCanId, (int) (j / 1000));
                    }
                    byte[] bArrPeek = eventCanId.queue.peek();
                    if (bArrPeek != null) {
                        byteBufferY.putInt(eventCanId.canId).put(eventCanId.size).put(bArrPeek);
                    }
                } else {
                    continue;
                }
            }
        }
        return zM ? b0(true, false, byteBufferY.array()) : d0(byteBufferY);
    }

    private ByteBuffer Y(int i2, int i3, long j, boolean z, boolean z2) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate((z2 ? 0 : 27) + i3);
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (z2) {
            return byteBufferAllocate.putLong(j);
        }
        c0(byteBufferAllocate, false, z, i3);
        if (!z && i2 > 0) {
            byteBufferAllocate.putInt(i2);
        }
        byteBufferAllocate.putLong(j);
        return byteBufferAllocate;
    }

    private ByteBuffer Z(boolean z, long j) {
        LinkedList<byte[]> linkedList;
        P p = this.f3442f;
        short sO = p == 0 ? (short) 0 : ((CanCollectConfig) p).o();
        if (sO < 20) {
            return null;
        }
        ByteBuffer byteBufferY = Y(0, sO, j, true, z);
        List<? extends CanBean> listN = ((CanCollectConfig) this.f3442f).n();
        for (int i2 = 0; i2 < listN.size(); i2++) {
            CanBean canBean = listN.get(i2);
            if ((canBean.b() & 2) == 2) {
                if (byteBufferY.remaining() < 8) {
                    break;
                }
                if (canBean instanceof CycleCanId) {
                    byte[] bArr = ((CycleCanId) canBean).data;
                    if (bArr != null) {
                        byteBufferY.putInt(canBean.canId).put(canBean.size).put(bArr);
                    }
                } else if ((canBean instanceof EventCanId) && (linkedList = canBean.queue) != null && linkedList.size() > 0 && linkedList.getLast() != null) {
                    byteBufferY.putInt(canBean.canId).put(canBean.size).put(linkedList.getLast());
                }
            }
        }
        return z ? b0(true, true, byteBufferY.array()) : d0(byteBufferY);
    }

    private int a0() {
        return new SecureRandom().nextInt(900) + 100;
    }

    private ByteBuffer b0(boolean z, boolean z2, byte[] bArr) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bArr.length + 24 + 3);
        c0(byteBufferAllocate, z, z2, bArr.length);
        byteBufferAllocate.put(bArr);
        d0(byteBufferAllocate);
        return byteBufferAllocate;
    }

    private void c0(ByteBuffer byteBuffer, boolean z, boolean z2, int i2) {
        byteBuffer.put((byte) 35).put((byte) 35);
        P p = this.f3442f;
        if (((CanCollectConfig) p).uuid != null) {
            byteBuffer.put(d.b(((CanCollectConfig) p).uuid));
        }
        byteBuffer.put((z ? a.i.AES_128 : a.i.NOT_ENCRYPT).a());
        byteBuffer.put(z2 ? (byte) 1 : (byte) 2);
        byteBuffer.putInt(i2);
    }

    private ByteBuffer d0(ByteBuffer byteBuffer) {
        byteBuffer.put(com.byd.data.common.i.d.a(byteBuffer.array(), byteBuffer.position()));
        byteBuffer.put((byte) 13).put((byte) 10);
        return byteBuffer;
    }

    @Override // com.byd.data.common.c.c.c
    public void E(short s, byte b2, byte[] bArr) {
        if (this.f3442f == 0 || this.f3439c < 6) {
            return;
        }
        ((CanCollectConfig) this.f3442f).B(s, b2, bArr);
    }

    @Override // com.byd.data.common.present.BaseCloudPresent
    protected int K() {
        return 1;
    }

    @Override // com.byd.data.common.present.BaseCloudPresent
    protected boolean N(int i2, byte b2) {
        String str = ((CanCollectConfig) this.f3442f).uuid;
        String strB = g.b(J());
        boolean z = false;
        int length = (int) com.byd.data.common.g.a.i().a(i2, false).length();
        boolean zEquals = TextUtils.equals(strB, str) & (length > 20);
        com.byd.data.common.c.c.a aVar = (com.byd.data.common.c.c.a) this.f3443g;
        if (((CanCollectConfig) this.f3442f).flagType > 0 && this.f3439c == 6) {
            z = true;
        }
        aVar.v(z);
        if (zEquals) {
            f.g(this.f3438b, strB + ",uuid is same no need to notify notifyVehicleHal length= " + length + ",updateIndex == " + ((int) b2));
            if (b2 == 0) {
                T(i2);
            }
            G();
        } else {
            g.e(J(), str);
        }
        return zEquals;
    }

    @Override // com.byd.data.common.present.BaseCloudPresent
    protected void S(ApiRetrofit apiRetrofit) {
        f.g(this.f3438b, "startReqCloud");
        apiRetrofit.d().c().C(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.byd.data.common.present.BaseCloudPresent
    /* JADX INFO: renamed from: V, reason: merged with bridge method [inline-methods] */
    public void H(CloudConfig cloudConfig) {
        if (cloudConfig == null) {
            return;
        }
        cloudConfig.setResult(cloudConfig.canCollectConfig);
    }

    @Override // com.byd.data.common.c.c.c
    public String c() {
        P p = this.f3442f;
        if (p == 0) {
            return null;
        }
        return ((CanCollectConfig) p).uuid;
    }

    @Override // com.byd.data.common.present.BaseCloudPresent, com.byd.data.common.c.a
    public void d() {
        this.f3443g = null;
        P p = this.f3442f;
        if (p != 0) {
            ((CanCollectConfig) p).e();
            this.f3442f = null;
        }
    }

    @Override // com.byd.data.common.c.c.c
    public ByteBuffer e(long j) {
        if (f()) {
            return Z(M(), j);
        }
        return null;
    }

    @Override // com.byd.data.common.c.a
    public int l(boolean z) {
        P p = this.f3442f;
        if (p == 0) {
            return 60;
        }
        CanCollectConfig canCollectConfig = (CanCollectConfig) p;
        CommonConfig commonConfig = z ? canCollectConfig.canCollectPeriodConfig : canCollectConfig.canCollectEventConfig;
        if (commonConfig == null) {
            return 60;
        }
        return (commonConfig.uploadCycle * 1000) / q();
    }

    @Override // com.byd.data.common.c.c.c
    public void p(com.byd.data.common.server.e.a aVar, int i2, Byte[] bArr, long j) {
        int iByteValue = bArr[bArr.length - 1].byteValue() & 255;
        if (iByteValue <= 20) {
            return;
        }
        byte bA = ((CanCollectConfig) this.f3442f).canCollectEventConfig.a();
        int i3 = ((int) (j / 1000)) - bA;
        if (aVar == null) {
            return;
        }
        List<? extends CanBean> listN = ((CanCollectConfig) this.f3442f).n();
        int i4 = 0;
        while (i4 < bA) {
            int i5 = i3 + i4;
            long j2 = ((long) i5) * 1000;
            if (this.f3439c < 6) {
                break;
            }
            ByteBuffer byteBufferX = X(i2, j2, iByteValue, bArr, listN);
            aVar.f(byteBufferX, i2, j2, false);
            if (((CanCollectConfig) this.f3442f).b() && i4 == bA - 1) {
                f.g(this.f3438b, i2 + " trigger event last =" + d.f(byteBufferX.array()));
            }
            i4++;
            i3 = i5;
        }
        if (this.f3439c == 6) {
            ((com.byd.data.common.c.c.a) this.f3443g).a(i2, bA);
        }
        if (((CanCollectConfig) this.f3442f).b()) {
            f.g(this.f3438b, i2 + " trigger event write end");
        }
    }

    @Override // com.byd.data.common.c.c.c
    public int q() {
        P p = this.f3442f;
        if (p == 0 || ((CanCollectConfig) p).canCollectPeriodConfig == null) {
            return 1000;
        }
        return ((CanCollectConfig) p).canCollectPeriodConfig.collectCycle;
    }

    @Override // com.byd.data.common.c.c.c
    public ByteBuffer v(int i2, long j) {
        int iByteValue;
        if (!f()) {
            return null;
        }
        List<? extends CanBean> listN = ((CanCollectConfig) this.f3442f).n();
        Byte[] bArrM = ((CanCollectConfig) this.f3442f).m(i2);
        if (d.a.a.f.b.c.a.a(listN) || bArrM == null || bArrM.length < 2 || (iByteValue = bArrM[bArrM.length - 1].byteValue() & 255) <= 20) {
            return null;
        }
        return X(i2, j, iByteValue, bArrM, listN);
    }
}
