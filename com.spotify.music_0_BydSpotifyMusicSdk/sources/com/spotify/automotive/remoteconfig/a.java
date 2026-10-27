package com.spotify.automotive.remoteconfig;

import p.bj;
import p.jx;
import p.my1;
import p.py0;
import p.v81;

/* JADX INFO: loaded from: classes.dex */
public final class a implements my1 {
    public final boolean a;
    public final boolean b;
    public final jx c;
    public final v81 d = kotlin.a.c(new py0() { // from class: com.spotify.automotive.remoteconfig.AutomotiveConnectionTypeFlagsProperties$dataProps$2
        {
            super(0);
        }

        @Override // p.py0
        public final Object invoke() {
            a aVar = this.this$0;
            jx jxVar = aVar.c;
            if (jxVar != null) {
                return (a) jxVar.b(new bj(aVar, 0));
            }
            return null;
        }
    });

    public a(boolean z, boolean z2, jx jxVar) {
        this.a = z;
        this.b = z2;
        this.c = jxVar;
    }

    public final boolean a() {
        a aVar = (a) this.d.getValue();
        return aVar != null ? aVar.a() : this.a;
    }

    public final boolean b() {
        a aVar = (a) this.d.getValue();
        return aVar != null ? aVar.b() : this.b;
    }
}
