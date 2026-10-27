package com.byd.vrsettings.utils;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import com.chad.library.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
public class MyViewModel extends AndroidViewModel {
    public boolean UserImprovementButtonState;
    public int curPos;
    public int currentFragment;
    public boolean isNAVI_AVAILABLEFlag;
    public boolean isShowUserImproveFragment;
    public boolean isShowWakeupFragment;
    public boolean showCerencePri;
    public int showDialogValue;
    public int showUserImproveDialogValue;
    public boolean skillFragmentOnResume;
    public boolean skillFragmentOnResumeforHELP;
    public int skillsItemValue;
    public int tabItemValue;
    public String userDefineText;

    public MyViewModel(Application application) {
        super(application);
        this.tabItemValue = 0;
        this.skillsItemValue = 0;
        this.showDialogValue = 0;
        this.showCerencePri = true;
        this.showUserImproveDialogValue = 0;
        this.currentFragment = 1;
        this.userDefineText = BuildConfig.FLAVOR;
        this.curPos = 0;
        this.isShowWakeupFragment = false;
        this.skillFragmentOnResume = false;
        this.skillFragmentOnResumeforHELP = false;
        this.isShowUserImproveFragment = false;
        this.UserImprovementButtonState = false;
        this.isNAVI_AVAILABLEFlag = false;
    }
}
