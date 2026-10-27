package ts.car.usb.v1.usb;

import android.hardware.usb.V1_0.PortRole;

/* JADX INFO: loaded from: classes.dex */
public class UsbPortRole {
    public int role;
    public int type;

    public PortRole convert() {
        PortRole role = new PortRole();
        role.type = this.type;
        role.role = this.role;
        return role;
    }

    UsbPortRole clone(PortRole role) {
        this.type = role.type;
        this.role = role.role;
        return this;
    }
}
