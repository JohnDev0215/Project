package com.amazon.mp3.activity.navigation;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.casting.CastingFragment;
import com.amazon.mp3.catalog.fragment.PlayQueueFragment;
import com.amazon.mp3.playback.fragment.NowPlayingFragment;

/* JADX INFO: loaded from: classes3.dex */
final class NowPlayingFragmentLoader extends FragmentLoader {
    @Override // com.amazon.mp3.activity.navigation.FragmentLoader
    public boolean navigateFromMenu(final int menuId) {
        return false;
    }

    public NowPlayingFragmentLoader(FragmentActivity fragmentActivity, FragmentController fragmentController) {
        super(fragmentActivity, fragmentController);
    }

    @Override // com.amazon.mp3.activity.navigation.FragmentLoader
    public boolean navigateFromIntent(final Intent intent) {
        Bundle extras = intent.getExtras();
        String string = extras.getString("com.amazon.mp3.fragment.extra");
        if (NowPlayingFragment.class.getSimpleName().equals(string)) {
            goToNowPlayingFragment(extras);
            return true;
        }
        if (CastingFragment.class.getSimpleName().equals(string)) {
            goToCastingFragment(extras);
            return true;
        }
        if (!PlayQueueFragment.class.getSimpleName().equals(string)) {
            return false;
        }
        goToPlayQueueFragment();
        return true;
    }

    private void goToNowPlayingFragment(final Bundle extras) {
        this.mFragmentController.changeScreenFragment(NowPlayingFragment.newInstance(extras), false, false);
    }

    private void goToCastingFragment(final Bundle extras) {
        CastingFragment castingFragment = new CastingFragment();
        castingFragment.setArguments(extras);
        this.mFragmentController.changeScreenFragment(castingFragment, true, false, false, R.anim.zoom_in, R.anim.fragment_fade_out, R.anim.zoom_in, R.anim.slide_out_down);
    }

    private void goToPlayQueueFragment() {
        this.mFragmentController.changeScreenFragment(new PlayQueueFragment(), true, false, false, R.anim.zoom_in, R.anim.fragment_fade_out, R.anim.zoom_in, R.anim.slide_out_down);
    }
}
