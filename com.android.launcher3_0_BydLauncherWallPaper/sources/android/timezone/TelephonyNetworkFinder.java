package android.timezone;

import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class TelephonyNetworkFinder {
    private final libcore.timezone.TelephonyNetworkFinder mDelegate;

    TelephonyNetworkFinder(libcore.timezone.TelephonyNetworkFinder telephonyNetworkFinder) {
        this.mDelegate = (libcore.timezone.TelephonyNetworkFinder) Objects.requireNonNull(telephonyNetworkFinder);
    }

    public TelephonyNetwork findNetworkByMccMnc(String str, String str2) {
        Objects.requireNonNull(str);
        Objects.requireNonNull(str2);
        libcore.timezone.TelephonyNetwork telephonyNetworkFindNetworkByMccMnc = this.mDelegate.findNetworkByMccMnc(str, str2);
        if (telephonyNetworkFindNetworkByMccMnc != null) {
            return new TelephonyNetwork(telephonyNetworkFindNetworkByMccMnc);
        }
        return null;
    }
}
