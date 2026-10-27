package com.byd.systemsettings.system;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.UiModeManager;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemProperties;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import b.b.g.p.d;
import b.b.g.r.k;
import b.b.g.r.z;
import b.b.m.g.f;
import b.b.o.b.b.a;
import com.byd.base.view.dialog.BaseDialogFragment;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.dialog.SimpleDialog;
import com.byd.carsettings.ui.widget.BydInvalidButton;
import com.byd.mvp.common.CarModel$Brand;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class PrivacyPolicyDialog extends BaseDialogFragment implements View.OnClickListener {
    public static final String PRIVACY_CHANGE_ACTION = "android.intent.action.PRIVACY_PAGE_OPERATION_CHANGE";
    public static final int PRIVACY_STATUS_AGREE = 1;
    public static final int PRIVACY_STATUS_REVOKE = 2;
    public static final int PRIVACY_STATUS_SKIP = 3;
    public static final String SYS_PRIVACY_STATUS = "persist.sys.vehicle_privacy_status";
    public static final String SYS_PRIVACY_TIME = "persist.sys.vehicle_privacy_time";
    private static final String TAG = "PrivacyPolicyDialog";
    private static final String TIME_FORMAT_24 = "yyyy-MM-dd HH:mm:ss";
    private static final String TIME_FORMAT_24_EN = "EEEE MMMM dd HH:mm:ss yyyy";
    private static final String TIME_FORMAT_24_XI = "EEEE dd MMMM yyyy HH:mm:ss";
    public static final int TYPE_AGREEMENT = 2;
    public static final int TYPE_OPEN_SOURCE = 3;
    public static final int TYPE_PRIVACY = 1;
    public LinearLayout bottomView;
    private long duration = 8;
    public BydInvalidButton invalidButton;
    public boolean isNightMode;
    public View ivBack;
    private ImageView ivLoading;
    public LinearLayout llDialog;
    public ViewGroup llMasks;
    private ContentResolver mContentResolver;
    private int mH5Type;
    private MyHandler mHandler;
    public Locale mLocale;
    private RotateAnimation mRotateAnimation;
    private TimerTask mTask;
    private Timer mTimer;
    public TextView privacyAllowTime;
    public TextView tvTitle;
    public String url;
    public WebView wbv;

    public class MyHandler extends Handler {
        private final Activity mActivity;

        public MyHandler(Activity activity) {
            this.mActivity = activity;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            Activity activity = this.mActivity;
            if (activity != null) {
                PrivacyPolicyDialog privacyPolicyDialog = PrivacyPolicyDialog.this;
                privacyPolicyDialog.invalidButton.setText(activity.getString(R.string.privacy_allow_second, new Object[]{Long.valueOf(privacyPolicyDialog.duration)}));
                if (PrivacyPolicyDialog.this.duration <= 0) {
                    PrivacyPolicyDialog.this.invalidButton.setText(this.mActivity.getString(R.string.privacy_allow));
                    PrivacyPolicyDialog.this.invalidButton.setBackgroundResource(R.drawable.selector_dialog_blue);
                    PrivacyPolicyDialog.this.stopTimer();
                }
                PrivacyPolicyDialog.this.duration--;
            }
        }
    }

    private static String getDateFormat(Locale locale) {
        return TIME_FORMAT_24;
    }

    private Map<String, String> getH5Params() {
        HashMap map = new HashMap();
        if (this.isNightMode) {
            map.put("theme", "dark");
        }
        return map;
    }

    /* JADX WARN: Removed duplicated region for block: B:70:0x0160  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String getPrivacyH5() {
        /*
            Method dump skipped, instruction units count: 696
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.systemsettings.system.PrivacyPolicyDialog.getPrivacyH5():java.lang.String");
    }

    public static PrivacyPolicyDialog newInstance(int i) {
        PrivacyPolicyDialog privacyPolicyDialog = new PrivacyPolicyDialog();
        Bundle bundle = new Bundle();
        bundle.putInt("h5_type", i);
        privacyPolicyDialog.setArguments(bundle);
        return privacyPolicyDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPrivacyBtnClick() {
        int i = SystemProperties.getInt(SYS_PRIVACY_STATUS, 3);
        if (i != 2 && i != 3) {
            CarModel$Brand carModel$Brand = k.f1817c;
            String string = getString(R.string.nfc_brand_byd);
            if (carModel$Brand == CarModel$Brand.DENZA) {
                string = getString(R.string.nfc_brand_denz);
            }
            SimpleDialog simpleDialogZ = SimpleDialog.z(getString(R.string.privacy_title), getString(R.string.privacy_content_2, string), getString(R.string.dialog_btn_ok), getString(R.string.dialog_btn_cancel), 100);
            if (simpleDialogZ.getDialog() == null || !simpleDialogZ.getDialog().isShowing()) {
                simpleDialogZ.show(getFragmentManager(), "SimpleDialog");
                return;
            }
            return;
        }
        if (this.duration > 0) {
            z.c(getContext(), getString(R.string.privacy_tips));
            return;
        }
        d.g.a().c0(getContext());
        String strC = f.c(getDateFormat(d.g.a().v(getContext())));
        this.privacyAllowTime.setVisibility(0);
        this.privacyAllowTime.setText(getString(R.string.privacy_allow_time, strC));
        this.invalidButton.setText(getString(R.string.privacy_chexiao_allow));
        SystemProperties.set(SYS_PRIVACY_STATUS, "1");
        Settings.Global.putInt(this.mContentResolver, SYS_PRIVACY_STATUS, 1);
        SystemProperties.set(SYS_PRIVACY_TIME, System.currentTimeMillis() + "");
        sendPrivacyChangeBroadcast();
    }

    private void sendPrivacyChangeBroadcast() {
        if (getContext() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction(PRIVACY_CHANGE_ACTION);
        intent.putExtra("privacy_policy_version", "056.20221101");
        int i = SystemProperties.getInt(SYS_PRIVACY_STATUS, 3);
        intent.putExtra(NotificationCompat.CATEGORY_STATUS, i != 1 ? 2 : 1);
        intent.putExtra("user_operation", i);
        SystemProperties.getLong(SYS_PRIVACY_TIME, 0L);
        intent.putExtra("effective_date", f.c(TIME_FORMAT_24));
        getContext().sendBroadcast(intent);
    }

    private void startLoading() {
        if (this.mRotateAnimation == null) {
            this.mRotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        }
        this.mRotateAnimation.setInterpolator(new LinearInterpolator());
        this.mRotateAnimation.setDuration(1500L);
        this.mRotateAnimation.setRepeatCount(-1);
        this.ivLoading.startAnimation(this.mRotateAnimation);
    }

    private void startTimer() {
        this.mTimer = new Timer();
        TimerTask timerTask = new TimerTask() { // from class: com.byd.systemsettings.system.PrivacyPolicyDialog.5
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                PrivacyPolicyDialog.this.mHandler.sendEmptyMessage(1);
            }
        };
        this.mTask = timerTask;
        this.mTimer.schedule(timerTask, 0L, 1000L);
    }

    @Override // com.byd.base.view.dialog.BaseDialogFragment
    public int getLayoutResource() {
        return a.c() == 2 ? this.mH5Type == 3 ? R.layout.dialog_open_source_license_r : R.layout.dialog_user_agreements_r : this.mH5Type == 3 ? R.layout.dialog_open_source_license : R.layout.dialog_user_agreements;
    }

    @Override // com.byd.base.view.dialog.LeakDialogFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle != null) {
            this.duration = bundle.getLong("duration");
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.iv_back_dialog_common_title) {
            dismiss();
        }
    }

    @Override // com.byd.base.view.dialog.BaseDialogFragment, com.byd.base.view.dialog.LeakDialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.isFullScreen = false;
        UiModeManager uiModeManager = (UiModeManager) getContext().getSystemService("uimode");
        if (uiModeManager != null) {
            this.isNightMode = uiModeManager.getNightMode() == 2;
        }
        this.mContentResolver = getContext().getContentResolver();
        this.mH5Type = getArguments().getInt("h5_type");
        this.mLocale = d.g.a().v(getContext());
        String privacyH5 = getPrivacyH5();
        int i = this.mH5Type;
        if (i == 2) {
            privacyH5 = CarModel$Brand.DENZA == k.f1817c ? Locale.SIMPLIFIED_CHINESE.equals(this.mLocale) ? "file:android_asset/h5/agreement/ts_agreement_zh.html" : (Locale.TRADITIONAL_CHINESE.equals(this.mLocale) || Locale.forLanguageTag("zh-HK").equals(this.mLocale)) ? "file:android_asset/h5/agreement/ts_agreement_hk.html" : Locale.forLanguageTag("ms-MY").equals(this.mLocale) ? "file:android_asset/h5/agreement/ts_agreement_ms.html" : Locale.forLanguageTag("th").equals(this.mLocale) ? "file:android_asset/h5/agreement/ts_agreement_th.html" : Locale.forLanguageTag("in").equals(this.mLocale) ? "file:android_asset/h5/agreement/ts_agreement_in.html" : "file:android_asset/h5/agreement/ts_agreement_en.html" : Locale.SIMPLIFIED_CHINESE.equals(this.mLocale) ? "file:android_asset/h5/agreement/agreement_zh.html" : (Locale.TRADITIONAL_CHINESE.equals(this.mLocale) || Locale.forLanguageTag("zh-HK").equals(this.mLocale)) ? "file:android_asset/h5/agreement/agreement_hk.html" : Locale.forLanguageTag("es").equals(this.mLocale) ? "file:android_asset/h5/agreement/agreement_es.html" : Locale.forLanguageTag("es-LA").equals(this.mLocale) ? "file:android_asset/h5/agreement/agreement_es_la.html" : Locale.forLanguageTag("pt-PT").equals(this.mLocale) ? "file:android_asset/h5/agreement/agreement_pt.html" : Locale.forLanguageTag("pt-BR").equals(this.mLocale) ? "file:android_asset/h5/agreement/agreement_pt_br.html" : Locale.FRENCH.equals(this.mLocale) ? "file:android_asset/h5/agreement/agreement_fr.html" : Locale.GERMAN.equals(this.mLocale) ? "file:android_asset/h5/agreement/agreement_de.html" : Locale.ITALIAN.equals(this.mLocale) ? "file:android_asset/h5/agreement/agreement_itali.html" : Locale.forLanguageTag("sv").equals(this.mLocale) ? "file:android_asset/h5/agreement/agreement_sv.html" : Locale.forLanguageTag("nb").equals(this.mLocale) ? "file:android_asset/h5/agreement/agreement_nb.html" : Locale.forLanguageTag("iw").equals(this.mLocale) ? "file:android_asset/h5/agreement/agreement_iw.html" : Locale.forLanguageTag("ru").equals(this.mLocale) ? "file:android_asset/h5/agreement/agreement_ru.html" : Locale.forLanguageTag("uz-UZ").equals(this.mLocale) ? "file:android_asset/h5/agreement/agreement_uz.html" : Locale.forLanguageTag("tr").equals(this.mLocale) ? "file:android_asset/h5/agreement/agreement_tr.html" : Locale.forLanguageTag("ms-MY").equals(this.mLocale) ? "file:android_asset/h5/agreement/agreement_ms.html" : Locale.JAPANESE.equals(this.mLocale) ? "file:android_asset/h5/agreement/agreement_jp.html" : Locale.forLanguageTag("th").equals(this.mLocale) ? "file:android_asset/h5/agreement/agreement_th.html" : Locale.forLanguageTag("in").equals(this.mLocale) ? "file:android_asset/h5/agreement/agreement_in.html" : "file:android_asset/h5/agreement/agreement_en.html";
        } else if (i == 3) {
            privacyH5 = (getContext().getResources().getConfiguration().uiMode & 48) == 32 ? "file:android_asset/h5/license_black.html" : "file:android_asset/h5/license_white.html";
        }
        this.url = privacyH5;
    }

    @Override // com.byd.base.view.dialog.BaseDialogFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        WebView webView = this.wbv;
        if (webView != null) {
            webView.setWebViewClient(null);
            this.wbv.setWebChromeClient(null);
            this.wbv = null;
        }
    }

    @Override // com.byd.base.view.dialog.LeakDialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        MyHandler myHandler = this.mHandler;
        if (myHandler != null) {
            myHandler.removeMessages(1);
        }
    }

    @Override // com.byd.base.view.dialog.LeakDialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(@NonNull DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        this.wbv.pauseTimers();
        this.wbv.removeAllViews();
        this.wbv.destroy();
        stopTimer();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        if (aVar.h() == 100) {
            b.b.m.c.a.a(getClass().getName(), "撤销同意隐私政策");
            SystemProperties.set(SYS_PRIVACY_STATUS, ExifInterface.GPS_MEASUREMENT_2D);
            SystemProperties.set(SYS_PRIVACY_TIME, System.currentTimeMillis() + "");
            Settings.Global.putInt(this.mContentResolver, SYS_PRIVACY_STATUS, 2);
            this.privacyAllowTime.setVisibility(8);
            this.invalidButton.setBackgroundResource(R.drawable.shape_bg_blue_h5_button_unenable);
            this.duration = 8L;
            startTimer();
            sendPrivacyChangeBroadcast();
        }
    }

    @Override // com.byd.base.view.dialog.LeakDialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putLong("duration", this.duration);
    }

    @Override // androidx.fragment.app.Fragment
    @SuppressLint({"SetJavaScriptEnabled"})
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.ll_dialog_masks);
        this.llMasks = viewGroup;
        viewGroup.setOnClickListener(this);
        this.llDialog = (LinearLayout) view.findViewById(R.id.ll_bg_dialo_sound_focus);
        this.tvTitle = (TextView) view.findViewById(R.id.tv_title_dialog_common_titles);
        this.bottomView = (LinearLayout) view.findViewById(R.id.ll_bottom_view);
        this.privacyAllowTime = (TextView) view.findViewById(R.id.privacy_allow_time);
        BydInvalidButton bydInvalidButton = (BydInvalidButton) view.findViewById(R.id.privacy_btn);
        this.invalidButton = bydInvalidButton;
        bydInvalidButton.setOnClickListener(new b.b.a.j.d.a() { // from class: com.byd.systemsettings.system.PrivacyPolicyDialog.1
            @Override // b.b.a.j.d.a
            public void onNoDoubleClick(View view2) {
                PrivacyPolicyDialog.this.onPrivacyBtnClick();
            }
        });
        View viewFindViewById = view.findViewById(R.id.iv_back_dialog_common_title);
        this.ivBack = viewFindViewById;
        viewFindViewById.setOnClickListener(this);
        this.ivLoading = (ImageView) view.findViewById(R.id.iv_loading);
        WebView webView = (WebView) view.findViewById(R.id.wbv_dialog_user_agreements);
        this.wbv = webView;
        webView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.byd.systemsettings.system.PrivacyPolicyDialog.2
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view2) {
                return true;
            }
        });
        this.wbv.setLongClickable(false);
        this.wbv.setBackgroundColor(0);
        this.wbv.getBackground().setAlpha(0);
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() { // from class: com.byd.systemsettings.system.PrivacyPolicyDialog.3
                @Override // java.lang.Runnable
                public void run() {
                    PrivacyPolicyDialog privacyPolicyDialog = PrivacyPolicyDialog.this;
                    privacyPolicyDialog.wbv.loadUrl(privacyPolicyDialog.url);
                }
            });
        }
        WebSettings settings = this.wbv.getSettings();
        settings.setCacheMode(2);
        settings.setJavaScriptEnabled(true);
        this.mHandler = new MyHandler(getActivity());
        int i = SystemProperties.getInt(SYS_PRIVACY_STATUS, 3);
        if (i == 2 || i == 3) {
            this.privacyAllowTime.setVisibility(8);
            this.invalidButton.setBackgroundResource(R.drawable.shape_bg_blue_h5_button_unenable);
            startTimer();
        } else {
            String strB = f.b(SystemProperties.getLong(SYS_PRIVACY_TIME, 0L), getDateFormat(d.g.a().v(getContext())));
            this.privacyAllowTime.setVisibility(0);
            this.privacyAllowTime.setText(getString(R.string.privacy_allow_time, strB));
            this.invalidButton.setText(getString(R.string.privacy_chexiao_allow));
        }
        this.wbv.setVerticalScrollBarEnabled(true);
        this.wbv.setScrollBarStyle(50331648);
        CarModel$Brand carModel$Brand = k.f1817c;
        String string = getString(R.string.nfc_brand_byd);
        if (carModel$Brand == CarModel$Brand.DENZA) {
            string = getString(R.string.nfc_brand_denz);
        }
        int i2 = this.mH5Type;
        if (i2 == 2) {
            this.tvTitle.setText(getString(R.string.user_agreement_system_h5_2, string));
            this.bottomView.setVisibility(8);
        } else if (i2 == 3) {
            this.tvTitle.setText(getString(R.string.open_source_licenses));
            this.bottomView.setVisibility(8);
        } else {
            this.tvTitle.setText(getString(R.string.privacy_policy_system_h5_2, string));
            this.bottomView.setVisibility(0);
        }
        this.wbv.setWebViewClient(new WebViewClient() { // from class: com.byd.systemsettings.system.PrivacyPolicyDialog.4
            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView2, String str) {
                super.onPageFinished(webView2, str);
                b.b.m.c.a.a(PrivacyPolicyDialog.TAG, "onPageFinished:" + str);
            }

            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView webView2, String str, Bitmap bitmap) {
                super.onPageStarted(webView2, str, bitmap);
                b.b.m.c.a.a(PrivacyPolicyDialog.TAG, "onPageStarted:" + str);
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView2, WebResourceRequest webResourceRequest) {
                return true;
            }
        });
        setAnim(this.llMasks, this.llDialog);
        if (this.windowTitle == null) {
            setWindowTitle(getContext(), getClass().getName());
        }
    }

    public void stopTimer() {
        TimerTask timerTask = this.mTask;
        if (timerTask != null) {
            timerTask.cancel();
            this.mTask = null;
        }
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
            this.mTimer = null;
        }
        MyHandler myHandler = this.mHandler;
        if (myHandler != null) {
            myHandler.removeMessages(1);
        }
        this.duration = 0L;
    }
}
