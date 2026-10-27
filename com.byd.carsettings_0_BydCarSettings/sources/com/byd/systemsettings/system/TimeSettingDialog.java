package com.byd.systemsettings.system;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.base.view.dialog.BaseDialogFragment;
import com.byd.carsettings.R;
import com.byd.carsettings.model.dilink.BydDate;
import com.byd.carsettings.ui.widget.BydTimePicker;

/* JADX INFO: loaded from: classes.dex */
public class TimeSettingDialog extends BaseDialogFragment implements BydTimePicker.a, View.OnClickListener {
    private Button btnCancel;
    private Button btnOk;
    private BydTimePicker btp;
    private View ivBack;
    private OnOKClickedListener onOKClickedListener;
    private BydDate timeSelected;
    private TextView tvTitle;

    public interface OnOKClickedListener {
        void onDateSelected(BydDate bydDate);
    }

    @Override // com.byd.base.view.dialog.BaseDialogFragment
    public int getLayoutResource() {
        return R.layout.dialog_time_setting;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        OnOKClickedListener onOKClickedListener;
        BydDate bydDate;
        if (view.getId() == R.id.btn_ok_dialog_bottom && (onOKClickedListener = this.onOKClickedListener) != null && (bydDate = this.timeSelected) != null) {
            onOKClickedListener.onDateSelected(bydDate);
        }
        dismiss();
    }

    @Override // com.byd.carsettings.ui.widget.BydTimePicker.a
    public void onDateSelected(BydDate bydDate) {
        this.timeSelected = bydDate;
    }

    @Override // com.byd.base.view.dialog.LeakDialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("timeSelected", this.timeSelected);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.tvTitle = (TextView) view.findViewById(R.id.tv_title_dialog_common_titles);
        View viewFindViewById = view.findViewById(R.id.iv_back_dialog_common_title);
        this.ivBack = viewFindViewById;
        viewFindViewById.setOnClickListener(this);
        Button button = (Button) view.findViewById(R.id.btn_cancel_dialog_bottom);
        this.btnCancel = button;
        button.setOnClickListener(this);
        Button button2 = (Button) view.findViewById(R.id.btn_ok_dialog_bottom);
        this.btnOk = button2;
        button2.setOnClickListener(this);
        BydTimePicker bydTimePicker = (BydTimePicker) view.findViewById(R.id.tp_dialog_time_setting);
        this.btp = bydTimePicker;
        bydTimePicker.setOnDateSelectedCallback(this);
        this.tvTitle.setText(getString(R.string.title_dialog_time_setting));
    }

    public void setOnOKClickedListener(OnOKClickedListener onOKClickedListener) {
        this.onOKClickedListener = onOKClickedListener;
    }
}
