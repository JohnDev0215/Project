package com.amazon.mp3.feed;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.actionbar.ActionBarProvider;
import com.amazon.mp3.actionbar.view.ActionBarView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.navigation.AlbumDestinationHandler;
import com.amazon.mp3.navigation.CustomerProfileDestinationHandler;
import com.amazon.music.connect.feed.ActivityFeedFragment;
import com.amazon.music.connect.feed.FeedEvent;
import com.amazon.music.destination.AlbumDestination;
import com.amazon.music.destination.CustomerProfileDestination;
import com.amazon.music.destination.parser.Action;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FeedEventObserver.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\n\u0010\b\u001a\u0004\u0018\u00010\tH\u0002J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/feed/FeedEventObserver;", "Landroidx/lifecycle/Observer;", "Lcom/amazon/music/connect/feed/FeedEvent;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "pageId", "", "(Landroidx/fragment/app/FragmentActivity;I)V", "getFeedActionBarProvider", "Lcom/amazon/mp3/actionbar/ActionBarProvider;", "onChanged", "", "feedEvent", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FeedEventObserver implements Observer<FeedEvent> {
    private final FragmentActivity activity;
    private final int pageId;

    public FeedEventObserver(FragmentActivity activity, int i) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activity = activity;
        this.pageId = i;
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(FeedEvent feedEvent) {
        ActionBarProvider feedActionBarProvider;
        boolean z = false;
        if (feedEvent != null && feedEvent.processed) {
            z = true;
        }
        if (z) {
            return;
        }
        if (feedEvent != null) {
            feedEvent.processed = true;
        }
        if ((feedEvent == null ? null : feedEvent.type) == FeedEvent.Type.RELEASE_ITEM_CLICKED) {
            new AlbumDestinationHandler().navigateTo((Context) this.activity, new AlbumDestination(feedEvent.canonicalId, feedEvent.secondaryId, Action.PLAY, null, null));
            return;
        }
        if ((feedEvent == null ? null : feedEvent.type) == FeedEvent.Type.FOLLOW_ITEM_CLICKED) {
            new CustomerProfileDestinationHandler().navigateTo((Context) this.activity, new CustomerProfileDestination(feedEvent.canonicalId, null, null));
            return;
        }
        if ((feedEvent == null ? null : feedEvent.type) == FeedEvent.Type.GENERIC_URL_ITEM_CLICKED) {
            this.activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(feedEvent.canonicalId)));
            return;
        }
        if ((feedEvent == null ? null : feedEvent.type) == FeedEvent.Type.FRAGMENT_RESUMED) {
            ActionBarProvider feedActionBarProvider2 = getFeedActionBarProvider();
            FragmentActivity fragmentActivity = this.activity;
            ActionBarView actionBarView = new ActionBarView(fragmentActivity, fragmentActivity.getResources().getString(R.string.activity_feed_title), null, false, true, null, 44, null);
            if (feedActionBarProvider2 != null) {
                feedActionBarProvider2.setHeaderView(actionBarView);
            }
            if (feedActionBarProvider2 == null) {
                return;
            }
            feedActionBarProvider2.requestHomeButtonAsBack();
            return;
        }
        if ((feedEvent != null ? feedEvent.type : null) != FeedEvent.Type.FRAGMENT_DESTROYED || (feedActionBarProvider = getFeedActionBarProvider()) == null) {
            return;
        }
        feedActionBarProvider.removeHeaderView();
    }

    private final ActionBarProvider getFeedActionBarProvider() {
        Fragment fragmentFindFragmentById = this.activity.getSupportFragmentManager().findFragmentById(this.pageId);
        if ((this.activity instanceof ActionBarProvider) && (fragmentFindFragmentById instanceof ActivityFeedFragment) && ((ActivityFeedFragment) fragmentFindFragmentById).isResumed()) {
            return (ActionBarProvider) this.activity;
        }
        return null;
    }
}
