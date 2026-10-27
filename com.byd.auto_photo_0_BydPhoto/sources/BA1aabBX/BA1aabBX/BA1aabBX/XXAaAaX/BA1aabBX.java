package BA1aabBX.BA1aabBX.BA1aabBX.XXAaAaX;

import android.content.Context;
import android.net.INetworkPolicyManager;
import android.net.NetworkPolicyManager;
import android.os.ServiceManager;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.net.IDiLinkNetworkPolicyManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_5_0})
@ServiceImpl(service = IDiLinkNetworkPolicyManager.class, singleton = true)
public class BA1aabBX implements IDiLinkNetworkPolicyManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final NetworkPolicyManager f5a;

    public BA1aabBX(Context context) {
        this.f5a = NetworkPolicyManager.from(context);
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public void disableNat(String str, String str2) {
        this.f5a.disableNat(str, str2);
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public void enableNat(String str, String str2) {
        this.f5a.enableNat(str, str2);
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public void enableWhiteRules(boolean z, boolean z2) {
        this.f5a.enableWhiteRules(z);
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public int[] getUidsWithPolicy(int i) {
        return this.f5a.getUidsWithPolicy(i);
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public boolean getWhiteListStats() {
        return INetworkPolicyManager.Stub.asInterface(ServiceManager.getService("netpolicy")).getWhiteListStats();
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public void setMeteredRestrictedUid(int i, boolean z) {
        this.f5a.setMeteredRestrictedUid(i, z);
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public void setUidPolicy(int i, int i2) {
        this.f5a.setUidPolicy(i, i2);
    }

    @Override // com.byd.dilink.net.IDiLinkNetworkPolicyManager
    public void setUidWhiteRules(int i, boolean z) {
        this.f5a.setUidWhiteRules(i, z);
    }
}
