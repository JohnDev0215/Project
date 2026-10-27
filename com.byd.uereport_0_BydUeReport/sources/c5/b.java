package c5;

import android.content.Context;
import android.hardware.bydauto.ac.AbsBYDAutoAcListener;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import com.byd.uereport.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: compiled from: AcHighTempSterilizationDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static TextView f2314a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BYDAutoAcDevice f2315b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public HandlerC0014b f2316c = new HandlerC0014b(this, null);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public WindowManager f2317d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public WindowManager.LayoutParams f2318e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Context f2319f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public View f2320g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ArrayList<Integer> f2321h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f2322i;

    /* JADX INFO: compiled from: AcHighTempSterilizationDialog.java */
    public class a extends AbsBYDAutoAcListener {
        public a() {
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onHighTempAntivirusCountDownChanged(int i8) {
            super.onHighTempAntivirusCountDownChanged(i8);
            BydLogHelper.logD("onHighTempAntivirusCountDownChanged time = " + i8);
            Message messageObtain = Message.obtain();
            messageObtain.what = 0;
            messageObtain.arg1 = i8;
            b.this.f2316c.sendMessage(messageObtain);
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onHighTempAntivirusStateChanged(int i8) {
            super.onHighTempAntivirusStateChanged(i8);
            BydLogHelper.logD("onHighTempAntivirusStateChanged,state:" + i8);
            if (i8 != 1) {
                b.this.c();
            }
        }
    }

    /* JADX INFO: renamed from: c5.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AcHighTempSterilizationDialog.java */
    public static class HandlerC0014b extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<b> f2324a;

        public /* synthetic */ HandlerC0014b(b bVar, a aVar) {
            this(bVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            WeakReference<b> weakReference = this.f2324a;
            if (weakReference == null || weakReference.get() == null || message.what != 0) {
                return;
            }
            b.f2314a.setText(String.valueOf(message.arg1));
            if (message.arg1 <= 0) {
                this.f2324a.get().c();
            }
        }

        public HandlerC0014b(b bVar) {
            this.f2324a = new WeakReference<>(bVar);
        }
    }

    public b(Context context) {
        this.f2319f = context;
        BYDAutoAcDevice bYDAutoAcDevice = BYDAutoAcDevice.getInstance(this.f2319f);
        this.f2315b = bYDAutoAcDevice;
        bYDAutoAcDevice.registerListener((AbsBYDAutoAcListener) new a());
        f();
        this.f2321h = new ArrayList<>();
        this.f2317d = (WindowManager) this.f2319f.getSystemService("window");
        e();
    }

    public void c() {
        int iHashCode = hashCode();
        BydLogHelper.logD("dismiss dialogId :" + iHashCode);
        BydLogHelper.logD("dismiss mDialogIds :" + this.f2321h);
        if (this.f2321h.contains(Integer.valueOf(iHashCode))) {
            this.f2321h.remove(Integer.valueOf(iHashCode));
            this.f2317d.removeView(this.f2320g);
            this.f2322i = false;
        }
    }

    public void d(View view) {
        f2314a = (TextView) view.findViewById(R.id.tv_remain_time);
        f2314a.setText(String.valueOf(this.f2315b.getHighTempAntivirusCountDown()));
    }

    public final void e() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.f2318e = layoutParams;
        layoutParams.type = 2024;
        layoutParams.format = -3;
        layoutParams.flags = 142608168;
        layoutParams.flags = 142608168 | 16777216;
        layoutParams.windowAnimations = R.style.default_style;
        layoutParams.gravity = 17;
        layoutParams.softInputMode = 16;
    }

    public final void f() {
        View viewInflate = LayoutInflater.from(this.f2319f).inflate(R.layout.dialog_ac_high_temp_sterilization_tips, (ViewGroup) null);
        this.f2320g = viewInflate;
        d(viewInflate);
    }

    public boolean g() {
        BydLogHelper.logD("isShowing() mIsShowing :" + this.f2322i);
        return this.f2322i;
    }

    public void h() {
        this.f2322i = true;
        int iHashCode = hashCode();
        BydLogHelper.logD("show dialogId :" + iHashCode);
        BydLogHelper.logD("show mDialogIds :" + this.f2321h);
        if (this.f2321h.contains(Integer.valueOf(iHashCode))) {
            return;
        }
        WindowManager.LayoutParams layoutParams = this.f2318e;
        layoutParams.width = -1;
        layoutParams.height = -1;
        if (this.f2320g != null) {
            this.f2321h.add(Integer.valueOf(iHashCode));
            this.f2317d.addView(this.f2320g, this.f2318e);
        }
    }
}
