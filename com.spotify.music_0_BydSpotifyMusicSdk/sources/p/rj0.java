package p;

import android.os.SystemClock;
import com.google.common.base.Optional;

/* JADX INFO: loaded from: classes.dex */
public final class rj0 implements io.reactivex.rxjava3.functions.p {
    public final /* synthetic */ com.spotify.eventsender.eventsender.observability.a c;
    public final /* synthetic */ int e;

    public rj0(com.spotify.eventsender.eventsender.observability.a aVar, int i) {
        this.c = aVar;
        this.e = i;
    }

    @Override // io.reactivex.rxjava3.functions.p
    public final boolean test(Object obj) {
        if (((Optional) obj).d()) {
            com.spotify.eventsender.eventsender.observability.a aVar = this.c;
            ((u11) aVar.d).getClass();
            if (SystemClock.elapsedRealtime() - aVar.i >= this.e * 1000) {
                return true;
            }
        }
        return false;
    }
}
