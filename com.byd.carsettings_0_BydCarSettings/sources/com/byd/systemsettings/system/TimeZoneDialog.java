package com.byd.systemsettings.system;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.b.a.c.a;
import com.byd.base.view.dialog.BaseDialogFragment;
import com.byd.carsettings.R;
import com.byd.carsettings.system.timezone.TimeZonePicker;
import java.util.Arrays;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class TimeZoneDialog extends BaseDialogFragment implements View.OnClickListener {
    public static final String BUNDLE_DEF_INDEX = "bundle_def_index";
    public static final String BUNDLE_GMT_ARR = "bundle_gmt_arr";
    public static final String BUNDLE_ZONE_ARR = "bundle_zone_arr";
    public Button btnCancel;
    public Button btnOk;
    public OnOKClickedListener clickedListener;
    public int defIndex = -1;
    public String[] gmtArr;
    public View ivBack;
    public LinearLayout llDialog;
    public ViewGroup llMasks;
    private ViewGroup opertionBottom;
    public TimeZonePicker timeZonePicker;
    public TextView tvTitle;
    public String[] zoneArr;

    public interface OnOKClickedListener {
        void onTimeZoneSelected(int i);
    }

    private void initData(Bundle bundle) {
        this.defIndex = bundle.getInt("bundle_def_index");
        this.zoneArr = bundle.getStringArray(BUNDLE_ZONE_ARR);
        this.gmtArr = bundle.getStringArray(BUNDLE_GMT_ARR);
    }

    public static TimeZoneDialog newTimeZoneDialog(int i, String[] strArr, String[] strArr2) {
        TimeZoneDialog timeZoneDialog = new TimeZoneDialog();
        Bundle bundle = new Bundle();
        bundle.putInt("bundle_def_index", i);
        bundle.putStringArray(BUNDLE_ZONE_ARR, strArr);
        bundle.putStringArray(BUNDLE_GMT_ARR, strArr2);
        timeZoneDialog.setArguments(bundle);
        return timeZoneDialog;
    }

    @Override // com.byd.base.view.dialog.BaseDialogFragment
    public int getLayoutResource() {
        return R.layout.dialog_time_zone_v2;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.btn_ok_dialog_bottom) {
            EventBus.getDefault().postSticky(new a(230011, this.timeZonePicker.getSelection()));
        }
        dismiss();
    }

    @Override // com.byd.base.view.dialog.BaseDialogFragment, com.byd.base.view.dialog.LeakDialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        initData(arguments);
        if (bundle != null) {
            this.defIndex = bundle.getInt("bundle_def_index");
        }
    }

    @Override // com.byd.base.view.dialog.BaseDialogFragment
    public void onLtrRudderR(boolean z) {
        setViewGroupRtl(this.opertionBottom, true);
    }

    @Override // com.byd.base.view.dialog.BaseDialogFragment
    public void onRtlRudderL(boolean z) {
        setViewGroupRtl(this.opertionBottom, false);
    }

    @Override // com.byd.base.view.dialog.LeakDialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("bundle_def_index", this.defIndex);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.opertionBottom = (ViewGroup) view.findViewById(R.id.ll_opertion_dialog_bottom);
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.ll_dialog_masks);
        this.llMasks = viewGroup;
        viewGroup.setOnClickListener(this);
        this.llDialog = (LinearLayout) view.findViewById(R.id.ll_bg_dialo_sound_focus);
        TextView textView = (TextView) view.findViewById(R.id.tv_title_dialog_common_titles);
        this.tvTitle = textView;
        textView.setText(getString(R.string.title_dialog_select_time_zone));
        View viewFindViewById = view.findViewById(R.id.iv_back_dialog_common_title);
        this.ivBack = viewFindViewById;
        viewFindViewById.setOnClickListener(this);
        this.ivBack.setVisibility(8);
        Button button = (Button) view.findViewById(R.id.btn_ok_dialog_bottom);
        this.btnOk = button;
        button.setOnClickListener(this);
        Button button2 = (Button) view.findViewById(R.id.btn_cancel_dialog_bottom);
        this.btnCancel = button2;
        button2.setOnClickListener(this);
        TimeZonePicker timeZonePicker = (TimeZonePicker) view.findViewById(R.id.np_dialog_time_zone);
        this.timeZonePicker = timeZonePicker;
        int i = this.defIndex;
        if (i != -1) {
            timeZonePicker.setSelection(i);
        }
        String[] strArr = this.zoneArr;
        if (strArr != null) {
            this.timeZonePicker.setLabels(Arrays.asList(strArr));
        }
        String[] strArr2 = this.gmtArr;
        if (strArr2 != null) {
            this.timeZonePicker.setLabelsSecond(Arrays.asList(strArr2));
        }
        this.timeZonePicker.setNumberPickerSize(3);
        this.timeZonePicker.setLabelSelectedColor(getResources().getColor(R.color.text_color_blue_night));
        this.timeZonePicker.setLabelUnSelectedColor(getResources().getColor(R.color.text_color_main_gray));
        this.timeZonePicker.setOnItemSelectedListener(new TimeZonePicker.c() { // from class: com.byd.systemsettings.system.TimeZoneDialog.1
            @Override // com.byd.carsettings.system.timezone.TimeZonePicker.c
            public void onItemSelected(int i2, String str) {
                TimeZoneDialog.this.defIndex = i2;
            }
        });
        this.mWidth = (int) getResources().getDimension(R.dimen.dp_560);
        this.mHeight = (int) getResources().getDimension(R.dimen.dp_400);
        setAnim(this.llMasks, this.llDialog);
        if (this.windowTitle == null) {
            setWindowTitle(getContext(), getClass().getName());
        }
    }

    public void setOnOKClickedListener(OnOKClickedListener onOKClickedListener) {
        this.clickedListener = onOKClickedListener;
    }
}
