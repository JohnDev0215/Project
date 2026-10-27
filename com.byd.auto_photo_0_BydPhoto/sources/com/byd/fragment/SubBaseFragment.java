package com.byd.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import b.a.k.h;
import com.byd.auto_photo.PhotoApp;
import com.byd.ui.a;

/* JADX INFO: loaded from: classes2.dex */
public abstract class SubBaseFragment extends a implements h.b {
    private String currentLauge;
    private h externalLoaderManager;
    protected boolean listenExternalState = true;

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.locale.toLanguageTag().equals(this.currentLauge)) {
            return;
        }
        this.currentLauge = configuration.locale.toLanguageTag();
        onLocaleChanged(configuration);
    }

    @Override // com.byd.ui.a, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (this.listenExternalState) {
            h hVarE = h.e(getActivity());
            this.externalLoaderManager = hVarE;
            hVarE.m(this);
        }
    }

    @Override // com.byd.ui.a, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.currentLauge = getActivity().getResources().getConfiguration().locale.toLanguageTag();
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.byd.ui.a, androidx.fragment.app.Fragment
    public void onDestroy() {
        if (this.listenExternalState) {
            this.externalLoaderManager.o(this);
            this.externalLoaderManager = null;
        }
        super.onDestroy();
    }

    public abstract /* synthetic */ void onExternalEject(String str, boolean z);

    protected void onLocaleChanged(Configuration configuration) {
    }

    protected void toastMsg(int i) {
        toastMsg(i, false);
    }

    protected void toastMsg(int i, boolean z) {
        if (getActivity() == null) {
            return;
        }
        ((PhotoApp) getActivity().getApplication()).g(getActivity(), i, z);
    }
}
