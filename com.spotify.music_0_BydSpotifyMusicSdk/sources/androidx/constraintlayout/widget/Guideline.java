package androidx.constraintlayout.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import p.dy;

/* JADX INFO: loaded from: classes.dex */
public class Guideline extends View {
    public boolean c;

    public Guideline(Context context) {
        super(context);
        this.c = true;
        super.setVisibility(8);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        setMeasuredDimension(0, 0);
    }

    public void setFilterRedundantCalls(boolean z) {
        this.c = z;
    }

    public void setGuidelineBegin(int i) {
        dy dyVar = (dy) getLayoutParams();
        if (this.c && dyVar.a == i) {
            return;
        }
        dyVar.a = i;
        setLayoutParams(dyVar);
    }

    public void setGuidelineEnd(int i) {
        dy dyVar = (dy) getLayoutParams();
        if (this.c && dyVar.b == i) {
            return;
        }
        dyVar.b = i;
        setLayoutParams(dyVar);
    }

    public void setGuidelinePercent(float f) {
        dy dyVar = (dy) getLayoutParams();
        if (this.c && dyVar.c == f) {
            return;
        }
        dyVar.c = f;
        setLayoutParams(dyVar);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
    }

    public Guideline(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = true;
        super.setVisibility(8);
    }
}
