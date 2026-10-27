package com.amazon.mp3.catalog.fragment.datasource.helper;

import com.amazon.music.platform.experiences.playlists.PlaylistFireFlyService;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: PlaylistFireflyProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/datasource/helper/PlaylistFireflyProvider;", "", "()V", "playlistFireflyService", "Lcom/amazon/music/platform/experiences/playlists/PlaylistFireFlyService;", "getFollowerCount", "Lcom/amazon/music/platform/experiences/playlists/PlaylistFollowerCount;", "playlistId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistFireflyProvider {
    private final PlaylistFireFlyService playlistFireflyService = new PlaylistFireFlyService(null, null, 3, null);

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.datasource.helper.PlaylistFireflyProvider$getFollowerCount$1, reason: invalid class name */
    /* JADX INFO: compiled from: PlaylistFireflyProvider.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.datasource.helper.PlaylistFireflyProvider", f = "PlaylistFireflyProvider.kt", i = {}, l = {17}, m = "getFollowerCount", n = {}, s = {})
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PlaylistFireflyProvider.this.getFollowerCount(null, this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getFollowerCount(java.lang.String r5, kotlin.coroutines.Continuation<? super com.amazon.music.platform.experiences.playlists.PlaylistFollowerCount> r6) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.amazon.mp3.catalog.fragment.datasource.helper.PlaylistFireflyProvider.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            com.amazon.mp3.catalog.fragment.datasource.helper.PlaylistFireflyProvider$getFollowerCount$1 r0 = (com.amazon.mp3.catalog.fragment.datasource.helper.PlaylistFireflyProvider.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.catalog.fragment.datasource.helper.PlaylistFireflyProvider$getFollowerCount$1 r0 = new com.amazon.mp3.catalog.fragment.datasource.helper.PlaylistFireflyProvider$getFollowerCount$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r6)
            goto L3f
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            kotlin.ResultKt.throwOnFailure(r6)
            com.amazon.music.platform.experiences.playlists.PlaylistFireFlyService r6 = r4.playlistFireflyService
            r0.label = r3
            java.lang.Object r6 = r6.getFollowerCount(r5, r0)
            if (r6 != r1) goto L3f
            return r1
        L3f:
            com.amazon.music.platform.Outcome r6 = (com.amazon.music.platform.Outcome) r6
            boolean r5 = r6 instanceof com.amazon.music.platform.Outcome.Success
            if (r5 == 0) goto L4c
            com.amazon.music.platform.Outcome$Success r6 = (com.amazon.music.platform.Outcome.Success) r6
            java.lang.Object r5 = r6.getValue()
            return r5
        L4c:
            boolean r5 = r6 instanceof com.amazon.music.platform.Outcome.Failure
            if (r5 != 0) goto L52
            r5 = 0
            return r5
        L52:
            com.amazon.music.platform.Outcome$Failure r6 = (com.amazon.music.platform.Outcome.Failure) r6
            java.lang.Throwable r5 = r6.getCause()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.datasource.helper.PlaylistFireflyProvider.getFollowerCount(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
