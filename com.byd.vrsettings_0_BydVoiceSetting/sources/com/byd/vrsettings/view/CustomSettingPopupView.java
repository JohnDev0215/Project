package com.byd.vrsettings.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.byd.vrsettings.R;
import com.byd.vrsettings.utils.ModeUtils;
import com.byd.vrsettings.utils.RLog;
import com.byd.vrsettings.utils.SharedPreferencesUtils;
import com.chad.library.BuildConfig;
import com.google.android.flexbox.FlexboxLayout;

/* JADX INFO: loaded from: classes.dex */
public class CustomSettingPopupView extends ConstraintLayout {
    private final boolean DEFAULT_SWITCH;
    private ConstraintLayout cl_popup;
    int command1Id;
    int command2Id;
    int command3Id;
    int command4Id;
    private FlexboxLayout fbl_popup;
    private boolean isUpDown;
    private ImageView iv_switch;
    private ImageView iv_up_down;
    int labelId;
    int labelhintId;
    private Context mContext;
    private String strCommand1;
    private String strCommand2;
    private String strCommand3;
    private String strCommand4;
    private String strLabel;
    private String strLabelHint;
    private TextView tv_btn_command1;
    private TextView tv_btn_command2;
    private TextView tv_btn_command3;
    private TextView tv_btn_command4;
    private TextView tv_title;
    private TextView tv_title_tip;
    private View v_bottomLine;

    public CustomSettingPopupView(Context context) {
        super(context);
        this.DEFAULT_SWITCH = false;
        this.strLabel = BuildConfig.FLAVOR;
        this.strLabelHint = BuildConfig.FLAVOR;
        this.strCommand1 = BuildConfig.FLAVOR;
        this.strCommand2 = BuildConfig.FLAVOR;
        this.strCommand3 = BuildConfig.FLAVOR;
        this.strCommand4 = BuildConfig.FLAVOR;
        this.mContext = context;
    }

    public CustomSettingPopupView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.DEFAULT_SWITCH = false;
        this.strLabel = BuildConfig.FLAVOR;
        this.strLabelHint = BuildConfig.FLAVOR;
        this.strCommand1 = BuildConfig.FLAVOR;
        this.strCommand2 = BuildConfig.FLAVOR;
        this.strCommand3 = BuildConfig.FLAVOR;
        this.strCommand4 = BuildConfig.FLAVOR;
        this.mContext = context;
        initCustomAttrs(context, attributeSet);
        initView(context);
    }

    public CustomSettingPopupView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.DEFAULT_SWITCH = false;
        this.strLabel = BuildConfig.FLAVOR;
        this.strLabelHint = BuildConfig.FLAVOR;
        this.strCommand1 = BuildConfig.FLAVOR;
        this.strCommand2 = BuildConfig.FLAVOR;
        this.strCommand3 = BuildConfig.FLAVOR;
        this.strCommand4 = BuildConfig.FLAVOR;
        this.mContext = context;
        initCustomAttrs(context, attributeSet);
        initView(context);
    }

    private void initCustomAttrs(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CustomSettingPopupView);
        this.labelId = typedArrayObtainStyledAttributes.getResourceId(4, R.string.text_empty);
        this.labelhintId = typedArrayObtainStyledAttributes.getResourceId(5, R.string.text_empty);
        this.command1Id = typedArrayObtainStyledAttributes.getResourceId(0, R.string.text_empty);
        this.command2Id = typedArrayObtainStyledAttributes.getResourceId(1, R.string.text_empty);
        this.command3Id = typedArrayObtainStyledAttributes.getResourceId(2, R.string.text_empty);
        this.command4Id = typedArrayObtainStyledAttributes.getResourceId(3, R.string.text_empty);
        this.strLabel = context.getResources().getString(this.labelId);
        this.strLabelHint = context.getResources().getString(this.labelhintId);
        this.strCommand1 = context.getResources().getString(this.command1Id);
        this.strCommand2 = context.getResources().getString(this.command2Id);
        this.strCommand3 = context.getResources().getString(this.command3Id);
        this.strCommand4 = context.getResources().getString(this.command4Id);
        typedArrayObtainStyledAttributes.recycle();
    }

    private void initView(Context context) {
        inflate(context, R.layout.custom_voice_setting_popup_view, this);
        this.cl_popup = (ConstraintLayout) findViewById(R.id.cl_popup);
        this.iv_switch = (ImageView) findViewById(R.id.iv_switch);
        this.tv_title = (TextView) findViewById(R.id.tv_title);
        this.tv_title_tip = (TextView) findViewById(R.id.tv_title_tip);
        TextView textView = (TextView) findViewById(R.id.tv_title);
        TextView textView2 = (TextView) findViewById(R.id.tv_title_tip);
        this.iv_up_down = (ImageView) findViewById(R.id.iv_up_down);
        this.fbl_popup = (FlexboxLayout) findViewById(R.id.fbl_popup);
        this.tv_btn_command1 = (TextView) findViewById(R.id.tv_btn_command1);
        this.tv_btn_command2 = (TextView) findViewById(R.id.tv_btn_command2);
        this.tv_btn_command3 = (TextView) findViewById(R.id.tv_btn_command3);
        this.tv_btn_command4 = (TextView) findViewById(R.id.tv_btn_command4);
        this.v_bottomLine = findViewById(R.id.v_bottomLine);
        textView.setText(this.strLabel);
        textView2.setText(this.strLabelHint);
        this.tv_btn_command1.setText(this.strCommand1);
        this.tv_btn_command2.setText(this.strCommand2);
        this.tv_btn_command3.setText(this.strCommand3);
        this.tv_btn_command4.setText(this.strCommand4);
        if (this.strCommand2.equals(BuildConfig.FLAVOR)) {
            this.tv_btn_command2.setVisibility(8);
        }
        if (this.strCommand3.equals(BuildConfig.FLAVOR)) {
            this.tv_btn_command3.setVisibility(8);
        }
        if (this.strCommand4.equals(BuildConfig.FLAVOR)) {
            this.tv_btn_command4.setVisibility(8);
        }
    }

    public void setSwitchClickListener(final String str, final String str2) {
        this.iv_switch.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.view.CustomSettingPopupView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String string = Settings.System.getString(CustomSettingPopupView.this.getContext().getContentResolver(), str);
                RLog.i("NEU_Wakeup_Free", "acSwitch keyWakeupFree=" + str + ",acSwitch=" + string);
                if (TextUtils.isEmpty(string)) {
                    CustomSettingPopupView.this.setSwitchStatus(true);
                    Settings.System.putString(CustomSettingPopupView.this.mContext.getContentResolver(), str, "true");
                    CustomSettingPopupView.this.setUpDownStatus(true, str2);
                    return;
                }
                CustomSettingPopupView.this.setSwitchStatus(!Boolean.parseBoolean(string));
                if (!Boolean.parseBoolean(string)) {
                    Settings.System.putString(CustomSettingPopupView.this.mContext.getContentResolver(), str, "true");
                    CustomSettingPopupView.this.setUpDownStatus(true, str2);
                } else {
                    Settings.System.putString(CustomSettingPopupView.this.mContext.getContentResolver(), str, "false");
                    CustomSettingPopupView.this.setUpDownStatus(false, str2);
                }
                RLog.e("NEU_Wakeup_Free", Settings.System.getString(CustomSettingPopupView.this.mContext.getContentResolver(), str));
            }
        });
    }

    public void setUpDownClickListener(final String str) {
        this.iv_up_down.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.view.CustomSettingPopupView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CustomSettingPopupView.this.setUpDownStatus(!r3.isUpDown, str);
            }
        });
    }

    public void setSwitchStatus(boolean z) {
        if (z) {
            this.iv_switch.setBackground(getResources().getDrawable(R.mipmap.switch_open, null));
        } else if (ModeUtils.isNightMode(getContext())) {
            this.iv_switch.setBackground(getResources().getDrawable(R.mipmap.switch_off_light_night, null));
        } else {
            this.iv_switch.setBackground(getResources().getDrawable(R.mipmap.switch_off_light, null));
        }
    }

    public void setUpDownStatus(boolean z, String str) {
        if (z) {
            this.isUpDown = true;
            if (ModeUtils.isNightMode(getContext())) {
                this.iv_up_down.setImageResource(R.mipmap.btn_up_n_night);
                this.cl_popup.setBackgroundColor(getResources().getColor(R.color.transparent));
            } else {
                this.iv_up_down.setImageResource(R.mipmap.btn_up_n);
                this.cl_popup.setBackground(getResources().getDrawable(R.mipmap.popup_background_open_light, null));
            }
            this.fbl_popup.setVisibility(0);
            this.tv_btn_command1.setVisibility(0);
            if (!this.tv_btn_command2.getText().equals(BuildConfig.FLAVOR)) {
                this.tv_btn_command2.setVisibility(0);
            }
            if (!this.tv_btn_command3.getText().equals(BuildConfig.FLAVOR)) {
                this.tv_btn_command3.setVisibility(0);
            }
            if (!this.tv_btn_command4.getText().equals(BuildConfig.FLAVOR)) {
                this.tv_btn_command4.setVisibility(0);
            }
        } else {
            this.isUpDown = false;
            this.fbl_popup.setVisibility(8);
            if (ModeUtils.isNightMode(getContext())) {
                this.iv_up_down.setImageResource(R.mipmap.btn_down_n_night);
            } else {
                this.iv_up_down.setImageResource(R.mipmap.btn_down_n);
            }
            this.cl_popup.setBackground(null);
            this.tv_btn_command1.setVisibility(8);
            this.tv_btn_command2.setVisibility(8);
            this.tv_btn_command3.setVisibility(8);
            this.tv_btn_command4.setVisibility(8);
        }
        SharedPreferencesUtils.setParam(this.mContext, str, Boolean.valueOf(this.isUpDown));
    }

    public void setTextCommand1(int i) {
        this.tv_btn_command1.setText(i);
    }

    public void setCharSequenceTextCommand1(CharSequence charSequence) {
        this.tv_btn_command1.setText(charSequence);
    }

    public void setTextCommand2(int i) {
        this.tv_btn_command2.setText(i);
    }

    public void setCharSequenceTextCommand2(CharSequence charSequence) {
        this.tv_btn_command2.setText(charSequence);
    }

    public void setTextCommand3(int i) {
        this.tv_btn_command3.setText(i);
    }

    public void setCharSequenceTextCommand3(CharSequence charSequence) {
        this.tv_btn_command3.setText(charSequence);
    }

    public void setTextCommand4(int i) {
        this.tv_btn_command4.setText(i);
    }

    public void setCharSequenceTextCommand4(CharSequence charSequence) {
        this.tv_btn_command4.setText(charSequence);
    }

    public void setTextCommandSize(float f) {
        this.tv_btn_command1.setTextSize(1, f);
        this.tv_btn_command2.setTextSize(1, f);
        this.tv_btn_command3.setTextSize(1, f);
        this.tv_btn_command4.setTextSize(1, f);
    }

    public void setTextCommand4Location(int i) {
        if (i == 0) {
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(this.cl_popup);
            constraintSet.connect(R.id.tv_btn_command4, 6, R.id.tv_btn_command3, 7);
            constraintSet.connect(R.id.tv_btn_command4, 3, R.id.tv_btn_command3, 3);
            constraintSet.setMargin(R.id.tv_btn_command4, 6, 60);
            constraintSet.setMargin(R.id.tv_btn_command4, 3, 0);
            constraintSet.applyTo(this.cl_popup);
            return;
        }
        if (i == 1) {
            ConstraintSet constraintSet2 = new ConstraintSet();
            constraintSet2.clone(this.cl_popup);
            constraintSet2.connect(R.id.tv_btn_command4, 6, R.id.tv_btn_command1, 6);
            constraintSet2.connect(R.id.tv_btn_command4, 3, R.id.tv_btn_command1, 4);
            constraintSet2.setMargin(R.id.tv_btn_command4, 3, 30);
            constraintSet2.setMargin(R.id.tv_btn_command4, 6, 0);
            constraintSet2.applyTo(this.cl_popup);
        }
    }

    public void setNight(String str) {
        this.tv_title.setTextColor(getResources().getColor(R.color.voice_setting_text_night_8155, null));
        this.tv_title_tip.setTextColor(getResources().getColor(R.color.voice_setting_tip_night_8155, null));
        this.tv_btn_command1.setTextColor(getResources().getColor(R.color.voice_setting_title85_night_8155, null));
        this.tv_btn_command2.setTextColor(getResources().getColor(R.color.voice_setting_title85_night_8155, null));
        this.tv_btn_command3.setTextColor(getResources().getColor(R.color.voice_setting_title85_night_8155, null));
        this.tv_btn_command4.setTextColor(getResources().getColor(R.color.voice_setting_title85_night_8155, null));
        this.tv_btn_command1.setBackground(getResources().getDrawable(R.mipmap.command_background_night, null));
        this.tv_btn_command2.setBackground(getResources().getDrawable(R.mipmap.command_background_night, null));
        this.tv_btn_command3.setBackground(getResources().getDrawable(R.mipmap.command_background_night, null));
        this.tv_btn_command4.setBackground(getResources().getDrawable(R.mipmap.command_background_night, null));
        String string = Settings.System.getString(getContext().getContentResolver(), str);
        if (TextUtils.isEmpty(string) || !Boolean.parseBoolean(string)) {
            this.iv_switch.setBackground(getResources().getDrawable(R.mipmap.switch_off_light_night, null));
        }
        if (this.isUpDown) {
            this.iv_up_down.setImageResource(R.mipmap.btn_up_n_night);
            this.cl_popup.setBackgroundColor(getResources().getColor(R.color.transparent));
        } else {
            this.iv_up_down.setImageResource(R.mipmap.btn_down_n_night);
            this.cl_popup.setBackground(null);
        }
        this.v_bottomLine.setBackgroundColor(getResources().getColor(R.color.popup_bottomLine_night_8155, null));
    }

    public void setLight(String str) {
        this.tv_title.setTextColor(getResources().getColor(R.color.voice_setting_title_8155, null));
        this.tv_title_tip.setTextColor(getResources().getColor(R.color.voice_setting_tip_8155, null));
        this.tv_btn_command1.setTextColor(getResources().getColor(R.color.tv_btn_command_text_8155, null));
        this.tv_btn_command2.setTextColor(getResources().getColor(R.color.tv_btn_command_text_8155, null));
        this.tv_btn_command3.setTextColor(getResources().getColor(R.color.tv_btn_command_text_8155, null));
        this.tv_btn_command4.setTextColor(getResources().getColor(R.color.tv_btn_command_text_8155, null));
        this.tv_btn_command1.setBackground(getResources().getDrawable(R.mipmap.command_background, null));
        this.tv_btn_command2.setBackground(getResources().getDrawable(R.mipmap.command_background, null));
        this.tv_btn_command3.setBackground(getResources().getDrawable(R.mipmap.command_background, null));
        this.tv_btn_command4.setBackground(getResources().getDrawable(R.mipmap.command_background, null));
        String string = Settings.System.getString(getContext().getContentResolver(), str);
        if (TextUtils.isEmpty(string) || !Boolean.parseBoolean(string)) {
            this.iv_switch.setBackground(getResources().getDrawable(R.mipmap.switch_off_light, null));
        }
        if (this.isUpDown) {
            this.iv_up_down.setImageResource(R.mipmap.btn_up_n);
            this.cl_popup.setBackground(getResources().getDrawable(R.mipmap.popup_background_open_light, null));
        } else {
            this.iv_up_down.setImageResource(R.mipmap.btn_down_n);
            this.cl_popup.setBackground(null);
        }
        this.v_bottomLine.setBackgroundColor(getResources().getColor(R.color.popup_bottomLine_8155, null));
    }

    public void reloadMessage() {
        this.strLabel = getResources().getString(this.labelId);
        this.strLabelHint = getResources().getString(this.labelhintId);
        this.strCommand1 = getResources().getString(this.command1Id);
        this.strCommand2 = getResources().getString(this.command2Id);
        this.strCommand3 = getResources().getString(this.command3Id);
        this.strCommand4 = getResources().getString(this.command4Id);
        this.tv_title.setText(this.strLabel);
        this.tv_title_tip.setText(this.strLabelHint);
        this.tv_btn_command1.setText(this.strCommand1);
        this.tv_btn_command2.setText(this.strCommand2);
        this.tv_btn_command3.setText(this.strCommand3);
        this.tv_btn_command4.setText(this.strCommand4);
        if (this.tv_btn_command2.equals(BuildConfig.FLAVOR)) {
            this.tv_btn_command2.setVisibility(8);
        }
        if (this.strCommand3.equals(BuildConfig.FLAVOR)) {
            this.tv_btn_command3.setVisibility(8);
        }
        if (this.strCommand4.equals(BuildConfig.FLAVOR)) {
            this.tv_btn_command4.setVisibility(8);
        }
    }
}
