package b.b.g.r;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import com.byd.carsettings.BydApp;
import com.byd.carsettings.R;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;
import com.bydux.api.common.views.window.ToastUtils;

/* JADX INFO: compiled from: ToastUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f1843a = "z";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Toast f1844b;

    public static void a() {
        ToastUtils.cancel();
    }

    public static void b(Context context, int i) {
        Context applicationContext;
        if (context == null) {
            b.b.m.c.a.d(f1843a, "[ToastUtils]----[Error]----context==null:" + i);
            applicationContext = BydApp.getContext();
        } else {
            applicationContext = context.getApplicationContext();
        }
        c(applicationContext, applicationContext.getResources().getString(i));
    }

    public static void c(Context context, String str) {
        a();
        ToastUtils.show(context == null ? BydApp.getContext() : context.getApplicationContext(), str);
    }

    public static void d(Context context, String str, int i) {
        Context context2 = context == null ? BydApp.getContext() : context.getApplicationContext();
        if (context2 != null) {
            Toast toast = f1844b;
            if (toast != null) {
                toast.cancel();
                f1844b = null;
            }
            f1844b = new Toast(context2);
            TextView textView = new TextView(context2);
            textView.setScaleX(0.9f);
            textView.setScaleY(0.9f);
            textView.setAlpha(0.0f);
            textView.setBackground(ContextCompat.getDrawable(context2, R.drawable.toast_background));
            textView.setTextColor(ContextCompat.getColor(context2, R.color.toast_textcolor));
            textView.setTextSize(0, context2.getResources().getDimension(R.dimen.sp_18));
            textView.setText(str);
            textView.setGravity(17);
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(context2.getResources().getDrawable(i), (Drawable) null, (Drawable) null, (Drawable) null);
            textView.setMaxWidth(context2.getResources().getDimensionPixelSize(R.dimen.dp_480));
            textView.setMinWidth(context2.getResources().getDimensionPixelSize(R.dimen.dp_200));
            textView.setPadding(context2.getResources().getDimensionPixelSize(R.dimen.dp_20), context2.getResources().getDimensionPixelSize(R.dimen.dp_4), context2.getResources().getDimensionPixelSize(R.dimen.dp_36), context2.getResources().getDimensionPixelSize(R.dimen.dp_4));
            f1844b.setGravity(49, 0, context2.getResources().getDimensionPixelSize(context2.getResources().getConfiguration().orientation == 1 ? R.dimen.dp_190 : R.dimen.dp_72));
            f1844b.setDuration(1);
            textView.animate().scaleX(1.0f).scaleY(1.0f).setInterpolator(PathInterpolatorUtils.getSharp()).setDuration(200L).start();
            textView.animate().alpha(1.0f).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).setDuration(200L).start();
            f1844b.setView(textView);
            f1844b.show();
        }
    }
}
