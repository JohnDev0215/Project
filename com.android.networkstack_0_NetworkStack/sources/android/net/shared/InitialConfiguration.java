package android.net.shared;

import android.net.InetAddresses;
import android.net.InitialConfigurationParcelable;
import android.net.IpPrefix;
import android.net.LinkAddress;
import android.net.RouteInfo;
import android.text.TextUtils;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
public class InitialConfiguration {
    public static final InetAddress INET6_ANY = InetAddresses.parseNumericAddress("::");
    public final Set<LinkAddress> ipAddresses = new HashSet();
    public final Set<IpPrefix> directlyConnectedRoutes = new HashSet();
    public final Set<InetAddress> dnsServers = new HashSet();

    private static boolean isCompliantIPv6PrefixLength(int i) {
        return 48 <= i && i <= 64;
    }

    public static InitialConfiguration copy(InitialConfiguration initialConfiguration) {
        if (initialConfiguration == null) {
            return null;
        }
        InitialConfiguration initialConfiguration2 = new InitialConfiguration();
        initialConfiguration2.ipAddresses.addAll(initialConfiguration.ipAddresses);
        initialConfiguration2.directlyConnectedRoutes.addAll(initialConfiguration.directlyConnectedRoutes);
        initialConfiguration2.dnsServers.addAll(initialConfiguration.dnsServers);
        return initialConfiguration2;
    }

    public String toString() {
        return String.format("InitialConfiguration(IPs: {%s}, prefixes: {%s}, DNS: {%s})", TextUtils.join(", ", this.ipAddresses), TextUtils.join(", ", this.directlyConnectedRoutes), TextUtils.join(", ", this.dnsServers));
    }

    public boolean isValid() {
        if (this.ipAddresses.isEmpty()) {
            return false;
        }
        for (final LinkAddress linkAddress : this.ipAddresses) {
            if (!any(this.directlyConnectedRoutes, new Predicate() { // from class: android.net.shared.-$$Lambda$InitialConfiguration$-me_MsIS7iJUFBnz7VXb_bkuZ1g
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((IpPrefix) obj).contains(linkAddress.getAddress());
                }
            })) {
                return false;
            }
        }
        for (final InetAddress inetAddress : this.dnsServers) {
            if (!any(this.directlyConnectedRoutes, new Predicate() { // from class: android.net.shared.-$$Lambda$InitialConfiguration$fPcApZyObbcs0b_t0iDOb7uyyVo
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((IpPrefix) obj).contains(inetAddress);
                }
            })) {
                return false;
            }
        }
        if (any(this.ipAddresses, not(new Predicate() { // from class: android.net.shared.-$$Lambda$InitialConfiguration$V8CoLWaph9IN8YQg4mdBlxzUFkI
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return InitialConfiguration.isPrefixLengthCompliant((LinkAddress) obj);
            }
        }))) {
            return false;
        }
        return ((any(this.directlyConnectedRoutes, new Predicate() { // from class: android.net.shared.-$$Lambda$InitialConfiguration$p_tYzrU5UtVU6wwyLn0miHqz-00
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return InitialConfiguration.isIPv6DefaultRoute((IpPrefix) obj);
            }
        }) && all(this.ipAddresses, not(new Predicate() { // from class: android.net.shared.-$$Lambda$InitialConfiguration$w5pXtjcZU54QER7TNMAvC4NSrfg
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return InitialConfiguration.isIPv6GUA((LinkAddress) obj);
            }
        }))) || any(this.directlyConnectedRoutes, not(new Predicate() { // from class: android.net.shared.-$$Lambda$InitialConfiguration$GEWT0gKtlkvLtgqIAII3RLQt3xQ
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return InitialConfiguration.isPrefixLengthCompliant((IpPrefix) obj);
            }
        })) || this.ipAddresses.stream().filter(new Predicate() { // from class: android.net.shared.-$$Lambda$InitialConfiguration$58qOz8A9XDsHfGLzFKkSY-aJR6w
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return InitialConfiguration.isIPv4((LinkAddress) obj);
            }
        }).count() > 1) ? false : true;
    }

    public boolean isProvisionedBy(List<LinkAddress> list, List<RouteInfo> list2) {
        if (this.ipAddresses.isEmpty()) {
            return false;
        }
        for (final LinkAddress linkAddress : this.ipAddresses) {
            if (!any(list, new Predicate() { // from class: android.net.shared.-$$Lambda$InitialConfiguration$52iZTx1bKOBJkSzJZ2-XL1b1D8g
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return linkAddress.isSameAddressAs((LinkAddress) obj);
                }
            })) {
                return false;
            }
        }
        if (list2 == null) {
            return true;
        }
        for (final IpPrefix ipPrefix : this.directlyConnectedRoutes) {
            if (!any(list2, new Predicate() { // from class: android.net.shared.-$$Lambda$InitialConfiguration$4Dtg_2trFtjFoeKOpr0rSIEUXu8
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return InitialConfiguration.isDirectlyConnectedRoute((RouteInfo) obj, ipPrefix);
                }
            })) {
                return false;
            }
        }
        return true;
    }

    public static InitialConfiguration fromStableParcelable(InitialConfigurationParcelable initialConfigurationParcelable) {
        if (initialConfigurationParcelable == null) {
            return null;
        }
        InitialConfiguration initialConfiguration = new InitialConfiguration();
        initialConfiguration.ipAddresses.addAll(Arrays.asList(initialConfigurationParcelable.ipAddresses));
        initialConfiguration.directlyConnectedRoutes.addAll(Arrays.asList(initialConfigurationParcelable.directlyConnectedRoutes));
        initialConfiguration.dnsServers.addAll(ParcelableUtil.fromParcelableArray(initialConfigurationParcelable.dnsServers, $$Lambda$SYWvjOUPlAZ_O2Z6yfFU9np1858.INSTANCE));
        return initialConfiguration;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof InitialConfiguration)) {
            return false;
        }
        InitialConfiguration initialConfiguration = (InitialConfiguration) obj;
        return this.ipAddresses.equals(initialConfiguration.ipAddresses) && this.directlyConnectedRoutes.equals(initialConfiguration.directlyConnectedRoutes) && this.dnsServers.equals(initialConfiguration.dnsServers);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isDirectlyConnectedRoute(RouteInfo routeInfo, IpPrefix ipPrefix) {
        return !routeInfo.hasGateway() && ipPrefix.equals(routeInfo.getDestination());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isPrefixLengthCompliant(LinkAddress linkAddress) {
        return isIPv4(linkAddress) || isCompliantIPv6PrefixLength(linkAddress.getPrefixLength());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isPrefixLengthCompliant(IpPrefix ipPrefix) {
        return isIPv4(ipPrefix) || isCompliantIPv6PrefixLength(ipPrefix.getPrefixLength());
    }

    private static boolean isIPv4(IpPrefix ipPrefix) {
        return ipPrefix.getAddress() instanceof Inet4Address;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isIPv4(LinkAddress linkAddress) {
        return linkAddress.getAddress() instanceof Inet4Address;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isIPv6DefaultRoute(IpPrefix ipPrefix) {
        return ipPrefix.getAddress().equals(INET6_ANY);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isIPv6GUA(LinkAddress linkAddress) {
        return linkAddress.isIpv6() && linkAddress.isGlobalPreferred();
    }

    public static <T> boolean any(Iterable<T> iterable, Predicate<T> predicate) {
        Iterator<T> it = iterable.iterator();
        while (it.hasNext()) {
            if (predicate.test(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean all(Iterable<T> iterable, Predicate<T> predicate) {
        return !any(iterable, not(predicate));
    }

    static /* synthetic */ boolean lambda$not$4(Predicate predicate, Object obj) {
        return !predicate.test(obj);
    }

    public static <T> Predicate<T> not(final Predicate<T> predicate) {
        return new Predicate() { // from class: android.net.shared.-$$Lambda$InitialConfiguration$MwQ3SqOgt4uewKbIAElg1lpdfTI
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return InitialConfiguration.lambda$not$4(predicate, obj);
            }
        };
    }
}
