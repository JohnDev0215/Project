package com.byd.vrsettings.utils;

import android.content.Context;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.byd.vrsettings.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class CustomViewpagerView extends ConstraintLayout {
    private Context context;
    private TextView tip1;
    private TextView tip2;
    private TextView tip3;
    private TextView tip4;

    public CustomViewpagerView(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        inflate(context, R.layout.custom_viewpager_view, this);
        this.tip1 = (TextView) findViewById(R.id.tip1);
        this.tip2 = (TextView) findViewById(R.id.tip2);
        this.tip3 = (TextView) findViewById(R.id.tip3);
        this.tip4 = (TextView) findViewById(R.id.tip4);
    }

    public void setTitle(ArrayList<String> arrayList) {
        RLog.i("NEU_CustomViewpagerView", "setTitle: " + arrayList);
        if (arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                if (i == 0) {
                    this.tip1.setText(arrayList.get(0));
                } else if (i == 1) {
                    this.tip2.setText(arrayList.get(1));
                } else if (i == 2) {
                    this.tip3.setText(arrayList.get(2));
                } else if (i == 3) {
                    this.tip4.setText(arrayList.get(3));
                }
            }
        }
    }

    public void setNight() {
        this.tip1.setTextColor(getResources().getColor(R.color.voice_setting_text_night_8155, null));
        this.tip2.setTextColor(getResources().getColor(R.color.voice_setting_text_night_8155, null));
        this.tip3.setTextColor(getResources().getColor(R.color.voice_setting_text_night_8155, null));
        this.tip4.setTextColor(getResources().getColor(R.color.voice_setting_text_night_8155, null));
    }

    public void setLight() {
        this.tip1.setTextColor(getResources().getColor(R.color.voice_setting_tip, null));
        this.tip2.setTextColor(getResources().getColor(R.color.voice_setting_tip, null));
        this.tip3.setTextColor(getResources().getColor(R.color.voice_setting_tip, null));
        this.tip4.setTextColor(getResources().getColor(R.color.voice_setting_tip, null));
    }
}
