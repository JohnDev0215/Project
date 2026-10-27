package org.chromium.components.download;

import J.N;
import android.os.Handler;
import defpackage.C0173a;
import defpackage.es3;
import defpackage.ve3;
import java.util.Iterator;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.components.download.a;

/* JADX INFO: loaded from: classes2.dex */
public class NetworkStatusListenerAndroid implements a.InterfaceC0130a {
    public static a b;
    public long a;

    public static class a implements a.InterfaceC0130a {
        public final Handler a;
        public org.chromium.components.download.a b;
        public boolean c;
        public int d = 0;
        public final b<a.InterfaceC0130a> e = new b<>();

        public a() {
            Object obj = ThreadUtils.a;
            Handler handler = new Handler(C0173a.h("NetworkStatusListener").getLooper());
            this.a = handler;
            handler.post(new ve3(13, this));
        }

        @Override // org.chromium.components.download.a.InterfaceC0130a
        public final void d(int i) {
            Object obj = ThreadUtils.a;
            this.d = i;
            Iterator<a.InterfaceC0130a> it = this.e.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    return;
                } else {
                    ((a.InterfaceC0130a) aVar.next()).d(i);
                }
            }
        }

        @Override // org.chromium.components.download.a.InterfaceC0130a
        public final void e(int i) {
            Object obj = ThreadUtils.a;
            if (this.c) {
                return;
            }
            this.d = i;
            this.c = true;
            Iterator<a.InterfaceC0130a> it = this.e.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    return;
                } else {
                    ((a.InterfaceC0130a) aVar.next()).e(i);
                }
            }
        }
    }

    public NetworkStatusListenerAndroid(long j) {
        Object obj = ThreadUtils.a;
        this.a = j;
        a aVar = b;
        if (aVar == null) {
            aVar = new a();
            b = aVar;
        }
        aVar.e.a(this);
        if (aVar.c) {
            e(aVar.d);
        }
    }

    @CalledByNative
    public static NetworkStatusListenerAndroid create(long j) {
        Object obj = ThreadUtils.a;
        return new NetworkStatusListenerAndroid(j);
    }

    @CalledByNative
    public final void clearNativePtr() {
        Object obj = ThreadUtils.a;
        a aVar = b;
        if (aVar == null) {
            aVar = new a();
            b = aVar;
        }
        aVar.a.post(new es3(8, aVar));
        aVar.e.c(this);
        this.a = 0L;
    }

    @Override // org.chromium.components.download.a.InterfaceC0130a
    public final void d(int i) {
        Object obj = ThreadUtils.a;
        long j = this.a;
        if (j != 0) {
            N.M9CWqWuv(j, this, i);
        }
    }

    @Override // org.chromium.components.download.a.InterfaceC0130a
    public final void e(int i) {
        Object obj = ThreadUtils.a;
        long j = this.a;
        if (j != 0) {
            N.MtmFml6u(j, this, i);
        }
    }

    @CalledByNative
    public int getCurrentConnectionType() {
        Object obj = ThreadUtils.a;
        a aVar = b;
        if (aVar == null) {
            aVar = new a();
            b = aVar;
        }
        return aVar.d;
    }
}
