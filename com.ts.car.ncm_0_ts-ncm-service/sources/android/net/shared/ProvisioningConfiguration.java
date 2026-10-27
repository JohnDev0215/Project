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
    private static final int DEFAULT_TIMEOUT_MS = 18000;
    private static final String TAG = "ProvisioningConfiguration";
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

    public static class Builder {
        protected ProvisioningConfiguration mConfig = new ProvisioningConfiguration();

        public Builder withoutIPv4() {
            this.mConfig.mEnableIPv4 = false;
            return this;
        }

        public Builder withoutIPv6() {
            this.mConfig.mEnableIPv6 = false;
            return this;
        }

        public Builder withoutMultinetworkPolicyTracker() {
            this.mConfig.mUsingMultinetworkPolicyTracker = false;
            return this;
        }

        public Builder withoutIpReachabilityMonitor() {
            this.mConfig.mUsingIpReachabilityMonitor = false;
            return this;
        }

        public Builder withPreDhcpAction() {
            this.mConfig.mRequestedPreDhcpActionMs = ProvisioningConfiguration.DEFAULT_TIMEOUT_MS;
            return this;
        }

        public Builder withPreDhcpAction(int dhcpActionTimeoutMs) {
            this.mConfig.mRequestedPreDhcpActionMs = dhcpActionTimeoutMs;
            return this;
        }

        public Builder withPreconnection() {
            this.mConfig.mEnablePreconnection = true;
            return this;
        }

        public Builder withInitialConfiguration(InitialConfiguration initialConfig) {
            this.mConfig.mInitialConfig = initialConfig;
            return this;
        }

        public Builder withStaticConfiguration(StaticIpConfiguration staticConfig) {
            this.mConfig.mStaticIpConfig = staticConfig;
            return this;
        }

        public Builder withApfCapabilities(ApfCapabilities apfCapabilities) {
            this.mConfig.mApfCapabilities = apfCapabilities;
            return this;
        }

        public Builder withProvisioningTimeoutMs(int timeoutMs) {
            this.mConfig.mProvisioningTimeoutMs = timeoutMs;
            return this;
        }

        public Builder withRandomMacAddress() {
            this.mConfig.mIPv6AddrGenMode = 0;
            return this;
        }

        public Builder withStableMacAddress() {
            this.mConfig.mIPv6AddrGenMode = 2;
            return this;
        }

        public Builder withNetwork(Network network) {
            this.mConfig.mNetwork = network;
            return this;
        }

        public Builder withDisplayName(String displayName) {
            this.mConfig.mDisplayName = displayName;
            return this;
        }

        public Builder withScanResultInfo(ScanResultInfo scanResultInfo) {
            this.mConfig.mScanResultInfo = scanResultInfo;
            return this;
        }

        public Builder withLayer2Information(Layer2Information layer2Info) {
            this.mConfig.mLayer2Info = layer2Info;
            return this;
        }

        public ProvisioningConfiguration build() {
            return new ProvisioningConfiguration(this.mConfig);
        }
    }

    public static class ScanResultInfo {
        private final String mBssid;
        private final List<InformationElement> mInformationElements;
        private final String mSsid;

        public static class InformationElement {
            private final int mId;
            private final byte[] mPayload;

            public InformationElement(int id, ByteBuffer payload) {
                this.mId = id;
                this.mPayload = ScanResultInfo.convertToByteArray(payload.asReadOnlyBuffer());
            }

            public int getId() {
                return this.mId;
            }

            public ByteBuffer getPayload() {
                return ByteBuffer.wrap(this.mPayload).asReadOnlyBuffer();
            }

            public boolean equals(Object o) {
                if (o == this) {
                    return true;
                }
                if (!(o instanceof InformationElement)) {
                    return false;
                }
                InformationElement other = (InformationElement) o;
                return this.mId == other.mId && Arrays.equals(this.mPayload, other.mPayload);
            }

            public int hashCode() {
                return Objects.hash(Integer.valueOf(this.mId), this.mPayload);
            }

            public String toString() {
                return "ID: " + this.mId + ", " + Arrays.toString(this.mPayload);
            }

            public InformationElementParcelable toStableParcelable() {
                InformationElementParcelable p = new InformationElementParcelable();
                p.id = this.mId;
                byte[] bArr = this.mPayload;
                p.payload = bArr != null ? (byte[]) bArr.clone() : null;
                return p;
            }

            public static InformationElement fromStableParcelable(InformationElementParcelable p) {
                if (p == null) {
                    return null;
                }
                return new InformationElement(p.id, ByteBuffer.wrap((byte[]) p.payload.clone()).asReadOnlyBuffer());
            }
        }

        public ScanResultInfo(String ssid, String bssid, List<InformationElement> informationElements) {
            Objects.requireNonNull(ssid, "ssid must not be null.");
            Objects.requireNonNull(bssid, "bssid must not be null.");
            this.mSsid = ssid;
            this.mBssid = bssid;
            this.mInformationElements = Collections.unmodifiableList(new ArrayList(informationElements));
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
            StringBuffer str = new StringBuffer();
            str.append("SSID: ");
            str.append(this.mSsid);
            str.append(", BSSID: ");
            str.append(this.mBssid);
            str.append(", Information Elements: {");
            for (InformationElement ie : this.mInformationElements) {
                str.append("[");
                str.append(ie.toString());
                str.append("]");
            }
            str.append("}");
            return str.toString();
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ScanResultInfo)) {
                return false;
            }
            ScanResultInfo other = (ScanResultInfo) o;
            return Objects.equals(this.mSsid, other.mSsid) && Objects.equals(this.mBssid, other.mBssid) && this.mInformationElements.equals(other.mInformationElements);
        }

        public int hashCode() {
            return Objects.hash(this.mSsid, this.mBssid, this.mInformationElements);
        }

        public ScanResultInfoParcelable toStableParcelable() {
            ScanResultInfoParcelable p = new ScanResultInfoParcelable();
            p.ssid = this.mSsid;
            p.bssid = this.mBssid;
            p.informationElements = (InformationElementParcelable[]) ParcelableUtil.toParcelableArray(this.mInformationElements, new Function() { // from class: android.net.shared.-$$Lambda$qVdKXOBVced7rdGB-dMQlkPbLW4
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((ProvisioningConfiguration.ScanResultInfo.InformationElement) obj).toStableParcelable();
                }
            }, InformationElementParcelable.class);
            return p;
        }

        public static ScanResultInfo fromStableParcelable(ScanResultInfoParcelable p) {
            if (p == null) {
                return null;
            }
            List<InformationElement> ies = new ArrayList<>();
            ies.addAll(ParcelableUtil.fromParcelableArray(p.informationElements, new Function() { // from class: android.net.shared.-$$Lambda$n57eyzHGkb9FpnLoKDxZ85lOKec
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ProvisioningConfiguration.ScanResultInfo.InformationElement.fromStableParcelable((InformationElementParcelable) obj);
                }
            }));
            return new ScanResultInfo(p.ssid, p.bssid, ies);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static byte[] convertToByteArray(ByteBuffer buffer) {
            byte[] bytes = new byte[buffer.limit()];
            ByteBuffer copy = buffer.asReadOnlyBuffer();
            try {
                try {
                    copy.position(0);
                    copy.get(bytes);
                    return bytes;
                } catch (BufferUnderflowException e) {
                    Log.wtf(ProvisioningConfiguration.TAG, "Buffer under flow exception should never happen.");
                    return bytes;
                }
            } catch (Throwable th) {
                return bytes;
            }
        }
    }

    public ProvisioningConfiguration() {
        this.mEnableIPv4 = true;
        this.mEnableIPv6 = true;
        this.mEnablePreconnection = false;
        this.mUsingMultinetworkPolicyTracker = true;
        this.mUsingIpReachabilityMonitor = true;
        this.mProvisioningTimeoutMs = DEFAULT_TIMEOUT_MS;
        this.mIPv6AddrGenMode = 2;
        this.mNetwork = null;
        this.mDisplayName = null;
    }

    public ProvisioningConfiguration(ProvisioningConfiguration other) {
        this.mEnableIPv4 = true;
        this.mEnableIPv6 = true;
        this.mEnablePreconnection = false;
        this.mUsingMultinetworkPolicyTracker = true;
        this.mUsingIpReachabilityMonitor = true;
        this.mProvisioningTimeoutMs = DEFAULT_TIMEOUT_MS;
        this.mIPv6AddrGenMode = 2;
        this.mNetwork = null;
        this.mDisplayName = null;
        this.mEnableIPv4 = other.mEnableIPv4;
        this.mEnableIPv6 = other.mEnableIPv6;
        this.mEnablePreconnection = other.mEnablePreconnection;
        this.mUsingMultinetworkPolicyTracker = other.mUsingMultinetworkPolicyTracker;
        this.mUsingIpReachabilityMonitor = other.mUsingIpReachabilityMonitor;
        this.mRequestedPreDhcpActionMs = other.mRequestedPreDhcpActionMs;
        this.mInitialConfig = InitialConfiguration.copy(other.mInitialConfig);
        this.mStaticIpConfig = other.mStaticIpConfig != null ? new StaticIpConfiguration(other.mStaticIpConfig) : null;
        this.mApfCapabilities = other.mApfCapabilities;
        this.mProvisioningTimeoutMs = other.mProvisioningTimeoutMs;
        this.mIPv6AddrGenMode = other.mIPv6AddrGenMode;
        this.mNetwork = other.mNetwork;
        this.mDisplayName = other.mDisplayName;
        this.mScanResultInfo = other.mScanResultInfo;
        this.mLayer2Info = other.mLayer2Info;
    }

    public ProvisioningConfigurationParcelable toStableParcelable() {
        StaticIpConfiguration staticIpConfiguration;
        ProvisioningConfigurationParcelable p = new ProvisioningConfigurationParcelable();
        p.enableIPv4 = this.mEnableIPv4;
        p.enableIPv6 = this.mEnableIPv6;
        p.enablePreconnection = this.mEnablePreconnection;
        p.usingMultinetworkPolicyTracker = this.mUsingMultinetworkPolicyTracker;
        p.usingIpReachabilityMonitor = this.mUsingIpReachabilityMonitor;
        p.requestedPreDhcpActionMs = this.mRequestedPreDhcpActionMs;
        InitialConfiguration initialConfiguration = this.mInitialConfig;
        p.initialConfig = initialConfiguration == null ? null : initialConfiguration.toStableParcelable();
        if (this.mStaticIpConfig == null) {
            staticIpConfiguration = null;
        } else {
            staticIpConfiguration = new StaticIpConfiguration(this.mStaticIpConfig);
        }
        p.staticIpConfig = staticIpConfiguration;
        p.apfCapabilities = this.mApfCapabilities;
        p.provisioningTimeoutMs = this.mProvisioningTimeoutMs;
        p.ipv6AddrGenMode = this.mIPv6AddrGenMode;
        p.network = this.mNetwork;
        p.displayName = this.mDisplayName;
        ScanResultInfo scanResultInfo = this.mScanResultInfo;
        p.scanResultInfo = scanResultInfo == null ? null : scanResultInfo.toStableParcelable();
        Layer2Information layer2Information = this.mLayer2Info;
        p.layer2Info = layer2Information != null ? layer2Information.toStableParcelable() : null;
        return p;
    }

    public static ProvisioningConfiguration fromStableParcelable(ProvisioningConfigurationParcelable p) {
        StaticIpConfiguration staticIpConfiguration = null;
        if (p == null) {
            return null;
        }
        ProvisioningConfiguration config = new ProvisioningConfiguration();
        config.mEnableIPv4 = p.enableIPv4;
        config.mEnableIPv6 = p.enableIPv6;
        config.mEnablePreconnection = p.enablePreconnection;
        config.mUsingMultinetworkPolicyTracker = p.usingMultinetworkPolicyTracker;
        config.mUsingIpReachabilityMonitor = p.usingIpReachabilityMonitor;
        config.mRequestedPreDhcpActionMs = p.requestedPreDhcpActionMs;
        config.mInitialConfig = InitialConfiguration.fromStableParcelable(p.initialConfig);
        if (p.staticIpConfig != null) {
            staticIpConfiguration = new StaticIpConfiguration(p.staticIpConfig);
        }
        config.mStaticIpConfig = staticIpConfiguration;
        config.mApfCapabilities = p.apfCapabilities;
        config.mProvisioningTimeoutMs = p.provisioningTimeoutMs;
        config.mIPv6AddrGenMode = p.ipv6AddrGenMode;
        config.mNetwork = p.network;
        config.mDisplayName = p.displayName;
        config.mScanResultInfo = ScanResultInfo.fromStableParcelable(p.scanResultInfo);
        config.mLayer2Info = Layer2Information.fromStableParcelable(p.layer2Info);
        return config;
    }

    public String toString() {
        return new StringJoiner(", ", getClass().getSimpleName() + "{", "}").add("mEnableIPv4: " + this.mEnableIPv4).add("mEnableIPv6: " + this.mEnableIPv6).add("mEnablePreconnection: " + this.mEnablePreconnection).add("mUsingMultinetworkPolicyTracker: " + this.mUsingMultinetworkPolicyTracker).add("mUsingIpReachabilityMonitor: " + this.mUsingIpReachabilityMonitor).add("mRequestedPreDhcpActionMs: " + this.mRequestedPreDhcpActionMs).add("mInitialConfig: " + this.mInitialConfig).add("mStaticIpConfig: " + this.mStaticIpConfig).add("mApfCapabilities: " + this.mApfCapabilities).add("mProvisioningTimeoutMs: " + this.mProvisioningTimeoutMs).add("mIPv6AddrGenMode: " + this.mIPv6AddrGenMode).add("mNetwork: " + this.mNetwork).add("mDisplayName: " + this.mDisplayName).add("mScanResultInfo: " + this.mScanResultInfo).add("mLayer2Info: " + this.mLayer2Info).toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ProvisioningConfiguration)) {
            return false;
        }
        ProvisioningConfiguration other = (ProvisioningConfiguration) obj;
        return this.mEnableIPv4 == other.mEnableIPv4 && this.mEnableIPv6 == other.mEnableIPv6 && this.mEnablePreconnection == other.mEnablePreconnection && this.mUsingMultinetworkPolicyTracker == other.mUsingMultinetworkPolicyTracker && this.mUsingIpReachabilityMonitor == other.mUsingIpReachabilityMonitor && this.mRequestedPreDhcpActionMs == other.mRequestedPreDhcpActionMs && Objects.equals(this.mInitialConfig, other.mInitialConfig) && Objects.equals(this.mStaticIpConfig, other.mStaticIpConfig) && Objects.equals(this.mApfCapabilities, other.mApfCapabilities) && this.mProvisioningTimeoutMs == other.mProvisioningTimeoutMs && this.mIPv6AddrGenMode == other.mIPv6AddrGenMode && Objects.equals(this.mNetwork, other.mNetwork) && Objects.equals(this.mDisplayName, other.mDisplayName) && Objects.equals(this.mScanResultInfo, other.mScanResultInfo) && Objects.equals(this.mLayer2Info, other.mLayer2Info);
    }

    public boolean isValid() {
        InitialConfiguration initialConfiguration = this.mInitialConfig;
        return initialConfiguration == null || initialConfiguration.isValid();
    }
}
