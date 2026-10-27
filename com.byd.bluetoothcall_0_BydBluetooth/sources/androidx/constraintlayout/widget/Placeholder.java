package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintLayout;

/* JADX INFO: loaded from: classes.dex */
public class Placeholder extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1049b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private View f1050c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f1051d;

    public Placeholder(Context context) {
        super(context);
        this.f1049b = -1;
        this.f1050c = null;
        this.f1051d = 4;
        a(null);
    }

    private void a(AttributeSet attributeSet) {
        super.setVisibility(this.f1051d);
        this.f1049b = -1;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, e.C3);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == e.D3) {
                    this.f1049b = typedArrayObtainStyledAttributes.getResourceId(index, this.f1049b);
                } else if (index == e.E3) {
                    this.f1051d = typedArrayObtainStyledAttributes.getInt(index, this.f1051d);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public void b(ConstraintLayout constraintLayout) {
        if (this.f1050c == null) {
            return;
        }
        ConstraintLayout.b bVar = (ConstraintLayout.b) getLayoutParams();
        ConstraintLayout.b bVar2 = (ConstraintLayout.b) this.f1050c.getLayoutParams();
        bVar2.v0.m1(0);
        ConstraintWidget.DimensionBehaviour dimensionBehaviourC = bVar.v0.C();
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
        if (dimensionBehaviourC != dimensionBehaviour) {
            bVar.v0.n1(bVar2.v0.Y());
        }
        if (bVar.v0.V() != dimensionBehaviour) {
            bVar.v0.O0(bVar2.v0.z());
        }
        bVar2.v0.m1(8);
    }

    public void c(ConstraintLayout constraintLayout) {
        if (this.f1049b == -1 && !isInEditMode()) {
            setVisibility(this.f1051d);
        }
        View viewFindViewById = constraintLayout.findViewById(this.f1049b);
        this.f1050c = viewFindViewById;
        if (viewFindViewById != null) {
            ((ConstraintLayout.b) viewFindViewById.getLayoutParams()).j0 = true;
            this.f1050c.setVisibility(0);
            setVisibility(0);
        }
    }

    public View getContent() {
        return this.f1050c;
    }

    public int getEmptyVisibility() {
        return this.f1051d;
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
        if (this.f1049b == i) {
            return;
        }
        View view = this.f1050c;
        if (view != null) {
            view.setVisibility(0);
            ((ConstraintLayout.b) this.f1050c.getLayoutParams()).j0 = false;
            this.f1050c = null;
        }
        this.f1049b = i;
        if (i == -1 || (viewFindViewById = ((View) getParent()).findViewById(i)) == null) {
            return;
        }
        viewFindViewById.setVisibility(8);
    }

    public void setEmptyVisibility(int i) {
        this.f1051d = i;
    }

    public Placeholder(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1049b = -1;
        this.f1050c = null;
        this.f1051d = 4;
        a(attributeSet);
    }

    public Placeholder(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1049b = -1;
        this.f1050c = null;
        this.f1051d = 4;
        a(attributeSet);
    }
}
