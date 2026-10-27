package com.amazon.mp3.brush;

import android.content.Context;
import com.amazon.mp3.brush.v3.converters.BrushV3PageGridConverter;
import com.amazon.mp3.prime.browse.BrowsePageFactory;
import com.amazon.music.browse.brush.BrushLatencyReportingUtil;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.page.api.MusicLandingPageApiGatewayClient;
import com.amazon.music.page.api.model.BasePageRequest;
import com.amazon.music.page.api.model.Page;
import com.amazon.music.page.api.model.PageRequest;
import com.amazon.music.page.api.model.PageResponse;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.Subscriber;

/* JADX INFO: compiled from: RxUiV3Page.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/brush/RxUiV3Page;", "", SearchMetricsServiceDefault.CONTENT_SRC_CLIENT, "Lcom/amazon/music/page/api/MusicLandingPageApiGatewayClient;", "context", "Landroid/content/Context;", "(Lcom/amazon/music/page/api/MusicLandingPageApiGatewayClient;Landroid/content/Context;)V", "get", "Lrx/Observable;", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "request", "Lcom/amazon/music/page/api/model/BasePageRequest;", "invalidateCache", "", "isPageExpired", "Lcom/amazon/music/page/api/model/PageRequest;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RxUiV3Page {
    private final MusicLandingPageApiGatewayClient client;
    private final Context context;

    public RxUiV3Page(MusicLandingPageApiGatewayClient client, Context context) {
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(context, "context");
        this.client = client;
        this.context = context;
    }

    public final Observable<PageGridViewModel> get(final BasePageRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        final BrushV3PageGridConverter brushV3PageGridConverter = new BrushV3PageGridConverter(this.context);
        final String profileId = BrowsePageFactory.INSTANCE.getProfileId(this.context);
        Observable<PageGridViewModel> observableCreate = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.brush.RxUiV3Page$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                RxUiV3Page.m443get$lambda1(request, this, profileId, brushV3PageGridConverter, (Subscriber) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create { subscriber ->\n …)\n            }\n        }");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: get$lambda-1, reason: not valid java name */
    public static final void m443get$lambda1(BasePageRequest request, RxUiV3Page this$0, String str, BrushV3PageGridConverter pageGridConverter, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(request, "$request");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(pageGridConverter, "$pageGridConverter");
        try {
            BrushLatencyReportingUtil brushLatencyReportingUtil = BrushLatencyReportingUtil.INSTANCE;
            String uri = request.getUri();
            Intrinsics.checkNotNullExpressionValue(uri, "request.uri");
            brushLatencyReportingUtil.trackLatency(uri, LatencyTrackingLeg.RESPONSE);
            PageResponse pageResponse = this$0.client.get(request, str);
            String uri2 = request.getUri();
            Intrinsics.checkNotNullExpressionValue(uri2, "request.uri");
            brushLatencyReportingUtil.trackLatency(uri2, LatencyTrackingLeg.PROCESSING);
            PageGridViewModel pageGridViewModelConvert2 = null;
            if (pageResponse != null) {
                Page page = pageResponse.getPage();
                Intrinsics.checkNotNullExpressionValue(page, "response.page");
                Page page2 = pageResponse.getPage();
                Intrinsics.checkNotNullExpressionValue(page2, "response.page");
                pageGridViewModelConvert2 = pageGridConverter.convert2(page, (List<? extends BaseViewModel>) pageGridConverter.getChildrenModels(page2));
            }
            subscriber.onNext(pageGridViewModelConvert2);
            subscriber.onCompleted();
        } catch (Exception e) {
            subscriber.onError(e);
        }
    }

    public final boolean isPageExpired(PageRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        return this.client.isPageExpired(request, BrowsePageFactory.INSTANCE.getProfileId(this.context));
    }

    public final boolean invalidateCache(BasePageRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        return this.client.invalidateCache(request, BrowsePageFactory.INSTANCE.getProfileId(this.context));
    }
}
