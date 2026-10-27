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

/* JADX INFO: loaded from: classes3.dex */
public class BydFragmentActivity extends FragmentActivity implements ISkinUpdate, IDynamicNewView {
    private final String TAG = getClass().getSimpleName();
    private SkinInflaterFactory mSkinInflaterFactory;

    @Override // com.bydux.api.common.views.skin.listener.IDynamicNewView
    public void dynamicAddSkinView(View view, List<DynamicAttr> list) {
        this.mSkinInflaterFactory.dynamicAddSkinView(this, view, list);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            Field declaredField = LayoutInflater.class.getDeclaredField("mFactorySet");
            declaredField.setAccessible(true);
            declaredField.setBoolean(getLayoutInflater(), false);
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
        } catch (NoSuchFieldException e4) {
            e4.printStackTrace();
        }
        this.mSkinInflaterFactory = new SkinInflaterFactory();
        getLayoutInflater().setFactory(this.mSkinInflaterFactory);
        SkinManager.getInstance().attach(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
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

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
    }

    public String onWindowAction() {
        return null;
    }

    @Override // com.bydux.api.common.views.skin.listener.IDynamicNewView
    public void dynamicAddSkinView(View view, String str, int i) {
        this.mSkinInflaterFactory.dynamicAddSkinView(this, view, str, i);
    }
}
