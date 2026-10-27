package com.amazon.avod.http;

import com.amazon.avod.perf.DurationMetric;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.SimpleCounterMetric;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.ErrorCodeUtils;
import com.amazon.avod.util.Preconditions2;
import com.amazon.bolthttp.EventListener;
import com.amazon.bolthttp.Request;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class MetricEventListener extends EventListener {
    private static final ImmutableMap<EventListener.NetworkConnectionEvent.State, String> NETWORK_CONNECTION_STATE_TO_METRIC_SUFFIX_MAP = (ImmutableMap) Preconditions2.checkFullKeyMapping(EventListener.NetworkConnectionEvent.State.class, ImmutableMap.of(EventListener.NetworkConnectionEvent.State.DNS_LOOKUP_ONLY, "-NetworkConn-DnsLookup", EventListener.NetworkConnectionEvent.State.CONNECTION_SETUP_ONLY, "-NetworkConn-ConnectionSetup", EventListener.NetworkConnectionEvent.State.REQUEST_SEND_ONLY, "-NetworkConn-RequestSend", EventListener.NetworkConnectionEvent.State.RESPONSE_FIRST_BYTE, "-NetworkConn-ResponseFirstByte", EventListener.NetworkConnectionEvent.State.RESPONSE_LAST_BYTE, "-NetworkConn-ResponseLastByte"));
    protected final ServiceNameProvider mServiceNameProvider;

    public interface ServiceNameProvider {
        String getApiShortName(@Nonnull Request<?> request);
    }

    public MetricEventListener(@Nonnull ServiceNameProvider serviceNameProvider) {
        this.mServiceNameProvider = (ServiceNameProvider) Preconditions.checkNotNull(serviceNameProvider, "serviceNameProvider");
    }

    @Override // com.amazon.bolthttp.EventListener
    public void onNetworkEvent(@Nonnull EventListener.NetworkEvent networkEvent, @Nonnull Request<?> request) {
        String apiShortName = this.mServiceNameProvider.getApiShortName(request);
        if (apiShortName == null || apiShortName.isEmpty()) {
            DLog.warnf("Unable to parse api name for: %s", request.getUrl());
        } else {
            reportNetworkCallDuration(apiShortName, networkEvent);
            reportNetworkExceptions(apiShortName, networkEvent);
        }
    }

    @Override // com.amazon.bolthttp.EventListener
    public void onNetworkConnectionEvent(@Nonnull EventListener.NetworkConnectionEvent networkConnectionEvent, @Nonnull Request<?> request) {
        String apiShortName = this.mServiceNameProvider.getApiShortName(request);
        if (apiShortName == null || apiShortName.isEmpty()) {
            DLog.warnf("Unable to parse api name for: %s", request.getUrl());
            return;
        }
        if (networkConnectionEvent.getState() == EventListener.NetworkConnectionEvent.State.REQUEST_SEND_ONLY && networkConnectionEvent.getByteCount() != null) {
            Profiler.reportTimerMetric(new DurationMetric(apiShortName + "-RequestSizeBytes", ImmutableList.of("Metric"), networkConnectionEvent.getByteCount().longValue()));
        }
        Profiler.reportTimerMetric(new DurationMetric(apiShortName + NETWORK_CONNECTION_STATE_TO_METRIC_SUFFIX_MAP.get(networkConnectionEvent.getState()), ImmutableList.of("Metric"), networkConnectionEvent.getAttemptDurationMillis()));
    }

    @Override // com.amazon.bolthttp.EventListener
    public void onExecutionStart(@Nonnull Request<?> request) {
        String apiShortName = this.mServiceNameProvider.getApiShortName(request);
        if (apiShortName == null || apiShortName.isEmpty()) {
            DLog.warnf("Unable to parse api name for: %s", request.getUrl());
        } else {
            reportRequestMade(apiShortName);
        }
    }

    @Override // com.amazon.bolthttp.EventListener
    public void onResponseHandlerEvent(@Nonnull EventListener.ResponseHandlerEvent responseHandlerEvent, @Nonnull Request<?> request) {
        String apiShortName = this.mServiceNameProvider.getApiShortName(request);
        if (apiShortName == null || apiShortName.isEmpty()) {
            DLog.warnf("Unable to parse api name for: %s", request.getUrl());
        } else {
            reportResponseDuration(apiShortName, responseHandlerEvent);
            reportResponseExceptions(apiShortName, responseHandlerEvent);
        }
    }

    @Override // com.amazon.bolthttp.EventListener
    public void onExecutionCompleteEvent(@Nonnull EventListener.ExecutionCompleteEvent executionCompleteEvent, @Nonnull Request<?> request) {
        String apiShortName = this.mServiceNameProvider.getApiShortName(request);
        if (apiShortName == null || apiShortName.isEmpty()) {
            DLog.warnf("Unable to parse api name for: %s", request.getUrl());
        } else {
            reportExecutionDuration(apiShortName, executionCompleteEvent);
            reportResponseSize(apiShortName, executionCompleteEvent);
        }
    }

    private static void reportNetworkCallDuration(@Nonnull String str, @Nonnull EventListener.NetworkEvent networkEvent) {
        String str2 = str + "-NetworkCall";
        EventListener.NetworkEvent.State state = networkEvent.getState();
        EventListener.NetworkEvent.State state2 = EventListener.NetworkEvent.State.SUCCESS;
        Profiler.reportTimerMetric(new DurationMetric(str2, ImmutableList.of(state == state2 ? "Success" : "Failure", (networkEvent.getState() == state2 ? "SuccessAttempt-" : "FailureAttempt-") + Integer.toString(networkEvent.getAttemptCount())), networkEvent.getAttemptDurationMillis()));
    }

    private static void reportRequestMade(@Nonnull String str) {
        Profiler.reportCounterMetric(new SimpleCounterMetric(str + "-RequestMade"));
    }

    protected static void reportNetworkExceptions(@Nonnull String str, @Nonnull EventListener.NetworkEvent networkEvent) {
        if (networkEvent.getState() == EventListener.NetworkEvent.State.SUCCESS) {
            return;
        }
        Profiler.reportCounterMetric(new SimpleCounterMetric(str + "-NetworkExceptions", (ImmutableList<String>) ImmutableList.of(ErrorCodeUtils.extractReportableMetric(networkEvent.getException()))));
    }

    private static void reportResponseDuration(@Nonnull String str, @Nonnull EventListener.ResponseHandlerEvent responseHandlerEvent) {
        String str2 = str + "-Parser";
        EventListener.ResponseHandlerEvent.State state = responseHandlerEvent.getState();
        EventListener.ResponseHandlerEvent.State state2 = EventListener.ResponseHandlerEvent.State.SUCCESS;
        Profiler.reportTimerMetric(new DurationMetric(str2, ImmutableList.of(state == state2 ? "Success" : "Failure", (responseHandlerEvent.getState() == state2 ? "SuccessAttempt-" : "FailureAttempt-") + Integer.toString(responseHandlerEvent.getAttemptCount())), responseHandlerEvent.getAttemptDurationMillis()));
    }

    private static void reportResponseExceptions(@Nonnull String str, @Nonnull EventListener.ResponseHandlerEvent responseHandlerEvent) {
        if (responseHandlerEvent.getState() == EventListener.ResponseHandlerEvent.State.SUCCESS) {
            return;
        }
        Profiler.reportCounterMetric(new SimpleCounterMetric(str + "-ParserExceptions", (ImmutableList<String>) ImmutableList.of(ErrorCodeUtils.extractReportableMetric(responseHandlerEvent.getException()))));
    }

    private static void reportExecutionDuration(@Nonnull String str, @Nonnull EventListener.ExecutionCompleteEvent executionCompleteEvent) {
        Profiler.reportTimerMetric(new DurationMetric(str + "-Execution", ImmutableList.of(executionCompleteEvent.getState() == EventListener.ExecutionCompleteEvent.State.SUCCESS ? "Success" : "Failure"), executionCompleteEvent.getExecutionDurationMillis()));
    }

    private static void reportResponseSize(@Nonnull String str, @Nonnull EventListener.ExecutionCompleteEvent executionCompleteEvent) {
        Profiler.reportTimerMetric(new DurationMetric(str + "-ResponseSizeBytes", ImmutableList.of(executionCompleteEvent.getState() == EventListener.ExecutionCompleteEvent.State.SUCCESS ? "Success" : "Failure"), executionCompleteEvent.getDownloadStatistics().getBytesProcessed()));
    }
}
