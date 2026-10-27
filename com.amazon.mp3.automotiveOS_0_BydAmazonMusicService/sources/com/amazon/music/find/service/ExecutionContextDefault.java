package com.amazon.music.find.service;

import com.amazon.music.find.metrics.PerformanceData;
import com.amazon.music.find.metrics.SearchMetricsService;
import com.amazon.music.find.metrics.UiEventMetadata;
import com.amazon.music.find.performance.Performance;
import com.amazon.music.find.performance.PerformanceException;
import com.amazon.music.find.performance.PerformanceMark;
import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.find.utils.PerformanceUtils;
import com.amazon.music.find.viewmodels.SearchExperienceMode;
import com.amazon.music.find.viewmodels.SearchOperation;
import com.amazon.music.platform.find.common.EntityType;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: ExecutionContextDefault.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u0000 =2\u00020\u0001:\u0002<=Bq\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\t\u0012\b\u0010\r\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000e\u001a\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\t\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0002\u0010\u0014J\u0010\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0016J\u0012\u0010.\u001a\u0004\u0018\u00010/2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u00100\u001a\u00020+H\u0016J\b\u00101\u001a\u00020+H\u0016J\b\u00102\u001a\u00020+H\u0016J\b\u00103\u001a\u00020+H\u0016J\b\u00104\u001a\u00020+H\u0016J\b\u00105\u001a\u00020+H\u0016J\b\u00106\u001a\u00020+H\u0016J\b\u00107\u001a\u00020+H\u0016J\b\u00108\u001a\u00020+H\u0016J\b\u00109\u001a\u00020+H\u0016J\u0010\u0010:\u001a\u00020+2\u0006\u0010;\u001a\u00020\u0011H\u0002R\u0014\u0010\u000b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0014\u0010\n\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u000f\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016R\u000e\u0010\u001c\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\f\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0016R\u0014\u0010\u000e\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0016R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0018\u0010\r\u001a\u0004\u0018\u00010\tX\u0096\u0004¢\u0006\n\n\u0002\u0010'\u001a\u0004\b%\u0010&R\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)¨\u0006>"}, d2 = {"Lcom/amazon/music/find/service/ExecutionContextDefault;", "Lcom/amazon/music/find/service/ExecutionContext;", "performance", "Lcom/amazon/music/find/performance/Performance;", "searchOperation", "Lcom/amazon/music/find/viewmodels/SearchOperation;", "searchPageContext", "Lcom/amazon/music/find/service/SearchPageContextData;", "emitUiPageView", "", "emitUiPageViewMilliseconds", "emitPageLatencies", "requestSearchViewFocus", "searchViewFocus", "resetScrollPosition", "injectContentInfo", ContentUtils.KEY_EVENT_ID, "", "uiEventMetadata", "Lcom/amazon/music/find/metrics/UiEventMetadata;", "(Lcom/amazon/music/find/performance/Performance;Lcom/amazon/music/find/viewmodels/SearchOperation;Lcom/amazon/music/find/service/SearchPageContextData;ZZZZLjava/lang/Boolean;ZZLjava/lang/String;Lcom/amazon/music/find/metrics/UiEventMetadata;)V", "getEmitPageLatencies", "()Z", "getEmitUiPageView", "getEmitUiPageViewMilliseconds", "getEventId", "()Ljava/lang/String;", "getInjectContentInfo", "mCompleted", "getPerformance", "()Lcom/amazon/music/find/performance/Performance;", "getRequestSearchViewFocus", "getResetScrollPosition", "getSearchOperation", "()Lcom/amazon/music/find/viewmodels/SearchOperation;", "getSearchPageContext", "()Lcom/amazon/music/find/service/SearchPageContextData;", "getSearchViewFocus", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getUiEventMetadata", "()Lcom/amazon/music/find/metrics/UiEventMetadata;", "complete", "", "searchMetricsService", "Lcom/amazon/music/find/metrics/SearchMetricsService;", "getPerformanceData", "Lcom/amazon/music/find/metrics/PerformanceData;", "markApiRequestCompleted", "markApiRequestStarted", "markConversionCompleted", "markConversionStarted", "markOperationCompleted", "markOperationStarted", "markRenderingCompleted", "markRenderingStarted", "markResponseCompleted", "markResponseStarted", "performanceMarkSafe", "name", "Builder", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ExecutionContextDefault implements ExecutionContext {
    private static final String TAG = "SPL";
    private static final Logger logger = LoggerFactory.getLogger(ExecutionContextDefault.class.getName());
    private final boolean emitPageLatencies;
    private final boolean emitUiPageView;
    private final boolean emitUiPageViewMilliseconds;
    private final String eventId;
    private final boolean injectContentInfo;
    private boolean mCompleted;
    private final Performance performance;
    private final boolean requestSearchViewFocus;
    private final boolean resetScrollPosition;
    private final SearchOperation searchOperation;
    private final SearchPageContextData searchPageContext;
    private final Boolean searchViewFocus;
    private final UiEventMetadata uiEventMetadata;

    public /* synthetic */ ExecutionContextDefault(Performance performance, SearchOperation searchOperation, SearchPageContextData searchPageContextData, boolean z, boolean z2, boolean z3, boolean z4, Boolean bool, boolean z5, boolean z6, String str, UiEventMetadata uiEventMetadata, DefaultConstructorMarker defaultConstructorMarker) {
        this(performance, searchOperation, searchPageContextData, z, z2, z3, z4, bool, z5, z6, str, uiEventMetadata);
    }

    private ExecutionContextDefault(Performance performance, SearchOperation searchOperation, SearchPageContextData searchPageContextData, boolean z, boolean z2, boolean z3, boolean z4, Boolean bool, boolean z5, boolean z6, String str, UiEventMetadata uiEventMetadata) {
        this.performance = performance;
        this.searchOperation = searchOperation;
        this.searchPageContext = searchPageContextData;
        this.emitUiPageView = z;
        this.emitUiPageViewMilliseconds = z2;
        this.emitPageLatencies = z3;
        this.requestSearchViewFocus = z4;
        this.searchViewFocus = bool;
        this.resetScrollPosition = z5;
        this.injectContentInfo = z6;
        this.eventId = str;
        this.uiEventMetadata = uiEventMetadata;
    }

    @Override // com.amazon.music.find.service.ExecutionContext
    public Performance getPerformance() {
        return this.performance;
    }

    @Override // com.amazon.music.find.service.ExecutionContext
    public SearchOperation getSearchOperation() {
        return this.searchOperation;
    }

    @Override // com.amazon.music.find.service.ExecutionContext
    public SearchPageContextData getSearchPageContext() {
        return this.searchPageContext;
    }

    @Override // com.amazon.music.find.service.ExecutionContext
    public boolean getEmitUiPageView() {
        return this.emitUiPageView;
    }

    @Override // com.amazon.music.find.service.ExecutionContext
    public boolean getEmitUiPageViewMilliseconds() {
        return this.emitUiPageViewMilliseconds;
    }

    @Override // com.amazon.music.find.service.ExecutionContext
    public boolean getEmitPageLatencies() {
        return this.emitPageLatencies;
    }

    @Override // com.amazon.music.find.service.ExecutionContext
    public boolean getRequestSearchViewFocus() {
        return this.requestSearchViewFocus;
    }

    @Override // com.amazon.music.find.service.ExecutionContext
    public Boolean getSearchViewFocus() {
        return this.searchViewFocus;
    }

    @Override // com.amazon.music.find.service.ExecutionContext
    public boolean getResetScrollPosition() {
        return this.resetScrollPosition;
    }

    @Override // com.amazon.music.find.service.ExecutionContext
    public boolean getInjectContentInfo() {
        return this.injectContentInfo;
    }

    @Override // com.amazon.music.find.service.ExecutionContext
    public String getEventId() {
        return this.eventId;
    }

    @Override // com.amazon.music.find.service.ExecutionContext
    public UiEventMetadata getUiEventMetadata() {
        return this.uiEventMetadata;
    }

    @Override // com.amazon.music.find.service.ExecutionContext
    public void markOperationStarted() {
        performanceMarkSafe(PerformanceUtils.MARK_OPERATION_STARTED);
    }

    @Override // com.amazon.music.find.service.ExecutionContext
    public void markOperationCompleted() {
        performanceMarkSafe(PerformanceUtils.MARK_OPERATION_COMPLETED);
    }

    @Override // com.amazon.music.find.service.ExecutionContext
    public void markResponseStarted() {
        performanceMarkSafe(PerformanceUtils.MARK_RESPONSE_STARTED);
    }

    @Override // com.amazon.music.find.service.ExecutionContext
    public void markResponseCompleted() {
        performanceMarkSafe(PerformanceUtils.MARK_RESPONSE_COMPLETED);
    }

    @Override // com.amazon.music.find.service.ExecutionContext
    public void markApiRequestStarted() {
        performanceMarkSafe(PerformanceUtils.MARK_API_REQUEST_STARTED);
    }

    @Override // com.amazon.music.find.service.ExecutionContext
    public void markApiRequestCompleted() {
        performanceMarkSafe(PerformanceUtils.MARK_API_REQUEST_COMPLETED);
    }

    @Override // com.amazon.music.find.service.ExecutionContext
    public void markConversionStarted() {
        performanceMarkSafe(PerformanceUtils.MARK_CONVERSION_STARTED);
    }

    @Override // com.amazon.music.find.service.ExecutionContext
    public void markConversionCompleted() {
        performanceMarkSafe(PerformanceUtils.MARK_CONVERSION_COMPLETED);
    }

    @Override // com.amazon.music.find.service.ExecutionContext
    public void markRenderingStarted() {
        performanceMarkSafe(PerformanceUtils.MARK_RENDERING_STARTED);
    }

    @Override // com.amazon.music.find.service.ExecutionContext
    public void markRenderingCompleted() {
        performanceMarkSafe(PerformanceUtils.MARK_RENDERING_COMPLETED);
    }

    private final void performanceMarkSafe(String name) {
        try {
            getPerformance().mark(name);
        } catch (PerformanceException e) {
            logger.error(TAG + "::tryPerformanceMark. Performance exception during setting the mark " + name + ": " + ((Object) e.getMessage()));
        } catch (Exception e2) {
            logger.error(TAG + "::tryPerformanceMark. Unhandled exception during setting the mark " + name + ": " + ((Object) e2.getMessage()));
        }
    }

    @Override // com.amazon.music.find.service.ExecutionContext
    public void complete(SearchMetricsService searchMetricsService) {
        SearchOperation searchOperation;
        SearchPageContextData searchPageContext;
        long jValueOf;
        Long l;
        Intrinsics.checkNotNullParameter(searchMetricsService, "searchMetricsService");
        if (this.mCompleted) {
            return;
        }
        try {
            PerformanceData performanceData = getPerformanceData(getPerformance());
            if (performanceData != null && (searchOperation = getSearchOperation()) != null) {
                SearchPageContextData searchPageContext2 = getSearchPageContext();
                SearchExperienceMode mode = searchPageContext2 == null ? null : searchPageContext2.getMode();
                SearchPageContextData searchPageContext3 = getSearchPageContext();
                Boolean boolIsOffline = searchPageContext3 == null ? null : searchPageContext3.isOffline();
                SearchPageContextData searchPageContext4 = getSearchPageContext();
                Boolean boolIsLibrary = searchPageContext4 == null ? null : searchPageContext4.isLibrary();
                SearchPageContextData searchPageContext5 = getSearchPageContext();
                searchMetricsService.reportLatencies(searchOperation, performanceData, mode, boolIsOffline, boolIsLibrary, searchPageContext5 == null ? null : searchPageContext5.getSeeMoreEntityType());
            }
            if (getEmitUiPageView() && (searchPageContext = getSearchPageContext()) != null) {
                SearchExperienceMode mode2 = searchPageContext.getMode();
                Boolean boolIsLibrary2 = searchPageContext.isLibrary();
                EntityType seeMoreEntityType = searchPageContext.getSeeMoreEntityType();
                if (!getEmitUiPageViewMilliseconds()) {
                    jValueOf = 0L;
                } else if (performanceData == null) {
                    l = null;
                    searchMetricsService.trackGenericUiPageView(mode2, boolIsLibrary2, l, seeMoreEntityType, getUiEventMetadata());
                } else {
                    jValueOf = Long.valueOf(performanceData.getOperationLatency());
                }
                l = jValueOf;
                searchMetricsService.trackGenericUiPageView(mode2, boolIsLibrary2, l, seeMoreEntityType, getUiEventMetadata());
            }
        } finally {
            this.mCompleted = true;
            Performance.clear$default(getPerformance(), null, 1, null);
        }
    }

    private final PerformanceData getPerformanceData(Performance performance) {
        long jLongValue;
        PerformanceMark markByName = performance.getMarkByName(PerformanceUtils.MARK_OPERATION_STARTED);
        Long lValueOf = markByName == null ? null : Long.valueOf(markByName.getStartTime());
        PerformanceMark markByName2 = performance.getMarkByName(PerformanceUtils.MARK_RESPONSE_STARTED);
        Long lValueOf2 = markByName2 == null ? null : Long.valueOf(markByName2.getStartTime());
        PerformanceMark markByName3 = performance.getMarkByName(PerformanceUtils.MARK_API_REQUEST_STARTED);
        Long lValueOf3 = markByName3 == null ? null : Long.valueOf(markByName3.getStartTime());
        PerformanceMark markByName4 = performance.getMarkByName(PerformanceUtils.MARK_API_REQUEST_COMPLETED);
        Long lValueOf4 = markByName4 == null ? null : Long.valueOf(markByName4.getStartTime());
        PerformanceMark markByName5 = performance.getMarkByName(PerformanceUtils.MARK_RESPONSE_COMPLETED);
        Long lValueOf5 = markByName5 == null ? null : Long.valueOf(markByName5.getStartTime());
        PerformanceMark markByName6 = performance.getMarkByName(PerformanceUtils.MARK_CONVERSION_STARTED);
        Long lValueOf6 = markByName6 == null ? null : Long.valueOf(markByName6.getStartTime());
        PerformanceMark markByName7 = performance.getMarkByName(PerformanceUtils.MARK_CONVERSION_COMPLETED);
        Long lValueOf7 = markByName7 == null ? null : Long.valueOf(markByName7.getStartTime());
        PerformanceMark markByName8 = performance.getMarkByName(PerformanceUtils.MARK_RENDERING_STARTED);
        Long lValueOf8 = markByName8 == null ? null : Long.valueOf(markByName8.getStartTime());
        PerformanceMark markByName9 = performance.getMarkByName(PerformanceUtils.MARK_RENDERING_COMPLETED);
        Long lValueOf9 = markByName9 == null ? null : Long.valueOf(markByName9.getStartTime());
        PerformanceMark markByName10 = performance.getMarkByName(PerformanceUtils.MARK_OPERATION_COMPLETED);
        Long lValueOf10 = markByName10 == null ? null : Long.valueOf(markByName10.getStartTime());
        if (lValueOf == null || lValueOf10 == null || lValueOf2 == null || lValueOf5 == null || lValueOf6 == null || lValueOf7 == null || lValueOf8 == null || lValueOf9 == null) {
            return null;
        }
        long jLongValue2 = lValueOf5.longValue() - lValueOf2.longValue();
        if (lValueOf3 == null || lValueOf4 == null) {
            lValueOf4 = lValueOf5;
            jLongValue = 0;
        } else {
            jLongValue = lValueOf4.longValue() - lValueOf3.longValue();
            lValueOf2 = lValueOf3;
        }
        return new PerformanceData(lValueOf.longValue(), lValueOf2.longValue(), lValueOf4.longValue(), lValueOf8.longValue(), lValueOf10.longValue(), jLongValue2, jLongValue, jLongValue2 - jLongValue, lValueOf7.longValue() - lValueOf6.longValue(), lValueOf9.longValue() - lValueOf8.longValue(), lValueOf10.longValue() - lValueOf.longValue());
    }

    /* JADX INFO: compiled from: ExecutionContextDefault.kt */
    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\nJ\u0010\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u001c\u001a\u00020\u0006J\u0010\u0010\u001d\u001a\u00020\u00002\b\u0010\u001e\u001a\u0004\u0018\u00010\u0012J3\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u00062\b\u0010\"\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$¢\u0006\u0002\u0010%J\u0006\u0010&\u001a\u00020\u0000J\u000e\u0010'\u001a\u00020\u00002\u0006\u0010(\u001a\u00020\u0004J\u0010\u0010)\u001a\u00020\u00002\b\b\u0002\u0010*\u001a\u00020\u0006J\u0010\u0010+\u001a\u00020\u00002\b\u0010,\u001a\u0004\u0018\u00010\u0010J\u0010\u0010-\u001a\u00020\u00002\b\b\u0002\u0010.\u001a\u00020\u0006J\u000e\u0010/\u001a\u00020\u00002\u0006\u00100\u001a\u00020\u0016J\u0010\u00101\u001a\u00020\u00002\b\b\u0002\u00102\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0014R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/amazon/music/find/service/ExecutionContextDefault$Builder;", "", "()V", "mDefaultPerformance", "Lcom/amazon/music/find/performance/Performance;", "mEmitPageLatencies", "", "mEmitUiPageView", "mEmitUiPageViewLoadTimeMilliseconds", "mEventId", "", "mInjectContentInfo", "mPerformance", "mRequestSearchViewFocus", "mResetScrollPosition", "mSearchOperation", "Lcom/amazon/music/find/viewmodels/SearchOperation;", "mSearchPageContext", "Lcom/amazon/music/find/service/SearchPageContextData;", "mSearchViewFocus", "Ljava/lang/Boolean;", "mUiEventMetadata", "Lcom/amazon/music/find/metrics/UiEventMetadata;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/amazon/music/find/service/ExecutionContextDefault;", "withEventId", ContentUtils.KEY_EVENT_ID, "withInjectContentInfo", "shouldInjectContentInfo", "withPageContext", "searchPageContext", "mode", "Lcom/amazon/music/find/viewmodels/SearchExperienceMode;", "isOffline", "isLibrary", "seeMoreEntityType", "Lcom/amazon/music/platform/find/common/EntityType;", "(Lcom/amazon/music/find/viewmodels/SearchExperienceMode;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcom/amazon/music/platform/find/common/EntityType;)Lcom/amazon/music/find/service/ExecutionContextDefault$Builder;", "withPageLatencies", "withPerformance", "performance", "withResetScrollPosition", "shouldResetScrollPosition", "withSearchOperation", "searchOperation", "withSearchViewFocus", "searchViewFocus", "withUiEventMetadata", "uiEventMetadata", "withUiPageView", "emitUiPageViewLoadTimeMilliseconds", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Builder {
        private boolean mEmitPageLatencies;
        private boolean mEmitUiPageView;
        private boolean mEmitUiPageViewLoadTimeMilliseconds;
        private String mEventId;
        private boolean mInjectContentInfo;
        private Performance mPerformance;
        private boolean mRequestSearchViewFocus;
        private SearchOperation mSearchOperation;
        private SearchPageContextData mSearchPageContext;
        private Boolean mSearchViewFocus;
        private UiEventMetadata mUiEventMetadata;
        private final Performance mDefaultPerformance = new Performance.Builder().build();
        private boolean mResetScrollPosition = true;

        public final Builder withPerformance(Performance performance) {
            Intrinsics.checkNotNullParameter(performance, "performance");
            this.mPerformance = performance;
            return this;
        }

        public final Builder withSearchOperation(SearchOperation searchOperation) {
            this.mSearchOperation = searchOperation;
            return this;
        }

        public final Builder withPageContext(SearchPageContextData searchPageContext) {
            this.mSearchPageContext = searchPageContext;
            return this;
        }

        public static /* synthetic */ Builder withPageContext$default(Builder builder, SearchExperienceMode searchExperienceMode, Boolean bool, Boolean bool2, EntityType entityType, int i, Object obj) {
            if ((i & 8) != 0) {
                entityType = null;
            }
            return builder.withPageContext(searchExperienceMode, bool, bool2, entityType);
        }

        public final Builder withPageContext(SearchExperienceMode mode, Boolean isOffline, Boolean isLibrary, EntityType seeMoreEntityType) {
            Intrinsics.checkNotNullParameter(mode, "mode");
            this.mSearchPageContext = new SearchPageContextData(mode, isOffline, isLibrary, seeMoreEntityType);
            return this;
        }

        public static /* synthetic */ Builder withUiPageView$default(Builder builder, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            return builder.withUiPageView(z);
        }

        public final Builder withUiPageView(boolean emitUiPageViewLoadTimeMilliseconds) {
            this.mEmitUiPageView = true;
            this.mEmitUiPageViewLoadTimeMilliseconds = emitUiPageViewLoadTimeMilliseconds;
            return this;
        }

        public final Builder withPageLatencies() {
            this.mEmitPageLatencies = true;
            return this;
        }

        public static /* synthetic */ Builder withSearchViewFocus$default(Builder builder, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            return builder.withSearchViewFocus(z);
        }

        public final Builder withSearchViewFocus(boolean searchViewFocus) {
            this.mRequestSearchViewFocus = true;
            this.mSearchViewFocus = Boolean.valueOf(searchViewFocus);
            return this;
        }

        public static /* synthetic */ Builder withResetScrollPosition$default(Builder builder, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = true;
            }
            return builder.withResetScrollPosition(z);
        }

        public final Builder withResetScrollPosition(boolean shouldResetScrollPosition) {
            this.mResetScrollPosition = shouldResetScrollPosition;
            return this;
        }

        public final Builder withUiEventMetadata(UiEventMetadata uiEventMetadata) {
            Intrinsics.checkNotNullParameter(uiEventMetadata, "uiEventMetadata");
            this.mUiEventMetadata = uiEventMetadata;
            return this;
        }

        public static /* synthetic */ Builder withInjectContentInfo$default(Builder builder, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            return builder.withInjectContentInfo(z);
        }

        public final Builder withInjectContentInfo(boolean shouldInjectContentInfo) {
            this.mInjectContentInfo = shouldInjectContentInfo;
            return this;
        }

        public final Builder withEventId(String eventId) {
            Intrinsics.checkNotNullParameter(eventId, "eventId");
            this.mEventId = eventId;
            return this;
        }

        public final ExecutionContextDefault build() {
            Performance performance = this.mPerformance;
            if (performance == null) {
                performance = this.mDefaultPerformance;
            }
            return new ExecutionContextDefault(performance, this.mSearchOperation, this.mSearchPageContext, this.mEmitUiPageView, this.mEmitUiPageViewLoadTimeMilliseconds, this.mEmitPageLatencies, this.mRequestSearchViewFocus, this.mSearchViewFocus, this.mResetScrollPosition, this.mInjectContentInfo, this.mEventId, this.mUiEventMetadata, null);
        }
    }
}
