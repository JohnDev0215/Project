package com.byd.vrsettings;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import com.byd.vrsettings.databinding.DialogNetTipBinding;
import com.byd.vrsettings.eventbus.BackPressedEvent;
import com.byd.vrsettings.service.DialogManager;
import com.byd.vrsettings.service.OtaDataManager;
import com.byd.vrsettings.utils.Common;
import com.byd.vrsettings.utils.Constant;
import com.byd.vrsettings.utils.ForegroundAppUtil;
import com.byd.vrsettings.utils.ModeUtils;
import com.byd.vrsettings.utils.NetUtils;
import com.byd.vrsettings.utils.RLog;
import com.byd.vrsettings.utils.SharedPreferencesUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class NetFloatWindowService extends Service {
    private static final String TAG = "Neu_FloatWindowService";
    DialogNetTipBinding binding;
    private View floatingView;
    private final IBinder mBinder = new LocalBinder();
    ConstraintSet set;
    private WindowManager windowManager;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.windowManager = (WindowManager) getSystemService("window");
        DialogNetTipBinding dialogNetTipBindingInflate = DialogNetTipBinding.inflate((LayoutInflater) getSystemService("layout_inflater"));
        this.binding = dialogNetTipBindingInflate;
        this.floatingView = dialogNetTipBindingInflate.getRoot();
        initView();
        if (EventBus.getDefault().isRegistered(this)) {
            return;
        }
        EventBus.getDefault().register(this);
    }

    private void initView() {
        this.binding.checkbox.setChecked(false);
        Context applicationContext = getApplicationContext();
        ConstraintSet constraintSet = new ConstraintSet();
        this.set = constraintSet;
        constraintSet.clone(this.binding.layoutDialogRoot);
        if (Common.isRightDriver() || Common.isRtl(applicationContext)) {
            this.set.clear(R.id.positive, 6);
            this.set.clear(R.id.positive, 7);
            this.set.connect(R.id.positive, 7, 0, 7, (int) TypedValue.applyDimension(1, 104.0f, applicationContext.getResources().getDisplayMetrics()));
            this.set.connect(R.id.positive, 6, R.id.negative, 7, (int) TypedValue.applyDimension(1, 48.0f, applicationContext.getResources().getDisplayMetrics()));
            this.set.clear(R.id.negative, 6);
            this.set.clear(R.id.negative, 7);
            this.set.connect(R.id.negative, 7, R.id.positive, 6);
            this.set.connect(R.id.negative, 6, 0, 6, (int) TypedValue.applyDimension(1, 104.0f, applicationContext.getResources().getDisplayMetrics()));
        } else {
            this.set.clear(R.id.negative, 6);
            this.set.clear(R.id.negative, 7);
            this.set.connect(R.id.negative, 7, 0, 7, (int) TypedValue.applyDimension(1, 104.0f, applicationContext.getResources().getDisplayMetrics()));
            this.set.connect(R.id.negative, 6, R.id.positive, 7, (int) TypedValue.applyDimension(1, 48.0f, applicationContext.getResources().getDisplayMetrics()));
            this.set.clear(R.id.positive, 6);
            this.set.clear(R.id.positive, 7);
            this.set.connect(R.id.positive, 7, R.id.negative, 6);
            this.set.connect(R.id.positive, 6, 0, 6, (int) TypedValue.applyDimension(1, 104.0f, applicationContext.getResources().getDisplayMetrics()));
        }
        this.set.applyTo(this.binding.layoutDialogRoot);
        setLight(!ModeUtils.isNightMode(this));
        this.binding.positive.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.NetFloatWindowService.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DialogManager.get().startSystemNetPage();
                NetFloatWindowService.this.setDontRemind();
            }
        });
        this.binding.negative.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.NetFloatWindowService.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NetFloatWindowService.this.setDontRemind();
                NetFloatWindowService.this.removeFloatingWindow();
                DialogManager.get().isResumeShowNetDialog = false;
            }
        });
    }

    private WindowManager.LayoutParams getLayoutParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, 2002, 8, -3);
        layoutParams.gravity = 17;
        return layoutParams;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        View view = this.floatingView;
        if (view != null) {
            this.windowManager.removeView(view);
        }
        DialogManager.get().isResumeShowNetDialog = true;
        EventBus.getDefault().unregister(this);
    }

    public void showFloatingWindow(boolean z) {
        if (NetUtils.isNetAvailable(getApplicationContext())) {
            RLog.e(TAG, "isNetworkAvailable:" + NetUtils.isNetAvailable(getApplicationContext()));
            return;
        }
        if (!z && !isVrSettingsForground()) {
            RLog.e(TAG, "isForegroundApp:" + ForegroundAppUtil.getTopActivity());
            return;
        }
        if (DialogManager.get().isNetDialogTodayNotTip(this)) {
            RLog.e(TAG, "isNetDialogTodayNotTip:" + SharedPreferencesUtils.getParam(this, SharedPreferencesUtils.SP_KEY_NET_DIALOG_TODAY_NOT_TIP, -1L));
            return;
        }
        if (this.windowManager != null) {
            try {
                RLog.e(TAG, "showFloatingWindow");
                OtaDataManager.updateViewLanguage(this.floatingView);
                updateContent();
                this.windowManager.addView(this.floatingView, getLayoutParams());
                return;
            } catch (Exception e) {
                RLog.e(TAG, "showFloatingWindow excp:" + e.getMessage());
                e.printStackTrace();
                return;
            }
        }
        RLog.e(TAG, "windowManager is null");
    }

    private void updateContent() {
        boolean zIsInstallWithKey = Common.isInstallWithKey(Constant.KEY_NAVI_AVAILABLE);
        RLog.i(TAG, "updateContent isNAVI_AVAILABLE:" + zIsInstallWithKey);
        String str = " " + getApplicationContext().getString(R.string.contect_net_navi);
        String string = getApplicationContext().getString(R.string.content_net_not_avaliable);
        Object[] objArr = new Object[1];
        if (!zIsInstallWithKey) {
            str = com.chad.library.BuildConfig.FLAVOR;
        }
        objArr[0] = str;
        this.binding.textviewContent.setText(String.format(string, objArr));
        this.binding.checkbox.setChecked(false);
    }

    private boolean isVrSettingsForground() {
        return ForegroundAppUtil.isForegroundApp(this, BuildConfig.APPLICATION_ID);
    }

    public void removeFloatingWindow() {
        WindowManager windowManager;
        View view = this.floatingView;
        if (view == null || view.getParent() == null || (windowManager = this.windowManager) == null) {
            return;
        }
        windowManager.removeView(this.floatingView);
    }

    public void setLight(boolean z) {
        if (z) {
            this.binding.textiewTitle.setTextColor(ContextCompat.getColor(this, R.color.voice_setting_title));
            this.binding.textviewContent.setTextColor(ContextCompat.getColor(this, R.color.voice_setting_title));
            this.binding.textviewRadio.setTextColor(ContextCompat.getColor(this, R.color.voice_setting_title));
            this.binding.layoutDialogRoot.setBackgroundResource(R.drawable.bg_setting_dialog);
            this.binding.negative.setTextColor(ContextCompat.getColor(this, R.color.voice_setting_title));
            this.binding.dialogBg.setBackgroundColor(ContextCompat.getColor(this, R.color.dialog_backgroud_color_day));
            return;
        }
        this.binding.textiewTitle.setTextColor(ContextCompat.getColor(this, R.color.voice_setting_title_night));
        this.binding.textviewContent.setTextColor(ContextCompat.getColor(this, R.color.voice_setting_title_night));
        this.binding.textviewRadio.setTextColor(ContextCompat.getColor(this, R.color.voice_setting_title_night));
        this.binding.layoutDialogRoot.setBackgroundResource(R.drawable.bg_setting_dialog_night);
        this.binding.negative.setTextColor(ContextCompat.getColor(this, R.color.voice_setting_title_night));
        this.binding.dialogBg.setBackgroundColor(ContextCompat.getColor(this, R.color.dialog_backgroud_color_night));
    }

    public class LocalBinder extends Binder {
        public LocalBinder() {
        }

        public NetFloatWindowService getService() {
            return NetFloatWindowService.this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDontRemind() {
        if (this.binding.checkbox.isChecked()) {
            DialogManager.setNetDialogTodayNotTip(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBackPressed(BackPressedEvent backPressedEvent) {
        removeFloatingWindow();
    }
}
