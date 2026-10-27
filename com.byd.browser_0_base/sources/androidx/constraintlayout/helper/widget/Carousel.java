package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionHelper;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.motion.widget.a;
import androidx.constraintlayout.widget.R$styleable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class Carousel extends MotionHelper {
    public float A;
    public int B;
    public int C;
    public float D;
    public final ArrayList<View> r;
    public int s;
    public MotionLayout t;
    public int u;
    public boolean v;
    public int w;
    public int x;
    public int y;
    public int z;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            Carousel carousel = Carousel.this;
            carousel.t.setProgress(0.0f);
            carousel.getClass();
            carousel.getClass();
            int i = carousel.s;
            throw null;
        }
    }

    public interface b {
    }

    public Carousel(Context context) {
        super(context);
        this.r = new ArrayList<>();
        this.s = 0;
        this.u = -1;
        this.v = false;
        this.w = -1;
        this.x = -1;
        this.y = -1;
        this.z = -1;
        this.A = 0.9f;
        this.B = 4;
        this.C = 1;
        this.D = 2.0f;
        new a();
    }

    @Override // androidx.constraintlayout.motion.widget.MotionHelper, androidx.constraintlayout.motion.widget.MotionLayout.h
    public final void a(int i) {
        int i2 = this.s;
        if (i == this.z) {
            this.s = i2 + 1;
        } else if (i == this.y) {
            this.s = i2 - 1;
        }
        if (!this.v) {
            throw null;
        }
        throw null;
    }

    public int getCount() {
        return 0;
    }

    public int getCurrentIndex() {
        return this.s;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public final void onAttachedToWindow() {
        androidx.constraintlayout.motion.widget.b bVar;
        androidx.constraintlayout.motion.widget.b bVar2;
        super.onAttachedToWindow();
        if (getParent() instanceof MotionLayout) {
            MotionLayout motionLayout = (MotionLayout) getParent();
            ArrayList<View> arrayList = this.r;
            arrayList.clear();
            for (int i = 0; i < this.f; i++) {
                arrayList.add(motionLayout.c(this.e[i]));
            }
            this.t = motionLayout;
            if (this.C == 2) {
                a.b bVarZ = motionLayout.z(this.x);
                if (bVarZ != null && (bVar2 = bVarZ.l) != null) {
                    bVar2.c = 5;
                }
                a.b bVarZ2 = this.t.z(this.w);
                if (bVarZ2 == null || (bVar = bVarZ2.l) == null) {
                    return;
                }
                bVar.c = 5;
            }
        }
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.r.clear();
    }

    public final void v(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Carousel);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.Carousel_carousel_firstView) {
                    this.u = typedArrayObtainStyledAttributes.getResourceId(index, this.u);
                } else if (index == R$styleable.Carousel_carousel_backwardTransition) {
                    this.w = typedArrayObtainStyledAttributes.getResourceId(index, this.w);
                } else if (index == R$styleable.Carousel_carousel_forwardTransition) {
                    this.x = typedArrayObtainStyledAttributes.getResourceId(index, this.x);
                } else if (index == R$styleable.Carousel_carousel_emptyViewsBehavior) {
                    this.B = typedArrayObtainStyledAttributes.getInt(index, this.B);
                } else if (index == R$styleable.Carousel_carousel_previousState) {
                    this.y = typedArrayObtainStyledAttributes.getResourceId(index, this.y);
                } else if (index == R$styleable.Carousel_carousel_nextState) {
                    this.z = typedArrayObtainStyledAttributes.getResourceId(index, this.z);
                } else if (index == R$styleable.Carousel_carousel_touchUp_dampeningFactor) {
                    this.A = typedArrayObtainStyledAttributes.getFloat(index, this.A);
                } else if (index == R$styleable.Carousel_carousel_touchUpMode) {
                    this.C = typedArrayObtainStyledAttributes.getInt(index, this.C);
                } else if (index == R$styleable.Carousel_carousel_touchUp_velocityThreshold) {
                    this.D = typedArrayObtainStyledAttributes.getFloat(index, this.D);
                } else if (index == R$styleable.Carousel_carousel_infinite) {
                    this.v = typedArrayObtainStyledAttributes.getBoolean(index, this.v);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public Carousel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.r = new ArrayList<>();
        this.s = 0;
        this.u = -1;
        this.v = false;
        this.w = -1;
        this.x = -1;
        this.y = -1;
        this.z = -1;
        this.A = 0.9f;
        this.B = 4;
        this.C = 1;
        this.D = 2.0f;
        new a();
        v(context, attributeSet);
    }

    @Override // androidx.constraintlayout.motion.widget.MotionHelper, androidx.constraintlayout.motion.widget.MotionLayout.h
    public final void b() {
    }

    public void setAdapter(b bVar) {
    }

    public Carousel(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.r = new ArrayList<>();
        this.s = 0;
        this.u = -1;
        this.v = false;
        this.w = -1;
        this.x = -1;
        this.y = -1;
        this.z = -1;
        this.A = 0.9f;
        this.B = 4;
        this.C = 1;
        this.D = 2.0f;
        new a();
        v(context, attributeSet);
    }
}
