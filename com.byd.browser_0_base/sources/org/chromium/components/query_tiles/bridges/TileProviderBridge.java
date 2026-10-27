package org.chromium.components.query_tiles.bridges;

import J.N;
import defpackage.ja3;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.query_tiles.QueryTile;

/* JADX INFO: loaded from: classes3.dex */
public class TileProviderBridge implements ja3 {
    public long a;

    public TileProviderBridge(long j) {
        this.a = j;
    }

    @CalledByNative
    public static TileProviderBridge create(long j) {
        return new TileProviderBridge(j);
    }

    @Override // defpackage.ja3
    public final void a(String str) {
        long j = this.a;
        if (j == 0) {
            return;
        }
        N.MgiexKaR(j, str);
    }

    @Override // defpackage.ja3
    public final void b(String str, Callback<List<QueryTile>> callback) {
        long j = this.a;
        if (j == 0) {
            return;
        }
        N.Mq1q5_mC(j, this, str, callback);
    }

    @CalledByNative
    public final void clearNativePtr() {
        this.a = 0L;
    }
}
