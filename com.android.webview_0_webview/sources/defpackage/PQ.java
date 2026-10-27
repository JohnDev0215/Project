package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.chromium.weblayer_private.BrowserImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class PQ extends LinearLayout implements FP {
    public float s;
    public TextView t;
    public ImageButton u;
    public final /* synthetic */ QQ v;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PQ(QQ qq, Context context, Bundle bundle) {
        super(context);
        this.v = qq;
        this.s = bundle.getFloat("UrlTextSize", 10.0f);
        View.inflate(getContext(), AbstractC0425Xm.j1, this);
        setOrientation(0);
        setBackgroundColor(0);
        this.t = (TextView) findViewById(AbstractC0371Um.k5);
        this.u = (ImageButton) findViewById(AbstractC0371Um.M3);
        a();
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x008c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a() {
        /*
            r10 = this;
            QQ r0 = r10.v
            org.chromium.weblayer_private.BrowserImpl r1 = r0.a
            if (r1 != 0) goto L7
            return
        L7:
            long r0 = r0.b
            java.lang.String r0 = J.N.M7Zzb0z2(r0)
            android.widget.TextView r1 = r10.t
            r1.setText(r0)
            android.widget.TextView r0 = r10.t
            r1 = 1084227584(0x40a00000, float:5.0)
            float r2 = r10.s
            float r1 = java.lang.Math.max(r1, r2)
            r2 = 2
            r0.setTextSize(r2, r1)
            android.widget.ImageButton r0 = r10.u
            QQ r1 = r10.v
            long r3 = r1.b
            int r1 = J.N.MgfY0koX(r3)
            QQ r3 = r10.v
            long r3 = r3.b
            boolean r3 = J.N.MX_pL9hY(r3)
            QQ r4 = r10.v
            org.chromium.weblayer_private.BrowserImpl r4 = r4.a
            rQ r4 = r4.e
            boolean r4 = org.chromium.ui.base.DeviceFormFactor.a(r4)
            r4 = r4 ^ 1
            r5 = 6
            r6 = 5
            r7 = 4
            r8 = 3
            r9 = 0
            if (r1 == 0) goto L5e
            if (r1 == r2) goto L5b
            if (r1 == r8) goto L5b
            if (r1 == r7) goto L5b
            if (r1 == r6) goto L58
            if (r1 == r5) goto L50
            goto L60
        L50:
            if (r3 == 0) goto L55
            int r1 = defpackage.AbstractC0317Rm.W1
            goto L64
        L55:
            int r1 = defpackage.AbstractC0317Rm.V1
            goto L64
        L58:
            int r1 = defpackage.AbstractC0317Rm.W1
            goto L64
        L5b:
            int r1 = defpackage.AbstractC0317Rm.U1
            goto L64
        L5e:
            if (r4 == 0) goto L62
        L60:
            r1 = 0
            goto L64
        L62:
            int r1 = defpackage.AbstractC0317Rm.V1
        L64:
            r0.setImageResource(r1)
            android.widget.ImageButton r0 = r10.u
            android.content.Context r1 = r10.getContext()
            android.content.res.Resources r1 = r1.getResources()
            QQ r3 = r10.v
            long r3 = r3.b
            int r3 = J.N.MgfY0koX(r3)
            if (r3 == 0) goto L8c
            if (r3 == r2) goto L89
            if (r3 == r8) goto L89
            if (r3 == r7) goto L89
            if (r3 == r6) goto L86
            if (r3 == r5) goto L8c
            goto L8e
        L86:
            int r9 = defpackage.AbstractC0566bn.W
            goto L8e
        L89:
            int r9 = defpackage.AbstractC0566bn.X
            goto L8e
        L8c:
            int r9 = defpackage.AbstractC0566bn.Y
        L8e:
            java.lang.String r1 = r1.getString(r9)
            r0.setContentDescription(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.PQ.a():void");
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        BrowserImpl browserImpl = this.v.a;
        if (browserImpl != null) {
            browserImpl.a.f(this);
            a();
        }
        super.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        BrowserImpl browserImpl = this.v.a;
        if (browserImpl != null) {
            browserImpl.a.g(this);
        }
        super.onDetachedFromWindow();
    }
}
