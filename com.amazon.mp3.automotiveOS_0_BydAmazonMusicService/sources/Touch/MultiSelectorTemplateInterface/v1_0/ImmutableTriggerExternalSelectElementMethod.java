package Touch.MultiSelectorTemplateInterface.v1_0;

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
@Generated(from = "TriggerExternalSelectElementMethod", generator = "Immutables")
public final class ImmutableTriggerExternalSelectElementMethod extends TriggerExternalSelectElementMethod {
    private final Boolean forced;
    private final Queue queue;
    private final String selectionKey;
    private final String selectionType;
    private final String selectionValue;

    private ImmutableTriggerExternalSelectElementMethod(Builder builder) {
        Boolean bool;
        this.queue = builder.queue;
        this.selectionKey = builder.selectionKey;
        this.selectionValue = builder.selectionValue;
        this.selectionType = builder.selectionType;
        if (builder.forced == null) {
            bool = (Boolean) requireNonNull(super.forced(), "forced");
        } else {
            bool = builder.forced;
        }
        this.forced = bool;
    }

    private ImmutableTriggerExternalSelectElementMethod(Queue queue, Boolean bool, String str, String str2, String str3) {
        this.queue = queue;
        this.forced = bool;
        this.selectionKey = str;
        this.selectionValue = str2;
        this.selectionType = str3;
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

    @Override // Touch.MultiSelectorTemplateInterface.v1_0.TriggerExternalSelectElementMethod
    @JsonProperty("selectionKey")
    public String selectionKey() {
        return this.selectionKey;
    }

    @Override // Touch.MultiSelectorTemplateInterface.v1_0.TriggerExternalSelectElementMethod
    @JsonProperty("selectionValue")
    public String selectionValue() {
        return this.selectionValue;
    }

    @Override // Touch.MultiSelectorTemplateInterface.v1_0.TriggerExternalSelectElementMethod
    @JsonProperty("selectionType")
    public String selectionType() {
        return this.selectionType;
    }

    public final ImmutableTriggerExternalSelectElementMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableTriggerExternalSelectElementMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.selectionKey, this.selectionValue, this.selectionType);
    }

    public final ImmutableTriggerExternalSelectElementMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableTriggerExternalSelectElementMethod(this.queue, bool2, this.selectionKey, this.selectionValue, this.selectionType);
    }

    public final ImmutableTriggerExternalSelectElementMethod withSelectionKey(String str) {
        String str2 = (String) requireNonNull(str, "selectionKey");
        return this.selectionKey.equals(str2) ? this : new ImmutableTriggerExternalSelectElementMethod(this.queue, this.forced, str2, this.selectionValue, this.selectionType);
    }

    public final ImmutableTriggerExternalSelectElementMethod withSelectionValue(String str) {
        String str2 = (String) requireNonNull(str, "selectionValue");
        return this.selectionValue.equals(str2) ? this : new ImmutableTriggerExternalSelectElementMethod(this.queue, this.forced, this.selectionKey, str2, this.selectionType);
    }

    public final ImmutableTriggerExternalSelectElementMethod withSelectionType(String str) {
        String str2 = (String) requireNonNull(str, "selectionType");
        return this.selectionType.equals(str2) ? this : new ImmutableTriggerExternalSelectElementMethod(this.queue, this.forced, this.selectionKey, this.selectionValue, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableTriggerExternalSelectElementMethod) && equalTo(0, (ImmutableTriggerExternalSelectElementMethod) obj);
    }

    private boolean equalTo(int i, ImmutableTriggerExternalSelectElementMethod immutableTriggerExternalSelectElementMethod) {
        return this.queue.equals(immutableTriggerExternalSelectElementMethod.queue) && this.forced.equals(immutableTriggerExternalSelectElementMethod.forced) && this.selectionKey.equals(immutableTriggerExternalSelectElementMethod.selectionKey) && this.selectionValue.equals(immutableTriggerExternalSelectElementMethod.selectionValue) && this.selectionType.equals(immutableTriggerExternalSelectElementMethod.selectionType);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.selectionKey.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.selectionValue.hashCode();
        return iHashCode4 + (iHashCode4 << 5) + this.selectionType.hashCode();
    }

    public String toString() {
        return "TriggerExternalSelectElementMethod{queue=" + this.queue + ", forced=" + this.forced + ", selectionKey=" + this.selectionKey + ", selectionValue=" + this.selectionValue + ", selectionType=" + this.selectionType + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "TriggerExternalSelectElementMethod", generator = "Immutables")
    static final class Json extends TriggerExternalSelectElementMethod {

        @Nullable
        Boolean forced;

        @Nullable
        Queue queue;

        @Nullable
        String selectionKey;

        @Nullable
        String selectionType;

        @Nullable
        String selectionValue;

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

        @JsonProperty("selectionKey")
        public void setSelectionKey(String str) {
            this.selectionKey = str;
        }

        @JsonProperty("selectionValue")
        public void setSelectionValue(String str) {
            this.selectionValue = str;
        }

        @JsonProperty("selectionType")
        public void setSelectionType(String str) {
            this.selectionType = str;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.MultiSelectorTemplateInterface.v1_0.TriggerExternalSelectElementMethod
        public String selectionKey() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.MultiSelectorTemplateInterface.v1_0.TriggerExternalSelectElementMethod
        public String selectionValue() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.MultiSelectorTemplateInterface.v1_0.TriggerExternalSelectElementMethod
        public String selectionType() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableTriggerExternalSelectElementMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        String str = json.selectionKey;
        if (str != null) {
            builder.selectionKey(str);
        }
        String str2 = json.selectionValue;
        if (str2 != null) {
            builder.selectionValue(str2);
        }
        String str3 = json.selectionType;
        if (str3 != null) {
            builder.selectionType(str3);
        }
        return builder.build();
    }

    public static ImmutableTriggerExternalSelectElementMethod copyOf(TriggerExternalSelectElementMethod triggerExternalSelectElementMethod) {
        if (triggerExternalSelectElementMethod instanceof ImmutableTriggerExternalSelectElementMethod) {
            return (ImmutableTriggerExternalSelectElementMethod) triggerExternalSelectElementMethod;
        }
        return builder().from(triggerExternalSelectElementMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "TriggerExternalSelectElementMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_QUEUE = 1;
        private static final long INIT_BIT_SELECTION_KEY = 2;
        private static final long INIT_BIT_SELECTION_TYPE = 8;
        private static final long INIT_BIT_SELECTION_VALUE = 4;

        @Nullable
        private Boolean forced;
        private long initBits;

        @Nullable
        private Queue queue;

        @Nullable
        private String selectionKey;

        @Nullable
        private String selectionType;

        @Nullable
        private String selectionValue;

        private Builder() {
            this.initBits = 15L;
        }

        public final Builder from(Method method) {
            ImmutableTriggerExternalSelectElementMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(TriggerExternalSelectElementMethod triggerExternalSelectElementMethod) {
            ImmutableTriggerExternalSelectElementMethod.requireNonNull(triggerExternalSelectElementMethod, "instance");
            from((Object) triggerExternalSelectElementMethod);
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
            if (obj instanceof TriggerExternalSelectElementMethod) {
                TriggerExternalSelectElementMethod triggerExternalSelectElementMethod = (TriggerExternalSelectElementMethod) obj;
                selectionType(triggerExternalSelectElementMethod.selectionType());
                selectionValue(triggerExternalSelectElementMethod.selectionValue());
                selectionKey(triggerExternalSelectElementMethod.selectionKey());
                if ((j & 1) == 0) {
                    forced(triggerExternalSelectElementMethod.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(triggerExternalSelectElementMethod.queue());
                }
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableTriggerExternalSelectElementMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableTriggerExternalSelectElementMethod.requireNonNull(bool, "forced");
            return this;
        }

        @JsonProperty("selectionKey")
        public final Builder selectionKey(String str) {
            this.selectionKey = (String) ImmutableTriggerExternalSelectElementMethod.requireNonNull(str, "selectionKey");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("selectionValue")
        public final Builder selectionValue(String str) {
            this.selectionValue = (String) ImmutableTriggerExternalSelectElementMethod.requireNonNull(str, "selectionValue");
            this.initBits &= -5;
            return this;
        }

        @JsonProperty("selectionType")
        public final Builder selectionType(String str) {
            this.selectionType = (String) ImmutableTriggerExternalSelectElementMethod.requireNonNull(str, "selectionType");
            this.initBits &= -9;
            return this;
        }

        public ImmutableTriggerExternalSelectElementMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableTriggerExternalSelectElementMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("selectionKey");
            }
            if ((this.initBits & 4) != 0) {
                arrayList.add("selectionValue");
            }
            if ((this.initBits & 8) != 0) {
                arrayList.add("selectionType");
            }
            return "Cannot build TriggerExternalSelectElementMethod, some of required attributes are not set " + arrayList;
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
