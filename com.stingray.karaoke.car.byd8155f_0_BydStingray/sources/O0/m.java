package O0;

import android.os.Build;
import android.text.BoringLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextPaint;

/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CharSequence f5021a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final TextPaint f5022b;
    public final int c;
    public float d = Float.NaN;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f5023e = Float.NaN;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public BoringLayout.Metrics f5024f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f5025g;

    public m(CharSequence charSequence, TextPaint textPaint, int i7) {
        this.f5021a = charSequence;
        this.f5022b = textPaint;
        this.c = i7;
    }

    public final BoringLayout.Metrics a() {
        if (!this.f5025g) {
            TextDirectionHeuristic textDirectionHeuristicA = y.a(this.c);
            int i7 = Build.VERSION.SDK_INT;
            CharSequence charSequence = this.f5021a;
            TextPaint textPaint = this.f5022b;
            this.f5024f = i7 >= 33 ? c.b(charSequence, textPaint, textDirectionHeuristicA) : d.b(charSequence, textPaint, textDirectionHeuristicA);
            this.f5025g = true;
        }
        return this.f5024f;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final float b() {
        /*
            r7 = this;
            float r0 = r7.d
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto Lb
            float r7 = r7.d
            goto L58
        Lb:
            android.text.BoringLayout$Metrics r0 = r7.a()
            if (r0 == 0) goto L14
            int r0 = r0.width
            goto L15
        L14:
            r0 = -1
        L15:
            float r0 = (float) r0
            r1 = 0
            int r2 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            android.text.TextPaint r3 = r7.f5022b
            java.lang.CharSequence r4 = r7.f5021a
            if (r2 >= 0) goto L2e
            r0 = 0
            int r2 = r4.length()
            float r0 = android.text.Layout.getDesiredWidth(r4, r0, r2, r3)
            double r5 = (double) r0
            double r5 = java.lang.Math.ceil(r5)
            float r0 = (float) r5
        L2e:
            int r2 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r2 != 0) goto L33
            goto L55
        L33:
            boolean r2 = r4 instanceof android.text.Spanned
            if (r2 == 0) goto L49
            android.text.Spanned r4 = (android.text.Spanned) r4
            java.lang.Class<Q0.f> r2 = Q0.f.class
            boolean r2 = O0.s.e(r4, r2)
            if (r2 != 0) goto L52
            java.lang.Class<Q0.e> r2 = Q0.e.class
            boolean r2 = O0.s.e(r4, r2)
            if (r2 != 0) goto L52
        L49:
            float r2 = r3.getLetterSpacing()
            int r1 = (r2 > r1 ? 1 : (r2 == r1 ? 0 : -1))
            if (r1 != 0) goto L52
            goto L55
        L52:
            r1 = 1056964608(0x3f000000, float:0.5)
            float r0 = r0 + r1
        L55:
            r7.d = r0
            r7 = r0
        L58:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: O0.m.b():float");
    }
}
