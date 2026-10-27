package org.chromium.chrome.browser.offlinepages;

import J.N;
import defpackage.ar1;
import defpackage.cb2;
import java.util.ArrayList;
import java.util.UUID;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: loaded from: classes2.dex */
public final class RequestCoordinatorBridge {
    public final Profile a;

    public static class RequestsRemovedCallback {
        @CalledByNative
        public void onResult(long[] jArr, int[] iArr) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jArr.length; i++) {
                long j = jArr[i];
                int i2 = iArr[i];
                arrayList.add(new a());
            }
            throw null;
        }
    }

    public static class a {
    }

    public RequestCoordinatorBridge(Profile profile) {
        this.a = profile;
    }

    public final void a(String str, String str2) {
        ar1 ar1Var = new ar1();
        String string = UUID.randomUUID().toString();
        N.Mh8zWNaq(this.a, new cb2(), str, str2, string, ar1Var.a(), true);
    }
}
