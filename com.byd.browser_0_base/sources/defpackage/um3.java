package defpackage;

import java.util.Locale;
import org.chromium.content_public.browser.RenderFrameHost;

/* JADX INFO: compiled from: ViewGroupOverlayApi18.java */
/* JADX INFO: loaded from: classes.dex */
public final class um3 implements a21, vb {
    public final /* synthetic */ int e;
    public Object f;

    public /* synthetic */ um3(int i, int i2) {
        this.e = i;
    }

    public static um3 e(String str) {
        if (str == null) {
            return null;
        }
        String[] strArrSplit = str.split("\\.");
        if (strArrSplit.length != 4) {
            return null;
        }
        um3 um3Var = new um3(5);
        for (int i = 0; i < 4; i++) {
            try {
                ((int[]) um3Var.f)[i] = Integer.parseInt(strArrSplit[i]);
            } catch (NumberFormatException unused) {
                return null;
            }
        }
        return um3Var;
    }

    @Override // defpackage.a21
    public final y11 d() {
        return new x90((RenderFrameHost) this.f);
    }

    public final boolean f(um3 um3Var) {
        for (int i = 0; i < 4; i++) {
            int i2 = ((int[]) this.f)[i];
            int i3 = ((int[]) um3Var.f)[i];
            if (i2 < i3) {
                return true;
            }
            if (i2 > i3) {
                return false;
            }
        }
        return false;
    }

    @Override // defpackage.vb
    public final zp1 getHandleBackPressChangedSupplier() {
        return ((rq0) this.f).f();
    }

    @Override // defpackage.vb
    public final int o() {
        int i = !((rq0) this.f).g() ? 1 : 0;
        ((rq0) this.f).i();
        return i;
    }

    public final String toString() {
        switch (this.e) {
            case 5:
                return String.format(Locale.US, "%d.%d.%d.%d", Integer.valueOf(((int[]) this.f)[0]), Integer.valueOf(((int[]) this.f)[1]), Integer.valueOf(((int[]) this.f)[2]), Integer.valueOf(((int[]) this.f)[3]));
            default:
                return super.toString();
        }
    }

    public /* synthetic */ um3(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public um3(int i) {
        this.e = i;
        int i2 = 6;
        if (i != 6) {
            this.f = new int[]{0, 0, 0, 0};
        } else {
            this(i2, new su1(new a8(), new xq2()));
        }
    }
}
