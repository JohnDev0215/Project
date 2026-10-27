package android.net.shared;

import android.net.INetd;
import android.net.IpPrefix;
import android.net.RouteInfo;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RouteUtils {

    public enum ModifyOperation {
        ADD,
        REMOVE
    }

    private static String findNextHop(RouteInfo route) {
        int type = route.getType();
        if (type == 1) {
            if (route.hasGateway()) {
                String nextHop = route.getGateway().getHostAddress();
                return nextHop;
            }
            return INetd.NEXTHOP_NONE;
        }
        if (type == 7) {
            return INetd.NEXTHOP_UNREACHABLE;
        }
        if (type == 9) {
            return INetd.NEXTHOP_THROW;
        }
        return INetd.NEXTHOP_NONE;
    }

    public static void addRoutesToLocalNetwork(INetd netd, String iface, List<RouteInfo> routes) {
        for (RouteInfo route : routes) {
            if (!route.isDefaultRoute()) {
                modifyRoute(netd, ModifyOperation.ADD, 99, route);
            }
        }
        modifyRoute(netd, ModifyOperation.ADD, 99, new RouteInfo(new IpPrefix("fe80::/64"), null, iface, 1));
    }

    public static int removeRoutesFromLocalNetwork(INetd netd, List<RouteInfo> routes) {
        int failures = 0;
        for (RouteInfo route : routes) {
            try {
                modifyRoute(netd, ModifyOperation.REMOVE, 99, route);
            } catch (IllegalStateException e) {
                failures++;
            }
        }
        return failures;
    }

    public static void modifyRoute(INetd netd, ModifyOperation op, int netId, RouteInfo route) {
        String ifName = route.getInterface();
        String dst = route.getDestination().toString();
        String nextHop = findNextHop(route);
        try {
            int i = AnonymousClass1.$SwitchMap$android$net$shared$RouteUtils$ModifyOperation[op.ordinal()];
            if (i == 1) {
                netd.networkAddRoute(netId, ifName, dst, nextHop);
            } else {
                if (i == 2) {
                    netd.networkRemoveRoute(netId, ifName, dst, nextHop);
                    return;
                }
                throw new IllegalStateException("Unsupported modify operation:" + op);
            }
        } catch (RemoteException | ServiceSpecificException e) {
            throw new IllegalStateException(e);
        }
    }

    /* JADX INFO: renamed from: android.net.shared.RouteUtils$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$net$shared$RouteUtils$ModifyOperation;

        static {
            int[] iArr = new int[ModifyOperation.values().length];
            $SwitchMap$android$net$shared$RouteUtils$ModifyOperation = iArr;
            try {
                iArr[ModifyOperation.ADD.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$net$shared$RouteUtils$ModifyOperation[ModifyOperation.REMOVE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }
}
