package androidx.constraintlayout.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.f;

/* JADX INFO: loaded from: classes.dex */
public class ReactiveGuide extends View implements f.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1052b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f1053c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f1054d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f1055e;

    public ReactiveGuide(Context context) {
        super(context);
        this.f1052b = -1;
        this.f1053c = false;
        this.f1054d = 0;
        this.f1055e = true;
        super.setVisibility(8);
        a(null);
    }

    private void a(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, e.x3);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == e.B3) {
                    this.f1052b = typedArrayObtainStyledAttributes.getResourceId(index, this.f1052b);
                } else if (index == e.y3) {
                    this.f1053c = typedArrayObtainStyledAttributes.getBoolean(index, this.f1053c);
                } else if (index == e.A3) {
                    this.f1054d = typedArrayObtainStyledAttributes.getResourceId(index, this.f1054d);
                } else if (index == e.z3) {
                    this.f1055e = typedArrayObtainStyledAttributes.getBoolean(index, this.f1055e);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        if (this.f1052b != -1) {
            ConstraintLayout.getSharedValues().a(this.f1052b, this);
        }
    }

    @Override // android.view.View
    @SuppressLint({"MissingSuperCall"})
    public void draw(Canvas canvas) {
    }

    public int getApplyToConstraintSetId() {
        return this.f1054d;
    }

    public int getAttributeId() {
        return this.f1052b;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(0, 0);
    }

    public void setAnimateChange(boolean z) {
        this.f1053c = z;
    }

    public void setApplyToConstraintSetId(int i) {
        this.f1054d = i;
    }

    public void setAttributeId(int i) {
        f sharedValues = ConstraintLayout.getSharedValues();
        int i2 = this.f1052b;
        if (i2 != -1) {
            sharedValues.b(i2, this);
        }
        this.f1052b = i;
        if (i != -1) {
            sharedValues.a(i, this);
        }
    }

    public void setGuidelineBegin(int i) {
        ConstraintLayout.b bVar = (ConstraintLayout.b) getLayoutParams();
        bVar.f1037a = i;
        setLayoutParams(bVar);
    }

    public void setGuidelineEnd(int i) {
        ConstraintLayout.b bVar = (ConstraintLayout.b) getLayoutParams();
        bVar.f1038b = i;
        setLayoutParams(bVar);
    }

    public void setGuidelinePercent(float f) {
        ConstraintLayout.b bVar = (ConstraintLayout.b) getLayoutParams();
        bVar.f1039c = f;
        setLayoutParams(bVar);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
    }

    public ReactiveGuide(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1052b = -1;
        this.f1053c = false;
        this.f1054d = 0;
        this.f1055e = true;
        super.setVisibility(8);
        a(attributeSet);
    }

    public ReactiveGuide(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1052b = -1;
        this.f1053c = false;
        this.f1054d = 0;
        this.f1055e = true;
        super.setVisibility(8);
        a(attributeSet);
    }
}
