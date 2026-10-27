package com.android.server.telecom;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public interface InCallWakeLockControllerFactory {
    InCallWakeLockController create(Context context, CallsManager callsManager);
}
