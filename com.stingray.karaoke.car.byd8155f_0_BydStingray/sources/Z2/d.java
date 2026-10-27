package Z2;

import android.text.Layout;
import android.text.SpannableStringBuilder;
import g2.C1289b;

/* JADX INFO: loaded from: classes.dex */
public final class d {
    public static final C1.b c = new C1.b(5);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1289b f10843a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f10844b;

    public d(SpannableStringBuilder spannableStringBuilder, Layout.Alignment alignment, float f10, int i7, float f11, int i10, boolean z6, int i11, int i12) {
        boolean z10;
        int i13;
        if (z6) {
            i13 = i11;
            z10 = true;
        } else {
            z10 = false;
            i13 = -16777216;
        }
        this.f10843a = new C1289b(spannableStringBuilder, alignment, null, null, f10, 0, i7, f11, i10, Integer.MIN_VALUE, -3.4028235E38f, -3.4028235E38f, -3.4028235E38f, z10, i13, Integer.MIN_VALUE, 0.0f);
        this.f10844b = i12;
    }
}
