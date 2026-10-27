package com.byd.overseaappstore.ui.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.annotation.CallSuper;
import androidx.annotation.MainThread;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;
import com.byd.overseaappstore.ui.view.base.BaseFragment;
import dagger.hilt.android.internal.managers.ViewComponentManager$FragmentContextWrapper;
import spi.b6;
import spi.bl;
import spi.kl;
import spi.oc;
import spi.pc;
import spi.xc;

/* JADX INFO: loaded from: classes.dex */
public abstract class Hilt_ProtocolFragment<VB extends ViewBinding> extends BaseFragment<VB> implements xc {
    public ViewComponentManager$FragmentContextWrapper c;
    public boolean d;
    public volatile oc e;
    public final Object f = new Object();
    public boolean g = false;

    @Override // spi.xc
    public final Object a() {
        if (this.e == null) {
            synchronized (this.f) {
                if (this.e == null) {
                    this.e = new oc(this);
                }
            }
        }
        return this.e.a();
    }

    @Override // androidx.fragment.app.Fragment
    public final Context getContext() {
        if (super.getContext() == null && !this.d) {
            return null;
        }
        h();
        return this.c;
    }

    @Override // androidx.fragment.app.Fragment, androidx.lifecycle.HasDefaultViewModelProviderFactory
    public final ViewModelProvider.Factory getDefaultViewModelProviderFactory() {
        return b6.a(this, super.getDefaultViewModelProviderFactory());
    }

    public final void h() {
        if (this.c == null) {
            this.c = new ViewComponentManager$FragmentContextWrapper(super.getContext(), this);
            this.d = pc.a(super.getContext());
        }
    }

    @Override // androidx.fragment.app.Fragment
    @CallSuper
    @MainThread
    public final void onAttach(Activity activity) {
        super.onAttach(activity);
        ViewComponentManager$FragmentContextWrapper viewComponentManager$FragmentContextWrapper = this.c;
        bl.a(viewComponentManager$FragmentContextWrapper == null || oc.c(viewComponentManager$FragmentContextWrapper) == activity, "onAttach called multiple times with different Context! Hilt Fragments should not be retained.", new Object[0]);
        h();
        if (this.g) {
            return;
        }
        this.g = true;
        ((kl) a()).f();
    }

    @Override // androidx.fragment.app.Fragment
    public final LayoutInflater onGetLayoutInflater(Bundle bundle) {
        LayoutInflater layoutInflaterOnGetLayoutInflater = super.onGetLayoutInflater(bundle);
        return layoutInflaterOnGetLayoutInflater.cloneInContext(new ViewComponentManager$FragmentContextWrapper(layoutInflaterOnGetLayoutInflater, this));
    }

    @Override // androidx.fragment.app.Fragment
    @CallSuper
    public final void onAttach(Context context) {
        super.onAttach(context);
        h();
        if (this.g) {
            return;
        }
        this.g = true;
        ((kl) a()).f();
    }
}
