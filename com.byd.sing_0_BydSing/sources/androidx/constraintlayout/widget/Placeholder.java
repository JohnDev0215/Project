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

/* JADX INFO: loaded from: classes.dex */
public class Placeholder extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1256b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private View f1257c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f1258d;

    public Placeholder(Context context) {
        super(context);
        this.f1256b = -1;
        this.f1257c = null;
        this.f1258d = 4;
        a(null);
    }

    private void a(AttributeSet attributeSet) {
        super.setVisibility(this.f1258d);
        this.f1256b = -1;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, e.M2);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == e.N2) {
                    this.f1256b = typedArrayObtainStyledAttributes.getResourceId(index, this.f1256b);
                } else if (index == e.O2) {
                    this.f1258d = typedArrayObtainStyledAttributes.getInt(index, this.f1258d);
                }
            }
        }
    }

    public void b(ConstraintLayout constraintLayout) {
        if (this.f1257c == null) {
            return;
        }
        ConstraintLayout.b bVar = (ConstraintLayout.b) getLayoutParams();
        ConstraintLayout.b bVar2 = (ConstraintLayout.b) this.f1257c.getLayoutParams();
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
        if (this.f1256b == -1 && !isInEditMode()) {
            setVisibility(this.f1258d);
        }
        View viewFindViewById = constraintLayout.findViewById(this.f1256b);
        this.f1257c = viewFindViewById;
        if (viewFindViewById != null) {
            ((ConstraintLayout.b) viewFindViewById.getLayoutParams()).b0 = true;
            this.f1257c.setVisibility(0);
            setVisibility(0);
        }
    }

    public View getContent() {
        return this.f1257c;
    }

    public int getEmptyVisibility() {
        return this.f1258d;
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

    public void setContentId(int i) {
        View viewFindViewById;
        if (this.f1256b == i) {
            return;
        }
        View view = this.f1257c;
        if (view != null) {
            view.setVisibility(0);
            ((ConstraintLayout.b) this.f1257c.getLayoutParams()).b0 = false;
            this.f1257c = null;
        }
        this.f1256b = i;
        if (i == -1 || (viewFindViewById = ((View) getParent()).findViewById(i)) == null) {
            return;
        }
        viewFindViewById.setVisibility(8);
    }

    public void setEmptyVisibility(int i) {
        this.f1258d = i;
    }

    public Placeholder(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1256b = -1;
        this.f1257c = null;
        this.f1258d = 4;
        a(attributeSet);
    }

    public Placeholder(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1256b = -1;
        this.f1257c = null;
        this.f1258d = 4;
        a(attributeSet);
    }
}
