package defpackage;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: re, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1444re {
    public static final Status d = new Status(8, "The connection to Google Play services was lost");
    public static final BasePendingResult[] e = new BasePendingResult[0];
    public final Set a = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));
    public final InterfaceC1499se b = new C1390qe(this);
    public final Map c;

    public C1444re(Map map) {
        this.c = map;
    }

    public final void a(BasePendingResult basePendingResult) {
        this.a.add(basePendingResult);
        basePendingResult.g.set(this.b);
    }
}
