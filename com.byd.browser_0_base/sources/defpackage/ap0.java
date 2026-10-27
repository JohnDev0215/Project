package defpackage;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import defpackage.om3;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: ForegroundDrawableCompat.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ap0 implements View.OnAttachStateChangeListener, View.OnLayoutChangeListener {
    public final View h;
    public boolean i;
    public Drawable j;
    public final RectF e = new RectF();
    public final RectF f = new RectF();
    public final Matrix g = new Matrix();
    public ImageView.ScaleType k = ImageView.ScaleType.FIT_CENTER;

    public ap0(View view) {
        this.h = view;
        view.addOnAttachStateChangeListener(this);
        view.addOnLayoutChangeListener(this);
    }

    public final void a(Canvas canvas) {
        Drawable drawable = this.j;
        if (drawable == null) {
            return;
        }
        Matrix matrix = this.g;
        if (drawable != null && this.i) {
            matrix.reset();
            int intrinsicWidth = this.j.getIntrinsicWidth();
            int intrinsicHeight = this.j.getIntrinsicHeight();
            View view = this.h;
            int width = view.getWidth();
            int height = view.getHeight();
            RectF rectF = this.e;
            rectF.set(0.0f, 0.0f, intrinsicWidth, intrinsicHeight);
            RectF rectF2 = this.f;
            rectF2.set(0.0f, 0.0f, width, height);
            ImageView.ScaleType scaleType = this.k;
            if (scaleType == ImageView.ScaleType.FIT_START) {
                matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.START);
                this.j.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
            } else if (scaleType == ImageView.ScaleType.FIT_CENTER) {
                matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
                this.j.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
            } else if (scaleType == ImageView.ScaleType.FIT_END) {
                matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.END);
                this.j.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
            } else if (scaleType == ImageView.ScaleType.CENTER) {
                matrix.setTranslate(Math.round((width - intrinsicWidth) * 0.5f), Math.round((height - intrinsicHeight) * 0.5f));
                this.j.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
            } else {
                this.j.setBounds(0, 0, width, height);
            }
            this.i = false;
        }
        if (matrix.isIdentity()) {
            this.j.draw(canvas);
            return;
        }
        int saveCount = canvas.getSaveCount();
        canvas.save();
        canvas.concat(matrix);
        this.j.draw(canvas);
        canvas.restoreToCount(saveCount);
    }

    public final void b(View view, int i) {
        View view2 = this.h;
        if (view2 != view || this.j == null) {
            return;
        }
        ViewParent parent = view2.getParent();
        if ((parent != null && (!(parent instanceof ViewGroup) || ((ViewGroup) parent).isShown())) && view2.getWindowVisibility() == 0) {
            this.j.setVisible(i == 0, false);
        }
    }

    public final void c(Drawable drawable) {
        Drawable drawable2 = this.j;
        if (drawable2 == drawable) {
            return;
        }
        View view = this.h;
        if (drawable2 != null) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            if (om3.g.b(view)) {
                this.j.setVisible(false, false);
            }
            this.j.setCallback(null);
            view.unscheduleDrawable(this.j);
            this.j = null;
        }
        this.j = drawable;
        if (drawable != null) {
            this.i = true;
            WeakHashMap<View, in3> weakHashMap2 = om3.a;
            drawable.setLayoutDirection(om3.e.c(view));
            if (this.j.isStateful()) {
                this.j.setState(view.getDrawableState());
            }
            if (om3.g.b(view)) {
                this.j.setVisible(view.getWindowVisibility() == 0 && view.isShown(), false);
            }
            this.j.setCallback(view);
        }
        ao3.e(view, "ForegroundDrawableCompat.setDrawable");
        view.invalidate();
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        Drawable drawable = this.j;
        if (drawable == null) {
            return;
        }
        int i9 = i4 - i2;
        if (i3 - i == drawable.getBounds().width() && i9 == this.j.getBounds().height()) {
            return;
        }
        this.i = true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        if (this.j == null) {
            return;
        }
        View view2 = this.h;
        if (!view2.isShown() || view2.getWindowVisibility() == 8) {
            return;
        }
        this.j.setVisible(view2.getVisibility() == 0, false);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        if (this.j == null) {
            return;
        }
        View view2 = this.h;
        if (!view2.isShown() || view2.getWindowVisibility() == 8) {
            return;
        }
        this.j.setVisible(false, false);
    }
}
