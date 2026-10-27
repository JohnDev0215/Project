package android.net.ip;

import android.net.INetd;
import android.net.InterfaceConfigurationParcel;
import android.net.LinkAddress;
import android.net.util.SharedLog;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import java.net.Inet4Address;
import java.net.InetAddress;

/* JADX INFO: loaded from: classes.dex */
public class InterfaceController {
    private final String mIfName;
    private final SharedLog mLog;
    private final INetd mNetd;

    public InterfaceController(String str, INetd iNetd, SharedLog sharedLog) {
        this.mIfName = str;
        this.mNetd = iNetd;
        this.mLog = sharedLog;
    }

    public boolean setInterfaceConfiguration(LinkAddress linkAddress, Boolean bool) {
        if (!(linkAddress.getAddress() instanceof Inet4Address)) {
            throw new IllegalArgumentException("Invalid or mismatched Inet4Address");
        }
        InterfaceConfigurationParcel interfaceConfigurationParcel = new InterfaceConfigurationParcel();
        interfaceConfigurationParcel.ifName = this.mIfName;
        interfaceConfigurationParcel.ipv4Addr = linkAddress.getAddress().getHostAddress();
        interfaceConfigurationParcel.prefixLength = linkAddress.getPrefixLength();
        interfaceConfigurationParcel.hwAddr = "";
        if (bool == null) {
            interfaceConfigurationParcel.flags = new String[0];
        } else {
            interfaceConfigurationParcel.flags = new String[]{bool.booleanValue() ? "up" : "down"};
        }
        try {
            this.mNetd.interfaceSetCfg(interfaceConfigurationParcel);
            return true;
        } catch (RemoteException | ServiceSpecificException e) {
            logError("Setting IPv4 address to %s/%d failed: %s", interfaceConfigurationParcel.ipv4Addr, Integer.valueOf(interfaceConfigurationParcel.prefixLength), e);
            return false;
        }
    }

    public boolean setIPv4Address(LinkAddress linkAddress) {
        return setInterfaceConfiguration(linkAddress, null);
    }

    public boolean clearIPv4Address() {
        return setIPv4Address(new LinkAddress("0.0.0.0/0"));
    }

    public boolean addAddress(InetAddress inetAddress, int i) {
        try {
            this.mNetd.interfaceAddAddress(this.mIfName, inetAddress.getHostAddress(), i);
            return true;
        } catch (ServiceSpecificException | RemoteException e) {
            logError("failed to add %s/%d: %s", inetAddress, Integer.valueOf(i), e);
            return false;
        }
    }

    public boolean removeAddress(InetAddress inetAddress, int i) {
        try {
            this.mNetd.interfaceDelAddress(this.mIfName, inetAddress.getHostAddress(), i);
            return true;
        } catch (ServiceSpecificException | RemoteException e) {
            logError("failed to remove %s/%d: %s", inetAddress, Integer.valueOf(i), e);
            return false;
        }
    }

    private void logError(String str, Object... objArr) {
        this.mLog.e(String.format(str, objArr));
    }
}
