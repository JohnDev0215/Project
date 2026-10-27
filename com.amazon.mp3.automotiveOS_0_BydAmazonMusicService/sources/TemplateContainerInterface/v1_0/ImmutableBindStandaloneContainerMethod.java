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
@Generated(from = "BindStandaloneContainerMethod", generator = "Immutables")
public final class ImmutableBindStandaloneContainerMethod extends BindStandaloneContainerMethod {
    private final Boolean forced;
    private final Queue queue;
    private final Template template;

    private ImmutableBindStandaloneContainerMethod(Builder builder) {
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

    private ImmutableBindStandaloneContainerMethod(Queue queue, Boolean bool, Template template) {
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

    @Override // TemplateContainerInterface.v1_0.BindStandaloneContainerMethod
    @JsonProperty("template")
    public Template template() {
        return this.template;
    }

    public final ImmutableBindStandaloneContainerMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableBindStandaloneContainerMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.template);
    }

    public final ImmutableBindStandaloneContainerMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableBindStandaloneContainerMethod(this.queue, bool2, this.template);
    }

    public final ImmutableBindStandaloneContainerMethod withTemplate(Template template) {
        if (this.template == template) {
            return this;
        }
        return new ImmutableBindStandaloneContainerMethod(this.queue, this.forced, (Template) requireNonNull(template, "template"));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableBindStandaloneContainerMethod) && equalTo(0, (ImmutableBindStandaloneContainerMethod) obj);
    }

    private boolean equalTo(int i, ImmutableBindStandaloneContainerMethod immutableBindStandaloneContainerMethod) {
        return this.queue.equals(immutableBindStandaloneContainerMethod.queue) && this.forced.equals(immutableBindStandaloneContainerMethod.forced) && this.template.equals(immutableBindStandaloneContainerMethod.template);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        return iHashCode2 + (iHashCode2 << 5) + this.template.hashCode();
    }

    public String toString() {
        return "BindStandaloneContainerMethod{queue=" + this.queue + ", forced=" + this.forced + ", template=" + this.template + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "BindStandaloneContainerMethod", generator = "Immutables")
    static final class Json extends BindStandaloneContainerMethod {

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

        @Override // TemplateContainerInterface.v1_0.BindStandaloneContainerMethod
        public Template template() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableBindStandaloneContainerMethod fromJson(Json json) {
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

    public static ImmutableBindStandaloneContainerMethod copyOf(BindStandaloneContainerMethod bindStandaloneContainerMethod) {
        if (bindStandaloneContainerMethod instanceof ImmutableBindStandaloneContainerMethod) {
            return (ImmutableBindStandaloneContainerMethod) bindStandaloneContainerMethod;
        }
        return builder().from(bindStandaloneContainerMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "BindStandaloneContainerMethod", generator = "Immutables")
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

        public final Builder from(BindStandaloneContainerMethod bindStandaloneContainerMethod) {
            ImmutableBindStandaloneContainerMethod.requireNonNull(bindStandaloneContainerMethod, "instance");
            from((Object) bindStandaloneContainerMethod);
            return this;
        }

        public final Builder from(Method method) {
            ImmutableBindStandaloneContainerMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof BindStandaloneContainerMethod) {
                BindStandaloneContainerMethod bindStandaloneContainerMethod = (BindStandaloneContainerMethod) obj;
                template(bindStandaloneContainerMethod.template());
                forced(bindStandaloneContainerMethod.forced());
                queue(bindStandaloneContainerMethod.queue());
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
            this.queue = (Queue) ImmutableBindStandaloneContainerMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableBindStandaloneContainerMethod.requireNonNull(bool, "forced");
            return this;
        }

        @JsonProperty("template")
        public final Builder template(Template template) {
            this.template = (Template) ImmutableBindStandaloneContainerMethod.requireNonNull(template, "template");
            this.initBits &= -3;
            return this;
        }

        public ImmutableBindStandaloneContainerMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableBindStandaloneContainerMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("template");
            }
            return "Cannot build BindStandaloneContainerMethod, some of required attributes are not set " + arrayList;
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
