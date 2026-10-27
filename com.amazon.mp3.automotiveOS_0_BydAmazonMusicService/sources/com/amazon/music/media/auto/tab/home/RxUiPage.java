package com.amazon.music.media.auto.tab.home;

import android.os.TransactionTooLargeException;
import com.amazon.music.browse.BrowseException;
import com.amazon.music.browse.brush.BrushLatencyReportingUtil;
import com.amazon.music.browse.page.PageRequest;
import com.amazon.music.browse.page.UiPage;
import com.amazon.music.media.auto.util.AutoLog;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.musicensembleservice.brush.Content;
import com.amazon.musicensembleservice.brush.PageResponse;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.Subscriber;

/* JADX INFO: compiled from: RxUiPage.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \f2\u00020\u0001:\u0001\fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J!\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0086\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/music/media/auto/tab/home/RxUiPage;", "", "page", "Lcom/amazon/music/browse/page/UiPage;", "(Lcom/amazon/music/browse/page/UiPage;)V", "get", "Lrx/Observable;", "Lcom/amazon/musicensembleservice/brush/Content;", "request", "Lcom/amazon/music/browse/page/PageRequest;", "getCached", "", "Companion", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RxUiPage {
    private static final String TAG = RxUiPage.class.getSimpleName();
    private final UiPage page;

    public RxUiPage(UiPage page) {
        Intrinsics.checkNotNullParameter(page, "page");
        this.page = page;
    }

    public final Observable<Content> get(final PageRequest request, final boolean getCached) {
        Intrinsics.checkNotNullParameter(request, "request");
        Observable<Content> observableCreate = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.music.media.auto.tab.home.RxUiPage$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                RxUiPage.m2168get$lambda0(request, this, getCached, (Subscriber) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create { subscriber ->\n …}\n            }\n        }");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: get$lambda-0, reason: not valid java name */
    public static final void m2168get$lambda0(PageRequest request, RxUiPage this$0, boolean z, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(request, "$request");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            BrushLatencyReportingUtil brushLatencyReportingUtil = BrushLatencyReportingUtil.INSTANCE;
            String uri = request.getUri();
            Intrinsics.checkNotNullExpressionValue(uri, "request.uri");
            brushLatencyReportingUtil.trackLatency(uri, LatencyTrackingLeg.RESPONSE);
            PageResponse pageResponse = this$0.page.get(request, Boolean.valueOf(z), null);
            String uri2 = request.getUri();
            Intrinsics.checkNotNullExpressionValue(uri2, "request.uri");
            brushLatencyReportingUtil.trackLatency(uri2, LatencyTrackingLeg.PROCESSING);
            subscriber.onNext(pageResponse.getContent());
            subscriber.onCompleted();
        } catch (Exception e) {
            if (e instanceof BrowseException) {
                AutoLog autoLog = AutoLog.INSTANCE;
                String TAG2 = TAG;
                Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                autoLog.error(TAG2, "Fail to fetch RxUiPage", e);
                subscriber.onError(e);
                return;
            }
            if (e instanceof TransactionTooLargeException) {
                AutoLog autoLog2 = AutoLog.INSTANCE;
                String TAG3 = TAG;
                Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
                autoLog2.error(TAG3, "Transaction too large", e);
                subscriber.onError(e);
                return;
            }
            AutoLog autoLog3 = AutoLog.INSTANCE;
            String TAG4 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG4, "TAG");
            autoLog3.error(TAG4, "Unknown exception trying to fetch RxUiPage", e);
            subscriber.onError(e);
        }
    }
}
