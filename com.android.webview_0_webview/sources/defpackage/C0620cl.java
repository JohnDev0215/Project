package defpackage;

/* JADX INFO: renamed from: cl, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0620cl extends AbstractC0564bl {
    public final byte[] v;

    public C0620cl(byte[] bArr) {
        if (bArr == null) {
            throw null;
        }
        this.v = bArr;
    }

    @Override // defpackage.AbstractC0731el
    public byte e(int i) {
        return this.v[i];
    }

    @Override // defpackage.AbstractC0731el
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AbstractC0731el) || size() != ((AbstractC0731el) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof C0620cl)) {
            return obj.equals(this);
        }
        C0620cl c0620cl = (C0620cl) obj;
        int i = this.s;
        int i2 = c0620cl.s;
        if (i != 0 && i2 != 0 && i != i2) {
            return false;
        }
        int size = size();
        if (size > c0620cl.size()) {
            throw new IllegalArgumentException("Length too large: " + size + size());
        }
        if (0 + size > c0620cl.size()) {
            throw new IllegalArgumentException("Ran off end of other: 0, " + size + ", " + c0620cl.size());
        }
        byte[] bArr = this.v;
        byte[] bArr2 = c0620cl.v;
        int iO = o() + size;
        int iO2 = o();
        int iO3 = c0620cl.o() + 0;
        while (iO2 < iO) {
            if (bArr[iO2] != bArr2[iO3]) {
                return false;
            }
            iO2++;
            iO3++;
        }
        return true;
    }

    @Override // defpackage.AbstractC0731el
    public void j(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.v, i, bArr, i2, i3);
    }

    @Override // defpackage.AbstractC0731el
    public byte k(int i) {
        return this.v[i];
    }

    @Override // defpackage.AbstractC0731el
    public final AbstractC0731el l(int i, int i2) {
        int iF = AbstractC0731el.f(i, i2, size());
        return iF == 0 ? AbstractC0731el.t : new C0459Zk(this.v, o() + i, iF);
    }

    public int o() {
        return 0;
    }

    @Override // defpackage.AbstractC0731el
    public int size() {
        return this.v.length;
    }
}
