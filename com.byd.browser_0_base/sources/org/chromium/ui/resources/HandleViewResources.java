package org.chromium.ui.resources;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.ContextThemeWrapper;
import defpackage.c6;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.ui.R$style;

/* JADX INFO: loaded from: classes3.dex */
public class HandleViewResources {
    public static final int[] a = {R.attr.textSelectHandleLeft};
    public static final int[] b = {R.attr.textSelectHandle};
    public static final int[] c = {R.attr.textSelectHandleRight};

    public static Bitmap a(Context context, int[] iArr) {
        Bitmap bitmapDecodeResource;
        if (context == null) {
            context = xz.a;
        }
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, R$style.ThemeOverlay_UI_SelectionHandle);
        TypedArray typedArrayObtainStyledAttributes = contextThemeWrapper.getTheme().obtainStyledAttributes(iArr);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(typedArrayObtainStyledAttributes.getIndex(0), 0);
        Resources resources = typedArrayObtainStyledAttributes.getResources();
        typedArrayObtainStyledAttributes.recycle();
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = config;
        Bitmap bitmapDecodeResource2 = BitmapFactory.decodeResource(resources, resourceId, options);
        if (bitmapDecodeResource2 != null) {
            return bitmapDecodeResource2;
        }
        if (resources != contextThemeWrapper.getResources() && (bitmapDecodeResource = BitmapFactory.decodeResource(contextThemeWrapper.getResources(), resourceId, options)) != null) {
            return bitmapDecodeResource;
        }
        TypedArray typedArrayObtainStyledAttributes2 = contextThemeWrapper.getTheme().obtainStyledAttributes(iArr);
        Drawable drawable = typedArrayObtainStyledAttributes2.getDrawable(0);
        if (drawable == null) {
            try {
                drawable = c6.c(contextThemeWrapper.getResources(), typedArrayObtainStyledAttributes2.getResourceId(0, 0), 0);
            } catch (Resources.NotFoundException unused) {
            }
        }
        typedArrayObtainStyledAttributes2.recycle();
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, config);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    @CalledByNative
    public static Bitmap getCenterHandleBitmap(Context context) {
        return a(context, b);
    }

    @CalledByNative
    public static float getHandleHorizontalPaddingRatio() {
        return 0.25f;
    }

    @CalledByNative
    public static Bitmap getLeftHandleBitmap(Context context) {
        return a(context, a);
    }

    @CalledByNative
    public static Bitmap getRightHandleBitmap(Context context) {
        return a(context, c);
    }
}
