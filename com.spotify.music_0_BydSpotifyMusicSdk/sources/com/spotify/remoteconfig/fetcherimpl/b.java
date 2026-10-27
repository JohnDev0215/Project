package com.spotify.remoteconfig.fetcherimpl;

import p.my1;
import p.py0;
import p.v81;

/* JADX INFO: loaded from: classes.dex */
public final class b implements my1 {
    public final boolean a;
    public final int b;
    public final v81 c = kotlin.a.c(new py0() { // from class: com.spotify.remoteconfig.fetcherimpl.RemoteConfigurationClientAndroidProperties$dataProps$2
        {
            super(0);
        }

        @Override // p.py0
        public final Object invoke() {
            this.this$0.getClass();
            return null;
        }
    });

    public b(boolean z, int i) {
        this.a = z;
        this.b = i;
    }

    public final boolean a() {
        b bVar = (b) this.c.getValue();
        return bVar != null ? bVar.a() : this.a;
    }

    public final int b() {
        b bVar = (b) this.c.getValue();
        return bVar != null ? bVar.b() : this.b;
    }
}
