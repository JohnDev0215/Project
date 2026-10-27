package com.amazon.mp3.catalog.fragment.provider;

import android.content.Context;
import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.util.UserSubscriptionUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaylistContentEnabilityProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0014J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0002¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/provider/PlaylistContentEnabilityProvider;", "Lcom/amazon/mp3/catalog/fragment/provider/LibraryContentEnabilityProvider;", "context", "Landroid/content/Context;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "(Landroid/content/Context;Landroidx/fragment/app/FragmentActivity;)V", "isContentAvailableOnClick", "", "contentItem", "Lcom/amazon/mp3/library/item/AbstractItem;", "isRecentlyPlayedPlaylist", "contentUri", "Landroid/net/Uri;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistContentEnabilityProvider extends LibraryContentEnabilityProvider {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlaylistContentEnabilityProvider(Context context, FragmentActivity fragmentActivity) {
        super(context, fragmentActivity);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // com.amazon.mp3.catalog.fragment.provider.LibraryContentEnabilityProvider
    protected boolean isContentAvailableOnClick(AbstractItem contentItem) {
        Intrinsics.checkNotNullParameter(contentItem, "contentItem");
        if (!(contentItem instanceof MusicTrack)) {
            return super.isContentAvailableOnClick(contentItem);
        }
        boolean zAreEqual = Intrinsics.areEqual("cirrus-local", LastViewedScreenUtil.getLastViewedSource(getContext(), LastViewedScreenUtil.LastViewedSource.CLOUD).toCirrusMediaSource());
        if (UserSubscriptionUtil.isNightwingOnly()) {
            Uri contentUri = contentItem.getContentUri();
            Intrinsics.checkNotNullExpressionValue(contentUri, "contentItem.getContentUri()");
            if (isRecentlyPlayedPlaylist(contentUri) && !zAreEqual && !contentItem.isAvailable() && (!AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled() || !contentItem.isExplicit())) {
                return true;
            }
        }
        if (UserSubscriptionUtil.isNightwingOnly()) {
            return (AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled() && ((MusicTrack) contentItem).isExplicit()) ? false : true;
        }
        return super.isContentAvailableOnClick(contentItem);
    }

    private final boolean isRecentlyPlayedPlaylist(Uri contentUri) {
        return MediaProvider.isSmartPlaylist(contentUri) && 3 == MediaProvider.SmartPlaylists.getPlaylistId(contentUri);
    }
}
