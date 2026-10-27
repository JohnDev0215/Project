package RR9QRrr.RR9QRrr.RR9QRrr.Rq9rq;

import android.content.Context;
import android.net.NetworkPolicyManager;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.net.IDiLinkNetworkPolicyManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_RCS})
@ServiceImpl(service = IDiLinkNetworkPolicyManager.class, singleton = true)
public class RR9QRrr implements IDiLinkNetworkPolicyManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final NetworkPolicyManager f117a;

    public RR9QRrr(Context context) {
        this.f117a = NetworkPolicyManager.from(context);
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public void disableNat(String str, String str2) {
        this.f117a.disableNat(str, str2);
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public void enableNat(String str, String str2) {
        this.f117a.enableNat(str, str2);
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public void enableWhiteRules(boolean z, boolean z2) {
        throw new UnsupportedTargetPlatformException("rcs不存在此接口");
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public int[] getUidsWithPolicy(int i) {
        return this.f117a.getUidsWithPolicy(i);
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public boolean getWhiteListStats() {
        throw new UnsupportedTargetPlatformException("rcs不存在此接口");
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public void setMeteredRestrictedUid(int i, boolean z) {
        this.f117a.setMeteredRestrictedUid(i, z);
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public void setUidPolicy(int i, int i2) {
        this.f117a.setUidPolicy(i, i2);
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public void setUidWhiteRules(int i, boolean z) {
        this.f117a.setUidWhiteRules(i, z);
    }
}
