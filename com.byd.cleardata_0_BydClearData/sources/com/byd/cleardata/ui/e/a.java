package com.byd.cleardata.ui.e;

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
import com.byd.cleardata.R;

/* JADX INFO: loaded from: classes.dex */
public abstract class a<T> extends h implements View.OnClickListener {
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

    /* JADX INFO: renamed from: com.byd.cleardata.ui.e.a$a, reason: collision with other inner class name */
    class DialogInterfaceOnKeyListenerC0099a implements DialogInterface.OnKeyListener {
        DialogInterfaceOnKeyListenerC0099a() {
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            return a.this.P1(i, keyEvent);
        }
    }

    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            a.this.O1();
        }
    }

    public interface c<T> {
        void a(int i, T t);
    }

    private void K1() {
        int[] iArr = new int[2];
        U1(iArr);
        this.v0 = iArr[0] == 0 ? -2 : this.m0.getResources().getDimensionPixelSize(iArr[0]);
        this.w0 = iArr[1] != 0 ? this.m0.getResources().getDimensionPixelSize(iArr[1]) : -2;
    }

    @Override // com.byd.cleardata.ui.e.h
    public Dialog D1(Bundle bundle) {
        Dialog dialog = new Dialog(j1(), R.style.NormalDialog);
        this.t0 = dialog;
        dialog.setCancelable(false);
        this.t0.setCanceledOnTouchOutside(false);
        this.t0.setOnKeyListener(new DialogInterfaceOnKeyListenerC0099a());
        Window window = this.t0.getWindow();
        window.setWindowAnimations(R.style.dialog_anim_style);
        window.getDecorView().setSystemUiVisibility(G1());
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

    protected int G1() {
        return 1280;
    }

    protected abstract int H1();

    protected abstract void I1(View view);

    protected void J1(Bundle bundle) {
    }

    protected abstract String L1();

    protected void M1(View view, LayoutInflater layoutInflater) {
        TextView textView;
        this.q0 = view.findViewById(R.id.ly_title);
        this.p0 = (TextView) view.findViewById(R.id.tv_title);
        String strL1 = L1();
        if (TextUtils.isEmpty(strL1)) {
            this.q0.setVisibility(8);
        } else {
            this.p0.setText(strL1);
        }
        layoutInflater.inflate(Q1(), (ViewGroup) view.findViewById(R.id.ly_content), true);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.ly_buttons);
        int iH1 = H1();
        if (iH1 == 0) {
            layoutInflater.inflate(R.layout.layout_buttons, (ViewGroup) relativeLayout, true);
            TextView textView2 = (TextView) view.findViewById(R.id.btn_ok);
            this.n0 = textView2;
            textView2.setOnClickListener(this);
        } else {
            if (iH1 != 1) {
                layoutInflater.inflate(R.layout.layout_single_button_positive, (ViewGroup) relativeLayout, true);
                textView = (TextView) view.findViewById(R.id.btn_ok);
                this.n0 = textView;
                textView.setOnClickListener(this);
                I1(view);
            }
            layoutInflater.inflate(R.layout.layout_single_button, (ViewGroup) relativeLayout, true);
        }
        textView = (TextView) view.findViewById(R.id.btn_cancel);
        this.o0 = textView;
        textView.setOnClickListener(this);
        I1(view);
    }

    public boolean N1() {
        Dialog dialog = this.t0;
        return dialog != null && dialog.isShowing();
    }

    public void O1() {
        z1();
    }

    protected boolean P1(int i, KeyEvent keyEvent) {
        if (i != 4 || keyEvent.getAction() != 1) {
            return false;
        }
        z1();
        return true;
    }

    protected abstract int Q1();

    public void R1(c<T> cVar) {
        this.k0 = cVar;
    }

    protected void S1(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.q0.setVisibility(0);
        this.p0.setText(str);
    }

    public void T1(boolean z) {
        this.r0.setVisibility(z ? 0 : 4);
    }

    protected abstract void U1(int[] iArr);

    public void V1(androidx.fragment.app.i iVar) {
        super.F1(iVar, getClass().getSimpleName());
    }

    @Override // com.byd.cleardata.ui.e.h, androidx.fragment.app.Fragment
    public void e0(Bundle bundle) {
        super.e0(bundle);
        J1(bundle);
    }

    @Override // com.byd.cleardata.ui.e.h, androidx.fragment.app.Fragment
    public void h0(Context context) {
        super.h0(context);
        this.m0 = context;
    }

    @Override // androidx.fragment.app.Fragment
    public View o0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        K1();
        View viewInflate = layoutInflater.inflate(this.u0 ? R.layout.dialog_fullscreen : R.layout.dialog_base, viewGroup, true);
        this.r0 = viewInflate;
        if (this.u0) {
            viewInflate.findViewById(R.id.root).setOnClickListener(new b());
            ConstraintLayout constraintLayout = (ConstraintLayout) this.r0.findViewById(R.id.content);
            LayoutInflater.from(this.m0).inflate(R.layout.dialog_base, (ViewGroup) constraintLayout, true);
            ConstraintLayout.b bVar = (ConstraintLayout.b) constraintLayout.getLayoutParams();
            ((ViewGroup.MarginLayoutParams) bVar).width = this.v0;
            ((ViewGroup.MarginLayoutParams) bVar).height = this.w0;
            constraintLayout.setLayoutParams(bVar);
            this.s0 = constraintLayout;
        }
        M1(this.r0, layoutInflater);
        return this.r0;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        c<T> cVar;
        int i;
        T t;
        int id = view.getId();
        if (id == R.id.btn_ok) {
            cVar = this.k0;
            if (cVar != null) {
                i = 0;
                t = this.l0;
                cVar.a(i, t);
            }
        } else if (id == R.id.btn_cancel && (cVar = this.k0) != null) {
            i = 1;
            t = null;
            cVar.a(i, t);
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

    @Override // com.byd.cleardata.ui.e.h, androidx.fragment.app.Fragment
    public void r0() {
        this.t0.setOnKeyListener(null);
        super.r0();
    }

    @Override // com.byd.cleardata.ui.e.h, androidx.fragment.app.Fragment
    public void s0() {
        super.s0();
        this.m0 = null;
    }
}
