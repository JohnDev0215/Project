package com.bydux.api.common.views.window;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.a;
import com.bydux.api.R;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;

/* JADX INFO: loaded from: classes.dex */
public class ToastUtils {
    private static String TAG = "ToastUtils";
    private static Toast toast;

    public static void cancel() {
        Toast toast2 = toast;
        if (toast2 != null) {
            toast2.cancel();
        }
    }

    public static void show(Context context, int i) {
        if (context != null) {
            show(context, context.getResources().getString(i));
            return;
        }
        Log.i(TAG, "[ToastUtils]----[Error]----context==null:" + i);
    }

    public static void show(Context context, String str) {
        if (context == null) {
            return;
        }
        Toast toast2 = toast;
        if (toast2 != null) {
            toast2.cancel();
            toast = null;
        }
        toast = new Toast(context);
        TextView textView = new TextView(context);
        textView.setScaleX(0.9f);
        textView.setScaleY(0.9f);
        textView.setAlpha(0.0f);
        textView.setBackground(a.d(context, R.drawable.toast_background));
        textView.setTextColor(a.b(context, R.color.toast_textcolor));
        textView.setTextSize(0, context.getResources().getDimension(R.dimen.sp_18));
        textView.setText(str);
        textView.setGravity(17);
        textView.setMaxWidth(context.getResources().getDimensionPixelSize(R.dimen.dp_480));
        textView.setMinWidth(context.getResources().getDimensionPixelSize(R.dimen.dp_200));
        Resources resources = context.getResources();
        int i = R.dimen.dp_24;
        int dimensionPixelSize = resources.getDimensionPixelSize(i);
        Resources resources2 = context.getResources();
        int i2 = R.dimen.dp_15;
        textView.setPadding(dimensionPixelSize, resources2.getDimensionPixelSize(i2), context.getResources().getDimensionPixelSize(i), context.getResources().getDimensionPixelSize(i2));
        toast.setGravity(49, 0, context.getResources().getDimensionPixelSize(context.getResources().getConfiguration().orientation == 1 ? R.dimen.dp_190 : R.dimen.dp_72));
        toast.setDuration(1);
        textView.animate().scaleX(1.0f).scaleY(1.0f).setInterpolator(PathInterpolatorUtils.getSharp()).setDuration(200L).start();
        textView.animate().alpha(1.0f).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).setDuration(200L).start();
        toast.setView(textView);
        toast.show();
    }

    public static void show(Context context, String str, int i) {
        if (context == null) {
            return;
        }
        Toast toast2 = toast;
        if (toast2 != null) {
            toast2.cancel();
            toast = null;
        }
        toast = new Toast(context);
        TextView textView = new TextView(context);
        textView.setScaleX(0.9f);
        textView.setScaleY(0.9f);
        textView.setAlpha(0.0f);
        textView.setBackground(a.d(context, R.drawable.toast_background));
        textView.setTextColor(a.b(context, R.color.toast_textcolor));
        textView.setTextSize(0, context.getResources().getDimension(R.dimen.sp_18));
        textView.setText(str);
        textView.setGravity(17);
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(context.getResources().getDrawable(i), (Drawable) null, (Drawable) null, (Drawable) null);
        textView.setMaxWidth(context.getResources().getDimensionPixelSize(R.dimen.dp_480));
        textView.setMinWidth(context.getResources().getDimensionPixelSize(R.dimen.dp_200));
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.dp_20);
        Resources resources = context.getResources();
        int i2 = R.dimen.dp_4;
        textView.setPadding(dimensionPixelSize, resources.getDimensionPixelSize(i2), context.getResources().getDimensionPixelSize(R.dimen.dp_36), context.getResources().getDimensionPixelSize(i2));
        toast.setGravity(49, 0, context.getResources().getDimensionPixelSize(context.getResources().getConfiguration().orientation == 1 ? R.dimen.dp_190 : R.dimen.dp_72));
        toast.setDuration(1);
        textView.animate().scaleX(1.0f).scaleY(1.0f).setInterpolator(PathInterpolatorUtils.getSharp()).setDuration(200L).start();
        textView.animate().alpha(1.0f).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).setDuration(200L).start();
        toast.setView(textView);
        toast.show();
    }
}
