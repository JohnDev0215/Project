package f5;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.byd.uereport.R;
import com.byd.uereport.dialog.DisplayAutoDialog;
import com.byd.uereport.dialog.DisplayEngineDialog;
import com.byd.uereport.dialog.DisplayModeDialog;
import com.bydux.api.common.tools.log.BydLogHelper;
import p3.e;
import p3.f;

/* JADX INFO: compiled from: DisplayModeServiceImp.java */
/* JADX INFO: loaded from: classes2.dex */
public class a implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile e f4966a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f4967b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public DisplayModeDialog f4968c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f4969d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f4970e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public DisplayAutoDialog f4971f;

    /* JADX INFO: renamed from: f5.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DisplayModeServiceImp.java */
    public class ViewOnTouchListenerC0042a implements View.OnTouchListener {
        public ViewOnTouchListenerC0042a() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            motionEvent.getAction();
            return true;
        }
    }

    /* JADX INFO: compiled from: DisplayModeServiceImp.java */
    public class b implements View.OnTouchListener {
        public b() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() != 4) {
                return true;
            }
            BydLogHelper.logD("Display", "onTouch: 点击外侧");
            return true;
        }
    }

    public a(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.f4967b = applicationContext;
        s3.a.h(applicationContext).c(this);
        f.getInstance();
    }

    public static e h(Context context) {
        if (f4966a == null) {
            synchronized (a.class) {
                if (f4966a == null) {
                    f4966a = new a(context);
                }
            }
        }
        return f4966a;
    }

    @Override // p3.e
    public void a() {
        DisplayModeDialog displayModeDialog = this.f4968c;
        if (displayModeDialog != null && displayModeDialog.isShowing()) {
            this.f4968c.dismiss();
            d();
        }
        DisplayAutoDialog displayAutoDialog = this.f4971f;
        if (displayAutoDialog == null || !displayAutoDialog.isShowing()) {
            DisplayAutoDialog displayAutoDialog2 = new DisplayAutoDialog(this.f4967b, f(), "", this);
            this.f4971f = displayAutoDialog2;
            displayAutoDialog2.show();
        }
    }

    @Override // p3.e
    public void b(int i8) {
        Context context = this.f4967b;
        if (context == null) {
            return;
        }
        j(i8, context.getString(R.string.the_vehicle_cannot_be_driven_under_display_mode));
    }

    @Override // p3.e
    public void c(int i8) {
        Context context = this.f4967b;
        if (context == null) {
            return;
        }
        if (1 == i8) {
            j(3, context.getString(R.string.display_exit_static_indoor));
        } else if (2 == i8) {
            j(3, context.getString(R.string.display_exit_static_outdoor));
        }
    }

    @Override // p3.e
    public void d() {
        if (this.f4968c != null) {
            this.f4968c = null;
            this.f4970e = false;
        }
    }

    @Override // p3.e
    public void e() {
        DisplayAutoDialog displayAutoDialog = this.f4971f;
        boolean z7 = displayAutoDialog != null && displayAutoDialog.isShowing();
        DisplayModeDialog displayModeDialog = this.f4968c;
        if (((displayModeDialog == null || !displayModeDialog.isShowing()) ? z7 ? this.f4971f : null : this.f4968c) == null) {
            return;
        }
        if (z7) {
            g();
            return;
        }
        int iD = this.f4968c.d();
        if (iD < 1) {
            return;
        }
        this.f4968c.dismiss();
        Context context = this.f4967b;
        DisplayModeDialog displayModeDialog2 = new DisplayModeDialog(context, s3.a.h(context).b(), this.f4969d, this);
        this.f4968c = displayModeDialog2;
        displayModeDialog2.f(true);
        this.f4968c.show();
        this.f4968c.c(iD);
    }

    @Override // p3.e
    public int f() {
        return s3.a.h(this.f4967b).b();
    }

    public final void g() {
        DisplayAutoDialog displayAutoDialog = this.f4971f;
        if (displayAutoDialog == null) {
            return;
        }
        if (displayAutoDialog.isShowing()) {
            this.f4971f.dismiss();
        }
        this.f4971f = null;
    }

    public final void i() {
        DisplayModeDialog displayModeDialog = this.f4968c;
        if (displayModeDialog != null && this.f4970e) {
            displayModeDialog.dismiss();
            d();
        }
        try {
            new DisplayEngineDialog(this.f4967b).show();
        } catch (Exception e8) {
            e8.printStackTrace();
        }
    }

    public final void j(int i8, String str) {
        g();
        DisplayModeDialog displayModeDialog = this.f4968c;
        if (displayModeDialog == null || !this.f4970e) {
            this.f4969d = str;
            DisplayModeDialog displayModeDialog2 = new DisplayModeDialog(this.f4967b, i8, str, this);
            this.f4968c = displayModeDialog2;
            displayModeDialog2.getWindow().getDecorView().setOnTouchListener(new b());
            this.f4968c.f(true);
            this.f4968c.show();
            this.f4970e = true;
        } else {
            displayModeDialog.g(str);
        }
        BydLogHelper.logI("DisplayModeServiceImp", "updateDialogMsg =" + str);
    }

    @Override // p3.e
    public void showCarModelChange(int i8) {
        if (i8 <= 0 || i8 > 3) {
            return;
        }
        g();
        if (s3.a.h(this.f4967b).d() && i8 == 2) {
            i();
            return;
        }
        DisplayModeDialog displayModeDialog = this.f4968c;
        if (displayModeDialog != null && displayModeDialog.isShowing()) {
            if (1 == i8) {
                j(i8, this.f4967b.getString(R.string.display_into_static_indoor));
                return;
            } else {
                if (2 == i8) {
                    j(i8, this.f4967b.getString(R.string.display_into_static_outdoor));
                    return;
                }
                return;
            }
        }
        this.f4969d = "";
        DisplayModeDialog displayModeDialog2 = new DisplayModeDialog(this.f4967b, i8, "", this);
        this.f4968c = displayModeDialog2;
        displayModeDialog2.setCanceledOnTouchOutside(false);
        this.f4968c.setCancelable(false);
        try {
            this.f4968c.show();
            this.f4968c.getWindow().getDecorView().setOnTouchListener(new ViewOnTouchListenerC0042a());
            this.f4970e = true;
        } catch (Exception e8) {
            e8.printStackTrace();
        }
    }
}
