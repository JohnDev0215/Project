package org.chromium.ui.resources;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import defpackage.AbstractC1623us;
import defpackage.Is;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class HandleViewResources {
    public static final int[] a = {R.attr.textSelectHandleLeft};
    public static final int[] b = {R.attr.textSelectHandle};
    public static final int[] c = {R.attr.textSelectHandleRight};

    public static Bitmap a(Context context, int[] iArr) {
        Bitmap bitmapDecodeResource;
        if (context == null) {
            context = Is.a;
        }
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(iArr);
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
        if (resources != context.getResources() && (bitmapDecodeResource = BitmapFactory.decodeResource(context.getResources(), resourceId, options)) != null) {
            return bitmapDecodeResource;
        }
        Drawable drawableB = b(context, iArr);
        int intrinsicWidth = drawableB.getIntrinsicWidth();
        int intrinsicHeight = drawableB.getIntrinsicHeight();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, config);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawableB.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawableB.draw(canvas);
        return bitmapCreateBitmap;
    }

    public static Drawable b(Context context, int[] iArr) {
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(iArr);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(0);
        if (drawable == null) {
            try {
                drawable = AbstractC1623us.c(context.getResources(), typedArrayObtainStyledAttributes.getResourceId(0, 0));
            } catch (Resources.NotFoundException unused) {
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        return drawable;
    }

    public static Bitmap getCenterHandleBitmap(Context context) {
        return a(context, b);
    }

    public static float getHandleHorizontalPaddingRatio() {
        return 0.25f;
    }

    public static Bitmap getLeftHandleBitmap(Context context) {
        return a(context, a);
    }

    public static Bitmap getRightHandleBitmap(Context context) {
        return a(context, c);
    }
}
