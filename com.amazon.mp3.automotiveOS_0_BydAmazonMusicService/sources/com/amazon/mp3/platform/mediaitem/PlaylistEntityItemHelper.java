package com.amazon.mp3.platform.mediaitem;

import android.content.Context;
import com.amazon.mp3.service.job.JobQueue;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaylistEntityItemHelper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nJ-\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/platform/mediaitem/PlaylistEntityItemHelper;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "getContentUri", "Landroid/net/Uri;", "identifier", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "import", "Lcom/amazon/mp3/playlist/CatalogPlaylist;", "addToLibrary", "", "jobQueue", "Lcom/amazon/mp3/service/job/JobQueue;", "(Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;ZLcom/amazon/mp3/service/job/JobQueue;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistEntityItemHelper {
    private static final String TAG = PlaylistEntityItemHelper.class.getSimpleName();
    private final Context context;

    /* JADX INFO: renamed from: com.amazon.mp3.platform.mediaitem.PlaylistEntityItemHelper$import$1, reason: invalid class name */
    /* JADX INFO: compiled from: PlaylistEntityItemHelper.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.platform.mediaitem.PlaylistEntityItemHelper", f = "PlaylistEntityItemHelper.kt", i = {0}, l = {109}, m = "import", n = {"this_$iv"}, s = {"L$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PlaylistEntityItemHelper.this.m1359import(null, false, null, this);
        }
    }

    public PlaylistEntityItemHelper(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public final Context getContext() {
        return this.context;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0063  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.net.Uri getContentUri(com.amazon.music.platform.data.entity.EntityItemIdentifier r6) {
        /*
            r5 = this;
            java.lang.String r0 = "identifier"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            com.amazon.music.platform.data.entity.EntityItemType r0 = r6.getType()
            com.amazon.music.platform.data.entity.CollectionType$Playlist r1 = com.amazon.music.platform.data.entity.CollectionType.Playlist.INSTANCE
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            r1 = 0
            if (r0 != 0) goto L13
            return r1
        L13:
            com.amazon.music.platform.data.entity.EntityId r0 = r6.getId()
            java.lang.String r0 = r0.getIdentifierType()
            com.amazon.music.platform.data.entity.EntityId r6 = r6.getId()
            java.lang.String r6 = r6.getIdentifier()
            int r2 = r0.hashCode()
            r3 = 2018519(0x1eccd7, float:2.828548E-39)
            java.lang.String r4 = "cirrus"
            if (r2 == r3) goto L5b
            r3 = 93508264(0x592d2a8, float:1.3807157E-35)
            if (r2 == r3) goto L4d
            r3 = 785431604(0x2ed0bc34, float:9.4921765E-11)
            if (r2 == r3) goto L39
            goto L63
        L39:
            java.lang.String r2 = "USER_PLAYLIST_ID"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L42
            goto L63
        L42:
            long r2 = java.lang.Long.parseLong(r6)
            java.lang.String r6 = "cirrus-local"
            android.net.Uri r6 = com.amazon.mp3.library.provider.MediaProvider.UdoPlaylists.getContentUri(r6, r2)
            goto L69
        L4d:
            java.lang.String r2 = "PLAYLIST_ID"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L56
            goto L63
        L56:
            android.net.Uri r6 = com.amazon.mp3.library.provider.MediaProvider.SharedUserPlaylists.getContentUri(r4, r6)
            goto L69
        L5b:
            java.lang.String r2 = "ASIN"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L65
        L63:
            r6 = r1
            goto L69
        L65:
            android.net.Uri r6 = com.amazon.mp3.library.provider.MediaProvider.PrimePlaylists.getContentUri(r4, r6)
        L69:
            if (r6 == 0) goto L76
            com.amazon.mp3.library.item.factory.LibraryItemFactory r0 = com.amazon.mp3.AmazonApplication.getLibraryItemFactory()
            com.amazon.mp3.library.item.Playlist r0 = r0.getPlaylist(r6)
            if (r0 == 0) goto L76
            return r6
        L76:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.platform.mediaitem.PlaylistEntityItemHelper.getContentUri(com.amazon.music.platform.data.entity.EntityItemIdentifier):android.net.Uri");
    }

    public static /* synthetic */ Object import$default(PlaylistEntityItemHelper playlistEntityItemHelper, EntityItemIdentifier entityItemIdentifier, boolean z, JobQueue jobQueue, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            jobQueue = new JobQueue(playlistEntityItemHelper.getContext());
        }
        return playlistEntityItemHelper.m1359import(entityItemIdentifier, z, jobQueue, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX INFO: renamed from: import, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object m1359import(com.amazon.music.platform.data.entity.EntityItemIdentifier r19, boolean r20, com.amazon.mp3.service.job.JobQueue r21, kotlin.coroutines.Continuation<? super com.amazon.mp3.playlist.CatalogPlaylist> r22) {
        /*
            r18 = this;
            r0 = r22
            boolean r1 = r0 instanceof com.amazon.mp3.platform.mediaitem.PlaylistEntityItemHelper.AnonymousClass1
            if (r1 == 0) goto L17
            r1 = r0
            com.amazon.mp3.platform.mediaitem.PlaylistEntityItemHelper$import$1 r1 = (com.amazon.mp3.platform.mediaitem.PlaylistEntityItemHelper.AnonymousClass1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L17
            int r2 = r2 - r3
            r1.label = r2
            r8 = r18
            goto L1e
        L17:
            com.amazon.mp3.platform.mediaitem.PlaylistEntityItemHelper$import$1 r1 = new com.amazon.mp3.platform.mediaitem.PlaylistEntityItemHelper$import$1
            r8 = r18
            r1.<init>(r0)
        L1e:
            java.lang.Object r0 = r1.result
            java.lang.Object r9 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r1.label
            r10 = 1
            if (r2 == 0) goto L3d
            if (r2 != r10) goto L35
            java.lang.Object r1 = r1.L$0
            com.amazon.mp3.platform.providers.Coroutines r1 = (com.amazon.mp3.platform.providers.Coroutines) r1
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L33
            goto L85
        L33:
            r0 = move-exception
            goto L64
        L35:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L3d:
            kotlin.ResultKt.throwOnFailure(r0)
            com.amazon.mp3.platform.providers.Coroutines r11 = com.amazon.mp3.platform.providers.Coroutines.INSTANCE
            r12 = 15000(0x3a98, double:7.411E-320)
            com.amazon.mp3.platform.mediaitem.PlaylistEntityItemHelper$import$$inlined$suspendCoroutineWithTimeout$default$1 r0 = new com.amazon.mp3.platform.mediaitem.PlaylistEntityItemHelper$import$$inlined$suspendCoroutineWithTimeout$default$1     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L62
            r3 = 0
            if (r20 == 0) goto L4b
            r7 = r10
            goto L4d
        L4b:
            r2 = 0
            r7 = r2
        L4d:
            r2 = r0
            r4 = r19
            r5 = r18
            r6 = r21
            r2.<init>(r3, r4, r5, r6, r7)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L62
            r1.L$0 = r11     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L62
            r1.label = r10     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L62
            java.lang.Object r0 = kotlinx.coroutines.TimeoutKt.withTimeout(r12, r0, r1)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L62
            if (r0 != r9) goto L85
            return r9
        L62:
            r0 = move-exception
            r1 = r11
        L64:
            r2 = 0
            java.lang.String r1 = r1.getTAG()
            java.lang.StackTraceElement[] r9 = r0.getStackTrace()
            java.lang.String r0 = "e.stackTrace"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r0)
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 62
            r17 = 0
            java.lang.String r10 = "\n"
            java.lang.String r0 = kotlin.collections.ArraysKt.joinToString$default(r9, r10, r11, r12, r13, r14, r15, r16, r17)
            com.amazon.mp3.util.Log.error(r1, r0)
            r0 = r2
        L85:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.platform.mediaitem.PlaylistEntityItemHelper.m1359import(com.amazon.music.platform.data.entity.EntityItemIdentifier, boolean, com.amazon.mp3.service.job.JobQueue, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
