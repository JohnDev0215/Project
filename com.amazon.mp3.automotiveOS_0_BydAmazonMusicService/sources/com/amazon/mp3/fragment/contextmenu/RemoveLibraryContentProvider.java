package com.amazon.mp3.fragment.contextmenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.dialog.fragment.DeleteTrackErrorDialogFragment;
import com.amazon.mp3.fragment.contextmenu.ContextMenuDownloadComponent;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.mp3.util.AccessibilityUtil;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.mp3.util.extensions.ActivityKt;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RemoveLibraryContentProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 12\u00020\u0001:\u000212B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J \u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u0011H\u0002J \u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001a2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u0014H\u0002J \u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u001a2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020\u0014H\u0002J\u0018\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001a2\u0006\u0010 \u001a\u00020!H\u0002J \u0010\"\u001a\u00020#2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u0014H\u0002J\u0018\u0010$\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u0014H\u0002J \u0010%\u001a\u00020#2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020\u0014H\u0002J\u0018\u0010&\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020\u0014H\u0002J\u0010\u0010'\u001a\u00020\b2\u0006\u0010(\u001a\u00020\u0018H\u0002J\u000e\u0010)\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u001dJ\u000e\u0010)\u001a\u00020\u00112\u0006\u0010 \u001a\u00020!J\u000e\u0010*\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u001dJ \u0010+\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010,\u001a\u00020#2\u0006\u0010-\u001a\u00020.H\u0002J\u000e\u0010/\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u00100\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0018R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/RemoveLibraryContentProvider;", "", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroid/app/Activity;", "removeCompleteListener", "Lcom/amazon/mp3/fragment/contextmenu/RemoveLibraryContentProvider$OnRemoveLibraryContentListener;", "(Landroid/app/Activity;Lcom/amazon/mp3/fragment/contextmenu/RemoveLibraryContentProvider$OnRemoveLibraryContentListener;)V", "CONFIRM_DELETE_ALBUM_ID", "", "CONFIRM_DELETE_TRACK_ID", "appContext", "Landroid/content/Context;", "compositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "executorService", "Ljava/util/concurrent/ExecutorService;", "announceDeleteFinished", "", "context", "successful", "", "buildConfirmDeletePrimeTrackDialog", "Landroid/app/Dialog;", "track", "Lcom/amazon/mp3/library/item/MusicTrack;", "confirmDeleteDialogHolder", "Lcom/amazon/mp3/fragment/contextmenu/ContextMenuDownloadComponent$ConfirmDeleteDialogHolder;", "displayTrackDeletionError", "getConfirmDeleteDialogHolder", "Lcom/amazon/mp3/library/item/Album;", "album", "deleteFromDevice", "metadata", "Lcom/amazon/music/views/library/metadata/AlbumMetadata;", "getConfirmRemoveAlbumDialogMessage", "", "getConfirmRemoveAlbumDialogTitleId", "getConfirmRemoveTrackDialogMessage", "getConfirmRemoveTrackDialogTitleId", "getDeleteContentMessageId", "item", "removeAlbumFromCloud", "removeAlbumFromDevice", "removeTrack", "asin", "uri", "Landroid/net/Uri;", "removeTrackFromCloud", "removeTrackFromDevice", "Companion", "OnRemoveLibraryContentListener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RemoveLibraryContentProvider {
    private final Activity activity;
    private final Context appContext;
    private final CompositeDisposable compositeDisposable;
    private final ExecutorService executorService;
    private final OnRemoveLibraryContentListener removeCompleteListener;
    private final int CONFIRM_DELETE_TRACK_ID = UniqueCodeUtil.nextUniqueCode();
    private final int CONFIRM_DELETE_ALBUM_ID = UniqueCodeUtil.nextUniqueCode();

    /* JADX INFO: compiled from: RemoveLibraryContentProvider.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/RemoveLibraryContentProvider$OnRemoveLibraryContentListener;", "", "onRemoveContentComplete", "", "uri", "Landroid/net/Uri;", "onRemoveContentConfirm", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface OnRemoveLibraryContentListener {
        void onRemoveContentComplete(Uri uri);

        void onRemoveContentConfirm();
    }

    public RemoveLibraryContentProvider(Activity activity, OnRemoveLibraryContentListener onRemoveLibraryContentListener) {
        this.activity = activity;
        this.removeCompleteListener = onRemoveLibraryContentListener;
        this.appContext = activity == null ? null : activity.getApplicationContext();
        this.compositeDisposable = new CompositeDisposable();
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(executorServiceNewSingleThreadExecutor, "newSingleThreadExecutor()");
        this.executorService = executorServiceNewSingleThreadExecutor;
    }

    public final synchronized void removeTrackFromCloud(MusicTrack track) {
        Intrinsics.checkNotNullParameter(track, "track");
        ContextMenuDownloadComponent.ConfirmDeleteDialogHolder<MusicTrack> confirmDeleteDialogHolder = getConfirmDeleteDialogHolder(track, false);
        if (confirmDeleteDialogHolder != null) {
            Uri contentUri = track.getContentUri();
            if ((track.isAllPrime() || track.isAllCatalog()) && (contentUri == null || MediaProvider.Tracks.isTrack(contentUri))) {
                Dialog dialogBuildConfirmDeletePrimeTrackDialog = buildConfirmDeletePrimeTrackDialog(track, confirmDeleteDialogHolder);
                if (dialogBuildConfirmDeletePrimeTrackDialog != null) {
                    dialogBuildConfirmDeletePrimeTrackDialog.show();
                }
            } else {
                ContextMenuDownloadComponent.deleteCloudContent(this.activity, this.CONFIRM_DELETE_TRACK_ID, track.getContentUri(), confirmDeleteDialogHolder, false, null, this.removeCompleteListener);
            }
        }
    }

    public final synchronized void removeTrackFromDevice(MusicTrack track) {
        Intrinsics.checkNotNullParameter(track, "track");
        ContextMenuDownloadComponent.ConfirmDeleteDialogHolder<MusicTrack> confirmDeleteDialogHolder = getConfirmDeleteDialogHolder(track, true);
        if (confirmDeleteDialogHolder != null) {
            ContextMenuDownloadComponent.deleteDeviceContent(this.activity, this.CONFIRM_DELETE_TRACK_ID, track.getContentUri(), confirmDeleteDialogHolder, this.removeCompleteListener);
        }
    }

    public final synchronized void removeAlbumFromCloud(Album album) {
        Intrinsics.checkNotNullParameter(album, "album");
        boolean z = album.isNotOwned() && album.getDownloadState() == 0;
        String id = album.getId();
        Intrinsics.checkNotNullExpressionValue(id, "album.id");
        Uri contentUri = MediaProvider.Albums.Tracks.getContentUri("cirrus-local", Long.parseLong(id));
        ContextMenuDownloadComponent.ConfirmDeleteDialogHolder<Album> confirmDeleteDialogHolder = getConfirmDeleteDialogHolder(album, false);
        if (confirmDeleteDialogHolder != null) {
            ContextMenuDownloadComponent.deleteCloudContent(this.activity, this.CONFIRM_DELETE_ALBUM_ID, ContentPlaybackUtils.INSTANCE.appendTracksIfNeeded(album.getContentUri()), confirmDeleteDialogHolder, z, contentUri);
        }
    }

    public final void removeAlbumFromCloud(AlbumMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        boolean z = Intrinsics.areEqual(metadata.getIsOwned(), Boolean.FALSE) && Intrinsics.areEqual(metadata.getIsDownloaded(), Boolean.TRUE);
        Uri contentUri = MediaProvider.Albums.Tracks.getContentUri("cirrus-local", MediaProvider.Albums.getAlbumId(metadata.getUri()));
        ContextMenuDownloadComponent.ConfirmDeleteDialogHolder<Album> confirmDeleteDialogHolder = getConfirmDeleteDialogHolder(metadata);
        if (confirmDeleteDialogHolder == null) {
            return;
        }
        ContextMenuDownloadComponent.deleteCloudContent(this.activity, this.CONFIRM_DELETE_ALBUM_ID, ContentPlaybackUtils.INSTANCE.appendTracksIfNeeded(metadata.getUri()), confirmDeleteDialogHolder, z, contentUri, this.removeCompleteListener);
    }

    public final synchronized void removeAlbumFromDevice(Album album) {
        Intrinsics.checkNotNullParameter(album, "album");
        Uri uriAppendTracksIfNeeded = ContentPlaybackUtils.INSTANCE.appendTracksIfNeeded(album.getContentUri());
        if (AmazonApplication.getCapabilities().isTrueLikesAndFollowsEnabled()) {
            Long albumId = CirrusMediaSource.getAlbumOrArtistIdByAsin(this.appContext, album.getContentUri(), album.getAsin());
            Intrinsics.checkNotNullExpressionValue(albumId, "albumId");
            uriAppendTracksIfNeeded = MediaProvider.Albums.Tracks.getContentUri("cirrus-local", albumId.longValue());
        }
        ContextMenuDownloadComponent.ConfirmDeleteDialogHolder<Album> confirmDeleteDialogHolder = getConfirmDeleteDialogHolder(album, true);
        if (confirmDeleteDialogHolder != null) {
            ContextMenuDownloadComponent.deleteDeviceContent(this.activity, this.CONFIRM_DELETE_ALBUM_ID, uriAppendTracksIfNeeded, confirmDeleteDialogHolder, this.removeCompleteListener);
        }
    }

    private final ContextMenuDownloadComponent.ConfirmDeleteDialogHolder<Album> getConfirmDeleteDialogHolder(Album album, boolean deleteFromDevice) {
        Activity activity = this.activity;
        if (activity == null) {
            return null;
        }
        return new ContextMenuDownloadComponent.ConfirmDeleteDialogHolder<>(album, getConfirmRemoveAlbumDialogTitleId(album, deleteFromDevice), getConfirmRemoveAlbumDialogMessage(activity, album, deleteFromDevice), deleteFromDevice ? 0 : R.string.dmusic_album_toast_deleting_album_cloud, deleteFromDevice ? 0 : R.string.dmusic_button_yes, deleteFromDevice ? 0 : R.string.dmusic_button_no, deleteFromDevice ? 0 : R.string.album_removed_content_description, R.string.album_removed_from_device_content_description);
    }

    private final ContextMenuDownloadComponent.ConfirmDeleteDialogHolder<MusicTrack> getConfirmDeleteDialogHolder(MusicTrack track, boolean deleteFromDevice) {
        Activity activity = this.activity;
        if (activity == null) {
            return null;
        }
        return new ContextMenuDownloadComponent.ConfirmDeleteDialogHolder<>(track, getConfirmRemoveTrackDialogTitleId(track, deleteFromDevice), getConfirmRemoveTrackDialogMessage(activity, track, deleteFromDevice), deleteFromDevice ? 0 : getDeleteContentMessageId(track), deleteFromDevice ? 0 : R.string.dmusic_button_yes, deleteFromDevice ? 0 : R.string.dmusic_button_no, deleteFromDevice ? 0 : R.string.song_removed_content_description, R.string.song_removed_from_device_content_description);
    }

    private final ContextMenuDownloadComponent.ConfirmDeleteDialogHolder<Album> getConfirmDeleteDialogHolder(AlbumMetadata metadata) {
        Activity activity = this.activity;
        if (activity == null) {
            return null;
        }
        return new ContextMenuDownloadComponent.ConfirmDeleteDialogHolder<>(null, R.string.dmusic_context_remove_from_library, activity.getString(R.string.dmusic_album_confirm_remove_library_msg, metadata.getTitle()), R.string.dmusic_stop_following_toast_message, R.string.dmusic_button_yes, R.string.dmusic_button_no, R.string.album_removed_content_description, R.string.album_removed_from_device_content_description);
    }

    private final int getDeleteContentMessageId(MusicTrack item) {
        return item.isAvailableOffline() ? R.string.dmusic_track_toast_deleting_track_device : (item.isNotOwned() && item.isInLibrary()) ? R.string.dmusic_track_toast_removing_track_cloud : R.string.dmusic_track_toast_deleting_track_cloud;
    }

    private final int getConfirmRemoveTrackDialogTitleId(MusicTrack track, boolean deleteFromDevice) {
        return deleteFromDevice ? R.string.dmusic_context_delete_device : track.isNotOwned() ? R.string.dmusic_context_remove_from_library : R.string.dmusic_context_delete_cloud;
    }

    private final String getConfirmRemoveTrackDialogMessage(Context context, MusicTrack track, boolean deleteFromDevice) {
        if (deleteFromDevice) {
            String string = context.getString(R.string.dmusic_track_confirm_delete_msg);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…track_confirm_delete_msg)");
            return string;
        }
        if (track.isNotOwned()) {
            String string2 = context.getString(R.string.dmusic_track_confirm_remove_cloud_msg, track.getTitle());
            Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.stri…e_cloud_msg, track.title)");
            return string2;
        }
        String string3 = context.getString(R.string.dmusic_track_confirm_delete_cloud_msg);
        Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.stri…confirm_delete_cloud_msg)");
        return string3;
    }

    private final int getConfirmRemoveAlbumDialogTitleId(Album album, boolean deleteFromDevice) {
        return deleteFromDevice ? R.string.dmusic_context_delete_device : album.isNotOwned() ? R.string.dmusic_context_remove_from_library : R.string.dmusic_context_delete_cloud;
    }

    private final String getConfirmRemoveAlbumDialogMessage(Context context, Album album, boolean deleteFromDevice) {
        if (deleteFromDevice) {
            String string = context.getString(R.string.dmusic_album_confirm_delete_msg);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…album_confirm_delete_msg)");
            return string;
        }
        if (album.isNotOwned()) {
            String string2 = context.getString(R.string.dmusic_album_confirm_remove_library_msg, album.getTitle());
            Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.stri…library_msg, album.title)");
            return string2;
        }
        String string3 = context.getString(R.string.dmusic_album_confirm_delete_cloud_msg, album.getTitle());
        Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.stri…e_cloud_msg, album.title)");
        return string3;
    }

    private final Dialog buildConfirmDeletePrimeTrackDialog(MusicTrack track, final ContextMenuDownloadComponent.ConfirmDeleteDialogHolder<MusicTrack> confirmDeleteDialogHolder) {
        Activity activity;
        final Uri contentUri = track.getContentUri();
        final String asin = track.getAsin();
        if (!((contentUri == null && asin == null) ? false : true)) {
            throw new IllegalArgumentException("invalid track uri or prime asin!".toString());
        }
        if (this.appContext == null || (activity = this.activity) == null) {
            return null;
        }
        return new AlertDialog.Builder(activity).setTitle(confirmDeleteDialogHolder.mTitleStringId).setMessage(confirmDeleteDialogHolder.mMessageString).setNegativeButton(R.string.dmusic_button_no, (DialogInterface.OnClickListener) null).setPositiveButton(R.string.dmusic_button_yes, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.fragment.contextmenu.RemoveLibraryContentProvider$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                RemoveLibraryContentProvider.m1143buildConfirmDeletePrimeTrackDialog$lambda11$lambda10$lambda9(this.f$0, confirmDeleteDialogHolder, asin, contentUri, dialogInterface, i);
            }
        }).create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: buildConfirmDeletePrimeTrackDialog$lambda-11$lambda-10$lambda-9, reason: not valid java name */
    public static final void m1143buildConfirmDeletePrimeTrackDialog$lambda11$lambda10$lambda9(RemoveLibraryContentProvider this$0, ContextMenuDownloadComponent.ConfirmDeleteDialogHolder confirmDeleteDialogHolder, String asin, Uri trackUri, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(confirmDeleteDialogHolder, "$confirmDeleteDialogHolder");
        BauhausToastUtils.showTextToast(this$0.appContext, confirmDeleteDialogHolder.mToastStringId, 1);
        Context context = this$0.appContext;
        Intrinsics.checkNotNullExpressionValue(asin, "asin");
        Intrinsics.checkNotNullExpressionValue(trackUri, "trackUri");
        this$0.removeTrack(context, asin, trackUri);
    }

    private final void removeTrack(final Context context, String asin, final Uri uri) {
        Disposable disposableSubscribe = new DeletePrimeTrackRx().deleteTrack(context, asin).subscribeOn(Schedulers.from(this.executorService)).observeOn(AndroidSchedulers.mainThread()).subscribe(new BiConsumer() { // from class: com.amazon.mp3.fragment.contextmenu.RemoveLibraryContentProvider$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.BiConsumer
            public final void accept(Object obj, Object obj2) {
                RemoveLibraryContentProvider.m1144removeTrack$lambda13(context, this, uri, (Boolean) obj, (Throwable) obj2);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "DeletePrimeTrackRx().del…      }\n                }");
        this.compositeDisposable.add(disposableSubscribe);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: removeTrack$lambda-13, reason: not valid java name */
    public static final void m1144removeTrack$lambda13(Context context, RemoveLibraryContentProvider this$0, Uri uri, Boolean result, Throwable th) {
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(uri, "$uri");
        if (th == null) {
            Intrinsics.checkNotNullExpressionValue(result, "result");
            if (result.booleanValue()) {
                this$0.announceDeleteFinished(context, true);
                OnRemoveLibraryContentListener onRemoveLibraryContentListener = this$0.removeCompleteListener;
                if (onRemoveLibraryContentListener == null) {
                    return;
                }
                onRemoveLibraryContentListener.onRemoveContentComplete(uri);
                return;
            }
        }
        this$0.announceDeleteFinished(context, false);
        this$0.displayTrackDeletionError();
    }

    private final void announceDeleteFinished(Context context, boolean successful) {
        AccessibilityUtil.announceForAccessibility(context, context.getString(successful ? R.string.song_removed_content_description : R.string.removal_fail_content_description));
    }

    private final void displayTrackDeletionError() {
        DeleteTrackErrorDialogFragment deleteTrackErrorDialogFragment = new DeleteTrackErrorDialogFragment();
        Activity activity = this.activity;
        if (activity == null) {
            return;
        }
        ActivityKt.showDialogFragment(activity, deleteTrackErrorDialogFragment);
    }
}
