package defpackage;

import java.util.Objects;

/* JADX INFO: renamed from: zl, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1891zl extends AbstractC0297Qk {
    public final AbstractC0065Dl s;
    public AbstractC0065Dl t;
    public boolean u = false;

    public AbstractC1891zl(AbstractC0065Dl abstractC0065Dl) {
        this.s = abstractC0065Dl;
        this.t = (AbstractC0065Dl) abstractC0065Dl.c(EnumC0047Cl.NEW_MUTABLE_INSTANCE, null, null);
    }

    public AbstractC0065Dl a() {
        if (this.u) {
            return this.t;
        }
        AbstractC0065Dl abstractC0065Dl = this.t;
        Objects.requireNonNull(abstractC0065Dl);
        C1011jm.c.b(abstractC0065Dl).h(abstractC0065Dl);
        this.u = true;
        return this.t;
    }

    public final void b() {
        if (this.u) {
            AbstractC0065Dl abstractC0065Dl = (AbstractC0065Dl) this.t.c(EnumC0047Cl.NEW_MUTABLE_INSTANCE, null, null);
            C1011jm.c.b(abstractC0065Dl).b(abstractC0065Dl, this.t);
            this.t = abstractC0065Dl;
            this.u = false;
        }
    }

    public AbstractC1891zl c(AbstractC0065Dl abstractC0065Dl) {
        b();
        AbstractC0065Dl abstractC0065Dl2 = this.t;
        C1011jm.c.b(abstractC0065Dl2).b(abstractC0065Dl2, abstractC0065Dl);
        return this;
    }

    public Object clone() {
        AbstractC1891zl abstractC1891zl = (AbstractC1891zl) this.s.c(EnumC0047Cl.NEW_BUILDER, null, null);
        abstractC1891zl.c(a());
        return abstractC1891zl;
    }
}
