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
@Generated(from = "LoadTimeClientInformation", generator = "Immutables")
public final class ImmutableLoadTimeClientInformation extends LoadTimeClientInformation {
    private final String loadTimeMilliseconds;

    private ImmutableLoadTimeClientInformation(String str) {
        this.loadTimeMilliseconds = str;
    }

    @Override // PageViewedMetricInterface.v1_0.LoadTimeClientInformation
    @JsonProperty("loadTimeMilliseconds")
    public String loadTimeMilliseconds() {
        return this.loadTimeMilliseconds;
    }

    public final ImmutableLoadTimeClientInformation withLoadTimeMilliseconds(String str) {
        String str2 = (String) requireNonNull(str, "loadTimeMilliseconds");
        return this.loadTimeMilliseconds.equals(str2) ? this : new ImmutableLoadTimeClientInformation(str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableLoadTimeClientInformation) && equalTo(0, (ImmutableLoadTimeClientInformation) obj);
    }

    private boolean equalTo(int i, ImmutableLoadTimeClientInformation immutableLoadTimeClientInformation) {
        return this.loadTimeMilliseconds.equals(immutableLoadTimeClientInformation.loadTimeMilliseconds);
    }

    public int hashCode() {
        return 172192 + this.loadTimeMilliseconds.hashCode() + 5381;
    }

    public String toString() {
        return "LoadTimeClientInformation{loadTimeMilliseconds=" + this.loadTimeMilliseconds + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "LoadTimeClientInformation", generator = "Immutables")
    static final class Json extends LoadTimeClientInformation {

        @Nullable
        String loadTimeMilliseconds;

        Json() {
        }

        @JsonProperty("loadTimeMilliseconds")
        public void setLoadTimeMilliseconds(String str) {
            this.loadTimeMilliseconds = str;
        }

        @Override // PageViewedMetricInterface.v1_0.LoadTimeClientInformation
        public String loadTimeMilliseconds() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableLoadTimeClientInformation fromJson(Json json) {
        Builder builder = builder();
        String str = json.loadTimeMilliseconds;
        if (str != null) {
            builder.loadTimeMilliseconds(str);
        }
        return builder.build();
    }

    public static ImmutableLoadTimeClientInformation copyOf(LoadTimeClientInformation loadTimeClientInformation) {
        if (loadTimeClientInformation instanceof ImmutableLoadTimeClientInformation) {
            return (ImmutableLoadTimeClientInformation) loadTimeClientInformation;
        }
        return builder().from(loadTimeClientInformation).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "LoadTimeClientInformation", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_LOAD_TIME_MILLISECONDS = 1;
        private long initBits;

        @Nullable
        private String loadTimeMilliseconds;

        private Builder() {
            this.initBits = 1L;
        }

        public final Builder from(LoadTimeClientInformation loadTimeClientInformation) {
            ImmutableLoadTimeClientInformation.requireNonNull(loadTimeClientInformation, "instance");
            loadTimeMilliseconds(loadTimeClientInformation.loadTimeMilliseconds());
            return this;
        }

        @JsonProperty("loadTimeMilliseconds")
        public final Builder loadTimeMilliseconds(String str) {
            this.loadTimeMilliseconds = (String) ImmutableLoadTimeClientInformation.requireNonNull(str, "loadTimeMilliseconds");
            this.initBits &= -2;
            return this;
        }

        public ImmutableLoadTimeClientInformation build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableLoadTimeClientInformation(this.loadTimeMilliseconds);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("loadTimeMilliseconds");
            }
            return "Cannot build LoadTimeClientInformation, some of required attributes are not set " + arrayList;
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
