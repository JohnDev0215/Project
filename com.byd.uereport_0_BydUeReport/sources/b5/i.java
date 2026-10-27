package b5;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import com.byd.bt.model.BTDevice;
import com.byd.uereport.R;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: LockWindowManager.java */
/* JADX INFO: loaded from: classes2.dex */
public class i implements View.OnClickListener {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static volatile i f2084d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public WindowManager f2086f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public WindowManager.LayoutParams f2087g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Context f2088h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public View f2089i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public View f2090j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public AppCompatEditText f2091k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public AppCompatButton f2092l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f2093m;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f2085e = "Lock_win";

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f2094n = "1996";

    /* JADX INFO: compiled from: LockWindowManager.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            i.this.e();
        }
    }

    /* JADX INFO: compiled from: LockWindowManager.java */
    public class b implements TextWatcher {
        public b() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i8, int i9, int i10) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i8, int i9, int i10) {
        }
    }

    /* JADX INFO: compiled from: LockWindowManager.java */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            i.this.f2091k.requestFocus();
            i.this.k();
        }
    }

    public i(Context context) {
        this.f2086f = (WindowManager) context.getSystemService("window");
        i();
        this.f2088h = context.getApplicationContext();
    }

    public static i f(Context context) {
        if (f2084d == null) {
            synchronized (i.class) {
                if (f2084d == null) {
                    f2084d = new i(context);
                }
            }
        }
        return f2084d;
    }

    public void d() {
        View view = this.f2089i;
        if (view != null) {
            this.f2086f.removeView(view);
            this.f2089i = null;
        }
    }

    public final void e() {
        BydLogHelper.logD("Lock_win", "show toast error view .");
        View view = this.f2090j;
        if (view != null) {
            this.f2086f.removeView(view);
            this.f2090j = null;
        }
    }

    public final View g() {
        View viewInflate = LayoutInflater.from(this.f2088h).inflate(R.layout.layout_lock_win, (ViewGroup) null);
        this.f2091k = (AppCompatEditText) viewInflate.findViewById(R.id.et_pwd);
        AppCompatButton appCompatButton = (AppCompatButton) viewInflate.findViewById(R.id.btn_verify);
        this.f2092l = appCompatButton;
        appCompatButton.setOnClickListener(this);
        this.f2091k.addTextChangedListener(new b());
        this.f2091k.postDelayed(new c(), 1000L);
        return viewInflate;
    }

    public final View h(Context context, String str) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.layout_toast_night, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_msg);
        textView.setText(str);
        textView.postDelayed(new a(), BTDevice.CONNECTED_TIME_STANDARD);
        return viewInflate;
    }

    public final void i() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.f2087g = layoutParams;
        layoutParams.type = 2045;
        layoutParams.format = -3;
        layoutParams.flags = 142608128;
        layoutParams.flags = 142608128 | 16777216;
        layoutParams.windowAnimations = R.style.default_style;
        layoutParams.softInputMode = 16;
    }

    public i j(String str) {
        this.f2093m = str;
        return this;
    }

    public final void k() {
        ((InputMethodManager) this.f2088h.getSystemService("input_method")).toggleSoftInput(0, 2);
    }

    public void l() {
        BydLogHelper.logD("Lock_win", "show lock view .");
        if (this.f2089i == null) {
            View viewG = g();
            this.f2089i = viewG;
            this.f2086f.addView(viewG, this.f2087g);
        }
    }

    public final void m(Context context, String str) {
        BydLogHelper.logD("Lock_win", "show toast error view .");
        if (this.f2090j == null) {
            WindowManager.LayoutParams layoutParams = this.f2087g;
            layoutParams.width = -2;
            layoutParams.height = -2;
            if (this.f2088h.getResources().getConfiguration().orientation == 2) {
                this.f2087g.y = (-((int) this.f2088h.getResources().getDimension(R.dimen.dp_360))) + ((int) this.f2088h.getResources().getDimension(R.dimen.dp_28)) + ((int) this.f2088h.getResources().getDimension(R.dimen.dp_128));
            } else {
                this.f2087g.y = (-((int) this.f2088h.getResources().getDimension(R.dimen.dp_640))) + ((int) this.f2088h.getResources().getDimension(R.dimen.dp_28)) + ((int) this.f2088h.getResources().getDimension(R.dimen.dp_246));
            }
            View viewH = h(context, str);
            this.f2090j = viewH;
            this.f2086f.addView(viewH, this.f2087g);
            WindowManager.LayoutParams layoutParams2 = this.f2087g;
            layoutParams2.width = -1;
            layoutParams2.height = -1;
            layoutParams2.y = 0;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.btn_verify) {
            return;
        }
        String strA = g5.f.a("/vehicleconfig/bind_vin_code.data");
        if (TextUtils.isEmpty(strA) || strA.length() < 6) {
            return;
        }
        if (!(strA.substring(strA.length() - 6) + "1996").equals(this.f2091k.getText().toString())) {
            Context context = this.f2088h;
            m(context, context.getString(R.string.toast_bind_pwd_error));
            return;
        }
        BydLogHelper.logD("Lock_win", "save new vin :" + this.f2093m);
        g5.f.b("/vehicleconfig/bind_vin_code.data", this.f2093m, false);
        d();
    }
}
