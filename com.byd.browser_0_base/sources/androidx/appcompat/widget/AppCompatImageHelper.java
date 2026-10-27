package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.R;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.in3;
import defpackage.om3;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatImageHelper {
    public final ImageView a;
    public TintInfo b;
    public int c = 0;

    public AppCompatImageHelper(ImageView imageView) {
        this.a = imageView;
    }

    public final void a() {
        TintInfo tintInfo;
        ImageView imageView = this.a;
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            DrawableUtils.a(drawable);
        }
        if (drawable == null || (tintInfo = this.b) == null) {
            return;
        }
        AppCompatDrawableManager.e(drawable, tintInfo, imageView.getDrawableState());
    }

    public final void b(AttributeSet attributeSet, int i) {
        int i2;
        ImageView imageView = this.a;
        Context context = imageView.getContext();
        int[] iArr = R.styleable.AppCompatImageView;
        TintTypedArray tintTypedArrayM = TintTypedArray.m(context, attributeSet, iArr, i);
        Context context2 = imageView.getContext();
        TypedArray typedArray = tintTypedArrayM.b;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.n.b(imageView, context2, iArr, attributeSet, typedArray, i, 0);
        try {
            Drawable drawable = imageView.getDrawable();
            if (drawable == null && (i2 = tintTypedArrayM.i(R.styleable.AppCompatImageView_srcCompat, -1)) != -1 && (drawable = AppCompatResources.a(imageView.getContext(), i2)) != null) {
                imageView.setImageDrawable(drawable);
            }
            if (drawable != null) {
                DrawableUtils.a(drawable);
            }
            int i3 = R.styleable.AppCompatImageView_tint;
            if (tintTypedArrayM.l(i3)) {
                imageView.setImageTintList(tintTypedArrayM.b(i3));
            }
            int i4 = R.styleable.AppCompatImageView_tintMode;
            if (tintTypedArrayM.l(i4)) {
                imageView.setImageTintMode(DrawableUtils.c(tintTypedArrayM.h(i4, -1), null));
            }
        } finally {
            tintTypedArrayM.n();
        }
    }

    public final void c(int i) {
        ImageView imageView = this.a;
        if (i != 0) {
            Drawable drawableA = AppCompatResources.a(imageView.getContext(), i);
            if (drawableA != null) {
                DrawableUtils.a(drawableA);
            }
            imageView.setImageDrawable(drawableA);
        } else {
            imageView.setImageDrawable(null);
        }
        a();
    }
}
