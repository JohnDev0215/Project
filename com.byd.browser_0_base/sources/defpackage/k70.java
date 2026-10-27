package defpackage;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.base.b;

/* JADX INFO: compiled from: DefaultBrowserInfo2.java */
/* JADX INFO: loaded from: classes.dex */
public final class k70 {
    public static b a;

    /* JADX INFO: compiled from: DefaultBrowserInfo2.java */
    public static class a {
        public final int a;

        public a(int i) {
            this.a = i;
        }
    }

    /* JADX INFO: compiled from: DefaultBrowserInfo2.java */
    public static class b extends i9<a> {
        public static final /* synthetic */ int i = 0;
        public final org.chromium.base.b<Callback<a>> h = new org.chromium.base.b<>();

        @Override // defpackage.i9
        public final a b() {
            Context context = xz.a;
            ResolveInfo resolveInfoE = wt1.e();
            if (resolveInfoE != null && resolveInfoE.match != 0) {
                TextUtils.equals(context.getPackageName(), resolveInfoE.activityInfo.applicationInfo.packageName);
                int i2 = resolveInfoE.activityInfo.applicationInfo.flags;
            }
            HashSet hashSet = new HashSet();
            List<ResolveInfo> listC = wt1.c(wt1.a, 983040);
            if (listC != null) {
                for (ResolveInfo resolveInfo : listC) {
                    if (hashSet.add(resolveInfo.activityInfo.applicationInfo.packageName)) {
                        if ((resolveInfo.activityInfo.applicationInfo.flags & 1) != 0) {
                            TextUtils.equals(context.getPackageName(), resolveInfo.activityInfo.applicationInfo.packageName);
                        }
                    }
                }
            }
            return new a(hashSet.size());
        }

        @Override // defpackage.i9
        public final void j() {
            o(null);
        }

        @Override // defpackage.i9
        public final void l(a aVar) {
            o(aVar);
        }

        public final void o(a aVar) {
            org.chromium.base.b<Callback<a>> bVar = this.h;
            Iterator<Callback<a>> it = bVar.iterator();
            while (true) {
                b.a aVar2 = (b.a) it;
                if (!aVar2.hasNext()) {
                    bVar.clear();
                    return;
                }
                ((Callback) aVar2.next()).onResult(aVar);
            }
        }
    }
}
