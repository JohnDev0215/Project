package defpackage;

import android.R;
import android.content.res.TypedArray;
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
import java.lang.reflect.Method;
import skin.support.R$styleable;

/* JADX INFO: compiled from: SkinCompatProgressBarHelper.java */
/* JADX INFO: loaded from: classes3.dex */
public class oo2 extends vd1 {
    public final ProgressBar k;
    public Bitmap l;
    public int m = 0;
    public int n = 0;
    public int o = 0;

    public oo2(ProgressBar progressBar) {
        this.k = progressBar;
    }

    public void J() {
        int iB = vd1.b(this.m);
        this.m = iB;
        ProgressBar progressBar = this.k;
        if (iB != 0) {
            Drawable drawableA = xo2.a(progressBar.getContext(), this.m);
            drawableA.setBounds(progressBar.getIndeterminateDrawable().getBounds());
            if (drawableA instanceof AnimationDrawable) {
                AnimationDrawable animationDrawable = (AnimationDrawable) drawableA;
                int numberOfFrames = animationDrawable.getNumberOfFrames();
                AnimationDrawable animationDrawable2 = new AnimationDrawable();
                animationDrawable2.setOneShot(animationDrawable.isOneShot());
                for (int i = 0; i < numberOfFrames; i++) {
                    Drawable drawableL = L(animationDrawable.getFrame(i), true);
                    drawableL.setLevel(TestVehicleHealthManagerImplCallback.SET_FRONT_WIPER_CHECK_STATE);
                    animationDrawable2.addFrame(drawableL, animationDrawable.getDuration(i));
                }
                animationDrawable2.setLevel(TestVehicleHealthManagerImplCallback.SET_FRONT_WIPER_CHECK_STATE);
                drawableA = animationDrawable2;
            }
            progressBar.setIndeterminateDrawable(drawableA);
        }
        int iB2 = vd1.b(this.n);
        this.n = iB2;
        if (iB2 != 0) {
            progressBar.setProgressDrawable(L(xo2.a(progressBar.getContext(), this.n), false));
        }
        int iB3 = vd1.b(this.o);
        this.o = iB3;
        if (iB3 != 0) {
            progressBar.setIndeterminateTintList(po2.b(progressBar.getContext(), this.o));
        }
    }

    public void K(AttributeSet attributeSet, int i) {
        ProgressBar progressBar = this.k;
        TypedArray typedArrayObtainStyledAttributes = progressBar.getContext().obtainStyledAttributes(attributeSet, R$styleable.SkinCompatProgressBar, i, 0);
        this.m = typedArrayObtainStyledAttributes.getResourceId(R$styleable.SkinCompatProgressBar_android_indeterminateDrawable, 0);
        this.n = typedArrayObtainStyledAttributes.getResourceId(R$styleable.SkinCompatProgressBar_android_progressDrawable, 0);
        typedArrayObtainStyledAttributes.recycle();
        TypedArray typedArrayObtainStyledAttributes2 = progressBar.getContext().obtainStyledAttributes(attributeSet, new int[]{R.attr.indeterminateTint}, i, 0);
        this.o = typedArrayObtainStyledAttributes2.getResourceId(0, 0);
        typedArrayObtainStyledAttributes2.recycle();
        J();
    }

    public final Drawable L(Drawable drawable, boolean z) {
        Class<?> cls = yo2.d;
        try {
            if (cls != null && cls.isAssignableFrom(drawable.getClass())) {
                Drawable drawableB = yo2.b(drawable);
                if (drawableB != null) {
                    Drawable drawableL = L(drawableB, z);
                    if (cls != null) {
                        if (yo2.f == null) {
                            try {
                                Method declaredMethod = cls.getDeclaredMethod("setWrappedDrawable", Drawable.class);
                                yo2.f = declaredMethod;
                                declaredMethod.setAccessible(true);
                            } catch (Exception unused) {
                            }
                        }
                        Method method = yo2.f;
                        if (method != null) {
                            method.invoke(drawable, drawableL);
                        }
                    }
                }
            } else {
                Class<?> cls2 = yo2.a;
                if (cls2 != null && cls2.isAssignableFrom(drawable.getClass())) {
                    Drawable drawableA = yo2.a(drawable);
                    if (drawableA != null) {
                        Drawable drawableL2 = L(drawableA, z);
                        if (cls2 != null) {
                            if (yo2.c == null) {
                                try {
                                    Method declaredMethod2 = cls2.getDeclaredMethod("setWrappedDrawable", Drawable.class);
                                    yo2.c = declaredMethod2;
                                    declaredMethod2.setAccessible(true);
                                } catch (Exception unused2) {
                                }
                            }
                            Method method2 = yo2.c;
                            if (method2 != null) {
                                method2.invoke(drawable, drawableL2);
                            }
                        }
                    }
                } else {
                    if (drawable instanceof LayerDrawable) {
                        LayerDrawable layerDrawable = (LayerDrawable) drawable;
                        int numberOfLayers = layerDrawable.getNumberOfLayers();
                        Drawable[] drawableArr = new Drawable[numberOfLayers];
                        for (int i = 0; i < numberOfLayers; i++) {
                            int id = layerDrawable.getId(i);
                            drawableArr[i] = L(layerDrawable.getDrawable(i), id == 16908301 || id == 16908303);
                        }
                        LayerDrawable layerDrawable2 = new LayerDrawable(drawableArr);
                        for (int i2 = 0; i2 < numberOfLayers; i2++) {
                            layerDrawable2.setId(i2, layerDrawable.getId(i2));
                        }
                        return layerDrawable2;
                    }
                    if (drawable instanceof BitmapDrawable) {
                        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                        Bitmap bitmap = bitmapDrawable.getBitmap();
                        if (this.l == null) {
                            this.l = bitmap;
                        }
                        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null));
                        shapeDrawable.getPaint().setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP));
                        shapeDrawable.getPaint().setColorFilter(bitmapDrawable.getPaint().getColorFilter());
                        return z ? new ClipDrawable(shapeDrawable, 3, 1) : shapeDrawable;
                    }
                }
            }
        } catch (Exception unused3) {
        }
        return drawable;
    }
}
