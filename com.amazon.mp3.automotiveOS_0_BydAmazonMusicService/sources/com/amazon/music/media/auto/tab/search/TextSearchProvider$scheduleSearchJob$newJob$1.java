package com.amazon.music.media.auto.tab.search;

import android.support.v4.media.MediaBrowserCompat;
import com.amazon.music.media.auto.AutoTextSearchQuery;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: TextSearchProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.music.media.auto.tab.search.TextSearchProvider$scheduleSearchJob$newJob$1", f = "TextSearchProvider.kt", i = {}, l = {92, 96, 97}, m = "invokeSuspend", n = {}, s = {})
final class TextSearchProvider$scheduleSearchJob$newJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<List<? extends MediaBrowserCompat.MediaItem>, Unit> $callback;
    final /* synthetic */ AutoTextSearchQuery $query;
    int label;
    final /* synthetic */ TextSearchProvider this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    TextSearchProvider$scheduleSearchJob$newJob$1(AutoTextSearchQuery autoTextSearchQuery, Function1<? super List<? extends MediaBrowserCompat.MediaItem>, Unit> function1, TextSearchProvider textSearchProvider, Continuation<? super TextSearchProvider$scheduleSearchJob$newJob$1> continuation) {
        super(2, continuation);
        this.$query = autoTextSearchQuery;
        this.$callback = function1;
        this.this$0 = textSearchProvider;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TextSearchProvider$scheduleSearchJob$newJob$1(this.$query, this.$callback, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TextSearchProvider$scheduleSearchJob$newJob$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00be A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) throws java.lang.Exception {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            java.lang.String r2 = "TAG"
            r3 = 3
            r4 = 2
            r5 = 1
            if (r1 == 0) goto L29
            if (r1 == r5) goto L25
            if (r1 == r4) goto L20
            if (r1 != r3) goto L18
            kotlin.ResultKt.throwOnFailure(r9)
            goto Lbf
        L18:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L20:
            kotlin.ResultKt.throwOnFailure(r9)
            goto Lb0
        L25:
            kotlin.ResultKt.throwOnFailure(r9)
            goto L7d
        L29:
            kotlin.ResultKt.throwOnFailure(r9)
            com.amazon.music.media.auto.util.AutoLog r9 = com.amazon.music.media.auto.util.AutoLog.INSTANCE
            java.lang.String r1 = com.amazon.music.media.auto.tab.search.TextSearchProvider.access$getTAG$cp()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "New coroutine launched for search request '"
            r6.append(r7)
            com.amazon.music.media.auto.AutoTextSearchQuery r7 = r8.$query
            java.lang.String r7 = r7.getSearchTerm()
            r6.append(r7)
            r7 = 39
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r9.debug(r1, r6)
            com.amazon.music.media.auto.AutoTextSearchQuery r9 = r8.$query
            java.lang.String r9 = r9.getSearchTerm()
            if (r9 != 0) goto L5e
            r9 = 0
            goto L62
        L5e:
            int r9 = r9.length()
        L62:
            if (r9 >= r5) goto L70
            kotlin.jvm.functions.Function1<java.util.List<? extends android.support.v4.media.MediaBrowserCompat$MediaItem>, kotlin.Unit> r9 = r8.$callback
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
            r9.invoke(r0)
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L70:
            long r6 = com.amazon.music.media.auto.tab.search.TextSearchProvider.access$getSEARCH_THROTTLE_DURATION$cp()
            r8.label = r5
            java.lang.Object r9 = kotlinx.coroutines.DelayKt.m4266delayVtjQ1oo(r6, r8)
            if (r9 != r0) goto L7d
            return r0
        L7d:
            com.amazon.music.media.auto.util.AutoLog r9 = com.amazon.music.media.auto.util.AutoLog.INSTANCE
            java.lang.String r1 = com.amazon.music.media.auto.tab.search.TextSearchProvider.access$getTAG$cp()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            java.lang.String r2 = "Executing search request"
            r9.debug(r1, r2)
            com.amazon.music.media.auto.tab.search.TextSearchProvider r9 = r8.this$0
            com.amazon.music.media.auto.tab.search.SearchContentConverter r9 = com.amazon.music.media.auto.tab.search.TextSearchProvider.access$getConverter$p(r9)
            int r9 = r9.getNumberOfResults()
            com.amazon.music.media.auto.tab.search.TextSearchProvider r1 = r8.this$0
            com.amazon.music.media.auto.tab.search.SearchContentConverter r1 = com.amazon.music.media.auto.tab.search.TextSearchProvider.access$getConverter$p(r1)
            int r1 = r1.getNumberOfTopHits()
            int r9 = java.lang.Math.max(r9, r1)
            com.amazon.music.media.auto.tab.search.TextSearchProvider r1 = r8.this$0
            com.amazon.music.media.auto.AutoTextSearchQuery r2 = r8.$query
            r8.label = r4
            java.lang.Object r9 = com.amazon.music.media.auto.tab.search.TextSearchProvider.access$executeTenzingRequest(r1, r2, r9, r8)
            if (r9 != r0) goto Lb0
            return r0
        Lb0:
            com.amazon.music.search.SearchResult r9 = (com.amazon.music.search.SearchResult) r9
            com.amazon.music.media.auto.tab.search.TextSearchProvider r1 = r8.this$0
            kotlin.jvm.functions.Function1<java.util.List<? extends android.support.v4.media.MediaBrowserCompat$MediaItem>, kotlin.Unit> r2 = r8.$callback
            r8.label = r3
            java.lang.Object r9 = com.amazon.music.media.auto.tab.search.TextSearchProvider.access$processTenzingResults(r1, r9, r2, r8)
            if (r9 != r0) goto Lbf
            return r0
        Lbf:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.media.auto.tab.search.TextSearchProvider$scheduleSearchJob$newJob$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
