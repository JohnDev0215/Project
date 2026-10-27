package com.android.systemui.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import com.android.systemui.R;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;
import com.bydux.api.common.views.window.ToastUtils;

/* JADX INFO: loaded from: classes.dex */
public class ToastUtil {
    private static String TAG = ToastUtils.class.getSimpleName();
    private static Toast toast;

    public static void show(Context context, int textRes, int duration) {
        if (context == null) {
            Log.i(TAG, "[ToastUtils]----[Error]----context==null:" + textRes);
        } else {
            show(context, context.getResources().getString(textRes), duration);
        }
    }

    public static void show(Context context, String text, int duration) {
        if (context != null) {
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
            textView.setBackground(ContextCompat.getDrawable(context, R.drawable.toast_background));
            textView.setTextColor(ContextCompat.getColor(context, R.color.toast_textcolor));
            textView.setTextSize(0, context.getResources().getDimension(R.dimen.sp_18));
            textView.setText(text);
            textView.setGravity(17);
            textView.setMaxWidth(context.getResources().getDimensionPixelSize(R.dimen.dp_480));
            textView.setMinWidth(context.getResources().getDimensionPixelSize(R.dimen.dp_180));
            textView.setPadding(context.getResources().getDimensionPixelSize(R.dimen.dp_22), context.getResources().getDimensionPixelSize(R.dimen.dp_15), context.getResources().getDimensionPixelSize(R.dimen.dp_22), context.getResources().getDimensionPixelSize(R.dimen.dp_15));
            int i = context.getResources().getConfiguration().orientation;
            toast.setGravity(49, 0, context.getResources().getDimensionPixelSize(R.dimen.dp_14));
            toast.setDuration(duration);
            textView.animate().scaleX(1.0f).scaleY(1.0f).setInterpolator(PathInterpolatorUtils.getSharp()).setDuration(200L).start();
            textView.animate().alpha(1.0f).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).setDuration(200L).start();
            toast.setView(textView);
            toast.show();
        }
    }

    public static void show(Context context, String text, int drawableId, int duration) {
        if (context != null) {
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
            textView.setBackground(ContextCompat.getDrawable(context, R.drawable.toast_background));
            textView.setTextColor(ContextCompat.getColor(context, R.color.toast_textcolor));
            textView.setTextSize(0, context.getResources().getDimension(R.dimen.sp_18));
            textView.setText(text);
            textView.setGravity(17);
            Drawable drawable = (Drawable) null;
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(context.getResources().getDrawable(drawableId), drawable, drawable, drawable);
            textView.setMaxWidth(context.getResources().getDimensionPixelSize(R.dimen.dp_480));
            textView.setMinWidth(context.getResources().getDimensionPixelSize(R.dimen.dp_180));
            textView.setPadding(context.getResources().getDimensionPixelSize(R.dimen.dp_20), context.getResources().getDimensionPixelSize(R.dimen.dp_4), context.getResources().getDimensionPixelSize(R.dimen.dp_36), context.getResources().getDimensionPixelSize(R.dimen.dp_4));
            int i = context.getResources().getConfiguration().orientation;
            toast.setGravity(49, 0, context.getResources().getDimensionPixelSize(R.dimen.dp_14));
            toast.setDuration(duration);
            textView.animate().scaleX(1.0f).scaleY(1.0f).setInterpolator(PathInterpolatorUtils.getSharp()).setDuration(200L).start();
            textView.animate().alpha(1.0f).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).setDuration(200L).start();
            toast.setView(textView);
            toast.show();
        }
    }

    public static void cancel() {
        Toast toast2 = toast;
        if (toast2 != null) {
            toast2.cancel();
        }
    }
}
