package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import b.e.b.k.e;

/* JADX INFO: loaded from: classes.dex */
public class Placeholder extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1028b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private View f1029c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f1030d;

    public Placeholder(Context context) {
        super(context);
        this.f1028b = -1;
        this.f1029c = null;
        this.f1030d = 4;
        a(null);
    }

    private void a(AttributeSet attributeSet) {
        super.setVisibility(this.f1030d);
        this.f1028b = -1;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, f.ConstraintLayout_placeholder);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == f.ConstraintLayout_placeholder_content) {
                    this.f1028b = typedArrayObtainStyledAttributes.getResourceId(index, this.f1028b);
                } else if (index == f.ConstraintLayout_placeholder_placeholder_emptyVisibility) {
                    this.f1030d = typedArrayObtainStyledAttributes.getInt(index, this.f1030d);
                }
            }
        }
    }

    public void b(ConstraintLayout constraintLayout) {
        if (this.f1029c == null) {
            return;
        }
        ConstraintLayout.b bVar = (ConstraintLayout.b) getLayoutParams();
        ConstraintLayout.b bVar2 = (ConstraintLayout.b) this.f1029c.getLayoutParams();
        bVar2.n0.E0(0);
        e.b bVarZ = bVar.n0.z();
        e.b bVar3 = e.b.FIXED;
        if (bVarZ != bVar3) {
            bVar.n0.F0(bVar2.n0.Q());
        }
        if (bVar.n0.N() != bVar3) {
            bVar.n0.i0(bVar2.n0.w());
        }
        bVar2.n0.E0(8);
    }

    public void c(ConstraintLayout constraintLayout) {
        if (this.f1028b == -1 && !isInEditMode()) {
            setVisibility(this.f1030d);
        }
        View viewFindViewById = constraintLayout.findViewById(this.f1028b);
        this.f1029c = viewFindViewById;
        if (viewFindViewById != null) {
            ((ConstraintLayout.b) viewFindViewById.getLayoutParams()).b0 = true;
            this.f1029c.setVisibility(0);
            setVisibility(0);
        }
    }

    public View getContent() {
        return this.f1029c;
    }

    public int getEmptyVisibility() {
        return this.f1030d;
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
        if (this.f1028b == i2) {
            return;
        }
        View view = this.f1029c;
        if (view != null) {
            view.setVisibility(0);
            ((ConstraintLayout.b) this.f1029c.getLayoutParams()).b0 = false;
            this.f1029c = null;
        }
        this.f1028b = i2;
        if (i2 == -1 || (viewFindViewById = ((View) getParent()).findViewById(i2)) == null) {
            return;
        }
        viewFindViewById.setVisibility(8);
    }

    public void setEmptyVisibility(int i2) {
        this.f1030d = i2;
    }

    public Placeholder(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1028b = -1;
        this.f1029c = null;
        this.f1030d = 4;
        a(attributeSet);
    }

    public Placeholder(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f1028b = -1;
        this.f1029c = null;
        this.f1030d = 4;
        a(attributeSet);
    }
}
