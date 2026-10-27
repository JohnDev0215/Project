package com.byd.overseaappstore.ui;

import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;
import com.byd.overseaappstore.ui.view.base.BaseActivity;
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory;
import java.util.Objects;
import java.util.Set;
import spi.b6;
import spi.ne;
import spi.t8;
import spi.xc;

/* JADX INFO: loaded from: classes.dex */
public abstract class Hilt_MainActivity<VB extends ViewBinding> extends BaseActivity<VB> implements xc {
    public volatile spi.j c;
    public final Object d = new Object();
    public boolean e = false;

    public Hilt_MainActivity() {
        addOnContextAvailableListener(new ne(this));
    }

    @Override // spi.xc
    public final Object a() {
        if (this.c == null) {
            synchronized (this.d) {
                if (this.c == null) {
                    this.c = new spi.j(this);
                }
            }
        }
        return this.c.a();
    }

    @Override // androidx.activity.ComponentActivity, androidx.lifecycle.HasDefaultViewModelProviderFactory
    public final ViewModelProvider.Factory getDefaultViewModelProviderFactory() {
        ViewModelProvider.Factory defaultViewModelProviderFactory = super.getDefaultViewModelProviderFactory();
        b6.c cVarA = ((b6.a) t8.a(this, b6.a.class)).a();
        Objects.requireNonNull(cVarA);
        Bundle extras = getIntent() != null ? getIntent().getExtras() : null;
        Set<String> set = cVarA.a;
        Objects.requireNonNull(defaultViewModelProviderFactory);
        return new HiltViewModelFactory(this, extras, set, defaultViewModelProviderFactory, cVarA.b);
    }
}
