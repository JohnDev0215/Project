package com.byd.vrsettings.activity;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.byd.vrdownload.exception.SSLCertificateValidationException;
import com.byd.vrdownload.i.IDownloadCallback;
import com.byd.vrdownload.model.AidlResult;
import com.byd.vrdownload.model.DownloadTask;
import com.byd.vrdownload.model.Failure;
import com.byd.vrdownload.model.LanguageOTAResult;
import com.byd.vrdownload.model.LanguageUpdate;
import com.byd.vrsettings.BaseActivity;
import com.byd.vrsettings.BydVoiceSettingApplication;
import com.byd.vrsettings.R;
import com.byd.vrsettings.adapter.CustomAdapter;
import com.byd.vrsettings.databinding.LayoutOtaUpgradeBinding;
import com.byd.vrsettings.dialog.CommonDialog;
import com.byd.vrsettings.dialog.CommonDialog2;
import com.byd.vrsettings.dialog.LoadingDialog;
import com.byd.vrsettings.dialog.OneButtonDialog;
import com.byd.vrsettings.dialog.RedownloadDialog;
import com.byd.vrsettings.eventbus.PrivacyPolicyEvent;
import com.byd.vrsettings.interf.LanguageListResultCallBack;
import com.byd.vrsettings.interf.OnBackPressInterface;
import com.byd.vrsettings.model.LanguageBean;
import com.byd.vrsettings.service.AidlServiceManager;
import com.byd.vrsettings.service.NotifyManager;
import com.byd.vrsettings.service.NotifyService;
import com.byd.vrsettings.service.OtaDataManager;
import com.byd.vrsettings.service.PollingHandler;
import com.byd.vrsettings.utils.Common;
import com.byd.vrsettings.utils.Constant;
import com.byd.vrsettings.utils.FileUtils;
import com.byd.vrsettings.utils.GSON;
import com.byd.vrsettings.utils.LanguageUtil;
import com.byd.vrsettings.utils.LogUtils;
import com.byd.vrsettings.utils.ModeUtils;
import com.byd.vrsettings.utils.NetUtils;
import com.byd.vrsettings.utils.RLog;
import com.byd.vrsettings.utils.SharedPreferencesUtils;
import com.byd.vrsettings.utils.Tools;
import com.byd.vrsettings.view.FirstPrivacyView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.google.gson.Gson;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class OtaUpgradeActivity extends BaseActivity implements View.OnClickListener {
    static final int COUNTS = 20;
    private AuthDialogReceiver authDialogReceiver;
    DownloadCallback callback;
    private CustomAdapter customAdapter;
    private FirstPrivacyView fv;
    private IntentFilter intentFilter;
    LayoutOtaUpgradeBinding mBinding;
    CommonDialog2.Builder mCommon2Builder;
    CommonDialog.Builder mCommonBuilder;
    CommonDialog2.Builder mGuideBuilder;
    private List<LanguageUpdate> mLanguageRemote;
    LoadingDialog.Builder mLoadingBuilder;
    OneButtonDialog.Builder mNoNetworkBuilder;
    RedownloadDialog.Builder mRedownloadBuilder;
    ConnectivityManager.NetworkCallback networkCallback;
    private PollingHandler pollingHandler;
    private String TAG = getClass().getSimpleName();
    Long prevClickTime = 0L;
    boolean isLocalShow = false;
    boolean isOnlineShow = false;
    boolean isSwitchShow = false;
    long[] mHits = new long[20];
    int DURATION = 6000;
    private boolean isOneClick = false;

    static /* synthetic */ void lambda$onResume$0(View view) {
    }

    static /* synthetic */ void lambda$showGuideToDownloadPkg$16(View view) {
    }

    static /* synthetic */ void lambda$showOneButtonDialog$15(View view) {
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        int intExtra;
        super.onCreate(bundle);
        RLog.e("wens", "网络是否连接：" + NetUtils.isNetAvailable(this));
        RLog.e("wens", "wifi连接：" + NetUtils.isWifiConnect());
        RLog.e("wens", "数据流量连接：" + NetUtils.isCellularConnect());
        RLog.e("wens", "蓝牙共享网络连接：" + NetUtils.isBluetoothConnect());
        getWindow().clearFlags(67108864);
        getWindow().getDecorView().setSystemUiVisibility(1280);
        getWindow().setStatusBarColor(0);
        getWindow().clearFlags(134217728);
        getWindow().addFlags(Integer.MIN_VALUE);
        getWindow().getDecorView().setSystemUiVisibility(512);
        getWindow().setNavigationBarColor(0);
        LayoutOtaUpgradeBinding layoutOtaUpgradeBindingInflate = LayoutOtaUpgradeBinding.inflate(LayoutInflater.from(this));
        this.mBinding = layoutOtaUpgradeBindingInflate;
        setContentView(layoutOtaUpgradeBindingInflate.getRoot());
        initData();
        initBroadCast();
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
        if (getIntent() == null || (intExtra = getIntent().getIntExtra("notifyid", -100)) == -100) {
            return;
        }
        ((NotificationManager) BydVoiceSettingApplication.getContext().getSystemService("notification")).cancel(intExtra);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        showGuideToDownloadPkg();
        String string = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), Constant.KEY_PRIVACY_POLICY);
        RLog.e(this.TAG, "Ota privacy switch:" + string);
        if (!"true".equals(string)) {
            showPrivacyView();
        }
        if (this.mRedownloadBuilder == null) {
            this.mRedownloadBuilder = new RedownloadDialog.Builder(this).setLight(!ModeUtils.isNightMode(this)).setPositiveClickLitener(getResources().getString(R.string.text_redownload), new View.OnClickListener() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity$$ExternalSyntheticLambda16
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    OtaUpgradeActivity.lambda$onResume$0(view);
                }
            });
        }
        NotifyManager.getInstance().cancelAll();
    }

    private void initData() {
        EventBus.getDefault().register(this);
        DownloadCallback downloadCallback = new DownloadCallback();
        this.callback = downloadCallback;
        downloadCallback.setActivity(this);
        AidlServiceManager.getInstance().registDownLoadCallBack(this.callback, "setting_main");
        this.mBinding.imageviewBack.setOnClickListener(this);
        this.mBinding.recyclerview.setHasFixedSize(true);
        this.mBinding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        this.customAdapter = new CustomAdapter(this);
        if (!TextUtils.isEmpty(OtaDataManager.getVIN()) && !TextUtils.isEmpty(OtaDataManager.getVehicleType())) {
            requestLanguageList();
        }
        this.mBinding.recyclerview.setAdapter(this.customAdapter);
        this.mBinding.textviewVoice.setOnClickListener(this);
        ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity.1
            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onAvailable(Network network) {
                super.onAvailable(network);
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLost(Network network) {
                super.onLost(network);
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                super.onCapabilitiesChanged(network, networkCapabilities);
                if (networkCapabilities.hasCapability(16)) {
                    if (networkCapabilities.hasTransport(1)) {
                        RLog.i(OtaUpgradeActivity.this.TAG, "wifi已经连接");
                    } else if (networkCapabilities.hasTransport(0)) {
                        RLog.i(OtaUpgradeActivity.this.TAG, "数据流量已经连接");
                    } else {
                        RLog.i(OtaUpgradeActivity.this.TAG, "其他网络");
                    }
                }
            }
        };
        this.networkCallback = networkCallback;
        NetUtils.registNetCallBack(networkCallback);
        this.customAdapter.setBtnClickListener(new CustomAdapter.CustomClickListener() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity$$ExternalSyntheticLambda10
            @Override // com.byd.vrsettings.adapter.CustomAdapter.CustomClickListener
            public final void onClick(int i, int i2) {
                this.f$0.m13lambda$initData$1$combydvrsettingsactivityOtaUpgradeActivity(i, i2);
            }
        });
        this.customAdapter.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity$$ExternalSyntheticLambda11
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                this.f$0.m14lambda$initData$2$combydvrsettingsactivityOtaUpgradeActivity(baseQuickAdapter, view, i);
            }
        });
        this.customAdapter.setHotClickListener(new CustomAdapter.CustomClickListener() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity$$ExternalSyntheticLambda12
            @Override // com.byd.vrsettings.adapter.CustomAdapter.CustomClickListener
            public final void onClick(int i, int i2) {
                this.f$0.m17lambda$initData$5$combydvrsettingsactivityOtaUpgradeActivity(i, i2);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$initData$1$com-byd-vrsettings-activity-OtaUpgradeActivity, reason: not valid java name */
    /* synthetic */ void m13lambda$initData$1$combydvrsettingsactivityOtaUpgradeActivity(int i, int i2) {
        RLog.i("wens", "request setCustomClickListener:" + i);
        LanguageBean item = this.customAdapter.getItem(i);
        if (i2 == 0 || i2 == 6) {
            doDownloadOrUpgrade(item, false);
            return;
        }
        if (i2 == 3) {
            doDownloadOrUpgrade(item, false);
            return;
        }
        if (i2 == 1) {
            LogUtils.i("wens", "pause");
            if (item.getTaskId() != -1) {
                RLog.i(this.TAG, "initData: pause result" + AidlServiceManager.getInstance().pause(item).getValue());
                return;
            }
            return;
        }
        if (i2 == 2) {
            LogUtils.i("wens", "continue");
            if (item.getTaskId() != -1) {
                if (NetUtils.isNetAvailable(this)) {
                    AidlServiceManager.getInstance().resume(item.getTaskId(), item.getLanguageRemote());
                    if (OtaDataManager.getAutoEnableState(item.getLanguageRemote().getLanguage()) == -1) {
                        if (item.getLanguageRemote().isDifferentialUpdate()) {
                            OtaDataManager.addAutoEnableList(item.getLanguageRemote().getLanguage(), 5);
                            return;
                        } else {
                            OtaDataManager.addAutoEnableList(item.getLanguageRemote().getLanguage(), 3);
                            return;
                        }
                    }
                    return;
                }
                showOneButtonDialog(getResources().getString(R.string.text_4g_not_connect));
            }
        }
    }

    /* JADX INFO: renamed from: lambda$initData$2$com-byd-vrsettings-activity-OtaUpgradeActivity, reason: not valid java name */
    /* synthetic */ void m14lambda$initData$2$combydvrsettingsactivityOtaUpgradeActivity(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        LanguageBean item = this.customAdapter.getItem(i);
        if (view.getId() == R.id.imageview_delete) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            if (jElapsedRealtime - this.prevClickTime.longValue() >= 500) {
                doCancel(item);
                this.prevClickTime = Long.valueOf(jElapsedRealtime);
            }
        }
    }

    /* JADX INFO: renamed from: lambda$initData$5$com-byd-vrsettings-activity-OtaUpgradeActivity, reason: not valid java name */
    /* synthetic */ void m17lambda$initData$5$combydvrsettingsactivityOtaUpgradeActivity(int i, int i2) {
        final LanguageBean item = this.customAdapter.getItem(i);
        LogUtils.i("wens", "setOnItemClickListener#source===" + item.getSource());
        if (item.getState() == 1) {
            OtaDataManager.showToast(getResources().getString(R.string.text_downloading_retry));
            return;
        }
        if (item.getState() == 7) {
            OtaDataManager.showToast(getResources().getString(R.string.text_vr_assistant_suspend));
            return;
        }
        if (item.getState() == 2) {
            CommonDialog.Builder positiveClickLitener = new CommonDialog.Builder(this).setLight(true ^ ModeUtils.isNightMode(this)).setContent(String.format(getResources().getString(R.string.text_download_not_finish_yet), LanguageUtil.convertAll(this, item.getLanguageRemote().getLanguage()))).setPositiveClickLitener(getResources().getString(R.string.text_download_continue), new View.OnClickListener() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m15lambda$initData$3$combydvrsettingsactivityOtaUpgradeActivity(item, view);
                }
            });
            this.mCommonBuilder = positiveClickLitener;
            positiveClickLitener.create().show();
        } else if (item.getState() == 0 || item.getState() == 6) {
            CommonDialog.Builder positiveClickLitener2 = new CommonDialog.Builder(this).setLight(true ^ ModeUtils.isNightMode(this)).setContent(String.format(getResources().getString(R.string.text_not_download_tip), LanguageUtil.convertAll(this, item.getLanguageRemote().getLanguage()))).setPositiveClickLitener(getResources().getString(R.string.text_download_immediately), new View.OnClickListener() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity$$ExternalSyntheticLambda9
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m16lambda$initData$4$combydvrsettingsactivityOtaUpgradeActivity(item, view);
                }
            });
            this.mCommonBuilder = positiveClickLitener2;
            positiveClickLitener2.create().show();
        } else {
            if (item.getSource() == 2 || item.getLanguageLocal().getUsed() != 0) {
                return;
            }
            showLoading(1);
            OtaDataManager.setSettingValue(item.getLanguageLocal(), 1);
        }
    }

    /* JADX INFO: renamed from: lambda$initData$3$com-byd-vrsettings-activity-OtaUpgradeActivity, reason: not valid java name */
    /* synthetic */ void m15lambda$initData$3$combydvrsettingsactivityOtaUpgradeActivity(LanguageBean languageBean, View view) {
        doDownloadOrUpgrade(languageBean, this.mCommonBuilder.getChecked());
    }

    /* JADX INFO: renamed from: lambda$initData$4$com-byd-vrsettings-activity-OtaUpgradeActivity, reason: not valid java name */
    /* synthetic */ void m16lambda$initData$4$combydvrsettingsactivityOtaUpgradeActivity(LanguageBean languageBean, View view) {
        doDownloadOrUpgrade(languageBean, this.mCommonBuilder.getChecked());
    }

    private void doCancel(final LanguageBean languageBean) {
        String str;
        String str2;
        LogUtils.i("wens", "doCancel#source====" + languageBean.getSource());
        try {
            File file = new File(languageBean.getLanguageLocal().getPath());
            LogUtils.i("wens", file.getPath() + "====canRead:" + file.canRead() + ",canWrite:" + file.canWrite());
        } catch (Exception e) {
            LogUtils.i("wens", "doCancel:" + e.getMessage());
        }
        if (languageBean.getSource() == 0) {
            if (languageBean.getLanguageLocal().getUsed() == 1) {
                str2 = String.format(getResources().getString(R.string.text_delete_using_tip), LanguageUtil.convertAll(this, languageBean.getLanguageLocal().getName()));
            } else {
                str2 = String.format(getResources().getString(R.string.text_confirm_delete_voice), LanguageUtil.convertAll(this, languageBean.getLanguageLocal().getName()));
            }
            CommonDialog2.Builder positiveClickLitener = new CommonDialog2.Builder(this).setLight(!ModeUtils.isNightMode(this)).setContent(str2).setPositiveClickLitener(getResources().getString(R.string.text_delete), new View.OnClickListener() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity$$ExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    OtaUpgradeActivity.lambda$doCancel$6(languageBean, view);
                }
            });
            this.mCommon2Builder = positiveClickLitener;
            positiveClickLitener.create().show();
            return;
        }
        if (languageBean.getSource() == 2) {
            CommonDialog2.Builder positiveClickLitener2 = new CommonDialog2.Builder(this).setLight(!ModeUtils.isNightMode(this)).setContent(String.format(getResources().getString(R.string.text_delete_downloading_tip), LanguageUtil.convertAll(this, languageBean.getLanguageRemote().getLanguage()))).setPositiveClickLitener(getResources().getString(R.string.text_delete), new View.OnClickListener() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity$$ExternalSyntheticLambda6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m10lambda$doCancel$7$combydvrsettingsactivityOtaUpgradeActivity(languageBean, view);
                }
            });
            this.mCommon2Builder = positiveClickLitener2;
            positiveClickLitener2.create().show();
            return;
        }
        if (languageBean.getSource() == 1) {
            if (languageBean.getState() == 1 || languageBean.getState() == 2 || languageBean.getState() == 7) {
                CommonDialog2.Builder positiveClickLitener3 = new CommonDialog2.Builder(this).setLight(!ModeUtils.isNightMode(this)).setContent(String.format(getResources().getString(R.string.text_delete_downloading_tip), LanguageUtil.convertAll(this, languageBean.getLanguageRemote().getLanguage()))).setPositiveClickLitener(getResources().getString(R.string.text_delete), new View.OnClickListener() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity$$ExternalSyntheticLambda7
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.m11lambda$doCancel$8$combydvrsettingsactivityOtaUpgradeActivity(languageBean, view);
                    }
                });
                this.mCommon2Builder = positiveClickLitener3;
                positiveClickLitener3.create().show();
            } else {
                if (languageBean.getLanguageLocal().getUsed() == 1) {
                    str = String.format(getResources().getString(R.string.text_delete_using_tip), LanguageUtil.convertAll(this, languageBean.getLanguageLocal().getName()));
                } else {
                    str = String.format(getResources().getString(R.string.text_confirm_delete_voice), LanguageUtil.convertAll(this, languageBean.getLanguageLocal().getName()));
                }
                CommonDialog2.Builder positiveClickLitener4 = new CommonDialog2.Builder(this).setLight(!ModeUtils.isNightMode(this)).setContent(str).setPositiveClickLitener(getResources().getString(R.string.text_delete), new View.OnClickListener() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity$$ExternalSyntheticLambda8
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        OtaDataManager.setSettingValue(languageBean.getLanguageLocal(), 2);
                    }
                });
                this.mCommon2Builder = positiveClickLitener4;
                positiveClickLitener4.create().show();
            }
        }
    }

    static /* synthetic */ void lambda$doCancel$6(LanguageBean languageBean, View view) {
        OtaDataManager.setSettingValue(languageBean.getLanguageLocal(), 2);
        OtaDataManager.removeAutoEnableList(languageBean.getLanguageRemote());
    }

    /* JADX INFO: renamed from: lambda$doCancel$7$com-byd-vrsettings-activity-OtaUpgradeActivity, reason: not valid java name */
    /* synthetic */ void m10lambda$doCancel$7$combydvrsettingsactivityOtaUpgradeActivity(LanguageBean languageBean, View view) {
        OtaDataManager.removeAutoEnableList(languageBean.getLanguageRemote());
        RLog.d("NEU_wens", "doCancel222  result:" + AidlServiceManager.getInstance().cancel(languageBean).getValue());
        updateDownloadStateById(languageBean.getTaskId(), true);
    }

    /* JADX INFO: renamed from: lambda$doCancel$8$com-byd-vrsettings-activity-OtaUpgradeActivity, reason: not valid java name */
    /* synthetic */ void m11lambda$doCancel$8$combydvrsettingsactivityOtaUpgradeActivity(LanguageBean languageBean, View view) {
        RLog.d("wens", "doCancel1111  result:" + AidlServiceManager.getInstance().cancel(languageBean));
        updateDownloadStateById(languageBean.getTaskId(), true);
    }

    private void doDownloadOrUpgrade(LanguageBean languageBean, boolean z) {
        if (languageBean.getLanguageRemote() == null) {
            OtaDataManager.showToast("data error");
            return;
        }
        final LanguageUpdate languageRemote = languageBean.getLanguageRemote();
        boolean zIsDifferentialUpdate = languageRemote.isDifferentialUpdate();
        String latestPatchPkgSize = zIsDifferentialUpdate ? languageRemote.getLatestPatchPkgSize() : languageRemote.getLatestPkgSize();
        if (zIsDifferentialUpdate) {
            OtaDataManager.addAutoEnableList(languageRemote.getLanguage(), z ? 6 : 5);
        } else {
            OtaDataManager.addAutoEnableList(languageRemote.getLanguage(), z ? 4 : 3);
        }
        if (TextUtils.isEmpty(latestPatchPkgSize)) {
            OtaDataManager.showToast("data size error");
            return;
        }
        long j = Long.parseLong(latestPatchPkgSize) * 3;
        LogUtils.i("wens", "Tools.getAvailableSize():" + Tools.getAvailableSize());
        if (j < Tools.getAvailableSize()) {
            if (NetUtils.isNetAvailable(this)) {
                if (NetUtils.isWifiConnect() || NetUtils.isBluetoothConnect()) {
                    AidlServiceManager.getInstance().downLoadPkg(languageRemote.getLanguage());
                    return;
                }
                if (NetUtils.isCellularConnect()) {
                    if (OtaDataManager.isTodayNotTip(this)) {
                        AidlServiceManager.getInstance().downLoadPkg(languageRemote.getLanguage());
                        return;
                    }
                    CommonDialog.Builder positiveClickLitener = new CommonDialog.Builder(this).setLight(!ModeUtils.isNightMode(this)).setContent(String.format(getResources().getString(R.string.text_not_connect_wifi_tip), LanguageUtil.convertAll(this, languageRemote.getLanguage()), getOnlineSize(languageBean))).setCheckBoxText(getResources().getString(R.string.text_not_remind_today)).setChecked(false).setPositiveClickLitener(getResources().getString(R.string.text_download_continue), new View.OnClickListener() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity$$ExternalSyntheticLambda2
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            this.f$0.m12x51ee0595(languageRemote, view);
                        }
                    });
                    this.mCommonBuilder = positiveClickLitener;
                    positiveClickLitener.create().show();
                    return;
                }
                return;
            }
            showOneButtonDialog(getResources().getString(R.string.text_4g_not_connect));
            return;
        }
        OtaDataManager.showToast(getResources().getString(R.string.text_not_enough_space));
    }

    /* JADX INFO: renamed from: lambda$doDownloadOrUpgrade$10$com-byd-vrsettings-activity-OtaUpgradeActivity, reason: not valid java name */
    /* synthetic */ void m12x51ee0595(LanguageUpdate languageUpdate, View view) {
        if (this.mCommonBuilder.getChecked()) {
            OtaDataManager.setTodayNotTip(this);
        }
        AidlServiceManager.getInstance().downLoadPkg(languageUpdate.getLanguage());
    }

    @Override // com.byd.vrsettings.BaseActivity
    protected void reloadData() {
        this.customAdapter.notifyDataSetChanged();
        OtaDataManager.updateViewLanguage(findViewById(R.id.view_ota_root));
    }

    public static class DownloadCallback extends IDownloadCallback.Stub {
        public OtaUpgradeActivity activity = null;

        public void setActivity(OtaUpgradeActivity otaUpgradeActivity) {
            this.activity = otaUpgradeActivity;
        }

        @Override // com.byd.vrdownload.i.IDownloadCallback
        public AidlResult checkSpaceQuota(long j) throws RemoteException {
            return AidlResult.fail(new Exception());
        }

        @Override // com.byd.vrdownload.i.IDownloadCallback
        public void getLanguageOTA(final LanguageOTAResult languageOTAResult) throws RemoteException {
            LogUtils.i("wens", "callback getLanguageOTA isSuccess===" + languageOTAResult.isSuccess());
            OtaUpgradeActivity otaUpgradeActivity = this.activity;
            if (otaUpgradeActivity == null) {
                return;
            }
            final String str = otaUpgradeActivity.TAG;
            this.activity.dismissLoading(3);
            if (languageOTAResult.isSuccess()) {
                if (this.activity == null) {
                    LogUtils.i(str, "callback getLanguageOTA 111 activity is null");
                }
                this.activity.mLanguageRemote = languageOTAResult.getList();
                LogUtils.i(str, "callback getLanguageOTA--->" + new Gson().toJson(this.activity.mLanguageRemote));
                OtaUpgradeActivity otaUpgradeActivity2 = this.activity;
                otaUpgradeActivity2.combineLocalAndOnlineList(otaUpgradeActivity2.mLanguageRemote, false);
                if (this.activity == null) {
                    LogUtils.i(str, "callback getLanguageOTA 222 activity is null");
                    return;
                }
                return;
            }
            this.activity.runOnUiThread(new Runnable() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity$DownloadCallback$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m22xe931e4b9(str, languageOTAResult);
                }
            });
            LogUtils.i("wens", "callback getLanguageOTA: " + languageOTAResult.getException().getMessage());
        }

        /* JADX INFO: renamed from: lambda$getLanguageOTA$0$com-byd-vrsettings-activity-OtaUpgradeActivity$DownloadCallback, reason: not valid java name */
        /* synthetic */ void m22xe931e4b9(String str, LanguageOTAResult languageOTAResult) {
            if (this.activity == null) {
                LogUtils.i(str, "callback getLanguageOTA 444 activity is null");
            }
            if (NetUtils.isNetAvailable(BydVoiceSettingApplication.getContext())) {
                if (languageOTAResult.getException() != null && (languageOTAResult.getException() instanceof SSLCertificateValidationException)) {
                    OtaDataManager.showToast(R.string.text_ssl_validation_fail);
                } else {
                    OtaDataManager.showToast(R.string.text_weak_network);
                }
                LogUtils.e(str, "result:" + new Gson().toJson(languageOTAResult.getException()));
            } else {
                OtaDataManager.showToast(R.string.text_net_retry);
            }
            LogUtils.i("wens", "云端列表失败 :" + languageOTAResult.toString());
            LogUtils.i("wens", "allTask -->" + new Gson().toJson(AidlServiceManager.getInstance().getAllTask()));
            OtaUpgradeActivity otaUpgradeActivity = this.activity;
            if (otaUpgradeActivity != null) {
                otaUpgradeActivity.combineLocalAndOnlineList(otaUpgradeActivity.mLanguageRemote, false);
            }
        }

        @Override // com.byd.vrdownload.i.IDownloadCallback
        public void onDownload(DownloadTask downloadTask) throws RemoteException {
            final ArrayList arrayList = new ArrayList();
            arrayList.add(downloadTask);
            LogUtils.i("wens", "callback onDownload task language=====" + downloadTask.getLanguageUpdate().getLanguage() + ",status=" + ((int) downloadTask.getStatus()));
            this.activity.runOnUiThread(new Runnable() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity$DownloadCallback$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m23xae277d13(arrayList);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onDownload$1$com-byd-vrsettings-activity-OtaUpgradeActivity$DownloadCallback, reason: not valid java name */
        /* synthetic */ void m23xae277d13(List list) {
            this.activity.updateDownloadState(list, false);
        }

        @Override // com.byd.vrdownload.i.IDownloadCallback
        public void onStartDownloadResult(AidlResult aidlResult) throws RemoteException {
            LogUtils.i("wens", "callback onStartDownloadResult=====" + GSON.toJson(aidlResult.getValue()));
            if ((aidlResult.getValue() instanceof Failure) && (((Failure) aidlResult.getValue()).getException() instanceof SSLCertificateValidationException)) {
                this.activity.mBinding.imageviewBack.postDelayed(new Runnable() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity.DownloadCallback.1
                    @Override // java.lang.Runnable
                    public void run() {
                        OtaDataManager.showToast(DownloadCallback.this.activity.getString(R.string.text_ssl_validation_fail));
                        LogUtils.i("wens", "onStartDownloadResult: SslCertificateValidationException");
                    }
                }, 2000L);
            }
        }

        @Override // com.byd.vrdownload.i.IDownloadCallback
        public void onDownloadProgress(DownloadTask downloadTask, long j, long j2) throws RemoteException {
            LogUtils.i("wens", "callback onDownloadProgress taskId-->" + downloadTask.getId());
            final ArrayList arrayList = new ArrayList();
            arrayList.add(downloadTask);
            this.activity.runOnUiThread(new Runnable() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity$DownloadCallback$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m24x4d091021(arrayList);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onDownloadProgress$2$com-byd-vrsettings-activity-OtaUpgradeActivity$DownloadCallback, reason: not valid java name */
        /* synthetic */ void m24x4d091021(List list) {
            this.activity.updateDownloadState(list, false);
        }

        @Override // com.byd.vrdownload.i.IDownloadCallback
        public void deleteLanguage(String str) throws RemoteException {
            LogUtils.i("wens", "callback deleteLanguage: " + str);
        }

        @Override // com.byd.vrdownload.i.IDownloadCallback
        public void installLanguage(String str) throws RemoteException {
            LogUtils.i("wens", "callback installLanguage===" + str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestLanguageList() {
        combineLocalAndOnlineList(this.mLanguageRemote, false);
        if (AidlServiceManager.getInstance().isServiceConnect()) {
            showLoading(3);
            AidlServiceManager.getInstance().requestLanguageList(OtaDataManager.createParemeter());
        }
    }

    private void updateDownloadStateById(int i, boolean z) {
        for (LanguageBean languageBean : this.customAdapter.getData()) {
            if (languageBean.getTaskId() == i) {
                languageBean.setState(languageBean.isUpdate() ? 3 : 0);
                if (z) {
                    LogUtils.e(this.TAG, "bean:" + new Gson().toJson(languageBean));
                    if (languageBean.getLanguageRemote() != null) {
                        NotifyManager.getInstance().removeByName(languageBean.getLanguageRemote().getLanguage());
                    } else if (languageBean.getLanguageLocal() != null) {
                        NotifyManager.getInstance().removeByName(languageBean.getLanguageLocal().convertName());
                    }
                }
            }
        }
        this.customAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void updateDownloadState(List<DownloadTask> list, boolean z) {
        if (list != null) {
            if (list.size() != 0) {
                LogUtils.i("wens", "updateDownloadState tasks size-->" + list.size());
                for (DownloadTask downloadTask : list) {
                    if (downloadTask.getStatus() != -4) {
                        Iterator<LanguageBean> it = this.customAdapter.getData().iterator();
                        while (true) {
                            if (it.hasNext()) {
                                LanguageBean next = it.next();
                                if (downloadTask.getLanguageUpdate() != null && next.getLanguageRemote() != null && next.getLanguageRemote().getLanguage().equals(downloadTask.getLanguageUpdate().getLanguage())) {
                                    LogUtils.i("wens", downloadTask.getLanguageUpdate().getLanguage() + "..getStatus======" + ((int) downloadTask.getStatus()));
                                    next.setLanguageRemote(downloadTask.getLanguageUpdate());
                                    next.setTaskId(downloadTask.getId());
                                    next.setRemotePath(downloadTask.getPath());
                                    boolean z2 = true;
                                    if (downloadTask.getStatus() == 6 || downloadTask.getStatus() == 2 || downloadTask.getStatus() == 3) {
                                        next.setState(1);
                                    } else if (downloadTask.getStatus() == -2) {
                                        next.setState(2);
                                    } else if (downloadTask.getStatus() == -3) {
                                        boolean zIsAssistantLive = OtaDataManager.isAssistantLive(this);
                                        if (z) {
                                            if (((Boolean) SharedPreferencesUtils.getParam(BydVoiceSettingApplication.getContext(), next.getLanguageRemote().getLanguage(), false)).booleanValue() && zIsAssistantLive) {
                                                next.setState(7);
                                                LogUtils.i("wens", "init installing ：" + downloadTask.getLanguageUpdate().getLanguage());
                                            } else {
                                                next.setState(next.isUpdate() ? 3 : 6);
                                                LogUtils.i("wens", "init notDownload ：" + next.isUpdate());
                                            }
                                        } else if (zIsAssistantLive) {
                                            StringBuilder sbAppend = new StringBuilder().append("!OtaDataManager.isConversationBegin():");
                                            if (OtaDataManager.isConversationBegin()) {
                                                z2 = false;
                                            }
                                            Log.e("wens", sbAppend.append(z2).toString());
                                            if (!OtaDataManager.isConversationBegin()) {
                                                next.setState(7);
                                                installLanguagePkg(next);
                                            } else {
                                                Log.e("wens", "WAIT_INSTALL");
                                                next.setState(9);
                                            }
                                        } else {
                                            RLog.e("wens", "isAssistantLive:" + zIsAssistantLive);
                                        }
                                    } else if (downloadTask.getStatus() == -1 || downloadTask.getStatus() == 0) {
                                        next.setState(2);
                                        if (!z) {
                                            final String str = String.format(getResources().getString(R.string.text_toast_download_suspend), LanguageUtil.convertAll(this, downloadTask.getLanguageUpdate().getLanguage()));
                                            runOnUiThread(new Runnable() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity$$ExternalSyntheticLambda13
                                                @Override // java.lang.Runnable
                                                public final void run() {
                                                    OtaDataManager.showToast(str);
                                                }
                                            });
                                        }
                                    } else if (downloadTask.getStatus() == -5) {
                                        RedownloadDialog.Builder builder = this.mRedownloadBuilder;
                                        if (builder != null) {
                                            builder.addData(downloadTask);
                                            runOnUiThread(new Runnable() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity$$ExternalSyntheticLambda14
                                                @Override // java.lang.Runnable
                                                public final void run() {
                                                    this.f$0.m20xb47ecbad();
                                                }
                                            });
                                        }
                                        AidlServiceManager.getInstance().cancel(next);
                                        updateDownloadStateById(next.getTaskId(), false);
                                    }
                                    LogUtils.i("wens", "percent=====" + Tools.percentInt(downloadTask.getSofar(), downloadTask.getTotal()));
                                    next.setProgress(Tools.percentInt(downloadTask.getSofar(), downloadTask.getTotal()));
                                }
                            }
                        }
                    }
                }
                runOnUiThread(new Runnable() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity$$ExternalSyntheticLambda15
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m21xee496d8c();
                    }
                });
            }
        }
    }

    /* JADX INFO: renamed from: lambda$updateDownloadState$12$com-byd-vrsettings-activity-OtaUpgradeActivity, reason: not valid java name */
    /* synthetic */ void m20xb47ecbad() {
        this.mRedownloadBuilder.create().show();
    }

    /* JADX INFO: renamed from: lambda$updateDownloadState$13$com-byd-vrsettings-activity-OtaUpgradeActivity, reason: not valid java name */
    /* synthetic */ void m21xee496d8c() {
        this.customAdapter.notifyDataSetChanged();
    }

    private void installLanguagePkg(LanguageBean languageBean) {
        RLog.i(this.TAG, "OTA Page installLanguagePkg");
        Intent intent = new Intent(this, (Class<?>) NotifyService.class);
        intent.putExtra("NotifyParams", "installPkg");
        intent.putExtra("languageRemote", languageBean.getLanguageRemote());
        intent.putExtra("taskId", languageBean.getTaskId());
        startForegroundService(intent);
    }

    private void startPolling() {
        List<DownloadTask> allTask = AidlServiceManager.getInstance().getAllTask();
        RLog.i("wens", "getAllTask--->" + allTask);
        if (allTask == null || allTask.isEmpty()) {
            RLog.i("wens", "allTask===null");
            return;
        }
        RLog.i("wens", "startPolling...");
        if (this.pollingHandler == null) {
            this.pollingHandler = PollingHandler.newTask(3000L, new PollingHandler.Consumer() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity$$ExternalSyntheticLambda1
                @Override // com.byd.vrsettings.service.PollingHandler.Consumer
                public final void next(Object obj) {
                    this.f$0.m19x17a14b26(obj);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$startPolling$14$com-byd-vrsettings-activity-OtaUpgradeActivity, reason: not valid java name */
    /* synthetic */ void m19x17a14b26(Object obj) {
        updateDownloadState(AidlServiceManager.getInstance().getAllTask(), false);
    }

    private void setPortrait() {
        RLog.i(this.TAG, "setPortrait");
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.mBinding.recyclerview.getLayoutParams();
        layoutParams.bottomMargin = Common.getNavigationBarHeight();
        this.mBinding.recyclerview.setLayoutParams(layoutParams);
        this.customAdapter.setLandscape(false);
    }

    private void setLandScape() {
        RLog.i(this.TAG, "setLandScape");
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.mBinding.recyclerview.getLayoutParams();
        layoutParams.bottomMargin = Common.getNavigationBarHeight();
        this.mBinding.recyclerview.setLayoutParams(layoutParams);
        this.customAdapter.setLandscape(true);
    }

    private void setLight() {
        getWindow().setNavigationBarColor(Color.parseColor("#F2F7FA"));
        this.customAdapter.setLight(true);
        this.mBinding.imageviewBack.setImageResource(R.mipmap.btn_back_n);
        this.mBinding.imageviewIcon.setImageResource(R.mipmap.icon_voice_language_light);
        this.mBinding.textviewVoice.setTextColor(ContextCompat.getColor(this, R.color.voice_setting_title));
        this.mBinding.viewOtaRoot.setBackgroundResource(R.mipmap.img_bg_setting);
        LoadingDialog.Builder builder = this.mLoadingBuilder;
        if (builder != null) {
            builder.setLight(true);
        }
        CommonDialog.Builder builder2 = this.mCommonBuilder;
        if (builder2 != null) {
            builder2.setLight(true);
        }
        CommonDialog2.Builder builder3 = this.mCommon2Builder;
        if (builder3 != null) {
            builder3.setLight(true);
        }
        CommonDialog2.Builder builder4 = this.mGuideBuilder;
        if (builder4 != null) {
            builder4.setLight(true);
        }
        RedownloadDialog.Builder builder5 = this.mRedownloadBuilder;
        if (builder5 != null) {
            builder5.setLight(true);
        }
        OneButtonDialog.Builder builder6 = this.mNoNetworkBuilder;
        if (builder6 != null) {
            builder6.setLight(true);
        }
    }

    private void setNight() {
        getWindow().setNavigationBarColor(Color.parseColor("#0F1C2B"));
        this.customAdapter.setLight(false);
        this.mBinding.imageviewBack.setImageResource(R.mipmap.btn_back_n_night);
        this.mBinding.imageviewIcon.setImageResource(R.mipmap.icon_voice_language_night);
        this.mBinding.textviewVoice.setTextColor(ContextCompat.getColor(this, R.color.voice_setting_title_night));
        this.mBinding.viewOtaRoot.setBackgroundResource(R.mipmap.img_bg_setting_night);
        LoadingDialog.Builder builder = this.mLoadingBuilder;
        if (builder != null) {
            builder.setLight(false);
        }
        CommonDialog.Builder builder2 = this.mCommonBuilder;
        if (builder2 != null) {
            builder2.setLight(false);
        }
        CommonDialog2.Builder builder3 = this.mCommon2Builder;
        if (builder3 != null) {
            builder3.setLight(false);
        }
        CommonDialog2.Builder builder4 = this.mGuideBuilder;
        if (builder4 != null) {
            builder4.setLight(false);
        }
        RedownloadDialog.Builder builder5 = this.mRedownloadBuilder;
        if (builder5 != null) {
            builder5.setLight(false);
        }
        OneButtonDialog.Builder builder6 = this.mNoNetworkBuilder;
        if (builder6 != null) {
            builder6.setLight(false);
        }
    }

    private void showLoading(final int i) {
        RLog.i(this.TAG, "showLoading-->" + i);
        runOnUiThread(new Runnable() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity.2
            @Override // java.lang.Runnable
            public void run() {
                int i2 = i;
                if (i2 == 1) {
                    OtaUpgradeActivity.this.isSwitchShow = true;
                } else if (i2 == 2) {
                    OtaUpgradeActivity.this.isLocalShow = true;
                } else if (i2 == 3) {
                    OtaUpgradeActivity.this.isOnlineShow = true;
                }
                if (OtaUpgradeActivity.this.mLoadingBuilder == null) {
                    OtaUpgradeActivity.this.mLoadingBuilder = new LoadingDialog.Builder(OtaUpgradeActivity.this).setLight(true ^ ModeUtils.isNightMode(OtaUpgradeActivity.this));
                    OtaUpgradeActivity.this.mLoadingBuilder.create(new OnBackPressInterface() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity.2.1
                        @Override // com.byd.vrsettings.interf.OnBackPressInterface
                        public void onBackPress() {
                            OtaUpgradeActivity.this.onBackPressed();
                        }
                    });
                }
                if (OtaUpgradeActivity.this.mLoadingBuilder.create().isShowing()) {
                    return;
                }
                OtaUpgradeActivity.this.mLoadingBuilder.create().show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissLoading(final int i) {
        RLog.i(this.TAG, "dismissLoading-->" + i);
        runOnUiThread(new Runnable() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity.3
            @Override // java.lang.Runnable
            public void run() {
                int i2 = i;
                if (i2 == 1) {
                    OtaUpgradeActivity.this.isSwitchShow = false;
                } else if (i2 == 2) {
                    OtaUpgradeActivity.this.isLocalShow = false;
                } else if (i2 == 3) {
                    OtaUpgradeActivity.this.isOnlineShow = false;
                }
                RLog.i(OtaUpgradeActivity.this.TAG, "isLocalShow-->" + OtaUpgradeActivity.this.isLocalShow + ",isOnlineShow-->" + OtaUpgradeActivity.this.isOnlineShow);
                if (OtaUpgradeActivity.this.isLocalShow || OtaUpgradeActivity.this.isOnlineShow || OtaUpgradeActivity.this.isSwitchShow) {
                    return;
                }
                if (OtaUpgradeActivity.this.mLoadingBuilder != null) {
                    OtaUpgradeActivity.this.mLoadingBuilder.create().dismiss();
                }
                OtaUpgradeActivity.this.isLocalShow = false;
                OtaUpgradeActivity.this.isOnlineShow = false;
                OtaUpgradeActivity.this.isSwitchShow = false;
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChangeLanguageEvent(PrivacyPolicyEvent privacyPolicyEvent) {
        if (privacyPolicyEvent.getType().equals(Constant.EVENT_BUS_LANGUAG_DPS)) {
            if ("delete".equals(privacyPolicyEvent.getValue())) {
                this.mBinding.imageviewBack.postDelayed(new Runnable() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity.4
                    @Override // java.lang.Runnable
                    public void run() {
                        OtaUpgradeActivity.this.requestLanguageList();
                    }
                }, 1000L);
            } else if ("switch".equals(privacyPolicyEvent.getValue())) {
                dismissLoading(1);
            } else {
                refreshList();
            }
        }
    }

    private void refreshList() {
        combineLocalAndOnlineList(this.mLanguageRemote, false);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        AuthDialogReceiver authDialogReceiver = this.authDialogReceiver;
        if (authDialogReceiver != null) {
            unregisterReceiver(authDialogReceiver);
        }
        EventBus.getDefault().unregister(this);
        RLog.i("wens", "OtaUpgradeActivity onDestroy-->");
        DownloadCallback downloadCallback = this.callback;
        if (downloadCallback != null) {
            downloadCallback.setActivity(null);
            AidlServiceManager.getInstance().unRegistDownLoadCallBack(this.callback);
            this.callback = null;
        }
        ConnectivityManager.NetworkCallback networkCallback = this.networkCallback;
        if (networkCallback != null) {
            NetUtils.unRegistNetCallBack(networkCallback);
            this.networkCallback = null;
        }
        LoadingDialog.Builder builder = this.mLoadingBuilder;
        if (builder != null) {
            builder.create().dismiss();
            this.mLoadingBuilder.create().cancel();
            this.mLoadingBuilder = null;
        }
        CommonDialog.Builder builder2 = this.mCommonBuilder;
        if (builder2 != null) {
            builder2.create().dismiss();
            this.mCommonBuilder.create().cancel();
            this.mCommonBuilder = null;
        }
        CommonDialog2.Builder builder3 = this.mCommon2Builder;
        if (builder3 != null) {
            builder3.create().dismiss();
            this.mCommon2Builder.create().cancel();
            this.mCommon2Builder = null;
        }
        CommonDialog2.Builder builder4 = this.mGuideBuilder;
        if (builder4 != null) {
            builder4.create().dismiss();
            this.mGuideBuilder.create().cancel();
            this.mGuideBuilder = null;
        }
        RedownloadDialog.Builder builder5 = this.mRedownloadBuilder;
        if (builder5 != null) {
            builder5.create().dismiss();
            this.mRedownloadBuilder.create().cancel();
            this.mRedownloadBuilder = null;
        }
        OneButtonDialog.Builder builder6 = this.mNoNetworkBuilder;
        if (builder6 != null) {
            builder6.create().dismiss();
            this.mNoNetworkBuilder.create().cancel();
            this.mNoNetworkBuilder = null;
        }
    }

    @Override // com.byd.vrsettings.BaseActivity
    protected void onConfigChangeUI(Configuration configuration) {
        if (configuration.orientation == 2) {
            setLandScape();
        } else if (configuration.orientation == 1) {
            setPortrait();
        }
        int i = configuration.uiMode & 48;
        if (i == 32) {
            setNight();
        } else if (i == 16) {
            setLight();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.imageview_back) {
            onBackPressed();
        } else if (view.getId() == R.id.textview_voice) {
            continuousClick();
        }
    }

    private boolean isFileValid(String str, String str2) {
        return FileUtils.checkMd5(new File(str), str2);
    }

    private void showOneButtonDialog(String str) {
        OneButtonDialog.Builder positiveClickLitener = new OneButtonDialog.Builder(this).setLight(!ModeUtils.isNightMode(this)).setContent(str).setPositiveClickLitener(getResources().getString(R.string.text_ok), new View.OnClickListener() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity$$ExternalSyntheticLambda17
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OtaUpgradeActivity.lambda$showOneButtonDialog$15(view);
            }
        });
        this.mNoNetworkBuilder = positiveClickLitener;
        positiveClickLitener.create().show();
    }

    private void continuousClick() {
        long[] jArr = this.mHits;
        System.arraycopy(jArr, 1, jArr, 0, jArr.length - 1);
        long[] jArr2 = this.mHits;
        jArr2[jArr2.length - 1] = SystemClock.uptimeMillis();
        LogUtils.e(this.TAG, "mHits:" + new Gson().toJson(this.mHits));
        if (!this.isOneClick) {
            this.mBinding.imageviewBack.postDelayed(new Runnable() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity.5
                @Override // java.lang.Runnable
                public void run() {
                    if (OtaUpgradeActivity.this.mHits[0] == 0) {
                        LogUtils.e(OtaUpgradeActivity.this.TAG, OtaUpgradeActivity.this.DURATION + "ms时间到");
                        OtaUpgradeActivity.this.mHits = new long[20];
                        OtaUpgradeActivity.this.isOneClick = false;
                    }
                }
            }, this.DURATION);
        }
        this.isOneClick = true;
        LogUtils.e(this.TAG, "点击。。");
        if (this.mHits[0] >= SystemClock.uptimeMillis() - ((long) this.DURATION)) {
            LogUtils.e(this.TAG, "跳转到暗门页");
            this.mHits = new long[20];
            this.isOneClick = false;
            Intent intent = new Intent();
            intent.setClass(this, DeletePkgActivity.class);
            startActivity(intent);
        }
    }

    private void showGuideToDownloadPkg() {
        if (OtaDataManager.isOtaShowGuide()) {
            CommonDialog2.Builder builder = this.mGuideBuilder;
            if (builder == null || !builder.create().isShowing()) {
                CommonDialog2.Builder negativeClickLitener = new CommonDialog2.Builder(this).setLight(!ModeUtils.isNightMode(this)).setContent(getResources().getString(R.string.text_no_pkg)).setPositiveClickLitener(getResources().getString(R.string.text_ok), new View.OnClickListener() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity$$ExternalSyntheticLambda3
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        OtaUpgradeActivity.lambda$showGuideToDownloadPkg$16(view);
                    }
                }).setTouchOutsizeDismiss(false).setNegativeClickLitener(getResources().getString(R.string.text_cancel), new View.OnClickListener() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity$$ExternalSyntheticLambda4
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.m18xbe604a2c(view);
                    }
                });
                this.mGuideBuilder = negativeClickLitener;
                negativeClickLitener.create(new OnBackPressInterface() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity.6
                    @Override // com.byd.vrsettings.interf.OnBackPressInterface
                    public void onBackPress() {
                        OtaUpgradeActivity.this.onBackPressed();
                    }
                }).show();
            }
        }
    }

    /* JADX INFO: renamed from: lambda$showGuideToDownloadPkg$17$com-byd-vrsettings-activity-OtaUpgradeActivity, reason: not valid java name */
    /* synthetic */ void m18xbe604a2c(View view) {
        onBackPressed();
    }

    @Override // com.byd.vrsettings.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (OtaDataManager.isOtaShowGuide()) {
            LogUtils.d(this.TAG, "return main page");
            setResult(100);
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDataList(final List<LanguageBean> list) {
        runOnUiThread(new Runnable() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity.7
            @Override // java.lang.Runnable
            public void run() {
                OtaUpgradeActivity.this.customAdapter.setList(list);
            }
        });
    }

    private String getOnlineSize(LanguageBean languageBean) {
        String latestPatchPkgSize = TextUtils.isEmpty(languageBean.getLanguageRemote().getLatestPatchPkgSize()) ? "0" : languageBean.getLanguageRemote().getLatestPatchPkgSize();
        String latestPkgSize = TextUtils.isEmpty(languageBean.getLanguageRemote().getLatestPkgSize()) ? "0" : languageBean.getLanguageRemote().getLatestPkgSize();
        if (!languageBean.getLanguageRemote().isDifferentialUpdate()) {
            latestPatchPkgSize = latestPkgSize;
        }
        return Tools.getDataSizeValue(latestPatchPkgSize);
    }

    private void initBroadCast() {
        this.authDialogReceiver = new AuthDialogReceiver();
        IntentFilter intentFilter = new IntentFilter();
        this.intentFilter = intentFilter;
        intentFilter.addAction("com.byd.voice.action.showguide");
        registerReceiver(this.authDialogReceiver, this.intentFilter);
    }

    class AuthDialogReceiver extends BroadcastReceiver {
        AuthDialogReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtils.d(OtaUpgradeActivity.this.TAG, "receive voice assistant not auth broadcast");
            OtaUpgradeActivity.this.onBackPressed();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void combineLocalAndOnlineList(List<LanguageUpdate> list, final boolean z) {
        if (z) {
            showLoading(2);
        }
        OtaDataManager.combineOberverLanguageData(list, new LanguageListResultCallBack() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity.8
            @Override // com.byd.vrsettings.interf.LanguageListResultCallBack
            public void onResult(List<LanguageBean> list2) {
                if (z) {
                    OtaUpgradeActivity.this.dismissLoading(2);
                }
                OtaUpgradeActivity.this.setDataList(list2);
            }
        });
    }

    private void showPrivacyView() {
        String string = Settings.System.getString(getContentResolver(), Constant.KEY_PRIVACY_POLICY);
        RLog.i(this.TAG, "ota upgrage onResume  ===========" + string);
        if (this.fv == null) {
            if (string == null || string.length() == 0 || string.equals("false")) {
                FirstPrivacyView firstPrivacyView = new FirstPrivacyView(this, new FirstPrivacyView.clickDismiss() { // from class: com.byd.vrsettings.activity.OtaUpgradeActivity.9
                    @Override // com.byd.vrsettings.view.FirstPrivacyView.clickDismiss
                    public void clickDismiss() {
                        RLog.i(OtaUpgradeActivity.this.TAG, "Dismiss");
                        OtaUpgradeActivity.this.fv = null;
                    }

                    @Override // com.byd.vrsettings.view.FirstPrivacyView.clickDismiss
                    public void notAllow() {
                        OtaUpgradeActivity.this.finish();
                        EventBus.getDefault().post("SystemLanguagechange");
                    }
                });
                this.fv = firstPrivacyView;
                firstPrivacyView.show();
            }
        }
    }
}
