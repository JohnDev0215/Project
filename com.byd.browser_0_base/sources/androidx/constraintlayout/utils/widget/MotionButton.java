package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class MotionButton extends AppCompatButton {
    public float h;
    public float i;
    public Path j;
    public ViewOutlineProvider k;
    public RectF l;

    public class a extends ViewOutlineProvider {
        public a() {
        }

        @Override // android.view.ViewOutlineProvider
        public final void getOutline(View view, Outline outline) {
            MotionButton motionButton = MotionButton.this;
            outline.setRoundRect(0, 0, motionButton.getWidth(), motionButton.getHeight(), (Math.min(r3, r4) * motionButton.h) / 2.0f);
        }
    }

    public class b extends ViewOutlineProvider {
        public b() {
        }

        @Override // android.view.ViewOutlineProvider
        public final void getOutline(View view, Outline outline) {
            MotionButton motionButton = MotionButton.this;
            outline.setRoundRect(0, 0, motionButton.getWidth(), motionButton.getHeight(), motionButton.i);
        }
    }

    public MotionButton(Context context) {
        super(context);
        this.h = 0.0f;
        this.i = Float.NaN;
        a(context, null);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        setPadding(0, 0, 0, 0);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ImageFilterView);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.ImageFilterView_round) {
                    setRound(typedArrayObtainStyledAttributes.getDimension(index, 0.0f));
                } else if (index == R$styleable.ImageFilterView_roundPercent) {
                    setRoundPercent(typedArrayObtainStyledAttributes.getFloat(index, 0.0f));
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        super.draw(canvas);
    }

    public float getRound() {
        return this.i;
    }

    public float getRoundPercent() {
        return this.h;
    }

    public void setRound(float f) {
        if (Float.isNaN(f)) {
            this.i = f;
            float f2 = this.h;
            this.h = -1.0f;
            setRoundPercent(f2);
            return;
        }
        boolean z = this.i != f;
        this.i = f;
        if (f != 0.0f) {
            if (this.j == null) {
                this.j = new Path();
            }
            if (this.l == null) {
                this.l = new RectF();
            }
            if (this.k == null) {
                b bVar = new b();
                this.k = bVar;
                setOutlineProvider(bVar);
            }
            setClipToOutline(true);
            this.l.set(0.0f, 0.0f, getWidth(), getHeight());
            this.j.reset();
            Path path = this.j;
            RectF rectF = this.l;
            float f3 = this.i;
            path.addRoundRect(rectF, f3, f3, Path.Direction.CW);
        } else {
            setClipToOutline(false);
        }
        if (z) {
            invalidateOutline();
        }
    }

    public void setRoundPercent(float f) {
        boolean z = this.h != f;
        this.h = f;
        if (f != 0.0f) {
            if (this.j == null) {
                this.j = new Path();
            }
            if (this.l == null) {
                this.l = new RectF();
            }
            if (this.k == null) {
                a aVar = new a();
                this.k = aVar;
                setOutlineProvider(aVar);
            }
            setClipToOutline(true);
            int width = getWidth();
            int height = getHeight();
            float fMin = (Math.min(width, height) * this.h) / 2.0f;
            this.l.set(0.0f, 0.0f, width, height);
            this.j.reset();
            this.j.addRoundRect(this.l, fMin, fMin, Path.Direction.CW);
        } else {
            setClipToOutline(false);
        }
        if (z) {
            invalidateOutline();
        }
    }

    public MotionButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = 0.0f;
        this.i = Float.NaN;
        a(context, attributeSet);
    }

    public MotionButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = 0.0f;
        this.i = Float.NaN;
        a(context, attributeSet);
    }
}
