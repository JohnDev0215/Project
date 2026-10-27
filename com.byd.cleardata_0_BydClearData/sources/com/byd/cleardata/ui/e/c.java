package com.byd.cleardata.ui.e;

import android.view.View;
import android.widget.TextView;
import com.byd.cleardata.R;

/* JADX INFO: loaded from: classes.dex */
public class c extends a {
    @Override // com.byd.cleardata.ui.e.a
    protected int H1() {
        return 0;
    }

    @Override // com.byd.cleardata.ui.e.a
    protected void I1(View view) {
        ((TextView) view.findViewById(R.id.tv_message)).setText(R.string.msg_clear_app_data);
    }

    @Override // com.byd.cleardata.ui.e.a
    protected String L1() {
        return R(R.string.title_clear_app_data);
    }

    @Override // com.byd.cleardata.ui.e.a
    protected int Q1() {
        return R.layout.dialog_confirm;
    }

    @Override // com.byd.cleardata.ui.e.a
    protected void U1(int[] iArr) {
        iArr[0] = R.dimen.dp_560;
        iArr[1] = R.dimen.dp_312;
    }
}
