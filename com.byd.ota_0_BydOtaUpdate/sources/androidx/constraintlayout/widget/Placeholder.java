package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintLayout;
import b.e.c.e;

/* JADX INFO: loaded from: classes.dex */
public class Placeholder extends View {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f417c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View f418d;
    public int s;

    public Placeholder(Context context) {
        super(context);
        this.f417c = -1;
        this.f418d = null;
        this.s = 4;
        a(null);
    }

    public final void a(AttributeSet attributeSet) {
        super.setVisibility(this.s);
        this.f417c = -1;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, e.M2);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == e.N2) {
                    this.f417c = typedArrayObtainStyledAttributes.getResourceId(index, this.f417c);
                } else if (index == e.O2) {
                    this.s = typedArrayObtainStyledAttributes.getInt(index, this.s);
                }
            }
        }
    }

    public void b(ConstraintLayout constraintLayout) {
        if (this.f418d == null) {
            return;
        }
        ConstraintLayout.b bVar = (ConstraintLayout.b) getLayoutParams();
        ConstraintLayout.b bVar2 = (ConstraintLayout.b) this.f418d.getLayoutParams();
        bVar2.n0.E0(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviourZ = bVar.n0.z();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
        if (dimensionBehaviourZ != dimensionBehaviour) {
            bVar.n0.F0(bVar2.n0.Q());
        }
        if (bVar.n0.N() != dimensionBehaviour) {
            bVar.n0.i0(bVar2.n0.w());
        }
        bVar2.n0.E0(8);
    }

    public void c(ConstraintLayout constraintLayout) {
        if (this.f417c == -1 && !isInEditMode()) {
            setVisibility(this.s);
        }
        View viewFindViewById = constraintLayout.findViewById(this.f417c);
        this.f418d = viewFindViewById;
        if (viewFindViewById != null) {
            ((ConstraintLayout.b) viewFindViewById.getLayoutParams()).b0 = true;
            this.f418d.setVisibility(0);
            setVisibility(0);
        }
    }

    public View getContent() {
        return this.f418d;
    }

    public int getEmptyVisibility() {
        return this.s;
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

    public void setContentId(int i2) {
        View viewFindViewById;
        if (this.f417c == i2) {
            return;
        }
        View view = this.f418d;
        if (view != null) {
            view.setVisibility(0);
            ((ConstraintLayout.b) this.f418d.getLayoutParams()).b0 = false;
            this.f418d = null;
        }
        this.f417c = i2;
        if (i2 == -1 || (viewFindViewById = ((View) getParent()).findViewById(i2)) == null) {
            return;
        }
        viewFindViewById.setVisibility(8);
    }

    public void setEmptyVisibility(int i2) {
        this.s = i2;
    }

    public Placeholder(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f417c = -1;
        this.f418d = null;
        this.s = 4;
        a(attributeSet);
    }

    public Placeholder(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f417c = -1;
        this.f418d = null;
        this.s = 4;
        a(attributeSet);
    }
}
