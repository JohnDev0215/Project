package TemplateListInterface.v1_0;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Queue;
import CoreInterface.v1_0.Template;
import CoreInterface.v1_0.TemplateScreenMode;
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
@Generated(from = "CreateTemplateMethod", generator = "Immutables")
public final class ImmutableCreateTemplateMethod extends CreateTemplateMethod {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final Boolean forced;
    private volatile transient InitShim initShim;
    private final Queue queue;
    private final TemplateScreenMode screenMode;
    private final Template template;

    private ImmutableCreateTemplateMethod(Builder builder) {
        this.initShim = new InitShim();
        this.queue = builder.queue;
        this.template = builder.template;
        if (builder.forced != null) {
            this.initShim.forced(builder.forced);
        }
        if (builder.screenMode != null) {
            this.initShim.screenMode(builder.screenMode);
        }
        this.forced = this.initShim.forced();
        this.screenMode = this.initShim.screenMode();
        this.initShim = null;
    }

    private ImmutableCreateTemplateMethod(Queue queue, Boolean bool, Template template, TemplateScreenMode templateScreenMode) {
        this.initShim = new InitShim();
        this.queue = queue;
        this.forced = bool;
        this.template = template;
        this.screenMode = templateScreenMode;
        this.initShim = null;
    }

    @Generated(from = "CreateTemplateMethod", generator = "Immutables")
    private final class InitShim {
        private Boolean forced;
        private byte forcedBuildStage;
        private TemplateScreenMode screenMode;
        private byte screenModeBuildStage;

        private InitShim() {
            this.forcedBuildStage = (byte) 0;
            this.screenModeBuildStage = (byte) 0;
        }

        Boolean forced() {
            byte b = this.forcedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.forcedBuildStage = (byte) -1;
                this.forced = (Boolean) ImmutableCreateTemplateMethod.requireNonNull(ImmutableCreateTemplateMethod.super.forced(), "forced");
                this.forcedBuildStage = (byte) 1;
            }
            return this.forced;
        }

        void forced(Boolean bool) {
            this.forced = bool;
            this.forcedBuildStage = (byte) 1;
        }

        TemplateScreenMode screenMode() {
            byte b = this.screenModeBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.screenModeBuildStage = (byte) -1;
                this.screenMode = (TemplateScreenMode) ImmutableCreateTemplateMethod.requireNonNull(ImmutableCreateTemplateMethod.super.screenMode(), "screenMode");
                this.screenModeBuildStage = (byte) 1;
            }
            return this.screenMode;
        }

        void screenMode(TemplateScreenMode templateScreenMode) {
            this.screenMode = templateScreenMode;
            this.screenModeBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.forcedBuildStage == -1) {
                arrayList.add("forced");
            }
            if (this.screenModeBuildStage == -1) {
                arrayList.add("screenMode");
            }
            return "Cannot build CreateTemplateMethod, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // CoreInterface.v1_0.Method
    @JsonProperty("queue")
    public Queue queue() {
        return this.queue;
    }

    @Override // CoreInterface.v1_0.Method
    @JsonProperty("forced")
    public Boolean forced() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.forced();
        }
        return this.forced;
    }

    @Override // TemplateListInterface.v1_0.CreateTemplateMethod
    @JsonProperty("template")
    public Template template() {
        return this.template;
    }

    @Override // TemplateListInterface.v1_0.CreateTemplateMethod
    @JsonProperty("screenMode")
    public TemplateScreenMode screenMode() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.screenMode();
        }
        return this.screenMode;
    }

    public final ImmutableCreateTemplateMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableCreateTemplateMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.template, this.screenMode);
    }

    public final ImmutableCreateTemplateMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableCreateTemplateMethod(this.queue, bool2, this.template, this.screenMode);
    }

    public final ImmutableCreateTemplateMethod withTemplate(Template template) {
        if (this.template == template) {
            return this;
        }
        return new ImmutableCreateTemplateMethod(this.queue, this.forced, (Template) requireNonNull(template, "template"), this.screenMode);
    }

    public final ImmutableCreateTemplateMethod withScreenMode(TemplateScreenMode templateScreenMode) {
        TemplateScreenMode templateScreenMode2 = (TemplateScreenMode) requireNonNull(templateScreenMode, "screenMode");
        return this.screenMode == templateScreenMode2 ? this : new ImmutableCreateTemplateMethod(this.queue, this.forced, this.template, templateScreenMode2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableCreateTemplateMethod) && equalTo(0, (ImmutableCreateTemplateMethod) obj);
    }

    private boolean equalTo(int i, ImmutableCreateTemplateMethod immutableCreateTemplateMethod) {
        return this.queue.equals(immutableCreateTemplateMethod.queue) && this.forced.equals(immutableCreateTemplateMethod.forced) && this.template.equals(immutableCreateTemplateMethod.template) && this.screenMode.equals(immutableCreateTemplateMethod.screenMode);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.template.hashCode();
        return iHashCode3 + (iHashCode3 << 5) + this.screenMode.hashCode();
    }

    public String toString() {
        return "CreateTemplateMethod{queue=" + this.queue + ", forced=" + this.forced + ", template=" + this.template + ", screenMode=" + this.screenMode + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "CreateTemplateMethod", generator = "Immutables")
    static final class Json extends CreateTemplateMethod {

        @Nullable
        Boolean forced;

        @Nullable
        Queue queue;

        @Nullable
        TemplateScreenMode screenMode;

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

        @JsonProperty("screenMode")
        public void setScreenMode(TemplateScreenMode templateScreenMode) {
            this.screenMode = templateScreenMode;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // TemplateListInterface.v1_0.CreateTemplateMethod
        public Template template() {
            throw new UnsupportedOperationException();
        }

        @Override // TemplateListInterface.v1_0.CreateTemplateMethod
        public TemplateScreenMode screenMode() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableCreateTemplateMethod fromJson(Json json) {
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
        TemplateScreenMode templateScreenMode = json.screenMode;
        if (templateScreenMode != null) {
            builder.screenMode(templateScreenMode);
        }
        return builder.build();
    }

    public static ImmutableCreateTemplateMethod copyOf(CreateTemplateMethod createTemplateMethod) {
        if (createTemplateMethod instanceof ImmutableCreateTemplateMethod) {
            return (ImmutableCreateTemplateMethod) createTemplateMethod;
        }
        return builder().from(createTemplateMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "CreateTemplateMethod", generator = "Immutables")
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
        private TemplateScreenMode screenMode;

        @Nullable
        private Template template;

        private Builder() {
            this.initBits = 3L;
        }

        public final Builder from(Method method) {
            ImmutableCreateTemplateMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(CreateTemplateMethod createTemplateMethod) {
            ImmutableCreateTemplateMethod.requireNonNull(createTemplateMethod, "instance");
            from((Object) createTemplateMethod);
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
            if (obj instanceof CreateTemplateMethod) {
                CreateTemplateMethod createTemplateMethod = (CreateTemplateMethod) obj;
                template(createTemplateMethod.template());
                screenMode(createTemplateMethod.screenMode());
                if ((j & 1) == 0) {
                    forced(createTemplateMethod.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(createTemplateMethod.queue());
                }
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableCreateTemplateMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableCreateTemplateMethod.requireNonNull(bool, "forced");
            return this;
        }

        @JsonProperty("template")
        public final Builder template(Template template) {
            this.template = (Template) ImmutableCreateTemplateMethod.requireNonNull(template, "template");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("screenMode")
        public final Builder screenMode(TemplateScreenMode templateScreenMode) {
            this.screenMode = (TemplateScreenMode) ImmutableCreateTemplateMethod.requireNonNull(templateScreenMode, "screenMode");
            return this;
        }

        public ImmutableCreateTemplateMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableCreateTemplateMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("template");
            }
            return "Cannot build CreateTemplateMethod, some of required attributes are not set " + arrayList;
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
