package ts.car.service.firewall;

/* JADX INFO: loaded from: classes.dex */
public interface IFirewallListener {
    void onFirewallStatusChange(Boolean bool);

    void onWhiteListStatusChange(boolean z);
}
