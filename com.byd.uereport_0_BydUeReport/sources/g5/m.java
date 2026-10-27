package g5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.byd.uereport.R;
import com.bydux.api.common.views.window.ToastUtils;

/* JADX INFO: compiled from: ToastUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class m {
    public static Toast a(Context context, String str, int i8) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.layout_toast, (ViewGroup) null);
        ((TextView) viewInflate.findViewById(R.id.tv_msg)).setText(str);
        Toast toast = new Toast(context);
        toast.setGravity(49, 0, ((int) context.getResources().getDimension(R.dimen.dp_128)) - e.f(context));
        toast.setDuration(i8);
        toast.setView(viewInflate);
        return toast;
    }

    public static Toast b(Context context, String str, int i8, boolean z7) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.layout_toast, (ViewGroup) null);
        ((TextView) viewInflate.findViewById(R.id.tv_msg)).setText(str);
        Toast toast = new Toast(context);
        int iF = e.f(context);
        if (z7) {
            toast.setGravity(49, 0, ((int) context.getResources().getDimension(R.dimen.dp_128)) - iF);
        } else {
            toast.setGravity(49, 0, ((int) context.getResources().getDimension(R.dimen.dp_452)) - iF);
        }
        toast.setDuration(i8);
        toast.setView(viewInflate);
        return toast;
    }

    public static void c(Context context, int i8) {
        d(context, context.getResources().getString(i8));
    }

    public static void d(Context context, String str) {
        ToastUtils.show(context, str);
    }

    public static void e(Context context, String str, int i8) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.layout_toast, (ViewGroup) null);
        ((TextView) viewInflate.findViewById(R.id.tv_msg)).setText(str);
        Toast toast = new Toast(context);
        toast.setGravity(49, 0, ((int) context.getResources().getDimension(R.dimen.dp_128)) - e.f(context));
        toast.setDuration(i8);
        toast.setView(viewInflate);
        toast.show();
    }
}
