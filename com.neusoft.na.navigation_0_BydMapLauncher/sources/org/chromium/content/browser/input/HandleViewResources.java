package org.chromium.content.browser.input;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
public class HandleViewResources {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final float HANDLE_HORIZONTAL_PADDING_RATIO = 0.25f;
    private static final int[] LEFT_HANDLE_ATTRS = {R.attr.textSelectHandleLeft};
    private static final int[] CENTER_HANDLE_ATTRS = {R.attr.textSelectHandle};
    private static final int[] RIGHT_HANDLE_ATTRS = {R.attr.textSelectHandleRight};

    public static float getHandleHorizontalPaddingRatio() {
        return HANDLE_HORIZONTAL_PADDING_RATIO;
    }

    public static Drawable getLeftHandleDrawable(Context context) {
        return getHandleDrawable(context, LEFT_HANDLE_ATTRS);
    }

    public static Drawable getCenterHandleDrawable(Context context) {
        return getHandleDrawable(context, CENTER_HANDLE_ATTRS);
    }

    public static Drawable getRightHandleDrawable(Context context) {
        return getHandleDrawable(context, RIGHT_HANDLE_ATTRS);
    }

    private static Drawable getHandleDrawable(Context context, int[] iArr) {
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(iArr);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(0);
        if (drawable == null) {
            try {
                drawable = ApiCompatibilityUtils.getDrawable(context.getResources(), typedArrayObtainStyledAttributes.getResourceId(0, 0));
            } catch (Resources.NotFoundException unused) {
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        return drawable;
    }

    private static Bitmap getHandleBitmap(Context context, int[] iArr) {
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        Drawable handleDrawable = getHandleDrawable(context, iArr);
        int intrinsicWidth = handleDrawable.getIntrinsicWidth();
        int intrinsicHeight = handleDrawable.getIntrinsicHeight();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, config);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        handleDrawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        handleDrawable.setColorFilter(Color.rgb(66, 133, 243), PorterDuff.Mode.SRC_IN);
        handleDrawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    private static Bitmap getLeftHandleBitmap(Context context) {
        return getHandleBitmap(context, LEFT_HANDLE_ATTRS);
    }

    private static Bitmap getCenterHandleBitmap(Context context) {
        return getHandleBitmap(context, CENTER_HANDLE_ATTRS);
    }

    private static Bitmap getRightHandleBitmap(Context context) {
        return getHandleBitmap(context, RIGHT_HANDLE_ATTRS);
    }
}
