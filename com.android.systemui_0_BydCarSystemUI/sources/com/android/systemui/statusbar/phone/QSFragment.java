package com.android.systemui.statusbar.phone;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.qs.QSIconConstants;
import com.android.systemui.qs.QuickQSPanel;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class QSFragment extends Fragment {
    public static final String TAG = "QSFragment";
    private BlurViewController mBlurViewController;
    private StatusBar mStatusBarComponent;
    private QuickQSPanel quickQSPanel;

    public QuickQSPanel getQuickQSPanel() {
        return this.quickQSPanel;
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        BydLogHelper.logD();
        QSIconConstants.initQSIcon();
        return inflater.inflate(R.layout.sysui_qs_panel, container, false);
    }

    @Override // android.app.Fragment
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        BydLogHelper.logV("onHiddenChanged hidden:" + hidden);
        StatusBar statusBar = (StatusBar) SysUiServiceProvider.getComponent(getContext(), StatusBar.class);
        this.mStatusBarComponent = statusBar;
        if (hidden) {
            statusBar.cancelAutoHide();
        } else {
            statusBar.scheduleAutoHide();
        }
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mStatusBarComponent = (StatusBar) SysUiServiceProvider.getComponent(getContext(), StatusBar.class);
        this.mBlurViewController = (BlurViewController) Dependency.get(BlurViewController.class);
        this.quickQSPanel = (QuickQSPanel) view;
        boolean zIsShow = this.mStatusBarComponent.isShow();
        BydLogHelper.logD("isShow:" + zIsShow);
        if (this.mBlurViewController.getBlurBitmap() == null || !zIsShow) {
            return;
        }
        this.quickQSPanel.getImgDrawerMask().setImageBitmap(this.mBlurViewController.getBlurBitmap());
        this.quickQSPanel.getImgDrawerMask().setVisibility(0);
        this.quickQSPanel.getImgDrawerOverlay().setVisibility(0);
    }

    public void goneMask() {
        if (this.quickQSPanel != null) {
            BydLogHelper.logD();
            this.quickQSPanel.goneMask();
        }
    }

    public boolean maskVisible() {
        QuickQSPanel quickQSPanel = this.quickQSPanel;
        return quickQSPanel != null && quickQSPanel.getImgDrawerMask().getVisibility() == 0;
    }
}
