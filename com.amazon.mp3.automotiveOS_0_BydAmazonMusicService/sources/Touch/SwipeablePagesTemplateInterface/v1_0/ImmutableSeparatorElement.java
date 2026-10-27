package Touch.SwipeablePagesTemplateInterface.v1_0;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
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
@Generated(from = "SeparatorElement", generator = "Immutables")
public final class ImmutableSeparatorElement extends SeparatorElement {
    private boolean equalTo(int i, ImmutableSeparatorElement immutableSeparatorElement) {
        return true;
    }

    public int hashCode() {
        return 1425793962;
    }

    public String toString() {
        return "SeparatorElement{}";
    }

    private ImmutableSeparatorElement(Builder builder) {
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSeparatorElement) && equalTo(0, (ImmutableSeparatorElement) obj);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SeparatorElement", generator = "Immutables")
    static final class Json extends SeparatorElement {
        Json() {
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSeparatorElement fromJson(Json json) {
        return builder().build();
    }

    public static ImmutableSeparatorElement copyOf(SeparatorElement separatorElement) {
        if (separatorElement instanceof ImmutableSeparatorElement) {
            return (ImmutableSeparatorElement) separatorElement;
        }
        return builder().from(separatorElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SeparatorElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private Builder() {
        }

        public final Builder from(SeparatorElement separatorElement) {
            ImmutableSeparatorElement.requireNonNull(separatorElement, "instance");
            return this;
        }

        public ImmutableSeparatorElement build() {
            return new ImmutableSeparatorElement(this);
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
