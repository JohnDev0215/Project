package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.webapps.WebappRegistry;
import org.chromium.webapk.lib.common.R$color;
import org.chromium.webapk.lib.common.R$dimen;
import org.chromium.webapk.lib.common.R$id;
import org.chromium.webapk.lib.common.R$layout;

/* JADX INFO: compiled from: WebappSplashController.java */
/* JADX INFO: loaded from: classes.dex */
public final class rs3 implements tq2 {
    public final o23 a;
    public final ls3 b;
    public xp3 c;

    public rs3(sq2 sq2Var, Activity activity, o23 o23Var, oj ojVar) {
        this.a = o23Var;
        ls3 ls3VarC = ls3.c(ojVar);
        this.b = ls3VarC;
        sq2Var.k = this;
        sq2Var.o = 300L;
        if (sq2Var.q) {
            sq2Var.F1();
        }
        if (ls3VarC.j()) {
            xp3 xp3Var = new xp3(ls3VarC.p(), activity);
            this.c = xp3Var;
            o23Var.g.add(xp3Var);
        }
    }

    @Override // defpackage.tq2
    public final void a(Tab tab) {
        xp3 xp3Var = this.c;
        if (xp3Var != null) {
            this.a.g.remove(xp3Var);
            tab.t(this.c);
            this.c = null;
        }
    }

    @Override // defpackage.tq2
    public final View b() {
        Context context = xz.a;
        ls3 ls3Var = this.b;
        Integer num = ls3Var.f().j;
        int iA = z7.A(num == null ? ls3Var.f().k : num.intValue());
        if (!ls3Var.e().l) {
            FrameLayout frameLayout = new FrameLayout(context);
            frameLayout.setBackgroundColor(iA);
            if (ls3Var.j()) {
                d(frameLayout, iA, ls3Var.e().b.a(), ls3Var.e().c);
            } else {
                ds3 ds3VarC = WebappRegistry.b.a.c(ls3Var.i());
                if (ds3VarC == null) {
                    d(frameLayout, iA, null, false);
                } else {
                    new cs3(ds3VarC, new qs3(this, frameLayout, iA)).c(i9.e);
                }
            }
            return frameLayout;
        }
        ImageView imageView = new ImageView(context);
        imageView.setBackgroundColor(iA);
        ku2 ku2VarT = ku2.t();
        try {
            String strU = ls3Var.u();
            StringBuilder sb = new StringBuilder("content://");
            sb.append(strU + ".SplashContentProvider");
            sb.append("/cached_splash_image");
            Bitmap bitmapC = pm0.c(context, Uri.parse(sb.toString()));
            ku2VarT.close();
            if (bitmapC != null) {
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setImageBitmap(bitmapC);
            }
            return imageView;
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001b  */
    @Override // defpackage.tq2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean c() {
        /*
            r2 = this;
            xp3 r2 = r2.c
            r0 = 0
            if (r2 == 0) goto L1f
            jp3 r2 = r2.f
            r1 = 1
            if (r2 == 0) goto L1b
            androidx.appcompat.app.AlertDialog r2 = r2.a
            if (r2 == 0) goto L16
            boolean r2 = r2.isShowing()
            if (r2 == 0) goto L16
            r2 = r1
            goto L17
        L16:
            r2 = r0
        L17:
            if (r2 == 0) goto L1b
            r2 = r1
            goto L1c
        L1b:
            r2 = r0
        L1c:
            if (r2 == 0) goto L1f
            r0 = r1
        L1f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.rs3.c():boolean");
    }

    public final void d(ViewGroup viewGroup, int i, Bitmap bitmap, boolean z) {
        boolean zL;
        int i2;
        Context context = xz.a;
        context.getResources();
        ls3 ls3Var = this.b;
        if (bitmap == null) {
            bitmap = ls3Var.g().a();
            zL = ls3Var.l();
            z = ls3Var.k();
        } else {
            zL = false;
        }
        String strP = ls3Var.p();
        boolean zH0 = z7.h0(i);
        Resources resources = context.getResources();
        if (bitmap == null || zL) {
            i2 = R$layout.webapp_splash_screen_no_icon;
        } else {
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            i2 = Math.min(bitmap.getScaledWidth(displayMetrics), bitmap.getScaledHeight(displayMetrics)) < resources.getDimensionPixelSize(R$dimen.webapp_splash_image_size_minimum) ? R$layout.webapp_splash_screen_no_icon : R$layout.webapp_splash_screen_large;
        }
        ViewGroup viewGroup2 = (ViewGroup) LayoutInflater.from(context).inflate(i2, viewGroup, true);
        TextView textView = (TextView) viewGroup2.findViewById(R$id.webapp_splash_screen_name);
        textView.setText(strP);
        if (zH0) {
            textView.setTextColor(context.getResources().getColor(R$color.webapp_splash_title_light, null));
        }
        ImageView imageView = (ImageView) viewGroup2.findViewById(R$id.webapp_splash_screen_icon);
        if (imageView == null) {
            return;
        }
        if (z) {
            imageView.setImageIcon(Icon.createWithAdaptiveBitmap(bitmap));
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }
}
