package com.amazon.mp3.fragment.contextmenu;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.util.AddToPlaylistManager;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: PlaylistTransactionsViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u001a\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b:\u0010;J\u0010\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002J\"\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\n\u001a\u00020\tJ\u0018\u0010\u000e\u001a\u00020\t2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\r\u001a\u00020\fJ\u000e\u0010\u000f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\fJ\u0017\u0010\u0010\u001a\u0004\u0018\u00010\u00042\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0013\u001a\n \u0012*\u0004\u0018\u00010\u00060\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0017R\u001c\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u0017R\u001f\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001a8\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u001f\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u001a8\u0006¢\u0006\f\n\u0004\b\u001f\u0010\u001c\u001a\u0004\b\u001f\u0010\u001eR\u001f\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u001a8\u0006¢\u0006\f\n\u0004\b \u0010\u001c\u001a\u0004\b!\u0010\u001eR$\u0010#\u001a\u0004\u0018\u00010\"8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R$\u0010)\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\"\u0010/\u001a\u00020\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b/\u00100\u001a\u0004\b1\u00102\"\u0004\b3\u00104R$\u00105\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b5\u0010\u0014\u001a\u0004\b6\u00107\"\u0004\b8\u00109¨\u0006<"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/PlaylistTransactionsViewModel;", "Landroidx/lifecycle/ViewModel;", "Landroid/content/Context;", "context", "", "checkIfNumberOfPlaylistsLimitReached", "", "name", "sourceId", "", "addTracks", "createPlaylist", "Landroid/net/Uri;", "playlistUri", "canEditOnlinePlaylist", "addToUserPlaylist", "addTracksToPlaylist", "(Landroid/net/Uri;)Lkotlin/Unit;", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "Landroidx/lifecycle/MutableLiveData;", "_canCreatePlaylist", "Landroidx/lifecycle/MutableLiveData;", "_isAlbumTrack", "_newPlaylistUri", "Landroidx/lifecycle/LiveData;", "canCreatePlaylist", "Landroidx/lifecycle/LiveData;", "getCanCreatePlaylist", "()Landroidx/lifecycle/LiveData;", "isAlbumTrack", "newPlaylistUri", "getNewPlaylistUri", "Lcom/amazon/mp3/library/util/AddToPlaylistManager;", "addToPlaylistManager", "Lcom/amazon/mp3/library/util/AddToPlaylistManager;", "getAddToPlaylistManager", "()Lcom/amazon/mp3/library/util/AddToPlaylistManager;", "setAddToPlaylistManager", "(Lcom/amazon/mp3/library/util/AddToPlaylistManager;)V", "tracksUri", "Landroid/net/Uri;", "getTracksUri", "()Landroid/net/Uri;", "setTracksUri", "(Landroid/net/Uri;)V", "albumCollection", "Z", "getAlbumCollection", "()Z", "setAlbumCollection", "(Z)V", "albumAsin", "getAlbumAsin", "()Ljava/lang/String;", "setAlbumAsin", "(Ljava/lang/String;)V", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class PlaylistTransactionsViewModel extends ViewModel {
    private final String TAG = PlaylistTransactionsViewModel.class.getSimpleName();
    private final MutableLiveData<Boolean> _canCreatePlaylist;
    private final MutableLiveData<Uri> _isAlbumTrack;
    private final MutableLiveData<Uri> _newPlaylistUri;
    private AddToPlaylistManager addToPlaylistManager;
    private String albumAsin;
    private boolean albumCollection;
    private final LiveData<Boolean> canCreatePlaylist;
    private final LiveData<Uri> isAlbumTrack;
    private final LiveData<Uri> newPlaylistUri;
    private Uri tracksUri;

    public PlaylistTransactionsViewModel() {
        MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(null);
        this._canCreatePlaylist = mutableLiveData;
        MutableLiveData<Uri> mutableLiveData2 = new MutableLiveData<>();
        mutableLiveData2.setValue(null);
        this._isAlbumTrack = mutableLiveData2;
        MutableLiveData<Uri> mutableLiveData3 = new MutableLiveData<>();
        mutableLiveData3.setValue(null);
        this._newPlaylistUri = mutableLiveData3;
        this.canCreatePlaylist = mutableLiveData;
        this.isAlbumTrack = mutableLiveData2;
        this.newPlaylistUri = mutableLiveData3;
    }

    public final LiveData<Boolean> getCanCreatePlaylist() {
        return this.canCreatePlaylist;
    }

    public final LiveData<Uri> isAlbumTrack() {
        return this.isAlbumTrack;
    }

    public final LiveData<Uri> getNewPlaylistUri() {
        return this.newPlaylistUri;
    }

    public final void setAddToPlaylistManager(AddToPlaylistManager addToPlaylistManager) {
        this.addToPlaylistManager = addToPlaylistManager;
    }

    public final Uri getTracksUri() {
        return this.tracksUri;
    }

    public final void setTracksUri(Uri uri) {
        this.tracksUri = uri;
    }

    public final boolean getAlbumCollection() {
        return this.albumCollection;
    }

    public final void setAlbumCollection(boolean z) {
        this.albumCollection = z;
    }

    public final String getAlbumAsin() {
        return this.albumAsin;
    }

    public final void setAlbumAsin(String str) {
        this.albumAsin = str;
    }

    public final void checkIfNumberOfPlaylistsLimitReached(final Context context) {
        if (context != null) {
            Observable.fromCallable(new Callable() { // from class: com.amazon.mp3.fragment.contextmenu.PlaylistTransactionsViewModel$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return PlaylistTransactionsViewModel.m1140checkIfNumberOfPlaylistsLimitReached$lambda3(context);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Boolean>() { // from class: com.amazon.mp3.fragment.contextmenu.PlaylistTransactionsViewModel.checkIfNumberOfPlaylistsLimitReached.2
                @Override // rx.Observer
                public void onCompleted() {
                }

                @Override // rx.Observer
                public /* bridge */ /* synthetic */ void onNext(Boolean bool) {
                    onNext(bool.booleanValue());
                }

                @Override // rx.Observer
                public void onError(Throwable error) {
                    Log.error(PlaylistTransactionsViewModel.this.TAG, "Error checking the number of existing playlists", error);
                }

                public void onNext(boolean numberOfPlaylistsLimitReached) {
                    PlaylistTransactionsViewModel.this._canCreatePlaylist.setValue(Boolean.valueOf(!numberOfPlaylistsLimitReached));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: checkIfNumberOfPlaylistsLimitReached$lambda-3, reason: not valid java name */
    public static final Boolean m1140checkIfNumberOfPlaylistsLimitReached$lambda3(Context context) {
        return Boolean.valueOf(PlaylistUtil.maxNumberOfPlaylistsReached(context));
    }

    public final void createPlaylist(final String name, final String sourceId, final boolean addTracks) {
        Intrinsics.checkNotNullParameter(name, "name");
        Observable.fromCallable(new Callable() { // from class: com.amazon.mp3.fragment.contextmenu.PlaylistTransactionsViewModel$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return PlaylistTransactionsViewModel.m1141createPlaylist$lambda5(sourceId, name);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Uri>() { // from class: com.amazon.mp3.fragment.contextmenu.PlaylistTransactionsViewModel.createPlaylist.2
            @Override // rx.Observer
            public void onCompleted() {
            }

            @Override // rx.Observer
            public void onError(Throwable error) {
                Log.error(PlaylistTransactionsViewModel.this.TAG, "Error when creating playlist", error);
            }

            @Override // rx.Observer
            public void onNext(Uri playlistUri) {
                if (addTracks) {
                    Uri playlistTracksUri = MediaProvider.UdoPlaylistTracks.getContentUri(sourceId, MediaProvider.Playlists.getPlaylistId(playlistUri));
                    PlaylistTransactionsViewModel.this._newPlaylistUri.setValue(playlistTracksUri);
                    PlaylistTransactionsViewModel playlistTransactionsViewModel = PlaylistTransactionsViewModel.this;
                    Intrinsics.checkNotNullExpressionValue(playlistTracksUri, "playlistTracksUri");
                    playlistTransactionsViewModel.addToUserPlaylist(playlistTracksUri);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: createPlaylist$lambda-5, reason: not valid java name */
    public static final Uri m1141createPlaylist$lambda5(String str, String name) {
        Intrinsics.checkNotNullParameter(name, "$name");
        MediaProvider mediaProvider = MediaProvider.getInstance();
        Uri contentUri = MediaProvider.UdoPlaylists.getContentUri(str);
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        Unit unit = Unit.INSTANCE;
        return mediaProvider.insert(contentUri, contentValues);
    }

    public final boolean canEditOnlinePlaylist(Context context, Uri playlistUri) {
        Intrinsics.checkNotNullParameter(playlistUri, "playlistUri");
        return ConnectivityUtil.hasAnyInternetConnection(context) && Intrinsics.areEqual("cirrus", MediaProvider.getSource(playlistUri));
    }

    public final void addToUserPlaylist(Uri playlistUri) {
        Intrinsics.checkNotNullParameter(playlistUri, "playlistUri");
        if (MediaProvider.Albums.Tracks.isAlbumTracks(this.tracksUri) && this.albumCollection) {
            this._isAlbumTrack.setValue(playlistUri);
        } else {
            addTracksToPlaylist(playlistUri);
        }
    }

    public final Unit addTracksToPlaylist(Uri playlistUri) {
        Intrinsics.checkNotNullParameter(playlistUri, "playlistUri");
        AddToPlaylistManager addToPlaylistManager = this.addToPlaylistManager;
        if (addToPlaylistManager == null) {
            return null;
        }
        addToPlaylistManager.addToPlaylist(playlistUri, this.tracksUri);
        return Unit.INSTANCE;
    }
}
