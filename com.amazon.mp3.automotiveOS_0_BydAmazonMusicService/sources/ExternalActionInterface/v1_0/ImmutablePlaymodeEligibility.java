package ExternalActionInterface.v1_0;

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
@Generated(from = "PlaymodeEligibility", generator = "Immutables")
public final class ImmutablePlaymodeEligibility extends PlaymodeEligibility {
    private final boolean canPlayAugmentedShuffle;
    private final boolean canPlayOnDemand;
    private final boolean canPlaySFA;
    private final boolean canPlayShuffle;

    private ImmutablePlaymodeEligibility(boolean z, boolean z2, boolean z3, boolean z4) {
        this.canPlayOnDemand = z;
        this.canPlayShuffle = z2;
        this.canPlayAugmentedShuffle = z3;
        this.canPlaySFA = z4;
    }

    @Override // ExternalActionInterface.v1_0.PlaymodeEligibility
    @JsonProperty("canPlayOnDemand")
    public boolean canPlayOnDemand() {
        return this.canPlayOnDemand;
    }

    @Override // ExternalActionInterface.v1_0.PlaymodeEligibility
    @JsonProperty("canPlayShuffle")
    public boolean canPlayShuffle() {
        return this.canPlayShuffle;
    }

    @Override // ExternalActionInterface.v1_0.PlaymodeEligibility
    @JsonProperty("canPlayAugmentedShuffle")
    public boolean canPlayAugmentedShuffle() {
        return this.canPlayAugmentedShuffle;
    }

    @Override // ExternalActionInterface.v1_0.PlaymodeEligibility
    @JsonProperty("canPlaySFA")
    public boolean canPlaySFA() {
        return this.canPlaySFA;
    }

    public final ImmutablePlaymodeEligibility withCanPlayOnDemand(boolean z) {
        return this.canPlayOnDemand == z ? this : new ImmutablePlaymodeEligibility(z, this.canPlayShuffle, this.canPlayAugmentedShuffle, this.canPlaySFA);
    }

    public final ImmutablePlaymodeEligibility withCanPlayShuffle(boolean z) {
        return this.canPlayShuffle == z ? this : new ImmutablePlaymodeEligibility(this.canPlayOnDemand, z, this.canPlayAugmentedShuffle, this.canPlaySFA);
    }

    public final ImmutablePlaymodeEligibility withCanPlayAugmentedShuffle(boolean z) {
        return this.canPlayAugmentedShuffle == z ? this : new ImmutablePlaymodeEligibility(this.canPlayOnDemand, this.canPlayShuffle, z, this.canPlaySFA);
    }

    public final ImmutablePlaymodeEligibility withCanPlaySFA(boolean z) {
        return this.canPlaySFA == z ? this : new ImmutablePlaymodeEligibility(this.canPlayOnDemand, this.canPlayShuffle, this.canPlayAugmentedShuffle, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutablePlaymodeEligibility) && equalTo(0, (ImmutablePlaymodeEligibility) obj);
    }

    private boolean equalTo(int i, ImmutablePlaymodeEligibility immutablePlaymodeEligibility) {
        return this.canPlayOnDemand == immutablePlaymodeEligibility.canPlayOnDemand && this.canPlayShuffle == immutablePlaymodeEligibility.canPlayShuffle && this.canPlayAugmentedShuffle == immutablePlaymodeEligibility.canPlayAugmentedShuffle && this.canPlaySFA == immutablePlaymodeEligibility.canPlaySFA;
    }

    public int hashCode() {
        int i = 172192 + (this.canPlayOnDemand ? 1231 : 1237) + 5381;
        int i2 = i + (i << 5) + (this.canPlayShuffle ? 1231 : 1237);
        int i3 = i2 + (i2 << 5) + (this.canPlayAugmentedShuffle ? 1231 : 1237);
        return i3 + (i3 << 5) + (this.canPlaySFA ? 1231 : 1237);
    }

    public String toString() {
        return "PlaymodeEligibility{canPlayOnDemand=" + this.canPlayOnDemand + ", canPlayShuffle=" + this.canPlayShuffle + ", canPlayAugmentedShuffle=" + this.canPlayAugmentedShuffle + ", canPlaySFA=" + this.canPlaySFA + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "PlaymodeEligibility", generator = "Immutables")
    static final class Json extends PlaymodeEligibility {
        boolean canPlayAugmentedShuffle;
        boolean canPlayAugmentedShuffleIsSet;
        boolean canPlayOnDemand;
        boolean canPlayOnDemandIsSet;
        boolean canPlaySFA;
        boolean canPlaySFAIsSet;
        boolean canPlayShuffle;
        boolean canPlayShuffleIsSet;

        Json() {
        }

        @JsonProperty("canPlayOnDemand")
        public void setCanPlayOnDemand(boolean z) {
            this.canPlayOnDemand = z;
            this.canPlayOnDemandIsSet = true;
        }

        @JsonProperty("canPlayShuffle")
        public void setCanPlayShuffle(boolean z) {
            this.canPlayShuffle = z;
            this.canPlayShuffleIsSet = true;
        }

        @JsonProperty("canPlayAugmentedShuffle")
        public void setCanPlayAugmentedShuffle(boolean z) {
            this.canPlayAugmentedShuffle = z;
            this.canPlayAugmentedShuffleIsSet = true;
        }

        @JsonProperty("canPlaySFA")
        public void setCanPlaySFA(boolean z) {
            this.canPlaySFA = z;
            this.canPlaySFAIsSet = true;
        }

        @Override // ExternalActionInterface.v1_0.PlaymodeEligibility
        public boolean canPlayOnDemand() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.PlaymodeEligibility
        public boolean canPlayShuffle() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.PlaymodeEligibility
        public boolean canPlayAugmentedShuffle() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.PlaymodeEligibility
        public boolean canPlaySFA() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutablePlaymodeEligibility fromJson(Json json) {
        Builder builder = builder();
        if (json.canPlayOnDemandIsSet) {
            builder.canPlayOnDemand(json.canPlayOnDemand);
        }
        if (json.canPlayShuffleIsSet) {
            builder.canPlayShuffle(json.canPlayShuffle);
        }
        if (json.canPlayAugmentedShuffleIsSet) {
            builder.canPlayAugmentedShuffle(json.canPlayAugmentedShuffle);
        }
        if (json.canPlaySFAIsSet) {
            builder.canPlaySFA(json.canPlaySFA);
        }
        return builder.build();
    }

    public static ImmutablePlaymodeEligibility copyOf(PlaymodeEligibility playmodeEligibility) {
        if (playmodeEligibility instanceof ImmutablePlaymodeEligibility) {
            return (ImmutablePlaymodeEligibility) playmodeEligibility;
        }
        return builder().from(playmodeEligibility).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "PlaymodeEligibility", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_CAN_PLAY_AUGMENTED_SHUFFLE = 4;
        private static final long INIT_BIT_CAN_PLAY_ON_DEMAND = 1;
        private static final long INIT_BIT_CAN_PLAY_SHUFFLE = 2;
        private static final long INIT_BIT_CAN_PLAY_S_F_A = 8;
        private boolean canPlayAugmentedShuffle;
        private boolean canPlayOnDemand;
        private boolean canPlaySFA;
        private boolean canPlayShuffle;
        private long initBits;

        private Builder() {
            this.initBits = 15L;
        }

        public final Builder from(PlaymodeEligibility playmodeEligibility) {
            ImmutablePlaymodeEligibility.requireNonNull(playmodeEligibility, "instance");
            canPlayOnDemand(playmodeEligibility.canPlayOnDemand());
            canPlayShuffle(playmodeEligibility.canPlayShuffle());
            canPlayAugmentedShuffle(playmodeEligibility.canPlayAugmentedShuffle());
            canPlaySFA(playmodeEligibility.canPlaySFA());
            return this;
        }

        @JsonProperty("canPlayOnDemand")
        public final Builder canPlayOnDemand(boolean z) {
            this.canPlayOnDemand = z;
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("canPlayShuffle")
        public final Builder canPlayShuffle(boolean z) {
            this.canPlayShuffle = z;
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("canPlayAugmentedShuffle")
        public final Builder canPlayAugmentedShuffle(boolean z) {
            this.canPlayAugmentedShuffle = z;
            this.initBits &= -5;
            return this;
        }

        @JsonProperty("canPlaySFA")
        public final Builder canPlaySFA(boolean z) {
            this.canPlaySFA = z;
            this.initBits &= -9;
            return this;
        }

        public ImmutablePlaymodeEligibility build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutablePlaymodeEligibility(this.canPlayOnDemand, this.canPlayShuffle, this.canPlayAugmentedShuffle, this.canPlaySFA);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("canPlayOnDemand");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("canPlayShuffle");
            }
            if ((this.initBits & 4) != 0) {
                arrayList.add("canPlayAugmentedShuffle");
            }
            if ((this.initBits & 8) != 0) {
                arrayList.add("canPlaySFA");
            }
            return "Cannot build PlaymodeEligibility, some of required attributes are not set " + arrayList;
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
