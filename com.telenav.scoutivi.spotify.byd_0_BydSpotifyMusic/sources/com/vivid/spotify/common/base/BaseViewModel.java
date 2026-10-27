package com.vivid.spotify.common.base;

import android.app.Application;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseViewModel extends androidx.lifecycle.b implements v3.a {
    protected t3.a rxExecutor;

    public BaseViewModel(Application application) {
        super(application);
        if (supportRxJava()) {
            this.rxExecutor = new t3.a(u3.a.c());
        }
        if (listenerLanguageChange() && (application instanceof e)) {
            ((e) application).g(this);
        }
    }

    protected boolean listenerLanguageChange() {
        return true;
    }

    @Override // androidx.lifecycle.a0
    protected void onCleared() {
        super.onCleared();
        t3.a aVar = this.rxExecutor;
        if (aVar != null) {
            aVar.b();
        }
        Application application = getApplication();
        if (listenerLanguageChange() && (application instanceof e)) {
            ((e) application).o(this);
        }
    }

    @Override // v3.a
    public void onLanguageChange(String str) {
    }

    protected boolean supportRxJava() {
        return true;
    }
}
