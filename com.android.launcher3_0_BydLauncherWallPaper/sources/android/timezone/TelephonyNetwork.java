package android.timezone;

import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class TelephonyNetwork {
    private final libcore.timezone.TelephonyNetwork mDelegate;

    TelephonyNetwork(libcore.timezone.TelephonyNetwork telephonyNetwork) {
        this.mDelegate = (libcore.timezone.TelephonyNetwork) Objects.requireNonNull(telephonyNetwork);
    }

    public String getMcc() {
        return this.mDelegate.getMcc();
    }

    public String getMnc() {
        return this.mDelegate.getMnc();
    }

    public String getCountryIsoCode() {
        return this.mDelegate.getCountryIsoCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.mDelegate.equals(((TelephonyNetwork) obj).mDelegate);
    }

    public int hashCode() {
        return Objects.hash(this.mDelegate);
    }

    public String toString() {
        return "TelephonyNetwork{mDelegate=" + this.mDelegate + '}';
    }
}
