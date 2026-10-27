package com.byd.cleardata.ui.e;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.byd.cleardata.R;

/* JADX INFO: loaded from: classes.dex */
public class k extends a {
    private int A0;
    private String y0;
    private TextView z0;

    public static k W1(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("key1", i);
        k kVar = new k();
        kVar.q1(bundle);
        return kVar;
    }

    @Override // com.byd.cleardata.ui.e.a
    protected int H1() {
        return 0;
    }

    @Override // com.byd.cleardata.ui.e.a
    protected void I1(View view) {
        TextView textView = (TextView) view.findViewById(R.id.tv_message);
        this.z0 = textView;
        textView.setText(this.y0);
    }

    @Override // com.byd.cleardata.ui.e.a
    protected String L1() {
        return null;
    }

    @Override // com.byd.cleardata.ui.e.a
    protected int Q1() {
        return R.layout.dialog_message;
    }

    @Override // com.byd.cleardata.ui.e.a
    protected void U1(int[] iArr) {
        iArr[0] = R.dimen.dp_560;
        iArr[1] = R.dimen.dp_264;
    }

    @Override // com.byd.cleardata.ui.e.h, androidx.fragment.app.Fragment
    public void k0(Bundle bundle) {
        super.k0(bundle);
        Bundle bundleW = w();
        if (bundleW != null) {
            int i = bundleW.getInt("key1");
            this.A0 = i;
            if (i == 1) {
                this.y0 = R(R.string.files_delete);
            }
        }
    }

    @Override // com.byd.cleardata.ui.e.a, android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.btn_ok && this.A0 == 1) {
            com.byd.cleardata.b.h().y(com.byd.cleardata.d.e.DELETING);
        }
        z1();
    }
}
