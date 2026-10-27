package ts.car.service.firewall;

import android.os.RemoteException;
import java.util.List;
import ts.car.service.IPlatformService;
import ts.car.service.firewall.data.ApplicationRule;

/* JADX INFO: loaded from: classes.dex */
public interface IFirewallManager extends IPlatformService {
    boolean addApplicationRule(Boolean bool, List<ApplicationRule> list) throws RemoteException;

    boolean addIpRule(Boolean bool, List<String> list, List<String> list2) throws RemoteException;

    boolean addPortRule(Boolean bool, List<Integer> list, List<String> list2) throws RemoteException;

    boolean addUrlRule(Boolean bool, List<String> list) throws RemoteException;

    List<String> getInterfaces() throws RemoteException;

    boolean getLogs(StringBuilder sb, long j, long j2) throws RemoteException;

    boolean getRules(int i, int i2) throws RemoteException;

    boolean isFirewallEnabled() throws RemoteException;

    boolean isReady() throws RemoteException;

    boolean isWhiteListEnabled() throws RemoteException;

    boolean registerListener(IFirewallListener iFirewallListener) throws RemoteException;

    boolean registerResponse(IFirewallResponse iFirewallResponse) throws RemoteException;

    boolean removeApplicationRule(Boolean bool, List<Integer> list, List<String> list2) throws RemoteException;

    boolean removeIpRule(Boolean bool, List<String> list, List<String> list2) throws RemoteException;

    boolean removePortRule(Boolean bool, List<Integer> list, List<String> list2) throws RemoteException;

    boolean removeUrlRule(Boolean bool, List<String> list) throws RemoteException;

    boolean setFirewallEnable(Boolean bool) throws RemoteException;

    boolean setWhiteListEnable(Boolean bool) throws RemoteException;

    boolean unregisterListener(IFirewallListener iFirewallListener) throws RemoteException;

    boolean unregisterResponse(IFirewallResponse iFirewallResponse) throws RemoteException;
}
