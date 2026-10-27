package p;

import android.os.Bundle;
import com.spotify.common.uri.SpotifyUri$Kind;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class w40 {
    public final st1 a;
    public final m b;
    public final pa0 c;
    public boolean d;
    public boolean e;
    public String f;
    public vg1 g;

    public w40(st1 st1Var, m mVar) {
        this.a = st1Var;
        this.b = mVar;
        pa0 pa0Var = new pa0();
        this.c = pa0Var;
        this.d = true;
        DisposableHelper.d(pa0Var.a.c, st1Var.e().map(qo.m).distinctUntilChanged().subscribe(new du(3, this)));
    }

    public final ArrayList a(String str) {
        if (!((com.spotify.remoteconfig.a) this.b.a.get()).c()) {
            return new ArrayList();
        }
        if2 if2VarG = vr2.g(str);
        SpotifyUri$Kind spotifyUri$Kind = if2VarG != null ? if2VarG.e : null;
        int i = spotifyUri$Kind == null ? -1 : v40.a[spotifyUri$Kind.ordinal()];
        if (i != 1 && i != 2 && i != 3 && i != 4) {
            return new ArrayList();
        }
        String[] strArr = new String[2];
        strArr[0] = (this.d || !v03.b(this.f, str)) ? "play" : "pause";
        strArr[1] = this.e ? "disable-shuffle" : "shuffle";
        return new ArrayList(new fb(strArr, true));
    }

    public final void b(String str) {
        vg1 vg1Var = this.g;
        if (vg1Var != null) {
            Bundle bundle = new Bundle();
            bundle.putString("androidx.media.utils.extras.KEY_CUSTOM_BROWSER_ACTION_RESULT_REFRESH_ITEM", str);
            if (vg1Var.c || vg1Var.d) {
                throw new IllegalStateException("sendProgressUpdate() called when either sendResult() or sendError() had already been called for: " + vg1Var.a);
            }
            if (bundle.containsKey("android.media.browse.extra.DOWNLOAD_PROGRESS")) {
                float f = bundle.getFloat("android.media.browse.extra.DOWNLOAD_PROGRESS");
                if (f < -1.0E-5f || f > 1.00001f) {
                    throw new IllegalArgumentException("The value of the EXTRA_DOWNLOAD_PROGRESS field must be a float number within [0.0, 1.0]");
                }
            }
            vg1Var.d(bundle);
        }
    }
}
