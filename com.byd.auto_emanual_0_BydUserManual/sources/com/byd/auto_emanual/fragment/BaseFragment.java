package com.byd.auto_emanual.fragment;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import com.byd.auto_emanual.util.LoadingUtil;

/* JADX INFO: loaded from: classes.dex */
public class BaseFragment extends Fragment implements IView {
    protected Context context;
    protected ObjectAnimator loadingAnimator;
    protected ImageView loadingIcon;
    protected RelativeLayout netErrorState;
    protected RelativeLayout netLoadingState;
    protected Presenter presenter;
    protected boolean reloadDataByNetChanged;

    @Override // com.byd.auto_emanual.fragment.IView
    public void loadComplete() {
    }

    public boolean notLoadWhenNotShowVideo() {
        return false;
    }

    @Override // com.byd.auto_emanual.fragment.IView
    public void showData(PageData pageData, int loadTime) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override // com.byd.auto_emanual.fragment.IView
    public void showLoadingDialog() {
        hideNetError();
        showNetLoading();
    }

    private void showNetLoading() {
        RelativeLayout relativeLayout = this.netLoadingState;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(0);
        }
        ImageView imageView = this.loadingIcon;
        if (imageView != null) {
            this.loadingAnimator = LoadingUtil.startLoading(imageView);
        }
    }

    public void hideNetLoading() {
        if (this.loadingIcon != null) {
            LoadingUtil.stopLoading(this.loadingAnimator);
        }
        RelativeLayout relativeLayout = this.netLoadingState;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(8);
        }
    }

    private void showNetError() {
        RelativeLayout relativeLayout = this.netErrorState;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(0);
        }
    }

    private void hideNetError() {
        RelativeLayout relativeLayout = this.netErrorState;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(8);
        }
    }

    @Override // com.byd.auto_emanual.fragment.IView
    public void showData(PageData pageData) {
        hideNetError();
        hideNetLoading();
    }

    @Override // com.byd.auto_emanual.fragment.IView
    public void showError() {
        hideNetLoading();
        showNetError();
    }

    @Override // com.byd.auto_emanual.fragment.IView
    public void loadData() {
        this.presenter = new Presenter(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.reloadDataByNetChanged = true;
        loadData();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.reloadDataByNetChanged = false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    public boolean isReloadDataByNetChanged() {
        return this.reloadDataByNetChanged;
    }
}
