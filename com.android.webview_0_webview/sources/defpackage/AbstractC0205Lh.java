package defpackage;

import java.io.Serializable;

/* JADX INFO: renamed from: Lh, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0205Lh {
    public static InterfaceC0222Mh a(InterfaceC0222Mh interfaceC0222Mh) {
        return ((interfaceC0222Mh instanceof C0240Nh) || (interfaceC0222Mh instanceof C0258Oh)) ? interfaceC0222Mh : interfaceC0222Mh instanceof Serializable ? new C0258Oh(interfaceC0222Mh) : new C0240Nh(interfaceC0222Mh);
    }
}
