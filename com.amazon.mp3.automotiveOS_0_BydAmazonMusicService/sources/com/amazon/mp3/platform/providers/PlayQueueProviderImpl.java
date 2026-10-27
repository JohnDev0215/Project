package com.amazon.mp3.platform.providers;

import android.content.Context;
import com.amazon.music.platform.providers.PlayQueueProvider;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayQueueProviderImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \f2\u00020\u0001:\u0001\fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J!\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/platform/providers/PlayQueueProviderImpl;", "Lcom/amazon/music/platform/providers/PlayQueueProvider;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "add", "", "entityItemIdentifier", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "position", "Lcom/amazon/music/platform/providers/PlayQueuePosition;", "(Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;Lcom/amazon/music/platform/providers/PlayQueuePosition;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlayQueueProviderImpl implements PlayQueueProvider {
    private static final String TAG = PlayQueueProviderImpl.class.getSimpleName();
    private final Context context;

    /* JADX INFO: renamed from: com.amazon.mp3.platform.providers.PlayQueueProviderImpl$add$1, reason: invalid class name */
    /* JADX INFO: compiled from: PlayQueueProviderImpl.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.platform.providers.PlayQueueProviderImpl", f = "PlayQueueProviderImpl.kt", i = {0, 0}, l = {23}, m = "add", n = {"entityItemIdentifier", "position"}, s = {"L$0", "L$1"})
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
            return PlayQueueProviderImpl.this.add(null, null, this);
        }
    }

    public PlayQueueProviderImpl(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // com.amazon.music.platform.providers.PlayQueueProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object add(com.amazon.music.platform.data.entity.EntityItemIdentifier r5, com.amazon.music.platform.providers.PlayQueuePosition r6, kotlin.coroutines.Continuation<? super java.lang.Boolean> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof com.amazon.mp3.platform.providers.PlayQueueProviderImpl.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            com.amazon.mp3.platform.providers.PlayQueueProviderImpl$add$1 r0 = (com.amazon.mp3.platform.providers.PlayQueueProviderImpl.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.platform.providers.PlayQueueProviderImpl$add$1 r0 = new com.amazon.mp3.platform.providers.PlayQueueProviderImpl$add$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3a
            if (r2 != r3) goto L32
            java.lang.Object r5 = r0.L$1
            r6 = r5
            com.amazon.music.platform.providers.PlayQueuePosition r6 = (com.amazon.music.platform.providers.PlayQueuePosition) r6
            java.lang.Object r5 = r0.L$0
            com.amazon.music.platform.data.entity.EntityItemIdentifier r5 = (com.amazon.music.platform.data.entity.EntityItemIdentifier) r5
            kotlin.ResultKt.throwOnFailure(r7)
            goto L4e
        L32:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3a:
            kotlin.ResultKt.throwOnFailure(r7)
            com.amazon.mp3.platform.mediaitem.EntityItemUtils r7 = com.amazon.mp3.platform.mediaitem.EntityItemUtils.INSTANCE
            android.content.Context r2 = r4.context
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r7 = r7.getEntityItemUri(r2, r5, r0)
            if (r7 != r1) goto L4e
            return r1
        L4e:
            android.net.Uri r7 = (android.net.Uri) r7
            r0 = 0
            if (r7 != 0) goto L7b
            java.lang.String r6 = com.amazon.mp3.platform.providers.PlayQueueProviderImpl.TAG
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r1 = "Unable to add "
            r7.append(r1)
            com.amazon.music.platform.data.entity.EntityId r5 = r5.getId()
            java.lang.String r5 = r5.getIdentifier()
            r7.append(r5)
            java.lang.String r5 = " to the play queue"
            r7.append(r5)
            java.lang.String r5 = r7.toString()
            com.amazon.mp3.util.Log.error(r6, r5)
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r0)
            return r5
        L7b:
            com.amazon.mp3.platform.mediaitem.EntityItemUtils r1 = com.amazon.mp3.platform.mediaitem.EntityItemUtils.INSTANCE
            android.net.Uri r5 = r1.getEntityItemTracksUri(r7, r5)
            com.amazon.mp3.library.provider.source.nowplaying.NowPlayingQueueUtil r7 = com.amazon.mp3.library.provider.source.nowplaying.NowPlayingQueueUtil.getInstance()
            com.amazon.music.platform.providers.PlayQueuePosition r1 = com.amazon.music.platform.providers.PlayQueuePosition.NEXT
            if (r6 != r1) goto L8a
            r0 = r3
        L8a:
            r7.addToPlayQueue(r5, r0)
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.platform.providers.PlayQueueProviderImpl.add(com.amazon.music.platform.data.entity.EntityItemIdentifier, com.amazon.music.platform.providers.PlayQueuePosition, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
