package com.ts.firewall.service.firewallhaladapter;

import com.ts.firewall.service.data.ApplicationRule;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface FirewallHalInterface {
    boolean addApplicationRule(int i, boolean z, List<ApplicationRule> list);

    boolean addIpRule(int i, boolean z, List<String> list, List<String> list2);

    boolean addPortRule(int i, boolean z, List<Integer> list, List<String> list2);

    boolean addUrlRule(int i, boolean z, List<String> list);

    List<String> getInterfaces();

    boolean getLogs(int i, long j, long j2);

    boolean getRules(int i, int i2);

    void init(FirewallHalListener firewallHalListener);

    boolean isFirewallEnabled();

    boolean isWhiteListEnabled();

    boolean removeApplicationRule(int i, boolean z, List<Integer> list);

    boolean removeIpRule(int i, boolean z, List<String> list, List<String> list2);

    boolean removePortRule(int i, boolean z, List<Integer> list, List<String> list2);

    boolean removeUrlRule(int i, boolean z, List<String> list);

    boolean setFirewallEnable(boolean z);

    boolean setWhiteListEnable(boolean z);

    void unit();
}
