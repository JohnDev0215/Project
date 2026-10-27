package com.byd.vrsettings.fragment;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.byd.vrsettings.BaseFragment;
import com.byd.vrsettings.R;
import com.byd.vrsettings.adapter.CustomViewpagerAdapter;
import com.byd.vrsettings.eventbus.PrivacyPolicyEvent;
import com.byd.vrsettings.eventbus.SkinEvent;
import com.byd.vrsettings.utils.Common;
import com.byd.vrsettings.utils.CustomViewpagerView;
import com.byd.vrsettings.utils.ModeUtils;
import com.byd.vrsettings.utils.PropertiesUtil;
import com.byd.vrsettings.view.CustomScrollView;
import com.byd.vrsettings.view.CustomSkillsPopupView;
import com.chad.library.BuildConfig;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class PhoneFragment extends BaseFragment {
    private List<CustomViewpagerView> contactList;
    private CustomSkillsPopupView cspv_contact;
    private CustomSkillsPopupView cspv_dial;
    private ConstraintLayout custom_phone;
    private List<CustomViewpagerView> dialList;
    private List<Integer> listContact;
    private List<Integer> listDial;
    private ConstraintSet set;
    private CustomScrollView sv_phone;
    private View view;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeSkin(SkinEvent skinEvent) {
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.view = layoutInflater.inflate(R.layout.item_skills_phone, viewGroup, false);
        initView();
        return this.view;
    }

    private void initView() {
        EventBus.getDefault().register(this);
        this.set = new ConstraintSet();
        CustomScrollView customScrollView = (CustomScrollView) this.view.findViewById(R.id.sv_phone);
        this.sv_phone = customScrollView;
        customScrollView.setOnScrollListener(new CustomScrollView.OnScrollListener() { // from class: com.byd.vrsettings.fragment.PhoneFragment.1
            @Override // com.byd.vrsettings.view.CustomScrollView.OnScrollListener
            public void onScrollY(int i) {
            }
        });
        this.cspv_dial = (CustomSkillsPopupView) this.view.findViewById(R.id.cspv_dial);
        this.custom_phone = (ConstraintLayout) this.view.findViewById(R.id.custom_phone);
        this.cspv_contact = (CustomSkillsPopupView) this.view.findViewById(R.id.cspv_contact);
        this.dialList = new ArrayList();
        this.contactList = new ArrayList();
        this.listDial = new ArrayList();
        this.listContact = new ArrayList();
        initIdList();
        initSkillsPopup();
    }

    public void setLandScape() {
        this.set.clone(this.custom_phone);
        this.set.connect(R.id.cspv_dial, 7, R.id.cspv_contact, 6, (int) TypedValue.applyDimension(1, 16.0f, getResources().getDisplayMetrics()));
        this.set.connect(R.id.cspv_contact, 3, R.id.cspv_dial, 3);
        this.set.connect(R.id.cspv_contact, 6, R.id.cspv_dial, 7);
        this.set.applyTo(this.custom_phone);
    }

    public void setPortrait() {
        this.set.clone(this.custom_phone);
        this.set.clear(R.id.cspv_dial, 7);
        this.set.connect(R.id.cspv_dial, 7, 0, 7);
        this.set.connect(R.id.cspv_contact, 3, R.id.cspv_dial, 4, (int) TypedValue.applyDimension(1, 21.0f, getResources().getDisplayMetrics()));
        this.set.connect(R.id.cspv_contact, 6, 0, 6);
        this.set.applyTo(this.custom_phone);
    }

    private void initIdList() {
        this.listDial.add(Integer.valueOf(R.string.text_call_jack));
        this.listDial.add(Integer.valueOf(R.string.text_dial_jack_number));
        this.listContact.add(Integer.valueOf(R.string.text_view_jack_contact_card));
        this.listContact.add(Integer.valueOf(R.string.text_view_address_book));
        this.listContact.add(Integer.valueOf(R.string.text_view_call_history));
        this.listContact.add(Integer.valueOf(R.string.text_view_missed_call));
    }

    private void initSkillsPopup() {
        setSmallIcon();
        setViewpagerAdapterByProp();
        setTitle();
    }

    private void setSmallIcon() {
        if (ModeUtils.isNightMode(getContext())) {
            this.cspv_dial.setSmallIcon(R.mipmap.ic_skills_one_night);
            this.cspv_contact.setSmallIcon(R.mipmap.ic_skills_two_night);
        } else {
            this.cspv_dial.setSmallIcon(R.mipmap.ic_skills_one);
            this.cspv_contact.setSmallIcon(R.mipmap.ic_skills_two);
        }
    }

    private void setTitle() {
        this.cspv_dial.setTitle(R.string.text_dial);
        this.cspv_contact.setTitle(R.string.text_find_a_contact);
    }

    private void setViewpagerAdapter() {
        this.cspv_dial.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerView(this.listDial, this.dialList)));
        this.cspv_contact.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerView(this.listContact, this.contactList)));
    }

    private void setTitleByProp() {
        this.cspv_dial.setTitle(PropertiesUtil.getProperty("dial"));
        this.cspv_contact.setTitle(PropertiesUtil.getProperty("contact"));
    }

    private void setViewpagerAdapterByProp() {
        this.cspv_dial.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerViewWithMap(PropertiesUtil.getPropertyGroup("phone_call_group"), this.dialList, BuildConfig.FLAVOR)));
        this.cspv_contact.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerViewWithMap(PropertiesUtil.getPropertyGroup("phone_contact_group"), this.contactList, BuildConfig.FLAVOR)));
        if (ModeUtils.isNightMode(getContext())) {
            setNight();
        } else {
            setLight();
        }
        if (getResources().getConfiguration().orientation == 2) {
            setLandScape();
        } else {
            setPortrait();
        }
    }

    @Override // com.byd.vrsettings.BaseFragment
    protected void onConfigChangeUI(Configuration configuration) {
        if (ModeUtils.isNightMode(getContext())) {
            setNight();
        } else {
            setLight();
        }
    }

    private void setNight() {
        setSmallIcon();
        this.cspv_dial.setNight();
        this.cspv_contact.setNight();
        for (int i = 0; i < this.dialList.size(); i++) {
            this.dialList.get(i).setNight();
        }
        for (int i2 = 0; i2 < this.contactList.size(); i2++) {
            this.contactList.get(i2).setNight();
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.sv_phone.setVerticalScrollbarThumbDrawable(getContext().getDrawable(R.drawable.bg_scrollbar_night));
        }
    }

    private void setLight() {
        setSmallIcon();
        this.cspv_dial.setLight();
        this.cspv_contact.setLight();
        for (int i = 0; i < this.dialList.size(); i++) {
            this.dialList.get(i).setLight();
        }
        for (int i2 = 0; i2 < this.contactList.size(); i2++) {
            this.contactList.get(i2).setLight();
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.sv_phone.setVerticalScrollbarThumbDrawable(getContext().getDrawable(R.drawable.bg_scrollbar));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeLanguage(PrivacyPolicyEvent privacyPolicyEvent) {
        if (privacyPolicyEvent.getType().equals("SP_KEY_CUR_LANGUAGE") && PropertiesUtil.loadLanguage()) {
            setViewpagerAdapterByProp();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override // com.byd.vrsettings.BaseFragment
    protected void reloadData() {
        setTitle();
    }
}
