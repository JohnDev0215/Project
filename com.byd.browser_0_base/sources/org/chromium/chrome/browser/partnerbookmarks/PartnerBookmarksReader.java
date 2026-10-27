package org.chromium.chrome.browser.partnerbookmarks;

import J.N;
import android.content.Context;
import android.util.Log;
import defpackage.a9;
import defpackage.ao3;
import defpackage.i9;
import defpackage.rw1;
import defpackage.sw1;
import java.util.HashSet;
import java.util.Iterator;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.partnercustomizations.PartnerBrowserCustomizations;

/* JADX INFO: loaded from: classes2.dex */
public final class PartnerBookmarksReader {
    public static final HashSet j = new HashSet();
    public sw1 a;
    public final Context c;
    public int e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public final Object d = new Object();
    public long b = N.MQQiT1PE(this);

    public interface FetchFaviconCallback {
        @CalledByNative
        void onFaviconFetch();

        @CalledByNative
        void onFaviconFetched(int i);
    }

    public interface a {
        void f(String str);

        void o();
    }

    public class b extends i9<Void> {
        public final Object h = new Object();

        public b() {
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x003f  */
        @Override // defpackage.i9
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Void b() {
            /*
                Method dump skipped, instruction units count: 349
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.partnerbookmarks.PartnerBookmarksReader.b.b():java.lang.Object");
        }

        @Override // defpackage.i9
        public final void l(Void r4) {
            synchronized (this.h) {
                PartnerBookmarksReader partnerBookmarksReader = PartnerBookmarksReader.this;
                boolean z = true;
                partnerBookmarksReader.h = true;
                if (partnerBookmarksReader.i) {
                    N.MZy4XMIu(partnerBookmarksReader.b, partnerBookmarksReader);
                }
                synchronized (partnerBookmarksReader.d) {
                    if (partnerBookmarksReader.e != 0 || !partnerBookmarksReader.h || !partnerBookmarksReader.i) {
                        z = false;
                    }
                    if (z) {
                        partnerBookmarksReader.a();
                    }
                }
            }
        }

        public final void o(rw1 rw1Var, HashSet<rw1> hashSet) {
            if (hashSet.contains(rw1Var)) {
                return;
            }
            hashSet.add(rw1Var);
            if (rw1Var.a != 0) {
                try {
                    synchronized (this.h) {
                        PartnerBookmarksReader partnerBookmarksReader = PartnerBookmarksReader.this;
                        String str = rw1Var.d;
                        String str2 = rw1Var.e;
                        boolean z = rw1Var.c;
                        long j = rw1Var.b;
                        byte[] bArr = rw1Var.f;
                        byte[] bArr2 = rw1Var.g;
                        HashSet hashSet2 = PartnerBookmarksReader.j;
                        partnerBookmarksReader.getClass();
                        rw1Var.h = N.M1QiERX9(partnerBookmarksReader.b, partnerBookmarksReader, str, str2, z, j, bArr, bArr2, partnerBookmarksReader.a.c(str), ao3.a(partnerBookmarksReader.c, 16.0f), new org.chromium.chrome.browser.partnerbookmarks.a(partnerBookmarksReader, str));
                    }
                } catch (IllegalArgumentException e) {
                    Log.w("cr_PartnerBMReader", "Error inserting bookmark " + rw1Var.e, e);
                }
                if (rw1Var.h == -1) {
                    Log.e("cr_PartnerBMReader", "Error creating bookmark '" + rw1Var.e + "'.");
                    return;
                }
            }
            if (rw1Var.c) {
                for (rw1 rw1Var2 : rw1Var.j) {
                    if (rw1Var2.i != rw1Var) {
                        Log.w("cr_PartnerBMReader", "Hierarchy error in bookmark '" + rw1Var.e + "'. Skipping.");
                    } else {
                        rw1Var2.b = rw1Var.h;
                        o(rw1Var2, hashSet);
                    }
                }
            }
        }
    }

    public PartnerBookmarksReader(Context context, PartnerBrowserCustomizations partnerBrowserCustomizations) {
        this.c = context;
        if (!partnerBrowserCustomizations.d) {
            partnerBrowserCustomizations.c(context);
        }
        partnerBrowserCustomizations.h(new a9(this, 16, partnerBrowserCustomizations));
    }

    public final void a() {
        synchronized (this.d) {
            if (this.f) {
                return;
            }
            sw1 sw1Var = this.a;
            if (sw1Var != null) {
                sw1Var.a();
            }
            if (this.g) {
                Iterator it = j.iterator();
                while (it.hasNext()) {
                    ((a) it.next()).o();
                }
            }
            N.Mp38r97L(this.b, this);
            this.b = 0L;
            this.f = true;
        }
    }
}
