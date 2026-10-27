package com.byd.dipilot;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;
import b.b.a.i.a;
import b.b.m.e.g;
import b.b.m.e.h;
import com.bydux.api.common.views.window.BydFragment;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseFragment<V extends ViewDataBinding, P extends g> extends BydFragment implements h<P> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public V f3615c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public P f3616d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ViewGroup f3617e;
    public boolean h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f3613a = getClass().getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f3614b = 2000;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f3618f = true;
    public boolean g = false;

    public BaseFragment() {
        Bundle bundle = new Bundle();
        bundle.putString(getClass().getName(), getClass().getName());
        setArguments(bundle);
    }

    public FragmentActivity d() {
        return requireActivity();
    }

    public abstract int getLayoutId();

    @Override // b.b.m.e.h
    public P getRefP() {
        return this.f3616d;
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        int layoutId = getLayoutId();
        if (a.f531a && "ActiveSafeFragment".equals(this.f3613a)) {
            this.f3615c = (V) DataBindingUtil.bind(u(layoutId));
        } else {
            this.f3615c = (V) DataBindingUtil.inflate(layoutInflater, layoutId, viewGroup, false);
        }
        this.f3617e = (ViewGroup) this.f3615c.getRoot();
        Context context = layoutInflater.getContext();
        w(context);
        v(context);
        return this.f3617e;
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        if (mo7getP() != null) {
            mo7getP().unbindV();
        }
        this.f3616d = null;
        this.f3617e = null;
        this.g = false;
        super.onDestroyView();
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onResume() {
        this.f3618f = false;
        super.onResume();
        if (this.g) {
            t();
        }
    }

    @Override // b.b.m.e.h
    public void setRefP(P p) {
        this.f3616d = p;
    }

    public abstract void t();

    public final View u(int i) {
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale = Locale.getDefault();
        configuration.setLocale(new Locale("rr", "KP"));
        resources.updateConfiguration(configuration, null);
        XmlResourceParser layout = resources.getLayout(i);
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, null);
        return getLayoutInflater().inflate(layout, (ViewGroup) null);
    }

    public abstract void v(Context context);

    public abstract void w(Context context);
}
