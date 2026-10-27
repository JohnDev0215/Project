package org.chromium.chrome.browser.usage_stats;

import J.N;
import com.google.protobuf.d0;
import defpackage.ct3;
import defpackage.ei0;
import defpackage.fk3;
import defpackage.fm3;
import defpackage.gi0;
import defpackage.m62;
import defpackage.p62;
import defpackage.s62;
import defpackage.x10;
import defpackage.z7;
import defpackage.zs3;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: loaded from: classes2.dex */
public class UsageStatsBridge {
    public final fk3 a;
    public final long b;

    public UsageStatsBridge(Profile profile, fk3 fk3Var) {
        this.b = N.MZTYueAb(this, profile);
        this.a = fk3Var;
    }

    @CalledByNative
    public static void createEventListAndRunCallback(byte[][] bArr, Callback<List<zs3>> callback) {
        ArrayList arrayList = new ArrayList(bArr.length);
        for (byte[] bArr2 : bArr) {
            try {
                arrayList.add(zs3.X(bArr2));
            } catch (d0 unused) {
            }
        }
        callback.onResult(arrayList);
    }

    @CalledByNative
    public static void createMapAndRunCallback(String[] strArr, String[] strArr2, Callback<Map<String, String>> callback) {
        HashMap map = new HashMap(strArr.length);
        for (int i = 0; i < strArr.length; i++) {
            map.put(strArr[i], strArr2[i]);
        }
        callback.onResult(map);
    }

    @CalledByNative
    public final void onAllHistoryDeleted() {
        fk3 fk3Var = this.a;
        fk3Var.getClass();
        Object obj = ThreadUtils.a;
        z7.d0(7);
        fk3Var.g.getClass();
        s62.c(null);
        gi0 gi0Var = fk3Var.b;
        gi0Var.getClass();
        s62 s62Var = new s62();
        gi0Var.b.h(new p62(gi0Var, 13, s62Var), new m62(7));
        s62Var.a(new x10(19, fk3Var));
    }

    @CalledByNative
    public final void onHistoryDeletedForDomains(String[] strArr) {
        ArrayList arrayList = new ArrayList(Arrays.asList(strArr));
        fk3 fk3Var = this.a;
        fk3Var.getClass();
        Object obj = ThreadUtils.a;
        z7.d0(9);
        fk3Var.g.getClass();
        s62.c(null);
        gi0 gi0Var = fk3Var.b;
        gi0Var.getClass();
        s62 s62Var = new s62();
        gi0Var.b.h(new ct3(gi0Var, 3, arrayList, s62Var), new fm3(7));
        s62Var.a(new p62(fk3Var, 15, arrayList));
    }

    @CalledByNative
    public final void onHistoryDeletedInRange(final long j, final long j2) {
        final fk3 fk3Var = this.a;
        fk3Var.getClass();
        Object obj = ThreadUtils.a;
        z7.d0(8);
        long jMin = Math.min(j2, System.currentTimeMillis());
        fk3Var.g.getClass();
        s62.c(null);
        gi0 gi0Var = fk3Var.b;
        gi0Var.getClass();
        s62 s62Var = new s62();
        gi0Var.b.h(new ei0(gi0Var, j, jMin, s62Var), new fm3(8));
        s62Var.a(new Callback() { // from class: ek3
            @Override // org.chromium.base.Callback
            public final void onResult(Object obj2) {
                gi0 gi0Var2 = fk3Var.b;
                long j3 = j;
                long j4 = j2;
                gi0Var2.getClass();
                s62 s62Var2 = new s62();
                gi0Var2.b.h(new ei0(gi0Var2, j3, j4, s62Var2), new fm3(8));
                s62Var2.a(new ps(21));
            }
        });
    }
}
