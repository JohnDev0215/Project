package h3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import com.byd.systemsettings.DeviceTipsDialog;
import com.byd.uereport.R;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: CpaaCustomToastManager.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile a f5327a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f5328b = "cpaa_win";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public WindowManager f5329c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public WindowManager.LayoutParams f5330d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Context f5331e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public View f5332f;

    /* JADX INFO: renamed from: h3.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CpaaCustomToastManager.java */
    public class C0052a extends i5.b {
        public C0052a(Context context, int i8, boolean z7) {
            super(context, i8, z7);
        }

        @Override // i5.b, android.text.style.ClickableSpan
        public void onClick(View view) {
            super.onClick(view);
            a.this.c();
            new DeviceTipsDialog(a.this.f5331e).show();
        }
    }

    /* JADX INFO: compiled from: CpaaCustomToastManager.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.c();
        }
    }

    public a(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.f5331e = applicationContext;
        this.f5329c = (WindowManager) applicationContext.getSystemService("window");
        f();
    }

    public static a e(Context context) {
        if (f5327a == null) {
            synchronized (a.class) {
                if (f5327a == null) {
                    f5327a = new a(context);
                }
            }
        }
        return f5327a;
    }

    public final void c() {
        BydLogHelper.logD("cpaa_win", "show toast error view .");
        View view = this.f5332f;
        if (view != null) {
            this.f5329c.removeView(view);
            this.f5332f = null;
        }
    }

    @SuppressLint({"StringFormatInvalid"})
    public final View d() {
        View viewInflate = LayoutInflater.from(this.f5331e).inflate(R.layout.layout_custom_cpaa_toast, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_tip);
        String str = String.format(this.f5331e.getString(R.string.cp_toast_connect_fail), this.f5331e.getString(R.string.cp_guide)) + " ";
        String string = this.f5331e.getString(R.string.cp_go_to);
        SpannableString spannableString = new SpannableString(str + string);
        Context context = this.f5331e;
        spannableString.setSpan(new C0052a(context, context.getColor(R.color.toast_more_color), false), str.length(), (str + string).length(), 17);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(spannableString);
        textView.setHighlightColor(0);
        textView.postDelayed(new b(), 3500L);
        return viewInflate;
    }

    public final void f() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.f5330d = layoutParams;
        layoutParams.type = 2038;
        layoutParams.format = -3;
        layoutParams.flags = 142608168;
        layoutParams.flags = 142608168 | 16777216;
        layoutParams.windowAnimations = R.style.default_style;
        layoutParams.softInputMode = 16;
    }

    public void g() {
        BydLogHelper.logD("cpaa_win", "show carplay toast .");
        if (this.f5332f == null) {
            this.f5332f = d();
            WindowManager.LayoutParams layoutParams = this.f5330d;
            layoutParams.width = -2;
            layoutParams.height = -2;
            if (this.f5331e.getResources().getConfiguration().orientation == 2) {
                this.f5330d.y = (-((int) this.f5331e.getResources().getDimension(R.dimen.dp_360))) + ((int) this.f5331e.getResources().getDimension(R.dimen.dp_28)) + ((int) this.f5331e.getResources().getDimension(R.dimen.dp_128));
            } else {
                this.f5330d.y = (-((int) this.f5331e.getResources().getDimension(R.dimen.dp_640))) + ((int) this.f5331e.getResources().getDimension(R.dimen.dp_28)) + ((int) this.f5331e.getResources().getDimension(R.dimen.dp_246));
            }
            this.f5329c.addView(this.f5332f, this.f5330d);
            WindowManager.LayoutParams layoutParams2 = this.f5330d;
            layoutParams2.width = -1;
            layoutParams2.height = -1;
            layoutParams2.y = 0;
        }
    }
}
