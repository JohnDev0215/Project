package com.byd.card.view.broadcast;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.android.systemui.Constants;
import com.android.systemui.SystemUIApplication;
import com.byd.card.bean.CardStatus;
import com.byd.card.bean.Constant;
import com.byd.card.bean.MessageEvent;
import com.byd.card.floatcard.Executors;
import com.byd.card.floatcard.TopActivityManager;
import com.byd.card.provider.ShortcutProvider;
import com.byd.card.utils.AppUtils;
import com.byd.card.view.activity.ActivityCollector;
import com.byd.card.view.activity.AddCardPopActivity;
import com.byd.card.view.activity.MoreAppActivity;
import com.byd.mediacenter.api.media.MediaControl;
import com.google.common.base.Ascii;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class CardReceiver extends BroadcastReceiver {
    public static final String ALL_APP_BTN = "com.byd.action.card.more.app";
    public static final String CAR_SETTINGS_NOT_MAIN_ACTIVITY_ACTION = "com.byd.carsettings.not.mainactivity.change";
    public static final String CLEAR_APP_DATA = "com.byd.system.clearAppData";
    public static final String CLOSE_APP_DETAIL = "com.byd.card.close.app.detail";
    public static final String CLOSE_CARD = "com.byd.card.close.FloatCard";
    public static final String CONTROL_CARD_PERMISSION = "com.byd.card.permissions.control";
    public static final String CPAA_DIALOG_DISMISS = "com.byd.system.dialog.ACTION_DISMISS";
    public static final String CPAA_START_ACTIVITY = "com.byd.system.ACTION_START_CPAA";
    public static final int DELAY_OPEN_TIME = 20;
    public static final int DELAY_TIME = 500;
    public static final String HIDE_MEDIA_POPUP = "com.byd.card.hide_media_popup";
    public static final String OPEN_CARD = "com.byd.card.open.FloatCard";
    public static final int SEND_DISMISS_SETTINGS_DIALOG = 1000;
    public static final String SHOW_MEDIA_POPUP = "com.byd.card.show_media_popup";
    public static final String SPLIT_SCREEN_STATUS = "SplitScreenStatus.BroadcastReceiver";
    public static final String SYS_BT_DIALOG_CHANGE_ACTION = "com.byd.carsettings.bt.dialog.state.change";
    public static final String SYS_CONNECT_DEVICE_DIALOG_CHANGE_ACTION = "com.byd.carsettings.connect_device.dialog.state.change";
    public static final String SYS_HOTSPOT_DIALOG_CHANGE_ACTION = "com.byd.carsettings.hotspot.dialog.state.change";
    public static final String SYS_WIFI_DIALOG_CHANGE_ACTION = "com.byd.carsettings.wifi.dialog.state.change";
    private static final String TAG = "CardReceiver";
    private AllAppClickListener allAppClickListener;
    private boolean mOpenAllApp;
    private boolean mSettingsDialogShow;
    private List<CardStatus> statuses = new ArrayList();
    private final Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.byd.card.view.broadcast.CardReceiver.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Intent intent = new Intent(SystemUIApplication.getInstance().getApplicationContext(), (Class<?>) MoreAppActivity.class);
            intent.addFlags(268435456);
            SystemUIApplication.getInstance().getApplicationContext().startActivity(intent);
        }
    };

    public interface AllAppClickListener {
        void openAllAppActivity();
    }

    public void setAllAppClickListener(AllAppClickListener listener) {
        this.allAppClickListener = listener;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        final String stringExtra = intent.getStringExtra(MediaControl.PARAM_PACKAGE);
        Log.w(TAG, "CardReceiver action " + action + ", extra = " + stringExtra);
        action.hashCode();
        byte b = -1;
        switch (action.hashCode()) {
            case -1969628033:
                if (action.equals("com.byd.carsettings.wifi.dialog.state.change")) {
                    b = 0;
                }
                break;
            case -1457110506:
                if (action.equals(CAR_SETTINGS_NOT_MAIN_ACTIVITY_ACTION)) {
                    b = 1;
                }
                break;
            case -1323414667:
                if (action.equals(SYS_CONNECT_DEVICE_DIALOG_CHANGE_ACTION)) {
                    b = 2;
                }
                break;
            case -1028758310:
                if (action.equals("SplitScreenStatus.BroadcastReceiver")) {
                    b = 3;
                }
                break;
            case -855379044:
                if (action.equals("com.byd.action.card.more.app")) {
                    b = 4;
                }
                break;
            case -396131185:
                if (action.equals("com.byd.carsettings.hotspot.dialog.state.change")) {
                    b = 5;
                }
                break;
            case -10132737:
                if (action.equals(SHOW_MEDIA_POPUP)) {
                    b = 6;
                }
                break;
            case 100796028:
                if (action.equals("com.byd.carsettings.bt.dialog.state.change")) {
                    b = 7;
                }
                break;
            case 404605391:
                if (action.equals(CLEAR_APP_DATA)) {
                    b = 8;
                }
                break;
            case 673295990:
                if (action.equals(CPAA_DIALOG_DISMISS)) {
                    b = 9;
                }
                break;
            case 775824836:
                if (action.equals(HIDE_MEDIA_POPUP)) {
                    b = 10;
                }
                break;
            case 1063038978:
                if (action.equals(CPAA_START_ACTIVITY)) {
                    b = Ascii.VT;
                }
                break;
            case 1816570232:
                if (action.equals(OPEN_CARD)) {
                    b = Ascii.FF;
                }
                break;
            case 2041082950:
                if (action.equals(CLOSE_CARD)) {
                    b = Ascii.CR;
                }
                break;
        }
        switch (b) {
            case 0:
            case 2:
            case 5:
            case 7:
                this.mSettingsDialogShow = intent.getBooleanExtra("show", false);
                Log.w(TAG, "CardReceiver mSettingsDialogShow " + this.mSettingsDialogShow + ", mOpenAllApp = " + this.mOpenAllApp);
                if (!this.mSettingsDialogShow && this.mOpenAllApp) {
                    dealNavigationBtnClick(context);
                    break;
                }
                break;
            case 1:
                AppUtils.setIsShowingCarSettingsSubActivity(intent.getBooleanExtra("show", false));
                break;
            case 3:
                if (intent.getBooleanExtra(Constants.SPLIT_SCREEN_STATUS_BROADCAST_EXTRA, false)) {
                    EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.SPLIT_SCREEN));
                } else {
                    EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.SPLIT_SCREEN_NOT));
                }
                break;
            case 4:
                closeAppDetail(context);
                dealNavigationBtnClick(context);
                break;
            case 6:
                EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.SHOW_MEDIA_POPUP));
                break;
            case 8:
                if (ShortcutProvider.SHORTCUT_WHITELIST.contains(stringExtra)) {
                    Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.view.broadcast.CardReceiver.2
                        @Override // java.lang.Runnable
                        public void run() {
                            AppUtils.removeShortcuts(stringExtra);
                        }
                    });
                }
                break;
            case 9:
                EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.CPAA_DIALOG_DISMISS));
                break;
            case 10:
                EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.HIDE_MEDIA_POPUP));
                break;
            case 11:
                EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.HIDE_FLOAT_WINDOW));
                break;
            case 12:
                setCardStatus(stringExtra, true);
                EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.SHOW_FLOAT_WINDOW));
                break;
            case 13:
                setCardStatus(stringExtra, false);
                EventBus.getDefault().post(new MessageEvent(Constant.EventBusMessage.HIDE_FLOAT_WINDOW));
                break;
        }
    }

    private void dealNavigationBtnClick(final Context context) {
        final MoreAppActivity moreAppActivity = (MoreAppActivity) ActivityCollector.getActivity(MoreAppActivity.class);
        if (moreAppActivity == null) {
            AllAppClickListener allAppClickListener = this.allAppClickListener;
            if (allAppClickListener != null) {
                allAppClickListener.openAllAppActivity();
            }
            if (this.mSettingsDialogShow) {
                context.sendBroadcast(new Intent("android.intent.byd.ACTION_DISMISS_ALL_SETTINGS_DIALOG"));
                this.mOpenAllApp = true;
                sendMsgOpenAllApp();
                return;
            } else {
                startMoreAppActivity(context);
                this.mOpenAllApp = false;
                return;
            }
        }
        TopActivityManager.getInstance(context).getTopActivity(new TopActivityManager.TopActivityCallback() { // from class: com.byd.card.view.broadcast.CardReceiver.3
            @Override // com.byd.card.floatcard.TopActivityManager.TopActivityCallback
            public void onTopActivityCallback(ComponentName componentName) {
                if (componentName == null) {
                    if (CardReceiver.this.allAppClickListener != null) {
                        CardReceiver.this.allAppClickListener.openAllAppActivity();
                    }
                    if (CardReceiver.this.mSettingsDialogShow) {
                        context.sendBroadcast(new Intent("android.intent.byd.ACTION_DISMISS_ALL_SETTINGS_DIALOG"));
                        CardReceiver.this.mOpenAllApp = true;
                        CardReceiver.this.sendMsgOpenAllApp();
                        return;
                    } else {
                        moreAppActivity.startActivity(new Intent(moreAppActivity, (Class<?>) MoreAppActivity.class));
                        CardReceiver.this.mOpenAllApp = false;
                        return;
                    }
                }
                if (!"com.byd.card.view.activity.MoreAppActivity".equals(componentName.getClassName())) {
                    if (CardReceiver.this.allAppClickListener != null) {
                        CardReceiver.this.allAppClickListener.openAllAppActivity();
                    }
                    if (CardReceiver.this.mSettingsDialogShow) {
                        context.sendBroadcast(new Intent("android.intent.byd.ACTION_DISMISS_ALL_SETTINGS_DIALOG"));
                        CardReceiver.this.mOpenAllApp = true;
                        CardReceiver.this.sendMsgOpenAllApp();
                        return;
                    } else {
                        moreAppActivity.startActivity(new Intent(moreAppActivity, (Class<?>) MoreAppActivity.class));
                        CardReceiver.this.mOpenAllApp = false;
                        return;
                    }
                }
                moreAppActivity.closeSoftKeyBoard();
                moreAppActivity.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMsgOpenAllApp() {
        Message messageObtain = Message.obtain();
        messageObtain.what = 1000;
        this.mHandler.sendMessageDelayed(messageObtain, 500L);
    }

    private void startMoreAppActivity(Context context) {
        AddCardPopActivity addCardPopActivity = (AddCardPopActivity) ActivityCollector.getActivity(AddCardPopActivity.class);
        Log.i(TAG, "startMoreAppActivity addCardPopActivity : " + addCardPopActivity);
        if (addCardPopActivity != null) {
            addCardPopActivity.finish();
            Message messageObtain = Message.obtain();
            messageObtain.what = 1000;
            this.mHandler.sendMessageDelayed(messageObtain, 20L);
            return;
        }
        Intent intent = new Intent(context, (Class<?>) MoreAppActivity.class);
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    private void setCardStatus(String packageName, boolean show) {
        if (packageName == null) {
            Log.w(TAG, "setCardStatus packageName is null");
            return;
        }
        int i = 0;
        while (true) {
            if (i >= this.statuses.size()) {
                i = -1;
                break;
            } else {
                if (packageName.equals(this.statuses.get(i).getPkg())) {
                    this.statuses.get(i).setShow(show);
                    break;
                }
                i++;
            }
        }
        if (i == -1) {
            CardStatus cardStatus = new CardStatus();
            cardStatus.setShow(show);
            cardStatus.setPkg(packageName);
            this.statuses.add(cardStatus);
        }
    }

    public boolean getCardStatus(String packageName) {
        for (CardStatus cardStatus : this.statuses) {
            if (packageName.equals(cardStatus.getPkg())) {
                return cardStatus.isShow();
            }
        }
        return false;
    }

    private void closeAppDetail(Context context) {
        Intent intent = new Intent();
        intent.setAction(CLOSE_APP_DETAIL);
        context.sendBroadcast(intent);
    }
}
