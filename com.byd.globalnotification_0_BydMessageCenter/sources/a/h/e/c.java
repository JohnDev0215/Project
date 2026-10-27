package a.h.e;

import a.e.f;
import a.h.h.i;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"NewApi"})
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final e f724a = new d();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final f<String, Typeface> f725b = new f<>(16);

    public static class a extends i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public a.h.d.b.e f726a;

        public a(a.h.d.b.e eVar) {
            this.f726a = eVar;
        }

        @Override // a.h.h.i
        public void a(int i) {
            a.h.d.b.e eVar = this.f726a;
            if (eVar != null) {
                eVar.d(i);
            }
        }

        @Override // a.h.h.i
        public void b(Typeface typeface) {
            a.h.d.b.e eVar = this.f726a;
            if (eVar != null) {
                eVar.e(typeface);
            }
        }
    }

    public static Typeface a(Context context, Typeface typeface, int i) {
        if (context != null) {
            return Typeface.create(typeface, i);
        }
        throw new IllegalArgumentException("Context cannot be null");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Typeface b(android.content.Context r4, a.h.d.b.a r5, android.content.res.Resources r6, int r7, int r8, a.h.d.b.e r9, android.os.Handler r10, boolean r11) {
        /*
            boolean r0 = r5 instanceof a.h.d.b.d
            if (r0 == 0) goto L5c
            a.h.d.b.d r5 = (a.h.d.b.d) r5
            java.lang.String r0 = r5.f714d
            r1 = 0
            r2 = 0
            if (r0 == 0) goto L26
            boolean r3 = r0.isEmpty()
            if (r3 == 0) goto L13
            goto L26
        L13:
            android.graphics.Typeface r0 = android.graphics.Typeface.create(r0, r2)
            android.graphics.Typeface r3 = android.graphics.Typeface.DEFAULT
            android.graphics.Typeface r3 = android.graphics.Typeface.create(r3, r2)
            if (r0 == 0) goto L26
            boolean r3 = r0.equals(r3)
            if (r3 != 0) goto L26
            goto L27
        L26:
            r0 = r1
        L27:
            if (r0 == 0) goto L2f
            if (r9 == 0) goto L2e
            r9.b(r0, r10)
        L2e:
            return r0
        L2f:
            r0 = 1
            if (r11 == 0) goto L37
            int r3 = r5.f713c
            if (r3 != 0) goto L3a
            goto L39
        L37:
            if (r9 != 0) goto L3a
        L39:
            r2 = r0
        L3a:
            if (r11 == 0) goto L3f
            int r11 = r5.f712b
            goto L40
        L3f:
            r11 = -1
        L40:
            android.os.Handler r10 = a.h.d.b.e.c(r10)
            a.h.e.c$a r0 = new a.h.e.c$a
            r0.<init>(r9)
            a.h.h.e r5 = r5.f711a
            a.h.h.c r9 = new a.h.h.c
            r9.<init>(r0, r10)
            if (r2 == 0) goto L57
            android.graphics.Typeface r4 = a.h.h.f.c(r4, r5, r9, r8, r11)
            goto L70
        L57:
            android.graphics.Typeface r4 = a.h.h.f.b(r4, r5, r8, r1, r9)
            goto L70
        L5c:
            a.h.e.e r11 = a.h.e.c.f724a
            a.h.d.b.b r5 = (a.h.d.b.b) r5
            android.graphics.Typeface r4 = r11.a(r4, r5, r6, r8)
            if (r9 == 0) goto L70
            if (r4 == 0) goto L6c
            r9.b(r4, r10)
            goto L70
        L6c:
            r5 = -3
            r9.a(r5, r10)
        L70:
            if (r4 == 0) goto L7b
            a.e.f<java.lang.String, android.graphics.Typeface> r5 = a.h.e.c.f725b
            java.lang.String r6 = d(r6, r7, r8)
            r5.b(r6, r4)
        L7b:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: a.h.e.c.b(android.content.Context, a.h.d.b.a, android.content.res.Resources, int, int, a.h.d.b.e, android.os.Handler, boolean):android.graphics.Typeface");
    }

    public static Typeface c(Context context, Resources resources, int i, String str, int i2) {
        Typeface typefaceC = f724a.c(context, resources, i, str, i2);
        if (typefaceC != null) {
            f725b.b(d(resources, i, i2), typefaceC);
        }
        return typefaceC;
    }

    public static String d(Resources resources, int i, int i2) {
        return resources.getResourcePackageName(i) + "-" + i + "-" + i2;
    }
}
