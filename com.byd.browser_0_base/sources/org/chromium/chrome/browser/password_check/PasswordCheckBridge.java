package org.chromium.chrome.browser.password_check;

import J.N;
import java.util.Iterator;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.chrome.browser.password_check.a;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public class PasswordCheckBridge {
    public long a = N.MC$M7l1y(this);
    public final a b;

    public interface a {
    }

    public PasswordCheckBridge(a aVar) {
        this.b = aVar;
    }

    @CalledByNative
    public static void insertCredential(CompromisedCredential[] compromisedCredentialArr, int i, String str, GURL gurl, String str2, String str3, String str4, String str5, String str6, String str7, long j, long j2, boolean z, boolean z2) {
        compromisedCredentialArr[i] = new CompromisedCredential(str, gurl, str2, str3, str4, str5, str6, str7, j, j2, z, z2);
    }

    @CalledByNative
    public void onCompromisedCredentialsFetched(int i) {
        e eVar = (e) this.b;
        eVar.d = true;
        Iterator<a.InterfaceC0101a> it = eVar.b.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a.InterfaceC0101a) aVar.next()).c();
            }
        }
    }

    @CalledByNative
    public void onPasswordCheckProgressChanged(int i, int i2) {
        Iterator<a.InterfaceC0101a> it = ((e) this.b).b.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a.InterfaceC0101a) aVar.next()).f(i, i2);
            }
        }
    }

    @CalledByNative
    public void onPasswordCheckStatusChanged(int i) {
        e eVar = (e) this.b;
        eVar.f = i;
        Iterator<a.InterfaceC0101a> it = eVar.b.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a.InterfaceC0101a) aVar.next()).b(i);
            }
        }
    }

    @CalledByNative
    public void onSavedPasswordsFetched(int i) {
        e eVar = (e) this.b;
        eVar.e = true;
        Iterator<a.InterfaceC0101a> it = eVar.b.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a.InterfaceC0101a) aVar.next()).e();
            }
        }
    }
}
