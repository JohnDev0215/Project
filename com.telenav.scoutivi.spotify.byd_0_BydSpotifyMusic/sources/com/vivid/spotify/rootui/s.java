package com.vivid.spotify.rootui;

import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import d4.p1;

/* JADX INFO: compiled from: MainRouteFragment.java */
/* JADX INFO: loaded from: classes.dex */
public class s extends Fragment {
    public static s O1(int i7) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", i7);
        s sVar = new s();
        sVar.C1(bundle);
        return sVar;
    }

    public static s P1(MediaBrowserCompat.MediaItem mediaItem) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("media_item", mediaItem);
        s sVar = new s();
        sVar.C1(bundle);
        return sVar;
    }

    private void R1(MediaBrowserCompat.MediaItem mediaItem) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("media_item", mediaItem);
        N1().o(com.vivid.spotify.t.action_to_rootGridFragment, bundle);
    }

    private void S1(MediaBrowserCompat.MediaItem mediaItem) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("media_item", mediaItem);
        N1().o(com.vivid.spotify.t.action_global_categoryFragment, bundle);
    }

    private void T1() {
        N1().n(com.vivid.spotify.t.action_global_searchFragment);
    }

    protected NavController N1() {
        return ((NavHostFragment) r().g0(com.vivid.spotify.t.nav_host_fragment)).Q1();
    }

    protected void Q1() {
        Bundle bundleC;
        if (q() != null) {
            if (q().getInt("type") == 1) {
                T1();
                return;
            }
            MediaBrowserCompat.MediaItem mediaItem = (MediaBrowserCompat.MediaItem) q().getParcelable("media_item");
            if (mediaItem == null || (bundleC = mediaItem.h().c()) == null) {
                return;
            }
            if (bundleC.getInt("android.media.browse.CONTENT_STYLE_BROWSABLE_HINT", -1) != 3) {
                R1(mediaItem);
            } else {
                S1(mediaItem);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View y0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        p1 p1VarD0 = p1.d0(layoutInflater, viewGroup, false);
        if (bundle == null) {
            Q1();
        }
        return p1VarD0.G();
    }
}
