package com.amazon.music.media.auto.tab.search;

import android.support.v4.media.MediaBrowserCompat;
import com.amazon.music.browse.BrowseContentSelector;
import com.amazon.music.media.auto.AutoTextSearchQuery;
import com.amazon.music.media.auto.provider.MediaBrowserNodeProvider;
import com.amazon.music.media.auto.util.AutoLog;
import com.amazon.music.media.auto.util.CredentialStorage;
import com.amazon.music.media.auto.util.MediaBrowserMetricsUtil;
import com.amazon.music.search.SearchService;
import com.amazon.music.search.SearchServiceImpl;
import com.fasterxml.jackson.dataformat.cbor.CBORConstants;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.wearable.WearableStatusCodes;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: TextSearchProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 %2\u00020\u0001:\u0001%B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012J%\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J7\u0010\u001a\u001a\u00020\u00102\b\u0010\u001b\u001a\u0004\u0018\u00010\u00142\u001a\u0010\u001c\u001a\u0016\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001e\u0012\u0004\u0012\u00020\u00100\u001dH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010 J*\u0010!\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00162\u001a\u0010\u001c\u001a\u0016\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001e\u0012\u0004\u0012\u00020\u00100\u001dJ\u0010\u0010\"\u001a\u00020\u00102\b\u0010#\u001a\u0004\u0018\u00010$R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006&"}, d2 = {"Lcom/amazon/music/media/auto/tab/search/TextSearchProvider;", "", "nodeProvider", "Lcom/amazon/music/media/auto/provider/MediaBrowserNodeProvider;", "converter", "Lcom/amazon/music/media/auto/tab/search/SearchContentConverter;", "mediaBrowserScope", "Lkotlinx/coroutines/CoroutineScope;", "(Lcom/amazon/music/media/auto/provider/MediaBrowserNodeProvider;Lcom/amazon/music/media/auto/tab/search/SearchContentConverter;Lkotlinx/coroutines/CoroutineScope;)V", "contentType", "Lcom/amazon/music/browse/BrowseContentSelector;", "searchJob", "Lkotlinx/coroutines/Job;", "searchService", "Lcom/amazon/music/search/SearchService;", "configureSearchService", "", "config", "Lcom/amazon/music/search/SearchService$Configuration;", "executeTenzingRequest", "Lcom/amazon/music/search/SearchResult;", SearchIntents.EXTRA_QUERY, "Lcom/amazon/music/media/auto/AutoTextSearchQuery;", "pageSize", "", "(Lcom/amazon/music/media/auto/AutoTextSearchQuery;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processTenzingResults", "tenzingResult", "callback", "Lkotlin/Function1;", "", "Landroid/support/v4/media/MediaBrowserCompat$MediaItem;", "(Lcom/amazon/music/search/SearchResult;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scheduleSearchJob", "setContentType", "credentials", "Lcom/amazon/music/media/auto/util/CredentialStorage$Credentials;", "Companion", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TextSearchProvider {
    private static final long RETRY_INITIAL_DELAY;
    private static final long RETRY_MAX_DELAY;
    private static final long SEARCH_RESULT_DELAY;
    private static final long SEARCH_THROTTLE_DURATION;
    private BrowseContentSelector contentType;
    private final SearchContentConverter converter;
    private final CoroutineScope mediaBrowserScope;
    private final MediaBrowserNodeProvider nodeProvider;
    private Job searchJob;
    private SearchService searchService;
    private static final String TAG = TextSearchProvider.class.getSimpleName();

    /* JADX INFO: renamed from: com.amazon.music.media.auto.tab.search.TextSearchProvider$executeTenzingRequest$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TextSearchProvider.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.music.media.auto.tab.search.TextSearchProvider", f = "TextSearchProvider.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {CBORConstants.PREFIX_TYPE_OBJECT, 166}, m = "executeTenzingRequest", n = {"this", "request", "retryDelay", "index", "this", "request", "retryDelay", "index"}, s = {"L$0", "L$1", "J$0", "I$1", "L$0", "L$1", "J$0", "I$1"})
    static final class C04341 extends ContinuationImpl {
        int I$0;
        int I$1;
        long J$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C04341(Continuation<? super C04341> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TextSearchProvider.this.executeTenzingRequest(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.media.auto.tab.search.TextSearchProvider$processTenzingResults$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TextSearchProvider.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.music.media.auto.tab.search.TextSearchProvider", f = "TextSearchProvider.kt", i = {0, 0, 0, 0}, l = {199}, m = "processTenzingResults", n = {"this", "callback", "searchResultNodes", "totalDelay"}, s = {"L$0", "L$1", "L$2", "J$0"})
    static final class C04351 extends ContinuationImpl {
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C04351(Continuation<? super C04351> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TextSearchProvider.this.processTenzingResults(null, null, this);
        }
    }

    public TextSearchProvider(MediaBrowserNodeProvider nodeProvider, SearchContentConverter converter, CoroutineScope mediaBrowserScope) {
        Intrinsics.checkNotNullParameter(nodeProvider, "nodeProvider");
        Intrinsics.checkNotNullParameter(converter, "converter");
        Intrinsics.checkNotNullParameter(mediaBrowserScope, "mediaBrowserScope");
        this.nodeProvider = nodeProvider;
        this.converter = converter;
        this.mediaBrowserScope = mediaBrowserScope;
    }

    static {
        Duration.Companion companion = Duration.INSTANCE;
        DurationUnit durationUnit = DurationUnit.MILLISECONDS;
        SEARCH_THROTTLE_DURATION = DurationKt.toDuration(200, durationUnit);
        SEARCH_RESULT_DELAY = DurationKt.toDuration(1000, durationUnit);
        RETRY_INITIAL_DELAY = DurationKt.toDuration(200, durationUnit);
        RETRY_MAX_DELAY = DurationKt.toDuration(WearableStatusCodes.TARGET_NODE_NOT_CONNECTED, durationUnit);
    }

    public final void configureSearchService(SearchService.Configuration config) {
        if (config != null) {
            BuildersKt__Builders_commonKt.launch$default(this.mediaBrowserScope, null, null, new AnonymousClass1(config, null), 3, null);
        } else {
            this.searchService = null;
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.media.auto.tab.search.TextSearchProvider$configureSearchService$1, reason: invalid class name */
    /* JADX INFO: compiled from: TextSearchProvider.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.media.auto.tab.search.TextSearchProvider$configureSearchService$1", f = "TextSearchProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ SearchService.Configuration $config;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(SearchService.Configuration configuration, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$config = configuration;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return TextSearchProvider.this.new AnonymousClass1(this.$config, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            TextSearchProvider.this.searchService = new SearchServiceImpl(this.$config);
            return Unit.INSTANCE;
        }
    }

    public final void setContentType(CredentialStorage.Credentials credentials) {
        this.contentType = credentials == null ? null : credentials.getContentType();
    }

    public final synchronized void scheduleSearchJob(AutoTextSearchQuery query, final Function1<? super List<? extends MediaBrowserCompat.MediaItem>, Unit> callback) {
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Job jobLaunch$default = BuildersKt__Builders_commonKt.launch$default(this.mediaBrowserScope, null, CoroutineStart.LAZY, new TextSearchProvider$scheduleSearchJob$newJob$1(query, callback, this, null), 1, null);
        jobLaunch$default.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: com.amazon.music.media.auto.tab.search.TextSearchProvider.scheduleSearchJob.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                if (th == null || (th instanceof CancellationException)) {
                    return;
                }
                AutoLog autoLog = AutoLog.INSTANCE;
                String TAG2 = TextSearchProvider.TAG;
                Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                autoLog.error(TAG2, "Search job threw an unexpected exception", th);
                MediaBrowserMetricsUtil mediaBrowserMetricsUtil = MediaBrowserMetricsUtil.INSTANCE;
                String TAG3 = TextSearchProvider.TAG;
                Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
                mediaBrowserMetricsUtil.sendEvent("autoMediaBrowserOnSearchFailure", TAG3, th.getMessage());
                callback.invoke(null);
            }
        });
        Job job = this.searchJob;
        if (job != null && job.isActive()) {
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.debug(TAG2, Intrinsics.stringPlus("Cancelling active search job ", this.searchJob));
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.searchJob = jobLaunch$default;
        jobLaunch$default.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:34:0x016e A[Catch: Exception -> 0x0044, CancellationException -> 0x0047, TryCatch #2 {CancellationException -> 0x0047, Exception -> 0x0044, blocks: (B:13:0x003f, B:32:0x016a, B:34:0x016e, B:36:0x017b), top: B:45:0x002b }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x017b A[Catch: Exception -> 0x0044, CancellationException -> 0x0047, TRY_LEAVE, TryCatch #2 {CancellationException -> 0x0047, Exception -> 0x0044, blocks: (B:13:0x003f, B:32:0x016a, B:34:0x016e, B:36:0x017b), top: B:45:0x002b }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object executeTenzingRequest(com.amazon.music.media.auto.AutoTextSearchQuery r17, int r18, kotlin.coroutines.Continuation<? super com.amazon.music.search.SearchResult> r19) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 427
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.media.auto.tab.search.TextSearchProvider.executeTenzingRequest(com.amazon.music.media.auto.AutoTextSearchQuery, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:26:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00c1 A[LOOP:0: B:34:0x00bb->B:36:0x00c1, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x009a -> B:31:0x009d). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object processTenzingResults(com.amazon.music.search.SearchResult r10, kotlin.jvm.functions.Function1<? super java.util.List<? extends android.support.v4.media.MediaBrowserCompat.MediaItem>, kotlin.Unit> r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            Method dump skipped, instruction units count: 252
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.media.auto.tab.search.TextSearchProvider.processTenzingResults(com.amazon.music.search.SearchResult, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
