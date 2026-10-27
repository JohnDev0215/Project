package defpackage;

import android.os.Parcel;

/* JADX INFO: renamed from: Eg, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractBinderC0078Eg extends AbstractBinderC0401Wg implements InterfaceC0096Fg {
    public AbstractBinderC0078Eg() {
        super("com.google.android.gms.flags.IFlagProvider");
    }

    @Override // defpackage.AbstractBinderC0401Wg
    public boolean u(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            init(AbstractBinderC0503ag.v(parcel.readStrongBinder()));
            parcel2.writeNoException();
        } else if (i == 2) {
            boolean booleanFlagValue = getBooleanFlagValue(parcel.readString(), AbstractC0383Vg.e(parcel), parcel.readInt());
            parcel2.writeNoException();
            parcel2.writeInt(booleanFlagValue ? 1 : 0);
        } else if (i == 3) {
            int intFlagValue = getIntFlagValue(parcel.readString(), parcel.readInt(), parcel.readInt());
            parcel2.writeNoException();
            parcel2.writeInt(intFlagValue);
        } else if (i == 4) {
            long longFlagValue = getLongFlagValue(parcel.readString(), parcel.readLong(), parcel.readInt());
            parcel2.writeNoException();
            parcel2.writeLong(longFlagValue);
        } else {
            if (i != 5) {
                return false;
            }
            String stringFlagValue = getStringFlagValue(parcel.readString(), parcel.readString(), parcel.readInt());
            parcel2.writeNoException();
            parcel2.writeString(stringFlagValue);
        }
        return true;
    }
}
