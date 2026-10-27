package O0;

import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.TextPaint;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextPaint f5047a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f5048b;
    public final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public A2.g f5049e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Layout f5050f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f5051g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f5052h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f5053i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final float f5054j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final float f5055k;
    public final boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Paint.FontMetricsInt f5056m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f5057n;
    public final Q0.h[] o;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public N0.q f5059q;
    public final boolean c = true;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Rect f5058p = new Rect();

    /* JADX WARN: Removed duplicated region for block: B:84:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01d7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public x(java.lang.CharSequence r39, float r40, android.text.TextPaint r41, int r42, android.text.TextUtils.TruncateAt r43, int r44, boolean r45, int r46, int r47, int r48, int r49, int r50, int r51, O0.m r52) {
        /*
            Method dump skipped, instruction units count: 832
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: O0.x.<init>(java.lang.CharSequence, float, android.text.TextPaint, int, android.text.TextUtils$TruncateAt, int, boolean, int, int, int, int, int, int, O0.m):void");
    }

    public final int a() {
        boolean z6 = this.d;
        Layout layout = this.f5050f;
        return (z6 ? layout.getLineBottom(this.f5051g - 1) : layout.getHeight()) + this.f5052h + this.f5053i + this.f5057n;
    }

    public final float b(int i7) {
        if (i7 == this.f5051g - 1) {
            return this.f5054j + this.f5055k;
        }
        return 0.0f;
    }

    public final N0.q c() {
        N0.q qVar = this.f5059q;
        if (qVar != null) {
            return qVar;
        }
        Layout layout = this.f5050f;
        N0.q qVar2 = new N0.q();
        qVar2.f4399m = layout;
        ArrayList arrayList = new ArrayList();
        int length = 0;
        do {
            int iJ0 = Q8.h.J0(((Layout) qVar2.f4399m).getText(), '\n', length, false, 4);
            length = iJ0 < 0 ? ((Layout) qVar2.f4399m).getText().length() : iJ0 + 1;
            arrayList.add(Integer.valueOf(length));
        } while (length < ((Layout) qVar2.f4399m).getText().length());
        qVar2.l = arrayList;
        int size = arrayList.size();
        ArrayList arrayList2 = new ArrayList(size);
        for (int i7 = 0; i7 < size; i7++) {
            arrayList2.add(null);
        }
        qVar2.f4400n = arrayList2;
        qVar2.o = new boolean[((ArrayList) qVar2.l).size()];
        ((ArrayList) qVar2.l).size();
        this.f5059q = qVar2;
        return qVar2;
    }

    public final float d(int i7) {
        Paint.FontMetricsInt fontMetricsInt;
        return this.f5052h + ((i7 != this.f5051g + (-1) || (fontMetricsInt = this.f5056m) == null) ? this.f5050f.getLineBaseline(i7) : g(i7) - fontMetricsInt.ascent);
    }

    public final float e(int i7) {
        Paint.FontMetricsInt fontMetricsInt;
        int i10 = this.f5051g;
        int i11 = i10 - 1;
        Layout layout = this.f5050f;
        if (i7 != i11 || (fontMetricsInt = this.f5056m) == null) {
            return this.f5052h + layout.getLineBottom(i7) + (i7 == i10 + (-1) ? this.f5053i : 0);
        }
        return layout.getLineBottom(i7 - 1) + fontMetricsInt.bottom;
    }

    public final int f(int i7) {
        Layout layout = this.f5050f;
        return layout.getEllipsisStart(i7) == 0 ? layout.getLineEnd(i7) : layout.getText().length();
    }

    public final float g(int i7) {
        return this.f5050f.getLineTop(i7) + (i7 == 0 ? 0 : this.f5052h);
    }

    public final float h(int i7, boolean z6) {
        return b(this.f5050f.getLineForOffset(i7)) + c().B(i7, true, z6);
    }

    public final float i(int i7, boolean z6) {
        return b(this.f5050f.getLineForOffset(i7)) + c().B(i7, false, z6);
    }

    public final A2.g j() {
        A2.g gVar = this.f5049e;
        if (gVar != null) {
            return gVar;
        }
        Layout layout = this.f5050f;
        A2.g gVar2 = new A2.g(layout.getText(), layout.getText().length(), this.f5047a.getTextLocale());
        this.f5049e = gVar2;
        return gVar2;
    }
}
