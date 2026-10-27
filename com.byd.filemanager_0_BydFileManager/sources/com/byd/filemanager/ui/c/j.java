package com.byd.filemanager.ui.c;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.byd.filemanager.R;
import com.byd.filemanager.bean.FileState;

/* JADX INFO: compiled from: MessageDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class j extends a {
    private int A0;
    private String y0;
    private TextView z0;

    public static j d2(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("key1", i);
        j jVar = new j();
        jVar.q1(bundle);
        return jVar;
    }

    @Override // com.byd.filemanager.ui.c.a
    protected int N1() {
        return 0;
    }

    @Override // com.byd.filemanager.ui.c.a
    protected void O1(View view) {
        TextView textView = (TextView) view.findViewById(R.id.tv_message);
        this.z0 = textView;
        textView.setText(this.y0);
    }

    @Override // com.byd.filemanager.ui.c.a
    protected void P1(Bundle bundle) {
    }

    @Override // com.byd.filemanager.ui.c.a
    protected String R1() {
        return null;
    }

    @Override // com.byd.filemanager.ui.c.a
    protected int X1() {
        return R.layout.dialog_message;
    }

    @Override // com.byd.filemanager.ui.c.a
    protected void b2(int[] iArr) {
        iArr[0] = R.dimen.dp_560;
        iArr[1] = R.dimen.dp_264;
    }

    @Override // com.byd.filemanager.ui.c.g, androidx.fragment.app.Fragment
    public void k0(Bundle bundle) {
        super.k0(bundle);
        Bundle bundleV = v();
        if (bundleV != null) {
            int i = bundleV.getInt("key1");
            this.A0 = i;
            if (i == 1) {
                this.y0 = Q(R.string.files_delete);
            }
        }
    }

    @Override // com.byd.filemanager.ui.c.a, android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.btn_ok && this.A0 == 1) {
            com.byd.filemanager.f.h().G(FileState.DELETING);
        }
        z1();
    }
}
