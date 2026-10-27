package GGW.GGW.GGW.GghgGh;

import android.content.Context;
import android.net.INetworkPolicyManager;
import android.net.NetworkPolicyManager;
import android.os.ServiceManager;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.net.IDiLinkNetworkPolicyManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {"RUI"})
@ServiceImpl(service = IDiLinkNetworkPolicyManager.class, singleton = true)
public class GGW implements IDiLinkNetworkPolicyManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final NetworkPolicyManager f45a;

    public GGW(Context context) {
        this.f45a = NetworkPolicyManager.from(context);
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public void disableNat(String str, String str2) {
        this.f45a.disableNat(str, str2);
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public void enableNat(String str, String str2) {
        this.f45a.enableNat(str, str2);
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public void enableWhiteRules(boolean z, boolean z2) {
        this.f45a.enableWhiteRules(z);
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public int[] getUidsWithPolicy(int i) {
        return this.f45a.getUidsWithPolicy(i);
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public boolean getWhiteListStats() {
        return INetworkPolicyManager.Stub.asInterface(ServiceManager.getService("netpolicy")).getWhiteListStats();
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public void setMeteredRestrictedUid(int i, boolean z) {
        this.f45a.setMeteredRestrictedUid(i, z);
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public void setUidPolicy(int i, int i2) {
        this.f45a.setUidPolicy(i, i2);
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public void setUidWhiteRules(int i, boolean z) {
        this.f45a.setUidWhiteRules(i, z);
    }
}
