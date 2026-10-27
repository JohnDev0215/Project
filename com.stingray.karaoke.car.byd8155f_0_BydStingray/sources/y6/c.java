package y6;

import A7.m;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class c extends View {
    public x6.c l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Paint f24134m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Rect f24135n;
    public float o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f24136p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f24137q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f24138r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public d f24139s;

    public final Paint getColorPaint() {
        return this.f24134m;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        m.f(canvas, "canvas");
        if (this.f24138r) {
            return;
        }
        d dVar = this.f24139s;
        if ((dVar != null ? dVar.getLayout() : null) != null) {
            if (this.l == null) {
                m.j("lyricsConfigs");
                throw null;
            }
            int i7 = dVar.getFirstLetterPosition().x;
            int height = (int) (dVar.getHeight() * 0.7f);
            int i10 = (int) (height * 0.1f);
            int height2 = (dVar.getHeight() - height) / 2;
            this.f24137q = height2;
            float f10 = i7;
            this.f24136p = f10;
            boolean z6 = dVar.f24148s;
            Rect rect = this.f24135n;
            if (z6) {
                int width = dVar.getWidth();
                x6.c cVar = this.l;
                if (cVar == null) {
                    m.j("lyricsConfigs");
                    throw null;
                }
                float f11 = width - (cVar.d.f23801e / 2);
                int i11 = (int) (f11 - ((f11 - this.f24136p) * this.o));
                int i12 = this.f24137q;
                rect.set(i11 - i10, i12, i11, height + i12);
            } else {
                x6.c cVar2 = this.l;
                if (cVar2 == null) {
                    m.j("lyricsConfigs");
                    throw null;
                }
                float f12 = cVar2.d.f23801e / 2;
                int i13 = (int) (((f10 - f12) * this.o) + f12);
                rect.set(i13, height2, i10 + i13, height + height2);
            }
            canvas.drawRect(rect, this.f24134m);
        }
    }

    public final void setColorPaint(Paint paint) {
        m.f(paint, "<set-?>");
        this.f24134m = paint;
    }
}
