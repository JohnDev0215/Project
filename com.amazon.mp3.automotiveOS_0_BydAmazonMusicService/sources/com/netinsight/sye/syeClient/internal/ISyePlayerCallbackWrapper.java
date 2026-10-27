package com.netinsight.sye.syeClient.internal;

import com.netinsight.sye.syeClient.audio.SyeAudioSample;
import com.netinsight.sye.syeClient.notification.SyeNotificationMessage;
import com.netinsight.sye.syeClient.video.SyeVideoSample;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\b`\u0018\u00002\u00020\u0001J'\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H&¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\n\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0004H&¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\f\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0004H&¢\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0004H&¢\u0006\u0004\b\u000e\u0010\rJ'\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0002H&¢\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0002H&¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0002H&¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0002H&¢\u0006\u0004\b\u001a\u0010\u0018J\u001f\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u0002H&¢\u0006\u0004\b\u001d\u0010\u0015J\u000f\u0010\u001e\u001a\u00020\u0007H&¢\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010 \u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0004H&¢\u0006\u0004\b \u0010\u000bJ\u0017\u0010!\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0004H&¢\u0006\u0004\b!\u0010\u000bJ\u0017\u0010$\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\"H&¢\u0006\u0004\b$\u0010%J\u0017\u0010&\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0004H&¢\u0006\u0004\b&\u0010\u000bJ\u0017\u0010)\u001a\u00020\u00072\u0006\u0010(\u001a\u00020'H&¢\u0006\u0004\b)\u0010*J\u0017\u0010-\u001a\u00020\u00072\u0006\u0010,\u001a\u00020+H&¢\u0006\u0004\b-\u0010.J\u0017\u00100\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u0004H&¢\u0006\u0004\b0\u0010\u000bJ\u0017\u00101\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u0004H&¢\u0006\u0004\b1\u0010\u000bJ\u0017\u00102\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0004H&¢\u0006\u0004\b2\u0010\u000bJ\u0017\u00103\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0004H&¢\u0006\u0004\b3\u0010\u000b¨\u00064"}, d2 = {"Lcom/netinsight/sye/syeClient/internal/ISyePlayerCallbackWrapper;", "", "", "errorCode", "", "jsonString", "message", "", "onFrontendError", "(ILjava/lang/String;Ljava/lang/String;)V", "onFrontendSuccess", "(Ljava/lang/String;)V", "onStreamingError", "(ILjava/lang/String;)V", "onError", "retryAfterMillis", "onErrorRetry", "(ILjava/lang/String;I)V", "fromStateCode", "toStateCode", "onStateChange", "(II)V", "allocationTimeMillis", "onEgressAllocated", "(I)V", "timeToResponseMillis", "onEgressContact", "egressTTFFMillis", "totalTTFFMillis", "onTimeToFirstFrame", "onStopInternal", "()V", "onAvailableAudioTracks", "onAudioStreamChange", "Lcom/netinsight/sye/syeClient/audio/SyeAudioSample;", "syeAudioSample", "onAudioSample", "(Lcom/netinsight/sye/syeClient/audio/SyeAudioSample;)V", "onVideoStreamChange", "Lcom/netinsight/sye/syeClient/video/SyeVideoSample;", "syeVideoSample", "onVideoSample", "(Lcom/netinsight/sye/syeClient/video/SyeVideoSample;)V", "Lcom/netinsight/sye/syeClient/notification/SyeNotificationMessage;", "syeNotificationMessage", "onNotificationMessage", "(Lcom/netinsight/sye/syeClient/notification/SyeNotificationMessage;)V", "jsonArray", "onAvailableClosedCaptionsChannels", "onAvailableClosedCaptionsServices", "onCEA708", "onTimelineUpdate", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public interface ISyePlayerCallbackWrapper {
    void onAudioSample(SyeAudioSample syeAudioSample);

    void onAudioStreamChange(String jsonString);

    void onAvailableAudioTracks(String jsonString);

    void onAvailableClosedCaptionsChannels(String jsonArray);

    void onAvailableClosedCaptionsServices(String jsonArray);

    void onCEA708(String jsonString);

    void onEgressAllocated(int allocationTimeMillis);

    void onEgressContact(int timeToResponseMillis);

    void onError(int errorCode, String message);

    void onErrorRetry(int errorCode, String message, int retryAfterMillis);

    void onFrontendError(int errorCode, String jsonString, String message);

    void onFrontendSuccess(String jsonString);

    void onNotificationMessage(SyeNotificationMessage syeNotificationMessage);

    void onStateChange(int fromStateCode, int toStateCode);

    void onStopInternal();

    void onStreamingError(int errorCode, String message);

    void onTimeToFirstFrame(int egressTTFFMillis, int totalTTFFMillis);

    void onTimelineUpdate(String jsonString);

    void onVideoSample(SyeVideoSample syeVideoSample);

    void onVideoStreamChange(String jsonString);
}
