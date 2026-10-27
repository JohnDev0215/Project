package com.byd.vrsettings.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;
import com.byd.vrsettings.R;
import com.byd.vrsettings.adapter.CustomViewpagerAdapter;
import com.byd.vrsettings.utils.RLog;

/* JADX INFO: loaded from: classes.dex */
public class CustomSkillsPopupView extends ConstraintLayout {
    private ConstraintLayout cl_custom_voice_skills_popup_view;
    private Context context;
    private ViewPager custom_viewpager;
    private ImageView iv_left_btn;
    private ImageView iv_right_btn;
    private ImageView iv_skills;
    private int pageCount;
    private TextView tv_skills;

    public CustomSkillsPopupView(Context context) {
        super(context);
        initView(context);
    }

    public CustomSkillsPopupView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    public CustomSkillsPopupView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        inflate(context, R.layout.custom_voice_skills_popup_view, this);
        this.cl_custom_voice_skills_popup_view = (ConstraintLayout) findViewById(R.id.cl_custom_voice_skills_popup_view);
        this.iv_skills = (ImageView) findViewById(R.id.iv_skills);
        this.tv_skills = (TextView) findViewById(R.id.tv_skills);
        this.iv_left_btn = (ImageView) findViewById(R.id.iv_left_btn);
        this.iv_right_btn = (ImageView) findViewById(R.id.iv_right_btn);
        this.custom_viewpager = (ViewPager) findViewById(R.id.custom_viewpager);
        this.iv_left_btn.setEnabled(false);
        this.custom_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.byd.vrsettings.view.CustomSkillsPopupView.1
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                RLog.i("NEU_CustomSkillsPopupView", "onPageSelected(1) " + i + "pageCount =" + CustomSkillsPopupView.this.pageCount);
                if (i == 0) {
                    CustomSkillsPopupView.this.iv_left_btn.setEnabled(false);
                    CustomSkillsPopupView.this.iv_right_btn.setEnabled(true);
                } else if (i == CustomSkillsPopupView.this.pageCount - 1) {
                    CustomSkillsPopupView.this.iv_left_btn.setEnabled(true);
                    CustomSkillsPopupView.this.iv_right_btn.setEnabled(false);
                } else {
                    CustomSkillsPopupView.this.iv_left_btn.setEnabled(true);
                    CustomSkillsPopupView.this.iv_right_btn.setEnabled(true);
                }
            }
        });
        this.iv_right_btn.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.view.CustomSkillsPopupView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CustomSkillsPopupView.this.custom_viewpager.setCurrentItem(CustomSkillsPopupView.this.custom_viewpager.getCurrentItem() + 1);
            }
        });
        this.iv_left_btn.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.view.CustomSkillsPopupView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CustomSkillsPopupView.this.custom_viewpager.setCurrentItem(CustomSkillsPopupView.this.custom_viewpager.getCurrentItem() - 1);
            }
        });
    }

    public void setSmallIcon(int i) {
        this.iv_skills.setImageResource(i);
    }

    public void setTitle(int i) {
        this.tv_skills.setText(i);
    }

    public void setTitle(String str) {
        this.tv_skills.setText(str);
    }

    public void setCustomAdapter(CustomViewpagerAdapter customViewpagerAdapter) {
        this.custom_viewpager.setAdapter(customViewpagerAdapter);
        this.iv_left_btn.setEnabled(false);
        this.pageCount = customViewpagerAdapter.getCount();
        if (customViewpagerAdapter.getCount() == 1) {
            this.iv_left_btn.setVisibility(4);
            this.iv_right_btn.setVisibility(4);
            this.iv_right_btn.setEnabled(false);
        } else {
            this.iv_left_btn.setVisibility(0);
            this.iv_right_btn.setVisibility(0);
            this.iv_right_btn.setEnabled(true);
        }
    }

    public void setNight() {
        this.cl_custom_voice_skills_popup_view.setBackground(getResources().getDrawable(R.drawable.bg_skills_popup_night, null));
        this.tv_skills.setTextColor(getResources().getColor(R.color.skills_title_8155, null));
        this.iv_left_btn.setBackground(getResources().getDrawable(R.drawable.iv_left_btn_night, null));
        this.iv_right_btn.setBackground(getResources().getDrawable(R.drawable.iv_right_btn_night, null));
    }

    public void setLight() {
        this.cl_custom_voice_skills_popup_view.setBackground(getResources().getDrawable(R.drawable.bg_skills_popup, null));
        this.tv_skills.setTextColor(getResources().getColor(R.color.skills_title_8155, null));
        this.iv_left_btn.setBackground(getResources().getDrawable(R.drawable.iv_left_btn, null));
        this.iv_right_btn.setBackground(getResources().getDrawable(R.drawable.iv_right_btn, null));
    }
}
