package ExternalActionInterface.v1_0;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Queue;
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
@Generated(from = "ToggleButtonMethod", generator = "Immutables")
public final class ImmutableToggleButtonMethod extends ToggleButtonMethod {
    private final Boolean forced;

    @Nullable
    private final Boolean isActive;
    private final Queue queue;

    @Nullable
    private final String widgetId;

    private ImmutableToggleButtonMethod(Builder builder) {
        Boolean bool;
        this.queue = builder.queue;
        this.widgetId = builder.widgetId;
        this.isActive = builder.isActive;
        if (builder.forced == null) {
            bool = (Boolean) requireNonNull(super.forced(), "forced");
        } else {
            bool = builder.forced;
        }
        this.forced = bool;
    }

    private ImmutableToggleButtonMethod(Queue queue, Boolean bool, @Nullable String str, @Nullable Boolean bool2) {
        this.queue = queue;
        this.forced = bool;
        this.widgetId = str;
        this.isActive = bool2;
    }

    @Override // CoreInterface.v1_0.Method
    @JsonProperty("queue")
    public Queue queue() {
        return this.queue;
    }

    @Override // CoreInterface.v1_0.Method
    @JsonProperty("forced")
    public Boolean forced() {
        return this.forced;
    }

    @Override // ExternalActionInterface.v1_0.ToggleButtonMethod
    @JsonProperty("widgetId")
    @Nullable
    public String widgetId() {
        return this.widgetId;
    }

    @Override // ExternalActionInterface.v1_0.ToggleButtonMethod
    @JsonProperty("isActive")
    @Nullable
    public Boolean isActive() {
        return this.isActive;
    }

    public final ImmutableToggleButtonMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableToggleButtonMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.widgetId, this.isActive);
    }

    public final ImmutableToggleButtonMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableToggleButtonMethod(this.queue, bool2, this.widgetId, this.isActive);
    }

    public final ImmutableToggleButtonMethod withWidgetId(@Nullable String str) {
        return equals(this.widgetId, str) ? this : new ImmutableToggleButtonMethod(this.queue, this.forced, str, this.isActive);
    }

    public final ImmutableToggleButtonMethod withIsActive(@Nullable Boolean bool) {
        return equals(this.isActive, bool) ? this : new ImmutableToggleButtonMethod(this.queue, this.forced, this.widgetId, bool);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableToggleButtonMethod) && equalTo(0, (ImmutableToggleButtonMethod) obj);
    }

    private boolean equalTo(int i, ImmutableToggleButtonMethod immutableToggleButtonMethod) {
        return this.queue.equals(immutableToggleButtonMethod.queue) && this.forced.equals(immutableToggleButtonMethod.forced) && equals(this.widgetId, immutableToggleButtonMethod.widgetId) && equals(this.isActive, immutableToggleButtonMethod.isActive);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + hashCode(this.widgetId);
        return iHashCode3 + (iHashCode3 << 5) + hashCode(this.isActive);
    }

    public String toString() {
        return "ToggleButtonMethod{queue=" + this.queue + ", forced=" + this.forced + ", widgetId=" + this.widgetId + ", isActive=" + this.isActive + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "ToggleButtonMethod", generator = "Immutables")
    static final class Json extends ToggleButtonMethod {

        @Nullable
        Boolean forced;

        @Nullable
        Boolean isActive;

        @Nullable
        Queue queue;

        @Nullable
        String widgetId;

        Json() {
        }

        @JsonProperty("queue")
        public void setQueue(Queue queue) {
            this.queue = queue;
        }

        @JsonProperty("forced")
        public void setForced(Boolean bool) {
            this.forced = bool;
        }

        @JsonProperty("widgetId")
        public void setWidgetId(@Nullable String str) {
            this.widgetId = str;
        }

        @JsonProperty("isActive")
        public void setIsActive(@Nullable Boolean bool) {
            this.isActive = bool;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.ToggleButtonMethod
        public String widgetId() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalActionInterface.v1_0.ToggleButtonMethod
        public Boolean isActive() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableToggleButtonMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        String str = json.widgetId;
        if (str != null) {
            builder.widgetId(str);
        }
        Boolean bool2 = json.isActive;
        if (bool2 != null) {
            builder.isActive(bool2);
        }
        return builder.build();
    }

    public static ImmutableToggleButtonMethod copyOf(ToggleButtonMethod toggleButtonMethod) {
        if (toggleButtonMethod instanceof ImmutableToggleButtonMethod) {
            return (ImmutableToggleButtonMethod) toggleButtonMethod;
        }
        return builder().from(toggleButtonMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "ToggleButtonMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_QUEUE = 1;

        @Nullable
        private Boolean forced;
        private long initBits;

        @Nullable
        private Boolean isActive;

        @Nullable
        private Queue queue;

        @Nullable
        private String widgetId;

        private Builder() {
            this.initBits = 1L;
        }

        public final Builder from(Method method) {
            ImmutableToggleButtonMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(ToggleButtonMethod toggleButtonMethod) {
            ImmutableToggleButtonMethod.requireNonNull(toggleButtonMethod, "instance");
            from((Object) toggleButtonMethod);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof Method) {
                Method method = (Method) obj;
                forced(method.forced());
                queue(method.queue());
                j = 3;
            } else {
                j = 0;
            }
            if (obj instanceof ToggleButtonMethod) {
                ToggleButtonMethod toggleButtonMethod = (ToggleButtonMethod) obj;
                Boolean boolIsActive = toggleButtonMethod.isActive();
                if (boolIsActive != null) {
                    isActive(boolIsActive);
                }
                if ((j & 1) == 0) {
                    forced(toggleButtonMethod.forced());
                    j |= 1;
                }
                String strWidgetId = toggleButtonMethod.widgetId();
                if (strWidgetId != null) {
                    widgetId(strWidgetId);
                }
                if ((j & 2) == 0) {
                    queue(toggleButtonMethod.queue());
                }
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableToggleButtonMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableToggleButtonMethod.requireNonNull(bool, "forced");
            return this;
        }

        @JsonProperty("widgetId")
        public final Builder widgetId(@Nullable String str) {
            this.widgetId = str;
            return this;
        }

        @JsonProperty("isActive")
        public final Builder isActive(@Nullable Boolean bool) {
            this.isActive = bool;
            return this;
        }

        public ImmutableToggleButtonMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableToggleButtonMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            return "Cannot build ToggleButtonMethod, some of required attributes are not set " + arrayList;
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
