package com.byd.filemanager.ui.c;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.byd.filemanager.R;

/* JADX INFO: compiled from: BaseDialog.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<T> extends g implements View.OnClickListener {
    protected c<T> k0;
    protected T l0;
    protected Context m0;
    protected TextView n0;
    protected TextView o0;
    protected TextView p0;
    protected View q0;
    protected View r0;
    protected View s0;
    protected Dialog t0;
    protected boolean u0 = true;
    protected int v0;
    protected int w0;
    protected int x0;

    /* JADX INFO: renamed from: com.byd.filemanager.ui.c.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BaseDialog.java */
    class DialogInterfaceOnKeyListenerC0090a implements DialogInterface.OnKeyListener {
        DialogInterfaceOnKeyListenerC0090a() {
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            return a.this.W1(i, keyEvent);
        }
    }

    /* JADX INFO: compiled from: BaseDialog.java */
    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            a.this.V1();
        }
    }

    /* JADX INFO: compiled from: BaseDialog.java */
    public interface c<T> {
        void a(int i, T t);
    }

    private int H1() {
        return com.byd.filemanager.f.h().v() ? T1() ? R.layout.dialog_base_progress : R.layout.dialog_base : T1() ? R.layout.dialog_base_progress_denza : R.layout.dialog_base_denza;
    }

    private int I1() {
        return com.byd.filemanager.f.h().v() ? R.layout.dialog_base : R.layout.dialog_base_denza;
    }

    private int J1() {
        return com.byd.filemanager.f.h().v() ? com.byd.filemanager.f.h().x() ? R.layout.layout_buttons_rh : R.layout.layout_buttons : com.byd.filemanager.f.h().x() ? R.layout.layout_buttons_denza_rh : R.layout.layout_buttons_denza;
    }

    private int K1() {
        return com.byd.filemanager.f.h().v() ? R.layout.layout_single_button : R.layout.layout_single_button_denza;
    }

    private int L1() {
        return com.byd.filemanager.f.h().v() ? R.layout.layout_single_button_positive : R.layout.layout_single_button_positive_denza;
    }

    private void Q1() {
        int[] iArr = new int[2];
        b2(iArr);
        this.v0 = iArr[0] == 0 ? -2 : this.m0.getResources().getDimensionPixelSize(iArr[0]);
        this.w0 = iArr[1] != 0 ? this.m0.getResources().getDimensionPixelSize(iArr[1]) : -2;
    }

    @Override // com.byd.filemanager.ui.c.g
    public Dialog E1(Bundle bundle) {
        Dialog dialog = new Dialog(j1(), R.style.NormalDialog);
        this.t0 = dialog;
        dialog.setCancelable(false);
        this.t0.setCanceledOnTouchOutside(false);
        this.t0.setOnKeyListener(new DialogInterfaceOnKeyListenerC0090a());
        Window window = this.t0.getWindow();
        window.setWindowAnimations(R.style.dialog_anim_style);
        window.getDecorView().setSystemUiVisibility(M1());
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        window.setNavigationBarColor(0);
        if (Build.VERSION.SDK_INT >= 28) {
            attributes.layoutInDisplayCutoutMode = 1;
        }
        window.setAttributes(attributes);
        return this.t0;
    }

    @Override // androidx.fragment.app.Fragment
    public void F0() {
        super.F0();
        Window window = this.t0.getWindow();
        if (this.u0) {
            window.setLayout(-1, -1);
        } else {
            window.setLayout(this.v0, this.w0);
        }
    }

    protected int M1() {
        return 1280;
    }

    protected abstract int N1();

    protected abstract void O1(View view);

    protected abstract void P1(Bundle bundle);

    protected abstract String R1();

    protected void S1(View view, LayoutInflater layoutInflater) {
        this.q0 = view.findViewById(R.id.ly_title);
        this.p0 = (TextView) view.findViewById(R.id.tv_title);
        String strR1 = R1();
        if (TextUtils.isEmpty(strR1)) {
            this.q0.setVisibility(8);
        } else {
            this.p0.setText(strR1);
        }
        layoutInflater.inflate(X1(), (ViewGroup) view.findViewById(R.id.ly_content), true);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.ly_buttons);
        int iN1 = N1();
        if (iN1 == 0) {
            layoutInflater.inflate(J1(), (ViewGroup) relativeLayout, true);
            TextView textView = (TextView) view.findViewById(R.id.btn_ok);
            this.n0 = textView;
            textView.setOnClickListener(this);
            TextView textView2 = (TextView) view.findViewById(R.id.btn_cancel);
            this.o0 = textView2;
            textView2.setOnClickListener(this);
        } else if (iN1 == 1) {
            layoutInflater.inflate(K1(), (ViewGroup) relativeLayout, true);
            TextView textView3 = (TextView) view.findViewById(R.id.btn_cancel);
            this.o0 = textView3;
            textView3.setOnClickListener(this);
        } else {
            layoutInflater.inflate(L1(), (ViewGroup) relativeLayout, true);
            TextView textView4 = (TextView) view.findViewById(R.id.btn_ok);
            this.n0 = textView4;
            textView4.setOnClickListener(this);
        }
        O1(view);
    }

    protected boolean T1() {
        return false;
    }

    public boolean U1() {
        Dialog dialog = this.t0;
        return dialog != null && dialog.isShowing();
    }

    public void V1() {
        z1();
    }

    protected boolean W1(int i, KeyEvent keyEvent) {
        if (i != 4 || keyEvent.getAction() != 1) {
            return false;
        }
        z1();
        return true;
    }

    protected abstract int X1();

    public void Y1(c<T> cVar) {
        this.k0 = cVar;
    }

    protected void Z1(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.q0.setVisibility(0);
        this.p0.setText(str);
    }

    public void a2(boolean z) {
        this.r0.setVisibility(z ? 0 : 4);
    }

    protected abstract void b2(int[] iArr);

    public void c2(androidx.fragment.app.g gVar) {
        super.G1(gVar, getClass().getSimpleName());
    }

    @Override // com.byd.filemanager.ui.c.g, androidx.fragment.app.Fragment
    public void e0(Bundle bundle) {
        super.e0(bundle);
        P1(bundle);
    }

    @Override // com.byd.filemanager.ui.c.g, androidx.fragment.app.Fragment
    public void h0(Context context) {
        super.h0(context);
        this.m0 = context;
    }

    @Override // androidx.fragment.app.Fragment
    public View o0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Q1();
        View viewInflate = layoutInflater.inflate(this.u0 ? R.layout.dialog_fullscreen : I1(), viewGroup, true);
        this.r0 = viewInflate;
        if (this.u0) {
            viewInflate.findViewById(R.id.root).setOnClickListener(new b());
            ConstraintLayout constraintLayout = (ConstraintLayout) this.r0.findViewById(R.id.content);
            LayoutInflater.from(this.m0).inflate(H1(), (ViewGroup) constraintLayout, true);
            ConstraintLayout.b bVar = (ConstraintLayout.b) constraintLayout.getLayoutParams();
            ((ViewGroup.MarginLayoutParams) bVar).width = this.v0;
            ((ViewGroup.MarginLayoutParams) bVar).height = this.w0;
            constraintLayout.setLayoutParams(bVar);
            this.s0 = constraintLayout;
        }
        S1(this.r0, layoutInflater);
        return this.r0;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        c<T> cVar;
        int id = view.getId();
        if (id == R.id.btn_ok) {
            c<T> cVar2 = this.k0;
            if (cVar2 != null) {
                cVar2.a(0, this.l0);
            }
        } else if (id == R.id.btn_cancel && (cVar = this.k0) != null) {
            cVar.a(1, null);
        }
        z1();
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Window window = this.t0.getWindow();
        if (this.u0) {
            window.setLayout(-1, -1);
        } else {
            window.setLayout(this.v0, this.w0);
        }
    }

    @Override // com.byd.filemanager.ui.c.g, androidx.fragment.app.Fragment
    public void r0() {
        this.t0.setOnKeyListener(null);
        super.r0();
    }

    @Override // com.byd.filemanager.ui.c.g, androidx.fragment.app.Fragment
    public void s0() {
        super.s0();
        this.m0 = null;
    }
}
