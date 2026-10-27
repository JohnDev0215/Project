package com.spotify.playerlimited.player.models;

import java.util.Arrays;
import p.c71;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public class GaiaState {
    public boolean a;
    public boolean b;
    public boolean c;
    public boolean d;
    public ConnectDevice[] e;

    @z61(name = "devices")
    public static /* synthetic */ void getDevices$annotations() {
    }

    @z61(name = "should_use_local_playback")
    public static /* synthetic */ void getLocalPlaybackEnabled$annotations() {
    }

    @z61(name = "is_active")
    public static /* synthetic */ void isActive$annotations() {
    }

    @z61(name = "is_active_on_same_device")
    public static /* synthetic */ void isActiveOnSameDevice$annotations() {
    }

    @z61(name = "is_connected")
    public static /* synthetic */ void isConnected$annotations() {
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GaiaState)) {
            return false;
        }
        GaiaState gaiaState = (GaiaState) obj;
        return this.a == gaiaState.a && this.b == gaiaState.b && this.c == gaiaState.c && this.d == gaiaState.d && Arrays.equals(this.e, gaiaState.e);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.e) + (Arrays.hashCode(new Object[]{Boolean.valueOf(this.a), Boolean.valueOf(this.b), Boolean.valueOf(this.c), Boolean.valueOf(this.d)}) * 31);
    }

    public final String toString() {
        return "{isActive=" + this.a + ", isActiveOnSameDevice=" + this.c + ", isConnected=" + this.b + ", localPlaybackEnabled=" + this.d + ", devices=" + Arrays.toString(this.e) + '}';
    }
}
