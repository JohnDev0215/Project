package com.byd.data.common.b.e;

import f.s;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ApiDns.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements s {
    @Override // f.s
    public List<InetAddress> b(String str) throws UnknownHostException {
        if (str == null) {
            throw new UnknownHostException("hostname == null");
        }
        try {
            ArrayList arrayList = new ArrayList();
            for (InetAddress inetAddress : InetAddress.getAllByName(str)) {
                if (inetAddress instanceof Inet4Address) {
                    arrayList.add(0, inetAddress);
                } else {
                    arrayList.add(inetAddress);
                }
            }
            return arrayList;
        } catch (NullPointerException e2) {
            UnknownHostException unknownHostException = new UnknownHostException("Broken system behaviour");
            unknownHostException.initCause(e2);
            throw unknownHostException;
        }
    }
}
