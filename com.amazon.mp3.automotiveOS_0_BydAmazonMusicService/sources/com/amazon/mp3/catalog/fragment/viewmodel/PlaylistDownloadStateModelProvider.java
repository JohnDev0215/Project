package com.amazon.mp3.catalog.fragment.viewmodel;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.factory.LibraryItemFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.views.library.models.DownloadStateModel;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: PlaylistDownloadStateModelProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u0005H\u0016J\u0018\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002J\"\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u0005H\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/PlaylistDownloadStateModelProvider;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/GroupDownloadStateModelProvider;", "context", "Landroid/content/Context;", "isCatalog", "", "(Landroid/content/Context;Z)V", "getInitialGroupDownloadState", "Lio/reactivex/Single;", "Lcom/amazon/music/views/library/models/DownloadStateModel;", "originalUri", "Landroid/net/Uri;", "computeGroupDownloadStateWithNonLibTracks", "isPlaylistUnified", "queryIsTrackGroupDownloaded", "uri", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistDownloadStateModelProvider extends GroupDownloadStateModelProvider {
    private static final String TAG = Reflection.getOrCreateKotlinClass(PlaylistDownloadStateModelProvider.class).getSimpleName();
    private final boolean isCatalog;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlaylistDownloadStateModelProvider(Context context, boolean z) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.isCatalog = z;
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider
    public Single<DownloadStateModel> getInitialGroupDownloadState(final Uri originalUri, final boolean computeGroupDownloadStateWithNonLibTracks) {
        if (this.isCatalog) {
            return super.getInitialGroupDownloadState(originalUri, computeGroupDownloadStateWithNonLibTracks);
        }
        Single<DownloadStateModel> singleFlatMap = Single.create(new SingleOnSubscribe() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.PlaylistDownloadStateModelProvider$$ExternalSyntheticLambda0
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                PlaylistDownloadStateModelProvider.m672getInitialGroupDownloadState$lambda0(this.f$0, originalUri, singleEmitter);
            }
        }).flatMap(new Function() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.PlaylistDownloadStateModelProvider$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return PlaylistDownloadStateModelProvider.m673getInitialGroupDownloadState$lambda1(this.f$0, originalUri, computeGroupDownloadStateWithNonLibTracks, (Boolean) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleFlatMap, "{\n            Single.cre…}\n            }\n        }");
        return singleFlatMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getInitialGroupDownloadState$lambda-0, reason: not valid java name */
    public static final void m672getInitialGroupDownloadState$lambda0(PlaylistDownloadStateModelProvider this$0, Uri uri, final SingleEmitter emitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        this$0.isPlaylistUnified(uri).subscribe(new Consumer() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.PlaylistDownloadStateModelProvider$$ExternalSyntheticLambda2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                emitter.onSuccess((Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getInitialGroupDownloadState$lambda-1, reason: not valid java name */
    public static final SingleSource m673getInitialGroupDownloadState$lambda1(PlaylistDownloadStateModelProvider this$0, Uri uri, boolean z, Boolean isUnified) {
        Single singleJust;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(isUnified, "isUnified");
        if (isUnified.booleanValue()) {
            DownloadStateModel.DownloadState downloadState = DownloadStateModel.DownloadState.DOWNLOADED;
            if (this$0.isRedownloadRequired(uri, downloadState)) {
                DownloadStateModel.DownloadState downloadState2 = DownloadStateModel.DownloadState.REDOWNLOAD;
                this$0.setGroupDownloadState(downloadState2);
                singleJust = Single.just(new DownloadStateModel(downloadState2, 0));
            } else {
                this$0.setGroupDownloadState(downloadState);
                singleJust = Single.just(new DownloadStateModel(downloadState, 0));
            }
            Intrinsics.checkNotNullExpressionValue(singleJust, "{\n                    if…      }\n                }");
            return singleJust;
        }
        return super.getInitialGroupDownloadState(uri, z);
    }

    private final Single<Boolean> isPlaylistUnified(Uri originalUri) {
        Single<Boolean> singleJust = Single.just(Boolean.valueOf(!MediaProvider.isSmartPlaylist(originalUri) && PlaylistUtil.isUserPlaylistDownloaded(PlaylistUtil.getPlaylistId(originalUri))));
        Intrinsics.checkNotNullExpressionValue(singleJust, "just(!isSmartPlaylist &&…PlaylistId(originalUri)))");
        return singleJust;
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider
    protected boolean queryIsTrackGroupDownloaded(Context context, Uri uri, boolean computeGroupDownloadStateWithNonLibTracks) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (uri == null) {
            return false;
        }
        if (this.isCatalog) {
            Cursor cursorQuery = MediaProvider.getInstance().query(uri, null, null, null, null);
            if (cursorQuery == null) {
                Log.warning(TAG, "Failed to find PrimePlaylist in database (cursor null)");
                return false;
            }
            try {
                if (!cursorQuery.moveToFirst()) {
                    Log.warning(TAG, "Failed to find PrimePlaylist in database (cursor empty)");
                    return false;
                }
                int i = 0;
                while (true) {
                    if (cursorQuery.isAfterLast()) {
                        return i > 0;
                    }
                    LibraryItemFactory libraryItemFactory = AmazonApplication.getLibraryItemFactory();
                    MusicTrack track = libraryItemFactory.getTrack(cursorQuery);
                    track.setParentEligibilities(libraryItemFactory.getCollectionItem(uri));
                    boolean z = track.getDownloadState() == 0;
                    boolean z2 = ContentAccessUtil.getCatalogContentUnavailableReason(track, ContentAccessUtil.ContentAccessOperation.STREAM) == null;
                    if (!z && z2) {
                        return false;
                    }
                    if (z) {
                        i++;
                    }
                    cursorQuery.moveToNext();
                }
            } finally {
                cursorQuery.close();
            }
        } else {
            if (MediaProvider.isSmartPlaylist(uri)) {
                return super.queryIsTrackGroupDownloaded(context, uri, computeGroupDownloadStateWithNonLibTracks);
            }
            return false;
        }
    }
}
