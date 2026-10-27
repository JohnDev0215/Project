package defpackage;

import java.util.Objects;

/* JADX INFO: renamed from: as, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0515as extends AbstractC1891zl {
    public C0515as(AbstractC0466Zr abstractC0466Zr) {
        super(C0571bs.DEFAULT_INSTANCE);
    }

    public C0515as d(String str) {
        b();
        C0571bs c0571bs = (C0571bs) this.t;
        Objects.requireNonNull(c0571bs);
        str.getClass();
        c0571bs.bitField0_ |= 2;
        c0571bs.country_ = str;
        return this;
    }

    public C0515as e(AbstractC0731el abstractC0731el) {
        b();
        C0571bs c0571bs = (C0571bs) this.t;
        Objects.requireNonNull(c0571bs);
        c0571bs.bitField0_ |= 16;
        c0571bs.seedData_ = abstractC0731el;
        return this;
    }

    public C0515as f(String str) {
        b();
        C0571bs c0571bs = (C0571bs) this.t;
        Objects.requireNonNull(c0571bs);
        str.getClass();
        c0571bs.bitField0_ |= 1;
        c0571bs.signature_ = str;
        return this;
    }
}
