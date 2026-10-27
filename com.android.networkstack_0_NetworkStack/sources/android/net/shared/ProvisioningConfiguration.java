package android.net.shared;

import android.net.InformationElementParcelable;
import android.net.Network;
import android.net.ProvisioningConfigurationParcelable;
import android.net.ScanResultInfoParcelable;
import android.net.StaticIpConfiguration;
import android.net.apf.ApfCapabilities;
import android.net.shared.ProvisioningConfiguration;
import android.util.Log;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.function.Function;

/* JADX INFO: loaded from: classes.dex */
public class ProvisioningConfiguration {
    public ApfCapabilities mApfCapabilities;
    public String mDisplayName;
    public boolean mEnableIPv4;
    public boolean mEnableIPv6;
    public boolean mEnablePreconnection;
    public int mIPv6AddrGenMode;
    public InitialConfiguration mInitialConfig;
    public Layer2Information mLayer2Info;
    public Network mNetwork;
    public int mProvisioningTimeoutMs;
    public int mRequestedPreDhcpActionMs;
    public ScanResultInfo mScanResultInfo;
    public StaticIpConfiguration mStaticIpConfig;
    public boolean mUsingIpReachabilityMonitor;
    public boolean mUsingMultinetworkPolicyTracker;

    public static class ScanResultInfo {
        private final String mBssid;
        private final List<InformationElement> mInformationElements;
        private final String mSsid;

        public static class InformationElement {
            private final int mId;
            private final byte[] mPayload;

            public InformationElement(int i, ByteBuffer byteBuffer) {
                this.mId = i;
                this.mPayload = ScanResultInfo.convertToByteArray(byteBuffer.asReadOnlyBuffer());
            }

            public int getId() {
                return this.mId;
            }

            public ByteBuffer getPayload() {
                return ByteBuffer.wrap(this.mPayload).asReadOnlyBuffer();
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof InformationElement)) {
                    return false;
                }
                InformationElement informationElement = (InformationElement) obj;
                return this.mId == informationElement.mId && Arrays.equals(this.mPayload, informationElement.mPayload);
            }

            public int hashCode() {
                return Objects.hash(Integer.valueOf(this.mId), this.mPayload);
            }

            public String toString() {
                return "ID: " + this.mId + ", " + Arrays.toString(this.mPayload);
            }

            public static InformationElement fromStableParcelable(InformationElementParcelable informationElementParcelable) {
                if (informationElementParcelable == null) {
                    return null;
                }
                return new InformationElement(informationElementParcelable.id, ByteBuffer.wrap((byte[]) informationElementParcelable.payload.clone()).asReadOnlyBuffer());
            }
        }

        public ScanResultInfo(String str, String str2, List<InformationElement> list) {
            Objects.requireNonNull(str, "ssid must not be null.");
            Objects.requireNonNull(str2, "bssid must not be null.");
            this.mSsid = str;
            this.mBssid = str2;
            this.mInformationElements = Collections.unmodifiableList(new ArrayList(list));
        }

        public String getSsid() {
            return this.mSsid;
        }

        public String getBssid() {
            return this.mBssid;
        }

        public List<InformationElement> getInformationElements() {
            return this.mInformationElements;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("SSID: ");
            stringBuffer.append(this.mSsid);
            stringBuffer.append(", BSSID: ");
            stringBuffer.append(this.mBssid);
            stringBuffer.append(", Information Elements: {");
            for (InformationElement informationElement : this.mInformationElements) {
                stringBuffer.append("[");
                stringBuffer.append(informationElement.toString());
                stringBuffer.append("]");
            }
            stringBuffer.append("}");
            return stringBuffer.toString();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ScanResultInfo)) {
                return false;
            }
            ScanResultInfo scanResultInfo = (ScanResultInfo) obj;
            return Objects.equals(this.mSsid, scanResultInfo.mSsid) && Objects.equals(this.mBssid, scanResultInfo.mBssid) && this.mInformationElements.equals(scanResultInfo.mInformationElements);
        }

        public int hashCode() {
            return Objects.hash(this.mSsid, this.mBssid, this.mInformationElements);
        }

        public static ScanResultInfo fromStableParcelable(ScanResultInfoParcelable scanResultInfoParcelable) {
            if (scanResultInfoParcelable == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(ParcelableUtil.fromParcelableArray(scanResultInfoParcelable.informationElements, new Function() { // from class: android.net.shared.-$$Lambda$n57eyzHGkb9FpnLoKDxZ85lOKec
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ProvisioningConfiguration.ScanResultInfo.InformationElement.fromStableParcelable((InformationElementParcelable) obj);
                }
            }));
            return new ScanResultInfo(scanResultInfoParcelable.ssid, scanResultInfoParcelable.bssid, arrayList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static byte[] convertToByteArray(ByteBuffer byteBuffer) {
            byte[] bArr = new byte[byteBuffer.limit()];
            ByteBuffer byteBufferAsReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
            try {
                try {
                    byteBufferAsReadOnlyBuffer.position(0);
                    byteBufferAsReadOnlyBuffer.get(bArr);
                    return bArr;
                } catch (BufferUnderflowException unused) {
                    Log.wtf("ProvisioningConfiguration", "Buffer under flow exception should never happen.");
                    return bArr;
                }
            } catch (Throwable unused2) {
                return bArr;
            }
        }
    }

    public ProvisioningConfiguration() {
        this.mEnableIPv4 = true;
        this.mEnableIPv6 = true;
        this.mEnablePreconnection = false;
        this.mUsingMultinetworkPolicyTracker = true;
        this.mUsingIpReachabilityMonitor = true;
        this.mProvisioningTimeoutMs = 18000;
        this.mIPv6AddrGenMode = 2;
        this.mNetwork = null;
        this.mDisplayName = null;
    }

    public ProvisioningConfiguration(ProvisioningConfiguration provisioningConfiguration) {
        this.mEnableIPv4 = true;
        this.mEnableIPv6 = true;
        this.mEnablePreconnection = false;
        this.mUsingMultinetworkPolicyTracker = true;
        this.mUsingIpReachabilityMonitor = true;
        this.mProvisioningTimeoutMs = 18000;
        this.mIPv6AddrGenMode = 2;
        this.mNetwork = null;
        this.mDisplayName = null;
        this.mEnableIPv4 = provisioningConfiguration.mEnableIPv4;
        this.mEnableIPv6 = provisioningConfiguration.mEnableIPv6;
        this.mEnablePreconnection = provisioningConfiguration.mEnablePreconnection;
        this.mUsingMultinetworkPolicyTracker = provisioningConfiguration.mUsingMultinetworkPolicyTracker;
        this.mUsingIpReachabilityMonitor = provisioningConfiguration.mUsingIpReachabilityMonitor;
        this.mRequestedPreDhcpActionMs = provisioningConfiguration.mRequestedPreDhcpActionMs;
        this.mInitialConfig = InitialConfiguration.copy(provisioningConfiguration.mInitialConfig);
        this.mStaticIpConfig = provisioningConfiguration.mStaticIpConfig != null ? new StaticIpConfiguration(provisioningConfiguration.mStaticIpConfig) : null;
        this.mApfCapabilities = provisioningConfiguration.mApfCapabilities;
        this.mProvisioningTimeoutMs = provisioningConfiguration.mProvisioningTimeoutMs;
        this.mIPv6AddrGenMode = provisioningConfiguration.mIPv6AddrGenMode;
        this.mNetwork = provisioningConfiguration.mNetwork;
        this.mDisplayName = provisioningConfiguration.mDisplayName;
        this.mScanResultInfo = provisioningConfiguration.mScanResultInfo;
        this.mLayer2Info = provisioningConfiguration.mLayer2Info;
    }

    public static ProvisioningConfiguration fromStableParcelable(ProvisioningConfigurationParcelable provisioningConfigurationParcelable) {
        if (provisioningConfigurationParcelable == null) {
            return null;
        }
        ProvisioningConfiguration provisioningConfiguration = new ProvisioningConfiguration();
        provisioningConfiguration.mEnableIPv4 = provisioningConfigurationParcelable.enableIPv4;
        provisioningConfiguration.mEnableIPv6 = provisioningConfigurationParcelable.enableIPv6;
        provisioningConfiguration.mEnablePreconnection = provisioningConfigurationParcelable.enablePreconnection;
        provisioningConfiguration.mUsingMultinetworkPolicyTracker = provisioningConfigurationParcelable.usingMultinetworkPolicyTracker;
        provisioningConfiguration.mUsingIpReachabilityMonitor = provisioningConfigurationParcelable.usingIpReachabilityMonitor;
        provisioningConfiguration.mRequestedPreDhcpActionMs = provisioningConfigurationParcelable.requestedPreDhcpActionMs;
        provisioningConfiguration.mInitialConfig = InitialConfiguration.fromStableParcelable(provisioningConfigurationParcelable.initialConfig);
        provisioningConfiguration.mStaticIpConfig = provisioningConfigurationParcelable.staticIpConfig != null ? new StaticIpConfiguration(provisioningConfigurationParcelable.staticIpConfig) : null;
        provisioningConfiguration.mApfCapabilities = provisioningConfigurationParcelable.apfCapabilities;
        provisioningConfiguration.mProvisioningTimeoutMs = provisioningConfigurationParcelable.provisioningTimeoutMs;
        provisioningConfiguration.mIPv6AddrGenMode = provisioningConfigurationParcelable.ipv6AddrGenMode;
        provisioningConfiguration.mNetwork = provisioningConfigurationParcelable.network;
        provisioningConfiguration.mDisplayName = provisioningConfigurationParcelable.displayName;
        provisioningConfiguration.mScanResultInfo = ScanResultInfo.fromStableParcelable(provisioningConfigurationParcelable.scanResultInfo);
        provisioningConfiguration.mLayer2Info = Layer2Information.fromStableParcelable(provisioningConfigurationParcelable.layer2Info);
        return provisioningConfiguration;
    }

    public String toString() {
        return new StringJoiner(", ", ProvisioningConfiguration.class.getSimpleName() + "{", "}").add("mEnableIPv4: " + this.mEnableIPv4).add("mEnableIPv6: " + this.mEnableIPv6).add("mEnablePreconnection: " + this.mEnablePreconnection).add("mUsingMultinetworkPolicyTracker: " + this.mUsingMultinetworkPolicyTracker).add("mUsingIpReachabilityMonitor: " + this.mUsingIpReachabilityMonitor).add("mRequestedPreDhcpActionMs: " + this.mRequestedPreDhcpActionMs).add("mInitialConfig: " + this.mInitialConfig).add("mStaticIpConfig: " + this.mStaticIpConfig).add("mApfCapabilities: " + this.mApfCapabilities).add("mProvisioningTimeoutMs: " + this.mProvisioningTimeoutMs).add("mIPv6AddrGenMode: " + this.mIPv6AddrGenMode).add("mNetwork: " + this.mNetwork).add("mDisplayName: " + this.mDisplayName).add("mScanResultInfo: " + this.mScanResultInfo).add("mLayer2Info: " + this.mLayer2Info).toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ProvisioningConfiguration)) {
            return false;
        }
        ProvisioningConfiguration provisioningConfiguration = (ProvisioningConfiguration) obj;
        return this.mEnableIPv4 == provisioningConfiguration.mEnableIPv4 && this.mEnableIPv6 == provisioningConfiguration.mEnableIPv6 && this.mEnablePreconnection == provisioningConfiguration.mEnablePreconnection && this.mUsingMultinetworkPolicyTracker == provisioningConfiguration.mUsingMultinetworkPolicyTracker && this.mUsingIpReachabilityMonitor == provisioningConfiguration.mUsingIpReachabilityMonitor && this.mRequestedPreDhcpActionMs == provisioningConfiguration.mRequestedPreDhcpActionMs && Objects.equals(this.mInitialConfig, provisioningConfiguration.mInitialConfig) && Objects.equals(this.mStaticIpConfig, provisioningConfiguration.mStaticIpConfig) && Objects.equals(this.mApfCapabilities, provisioningConfiguration.mApfCapabilities) && this.mProvisioningTimeoutMs == provisioningConfiguration.mProvisioningTimeoutMs && this.mIPv6AddrGenMode == provisioningConfiguration.mIPv6AddrGenMode && Objects.equals(this.mNetwork, provisioningConfiguration.mNetwork) && Objects.equals(this.mDisplayName, provisioningConfiguration.mDisplayName) && Objects.equals(this.mScanResultInfo, provisioningConfiguration.mScanResultInfo) && Objects.equals(this.mLayer2Info, provisioningConfiguration.mLayer2Info);
    }

    public boolean isValid() {
        InitialConfiguration initialConfiguration = this.mInitialConfig;
        return initialConfiguration == null || initialConfiguration.isValid();
    }
}
