package defpackage;

import java.util.Arrays;

/* JADX INFO: renamed from: Uf, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class BinderC0364Uf extends AbstractBinderC0382Vf {
    public final byte[] b;

    public BinderC0364Uf(byte[] bArr) {
        super(Arrays.copyOfRange(bArr, 0, 25));
        this.b = bArr;
    }

    @Override // defpackage.AbstractBinderC0382Vf
    public final byte[] v() {
        return this.b;
    }
}
