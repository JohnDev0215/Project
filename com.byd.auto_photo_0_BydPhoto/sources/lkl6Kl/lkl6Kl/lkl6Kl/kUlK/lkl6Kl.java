package lkl6Kl.lkl6Kl.lkl6Kl.kUlK;

import android.content.Context;
import android.net.INetworkPolicyManager;
import android.net.NetworkPolicyManager;
import android.os.ServiceManager;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.net.IDiLinkNetworkPolicyManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_4_0})
@ServiceImpl(service = IDiLinkNetworkPolicyManager.class, singleton = true)
public class lkl6Kl implements IDiLinkNetworkPolicyManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final NetworkPolicyManager f3135a;

    public lkl6Kl(Context context) {
        this.f3135a = NetworkPolicyManager.from(context);
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public void disableNat(String str, String str2) {
        this.f3135a.disableNat(str, str2);
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public void enableNat(String str, String str2) {
        this.f3135a.enableNat(str, str2);
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public void enableWhiteRules(boolean z, boolean z2) {
        this.f3135a.enableWhiteRules(z);
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public int[] getUidsWithPolicy(int i) {
        return this.f3135a.getUidsWithPolicy(i);
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public boolean getWhiteListStats() {
        return INetworkPolicyManager.Stub.asInterface(ServiceManager.getService("netpolicy")).getWhiteListStats();
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public void setMeteredRestrictedUid(int i, boolean z) {
        this.f3135a.setMeteredRestrictedUid(i, z);
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public void setUidPolicy(int i, int i2) {
        this.f3135a.setUidPolicy(i, i2);
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public void setUidWhiteRules(int i, boolean z) {
        this.f3135a.setUidWhiteRules(i, z);
    }
}
