package com.amazon.music.featuregating.weblabs;

import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.platform.metrics.event.FlexWrapper;
import com.amazon.music.platform.metrics.event.MetricsWrapper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WeblabMetricsWrapper.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0002J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0002J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0002J$\u0010\u000f\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000b\u001a\u00020\u00022\n\u0010\u000e\u001a\u00060\fj\u0002`\r¨\u0006\u0012"}, d2 = {"Lcom/amazon/music/featuregating/weblabs/WeblabMetricsWrapper;", "", "", "reason", "Lcom/amazon/music/platform/metrics/event/MetricsWrapper;", "clientInitFailed", "weblab", "noTreatmentSnapshotReturned", "noTreatmentReturned", "trackUnregisteredWeblab", ParserUtil.TAG_QUERY_PARAM_NAME, "method", "Ljava/lang/Exception;", "Lkotlin/Exception;", "exception", "trackException", "<init>", "()V", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
public final class WeblabMetricsWrapper {
    public static final WeblabMetricsWrapper INSTANCE = new WeblabMetricsWrapper();

    private WeblabMetricsWrapper() {
    }

    public final MetricsWrapper clientInitFailed(String reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        return new FlexWrapper.Builder().withName("clientInitFailed").withStr1(reason).build();
    }

    public final MetricsWrapper noTreatmentSnapshotReturned(String weblab) {
        Intrinsics.checkNotNullParameter(weblab, "weblab");
        return new FlexWrapper.Builder().withName("noTreatmentSnapshotReturnedForWeblab").withStr1(weblab).build();
    }

    public final MetricsWrapper noTreatmentReturned(String weblab) {
        Intrinsics.checkNotNullParameter(weblab, "weblab");
        return new FlexWrapper.Builder().withName("noTreatmentReturnedForWeblab").withStr1(weblab).build();
    }

    public final MetricsWrapper trackUnregisteredWeblab(String weblab) {
        Intrinsics.checkNotNullParameter(weblab, "weblab");
        return new FlexWrapper.Builder().withName("weblabNotRegistered").withStr1(weblab).build();
    }

    public final MetricsWrapper trackException(String tag, String method, Exception exception) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(exception, "exception");
        return new FlexWrapper.Builder().withName("weblabException").withStr1(method).withStr2(exception.getMessage()).withStr3(tag).build();
    }
}
