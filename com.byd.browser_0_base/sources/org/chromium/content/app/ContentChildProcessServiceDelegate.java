package org.chromium.content.app;

import J.N;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import android.view.Surface;
import defpackage.bo;
import defpackage.bw0;
import defpackage.u41;
import defpackage.xz;
import java.util.List;
import org.chromium.base.BuildInfo;
import org.chromium.base.JNIUtils;
import org.chromium.base.UnguessableToken;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.library_loader.a;
import org.chromium.content.common.SurfaceWrapper;

/* JADX INFO: loaded from: classes3.dex */
public class ContentChildProcessServiceDelegate implements bo {
    public bw0 a;
    public int b;
    public long c;
    public SparseArray<String> d;

    public ContentChildProcessServiceDelegate() {
        if (BuildInfo.b()) {
            return;
        }
        Thread.setDefaultUncaughtExceptionHandler(new u41());
    }

    public final void a(Context context) {
        boolean z;
        a aVar = a.l;
        synchronized (aVar.h) {
            z = aVar.j;
        }
        if (z) {
            synchronized (aVar.h) {
                aVar.c();
            }
            N.M0zXFFiu(this);
            return;
        }
        JNIUtils.a = Boolean.TRUE;
        aVar.g.c();
        synchronized (aVar.h) {
            if (aVar.c != 0 && context != xz.a) {
                throw new IllegalStateException("Attempt to load again from alternate context.");
            }
            aVar.e(context.getApplicationInfo(), false);
        }
        aVar.f();
        synchronized (aVar.h) {
            aVar.c();
        }
        N.M0zXFFiu(this);
    }

    public final void b(List list, Bundle bundle) {
        bw0 c0032a = null;
        if (list != null && !list.isEmpty()) {
            IBinder iBinder = (IBinder) list.get(0);
            int i = bw0.a.e;
            if (iBinder != null) {
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("org.chromium.content.common.IGpuProcessCallback");
                c0032a = (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof bw0)) ? new bw0.a.C0032a(iBinder) : (bw0) iInterfaceQueryLocalInterface;
            }
        }
        this.a = c0032a;
        this.b = bundle.getInt("com.google.android.apps.chrome.extra.cpu_count");
        this.c = bundle.getLong("com.google.android.apps.chrome.extra.cpu_features");
        a.l.g.f(bundle);
    }

    @CalledByNative
    public final void forwardSurfaceForSurfaceRequest(UnguessableToken unguessableToken, Surface surface) {
        bw0 bw0Var = this.a;
        try {
            if (bw0Var == null) {
                Log.e("cr_ContentCPSDelegate", "No callback interface has been provided.");
            } else {
                bw0Var.d(unguessableToken, surface);
            }
        } catch (RemoteException e) {
            Log.e("cr_ContentCPSDelegate", "Unable to call forwardSurfaceForSurfaceRequest: %s", e);
        } finally {
            surface.release();
        }
    }

    @CalledByNative
    public final SurfaceWrapper getViewSurface(int i) {
        bw0 bw0Var = this.a;
        if (bw0Var == null) {
            Log.e("cr_ContentCPSDelegate", "No callback interface has been provided.");
            return null;
        }
        try {
            return bw0Var.t(i);
        } catch (RemoteException e) {
            Log.e("cr_ContentCPSDelegate", "Unable to call getViewSurface: %s", e);
            return null;
        }
    }

    @CalledByNative
    public final void setFileDescriptorsIdsToKeys(int[] iArr, String[] strArr) {
        this.d = new SparseArray<>();
        for (int i = 0; i < iArr.length; i++) {
            this.d.put(iArr[i], strArr[i]);
        }
    }
}
