package android.net.dhcp;

import android.net.LinkAddress;
import android.net.networkstack.DhcpResults;
import android.net.util.IpMask;
import android.os.Build;
import android.os.SystemProperties;
import android.system.OsConstants;
import android.text.TextUtils;
import com.android.networkstack.apishim.common.ShimUtils;
import com.android.networkstack.util.Inet4AddressUtils;
import com.android.server.util.NetworkStackConstants;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class DhcpPacket {
    protected boolean mBroadcast;
    protected Inet4Address mBroadcastAddress;
    protected String mCaptivePortalUrl;
    protected byte[] mClientId;
    public final Inet4Address mClientIp;
    protected final byte[] mClientMac;
    protected List<Inet4Address> mDnsServers;
    protected String mDomainName;
    protected List<Inet4Address> mGateways;
    protected String mHostName;
    protected Integer mLeaseTime;
    protected String mMessage;
    protected Short mMtu;
    private final Inet4Address mNextIp;
    protected boolean mRapidCommit;
    protected final Inet4Address mRelayIp;
    public Inet4Address mRequestedIp;
    protected byte[] mRequestedParams;
    protected final short mSecs;
    protected String mServerHostName;
    public Inet4Address mServerIdentifier;
    protected Inet4Address mSubnetMask;
    protected final int mTransId;
    protected String mVendorInfo;
    protected final Inet4Address mYourIp;
    public static final Inet4Address INADDR_ANY = NetworkStackConstants.IPV4_ADDR_ANY;
    public static final Inet4Address INADDR_BROADCAST = NetworkStackConstants.IPV4_ADDR_ALL;
    public static final byte[] ETHER_BROADCAST = {-1, -1, -1, -1, -1, -1};
    static String testOverrideVendorId = null;

    private static int intAbs(short s) {
        return s & 65535;
    }

    private static boolean isPacketServerToServer(short s, short s2) {
        return s == 67 && s2 == 67;
    }

    private static boolean isPacketToOrFromClient(short s, short s2) {
        return s == 68 || s2 == 68;
    }

    public abstract ByteBuffer buildPacket(int i, short s, short s2);

    abstract void finishPacket(ByteBuffer byteBuffer);

    protected DhcpPacket(int i, short s, Inet4Address inet4Address, Inet4Address inet4Address2, Inet4Address inet4Address3, Inet4Address inet4Address4, byte[] bArr, boolean z) {
        this.mTransId = i;
        this.mSecs = s;
        this.mClientIp = inet4Address;
        this.mYourIp = inet4Address2;
        this.mNextIp = inet4Address3;
        this.mRelayIp = inet4Address4;
        this.mClientMac = bArr;
        this.mBroadcast = z;
    }

    public int getTransactionId() {
        return this.mTransId;
    }

    public byte[] getClientMac() {
        return this.mClientMac;
    }

    public boolean hasExplicitClientId() {
        return this.mClientId != null;
    }

    public byte[] getExplicitClientIdOrNull() {
        if (hasExplicitClientId()) {
            return getClientId();
        }
        return null;
    }

    public byte[] getClientId() {
        if (hasExplicitClientId()) {
            byte[] bArr = this.mClientId;
            return Arrays.copyOf(bArr, bArr.length);
        }
        byte[] bArr2 = this.mClientMac;
        byte[] bArr3 = new byte[bArr2.length + 1];
        bArr3[0] = 1;
        System.arraycopy(bArr2, 0, bArr3, 1, bArr2.length);
        return bArr3;
    }

    public boolean hasRequestedParam(byte b) {
        byte[] bArr = this.mRequestedParams;
        if (bArr == null) {
            return false;
        }
        for (byte b2 : bArr) {
            if (b2 == b) {
                return true;
            }
        }
        return false;
    }

    protected void fillInPacket(int i, Inet4Address inet4Address, Inet4Address inet4Address2, short s, short s2, ByteBuffer byteBuffer, byte b, boolean z) {
        int iPosition;
        int iPosition2;
        int iPosition3;
        int iPosition4;
        int iPosition5;
        int iPosition6;
        int iPosition7;
        byte[] address = inet4Address.getAddress();
        byte[] address2 = inet4Address2.getAddress();
        byteBuffer.clear();
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
        if (i == 0) {
            byteBuffer.put(ETHER_BROADCAST);
            byteBuffer.put(this.mClientMac);
            byteBuffer.putShort((short) OsConstants.ETH_P_IP);
        }
        if (i <= 1) {
            iPosition3 = byteBuffer.position();
            byteBuffer.put((byte) 69);
            byteBuffer.put((byte) 16);
            iPosition4 = byteBuffer.position();
            byteBuffer.putShort((short) 0);
            byteBuffer.putShort((short) 0);
            byteBuffer.putShort((short) 16384);
            byteBuffer.put((byte) 64);
            byteBuffer.put((byte) 17);
            iPosition5 = byteBuffer.position();
            byteBuffer.putShort((short) 0);
            byteBuffer.put(address2);
            byteBuffer.put(address);
            iPosition = byteBuffer.position();
            iPosition2 = byteBuffer.position();
            byteBuffer.putShort(s2);
            byteBuffer.putShort(s);
            iPosition6 = byteBuffer.position();
            byteBuffer.putShort((short) 0);
            iPosition7 = byteBuffer.position();
            byteBuffer.putShort((short) 0);
        } else {
            iPosition = 0;
            iPosition2 = 0;
            iPosition3 = 0;
            iPosition4 = 0;
            iPosition5 = 0;
            iPosition6 = 0;
            iPosition7 = 0;
        }
        byteBuffer.put(b);
        byteBuffer.put((byte) 1);
        byteBuffer.put((byte) this.mClientMac.length);
        byteBuffer.put((byte) 0);
        byteBuffer.putInt(this.mTransId);
        byteBuffer.putShort(this.mSecs);
        if (z) {
            byteBuffer.putShort(Short.MIN_VALUE);
        } else {
            byteBuffer.putShort((short) 0);
        }
        byteBuffer.put(this.mClientIp.getAddress());
        byteBuffer.put(this.mYourIp.getAddress());
        byteBuffer.put(this.mNextIp.getAddress());
        byteBuffer.put(this.mRelayIp.getAddress());
        byteBuffer.put(this.mClientMac);
        byteBuffer.position(byteBuffer.position() + (16 - this.mClientMac.length) + 64 + 128);
        byteBuffer.putInt(1669485411);
        finishPacket(byteBuffer);
        if ((byteBuffer.position() & 1) == 1) {
            byteBuffer.put((byte) 0);
        }
        if (i <= 1) {
            short sPosition = (short) (byteBuffer.position() - iPosition2);
            byteBuffer.putShort(iPosition6, sPosition);
            byteBuffer.putShort(iPosition7, (short) checksum(byteBuffer, intAbs(byteBuffer.getShort(iPosition5 + 2)) + 0 + intAbs(byteBuffer.getShort(iPosition5 + 4)) + intAbs(byteBuffer.getShort(iPosition5 + 6)) + intAbs(byteBuffer.getShort(iPosition5 + 8)) + 17 + sPosition, iPosition2, byteBuffer.position()));
            byteBuffer.putShort(iPosition4, (short) (byteBuffer.position() - iPosition3));
            byteBuffer.putShort(iPosition5, (short) checksum(byteBuffer, 0, iPosition3, iPosition));
        }
    }

    private int checksum(ByteBuffer byteBuffer, int i, int i2, int i3) {
        int iPosition = byteBuffer.position();
        byteBuffer.position(i2);
        ShortBuffer shortBufferAsShortBuffer = byteBuffer.asShortBuffer();
        byteBuffer.position(iPosition);
        int i4 = (i3 - i2) / 2;
        short[] sArr = new short[i4];
        shortBufferAsShortBuffer.get(sArr);
        for (int i5 = 0; i5 < i4; i5++) {
            i += intAbs(sArr[i5]);
        }
        int i6 = i2 + (i4 * 2);
        if (i3 != i6) {
            short s = byteBuffer.get(i6);
            if (s < 0) {
                s = (short) (s + 256);
            }
            i += s * 256;
        }
        int i7 = ((i >> 16) & 65535) + (i & 65535);
        return intAbs((short) (~((i7 + ((i7 >> 16) & 65535)) & 65535)));
    }

    protected static void addTlv(ByteBuffer byteBuffer, byte b, byte b2) {
        byteBuffer.put(b);
        byteBuffer.put((byte) 1);
        byteBuffer.put(b2);
    }

    protected static void addTlv(ByteBuffer byteBuffer, byte b) {
        byteBuffer.put(b);
        byteBuffer.put((byte) 0);
    }

    protected static void addTlv(ByteBuffer byteBuffer, byte b, byte[] bArr) {
        if (bArr != null) {
            if (bArr.length > 255) {
                throw new IllegalArgumentException("DHCP option too long: " + bArr.length + " vs. 255");
            }
            byteBuffer.put(b);
            byteBuffer.put((byte) bArr.length);
            byteBuffer.put(bArr);
        }
    }

    protected static void addTlv(ByteBuffer byteBuffer, byte b, Inet4Address inet4Address) {
        if (inet4Address != null) {
            addTlv(byteBuffer, b, inet4Address.getAddress());
        }
    }

    protected static void addTlv(ByteBuffer byteBuffer, byte b, List<Inet4Address> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        int size = list.size() * 4;
        if (size > 255) {
            throw new IllegalArgumentException("DHCP option too long: " + size + " vs. 255");
        }
        byteBuffer.put(b);
        byteBuffer.put((byte) size);
        Iterator<Inet4Address> it = list.iterator();
        while (it.hasNext()) {
            byteBuffer.put(it.next().getAddress());
        }
    }

    protected static void addTlv(ByteBuffer byteBuffer, byte b, Short sh) {
        if (sh != null) {
            byteBuffer.put(b);
            byteBuffer.put((byte) 2);
            byteBuffer.putShort(sh.shortValue());
        }
    }

    protected static void addTlv(ByteBuffer byteBuffer, byte b, Integer num) {
        if (num != null) {
            byteBuffer.put(b);
            byteBuffer.put((byte) 4);
            byteBuffer.putInt(num.intValue());
        }
    }

    protected static void addTlv(ByteBuffer byteBuffer, byte b, String str) {
        if (str != null) {
            try {
                addTlv(byteBuffer, b, str.getBytes("US-ASCII"));
            } catch (UnsupportedEncodingException unused) {
                throw new IllegalArgumentException("String is not US-ASCII: " + str);
            }
        }
    }

    protected static void addTlvEnd(ByteBuffer byteBuffer) {
        byteBuffer.put((byte) -1);
    }

    private String getVendorId() {
        String str = testOverrideVendorId;
        if (str != null) {
            return str;
        }
        return "android-dhcp-" + Build.VERSION.RELEASE;
    }

    /* JADX WARN: Failed to analyze thrown exceptions
    java.util.ConcurrentModificationException
    	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1095)
    	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:1049)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:130)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:178)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:131)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:178)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:131)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
     */
    public String getHostname() {
        if (this.mHostName == null && !ShimUtils.isReleaseOrDevelopmentApiAbove(29)) {
            return SystemProperties.get("net.hostname");
        }
        return this.mHostName;
    }

    protected void addCommonClientTlvs(ByteBuffer byteBuffer) {
        addTlv(byteBuffer, (byte) 57, (Short) 1500);
        addTlv(byteBuffer, (byte) 60, getVendorId());
        String hostname = getHostname();
        if (TextUtils.isEmpty(hostname)) {
            return;
        }
        addTlv(byteBuffer, (byte) 12, hostname);
    }

    protected void addCommonServerTlvs(ByteBuffer byteBuffer) {
        addTlv(byteBuffer, (byte) 51, this.mLeaseTime);
        Integer num = this.mLeaseTime;
        if (num != null && num.intValue() != -1) {
            addTlv(byteBuffer, (byte) 58, Integer.valueOf((int) (Integer.toUnsignedLong(this.mLeaseTime.intValue()) / 2)));
            addTlv(byteBuffer, (byte) 59, Integer.valueOf((int) ((Integer.toUnsignedLong(this.mLeaseTime.intValue()) * 875) / 1000)));
        }
        addTlv(byteBuffer, (byte) 1, this.mSubnetMask);
        addTlv(byteBuffer, (byte) 28, this.mBroadcastAddress);
        addTlv(byteBuffer, (byte) 3, this.mGateways);
        addTlv(byteBuffer, (byte) 6, this.mDnsServers);
        addTlv(byteBuffer, (byte) 15, this.mDomainName);
        addTlv(byteBuffer, (byte) 12, this.mHostName);
        addTlv(byteBuffer, (byte) 43, this.mVendorInfo);
        Short sh = this.mMtu;
        if (sh != null && Short.toUnsignedInt(sh.shortValue()) >= 68) {
            addTlv(byteBuffer, (byte) 26, this.mMtu);
        }
        addTlv(byteBuffer, (byte) 114, this.mCaptivePortalUrl);
    }

    public static String macToString(byte[] bArr) {
        String str = "";
        for (int i = 0; i < bArr.length; i++) {
            str = str + ("0" + Integer.toHexString(bArr[i])).substring(r2.length() - 2);
            if (i != bArr.length - 1) {
                str = str + ":";
            }
        }
        return str;
    }

    public String toString() {
        return IpMask.maskMacString(macToString(this.mClientMac));
    }

    private static Inet4Address readIpAddress(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[4];
        byteBuffer.get(bArr);
        try {
            return (Inet4Address) Inet4Address.getByAddress(bArr);
        } catch (UnknownHostException unused) {
            return null;
        }
    }

    private static String readAsciiString(ByteBuffer byteBuffer, int i, boolean z) {
        byte[] bArr = new byte[i];
        byteBuffer.get(bArr);
        if (!z) {
            int i2 = 0;
            while (i2 < i && bArr[i2] != 0) {
                i2++;
            }
            i = i2;
        }
        return new String(bArr, 0, i, StandardCharsets.US_ASCII);
    }

    public static class ParseException extends Exception {
        public final int errorCode;

        public ParseException(int i, String str, Object... objArr) {
            super(String.format(str, objArr));
            this.errorCode = i;
        }
    }

    private static int skipOption(ByteBuffer byteBuffer, int i) throws BufferUnderflowException {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2++;
            byteBuffer.get();
        }
        return i2;
    }

    private static boolean shouldSkipOption(byte b, byte[] bArr) {
        for (byte b2 : bArr) {
            if (b2 == b) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:86:0x0227. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:143:0x03f4  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x03f7  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x02d1 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static android.net.dhcp.DhcpPacket decodeFullPacket(java.nio.ByteBuffer r39, int r40, byte[] r41) throws android.net.dhcp.DhcpPacket.ParseException {
        /*
            Method dump skipped, instruction units count: 1176
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.net.dhcp.DhcpPacket.decodeFullPacket(java.nio.ByteBuffer, int, byte[]):android.net.dhcp.DhcpPacket");
    }

    public static DhcpPacket decodeFullPacket(byte[] bArr, int i, int i2, byte[] bArr2) throws ParseException {
        try {
            return decodeFullPacket(ByteBuffer.wrap(bArr, 0, i).order(ByteOrder.BIG_ENDIAN), i2, bArr2);
        } catch (ParseException e) {
            throw e;
        } catch (Exception e2) {
            throw new ParseException(84082688, e2.getMessage(), new Object[0]);
        }
    }

    public static DhcpPacket decodeFullPacket(byte[] bArr, int i, int i2) throws ParseException {
        return decodeFullPacket(bArr, i, i2, new byte[0]);
    }

    public DhcpResults toDhcpResults() {
        int iNetmaskToPrefixLength;
        Inet4Address inet4Address = this.mYourIp;
        if (inet4Address.equals(NetworkStackConstants.IPV4_ADDR_ANY)) {
            inet4Address = this.mClientIp;
            if (inet4Address.equals(NetworkStackConstants.IPV4_ADDR_ANY)) {
                return null;
            }
        }
        Inet4Address inet4Address2 = this.mSubnetMask;
        if (inet4Address2 != null) {
            try {
                iNetmaskToPrefixLength = Inet4AddressUtils.netmaskToPrefixLength(inet4Address2);
            } catch (IllegalArgumentException unused) {
                return null;
            }
        } else {
            iNetmaskToPrefixLength = Inet4AddressUtils.getImplicitNetmask(inet4Address);
        }
        DhcpResults dhcpResults = new DhcpResults();
        try {
            dhcpResults.ipAddress = new LinkAddress(inet4Address, iNetmaskToPrefixLength);
            short sShortValue = 0;
            if (this.mGateways.size() > 0) {
                dhcpResults.gateway = this.mGateways.get(0);
            }
            dhcpResults.dnsServers.addAll(this.mDnsServers);
            dhcpResults.domains = this.mDomainName;
            dhcpResults.serverAddress = this.mServerIdentifier;
            dhcpResults.vendorInfo = this.mVendorInfo;
            Integer num = this.mLeaseTime;
            dhcpResults.leaseDuration = num != null ? num.intValue() : -1;
            Short sh = this.mMtu;
            if (sh != null && 1280 <= sh.shortValue() && this.mMtu.shortValue() <= 1500) {
                sShortValue = this.mMtu.shortValue();
            }
            dhcpResults.mtu = sShortValue;
            dhcpResults.serverHostName = this.mServerHostName;
            dhcpResults.captivePortalApiUrl = this.mCaptivePortalUrl;
            return dhcpResults;
        } catch (IllegalArgumentException unused2) {
            return null;
        }
    }

    public long getLeaseTimeMillis() {
        Integer num = this.mLeaseTime;
        if (num == null || num.intValue() == -1) {
            return 0L;
        }
        if (this.mLeaseTime.intValue() < 0 || this.mLeaseTime.intValue() >= 60) {
            return (((long) this.mLeaseTime.intValue()) & 4294967295L) * 1000;
        }
        return 60000L;
    }

    public static ByteBuffer buildDiscoverPacket(int i, int i2, short s, byte[] bArr, boolean z, byte[] bArr2, boolean z2, String str) {
        Inet4Address inet4Address = INADDR_ANY;
        DhcpDiscoverPacket dhcpDiscoverPacket = new DhcpDiscoverPacket(i2, s, inet4Address, bArr, z, inet4Address, z2);
        dhcpDiscoverPacket.mRequestedParams = bArr2;
        dhcpDiscoverPacket.mHostName = str;
        return dhcpDiscoverPacket.buildPacket(i, (short) 67, (short) 68);
    }

    public static ByteBuffer buildOfferPacket(int i, int i2, boolean z, Inet4Address inet4Address, Inet4Address inet4Address2, Inet4Address inet4Address3, byte[] bArr, Integer num, Inet4Address inet4Address4, Inet4Address inet4Address5, List<Inet4Address> list, List<Inet4Address> list2, Inet4Address inet4Address6, String str, String str2, boolean z2, short s, String str3) {
        DhcpOfferPacket dhcpOfferPacket = new DhcpOfferPacket(i2, (short) 0, z, inet4Address, inet4Address2, INADDR_ANY, inet4Address3, bArr);
        dhcpOfferPacket.mGateways = list;
        dhcpOfferPacket.mDnsServers = list2;
        dhcpOfferPacket.mLeaseTime = num;
        dhcpOfferPacket.mDomainName = str;
        dhcpOfferPacket.mHostName = str2;
        dhcpOfferPacket.mServerIdentifier = inet4Address6;
        dhcpOfferPacket.mSubnetMask = inet4Address4;
        dhcpOfferPacket.mBroadcastAddress = inet4Address5;
        dhcpOfferPacket.mMtu = Short.valueOf(s);
        dhcpOfferPacket.mCaptivePortalUrl = str3;
        if (z2) {
            dhcpOfferPacket.mVendorInfo = "ANDROID_METERED";
        }
        return dhcpOfferPacket.buildPacket(i, (short) 68, (short) 67);
    }

    public static ByteBuffer buildAckPacket(int i, int i2, boolean z, Inet4Address inet4Address, Inet4Address inet4Address2, Inet4Address inet4Address3, Inet4Address inet4Address4, byte[] bArr, Integer num, Inet4Address inet4Address5, Inet4Address inet4Address6, List<Inet4Address> list, List<Inet4Address> list2, Inet4Address inet4Address7, String str, String str2, boolean z2, short s, boolean z3, String str3) {
        DhcpAckPacket dhcpAckPacket = new DhcpAckPacket(i2, (short) 0, z, inet4Address, inet4Address2, inet4Address4, inet4Address3, bArr, z3);
        dhcpAckPacket.mGateways = list;
        dhcpAckPacket.mDnsServers = list2;
        dhcpAckPacket.mLeaseTime = num;
        dhcpAckPacket.mDomainName = str;
        dhcpAckPacket.mHostName = str2;
        dhcpAckPacket.mSubnetMask = inet4Address5;
        dhcpAckPacket.mServerIdentifier = inet4Address7;
        dhcpAckPacket.mBroadcastAddress = inet4Address6;
        dhcpAckPacket.mMtu = Short.valueOf(s);
        dhcpAckPacket.mCaptivePortalUrl = str3;
        if (z2) {
            dhcpAckPacket.mVendorInfo = "ANDROID_METERED";
        }
        return dhcpAckPacket.buildPacket(i, (short) 68, (short) 67);
    }

    public static ByteBuffer buildNakPacket(int i, int i2, Inet4Address inet4Address, Inet4Address inet4Address2, byte[] bArr, boolean z, String str) {
        DhcpNakPacket dhcpNakPacket = new DhcpNakPacket(i2, (short) 0, inet4Address2, bArr, z);
        dhcpNakPacket.mMessage = str;
        dhcpNakPacket.mServerIdentifier = inet4Address;
        return dhcpNakPacket.buildPacket(i, (short) 68, (short) 67);
    }

    public static ByteBuffer buildRequestPacket(int i, int i2, short s, Inet4Address inet4Address, boolean z, byte[] bArr, Inet4Address inet4Address2, Inet4Address inet4Address3, byte[] bArr2, String str) {
        DhcpRequestPacket dhcpRequestPacket = new DhcpRequestPacket(i2, s, inet4Address, INADDR_ANY, bArr, z);
        dhcpRequestPacket.mRequestedIp = inet4Address2;
        dhcpRequestPacket.mServerIdentifier = inet4Address3;
        dhcpRequestPacket.mHostName = str;
        dhcpRequestPacket.mRequestedParams = bArr2;
        return dhcpRequestPacket.buildPacket(i, (short) 67, (short) 68);
    }

    public static ByteBuffer buildDeclinePacket(int i, int i2, byte[] bArr, Inet4Address inet4Address, Inet4Address inet4Address2) {
        Inet4Address inet4Address3 = INADDR_ANY;
        return new DhcpDeclinePacket(i2, (short) 0, inet4Address3, inet4Address3, inet4Address3, inet4Address3, bArr, inet4Address, inet4Address2).buildPacket(i, (short) 67, (short) 68);
    }
}
