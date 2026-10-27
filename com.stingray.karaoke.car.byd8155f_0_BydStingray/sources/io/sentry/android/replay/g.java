package io.sentry.android.replay;

import io.sentry.EnumC1495p1;
import io.sentry.F1;
import java.io.File;
import java.io.IOException;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class g extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f16395m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ i f16396n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ g(i iVar, int i7) {
        super(0);
        this.f16395m = i7;
        this.f16396n = iVar;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() throws IOException {
        switch (this.f16395m) {
            case 0:
                i iVar = this.f16396n;
                if (iVar.g() == null) {
                    return null;
                }
                File file = new File(iVar.g(), ".ongoing_segment");
                if (!file.exists()) {
                    file.createNewFile();
                }
                return file;
            default:
                i iVar2 = this.f16396n;
                F1 f12 = iVar2.l;
                A7.m.f(f12, "options");
                io.sentry.protocol.t tVar = iVar2.f16404m;
                A7.m.f(tVar, "replayId");
                String cacheDirPath = f12.getCacheDirPath();
                if (cacheDirPath == null || cacheDirPath.length() == 0) {
                    f12.getLogger().k(EnumC1495p1.WARNING, "SentryOptions.cacheDirPath is not set, session replay is no-op", new Object[0]);
                    return null;
                }
                String cacheDirPath2 = f12.getCacheDirPath();
                A7.m.c(cacheDirPath2);
                File file2 = new File(cacheDirPath2, "replay_" + tVar);
                file2.mkdirs();
                return file2;
        }
    }
}
