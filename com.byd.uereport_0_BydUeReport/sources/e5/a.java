package e5;

import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.byd.uereport.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import g5.c;

/* JADX INFO: compiled from: MassageTipsDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f4627a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public View f4628b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public WindowManager f4630d;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f4636j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public TextView f4637k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public TextView f4638l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ImageView f4639m;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Handler f4629c = new Handler(Looper.myLooper());

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile int f4631e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4632f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4633g = 1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4634h = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Runnable f4635i = new RunnableC0032a();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int[] f4640n = {R.string.vehiclesettings_seat_massage_mode_close, R.string.vehiclesettings_carseat_massage_wave, R.string.vehiclesettings_carseat_massage_pluse, R.string.vehiclesettings_carseat_massage_stretch, R.string.vehiclesettings_seat_massage_mode_slack, R.string.vehiclesettings_seat_massage_mode_relieve};

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int[] f4641o = {R.string.vehiclesettings_control_reminder_touch_weak, R.string.vehiclesettings_control_reminder_touch_middle, R.string.vehiclesettings_control_touch_strong};

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int[] f4642p = {R.attr.icon_dark_float_off_48, R.attr.icon_dark_float_wave_48, R.attr.icon_dark_float_pulse_48, R.attr.icon_dark_float_stretch_48, R.attr.icon_dark_float_waist_48, R.attr.icon_dark_float_shoulder_48};

    /* JADX INFO: renamed from: e5.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MassageTipsDialog.java */
    public class RunnableC0032a implements Runnable {
        public RunnableC0032a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.b(a.this);
            BydLogHelper.logD("count ： " + a.this.f4631e);
            if (a.this.f4631e == 3) {
                a.this.g();
            } else {
                a.this.f4629c.postDelayed(this, 1000L);
            }
        }
    }

    public a(Context context, int i8) {
        this.f4627a = context;
        this.f4636j = i8;
    }

    public static /* synthetic */ int b(a aVar) {
        int i8 = aVar.f4631e;
        aVar.f4631e = i8 + 1;
        return i8;
    }

    public void d() {
        BydLogHelper.logD("isShow : " + this.f4632f);
        Handler handler = this.f4629c;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        WindowManager windowManager = this.f4630d;
        if (windowManager != null && this.f4632f) {
            windowManager.removeView(this.f4628b);
        }
        this.f4632f = false;
    }

    public final boolean e(int i8) {
        if (c.e() == 0) {
            if (i8 != 1) {
                return false;
            }
        } else if (i8 != 2) {
            return false;
        }
        return true;
    }

    public boolean f() {
        return this.f4632f;
    }

    public synchronized void g() {
        if (this.f4631e == 3) {
            d();
        }
    }

    public void h(int i8, int i9, int i10) {
        this.f4630d = (WindowManager) this.f4627a.getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = 2038;
        layoutParams.flags = 787752;
        layoutParams.flags = 787752 | 16777216;
        layoutParams.format = -3;
        layoutParams.width = (int) this.f4627a.getResources().getDimension(R.dimen.dp_151);
        layoutParams.height = -2;
        this.f4630d.getDefaultDisplay().getSize(new Point());
        layoutParams.y = (int) this.f4627a.getResources().getDimension(R.dimen.dp_80);
        layoutParams.x = (int) this.f4627a.getResources().getDimension(R.dimen.dp_40);
        layoutParams.gravity = e(i10) ? 8388659 : 8388661;
        View viewInflate = LayoutInflater.from(this.f4627a).inflate(R.layout.dialog_massage_tips_layout, (ViewGroup) null);
        this.f4628b = viewInflate;
        TextView textView = (TextView) viewInflate.findViewById(R.id.ue_report_massage_title_tv);
        int i11 = this.f4636j;
        if (i11 == 1) {
            textView.setText(this.f4628b.getContext().getString(R.string.vehiclesettings_seat_turn_main));
        } else if (i11 == 2) {
            textView.setText(this.f4628b.getContext().getString(R.string.vehiclesettings_seat_turn_passagenger));
        }
        this.f4637k = (TextView) this.f4628b.findViewById(R.id.ue_report_massage_mode_tv);
        this.f4638l = (TextView) this.f4628b.findViewById(R.id.ue_report_massage_level_tv);
        this.f4639m = (ImageView) this.f4628b.findViewById(R.id.ue_report_massage_mode_img);
        this.f4630d.addView(this.f4628b, layoutParams);
        this.f4632f = true;
        BydLogHelper.logD("MassageDialog", "updateMode , mode: " + i8 + " level : " + i9 + " location : " + i10 + " lastModel : " + this.f4633g + " lastLevel : " + this.f4634h);
        if (i8 != this.f4633g || i9 != this.f4634h) {
            i();
            l(i8, i9);
        }
        this.f4629c.postDelayed(this.f4635i, 1000L);
    }

    public void i() {
        this.f4631e = 0;
    }

    public void j(int i8, int i9, int i10, boolean z7) {
        BydLogHelper.logD("MassageDialog", "updateLevel , mode: " + i8 + " level : " + i9 + " location : " + i10 + " lastLevel : " + this.f4634h + " needShow : " + z7);
        if (!z7) {
            this.f4634h = i9;
            this.f4633g = i8;
            return;
        }
        if (i9 != this.f4634h) {
            if (this.f4632f) {
                i();
                l(i8, i9);
            } else {
                h(i8, i9, i10);
            }
        }
        this.f4634h = i9;
        this.f4633g = i8;
    }

    public void k(int i8, int i9, int i10, boolean z7) {
        BydLogHelper.logD("MassageDialog", "updateMode , mode: " + i8 + " level : " + i9 + " location : " + i10 + " lastModel : " + this.f4633g + " needShow : " + z7);
        if (!z7) {
            this.f4634h = i9;
            this.f4633g = i8;
            return;
        }
        if (i8 != this.f4633g) {
            if (this.f4632f) {
                i();
                l(i8, i9);
            } else {
                h(i8, i9, i10);
            }
        }
        this.f4634h = i9;
        this.f4633g = i8;
    }

    public final void l(int i8, int i9) {
        TextView textView;
        if (i8 >= 1 && i8 <= 6) {
            TextView textView2 = this.f4637k;
            if (textView2 != null) {
                textView2.setText(this.f4640n[i8 - 1]);
                TextView textView3 = this.f4638l;
                if (textView3 != null) {
                    textView3.setVisibility(i8 == 1 ? 8 : 0);
                }
                this.f4633g = i8;
            }
            ImageView imageView = this.f4639m;
            if (imageView != null) {
                imageView.setImageResource(q2.a.a(this.f4627a, this.f4642p[i8 - 1]));
            }
        }
        if (i9 < 1 || i9 > 3 || (textView = this.f4638l) == null) {
            return;
        }
        textView.setText(this.f4641o[i9 - 1]);
        this.f4634h = i9;
    }
}
