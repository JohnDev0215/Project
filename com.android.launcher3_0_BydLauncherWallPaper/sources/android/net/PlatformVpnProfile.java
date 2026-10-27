package android.net;

import com.android.internal.net.VpnProfile;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class PlatformVpnProfile {
    public static final int MAX_MTU_DEFAULT = 1360;
    public static final int TYPE_IKEV2_IPSEC_PSK = 7;
    public static final int TYPE_IKEV2_IPSEC_RSA = 8;
    public static final int TYPE_IKEV2_IPSEC_USER_PASS = 6;
    protected final int mType;

    @Retention(RetentionPolicy.SOURCE)
    public @interface PlatformVpnType {
    }

    public abstract VpnProfile toVpnProfile() throws GeneralSecurityException, IOException;

    PlatformVpnProfile(int i) {
        this.mType = i;
    }

    public final int getType() {
        return this.mType;
    }

    public final String getTypeString() {
        int i = this.mType;
        return i != 6 ? i != 7 ? i != 8 ? "Unknown VPN profile type" : "IKEv2/IPsec RSA Digital Signature" : "IKEv2/IPsec Preshared key" : "IKEv2/IPsec Username/Password";
    }

    public static PlatformVpnProfile fromVpnProfile(VpnProfile vpnProfile) throws GeneralSecurityException, IOException {
        int i = vpnProfile.type;
        if (i == 6 || i == 7 || i == 8) {
            return Ikev2VpnProfile.fromVpnProfile(vpnProfile);
        }
        throw new IllegalArgumentException("Unknown VPN Profile type");
    }
}
