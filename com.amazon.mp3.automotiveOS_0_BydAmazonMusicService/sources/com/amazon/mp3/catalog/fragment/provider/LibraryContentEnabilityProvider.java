package com.amazon.mp3.catalog.fragment.provider;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.activity.MusicLauncherActivity;
import com.amazon.mp3.activity.NetworkErrorDialogActivity;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.MusicDownloaderKt;
import com.amazon.mp3.fragment.contextmenu.RemoveLibraryContentProvider;
import com.amazon.mp3.library.fragment.DeleteContentHandler;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.ContentEnabilityDialogUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PrimeContentUtil;
import com.amazon.mp3.util.extensions.ActivityKt;
import com.amazon.music.media.playback.util.AndroidUtils;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import com.amazon.music.views.library.providers.AvailabilityState;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryContentEnabilityProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u000fH\u0002J\u000e\u0010\u0013\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u00172\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u000fH\u0014J\u0010\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u001c\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u001d"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/provider/LibraryContentEnabilityProvider;", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "context", "Landroid/content/Context;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "(Landroid/content/Context;Landroidx/fragment/app/FragmentActivity;)V", "TAG", "", "kotlin.jvm.PlatformType", "getContext", "()Landroid/content/Context;", "deleteContent", "", "item", "Lcom/amazon/mp3/library/item/AbstractItem;", "getContentUnavailableReason", "Lcom/amazon/mp3/prime/ContentUnavailableReason;", "contentItem", "handleContentAccessUnavailable", "metadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "isContentAccessAvailable", "", "isContentAvailableOnClick", "shouldEnableContent", "showContentEnabilityDialog", "reason", "tryToResolveDeviceNotAuthorized", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class LibraryContentEnabilityProvider implements ContentEnabilityProvider {
    private final String TAG;
    private final FragmentActivity activity;
    private final Context context;

    @Override // com.amazon.music.views.library.providers.ContentEnabilityProvider
    public boolean shouldEnableContent(ContentMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        return true;
    }

    public LibraryContentEnabilityProvider(Context context, FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.activity = fragmentActivity;
        this.TAG = LibraryContentEnabilityProvider.class.getSimpleName();
    }

    @Override // com.amazon.music.views.library.providers.ContentEnabilityProvider
    public AvailabilityState getAvailabilityState(ContentMetadata contentMetadata) {
        return ContentEnabilityProvider.DefaultImpls.getAvailabilityState(this, contentMetadata);
    }

    protected final Context getContext() {
        return this.context;
    }

    @Override // com.amazon.music.views.library.providers.ContentEnabilityProvider
    public Object isContentAvailable(ContentMetadata contentMetadata, Continuation<? super Boolean> continuation) {
        return ContentEnabilityProvider.DefaultImpls.isContentAvailable(this, contentMetadata, continuation);
    }

    @Override // com.amazon.music.views.library.providers.ContentEnabilityProvider
    public void setContentEnabled(View view, BaseViewContentModel baseViewContentModel) {
        ContentEnabilityProvider.DefaultImpls.setContentEnabled(this, view, baseViewContentModel);
    }

    @Override // com.amazon.music.views.library.providers.ContentEnabilityProvider
    public void updateViewEnability(View view, boolean z) {
        ContentEnabilityProvider.DefaultImpls.updateViewEnability(this, view, z);
    }

    @Override // com.amazon.music.views.library.providers.ContentEnabilityProvider
    public boolean isContentAccessAvailable(ContentMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        AbstractItem item = new CirrusSourceLibraryItemFactory(this.context).getItem(metadata.getUri());
        if (!(metadata instanceof TrackMetadata)) {
            if (metadata instanceof AlbumMetadata ? true : metadata instanceof PlaylistMetadata ? true : metadata instanceof UserPlaylistMetadata) {
                return isContentAccessAvailable(item);
            }
            return true;
        }
        TrackMetadata trackMetadata = (TrackMetadata) metadata;
        if (trackMetadata.getIsSuggestion()) {
            return true;
        }
        MusicTrack musicTrack = item instanceof MusicTrack ? (MusicTrack) item : null;
        ContentMetadata containerMetadata = trackMetadata.getContainerMetadata();
        Uri uri = containerMetadata != null ? containerMetadata.getUri() : null;
        if (musicTrack != null) {
            musicTrack.setParentEligibilities(new CirrusSourceLibraryItemFactory(this.context).getItem(uri));
        }
        if (musicTrack != null) {
            musicTrack.setCollectionUri(uri);
        }
        return isContentAccessAvailable(musicTrack);
    }

    public final boolean isContentAccessAvailable(AbstractItem item) {
        if (item == null) {
            return false;
        }
        if (item instanceof MusicTrack ? true : item instanceof Album ? true : item instanceof Playlist ? true : item instanceof CatalogPlaylist) {
            return isContentAvailableOnClick(item);
        }
        return true;
    }

    @Override // com.amazon.music.views.library.providers.ContentEnabilityProvider
    public void handleContentAccessUnavailable(ContentMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        AbstractItem item = new CirrusSourceLibraryItemFactory(this.context).getItem(metadata.getUri());
        if (item == null) {
            return;
        }
        handleContentAccessUnavailable(item);
    }

    public final void handleContentAccessUnavailable(AbstractItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        ContentUnavailableReason playCatalogContentUnavailableReason = ContentAccessUtil.getPlayCatalogContentUnavailableReason(item);
        if (playCatalogContentUnavailableReason == null) {
            ConnectivityUtil.checkConnectivityAndShowDialog(this.activity);
            return;
        }
        if (playCatalogContentUnavailableReason == ContentUnavailableReason.DEVICE_NOT_PRIME_AUTHORIZED) {
            tryToResolveDeviceNotAuthorized(item);
            return;
        }
        if (playCatalogContentUnavailableReason == ContentUnavailableReason.FILE_DOES_NOT_EXIST_ON_DISK) {
            if (item.getDownloadState() == 0) {
                BauhausToastUtils.showTextToast(this.activity, this.context.getString(R.string.sd_card_not_found_toast), 1);
                return;
            }
            return;
        }
        if (playCatalogContentUnavailableReason == ContentUnavailableReason.FILE_DOES_NOT_EXIST) {
            if (item.getAsin() != null) {
                MusicDownloaderKt musicDownloaderKt = MusicDownloaderKt.INSTANCE;
                String asin = item.getAsin();
                Intrinsics.checkNotNullExpressionValue(asin, "item.asin");
                musicDownloaderKt.showQueueInvalidTrackRedownloadDialog(asin, AndroidUtils.getCurrentActivity());
                return;
            }
            Log.warning(this.TAG, "Corrupted item cannot be redownloaded. Asin is null for contentItem of type");
            return;
        }
        if (playCatalogContentUnavailableReason == ContentUnavailableReason.DOWNLOAD_CORRUPTED) {
            if (item.getAsin() != null) {
                MusicDownloaderKt musicDownloaderKt2 = MusicDownloaderKt.INSTANCE;
                String asin2 = item.getAsin();
                Intrinsics.checkNotNullExpressionValue(asin2, "item.asin");
                musicDownloaderKt2.showQueueInvalidTrackRedownloadDialog(asin2, AndroidUtils.getCurrentActivity());
                return;
            }
            Log.warning(this.TAG, "Corrupted item cannot be redownloaded. Asin is null for contentItem of type");
            return;
        }
        if (playCatalogContentUnavailableReason == ContentUnavailableReason.CUSTOMER_DATA_NOT_READY) {
            ConnectivityUtil.checkConnectivityAndShowDialog(this.activity);
        } else {
            showContentEnabilityDialog(playCatalogContentUnavailableReason, item);
        }
    }

    private final ContentUnavailableReason getContentUnavailableReason(AbstractItem contentItem) {
        ContentUnavailableReason playCatalogContentUnavailableReason = ContentAccessUtil.getPlayCatalogContentUnavailableReason(contentItem);
        if ((playCatalogContentUnavailableReason == ContentUnavailableReason.ASSET_IS_PRE_WIDEVINE && contentItem.isRemote()) || playCatalogContentUnavailableReason == ContentUnavailableReason.NO_NETWORK) {
            return null;
        }
        if (contentItem instanceof MusicTrack) {
            return playCatalogContentUnavailableReason;
        }
        if ((contentItem instanceof Album ? true : contentItem instanceof Playlist ? true : contentItem instanceof CatalogPlaylist) && playCatalogContentUnavailableReason == ContentUnavailableReason.NOT_AVAILABLE_OFFLINE) {
            return playCatalogContentUnavailableReason;
        }
        return null;
    }

    protected boolean isContentAvailableOnClick(AbstractItem contentItem) {
        Intrinsics.checkNotNullParameter(contentItem, "contentItem");
        boolean z = LastViewedScreenUtil.isSourceLocal(this.context) || contentItem.isLocal();
        if (z || !contentItem.isAvailable()) {
            return (z && contentItem.isAvailableOffline()) || getContentUnavailableReason(contentItem) == null;
        }
        return true;
    }

    private final void tryToResolveDeviceNotAuthorized(final AbstractItem item) {
        AccountCredentialUtil accountCredentialUtil = AccountCredentialUtil.get(this.context);
        accountCredentialUtil.synchronizeWithDeviceRegistration(this.activity, false);
        if (accountCredentialUtil.isSignedIn()) {
            PrimeContentUtil.tryToAutoResolveEnabilityProblem(item, new PrimeContentUtil.ContentAvailabilityStateListener() { // from class: com.amazon.mp3.catalog.fragment.provider.LibraryContentEnabilityProvider$$ExternalSyntheticLambda0
                @Override // com.amazon.mp3.util.PrimeContentUtil.ContentAvailabilityStateListener
                public final void resultReceived(boolean z, ContentUnavailableReason contentUnavailableReason) {
                    LibraryContentEnabilityProvider.m585tryToResolveDeviceNotAuthorized$lambda1(this.f$0, item, z, contentUnavailableReason);
                }
            });
        } else {
            this.context.startActivity(new Intent(this.context, (Class<?>) MusicLauncherActivity.class));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: tryToResolveDeviceNotAuthorized$lambda-1, reason: not valid java name */
    public static final void m585tryToResolveDeviceNotAuthorized$lambda1(LibraryContentEnabilityProvider this$0, AbstractItem item, boolean z, ContentUnavailableReason contentUnavailableReason) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(item, "$item");
        if (z || contentUnavailableReason == null) {
            return;
        }
        this$0.showContentEnabilityDialog(contentUnavailableReason, item);
    }

    private final void showContentEnabilityDialog(ContentUnavailableReason reason, final AbstractItem item) {
        Context context = this.activity;
        if (context == null) {
            context = this.context;
        }
        DialogFragment dialogForDisabledReason = ContentEnabilityDialogUtil.getDialogForDisabledReason(context, reason, new DeleteContentHandler() { // from class: com.amazon.mp3.catalog.fragment.provider.LibraryContentEnabilityProvider$$ExternalSyntheticLambda1
            @Override // com.amazon.mp3.library.fragment.DeleteContentHandler
            public final void deleteContent() {
                LibraryContentEnabilityProvider.m584showContentEnabilityDialog$lambda2(this.f$0, item);
            }
        }, item.getItemTypeName(), item, ContentAccessUtil.ContentAccessOperation.STREAM);
        FragmentActivity fragmentActivity = this.activity;
        if (fragmentActivity == null) {
            return;
        }
        ActivityKt.showDialogFragment(fragmentActivity, dialogForDisabledReason);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showContentEnabilityDialog$lambda-2, reason: not valid java name */
    public static final void m584showContentEnabilityDialog$lambda2(LibraryContentEnabilityProvider this$0, AbstractItem item) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(item, "$item");
        this$0.deleteContent(item);
    }

    private final void deleteContent(AbstractItem item) {
        if (item.isRemote() && !ConnectivityUtil.hasAnyInternetConnection()) {
            Context context = this.context;
            context.startActivity(NetworkErrorDialogActivity.getStartIntent(context));
        } else if (item instanceof MusicTrack) {
            new RemoveLibraryContentProvider(this.activity, null).removeTrackFromCloud((MusicTrack) item);
        }
    }
}
