package com.amazon.mp3.prime.browse;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.mp3.library.fragment.RecommendationsTabFragment;
import com.amazon.mp3.library.fragment.StationsFragment;

/* JADX INFO: loaded from: classes4.dex */
public enum PrimeMusicTab {
    BROWSE_HOME(R.string.dmusic_browse_home_tab),
    RECOMMENDATIONS(R.string.dmusic_recommendations_tab),
    PRIME_STATIONS(R.string.dmusic_library_stations_tab);

    private int titleResId;

    PrimeMusicTab(final int resId) {
        this.titleResId = resId;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.prime.browse.PrimeMusicTab$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$prime$browse$PrimeMusicTab;

        static {
            int[] iArr = new int[PrimeMusicTab.values().length];
            $SwitchMap$com$amazon$mp3$prime$browse$PrimeMusicTab = iArr;
            try {
                iArr[PrimeMusicTab.RECOMMENDATIONS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$browse$PrimeMusicTab[PrimeMusicTab.PRIME_STATIONS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$browse$PrimeMusicTab[PrimeMusicTab.BROWSE_HOME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public Fragment getFragment(final Bundle arguments) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$prime$browse$PrimeMusicTab[ordinal()];
        if (i == 1) {
            return new RecommendationsTabFragment.Builder(arguments).usePagerListView(true).saveFragmentScrollPosition(true).build();
        }
        if (i == 2) {
            return StationsFragment.newInstance(arguments);
        }
        return new BrushFragmentNavigation.Builder("home").createFragment();
    }

    public int getTitleResourceId() {
        return this.titleResId;
    }
}
