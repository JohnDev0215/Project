package com.stingray.karaoke.car.ui.splash;

import H7.InterfaceC0318c;
import I5.m;
import N1.C0420a;
import O6.d;
import Q6.b;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.lifecycle.h0;
import b2.C0993c;
import com.stingray.karaoke.car.MainActivity;
import h.n;
import kotlin.Metadata;
import l3.P;
import n7.z;
import w0.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/stingray/karaoke/car/ui/splash/SplashActivity;", "Lh/n;", "<init>", "()V", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SuppressLint({"CustomSplashScreen"})
public final class SplashActivity extends n implements b {

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public C0420a f13031F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public volatile O6.b f13032G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final Object f13033H = new Object();

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public boolean f13034I = false;

    public SplashActivity() {
        i(new m(this, 1));
    }

    @Override // Q6.b
    public final Object c() {
        return m().c();
    }

    @Override // h.n, androidx.lifecycle.InterfaceC0977o
    public final h0 d() {
        return c.N(this, super.d());
    }

    public final O6.b m() {
        if (this.f13032G == null) {
            synchronized (this.f13033H) {
                try {
                    if (this.f13032G == null) {
                        this.f13032G = new O6.b(this, 0);
                    }
                } finally {
                }
            }
        }
        return this.f13032G;
    }

    public final void n(Bundle bundle) {
        super.onCreate(bundle);
        if (getApplication() instanceof b) {
            O6.b bVar = (O6.b) m().o;
            n nVar = bVar.f5162m;
            P p10 = new P(nVar.f(), new N6.c((n) bVar.o, 1), nVar.e());
            InterfaceC0318c interfaceC0318cL = z.l(d.class);
            String strA = interfaceC0318cL.a();
            if (strA == null) {
                throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
            }
            C0420a c0420a = ((d) p10.s(interfaceC0318cL, "androidx.lifecycle.ViewModelProvider.DefaultKey:".concat(strA))).c;
            this.f13031F = c0420a;
            if (((C0993c) c0420a.l) == null) {
                c0420a.l = (C0993c) e();
            }
        }
    }

    @Override // h.n, android.app.Activity
    public final void onCreate(Bundle bundle) {
        n(bundle);
        startActivity(new Intent(this, (Class<?>) MainActivity.class));
        finish();
    }

    @Override // android.app.Activity
    public final void onDestroy() {
        super.onDestroy();
        C0420a c0420a = this.f13031F;
        if (c0420a != null) {
            c0420a.l = null;
        }
    }
}
