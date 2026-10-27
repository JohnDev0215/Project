package android.net;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.proto.ProtoOutputStream;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.BitUtils;
import com.android.internal.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

/* JADX INFO: loaded from: classes2.dex */
public final class NetworkCapabilities implements Parcelable {
    private static final long CONNECTIVITY_MANAGED_CAPABILITIES = 17498112;
    private static final long DEFAULT_CAPABILITIES = 57344;
    private static final long FORCE_RESTRICTED_CAPABILITIES = 4194304;
    public static final int LINK_BANDWIDTH_UNSPECIFIED = 0;
    private static final int MAX_NET_CAPABILITY = 25;
    public static final int MAX_TRANSPORT = 7;
    private static final int MIN_NET_CAPABILITY = 0;
    public static final int MIN_TRANSPORT = 0;
    private static final long MUTABLE_CAPABILITIES = 54476800;
    public static final int NET_CAPABILITY_CAPTIVE_PORTAL = 17;
    public static final int NET_CAPABILITY_CBS = 5;
    public static final int NET_CAPABILITY_DUN = 2;
    public static final int NET_CAPABILITY_EIMS = 10;
    public static final int NET_CAPABILITY_FOREGROUND = 19;
    public static final int NET_CAPABILITY_FOTA = 3;
    public static final int NET_CAPABILITY_IA = 7;
    public static final int NET_CAPABILITY_IMS = 4;
    public static final int NET_CAPABILITY_INTERNET = 12;
    public static final int NET_CAPABILITY_MCX = 23;
    public static final int NET_CAPABILITY_MMS = 0;
    public static final int NET_CAPABILITY_NOT_CONGESTED = 20;
    public static final int NET_CAPABILITY_NOT_METERED = 11;
    public static final int NET_CAPABILITY_NOT_RESTRICTED = 13;
    public static final int NET_CAPABILITY_NOT_ROAMING = 18;
    public static final int NET_CAPABILITY_NOT_SUSPENDED = 21;
    public static final int NET_CAPABILITY_NOT_VPN = 15;

    @SystemApi
    public static final int NET_CAPABILITY_OEM_PAID = 22;

    @SystemApi
    public static final int NET_CAPABILITY_PARTIAL_CONNECTIVITY = 24;
    public static final int NET_CAPABILITY_RCS = 8;
    public static final int NET_CAPABILITY_SUPL = 1;
    public static final int NET_CAPABILITY_TEMPORARILY_NOT_METERED = 25;
    public static final int NET_CAPABILITY_TRUSTED = 14;
    public static final int NET_CAPABILITY_VALIDATED = 16;
    public static final int NET_CAPABILITY_WIFI_P2P = 6;
    public static final int NET_CAPABILITY_XCAP = 9;
    private static final long NON_REQUESTABLE_CAPABILITIES = 54460416;
    static final long RESTRICTED_CAPABILITIES = 8390588;
    public static final int SIGNAL_STRENGTH_UNSPECIFIED = Integer.MIN_VALUE;
    private static final String TAG = "NetworkCapabilities";
    private static final long TEST_NETWORKS_ALLOWED_CAPABILITIES = 37005312;
    private static final int TEST_NETWORKS_ALLOWED_TRANSPORTS = 136;
    public static final int TRANSPORT_BLUETOOTH = 2;
    public static final int TRANSPORT_CELLULAR = 0;
    public static final int TRANSPORT_ETHERNET = 3;
    public static final int TRANSPORT_LOWPAN = 6;
    public static final int TRANSPORT_TEST = 7;
    public static final int TRANSPORT_VPN = 4;
    public static final int TRANSPORT_WIFI = 1;
    public static final int TRANSPORT_WIFI_AWARE = 5;
    static final long UNRESTRICTED_CAPABILITIES = 4163;
    private int[] mAdministratorUids;
    private int mLinkDownBandwidthKbps;
    private int mLinkUpBandwidthKbps;
    private long mNetworkCapabilities;
    private NetworkSpecifier mNetworkSpecifier;
    private int mOwnerUid;
    private boolean mPrivateDnsBroken;
    private String mRequestorPackageName;
    private int mRequestorUid;
    private String mSSID;
    private int mSignalStrength;
    private TransportInfo mTransportInfo;
    private long mTransportTypes;
    private ArraySet<UidRange> mUids;
    private long mUnwantedNetworkCapabilities;
    private static final String[] TRANSPORT_NAMES = {"CELLULAR", "WIFI", "BLUETOOTH", "ETHERNET", "VPN", "WIFI_AWARE", "LOWPAN", "TEST"};
    public static final Parcelable.Creator<NetworkCapabilities> CREATOR = new Parcelable.Creator<NetworkCapabilities>() { // from class: android.net.NetworkCapabilities.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetworkCapabilities createFromParcel(Parcel parcel) {
            NetworkCapabilities networkCapabilities = new NetworkCapabilities();
            networkCapabilities.mNetworkCapabilities = parcel.readLong();
            networkCapabilities.mUnwantedNetworkCapabilities = parcel.readLong();
            networkCapabilities.mTransportTypes = parcel.readLong();
            networkCapabilities.mLinkUpBandwidthKbps = parcel.readInt();
            networkCapabilities.mLinkDownBandwidthKbps = parcel.readInt();
            networkCapabilities.mNetworkSpecifier = (NetworkSpecifier) parcel.readParcelable(null);
            networkCapabilities.mTransportInfo = (TransportInfo) parcel.readParcelable(null);
            networkCapabilities.mSignalStrength = parcel.readInt();
            networkCapabilities.mUids = parcel.readArraySet(null);
            networkCapabilities.mSSID = parcel.readString();
            networkCapabilities.mPrivateDnsBroken = parcel.readBoolean();
            networkCapabilities.setAdministratorUids(parcel.createIntArray());
            networkCapabilities.mOwnerUid = parcel.readInt();
            networkCapabilities.mRequestorUid = parcel.readInt();
            networkCapabilities.mRequestorPackageName = parcel.readString();
            return networkCapabilities;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetworkCapabilities[] newArray(int i) {
            return new NetworkCapabilities[i];
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    interface NameOf {
        String nameOf(int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface NetCapability {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Transport {
    }

    private static boolean isValidCapability(int i) {
        return i >= 0 && i <= 25;
    }

    public static boolean isValidTransport(int i) {
        return i >= 0 && i <= 7;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public NetworkCapabilities() {
        this.mOwnerUid = -1;
        this.mAdministratorUids = new int[0];
        this.mLinkUpBandwidthKbps = 0;
        this.mLinkDownBandwidthKbps = 0;
        this.mNetworkSpecifier = null;
        this.mTransportInfo = null;
        this.mSignalStrength = Integer.MIN_VALUE;
        this.mUids = null;
        clearAll();
        this.mNetworkCapabilities = DEFAULT_CAPABILITIES;
    }

    public NetworkCapabilities(NetworkCapabilities networkCapabilities) {
        this.mOwnerUid = -1;
        this.mAdministratorUids = new int[0];
        this.mLinkUpBandwidthKbps = 0;
        this.mLinkDownBandwidthKbps = 0;
        this.mNetworkSpecifier = null;
        this.mTransportInfo = null;
        this.mSignalStrength = Integer.MIN_VALUE;
        this.mUids = null;
        if (networkCapabilities != null) {
            set(networkCapabilities);
        }
    }

    public void clearAll() {
        this.mUnwantedNetworkCapabilities = 0L;
        this.mTransportTypes = 0L;
        this.mNetworkCapabilities = 0L;
        this.mLinkDownBandwidthKbps = 0;
        this.mLinkUpBandwidthKbps = 0;
        this.mNetworkSpecifier = null;
        this.mTransportInfo = null;
        this.mSignalStrength = Integer.MIN_VALUE;
        this.mUids = null;
        this.mAdministratorUids = new int[0];
        this.mOwnerUid = -1;
        this.mSSID = null;
        this.mPrivateDnsBroken = false;
        this.mRequestorUid = -1;
        this.mRequestorPackageName = null;
    }

    public void set(NetworkCapabilities networkCapabilities) {
        this.mNetworkCapabilities = networkCapabilities.mNetworkCapabilities;
        this.mTransportTypes = networkCapabilities.mTransportTypes;
        this.mLinkUpBandwidthKbps = networkCapabilities.mLinkUpBandwidthKbps;
        this.mLinkDownBandwidthKbps = networkCapabilities.mLinkDownBandwidthKbps;
        this.mNetworkSpecifier = networkCapabilities.mNetworkSpecifier;
        this.mTransportInfo = networkCapabilities.mTransportInfo;
        this.mSignalStrength = networkCapabilities.mSignalStrength;
        setUids(networkCapabilities.mUids);
        setAdministratorUids(networkCapabilities.getAdministratorUids());
        this.mOwnerUid = networkCapabilities.mOwnerUid;
        this.mUnwantedNetworkCapabilities = networkCapabilities.mUnwantedNetworkCapabilities;
        this.mSSID = networkCapabilities.mSSID;
        this.mPrivateDnsBroken = networkCapabilities.mPrivateDnsBroken;
        this.mRequestorUid = networkCapabilities.mRequestorUid;
        this.mRequestorPackageName = networkCapabilities.mRequestorPackageName;
    }

    public NetworkCapabilities addCapability(int i) {
        checkValidCapability(i);
        int i2 = 1 << i;
        this.mNetworkCapabilities |= (long) i2;
        this.mUnwantedNetworkCapabilities &= (long) (~i2);
        return this;
    }

    public void addUnwantedCapability(int i) {
        checkValidCapability(i);
        int i2 = 1 << i;
        this.mUnwantedNetworkCapabilities |= (long) i2;
        this.mNetworkCapabilities &= (long) (~i2);
    }

    public NetworkCapabilities removeCapability(int i) {
        checkValidCapability(i);
        long j = ~(1 << i);
        this.mNetworkCapabilities &= j;
        this.mUnwantedNetworkCapabilities = j & this.mUnwantedNetworkCapabilities;
        return this;
    }

    public NetworkCapabilities setCapability(int i, boolean z) {
        if (z) {
            addCapability(i);
        } else {
            removeCapability(i);
        }
        return this;
    }

    public int[] getCapabilities() {
        return BitUtils.unpackBits(this.mNetworkCapabilities);
    }

    public int[] getUnwantedCapabilities() {
        return BitUtils.unpackBits(this.mUnwantedNetworkCapabilities);
    }

    public void setCapabilities(int[] iArr, int[] iArr2) {
        this.mNetworkCapabilities = BitUtils.packBits(iArr);
        this.mUnwantedNetworkCapabilities = BitUtils.packBits(iArr2);
    }

    @Deprecated
    public void setCapabilities(int[] iArr) {
        setCapabilities(iArr, new int[0]);
    }

    public boolean hasCapability(int i) {
        if (isValidCapability(i)) {
            if ((((long) (1 << i)) & this.mNetworkCapabilities) != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean hasUnwantedCapability(int i) {
        if (isValidCapability(i)) {
            if ((((long) (1 << i)) & this.mUnwantedNetworkCapabilities) != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean hasConnectivityManagedCapability() {
        return (this.mNetworkCapabilities & CONNECTIVITY_MANAGED_CAPABILITIES) != 0;
    }

    private void combineNetCapabilities(NetworkCapabilities networkCapabilities) {
        this.mNetworkCapabilities |= networkCapabilities.mNetworkCapabilities;
        this.mUnwantedNetworkCapabilities |= networkCapabilities.mUnwantedNetworkCapabilities;
    }

    public String describeFirstNonRequestableCapability() {
        long j = (this.mNetworkCapabilities | this.mUnwantedNetworkCapabilities) & NON_REQUESTABLE_CAPABILITIES;
        if (j != 0) {
            return capabilityNameOf(BitUtils.unpackBits(j)[0]);
        }
        if (this.mLinkUpBandwidthKbps != 0 || this.mLinkDownBandwidthKbps != 0) {
            return "link bandwidth";
        }
        if (hasSignalStrength()) {
            return "signalStrength";
        }
        if (isPrivateDnsBroken()) {
            return "privateDnsBroken";
        }
        return null;
    }

    private boolean satisfiedByNetCapabilities(NetworkCapabilities networkCapabilities, boolean z) {
        long j = this.mNetworkCapabilities;
        long j2 = this.mUnwantedNetworkCapabilities;
        long j3 = networkCapabilities.mNetworkCapabilities;
        if (z) {
            j &= -54476801;
            j2 &= -54476801;
        }
        return (j3 & j) == j && (j3 & j2) == 0;
    }

    public boolean equalsNetCapabilities(NetworkCapabilities networkCapabilities) {
        return networkCapabilities.mNetworkCapabilities == this.mNetworkCapabilities && networkCapabilities.mUnwantedNetworkCapabilities == this.mUnwantedNetworkCapabilities;
    }

    private boolean equalsNetCapabilitiesRequestable(NetworkCapabilities networkCapabilities) {
        return (this.mNetworkCapabilities & (-54460417)) == (networkCapabilities.mNetworkCapabilities & (-54460417)) && (this.mUnwantedNetworkCapabilities & (-54460417)) == (networkCapabilities.mUnwantedNetworkCapabilities & (-54460417));
    }

    public boolean deduceRestrictedCapability() {
        long j = this.mNetworkCapabilities;
        boolean z = (4194304 & j) != 0;
        boolean z2 = (UNRESTRICTED_CAPABILITIES & j) != 0;
        boolean z3 = (j & RESTRICTED_CAPABILITIES) != 0;
        if (z) {
            return true;
        }
        return z3 && !z2;
    }

    public void maybeMarkCapabilitiesRestricted() {
        if (deduceRestrictedCapability()) {
            removeCapability(13);
        }
    }

    public void restrictCapabilitesForTestNetwork(int i) {
        long j = this.mNetworkCapabilities;
        long j2 = this.mTransportTypes;
        NetworkSpecifier networkSpecifier = this.mNetworkSpecifier;
        int i2 = this.mSignalStrength;
        int ownerUid = getOwnerUid();
        int[] administratorUids = getAdministratorUids();
        clearAll();
        this.mTransportTypes = (j2 & 136) | 128;
        this.mNetworkCapabilities = j & TEST_NETWORKS_ALLOWED_CAPABILITIES;
        this.mNetworkSpecifier = networkSpecifier;
        this.mSignalStrength = i2;
        if (ownerUid == i) {
            setOwnerUid(i);
        }
        if (ArrayUtils.contains(administratorUids, i)) {
            setAdministratorUids(new int[]{i});
        }
    }

    public NetworkCapabilities addTransportType(int i) {
        checkValidTransportType(i);
        this.mTransportTypes |= (long) (1 << i);
        setNetworkSpecifier(this.mNetworkSpecifier);
        return this;
    }

    public NetworkCapabilities removeTransportType(int i) {
        checkValidTransportType(i);
        this.mTransportTypes &= (long) (~(1 << i));
        setNetworkSpecifier(this.mNetworkSpecifier);
        return this;
    }

    public NetworkCapabilities setTransportType(int i, boolean z) {
        if (z) {
            addTransportType(i);
        } else {
            removeTransportType(i);
        }
        return this;
    }

    @SystemApi
    public int[] getTransportTypes() {
        return BitUtils.unpackBits(this.mTransportTypes);
    }

    public void setTransportTypes(int[] iArr) {
        this.mTransportTypes = BitUtils.packBits(iArr);
    }

    public boolean hasTransport(int i) {
        if (isValidTransport(i)) {
            if ((((long) (1 << i)) & this.mTransportTypes) != 0) {
                return true;
            }
        }
        return false;
    }

    private void combineTransportTypes(NetworkCapabilities networkCapabilities) {
        this.mTransportTypes |= networkCapabilities.mTransportTypes;
    }

    private boolean satisfiedByTransportTypes(NetworkCapabilities networkCapabilities) {
        long j = this.mTransportTypes;
        return j == 0 || (networkCapabilities.mTransportTypes & j) != 0;
    }

    public boolean equalsTransportTypes(NetworkCapabilities networkCapabilities) {
        return networkCapabilities.mTransportTypes == this.mTransportTypes;
    }

    public NetworkCapabilities setOwnerUid(int i) {
        this.mOwnerUid = i;
        return this;
    }

    public int getOwnerUid() {
        return this.mOwnerUid;
    }

    public NetworkCapabilities setAdministratorUids(int[] iArr) {
        int[] iArr2;
        int i;
        int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);
        this.mAdministratorUids = iArrCopyOf;
        Arrays.sort(iArrCopyOf);
        int i2 = 0;
        do {
            iArr2 = this.mAdministratorUids;
            if (i2 >= iArr2.length - 1) {
                return this;
            }
            i = iArr2[i2];
            i2++;
        } while (i < iArr2[i2]);
        throw new IllegalArgumentException("All administrator UIDs must be unique");
    }

    @SystemApi
    public int[] getAdministratorUids() {
        int[] iArr = this.mAdministratorUids;
        return Arrays.copyOf(iArr, iArr.length);
    }

    public boolean equalsAdministratorUids(NetworkCapabilities networkCapabilities) {
        return Arrays.equals(this.mAdministratorUids, networkCapabilities.mAdministratorUids);
    }

    private void combineAdministratorUids(NetworkCapabilities networkCapabilities) {
        int[] iArr = networkCapabilities.mAdministratorUids;
        if (iArr.length == 0) {
            return;
        }
        if (this.mAdministratorUids.length == 0) {
            this.mAdministratorUids = Arrays.copyOf(iArr, iArr.length);
        } else if (!equalsAdministratorUids(networkCapabilities)) {
            throw new IllegalStateException("Can't combine two different administrator UID lists");
        }
    }

    public NetworkCapabilities setLinkUpstreamBandwidthKbps(int i) {
        this.mLinkUpBandwidthKbps = i;
        return this;
    }

    public int getLinkUpstreamBandwidthKbps() {
        return this.mLinkUpBandwidthKbps;
    }

    public NetworkCapabilities setLinkDownstreamBandwidthKbps(int i) {
        this.mLinkDownBandwidthKbps = i;
        return this;
    }

    public int getLinkDownstreamBandwidthKbps() {
        return this.mLinkDownBandwidthKbps;
    }

    private void combineLinkBandwidths(NetworkCapabilities networkCapabilities) {
        this.mLinkUpBandwidthKbps = Math.max(this.mLinkUpBandwidthKbps, networkCapabilities.mLinkUpBandwidthKbps);
        this.mLinkDownBandwidthKbps = Math.max(this.mLinkDownBandwidthKbps, networkCapabilities.mLinkDownBandwidthKbps);
    }

    private boolean satisfiedByLinkBandwidths(NetworkCapabilities networkCapabilities) {
        return this.mLinkUpBandwidthKbps <= networkCapabilities.mLinkUpBandwidthKbps && this.mLinkDownBandwidthKbps <= networkCapabilities.mLinkDownBandwidthKbps;
    }

    private boolean equalsLinkBandwidths(NetworkCapabilities networkCapabilities) {
        return this.mLinkUpBandwidthKbps == networkCapabilities.mLinkUpBandwidthKbps && this.mLinkDownBandwidthKbps == networkCapabilities.mLinkDownBandwidthKbps;
    }

    public static int minBandwidth(int i, int i2) {
        return i == 0 ? i2 : i2 == 0 ? i : Math.min(i, i2);
    }

    public static int maxBandwidth(int i, int i2) {
        return Math.max(i, i2);
    }

    public NetworkCapabilities setNetworkSpecifier(NetworkSpecifier networkSpecifier) {
        if (networkSpecifier != null && Long.bitCount(this.mTransportTypes) != 1) {
            throw new IllegalStateException("Must have a single transport specified to use setNetworkSpecifier");
        }
        this.mNetworkSpecifier = networkSpecifier;
        return this;
    }

    public NetworkCapabilities setTransportInfo(TransportInfo transportInfo) {
        this.mTransportInfo = transportInfo;
        return this;
    }

    public NetworkSpecifier getNetworkSpecifier() {
        return this.mNetworkSpecifier;
    }

    public TransportInfo getTransportInfo() {
        return this.mTransportInfo;
    }

    private void combineSpecifiers(NetworkCapabilities networkCapabilities) {
        NetworkSpecifier networkSpecifier = this.mNetworkSpecifier;
        if (networkSpecifier != null && !networkSpecifier.equals(networkCapabilities.mNetworkSpecifier)) {
            throw new IllegalStateException("Can't combine two networkSpecifiers");
        }
        setNetworkSpecifier(networkCapabilities.mNetworkSpecifier);
    }

    private boolean satisfiedBySpecifier(NetworkCapabilities networkCapabilities) {
        NetworkSpecifier networkSpecifier = this.mNetworkSpecifier;
        return networkSpecifier == null || networkSpecifier.canBeSatisfiedBy(networkCapabilities.mNetworkSpecifier) || (networkCapabilities.mNetworkSpecifier instanceof MatchAllNetworkSpecifier);
    }

    private boolean equalsSpecifier(NetworkCapabilities networkCapabilities) {
        return Objects.equals(this.mNetworkSpecifier, networkCapabilities.mNetworkSpecifier);
    }

    private void combineTransportInfos(NetworkCapabilities networkCapabilities) {
        TransportInfo transportInfo = this.mTransportInfo;
        if (transportInfo != null && !transportInfo.equals(networkCapabilities.mTransportInfo)) {
            throw new IllegalStateException("Can't combine two TransportInfos");
        }
        setTransportInfo(networkCapabilities.mTransportInfo);
    }

    private boolean equalsTransportInfo(NetworkCapabilities networkCapabilities) {
        return Objects.equals(this.mTransportInfo, networkCapabilities.mTransportInfo);
    }

    public NetworkCapabilities setSignalStrength(int i) {
        this.mSignalStrength = i;
        return this;
    }

    public boolean hasSignalStrength() {
        return this.mSignalStrength > Integer.MIN_VALUE;
    }

    public int getSignalStrength() {
        return this.mSignalStrength;
    }

    private void combineSignalStrength(NetworkCapabilities networkCapabilities) {
        this.mSignalStrength = Math.max(this.mSignalStrength, networkCapabilities.mSignalStrength);
    }

    private boolean satisfiedBySignalStrength(NetworkCapabilities networkCapabilities) {
        return this.mSignalStrength <= networkCapabilities.mSignalStrength;
    }

    private boolean equalsSignalStrength(NetworkCapabilities networkCapabilities) {
        return this.mSignalStrength == networkCapabilities.mSignalStrength;
    }

    public NetworkCapabilities setSingleUid(int i) {
        ArraySet arraySet = new ArraySet(1);
        arraySet.add(new UidRange(i, i));
        setUids(arraySet);
        return this;
    }

    public NetworkCapabilities setUids(Set<UidRange> set) {
        if (set == null) {
            this.mUids = null;
        } else {
            this.mUids = new ArraySet<>(set);
        }
        return this;
    }

    public Set<UidRange> getUids() {
        if (this.mUids == null) {
            return null;
        }
        return new ArraySet((ArraySet) this.mUids);
    }

    public boolean appliesToUid(int i) {
        ArraySet<UidRange> arraySet = this.mUids;
        if (arraySet == null) {
            return true;
        }
        Iterator<UidRange> it = arraySet.iterator();
        while (it.hasNext()) {
            if (it.next().contains(i)) {
                return true;
            }
        }
        return false;
    }

    public boolean equalsUids(NetworkCapabilities networkCapabilities) {
        ArraySet<UidRange> arraySet = networkCapabilities.mUids;
        if (arraySet == null) {
            return this.mUids == null;
        }
        if (this.mUids == null) {
            return false;
        }
        ArraySet arraySet2 = new ArraySet((ArraySet) this.mUids);
        for (UidRange uidRange : arraySet) {
            if (!arraySet2.contains(uidRange)) {
                return false;
            }
            arraySet2.remove(uidRange);
        }
        return arraySet2.isEmpty();
    }

    public boolean satisfiedByUids(NetworkCapabilities networkCapabilities) {
        ArraySet<UidRange> arraySet;
        if (networkCapabilities.mUids != null && (arraySet = this.mUids) != null) {
            for (UidRange uidRange : arraySet) {
                if (uidRange.contains(networkCapabilities.mOwnerUid)) {
                    return true;
                }
                if (!networkCapabilities.appliesToUidRange(uidRange)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean appliesToUidRange(UidRange uidRange) {
        ArraySet<UidRange> arraySet = this.mUids;
        if (arraySet == null) {
            return true;
        }
        Iterator<UidRange> it = arraySet.iterator();
        while (it.hasNext()) {
            if (it.next().containsRange(uidRange)) {
                return true;
            }
        }
        return false;
    }

    private void combineUids(NetworkCapabilities networkCapabilities) {
        ArraySet<UidRange> arraySet;
        ArraySet<UidRange> arraySet2 = networkCapabilities.mUids;
        if (arraySet2 == null || (arraySet = this.mUids) == null) {
            this.mUids = null;
        } else {
            arraySet.addAll((ArraySet<? extends UidRange>) arraySet2);
        }
    }

    public NetworkCapabilities setSSID(String str) {
        this.mSSID = str;
        return this;
    }

    @SystemApi
    public String getSsid() {
        return this.mSSID;
    }

    public boolean equalsSSID(NetworkCapabilities networkCapabilities) {
        return Objects.equals(this.mSSID, networkCapabilities.mSSID);
    }

    public boolean satisfiedBySSID(NetworkCapabilities networkCapabilities) {
        String str = this.mSSID;
        return str == null || str.equals(networkCapabilities.mSSID);
    }

    private void combineSSIDs(NetworkCapabilities networkCapabilities) {
        String str = this.mSSID;
        if (str != null && !str.equals(networkCapabilities.mSSID)) {
            throw new IllegalStateException("Can't combine two SSIDs");
        }
        setSSID(networkCapabilities.mSSID);
    }

    public void combineCapabilities(NetworkCapabilities networkCapabilities) {
        combineNetCapabilities(networkCapabilities);
        combineTransportTypes(networkCapabilities);
        combineLinkBandwidths(networkCapabilities);
        combineSpecifiers(networkCapabilities);
        combineTransportInfos(networkCapabilities);
        combineSignalStrength(networkCapabilities);
        combineUids(networkCapabilities);
        combineSSIDs(networkCapabilities);
        combineRequestor(networkCapabilities);
        combineAdministratorUids(networkCapabilities);
    }

    private boolean satisfiedByNetworkCapabilities(NetworkCapabilities networkCapabilities, boolean z) {
        return networkCapabilities != null && satisfiedByNetCapabilities(networkCapabilities, z) && satisfiedByTransportTypes(networkCapabilities) && (z || satisfiedByLinkBandwidths(networkCapabilities)) && satisfiedBySpecifier(networkCapabilities) && ((z || satisfiedBySignalStrength(networkCapabilities)) && ((z || satisfiedByUids(networkCapabilities)) && ((z || satisfiedBySSID(networkCapabilities)) && (z || satisfiedByRequestor(networkCapabilities)))));
    }

    @SystemApi
    public boolean satisfiedByNetworkCapabilities(NetworkCapabilities networkCapabilities) {
        return satisfiedByNetworkCapabilities(networkCapabilities, false);
    }

    public boolean satisfiedByImmutableNetworkCapabilities(NetworkCapabilities networkCapabilities) {
        return satisfiedByNetworkCapabilities(networkCapabilities, true);
    }

    public String describeImmutableDifferences(NetworkCapabilities networkCapabilities) {
        if (networkCapabilities == null) {
            return "other NetworkCapabilities was null";
        }
        StringJoiner stringJoiner = new StringJoiner(", ");
        long j = this.mNetworkCapabilities & (-54478849);
        long j2 = (-54478849) & networkCapabilities.mNetworkCapabilities;
        if (j != j2) {
            stringJoiner.add(String.format("immutable capabilities changed: %s -> %s", capabilityNamesOf(BitUtils.unpackBits(j)), capabilityNamesOf(BitUtils.unpackBits(j2))));
        }
        if (!equalsSpecifier(networkCapabilities)) {
            stringJoiner.add(String.format("specifier changed: %s -> %s", getNetworkSpecifier(), networkCapabilities.getNetworkSpecifier()));
        }
        if (!equalsTransportTypes(networkCapabilities)) {
            stringJoiner.add(String.format("transports changed: %s -> %s", transportNamesOf(getTransportTypes()), transportNamesOf(networkCapabilities.getTransportTypes())));
        }
        return stringJoiner.toString();
    }

    public boolean equalRequestableCapabilities(NetworkCapabilities networkCapabilities) {
        return networkCapabilities != null && equalsNetCapabilitiesRequestable(networkCapabilities) && equalsTransportTypes(networkCapabilities) && equalsSpecifier(networkCapabilities);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof NetworkCapabilities)) {
            return false;
        }
        NetworkCapabilities networkCapabilities = (NetworkCapabilities) obj;
        return equalsNetCapabilities(networkCapabilities) && equalsTransportTypes(networkCapabilities) && equalsLinkBandwidths(networkCapabilities) && equalsSignalStrength(networkCapabilities) && equalsSpecifier(networkCapabilities) && equalsTransportInfo(networkCapabilities) && equalsUids(networkCapabilities) && equalsSSID(networkCapabilities) && equalsPrivateDnsBroken(networkCapabilities) && equalsRequestor(networkCapabilities) && equalsAdministratorUids(networkCapabilities);
    }

    public int hashCode() {
        long j = this.mNetworkCapabilities;
        int i = ((int) (j & (-1))) + (((int) (j >> 32)) * 3);
        long j2 = this.mUnwantedNetworkCapabilities;
        int i2 = i + (((int) (j2 & (-1))) * 5) + (((int) (j2 >> 32)) * 7);
        long j3 = this.mTransportTypes;
        return i2 + (((int) ((-1) & j3)) * 11) + (((int) (j3 >> 32)) * 13) + (this.mLinkUpBandwidthKbps * 17) + (this.mLinkDownBandwidthKbps * 19) + (Objects.hashCode(this.mNetworkSpecifier) * 23) + (this.mSignalStrength * 29) + (Objects.hashCode(this.mUids) * 31) + (Objects.hashCode(this.mSSID) * 37) + (Objects.hashCode(this.mTransportInfo) * 41) + (Objects.hashCode(Boolean.valueOf(this.mPrivateDnsBroken)) * 43) + (Objects.hashCode(Integer.valueOf(this.mRequestorUid)) * 47) + (Objects.hashCode(this.mRequestorPackageName) * 53) + (Arrays.hashCode(this.mAdministratorUids) * 59);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.mNetworkCapabilities);
        parcel.writeLong(this.mUnwantedNetworkCapabilities);
        parcel.writeLong(this.mTransportTypes);
        parcel.writeInt(this.mLinkUpBandwidthKbps);
        parcel.writeInt(this.mLinkDownBandwidthKbps);
        parcel.writeParcelable((Parcelable) this.mNetworkSpecifier, i);
        parcel.writeParcelable((Parcelable) this.mTransportInfo, i);
        parcel.writeInt(this.mSignalStrength);
        parcel.writeArraySet(this.mUids);
        parcel.writeString(this.mSSID);
        parcel.writeBoolean(this.mPrivateDnsBroken);
        parcel.writeIntArray(getAdministratorUids());
        parcel.writeInt(this.mOwnerUid);
        parcel.writeInt(this.mRequestorUid);
        parcel.writeString(this.mRequestorPackageName);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        if (0 != this.mTransportTypes) {
            sb.append(" Transports: ");
            appendStringRepresentationOfBitMaskToStringBuilder(sb, this.mTransportTypes, new NameOf() { // from class: android.net.-$$Lambda$pe5WjjQqkwyt5gAS9woC__7glwM
                @Override // android.net.NetworkCapabilities.NameOf
                public final String nameOf(int i) {
                    return NetworkCapabilities.transportNameOf(i);
                }
            }, "|");
        }
        if (0 != this.mNetworkCapabilities) {
            sb.append(" Capabilities: ");
            appendStringRepresentationOfBitMaskToStringBuilder(sb, this.mNetworkCapabilities, new NameOf() { // from class: android.net.-$$Lambda$6jONm9iZeet7X1Mm7F-We1wpl7Y
                @Override // android.net.NetworkCapabilities.NameOf
                public final String nameOf(int i) {
                    return NetworkCapabilities.capabilityNameOf(i);
                }
            }, "&");
        }
        if (0 != this.mUnwantedNetworkCapabilities) {
            sb.append(" Unwanted: ");
            appendStringRepresentationOfBitMaskToStringBuilder(sb, this.mUnwantedNetworkCapabilities, new NameOf() { // from class: android.net.-$$Lambda$6jONm9iZeet7X1Mm7F-We1wpl7Y
                @Override // android.net.NetworkCapabilities.NameOf
                public final String nameOf(int i) {
                    return NetworkCapabilities.capabilityNameOf(i);
                }
            }, "&");
        }
        if (this.mLinkUpBandwidthKbps > 0) {
            sb.append(" LinkUpBandwidth>=").append(this.mLinkUpBandwidthKbps).append("Kbps");
        }
        if (this.mLinkDownBandwidthKbps > 0) {
            sb.append(" LinkDnBandwidth>=").append(this.mLinkDownBandwidthKbps).append("Kbps");
        }
        if (this.mNetworkSpecifier != null) {
            sb.append(" Specifier: <").append(this.mNetworkSpecifier).append(">");
        }
        if (this.mTransportInfo != null) {
            sb.append(" TransportInfo: <").append(this.mTransportInfo).append(">");
        }
        if (hasSignalStrength()) {
            sb.append(" SignalStrength: ").append(this.mSignalStrength);
        }
        ArraySet<UidRange> arraySet = this.mUids;
        if (arraySet != null) {
            if (1 == arraySet.size() && this.mUids.valueAt(0).count() == 1) {
                sb.append(" Uid: ").append(this.mUids.valueAt(0).start);
            } else {
                sb.append(" Uids: <").append(this.mUids).append(">");
            }
        }
        if (this.mOwnerUid != -1) {
            sb.append(" OwnerUid: ").append(this.mOwnerUid);
        }
        if (this.mAdministratorUids.length == 0) {
            sb.append(" AdministratorUids: ").append(Arrays.toString(this.mAdministratorUids));
        }
        if (this.mSSID != null) {
            sb.append(" SSID: ").append(this.mSSID);
        }
        if (this.mPrivateDnsBroken) {
            sb.append(" Private DNS is broken");
        }
        sb.append(" RequestorUid: ").append(this.mRequestorUid);
        sb.append(" RequestorPackageName: ").append(this.mRequestorPackageName);
        sb.append("]");
        return sb.toString();
    }

    public static void appendStringRepresentationOfBitMaskToStringBuilder(StringBuilder sb, long j, NameOf nameOf, String str) {
        boolean z = false;
        int i = 0;
        while (j != 0) {
            if ((1 & j) != 0) {
                if (z) {
                    sb.append(str);
                } else {
                    z = true;
                }
                sb.append(nameOf.nameOf(i));
            }
            j >>= 1;
            i++;
        }
    }

    public void dumpDebug(ProtoOutputStream protoOutputStream, long j) {
        long jStart = protoOutputStream.start(j);
        for (int i : getTransportTypes()) {
            protoOutputStream.write(2259152797697L, i);
        }
        for (int i2 : getCapabilities()) {
            protoOutputStream.write(2259152797698L, i2);
        }
        protoOutputStream.write(1120986464259L, this.mLinkUpBandwidthKbps);
        protoOutputStream.write(1120986464260L, this.mLinkDownBandwidthKbps);
        NetworkSpecifier networkSpecifier = this.mNetworkSpecifier;
        if (networkSpecifier != null) {
            protoOutputStream.write(1138166333445L, networkSpecifier.toString());
        }
        protoOutputStream.write(1133871366150L, hasSignalStrength());
        protoOutputStream.write(NetworkCapabilitiesProto.SIGNAL_STRENGTH, this.mSignalStrength);
        protoOutputStream.end(jStart);
    }

    public static String capabilityNamesOf(int[] iArr) {
        StringJoiner stringJoiner = new StringJoiner("|");
        if (iArr != null) {
            for (int i : iArr) {
                stringJoiner.add(capabilityNameOf(i));
            }
        }
        return stringJoiner.toString();
    }

    public static String capabilityNameOf(int i) {
        switch (i) {
            case 0:
                return "MMS";
            case 1:
                return "SUPL";
            case 2:
                return "DUN";
            case 3:
                return "FOTA";
            case 4:
                return "IMS";
            case 5:
                return "CBS";
            case 6:
                return "WIFI_P2P";
            case 7:
                return "IA";
            case 8:
                return "RCS";
            case 9:
                return "XCAP";
            case 10:
                return "EIMS";
            case 11:
                return "NOT_METERED";
            case 12:
                return "INTERNET";
            case 13:
                return "NOT_RESTRICTED";
            case 14:
                return "TRUSTED";
            case 15:
                return "NOT_VPN";
            case 16:
                return "VALIDATED";
            case 17:
                return "CAPTIVE_PORTAL";
            case 18:
                return "NOT_ROAMING";
            case 19:
                return "FOREGROUND";
            case 20:
                return "NOT_CONGESTED";
            case 21:
                return "NOT_SUSPENDED";
            case 22:
                return "OEM_PAID";
            case 23:
                return "MCX";
            case 24:
                return "PARTIAL_CONNECTIVITY";
            case 25:
                return "TEMPORARILY_NOT_METERED";
            default:
                return Integer.toString(i);
        }
    }

    public static String transportNamesOf(int[] iArr) {
        StringJoiner stringJoiner = new StringJoiner("|");
        if (iArr != null) {
            for (int i : iArr) {
                stringJoiner.add(transportNameOf(i));
            }
        }
        return stringJoiner.toString();
    }

    public static String transportNameOf(int i) {
        return !isValidTransport(i) ? "UNKNOWN" : TRANSPORT_NAMES[i];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkValidTransportType(int i) {
        Preconditions.checkArgument(isValidTransport(i), "Invalid TransportType " + i);
    }

    private static void checkValidCapability(int i) {
        Preconditions.checkArgument(isValidCapability(i), "NetworkCapability " + i + "out of range");
    }

    public boolean isMetered() {
        return !hasCapability(11);
    }

    public boolean isPrivateDnsBroken() {
        return this.mPrivateDnsBroken;
    }

    public void setPrivateDnsBroken(boolean z) {
        this.mPrivateDnsBroken = z;
    }

    private boolean equalsPrivateDnsBroken(NetworkCapabilities networkCapabilities) {
        return this.mPrivateDnsBroken == networkCapabilities.mPrivateDnsBroken;
    }

    public NetworkCapabilities setRequestorUid(int i) {
        this.mRequestorUid = i;
        return this;
    }

    public int getRequestorUid() {
        return this.mRequestorUid;
    }

    public NetworkCapabilities setRequestorPackageName(String str) {
        this.mRequestorPackageName = str;
        return this;
    }

    public String getRequestorPackageName() {
        return this.mRequestorPackageName;
    }

    public NetworkCapabilities setRequestorUidAndPackageName(int i, String str) {
        return setRequestorUid(i).setRequestorPackageName(str);
    }

    private boolean satisfiedByRequestor(NetworkCapabilities networkCapabilities) {
        int i;
        String str;
        int i2 = this.mRequestorUid;
        if (i2 != -1 && (i = networkCapabilities.mRequestorUid) != -1) {
            if (i2 != i) {
                return false;
            }
            String str2 = networkCapabilities.mRequestorPackageName;
            if (str2 != null && (str = this.mRequestorPackageName) != null) {
                return TextUtils.equals(str, str2);
            }
        }
        return true;
    }

    private void combineRequestor(NetworkCapabilities networkCapabilities) {
        int i = this.mRequestorUid;
        if (i != -1 && i != networkCapabilities.mOwnerUid) {
            throw new IllegalStateException("Can't combine two uids");
        }
        String str = this.mRequestorPackageName;
        if (str != null && !str.equals(networkCapabilities.mRequestorPackageName)) {
            throw new IllegalStateException("Can't combine two package names");
        }
        setRequestorUid(networkCapabilities.mRequestorUid);
        setRequestorPackageName(networkCapabilities.mRequestorPackageName);
    }

    private boolean equalsRequestor(NetworkCapabilities networkCapabilities) {
        return this.mRequestorUid == networkCapabilities.mRequestorUid && TextUtils.equals(this.mRequestorPackageName, networkCapabilities.mRequestorPackageName);
    }

    @SystemApi
    public static final class Builder {
        private final NetworkCapabilities mCaps;

        public Builder() {
            this.mCaps = new NetworkCapabilities();
        }

        public Builder(NetworkCapabilities networkCapabilities) {
            Objects.requireNonNull(networkCapabilities);
            this.mCaps = new NetworkCapabilities(networkCapabilities);
        }

        public Builder addTransportType(int i) {
            NetworkCapabilities.checkValidTransportType(i);
            this.mCaps.addTransportType(i);
            return this;
        }

        public Builder removeTransportType(int i) {
            NetworkCapabilities.checkValidTransportType(i);
            this.mCaps.removeTransportType(i);
            return this;
        }

        public Builder addCapability(int i) {
            this.mCaps.setCapability(i, true);
            return this;
        }

        public Builder removeCapability(int i) {
            this.mCaps.setCapability(i, false);
            return this;
        }

        public Builder setOwnerUid(int i) {
            this.mCaps.setOwnerUid(i);
            return this;
        }

        public Builder setAdministratorUids(int[] iArr) {
            Objects.requireNonNull(iArr);
            this.mCaps.setAdministratorUids(iArr);
            return this;
        }

        public Builder setLinkUpstreamBandwidthKbps(int i) {
            this.mCaps.setLinkUpstreamBandwidthKbps(i);
            return this;
        }

        public Builder setLinkDownstreamBandwidthKbps(int i) {
            this.mCaps.setLinkDownstreamBandwidthKbps(i);
            return this;
        }

        public Builder setNetworkSpecifier(NetworkSpecifier networkSpecifier) {
            this.mCaps.setNetworkSpecifier(networkSpecifier);
            return this;
        }

        public Builder setTransportInfo(TransportInfo transportInfo) {
            this.mCaps.setTransportInfo(transportInfo);
            return this;
        }

        public Builder setSignalStrength(int i) {
            this.mCaps.setSignalStrength(i);
            return this;
        }

        public Builder setSsid(String str) {
            this.mCaps.setSSID(str);
            return this;
        }

        public Builder setRequestorUid(int i) {
            this.mCaps.setRequestorUid(i);
            return this;
        }

        public Builder setRequestorPackageName(String str) {
            this.mCaps.setRequestorPackageName(str);
            return this;
        }

        public NetworkCapabilities build() {
            if (this.mCaps.getOwnerUid() != -1 && !ArrayUtils.contains(this.mCaps.getAdministratorUids(), this.mCaps.getOwnerUid())) {
                throw new IllegalStateException("The owner UID must be included in  administrator UIDs.");
            }
            return new NetworkCapabilities(this.mCaps);
        }
    }
}
