package androidx.constraintlayout.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import r1.C2272d;

/* JADX INFO: loaded from: classes.dex */
public class Guideline extends View {
    public boolean l;

    public Guideline(Context context) {
        super(context);
        this.l = true;
        super.setVisibility(8);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i10) {
        setMeasuredDimension(0, 0);
    }

    public void setFilterRedundantCalls(boolean z6) {
        this.l = z6;
    }

    public void setGuidelineBegin(int i7) {
        C2272d c2272d = (C2272d) getLayoutParams();
        if (this.l && c2272d.f21614a == i7) {
            return;
        }
        c2272d.f21614a = i7;
        setLayoutParams(c2272d);
    }

    public void setGuidelineEnd(int i7) {
        C2272d c2272d = (C2272d) getLayoutParams();
        if (this.l && c2272d.f21616b == i7) {
            return;
        }
        c2272d.f21616b = i7;
        setLayoutParams(c2272d);
    }

    public void setGuidelinePercent(float f10) {
        C2272d c2272d = (C2272d) getLayoutParams();
        if (this.l && c2272d.c == f10) {
            return;
        }
        c2272d.c = f10;
        setLayoutParams(c2272d);
    }

    @Override // android.view.View
    public void setVisibility(int i7) {
    }

    public Guideline(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.l = true;
        super.setVisibility(8);
    }
}
