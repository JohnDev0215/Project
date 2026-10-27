package com.android.server.telecom;

import android.content.Context;
import com.android.server.telecom.TelecomSystem;

/* JADX INFO: loaded from: classes.dex */
public interface HeadsetMediaButtonFactory {
    HeadsetMediaButton create(Context context, CallsManager callsManager, TelecomSystem.SyncRoot syncRoot);
}
