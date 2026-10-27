package j1;

import android.content.Context;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.byd.autoservice.R;
import i1.g;

/* JADX INFO: compiled from: UsetDefindWindow.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f3501b;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public AppCompatButton f3517r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public AppCompatButton f3518s;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public View f3502c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public WindowManager f3503d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public WindowManager.LayoutParams f3504e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public View f3505f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public RadioGroup f3506g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public RadioButton f3507h = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public RadioButton f3508i = null;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public RadioButton f3509j = null;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public RadioButton f3510k = null;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public RadioButton f3511l = null;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public RadioButton f3512m = null;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public RadioButton f3513n = null;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public ConstraintLayout f3514o = null;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f3515p = 0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public TextView f3516q = null;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f3519t = false;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f3520u = 0;

    public b(Context context) {
        this.f3501b = null;
        this.f3501b = context;
        e();
    }

    public void a() {
        this.f3503d.removeView(this.f3502c);
        this.f3503d = null;
        this.f3519t = false;
        f1.b.S0().d();
    }

    public void b() {
        i();
    }

    public final boolean c() {
        Log.d("HSJFLDSF", "getSupportNaviHome == " + SystemProperties.get("persist.sys.automap_onekey_home"));
        return !TextUtils.isEmpty(r0);
    }

    public final void d(int i3) {
        int iA = i3 == 0 ? i1.b.a(this.f3501b, f1.b.S0().O0()) : i1.b.b(this.f3501b, f1.b.S0().O0());
        this.f3515p = iA;
        switch (iA) {
            case 0:
                this.f3510k.setChecked(true);
                break;
            case 1:
                this.f3511l.setChecked(true);
                break;
            case 2:
                this.f3512m.setChecked(true);
                break;
            case 3:
                this.f3513n.setChecked(true);
                break;
            case 4:
                this.f3507h.setChecked(true);
                break;
            case 5:
                this.f3508i.setChecked(true);
                break;
            case 6:
                this.f3509j.setChecked(true);
                break;
        }
        this.f3506g.setOnCheckedChangeListener(this);
    }

    public final void e() {
        if (g.a() == 1) {
            this.f3502c = LayoutInflater.from(this.f3501b).inflate(R.layout.right_driver_dialog_base, (ViewGroup) null);
        } else {
            this.f3502c = LayoutInflater.from(this.f3501b).inflate(R.layout.dialog_base, (ViewGroup) null);
        }
        this.f3505f = this.f3502c.findViewById(R.id.dilaog_line);
        this.f3517r = (AppCompatButton) this.f3502c.findViewById(R.id.btn_ok);
        this.f3518s = (AppCompatButton) this.f3502c.findViewById(R.id.btn_cancel);
        this.f3517r.setOnClickListener(this);
        this.f3518s.setOnClickListener(this);
        this.f3506g = (RadioGroup) this.f3502c.findViewById(R.id.defined_group);
        this.f3507h = (RadioButton) this.f3502c.findViewById(R.id.rotate_btn_play_pause);
        this.f3508i = (RadioButton) this.f3502c.findViewById(R.id.radio_btn_source);
        this.f3509j = (RadioButton) this.f3502c.findViewById(R.id.radio_btn_navi_home);
        this.f3510k = (RadioButton) this.f3502c.findViewById(R.id.rotate_btn);
        this.f3511l = (RadioButton) this.f3502c.findViewById(R.id.radio_btn);
        this.f3512m = (RadioButton) this.f3502c.findViewById(R.id.music_btn);
        this.f3513n = (RadioButton) this.f3502c.findViewById(R.id.ac_btn);
        this.f3514o = (ConstraintLayout) this.f3502c.findViewById(R.id.layout_user);
        this.f3516q = (TextView) this.f3502c.findViewById(R.id.tv_title);
    }

    public boolean f() {
        return this.f3519t;
    }

    public void g() {
        if (this.f3503d != null) {
            a();
        }
    }

    public void h(int i3) {
        char c3;
        this.f3520u = i3;
        int iO0 = f1.b.S0().O0();
        Log.d("LKJSDFJHDS", "carId == " + iO0);
        if (123 == iO0 || 180 == iO0) {
            this.f3507h.setVisibility(0);
            this.f3508i.setVisibility(0);
            if (c()) {
                this.f3509j.setVisibility(0);
                c3 = 3;
            } else {
                this.f3509j.setVisibility(8);
                c3 = 2;
            }
            this.f3510k.setVisibility(8);
            this.f3511l.setVisibility(8);
            this.f3512m.setVisibility(8);
            this.f3513n.setVisibility(8);
        } else {
            this.f3507h.setVisibility(8);
            this.f3508i.setVisibility(8);
            this.f3509j.setVisibility(8);
            this.f3510k.setVisibility(0);
            this.f3511l.setVisibility(0);
            this.f3512m.setVisibility(0);
            this.f3513n.setVisibility(0);
            c3 = 4;
        }
        d(i3);
        this.f3503d = (WindowManager) this.f3501b.getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.f3504e = layoutParams;
        layoutParams.type = 2002;
        layoutParams.flags = -2080374744;
        layoutParams.format = 1;
        layoutParams.gravity = 17;
        layoutParams.width = -1;
        layoutParams.height = -1;
        layoutParams.x = 0;
        layoutParams.y = 0;
        ConstraintLayout.b bVar = (ConstraintLayout.b) this.f3514o.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) bVar).height = (int) this.f3501b.getResources().getDimension(R.dimen.dp_360);
        if (c3 == 2) {
            ((ViewGroup.MarginLayoutParams) bVar).height = (int) this.f3501b.getResources().getDimension(R.dimen.dp_312);
        } else if (c3 == 3) {
            ((ViewGroup.MarginLayoutParams) bVar).height = (int) this.f3501b.getResources().getDimension(R.dimen.dp_360);
        } else if (c3 == 4) {
            ((ViewGroup.MarginLayoutParams) bVar).height = (int) this.f3501b.getResources().getDimension(R.dimen.dp_408);
        } else if (c3 > 4) {
            ((ViewGroup.MarginLayoutParams) bVar).height = (int) this.f3501b.getResources().getDimension(R.dimen.dp_456);
        }
        this.f3514o.setLayoutParams(bVar);
        i();
        if (!f()) {
            this.f3503d.addView(this.f3502c, this.f3504e);
        }
        this.f3519t = true;
    }

    public final void i() {
        if (a.a.a(this.f3501b).b() == 1) {
            this.f3514o.setBackground(this.f3501b.getDrawable(R.drawable.bg_dialog_mr));
            this.f3517r.setBackground(this.f3501b.getDrawable(R.drawable.denza_dialog_btn_p_shape));
            this.f3518s.setBackground(this.f3501b.getDrawable(R.drawable.denza_dialog_btn_n_shape));
            this.f3517r.setTextColor(this.f3501b.getColor(R.color.color_auto_toast_text));
            this.f3518s.setTextColor(this.f3501b.getColor(R.color.color_auto_toast_text));
        } else {
            this.f3514o.setBackground(this.f3501b.getDrawable(R.drawable.bg_dialog));
            this.f3517r.setBackground(this.f3501b.getDrawable(R.drawable.bg_button_default));
            this.f3518s.setBackground(this.f3501b.getDrawable(R.drawable.bg_button_normal));
            this.f3517r.setTextColor(this.f3501b.getColor(R.color.selector_text_button));
            this.f3518s.setTextColor(this.f3501b.getColor(R.color.selector_text_button));
        }
        this.f3507h.setTextColor(this.f3501b.getColor(R.color.text_title));
        this.f3507h.setText(R.string.radio_button_play_pause);
        this.f3508i.setTextColor(this.f3501b.getColor(R.color.text_title));
        this.f3508i.setText(R.string.radio_button_source);
        this.f3509j.setTextColor(this.f3501b.getColor(R.color.text_title));
        this.f3509j.setText(R.string.radio_button_navi_home);
        this.f3510k.setTextColor(this.f3501b.getColor(R.color.text_title));
        this.f3511l.setTextColor(this.f3501b.getColor(R.color.text_title));
        this.f3512m.setTextColor(this.f3501b.getColor(R.color.text_title));
        this.f3513n.setTextColor(this.f3501b.getColor(R.color.text_title));
        this.f3516q.setTextColor(this.f3501b.getColor(R.color.text_title));
        this.f3505f.setBackgroundColor(this.f3501b.getColor(R.color.divider));
        if (180 == f1.b.S0().O0()) {
            this.f3516q.setText(this.f3520u == 0 ? R.string.dialog_title_user_left : R.string.dialog_title_user_right);
        } else {
            this.f3516q.setText(R.string.dialog_title_user);
        }
        this.f3517r.setText(R.string.btn_comfirm);
        this.f3518s.setText(R.string.cancel);
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i3) {
        switch (i3) {
            case R.id.ac_btn /* 2131230734 */:
                this.f3515p = 3;
                break;
            case R.id.music_btn /* 2131230971 */:
                this.f3515p = 2;
                break;
            case R.id.radio_btn /* 2131231002 */:
                this.f3515p = 1;
                break;
            case R.id.radio_btn_navi_home /* 2131231003 */:
                this.f3515p = 6;
                break;
            case R.id.radio_btn_source /* 2131231004 */:
                this.f3515p = 5;
                break;
            case R.id.rotate_btn /* 2131231015 */:
                this.f3515p = 0;
                break;
            case R.id.rotate_btn_play_pause /* 2131231016 */:
                this.f3515p = 4;
                break;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel /* 2131230811 */:
                a();
                break;
            case R.id.btn_ok /* 2131230812 */:
                if (this.f3520u == 1) {
                    i1.b.e(this.f3501b, f1.b.S0().O0(), this.f3515p);
                } else {
                    i1.b.d(this.f3501b, f1.b.S0().O0(), this.f3515p);
                }
                a();
                break;
        }
    }
}
