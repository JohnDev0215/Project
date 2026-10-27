package i1;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.byd.autoservice.R;

/* JADX INFO: compiled from: Bydtoast.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static c f3283a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public transient Context f3284b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Dialog f3285c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TextView f3286d;

    public c(Context context) {
        this.f3284b = context;
        a();
    }

    public static c b(Context context) {
        synchronized (c.class) {
            if (f3283a == null) {
                f3283a = new c(context.getApplicationContext());
            }
        }
        return f3283a;
    }

    public final void a() {
        this.f3285c = new Dialog(this.f3284b, R.style.MyToastDialogTheme);
        int identifier = this.f3284b.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier > 0) {
            this.f3284b.getResources().getDimensionPixelSize(identifier);
        }
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.height = -2;
        layoutParams.width = -2;
        layoutParams.format = -3;
        layoutParams.type = 2006;
        layoutParams.windowAnimations = android.R.style.Animation.Toast;
        layoutParams.flags = 152;
        layoutParams.gravity = 48;
        layoutParams.y = this.f3284b.getResources().getDimensionPixelSize(this.f3284b.getResources().getConfiguration().orientation == 1 ? R.dimen.dp_230 : R.dimen.dp_112);
        Window window = this.f3285c.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setDimAmount(0.0f);
        window.clearFlags(2);
        window.setAttributes(layoutParams);
        View viewInflate = LayoutInflater.from(this.f3284b).inflate(R.layout.auto_toast, (ViewGroup) null);
        viewInflate.setPadding(this.f3284b.getResources().getDimensionPixelSize(R.dimen.dp_24), this.f3284b.getResources().getDimensionPixelSize(R.dimen.dp_15), this.f3284b.getResources().getDimensionPixelSize(R.dimen.dp_24), this.f3284b.getResources().getDimensionPixelSize(R.dimen.dp_15));
        this.f3286d = (TextView) viewInflate.findViewById(R.id.tv_content);
        this.f3285c.setContentView(viewInflate);
    }

    public void c() {
        Dialog dialog = this.f3285c;
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public void d(String str) {
        TextView textView = this.f3286d;
        if (textView != null) {
            textView.setText(str);
        }
        Dialog dialog = this.f3285c;
        if (dialog != null) {
            dialog.show();
        }
    }
}
