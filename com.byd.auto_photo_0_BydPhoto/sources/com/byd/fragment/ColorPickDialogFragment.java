package com.byd.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.byd.auto_photo.R;
import com.byd.view.ColorPickerView;

/* JADX INFO: loaded from: classes2.dex */
public class ColorPickDialogFragment extends BaseDialogFragment {
    private int colorResult = ViewCompat.MEASURED_STATE_MASK;
    private OnPickerListener onPickerListener;
    ColorPickerView pickerView;
    private TextView tvBlue;
    private TextView tvGreen;
    private TextView tvRed;
    private View viewResult;

    public interface OnPickerListener {
        void onColorPick(int i);
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected int getLayout() {
        return R.layout.fragment_color_pick_dialog;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.byd.fragment.BaseDialogSuperFragment
    public void onSureClick() {
        OnPickerListener onPickerListener = this.onPickerListener;
        if (onPickerListener != null) {
            onPickerListener.onColorPick(this.colorResult);
        }
        super.onSureClick();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        this.tvRed = (TextView) view.findViewById(R.id.tv_red);
        this.tvGreen = (TextView) view.findViewById(R.id.tv_green);
        this.tvBlue = (TextView) view.findViewById(R.id.tv_blue);
        View viewFindViewById = view.findViewById(R.id.view_effect);
        this.viewResult = viewFindViewById;
        viewFindViewById.setBackgroundColor(this.colorResult);
        ColorPickerView colorPickerView = (ColorPickerView) view.findViewById(R.id.iv_color_picker_view);
        this.pickerView = colorPickerView;
        colorPickerView.setOnColorPickerListener(new ColorPickerView.OnColorPickerListener() { // from class: com.byd.fragment.ColorPickDialogFragment.1
            @Override // com.byd.view.ColorPickerView.OnColorPickerListener
            public void onColorChange(int i) {
                ColorPickDialogFragment.this.colorResult = i;
                ColorPickDialogFragment.this.viewResult.setBackgroundColor(ColorPickDialogFragment.this.colorResult);
                ColorPickDialogFragment.this.tvRed.setText(String.valueOf(Color.red(i)));
                ColorPickDialogFragment.this.tvGreen.setText(String.valueOf(Color.green(i)));
                ColorPickDialogFragment.this.tvBlue.setText(String.valueOf(Color.blue(i)));
            }
        });
        setBackgroundColor(-14540254);
    }

    public void setOnPickerListener(OnPickerListener onPickerListener) {
        this.onPickerListener = onPickerListener;
    }
}
