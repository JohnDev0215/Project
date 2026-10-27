package TemplateContainerInterface.v1_0;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Queue;
import CoreInterface.v1_0.Template;
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
@Generated(from = "CreateStandaloneContainerMethod", generator = "Immutables")
public final class ImmutableCreateStandaloneContainerMethod extends CreateStandaloneContainerMethod {
    private final Boolean forced;
    private final Queue queue;
    private final Template template;

    private ImmutableCreateStandaloneContainerMethod(Builder builder) {
        Boolean bool;
        this.queue = builder.queue;
        this.template = builder.template;
        if (builder.forced == null) {
            bool = (Boolean) requireNonNull(super.forced(), "forced");
        } else {
            bool = builder.forced;
        }
        this.forced = bool;
    }

    private ImmutableCreateStandaloneContainerMethod(Queue queue, Boolean bool, Template template) {
        this.queue = queue;
        this.forced = bool;
        this.template = template;
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

    @Override // TemplateContainerInterface.v1_0.CreateStandaloneContainerMethod
    @JsonProperty("template")
    public Template template() {
        return this.template;
    }

    public final ImmutableCreateStandaloneContainerMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableCreateStandaloneContainerMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.template);
    }

    public final ImmutableCreateStandaloneContainerMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableCreateStandaloneContainerMethod(this.queue, bool2, this.template);
    }

    public final ImmutableCreateStandaloneContainerMethod withTemplate(Template template) {
        if (this.template == template) {
            return this;
        }
        return new ImmutableCreateStandaloneContainerMethod(this.queue, this.forced, (Template) requireNonNull(template, "template"));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableCreateStandaloneContainerMethod) && equalTo(0, (ImmutableCreateStandaloneContainerMethod) obj);
    }

    private boolean equalTo(int i, ImmutableCreateStandaloneContainerMethod immutableCreateStandaloneContainerMethod) {
        return this.queue.equals(immutableCreateStandaloneContainerMethod.queue) && this.forced.equals(immutableCreateStandaloneContainerMethod.forced) && this.template.equals(immutableCreateStandaloneContainerMethod.template);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        return iHashCode2 + (iHashCode2 << 5) + this.template.hashCode();
    }

    public String toString() {
        return "CreateStandaloneContainerMethod{queue=" + this.queue + ", forced=" + this.forced + ", template=" + this.template + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "CreateStandaloneContainerMethod", generator = "Immutables")
    static final class Json extends CreateStandaloneContainerMethod {

        @Nullable
        Boolean forced;

        @Nullable
        Queue queue;

        @Nullable
        Template template;

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

        @JsonProperty("template")
        public void setTemplate(Template template) {
            this.template = template;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // TemplateContainerInterface.v1_0.CreateStandaloneContainerMethod
        public Template template() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableCreateStandaloneContainerMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        Template template = json.template;
        if (template != null) {
            builder.template(template);
        }
        return builder.build();
    }

    public static ImmutableCreateStandaloneContainerMethod copyOf(CreateStandaloneContainerMethod createStandaloneContainerMethod) {
        if (createStandaloneContainerMethod instanceof ImmutableCreateStandaloneContainerMethod) {
            return (ImmutableCreateStandaloneContainerMethod) createStandaloneContainerMethod;
        }
        return builder().from(createStandaloneContainerMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "CreateStandaloneContainerMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_QUEUE = 1;
        private static final long INIT_BIT_TEMPLATE = 2;

        @Nullable
        private Boolean forced;
        private long initBits;

        @Nullable
        private Queue queue;

        @Nullable
        private Template template;

        private Builder() {
            this.initBits = 3L;
        }

        public final Builder from(CreateStandaloneContainerMethod createStandaloneContainerMethod) {
            ImmutableCreateStandaloneContainerMethod.requireNonNull(createStandaloneContainerMethod, "instance");
            from((Object) createStandaloneContainerMethod);
            return this;
        }

        public final Builder from(Method method) {
            ImmutableCreateStandaloneContainerMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof CreateStandaloneContainerMethod) {
                CreateStandaloneContainerMethod createStandaloneContainerMethod = (CreateStandaloneContainerMethod) obj;
                template(createStandaloneContainerMethod.template());
                forced(createStandaloneContainerMethod.forced());
                queue(createStandaloneContainerMethod.queue());
                j = 3;
            } else {
                j = 0;
            }
            if (obj instanceof Method) {
                Method method = (Method) obj;
                if ((j & 1) == 0) {
                    forced(method.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(method.queue());
                }
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableCreateStandaloneContainerMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableCreateStandaloneContainerMethod.requireNonNull(bool, "forced");
            return this;
        }

        @JsonProperty("template")
        public final Builder template(Template template) {
            this.template = (Template) ImmutableCreateStandaloneContainerMethod.requireNonNull(template, "template");
            this.initBits &= -3;
            return this;
        }

        public ImmutableCreateStandaloneContainerMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableCreateStandaloneContainerMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("template");
            }
            return "Cannot build CreateStandaloneContainerMethod, some of required attributes are not set " + arrayList;
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
