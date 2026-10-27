package org.chromium.components.paintpreview.player;

import J.N;
import android.graphics.Bitmap;
import android.graphics.Rect;
import defpackage.pk1;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.Callback;
import org.chromium.base.SysUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.UnguessableToken;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.paintpreview.player.a;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes3.dex */
public class PlayerCompositorDelegateImpl implements a {
    public final a.InterfaceC0136a a;
    public long b;
    public final ArrayList c = new ArrayList();

    public PlayerCompositorDelegateImpl(pk1 pk1Var, long j, GURL gurl, String str, boolean z, a.InterfaceC0136a interfaceC0136a, Callback<Integer> callback) {
        this.a = interfaceC0136a;
        if (pk1Var == null || pk1Var.a() == 0) {
            return;
        }
        TraceEvent.q("PlayerCompositorDelegateImplJni.initialize()", null);
        this.b = N.MP_1CaX6(this, pk1Var.a(), j, gurl.j(), str, z, callback, SysUtils.amountOfPhysicalMemoryKB() < 2048);
        TraceEvent.C("PlayerCompositorDelegateImplJni.initialize()");
    }

    public final int a(UnguessableToken unguessableToken, Rect rect, float f, Callback<Bitmap> callback, Runnable runnable) {
        long j = this.b;
        if (j == 0) {
            return -1;
        }
        return N.MiIDqW7F(j, unguessableToken, callback, runnable, f, rect.left, rect.top, rect.width(), rect.height());
    }

    @CalledByNative
    public void onCompositorReady(UnguessableToken unguessableToken, UnguessableToken[] unguessableTokenArr, int[] iArr, int[] iArr2, int[] iArr3, UnguessableToken[] unguessableTokenArr2, int[] iArr4, float f, long j) {
        this.a.g(unguessableToken, unguessableTokenArr, iArr, iArr2, iArr3, unguessableTokenArr2, iArr4, f, j);
    }

    @CalledByNative
    public void onModerateMemoryPressure() {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
    }
}
