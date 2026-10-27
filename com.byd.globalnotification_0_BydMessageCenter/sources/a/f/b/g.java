package a.f.b;

import a.f.a.i.d;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;

/* JADX INFO: loaded from: classes.dex */
public class g extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f693a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public View f694b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f695c;

    public void a() {
        d.a aVar = d.a.FIXED;
        if (this.f694b == null) {
            return;
        }
        ConstraintLayout.a aVar2 = (ConstraintLayout.a) getLayoutParams();
        ConstraintLayout.a aVar3 = (ConstraintLayout.a) this.f694b.getLayoutParams();
        aVar3.r0.k0 = 0;
        if (aVar2.r0.m() != aVar) {
            aVar2.r0.S(aVar3.r0.u());
        }
        if (aVar2.r0.t() != aVar) {
            aVar2.r0.N(aVar3.r0.l());
        }
        aVar3.r0.k0 = 8;
    }

    public View getContent() {
        return this.f694b;
    }

    public int getEmptyVisibility() {
        return this.f695c;
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
            canvas.drawText("?", ((iWidth / 2.0f) - (rect.width() / 2.0f)) - rect.left, ((rect.height() / 2.0f) + (iHeight / 2.0f)) - rect.bottom, paint);
        }
    }

    public void setContentId(int i) {
        View viewFindViewById;
        if (this.f693a == i) {
            return;
        }
        View view = this.f694b;
        if (view != null) {
            view.setVisibility(0);
            ((ConstraintLayout.a) this.f694b.getLayoutParams()).f0 = false;
            this.f694b = null;
        }
        this.f693a = i;
        if (i == -1 || (viewFindViewById = ((View) getParent()).findViewById(i)) == null) {
            return;
        }
        viewFindViewById.setVisibility(8);
    }

    public void setEmptyVisibility(int i) {
        this.f695c = i;
    }
}
