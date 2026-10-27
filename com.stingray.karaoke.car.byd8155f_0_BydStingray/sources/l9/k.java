package l9;

import c9.D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import n7.AbstractC2000o;

/* JADX INFO: loaded from: classes.dex */
public final class k extends o {
    public static final boolean c;

    static {
        String property = System.getProperty("java.specification.version");
        Integer numA0 = property != null ? Q8.o.A0(property) : null;
        boolean z6 = false;
        if (numA0 == null) {
            try {
                SSLSocket.class.getMethod("getApplicationProtocol", null);
                z6 = true;
            } catch (NoSuchMethodException unused) {
            }
        } else if (numA0.intValue() >= 9) {
            z6 = true;
        }
        c = z6;
    }

    @Override // l9.o
    public final void d(SSLSocket sSLSocket, String str, List list) {
        A7.m.f(list, "protocols");
        SSLParameters sSLParameters = sSLSocket.getSSLParameters();
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((D) obj) != D.HTTP_1_0) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((D) it.next()).l);
        }
        sSLParameters.setApplicationProtocols((String[]) arrayList2.toArray(new String[0]));
        sSLSocket.setSSLParameters(sSLParameters);
    }

    @Override // l9.o
    public final String f(SSLSocket sSLSocket) {
        try {
            String applicationProtocol = sSLSocket.getApplicationProtocol();
            if (applicationProtocol == null ? true : applicationProtocol.equals("")) {
                return null;
            }
            return applicationProtocol;
        } catch (UnsupportedOperationException unused) {
            return null;
        }
    }
}
