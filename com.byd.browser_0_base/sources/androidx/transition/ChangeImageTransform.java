package androidx.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import defpackage.cf3;
import defpackage.df3;
import defpackage.wc1;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class ChangeImageTransform extends Transition {
    public static final String[] C = {"android:changeImageTransform:matrix", "android:changeImageTransform:bounds"};
    public static final a D = new a();
    public static final b E = new b();

    public class a implements TypeEvaluator<Matrix> {
        @Override // android.animation.TypeEvaluator
        public final /* bridge */ /* synthetic */ Matrix evaluate(float f, Matrix matrix, Matrix matrix2) {
            return null;
        }
    }

    public class b extends Property<ImageView, Matrix> {
        public b() {
            super(Matrix.class, "animatedTransform");
        }

        @Override // android.util.Property
        public final /* bridge */ /* synthetic */ Matrix get(ImageView imageView) {
            return null;
        }

        @Override // android.util.Property
        public final void set(ImageView imageView, Matrix matrix) {
            imageView.animateTransform(matrix);
        }
    }

    public static /* synthetic */ class c {
        public static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[ImageView.ScaleType.values().length];
            a = iArr;
            try {
                iArr[ImageView.ScaleType.FIT_XY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ImageView.ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public ChangeImageTransform() {
    }

    public final void Q(df3 df3Var) {
        Matrix matrix;
        View view = df3Var.b;
        if ((view instanceof ImageView) && view.getVisibility() == 0) {
            ImageView imageView = (ImageView) view;
            if (imageView.getDrawable() == null) {
                return;
            }
            HashMap map = df3Var.a;
            map.put("android:changeImageTransform:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
            Drawable drawable = imageView.getDrawable();
            if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
                matrix = new Matrix(imageView.getImageMatrix());
            } else {
                int i = c.a[imageView.getScaleType().ordinal()];
                if (i == 1) {
                    Drawable drawable2 = imageView.getDrawable();
                    Matrix matrix2 = new Matrix();
                    matrix2.postScale(imageView.getWidth() / drawable2.getIntrinsicWidth(), imageView.getHeight() / drawable2.getIntrinsicHeight());
                    matrix = matrix2;
                } else if (i != 2) {
                    matrix = new Matrix(imageView.getImageMatrix());
                } else {
                    Drawable drawable3 = imageView.getDrawable();
                    int intrinsicWidth = drawable3.getIntrinsicWidth();
                    float width = imageView.getWidth();
                    float f = intrinsicWidth;
                    int intrinsicHeight = drawable3.getIntrinsicHeight();
                    float height = imageView.getHeight();
                    float f2 = intrinsicHeight;
                    float fMax = Math.max(width / f, height / f2);
                    int iRound = Math.round((width - (f * fMax)) / 2.0f);
                    int iRound2 = Math.round((height - (f2 * fMax)) / 2.0f);
                    Matrix matrix3 = new Matrix();
                    matrix3.postScale(fMax, fMax);
                    matrix3.postTranslate(iRound, iRound2);
                    matrix = matrix3;
                }
            }
            map.put("android:changeImageTransform:matrix", matrix);
        }
    }

    @Override // androidx.transition.Transition
    public final void i(df3 df3Var) {
        Q(df3Var);
    }

    @Override // androidx.transition.Transition
    public final void l(df3 df3Var) {
        Q(df3Var);
    }

    @Override // androidx.transition.Transition
    public final Animator p(ViewGroup viewGroup, df3 df3Var, df3 df3Var2) {
        if (df3Var == null || df3Var2 == null) {
            return null;
        }
        HashMap map = df3Var.a;
        Rect rect = (Rect) map.get("android:changeImageTransform:bounds");
        HashMap map2 = df3Var2.a;
        Rect rect2 = (Rect) map2.get("android:changeImageTransform:bounds");
        if (rect == null || rect2 == null) {
            return null;
        }
        Matrix matrix = (Matrix) map.get("android:changeImageTransform:matrix");
        Matrix matrix2 = (Matrix) map2.get("android:changeImageTransform:matrix");
        boolean z = (matrix == null && matrix2 == null) || (matrix != null && matrix.equals(matrix2));
        if (rect.equals(rect2) && z) {
            return null;
        }
        ImageView imageView = (ImageView) df3Var2.b;
        Drawable drawable = imageView.getDrawable();
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        b bVar = E;
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            a aVar = D;
            wc1.a aVar2 = wc1.a;
            return ObjectAnimator.ofObject(imageView, bVar, aVar, aVar2, aVar2);
        }
        if (matrix == null) {
            matrix = wc1.a;
        }
        if (matrix2 == null) {
            matrix2 = wc1.a;
        }
        bVar.set(imageView, matrix);
        return ObjectAnimator.ofObject(imageView, bVar, new cf3(), matrix, matrix2);
    }

    @Override // androidx.transition.Transition
    public final String[] v() {
        return C;
    }

    public ChangeImageTransform(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
