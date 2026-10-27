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
@Generated(from = "CreateAndBindTemplateMethod", generator = "Immutables")
public final class ImmutableCreateAndBindTemplateMethod extends CreateAndBindTemplateMethod {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final Boolean forced;
    private volatile transient InitShim initShim;
    private final Queue queue;
    private final TemplateScreenMode screenMode;
    private final Template template;

    private ImmutableCreateAndBindTemplateMethod(Builder builder) {
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

    private ImmutableCreateAndBindTemplateMethod(Queue queue, Boolean bool, Template template, TemplateScreenMode templateScreenMode) {
        this.initShim = new InitShim();
        this.queue = queue;
        this.forced = bool;
        this.template = template;
        this.screenMode = templateScreenMode;
        this.initShim = null;
    }

    @Generated(from = "CreateAndBindTemplateMethod", generator = "Immutables")
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
                this.forced = (Boolean) ImmutableCreateAndBindTemplateMethod.requireNonNull(ImmutableCreateAndBindTemplateMethod.super.forced(), "forced");
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
                this.screenMode = (TemplateScreenMode) ImmutableCreateAndBindTemplateMethod.requireNonNull(ImmutableCreateAndBindTemplateMethod.super.screenMode(), "screenMode");
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
            return "Cannot build CreateAndBindTemplateMethod, attribute initializers form cycle " + arrayList;
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

    @Override // TemplateListInterface.v1_0.CreateAndBindTemplateMethod
    @JsonProperty("template")
    public Template template() {
        return this.template;
    }

    @Override // TemplateListInterface.v1_0.CreateAndBindTemplateMethod
    @JsonProperty("screenMode")
    public TemplateScreenMode screenMode() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.screenMode();
        }
        return this.screenMode;
    }

    public final ImmutableCreateAndBindTemplateMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableCreateAndBindTemplateMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.template, this.screenMode);
    }

    public final ImmutableCreateAndBindTemplateMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableCreateAndBindTemplateMethod(this.queue, bool2, this.template, this.screenMode);
    }

    public final ImmutableCreateAndBindTemplateMethod withTemplate(Template template) {
        if (this.template == template) {
            return this;
        }
        return new ImmutableCreateAndBindTemplateMethod(this.queue, this.forced, (Template) requireNonNull(template, "template"), this.screenMode);
    }

    public final ImmutableCreateAndBindTemplateMethod withScreenMode(TemplateScreenMode templateScreenMode) {
        TemplateScreenMode templateScreenMode2 = (TemplateScreenMode) requireNonNull(templateScreenMode, "screenMode");
        return this.screenMode == templateScreenMode2 ? this : new ImmutableCreateAndBindTemplateMethod(this.queue, this.forced, this.template, templateScreenMode2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableCreateAndBindTemplateMethod) && equalTo(0, (ImmutableCreateAndBindTemplateMethod) obj);
    }

    private boolean equalTo(int i, ImmutableCreateAndBindTemplateMethod immutableCreateAndBindTemplateMethod) {
        return this.queue.equals(immutableCreateAndBindTemplateMethod.queue) && this.forced.equals(immutableCreateAndBindTemplateMethod.forced) && this.template.equals(immutableCreateAndBindTemplateMethod.template) && this.screenMode.equals(immutableCreateAndBindTemplateMethod.screenMode);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.template.hashCode();
        return iHashCode3 + (iHashCode3 << 5) + this.screenMode.hashCode();
    }

    public String toString() {
        return "CreateAndBindTemplateMethod{queue=" + this.queue + ", forced=" + this.forced + ", template=" + this.template + ", screenMode=" + this.screenMode + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "CreateAndBindTemplateMethod", generator = "Immutables")
    static final class Json extends CreateAndBindTemplateMethod {

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

        @Override // TemplateListInterface.v1_0.CreateAndBindTemplateMethod
        public Template template() {
            throw new UnsupportedOperationException();
        }

        @Override // TemplateListInterface.v1_0.CreateAndBindTemplateMethod
        public TemplateScreenMode screenMode() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableCreateAndBindTemplateMethod fromJson(Json json) {
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

    public static ImmutableCreateAndBindTemplateMethod copyOf(CreateAndBindTemplateMethod createAndBindTemplateMethod) {
        if (createAndBindTemplateMethod instanceof ImmutableCreateAndBindTemplateMethod) {
            return (ImmutableCreateAndBindTemplateMethod) createAndBindTemplateMethod;
        }
        return builder().from(createAndBindTemplateMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "CreateAndBindTemplateMethod", generator = "Immutables")
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

        public final Builder from(CreateAndBindTemplateMethod createAndBindTemplateMethod) {
            ImmutableCreateAndBindTemplateMethod.requireNonNull(createAndBindTemplateMethod, "instance");
            from((Object) createAndBindTemplateMethod);
            return this;
        }

        public final Builder from(Method method) {
            ImmutableCreateAndBindTemplateMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof CreateAndBindTemplateMethod) {
                CreateAndBindTemplateMethod createAndBindTemplateMethod = (CreateAndBindTemplateMethod) obj;
                template(createAndBindTemplateMethod.template());
                screenMode(createAndBindTemplateMethod.screenMode());
                forced(createAndBindTemplateMethod.forced());
                queue(createAndBindTemplateMethod.queue());
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
            this.queue = (Queue) ImmutableCreateAndBindTemplateMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableCreateAndBindTemplateMethod.requireNonNull(bool, "forced");
            return this;
        }

        @JsonProperty("template")
        public final Builder template(Template template) {
            this.template = (Template) ImmutableCreateAndBindTemplateMethod.requireNonNull(template, "template");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("screenMode")
        public final Builder screenMode(TemplateScreenMode templateScreenMode) {
            this.screenMode = (TemplateScreenMode) ImmutableCreateAndBindTemplateMethod.requireNonNull(templateScreenMode, "screenMode");
            return this;
        }

        public ImmutableCreateAndBindTemplateMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableCreateAndBindTemplateMethod(this);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("template");
            }
            return "Cannot build CreateAndBindTemplateMethod, some of required attributes are not set " + arrayList;
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
