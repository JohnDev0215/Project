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
    private int f737b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private View f738c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f739d;

    public Placeholder(Context context) {
        super(context);
        this.f737b = -1;
        this.f738c = null;
        this.f739d = 4;
        a(null);
    }

    private void a(AttributeSet attributeSet) {
        super.setVisibility(this.f739d);
        this.f737b = -1;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, e.M2);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == e.N2) {
                    this.f737b = typedArrayObtainStyledAttributes.getResourceId(index, this.f737b);
                } else if (index == e.O2) {
                    this.f739d = typedArrayObtainStyledAttributes.getInt(index, this.f739d);
                }
            }
        }
    }

    public void b(ConstraintLayout constraintLayout) {
        if (this.f738c == null) {
            return;
        }
        ConstraintLayout.b bVar = (ConstraintLayout.b) getLayoutParams();
        ConstraintLayout.b bVar2 = (ConstraintLayout.b) this.f738c.getLayoutParams();
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
        if (this.f737b == -1 && !isInEditMode()) {
            setVisibility(this.f739d);
        }
        View viewFindViewById = constraintLayout.findViewById(this.f737b);
        this.f738c = viewFindViewById;
        if (viewFindViewById != null) {
            ((ConstraintLayout.b) viewFindViewById.getLayoutParams()).b0 = true;
            this.f738c.setVisibility(0);
            setVisibility(0);
        }
    }

    public View getContent() {
        return this.f738c;
    }

    public int getEmptyVisibility() {
        return this.f739d;
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
        if (this.f737b == i) {
            return;
        }
        View view = this.f738c;
        if (view != null) {
            view.setVisibility(0);
            ((ConstraintLayout.b) this.f738c.getLayoutParams()).b0 = false;
            this.f738c = null;
        }
        this.f737b = i;
        if (i == -1 || (viewFindViewById = ((View) getParent()).findViewById(i)) == null) {
            return;
        }
        viewFindViewById.setVisibility(8);
    }

    public void setEmptyVisibility(int i) {
        this.f739d = i;
    }

    public Placeholder(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f737b = -1;
        this.f738c = null;
        this.f739d = 4;
        a(attributeSet);
    }

    public Placeholder(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f737b = -1;
        this.f738c = null;
        this.f739d = 4;
        a(attributeSet);
    }
}
