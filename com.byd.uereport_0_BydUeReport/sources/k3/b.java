package k3;

import android.content.Context;
import android.hardware.bydauto.dipilot.BYDAutoDiPilotDevice;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import android.hardware.bydauto.test.AbsBYDAutoTestListener;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.byd.bt.model.BTDevice;
import com.byd.uereport.R;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: CpdWindowManager.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements View.OnClickListener {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static AbsBYDAutoTestListener f5855d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static volatile b f5856e;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public WindowManager f5861j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public WindowManager.LayoutParams f5862k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Context f5863l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public View f5864m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public View f5865n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public View f5866o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f5867p;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f5857f = "cpd_win";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f5858g = "byd_cpd_window_event";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f5859h = 1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f5860i = 0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f5868q = 3000;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f5869r = 3001;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Handler f5870s = new Handler(Looper.getMainLooper(), new a());

    /* JADX INFO: compiled from: CpdWindowManager.java */
    public class a implements Handler.Callback {
        public a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i8 = message.what;
            if (i8 == 3000) {
                b.this.r();
            } else if (i8 == 3001) {
                BYDAutoOtaDevice bYDAutoOtaDevice = BYDAutoOtaDevice.getInstance(b.this.f5863l);
                if (bYDAutoOtaDevice.getBcmOtaState() == 1) {
                    b.this.f5867p = false;
                    BydLogHelper.logD("cpd_win", "NOT SCREEN OFF , because in OTA UPGRADING....");
                } else if (b.this.f5867p) {
                    b.this.f5867p = false;
                    BydLogHelper.logD("cpd_win", "NOT SCREEN OFF , because OTA  block screen off....");
                } else {
                    bYDAutoOtaDevice.setBackLightState(2);
                }
            }
            return false;
        }
    }

    /* JADX INFO: renamed from: k3.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CpdWindowManager.java */
    public class RunnableC0073b implements Runnable {
        public RunnableC0073b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.j();
        }
    }

    /* JADX INFO: compiled from: CpdWindowManager.java */
    public class c extends AbsBYDAutoTestListener {
        public c() {
        }

        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onGetOffScreenFlagResult(int i8, int i9) {
            BydLogHelper.logD(AbsBYDAutoTestListener.TAG, "onGetOffScreenFlagResult type:" + i8 + ", resultCode:" + i9);
            if (i9 == 1) {
                b.this.f5867p = true;
            }
        }
    }

    /* JADX INFO: compiled from: CpdWindowManager.java */
    public class d implements View.OnTouchListener {
        public d() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action == 0) {
                b.this.f5870s.sendEmptyMessageDelayed(3000, 3000L);
            } else if (action == 1) {
                b.this.f5870s.removeMessages(3000);
            }
            return true;
        }
    }

    public b(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.f5863l = applicationContext;
        this.f5861j = (WindowManager) applicationContext.getSystemService("window");
        o();
    }

    public static b m(Context context) {
        if (f5856e == null) {
            synchronized (b.class) {
                if (f5856e == null) {
                    f5856e = new b(context);
                }
            }
        }
        return f5856e;
    }

    public void f() {
        h();
        i();
    }

    public void g() {
        BydLogHelper.logD("cpd_win", "dismiss cpd view , screen off .");
        h();
        View view = this.f5864m;
        if (view != null) {
            this.f5861j.removeView(view);
            this.f5864m = null;
            this.f5870s.sendEmptyMessage(3001);
            u();
            v(0);
        }
    }

    public void h() {
        BydLogHelper.logD("cpd_win", "dismiss cpd dialog .");
        View view = this.f5865n;
        if (view != null) {
            this.f5861j.removeView(view);
            this.f5865n = null;
        }
    }

    public void i() {
        BydLogHelper.logD("cpd_win", "dismiss cpd view .");
        View view = this.f5864m;
        if (view != null) {
            this.f5861j.removeView(view);
            this.f5864m = null;
            v(0);
        }
    }

    public final void j() {
        BydLogHelper.logD("cpd_win", "show toast error view .");
        View view = this.f5866o;
        if (view != null) {
            this.f5861j.removeView(view);
            this.f5866o = null;
        }
    }

    public final View k() {
        View viewInflate = LayoutInflater.from(this.f5863l).inflate(R.layout.dialog_cpd_confirm, (ViewGroup) null);
        ConstraintLayout constraintLayout = (ConstraintLayout) viewInflate.findViewById(R.id.cl_content);
        if (g5.c.e() == 1) {
            constraintLayout.setLayoutDirection(1);
        } else {
            constraintLayout.setLayoutDirection(0);
        }
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_confirm);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.tv_cancel);
        View viewFindViewById = viewInflate.findViewById(R.id.background);
        textView.setOnClickListener(this);
        textView2.setOnClickListener(this);
        viewFindViewById.setOnClickListener(this);
        return viewInflate;
    }

    public final View l() {
        View viewInflate = LayoutInflater.from(this.f5863l).inflate(R.layout.dialog_child_presence_detection, (ViewGroup) null);
        viewInflate.findViewById(R.id.btn_exit).setOnTouchListener(new d());
        return viewInflate;
    }

    public final View n() {
        View viewInflate = LayoutInflater.from(this.f5863l).inflate(R.layout.layout_toast_night, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_msg);
        textView.setText(this.f5863l.getString(R.string.text_cpd_toast_exit_tip));
        textView.postDelayed(new RunnableC0073b(), BTDevice.CONNECTED_TIME_STANDARD);
        return viewInflate;
    }

    public final void o() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.f5862k = layoutParams;
        layoutParams.type = 2045;
        layoutParams.format = -3;
        layoutParams.flags = 142608168;
        layoutParams.flags = 142608168 | 16777216;
        layoutParams.windowAnimations = R.style.default_style;
        layoutParams.softInputMode = 16;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.background || id == R.id.tv_cancel) {
            h();
        } else {
            if (id != R.id.tv_confirm) {
                return;
            }
            BYDAutoDiPilotDevice.getInstance(this.f5863l).setCpdStatus(2);
            f();
            t();
            this.f5870s.sendEmptyMessageDelayed(3001, 1000L);
        }
    }

    public void p() {
        if (this.f5864m != null) {
            i();
            s();
        }
        if (this.f5865n != null) {
            h();
            r();
        }
    }

    public final void q() {
        BydLogHelper.logD("cpd_win", "registerOffScreenFlag.");
        BYDAutoTestDevice bYDAutoTestDevice = BYDAutoTestDevice.getInstance(this.f5863l);
        c cVar = new c();
        f5855d = cVar;
        bYDAutoTestDevice.registerListener((AbsBYDAutoTestListener) cVar);
        BYDAutoTestDevice.getInstance(this.f5863l).queryOffScreenFlag(0);
    }

    public void r() {
        BydLogHelper.logD("cpd_win", "show cpd dialog .");
        if (this.f5865n == null) {
            View viewK = k();
            this.f5865n = viewK;
            this.f5861j.addView(viewK, this.f5862k);
        }
    }

    public void s() {
        BydLogHelper.logD("cpd_win", "show cpd view .");
        if (this.f5864m == null) {
            View viewL = l();
            this.f5864m = viewL;
            this.f5861j.addView(viewL, this.f5862k);
            v(1);
            q();
        }
    }

    public void t() {
        BydLogHelper.logD("cpd_win", "show cpd toast .");
        if (this.f5866o == null) {
            this.f5866o = n();
            WindowManager.LayoutParams layoutParams = this.f5862k;
            layoutParams.width = -2;
            layoutParams.height = -2;
            if (this.f5863l.getResources().getConfiguration().orientation == 2) {
                this.f5862k.y = (-((int) this.f5863l.getResources().getDimension(R.dimen.dp_360))) + ((int) this.f5863l.getResources().getDimension(R.dimen.dp_28)) + ((int) this.f5863l.getResources().getDimension(R.dimen.dp_128));
            } else {
                this.f5862k.y = (-((int) this.f5863l.getResources().getDimension(R.dimen.dp_640))) + ((int) this.f5863l.getResources().getDimension(R.dimen.dp_28)) + ((int) this.f5863l.getResources().getDimension(R.dimen.dp_246));
            }
            this.f5861j.addView(this.f5866o, this.f5862k);
            WindowManager.LayoutParams layoutParams2 = this.f5862k;
            layoutParams2.width = -1;
            layoutParams2.height = -1;
            layoutParams2.y = 0;
        }
    }

    public final void u() {
        BYDAutoTestDevice.getInstance(this.f5863l).unregisterListener(f5855d);
    }

    public final void v(int i8) {
        Settings.System.putInt(this.f5863l.getContentResolver(), "byd_cpd_window_event", i8);
        g5.a.b("cpd_win", "updateCpdSettings:" + Settings.System.getInt(this.f5863l.getContentResolver(), "byd_cpd_window_event", i8));
    }
}
