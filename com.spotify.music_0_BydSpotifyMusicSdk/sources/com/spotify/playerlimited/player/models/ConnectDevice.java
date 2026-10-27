package com.spotify.playerlimited.player.models;

import com.spotify.connectivity.productstate.RxProductState;
import java.util.Arrays;
import p.c71;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class ConnectDevice {
    public boolean a;
    public boolean b;
    public String c;
    public String d;
    public String e;
    public String f;

    @z61(name = "identifier")
    public static /* synthetic */ void getIdentifier$annotations() {
    }

    @z61(name = "name")
    public static /* synthetic */ void getName$annotations() {
    }

    @z61(name = "physical_identifier")
    public static /* synthetic */ void getPhysicalIdentifier$annotations() {
    }

    @z61(name = RxProductState.Keys.KEY_TYPE)
    public static /* synthetic */ void getType$annotations() {
    }

    @z61(name = "is_active")
    public static /* synthetic */ void isActive$annotations() {
    }

    @z61(name = "is_self")
    public static /* synthetic */ void isSelf$annotations() {
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ConnectDevice)) {
            return false;
        }
        ConnectDevice connectDevice = (ConnectDevice) obj;
        return this.a == connectDevice.a && this.b == connectDevice.b && v03.b(this.c, connectDevice.c) && v03.b(this.e, connectDevice.e) && v03.b(this.d, connectDevice.d) && v03.b(this.f, connectDevice.f);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Boolean.valueOf(this.a), Boolean.valueOf(this.b), this.c, this.e, this.d, this.f});
    }

    public final String toString() {
        return "{isActive=" + this.a + ", isSelf=" + this.b + ", name=" + this.c + ", identifier=" + this.e + ", physicalIdentifier=" + this.d + "}, type=" + this.f;
    }
}
