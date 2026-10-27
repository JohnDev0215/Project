package n4;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public final class j implements l, e4.f, com.bumptech.glide.load.data.g {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ByteBuffer f19944m;

    public j(int i7) {
        this.l = i7;
        switch (i7) {
            case 3:
                this.f19944m = ByteBuffer.allocate(4);
                break;
            default:
                this.f19944m = ByteBuffer.allocate(8);
                break;
        }
    }

    @Override // com.bumptech.glide.load.data.g
    public Object a() {
        ByteBuffer byteBuffer = this.f19944m;
        byteBuffer.position(0);
        return byteBuffer;
    }

    @Override // com.bumptech.glide.load.data.g
    public void b() {
    }

    public short c(int i7) {
        ByteBuffer byteBuffer = this.f19944m;
        if (byteBuffer.remaining() - i7 >= 2) {
            return byteBuffer.getShort(i7);
        }
        return (short) -1;
    }

    @Override // e4.f
    public void e(byte[] bArr, Object obj, MessageDigest messageDigest) {
        switch (this.l) {
            case 2:
                Long l = (Long) obj;
                messageDigest.update(bArr);
                synchronized (this.f19944m) {
                    this.f19944m.position(0);
                    messageDigest.update(this.f19944m.putLong(l.longValue()).array());
                    break;
                }
                return;
            default:
                Integer num = (Integer) obj;
                if (num == null) {
                    return;
                }
                messageDigest.update(bArr);
                synchronized (this.f19944m) {
                    this.f19944m.position(0);
                    messageDigest.update(this.f19944m.putInt(num.intValue()).array());
                    break;
                }
                return;
        }
    }

    @Override // n4.l
    public long h(long j3) {
        ByteBuffer byteBuffer = this.f19944m;
        int iMin = (int) Math.min(byteBuffer.remaining(), j3);
        byteBuffer.position(byteBuffer.position() + iMin);
        return iMin;
    }

    @Override // n4.l
    public short j() throws k {
        ByteBuffer byteBuffer = this.f19944m;
        if (byteBuffer.remaining() >= 1) {
            return (short) (byteBuffer.get() & 255);
        }
        throw new k();
    }

    @Override // n4.l
    public int p() {
        return j() | (j() << 8);
    }

    @Override // n4.l
    public int w(int i7, byte[] bArr) {
        ByteBuffer byteBuffer = this.f19944m;
        int iMin = Math.min(i7, byteBuffer.remaining());
        if (iMin == 0) {
            return -1;
        }
        byteBuffer.get(bArr, 0, iMin);
        return iMin;
    }

    public j(int i7, byte[] bArr) {
        this.l = 1;
        this.f19944m = (ByteBuffer) ByteBuffer.wrap(bArr).order(ByteOrder.BIG_ENDIAN).limit(i7);
    }

    public j(int i7, ByteBuffer byteBuffer) {
        this.l = i7;
        switch (i7) {
            case 4:
                this.f19944m = byteBuffer;
                break;
            default:
                this.f19944m = byteBuffer;
                byteBuffer.order(ByteOrder.BIG_ENDIAN);
                break;
        }
    }
}
