package android.net.shared;

import android.net.INetd;
import android.net.IpPrefix;
import android.net.RouteInfo;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RouteUtils {

    public enum ModifyOperation {
        ADD,
        REMOVE
    }

    private static String findNextHop(RouteInfo routeInfo) {
        int type = routeInfo.getType();
        return type != 1 ? type != 7 ? type != 9 ? "" : "throw" : "unreachable" : routeInfo.hasGateway() ? routeInfo.getGateway().getHostAddress() : "";
    }

    public static void addRoutesToLocalNetwork(INetd iNetd, String str, List<RouteInfo> list) {
        for (RouteInfo routeInfo : list) {
            if (!routeInfo.isDefaultRoute()) {
                modifyRoute(iNetd, ModifyOperation.ADD, 99, routeInfo);
            }
        }
        modifyRoute(iNetd, ModifyOperation.ADD, 99, new RouteInfo(new IpPrefix("fe80::/64"), null, str, 1));
    }

    public static int removeRoutesFromLocalNetwork(INetd iNetd, List<RouteInfo> list) {
        Iterator<RouteInfo> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            try {
                modifyRoute(iNetd, ModifyOperation.REMOVE, 99, it.next());
            } catch (IllegalStateException unused) {
                i++;
            }
        }
        return i;
    }

    public static void modifyRoute(INetd iNetd, ModifyOperation modifyOperation, int i, RouteInfo routeInfo) {
        String str = routeInfo.getInterface();
        String string = routeInfo.getDestination().toString();
        String strFindNextHop = findNextHop(routeInfo);
        try {
            int i2 = AnonymousClass1.$SwitchMap$android$net$shared$RouteUtils$ModifyOperation[modifyOperation.ordinal()];
            if (i2 == 1) {
                iNetd.networkAddRoute(i, str, string, strFindNextHop);
            } else {
                if (i2 == 2) {
                    iNetd.networkRemoveRoute(i, str, string, strFindNextHop);
                    return;
                }
                throw new IllegalStateException("Unsupported modify operation:" + modifyOperation);
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
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$net$shared$RouteUtils$ModifyOperation[ModifyOperation.REMOVE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
