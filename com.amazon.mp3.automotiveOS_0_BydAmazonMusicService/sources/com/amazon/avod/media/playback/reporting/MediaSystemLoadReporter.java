package com.amazon.avod.media.playback.reporting;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.util.DisplayMetrics;
import com.amazon.avod.media.playback.support.DeviceCapabilityDetector;
import com.amazon.avod.media.playback.support.PlaybackSupportInfo;
import com.amazon.avod.media.playback.support.RendererScheme;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.playback.capability.DeviceCapability;
import com.amazon.avod.qahooks.PlaybackQAEvent;
import com.amazon.avod.qahooks.PlaybackQAMetric;
import com.amazon.avod.qahooks.QALog;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class MediaSystemLoadReporter {
    private final Configuration mConfiguration;
    private final DisplayMetrics mDisplayMetrics;
    private final PlaybackEventReporter mEventReporter;

    public MediaSystemLoadReporter(@Nonnull Context context, @Nonnull EventReporterFactory eventReporterFactory, @Nonnull String str) {
        this(eventReporterFactory.newStandaloneEventReporter(str), context.getResources().getConfiguration(), context.getResources().getDisplayMetrics());
    }

    public MediaSystemLoadReporter(@Nonnull PlaybackEventReporter playbackEventReporter, @Nonnull Configuration configuration, @Nonnull DisplayMetrics displayMetrics) {
        this.mEventReporter = (PlaybackEventReporter) Preconditions.checkNotNull(playbackEventReporter);
        this.mConfiguration = (Configuration) Preconditions.checkNotNull(configuration);
        this.mDisplayMetrics = (DisplayMetrics) Preconditions.checkNotNull(displayMetrics);
    }

    public void reportLoadMetric(@Nonnull ImmutableList<RendererScheme> immutableList, @Nonnull String str) {
        Preconditions.checkNotNull(immutableList, "rendererSchemes");
        Preconditions.checkState(immutableList.size() >= 1, "rendererSchemes must contain one or more schemes");
        Preconditions.checkNotNull(str);
        DeviceCapabilityDetector capabilityDetector = immutableList.get(0).getCapabilityDetector();
        DeviceCapability deviceCapability = capabilityDetector.getDeviceCapability();
        if (deviceCapability == null) {
            DLog.warnf("Failed to get device capabilities.");
        }
        StringBuilder sb = new StringBuilder();
        UnmodifiableIterator<RendererScheme> it = immutableList.iterator();
        while (it.hasNext()) {
            sb.append(it.next().getClass().getSimpleName());
            sb.append(";");
        }
        this.mEventReporter.reportMetric("AppLoad", sb.toString(), null, getNoteField(capabilityDetector, deviceCapability, str), null);
        Profiler.incrementCounter(String.format(Locale.US, "%s-%s", "MediaSystem-LoadSuccess", sb.toString()));
        QALog.newQALog(PlaybackQAEvent.MEDIASYSTEM_INITIALIZE).addMetric((QALog.QALoggableMetric) PlaybackQAMetric.SUCCESS, true).addMetric(PlaybackQAMetric.RENDERER_SCHEME, sb).send();
    }

    @Nonnull
    private String getNoteField(@Nonnull DeviceCapabilityDetector deviceCapabilityDetector, @Nullable DeviceCapability deviceCapability, @Nonnull String str) {
        PlaybackSupportInfo playbackSupportInfo = deviceCapabilityDetector.getPlaybackSupportInfo();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("fp", Build.FINGERPRINT);
            jSONObject.put("resPath", str);
            jSONObject.put("cores", deviceCapability == null ? 0 : deviceCapability.getNumberOfCores());
            jSONObject.put("freq", deviceCapability == null ? 0 : deviceCapability.getCoreFrequency());
            jSONObject.put("neon", deviceCapability == null ? false : deviceCapability.getNeonSupport());
            jSONObject.put("hwAcc", deviceCapability == null ? false : deviceCapability.getHardwareAcceleration());
            jSONObject.put("supp", playbackSupportInfo.isPlaybackSupported());
            jSONObject.put("sust", playbackSupportInfo.isPlaybackSustainable());
            jSONObject.put("pbkInfo", playbackSupportInfo.name());
            jSONObject.put("maxBit", deviceCapability == null ? 0 : deviceCapabilityDetector.getHighestSustainableBitrate());
            jSONObject.put("hw", Build.HARDWARE);
            jSONObject.put("dpi", this.mDisplayMetrics.densityDpi);
            jSONObject.put("font", String.format(Locale.US, "%.2f", Float.valueOf(this.mConfiguration.fontScale)));
        } catch (JSONException e) {
            DLog.errorf("Failed to populate JSON object: %s", e);
        }
        return jSONObject.toString();
    }
}
