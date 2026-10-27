package com.spotify.connectivity.sessionservice;

import com.spotify.connectivity.auth.NativeSession;
import com.spotify.connectivity.sessionapi.SharedNativeSession;
import p.r10;
import p.u10;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class DefaultSharedNativeSession implements SharedNativeSession {
    private final r10 coreThreadingApi;
    private NativeSession internalNativeSession;
    private int refCount;

    public DefaultSharedNativeSession(NativeSession nativeSession, r10 r10Var) {
        v03.j(nativeSession, "nativeSession");
        v03.j(r10Var, "coreThreadingApi");
        this.coreThreadingApi = r10Var;
        this.internalNativeSession = nativeSession;
        this.refCount = 1;
    }

    @Override // com.spotify.connectivity.sessionapi.SharedNativeSession
    public synchronized NativeSession acquire() {
        this.refCount++;
        return this.internalNativeSession;
    }

    @Override // com.spotify.connectivity.sessionapi.SharedNativeSession
    public synchronized boolean alive() {
        return this.refCount != 0;
    }

    public final r10 getCoreThreadingApi() {
        return this.coreThreadingApi;
    }

    @Override // com.spotify.connectivity.sessionapi.SharedNativeSession
    public synchronized void release() {
        try {
            int i = this.refCount - 1;
            this.refCount = i;
            if (i == 0) {
                if (((u10) this.coreThreadingApi).a.isCurrentThread()) {
                    this.internalNativeSession.destroy();
                } else {
                    ((u10) this.coreThreadingApi).a.runBlocking(new Runnable() { // from class: com.spotify.connectivity.sessionservice.DefaultSharedNativeSession.release.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            DefaultSharedNativeSession.this.internalNativeSession.destroy();
                        }
                    });
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}
