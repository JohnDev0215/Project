package com.spotify.automotive.accountmanager.service;

import android.content.Intent;
import android.os.IBinder;
import p.ff2;
import p.t50;

/* JADX INFO: loaded from: classes.dex */
public class SpotifyAccountAuthenticatorService extends t50 {
    public ff2 c;

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.c.getIBinder();
    }
}
