package com.byd.card.base;

import android.os.Bundle;
import com.byd.card.base.BasePresenter;
import com.byd.card.view.activity.ActivityCollector;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.window.BydActivity;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseMvpActivity<P extends BasePresenter> extends BydActivity implements IView {
    private static final String TAG = "BaseMvpActivity";
    protected P mPresenter;

    protected abstract P createPresenter();

    protected abstract int getLayoutId();

    protected abstract void initView();

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this, getClass());
        try {
            setContentView(getLayoutId());
        } catch (Exception e) {
            BydLogHelper.logE(TAG, "e = " + e.getCause());
        }
        initPresenter();
        initView();
    }

    private void initPresenter() {
        P p = (P) createPresenter();
        this.mPresenter = p;
        if (p != null) {
            p.attachView(this);
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
        P p = this.mPresenter;
        if (p != null) {
            p.detachView();
            this.mPresenter = null;
        }
    }
}
