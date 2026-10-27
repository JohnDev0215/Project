package p;

import android.os.SystemClock;
import android.support.v4.media.session.PlaybackStateCompat;
import com.google.common.base.Optional;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class t0 implements ph1 {
    @Override // p.ph1
    public final void a(bi1 bi1Var) {
        bi1Var.e(new PlaybackStateCompat(8, -1L, 0L, 0.0f, vr2.d(Optional.a()), 0, null, SystemClock.elapsedRealtime(), new ArrayList(), -1L, null));
    }
}
