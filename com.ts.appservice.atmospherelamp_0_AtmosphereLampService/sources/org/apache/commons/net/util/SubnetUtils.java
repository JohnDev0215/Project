package org.apache.commons.net.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes3.dex */
public class SubnetUtils {
    private static final int NBITS = 32;
    private static final String PARSE_FAIL = "Could not parse [%s]";
    private final int address;
    private final int broadcast;
    private boolean inclusiveHostCount = false;
    private final int netmask;
    private final int network;
    private static final String IP_ADDRESS = "(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})";
    private static final Pattern addressPattern = Pattern.compile(IP_ADDRESS);
    private static final String SLASH_FORMAT = "(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})/(\\d{1,2})";
    private static final Pattern cidrPattern = Pattern.compile(SLASH_FORMAT);

    public SubnetUtils(String cidrNotation) {
        Matcher matcher = cidrPattern.matcher(cidrNotation);
        if (matcher.matches()) {
            this.address = matchAddress(matcher);
            int trailingZeroes = 32 - rangeCheck(Integer.parseInt(matcher.group(5)), 0, 32);
            int i = (int) (4294967295 << trailingZeroes);
            this.netmask = i;
            int i2 = this.address & i;
            this.network = i2;
            this.broadcast = (~i) | i2;
            return;
        }
        throw new IllegalArgumentException(String.format(PARSE_FAIL, cidrNotation));
    }

    public SubnetUtils(String address, String mask) {
        this.address = toInteger(address);
        int integer = toInteger(mask);
        this.netmask = integer;
        if (((-integer) & integer) - 1 != (~integer)) {
            throw new IllegalArgumentException(String.format(PARSE_FAIL, mask));
        }
        int i = this.address & integer;
        this.network = i;
        this.broadcast = i | (~integer);
    }

    public boolean isInclusiveHostCount() {
        return this.inclusiveHostCount;
    }

    public void setInclusiveHostCount(boolean inclusiveHostCount) {
        this.inclusiveHostCount = inclusiveHostCount;
    }

    public final class SubnetInfo {
        private static final long UNSIGNED_INT_MASK = 4294967295L;

        private SubnetInfo() {
        }

        private long networkLong() {
            return ((long) SubnetUtils.this.network) & UNSIGNED_INT_MASK;
        }

        private long broadcastLong() {
            return ((long) SubnetUtils.this.broadcast) & UNSIGNED_INT_MASK;
        }

        private int low() {
            if (SubnetUtils.this.isInclusiveHostCount()) {
                return SubnetUtils.this.network;
            }
            if (broadcastLong() - networkLong() > 1) {
                return SubnetUtils.this.network + 1;
            }
            return 0;
        }

        private int high() {
            if (SubnetUtils.this.isInclusiveHostCount()) {
                return SubnetUtils.this.broadcast;
            }
            if (broadcastLong() - networkLong() > 1) {
                return SubnetUtils.this.broadcast - 1;
            }
            return 0;
        }

        public boolean isInRange(String address) {
            return isInRange(SubnetUtils.toInteger(address));
        }

        public boolean isInRange(int address) {
            if (address == 0) {
                return false;
            }
            long addLong = ((long) address) & UNSIGNED_INT_MASK;
            long lowLong = ((long) low()) & UNSIGNED_INT_MASK;
            long highLong = UNSIGNED_INT_MASK & ((long) high());
            return addLong >= lowLong && addLong <= highLong;
        }

        public String getBroadcastAddress() {
            return format(toArray(SubnetUtils.this.broadcast));
        }

        public String getNetworkAddress() {
            return format(toArray(SubnetUtils.this.network));
        }

        public String getNetmask() {
            return format(toArray(SubnetUtils.this.netmask));
        }

        public String getAddress() {
            return format(toArray(SubnetUtils.this.address));
        }

        public String getNextAddress() {
            return format(toArray(SubnetUtils.this.address + 1));
        }

        public String getPreviousAddress() {
            return format(toArray(SubnetUtils.this.address - 1));
        }

        public String getLowAddress() {
            return format(toArray(low()));
        }

        public String getHighAddress() {
            return format(toArray(high()));
        }

        @Deprecated
        public int getAddressCount() {
            long countLong = getAddressCountLong();
            if (countLong > 2147483647L) {
                throw new RuntimeException("Count is larger than an integer: " + countLong);
            }
            return (int) countLong;
        }

        public long getAddressCountLong() {
            long b = broadcastLong();
            long n = networkLong();
            long count = (b - n) + ((long) (SubnetUtils.this.isInclusiveHostCount() ? 1 : -1));
            if (count < 0) {
                return 0L;
            }
            return count;
        }

        public int asInteger(String address) {
            return SubnetUtils.toInteger(address);
        }

        public String getCidrSignature() {
            StringBuilder sb = new StringBuilder();
            sb.append(format(toArray(SubnetUtils.this.address)));
            sb.append("/");
            SubnetUtils subnetUtils = SubnetUtils.this;
            sb.append(subnetUtils.pop(subnetUtils.netmask));
            return sb.toString();
        }

        public String[] getAllAddresses() {
            int ct = getAddressCount();
            String[] addresses = new String[ct];
            if (ct == 0) {
                return addresses;
            }
            int add = low();
            int j = 0;
            while (add <= high()) {
                addresses[j] = format(toArray(add));
                add++;
                j++;
            }
            return addresses;
        }

        private int[] toArray(int val) {
            int[] ret = new int[4];
            for (int j = 3; j >= 0; j--) {
                ret[j] = ret[j] | ((val >>> ((3 - j) * 8)) & 255);
            }
            return ret;
        }

        private String format(int[] octets) {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < octets.length; i++) {
                str.append(octets[i]);
                if (i != octets.length - 1) {
                    str.append(".");
                }
            }
            return str.toString();
        }

        public String toString() {
            return "CIDR Signature:\t[" + getCidrSignature() + "] Netmask: [" + getNetmask() + "]\nNetwork:\t[" + getNetworkAddress() + "]\nBroadcast:\t[" + getBroadcastAddress() + "]\nFirst Address:\t[" + getLowAddress() + "]\nLast Address:\t[" + getHighAddress() + "]\n# Addresses:\t[" + getAddressCount() + "]\n";
        }
    }

    public final SubnetInfo getInfo() {
        return new SubnetInfo();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int toInteger(String address) {
        Matcher matcher = addressPattern.matcher(address);
        if (matcher.matches()) {
            return matchAddress(matcher);
        }
        throw new IllegalArgumentException(String.format(PARSE_FAIL, address));
    }

    private static int matchAddress(Matcher matcher) {
        int addr = 0;
        for (int i = 1; i <= 4; i++) {
            int n = rangeCheck(Integer.parseInt(matcher.group(i)), 0, 255);
            addr |= (n & 255) << ((4 - i) * 8);
        }
        return addr;
    }

    private static int rangeCheck(int value, int begin, int end) {
        if (value >= begin && value <= end) {
            return value;
        }
        throw new IllegalArgumentException("Value [" + value + "] not in range [" + begin + "," + end + "]");
    }

    int pop(int x) {
        int x2 = x - ((x >>> 1) & 1431655765);
        int x3 = (x2 & 858993459) + (858993459 & (x2 >>> 2));
        int x4 = ((x3 >>> 4) + x3) & 252645135;
        int x5 = x4 + (x4 >>> 8);
        return (x5 + (x5 >>> 16)) & 63;
    }

    public SubnetUtils getNext() {
        return new SubnetUtils(getInfo().getNextAddress(), getInfo().getNetmask());
    }

    public SubnetUtils getPrevious() {
        return new SubnetUtils(getInfo().getPreviousAddress(), getInfo().getNetmask());
    }
}
