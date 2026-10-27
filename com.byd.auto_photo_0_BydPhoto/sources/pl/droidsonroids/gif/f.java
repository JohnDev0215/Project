package pl.droidsonroids.gif;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.RawRes;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final List<String> f3196a = Arrays.asList("raw", "drawable", "mipmap");

    static class a extends b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final int f3197c;
        final int d;

        a() {
            this.f3197c = 0;
            this.d = 0;
        }

        a(ImageView imageView, AttributeSet attributeSet, int i, int i2) {
            super(imageView, attributeSet, i, i2);
            this.f3197c = a(imageView, attributeSet, true);
            this.d = a(imageView, attributeSet, false);
        }

        private static int a(ImageView imageView, AttributeSet attributeSet, boolean z) {
            int attributeResourceValue = attributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res/android", z ? "src" : "background", 0);
            if (attributeResourceValue > 0) {
                if (f.f3196a.contains(imageView.getResources().getResourceTypeName(attributeResourceValue)) && !f.e(imageView, z, attributeResourceValue)) {
                    return attributeResourceValue;
                }
            }
            return 0;
        }
    }

    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f3198a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f3199b;

        b() {
            this.f3198a = false;
            this.f3199b = -1;
        }

        b(View view, AttributeSet attributeSet, int i, int i2) {
            TypedArray typedArrayObtainStyledAttributes = view.getContext().obtainStyledAttributes(attributeSet, R$styleable.GifView, i, i2);
            this.f3198a = typedArrayObtainStyledAttributes.getBoolean(R$styleable.GifView_freezesAnimation, false);
            this.f3199b = typedArrayObtainStyledAttributes.getInt(R$styleable.GifView_loopCount, -1);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    static void a(int i, Drawable drawable) {
        if (drawable instanceof c) {
            ((c) drawable).h(i);
        }
    }

    static float b(@NonNull Resources resources, @DrawableRes @RawRes int i) {
        TypedValue typedValue = new TypedValue();
        resources.getValue(i, typedValue, true);
        int i2 = typedValue.density;
        if (i2 == 0) {
            i2 = 160;
        } else if (i2 == 65535) {
            i2 = 0;
        }
        int i3 = resources.getDisplayMetrics().densityDpi;
        if (i2 <= 0 || i3 <= 0) {
            return 1.0f;
        }
        return i3 / i2;
    }

    static a c(ImageView imageView, AttributeSet attributeSet, int i, int i2) {
        if (attributeSet == null || imageView.isInEditMode()) {
            return new a();
        }
        a aVar = new a(imageView, attributeSet, i, i2);
        int i3 = aVar.f3199b;
        if (i3 >= 0) {
            a(i3, imageView.getDrawable());
            a(i3, imageView.getBackground());
        }
        return aVar;
    }

    static boolean d(ImageView imageView, Uri uri) {
        if (uri == null) {
            return false;
        }
        try {
            imageView.setImageDrawable(new c(imageView.getContext().getContentResolver(), uri));
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    static boolean e(ImageView imageView, boolean z, int i) {
        Resources resources = imageView.getResources();
        if (resources != null) {
            try {
                if (!f3196a.contains(resources.getResourceTypeName(i))) {
                    return false;
                }
                c cVar = new c(resources, i);
                if (z) {
                    imageView.setImageDrawable(cVar);
                    return true;
                }
                imageView.setBackground(cVar);
                return true;
            } catch (Resources.NotFoundException | IOException unused) {
            }
        }
        return false;
    }
}
