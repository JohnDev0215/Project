package c.a.d.h;

import android.content.Context;
import android.util.TypedValue;
import android.widget.TextView;
import android.widget.Toast;
import com.byd.ota.R;

/* JADX INFO: compiled from: ToastUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Toast f2503a;

    public static void a(Context context, String str, int i2) {
        if (context != null) {
            Toast toast = f2503a;
            if (toast != null) {
                toast.cancel();
                f2503a = null;
            }
            f2503a = new Toast(context);
            TextView textView = new TextView(context);
            textView.setScaleX(0.9f);
            textView.setScaleY(0.9f);
            textView.setAlpha(0.0f);
            textView.setBackground(b.f.f.a.c(context, R.drawable.toast_background));
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.toast_textcolor, typedValue, true);
            textView.setTextColor(b.f.f.a.a(context, typedValue.resourceId));
            textView.setTextSize(0, context.getResources().getDimension(R.dimen.sp_18));
            textView.setText(str);
            textView.setGravity(17);
            textView.setMaxWidth(context.getResources().getDimensionPixelSize(R.dimen.dp_480));
            textView.setMinWidth(context.getResources().getDimensionPixelSize(R.dimen.dp_200));
            textView.setPadding(context.getResources().getDimensionPixelSize(R.dimen.dp_24), context.getResources().getDimensionPixelSize(R.dimen.dp_15), context.getResources().getDimensionPixelSize(R.dimen.dp_24), context.getResources().getDimensionPixelSize(R.dimen.dp_15));
            int i3 = context.getResources().getConfiguration().orientation;
            f2503a.setGravity(80, 0, 0);
            f2503a.setDuration(i2);
            textView.animate().scaleX(1.0f).scaleY(1.0f).setInterpolator(b.a()).setDuration(200L).start();
            textView.animate().alpha(1.0f).setInterpolator(b.c()).setDuration(200L).start();
            f2503a.setView(textView);
            f2503a.show();
        }
    }
}
