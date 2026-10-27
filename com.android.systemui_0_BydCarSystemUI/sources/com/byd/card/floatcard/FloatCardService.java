package com.byd.card.floatcard;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.hardware.bydauto.properties.CarSettings;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.SystemProperties;
import android.os.UserManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.SystemUI;
import com.android.systemui.SystemUIApplication;
import com.android.systemui.stackdivider.Divider;
import com.android.systemui.stackdivider.DividerUtils;
import com.android.systemui.stackdivider.SplitScreenController;
import com.android.systemui.statusbar.wm.Util;
import com.byd.bluetoothaidl.listener.UIListener_Call;
import com.byd.bluetoothaidl.manage.HfpManage;
import com.byd.card.bean.AppOrShortcutItem;
import com.byd.card.bean.Constant;
import com.byd.card.bean.MessageEvent;
import com.byd.card.callback.INotifyListener;
import com.byd.card.callback.NotifyListenerManager;
import com.byd.card.drag.LruCacheBitmap;
import com.byd.card.floatcard.DragCardView;
import com.byd.card.floatcard.FloatCardContainer;
import com.byd.card.floatcard.TopActivityManager;
import com.byd.card.forbidden.ForbiddenManager;
import com.byd.card.utils.AppUtils;
import com.byd.card.utils.PinyinUtils;
import com.byd.card.utils.TransportModeManager;
import com.byd.card.view.activity.ActivityCollector;
import com.byd.card.view.activity.AddCardPopActivity;
import com.byd.card.view.broadcast.AppInstallBroadcastReceiver;
import com.byd.card.view.broadcast.AutoMapWidgetProvider;
import com.byd.card.view.broadcast.CardReceiver;
import com.byd.card.view.broadcast.HomeBroadcastReceiver;
import com.byd.card.widget.MediaMenuPopWindow;
import com.byd.card.widget.RollBenchMode;
import com.byd.card.widget.TransportMode;
import com.byd.info.BtCallInfo;
import com.byd.mediacenter.api.media.MediaControl;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.util.HandlerUtil;
import com.bydux.api.common.views.skin.loader.SkinManager;
import com.bydux.api.common.views.window.ToastUtils;
import com.google.common.base.Ascii;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class FloatCardService extends SystemUI implements TopActivityManager.TopActivityChangeListener, UIListener_Call {
    public static final String ACTION_REQUEST_FOR_MEDIA_INFO = "com.byd.ACTION_REQUEST_FOR_MEDIA_INFO";
    private static final String ACTIVATED = "activated";
    private static final int DELAY_UPDATE_CARD = 3000;
    private static final int DELAY_UPDATE_NAVI_CARD = 500;
    private static final int GEAR_R = 2;
    private static final String SCREENSAVER = "navibar_mode_on_screensaver";
    private static final int SCREEN_BLACK = 0;
    private static final int SCREEN_NORMAL = 1;
    private static final int SCREEN_PROTECT = 2;
    public static final String TAG = "FloatCardService";
    private FloatCardContainer cardContainer;
    private ContentResolver contentResolver;
    private HomeBroadcastReceiver homeBroadcastReceiver;
    private boolean isOpenAllAppCard;
    private Locale locale;
    private AppInstallBroadcastReceiver mAppInstallReceiver;
    private CardReceiver mCardReceiver;
    private ContentResolver mContentResolver;
    private Context mContext;
    private DragCardView mDragCardView;
    private SkinHandler mHandler;
    UserManager mUserManager;
    private MediaMenuPopWindow mediaMenuPopWindow;
    private int orientation;
    private TransportMode transportMode;
    private int uiMode;
    private boolean mFirstCall = true;
    private boolean mBlueCalling = false;
    private boolean isUiModeChange = false;
    private int update_navi_card_count = 0;
    private ContentObserver mScreenOffContentObserver = new ContentObserver(new Handler()) { // from class: com.byd.card.floatcard.FloatCardService.6
        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return super.deliverSelfNotifications();
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange, Uri uri) {
            super.onChange(selfChange, uri);
            if (uri.getLastPathSegment().equals("navibar_mode_on_screensaver")) {
                if (Settings.Global.getInt(FloatCardService.this.mContentResolver, "navibar_mode_on_screensaver", 0) == 1 || FloatCardService.this.transportMode == null) {
                    FloatCardService.this.controlTransportMode();
                } else {
                    FloatCardService.this.transportMode.hideView();
                }
            }
        }
    };
    private ContentObserver mContentObserver = new ContentObserver(new Handler()) { // from class: com.byd.card.floatcard.FloatCardService.7
        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return super.deliverSelfNotifications();
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange, Uri uri) {
            super.onChange(selfChange, uri);
            String str = SystemProperties.get(BYDAutoSysPropertiesDevice.BOOT_BUSINESS);
            Log.w(FloatCardService.TAG, "ContentObserver onChange:" + str + ", lock = " + FloatCardService.this.mUserManager.isUserUnlocked());
            if (FloatCardService.ACTIVATED.equals(str) && FloatCardService.this.mUserManager.isUserUnlocked()) {
                if (FloatCardService.this.cardContainer == null) {
                    FloatCardService.this.addCardWindow();
                }
                FloatCardService.this.cardContainer.animShow();
            }
        }
    };

    @Override // com.android.systemui.SystemUI
    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
    }

    private void handleHome() {
        Message messageObtain = Message.obtain();
        messageObtain.what = 4;
        this.mHandler.sendMessageDelayed(messageObtain, 200L);
        MediaMenuPopWindow mediaMenuPopWindow = this.mediaMenuPopWindow;
        if (mediaMenuPopWindow != null) {
            mediaMenuPopWindow.dismiss();
        }
        DragCardView dragCardView = this.mDragCardView;
        if (dragCardView != null) {
            dragCardView.setAddBtnStatus(true);
            this.mDragCardView.setDeleteViewVisibility(false);
        }
        closeAddCardActivity();
    }

    private void closeAddCardActivity() {
        AddCardPopActivity addCardPopActivity = (AddCardPopActivity) ActivityCollector.getActivity(AddCardPopActivity.class);
        if (addCardPopActivity != null) {
            addCardPopActivity.finish();
        }
    }

    private void handleUnLock() {
        Log.w(TAG, " handleUnLock");
        initView();
        DBUtils.getInstance().initDatabaseAppInfo(this.mContext);
    }

    @Override // com.android.systemui.SystemUI
    public void start() {
        this.mContext = SystemUIApplication.getInstance().getApplicationContext();
        HfpManage.getInstance().init(this.mContext.getApplicationContext(), "com.byd.bluetoothcall");
        PinyinUtils.initPinyin();
        this.mUserManager = (UserManager) this.mContext.getSystemService("user");
        NotifyListenerManager.getInstance().registerListener(new INotifyListener() { // from class: com.byd.card.floatcard.FloatCardService.1
            @Override // com.byd.card.callback.INotifyListener
            public void notifyContext(List<AppOrShortcutItem> list) {
                if (FloatCardService.this.mDragCardView != null) {
                    FloatCardService.this.mDragCardView.addCardApp(list);
                }
                FloatCardService.this.showCardView();
            }
        }, "add_card");
        EventBus.getDefault().register(this);
        TopActivityManager.getInstance(this.mContext).registerTopActivityChangeListener(this);
        this.mContentResolver = this.mContext.getContentResolver();
        this.uiMode = this.mContext.getResources().getConfiguration().uiMode;
        this.locale = this.mContext.getResources().getConfiguration().locale;
        this.orientation = this.mContext.getResources().getConfiguration().orientation;
        this.mHandler = new SkinHandler();
        Log.i(TAG, "start orientation = " + this.orientation);
        ForbiddenManager.getInstance().init();
    }

    @Override // com.android.systemui.SystemUI
    protected void onBootCompleted() {
        registerContentObserver();
        registerCardBroadcast();
        registerAppChangedBroadcast();
        registerOpenListener();
        registerHomeBroadcast();
        HfpManage.getInstance().registerUIListener_Call(this);
        TransportModeManager.getInstance().registerTransportModeListener(this.mContext, new TransportModeManager.OnTransportModeChangeListener() { // from class: com.byd.card.floatcard.FloatCardService.2
            @Override // com.byd.card.utils.TransportModeManager.OnTransportModeChangeListener
            public void onTransportModeChanged(int state) {
                Log.i(FloatCardService.TAG, "onTransportModeChanged state = " + state);
                if (1 == state && BYDAutoStatisticDevice.getInstance(FloatCardService.this.mContext).getTotalMileageValue() < 60.0f) {
                    FloatCardService.this.controlTransportMode();
                } else {
                    HandlerUtil.runOnUiThread(new Runnable() { // from class: com.byd.card.floatcard.FloatCardService.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (FloatCardService.this.transportMode != null) {
                                FloatCardService.this.transportMode.hideView();
                            }
                        }
                    });
                }
            }
        });
        initTransportModeView();
        boolean zIsUserUnlocked = this.mUserManager.isUserUnlocked();
        Log.w(TAG, "onBootCompleted start Unlocked = " + zIsUserUnlocked);
        if (zIsUserUnlocked) {
            initView();
            DBUtils.getInstance().initDatabaseAppInfo(this.mContext);
        } else {
            this.mContext.registerReceiver(this.homeBroadcastReceiver, new IntentFilter("android.intent.action.USER_UNLOCKED"));
        }
    }

    private void initTransportModeView() {
        TransportMode transportMode = new TransportMode(this.mContext);
        this.transportMode = transportMode;
        transportMode.addView();
        if (1 != TransportModeManager.getInstance().getTransportMode(this.mContext)) {
            this.transportMode.hideView();
        }
    }

    @Override // com.android.systemui.SystemUI
    public void onConfigurationChanged(Configuration newConfig) {
        MediaMenuPopWindow mediaMenuPopWindow = this.mediaMenuPopWindow;
        if (mediaMenuPopWindow != null) {
            mediaMenuPopWindow.dismiss();
            this.mediaMenuPopWindow = null;
        }
        Log.i(TAG, "onConfigurationChanged locale = " + newConfig.locale + ", locale = " + this.locale);
        if (!newConfig.locale.equals(this.locale)) {
            DBUtils.getInstance().updateAppName(this.mContext);
        }
        if (newConfig.uiMode != this.uiMode) {
            TransportMode transportMode = this.transportMode;
            if (transportMode == null) {
                this.transportMode = new TransportMode(this.mContext);
            } else {
                transportMode.onConfigurationChanged(newConfig);
            }
            RollBenchMode.getInstance(this.mContext).onConfigurationChanged(newConfig);
            if (this.cardContainer != null) {
                if (!AppUtils.isLandSpace(this.mContext)) {
                    this.cardContainer.updateLayout(-1, this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_160));
                } else {
                    this.cardContainer.updateLayout(-1, this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_125));
                }
            }
            this.uiMode = newConfig.uiMode;
            this.isUiModeChange = true;
            this.mHandler.sendEmptyMessage(3);
        }
        if (newConfig.locale.equals(this.locale) && newConfig.orientation == this.orientation) {
            return;
        }
        TransportMode transportMode2 = this.transportMode;
        if (transportMode2 == null) {
            this.transportMode = new TransportMode(this.mContext);
        } else if (transportMode2.isShow() || !newConfig.locale.equals(this.locale)) {
            this.transportMode.onConfigurationChanged(newConfig);
        }
        RollBenchMode.getInstance(this.mContext).onConfigurationChanged(newConfig);
        if (this.cardContainer != null) {
            if (!AppUtils.isLandSpace(this.mContext)) {
                this.cardContainer.updateLayout(-1, this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_160));
            } else {
                this.cardContainer.updateLayout(-1, this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_125));
            }
        }
        DragCardView dragCardView = this.mDragCardView;
        if (dragCardView != null) {
            dragCardView.onConfigurationChanged(newConfig);
        }
        this.mHandler.sendEmptyMessage(1);
        this.locale = newConfig.locale;
        this.orientation = newConfig.orientation;
    }

    private void unregisterBroadCastReceiver() {
        this.mContext.unregisterReceiver(this.homeBroadcastReceiver);
        this.mContext.unregisterReceiver(this.mCardReceiver);
        this.mContext.unregisterReceiver(this.mAppInstallReceiver);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        Log.i(TAG, "onMessageEvent tag = " + event.tag + ", cardContainer = " + this.cardContainer);
        if (TextUtils.isEmpty(event.tag)) {
        }
        String str = event.tag;
        str.hashCode();
        byte b = -1;
        switch (str.hashCode()) {
            case -2110177520:
                if (str.equals(Constant.EventBusMessage.HIDE_FLOAT_WINDOW)) {
                    b = 0;
                }
                break;
            case -1859032493:
                if (str.equals(Constant.EventBusMessage.HIDE_CARD_NO_ANIMATOR)) {
                    b = 1;
                }
                break;
            case -1805356995:
                if (str.equals(Constant.EventBusMessage.REMOVE_SHORTCUTS_CARD)) {
                    b = 2;
                }
                break;
            case -1709891079:
                if (str.equals(Constant.EventBusMessage.SHOW_ADD_CARD_EDIT)) {
                    b = 3;
                }
                break;
            case -1585919400:
                if (str.equals(Constant.EventBusMessage.ACTION_CHANGE_SKIN_SUCCESS)) {
                    b = 4;
                }
                break;
            case -1474603439:
                if (str.equals(Constant.EventBusMessage.SELECT_APP_CARD)) {
                    b = 5;
                }
                break;
            case -1381420780:
                if (str.equals(Constant.EventBusMessage.HIDE_MEDIA_POPUP)) {
                    b = 6;
                }
                break;
            case -1236010876:
                if (str.equals(Constant.EventBusMessage.ADD_NAVI)) {
                    b = 7;
                }
                break;
            case -802400863:
                if (str.equals(Constant.EventBusMessage.CHECK_CARD_FORBIDDEN)) {
                    b = 8;
                }
                break;
            case -705058507:
                if (str.equals(Constant.EventBusMessage.SHOW_FLOAT_WINDOW)) {
                    b = 9;
                }
                break;
            case -485868801:
                if (str.equals(Constant.EventBusMessage.HOME_KEY)) {
                    b = 10;
                }
                break;
            case -293736831:
                if (str.equals(Constant.EventBusMessage.REMOVE_WIDGET_CARD)) {
                    b = Ascii.VT;
                }
                break;
            case -179908303:
                if (str.equals(Constant.EventBusMessage.SPLIT_SCREEN)) {
                    b = Ascii.FF;
                }
                break;
            case -160398121:
                if (str.equals(Constant.EventBusMessage.USER_UNLOCKED)) {
                    b = Ascii.CR;
                }
                break;
            case 181936858:
                if (str.equals(Constant.EventBusMessage.BACK_KEY_DOWN)) {
                    b = Ascii.SO;
                }
                break;
            case 593643964:
                if (str.equals(Constant.EventBusMessage.HIDE_ADD_CARD_DIALOG)) {
                    b = Ascii.SI;
                }
                break;
            case 914679749:
                if (str.equals(Constant.EventBusMessage.CPAA_DIALOG_DISMISS)) {
                    b = Ascii.DLE;
                }
                break;
            case 1365472201:
                if (str.equals(Constant.EventBusMessage.REMOVE_APP_CARD)) {
                    b = 17;
                }
                break;
            case 1716890309:
                if (str.equals(Constant.EventBusMessage.SPLIT_SCREEN_NOT)) {
                    b = Ascii.DC2;
                }
                break;
            case 1892631735:
                if (str.equals(Constant.EventBusMessage.CLICK_UI_MODE)) {
                    b = 19;
                }
                break;
            case 1976059774:
                if (str.equals(Constant.EventBusMessage.HIDE_ADD_CARD_EDIT)) {
                    b = Ascii.DC4;
                }
                break;
            case 2127588943:
                if (str.equals(Constant.EventBusMessage.SHOW_MEDIA_POPUP)) {
                    b = Ascii.NAK;
                }
                break;
        }
        switch (b) {
            case 0:
                FloatCardContainer floatCardContainer = this.cardContainer;
                if (floatCardContainer != null) {
                    floatCardContainer.animHide();
                }
                break;
            case 1:
                FloatCardContainer floatCardContainer2 = this.cardContainer;
                if (floatCardContainer2 != null) {
                    floatCardContainer2.hide();
                }
                break;
            case 2:
                DragCardView dragCardView = this.mDragCardView;
                if (dragCardView != null) {
                    dragCardView.removeCardShortcuts(event.pkg);
                }
                break;
            case 3:
                this.isOpenAllAppCard = false;
                DragCardView dragCardView2 = this.mDragCardView;
                if (dragCardView2 != null) {
                    dragCardView2.setAddBtnStatus(true);
                }
                break;
            case 4:
                LruCacheBitmap.getInstance().clearCache();
                if (this.isUiModeChange) {
                    this.mHandler.sendEmptyMessageDelayed(3, 0L);
                } else {
                    this.mHandler.sendEmptyMessageDelayed(2, 0L);
                }
                this.isUiModeChange = false;
                MediaMenuPopWindow mediaMenuPopWindow = this.mediaMenuPopWindow;
                if (mediaMenuPopWindow != null) {
                    mediaMenuPopWindow.dismiss();
                    this.mediaMenuPopWindow = null;
                }
                if (AppUtils.isInstalled(SystemUIApplication.getContext(), Constant.PackageName.AUTO_MAP_PKG) || AppUtils.isInstalled(SystemUIApplication.getContext(), Constant.PackageName.ZNAV_MAP_PKG) || AppUtils.isInstalled(SystemUIApplication.getContext(), Constant.PackageName.T_MAP_PKG)) {
                    Intent intent = new Intent(AutoMapWidgetProvider.UPDATE_ACTION);
                    intent.setComponent(new ComponentName(this.mContext.getPackageName(), AutoMapWidgetProvider.class.getName()));
                    this.mContext.sendBroadcast(intent);
                }
                break;
            case 5:
                DragCardView dragCardView3 = this.mDragCardView;
                if (dragCardView3 != null) {
                    dragCardView3.addCardApp(event.popAddCardDataItem);
                }
                break;
            case 6:
                MediaMenuPopWindow mediaMenuPopWindow2 = this.mediaMenuPopWindow;
                if (mediaMenuPopWindow2 != null) {
                    mediaMenuPopWindow2.dismiss();
                    this.mediaMenuPopWindow = null;
                }
                break;
            case 7:
                Message messageObtain = Message.obtain();
                messageObtain.what = 6;
                messageObtain.obj = event.pkg;
                this.mHandler.sendMessage(messageObtain);
                updateSplitMapPackage(event.pkg);
                break;
            case 8:
                Log.d(TAG, "CHECK_CARD_FORBIDDEN mDragCardView = " + this.mDragCardView);
                DragCardView dragCardView4 = this.mDragCardView;
                if (dragCardView4 != null) {
                    dragCardView4.updateForbidden(ForbiddenManager.isPGear());
                }
                break;
            case 9:
            case 16:
                dealCard(this.mContext, true);
                break;
            case 10:
                handleHome();
                break;
            case 11:
                Message messageObtain2 = Message.obtain();
                messageObtain2.what = 7;
                messageObtain2.obj = event.pkg;
                this.mHandler.sendMessage(messageObtain2);
                break;
            case 12:
                FloatCardContainer floatCardContainer3 = this.cardContainer;
                if (floatCardContainer3 != null) {
                    floatCardContainer3.animHide();
                }
                TransportMode transportMode = this.transportMode;
                if (transportMode != null) {
                    transportMode.hideView();
                }
                break;
            case 13:
                handleUnLock();
                break;
            case 14:
            case 15:
            case 19:
                DragCardView dragCardView5 = this.mDragCardView;
                if (dragCardView5 != null) {
                    dragCardView5.setDeleteViewVisibility(false);
                }
                break;
            case 17:
                DragCardView dragCardView6 = this.mDragCardView;
                if (dragCardView6 != null) {
                    dragCardView6.removeCardApp(event.pkg);
                }
                break;
            case 18:
                dealQuitSplitScreenCard(this.mContext);
                break;
            case 20:
                this.isOpenAllAppCard = true;
                DragCardView dragCardView7 = this.mDragCardView;
                if (dragCardView7 != null) {
                    dragCardView7.setAddBtnStatus(false);
                }
                break;
            case 21:
                if (this.mediaMenuPopWindow == null) {
                    this.mediaMenuPopWindow = new MediaMenuPopWindow(this.mContext);
                }
                this.mediaMenuPopWindow.initCheckedItem(this.mDragCardView.getMediaCard());
                break;
        }
    }

    private void updateSplitMapPackage(String pkg) {
        Log.i(TAG, "updateSplitMapPackage pkg = " + pkg);
        if (TextUtils.isEmpty(pkg)) {
        }
        pkg.hashCode();
        switch (pkg) {
            case "com.byd.automap":
            case "net.zmap.znav.byd8155":
            case "com.neusoft.na.navigation":
            case "com.huawei.maps.auto.app":
            case "com.tmap.auto.byd":
                ((SplitScreenController) Dependency.get(SplitScreenController.class)).setNavPackage(pkg);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addCardWindow() {
        Log.w(TAG, "addCardWindow start!");
        DBUtils.getInstance().checkClearCardData();
        DragCardView dragCardView = new DragCardView(this.mContext);
        this.mDragCardView = dragCardView;
        dragCardView.setSystemUiVisibility(512);
        this.mDragCardView.setOnAddClickListener(new DragCardView.OnAddClickListener() { // from class: com.byd.card.floatcard.FloatCardService.3
            @Override // com.byd.card.floatcard.DragCardView.OnAddClickListener
            public void onAddClick(View view) {
                if (!((Divider) SysUiServiceProvider.getComponent(FloatCardService.this.mContext, Divider.class)).isSplitActive()) {
                    if (!FloatCardService.this.mBlueCalling) {
                        if (FloatCardService.this.transportMode != null) {
                            FloatCardService.this.transportMode.hideView();
                        }
                        Intent intent = new Intent(FloatCardService.this.mContext, (Class<?>) AddCardPopActivity.class);
                        intent.addFlags(268435456);
                        FloatCardService.this.mContext.startActivity(intent);
                        FloatCardService.this.isOpenAllAppCard = true;
                        if (FloatCardService.this.mDragCardView != null) {
                            FloatCardService.this.mDragCardView.setDeleteViewVisibility(true);
                            return;
                        }
                        return;
                    }
                    Log.i(FloatCardService.TAG, "blue is calling");
                    ToastUtils.show(FloatCardService.this.mContext, FloatCardService.this.mContext.getString(R.string.forbid_add_tip));
                    return;
                }
                Log.w(FloatCardService.TAG, "onAddClick is SplitActive");
            }
        });
        FloatCardContainer floatCardContainer = new FloatCardContainer(this.mContext);
        this.cardContainer = floatCardContainer;
        floatCardContainer.setOnOutsideListener(new FloatCardContainer.OnOutsideListener() { // from class: com.byd.card.floatcard.FloatCardService.4
            @Override // com.byd.card.floatcard.FloatCardContainer.OnOutsideListener
            public void onOutside(boolean out) {
                if (FloatCardService.this.isOpenAllAppCard) {
                    return;
                }
                FloatCardService.this.mDragCardView.setDeleteViewVisibility(false);
            }
        });
        this.cardContainer.setView(this.mDragCardView);
        this.cardContainer.setGravity(80, 0, this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_16) + Util.getNavigationBarHeight(this.mContext));
        if (AppUtils.isLandSpace(this.mContext)) {
            this.cardContainer.setSize(-1, this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_125));
        } else {
            this.cardContainer.setSize(-1, this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_160));
        }
        handlerInitCard(this.cardContainer);
        this.cardContainer.init();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCardView() {
        dealCard(this.mContext, false);
    }

    private void initView() {
        try {
            int i = Settings.Secure.getInt(this.mContentResolver, "user_setup_complete");
            Log.w(TAG, "initView user_setup_complete = " + i);
            if (i == 1) {
                addCardWindow();
                dealCard(this.mContext, false);
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            Log.w(TAG, "SettingNotFoundException = " + e.getMessage());
        }
    }

    private void registerCardBroadcast() {
        this.mCardReceiver = new CardReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CardReceiver.OPEN_CARD);
        intentFilter.addAction(CardReceiver.CLOSE_CARD);
        intentFilter.addAction(CardReceiver.SHOW_MEDIA_POPUP);
        intentFilter.addAction(CardReceiver.HIDE_MEDIA_POPUP);
        intentFilter.addAction("com.byd.action.card.more.app");
        intentFilter.addAction(CardReceiver.CPAA_DIALOG_DISMISS);
        intentFilter.addAction(CardReceiver.CPAA_START_ACTIVITY);
        intentFilter.addAction("SplitScreenStatus.BroadcastReceiver");
        intentFilter.addAction("com.byd.carsettings.wifi.dialog.state.change");
        intentFilter.addAction("com.byd.carsettings.bt.dialog.state.change");
        intentFilter.addAction("com.byd.carsettings.hotspot.dialog.state.change");
        intentFilter.addAction(CardReceiver.SYS_CONNECT_DEVICE_DIALOG_CHANGE_ACTION);
        intentFilter.addAction(CardReceiver.CAR_SETTINGS_NOT_MAIN_ACTIVITY_ACTION);
        intentFilter.addAction(CardReceiver.CLEAR_APP_DATA);
        this.mContext.registerReceiver(this.mCardReceiver, intentFilter);
        this.mCardReceiver.setAllAppClickListener(new CardReceiver.AllAppClickListener() { // from class: com.byd.card.floatcard.FloatCardService.5
            @Override // com.byd.card.view.broadcast.CardReceiver.AllAppClickListener
            public void openAllAppActivity() {
                if (FloatCardService.this.cardContainer != null) {
                    FloatCardService.this.cardContainer.hide();
                }
                if (FloatCardService.this.transportMode != null) {
                    FloatCardService.this.transportMode.hideView();
                }
            }
        });
    }

    private void registerAppChangedBroadcast() {
        this.mAppInstallReceiver = new AppInstallBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addDataScheme(MediaControl.PARAM_PACKAGE);
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        this.mContext.registerReceiver(this.mAppInstallReceiver, intentFilter);
    }

    private void registerHomeBroadcast() {
        this.homeBroadcastReceiver = new HomeBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(HomeBroadcastReceiver.CLOSE_SYSTEM);
        this.mContext.registerReceiver(this.homeBroadcastReceiver, intentFilter);
    }

    @Override // com.byd.card.floatcard.TopActivityManager.TopActivityChangeListener
    public void onTopActivityChanged(ComponentName componentName, boolean processDied) {
        Log.i(TAG, "onTopActivityChanged = " + componentName);
        if (componentName == null) {
            return;
        }
        ForbiddenManager.getInstance().onTopActivityChanged();
        MediaMenuPopWindow mediaMenuPopWindow = this.mediaMenuPopWindow;
        if (mediaMenuPopWindow != null && !processDied) {
            mediaMenuPopWindow.dismiss();
        }
        if (!Constant.TopActivity.ADD_CARD_ACTIVITY.equals(componentName.getClassName())) {
            closeAddCardActivity();
            DragCardView dragCardView = this.mDragCardView;
            if (dragCardView != null) {
                dragCardView.setDeleteShow(false);
            }
        }
        String packageName = componentName.getPackageName();
        controlTransportMode();
        if (packageName != null) {
            packageName.hashCode();
            switch (packageName) {
            }
            return;
        }
        Log.i(TAG, "onTopActivityChanged cardContainer = " + this.cardContainer);
        if ("com.byd.launcherwallpaper".equals(componentName.getClassName()) || "com.byd.bluetoothcall.view.activity.MainActivity".equals(componentName.getClassName()) || Constant.TopActivity.LAUNCHER3.equals(componentName.getClassName()) || Constant.TopActivity.ADD_CARD_ACTIVITY.equals(componentName.getClassName())) {
            boolean zIsSplitActive = ((Divider) SysUiServiceProvider.getComponent(this.mContext, Divider.class)).isSplitActive();
            Log.i(TAG, "onTopActivityChanged isSplitActive = " + zIsSplitActive);
            FloatCardContainer floatCardContainer = this.cardContainer;
            if (floatCardContainer == null || zIsSplitActive) {
                return;
            }
            floatCardContainer.animShow();
            return;
        }
        if (Constant.TopActivity.VR_UPDATE_INFO_ACTIVITY.equals(componentName.getClassName())) {
            return;
        }
        FloatCardContainer floatCardContainer2 = this.cardContainer;
        if (floatCardContainer2 != null) {
            floatCardContainer2.animHide();
        }
        DragCardView dragCardView2 = this.mDragCardView;
        if (dragCardView2 != null) {
            dragCardView2.setDeleteShow(false);
        }
    }

    private void registerOpenListener() {
        Uri uriFor = Settings.Secure.getUriFor("user_setup_complete");
        this.mContentResolver.registerContentObserver(uriFor, true, this.mContentObserver);
        this.mContentResolver.registerContentObserver(Settings.Global.getUriFor("navibar_mode_on_screensaver"), false, this.mScreenOffContentObserver);
        Log.w(TAG, "registerOpenListener uri = " + uriFor);
    }

    private void unregisterOpenListener() {
        this.mContentResolver.unregisterContentObserver(this.mContentObserver);
    }

    private void dealCard(Context context, final boolean broadcast) {
        TopActivityManager.getInstance(context).getTopActivity(new TopActivityManager.TopActivityCallback() { // from class: com.byd.card.floatcard.FloatCardService.8
            @Override // com.byd.card.floatcard.TopActivityManager.TopActivityCallback
            public void onTopActivityCallback(ComponentName componentName) {
                if (componentName != null) {
                    boolean zIsSplitActive = ((Divider) SysUiServiceProvider.getComponent(FloatCardService.this.mContext, Divider.class)).isSplitActive();
                    Log.i(FloatCardService.TAG, "dealCard componentName = " + componentName.getPackageName() + ", isSplitActive = " + zIsSplitActive + ", cardContainer = " + FloatCardService.this.cardContainer);
                    if ("com.byd.bluetoothcall".equals(componentName.getPackageName()) || "com.neusoft.na.navigation".equals(componentName.getPackageName()) || "com.huawei.maps.auto.app".equals(componentName.getPackageName()) || "com.byd.localmusic".equals(componentName.getPackageName()) || "com.byd.mycar".equals(componentName.getPackageName()) || Constant.PackageName.MY_CAR_PKG_TS.equals(componentName.getPackageName())) {
                        boolean cardStatus = FloatCardService.this.mCardReceiver.getCardStatus(componentName.getPackageName());
                        Log.i(FloatCardService.TAG, "show = " + cardStatus + ", broadcast = " + broadcast + ", cardContainer = " + FloatCardService.this.cardContainer);
                        if (broadcast) {
                            if (!zIsSplitActive && cardStatus && FloatCardService.this.cardContainer != null) {
                                FloatCardService.this.cardContainer.animShow();
                            }
                        } else if (!zIsSplitActive && FloatCardService.this.cardContainer != null) {
                            FloatCardService.this.cardContainer.animShow();
                        }
                    } else if (("com.android.launcher3".equals(componentName.getPackageName()) || "com.byd.launcherwallpaper".equals(componentName.getPackageName()) || Constant.TopActivity.VR_UPDATE_INFO_ACTIVITY.equals(componentName.getClassName()) || Constant.TopActivity.ADD_CARD_ACTIVITY.equals(componentName.getClassName())) && !zIsSplitActive && FloatCardService.this.cardContainer != null) {
                        FloatCardService.this.cardContainer.animShow();
                    }
                    if ("com.neusoft.na.navigation".equals(componentName.getPackageName()) || "com.huawei.maps.auto.app".equals(componentName.getPackageName()) || "com.android.launcher3".equals(componentName.getPackageName()) || "com.byd.launcherwallpaper".equals(componentName.getPackageName())) {
                        if (1 != TransportModeManager.getInstance().getTransportMode(FloatCardService.this.mContext) || BYDAutoStatisticDevice.getInstance(FloatCardService.this.mContext).getTotalMileageValue() >= 60.0f) {
                            if (FloatCardService.this.transportMode != null) {
                                FloatCardService.this.transportMode.hideView();
                                return;
                            }
                            return;
                        } else {
                            if (FloatCardService.this.transportMode != null) {
                                FloatCardService.this.transportMode.showView();
                                return;
                            }
                            return;
                        }
                    }
                    return;
                }
                Log.w(FloatCardService.TAG, "dealCard componentName is null");
            }
        });
    }

    private void dealQuitSplitScreenCard(Context context) {
        TopActivityManager.getInstance(context).getTopActivity(new TopActivityManager.TopActivityCallback() { // from class: com.byd.card.floatcard.FloatCardService.9
            @Override // com.byd.card.floatcard.TopActivityManager.TopActivityCallback
            public void onTopActivityCallback(ComponentName componentName) {
                boolean cardStatus;
                if (componentName != null) {
                    cardStatus = FloatCardService.this.mCardReceiver.getCardStatus(componentName.getPackageName());
                    Log.i(FloatCardService.TAG, "dealQuitSplitScreenCard componentName = " + componentName.getPackageName() + ", show = " + cardStatus + ", cardContainer = " + FloatCardService.this.cardContainer);
                    String packageName = componentName.getPackageName();
                    packageName.hashCode();
                    switch (packageName) {
                        case "com.byd.mycar.ts":
                        case "com.byd.mycar":
                        case "com.neusoft.na.navigation":
                        case "com.huawei.maps.auto.app":
                        case "com.byd.bluetoothcall":
                        case "com.byd.localmusic":
                            if (cardStatus && FloatCardService.this.cardContainer != null) {
                                FloatCardService.this.cardContainer.animShow();
                                break;
                            }
                            break;
                        case "com.android.launcher3":
                        case "com.byd.launcherwallpaper":
                            if (FloatCardService.this.cardContainer != null) {
                                FloatCardService.this.cardContainer.animShow();
                                break;
                            }
                            break;
                        default:
                            if (FloatCardService.this.cardContainer != null) {
                                FloatCardService.this.cardContainer.animHide();
                                break;
                            }
                            break;
                    }
                    if ("com.neusoft.na.navigation".equals(componentName.getPackageName()) || "com.huawei.maps.auto.app".equals(componentName.getPackageName()) || "com.android.launcher3".equals(componentName.getPackageName()) || "com.byd.launcherwallpaper".equals(componentName.getPackageName())) {
                        if (1 != TransportModeManager.getInstance().getTransportMode(FloatCardService.this.mContext) || BYDAutoStatisticDevice.getInstance(FloatCardService.this.mContext).getTotalMileageValue() >= 60.0f) {
                            if (FloatCardService.this.transportMode != null) {
                                FloatCardService.this.transportMode.hideView();
                                return;
                            }
                            return;
                        } else {
                            if (FloatCardService.this.transportMode != null) {
                                FloatCardService.this.transportMode.showView();
                                return;
                            }
                            return;
                        }
                    }
                    return;
                }
                Log.w(FloatCardService.TAG, "dealQuitSplitScreenCard componentName is null");
            }
        });
    }

    private void forbidCardMove(final boolean isLock) {
        Executors.MAIN_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.FloatCardService.10
            @Override // java.lang.Runnable
            public void run() {
                if (FloatCardService.this.mDragCardView != null) {
                    FloatCardService.this.mDragCardView.setCanScrollHorizontally(isLock);
                }
            }
        });
    }

    private void setLongClickable(final boolean clickable) {
        Executors.MAIN_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.FloatCardService.11
            @Override // java.lang.Runnable
            public void run() {
                if (FloatCardService.this.mDragCardView != null) {
                    FloatCardService.this.mDragCardView.setLongClickable(clickable);
                }
            }
        });
    }

    private void moveBluetoothCardFront() {
        DragCardView dragCardView = this.mDragCardView;
        if (dragCardView != null) {
            dragCardView.moveBluetoothCardFront();
        }
    }

    private void moveBluetoothCardBack() {
        DragCardView dragCardView = this.mDragCardView;
        if (dragCardView != null) {
            dragCardView.moveBluetoothCardBack();
        }
    }

    @Override // com.byd.bluetoothaidl.listener.UIListener_Call
    public void CallState_Change(List<BtCallInfo> list) {
        Log.i("DragCardView", "CallState_Change isEmpty = " + list.isEmpty() + ", mFirstCall = " + this.mFirstCall + ", mBlueCalling = " + this.mBlueCalling);
        if (list.isEmpty()) {
            moveBluetoothCardBack();
            setLongClickable(true);
            this.mFirstCall = true;
            this.mBlueCalling = false;
            return;
        }
        if (this.mFirstCall) {
            moveBluetoothCardFront();
            setLongClickable(false);
            this.mBlueCalling = true;
            this.mFirstCall = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void controlTransportMode() {
        TopActivityManager.getInstance(this.mContext).getTopActivity(new TopActivityManager.TopActivityCallback() { // from class: com.byd.card.floatcard.FloatCardService.12
            @Override // com.byd.card.floatcard.TopActivityManager.TopActivityCallback
            public void onTopActivityCallback(ComponentName componentName) {
                if (componentName == null) {
                    Log.w(FloatCardService.TAG, "controlTransportMode componentName is null");
                    return;
                }
                if ("com.byd.launcherwallpaper".equals(componentName.getClassName()) || Constant.TopActivity.LAUNCHER3.equals(componentName.getClassName()) || "com.neusoft.na.navigation".equals(componentName.getPackageName()) || "com.huawei.maps.auto.app".equals(componentName.getPackageName())) {
                    boolean zIsSplitActive = ((Divider) SysUiServiceProvider.getComponent(FloatCardService.this.mContext, Divider.class)).isSplitActive();
                    if (FloatCardService.this.transportMode == null || zIsSplitActive || TransportModeManager.getInstance().getTransportMode(FloatCardService.this.mContext) != 1) {
                        if (FloatCardService.this.transportMode != null) {
                            FloatCardService.this.transportMode.hideView();
                            return;
                        }
                        return;
                    }
                    FloatCardService.this.transportMode.showView();
                    return;
                }
                if (FloatCardService.this.transportMode != null) {
                    FloatCardService.this.transportMode.hideView();
                }
            }
        });
    }

    private void registerContentObserver() {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        this.contentResolver = contentResolver;
        contentResolver.registerContentObserver(CarSettings.UserTableData.CONTENT_URI, true, new SettingsObserver(null));
    }

    private final class SettingsObserver extends ContentObserver {
        public SettingsObserver(Handler handler) {
            super(handler);
        }

        public void onChange(boolean selfChange, Uri uri, String key, int operationFlag) {
            super.onChange(selfChange, uri, key, operationFlag);
            BydLogHelper.logE(FloatCardService.TAG, "ban onChange key / " + key + ", operationFlag / " + operationFlag);
            if (CarSettings.UserTableData.BYD_APP_THEME.equals(key) && operationFlag == 3) {
                FloatCardService.this.setThemeResource();
                FloatCardService.this.mHandler.removeMessages(5);
                FloatCardService.this.mHandler.sendEmptyMessage(5);
            }
        }
    }

    private void sendBroadcast(String path, int mode) {
        Intent intent = new Intent(SkinManager.ACTION_BYDUI_SKIN_CHANGED);
        intent.putExtra(SkinManager.FIELD_DAY_NIGHT_MODE, mode);
        intent.putExtra("bydux_skin_path", path);
        this.mContext.sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setThemeResource() {
        int i = CarSettings.UserTableData.getInt(this.mContext.getContentResolver(), CarSettings.UserTableData.BYD_APP_THEME);
        BydLogHelper.logI(TAG, "ban onChange BYD_APP_THEME uuid / " + i);
        if (i == 100000010 || i == 0) {
            sendBroadcast("", 1);
            Settings.System.putString(this.mContext.getContentResolver(), "bydux_skin_path", "");
            return;
        }
        if (i == 100000011) {
            sendBroadcast(Constant.SkinPath.SKIN_PATH_1, 2);
            Settings.System.putString(this.mContext.getContentResolver(), "bydux_skin_path", Constant.SkinPath.SKIN_PATH_1);
            return;
        }
        if (i == 100000012) {
            sendBroadcast(Constant.SkinPath.SKIN_PATH_2, 2);
            Settings.System.putString(this.mContext.getContentResolver(), "bydux_skin_path", Constant.SkinPath.SKIN_PATH_2);
            return;
        }
        if (i == 100000013) {
            sendBroadcast(Constant.SkinPath.SKIN_PATH_3, 2);
            Settings.System.putString(this.mContext.getContentResolver(), "bydux_skin_path", Constant.SkinPath.SKIN_PATH_3);
        } else if (i == 100000014) {
            sendBroadcast(Constant.SkinPath.SKIN_PATH_4, 2);
            Settings.System.putString(this.mContext.getContentResolver(), "bydux_skin_path", Constant.SkinPath.SKIN_PATH_4);
        } else if (i == 100000015) {
            sendBroadcast(Constant.SkinPath.SKIN_PATH_5, 2);
            Settings.System.putString(this.mContext.getContentResolver(), "bydux_skin_path", Constant.SkinPath.SKIN_PATH_5);
        }
    }

    private final class SkinHandler extends Handler {
        private SkinHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    FloatCardService.this.setThemeResource();
                    break;
                case 2:
                case 3:
                    if (FloatCardService.this.mDragCardView != null) {
                        FloatCardService.this.mDragCardView.updateView();
                    }
                    break;
                case 4:
                    TopActivityManager.getInstance(FloatCardService.this.mContext).getTopActivity(new TopActivityManager.TopActivityCallback() { // from class: com.byd.card.floatcard.FloatCardService.SkinHandler.1
                        @Override // com.byd.card.floatcard.TopActivityManager.TopActivityCallback
                        public void onTopActivityCallback(ComponentName componentName) {
                            Log.i(FloatCardService.TAG, "Home componentName = " + componentName + ", cardContainer = " + FloatCardService.this.cardContainer);
                            if (componentName == null || !componentName.getPackageName().equals("com.android.launcher3")) {
                                return;
                            }
                            if (FloatCardService.this.cardContainer != null) {
                                FloatCardService.this.cardContainer.animShow();
                            }
                            if (FloatCardService.this.mDragCardView != null) {
                                FloatCardService.this.mDragCardView.moveToFirst();
                            }
                        }
                    });
                    break;
                case 5:
                    DividerUtils.getInstance().startLoadAppAsyncTask(FloatCardService.this.mContext);
                    break;
                case 6:
                    Log.i(FloatCardService.TAG, "MESSAGE_ADD_NAVI_CARD mDragCardView = " + FloatCardService.this.mDragCardView + ", update_navi_card_count =" + FloatCardService.this.update_navi_card_count);
                    if (FloatCardService.this.mDragCardView != null) {
                        FloatCardService.this.mDragCardView.addWidgetItem((String) msg.obj);
                    } else if (FloatCardService.this.update_navi_card_count < 5) {
                        Message messageObtain = Message.obtain();
                        messageObtain.what = 6;
                        messageObtain.obj = msg.obj;
                        FloatCardService.this.mHandler.sendMessageDelayed(messageObtain, 500L);
                        FloatCardService.this.update_navi_card_count++;
                    }
                    break;
                case 7:
                    Log.i(FloatCardService.TAG, "MESSAGE_REMOVE_NAVI_CARD mDragCardView = " + FloatCardService.this.mDragCardView + ", update_navi_card_count = " + FloatCardService.this.update_navi_card_count);
                    if (FloatCardService.this.mDragCardView != null) {
                        FloatCardService.this.mDragCardView.removeWidgetItem((String) msg.obj);
                    } else if (FloatCardService.this.update_navi_card_count < 5) {
                        Message messageObtain2 = Message.obtain();
                        messageObtain2.what = 7;
                        messageObtain2.obj = msg.obj;
                        FloatCardService.this.mHandler.sendMessageDelayed(messageObtain2, 500L);
                        FloatCardService.this.update_navi_card_count++;
                    }
                    break;
            }
        }
    }

    private void handlerInitCard(FloatCardContainer cardContainer) {
        ComponentName currentTopActivity = TopActivityManager.getInstance(this.mContext).getCurrentTopActivity();
        if (currentTopActivity == null || cardContainer == null) {
            Log.w(TAG, "handlerInitCard is null");
            return;
        }
        Log.i(TAG, "handlerInitCard componentName = " + currentTopActivity);
        if ("com.android.launcher3".equals(currentTopActivity.getPackageName()) || Constant.TopActivity.VR_UPDATE_INFO_ACTIVITY.equals(currentTopActivity.getClassName())) {
            return;
        }
        cardContainer.hide();
    }
}
