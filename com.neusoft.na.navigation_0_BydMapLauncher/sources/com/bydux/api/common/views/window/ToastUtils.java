package com.bydux.api.common.views.window;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import com.bydux.api.R;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;

/* JADX INFO: loaded from: classes2.dex */
public class ToastUtils {
    private static String TAG = "ToastUtils";
    private static Toast toast;

    public static void show(Context context, int textRes) {
        if (context == null) {
            Log.i(TAG, "[ToastUtils]----[Error]----context==null:" + textRes);
        } else {
            show(context, context.getResources().getString(textRes));
        }
    }

    public static void show(Context context, String text) {
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
        textView.setBackground(ContextCompat.getDrawable(context, R.drawable.toast_background));
        textView.setTextColor(ContextCompat.getColor(context, R.color.toast_textcolor));
        textView.setTextSize(0, context.getResources().getDimension(R.dimen.sp_18));
        textView.setText(text);
        textView.setGravity(17);
        textView.setMaxWidth(context.getResources().getDimensionPixelSize(R.dimen.dp_480));
        textView.setMinWidth(context.getResources().getDimensionPixelSize(R.dimen.dp_200));
        textView.setPadding(context.getResources().getDimensionPixelSize(R.dimen.dp_24), context.getResources().getDimensionPixelSize(R.dimen.dp_15), context.getResources().getDimensionPixelSize(R.dimen.dp_24), context.getResources().getDimensionPixelSize(R.dimen.dp_15));
        toast.setGravity(49, 0, context.getResources().getDimensionPixelSize(context.getResources().getConfiguration().orientation == 1 ? R.dimen.dp_190 : R.dimen.dp_72));
        toast.setDuration(0);
        textView.animate().scaleX(1.0f).scaleY(1.0f).setInterpolator(PathInterpolatorUtils.getSharp()).setDuration(200L).start();
        textView.animate().alpha(1.0f).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).setDuration(200L).start();
        toast.setView(textView);
        toast.show();
    }

    public static void show(Context context, String text, int drawableId) {
        if (context == null || context == null) {
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
        textView.setBackground(ContextCompat.getDrawable(context, R.drawable.toast_background));
        textView.setTextColor(ContextCompat.getColor(context, R.color.toast_textcolor));
        textView.setTextSize(0, context.getResources().getDimension(R.dimen.sp_18));
        textView.setText(text);
        textView.setGravity(17);
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(context.getResources().getDrawable(drawableId), (Drawable) null, (Drawable) null, (Drawable) null);
        textView.setMaxWidth(context.getResources().getDimensionPixelSize(R.dimen.dp_480));
        textView.setMinWidth(context.getResources().getDimensionPixelSize(R.dimen.dp_200));
        textView.setPadding(context.getResources().getDimensionPixelSize(R.dimen.dp_20), context.getResources().getDimensionPixelSize(R.dimen.dp_4), context.getResources().getDimensionPixelSize(R.dimen.dp_36), context.getResources().getDimensionPixelSize(R.dimen.dp_4));
        toast.setGravity(49, 0, context.getResources().getDimensionPixelSize(context.getResources().getConfiguration().orientation == 1 ? R.dimen.dp_190 : R.dimen.dp_72));
        toast.setDuration(0);
        textView.animate().scaleX(1.0f).scaleY(1.0f).setInterpolator(PathInterpolatorUtils.getSharp()).setDuration(200L).start();
        textView.animate().alpha(1.0f).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).setDuration(200L).start();
        toast.setView(textView);
        toast.show();
    }

    public static void cancel() {
        Toast toast2 = toast;
        if (toast2 != null) {
            toast2.cancel();
        }
    }
}
