package androidx.constraintlayout.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.b;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public class ReactiveGuide extends View implements b.a {
    public int e;
    public boolean f;
    public int g;
    public boolean h;

    public ReactiveGuide(Context context) {
        super(context);
        this.e = -1;
        this.f = false;
        this.g = 0;
        this.h = true;
        super.setVisibility(8);
        a(null);
    }

    public final void a(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_ReactiveGuide);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.ConstraintLayout_ReactiveGuide_reactiveGuide_valueId) {
                    this.e = typedArrayObtainStyledAttributes.getResourceId(index, this.e);
                } else if (index == R$styleable.ConstraintLayout_ReactiveGuide_reactiveGuide_animateChange) {
                    this.f = typedArrayObtainStyledAttributes.getBoolean(index, this.f);
                } else if (index == R$styleable.ConstraintLayout_ReactiveGuide_reactiveGuide_applyToConstraintSet) {
                    this.g = typedArrayObtainStyledAttributes.getResourceId(index, this.g);
                } else if (index == R$styleable.ConstraintLayout_ReactiveGuide_reactiveGuide_applyToAllConstraintSets) {
                    this.h = typedArrayObtainStyledAttributes.getBoolean(index, this.h);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        if (this.e != -1) {
            ConstraintLayout.getSharedValues().a(this.e, this);
        }
    }

    public int getApplyToConstraintSetId() {
        return this.g;
    }

    public int getAttributeId() {
        return this.e;
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        setMeasuredDimension(0, 0);
    }

    public void setAnimateChange(boolean z) {
        this.f = z;
    }

    public void setApplyToConstraintSetId(int i) {
        this.g = i;
    }

    public void setAttributeId(int i) {
        HashSet<WeakReference<b.a>> hashSet;
        b sharedValues = ConstraintLayout.getSharedValues();
        int i2 = this.e;
        if (i2 != -1 && (hashSet = sharedValues.a.get(Integer.valueOf(i2))) != null) {
            ArrayList arrayList = new ArrayList();
            for (WeakReference<b.a> weakReference : hashSet) {
                b.a aVar = weakReference.get();
                if (aVar == null || aVar == this) {
                    arrayList.add(weakReference);
                }
            }
            hashSet.removeAll(arrayList);
        }
        this.e = i;
        if (i != -1) {
            sharedValues.a(i, this);
        }
    }

    public void setGuidelineBegin(int i) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) getLayoutParams();
        layoutParams.a = i;
        setLayoutParams(layoutParams);
    }

    public void setGuidelineEnd(int i) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) getLayoutParams();
        layoutParams.b = i;
        setLayoutParams(layoutParams);
    }

    public void setGuidelinePercent(float f) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) getLayoutParams();
        layoutParams.c = f;
        setLayoutParams(layoutParams);
    }

    public ReactiveGuide(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = -1;
        this.f = false;
        this.g = 0;
        this.h = true;
        super.setVisibility(8);
        a(attributeSet);
    }

    public ReactiveGuide(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = -1;
        this.f = false;
        this.g = 0;
        this.h = true;
        super.setVisibility(8);
        a(attributeSet);
    }

    @Override // android.view.View
    @SuppressLint({"MissingSuperCall"})
    public final void draw(Canvas canvas) {
    }

    @Override // android.view.View
    public void setVisibility(int i) {
    }
}
