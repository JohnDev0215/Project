package com.amazon.avod.session;

import com.amazon.avod.metrics.pmet.EnumeratedCounterMetricTemplate;
import com.amazon.avod.metrics.pmet.MetricParameter;
import com.amazon.avod.metrics.pmet.MetricSource;
import com.amazon.avod.metrics.pmet.internal.MetricNameTemplate;
import com.amazon.avod.metrics.pmet.internal.ValidatedCounterMetric;
import com.google.common.collect.ImmutableList;
import javax.annotation.Nonnull;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SessionIdMetrics.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\u000fB\u0011\b\u0002\u0012\u0006\u0010\f\u001a\u00020\t¢\u0006\u0004\b\r\u0010\u000eJ*\u0010\b\u001a\u00020\u00072\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0003H\u0016R\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000bj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016¨\u0006\u0017"}, d2 = {"Lcom/amazon/avod/session/SessionIdMetrics;", "", "Lcom/amazon/avod/metrics/pmet/EnumeratedCounterMetricTemplate;", "Lcom/google/common/collect/ImmutableList;", "Lcom/amazon/avod/metrics/pmet/MetricParameter;", "nameParameters", "valueParameters", "Lcom/amazon/avod/metrics/pmet/internal/ValidatedCounterMetric;", "format", "Lcom/amazon/avod/metrics/pmet/internal/MetricNameTemplate;", "mNameTemplate", "Lcom/amazon/avod/metrics/pmet/internal/MetricNameTemplate;", "nameTemplate", "<init>", "(Ljava/lang/String;ILcom/amazon/avod/metrics/pmet/internal/MetricNameTemplate;)V", "RetrieverType", "SESSION_ID_CHANGED", "SESSION_ID_NOT_CHANGED", "SESSION_ID_DIFFERENT_WEBVIEW", "SESSION_ID_NOT_DIFFERENT_WEBVIEW", "SESSION_ID_RETRIEVAL_STARTED", "SESSION_ID_RETRIEVAL_SUCCESS", "SESSION_ID_RETRIEVAL_FAILURE", "ATVAndroidCore_release"}, k = 1, mv = {1, 6, 0})
public enum SessionIdMetrics implements EnumeratedCounterMetricTemplate {
    SESSION_ID_CHANGED(new MetricNameTemplate("SessionId:IdChanged:", ImmutableList.of(RetrieverType.class))),
    SESSION_ID_NOT_CHANGED(new MetricNameTemplate("SessionId:IdNotChanged:", ImmutableList.of(RetrieverType.class))),
    SESSION_ID_DIFFERENT_WEBVIEW(new MetricNameTemplate("SessionId:IdDifferentWebView:", ImmutableList.of(RetrieverType.class))),
    SESSION_ID_NOT_DIFFERENT_WEBVIEW(new MetricNameTemplate("SessionId:IdNotDifferentWebView:", ImmutableList.of(RetrieverType.class))),
    SESSION_ID_RETRIEVAL_STARTED(new MetricNameTemplate("SessionId:RetrievalStarted:", ImmutableList.of(RetrieverType.class))),
    SESSION_ID_RETRIEVAL_SUCCESS(new MetricNameTemplate("SessionId:RetrievalSuccess:", ImmutableList.of(RetrieverType.class))),
    SESSION_ID_RETRIEVAL_FAILURE(new MetricNameTemplate("SessionId:RetrievalFailure:", ImmutableList.of(RetrieverType.class)));

    private final MetricNameTemplate mNameTemplate;

    /* JADX INFO: renamed from: format$lambda-0, reason: not valid java name */
    private static final String m97format$lambda0() {
        return "SESSION_ID";
    }

    @Override // com.amazon.avod.metrics.pmet.EnumeratedCounterMetricTemplate
    @Nonnull
    public /* bridge */ /* synthetic */ ValidatedCounterMetric format(@Nonnull ImmutableList immutableList, @Nonnull ImmutableList immutableList2, long j) {
        return super.format(immutableList, immutableList2, j);
    }

    SessionIdMetrics(MetricNameTemplate metricNameTemplate) {
        this.mNameTemplate = metricNameTemplate;
    }

    @Override // com.amazon.avod.metrics.pmet.EnumeratedCounterMetricTemplate
    public ValidatedCounterMetric format(ImmutableList<MetricParameter> nameParameters, ImmutableList<ImmutableList<MetricParameter>> valueParameters) {
        Intrinsics.checkNotNullParameter(nameParameters, "nameParameters");
        Intrinsics.checkNotNullParameter(valueParameters, "valueParameters");
        return new ValidatedCounterMetric(this.mNameTemplate.format(nameParameters), EnumeratedCounterMetricTemplate.COUNTER_ONLY, new MetricSource() { // from class: com.amazon.avod.session.SessionIdMetrics$$ExternalSyntheticLambda0
        });
    }

    /* JADX INFO: compiled from: SessionIdMetrics.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/amazon/avod/session/SessionIdMetrics$RetrieverType;", "", "Lcom/amazon/avod/metrics/pmet/MetricParameter;", "(Ljava/lang/String;I)V", "toReportableString", "", "THIRD_PARTY", "DCM", "QA_HOOKS", "NO_OP", "ATVAndroidCore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum RetrieverType implements MetricParameter {
        THIRD_PARTY,
        DCM,
        QA_HOOKS,
        NO_OP;

        @Override // com.amazon.avod.metrics.pmet.MetricParameter
        public String toReportableString() {
            return name();
        }
    }
}
