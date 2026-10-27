package com.amazon.mp3.library.provider;

import android.content.Context;
import com.amazon.mp3.library.adapter.DefaultAlbumStateProvider;
import com.amazon.mp3.library.adapter.DefaultTrackStateProvider;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.music.find.model.search.SearchItem;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryStateProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\u001cB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\fJ\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0006J\u0006\u0010\u0015\u001a\u00020\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\b\u0010\u0013\u001a\u0004\u0018\u00010\fJ\u000e\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0013\u001a\u00020\fJ\u0016\u0010\u001a\u001a\u00020\u00182\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/amazon/mp3/library/provider/LibraryStateProvider;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mAlbumStateListener", "Lcom/amazon/mp3/library/provider/StateProvider$Listener;", "Lcom/amazon/mp3/prime/browse/metadata/PrimeAlbum;", "mAlbumStateProvider", "Lcom/amazon/mp3/library/adapter/DefaultAlbumStateProvider;", "mListener", "Lcom/amazon/mp3/library/provider/LibraryStateProvider$Listener;", "Lcom/amazon/music/find/model/search/SearchItem;", "mTrackStateListener", "Lcom/amazon/mp3/prime/browse/metadata/PrimeTrack;", "mTrackStateProvider", "Lcom/amazon/mp3/library/adapter/DefaultTrackStateProvider;", "contentIsNotInLibrary", "", "item", "getTrackStateListener", "getTrackStateProvider", "Lcom/amazon/mp3/library/provider/TrackStateProvider;", "requestState", "", "setAddingToLibrary", "setListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Listener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LibraryStateProvider {
    private final StateProvider.Listener<PrimeAlbum> mAlbumStateListener;
    private final DefaultAlbumStateProvider mAlbumStateProvider;
    private Listener<SearchItem> mListener;
    private final StateProvider.Listener<PrimeTrack> mTrackStateListener;
    private final DefaultTrackStateProvider mTrackStateProvider;

    /* JADX INFO: compiled from: LibraryStateProvider.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J%\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u00002\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H&¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/library/provider/LibraryStateProvider$Listener;", "U", "", "onStateChanged", "", "item", "ownershipState", "", "downloadState", "(Ljava/lang/Object;II)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface Listener<U> {
        void onStateChanged(U item, int ownershipState, int downloadState);
    }

    public LibraryStateProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.mTrackStateProvider = new DefaultTrackStateProvider(context);
        this.mTrackStateListener = new StateProvider.Listener() { // from class: com.amazon.mp3.library.provider.LibraryStateProvider$$ExternalSyntheticLambda0
            @Override // com.amazon.mp3.library.provider.StateProvider.Listener
            public final void onStateChanged(Object obj, int i, int i2) {
                LibraryStateProvider.m1338mTrackStateListener$lambda1(this.f$0, (PrimeTrack) obj, i, i2);
            }
        };
        this.mAlbumStateListener = new StateProvider.Listener() { // from class: com.amazon.mp3.library.provider.LibraryStateProvider$$ExternalSyntheticLambda1
            @Override // com.amazon.mp3.library.provider.StateProvider.Listener
            public final void onStateChanged(Object obj, int i, int i2) {
                LibraryStateProvider.m1337mAlbumStateListener$lambda3(this.f$0, (PrimeAlbum) obj, i, i2);
            }
        };
        this.mAlbumStateProvider = new DefaultAlbumStateProvider(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mTrackStateListener$lambda-1, reason: not valid java name */
    public static final void m1338mTrackStateListener$lambda1(LibraryStateProvider this$0, PrimeTrack item, int i, int i2) {
        Listener<SearchItem> listener;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (item == null || (listener = this$0.mListener) == null) {
            return;
        }
        Intrinsics.checkNotNullExpressionValue(item, "item");
        listener.onStateChanged(item, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mAlbumStateListener$lambda-3, reason: not valid java name */
    public static final void m1337mAlbumStateListener$lambda3(LibraryStateProvider this$0, PrimeAlbum item, int i, int i2) {
        Listener<SearchItem> listener;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (item == null || (listener = this$0.mListener) == null) {
            return;
        }
        Intrinsics.checkNotNullExpressionValue(item, "item");
        listener.onStateChanged(item, i, i2);
    }

    public final TrackStateProvider getTrackStateProvider() {
        return this.mTrackStateProvider;
    }

    public final StateProvider.Listener<PrimeTrack> getTrackStateListener() {
        return this.mTrackStateListener;
    }

    public final void setListener(Listener<SearchItem> listener) {
        this.mListener = listener;
    }

    public final void requestState(SearchItem item) {
        if (item instanceof PrimeTrack) {
            this.mTrackStateProvider.requestState((PrimeTrack) item, this.mTrackStateListener);
        } else if (item instanceof PrimeAlbum) {
            this.mAlbumStateProvider.requestState((PrimeAlbum) item, this.mAlbumStateListener);
        }
    }

    public final boolean contentIsNotInLibrary(SearchItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (!(item instanceof PrimeTrack)) {
            return false;
        }
        PrimeTrack primeTrack = (PrimeTrack) item;
        return (this.mTrackStateProvider.isInLibrary(primeTrack) || this.mTrackStateProvider.isAddingToLibrary(primeTrack)) ? false : true;
    }

    public final void setAddingToLibrary(SearchItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item instanceof PrimeTrack) {
            this.mTrackStateProvider.setAddingToLibrary((PrimeTrack) item, true);
        }
    }
}
