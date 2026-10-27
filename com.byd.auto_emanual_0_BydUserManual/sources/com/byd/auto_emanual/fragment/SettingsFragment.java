package com.byd.auto_emanual.fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.view.KeyEventDispatcher;
import com.byd.auto_emanual.ExecutorManager;
import com.byd.auto_emanual.IRefresh;
import com.byd.auto_emanual.ManualRunnable;
import com.byd.auto_emanual.R;
import com.byd.auto_emanual.update.NewVersionInfo;
import com.byd.auto_emanual.update.UpdateCheckUtil;
import com.byd.auto_emanual.util.ApkInstaller;
import com.byd.auto_emanual.util.CLickProxyUtil;
import com.byd.auto_emanual.util.DeviceUtil;
import com.byd.auto_emanual.util.DialogUtil;
import com.byd.auto_emanual.util.MLog;
import com.byd.auto_emanual.util.NetUtil;
import com.byd.auto_emanual.util.ResourceUtil;
import com.byd.auto_emanual.util.SharedPrefUtil;
import com.byd.auto_emanual.util.Utils;
import com.byd.auto_emanual.util.ViewUtil;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class SettingsFragment extends BaseFragment {
    public static final int MSG_ZIP_DOWNLOADING = 2;
    public static final int MSG_ZIP_DOWNLOAD_END = 3;
    public static final int MSG_ZIP_DOWNLOAD_ERROR = 4;
    public static final int MSG_ZIP_DOWNLOAD_START = 1;
    public static final int MSG_ZIP_DOWNLOAD_UPDATE = 5;
    private static final String TAG = "SettingsFragment";
    private TextView agreement;
    private ImageView agreementImage;
    private int buttonText;
    private TextView copyright;
    private AlertDialog disclaimerDialog;
    private TextView downloadButton;
    private TextView downloadDetail;
    private View downloadLine;
    private AlertDialog downloadPromptDialog;
    private TextView downloadSize;
    private TextView downloadText;
    private ImageView downloadUpdateDot;
    private SettingsHandler handler;
    private List<String> items;
    private AlertDialog newVersionDialog;
    private TextView offlineResource;
    private final OnUpdateListener onUpdateListener = new OnUpdateListener() { // from class: com.byd.auto_emanual.fragment.SettingsFragment.1
        @Override // com.byd.auto_emanual.fragment.OnUpdateListener
        public void onDownloadProgress(int progress) {
        }

        @Override // com.byd.auto_emanual.fragment.OnUpdateListener
        public void onNewVersion(NewVersionInfo info, boolean checkInBackground) {
            if (info != null) {
                SettingsFragment.this.updateUIByNewVersion();
                if (info.getUpgradeStyle() == 3) {
                    UpdateCheckUtil.getInstance().startDownload(info);
                    return;
                }
                SettingsFragment.this.otherStateDialog = OtherStateDialog.getDialogInstance(R.string.settings_update_updating);
                SettingsFragment settingsFragment = SettingsFragment.this;
                settingsFragment.newVersionDialog = DialogUtil.showNewVersion(settingsFragment.context, info, SettingsFragment.this.otherStateDialog, SettingsFragment.this.getChildFragmentManager(), OtherStateDialog.STAGE_LOADING);
                return;
            }
            Toast.makeText(SettingsFragment.this.context, SettingsFragment.this.context.getString(R.string.settings_update_latest_version), 0).show();
        }

        @Override // com.byd.auto_emanual.fragment.OnUpdateListener
        public void onCheckUpdateError(String msg) {
            MLog.d(SettingsFragment.TAG, "onCheckUpdateError " + msg);
            Toast.makeText(SettingsFragment.this.context, msg, 0).show();
        }

        @Override // com.byd.auto_emanual.fragment.OnUpdateListener
        public void onDownloadOk(String savePath, int updateStyle) throws Throwable {
            MLog.d(SettingsFragment.TAG, "onDownloadOk! updateStyle = " + updateStyle);
            if (updateStyle == 3) {
                ApkInstaller.installSilence(SettingsFragment.this.context, savePath);
            } else {
                Toast.makeText(SettingsFragment.this.context, SettingsFragment.this.context.getString(R.string.settings_download_ok), 0).show();
                ApkInstaller.installBySystem(SettingsFragment.this.context, savePath);
            }
        }

        @Override // com.byd.auto_emanual.fragment.OnUpdateListener
        public void onDownloadFail(int updateStyle) {
            if (updateStyle == 3) {
                return;
            }
            MLog.d(SettingsFragment.TAG, "onDownloadFail! updateStyle = " + updateStyle);
            Toast.makeText(SettingsFragment.this.context, SettingsFragment.this.context.getString(R.string.settings_download_failed), 0).show();
        }
    };
    private OtherStateDialog otherStateDialog;
    private LinearLayout settingsAbort;
    private LinearLayout settingsDownload;
    private RelativeLayout settingsDownloadLanguage;
    private ImageView settingsIcon;
    private TextView settingsName;
    private ScrollView settingsScroll;
    private TextView supportLanguage;
    private TextView version;

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUIByNewVersion() {
    }

    @Override // com.byd.auto_emanual.fragment.BaseFragment
    public boolean notLoadWhenNotShowVideo() {
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.handler = new SettingsHandler(this.context.getMainLooper());
    }

    private void initItemsData() {
        this.items = Arrays.asList(this.context.getResources().getStringArray(R.array.settings_items));
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.settings_fragment, container, false);
        initView(viewInflate);
        initItemsView(viewInflate);
        updateViewByConfig();
        updateColorByConfig();
        updateTextByConfig();
        return viewInflate;
    }

    private void initView(View view) {
        this.settingsScroll = (ScrollView) view.findViewById(R.id.settings_scroll);
        this.settingsAbort = (LinearLayout) view.findViewById(R.id.settings_about);
        this.settingsName = (TextView) view.findViewById(R.id.settings_name);
    }

    private void updateViewByConfig() {
        int i = this.context.getResources().getConfiguration().orientation;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.settingsScroll.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.settingsAbort.getLayoutParams();
        if (DeviceUtil.isRightDriver()) {
            if (ViewUtil.isPort(i)) {
                layoutParams.addRule(3, R.id.settings_about);
                layoutParams.topMargin = this.context.getResources().getDimensionPixelOffset(R.dimen.settings_scroll_margin_top_port);
                layoutParams.width = -2;
                layoutParams2.removeRule(16);
                layoutParams2.topMargin = this.context.getResources().getDimensionPixelOffset(R.dimen.settings_abort_margin_top_port);
            } else {
                layoutParams.removeRule(3);
                layoutParams.addRule(21);
                layoutParams.topMargin = this.context.getResources().getDimensionPixelOffset(R.dimen.settings_scroll_margin_top);
                layoutParams.width = getResources().getDimensionPixelOffset(R.dimen.settings_item_width);
                layoutParams2.addRule(16, R.id.settings_scroll);
                layoutParams2.topMargin = this.context.getResources().getDimensionPixelOffset(R.dimen.settings_abort_margin_top);
            }
        } else if (ViewUtil.isPort(i)) {
            layoutParams.addRule(3, R.id.settings_about);
            layoutParams.topMargin = this.context.getResources().getDimensionPixelOffset(R.dimen.settings_scroll_margin_top_port);
            layoutParams.width = -2;
            layoutParams2.removeRule(1);
            layoutParams2.topMargin = this.context.getResources().getDimensionPixelOffset(R.dimen.settings_abort_margin_top_port);
        } else {
            layoutParams.removeRule(3);
            layoutParams.topMargin = this.context.getResources().getDimensionPixelOffset(R.dimen.settings_scroll_margin_top);
            layoutParams.width = getResources().getDimensionPixelOffset(R.dimen.settings_item_width);
            layoutParams2.addRule(1, R.id.settings_scroll);
            layoutParams2.topMargin = this.context.getResources().getDimensionPixelOffset(R.dimen.settings_abort_margin_top);
        }
        this.settingsScroll.setLayoutParams(layoutParams);
        this.settingsAbort.setLayoutParams(layoutParams2);
        if (this.agreementImage != null) {
            if (ViewUtil.isRtl(getResources().getConfiguration().getLayoutDirection())) {
                this.agreementImage.setRotation(180.0f);
            } else {
                this.agreementImage.setRotation(0.0f);
            }
        }
    }

    private void initItemsView(View view) {
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.settings_item_2);
        this.agreement = (TextView) relativeLayout.findViewById(R.id.settings_item);
        this.agreementImage = (ImageView) relativeLayout.findViewById(R.id.settings_item_image);
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.fragment.-$$Lambda$SettingsFragment$aQu0ogkIZCLtnuq_OzmOsvyY2cY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$initItemsView$0$SettingsFragment(view2);
            }
        });
        this.downloadText = (TextView) view.findViewById(R.id.settings_download_text);
        this.downloadDetail = (TextView) view.findViewById(R.id.settings_download_text_detail);
        this.supportLanguage = (TextView) view.findViewById(R.id.settings_support_language_text);
        this.downloadLine = view.findViewById(R.id.settings_download_line);
        this.offlineResource = (TextView) view.findViewById(R.id.settings_download_language);
        updateDownloadLanguage();
        this.downloadSize = (TextView) view.findViewById(R.id.settings_download_size);
        updateDownloadSize();
        this.downloadButton = (TextView) view.findViewById(R.id.settings_download_button);
        this.downloadUpdateDot = (ImageView) view.findViewById(R.id.settings_download_dot);
        updateDownloadButtonText();
        this.downloadButton.setOnClickListener(CLickProxyUtil.getInstance(new View.OnClickListener() { // from class: com.byd.auto_emanual.fragment.-$$Lambda$SettingsFragment$iWn1YDBLA8sXFD23yx7Fu5KS2tU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$initItemsView$1$SettingsFragment(view2);
            }
        }, 2000));
        this.settingsDownload = (LinearLayout) view.findViewById(R.id.settings_download_layout);
        this.settingsDownloadLanguage = (RelativeLayout) view.findViewById(R.id.settings_download_language_layout);
        TextView textView = (TextView) view.findViewById(R.id.settings_version);
        this.version = textView;
        textView.setText(Utils.getVersionName(this.context));
        this.settingsIcon = (ImageView) view.findViewById(R.id.settings_icon);
        this.copyright = (TextView) view.findViewById(R.id.settings_copyright);
    }

    public /* synthetic */ void lambda$initItemsView$0$SettingsFragment(View view) {
        if (ViewUtil.isTimeToDo(1000)) {
            this.disclaimerDialog = DialogUtil.showDisclaimer(this.context, false, null);
        }
    }

    public /* synthetic */ void lambda$initItemsView$1$SettingsFragment(View view) {
        doDownload();
    }

    private void updateDownloadSize() {
        double d;
        String strLoad = SharedPrefUtil.load(this.context, ManualFragment.zipRequireDownloadSizeKey());
        if (TextUtils.isEmpty(strLoad)) {
            d = 0.0d;
        } else {
            try {
                d = Double.parseDouble(strLoad);
            } catch (NumberFormatException unused) {
                MLog.e(TAG, "updateDownloadSize " + strLoad);
                d = 0.0d;
            }
        }
        this.downloadSize.setText(getString(R.string.settings_download_size, String.format(Locale.ENGLISH, "%.1f", Double.valueOf((d / 1024.0d) / 1024.0d))));
    }

    private void updateDownloadLanguage() {
        String str = getString(R.string.settings_support_language) + Utils.getResourceLanguageDisplay();
        MLog.d(TAG, "updateDownloadLanguage set supportLanguage " + str);
        this.supportLanguage.setText(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshMain() {
        KeyEventDispatcher.Component activity = getActivity();
        final IRefresh iRefresh = activity instanceof IRefresh ? (IRefresh) activity : null;
        if (iRefresh != null) {
            this.downloadButton.postDelayed(new Runnable() { // from class: com.byd.auto_emanual.fragment.-$$Lambda$SettingsFragment$AyWn0WDqFs627jOzIeiEW304UCE
                @Override // java.lang.Runnable
                public final void run() {
                    iRefresh.refresh();
                }
            }, 0L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDownloadButtonText() {
        refreshMain();
        boolean z = Boolean.parseBoolean(SharedPrefUtil.load(this.context, ManualFragment.zipRequireUpdateKey()));
        if (TextUtils.isEmpty(SharedPrefUtil.load(this.context, ManualFragment.zipRequireDownloadSizeKey()))) {
            if (z) {
                this.downloadButton.setText(R.string.update);
                this.downloadUpdateDot.setVisibility(0);
                return;
            } else {
                this.downloadButton.setText(R.string.settings_download_button_download);
                this.downloadUpdateDot.setVisibility(8);
                return;
            }
        }
        String strLoad = SharedPrefUtil.load(this.context, ManualFragment.zipRequireDownloadKey());
        if (TextUtils.isEmpty(strLoad) || Boolean.parseBoolean(strLoad)) {
            if (z) {
                this.downloadButton.setText(R.string.update);
                this.downloadUpdateDot.setVisibility(0);
            } else {
                this.downloadButton.setText(R.string.settings_download_button_download);
                this.downloadUpdateDot.setVisibility(8);
            }
            int i = this.buttonText;
            if (i != 0) {
                this.downloadButton.setText(i);
                return;
            }
            return;
        }
        this.downloadButton.setText(R.string.settings_download_button_downloaded);
        this.downloadUpdateDot.setVisibility(8);
    }

    private void doDownload() {
        if (this.downloadButton.getText().equals(getString(R.string.settings_download_button_downloading))) {
            Toast.makeText(this.context, R.string.download_and_wait, 0).show();
            MLog.i(TAG, "doDownload downloading.");
            return;
        }
        final ManualRunnable manualRunnable = ExecutorManager.getManualRunnable();
        if (manualRunnable == null) {
            Toast.makeText(this.context, R.string.download_exception_and_restart, 0).show();
            MLog.i(TAG, "doDownload runnable not exist.");
            return;
        }
        if (!NetUtil.isNetAvailable(this.context)) {
            Toast.makeText(this.context, R.string.net_error_try_later, 0).show();
            MLog.i(TAG, "doDownload net not available.");
            return;
        }
        boolean zIsDownloadZip = manualRunnable.isDownloadZip();
        updateDownloadLanguage();
        updateDownloadSize();
        if (!zIsDownloadZip) {
            Toast.makeText(this.context, R.string.download_resource_already_exist, 0).show();
            this.downloadButton.setText(R.string.settings_download_button_downloaded);
            this.buttonText = R.string.settings_download_button_downloaded;
            MLog.i(TAG, "doDownload resource exists.");
            return;
        }
        if (NetUtil.isWifi(this.context)) {
            goToDownloadZip(manualRunnable);
        } else {
            this.downloadPromptDialog = DialogUtil.downloadPromptDialog(getActivity(), new View.OnClickListener() { // from class: com.byd.auto_emanual.fragment.-$$Lambda$SettingsFragment$VUgfCiL9QmziOTltZs-uxUG_xxA
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$doDownload$3$SettingsFragment(manualRunnable, view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$doDownload$3$SettingsFragment(ManualRunnable manualRunnable, View view) {
        goToDownloadZip(manualRunnable);
    }

    private void goToDownloadZip(ManualRunnable manualRunnable) {
        this.downloadButton.setText(R.string.settings_download_button_prepare);
        this.buttonText = R.string.settings_download_button_prepare;
        manualRunnable.downloadZip(this.handler);
    }

    private void updateColorByConfig() {
        ResourceUtil.setTextColor(this.agreement, R.attr.settings_item_text_color);
        ResourceUtil.setBackgroundDrawable(this.agreement, R.drawable.settings_item_background);
        ResourceUtil.setTextColor(this.downloadText, R.attr.settings_download_text_color);
        ResourceUtil.setTextColor(this.downloadDetail, R.attr.settings_download_text_detail_color);
        ResourceUtil.setTextColor(this.supportLanguage, R.attr.settings_download_text_detail_color);
        ResourceUtil.setTextColor(this.offlineResource, R.attr.settings_download_text_color);
        ResourceUtil.setTextColor(this.downloadSize, R.attr.settings_download_text_color);
        this.downloadLine.setBackgroundColor(ContextCompat.getColor(this.context, R.color.settings_download_line_color));
        ResourceUtil.setTextColor(this.settingsName, R.attr.settings_name_text_color);
        ResourceUtil.setTextColor(this.version, R.attr.settings_version_text_color);
        ResourceUtil.setTextColor(this.copyright, R.attr.settings_copyright_text_color);
        ResourceUtil.setBackground(this.settingsIcon, R.attr.icon_settings);
        ResourceUtil.setBackgroundDrawable(this.settingsDownload, R.drawable.settings_item_background);
        ResourceUtil.setBackgroundDrawable(this.settingsDownloadLanguage, R.drawable.settings_item_background);
        ResourceUtil.setBackgroundDrawable(this.agreement, R.drawable.settings_item_background);
        ResourceUtil.setBackground(this.agreementImage, R.attr.icon_settings_item);
        if (!Utils.is6125F()) {
            this.downloadButton.setBackground(ContextCompat.getDrawable(this.context, R.drawable.button_confirm_background_shape_selector));
        }
        AlertDialog alertDialog = this.disclaimerDialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            DialogUtil.updateDialogColorByConfig(this.disclaimerDialog);
        }
        AlertDialog alertDialog2 = this.newVersionDialog;
        if (alertDialog2 != null && alertDialog2.isShowing()) {
            DialogUtil.updateDialogColorByConfig(this.newVersionDialog);
        }
        AlertDialog alertDialog3 = this.downloadPromptDialog;
        if (alertDialog3 == null || !alertDialog3.isShowing()) {
            return;
        }
        DialogUtil.updateDialogColorByConfig(this.downloadPromptDialog);
    }

    private void updateTextByConfig() {
        initItemsData();
        this.settingsName.setText(R.string.settings_name);
        this.downloadText.setText(R.string.settings_download_text);
        this.downloadDetail.setText(R.string.settings_download_text_detail);
        this.offlineResource.setText(R.string.off_line_data);
        updateDownloadSize();
        updateDownloadButtonText();
        updateDownloadLanguage();
        this.agreement.setText(this.items.get(2));
        AlertDialog alertDialog = this.disclaimerDialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            DialogUtil.updateDialogTextByConfig(this.disclaimerDialog, R.string.dialog_disclaimer_title, R.string.dialog_disclaimer_detail, R.string.dialog_disclaimer_radio, R.string.dialog_button_confirm, R.string.dialog_button_cancel);
        }
        AlertDialog alertDialog2 = this.newVersionDialog;
        if (alertDialog2 != null && alertDialog2.isShowing()) {
            DialogUtil.updateDialogTextByConfig(this.newVersionDialog, R.string.settings_version_update, -1, -1, R.string.settings_update_now, R.string.settings_update_later);
        }
        AlertDialog alertDialog3 = this.downloadPromptDialog;
        if (alertDialog3 == null || !alertDialog3.isShowing()) {
            return;
        }
        DialogUtil.updateDialogTextByConfig(this.downloadPromptDialog, R.string.download_prompt_title, R.string.download_prompt_detail, -1, R.string.dialog_button_confirm, R.string.dialog_button_cancel);
    }

    private void checkUpdate(boolean checkInBackground) {
        if (ViewUtil.showToast(Toast.makeText(this.context, this.context.getString(R.string.settings_update_checking), 0), 5000)) {
            UpdateCheckUtil.getInstance().setOnUpdateListener(this.onUpdateListener);
            UpdateCheckUtil.getInstance().startCheckUpdate(checkInBackground);
        }
    }

    private void feedBack() {
        new Thread(new Runnable() { // from class: com.byd.auto_emanual.fragment.-$$Lambda$SettingsFragment$xrknwXytHA5jqHjNNCCHk8PrHkA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$feedBack$4$SettingsFragment();
            }
        }).start();
    }

    public /* synthetic */ void lambda$feedBack$4$SettingsFragment() {
        Intent intent = new Intent("com.byd.negativescreen.feedback_action");
        Bundle bundle = new Bundle();
        bundle.putBoolean("isStartRecorder", false);
        bundle.putInt("appType", 3);
        bundle.putString("packageName", this.context.getPackageName());
        bundle.putString("versionName", Utils.getVersionName(this.context));
        intent.putExtras(bundle);
        MLog.i(TAG, "feedBack bundle : isStartRecorder = " + bundle.getBoolean("isStartRecorder") + ", appType = " + bundle.getInt("appType") + ", packageName = " + bundle.getString("packageName") + ", versionName = " + bundle.getString("versionName"));
        startActivity(intent);
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateColorByConfig();
        updateTextByConfig();
        updateViewByConfig();
    }

    @Override // com.byd.auto_emanual.fragment.BaseFragment, com.byd.auto_emanual.fragment.IView
    public void loadData() {
        updateTextByConfig();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public class SettingsHandler extends Handler {
        SettingsHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.what;
            if (i == 1) {
                SettingsFragment.this.downloadButton.setText(R.string.settings_download_button_downloading);
                SettingsFragment.this.buttonText = R.string.settings_download_button_downloading;
                Toast.makeText(SettingsFragment.this.context, R.string.download_and_wait, 0).show();
                return;
            }
            if (i == 3) {
                SettingsFragment.this.downloadButton.setText(R.string.settings_download_button_downloaded);
                SettingsFragment.this.buttonText = R.string.settings_download_button_downloaded;
                SettingsFragment.this.downloadUpdateDot.setVisibility(8);
                SettingsFragment.this.refreshMain();
                Toast.makeText(SettingsFragment.this.context, R.string.download_and_complete, 0).show();
                return;
            }
            if (i != 4) {
                if (i != 5) {
                    return;
                }
                SettingsFragment.this.buttonText = 0;
                SettingsFragment.this.updateDownloadButtonText();
                return;
            }
            if (Boolean.parseBoolean(SharedPrefUtil.load(SettingsFragment.this.context, ManualFragment.zipRequireUpdateKey()))) {
                SettingsFragment.this.downloadButton.setText(R.string.update);
                SettingsFragment.this.downloadUpdateDot.setVisibility(0);
                SettingsFragment.this.buttonText = R.string.update;
            } else {
                SettingsFragment.this.downloadButton.setText(R.string.settings_download_button_download);
                SettingsFragment.this.downloadUpdateDot.setVisibility(8);
                SettingsFragment.this.buttonText = R.string.settings_download_button_download;
            }
            Toast.makeText(SettingsFragment.this.context, R.string.download_exception_and_restart, 0).show();
        }
    }

    public SettingsHandler getHandler() {
        return this.handler;
    }
}
