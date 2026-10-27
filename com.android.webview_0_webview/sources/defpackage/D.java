package defpackage;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class D extends J {
    public C1796y G;
    public C H;
    public int I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public int f0J;
    public boolean K;

    public D(C1796y c1796y, Resources resources) {
        super(null);
        this.I = -1;
        this.f0J = -1;
        C1796y c1796y2 = new C1796y(c1796y, this, resources);
        super.e(c1796y2);
        this.G = c1796y2;
        onStateChange(getState());
        jumpToCurrentState();
    }

    /* JADX WARN: Code restructure failed: missing block: B:80:0x01cf, code lost:
    
        r5.onStateChange(r5.getState());
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x01d6, code lost:
    
        return r5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static defpackage.D f(android.content.Context r17, android.content.res.Resources r18, org.xmlpull.v1.XmlPullParser r19, android.util.AttributeSet r20, android.content.res.Resources.Theme r21) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 501
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.D.f(android.content.Context, android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):D");
    }

    @Override // defpackage.J, defpackage.H
    public void e(G g) {
        super.e(g);
        if (g instanceof C1796y) {
            this.G = (C1796y) g;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // defpackage.H, android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        super.jumpToCurrentState();
        C c = this.H;
        if (c != null) {
            c.d();
            this.H = null;
            d(this.I);
            this.I = -1;
            this.f0J = -1;
        }
    }

    @Override // defpackage.J, defpackage.H, android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.K) {
            super.mutate();
            if (this == this) {
                this.G.e();
                this.K = true;
            }
        }
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00e8  */
    @Override // defpackage.J, android.graphics.drawable.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onStateChange(int[] r19) {
        /*
            Method dump skipped, instruction units count: 243
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.D.onStateChange(int[]):boolean");
    }

    @Override // defpackage.H, android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (this.H != null && (visible || z2)) {
            if (z) {
                this.H.c();
            } else {
                jumpToCurrentState();
            }
        }
        return visible;
    }
}
