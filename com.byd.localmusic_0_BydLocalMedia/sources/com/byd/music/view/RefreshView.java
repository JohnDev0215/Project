package com.byd.music.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.byd.music.R;
import com.byd.music.view.ViewWarn;

/* JADX INFO: loaded from: classes5.dex */
public class RefreshView extends ConstraintLayout {
    private String TAG;
    LoadingView loadingView;
    ViewWarn viewWarn;

    public RefreshView(Context context) {
        super(context);
        this.TAG = RefreshView.class.getName();
        init();
    }

    public RefreshView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = RefreshView.class.getName();
        init();
    }

    public RefreshView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = RefreshView.class.getName();
        init();
    }

    private void init() {
        ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.view_refresh, (ViewGroup) this, true);
        this.loadingView = (LoadingView) findViewById(R.id.view_refresh_loading);
        this.viewWarn = (ViewWarn) findViewById(R.id.view_warn);
        setVisibility(8);
    }

    public void showLoading() {
        this.viewWarn.setVisibility(8);
        setVisibility(0);
        this.loadingView.setRotate(true);
    }

    public void showWarn(int i, String str, ViewWarn.OnClick onClick) {
        this.loadingView.setRotate(false);
        this.viewWarn.setVisibility(0);
        setVisibility(0);
        this.viewWarn.initNoneNetView(i, str, onClick);
    }

    public void hide() {
        this.loadingView.setRotate(false);
        setVisibility(8);
    }
}
