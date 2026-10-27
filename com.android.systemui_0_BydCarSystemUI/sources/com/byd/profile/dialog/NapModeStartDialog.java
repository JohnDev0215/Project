package com.byd.profile.dialog;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import com.byd.profile.Constants;
import com.byd.profile.R;
import com.byd.profile.StatusBarChangeEvent;
import com.byd.profile.mode.RestMode;
import com.byd.profile.utils.SharedPreferencesUtil;
import com.byd.profile.utils.VoiceUtil;
import com.byd.profile.view.NumberPickerView;
import com.bydux.api.common.tools.log.BydLogHelper;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class NapModeStartDialog extends BaseFullscreenDialog implements View.OnClickListener {
    private int duration;
    private NumberPickerView mNumberPicker;
    private View mRoot;
    private int[] values;

    @Override // com.byd.profile.dialog.FloatingWindow
    protected void setWidthAndHeight(int[] size) {
    }

    public NapModeStartDialog(Context context) {
        super(context);
    }

    @Override // com.byd.profile.dialog.BaseFullscreenDialog
    protected int initLayoutRes() {
        return R.layout.dialog_nap_mode_start;
    }

    @Override // com.byd.profile.dialog.BaseFullscreenDialog
    protected int initTitle() {
        return R.string.dialog_title_rest_set;
    }

    @Override // com.byd.profile.dialog.BaseFullscreenDialog
    protected void initContent(View view) {
        view.findViewById(R.id.btn_reset).setVisibility(4);
        this.mNumberPicker = (NumberPickerView) view.findViewById(R.id.duration_picker);
        int[] intArray = this.mContext.getResources().getIntArray(R.array.rest_duration);
        this.values = intArray;
        this.mNumberPicker.setDisplayedValues(intArray);
        this.mNumberPicker.setMinAndMaxShowIndex(0, this.values.length - 1, true);
        this.mNumberPicker.setDividerColor(android.R.color.transparent);
        this.mNumberPicker.setOnValueChangedListener(new NumberPickerView.OnValueChangeListener() { // from class: com.byd.profile.dialog.-$$Lambda$NapModeStartDialog$yLR8AfKeVnJ5dWdWGgVxBcWIDtA
            @Override // com.byd.profile.view.NumberPickerView.OnValueChangeListener
            public final void onValueChange(NumberPickerView numberPickerView, int i, int i2) {
                this.f$0.lambda$initContent$0$NapModeStartDialog(numberPickerView, i, i2);
            }
        });
        EventBus.getDefault().register(this);
        Button button = (Button) view.findViewById(R.id.btn_ok);
        button.setText(R.string.start);
        button.setOnClickListener(this);
        view.findViewById(R.id.btn_cancel).setOnClickListener(this);
        VoiceUtil.remainder(this.mContext, RestMode.VoiceResIndex.CONFIRM_PASSENGER.index);
    }

    public /* synthetic */ void lambda$initContent$0$NapModeStartDialog(NumberPickerView numberPickerView, int i, int i2) {
        this.duration = this.values[i2];
        BydLogHelper.logD("duration = " + this.duration);
    }

    @Override // com.byd.profile.dialog.BaseFullscreenDialog
    protected void onViewAttach() {
        int i = 0;
        int i2 = 0;
        while (true) {
            int[] iArr = this.values;
            if (i2 >= iArr.length) {
                break;
            }
            if (iArr[i2] == this.duration) {
                i = i2;
                break;
            }
            i2++;
        }
        this.mNumberPicker.setPickedIndexRelativeToRaw(i);
    }

    @Override // com.byd.profile.dialog.BaseFullscreenDialog
    protected void onViewDetach() {
        EventBus.getDefault().unregister(this);
    }

    @Override // com.byd.profile.dialog.BaseFullscreenDialog
    protected void initData() {
        this.duration = SharedPreferencesUtil.getInt(this.mContext, Constants.SP_KEY_DURATION_REST, 30);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStatusBarChange(StatusBarChangeEvent event) {
        BydLogHelper.logD("onStatusBarChange");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (v.getId() == R.id.btn_ok) {
            SharedPreferencesUtil.putInt(this.mContext, Constants.SP_KEY_DURATION_REST, this.duration);
            if (this.mOnDialogClickListener != null) {
                this.mOnDialogClickListener.onConfirmClick();
            }
        }
        dismiss();
    }
}
