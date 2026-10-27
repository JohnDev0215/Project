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

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.time = System.currentTimeMillis();
        if (getActivity() instanceof BydActivity) {
            SkinInflaterFactory skinInflaterFactory = ((BydActivity) getActivity()).getSkinInflaterFactory();
            this.mSkinInflaterFactory = skinInflaterFactory;
            if (skinInflaterFactory != null) {
                skinInflaterFactory.setViewTag(this.time);
            }
        }
        return super.onCreateView(inflater, container, savedInstanceState);
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

    private void destroySkinResource() {
        SkinInflaterFactory skinInflaterFactory = this.mSkinInflaterFactory;
        if (skinInflaterFactory != null) {
            skinInflaterFactory.cleanFragmentItem(this.time);
        }
        this.mDynamicNewView = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
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

    @Override // com.bydux.api.common.views.skin.listener.IDynamicNewView
    public void dynamicAddSkinView(View view, List<DynamicAttr> attrList) {
        this.mDynamicNewView.dynamicAddSkinView(view, attrList);
    }

    @Override // com.bydux.api.common.views.skin.listener.IDynamicNewView
    public void dynamicAddSkinView(View view, String attrName, int attrResId) {
        this.mDynamicNewView.dynamicAddSkinView(view, attrName, attrResId);
    }
}
