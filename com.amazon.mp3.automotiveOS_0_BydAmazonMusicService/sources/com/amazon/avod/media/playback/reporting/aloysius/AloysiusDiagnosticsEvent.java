package com.amazon.avod.media.playback.reporting.aloysius;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.media.events.model.MediaEvent;
import com.amazon.avod.qos.reporter.AloysiusDiagnosticsState;
import com.amazon.avod.util.DLog;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
@VisibleForTesting
public class AloysiusDiagnosticsEvent implements MediaEvent {
    private static final int DIAGNOSTICS_LABEL_LENGTH = 60;
    private static final int DIAGNOSTICS_VALUE_LENGTH = 200;
    private final String mLabel;
    private final AloysiusDiagnosticsState mState;
    private final String mValue;

    private AloysiusDiagnosticsEvent(@Nonnull Builder builder) {
        Preconditions.checkNotNull(builder, "builder");
        this.mValue = builder.mValue;
        this.mLabel = builder.mLabel;
        this.mState = builder.mState;
    }

    @Nonnull
    public String getValue() {
        return this.mValue;
    }

    @Nonnull
    public String getLabel() {
        return this.mLabel;
    }

    public AloysiusDiagnosticsState getState() {
        return this.mState;
    }

    @Override // com.amazon.avod.media.events.model.MediaEvent
    @Nonnull
    public MediaEvent.MediaEventType getMediaEventType() {
        return MediaEvent.MediaEventType.Diagnostic;
    }

    public static class Builder {
        private String mLabel;
        private AloysiusDiagnosticsState mState;
        private String mValue;

        public Builder diagValue(@Nonnull String str) {
            Preconditions.checkNotNull(str, "value");
            if (str.length() > 200) {
                DLog.devf("Diagnostics value field is greater than allowed %d characters", 200);
            }
            this.mValue = str;
            return this;
        }

        public Builder diagLabel(@Nonnull String str) {
            Preconditions.checkNotNull(str, ContextMappingConstants.LABEL);
            if (str.length() > 60) {
                DLog.devf("Diagnostics label field is greater than allowed %d characters", 60);
            }
            this.mLabel = str;
            return this;
        }

        public Builder diagState(AloysiusDiagnosticsState aloysiusDiagnosticsState) {
            this.mState = aloysiusDiagnosticsState;
            return this;
        }

        @Nonnull
        public MediaEvent build() {
            return new AloysiusDiagnosticsEvent(this);
        }
    }
}
