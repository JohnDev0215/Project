package defpackage;

import J.N;
import android.graphics.Color;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import org.chromium.content.browser.accessibility.captioning.CaptioningController;

/* JADX INFO: renamed from: oA, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1258oA {
    public boolean a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public final Map i = new WeakHashMap();

    public static String a(Integer num) {
        return num == null ? "" : String.format("rgba(%s, %s, %s, %s)", Integer.valueOf(Color.red(num.intValue())), Integer.valueOf(Color.green(num.intValue())), Integer.valueOf(Color.blue(num.intValue())), new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US)).format(((double) Color.alpha(num.intValue())) / 255.0d));
    }

    public boolean b() {
        return !this.i.isEmpty();
    }

    public void c(InterfaceC1532tA interfaceC1532tA) {
        boolean z = this.a;
        if (!z) {
            CaptioningController captioningController = (CaptioningController) interfaceC1532tA;
            if (captioningController.b == 0) {
                return;
            }
            N.MM3_AH7F(captioningController.b, captioningController, false, Objects.toString(null, ""), Objects.toString(null, ""), Objects.toString(null, ""), Objects.toString(null, ""), Objects.toString(null, ""), Objects.toString(null, ""), Objects.toString(null, ""));
            return;
        }
        String str = this.b;
        String str2 = this.c;
        String str3 = this.d;
        String str4 = this.e;
        String str5 = this.f;
        String str6 = this.g;
        String str7 = this.h;
        CaptioningController captioningController2 = (CaptioningController) interfaceC1532tA;
        if (captioningController2.b == 0) {
            return;
        }
        N.MM3_AH7F(captioningController2.b, captioningController2, z, Objects.toString(str, ""), Objects.toString(str2, ""), Objects.toString(str3, ""), Objects.toString(str4, ""), Objects.toString(str5, ""), Objects.toString(str6, ""), Objects.toString(str7, ""));
    }

    public final void d() {
        Iterator it = this.i.keySet().iterator();
        while (it.hasNext()) {
            c((InterfaceC1532tA) it.next());
        }
    }

    public void e(float f) {
        this.h = new DecimalFormat("#%", new DecimalFormatSymbols(Locale.US)).format(f);
        d();
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void f(defpackage.C1313pA r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            java.lang.Integer r2 = r1.d
            java.lang.String r2 = a(r2)
            r0.f = r2
            java.lang.Integer r2 = r1.a
            java.lang.String r2 = a(r2)
            r0.b = r2
            java.lang.Integer r2 = r1.b
            java.lang.String r2 = a(r2)
            java.lang.Integer r3 = r1.c
            r4 = 1
            r5 = 2
            java.lang.String r6 = ""
            if (r3 == 0) goto L3d
            int r3 = r3.intValue()
            if (r3 == r4) goto L3a
            if (r3 == r5) goto L37
            r7 = 3
            if (r3 == r7) goto L34
            r7 = 4
            if (r3 == r7) goto L31
            goto L3d
        L31:
            java.lang.String r3 = "%2$s %2$s 0 %1$s"
            goto L3e
        L34:
            java.lang.String r3 = "-%2$s -%2$s 0 %1$s"
            goto L3e
        L37:
            java.lang.String r3 = "%1$s %2$s %2$s 0.1em"
            goto L3e
        L3a:
            java.lang.String r3 = "%2$s %2$s 0 %1$s, -%2$s -%2$s 0 %1$s, %2$s -%2$s 0 %1$s, -%2$s %2$s 0 %1$s"
            goto L3e
        L3d:
            r3 = r6
        L3e:
            if (r2 == 0) goto L46
            boolean r7 = r2.isEmpty()
            if (r7 == 0) goto L48
        L46:
            java.lang.String r2 = "silver"
        L48:
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r7 = 0
            r5[r7] = r2
            java.lang.String r2 = "0.05em"
            r5[r4] = r2
            java.lang.String r2 = java.lang.String.format(r3, r5)
            r0.g = r2
            android.graphics.Typeface r1 = r1.e
            if (r1 != 0) goto L5c
            goto L8c
        L5c:
            r2 = 10
            java.lang.String r8 = ""
            java.lang.String r9 = "sans-serif"
            java.lang.String r10 = "sans-serif-condensed"
            java.lang.String r11 = "sans-serif-monospace"
            java.lang.String r12 = "serif"
            java.lang.String r13 = "serif-monospace"
            java.lang.String r14 = "casual"
            java.lang.String r15 = "cursive"
            java.lang.String r16 = "sans-serif-smallcaps"
            java.lang.String r17 = "monospace"
            java.lang.String[] r3 = new java.lang.String[]{r8, r9, r10, r11, r12, r13, r14, r15, r16, r17}
        L76:
            if (r7 >= r2) goto L8c
            r4 = r3[r7]
            int r5 = r1.getStyle()
            android.graphics.Typeface r5 = android.graphics.Typeface.create(r4, r5)
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L89
            goto L8d
        L89:
            int r7 = r7 + 1
            goto L76
        L8c:
            r4 = r6
        L8d:
            r0.c = r4
            if (r1 == 0) goto L9c
            boolean r1 = r1.isItalic()
            if (r1 == 0) goto L9c
            java.lang.String r1 = "italic"
            r0.d = r1
            goto L9e
        L9c:
            r0.d = r6
        L9e:
            r0.e = r6
            r18.d()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C1258oA.f(pA):void");
    }
}
