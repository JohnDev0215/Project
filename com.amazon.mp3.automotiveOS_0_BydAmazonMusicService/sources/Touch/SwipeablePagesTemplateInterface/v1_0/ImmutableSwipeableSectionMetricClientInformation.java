package Touch.SwipeablePagesTemplateInterface.v1_0;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/* JADX INFO: loaded from: classes.dex */
@SuppressFBWarnings
@ParametersAreNonnullByDefault
@Immutable
@Generated(from = "SwipeableSectionMetricClientInformation", generator = "Immutables")
public final class ImmutableSwipeableSectionMetricClientInformation extends SwipeableSectionMetricClientInformation {

    @Nullable
    private final ImpressionEndReason impressionEndReason;

    @Nullable
    private final String impressionEndTimestamp;

    @Nullable
    private final String impressionTimestamp;

    private ImmutableSwipeableSectionMetricClientInformation(@Nullable String str, @Nullable String str2, @Nullable ImpressionEndReason impressionEndReason) {
        this.impressionTimestamp = str;
        this.impressionEndTimestamp = str2;
        this.impressionEndReason = impressionEndReason;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.SwipeableSectionMetricClientInformation
    @JsonProperty("impressionTimestamp")
    @Nullable
    public String impressionTimestamp() {
        return this.impressionTimestamp;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.SwipeableSectionMetricClientInformation
    @JsonProperty("impressionEndTimestamp")
    @Nullable
    public String impressionEndTimestamp() {
        return this.impressionEndTimestamp;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.SwipeableSectionMetricClientInformation
    @JsonProperty("impressionEndReason")
    @Nullable
    public ImpressionEndReason impressionEndReason() {
        return this.impressionEndReason;
    }

    public final ImmutableSwipeableSectionMetricClientInformation withImpressionTimestamp(@Nullable String str) {
        return equals(this.impressionTimestamp, str) ? this : new ImmutableSwipeableSectionMetricClientInformation(str, this.impressionEndTimestamp, this.impressionEndReason);
    }

    public final ImmutableSwipeableSectionMetricClientInformation withImpressionEndTimestamp(@Nullable String str) {
        return equals(this.impressionEndTimestamp, str) ? this : new ImmutableSwipeableSectionMetricClientInformation(this.impressionTimestamp, str, this.impressionEndReason);
    }

    public final ImmutableSwipeableSectionMetricClientInformation withImpressionEndReason(@Nullable ImpressionEndReason impressionEndReason) {
        return this.impressionEndReason == impressionEndReason ? this : new ImmutableSwipeableSectionMetricClientInformation(this.impressionTimestamp, this.impressionEndTimestamp, impressionEndReason);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSwipeableSectionMetricClientInformation) && equalTo(0, (ImmutableSwipeableSectionMetricClientInformation) obj);
    }

    private boolean equalTo(int i, ImmutableSwipeableSectionMetricClientInformation immutableSwipeableSectionMetricClientInformation) {
        return equals(this.impressionTimestamp, immutableSwipeableSectionMetricClientInformation.impressionTimestamp) && equals(this.impressionEndTimestamp, immutableSwipeableSectionMetricClientInformation.impressionEndTimestamp) && equals(this.impressionEndReason, immutableSwipeableSectionMetricClientInformation.impressionEndReason);
    }

    public int hashCode() {
        int iHashCode = 172192 + hashCode(this.impressionTimestamp) + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + hashCode(this.impressionEndTimestamp);
        return iHashCode2 + (iHashCode2 << 5) + hashCode(this.impressionEndReason);
    }

    public String toString() {
        return "SwipeableSectionMetricClientInformation{impressionTimestamp=" + this.impressionTimestamp + ", impressionEndTimestamp=" + this.impressionEndTimestamp + ", impressionEndReason=" + this.impressionEndReason + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SwipeableSectionMetricClientInformation", generator = "Immutables")
    static final class Json extends SwipeableSectionMetricClientInformation {

        @Nullable
        ImpressionEndReason impressionEndReason;

        @Nullable
        String impressionEndTimestamp;

        @Nullable
        String impressionTimestamp;

        Json() {
        }

        @JsonProperty("impressionTimestamp")
        public void setImpressionTimestamp(@Nullable String str) {
            this.impressionTimestamp = str;
        }

        @JsonProperty("impressionEndTimestamp")
        public void setImpressionEndTimestamp(@Nullable String str) {
            this.impressionEndTimestamp = str;
        }

        @JsonProperty("impressionEndReason")
        public void setImpressionEndReason(@Nullable ImpressionEndReason impressionEndReason) {
            this.impressionEndReason = impressionEndReason;
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.SwipeableSectionMetricClientInformation
        public String impressionTimestamp() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.SwipeableSectionMetricClientInformation
        public String impressionEndTimestamp() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.SwipeableSectionMetricClientInformation
        public ImpressionEndReason impressionEndReason() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSwipeableSectionMetricClientInformation fromJson(Json json) {
        Builder builder = builder();
        String str = json.impressionTimestamp;
        if (str != null) {
            builder.impressionTimestamp(str);
        }
        String str2 = json.impressionEndTimestamp;
        if (str2 != null) {
            builder.impressionEndTimestamp(str2);
        }
        ImpressionEndReason impressionEndReason = json.impressionEndReason;
        if (impressionEndReason != null) {
            builder.impressionEndReason(impressionEndReason);
        }
        return builder.build();
    }

    public static ImmutableSwipeableSectionMetricClientInformation copyOf(SwipeableSectionMetricClientInformation swipeableSectionMetricClientInformation) {
        if (swipeableSectionMetricClientInformation instanceof ImmutableSwipeableSectionMetricClientInformation) {
            return (ImmutableSwipeableSectionMetricClientInformation) swipeableSectionMetricClientInformation;
        }
        return builder().from(swipeableSectionMetricClientInformation).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SwipeableSectionMetricClientInformation", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {

        @Nullable
        private ImpressionEndReason impressionEndReason;

        @Nullable
        private String impressionEndTimestamp;

        @Nullable
        private String impressionTimestamp;

        private Builder() {
        }

        public final Builder from(SwipeableSectionMetricClientInformation swipeableSectionMetricClientInformation) {
            ImmutableSwipeableSectionMetricClientInformation.requireNonNull(swipeableSectionMetricClientInformation, "instance");
            String strImpressionTimestamp = swipeableSectionMetricClientInformation.impressionTimestamp();
            if (strImpressionTimestamp != null) {
                impressionTimestamp(strImpressionTimestamp);
            }
            String strImpressionEndTimestamp = swipeableSectionMetricClientInformation.impressionEndTimestamp();
            if (strImpressionEndTimestamp != null) {
                impressionEndTimestamp(strImpressionEndTimestamp);
            }
            ImpressionEndReason impressionEndReason = swipeableSectionMetricClientInformation.impressionEndReason();
            if (impressionEndReason != null) {
                impressionEndReason(impressionEndReason);
            }
            return this;
        }

        @JsonProperty("impressionTimestamp")
        public final Builder impressionTimestamp(@Nullable String str) {
            this.impressionTimestamp = str;
            return this;
        }

        @JsonProperty("impressionEndTimestamp")
        public final Builder impressionEndTimestamp(@Nullable String str) {
            this.impressionEndTimestamp = str;
            return this;
        }

        @JsonProperty("impressionEndReason")
        public final Builder impressionEndReason(@Nullable ImpressionEndReason impressionEndReason) {
            this.impressionEndReason = impressionEndReason;
            return this;
        }

        public ImmutableSwipeableSectionMetricClientInformation build() {
            return new ImmutableSwipeableSectionMetricClientInformation(this.impressionTimestamp, this.impressionEndTimestamp, this.impressionEndReason);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    private static int hashCode(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    private static boolean equals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }
}
