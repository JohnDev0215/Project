package defpackage;

import android.content.pm.ResolveInfo;
import defpackage.al2;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: compiled from: ShareSheetUsageRankingHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fl2 {
    public static final Set<String> h;
    public final ji a;
    public final cl2 b;
    public final Profile c;
    public final vk2 d;
    public final long e;
    public final int f;
    public final al2.a g;

    /* JADX INFO: compiled from: ShareSheetUsageRankingHelper.java */
    public static class a implements Comparator<ResolveInfo> {
        @Override // java.util.Comparator
        public final int compare(ResolveInfo resolveInfo, ResolveInfo resolveInfo2) {
            return resolveInfo.activityInfo.packageName.compareTo(resolveInfo2.activityInfo.packageName);
        }
    }

    static {
        Object[] objArr = {"com.android.cts.ctsshim", "com.android.cts.priv.ctsshim"};
        HashSet hashSet = new HashSet(2);
        for (int i = 0; i < 2; i++) {
            Object obj = objArr[i];
            Objects.requireNonNull(obj);
            if (!hashSet.add(obj)) {
                throw new IllegalArgumentException("duplicate element: " + obj);
            }
        }
        h = Collections.unmodifiableSet(hashSet);
    }

    public fl2(ji jiVar, vk2 vk2Var, long j, int i, al2.a aVar, cl2 cl2Var, Profile profile) {
        this.a = jiVar;
        this.b = cl2Var;
        this.c = profile;
        this.d = vk2Var;
        this.e = j;
        this.f = i;
        this.g = aVar;
    }
}
