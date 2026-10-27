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
    public int f1064b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public View f1065c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1066d;

    public Placeholder(Context context) {
        super(context);
        this.f1064b = -1;
        this.f1065c = null;
        this.f1066d = 4;
        a(null);
    }

    public final void a(AttributeSet attributeSet) {
        super.setVisibility(this.f1066d);
        this.f1064b = -1;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_placeholder);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i3);
                if (index == R$styleable.ConstraintLayout_placeholder_content) {
                    this.f1064b = typedArrayObtainStyledAttributes.getResourceId(index, this.f1064b);
                } else if (index == R$styleable.ConstraintLayout_placeholder_placeholder_emptyVisibility) {
                    this.f1066d = typedArrayObtainStyledAttributes.getInt(index, this.f1066d);
                }
            }
        }
    }

    public void b(ConstraintLayout constraintLayout) {
        if (this.f1065c == null) {
            return;
        }
        ConstraintLayout.b bVar = (ConstraintLayout.b) getLayoutParams();
        ConstraintLayout.b bVar2 = (ConstraintLayout.b) this.f1065c.getLayoutParams();
        bVar2.f1029n0.E0(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviourZ = bVar.f1029n0.z();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
        if (dimensionBehaviourZ != dimensionBehaviour) {
            bVar.f1029n0.F0(bVar2.f1029n0.Q());
        }
        if (bVar.f1029n0.N() != dimensionBehaviour) {
            bVar.f1029n0.i0(bVar2.f1029n0.w());
        }
        bVar2.f1029n0.E0(8);
    }

    public void c(ConstraintLayout constraintLayout) {
        if (this.f1064b == -1 && !isInEditMode()) {
            setVisibility(this.f1066d);
        }
        View viewFindViewById = constraintLayout.findViewById(this.f1064b);
        this.f1065c = viewFindViewById;
        if (viewFindViewById != null) {
            ((ConstraintLayout.b) viewFindViewById.getLayoutParams()).f1005b0 = true;
            this.f1065c.setVisibility(0);
            setVisibility(0);
        }
    }

    public View getContent() {
        return this.f1065c;
    }

    public int getEmptyVisibility() {
        return this.f1066d;
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

    public void setContentId(int i3) {
        View viewFindViewById;
        if (this.f1064b == i3) {
            return;
        }
        View view = this.f1065c;
        if (view != null) {
            view.setVisibility(0);
            ((ConstraintLayout.b) this.f1065c.getLayoutParams()).f1005b0 = false;
            this.f1065c = null;
        }
        this.f1064b = i3;
        if (i3 == -1 || (viewFindViewById = ((View) getParent()).findViewById(i3)) == null) {
            return;
        }
        viewFindViewById.setVisibility(8);
    }

    public void setEmptyVisibility(int i3) {
        this.f1066d = i3;
    }

    public Placeholder(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1064b = -1;
        this.f1065c = null;
        this.f1066d = 4;
        a(attributeSet);
    }

    public Placeholder(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        this.f1064b = -1;
        this.f1065c = null;
        this.f1066d = 4;
        a(attributeSet);
    }
}
