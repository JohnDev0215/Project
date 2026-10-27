package com.amazon.mp3.platform.providers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.service.sync.SyncJob;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.service.ClearCacheService;
import com.amazon.music.platform.data.Listener;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.visualon.OSMPUtils.voOSType;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: LibrarySyncStateProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u0001\u0017B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0014\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\bJ\u000e\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u001c\u0010\u0011\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0014\u0010\u0014\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\bJ\b\u0010\u0015\u001a\u00020\u000bH\u0007J\b\u0010\u0016\u001a\u00020\u000bH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/platform/providers/LibrarySyncStateProvider;", "Landroid/content/BroadcastReceiver;", "Landroidx/lifecycle/LifecycleObserver;", "()V", "libraryStateFilter", "Landroid/content/IntentFilter;", "listenerSet", "", "Lcom/amazon/music/platform/data/Listener;", "Lcom/amazon/mp3/platform/providers/LibrarySyncStateProvider$LibrarySyncState;", "addLibraryStateListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "getLibraryState", "context", "Landroid/content/Context;", "notifyListeners", "onReceive", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "removeLibraryStateListener", "startListening", "stopListening", "LibrarySyncState", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LibrarySyncStateProvider extends BroadcastReceiver implements LifecycleObserver {
    public static final LibrarySyncStateProvider INSTANCE;
    private static final IntentFilter libraryStateFilter;
    private static final Set<Listener<LibrarySyncState>> listenerSet;

    private LibrarySyncStateProvider() {
    }

    /* JADX INFO: compiled from: LibrarySyncStateProvider.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0006\u0010\u0003\u001a\u00020\u0002J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0007\u001a\u00020\u0006HÖ\u0001J\u0013\u0010\t\u001a\u00020\u00022\b\u0010\b\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000e\u0010\rR\u0017\u0010\u000f\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u000b\u001a\u0004\b\u000f\u0010\r¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/platform/providers/LibrarySyncStateProvider$LibrarySyncState;", "", "", "isRunningInitialSync", "", "toString", "", "hashCode", "other", "equals", "hasCompletedInitialSync", "Z", "getHasCompletedInitialSync", "()Z", "isSyncing", "isClearingCache", "<init>", "(ZZZ)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class LibrarySyncState {
        private final boolean hasCompletedInitialSync;
        private final boolean isClearingCache;
        private final boolean isSyncing;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LibrarySyncState)) {
                return false;
            }
            LibrarySyncState librarySyncState = (LibrarySyncState) other;
            return this.hasCompletedInitialSync == librarySyncState.hasCompletedInitialSync && this.isSyncing == librarySyncState.isSyncing && this.isClearingCache == librarySyncState.isClearingCache;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v6 */
        /* JADX WARN: Type inference failed for: r0v7 */
        /* JADX WARN: Type inference failed for: r1v0 */
        /* JADX WARN: Type inference failed for: r1v1, types: [int] */
        /* JADX WARN: Type inference failed for: r1v2 */
        /* JADX WARN: Type inference failed for: r2v1, types: [int] */
        /* JADX WARN: Type inference failed for: r2v3 */
        /* JADX WARN: Type inference failed for: r2v4 */
        public int hashCode() {
            boolean z = this.hasCompletedInitialSync;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            boolean z2 = this.isSyncing;
            ?? r2 = z2;
            if (z2) {
                r2 = 1;
            }
            int i2 = (i + r2) * 31;
            boolean z3 = this.isClearingCache;
            return i2 + (z3 ? 1 : z3);
        }

        public String toString() {
            return "LibrarySyncState(hasCompletedInitialSync=" + this.hasCompletedInitialSync + ", isSyncing=" + this.isSyncing + ", isClearingCache=" + this.isClearingCache + ')';
        }

        public LibrarySyncState(boolean z, boolean z2, boolean z3) {
            this.hasCompletedInitialSync = z;
            this.isSyncing = z2;
            this.isClearingCache = z3;
        }

        /* JADX INFO: renamed from: isSyncing, reason: from getter */
        public final boolean getIsSyncing() {
            return this.isSyncing;
        }

        /* JADX INFO: renamed from: isClearingCache, reason: from getter */
        public final boolean getIsClearingCache() {
            return this.isClearingCache;
        }

        public final boolean isRunningInitialSync() {
            return !this.hasCompletedInitialSync && this.isSyncing;
        }
    }

    static {
        LibrarySyncStateProvider librarySyncStateProvider = new LibrarySyncStateProvider();
        INSTANCE = librarySyncStateProvider;
        listenerSet = new LinkedHashSet();
        IntentFilter intentFilter = new IntentFilter();
        libraryStateFilter = intentFilter;
        intentFilter.addAction("com.amazon.mp3.library.service.sync.ACTION_SYNC_SERVICE_STARTED");
        intentFilter.addAction("com.amazon.mp3.library.service.sync.ACTION_SYNC_SERVICE_STOPPED");
        intentFilter.addAction("com.amazon.mp3.library.service.sync.ACTION_LIBRARY_SYNC_STOPPED");
        intentFilter.addAction("com.amazon.mp3.ACTION_CACHE_CLEARING");
        intentFilter.addAction("com.amazon.mp3.ACTION_CACHE_CLEARED");
        intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
        ProcessLifecycleOwner.get().getLifecycle().addObserver(librarySyncStateProvider);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public final void startListening() {
        AmazonApplication.getApplication().registerReceiver(this, libraryStateFilter);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public final void stopListening() {
        AmazonApplication.getApplication().unregisterReceiver(this);
    }

    public final synchronized void addLibraryStateListener(Listener<LibrarySyncState> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        listenerSet.add(listener);
    }

    public final synchronized void removeLibraryStateListener(Listener<LibrarySyncState> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        listenerSet.remove(listener);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (context == null) {
            return;
        }
        String action = intent == null ? null : intent.getAction();
        if (action != null) {
            switch (action.hashCode()) {
                case -1638296896:
                    if (!action.equals("com.amazon.mp3.ACTION_CACHE_CLEARING")) {
                    }
                    INSTANCE.notifyListeners(context);
                    break;
                case -1514214344:
                    if (!action.equals("android.intent.action.MEDIA_MOUNTED")) {
                    }
                    SyncService.startSync(context, voOSType.VOOSMP_PID_ANALYTICS_AGENT);
                    break;
                case -963871873:
                    if (!action.equals("android.intent.action.MEDIA_UNMOUNTED")) {
                    }
                    SyncService.startSync(context, voOSType.VOOSMP_PID_ANALYTICS_AGENT);
                    break;
                case 224246241:
                    if (!action.equals("com.amazon.mp3.ACTION_CACHE_CLEARED")) {
                    }
                    INSTANCE.notifyListeners(context);
                    break;
                case 453623978:
                    if (!action.equals("com.amazon.mp3.library.service.sync.ACTION_LIBRARY_SYNC_STOPPED")) {
                    }
                    INSTANCE.notifyListeners(context);
                    break;
                case 1079909008:
                    if (!action.equals("com.amazon.mp3.library.service.sync.ACTION_SYNC_SERVICE_STARTED")) {
                    }
                    INSTANCE.notifyListeners(context);
                    break;
                case 1092774876:
                    if (!action.equals("com.amazon.mp3.library.service.sync.ACTION_SYNC_SERVICE_STOPPED")) {
                    }
                    INSTANCE.notifyListeners(context);
                    break;
            }
        }
    }

    public final LibrarySyncState getLibraryState(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new LibrarySyncState(!SyncJob.isInitialSync(context), SyncService.isRunning(), ClearCacheService.isRunning());
    }

    /* JADX INFO: renamed from: com.amazon.mp3.platform.providers.LibrarySyncStateProvider$notifyListeners$1, reason: invalid class name */
    /* JADX INFO: compiled from: LibrarySyncStateProvider.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.platform.providers.LibrarySyncStateProvider$notifyListeners$1", f = "LibrarySyncStateProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Context context, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LibrarySyncStateProvider.this.new AnonymousClass1(this.$context, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                LibrarySyncStateProvider librarySyncStateProvider = LibrarySyncStateProvider.this;
                Context context = this.$context;
                synchronized (librarySyncStateProvider) {
                    LibrarySyncState libraryState = LibrarySyncStateProvider.INSTANCE.getLibraryState(context);
                    Iterator it = LibrarySyncStateProvider.listenerSet.iterator();
                    while (it.hasNext()) {
                        ((Listener) it.next()).onEvent(libraryState);
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    private final void notifyListeners(Context context) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(context, null), 3, null);
    }
}
