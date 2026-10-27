package Z2;

import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import g2.C1289b;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f10814a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f10815b;
    public final StringBuilder c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f10816e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f10817f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f10818g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f10819h;

    public b(int i7, int i10) {
        ArrayList arrayList = new ArrayList();
        this.f10814a = arrayList;
        ArrayList arrayList2 = new ArrayList();
        this.f10815b = arrayList2;
        StringBuilder sb = new StringBuilder();
        this.c = sb;
        this.f10818g = i7;
        arrayList.clear();
        arrayList2.clear();
        sb.setLength(0);
        this.d = 15;
        this.f10816e = 0;
        this.f10817f = 0;
        this.f10819h = i10;
    }

    public final void a(char c) {
        StringBuilder sb = this.c;
        if (sb.length() < 32) {
            sb.append(c);
        }
    }

    public final void b() {
        StringBuilder sb = this.c;
        int length = sb.length();
        if (length > 0) {
            sb.delete(length - 1, length);
            ArrayList arrayList = this.f10814a;
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                a aVar = (a) arrayList.get(size);
                int i7 = aVar.c;
                if (i7 != length) {
                    return;
                }
                aVar.c = i7 - 1;
            }
        }
    }

    public final C1289b c(int i7) {
        float f10;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        int i10 = 0;
        while (true) {
            ArrayList arrayList = this.f10815b;
            if (i10 >= arrayList.size()) {
                break;
            }
            spannableStringBuilder.append((CharSequence) arrayList.get(i10));
            spannableStringBuilder.append('\n');
            i10++;
        }
        spannableStringBuilder.append((CharSequence) d());
        if (spannableStringBuilder.length() == 0) {
            return null;
        }
        int i11 = this.f10816e + this.f10817f;
        int length = (32 - i11) - spannableStringBuilder.length();
        int i12 = i11 - length;
        int i13 = i7;
        if (i13 == Integer.MIN_VALUE) {
            i13 = (this.f10818g != 2 || (Math.abs(i12) >= 3 && length >= 0)) ? (this.f10818g != 2 || i12 <= 0) ? 0 : 2 : 1;
        }
        if (i13 != 1) {
            if (i13 == 2) {
                i11 = 32 - length;
            }
            f10 = ((i11 / 32.0f) * 0.8f) + 0.1f;
        } else {
            f10 = 0.5f;
        }
        int i14 = this.d;
        if (i14 > 7) {
            i14 -= 17;
        } else if (this.f10818g == 1) {
            i14 -= this.f10819h - 1;
        }
        return new C1289b(spannableStringBuilder, Layout.Alignment.ALIGN_NORMAL, null, null, i14, 1, Integer.MIN_VALUE, f10, i13, Integer.MIN_VALUE, -3.4028235E38f, -3.4028235E38f, -3.4028235E38f, false, -16777216, Integer.MIN_VALUE, 0.0f);
    }

    public final SpannableString d() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.c);
        int length = spannableStringBuilder.length();
        int i7 = -1;
        int i10 = -1;
        int i11 = -1;
        int i12 = -1;
        int i13 = 0;
        int i14 = 0;
        boolean z6 = false;
        while (true) {
            ArrayList arrayList = this.f10814a;
            if (i13 >= arrayList.size()) {
                break;
            }
            a aVar = (a) arrayList.get(i13);
            boolean z10 = aVar.f10813b;
            int i15 = aVar.f10812a;
            if (i15 != 8) {
                boolean z11 = i15 == 7;
                if (i15 != 7) {
                    i12 = c.B[i15];
                }
                z6 = z11;
            }
            int i16 = aVar.c;
            i13++;
            if (i16 != (i13 < arrayList.size() ? ((a) arrayList.get(i13)).c : length)) {
                if (i7 != -1 && !z10) {
                    spannableStringBuilder.setSpan(new UnderlineSpan(), i7, i16, 33);
                    i7 = -1;
                } else if (i7 == -1 && z10) {
                    i7 = i16;
                }
                if (i10 != -1 && !z6) {
                    spannableStringBuilder.setSpan(new StyleSpan(2), i10, i16, 33);
                    i10 = -1;
                } else if (i10 == -1 && z6) {
                    i10 = i16;
                }
                if (i12 != i11) {
                    if (i11 != -1) {
                        spannableStringBuilder.setSpan(new ForegroundColorSpan(i11), i14, i16, 33);
                    }
                    i11 = i12;
                    i14 = i16;
                }
            }
        }
        if (i7 != -1 && i7 != length) {
            spannableStringBuilder.setSpan(new UnderlineSpan(), i7, length, 33);
        }
        if (i10 != -1 && i10 != length) {
            spannableStringBuilder.setSpan(new StyleSpan(2), i10, length, 33);
        }
        if (i14 != length && i11 != -1) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan(i11), i14, length, 33);
        }
        return new SpannableString(spannableStringBuilder);
    }

    public final boolean e() {
        return this.f10814a.isEmpty() && this.f10815b.isEmpty() && this.c.length() == 0;
    }
}
