package PlaybackInterface.v1_0;

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
@Generated(from = "StatsForNerdsClientInformation", generator = "Immutables")
public final class ImmutableStatsForNerdsClientInformation extends StatsForNerdsClientInformation {
    private final Integer bitDepth;
    private final Double sampleRate;

    private ImmutableStatsForNerdsClientInformation(Integer num, Double d) {
        this.bitDepth = num;
        this.sampleRate = d;
    }

    @Override // PlaybackInterface.v1_0.StatsForNerdsClientInformation
    @JsonProperty("bitDepth")
    public Integer bitDepth() {
        return this.bitDepth;
    }

    @Override // PlaybackInterface.v1_0.StatsForNerdsClientInformation
    @JsonProperty("sampleRate")
    public Double sampleRate() {
        return this.sampleRate;
    }

    public final ImmutableStatsForNerdsClientInformation withBitDepth(Integer num) {
        Integer num2 = (Integer) requireNonNull(num, "bitDepth");
        return this.bitDepth.equals(num2) ? this : new ImmutableStatsForNerdsClientInformation(num2, this.sampleRate);
    }

    public final ImmutableStatsForNerdsClientInformation withSampleRate(Double d) {
        Double d2 = (Double) requireNonNull(d, "sampleRate");
        return this.sampleRate.equals(d2) ? this : new ImmutableStatsForNerdsClientInformation(this.bitDepth, d2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableStatsForNerdsClientInformation) && equalTo(0, (ImmutableStatsForNerdsClientInformation) obj);
    }

    private boolean equalTo(int i, ImmutableStatsForNerdsClientInformation immutableStatsForNerdsClientInformation) {
        return this.bitDepth.equals(immutableStatsForNerdsClientInformation.bitDepth) && this.sampleRate.equals(immutableStatsForNerdsClientInformation.sampleRate);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.bitDepth.hashCode() + 5381;
        return iHashCode + (iHashCode << 5) + this.sampleRate.hashCode();
    }

    public String toString() {
        return "StatsForNerdsClientInformation{bitDepth=" + this.bitDepth + ", sampleRate=" + this.sampleRate + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "StatsForNerdsClientInformation", generator = "Immutables")
    static final class Json extends StatsForNerdsClientInformation {

        @Nullable
        Integer bitDepth;

        @Nullable
        Double sampleRate;

        Json() {
        }

        @JsonProperty("bitDepth")
        public void setBitDepth(Integer num) {
            this.bitDepth = num;
        }

        @JsonProperty("sampleRate")
        public void setSampleRate(Double d) {
            this.sampleRate = d;
        }

        @Override // PlaybackInterface.v1_0.StatsForNerdsClientInformation
        public Integer bitDepth() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.StatsForNerdsClientInformation
        public Double sampleRate() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableStatsForNerdsClientInformation fromJson(Json json) {
        Builder builder = builder();
        Integer num = json.bitDepth;
        if (num != null) {
            builder.bitDepth(num);
        }
        Double d = json.sampleRate;
        if (d != null) {
            builder.sampleRate(d);
        }
        return builder.build();
    }

    public static ImmutableStatsForNerdsClientInformation copyOf(StatsForNerdsClientInformation statsForNerdsClientInformation) {
        if (statsForNerdsClientInformation instanceof ImmutableStatsForNerdsClientInformation) {
            return (ImmutableStatsForNerdsClientInformation) statsForNerdsClientInformation;
        }
        return builder().from(statsForNerdsClientInformation).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "StatsForNerdsClientInformation", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_BIT_DEPTH = 1;
        private static final long INIT_BIT_SAMPLE_RATE = 2;

        @Nullable
        private Integer bitDepth;
        private long initBits;

        @Nullable
        private Double sampleRate;

        private Builder() {
            this.initBits = 3L;
        }

        public final Builder from(StatsForNerdsClientInformation statsForNerdsClientInformation) {
            ImmutableStatsForNerdsClientInformation.requireNonNull(statsForNerdsClientInformation, "instance");
            bitDepth(statsForNerdsClientInformation.bitDepth());
            sampleRate(statsForNerdsClientInformation.sampleRate());
            return this;
        }

        @JsonProperty("bitDepth")
        public final Builder bitDepth(Integer num) {
            this.bitDepth = (Integer) ImmutableStatsForNerdsClientInformation.requireNonNull(num, "bitDepth");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("sampleRate")
        public final Builder sampleRate(Double d) {
            this.sampleRate = (Double) ImmutableStatsForNerdsClientInformation.requireNonNull(d, "sampleRate");
            this.initBits &= -3;
            return this;
        }

        public ImmutableStatsForNerdsClientInformation build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableStatsForNerdsClientInformation(this.bitDepth, this.sampleRate);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("bitDepth");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("sampleRate");
            }
            return "Cannot build StatsForNerdsClientInformation, some of required attributes are not set " + arrayList;
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
