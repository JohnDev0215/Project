package io.sentry;

import h8.AbstractC1386c;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class S0 implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ F1 f15942m;

    public /* synthetic */ S0(F1 f12, int i7) {
        this.l = i7;
        this.f15942m = f12;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                this.f15942m.loadLazyFields();
                return;
            case 1:
                F1 f12 = this.f15942m;
                String cacheDirPathWithoutDsn = f12.getCacheDirPathWithoutDsn();
                if (cacheDirPathWithoutDsn != null) {
                    File file = new File(cacheDirPathWithoutDsn, "app_start_profiling_config");
                    try {
                        AbstractC1386c.g(file);
                        if (f12.isEnableAppStartProfiling()) {
                            if (!f12.isTracingEnabled()) {
                                f12.getLogger().k(EnumC1495p1.INFO, "Tracing is disabled and app start profiling will not start.", new Object[0]);
                                return;
                            }
                            if (file.createNewFile()) {
                                V0 v02 = new V0(f12, new L1(f12, 1).c(new e7.f(new b2("app.launch", io.sentry.protocol.C.CUSTOM, "profile", null), 11)));
                                FileOutputStream fileOutputStream = new FileOutputStream(file);
                                try {
                                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, U0.d));
                                    try {
                                        f12.getSerializer().f(v02, bufferedWriter);
                                        bufferedWriter.close();
                                        fileOutputStream.close();
                                        return;
                                    } finally {
                                        try {
                                            break;
                                        } catch (Throwable th) {
                                        }
                                    }
                                } finally {
                                    try {
                                        break;
                                    } catch (Throwable th2) {
                                    }
                                }
                            }
                            return;
                        }
                        return;
                    } catch (Throwable th3) {
                        f12.getLogger().s(EnumC1495p1.ERROR, "Unable to create app start profiling config file. ", th3);
                        return;
                    }
                }
                return;
            case 2:
                F1 f13 = this.f15942m;
                for (M m10 : f13.getOptionsObservers()) {
                    String release = f13.getRelease();
                    io.sentry.cache.e eVar = (io.sentry.cache.e) m10;
                    if (release == null) {
                        eVar.a("release.json");
                    } else {
                        eVar.b(release, "release.json");
                    }
                    String proguardUuid = f13.getProguardUuid();
                    if (proguardUuid == null) {
                        eVar.a("proguard-uuid.json");
                    } else {
                        eVar.b(proguardUuid, "proguard-uuid.json");
                    }
                    io.sentry.protocol.r sdkVersion = f13.getSdkVersion();
                    if (sdkVersion == null) {
                        eVar.a("sdk-version.json");
                    } else {
                        eVar.b(sdkVersion, "sdk-version.json");
                    }
                    String dist = f13.getDist();
                    if (dist == null) {
                        eVar.a("dist.json");
                    } else {
                        eVar.b(dist, "dist.json");
                    }
                    String environment = f13.getEnvironment();
                    if (environment == null) {
                        eVar.a("environment.json");
                    } else {
                        eVar.b(environment, "environment.json");
                    }
                    eVar.b(f13.getTags(), "tags.json");
                    Double d = f13.getExperimental().f16913a.f15881b;
                    if (d == null) {
                        eVar.a("replay-error-sample-rate.json");
                    } else {
                        eVar.b(d.toString(), "replay-error-sample-rate.json");
                    }
                }
                return;
            default:
                E.f15834a.h(this.f15942m.getFlushTimeoutMillis());
                return;
        }
    }
}
