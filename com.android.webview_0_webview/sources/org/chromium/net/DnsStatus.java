package org.chromium.net;

import java.net.InetAddress;
import java.util.List;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class DnsStatus {
    public final List a;
    public final boolean b;
    public final String c;

    public DnsStatus(List list, boolean z, String str) {
        this.a = list;
        this.b = z;
        this.c = str == null ? "" : str;
    }

    public byte[][] getDnsServers() {
        byte[][] bArr = new byte[this.a.size()][];
        for (int i = 0; i < this.a.size(); i++) {
            bArr[i] = ((InetAddress) this.a.get(i)).getAddress();
        }
        return bArr;
    }

    public boolean getPrivateDnsActive() {
        return this.b;
    }

    public String getPrivateDnsServerName() {
        return this.c;
    }
}
