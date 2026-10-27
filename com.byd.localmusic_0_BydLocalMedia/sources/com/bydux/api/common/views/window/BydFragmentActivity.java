package com.bydux.api.common.views.window;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.skin.entity.DynamicAttr;
import com.bydux.api.common.views.skin.listener.IDynamicNewView;
import com.bydux.api.common.views.skin.listener.ISkinUpdate;
import com.bydux.api.common.views.skin.loader.SkinInflaterFactory;
import com.bydux.api.common.views.skin.loader.SkinManager;
import java.lang.reflect.Field;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class BydFragmentActivity extends FragmentActivity implements ISkinUpdate, IDynamicNewView {
    private final String TAG = getClass().getSimpleName();
    private SkinInflaterFactory mSkinInflaterFactory;

    protected String onWindowAction() {
        return null;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Field declaredField = LayoutInflater.class.getDeclaredField("mFactorySet");
            declaredField.setAccessible(true);
            declaredField.setBoolean(getLayoutInflater(), false);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (NoSuchFieldException e3) {
            e3.printStackTrace();
        }
        this.mSkinInflaterFactory = new SkinInflaterFactory();
        getLayoutInflater().setFactory(this.mSkinInflaterFactory);
        SkinManager.getInstance().attach(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        SkinManager.getInstance().detach(this);
        this.mSkinInflaterFactory.clean();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
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
