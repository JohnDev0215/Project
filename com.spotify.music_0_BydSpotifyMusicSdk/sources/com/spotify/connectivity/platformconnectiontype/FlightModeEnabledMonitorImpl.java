package com.spotify.connectivity.platformconnectiontype;

import android.content.Context;
import android.provider.Settings;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class FlightModeEnabledMonitorImpl extends FlightModeEnabledMonitorAbstract {
    private final Context appContext;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlightModeEnabledMonitorImpl(Context context) {
        super(context);
        v03.j(context, "context");
        this.appContext = context.getApplicationContext();
    }

    @Override // com.spotify.connectivity.platformconnectiontype.FlightModeEnabledMonitor
    public boolean isFlightModeEnabledCurrently() {
        return Settings.Global.getInt(this.appContext.getContentResolver(), "airplane_mode_on", 0) != 0;
    }
}
