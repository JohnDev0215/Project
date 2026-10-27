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
import com.byd.vrsettings.utils.Constant;
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
public class PlayerFragment extends BaseFragment {
    private CustomSkillsPopupView cspv_music;
    private CustomSkillsPopupView cspv_radio;
    private ConstraintLayout custom_player;
    private List<Integer> listMusic;
    private List<Integer> listRadio;
    private List<CustomViewpagerView> musicList;
    private List<CustomViewpagerView> radioList;
    private ConstraintSet set;
    private CustomScrollView sv_player;
    private View view;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeSkin(SkinEvent skinEvent) {
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.view = layoutInflater.inflate(R.layout.item_skills_player, viewGroup, false);
        initView();
        return this.view;
    }

    private void initView() {
        EventBus.getDefault().register(this);
        this.set = new ConstraintSet();
        this.custom_player = (ConstraintLayout) this.view.findViewById(R.id.custom_player);
        CustomScrollView customScrollView = (CustomScrollView) this.view.findViewById(R.id.sv_player);
        this.sv_player = customScrollView;
        customScrollView.setOnScrollListener(new CustomScrollView.OnScrollListener() { // from class: com.byd.vrsettings.fragment.PlayerFragment.1
            @Override // com.byd.vrsettings.view.CustomScrollView.OnScrollListener
            public void onScrollY(int i) {
            }
        });
        this.cspv_music = (CustomSkillsPopupView) this.view.findViewById(R.id.cspv_music);
        this.cspv_radio = (CustomSkillsPopupView) this.view.findViewById(R.id.cspv_radio);
        this.musicList = new ArrayList();
        this.radioList = new ArrayList();
        this.listMusic = new ArrayList();
        this.listRadio = new ArrayList();
        initIdList();
        initSkillsPopup();
    }

    public void setLandScape() {
        this.set.clone(this.custom_player);
        this.set.connect(R.id.cspv_music, 7, R.id.cspv_radio, 6, (int) TypedValue.applyDimension(1, 16.0f, getResources().getDisplayMetrics()));
        this.set.connect(R.id.cspv_radio, 3, R.id.cspv_music, 3);
        this.set.connect(R.id.cspv_radio, 6, R.id.cspv_music, 7);
        this.set.applyTo(this.custom_player);
    }

    public void setPortrait() {
        this.set.clone(this.custom_player);
        this.set.clear(R.id.cspv_music, 7);
        this.set.connect(R.id.cspv_music, 7, 0, 7);
        this.set.connect(R.id.cspv_radio, 3, R.id.cspv_music, 4, (int) TypedValue.applyDimension(1, 21.0f, getResources().getDisplayMetrics()));
        this.set.connect(R.id.cspv_radio, 6, 0, 6);
        this.set.applyTo(this.custom_player);
    }

    private void initIdList() {
        this.listMusic.add(Integer.valueOf(R.string.text_play_music));
        this.listMusic.add(Integer.valueOf(R.string.text_pause_music));
        this.listMusic.add(Integer.valueOf(R.string.text_previous_song));
        this.listMusic.add(Integer.valueOf(R.string.text_next_song));
        this.listRadio.add(Integer.valueOf(R.string.text_turn_in_fm));
        this.listRadio.add(Integer.valueOf(R.string.text_scan_stations));
        this.listRadio.add(Integer.valueOf(R.string.text_turn_on_radio));
        this.listRadio.add(Integer.valueOf(R.string.text_turn_off_radio));
        this.listRadio.add(Integer.valueOf(R.string.text_listen_to_radio));
    }

    private void initSkillsPopup() {
        setSmallIcon();
        setViewpagerAdapterByProp();
        setTitle();
    }

    private void setSmallIcon() {
        if (ModeUtils.isNightMode(getContext())) {
            this.cspv_music.setSmallIcon(R.mipmap.ic_skills_one_night);
            this.cspv_radio.setSmallIcon(R.mipmap.ic_skills_two_night);
        } else {
            this.cspv_music.setSmallIcon(R.mipmap.ic_skills_one);
            this.cspv_radio.setSmallIcon(R.mipmap.ic_skills_two);
        }
    }

    private void setTitle() {
        this.cspv_music.setTitle(R.string.text_music);
        this.cspv_radio.setTitle(R.string.text_radio);
    }

    private void setViewpagerAdapter() {
        this.cspv_music.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerView(this.listMusic, this.musicList)));
        this.cspv_radio.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerView(this.listRadio, this.radioList)));
    }

    private void setTitleByProp() {
        this.cspv_music.setTitle(PropertiesUtil.getProperty("music"));
        this.cspv_radio.setTitle(PropertiesUtil.getProperty("radio"));
    }

    private void setViewpagerAdapterByProp() {
        this.cspv_music.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerViewWithMap(PropertiesUtil.getPropertyGroup("media_music_group"), this.musicList, BuildConfig.FLAVOR)));
        this.cspv_radio.setCustomAdapter(new CustomViewpagerAdapter(Common.getCustomPagerViewWithMap(PropertiesUtil.getPropertyGroup("media_radio_group"), this.radioList, Constant.KEY_DEL_SCAN)));
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
        this.cspv_music.setNight();
        this.cspv_radio.setNight();
        for (int i = 0; i < this.musicList.size(); i++) {
            this.musicList.get(i).setNight();
        }
        for (int i2 = 0; i2 < this.radioList.size(); i2++) {
            this.radioList.get(i2).setNight();
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.sv_player.setVerticalScrollbarThumbDrawable(getContext().getDrawable(R.drawable.bg_scrollbar_night));
        }
    }

    private void setLight() {
        setSmallIcon();
        this.cspv_music.setLight();
        this.cspv_radio.setLight();
        for (int i = 0; i < this.musicList.size(); i++) {
            this.musicList.get(i).setLight();
        }
        for (int i2 = 0; i2 < this.radioList.size(); i2++) {
            this.radioList.get(i2).setLight();
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.sv_player.setVerticalScrollbarThumbDrawable(getContext().getDrawable(R.drawable.bg_scrollbar));
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
