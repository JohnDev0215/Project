package com.amazon.mp3.providers;

import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.nowplaying.StationTrackProvider;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.prime.station.StationStorageUtil;
import com.amazon.mp3.prime.station.StationsFactory;
import com.amazon.mp3.recentlyplayed.RecentStationsUpgradeHandler;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.mp3.recentlyplayed.RecentsPlaybackHelper;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.ContentEnabilityDialogUtil;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.station.StationItem;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: RecentsPlaybackProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001a\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J0\u0010\u0011\u001a\u00020\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\b2\b\u0010\u0015\u001a\u0004\u0018\u00010\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002H\u0016J\u0018\u0010\u0016\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u00022\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cJ\"\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u00022\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\bJ\u0012\u0010\u001e\u001a\u00020\r2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0002H\u0002R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0002X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/amazon/mp3/providers/RecentsPlaybackProvider;", "Lcom/amazon/mp3/util/ContentAccessUtil$CatalogActionListener;", "Lcom/amazon/mp3/recentlyplayed/RecentlyPlayedItem;", "Lcom/amazon/mp3/recentlyplayed/RecentStationsUpgradeHandler;", "mActivity", "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", "TAG", "", "kotlin.jvm.PlatformType", "TAG_RECENTLY_PLAYED_DISABLED_DIALOG", "mRecentlyPlayedItem", "continueCatalogAction", "", "item", "canContinue", "", "doStationsUpgrade", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "seedId", "providerState", "showContentEnabilityDialog", "reason", "Lcom/amazon/mp3/prime/ContentUnavailableReason;", "startPlayback", "recentlyPlayedItem", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "blockRef", "startPlayingItem", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RecentsPlaybackProvider implements ContentAccessUtil.CatalogActionListener<RecentlyPlayedItem>, RecentStationsUpgradeHandler {
    private final String TAG;
    private final String TAG_RECENTLY_PLAYED_DISABLED_DIALOG;
    private final FragmentActivity mActivity;
    private RecentlyPlayedItem mRecentlyPlayedItem;

    public RecentsPlaybackProvider(FragmentActivity mActivity) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        this.mActivity = mActivity;
        this.TAG_RECENTLY_PLAYED_DISABLED_DIALOG = "TAG_RECENTLY_PLAYED_DISABLED_DIALOG";
        this.TAG = RecentsPlaybackProvider.class.getSimpleName();
    }

    public final void startPlayback(RecentlyPlayedItem recentlyPlayedItem, PageType pageType, String blockRef) {
        Intrinsics.checkNotNullParameter(recentlyPlayedItem, "recentlyPlayedItem");
        this.mRecentlyPlayedItem = recentlyPlayedItem;
        if (MediaProvider.Station.isStationFromAnything(recentlyPlayedItem.getContentUri())) {
            StationUtils.sendStartSFAUiClickMetrics(MediaProvider.Station.getStationFromAnythingSeedId(recentlyPlayedItem.getContentUri()), EntityIdType.SFA_STATION_SEED_ID, pageType);
        } else {
            MetricsLogger.sendUiClickMetricForRecentlyPlayedItem(recentlyPlayedItem, pageType, blockRef);
        }
        ContentAccessUtil.checkContinueActionWithoutEnabilityCheck((Context) this.mActivity, recentlyPlayedItem, ContentAccessUtil.ContentAccessOperation.STREAM, (ContentAccessUtil.CatalogActionListener) this);
    }

    public final void startPlayback(RecentlyPlayedItem recentlyPlayedItem, PageType pageType) {
        Intrinsics.checkNotNullParameter(recentlyPlayedItem, "recentlyPlayedItem");
        startPlayback(recentlyPlayedItem, pageType, null);
    }

    @Override // com.amazon.mp3.util.ContentAccessUtil.CatalogActionListener
    public void continueCatalogAction(RecentlyPlayedItem item, boolean canContinue) {
        if (canContinue) {
            startPlayingItem(this.mRecentlyPlayedItem);
        }
    }

    @Override // com.amazon.mp3.recentlyplayed.RecentStationsUpgradeHandler
    public void doStationsUpgrade(final Intent intent, final String seedId, String providerState, RecentlyPlayedItem item) {
        Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.providers.RecentsPlaybackProvider$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                RecentsPlaybackProvider.m1607doStationsUpgrade$lambda0(this.f$0, seedId, (Subscriber) obj);
            }
        }).timeout(3L, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe((Subscriber) new Subscriber<StationItem>() { // from class: com.amazon.mp3.providers.RecentsPlaybackProvider.doStationsUpgrade.2
            @Override // rx.Observer
            public void onCompleted() {
                RecentsPlaybackProvider.this.mActivity.startActivity(intent);
                RecentsPlaybackProvider.this.mActivity.overridePendingTransition(R.anim.slide_in_up, R.anim.fade_out);
            }

            @Override // rx.Observer
            public void onError(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                BauhausToastUtils.showTextToast(RecentsPlaybackProvider.this.mActivity, R.string.dmusic_no_connection_title, 0);
            }

            @Override // rx.Observer
            public void onNext(StationItem stationItem) {
                Intrinsics.checkNotNullParameter(stationItem, "stationItem");
                try {
                    StationTrackProvider.stationItemToJson(new JSONObject(), stationItem, MediaProvider.Station.getContentUri(stationItem.getKey()));
                } catch (JSONException unused) {
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: doStationsUpgrade$lambda-0, reason: not valid java name */
    public static final void m1607doStationsUpgrade$lambda0(RecentsPlaybackProvider this$0, String str, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            StationStorageUtil.doUpgrade(this$0.mActivity);
            subscriber.onNext(StationsFactory.getInstance(this$0.mActivity).getStationManager().getStationItemBySeedId(str));
            subscriber.onCompleted();
        } catch (Exception e) {
            subscriber.onError(e);
        }
    }

    private final void startPlayingItem(RecentlyPlayedItem recentlyPlayedItem) {
        Unit unit = null;
        ContentUnavailableReason contentUnavailableReason = recentlyPlayedItem == null ? null : recentlyPlayedItem.getContentUnavailableReason();
        if (contentUnavailableReason != null) {
            showContentEnabilityDialog(recentlyPlayedItem, contentUnavailableReason);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            RecentsPlaybackHelper.startRecentlyPlayed(recentlyPlayedItem, this.mActivity, this);
        }
    }

    private final void showContentEnabilityDialog(RecentlyPlayedItem item, ContentUnavailableReason reason) {
        FragmentActivity fragmentActivity = this.mActivity;
        DialogFragment dialogForDisabledReason = ContentEnabilityDialogUtil.getDialogForDisabledReason(fragmentActivity, reason, null, item.getLibraryItemName(fragmentActivity.getResources()), item, ContentAccessUtil.ContentAccessOperation.STREAM);
        if (dialogForDisabledReason != null) {
            FragmentManager supportFragmentManager = this.mActivity.getSupportFragmentManager();
            Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "mActivity.getSupportFragmentManager()");
            dialogForDisabledReason.show(supportFragmentManager, this.TAG_RECENTLY_PLAYED_DISABLED_DIALOG);
        }
    }
}
