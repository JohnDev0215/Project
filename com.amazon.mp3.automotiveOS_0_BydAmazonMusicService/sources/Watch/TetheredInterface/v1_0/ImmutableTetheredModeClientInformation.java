package Watch.TetheredInterface.v1_0;

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
@Generated(from = "TetheredModeClientInformation", generator = "Immutables")
public final class ImmutableTetheredModeClientInformation extends TetheredModeClientInformation {
    private final Boolean isTethered;

    private ImmutableTetheredModeClientInformation(Boolean bool) {
        this.isTethered = bool;
    }

    @Override // Watch.TetheredInterface.v1_0.TetheredModeClientInformation
    @JsonProperty("isTethered")
    public Boolean isTethered() {
        return this.isTethered;
    }

    public final ImmutableTetheredModeClientInformation withIsTethered(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "isTethered");
        return this.isTethered.equals(bool2) ? this : new ImmutableTetheredModeClientInformation(bool2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableTetheredModeClientInformation) && equalTo(0, (ImmutableTetheredModeClientInformation) obj);
    }

    private boolean equalTo(int i, ImmutableTetheredModeClientInformation immutableTetheredModeClientInformation) {
        return this.isTethered.equals(immutableTetheredModeClientInformation.isTethered);
    }

    public int hashCode() {
        return 172192 + this.isTethered.hashCode() + 5381;
    }

    public String toString() {
        return "TetheredModeClientInformation{isTethered=" + this.isTethered + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "TetheredModeClientInformation", generator = "Immutables")
    static final class Json extends TetheredModeClientInformation {

        @Nullable
        Boolean isTethered;

        Json() {
        }

        @JsonProperty("isTethered")
        public void setIsTethered(Boolean bool) {
            this.isTethered = bool;
        }

        @Override // Watch.TetheredInterface.v1_0.TetheredModeClientInformation
        public Boolean isTethered() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableTetheredModeClientInformation fromJson(Json json) {
        Builder builder = builder();
        Boolean bool = json.isTethered;
        if (bool != null) {
            builder.isTethered(bool);
        }
        return builder.build();
    }

    public static ImmutableTetheredModeClientInformation copyOf(TetheredModeClientInformation tetheredModeClientInformation) {
        if (tetheredModeClientInformation instanceof ImmutableTetheredModeClientInformation) {
            return (ImmutableTetheredModeClientInformation) tetheredModeClientInformation;
        }
        return builder().from(tetheredModeClientInformation).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "TetheredModeClientInformation", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_IS_TETHERED = 1;
        private long initBits;

        @Nullable
        private Boolean isTethered;

        private Builder() {
            this.initBits = 1L;
        }

        public final Builder from(TetheredModeClientInformation tetheredModeClientInformation) {
            ImmutableTetheredModeClientInformation.requireNonNull(tetheredModeClientInformation, "instance");
            isTethered(tetheredModeClientInformation.isTethered());
            return this;
        }

        @JsonProperty("isTethered")
        public final Builder isTethered(Boolean bool) {
            this.isTethered = (Boolean) ImmutableTetheredModeClientInformation.requireNonNull(bool, "isTethered");
            this.initBits &= -2;
            return this;
        }

        public ImmutableTetheredModeClientInformation build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableTetheredModeClientInformation(this.isTethered);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("isTethered");
            }
            return "Cannot build TetheredModeClientInformation, some of required attributes are not set " + arrayList;
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
