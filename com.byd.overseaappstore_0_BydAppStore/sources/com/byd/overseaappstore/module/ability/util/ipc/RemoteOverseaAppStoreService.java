package com.byd.overseaappstore.module.ability.util.ipc;

import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.ct;
import spi.fm;
import spi.hm;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/byd/overseaappstore/module/ability/util/ipc/RemoteOverseaAppStoreService;", "Landroid/app/Service;", "<init>", "()V", "module_ability_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class RemoteOverseaAppStoreService extends Hilt_RemoteOverseaAppStoreService {
    public hm d;

    public static final class a extends fm {
        public a() {
        }
    }

    @Override // android.app.Service
    @NotNull
    public final IBinder onBind(@NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        ct.a("remoteWork server onBind", new Object[0]);
        return new a();
    }

    @Override // com.byd.overseaappstore.module.ability.util.ipc.Hilt_RemoteOverseaAppStoreService, android.app.Service
    public final void onCreate() {
        super.onCreate();
        ct.a("remoteWork server onCreate", new Object[0]);
    }

    @Override // android.app.Service
    public final boolean onUnbind(@Nullable Intent intent) {
        ct.a("remoteWork server onUnbind", new Object[0]);
        return super.onUnbind(intent);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final void unbindService(@NotNull ServiceConnection conn) {
        Intrinsics.checkNotNullParameter(conn, "conn");
        super.unbindService(conn);
        ct.a("remoteWork server unbindService", new Object[0]);
    }
}
