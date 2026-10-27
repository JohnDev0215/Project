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

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1155d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public View f1156e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1157f;

    public Placeholder(Context context) {
        super(context);
        this.f1155d = -1;
        this.f1156e = null;
        this.f1157f = 4;
        a(null);
    }

    public final void a(AttributeSet attributeSet) {
        super.setVisibility(this.f1157f);
        this.f1155d = -1;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_placeholder);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i8 = 0; i8 < indexCount; i8++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i8);
                if (index == R$styleable.ConstraintLayout_placeholder_content) {
                    this.f1155d = typedArrayObtainStyledAttributes.getResourceId(index, this.f1155d);
                } else if (index == R$styleable.ConstraintLayout_placeholder_placeholder_emptyVisibility) {
                    this.f1157f = typedArrayObtainStyledAttributes.getInt(index, this.f1157f);
                }
            }
        }
    }

    public void b(ConstraintLayout constraintLayout) {
        if (this.f1156e == null) {
            return;
        }
        ConstraintLayout.b bVar = (ConstraintLayout.b) getLayoutParams();
        ConstraintLayout.b bVar2 = (ConstraintLayout.b) this.f1156e.getLayoutParams();
        bVar2.f1120n0.E0(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviourZ = bVar.f1120n0.z();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
        if (dimensionBehaviourZ != dimensionBehaviour) {
            bVar.f1120n0.F0(bVar2.f1120n0.Q());
        }
        if (bVar.f1120n0.N() != dimensionBehaviour) {
            bVar.f1120n0.i0(bVar2.f1120n0.w());
        }
        bVar2.f1120n0.E0(8);
    }

    public void c(ConstraintLayout constraintLayout) {
        if (this.f1155d == -1 && !isInEditMode()) {
            setVisibility(this.f1157f);
        }
        View viewFindViewById = constraintLayout.findViewById(this.f1155d);
        this.f1156e = viewFindViewById;
        if (viewFindViewById != null) {
            ((ConstraintLayout.b) viewFindViewById.getLayoutParams()).f1096b0 = true;
            this.f1156e.setVisibility(0);
            setVisibility(0);
        }
    }

    public View getContent() {
        return this.f1156e;
    }

    public int getEmptyVisibility() {
        return this.f1157f;
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

    public void setContentId(int i8) {
        View viewFindViewById;
        if (this.f1155d == i8) {
            return;
        }
        View view = this.f1156e;
        if (view != null) {
            view.setVisibility(0);
            ((ConstraintLayout.b) this.f1156e.getLayoutParams()).f1096b0 = false;
            this.f1156e = null;
        }
        this.f1155d = i8;
        if (i8 == -1 || (viewFindViewById = ((View) getParent()).findViewById(i8)) == null) {
            return;
        }
        viewFindViewById.setVisibility(8);
    }

    public void setEmptyVisibility(int i8) {
        this.f1157f = i8;
    }

    public Placeholder(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1155d = -1;
        this.f1156e = null;
        this.f1157f = 4;
        a(attributeSet);
    }

    public Placeholder(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f1155d = -1;
        this.f1156e = null;
        this.f1157f = 4;
        a(attributeSet);
    }
}
