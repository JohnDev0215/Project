package com.byd.filemanager.ui.c;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.byd.filemanager.R;
import java.lang.reflect.Field;

/* JADX INFO: compiled from: LoadingDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends g {
    private Dialog k0;

    /* JADX INFO: compiled from: LoadingDialog.java */
    class a implements DialogInterface.OnKeyListener {
        a() {
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            if (i != 4 || keyEvent.getAction() != 1) {
                return false;
            }
            com.byd.filemanager.k.b.b("onKeyBack");
            org.greenrobot.eventbus.c.c().k(new com.byd.music.f.f(20));
            return false;
        }
    }

    @Override // com.byd.filemanager.ui.c.g
    public Dialog E1(Bundle bundle) {
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
        C1().getWindow().setLayout(K().getDimensionPixelSize(R.dimen.dp_264), K().getDimensionPixelSize(R.dimen.dp_152));
    }

    @Override // com.byd.filemanager.ui.c.g
    public void G1(androidx.fragment.app.g gVar, String str) {
        try {
            Field declaredField = g.class.getDeclaredField("i0");
            declaredField.setAccessible(true);
            declaredField.set(this, Boolean.FALSE);
        } catch (IllegalAccessException | NoSuchFieldException e2) {
            e2.printStackTrace();
        }
        try {
            Field declaredField2 = g.class.getDeclaredField("j0");
            declaredField2.setAccessible(true);
            declaredField2.set(this, Boolean.TRUE);
        } catch (IllegalAccessException | NoSuchFieldException e3) {
            e3.printStackTrace();
        }
        androidx.fragment.app.j jVarA = gVar.a();
        jVarA.c(this, str);
        jVarA.g();
    }

    protected void H1(View view) {
    }

    public boolean I1() {
        Dialog dialogC1 = C1();
        return dialogC1 != null && dialogC1.isShowing();
    }

    public void J1(androidx.fragment.app.g gVar) {
        G1(gVar, h.class.getSimpleName());
    }

    @Override // androidx.fragment.app.Fragment
    public View o0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_loading, viewGroup, true);
        H1(viewInflate);
        return viewInflate;
    }

    @Override // com.byd.filemanager.ui.c.g, androidx.fragment.app.Fragment
    public void r0() {
        this.k0.setOnKeyListener(null);
        super.r0();
    }
}
