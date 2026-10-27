package android.net.ip;

import android.net.INetd;
import android.net.InterfaceConfigurationParcel;
import android.net.LinkAddress;
import android.net.util.SharedLog;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import android.system.OsConstants;
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

    private boolean setEnableIPv6(boolean z) {
        try {
            this.mNetd.interfaceSetEnableIPv6(this.mIfName, z);
            return true;
        } catch (RemoteException | ServiceSpecificException e) {
            Object[] objArr = new Object[2];
            objArr[0] = z ? "enabling" : "disabling";
            objArr[1] = e;
            logError("%s IPv6 failed: %s", objArr);
            return false;
        }
    }

    public boolean enableIPv6() {
        return setEnableIPv6(true);
    }

    public boolean disableIPv6() {
        return setEnableIPv6(false);
    }

    public boolean setIPv6PrivacyExtensions(boolean z) {
        try {
            this.mNetd.interfaceSetIPv6PrivacyExtensions(this.mIfName, z);
            return true;
        } catch (RemoteException | ServiceSpecificException e) {
            Object[] objArr = new Object[2];
            objArr[0] = z ? "enabling" : "disabling";
            objArr[1] = e;
            logError("error %s IPv6 privacy extensions: %s", objArr);
            return false;
        }
    }

    public boolean setIPv6AddrGenModeIfSupported(int i) {
        try {
            this.mNetd.setIPv6AddrGenMode(this.mIfName, i);
        } catch (RemoteException e) {
            logError("Unable to set IPv6 addrgen mode: %s", e);
            return false;
        } catch (ServiceSpecificException e2) {
            if (e2.errorCode != OsConstants.EOPNOTSUPP) {
                logError("Unable to set IPv6 addrgen mode: %s", e2);
                return false;
            }
        }
        return true;
    }

    public boolean addAddress(LinkAddress linkAddress) {
        return addAddress(linkAddress.getAddress(), linkAddress.getPrefixLength());
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

    public boolean clearAllAddresses() {
        try {
            this.mNetd.interfaceClearAddrs(this.mIfName);
            return true;
        } catch (Exception e) {
            logError("Failed to clear addresses: %s", e);
            return false;
        }
    }

    private void logError(String str, Object... objArr) {
        this.mLog.e(String.format(str, objArr));
    }
}
