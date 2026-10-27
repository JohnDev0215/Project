package defpackage;

import android.graphics.Color;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Iterator;
import java.util.Locale;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: CaptioningChangeDelegate.java */
/* JADX INFO: loaded from: classes3.dex */
public final class dm {
    public boolean a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public final WeakHashMap i = new WeakHashMap();

    public static String a(Integer num) {
        return num == null ? "" : String.format("rgba(%s, %s, %s, %s)", Integer.valueOf(Color.red(num.intValue())), Integer.valueOf(Color.green(num.intValue())), Integer.valueOf(Color.blue(num.intValue())), new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US)).format(((double) Color.alpha(num.intValue())) / 255.0d));
    }

    public final void b(cy2 cy2Var) {
        boolean z = this.a;
        if (z) {
            cy2Var.a(new m83(z, this.b, this.c, this.d, this.e, this.f, this.g, this.h));
        } else {
            cy2Var.a(new m83());
        }
    }

    public final void c() {
        Iterator it = this.i.keySet().iterator();
        while (it.hasNext()) {
            b((cy2) it.next());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d(defpackage.em r19) {
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
            r3 = 1
            java.lang.String r4 = ""
            java.lang.Integer r5 = r1.c
            r6 = 2
            if (r5 == 0) goto L3d
            int r5 = r5.intValue()
            if (r5 == r3) goto L3a
            if (r5 == r6) goto L37
            r7 = 3
            if (r5 == r7) goto L34
            r7 = 4
            if (r5 == r7) goto L31
            goto L3d
        L31:
            java.lang.String r5 = "%2$s %2$s 0 %1$s"
            goto L3e
        L34:
            java.lang.String r5 = "-%2$s -%2$s 0 %1$s"
            goto L3e
        L37:
            java.lang.String r5 = "%1$s %2$s %2$s 0.1em"
            goto L3e
        L3a:
            java.lang.String r5 = "%2$s %2$s 0 %1$s, -%2$s -%2$s 0 %1$s, %2$s -%2$s 0 %1$s, -%2$s %2$s 0 %1$s"
            goto L3e
        L3d:
            r5 = r4
        L3e:
            if (r2 == 0) goto L46
            boolean r7 = r2.isEmpty()
            if (r7 == 0) goto L48
        L46:
            java.lang.String r2 = "silver"
        L48:
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r7 = 0
            r6[r7] = r2
            java.lang.String r2 = "0.05em"
            r6[r3] = r2
            java.lang.String r2 = java.lang.String.format(r5, r6)
            r0.g = r2
            android.graphics.Typeface r1 = r1.e
            if (r1 != 0) goto L5c
            goto L8c
        L5c:
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
            java.lang.String[] r2 = new java.lang.String[]{r8, r9, r10, r11, r12, r13, r14, r15, r16, r17}
        L74:
            r3 = 10
            if (r7 >= r3) goto L8c
            r3 = r2[r7]
            int r5 = r1.getStyle()
            android.graphics.Typeface r5 = android.graphics.Typeface.create(r3, r5)
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L89
            goto L8d
        L89:
            int r7 = r7 + 1
            goto L74
        L8c:
            r3 = r4
        L8d:
            r0.c = r3
            if (r1 == 0) goto L9c
            boolean r1 = r1.isItalic()
            if (r1 == 0) goto L9c
            java.lang.String r1 = "italic"
            r0.d = r1
            goto L9e
        L9c:
            r0.d = r4
        L9e:
            r0.e = r4
            r18.c()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.dm.d(em):void");
    }
}
