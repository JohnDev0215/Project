package com.byd.vrassistant.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import com.byd.vrassistant.ui.R;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.viewInf.usbInstall.IUsbInstallView;

/* JADX INFO: loaded from: classes2.dex */
public class CopyDataProgressBar extends ConstraintLayout implements IUsbInstallView {
    private static final String TAG = "CopyDataProgressBar";
    public Button bt_cancel;
    public Button bt_start;
    public ConstraintLayout constraint_layout;
    public Group group_start;
    private Context mContext;
    public ProgressBar pb_copy;
    public TextView tx_state;
    public TextView tx_state2;
    public TextView tx_title;

    @Override // com.byd.vrassistant.viewInf.common.IViewBase
    public View getView() {
        return this;
    }

    public CopyDataProgressBar(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    private void initView() {
        RLog.i(TAG, "initView");
        LayoutInflater.from(this.mContext).inflate(R.layout.usb_prog_bar, (ViewGroup) this, true);
        this.constraint_layout = (ConstraintLayout) findViewById(R.id.cl_prog);
        this.tx_title = (TextView) findViewById(R.id.tx_title);
        this.tx_state = (TextView) findViewById(R.id.tx_state);
        this.tx_state2 = (TextView) findViewById(R.id.tx_state2);
        this.pb_copy = (ProgressBar) findViewById(R.id.pb_copy);
        this.bt_cancel = (Button) findViewById(R.id.bt_cancel);
        this.bt_start = (Button) findViewById(R.id.bt_start);
        this.group_start = (Group) findViewById(R.id.group_start);
    }
}
