package com.byd.engineermode.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.g;
import butterknife.R;

/* JADX INFO: compiled from: LoadingDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends androidx.fragment.app.b {

    /* JADX INFO: compiled from: LoadingDialog.java */
    class a implements DialogInterface.OnKeyListener {
        a() {
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            if (i != 4 || keyEvent.getAction() != 1) {
                return false;
            }
            com.byd.engineermode.utils.a.a("onKeyBack");
            return false;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.dialog_loading, viewGroup, true);
        t1(viewInflate);
        return viewInflate;
    }

    @Override // androidx.fragment.app.b
    public Dialog q1(Bundle bundle) {
        Dialog dialog = new Dialog(i(), R.style.MyDialog);
        dialog.requestWindowFeature(1);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(new a());
        return dialog;
    }

    protected void t1(View view) {
    }

    public boolean u1() {
        Dialog dialogP1 = p1();
        return dialogP1 != null && dialogP1.isShowing();
    }

    @Override // androidx.fragment.app.Fragment
    public void v0() {
        super.v0();
        p1().getWindow().setLayout(C().getDimensionPixelSize(R.dimen.dp_264), C().getDimensionPixelSize(R.dimen.dp_152));
    }

    public void v1(g gVar) {
        super.s1(gVar, c.class.getSimpleName());
    }
}
