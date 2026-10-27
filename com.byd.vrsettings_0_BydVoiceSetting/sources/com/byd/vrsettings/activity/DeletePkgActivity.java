package com.byd.vrsettings.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.byd.vrsettings.BydVoiceSettingApplication;
import com.byd.vrsettings.R;
import com.byd.vrsettings.adapter.DeletePkgAdapter;
import com.byd.vrsettings.databinding.LayoutDeletePkgBinding;
import com.byd.vrsettings.dialog.CommonDialog2;
import com.byd.vrsettings.eventbus.PrivacyPolicyEvent;
import com.byd.vrsettings.model.LanguageLocal;
import com.byd.vrsettings.service.AidlServiceManager;
import com.byd.vrsettings.service.OtaDataManager;
import com.byd.vrsettings.utils.Common;
import com.byd.vrsettings.utils.GSON;
import com.byd.vrsettings.utils.LanguageUtil;
import com.byd.vrsettings.utils.LogUtils;
import com.byd.vrsettings.utils.ModeUtils;
import com.byd.vrsettings.utils.RLog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.google.gson.Gson;
import java.io.File;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class DeletePkgActivity extends AppCompatActivity implements View.OnClickListener {
    static final int COUNTS = 5;
    private DeletePkgAdapter mAdapter;
    LayoutDeletePkgBinding mBinding;
    CommonDialog2.Builder mCommon2Builder;
    private File mFile;
    private final String path = "/navi/BYDAssistant_CR/data/";
    private String TAG = getClass().getSimpleName();
    long[] mHits = new long[5];
    int DURATION = 6000;
    private boolean isOneClick = false;

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        EventBus.getDefault().register(this);
        getWindow().clearFlags(67108864);
        getWindow().getDecorView().setSystemUiVisibility(1280);
        getWindow().addFlags(Integer.MIN_VALUE);
        getWindow().setStatusBarColor(0);
        LayoutDeletePkgBinding layoutDeletePkgBindingInflate = LayoutDeletePkgBinding.inflate(LayoutInflater.from(this));
        this.mBinding = layoutDeletePkgBindingInflate;
        setContentView(layoutDeletePkgBindingInflate.getRoot());
        initData();
    }

    private void initData() {
        this.mAdapter = new DeletePkgAdapter(this);
        this.mBinding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        this.mBinding.recyclerview.setAdapter(this.mAdapter);
        this.mBinding.imageviewBack.setOnClickListener(this);
        this.mBinding.textviewVoice.setOnClickListener(this);
        requestLocalPkgList();
        if (getResources().getConfiguration().orientation == 2) {
            setLandScape();
        } else {
            setPortrait();
        }
        if (ModeUtils.isNightMode(this)) {
            setNight();
        } else {
            setLight();
        }
        this.mAdapter.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.byd.vrsettings.activity.DeletePkgActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                this.f$0.m9lambda$initData$0$combydvrsettingsactivityDeletePkgActivity(baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initData$0$com-byd-vrsettings-activity-DeletePkgActivity, reason: not valid java name */
    /* synthetic */ void m9lambda$initData$0$combydvrsettingsactivityDeletePkgActivity(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        LanguageLocal item = this.mAdapter.getItem(i);
        if (view.getId() == R.id.imageview_delete) {
            doCancel(item);
        }
    }

    private void setPortrait() {
        RLog.i(this.TAG, "setPortrait");
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.mBinding.recyclerview.getLayoutParams();
        layoutParams.bottomMargin = Common.getNavigationBarHeight();
        this.mBinding.recyclerview.setLayoutParams(layoutParams);
        this.mAdapter.setLandscape(false);
    }

    private void setLandScape() {
        RLog.i(this.TAG, "setLandScape");
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.mBinding.recyclerview.getLayoutParams();
        layoutParams.bottomMargin = Common.getNavigationBarHeight();
        this.mBinding.recyclerview.setLayoutParams(layoutParams);
        this.mAdapter.setLandscape(true);
    }

    private void requestLocalPkgList() {
        String string = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), "KEY_LANGUAG_NAVIDPS");
        if (!TextUtils.isEmpty(string)) {
            this.mAdapter.setNewInstance(GSON.fromJsonList(string, LanguageLocal.class));
        } else {
            Toast.makeText(this, "data is null", 0).show();
        }
    }

    private void setLight() {
        getWindow().setNavigationBarColor(Color.parseColor("#F2F7FA"));
        this.mAdapter.setLight(true);
        this.mBinding.imageviewIcon.setImageResource(R.mipmap.icon_voice_language_light);
        this.mBinding.textviewVoice.setTextColor(ContextCompat.getColor(this, R.color.voice_setting_title));
        this.mBinding.viewOtaRoot.setBackgroundResource(R.mipmap.img_bg_setting);
        CommonDialog2.Builder builder = this.mCommon2Builder;
        if (builder != null) {
            builder.setLight(true);
        }
        this.mBinding.tvLoading.setTextColor(ContextCompat.getColor(this, R.color.voice_setting_text_selected));
        this.mBinding.textviewVersion.setTextColor(ContextCompat.getColor(this, R.color.voice_setting_title));
    }

    private void setNight() {
        getWindow().setNavigationBarColor(Color.parseColor("#0F1C2B"));
        this.mAdapter.setLight(false);
        this.mBinding.imageviewIcon.setImageResource(R.mipmap.icon_voice_language_night);
        this.mBinding.textviewVoice.setTextColor(ContextCompat.getColor(this, R.color.voice_setting_title_night));
        this.mBinding.viewOtaRoot.setBackgroundResource(R.mipmap.img_bg_setting_night);
        CommonDialog2.Builder builder = this.mCommon2Builder;
        if (builder != null) {
            builder.setLight(false);
        }
        this.mBinding.tvLoading.setTextColor(ContextCompat.getColor(this, R.color.voice_volume_text_night));
        this.mBinding.textviewVersion.setTextColor(ContextCompat.getColor(this, R.color.voice_volume_text_night));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.imageview_back) {
            finish();
        } else if (view.getId() == R.id.textview_voice) {
            continuousClick();
        }
    }

    private String getName(String str) {
        return str.split("//")[r2.length - 1];
    }

    private void doCancel(final LanguageLocal languageLocal) {
        if (OtaDataManager.getCurrentLanguageName().contains("spa") && languageLocal.getName().contains("spa")) {
            Toast.makeText(this, "当前语音包正在使用中，不可删除", 0).show();
        } else if (OtaDataManager.getCurrentLanguageName().contains("por") && languageLocal.getName().contains("por")) {
            Toast.makeText(this, "当前语音包正在使用中，不可删除", 0).show();
            return;
        } else if (OtaDataManager.getCurrentLanguageName().contains("eng") && languageLocal.getName().contains("eng")) {
            Toast.makeText(this, "当前语音包正在使用中，不可删除", 0).show();
            return;
        } else if (OtaDataManager.getCurrentLanguageName().equals(languageLocal.getName())) {
            Toast.makeText(this, "当前语音包正在使用中，不可删除", 0).show();
            return;
        }
        CommonDialog2.Builder positiveClickLitener = new CommonDialog2.Builder(this).setLight(true ^ ModeUtils.isNightMode(this)).setContent(String.format(getResources().getString(R.string.text_confirm_delete_voice), LanguageUtil.topLanguageConvertBackDoor(this, languageLocal.getName()))).setPositiveClickLitener(getResources().getString(R.string.text_delete), new View.OnClickListener() { // from class: com.byd.vrsettings.activity.DeletePkgActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m8lambda$doCancel$1$combydvrsettingsactivityDeletePkgActivity(languageLocal, view);
            }
        });
        this.mCommon2Builder = positiveClickLitener;
        positiveClickLitener.create().show();
    }

    /* JADX INFO: renamed from: lambda$doCancel$1$com-byd-vrsettings-activity-DeletePkgActivity, reason: not valid java name */
    /* synthetic */ void m8lambda$doCancel$1$combydvrsettingsactivityDeletePkgActivity(LanguageLocal languageLocal, View view) {
        deleteFolder(languageLocal.getPath());
        notifyVrAssistant(languageLocal.getPath());
    }

    public void deleteFolder(String str) {
        deleteFile(new File(str));
    }

    public void deleteFile(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles == null || fileArrListFiles.length == 0) {
                file.delete();
                return;
            }
            for (File file2 : fileArrListFiles) {
                deleteFile(file2);
            }
            file.delete();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChangeLanguageEvent(PrivacyPolicyEvent privacyPolicyEvent) {
        if (privacyPolicyEvent.getType().equals("KEY_LANGUAG_NAVIDPS")) {
            requestLocalPkgList();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void notifyVrAssistant(String str) {
        Intent intent = new Intent("com.byd.voice.action.initlang");
        intent.setPackage("com.byd.vrassistant");
        intent.putExtra("data_path", str);
        sendBroadcast(intent);
    }

    private void continuousClick() {
        long[] jArr = this.mHits;
        System.arraycopy(jArr, 1, jArr, 0, jArr.length - 1);
        long[] jArr2 = this.mHits;
        jArr2[jArr2.length - 1] = SystemClock.uptimeMillis();
        LogUtils.e(this.TAG, "mHits:" + new Gson().toJson(this.mHits));
        if (!this.isOneClick) {
            this.mBinding.imageviewBack.postDelayed(new Runnable() { // from class: com.byd.vrsettings.activity.DeletePkgActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    if (DeletePkgActivity.this.mHits[0] == 0) {
                        LogUtils.e(DeletePkgActivity.this.TAG, DeletePkgActivity.this.DURATION + "ms时间到");
                        DeletePkgActivity.this.mHits = new long[5];
                        DeletePkgActivity.this.isOneClick = false;
                    }
                }
            }, this.DURATION);
        }
        this.isOneClick = true;
        LogUtils.e(this.TAG, "点击。。");
        if (this.mHits[0] >= SystemClock.uptimeMillis() - ((long) this.DURATION)) {
            LogUtils.e(this.TAG, "跳转到暗门页");
            this.mHits = new long[5];
            this.isOneClick = false;
            Toast.makeText(this, "show version info", 0).show();
            this.mBinding.textviewVersion.setVisibility(0);
            this.mBinding.textviewVersion.setText(getVersionInfo());
        }
    }

    private String getVersionInfo() {
        try {
            String str = BydVoiceSettingApplication.getContext().getPackageManager().getPackageInfo("com.byd.vrassistant", 0).versionName;
            String str2 = BydVoiceSettingApplication.getContext().getPackageManager().getPackageInfo(BydVoiceSettingApplication.getContext().getPackageName(), 0).versionName;
            String downloadAppVersion = getDownloadAppVersion();
            String string = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), "KEY_VOICE_VERSION");
            StringBuilder sb = new StringBuilder();
            sb.append("V." + str);
            sb.append("|");
            sb.append("V." + str2);
            sb.append("|");
            sb.append(downloadAppVersion);
            sb.append("|");
            sb.append("V." + string);
            return sb.toString();
        } catch (PackageManager.NameNotFoundException unused) {
            return "error";
        }
    }

    private String getDownloadAppVersion() {
        try {
            String downloadAppVersion = AidlServiceManager.getInstance().getDownloadAppVersion();
            RLog.i(this.TAG, "-downLoad VersionCode:" + downloadAppVersion);
            return downloadAppVersion;
        } catch (RemoteException e) {
            e.printStackTrace();
            return BydVoiceSettingApplication.getContext().getString(R.string.text_smart_voice_version);
        }
    }
}
