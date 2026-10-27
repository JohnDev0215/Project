package com.bydux.api.common.views.window;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.bydux.api.common.views.skin.entity.DynamicAttr;
import com.bydux.api.common.views.skin.listener.IDynamicNewView;
import com.bydux.api.common.views.skin.loader.SkinInflaterFactory;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BydFragment extends Fragment implements IDynamicNewView {
    private IDynamicNewView mDynamicNewView;
    private SkinInflaterFactory mSkinInflaterFactory;
    private long time = -1;

    private void destroySkinResource() {
        SkinInflaterFactory skinInflaterFactory = this.mSkinInflaterFactory;
        if (skinInflaterFactory != null) {
            skinInflaterFactory.cleanFragmentItem(this.time);
        }
        this.mDynamicNewView = null;
    }

    @Override // com.bydux.api.common.views.skin.listener.IDynamicNewView
    public void dynamicAddSkinView(View view, List<DynamicAttr> list) {
        this.mDynamicNewView.dynamicAddSkinView(view, list);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.mDynamicNewView = (IDynamicNewView) context;
        } catch (ClassCastException unused) {
            this.mDynamicNewView = null;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.time = System.currentTimeMillis();
        if (getActivity() instanceof BydActivity) {
            SkinInflaterFactory skinInflaterFactory = ((BydActivity) getActivity()).getSkinInflaterFactory();
            this.mSkinInflaterFactory = skinInflaterFactory;
            if (skinInflaterFactory != null) {
                skinInflaterFactory.setViewTag(this.time);
            }
        }
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        destroySkinResource();
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
    }

    @Override // com.bydux.api.common.views.skin.listener.IDynamicNewView
    public void dynamicAddSkinView(View view, String str, int i) {
        this.mDynamicNewView.dynamicAddSkinView(view, str, i);
    }
}
