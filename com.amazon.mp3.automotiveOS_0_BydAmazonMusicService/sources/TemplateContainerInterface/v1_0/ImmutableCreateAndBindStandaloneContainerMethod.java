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
@Generated(from = "CreateAndBindStandaloneContainerMethod", generator = "Immutables")
public final class ImmutableCreateAndBindStandaloneContainerMethod extends CreateAndBindStandaloneContainerMethod {
    private final Boolean forced;
    private final Queue queue;
    private final Template template;

    private ImmutableCreateAndBindStandaloneContainerMethod(Builder builder) {
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

    private ImmutableCreateAndBindStandaloneContainerMethod(Queue queue, Boolean bool, Template template) {
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

    @Override // TemplateContainerInterface.v1_0.CreateAndBindStandaloneContainerMethod
    @JsonProperty("template")
    public Template template() {
        return this.template;
    }

    public final ImmutableCreateAndBindStandaloneContainerMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableCreateAndBindStandaloneContainerMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.template);
    }

    public final ImmutableCreateAndBindStandaloneContainerMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableCreateAndBindStandaloneContainerMethod(this.queue, bool2, this.template);
    }

    public final ImmutableCreateAndBindStandaloneContainerMethod withTemplate(Template template) {
        if (this.template == template) {
            return this;
        }
        return new ImmutableCreateAndBindStandaloneContainerMethod(this.queue, this.forced, (Template) requireNonNull(template, "template"));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableCreateAndBindStandaloneContainerMethod) && equalTo(0, (ImmutableCreateAndBindStandaloneContainerMethod) obj);
    }

    private boolean equalTo(int i, ImmutableCreateAndBindStandaloneContainerMethod immutableCreateAndBindStandaloneContainerMethod) {
        return this.queue.equals(immutableCreateAndBindStandaloneContainerMethod.queue) && this.forced.equals(immutableCreateAndBindStandaloneContainerMethod.forced) && this.template.equals(immutableCreateAndBindStandaloneContainerMethod.template);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        return iHashCode2 + (iHashCode2 << 5) + this.template.hashCode();
    }

    public String toString() {
        return "CreateAndBindStandaloneContainerMethod{queue=" + this.queue + ", forced=" + this.forced + ", template=" + this.template + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "CreateAndBindStandaloneContainerMethod", generator = "Immutables")
    static final class Json extends CreateAndBindStandaloneContainerMethod {

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

        @Override // TemplateContainerInterface.v1_0.CreateAndBindStandaloneContainerMethod
        public Template template() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableCreateAndBindStandaloneContainerMethod fromJson(Json json) {
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

    public static ImmutableCreateAndBindStandaloneContainerMethod copyOf(CreateAndBindStandaloneContainerMethod createAndBindStandaloneContainerMethod) {
        if (createAndBindStandaloneContainerMethod instanceof ImmutableCreateAndBindStandaloneContainerMethod) {
            return (ImmutableCreateAndBindStandaloneContainerMethod) createAndBindStandaloneContainerMethod;
        }
        return builder().from(createAndBindStandaloneContainerMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "CreateAndBindStandaloneContainerMethod", generator = "Immutables")
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

        public final Builder from(Method method) {
            ImmutableCreateAndBindStandaloneContainerMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(CreateAndBindStandaloneContainerMethod createAndBindStandaloneContainerMethod) {
            ImmutableCreateAndBindStandaloneContainerMethod.requireNonNull(createAndBindStandaloneContainerMethod, "instance");
            from((Object) createAndBindStandaloneContainerMethod);
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
            if (obj instanceof CreateAndBindStandaloneContainerMethod) {
                CreateAndBindStandaloneContainerMethod createAndBindStandaloneContainerMethod = (CreateAndBindStandaloneContainerMethod) obj;
                template(createAndBindStandaloneContainerMethod.template());
                if ((j & 1) == 0) {
                    forced(createAndBindStandaloneContainerMethod.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(createAndBindStandaloneContainerMethod.queue());
                }
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableCreateAndBindStandaloneContainerMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableCreateAndBindStandaloneContainerMethod.requireNonNull(bool, "forced");
            return this;
        }

        @JsonProperty("template")
        public final Builder template(Template template) {
            this.template = (Template) ImmutableCreateAndBindStandaloneContainerMethod.requireNonNull(template, "template");
            this.initBits &= -3;
            return this;
        }

        public ImmutableCreateAndBindStandaloneContainerMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableCreateAndBindStandaloneContainerMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("template");
            }
            return "Cannot build CreateAndBindStandaloneContainerMethod, some of required attributes are not set " + arrayList;
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
