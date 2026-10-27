package com.amazon.avod.content.smoothstream;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.event.ContentFpsEvent;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.util.DLog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FpsReporter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00132\u00020\u0001:\u0002\u0013\u0014B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J \u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/avod/content/smoothstream/FpsReporter;", "Lcom/amazon/avod/content/smoothstream/FpsReporterInterface;", "eventTransport", "Lcom/amazon/avod/content/event/ContentManagementEventBus;", "(Lcom/amazon/avod/content/event/ContentManagementEventBus;)V", "shouldPostSingleContentFpsEvent", "", "(Lcom/amazon/avod/content/event/ContentManagementEventBus;Z)V", "postedOnce", "priorReportedFramesPerSecond", "", "processFragment", "", "context", "Lcom/amazon/avod/content/ContentSessionContext;", "fragmentUri", "Lcom/amazon/avod/content/smoothstream/SmoothStreamingURI;", "fragment", "Lcom/amazon/avod/content/smoothstream/Fragment;", "Companion", "FpsReporterConfig", "ATVPlaybackContent_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FpsReporter implements FpsReporterInterface {
    private final ContentManagementEventBus eventTransport;
    private boolean postedOnce;
    private double priorReportedFramesPerSecond;
    private final boolean shouldPostSingleContentFpsEvent;

    public FpsReporter(ContentManagementEventBus eventTransport, boolean z) {
        Intrinsics.checkNotNullParameter(eventTransport, "eventTransport");
        this.eventTransport = eventTransport;
        this.shouldPostSingleContentFpsEvent = z;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FpsReporter(ContentManagementEventBus eventTransport) {
        this(eventTransport, FpsReporterConfig.INSTANCE.getShouldPostSingleContentFpsEvent());
        Intrinsics.checkNotNullParameter(eventTransport, "eventTransport");
    }

    @Override // com.amazon.avod.content.smoothstream.FpsReporterInterface
    public void processFragment(ContentSessionContext context, SmoothStreamingURI fragmentUri, Fragment fragment) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fragmentUri, "fragmentUri");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        if (fragmentUri.getStream().isVideo()) {
            if (this.postedOnce && this.shouldPostSingleContentFpsEvent) {
                return;
            }
            int sampleCount = fragment.getSampleCount();
            float sampleDuration = 0.0f;
            float timeScale = fragmentUri.getQualityLevel() == null ? 1.0f : r9.getTimeScale();
            for (int i = 0; i < sampleCount; i++) {
                sampleDuration += fragment.getSampleDuration(i) / timeScale;
            }
            double dRint = Math.rint((((double) sampleCount) * 1000.0d) / ((double) sampleDuration)) / 1000.0d;
            if (dRint == this.priorReportedFramesPerSecond) {
                return;
            }
            DLog.devf("Reporting ContentFpsEvent with " + dRint + " FPS");
            this.eventTransport.postEvent(new ContentFpsEvent(context.getContent(), context.getSessionType(), dRint));
            this.priorReportedFramesPerSecond = dRint;
            this.postedOnce = true;
        }
    }

    /* JADX INFO: compiled from: FpsReporter.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/avod/content/smoothstream/FpsReporter$FpsReporterConfig;", "Lcom/amazon/avod/media/framework/config/MediaConfigBase;", "()V", "shouldPostSingleContentFpsEvent", "", "getShouldPostSingleContentFpsEvent", "()Z", "ATVPlaybackContent_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class FpsReporterConfig extends MediaConfigBase {
        public static final FpsReporterConfig INSTANCE;
        private static final boolean shouldPostSingleContentFpsEvent;

        private FpsReporterConfig() {
        }

        static {
            FpsReporterConfig fpsReporterConfig = new FpsReporterConfig();
            INSTANCE = fpsReporterConfig;
            Boolean value = fpsReporterConfig.newBooleanConfigValue("fpsReporter_shouldPostSingleContentFpsEvent", false).getValue();
            Intrinsics.checkNotNullExpressionValue(value, "newBooleanConfigValue(\n …ntFpsEvent\", false).value");
            shouldPostSingleContentFpsEvent = value.booleanValue();
        }

        public final boolean getShouldPostSingleContentFpsEvent() {
            return shouldPostSingleContentFpsEvent;
        }
    }
}
