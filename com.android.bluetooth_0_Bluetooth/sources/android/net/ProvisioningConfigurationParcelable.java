package android.net;

import android.net.apf.ApfCapabilities;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ProvisioningConfigurationParcelable implements Parcelable {
    public static final Parcelable.Creator<ProvisioningConfigurationParcelable> CREATOR = new Parcelable.Creator<ProvisioningConfigurationParcelable>() { // from class: android.net.ProvisioningConfigurationParcelable.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProvisioningConfigurationParcelable createFromParcel(Parcel _aidl_source) {
            ProvisioningConfigurationParcelable _aidl_out = new ProvisioningConfigurationParcelable();
            _aidl_out.readFromParcel(_aidl_source);
            return _aidl_out;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProvisioningConfigurationParcelable[] newArray(int _aidl_size) {
            return new ProvisioningConfigurationParcelable[_aidl_size];
        }
    };
    public ApfCapabilities apfCapabilities;
    public String displayName;
    public boolean enableIPv4;
    public boolean enableIPv6;
    public boolean enablePreconnection;
    public InitialConfigurationParcelable initialConfig;
    public int ipv6AddrGenMode;
    public Layer2InformationParcelable layer2Info;
    public Network network;
    public int provisioningTimeoutMs;
    public int requestedPreDhcpActionMs;
    public ScanResultInfoParcelable scanResultInfo;
    public StaticIpConfiguration staticIpConfig;
    public boolean usingIpReachabilityMonitor;
    public boolean usingMultinetworkPolicyTracker;

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeInt(this.enableIPv4 ? 1 : 0);
        parcel.writeInt(this.enableIPv6 ? 1 : 0);
        parcel.writeInt(this.usingMultinetworkPolicyTracker ? 1 : 0);
        parcel.writeInt(this.usingIpReachabilityMonitor ? 1 : 0);
        parcel.writeInt(this.requestedPreDhcpActionMs);
        if (this.initialConfig != null) {
            parcel.writeInt(1);
            this.initialConfig.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        if (this.staticIpConfig != null) {
            parcel.writeInt(1);
            this.staticIpConfig.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        if (this.apfCapabilities != null) {
            parcel.writeInt(1);
            this.apfCapabilities.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeInt(this.provisioningTimeoutMs);
        parcel.writeInt(this.ipv6AddrGenMode);
        if (this.network != null) {
            parcel.writeInt(1);
            this.network.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeString(this.displayName);
        parcel.writeInt(this.enablePreconnection ? 1 : 0);
        if (this.scanResultInfo != null) {
            parcel.writeInt(1);
            this.scanResultInfo.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        if (this.layer2Info != null) {
            parcel.writeInt(1);
            this.layer2Info.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        int iDataPosition2 = parcel.dataPosition();
        parcel.setDataPosition(iDataPosition);
        parcel.writeInt(iDataPosition2 - iDataPosition);
        parcel.setDataPosition(iDataPosition2);
    }

    public final void readFromParcel(Parcel _aidl_parcel) {
        int _aidl_start_pos = _aidl_parcel.dataPosition();
        int _aidl_parcelable_size = _aidl_parcel.readInt();
        if (_aidl_parcelable_size < 0) {
            return;
        }
        try {
            boolean z = true;
            this.enableIPv4 = _aidl_parcel.readInt() != 0;
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            this.enableIPv6 = _aidl_parcel.readInt() != 0;
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            this.usingMultinetworkPolicyTracker = _aidl_parcel.readInt() != 0;
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            this.usingIpReachabilityMonitor = _aidl_parcel.readInt() != 0;
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            this.requestedPreDhcpActionMs = _aidl_parcel.readInt();
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            if (_aidl_parcel.readInt() != 0) {
                this.initialConfig = InitialConfigurationParcelable.CREATOR.createFromParcel(_aidl_parcel);
            } else {
                this.initialConfig = null;
            }
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            if (_aidl_parcel.readInt() != 0) {
                this.staticIpConfig = (StaticIpConfiguration) StaticIpConfiguration.CREATOR.createFromParcel(_aidl_parcel);
            } else {
                this.staticIpConfig = null;
            }
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            if (_aidl_parcel.readInt() != 0) {
                this.apfCapabilities = (ApfCapabilities) ApfCapabilities.CREATOR.createFromParcel(_aidl_parcel);
            } else {
                this.apfCapabilities = null;
            }
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            this.provisioningTimeoutMs = _aidl_parcel.readInt();
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            this.ipv6AddrGenMode = _aidl_parcel.readInt();
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            if (_aidl_parcel.readInt() != 0) {
                this.network = (Network) Network.CREATOR.createFromParcel(_aidl_parcel);
            } else {
                this.network = null;
            }
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            this.displayName = _aidl_parcel.readString();
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            if (_aidl_parcel.readInt() == 0) {
                z = false;
            }
            this.enablePreconnection = z;
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            if (_aidl_parcel.readInt() != 0) {
                this.scanResultInfo = ScanResultInfoParcelable.CREATOR.createFromParcel(_aidl_parcel);
            } else {
                this.scanResultInfo = null;
            }
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            if (_aidl_parcel.readInt() != 0) {
                this.layer2Info = Layer2InformationParcelable.CREATOR.createFromParcel(_aidl_parcel);
            } else {
                this.layer2Info = null;
            }
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
            }
        } finally {
            _aidl_parcel.setDataPosition(_aidl_start_pos + _aidl_parcelable_size);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}
