package com.byd.uereport.dialog;

import android.content.Context;
import android.hardware.bydauto.TsNormalNum;
import android.hardware.bydauto.vehiclehealth.BYDAutoVehicleHealthDevice;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import b5.g;
import com.byd.uereport.R;
import g5.l;
import java.lang.ref.WeakReference;
import z3.e;

/* JADX INFO: loaded from: classes2.dex */
public class OverhaulEPBDialog extends g {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f3245d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static int f3246e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static int f3247f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f3248g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Context f3249h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public TextView f3250i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public ImageView f3251j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ProgressBar f3252k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ProgressBar f3253l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f3254m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f3255n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f3256o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public d f3257p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Message f3258q;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            boolean unused = OverhaulEPBDialog.f3245d = !OverhaulEPBDialog.f3245d;
            if (OverhaulEPBDialog.f3245d) {
                OverhaulEPBDialog.this.x();
            } else {
                OverhaulEPBDialog.this.y();
            }
            OverhaulEPBDialog overhaulEPBDialog = OverhaulEPBDialog.this;
            overhaulEPBDialog.onWindowAttributesChanged(overhaulEPBDialog.mLayoutParams);
        }
    }

    public class b implements View.OnTouchListener {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f3260d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f3261e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f3262f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f3263g;

        public b() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (!OverhaulEPBDialog.f3245d) {
                return true;
            }
            int action = motionEvent.getAction();
            if (action == 0) {
                this.f3260d = (int) motionEvent.getRawX();
                this.f3261e = (int) motionEvent.getRawY();
                this.f3262f = (int) motionEvent.getRawX();
                this.f3263g = (int) motionEvent.getRawY();
                return false;
            }
            if (action == 1) {
                int rawX = (int) motionEvent.getRawX();
                if (this.f3262f != rawX) {
                    return false;
                }
                int i8 = this.f3263g;
                return false;
            }
            if (action != 2) {
                return false;
            }
            int rawX2 = (int) motionEvent.getRawX();
            int rawY = (int) motionEvent.getRawY();
            int i9 = rawX2 - this.f3260d;
            int i10 = rawY - this.f3261e;
            this.f3260d = rawX2;
            this.f3261e = rawY;
            OverhaulEPBDialog.f3246e += i9;
            OverhaulEPBDialog.f3247f += i10;
            OverhaulEPBDialog.this.f3258q = Message.obtain();
            OverhaulEPBDialog.this.f3258q.what = 4;
            OverhaulEPBDialog.this.f3258q.arg1 = OverhaulEPBDialog.f3246e;
            OverhaulEPBDialog.this.f3258q.arg2 = OverhaulEPBDialog.f3247f;
            OverhaulEPBDialog.this.f3257p.sendMessage(OverhaulEPBDialog.this.f3258q);
            return false;
        }
    }

    public class c implements View.OnTouchListener {
        public c() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action != 0) {
                if (action == 1 || action == 3) {
                    OverhaulEPBDialog.this.f3256o = false;
                    OverhaulEPBDialog.this.f3257p.sendEmptyMessage(0);
                }
            } else if (OverhaulEPBDialog.this.f3252k.getVisibility() != 0) {
                OverhaulEPBDialog.this.f3256o = true;
                OverhaulEPBDialog.this.f3257p.sendEmptyMessage(1);
                OverhaulEPBDialog.this.f3257p.removeMessages(2);
                OverhaulEPBDialog.this.f3257p.sendEmptyMessageDelayed(2, OverhaulEPBDialog.this.f3255n);
            }
            return true;
        }
    }

    public static class d extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<OverhaulEPBDialog> f3266a;

        public d(OverhaulEPBDialog overhaulEPBDialog) {
            this.f3266a = new WeakReference<>(overhaulEPBDialog);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (this.f3266a.get() == null) {
                return;
            }
            try {
                int i8 = message.what;
                if (i8 == 0) {
                    this.f3266a.get().f3253l.setVisibility(8);
                    return;
                }
                if (i8 == 1) {
                    this.f3266a.get().f3254m = 0;
                    this.f3266a.get().f3253l.setProgress(0);
                    this.f3266a.get().f3253l.setVisibility(0);
                    return;
                }
                if (i8 != 2) {
                    if (i8 == 3) {
                        this.f3266a.get().dismiss();
                        return;
                    }
                    if (i8 == 4) {
                        this.f3266a.get().mLayoutParams.x = message.arg1;
                        this.f3266a.get().mLayoutParams.y = message.arg2;
                        this.f3266a.get().onWindowAttributesChanged(this.f3266a.get().mLayoutParams);
                        return;
                    }
                    return;
                }
                OverhaulEPBDialog.i(this.f3266a.get(), TsNormalNum.THOUSAND_FIVE / this.f3266a.get().f3255n);
                if (this.f3266a.get().f3254m < 5000) {
                    this.f3266a.get().f3253l.setProgress(this.f3266a.get().f3254m / this.f3266a.get().f3255n);
                    if (this.f3266a.get().f3256o) {
                        sendEmptyMessageDelayed(2, this.f3266a.get().f3255n);
                        return;
                    }
                    return;
                }
                this.f3266a.get().f3253l.setProgress(100);
                this.f3266a.get().f3252k.setVisibility(0);
                this.f3266a.get().f3252k.setPadding(this.f3266a.get().f3249h.getResources().getDimensionPixelSize(R.dimen.dp_10), 0, 0, 0);
                this.f3266a.get().f3250i.setPadding(this.f3266a.get().f3249h.getResources().getDimensionPixelSize(R.dimen.dp_2), 0, this.f3266a.get().f3249h.getResources().getDimensionPixelSize(R.dimen.dp_24), 0);
                BYDAutoVehicleHealthDevice.getInstance(this.f3266a.get().f3249h).setEPBState(1);
                sendEmptyMessageDelayed(3, e.INTERVAL);
            } catch (Exception e8) {
                e8.printStackTrace();
            }
        }
    }

    public OverhaulEPBDialog(Context context) {
        super(context);
        this.f3248g = getClass().getSimpleName();
        this.f3254m = 0;
        this.f3255n = 50;
        this.f3256o = false;
        this.f3257p = null;
        this.f3249h = context;
    }

    public static /* synthetic */ int i(OverhaulEPBDialog overhaulEPBDialog, int i8) {
        int i9 = overhaulEPBDialog.f3254m + i8;
        overhaulEPBDialog.f3254m = i9;
        return i9;
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void cancel() {
        super.cancel();
        try {
            l.b("sys.byd.uereport.epb", "0");
        } catch (Exception e8) {
            e8.printStackTrace();
        }
    }

    @Override // b5.g, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        d dVar = this.f3257p;
        if (dVar != null) {
            dVar.removeCallbacksAndMessages(null);
        }
        try {
            l.b("sys.byd.uereport.epb", "0");
        } catch (Exception e8) {
            e8.printStackTrace();
        }
    }

    @Override // b5.g
    public void initContent(View view) {
        this.f3252k = (ProgressBar) view.findViewById(R.id.progressbar);
        this.f3253l = (ProgressBar) view.findViewById(R.id.progress_time_longclick);
        this.f3250i = (TextView) view.findViewById(R.id.tv_ok);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_title);
        this.f3251j = imageView;
        imageView.setOnClickListener(new a());
        this.mRoot.setOnTouchListener(new b());
        this.f3250i.setOnTouchListener(new c());
        if (f3245d) {
            x();
        } else {
            y();
        }
        onWindowAttributesChanged(this.mLayoutParams);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    @Override // b5.g
    public void initData() {
        this.f3257p = new d(this);
    }

    @Override // b5.g
    public int initLayoutRes() {
        return r4.a.a(R.layout.dialog_carhealthy_epb);
    }

    @Override // b5.g
    public boolean isFullScreen() {
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // b5.g
    public void setWidthAndHeight(int[] iArr) {
    }

    @Override // b5.g, android.app.Dialog
    public void show() {
        super.show();
        try {
            l.b("sys.byd.uereport.epb", "1");
        } catch (Exception e8) {
            e8.printStackTrace();
        }
    }

    public final void x() {
        this.mLayoutParams.height = getContext().getResources().getDimensionPixelSize(R.dimen.dp_531);
        this.mLayoutParams.width = getContext().getResources().getDimensionPixelSize(R.dimen.dp_476);
        this.mLayoutParams.dimAmount = 0.0f;
        if (f3246e == 0) {
            f3246e = (g5.e.e(getContext()) - this.mLayoutParams.width) / 2;
        }
        if (f3247f == 0) {
            f3247f = (-(g5.e.d(getContext()) - this.mLayoutParams.height)) / 2;
        }
        WindowManager.LayoutParams layoutParams = this.mLayoutParams;
        layoutParams.x = f3246e;
        layoutParams.y = f3247f;
        findViewById(R.id.ll_content2).setVisibility(0);
        findViewById(R.id.dampingScrollView).setVisibility(8);
        this.f3251j.setImageDrawable(getContext().getDrawable(R.drawable.icon_dark_sentry_video_enlarge_48));
        getWindow().addFlags(32);
    }

    public final void y() {
        boolean z7 = (getContext().getResources().getConfiguration().uiMode & 48) == 32;
        this.mLayoutParams.height = getContext().getResources().getDimensionPixelSize(R.dimen.dp_714);
        this.mLayoutParams.width = getContext().getResources().getDimensionPixelSize(R.dimen.dp_636);
        WindowManager.LayoutParams layoutParams = this.mLayoutParams;
        layoutParams.dimAmount = z7 ? 0.72f : 0.16f;
        layoutParams.x = 0;
        layoutParams.y = 0;
        findViewById(R.id.ll_content2).setVisibility(8);
        findViewById(R.id.dampingScrollView).setVisibility(0);
        this.f3251j.setImageDrawable(getContext().getDrawable(R.drawable.icon_dark_sentry_video_narrow_48));
        getWindow().clearFlags(32);
    }

    public void z() {
    }
}
