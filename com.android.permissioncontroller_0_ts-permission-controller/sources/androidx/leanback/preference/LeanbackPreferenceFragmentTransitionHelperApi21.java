package androidx.leanback.preference;

import android.app.Fragment;
import android.transition.Transition;
import androidx.core.view.GravityCompat;
import androidx.leanback.transition.FadeAndShortSlide;

/* JADX INFO: loaded from: classes3.dex */
public class LeanbackPreferenceFragmentTransitionHelperApi21 {
    public static void addTransitions(Fragment f) {
        Transition transitionStartEdge = new FadeAndShortSlide(GravityCompat.START);
        Transition transitionEndEdge = new FadeAndShortSlide(GravityCompat.END);
        f.setEnterTransition(transitionEndEdge);
        f.setExitTransition(transitionStartEdge);
        f.setReenterTransition(transitionStartEdge);
        f.setReturnTransition(transitionEndEdge);
    }

    static void addTransitions(androidx.fragment.app.Fragment f) {
        Transition transitionStartEdge = new FadeAndShortSlide(GravityCompat.START);
        Transition transitionEndEdge = new FadeAndShortSlide(GravityCompat.END);
        f.setEnterTransition(transitionEndEdge);
        f.setExitTransition(transitionStartEdge);
        f.setReenterTransition(transitionStartEdge);
        f.setReturnTransition(transitionEndEdge);
    }

    private LeanbackPreferenceFragmentTransitionHelperApi21() {
    }
}
