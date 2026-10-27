package androidx.appcompat.widget;

import android.R;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.hardware.bydauto_test.TestVehicleHealthManagerImplCallback;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import defpackage.xt3;

/* JADX INFO: loaded from: classes.dex */
class AppCompatProgressBarHelper {
    public static final int[] c = {R.attr.indeterminateDrawable, R.attr.progressDrawable};
    public final ProgressBar a;
    public Bitmap b;

    public static class Api23Impl {
        private Api23Impl() {
        }
    }

    public AppCompatProgressBarHelper(ProgressBar progressBar) {
        this.a = progressBar;
    }

    public void a(AttributeSet attributeSet, int i) {
        ProgressBar progressBar = this.a;
        TintTypedArray tintTypedArrayM = TintTypedArray.m(progressBar.getContext(), attributeSet, c, i);
        Drawable drawableF = tintTypedArrayM.f(0);
        if (drawableF != null) {
            if (drawableF instanceof AnimationDrawable) {
                AnimationDrawable animationDrawable = (AnimationDrawable) drawableF;
                int numberOfFrames = animationDrawable.getNumberOfFrames();
                AnimationDrawable animationDrawable2 = new AnimationDrawable();
                animationDrawable2.setOneShot(animationDrawable.isOneShot());
                for (int i2 = 0; i2 < numberOfFrames; i2++) {
                    Drawable drawableB = b(animationDrawable.getFrame(i2), true);
                    drawableB.setLevel(TestVehicleHealthManagerImplCallback.SET_FRONT_WIPER_CHECK_STATE);
                    animationDrawable2.addFrame(drawableB, animationDrawable.getDuration(i2));
                }
                animationDrawable2.setLevel(TestVehicleHealthManagerImplCallback.SET_FRONT_WIPER_CHECK_STATE);
                drawableF = animationDrawable2;
            }
            progressBar.setIndeterminateDrawable(drawableF);
        }
        Drawable drawableF2 = tintTypedArrayM.f(1);
        if (drawableF2 != null) {
            progressBar.setProgressDrawable(b(drawableF2, false));
        }
        tintTypedArrayM.n();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Drawable b(Drawable drawable, boolean z) {
        if (drawable instanceof xt3) {
            xt3 xt3Var = (xt3) drawable;
            Drawable drawableB = xt3Var.b();
            if (drawableB != null) {
                xt3Var.a(b(drawableB, z));
            }
        } else {
            if (drawable instanceof LayerDrawable) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                int numberOfLayers = layerDrawable.getNumberOfLayers();
                Drawable[] drawableArr = new Drawable[numberOfLayers];
                for (int i = 0; i < numberOfLayers; i++) {
                    int id = layerDrawable.getId(i);
                    drawableArr[i] = b(layerDrawable.getDrawable(i), id == 16908301 || id == 16908303);
                }
                LayerDrawable layerDrawable2 = new LayerDrawable(drawableArr);
                for (int i2 = 0; i2 < numberOfLayers; i2++) {
                    layerDrawable2.setId(i2, layerDrawable.getId(i2));
                    layerDrawable2.setLayerGravity(i2, layerDrawable.getLayerGravity(i2));
                    layerDrawable2.setLayerWidth(i2, layerDrawable.getLayerWidth(i2));
                    layerDrawable2.setLayerHeight(i2, layerDrawable.getLayerHeight(i2));
                    layerDrawable2.setLayerInsetLeft(i2, layerDrawable.getLayerInsetLeft(i2));
                    layerDrawable2.setLayerInsetRight(i2, layerDrawable.getLayerInsetRight(i2));
                    layerDrawable2.setLayerInsetTop(i2, layerDrawable.getLayerInsetTop(i2));
                    layerDrawable2.setLayerInsetBottom(i2, layerDrawable.getLayerInsetBottom(i2));
                    layerDrawable2.setLayerInsetStart(i2, layerDrawable.getLayerInsetStart(i2));
                    layerDrawable2.setLayerInsetEnd(i2, layerDrawable.getLayerInsetEnd(i2));
                }
                return layerDrawable2;
            }
            if (drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                Bitmap bitmap = bitmapDrawable.getBitmap();
                if (this.b == null) {
                    this.b = bitmap;
                }
                ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null));
                shapeDrawable.getPaint().setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP));
                shapeDrawable.getPaint().setColorFilter(bitmapDrawable.getPaint().getColorFilter());
                return z ? new ClipDrawable(shapeDrawable, 3, 1) : shapeDrawable;
            }
        }
        return drawable;
    }
}
