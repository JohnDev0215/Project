package com.amazon.mp3.catalog.fragment.datasource.helper;

import com.amazon.mp3.catalog.fragment.datasource.DBQueryUtil;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Playlist;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CommunityPlaylistActions.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\fJ\u0012\u0010\r\u001a\u0004\u0018\u00010\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/datasource/helper/CommunityPlaylistActions;", "", "playlistFireflyProvider", "Lcom/amazon/mp3/catalog/fragment/datasource/helper/PlaylistFireflyProvider;", "(Lcom/amazon/mp3/catalog/fragment/datasource/helper/PlaylistFireflyProvider;)V", "TAG", "", "kotlin.jvm.PlatformType", "fetchFollowerCount", "", Environment.PLAYLIST_PATH, "Lcom/amazon/mp3/library/item/AbstractItem;", "(Lcom/amazon/mp3/library/item/AbstractItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSharedId", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CommunityPlaylistActions {
    private final String TAG;
    private final PlaylistFireflyProvider playlistFireflyProvider;

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.datasource.helper.CommunityPlaylistActions$fetchFollowerCount$1, reason: invalid class name */
    /* JADX INFO: compiled from: CommunityPlaylistActions.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.datasource.helper.CommunityPlaylistActions", f = "CommunityPlaylistActions.kt", i = {0, 0}, l = {32}, m = "fetchFollowerCount", n = {"this", Environment.PLAYLIST_PATH}, s = {"L$0", "L$1"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CommunityPlaylistActions.this.fetchFollowerCount(null, this);
        }
    }

    public CommunityPlaylistActions(PlaylistFireflyProvider playlistFireflyProvider) {
        Intrinsics.checkNotNullParameter(playlistFireflyProvider, "playlistFireflyProvider");
        this.playlistFireflyProvider = playlistFireflyProvider;
        this.TAG = CommunityPlaylistActions.class.getSimpleName();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object fetchFollowerCount(com.amazon.mp3.library.item.AbstractItem r5, kotlin.coroutines.Continuation<? super java.lang.Integer> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.amazon.mp3.catalog.fragment.datasource.helper.CommunityPlaylistActions.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            com.amazon.mp3.catalog.fragment.datasource.helper.CommunityPlaylistActions$fetchFollowerCount$1 r0 = (com.amazon.mp3.catalog.fragment.datasource.helper.CommunityPlaylistActions.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.catalog.fragment.datasource.helper.CommunityPlaylistActions$fetchFollowerCount$1 r0 = new com.amazon.mp3.catalog.fragment.datasource.helper.CommunityPlaylistActions$fetchFollowerCount$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            java.lang.Object r5 = r0.L$1
            com.amazon.mp3.library.item.AbstractItem r5 = (com.amazon.mp3.library.item.AbstractItem) r5
            java.lang.Object r0 = r0.L$0
            com.amazon.mp3.catalog.fragment.datasource.helper.CommunityPlaylistActions r0 = (com.amazon.mp3.catalog.fragment.datasource.helper.CommunityPlaylistActions) r0
            kotlin.ResultKt.throwOnFailure(r6)     // Catch: java.lang.Exception -> L31
            goto L57
        L31:
            r6 = move-exception
            goto L6f
        L33:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3b:
            kotlin.ResultKt.throwOnFailure(r6)
            java.lang.String r6 = r4.getSharedId(r5)     // Catch: java.lang.Exception -> L6d
            if (r6 != 0) goto L47
            r6 = 0
            r0 = r4
            goto L59
        L47:
            com.amazon.mp3.catalog.fragment.datasource.helper.PlaylistFireflyProvider r2 = r4.playlistFireflyProvider     // Catch: java.lang.Exception -> L6d
            r0.L$0 = r4     // Catch: java.lang.Exception -> L6d
            r0.L$1 = r5     // Catch: java.lang.Exception -> L6d
            r0.label = r3     // Catch: java.lang.Exception -> L6d
            java.lang.Object r6 = r2.getFollowerCount(r6, r0)     // Catch: java.lang.Exception -> L6d
            if (r6 != r1) goto L56
            return r1
        L56:
            r0 = r4
        L57:
            com.amazon.music.platform.experiences.playlists.PlaylistFollowerCount r6 = (com.amazon.music.platform.experiences.playlists.PlaylistFollowerCount) r6     // Catch: java.lang.Exception -> L31
        L59:
            if (r6 != 0) goto L5c
            goto L96
        L5c:
            java.lang.Double r6 = r6.getFollowerCount()     // Catch: java.lang.Exception -> L31
            if (r6 != 0) goto L63
            goto L96
        L63:
            double r1 = r6.doubleValue()     // Catch: java.lang.Exception -> L31
            int r6 = (int) r1     // Catch: java.lang.Exception -> L31
            java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r6)     // Catch: java.lang.Exception -> L31
            return r5
        L6d:
            r6 = move-exception
            r0 = r4
        L6f:
            java.lang.String r0 = r0.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "unable to retrieve follower count for playlist "
            r1.append(r2)
            java.lang.String r5 = r5.getAsin()
            r1.append(r5)
            java.lang.String r5 = " due to error "
            r1.append(r5)
            java.lang.String r5 = r6.getMessage()
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            com.amazon.mp3.util.Log.error(r0, r5)
        L96:
            r5 = -1
            java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.datasource.helper.CommunityPlaylistActions.fetchFollowerCount(com.amazon.mp3.library.item.AbstractItem, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final String getSharedId(AbstractItem playlist) {
        String sharedId = DBQueryUtil.INSTANCE.getSharedId(playlist);
        return sharedId == null ? playlist instanceof Playlist ? ((Playlist) playlist).getLuid() : playlist.getAsin() : sharedId;
    }
}
