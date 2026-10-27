package com.vivid.spotify.nowplaying;

import android.text.TextUtils;
import com.vivid.spotify.MainActivity;
import com.vivid.spotify.rootui.s;
import com.vivid.spotify.t;

/* JADX INFO: compiled from: NowPlayingRouteFragment.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends s {
    public static g W1() {
        return new g();
    }

    @Override // com.vivid.spotify.rootui.s
    protected void Q1() {
        V1();
    }

    public void U1() {
        N1().v(t.nowPlayingFragment, false);
    }

    public void V1() {
        N1().n(t.action_global_nowPlayingFragment);
    }

    public void X1() {
        androidx.navigation.e eVarF = N1().f();
        if (eVarF != null) {
            if (!TextUtils.equals("now_playing", eVarF.e().l())) {
                N1().s();
                return;
            }
            androidx.fragment.app.d dVarJ = j();
            if (dVarJ instanceof MainActivity) {
                ((MainActivity) dVarJ).exitNowPlayingPage(null);
            }
        }
    }
}
