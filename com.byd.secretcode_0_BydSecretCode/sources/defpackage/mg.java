package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.byd.secretcode.R;
import com.bydux.api.common.tools.util.ScreenUtil;

/* JADX INFO: compiled from: Bydtoast.java */
/* JADX INFO: loaded from: classes.dex */
public class mg {
    public static void a(Activity activity, String str) {
        Configuration configuration = activity.getResources().getConfiguration();
        ScreenUtil.getScreenHeight(activity);
        ScreenUtil.getScreenWidth(activity);
        int i = configuration.orientation == 2 ? 190 : 72;
        View viewInflate = activity.getLayoutInflater().inflate(R.layout.custom_toast, (ViewGroup) activity.findViewById(R.id.toast_layout_root));
        ((TextView) viewInflate.findViewById(R.id.tv_toast)).setText(str);
        Toast toast = new Toast(activity.getApplicationContext());
        toast.setGravity(48, 0, i);
        toast.setDuration(1);
        toast.setView(viewInflate);
        toast.show();
    }

    public static void b(Context context, String str) {
        Toast toast = new Toast(context);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.dp_72);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.auto_toast, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_content);
        toast.setView(viewInflate);
        toast.setGravity(48, 0, dimensionPixelSize);
        textView.setText(str);
        toast.setDuration(0);
        toast.show();
    }
}
