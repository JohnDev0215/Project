package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import defpackage.vj0;

/* JADX INFO: loaded from: classes2.dex */
public class FadingShadowView extends View {
    public vj0 e;
    public int f;
    public float g;

    public FadingShadowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = 1.0f;
    }

    public final void a(int i) {
        this.e = new vj0(i);
        this.f = 0;
        postInvalidateOnAnimation();
    }

    public float getStrength() {
        return this.g;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        vj0 vj0Var = this.e;
        if (vj0Var != null) {
            int i = this.f;
            float fMax = Math.max(0.0f, Math.min(1.0f, this.g)) * getHeight();
            if (fMax < 1.0f) {
                return;
            }
            int scrollX = getScrollX();
            int right = getRight() + scrollX;
            Paint paint = vj0Var.a;
            LinearGradient linearGradient = vj0Var.c;
            Matrix matrix = vj0Var.b;
            if (i == 1) {
                int bottom = (getBottom() + getScrollY()) - getTop();
                matrix.setScale(1.0f, fMax);
                matrix.postRotate(180.0f);
                float f = scrollX;
                float f2 = bottom;
                matrix.postTranslate(f, f2);
                linearGradient.setLocalMatrix(matrix);
                paint.setShader(linearGradient);
                canvas.drawRect(f, f2 - fMax, right, f2, paint);
                return;
            }
            if (i == 0) {
                int scrollY = getScrollY();
                matrix.setScale(1.0f, fMax);
                float f3 = scrollX;
                float f4 = scrollY;
                matrix.postTranslate(f3, f4);
                linearGradient.setLocalMatrix(matrix);
                paint.setShader(linearGradient);
                canvas.drawRect(f3, f4, right, f4 + fMax, paint);
            }
        }
    }

    public void setStrength(float f) {
        if (this.g != f) {
            this.g = f;
            postInvalidateOnAnimation();
        }
    }
}
