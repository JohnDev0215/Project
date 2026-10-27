package Z2;

import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static final boolean[] f10845A;
    public static final int[] B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public static final int[] f10846C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public static final int[] f10847D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public static final int[] f10848E;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final int f10849v = c(2, 2, 2, 0);

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final int f10850w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final int[] f10851x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final int[] f10852y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final int[] f10853z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f10854a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final SpannableStringBuilder f10855b = new SpannableStringBuilder();
    public boolean c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f10856e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f10857f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f10858g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f10859h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f10860i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f10861j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f10862k;
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f10863m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f10864n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f10865p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f10866q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f10867r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f10868s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f10869t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f10870u;

    static {
        int iC = c(0, 0, 0, 0);
        f10850w = iC;
        int iC2 = c(0, 0, 0, 3);
        f10851x = new int[]{0, 0, 0, 0, 0, 2, 0};
        f10852y = new int[]{0, 0, 0, 0, 0, 0, 2};
        f10853z = new int[]{3, 3, 3, 3, 3, 3, 1};
        f10845A = new boolean[]{false, false, false, true, true, true, false};
        B = new int[]{iC, iC2, iC, iC, iC2, iC, iC};
        f10846C = new int[]{0, 1, 2, 3, 4, 3, 4};
        f10847D = new int[]{0, 0, 0, 0, 0, 3, 3};
        f10848E = new int[]{iC, iC, iC, iC, iC, iC2, iC2};
    }

    public e() {
        d();
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int c(int r4, int r5, int r6, int r7) {
        /*
            r0 = 4
            h2.AbstractC1346c.f(r4, r0)
            h2.AbstractC1346c.f(r5, r0)
            h2.AbstractC1346c.f(r6, r0)
            h2.AbstractC1346c.f(r7, r0)
            r0 = 0
            r1 = 1
            r2 = 255(0xff, float:3.57E-43)
            if (r7 == 0) goto L1b
            if (r7 == r1) goto L1b
            r3 = 2
            if (r7 == r3) goto L1f
            r3 = 3
            if (r7 == r3) goto L1d
        L1b:
            r7 = r2
            goto L21
        L1d:
            r7 = r0
            goto L21
        L1f:
            r7 = 127(0x7f, float:1.78E-43)
        L21:
            if (r4 <= r1) goto L25
            r4 = r2
            goto L26
        L25:
            r4 = r0
        L26:
            if (r5 <= r1) goto L2a
            r5 = r2
            goto L2b
        L2a:
            r5 = r0
        L2b:
            if (r6 <= r1) goto L2e
            r0 = r2
        L2e:
            int r4 = android.graphics.Color.argb(r7, r4, r5, r0)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: Z2.e.c(int, int, int, int):int");
    }

    public final void a(char c) {
        SpannableStringBuilder spannableStringBuilder = this.f10855b;
        if (c != '\n') {
            spannableStringBuilder.append(c);
            return;
        }
        ArrayList arrayList = this.f10854a;
        arrayList.add(b());
        spannableStringBuilder.clear();
        if (this.o != -1) {
            this.o = 0;
        }
        if (this.f10865p != -1) {
            this.f10865p = 0;
        }
        if (this.f10866q != -1) {
            this.f10866q = 0;
        }
        if (this.f10868s != -1) {
            this.f10868s = 0;
        }
        while (true) {
            if (arrayList.size() < this.f10861j && arrayList.size() < 15) {
                this.f10870u = arrayList.size();
                return;
            }
            arrayList.remove(0);
        }
    }

    public final SpannableString b() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.f10855b);
        int length = spannableStringBuilder.length();
        if (length > 0) {
            if (this.o != -1) {
                spannableStringBuilder.setSpan(new StyleSpan(2), this.o, length, 33);
            }
            if (this.f10865p != -1) {
                spannableStringBuilder.setSpan(new UnderlineSpan(), this.f10865p, length, 33);
            }
            if (this.f10866q != -1) {
                spannableStringBuilder.setSpan(new ForegroundColorSpan(this.f10867r), this.f10866q, length, 33);
            }
            if (this.f10868s != -1) {
                spannableStringBuilder.setSpan(new BackgroundColorSpan(this.f10869t), this.f10868s, length, 33);
            }
        }
        return new SpannableString(spannableStringBuilder);
    }

    public final void d() {
        this.f10854a.clear();
        this.f10855b.clear();
        this.o = -1;
        this.f10865p = -1;
        this.f10866q = -1;
        this.f10868s = -1;
        this.f10870u = 0;
        this.c = false;
        this.d = false;
        this.f10856e = 4;
        this.f10857f = false;
        this.f10858g = 0;
        this.f10859h = 0;
        this.f10860i = 0;
        this.f10861j = 15;
        this.f10862k = 0;
        this.l = 0;
        this.f10863m = 0;
        int i7 = f10850w;
        this.f10864n = i7;
        this.f10867r = f10849v;
        this.f10869t = i7;
    }

    public final void e(boolean z6, boolean z10) {
        int i7 = this.o;
        SpannableStringBuilder spannableStringBuilder = this.f10855b;
        if (i7 != -1) {
            if (!z6) {
                spannableStringBuilder.setSpan(new StyleSpan(2), this.o, spannableStringBuilder.length(), 33);
                this.o = -1;
            }
        } else if (z6) {
            this.o = spannableStringBuilder.length();
        }
        if (this.f10865p == -1) {
            if (z10) {
                this.f10865p = spannableStringBuilder.length();
            }
        } else {
            if (z10) {
                return;
            }
            spannableStringBuilder.setSpan(new UnderlineSpan(), this.f10865p, spannableStringBuilder.length(), 33);
            this.f10865p = -1;
        }
    }

    public final void f(int i7, int i10) {
        int i11 = this.f10866q;
        SpannableStringBuilder spannableStringBuilder = this.f10855b;
        if (i11 != -1 && this.f10867r != i7) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan(this.f10867r), this.f10866q, spannableStringBuilder.length(), 33);
        }
        if (i7 != f10849v) {
            this.f10866q = spannableStringBuilder.length();
            this.f10867r = i7;
        }
        if (this.f10868s != -1 && this.f10869t != i10) {
            spannableStringBuilder.setSpan(new BackgroundColorSpan(this.f10869t), this.f10868s, spannableStringBuilder.length(), 33);
        }
        if (i10 != f10850w) {
            this.f10868s = spannableStringBuilder.length();
            this.f10869t = i10;
        }
    }
}
