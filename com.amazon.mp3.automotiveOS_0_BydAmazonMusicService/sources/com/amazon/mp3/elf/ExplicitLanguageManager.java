package com.amazon.mp3.elf;

import android.content.Context;
import android.os.AsyncTask;
import android.preference.Preference;
import android.preference.SwitchPreference;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.casting.CastingViewsFactory;
import com.amazon.mp3.explore.providers.ExploreExplicitFilterProvider;
import com.amazon.mp3.playback.PlayStateMutationReason;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.service.ClearCacheService;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.Log;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.casting.session.CastingSessionManager;
import java.util.concurrent.Callable;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class ExplicitLanguageManager {
    private static final String TAG = "ExplicitLanguageManager";
    private Subscription clearPlaybackSubscription;
    private final Context mContext;

    public void setPreferenceTitleForAutomation(final Preference preference) {
    }

    public ExplicitLanguageManager(final Context context) {
        this.mContext = context;
    }

    public static boolean isExplicitFilterEnabled() {
        return AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled();
    }

    public void toggleExplicitFilterSetting() {
        toggleExplicitFilterSetting(null);
    }

    public void toggleExplicitFilterSetting(final SwitchPreference switchPreference) {
        clearCacheIfNeeded();
        clearPlaybackIfNeeded();
        ExplicitLanguageFilterMetrics.emitToggleClickMetric(switchPreference != null);
        setExplicitFilterSetting(true ^ isExplicitFilterEnabled(), switchPreference);
    }

    public void setExplicitFilterSetting(final boolean newExplicitSetting, final SwitchPreference switchPreference) {
        new ExplicitFilterUpdateTask(newExplicitSetting, switchPreference).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void setPreferenceSubtitle(final Preference preference) {
        if (preference == null || this.mContext == null) {
            return;
        }
        if (CastingUtil.isCastingToAlexa()) {
            preference.setEnabled(false);
            Context context = this.mContext;
            preference.setSummary(context.getString(R.string.casting_unavailable_setting_text, CastingUtil.getCastingDeviceName(context)));
        } else if (isExplicitFilterEnabled()) {
            preference.setSummary(this.mContext.getString(R.string.dmusic_explicit_filter_content_unavailable_title));
        } else {
            preference.setSummary(this.mContext.getString(R.string.dmusic_explicit_filter_block_message));
        }
    }

    private void clearCacheIfNeeded() {
        if (SettingsUtil.hasUsedExplicitLanguageFilter(this.mContext)) {
            return;
        }
        Log.info(TAG, "Clearing cache the first time the user uses ELF");
        SettingsUtil.setHasUsedExplicitLanguageFilter(this.mContext);
        ClearCacheService.startClearCache(this.mContext, -108809);
    }

    private void clearPlaybackIfNeeded() {
        Subscription subscription = this.clearPlaybackSubscription;
        if (subscription != null && !subscription.isUnsubscribed()) {
            this.clearPlaybackSubscription.unsubscribe();
        }
        this.clearPlaybackSubscription = Observable.fromCallable(new Callable() { // from class: com.amazon.mp3.elf.ExplicitLanguageManager$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$clearPlaybackIfNeeded$0();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).onErrorResumeNext(Observable.just(Boolean.FALSE)).subscribe(new Action1() { // from class: com.amazon.mp3.elf.ExplicitLanguageManager$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$clearPlaybackIfNeeded$1((Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$clearPlaybackIfNeeded$0() throws Exception {
        return Boolean.valueOf(NowPlayingUtil.isStationPlaying() || NowPlayingUtil.isExplicitTrackPlaying() || NowPlayingUtil.isVideoPresentInPlayQueue(this.mContext));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$clearPlaybackIfNeeded$1(Boolean bool) {
        if (bool.booleanValue()) {
            Log.info(TAG, "Clearing playqueue due to ELF toggle");
            NowPlayingUtil.clearAndFinishNowPlaying(this.mContext, PlayStateMutationReason.ELF_TOGGLE);
        }
    }

    private class ExplicitFilterUpdateTask extends AsyncTask<Void, Void, Boolean> {
        private boolean mNewExplicitSetting;
        private SwitchPreference mSwitchPreference;

        public ExplicitFilterUpdateTask(final boolean explicitFilterSetting, final SwitchPreference switchPreference) {
            this.mNewExplicitSetting = explicitFilterSetting;
            this.mSwitchPreference = switchPreference;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(final Void... params) {
            return Boolean.valueOf(AccountManagerSingleton.get().updateExplicitLanguagePreference(this.mNewExplicitSetting));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(final Boolean success) {
            updatePreferenceSetting();
            displayToastOnTaskComplete(success.booleanValue());
            updateCastingState(success.booleanValue());
            if (success.booleanValue()) {
                ExploreExplicitFilterProvider.refreshTemplateFragmentIfPresent(ExplicitLanguageManager.this.mContext);
            }
        }

        private void updatePreferenceSetting() {
            SwitchPreference switchPreference = this.mSwitchPreference;
            if (switchPreference == null) {
                return;
            }
            switchPreference.setChecked(ExplicitLanguageManager.isExplicitFilterEnabled());
            ExplicitLanguageManager.this.setPreferenceTitleForAutomation(this.mSwitchPreference);
            ExplicitLanguageManager.this.setPreferenceSubtitle(this.mSwitchPreference);
        }

        private void displayToastOnTaskComplete(final boolean success) {
            String string;
            if (success) {
                string = ExplicitLanguageManager.this.mContext.getString(ExplicitLanguageManager.isExplicitFilterEnabled() ? R.string.dmusic_explicit_filter_blocked_toast : R.string.dmusic_explicit_filter_unblocked_toast);
            } else {
                string = "Update preferences failed";
            }
            BauhausToastUtils.showDefaultToast(ExplicitLanguageManager.this.mContext, string, 0);
        }

        private void updateCastingState(final boolean success) {
            if (success) {
                ExplicitLanguageManager.this.updateCastingSession();
            }
        }
    }

    public void updateCastingSession() {
        if (CastingUtil.isCasting()) {
            CastingSessionManager.getInstance().updateAllowedParentalControls(CastingViewsFactory.createAllowedParentalControls());
        }
    }

    public static boolean isEnabled() {
        return new ELFFeatureGating().isEnabled();
    }
}
