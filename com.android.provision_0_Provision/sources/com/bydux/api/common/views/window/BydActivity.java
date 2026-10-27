package com.bydux.api.common.views.window;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.bydux.api.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.skin.entity.DynamicAttr;
import com.bydux.api.common.views.skin.listener.IDynamicNewView;
import com.bydux.api.common.views.skin.listener.ISkinUpdate;
import com.bydux.api.common.views.skin.loader.SkinInflaterFactory;
import com.bydux.api.common.views.skin.loader.SkinManager;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BydActivity extends AppCompatActivity implements ISkinUpdate, IDynamicNewView {
    private final String TAG = getClass().getSimpleName();
    private SkinInflaterFactory mSkinInflaterFactory;

    protected String onWindowAction() {
        return null;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        this.mSkinInflaterFactory = new SkinInflaterFactory();
        getLayoutInflater().setFactory(this.mSkinInflaterFactory);
        overridePendingTransition(R.anim.anim_byd_activity_open_enter, R.anim.anim_byd_activity_open_exit);
        super.onCreate(savedInstanceState);
        SkinManager.getInstance().attach(this);
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_byd_activity_close_enter, R.anim.anim_byd_activity_close_exit);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        SkinManager.getInstance().detach(this);
        this.mSkinInflaterFactory.clean();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        TextUtils.isEmpty(onWindowAction());
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        TextUtils.isEmpty(onWindowAction());
    }

    public SkinInflaterFactory getSkinInflaterFactory() {
        return this.mSkinInflaterFactory;
    }

    @Override // com.bydux.api.common.views.skin.listener.ISkinUpdate
    public void onSkinUpdate() {
        BydLogHelper.logV(this.TAG, "onSkinUpdate", "skin=" + SkinManager.getInstance().getSkinPath());
        this.mSkinInflaterFactory.applySkin();
    }

    @Override // com.bydux.api.common.views.skin.listener.IDynamicNewView
    public void dynamicAddSkinView(View view, List<DynamicAttr> attrList) {
        this.mSkinInflaterFactory.dynamicAddSkinView(this, view, attrList);
    }

    @Override // com.bydux.api.common.views.skin.listener.IDynamicNewView
    public void dynamicAddSkinView(View view, String attrName, int attrResId) {
        this.mSkinInflaterFactory.dynamicAddSkinView(this, view, attrName, attrResId);
    }
}
