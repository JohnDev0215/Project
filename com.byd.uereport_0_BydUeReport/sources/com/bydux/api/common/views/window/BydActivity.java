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

/* JADX INFO: loaded from: classes2.dex */
public class BydActivity extends AppCompatActivity implements ISkinUpdate, IDynamicNewView {
    private final String TAG = getClass().getSimpleName();
    private SkinInflaterFactory mSkinInflaterFactory;

    @Override // com.bydux.api.common.views.skin.listener.IDynamicNewView
    public void dynamicAddSkinView(View view, List<DynamicAttr> list) {
        this.mSkinInflaterFactory.dynamicAddSkinView(this, view, list);
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_byd_activity_close_enter, R.anim.anim_byd_activity_close_exit);
    }

    public SkinInflaterFactory getSkinInflaterFactory() {
        return this.mSkinInflaterFactory;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.mSkinInflaterFactory = new SkinInflaterFactory();
        getLayoutInflater().setFactory(this.mSkinInflaterFactory);
        overridePendingTransition(R.anim.anim_byd_activity_open_enter, R.anim.anim_byd_activity_open_exit);
        super.onCreate(bundle);
        SkinManager.getInstance().attach(this);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        SkinManager.getInstance().detach(this);
        this.mSkinInflaterFactory.clean();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        TextUtils.isEmpty(onWindowAction());
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        TextUtils.isEmpty(onWindowAction());
    }

    @Override // com.bydux.api.common.views.skin.listener.ISkinUpdate
    public void onSkinUpdate() {
        BydLogHelper.logV(this.TAG, "onSkinUpdate", "skin=" + SkinManager.getInstance().getSkinPath());
        this.mSkinInflaterFactory.applySkin();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
    }

    public String onWindowAction() {
        return null;
    }

    @Override // com.bydux.api.common.views.skin.listener.IDynamicNewView
    public void dynamicAddSkinView(View view, String str, int i8) {
        this.mSkinInflaterFactory.dynamicAddSkinView(this, view, str, i8);
    }
}
