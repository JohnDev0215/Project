package O0;

import android.text.Layout;
import android.text.TextDirectionHeuristic;
import android.text.TextPaint;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public final class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CharSequence f5027a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f5028b;
    public final int c;
    public final TextPaint d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f5029e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final TextDirectionHeuristic f5030f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Layout.Alignment f5031g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f5032h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final TextUtils.TruncateAt f5033i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f5034j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final float f5035k;
    public final float l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f5036m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f5037n;
    public final boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f5038p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f5039q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f5040r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f5041s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final int[] f5042t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final int[] f5043u;

    public u(CharSequence charSequence, int i7, int i10, TextPaint textPaint, int i11, TextDirectionHeuristic textDirectionHeuristic, Layout.Alignment alignment, int i12, TextUtils.TruncateAt truncateAt, int i13, float f10, float f11, int i14, boolean z6, boolean z10, int i15, int i16, int i17, int i18, int[] iArr, int[] iArr2) {
        this.f5027a = charSequence;
        this.f5028b = i7;
        this.c = i10;
        this.d = textPaint;
        this.f5029e = i11;
        this.f5030f = textDirectionHeuristic;
        this.f5031g = alignment;
        this.f5032h = i12;
        this.f5033i = truncateAt;
        this.f5034j = i13;
        this.f5035k = f10;
        this.l = f11;
        this.f5036m = i14;
        this.f5037n = z6;
        this.o = z10;
        this.f5038p = i15;
        this.f5039q = i16;
        this.f5040r = i17;
        this.f5041s = i18;
        this.f5042t = iArr;
        this.f5043u = iArr2;
        if (i7 < 0 || i7 > i10) {
            throw new IllegalArgumentException("invalid start value");
        }
        int length = charSequence.length();
        if (i10 < 0 || i10 > length) {
            throw new IllegalArgumentException("invalid end value");
        }
        if (i12 < 0) {
            throw new IllegalArgumentException("invalid maxLines value");
        }
        if (i11 < 0) {
            throw new IllegalArgumentException("invalid width value");
        }
        if (i13 < 0) {
            throw new IllegalArgumentException("invalid ellipsizedWidth value");
        }
        if (f10 < 0.0f) {
            throw new IllegalArgumentException("invalid lineSpacingMultiplier value");
        }
    }
}
