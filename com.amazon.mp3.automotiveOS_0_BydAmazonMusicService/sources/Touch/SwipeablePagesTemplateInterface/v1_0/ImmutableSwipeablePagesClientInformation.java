package Touch.SwipeablePagesTemplateInterface.v1_0;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/* JADX INFO: loaded from: classes.dex */
@SuppressFBWarnings
@ParametersAreNonnullByDefault
@Immutable
@Generated(from = "SwipeablePagesClientInformation", generator = "Immutables")
public final class ImmutableSwipeablePagesClientInformation extends SwipeablePagesClientInformation {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private volatile transient InitShim initShim;
    private final boolean isChartsV3Enabled;
    private final boolean isStageEnabled;

    private ImmutableSwipeablePagesClientInformation(Builder builder) {
        this.initShim = new InitShim();
        if (builder.isStageEnabledIsSet()) {
            this.initShim.isStageEnabled(builder.isStageEnabled);
        }
        if (builder.isChartsV3EnabledIsSet()) {
            this.initShim.isChartsV3Enabled(builder.isChartsV3Enabled);
        }
        this.isStageEnabled = this.initShim.isStageEnabled();
        this.isChartsV3Enabled = this.initShim.isChartsV3Enabled();
        this.initShim = null;
    }

    private ImmutableSwipeablePagesClientInformation(boolean z, boolean z2) {
        this.initShim = new InitShim();
        this.isStageEnabled = z;
        this.isChartsV3Enabled = z2;
        this.initShim = null;
    }

    @Generated(from = "SwipeablePagesClientInformation", generator = "Immutables")
    private final class InitShim {
        private boolean isChartsV3Enabled;
        private byte isChartsV3EnabledBuildStage;
        private boolean isStageEnabled;
        private byte isStageEnabledBuildStage;

        private InitShim() {
            this.isStageEnabledBuildStage = (byte) 0;
            this.isChartsV3EnabledBuildStage = (byte) 0;
        }

        boolean isStageEnabled() {
            byte b = this.isStageEnabledBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isStageEnabledBuildStage = (byte) -1;
                this.isStageEnabled = ImmutableSwipeablePagesClientInformation.super.isStageEnabled();
                this.isStageEnabledBuildStage = (byte) 1;
            }
            return this.isStageEnabled;
        }

        void isStageEnabled(boolean z) {
            this.isStageEnabled = z;
            this.isStageEnabledBuildStage = (byte) 1;
        }

        boolean isChartsV3Enabled() {
            byte b = this.isChartsV3EnabledBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.isChartsV3EnabledBuildStage = (byte) -1;
                this.isChartsV3Enabled = ImmutableSwipeablePagesClientInformation.super.isChartsV3Enabled();
                this.isChartsV3EnabledBuildStage = (byte) 1;
            }
            return this.isChartsV3Enabled;
        }

        void isChartsV3Enabled(boolean z) {
            this.isChartsV3Enabled = z;
            this.isChartsV3EnabledBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.isStageEnabledBuildStage == -1) {
                arrayList.add("isStageEnabled");
            }
            if (this.isChartsV3EnabledBuildStage == -1) {
                arrayList.add("isChartsV3Enabled");
            }
            return "Cannot build SwipeablePagesClientInformation, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePagesClientInformation
    @JsonProperty("isStageEnabled")
    public boolean isStageEnabled() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isStageEnabled();
        }
        return this.isStageEnabled;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePagesClientInformation
    @JsonProperty("isChartsV3Enabled")
    public boolean isChartsV3Enabled() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.isChartsV3Enabled();
        }
        return this.isChartsV3Enabled;
    }

    public final ImmutableSwipeablePagesClientInformation withIsStageEnabled(boolean z) {
        return this.isStageEnabled == z ? this : new ImmutableSwipeablePagesClientInformation(z, this.isChartsV3Enabled);
    }

    public final ImmutableSwipeablePagesClientInformation withIsChartsV3Enabled(boolean z) {
        return this.isChartsV3Enabled == z ? this : new ImmutableSwipeablePagesClientInformation(this.isStageEnabled, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSwipeablePagesClientInformation) && equalTo(0, (ImmutableSwipeablePagesClientInformation) obj);
    }

    private boolean equalTo(int i, ImmutableSwipeablePagesClientInformation immutableSwipeablePagesClientInformation) {
        return this.isStageEnabled == immutableSwipeablePagesClientInformation.isStageEnabled && this.isChartsV3Enabled == immutableSwipeablePagesClientInformation.isChartsV3Enabled;
    }

    public int hashCode() {
        int i = 172192 + (this.isStageEnabled ? 1231 : 1237) + 5381;
        return i + (i << 5) + (this.isChartsV3Enabled ? 1231 : 1237);
    }

    public String toString() {
        return "SwipeablePagesClientInformation{isStageEnabled=" + this.isStageEnabled + ", isChartsV3Enabled=" + this.isChartsV3Enabled + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SwipeablePagesClientInformation", generator = "Immutables")
    static final class Json extends SwipeablePagesClientInformation {
        boolean isChartsV3Enabled;
        boolean isChartsV3EnabledIsSet;
        boolean isStageEnabled;
        boolean isStageEnabledIsSet;

        Json() {
        }

        @JsonProperty("isStageEnabled")
        public void setIsStageEnabled(boolean z) {
            this.isStageEnabled = z;
            this.isStageEnabledIsSet = true;
        }

        @JsonProperty("isChartsV3Enabled")
        public void setIsChartsV3Enabled(boolean z) {
            this.isChartsV3Enabled = z;
            this.isChartsV3EnabledIsSet = true;
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePagesClientInformation
        public boolean isStageEnabled() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePagesClientInformation
        public boolean isChartsV3Enabled() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSwipeablePagesClientInformation fromJson(Json json) {
        Builder builder = builder();
        if (json.isStageEnabledIsSet) {
            builder.isStageEnabled(json.isStageEnabled);
        }
        if (json.isChartsV3EnabledIsSet) {
            builder.isChartsV3Enabled(json.isChartsV3Enabled);
        }
        return builder.build();
    }

    public static ImmutableSwipeablePagesClientInformation copyOf(SwipeablePagesClientInformation swipeablePagesClientInformation) {
        if (swipeablePagesClientInformation instanceof ImmutableSwipeablePagesClientInformation) {
            return (ImmutableSwipeablePagesClientInformation) swipeablePagesClientInformation;
        }
        return builder().from(swipeablePagesClientInformation).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SwipeablePagesClientInformation", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_IS_CHARTS_V3_ENABLED = 2;
        private static final long OPT_BIT_IS_STAGE_ENABLED = 1;
        private boolean isChartsV3Enabled;
        private boolean isStageEnabled;
        private long optBits;

        private Builder() {
        }

        public final Builder from(SwipeablePagesClientInformation swipeablePagesClientInformation) {
            ImmutableSwipeablePagesClientInformation.requireNonNull(swipeablePagesClientInformation, "instance");
            isStageEnabled(swipeablePagesClientInformation.isStageEnabled());
            isChartsV3Enabled(swipeablePagesClientInformation.isChartsV3Enabled());
            return this;
        }

        @JsonProperty("isStageEnabled")
        public final Builder isStageEnabled(boolean z) {
            this.isStageEnabled = z;
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("isChartsV3Enabled")
        public final Builder isChartsV3Enabled(boolean z) {
            this.isChartsV3Enabled = z;
            this.optBits |= 2;
            return this;
        }

        public ImmutableSwipeablePagesClientInformation build() {
            return new ImmutableSwipeablePagesClientInformation(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isStageEnabledIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isChartsV3EnabledIsSet() {
            return (this.optBits & 2) != 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }
}
