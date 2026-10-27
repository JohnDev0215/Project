package com.byd.overseaappstore;

import android.app.Application;
import androidx.annotation.CallSuper;
import spi.g0;
import spi.nk;
import spi.w3;
import spi.xc;

/* JADX INFO: loaded from: classes.dex */
public abstract class Hilt_OverseaAppStoreApp extends Application implements xc {
    public boolean a = false;
    public final g0 b = new g0(new a());

    public class a implements w3 {
        public a() {
        }
    }

    @Override // spi.xc
    public final Object a() {
        return this.b.a();
    }

    @Override // android.app.Application
    @CallSuper
    public void onCreate() {
        if (!this.a) {
            this.a = true;
            ((nk) a()).h();
        }
        super.onCreate();
    }
}
