package android.net;

import android.bluetooth.BluetoothAdapter;
import android.net.util.MacAddressUtils;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.SettingsStringUtil;
import com.android.internal.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.Inet6Address;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class MacAddress implements Parcelable {
    public static final MacAddress ALL_ZEROS_ADDRESS;
    private static final MacAddress BASE_GOOGLE_MAC;
    public static final MacAddress BROADCAST_ADDRESS;
    public static final Parcelable.Creator<MacAddress> CREATOR;
    private static final MacAddress DEFAULT_MAC_ADDRESS;
    private static final byte[] ETHER_ADDR_BROADCAST;
    private static final int ETHER_ADDR_LEN = 6;
    private static final long LOCALLY_ASSIGNED_MASK;
    private static final long MULTICAST_MASK;
    private static final long NIC_MASK;
    private static final long OUI_MASK;
    public static final int TYPE_BROADCAST = 3;
    public static final int TYPE_MULTICAST = 2;
    public static final int TYPE_UNICAST = 1;
    public static final int TYPE_UNKNOWN = 0;
    private static final long VALID_LONG_MASK = 281474976710655L;
    private final long mAddr;

    @Retention(RetentionPolicy.SOURCE)
    public @interface MacAddressType {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    static {
        byte[] bArrAddr = addr(255, 255, 255, 255, 255, 255);
        ETHER_ADDR_BROADCAST = bArrAddr;
        BROADCAST_ADDRESS = fromBytes(bArrAddr);
        ALL_ZEROS_ADDRESS = new MacAddress(0L);
        LOCALLY_ASSIGNED_MASK = fromString("2:0:0:0:0:0").mAddr;
        MULTICAST_MASK = fromString("1:0:0:0:0:0").mAddr;
        OUI_MASK = fromString("ff:ff:ff:0:0:0").mAddr;
        NIC_MASK = fromString("0:0:0:ff:ff:ff").mAddr;
        BASE_GOOGLE_MAC = fromString("da:a1:19:0:0:0");
        DEFAULT_MAC_ADDRESS = fromString(BluetoothAdapter.DEFAULT_MAC_ADDRESS);
        CREATOR = new Parcelable.Creator<MacAddress>() { // from class: android.net.MacAddress.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public MacAddress createFromParcel(Parcel parcel) {
                return new MacAddress(parcel.readLong());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public MacAddress[] newArray(int i) {
                return new MacAddress[i];
            }
        };
    }

    private MacAddress(long j) {
        this.mAddr = j & VALID_LONG_MASK;
    }

    public int getAddressType() {
        if (equals(BROADCAST_ADDRESS)) {
            return 3;
        }
        return (this.mAddr & MULTICAST_MASK) != 0 ? 2 : 1;
    }

    public boolean isLocallyAssigned() {
        return (this.mAddr & LOCALLY_ASSIGNED_MASK) != 0;
    }

    public byte[] toByteArray() {
        return byteAddrFromLongAddr(this.mAddr);
    }

    public String toString() {
        return stringAddrFromLongAddr(this.mAddr);
    }

    public String toOuiString() {
        return String.format("%02x:%02x:%02x", Long.valueOf((this.mAddr >> 40) & 255), Long.valueOf((this.mAddr >> 32) & 255), Long.valueOf((this.mAddr >> 24) & 255));
    }

    public int hashCode() {
        long j = this.mAddr;
        return (int) (j ^ (j >> 32));
    }

    public boolean equals(Object obj) {
        return (obj instanceof MacAddress) && ((MacAddress) obj).mAddr == this.mAddr;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.mAddr);
    }

    public static boolean isMacAddress(byte[] bArr) {
        return MacAddressUtils.isMacAddress(bArr);
    }

    public static int macAddressType(byte[] bArr) {
        if (isMacAddress(bArr)) {
            return fromBytes(bArr).getAddressType();
        }
        return 0;
    }

    public static byte[] byteAddrFromStringAddr(String str) {
        Preconditions.checkNotNull(str);
        String[] strArrSplit = str.split(SettingsStringUtil.DELIMITER);
        if (strArrSplit.length != 6) {
            throw new IllegalArgumentException(str + " was not a valid MAC address");
        }
        byte[] bArr = new byte[6];
        for (int i = 0; i < 6; i++) {
            int iIntValue = Integer.valueOf(strArrSplit[i], 16).intValue();
            if (iIntValue < 0 || 255 < iIntValue) {
                throw new IllegalArgumentException(str + "was not a valid MAC address");
            }
            bArr[i] = (byte) iIntValue;
        }
        return bArr;
    }

    public static String stringAddrFromByteAddr(byte[] bArr) {
        if (isMacAddress(bArr)) {
            return String.format("%02x:%02x:%02x:%02x:%02x:%02x", Byte.valueOf(bArr[0]), Byte.valueOf(bArr[1]), Byte.valueOf(bArr[2]), Byte.valueOf(bArr[3]), Byte.valueOf(bArr[4]), Byte.valueOf(bArr[5]));
        }
        return null;
    }

    private static byte[] byteAddrFromLongAddr(long j) {
        return MacAddressUtils.byteAddrFromLongAddr(j);
    }

    private static long longAddrFromByteAddr(byte[] bArr) {
        return MacAddressUtils.longAddrFromByteAddr(bArr);
    }

    private static long longAddrFromStringAddr(String str) {
        Preconditions.checkNotNull(str);
        String[] strArrSplit = str.split(SettingsStringUtil.DELIMITER);
        if (strArrSplit.length != 6) {
            throw new IllegalArgumentException(str + " was not a valid MAC address");
        }
        long j = 0;
        for (String str2 : strArrSplit) {
            int iIntValue = Integer.valueOf(str2, 16).intValue();
            if (iIntValue < 0 || 255 < iIntValue) {
                throw new IllegalArgumentException(str + "was not a valid MAC address");
            }
            j = (j << 8) + ((long) iIntValue);
        }
        return j;
    }

    private static String stringAddrFromLongAddr(long j) {
        return String.format("%02x:%02x:%02x:%02x:%02x:%02x", Long.valueOf((j >> 40) & 255), Long.valueOf((j >> 32) & 255), Long.valueOf((j >> 24) & 255), Long.valueOf((j >> 16) & 255), Long.valueOf((j >> 8) & 255), Long.valueOf(j & 255));
    }

    public static MacAddress fromString(String str) {
        return new MacAddress(longAddrFromStringAddr(str));
    }

    public static MacAddress fromBytes(byte[] bArr) {
        return new MacAddress(longAddrFromByteAddr(bArr));
    }

    public static MacAddress createRandomUnicastAddressWithGoogleBase() {
        return MacAddressUtils.createRandomUnicastAddress(BASE_GOOGLE_MAC, new SecureRandom());
    }

    private static byte[] addr(int... iArr) {
        if (iArr.length != 6) {
            throw new IllegalArgumentException(Arrays.toString(iArr) + " was not an array with length equal to 6");
        }
        byte[] bArr = new byte[6];
        for (int i = 0; i < 6; i++) {
            bArr[i] = (byte) iArr[i];
        }
        return bArr;
    }

    public boolean matches(MacAddress macAddress, MacAddress macAddress2) {
        Preconditions.checkNotNull(macAddress);
        Preconditions.checkNotNull(macAddress2);
        long j = this.mAddr;
        long j2 = macAddress2.mAddr;
        return (j & j2) == (macAddress.mAddr & j2);
    }

    public Inet6Address getLinkLocalIpv6FromEui48Mac() {
        byte[] byteArray = toByteArray();
        try {
            return Inet6Address.getByAddress((String) null, new byte[]{-2, -128, 0, 0, 0, 0, 0, 0, (byte) (byteArray[0] ^ 2), byteArray[1], byteArray[2], -1, -2, byteArray[3], byteArray[4], byteArray[5]}, 0);
        } catch (UnknownHostException unused) {
            return null;
        }
    }
}
