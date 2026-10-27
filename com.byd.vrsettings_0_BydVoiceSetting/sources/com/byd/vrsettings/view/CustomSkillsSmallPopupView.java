package com.byd.vrsettings.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.byd.vrsettings.R;

/* JADX INFO: loaded from: classes.dex */
public class CustomSkillsSmallPopupView extends ConstraintLayout {
    private ConstraintLayout cl_item_skills_popup_view;
    private Context context;
    private TextView tv_item_skills_popup_view;

    public CustomSkillsSmallPopupView(Context context) {
        super(context);
        initView(context);
    }

    public CustomSkillsSmallPopupView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    public CustomSkillsSmallPopupView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        inflate(context, R.layout.custom_skills_popup_view, this);
        this.cl_item_skills_popup_view = (ConstraintLayout) findViewById(R.id.cl_item_skills_popup_view);
        this.tv_item_skills_popup_view = (TextView) findViewById(R.id.tv_item_skills_popup_view);
    }

    public void setTitle(int i) {
        this.tv_item_skills_popup_view.setText(i);
    }

    public void setTitle(String str) {
        this.tv_item_skills_popup_view.setText(str);
    }

    public void setNight() {
        this.tv_item_skills_popup_view.setTextColor(getResources().getColor(R.color.voice_setting_tip_night, null));
    }

    public void setLight() {
        this.tv_item_skills_popup_view.setTextColor(getResources().getColor(R.color.voice_setting_tip_8155, null));
    }
}
