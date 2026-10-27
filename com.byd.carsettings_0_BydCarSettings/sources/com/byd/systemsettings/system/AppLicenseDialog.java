package com.byd.systemsettings.system;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.base.view.dialog.BaseDialogFragment;
import com.byd.carsettings.R;
import com.byd.carsettings.model.dilink.CopyrightNoticeApp;
import com.byd.mvp.ui.widget.BydBridgeWebView;

/* JADX INFO: loaded from: classes.dex */
public class AppLicenseDialog extends BaseDialogFragment implements View.OnClickListener {
    public static final String COPYRIGHT_NOTICE_APP = "CopyrightNoticeApp";
    public static final String LICENSE_DIR = "file:///android_asset/html/applicense/";
    private static final String TAG = "AppLicenseDialog";
    private View ivBack;
    private LinearLayout llDialog;
    private ViewGroup llMasks;
    private CopyrightNoticeApp noticeApp;
    private TextView tvTitle;
    private String url;
    private BydBridgeWebView wbv;

    public static AppLicenseDialog newInstance(CopyrightNoticeApp copyrightNoticeApp) {
        AppLicenseDialog appLicenseDialog = new AppLicenseDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable(COPYRIGHT_NOTICE_APP, copyrightNoticeApp);
        appLicenseDialog.setArguments(bundle);
        return appLicenseDialog;
    }

    @Override // com.byd.base.view.dialog.BaseDialogFragment
    public int getLayoutResource() {
        return R.layout.dialog_user_agreements;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.iv_back_dialog_common_title || view.getId() == R.id.ll_dialog_masks) {
            dismiss();
        }
    }

    @Override // com.byd.base.view.dialog.BaseDialogFragment, com.byd.base.view.dialog.LeakDialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.isFullScreen = false;
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        this.noticeApp = (CopyrightNoticeApp) arguments.getSerializable(COPYRIGHT_NOTICE_APP);
        this.url = LICENSE_DIR + this.noticeApp.noticeH5;
    }

    @Override // com.byd.base.view.dialog.LeakDialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(@NonNull DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        this.wbv.pauseTimers();
        this.wbv.removeAllViews();
        this.wbv.destroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.ll_dialog_masks);
        this.llMasks = viewGroup;
        viewGroup.setOnClickListener(this);
        this.llDialog = (LinearLayout) view.findViewById(R.id.ll_bg_dialo_sound_focus);
        TextView textView = (TextView) view.findViewById(R.id.tv_title_dialog_common_titles);
        this.tvTitle = textView;
        textView.setText(getString(R.string.open_source_licenses));
        View viewFindViewById = view.findViewById(R.id.iv_back_dialog_common_title);
        this.ivBack = viewFindViewById;
        viewFindViewById.setOnClickListener(this);
        BydBridgeWebView bydBridgeWebView = (BydBridgeWebView) view.findViewById(R.id.wbv_dialog_user_agreements);
        this.wbv = bydBridgeWebView;
        bydBridgeWebView.loadUrl(this.url);
        this.wbv.setWebViewClient(new WebViewClient() { // from class: com.byd.systemsettings.system.AppLicenseDialog.1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                return true;
            }
        });
        setAnim(this.llMasks, this.llDialog);
    }
}
