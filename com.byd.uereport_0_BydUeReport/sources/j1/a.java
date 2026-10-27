package j1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.view.animation.Animation;
import android.widget.ImageView;
import o0.t;

/* JADX INFO: compiled from: CircleImageView.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends ImageView {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Animation.AnimationListener f5537d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5538e;

    /* JADX INFO: renamed from: j1.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CircleImageView.java */
    public class C0065a extends OvalShape {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public RadialGradient f5539d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Paint f5540e = new Paint();

        public C0065a(int i8) {
            a.this.f5538e = i8;
            a((int) rect().width());
        }

        public final void a(int i8) {
            float f8 = i8 / 2;
            RadialGradient radialGradient = new RadialGradient(f8, f8, a.this.f5538e, new int[]{1023410176, 0}, (float[]) null, Shader.TileMode.CLAMP);
            this.f5539d = radialGradient;
            this.f5540e.setShader(radialGradient);
        }

        @Override // android.graphics.drawable.shapes.OvalShape, android.graphics.drawable.shapes.RectShape, android.graphics.drawable.shapes.Shape
        public void draw(Canvas canvas, Paint paint) {
            float width = a.this.getWidth() / 2;
            float height = a.this.getHeight() / 2;
            canvas.drawCircle(width, height, width, this.f5540e);
            canvas.drawCircle(width, height, r0 - a.this.f5538e, paint);
        }

        @Override // android.graphics.drawable.shapes.RectShape, android.graphics.drawable.shapes.Shape
        public void onResize(float f8, float f9) {
            super.onResize(f8, f9);
            a((int) f8);
        }
    }

    public a(Context context, int i8) {
        ShapeDrawable shapeDrawable;
        super(context);
        float f8 = getContext().getResources().getDisplayMetrics().density;
        int i9 = (int) (1.75f * f8);
        int i10 = (int) (0.0f * f8);
        this.f5538e = (int) (3.5f * f8);
        if (a()) {
            shapeDrawable = new ShapeDrawable(new OvalShape());
            t.s0(this, f8 * 4.0f);
        } else {
            ShapeDrawable shapeDrawable2 = new ShapeDrawable(new C0065a(this.f5538e));
            setLayerType(1, shapeDrawable2.getPaint());
            shapeDrawable2.getPaint().setShadowLayer(this.f5538e, i10, i9, 503316480);
            int i11 = this.f5538e;
            setPadding(i11, i11, i11, i11);
            shapeDrawable = shapeDrawable2;
        }
        shapeDrawable.getPaint().setColor(i8);
        t.o0(this, shapeDrawable);
    }

    public final boolean a() {
        return Build.VERSION.SDK_INT >= 21;
    }

    public void b(Animation.AnimationListener animationListener) {
        this.f5537d = animationListener;
    }

    @Override // android.view.View
    public void onAnimationEnd() {
        super.onAnimationEnd();
        Animation.AnimationListener animationListener = this.f5537d;
        if (animationListener != null) {
            animationListener.onAnimationEnd(getAnimation());
        }
    }

    @Override // android.view.View
    public void onAnimationStart() {
        super.onAnimationStart();
        Animation.AnimationListener animationListener = this.f5537d;
        if (animationListener != null) {
            animationListener.onAnimationStart(getAnimation());
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i8, int i9) {
        super.onMeasure(i8, i9);
        if (a()) {
            return;
        }
        setMeasuredDimension(getMeasuredWidth() + (this.f5538e * 2), getMeasuredHeight() + (this.f5538e * 2));
    }

    @Override // android.view.View
    public void setBackgroundColor(int i8) {
        if (getBackground() instanceof ShapeDrawable) {
            ((ShapeDrawable) getBackground()).getPaint().setColor(i8);
        }
    }
}
