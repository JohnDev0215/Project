package com.amazon.mp3.activity.navigation;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.store.activity.HTMLStoreActivityFactory;
import com.amazon.mp3.store.html5.activity.StoreFragment;

/* JADX INFO: loaded from: classes3.dex */
final class StoreFragmentLoader extends FragmentLoader {
    public StoreFragmentLoader(FragmentActivity fragmentActivity, FragmentController fragmentController) {
        super(fragmentActivity, fragmentController);
    }

    @Override // com.amazon.mp3.activity.navigation.FragmentLoader
    public boolean navigateFromMenu(final int menuId) {
        switch (menuId) {
            case R.id.store_best_sellers_tab /* 2131430260 */:
                onBestSellersSelected();
                break;
            case R.id.store_browse_genres_tab /* 2131430262 */:
                onBrowseGenresSelected();
                break;
            case R.id.store_music_tab /* 2131430265 */:
                onMusicStoreSelected();
                break;
            case R.id.store_new_releases_tab /* 2131430267 */:
                onNewReleasesSelected();
                break;
            case R.id.store_recommended_tab /* 2131430269 */:
                onRecommendedForYouSelected();
                break;
        }
        return true;
    }

    @Override // com.amazon.mp3.activity.navigation.FragmentLoader
    public boolean navigateFromIntent(final Intent intent) {
        if (!StoreFragment.class.getSimpleName().equals(intent.getExtras().getString("com.amazon.mp3.fragment.extra"))) {
            return false;
        }
        goToStore(intent);
        return true;
    }

    private void goToStore(final Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            throw new IllegalStateException("Cannot go to store with empty intent extra");
        }
        Bundle bundle = extras.getBundle("com.amazon.mp3.store.extra.STORE_ARGUMENTS_BUNDLE");
        if (bundle == null) {
            bundle = HTMLStoreActivityFactory.bundleForDefaultStorePage();
        }
        launchStore(bundle, true);
    }

    private void onMusicStoreSelected() {
        Bundle bundle = new Bundle();
        bundle.putString("com.amazon.mp3.store.TITLE", this.mFragmentActivity.getString(R.string.dmusic_store_music_tab));
        launchStore(bundle);
    }

    private void onRecommendedForYouSelected() {
        Bundle bundleBundleForRecommendedForYou = HTMLStoreActivityFactory.bundleForRecommendedForYou();
        bundleBundleForRecommendedForYou.putString("com.amazon.mp3.store.TITLE", this.mFragmentActivity.getString(R.string.dmusic_store_recommended_tab));
        launchStore(bundleBundleForRecommendedForYou);
    }

    private void onBestSellersSelected() {
        Bundle bundleBundleForBestsellers = HTMLStoreActivityFactory.bundleForBestsellers();
        bundleBundleForBestsellers.putString("com.amazon.mp3.store.TITLE", this.mFragmentActivity.getString(R.string.dmusic_store_best_sellers_tab));
        launchStore(bundleBundleForBestsellers);
    }

    private void onNewReleasesSelected() {
        Bundle bundleBundleForNewReleases = HTMLStoreActivityFactory.bundleForNewReleases();
        bundleBundleForNewReleases.putString("com.amazon.mp3.store.TITLE", this.mFragmentActivity.getString(R.string.dmusic_store_new_releases_tab));
        launchStore(bundleBundleForNewReleases);
    }

    private void onBrowseGenresSelected() {
        Bundle bundleBundleForGenres = HTMLStoreActivityFactory.bundleForGenres();
        bundleBundleForGenres.putString("com.amazon.mp3.store.TITLE", this.mFragmentActivity.getString(R.string.dmusic_store_browse_genres_tab));
        launchStore(bundleBundleForGenres);
    }

    private void launchStore(final Bundle bundle) {
        launchStore(bundle, false);
    }

    private void launchStore(Bundle bundle, boolean addToBackStack) {
        bundle.putBoolean("com.amazon.mp3.extra.EXTRA_CLEAR_HISTORY", true);
        this.mFragmentController.changeScreenFragment(StoreFragment.newInstance(bundle), addToBackStack, true);
    }
}
