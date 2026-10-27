package androidx.constraintlayout.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import r.e;

/* JADX INFO: compiled from: Placeholder.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends View {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f1878e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private View f1879g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f1880h;

    public void a(ConstraintLayout constraintLayout) {
        if (this.f1879g == null) {
            return;
        }
        ConstraintLayout.b bVar = (ConstraintLayout.b) getLayoutParams();
        ConstraintLayout.b bVar2 = (ConstraintLayout.b) this.f1879g.getLayoutParams();
        bVar2.f1715v0.m1(0);
        e.b bVarC = bVar.f1715v0.C();
        e.b bVar3 = e.b.FIXED;
        if (bVarC != bVar3) {
            bVar.f1715v0.n1(bVar2.f1715v0.Y());
        }
        if (bVar.f1715v0.V() != bVar3) {
            bVar.f1715v0.O0(bVar2.f1715v0.z());
        }
        bVar2.f1715v0.m1(8);
    }

    public void b(ConstraintLayout constraintLayout) {
        if (this.f1878e == -1 && !isInEditMode()) {
            setVisibility(this.f1880h);
        }
        View viewFindViewById = constraintLayout.findViewById(this.f1878e);
        this.f1879g = viewFindViewById;
        if (viewFindViewById != null) {
            ((ConstraintLayout.b) viewFindViewById.getLayoutParams()).f1691j0 = true;
            this.f1879g.setVisibility(0);
            setVisibility(0);
        }
    }

    public View getContent() {
        return this.f1879g;
    }

    public int getEmptyVisibility() {
        return this.f1880h;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (isInEditMode()) {
            canvas.drawRGB(223, 223, 223);
            Paint paint = new Paint();
            paint.setARGB(255, 210, 210, 210);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTypeface(Typeface.create(Typeface.DEFAULT, 0));
            Rect rect = new Rect();
            canvas.getClipBounds(rect);
            paint.setTextSize(rect.height());
            int iHeight = rect.height();
            int iWidth = rect.width();
            paint.setTextAlign(Paint.Align.LEFT);
            paint.getTextBounds("?", 0, 1, rect);
            canvas.drawText("?", ((iWidth / 2.0f) - (rect.width() / 2.0f)) - rect.left, ((iHeight / 2.0f) + (rect.height() / 2.0f)) - rect.bottom, paint);
        }
    }

    public void setContentId(int i7) {
        View viewFindViewById;
        if (this.f1878e == i7) {
            return;
        }
        View view = this.f1879g;
        if (view != null) {
            view.setVisibility(0);
            ((ConstraintLayout.b) this.f1879g.getLayoutParams()).f1691j0 = false;
            this.f1879g = null;
        }
        this.f1878e = i7;
        if (i7 == -1 || (viewFindViewById = ((View) getParent()).findViewById(i7)) == null) {
            return;
        }
        viewFindViewById.setVisibility(8);
    }

    public void setEmptyVisibility(int i7) {
        this.f1880h = i7;
    }
}
