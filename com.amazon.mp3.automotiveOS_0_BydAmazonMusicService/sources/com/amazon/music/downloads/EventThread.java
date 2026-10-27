package com.amazon.music.downloads;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EventThread.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u001b\u0010\u001cJ\u000e\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0001J\u000e\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0001J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u0018\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH\u0016J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u0018\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\u0018\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0006H\u0016R\u001c\u0010\u0016\u001a\n \u0015*\u0004\u0018\u00010\u00140\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00010\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001a¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/downloads/EventThread;", "Lcom/amazon/music/downloads/DownloadEventListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "", "registerDownloadEventListener", "unregisterDownloadEventListener", "", "id", "onAdded", "", "percent", "onProgress", "onPaused", "onCanceled", "onResumed", "Lcom/amazon/music/downloads/ErrorReason;", "reason", "onError", "fileName", "onDownloaded", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "mMessengerThreadService", "Ljava/util/concurrent/ExecutorService;", "Ljava/util/concurrent/CopyOnWriteArrayList;", "mListenerList", "Ljava/util/concurrent/CopyOnWriteArrayList;", "<init>", "()V", "DMMDownloadManager_release"}, k = 1, mv = {1, 6, 0})
public final class EventThread implements DownloadEventListener {
    private final ExecutorService mMessengerThreadService = Executors.newSingleThreadExecutor();
    private final CopyOnWriteArrayList<DownloadEventListener> mListenerList = new CopyOnWriteArrayList<>();

    public final void registerDownloadEventListener(DownloadEventListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.mListenerList.add(listener);
    }

    public final void unregisterDownloadEventListener(DownloadEventListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.mListenerList.remove(listener);
    }

    @Override // com.amazon.music.downloads.DownloadEventListener
    public void onAdded(final String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        if (this.mListenerList.isEmpty()) {
            return;
        }
        this.mMessengerThreadService.execute(new Runnable() { // from class: com.amazon.music.downloads.EventThread$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                EventThread.m1991onAdded$lambda0(this.f$0, id);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onAdded$lambda-0, reason: not valid java name */
    public static final void m1991onAdded$lambda0(EventThread this$0, String id) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id, "$id");
        Iterator<DownloadEventListener> it = this$0.mListenerList.iterator();
        while (it.hasNext()) {
            it.next().onAdded(id);
        }
    }

    @Override // com.amazon.music.downloads.DownloadEventListener
    public void onProgress(final String id, final float percent) {
        Intrinsics.checkNotNullParameter(id, "id");
        if (this.mListenerList.isEmpty()) {
            return;
        }
        this.mMessengerThreadService.execute(new Runnable() { // from class: com.amazon.music.downloads.EventThread$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                EventThread.m1996onProgress$lambda1(this.f$0, id, percent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onProgress$lambda-1, reason: not valid java name */
    public static final void m1996onProgress$lambda1(EventThread this$0, String id, float f) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id, "$id");
        Iterator<DownloadEventListener> it = this$0.mListenerList.iterator();
        while (it.hasNext()) {
            it.next().onProgress(id, f);
        }
    }

    @Override // com.amazon.music.downloads.DownloadEventListener
    public void onPaused(final String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        if (this.mListenerList.isEmpty()) {
            return;
        }
        this.mMessengerThreadService.execute(new Runnable() { // from class: com.amazon.music.downloads.EventThread$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                EventThread.m1995onPaused$lambda2(this.f$0, id);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onPaused$lambda-2, reason: not valid java name */
    public static final void m1995onPaused$lambda2(EventThread this$0, String id) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id, "$id");
        Iterator<DownloadEventListener> it = this$0.mListenerList.iterator();
        while (it.hasNext()) {
            it.next().onPaused(id);
        }
    }

    @Override // com.amazon.music.downloads.DownloadEventListener
    public void onCanceled(final String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        if (this.mListenerList.isEmpty()) {
            return;
        }
        this.mMessengerThreadService.execute(new Runnable() { // from class: com.amazon.music.downloads.EventThread$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                EventThread.m1992onCanceled$lambda3(this.f$0, id);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCanceled$lambda-3, reason: not valid java name */
    public static final void m1992onCanceled$lambda3(EventThread this$0, String id) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id, "$id");
        Iterator<DownloadEventListener> it = this$0.mListenerList.iterator();
        while (it.hasNext()) {
            it.next().onCanceled(id);
        }
    }

    @Override // com.amazon.music.downloads.DownloadEventListener
    public void onResumed(final String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        if (this.mListenerList.isEmpty()) {
            return;
        }
        this.mMessengerThreadService.execute(new Runnable() { // from class: com.amazon.music.downloads.EventThread$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                EventThread.m1997onResumed$lambda4(this.f$0, id);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onResumed$lambda-4, reason: not valid java name */
    public static final void m1997onResumed$lambda4(EventThread this$0, String id) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id, "$id");
        Iterator<DownloadEventListener> it = this$0.mListenerList.iterator();
        while (it.hasNext()) {
            it.next().onResumed(id);
        }
    }

    @Override // com.amazon.music.downloads.DownloadEventListener
    public void onError(final String id, final ErrorReason reason) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(reason, "reason");
        if (this.mListenerList.isEmpty()) {
            return;
        }
        this.mMessengerThreadService.execute(new Runnable() { // from class: com.amazon.music.downloads.EventThread$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                EventThread.m1994onError$lambda5(this.f$0, id, reason);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onError$lambda-5, reason: not valid java name */
    public static final void m1994onError$lambda5(EventThread this$0, String id, ErrorReason reason) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id, "$id");
        Intrinsics.checkNotNullParameter(reason, "$reason");
        Iterator<DownloadEventListener> it = this$0.mListenerList.iterator();
        while (it.hasNext()) {
            it.next().onError(id, reason);
        }
    }

    @Override // com.amazon.music.downloads.DownloadEventListener
    public void onDownloaded(final String id, final String fileName) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        if (this.mListenerList.isEmpty()) {
            return;
        }
        this.mMessengerThreadService.execute(new Runnable() { // from class: com.amazon.music.downloads.EventThread$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                EventThread.m1993onDownloaded$lambda7(this.f$0, id, fileName);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onDownloaded$lambda-7, reason: not valid java name */
    public static final void m1993onDownloaded$lambda7(EventThread this$0, String id, String fileName) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id, "$id");
        Intrinsics.checkNotNullParameter(fileName, "$fileName");
        Iterator<DownloadEventListener> it = this$0.mListenerList.iterator();
        while (it.hasNext()) {
            it.next().onDownloaded(id, fileName);
        }
    }
}
