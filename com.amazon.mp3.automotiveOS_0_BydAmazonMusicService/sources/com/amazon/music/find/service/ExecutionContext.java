package com.amazon.music.find.service;

import com.amazon.music.find.metrics.SearchMetricsService;
import com.amazon.music.find.metrics.UiEventMetadata;
import com.amazon.music.find.performance.Performance;
import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.find.viewmodels.SearchOperation;
import kotlin.Metadata;

/* JADX INFO: compiled from: ExecutionContext.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\bf\u0018\u00002\u00020\u0001J\u0010\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H&J\b\u0010+\u001a\u00020(H&J\b\u0010,\u001a\u00020(H&J\b\u0010-\u001a\u00020(H&J\b\u0010.\u001a\u00020(H&J\b\u0010/\u001a\u00020(H&J\b\u00100\u001a\u00020(H&J\b\u00101\u001a\u00020(H&J\b\u00102\u001a\u00020(H&J\b\u00103\u001a\u00020(H&J\b\u00104\u001a\u00020(H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0012\u0010\b\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0005R\u0014\u0010\n\u001a\u0004\u0018\u00010\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0005R\u0012\u0010\u0010\u001a\u00020\u0011X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0012\u0010\u0014\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0005R\u0012\u0010\u0016\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0005R\u0014\u0010\u0018\u001a\u0004\u0018\u00010\u0019X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u0004\u0018\u00010\u001dX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0014\u0010#\u001a\u0004\u0018\u00010$X¦\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&¨\u00065"}, d2 = {"Lcom/amazon/music/find/service/ExecutionContext;", "", "emitPageLatencies", "", "getEmitPageLatencies", "()Z", "emitUiPageView", "getEmitUiPageView", "emitUiPageViewMilliseconds", "getEmitUiPageViewMilliseconds", ContentUtils.KEY_EVENT_ID, "", "getEventId", "()Ljava/lang/String;", "injectContentInfo", "getInjectContentInfo", "performance", "Lcom/amazon/music/find/performance/Performance;", "getPerformance", "()Lcom/amazon/music/find/performance/Performance;", "requestSearchViewFocus", "getRequestSearchViewFocus", "resetScrollPosition", "getResetScrollPosition", "searchOperation", "Lcom/amazon/music/find/viewmodels/SearchOperation;", "getSearchOperation", "()Lcom/amazon/music/find/viewmodels/SearchOperation;", "searchPageContext", "Lcom/amazon/music/find/service/SearchPageContextData;", "getSearchPageContext", "()Lcom/amazon/music/find/service/SearchPageContextData;", "searchViewFocus", "getSearchViewFocus", "()Ljava/lang/Boolean;", "uiEventMetadata", "Lcom/amazon/music/find/metrics/UiEventMetadata;", "getUiEventMetadata", "()Lcom/amazon/music/find/metrics/UiEventMetadata;", "complete", "", "searchMetricsService", "Lcom/amazon/music/find/metrics/SearchMetricsService;", "markApiRequestCompleted", "markApiRequestStarted", "markConversionCompleted", "markConversionStarted", "markOperationCompleted", "markOperationStarted", "markRenderingCompleted", "markRenderingStarted", "markResponseCompleted", "markResponseStarted", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface ExecutionContext {
    void complete(SearchMetricsService searchMetricsService);

    boolean getEmitPageLatencies();

    boolean getEmitUiPageView();

    boolean getEmitUiPageViewMilliseconds();

    String getEventId();

    boolean getInjectContentInfo();

    Performance getPerformance();

    boolean getRequestSearchViewFocus();

    boolean getResetScrollPosition();

    SearchOperation getSearchOperation();

    SearchPageContextData getSearchPageContext();

    Boolean getSearchViewFocus();

    UiEventMetadata getUiEventMetadata();

    void markApiRequestCompleted();

    void markApiRequestStarted();

    void markConversionCompleted();

    void markConversionStarted();

    void markOperationCompleted();

    void markOperationStarted();

    void markRenderingCompleted();

    void markRenderingStarted();

    void markResponseCompleted();

    void markResponseStarted();
}
