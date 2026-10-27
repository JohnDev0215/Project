package com.spotify.remoteconfig.resolverimpl;

import p.bj;
import p.jx;
import p.my1;
import p.py0;
import p.v81;

/* JADX INFO: loaded from: classes.dex */
public final class a implements my1 {
    public final boolean a;
    public final jx b;
    public final v81 c = kotlin.a.c(new py0() { // from class: com.spotify.remoteconfig.resolverimpl.RemoteConfigSnapshotIdProperties$dataProps$2
        {
            super(0);
        }

        @Override // p.py0
        public final Object invoke() {
            a aVar = this.this$0;
            jx jxVar = aVar.b;
            if (jxVar != null) {
                return (a) jxVar.b(new bj(aVar, 5));
            }
            return null;
        }
    });

    public a(boolean z, jx jxVar) {
        this.a = z;
        this.b = jxVar;
    }

    public final boolean a() {
        a aVar = (a) this.c.getValue();
        return aVar != null ? aVar.a() : this.a;
    }
}
