package com.android.server.telecom;

import android.content.Context;
import com.android.server.telecom.TelecomSystem;
import com.android.server.telecom.Timeouts;

/* JADX INFO: loaded from: classes.dex */
public interface InCallControllerFactory {
    InCallController create(Context context, TelecomSystem.SyncRoot syncRoot, CallsManager callsManager, SystemStateHelper systemStateHelper, DefaultDialerCache defaultDialerCache, Timeouts.Adapter adapter, EmergencyCallHelper emergencyCallHelper);
}
