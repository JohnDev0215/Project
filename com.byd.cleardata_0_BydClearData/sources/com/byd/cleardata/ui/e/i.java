package com.byd.cleardata.ui.e;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.byd.cleardata.R;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public class i extends h {
    private Dialog k0;

    class a implements DialogInterface.OnKeyListener {
        a() {
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            if (i != 4 || keyEvent.getAction() != 1) {
                return false;
            }
            com.byd.cleardata.i.c.b("onKeyBack");
            org.greenrobot.eventbus.c.c().k(new com.byd.cleardata.e.f(20));
            return false;
        }
    }

    @Override // com.byd.cleardata.ui.e.h
    public Dialog D1(Bundle bundle) {
        Dialog dialog = new Dialog(p(), R.style.NormalDialog);
        this.k0 = dialog;
        dialog.requestWindowFeature(1);
        this.k0.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        this.k0.getWindow().setDimAmount(0.0f);
        this.k0.setCancelable(false);
        this.k0.setCanceledOnTouchOutside(false);
        this.k0.setOnKeyListener(new a());
        return this.k0;
    }

    @Override // androidx.fragment.app.Fragment
    public void F0() {
        super.F0();
        B1().getWindow().setLayout(L().getDimensionPixelSize(R.dimen.dp_264), L().getDimensionPixelSize(R.dimen.dp_152));
    }

    @Override // com.byd.cleardata.ui.e.h
    public void F1(androidx.fragment.app.i iVar, String str) {
        try {
            Field declaredField = h.class.getDeclaredField("i0");
            declaredField.setAccessible(true);
            declaredField.set(this, Boolean.FALSE);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        try {
            Field declaredField2 = h.class.getDeclaredField("j0");
            declaredField2.setAccessible(true);
            declaredField2.set(this, Boolean.TRUE);
        } catch (IllegalAccessException | NoSuchFieldException e2) {
            e2.printStackTrace();
        }
        androidx.fragment.app.n nVarA = iVar.a();
        nVarA.b(this, str);
        nVarA.e();
    }

    protected void G1(View view) {
    }

    public boolean H1() {
        Dialog dialogB1 = B1();
        return dialogB1 != null && dialogB1.isShowing();
    }

    public void I1(androidx.fragment.app.i iVar) {
        F1(iVar, i.class.getSimpleName());
    }

    @Override // androidx.fragment.app.Fragment
    public View o0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_loading, viewGroup, true);
        G1(viewInflate);
        return viewInflate;
    }

    @Override // com.byd.cleardata.ui.e.h, androidx.fragment.app.Fragment
    public void r0() {
        this.k0.setOnKeyListener(null);
        super.r0();
    }
}
