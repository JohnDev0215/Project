package e1;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.byd.autoservice.R;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: PowerScreenActivity.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static c f2805a = new c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public View f2806b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public FrameLayout f2807c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public f1.b f2808d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public i1.a f2809e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Context f2810f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public WindowManager f2811g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public WindowManager.LayoutParams f2812h = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public View f2813i = null;

    /* JADX INFO: compiled from: PowerScreenActivity.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            c.this.c();
        }
    }

    public static c b() {
        return f2805a;
    }

    public void a() {
        if (this.f2811g == null) {
            return;
        }
        Settings.Global.putInt(this.f2810f.getContentResolver(), "navibar_mode_on_screensaver", 1);
        BydLogHelper.logD("PowerScreenActivity", "hidePowerWindow mView =" + this.f2813i);
        this.f2811g.removeView(this.f2813i);
        this.f2809e.h(false);
        this.f2809e.i(false);
        this.f2811g = null;
        this.f2809e = null;
    }

    public void c() {
        a();
    }

    public void d() {
        this.f2806b.setBackgroundResource(R.drawable.bg_main_activity);
    }

    public final void e() {
        d();
    }

    public void f(Context context) {
        this.f2810f = context;
        if (this.f2809e == null && this.f2811g == null) {
            Settings.Global.putInt(context.getContentResolver(), "navibar_mode_on_screensaver", 0);
            this.f2809e = new i1.a(this.f2810f);
            this.f2811g = (WindowManager) this.f2810f.getSystemService("window");
            BydLogHelper.logD("PowerScreenActivity", "showPowerWindow11111");
            this.f2809e.h(true);
            this.f2809e.i(true);
            this.f2813i = LayoutInflater.from(this.f2810f).inflate(R.layout.activity_power_screen, (ViewGroup) null);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            this.f2812h = layoutParams;
            if (Build.VERSION.SDK_INT >= 26) {
                layoutParams.type = 2038;
            } else {
                layoutParams.type = 2003;
            }
            layoutParams.flags = -2080373757;
            layoutParams.format = 1;
            layoutParams.gravity = 17;
            layoutParams.width = -1;
            layoutParams.height = -1;
            layoutParams.systemUiVisibility = 2054;
            layoutParams.rotationAnimation = 1;
            ImageView imageView = (ImageView) this.f2813i.findViewById(R.id.iv_animation_audio_screen);
            this.f2807c = (FrameLayout) this.f2813i.findViewById(R.id.fl_clock_widget);
            this.f2806b = this.f2813i.findViewById(R.id.bg_audio_screen);
            imageView.setVisibility(0);
            e();
            this.f2811g.addView(this.f2813i, this.f2812h);
            this.f2806b.setOnClickListener(new a());
        }
    }
}
