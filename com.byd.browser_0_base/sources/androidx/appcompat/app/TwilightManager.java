package androidx.appcompat.app;

import android.content.Context;
import android.location.LocationManager;

/* JADX INFO: loaded from: classes.dex */
class TwilightManager {
    public static TwilightManager d;
    public final Context a;
    public final LocationManager b;
    public final TwilightState c = new TwilightState();

    public static class TwilightState {
        public boolean a;
        public long b;
    }

    public TwilightManager(Context context, LocationManager locationManager) {
        this.a = context;
        this.b = locationManager;
    }
}
