package com.amazon.mp3.fragment.viewmodel;

import android.content.Context;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.platform.providers.LibrarySyncStateProvider;
import com.amazon.music.platform.data.Listener;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/* JADX INFO: compiled from: LibrarySyncStateUpdater.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 \u00132\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0013\u0014B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\fJ\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\fJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016J\u0006\u0010\u0011\u001a\u00020\u000fJ\u0006\u0010\u0012\u001a\u00020\u000fR2\u0010\u0006\u001a&\u0012\f\u0012\n \t*\u0004\u0018\u00010\b0\b \t*\u0012\u0012\f\u0012\n \t*\u0004\u0018\u00010\b0\b\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R2\u0010\n\u001a&\u0012\f\u0012\n \t*\u0004\u0018\u00010\u00020\u0002 \t*\u0012\u0012\f\u0012\n \t*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/fragment/viewmodel/LibrarySyncStateUpdater;", "Lcom/amazon/music/platform/data/Listener;", "Lcom/amazon/mp3/platform/providers/LibrarySyncStateProvider$LibrarySyncState;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "libraryEventSubject", "Lrx/subjects/BehaviorSubject;", "Lcom/amazon/mp3/fragment/viewmodel/LibrarySyncStateUpdater$LibrarySyncEvent;", "kotlin.jvm.PlatformType", "libraryStateSubject", "getLibraryEventObserver", "Lrx/Observable;", "getLibraryStateObserver", "onEvent", "", "updatedLibraryState", "startObservingLibraryStateEvents", "stopObservingLibraryStateEvents", "Companion", "LibrarySyncEvent", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LibrarySyncStateUpdater implements Listener<LibrarySyncStateProvider.LibrarySyncState> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final BehaviorSubject<LibrarySyncEvent> libraryEventSubject;
    private final BehaviorSubject<LibrarySyncStateProvider.LibrarySyncState> libraryStateSubject;

    /* JADX INFO: compiled from: LibrarySyncStateUpdater.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/fragment/viewmodel/LibrarySyncStateUpdater$LibrarySyncEvent;", "", "(Ljava/lang/String;I)V", "INITIAL_LIBRARY_SYNC_COMPLETED", "SYNC_COMPLETED", "CLEAR_CACHE_COMPLETED", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum LibrarySyncEvent {
        INITIAL_LIBRARY_SYNC_COMPLETED,
        SYNC_COMPLETED,
        CLEAR_CACHE_COMPLETED
    }

    public LibrarySyncStateUpdater(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.libraryStateSubject = BehaviorSubject.create();
        this.libraryEventSubject = BehaviorSubject.create();
        onEvent(LibrarySyncStateProvider.INSTANCE.getLibraryState(context));
        SyncService.startTimeCheckedSync(context);
    }

    /* JADX INFO: compiled from: LibrarySyncStateUpdater.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u001a\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u0018\u0010\n\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u0018\u0010\u000b\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u0006¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/fragment/viewmodel/LibrarySyncStateUpdater$Companion;", "", "()V", "clearCacheCompleted", "", "previousLibraryState", "Lcom/amazon/mp3/platform/providers/LibrarySyncStateProvider$LibrarySyncState;", "currentLibraryState", "getLibrarySyncEvent", "Lcom/amazon/mp3/fragment/viewmodel/LibrarySyncStateUpdater$LibrarySyncEvent;", "initialLibrarySyncCompleted", "syncCompleted", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean initialLibrarySyncCompleted(LibrarySyncStateProvider.LibrarySyncState previousLibraryState, LibrarySyncStateProvider.LibrarySyncState currentLibraryState) {
            Intrinsics.checkNotNullParameter(currentLibraryState, "currentLibraryState");
            if (!currentLibraryState.isRunningInitialSync()) {
                if (previousLibraryState != null && previousLibraryState.isRunningInitialSync()) {
                    return true;
                }
            }
            return false;
        }

        public final boolean syncCompleted(LibrarySyncStateProvider.LibrarySyncState previousLibraryState, LibrarySyncStateProvider.LibrarySyncState currentLibraryState) {
            Intrinsics.checkNotNullParameter(currentLibraryState, "currentLibraryState");
            if (!currentLibraryState.getIsSyncing()) {
                if (previousLibraryState != null && previousLibraryState.getIsSyncing()) {
                    return true;
                }
            }
            return false;
        }

        public final boolean clearCacheCompleted(LibrarySyncStateProvider.LibrarySyncState previousLibraryState, LibrarySyncStateProvider.LibrarySyncState currentLibraryState) {
            Intrinsics.checkNotNullParameter(currentLibraryState, "currentLibraryState");
            if (!currentLibraryState.getIsClearingCache()) {
                if (previousLibraryState != null && previousLibraryState.getIsClearingCache()) {
                    return true;
                }
            }
            return false;
        }

        public final LibrarySyncEvent getLibrarySyncEvent(LibrarySyncStateProvider.LibrarySyncState previousLibraryState, LibrarySyncStateProvider.LibrarySyncState currentLibraryState) {
            Intrinsics.checkNotNullParameter(currentLibraryState, "currentLibraryState");
            if (initialLibrarySyncCompleted(previousLibraryState, currentLibraryState)) {
                return LibrarySyncEvent.INITIAL_LIBRARY_SYNC_COMPLETED;
            }
            if (syncCompleted(previousLibraryState, currentLibraryState)) {
                return LibrarySyncEvent.SYNC_COMPLETED;
            }
            if (clearCacheCompleted(previousLibraryState, currentLibraryState)) {
                return LibrarySyncEvent.CLEAR_CACHE_COMPLETED;
            }
            return null;
        }
    }

    public final void startObservingLibraryStateEvents() {
        LibrarySyncStateProvider.INSTANCE.addLibraryStateListener(this);
    }

    public final void stopObservingLibraryStateEvents() {
        LibrarySyncStateProvider.INSTANCE.removeLibraryStateListener(this);
    }

    public final Observable<LibrarySyncStateProvider.LibrarySyncState> getLibraryStateObserver() {
        Observable<LibrarySyncStateProvider.LibrarySyncState> observableSubscribeOn = this.libraryStateSubject.observeOn(Schedulers.io()).subscribeOn(Schedulers.io());
        Intrinsics.checkNotNullExpressionValue(observableSubscribeOn, "libraryStateSubject.obse…hedulers.Schedulers.io())");
        return observableSubscribeOn;
    }

    public final Observable<LibrarySyncEvent> getLibraryEventObserver() {
        Observable<LibrarySyncEvent> observableSubscribeOn = this.libraryEventSubject.observeOn(Schedulers.io()).subscribeOn(Schedulers.io());
        Intrinsics.checkNotNullExpressionValue(observableSubscribeOn, "libraryEventSubject.obse…hedulers.Schedulers.io())");
        return observableSubscribeOn;
    }

    @Override // com.amazon.music.platform.data.Listener
    public void onEvent(LibrarySyncStateProvider.LibrarySyncState updatedLibraryState) {
        Intrinsics.checkNotNullParameter(updatedLibraryState, "updatedLibraryState");
        LibrarySyncStateProvider.LibrarySyncState value = this.libraryStateSubject.getValue();
        this.libraryStateSubject.onNext(updatedLibraryState);
        LibrarySyncEvent librarySyncEvent = INSTANCE.getLibrarySyncEvent(value, updatedLibraryState);
        if (librarySyncEvent == null) {
            return;
        }
        this.libraryEventSubject.onNext(librarySyncEvent);
    }
}
