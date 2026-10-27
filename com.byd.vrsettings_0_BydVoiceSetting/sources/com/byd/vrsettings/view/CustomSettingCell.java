package com.byd.vrsettings.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.byd.vrsettings.R;

/* JADX INFO: loaded from: classes.dex */
public class CustomSettingCell extends ConstraintLayout {
    private View bottomLine;
    private Context context;

    public CustomSettingCell(Context context) {
        super(context);
        initView(context);
    }

    public CustomSettingCell(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    public CustomSettingCell(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        inflate(context, R.layout.custom_setting_cell, this);
        this.bottomLine = findViewById(R.id.cell_bottomLine);
    }

    public void setNight() {
        this.bottomLine.setBackgroundColor(getResources().getColor(R.color.setting_cell_bottomLine_night, null));
    }

    public void setLight() {
        this.bottomLine.setBackgroundColor(getResources().getColor(R.color.setting_cell_bottomLine, null));
    }
}
