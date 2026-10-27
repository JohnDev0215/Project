package com.byd.logtools.auth.f;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.byd.logtools.R;
import com.byd.logtools.auth.utils.Constant$Status;
import com.byd.logtools.auth.utils.e;
import com.byd.logtools.auth.utils.g;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: QrCodeDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends Dialog implements View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ImageView f3130b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ImageView f3131c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private TextView f3132d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private TextView f3133e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private TextView f3134f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private TextView f3135g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private LinearLayout f3136h;
    private TextView i;
    private TextView j;
    private ImageView k;
    private Handler l;
    private Context m;
    private com.byd.logtools.auth.f.a n;
    private ConstraintLayout.b o;
    private ConstraintLayout.b p;
    public int q;
    public Constant$Status r;

    /* JADX INFO: compiled from: QrCodeDialog.java */
    class a implements DialogInterface.OnKeyListener {
        a() {
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            if (i != 4 || keyEvent.getAction() != 1) {
                return false;
            }
            if (b.this.n != null) {
                b.this.n.a();
            }
            b.this.dismiss();
            return true;
        }
    }

    /* JADX INFO: renamed from: com.byd.logtools.auth.f.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: QrCodeDialog.java */
    static /* synthetic */ class C0057b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3138a;

        static {
            int[] iArr = new int[Constant$Status.values().length];
            f3138a = iArr;
            try {
                iArr[Constant$Status.NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3138a[Constant$Status.LOAD_FAIL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3138a[Constant$Status.EXPIRE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3138a[Constant$Status.SUCCESS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3138a[Constant$Status.FAIL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: compiled from: QrCodeDialog.java */
    static class c<T> extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final WeakReference<T> f3139a;

        public c(T t) {
            this.f3139a = new WeakReference<>(t);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.f3139a.get() != null && message.what == 1) {
                ((b) this.f3139a.get()).dismiss();
            }
        }
    }

    public b(Context context, int i) {
        super(context, i);
        this.q = 1;
        this.m = context;
    }

    private void b() {
        BydLogHelper.logD("hideScanTypeLayout");
        m(false);
    }

    private void c() {
        this.f3130b = (ImageView) findViewById(R.id.iv_qrcode);
        this.f3131c = (ImageView) findViewById(R.id.iv_qrcode_state);
        this.f3132d = (TextView) findViewById(R.id.tv_title);
        this.f3133e = (TextView) findViewById(R.id.tv_tips);
        this.f3134f = (TextView) findViewById(R.id.tv_left_time);
        this.f3135g = (TextView) findViewById(R.id.tv_result);
        this.f3136h = (LinearLayout) findViewById(R.id.layout_scan_type);
        this.i = (TextView) findViewById(R.id.tv_we_chat);
        this.j = (TextView) findViewById(R.id.tv_vds);
        this.k = (ImageView) findViewById(R.id.iv_back);
        this.o = (ConstraintLayout.b) this.f3130b.getLayoutParams();
        this.p = (ConstraintLayout.b) this.f3131c.getLayoutParams();
        this.f3131c.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.l = new c(this);
    }

    private void e(int i) {
        if (this.q == i) {
            return;
        }
        this.q = i;
        l(i);
        this.n.b(i);
    }

    private void h(int i, int i2, boolean z) {
        b();
        this.f3135g.setText(i);
        this.f3131c.setImageResource(i2);
        this.f3131c.setVisibility(0);
        this.f3131c.setClickable(z);
    }

    private void i() {
        m(true);
    }

    private void l(int i) {
        if (i == 1) {
            this.i.setTextColor(this.m.getColor(R.color.text_color_sb_selected));
            this.i.setBackgroundDrawable(this.m.getDrawable(R.drawable.bg_segment_button_selected));
            this.j.setTextColor(this.m.getColor(R.color.text_color_sb_unselected));
            this.j.setBackground(null);
            return;
        }
        if (i == 2) {
            this.j.setTextColor(this.m.getColor(R.color.text_color_sb_selected));
            this.j.setBackgroundDrawable(this.m.getDrawable(R.drawable.bg_segment_button_selected));
            this.i.setTextColor(this.m.getColor(R.color.text_color_sb_unselected));
            this.i.setBackground(null);
        }
    }

    private void m(boolean z) {
        ((ViewGroup.MarginLayoutParams) this.o).topMargin = z ? this.m.getResources().getDimensionPixelSize(R.dimen.dp_78) : this.m.getResources().getDimensionPixelSize(R.dimen.dp_50);
        this.f3130b.setLayoutParams(this.o);
        ((ViewGroup.MarginLayoutParams) this.p).topMargin = z ? this.m.getResources().getDimensionPixelSize(R.dimen.dp_78) : this.m.getResources().getDimensionPixelSize(R.dimen.dp_50);
        this.f3131c.setLayoutParams(this.p);
        this.f3136h.setVisibility(z ? 0 : 8);
        this.f3133e.setVisibility(z ? 0 : 8);
        this.f3134f.setVisibility(z ? 0 : 8);
        this.f3135g.setVisibility(z ? 8 : 0);
    }

    @SuppressLint({"UseCompatLoadingForDrawables"})
    public void d() {
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        findViewById(R.id.layout_bg_outside).setBackgroundColor(this.m.getColor(R.color.bg_color_dialog_outside));
        findViewById(R.id.layout_bg).setBackgroundDrawable(this.m.getDrawable(R.drawable.bg_qrcode_dialog));
        this.f3132d.setTextColor(this.m.getColor(R.color.text_color_main_dilink_pagers));
        this.f3133e.setTextColor(this.m.getColor(R.color.text_color_main_dilink_pagers));
        this.f3134f.setTextColor(this.m.getColor(R.color.text_color_qrcode_left_time));
        this.f3135g.setTextColor(this.m.getColor(R.color.text_color_main_dilink_pagers));
        findViewById(R.id.v_line_dialog).setBackgroundColor(this.m.getColor(R.color.bg_color_line));
        this.k.setImageResource(R.drawable.icon_qrcode_close);
        this.f3131c.setBackgroundDrawable(this.m.getDrawable(R.drawable.bg_qrcode_state));
        Constant$Status constant$Status = this.r;
        if (constant$Status == Constant$Status.EXPIRE || constant$Status == Constant$Status.LOAD_FAIL) {
            this.f3131c.setImageResource(R.drawable.icon_qrcode_refresh);
        }
        l(this.q);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        this.n = null;
        this.l.removeCallbacksAndMessages(null);
        super.dismiss();
    }

    public void f(com.byd.logtools.auth.f.a aVar) {
        this.n = aVar;
    }

    public void g(String str) {
        if (str != null) {
            this.f3132d.setText(String.format(this.m.getString(R.string.qrcode_title_with_module), str));
        } else {
            this.f3132d.setText(R.string.qrcode_title);
        }
    }

    public void j(long j) {
        if (j > 0) {
            n(Constant$Status.NORMAL);
            this.f3134f.setVisibility(0);
            this.f3134f.setText(String.format(this.m.getString(R.string.qrcode_time), Long.valueOf((j / 1000) + 1)));
        }
    }

    public void k(String str) {
        this.f3131c.setVisibility(8);
        if (str == null || str.isEmpty()) {
            n(Constant$Status.LOAD_FAIL);
            return;
        }
        n(Constant$Status.NORMAL);
        Bitmap bitmapA = e.a(str, (int) TypedValue.applyDimension(1, 180, this.m.getResources().getDisplayMetrics()));
        if (bitmapA != null) {
            this.f3130b.setImageBitmap(bitmapA);
        }
    }

    public void n(Constant$Status constant$Status) {
        if (constant$Status.equals(this.r)) {
            return;
        }
        BydLogHelper.logD("updateStatus: " + this.r + " -> " + constant$Status);
        this.r = constant$Status;
        int i = C0057b.f3138a[constant$Status.ordinal()];
        if (i == 1) {
            i();
            this.f3133e.setText(R.string.qrcode_tip);
            return;
        }
        if (i == 2) {
            h(R.string.qrcode_refresh_fail, R.drawable.icon_qrcode_refresh, true);
            this.f3130b.setImageResource(R.drawable.image_qrcode_default);
            return;
        }
        if (i == 3) {
            h(R.string.qrcode_expired, R.drawable.icon_qrcode_refresh, true);
            return;
        }
        if (i == 4) {
            h(R.string.qrcode_success, R.drawable.icon_qrcode_succeed, false);
            this.l.sendEmptyMessageDelayed(1, 1000L);
        } else {
            if (i != 5) {
                return;
            }
            h(R.string.qrcode_network_warning, R.drawable.icon_qrcode_warning, false);
            this.l.sendEmptyMessageDelayed(1, 1000L);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back /* 2131296464 */:
                com.byd.logtools.auth.f.a aVar = this.n;
                if (aVar != null) {
                    aVar.a();
                }
                dismiss();
                break;
            case R.id.iv_qrcode_state /* 2131296471 */:
                com.byd.logtools.auth.f.a aVar2 = this.n;
                if (aVar2 != null) {
                    aVar2.b(this.q);
                    e(1);
                }
                break;
            case R.id.tv_vds /* 2131296708 */:
                e(2);
                break;
            case R.id.tv_we_chat /* 2131296709 */:
                e(1);
                break;
        }
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        BydLogHelper.logD("onCreate");
        setContentView(g.c(this.m) ? R.layout.dialog_qrcode_verify_rtl : R.layout.dialog_qrcode_verify);
        c();
        setOnKeyListener(new a());
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
        BydLogHelper.logD("onStart");
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        BydLogHelper.logD("show");
    }
}
