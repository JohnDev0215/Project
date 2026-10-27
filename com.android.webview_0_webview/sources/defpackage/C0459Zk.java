package defpackage;

/* JADX INFO: renamed from: Zk, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0459Zk extends C0620cl {
    public final int w;
    public final int x;

    public C0459Zk(byte[] bArr, int i, int i2) {
        super(bArr);
        AbstractC0731el.f(i, i + i2, bArr.length);
        this.w = i;
        this.x = i2;
    }

    @Override // defpackage.C0620cl, defpackage.AbstractC0731el
    public byte e(int i) {
        int i2 = this.x;
        if (((i2 - (i + 1)) | i) >= 0) {
            return this.v[this.w + i];
        }
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException(Z7.a("Index < 0: ", i));
        }
        throw new ArrayIndexOutOfBoundsException("Index > length: " + i + ", " + i2);
    }

    @Override // defpackage.C0620cl, defpackage.AbstractC0731el
    public void j(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.v, this.w + i, bArr, i2, i3);
    }

    @Override // defpackage.C0620cl, defpackage.AbstractC0731el
    public byte k(int i) {
        return this.v[this.w + i];
    }

    @Override // defpackage.C0620cl
    public int o() {
        return this.w;
    }

    @Override // defpackage.C0620cl, defpackage.AbstractC0731el
    public int size() {
        return this.x;
    }
}
