package PageViewedMetricInterface.v1_0;

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
@Generated(from = "RefMarkerClientInformation", generator = "Immutables")
public final class ImmutableRefMarkerClientInformation extends RefMarkerClientInformation {
    private final String refMarker;

    private ImmutableRefMarkerClientInformation(String str) {
        this.refMarker = str;
    }

    @Override // PageViewedMetricInterface.v1_0.RefMarkerClientInformation
    @JsonProperty("refMarker")
    public String refMarker() {
        return this.refMarker;
    }

    public final ImmutableRefMarkerClientInformation withRefMarker(String str) {
        String str2 = (String) requireNonNull(str, "refMarker");
        return this.refMarker.equals(str2) ? this : new ImmutableRefMarkerClientInformation(str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableRefMarkerClientInformation) && equalTo(0, (ImmutableRefMarkerClientInformation) obj);
    }

    private boolean equalTo(int i, ImmutableRefMarkerClientInformation immutableRefMarkerClientInformation) {
        return this.refMarker.equals(immutableRefMarkerClientInformation.refMarker);
    }

    public int hashCode() {
        return 172192 + this.refMarker.hashCode() + 5381;
    }

    public String toString() {
        return "RefMarkerClientInformation{refMarker=" + this.refMarker + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "RefMarkerClientInformation", generator = "Immutables")
    static final class Json extends RefMarkerClientInformation {

        @Nullable
        String refMarker;

        Json() {
        }

        @JsonProperty("refMarker")
        public void setRefMarker(String str) {
            this.refMarker = str;
        }

        @Override // PageViewedMetricInterface.v1_0.RefMarkerClientInformation
        public String refMarker() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableRefMarkerClientInformation fromJson(Json json) {
        Builder builder = builder();
        String str = json.refMarker;
        if (str != null) {
            builder.refMarker(str);
        }
        return builder.build();
    }

    public static ImmutableRefMarkerClientInformation copyOf(RefMarkerClientInformation refMarkerClientInformation) {
        if (refMarkerClientInformation instanceof ImmutableRefMarkerClientInformation) {
            return (ImmutableRefMarkerClientInformation) refMarkerClientInformation;
        }
        return builder().from(refMarkerClientInformation).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "RefMarkerClientInformation", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_REF_MARKER = 1;
        private long initBits;

        @Nullable
        private String refMarker;

        private Builder() {
            this.initBits = 1L;
        }

        public final Builder from(RefMarkerClientInformation refMarkerClientInformation) {
            ImmutableRefMarkerClientInformation.requireNonNull(refMarkerClientInformation, "instance");
            refMarker(refMarkerClientInformation.refMarker());
            return this;
        }

        @JsonProperty("refMarker")
        public final Builder refMarker(String str) {
            this.refMarker = (String) ImmutableRefMarkerClientInformation.requireNonNull(str, "refMarker");
            this.initBits &= -2;
            return this;
        }

        public ImmutableRefMarkerClientInformation build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableRefMarkerClientInformation(this.refMarker);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("refMarker");
            }
            return "Cannot build RefMarkerClientInformation, some of required attributes are not set " + arrayList;
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
