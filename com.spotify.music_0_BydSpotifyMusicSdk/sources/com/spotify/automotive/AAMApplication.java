package com.spotify.automotive;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.spotify.base.java.logging.LogLevel;
import com.spotify.base.java.logging.Logger;
import com.spotify.voiceassistants.playermodels.SpeakeasyPlayerModelsMoshiAdaptersModule;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import p.b51;
import p.e91;
import p.h50;
import p.hc1;
import p.hj2;
import p.hk0;
import p.ia0;
import p.ik;
import p.jc1;
import p.jk;
import p.kk;
import p.lc;
import p.m11;
import p.q50;
import p.qo;
import p.v03;
import p.zb0;

/* JADX INFO: loaded from: classes.dex */
public class AAMApplication extends Application implements m11 {
    public ia0 c;

    public final void a() {
        super.onCreate();
        EnumSet enumSetOf = EnumSet.of(LogLevel.k, LogLevel.l, LogLevel.m, LogLevel.n);
        hc1[] hc1VarArr = {new jc1(enumSetOf), new hj2(new qo(), enumSetOf)};
        List list = Logger.a;
        hk0 hk0Var = new hk0(26);
        zb0 zb0Var = new zb0();
        Logger.a = Collections.unmodifiableList(Arrays.asList(hc1VarArr));
        Logger.b = hk0Var;
        Logger.c = zb0Var;
        RxJavaPlugins.a = new ik();
        FirebaseCrashlytics firebaseCrashlytics = FirebaseCrashlytics.getInstance();
        v03.i(firebaseCrashlytics, "getInstance()");
        lc.q(new e91(new jk(firebaseCrashlytics)));
    }

    @Override // android.content.ContextWrapper
    public final void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        b51 b51Var = q50.a;
        this.c = new h50(new zb0(), new SpeakeasyPlayerModelsMoshiAdaptersModule(), this).b();
    }

    @Override // p.m11
    public final ia0 b() {
        return this.c;
    }

    @Override // android.app.Application
    public final void onCreate() {
        a();
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
            int i = kk.a;
            Logger.b("Spotify AAM Version = 4.4.1.0", new Object[0]);
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
            throw th;
        }
    }
}
