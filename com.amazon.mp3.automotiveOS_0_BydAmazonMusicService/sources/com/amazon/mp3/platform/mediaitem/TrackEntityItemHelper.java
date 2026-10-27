package com.amazon.mp3.platform.mediaitem;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.external.api.uri.QuerySongByAsin;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.data.entity.SingleType;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TrackEntityItemHelper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nJ\u001b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/platform/mediaitem/TrackEntityItemHelper;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "getContentUri", "Landroid/net/Uri;", "identifier", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "import", "Lcom/amazon/mp3/library/item/MusicTrack;", "(Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TrackEntityItemHelper {
    private static final String TAG = TrackEntityItemHelper.class.getSimpleName();
    private final Context context;

    /* JADX INFO: renamed from: com.amazon.mp3.platform.mediaitem.TrackEntityItemHelper$import$1, reason: invalid class name */
    /* JADX INFO: compiled from: TrackEntityItemHelper.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.platform.mediaitem.TrackEntityItemHelper", f = "TrackEntityItemHelper.kt", i = {0}, l = {84}, m = "import", n = {"this_$iv"}, s = {"L$0"})
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
            return TrackEntityItemHelper.this.m1360import(null, this);
        }
    }

    public TrackEntityItemHelper(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public final Context getContext() {
        return this.context;
    }

    public final Uri getContentUri(EntityItemIdentifier identifier) {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        if (!Intrinsics.areEqual(identifier.getType(), SingleType.Track.INSTANCE)) {
            return null;
        }
        return new QuerySongByAsin(this.context).findCollectionUriByAsin(identifier.getId().getIdentifier());
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /* JADX INFO: renamed from: import, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object m1360import(com.amazon.music.platform.data.entity.EntityItemIdentifier r16, kotlin.coroutines.Continuation<? super com.amazon.mp3.library.item.MusicTrack> r17) {
        /*
            r15 = this;
            r1 = r15
            r0 = r17
            boolean r2 = r0 instanceof com.amazon.mp3.platform.mediaitem.TrackEntityItemHelper.AnonymousClass1
            if (r2 == 0) goto L16
            r2 = r0
            com.amazon.mp3.platform.mediaitem.TrackEntityItemHelper$import$1 r2 = (com.amazon.mp3.platform.mediaitem.TrackEntityItemHelper.AnonymousClass1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L16
            int r3 = r3 - r4
            r2.label = r3
            goto L1b
        L16:
            com.amazon.mp3.platform.mediaitem.TrackEntityItemHelper$import$1 r2 = new com.amazon.mp3.platform.mediaitem.TrackEntityItemHelper$import$1
            r2.<init>(r0)
        L1b:
            java.lang.Object r0 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 0
            r6 = 1
            if (r4 == 0) goto L3b
            if (r4 != r6) goto L33
            java.lang.Object r2 = r2.L$0
            com.amazon.mp3.platform.providers.Coroutines r2 = (com.amazon.mp3.platform.providers.Coroutines) r2
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L31
            goto L54
        L31:
            r0 = move-exception
            goto L58
        L33:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L3b:
            kotlin.ResultKt.throwOnFailure(r0)
            com.amazon.mp3.platform.providers.Coroutines r4 = com.amazon.mp3.platform.providers.Coroutines.INSTANCE
            r7 = 15000(0x3a98, double:7.411E-320)
            com.amazon.mp3.platform.mediaitem.TrackEntityItemHelper$import$$inlined$suspendCoroutineWithTimeout$default$1 r0 = new com.amazon.mp3.platform.mediaitem.TrackEntityItemHelper$import$$inlined$suspendCoroutineWithTimeout$default$1     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L56
            r9 = r16
            r0.<init>(r5, r9, r15)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L56
            r2.L$0 = r4     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L56
            r2.label = r6     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L56
            java.lang.Object r0 = kotlinx.coroutines.TimeoutKt.withTimeout(r7, r0, r2)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L56
            if (r0 != r3) goto L54
            return r3
        L54:
            r5 = r0
            goto L76
        L56:
            r0 = move-exception
            r2 = r4
        L58:
            java.lang.String r2 = r2.getTAG()
            java.lang.StackTraceElement[] r6 = r0.getStackTrace()
            java.lang.String r0 = "e.stackTrace"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r0)
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 62
            r14 = 0
            java.lang.String r7 = "\n"
            java.lang.String r0 = kotlin.collections.ArraysKt.joinToString$default(r6, r7, r8, r9, r10, r11, r12, r13, r14)
            com.amazon.mp3.util.Log.error(r2, r0)
        L76:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.platform.mediaitem.TrackEntityItemHelper.m1360import(com.amazon.music.platform.data.entity.EntityItemIdentifier, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
