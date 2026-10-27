package Touch.DialogTemplateInterface.v1_0;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
import CoreInterface.v1_0.TemplateLaunchMode;
import Touch.WidgetsInterface.v1_0.PlainIconButtonElement;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/* JADX INFO: loaded from: classes.dex */
@SuppressFBWarnings
@ParametersAreNonnullByDefault
@Immutable
@Generated(from = "DialogTemplate", generator = "Immutables")
public final class ImmutableDialogTemplate extends DialogTemplate {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final String body;
    private final List<DialogButtonElement> buttons;
    private final Long cacheSeconds;
    private final PlainIconButtonElement closeButton;
    private final String header;
    private volatile transient InitShim initShim;
    private final TemplateLaunchMode launchMode;
    private final List<Method> onBound;
    private final List<Method> onCreated;
    private final List<Method> onViewed;

    private ImmutableDialogTemplate(Builder builder) {
        this.initShim = new InitShim();
        this.header = builder.header;
        this.body = builder.body;
        this.closeButton = builder.closeButton;
        if (builder.onCreatedIsSet()) {
            this.initShim.onCreated(createUnmodifiableList(true, builder.onCreated));
        }
        if (builder.onBoundIsSet()) {
            this.initShim.onBound(createUnmodifiableList(true, builder.onBound));
        }
        if (builder.onViewedIsSet()) {
            this.initShim.onViewed(createUnmodifiableList(true, builder.onViewed));
        }
        if (builder.launchMode != null) {
            this.initShim.launchMode(builder.launchMode);
        }
        if (builder.cacheSeconds != null) {
            this.initShim.cacheSeconds(builder.cacheSeconds);
        }
        if (builder.buttonsIsSet()) {
            this.initShim.buttons(createUnmodifiableList(true, builder.buttons));
        }
        this.onCreated = this.initShim.onCreated();
        this.onBound = this.initShim.onBound();
        this.onViewed = this.initShim.onViewed();
        this.launchMode = this.initShim.launchMode();
        this.cacheSeconds = this.initShim.cacheSeconds();
        this.buttons = this.initShim.buttons();
        this.initShim = null;
    }

    private ImmutableDialogTemplate(List<Method> list, List<Method> list2, List<Method> list3, TemplateLaunchMode templateLaunchMode, Long l, String str, String str2, List<DialogButtonElement> list4, PlainIconButtonElement plainIconButtonElement) {
        this.initShim = new InitShim();
        this.onCreated = list;
        this.onBound = list2;
        this.onViewed = list3;
        this.launchMode = templateLaunchMode;
        this.cacheSeconds = l;
        this.header = str;
        this.body = str2;
        this.buttons = list4;
        this.closeButton = plainIconButtonElement;
        this.initShim = null;
    }

    @Generated(from = "DialogTemplate", generator = "Immutables")
    private final class InitShim {
        private List<DialogButtonElement> buttons;
        private byte buttonsBuildStage;
        private Long cacheSeconds;
        private byte cacheSecondsBuildStage;
        private TemplateLaunchMode launchMode;
        private byte launchModeBuildStage;
        private List<Method> onBound;
        private byte onBoundBuildStage;
        private List<Method> onCreated;
        private byte onCreatedBuildStage;
        private List<Method> onViewed;
        private byte onViewedBuildStage;

        private InitShim() {
            this.onCreatedBuildStage = (byte) 0;
            this.onBoundBuildStage = (byte) 0;
            this.onViewedBuildStage = (byte) 0;
            this.launchModeBuildStage = (byte) 0;
            this.cacheSecondsBuildStage = (byte) 0;
            this.buttonsBuildStage = (byte) 0;
        }

        List<Method> onCreated() {
            byte b = this.onCreatedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onCreatedBuildStage = (byte) -1;
                this.onCreated = ImmutableDialogTemplate.createUnmodifiableList(false, ImmutableDialogTemplate.createSafeList(ImmutableDialogTemplate.super.onCreated(), true, false));
                this.onCreatedBuildStage = (byte) 1;
            }
            return this.onCreated;
        }

        void onCreated(List<Method> list) {
            this.onCreated = list;
            this.onCreatedBuildStage = (byte) 1;
        }

        List<Method> onBound() {
            byte b = this.onBoundBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onBoundBuildStage = (byte) -1;
                this.onBound = ImmutableDialogTemplate.createUnmodifiableList(false, ImmutableDialogTemplate.createSafeList(ImmutableDialogTemplate.super.onBound(), true, false));
                this.onBoundBuildStage = (byte) 1;
            }
            return this.onBound;
        }

        void onBound(List<Method> list) {
            this.onBound = list;
            this.onBoundBuildStage = (byte) 1;
        }

        List<Method> onViewed() {
            byte b = this.onViewedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onViewedBuildStage = (byte) -1;
                this.onViewed = ImmutableDialogTemplate.createUnmodifiableList(false, ImmutableDialogTemplate.createSafeList(ImmutableDialogTemplate.super.onViewed(), true, false));
                this.onViewedBuildStage = (byte) 1;
            }
            return this.onViewed;
        }

        void onViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedBuildStage = (byte) 1;
        }

        TemplateLaunchMode launchMode() {
            byte b = this.launchModeBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.launchModeBuildStage = (byte) -1;
                this.launchMode = (TemplateLaunchMode) ImmutableDialogTemplate.requireNonNull(ImmutableDialogTemplate.super.launchMode(), "launchMode");
                this.launchModeBuildStage = (byte) 1;
            }
            return this.launchMode;
        }

        void launchMode(TemplateLaunchMode templateLaunchMode) {
            this.launchMode = templateLaunchMode;
            this.launchModeBuildStage = (byte) 1;
        }

        Long cacheSeconds() {
            byte b = this.cacheSecondsBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.cacheSecondsBuildStage = (byte) -1;
                this.cacheSeconds = (Long) ImmutableDialogTemplate.requireNonNull(ImmutableDialogTemplate.super.cacheSeconds(), "cacheSeconds");
                this.cacheSecondsBuildStage = (byte) 1;
            }
            return this.cacheSeconds;
        }

        void cacheSeconds(Long l) {
            this.cacheSeconds = l;
            this.cacheSecondsBuildStage = (byte) 1;
        }

        List<DialogButtonElement> buttons() {
            byte b = this.buttonsBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.buttonsBuildStage = (byte) -1;
                this.buttons = ImmutableDialogTemplate.createUnmodifiableList(false, ImmutableDialogTemplate.createSafeList(ImmutableDialogTemplate.super.buttons(), true, false));
                this.buttonsBuildStage = (byte) 1;
            }
            return this.buttons;
        }

        void buttons(List<DialogButtonElement> list) {
            this.buttons = list;
            this.buttonsBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.onCreatedBuildStage == -1) {
                arrayList.add("onCreated");
            }
            if (this.onBoundBuildStage == -1) {
                arrayList.add("onBound");
            }
            if (this.onViewedBuildStage == -1) {
                arrayList.add("onViewed");
            }
            if (this.launchModeBuildStage == -1) {
                arrayList.add("launchMode");
            }
            if (this.cacheSecondsBuildStage == -1) {
                arrayList.add("cacheSeconds");
            }
            if (this.buttonsBuildStage == -1) {
                arrayList.add("buttons");
            }
            return "Cannot build DialogTemplate, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // CoreInterface.v1_0.Template
    @JsonProperty("onCreated")
    public List<Method> onCreated() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onCreated();
        }
        return this.onCreated;
    }

    @Override // CoreInterface.v1_0.Template
    @JsonProperty("onBound")
    public List<Method> onBound() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onBound();
        }
        return this.onBound;
    }

    @Override // CoreInterface.v1_0.Template
    @JsonProperty("onViewed")
    public List<Method> onViewed() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onViewed();
        }
        return this.onViewed;
    }

    @Override // CoreInterface.v1_0.Template
    @JsonProperty("launchMode")
    public TemplateLaunchMode launchMode() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.launchMode();
        }
        return this.launchMode;
    }

    @Override // CoreInterface.v1_0.Template
    @JsonProperty("cacheSeconds")
    public Long cacheSeconds() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.cacheSeconds();
        }
        return this.cacheSeconds;
    }

    @Override // Touch.DialogTemplateInterface.v1_0.DialogTemplate
    @JsonProperty("header")
    public String header() {
        return this.header;
    }

    @Override // Touch.DialogTemplateInterface.v1_0.DialogTemplate
    @JsonProperty("body")
    public String body() {
        return this.body;
    }

    @Override // Touch.DialogTemplateInterface.v1_0.DialogTemplate
    @JsonProperty("buttons")
    public List<DialogButtonElement> buttons() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.buttons();
        }
        return this.buttons;
    }

    @Override // Touch.DialogTemplateInterface.v1_0.DialogTemplate
    @JsonProperty("closeButton")
    public PlainIconButtonElement closeButton() {
        return this.closeButton;
    }

    public final ImmutableDialogTemplate withOnCreated(Method... methodArr) {
        return new ImmutableDialogTemplate(createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.header, this.body, this.buttons, this.closeButton);
    }

    public final ImmutableDialogTemplate withOnCreated(Iterable<? extends Method> iterable) {
        return this.onCreated == iterable ? this : new ImmutableDialogTemplate(createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.header, this.body, this.buttons, this.closeButton);
    }

    public final ImmutableDialogTemplate withOnBound(Method... methodArr) {
        return new ImmutableDialogTemplate(this.onCreated, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onViewed, this.launchMode, this.cacheSeconds, this.header, this.body, this.buttons, this.closeButton);
    }

    public final ImmutableDialogTemplate withOnBound(Iterable<? extends Method> iterable) {
        if (this.onBound == iterable) {
            return this;
        }
        return new ImmutableDialogTemplate(this.onCreated, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onViewed, this.launchMode, this.cacheSeconds, this.header, this.body, this.buttons, this.closeButton);
    }

    public final ImmutableDialogTemplate withOnViewed(Method... methodArr) {
        return new ImmutableDialogTemplate(this.onCreated, this.onBound, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.launchMode, this.cacheSeconds, this.header, this.body, this.buttons, this.closeButton);
    }

    public final ImmutableDialogTemplate withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableDialogTemplate(this.onCreated, this.onBound, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.launchMode, this.cacheSeconds, this.header, this.body, this.buttons, this.closeButton);
    }

    public final ImmutableDialogTemplate withLaunchMode(TemplateLaunchMode templateLaunchMode) {
        TemplateLaunchMode templateLaunchMode2 = (TemplateLaunchMode) requireNonNull(templateLaunchMode, "launchMode");
        return this.launchMode == templateLaunchMode2 ? this : new ImmutableDialogTemplate(this.onCreated, this.onBound, this.onViewed, templateLaunchMode2, this.cacheSeconds, this.header, this.body, this.buttons, this.closeButton);
    }

    public final ImmutableDialogTemplate withCacheSeconds(Long l) {
        Long l2 = (Long) requireNonNull(l, "cacheSeconds");
        return this.cacheSeconds.equals(l2) ? this : new ImmutableDialogTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, l2, this.header, this.body, this.buttons, this.closeButton);
    }

    public final ImmutableDialogTemplate withHeader(String str) {
        String str2 = (String) requireNonNull(str, "header");
        return this.header.equals(str2) ? this : new ImmutableDialogTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, str2, this.body, this.buttons, this.closeButton);
    }

    public final ImmutableDialogTemplate withBody(String str) {
        String str2 = (String) requireNonNull(str, "body");
        return this.body.equals(str2) ? this : new ImmutableDialogTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.header, str2, this.buttons, this.closeButton);
    }

    public final ImmutableDialogTemplate withButtons(DialogButtonElement... dialogButtonElementArr) {
        return new ImmutableDialogTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.header, this.body, createUnmodifiableList(false, createSafeList(Arrays.asList(dialogButtonElementArr), true, false)), this.closeButton);
    }

    public final ImmutableDialogTemplate withButtons(Iterable<? extends DialogButtonElement> iterable) {
        if (this.buttons == iterable) {
            return this;
        }
        return new ImmutableDialogTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.header, this.body, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.closeButton);
    }

    public final ImmutableDialogTemplate withCloseButton(PlainIconButtonElement plainIconButtonElement) {
        if (this.closeButton == plainIconButtonElement) {
            return this;
        }
        return new ImmutableDialogTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.header, this.body, this.buttons, (PlainIconButtonElement) requireNonNull(plainIconButtonElement, "closeButton"));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableDialogTemplate) && equalTo(0, (ImmutableDialogTemplate) obj);
    }

    private boolean equalTo(int i, ImmutableDialogTemplate immutableDialogTemplate) {
        return this.onCreated.equals(immutableDialogTemplate.onCreated) && this.onBound.equals(immutableDialogTemplate.onBound) && this.onViewed.equals(immutableDialogTemplate.onViewed) && this.launchMode.equals(immutableDialogTemplate.launchMode) && this.cacheSeconds.equals(immutableDialogTemplate.cacheSeconds) && this.header.equals(immutableDialogTemplate.header) && this.body.equals(immutableDialogTemplate.body) && this.buttons.equals(immutableDialogTemplate.buttons) && this.closeButton.equals(immutableDialogTemplate.closeButton);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.onCreated.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onBound.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.onViewed.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.launchMode.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.cacheSeconds.hashCode();
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + this.header.hashCode();
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + this.body.hashCode();
        int iHashCode8 = iHashCode7 + (iHashCode7 << 5) + this.buttons.hashCode();
        return iHashCode8 + (iHashCode8 << 5) + this.closeButton.hashCode();
    }

    public String toString() {
        return "DialogTemplate{onCreated=" + this.onCreated + ", onBound=" + this.onBound + ", onViewed=" + this.onViewed + ", launchMode=" + this.launchMode + ", cacheSeconds=" + this.cacheSeconds + ", header=" + this.header + ", body=" + this.body + ", buttons=" + this.buttons + ", closeButton=" + this.closeButton + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "DialogTemplate", generator = "Immutables")
    static final class Json extends DialogTemplate {

        @Nullable
        String body;
        boolean buttonsIsSet;

        @Nullable
        Long cacheSeconds;

        @Nullable
        PlainIconButtonElement closeButton;

        @Nullable
        String header;

        @Nullable
        TemplateLaunchMode launchMode;
        boolean onBoundIsSet;
        boolean onCreatedIsSet;
        boolean onViewedIsSet;

        @Nullable
        List<Method> onCreated = Collections.emptyList();

        @Nullable
        List<Method> onBound = Collections.emptyList();

        @Nullable
        List<Method> onViewed = Collections.emptyList();

        @Nullable
        List<DialogButtonElement> buttons = Collections.emptyList();

        Json() {
        }

        @JsonProperty("onCreated")
        public void setOnCreated(List<Method> list) {
            this.onCreated = list;
            this.onCreatedIsSet = true;
        }

        @JsonProperty("onBound")
        public void setOnBound(List<Method> list) {
            this.onBound = list;
            this.onBoundIsSet = true;
        }

        @JsonProperty("onViewed")
        public void setOnViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedIsSet = true;
        }

        @JsonProperty("launchMode")
        public void setLaunchMode(TemplateLaunchMode templateLaunchMode) {
            this.launchMode = templateLaunchMode;
        }

        @JsonProperty("cacheSeconds")
        public void setCacheSeconds(Long l) {
            this.cacheSeconds = l;
        }

        @JsonProperty("header")
        public void setHeader(String str) {
            this.header = str;
        }

        @JsonProperty("body")
        public void setBody(String str) {
            this.body = str;
        }

        @JsonProperty("buttons")
        public void setButtons(List<DialogButtonElement> list) {
            this.buttons = list;
            this.buttonsIsSet = true;
        }

        @JsonProperty("closeButton")
        public void setCloseButton(PlainIconButtonElement plainIconButtonElement) {
            this.closeButton = plainIconButtonElement;
        }

        @Override // CoreInterface.v1_0.Template
        public List<Method> onCreated() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Template
        public List<Method> onBound() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Template
        public List<Method> onViewed() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Template
        public TemplateLaunchMode launchMode() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Template
        public Long cacheSeconds() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.DialogTemplateInterface.v1_0.DialogTemplate
        public String header() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.DialogTemplateInterface.v1_0.DialogTemplate
        public String body() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.DialogTemplateInterface.v1_0.DialogTemplate
        public List<DialogButtonElement> buttons() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.DialogTemplateInterface.v1_0.DialogTemplate
        public PlainIconButtonElement closeButton() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableDialogTemplate fromJson(Json json) {
        Builder builder = builder();
        if (json.onCreatedIsSet) {
            builder.addAllOnCreated(json.onCreated);
        }
        if (json.onBoundIsSet) {
            builder.addAllOnBound(json.onBound);
        }
        if (json.onViewedIsSet) {
            builder.addAllOnViewed(json.onViewed);
        }
        TemplateLaunchMode templateLaunchMode = json.launchMode;
        if (templateLaunchMode != null) {
            builder.launchMode(templateLaunchMode);
        }
        Long l = json.cacheSeconds;
        if (l != null) {
            builder.cacheSeconds(l);
        }
        String str = json.header;
        if (str != null) {
            builder.header(str);
        }
        String str2 = json.body;
        if (str2 != null) {
            builder.body(str2);
        }
        if (json.buttonsIsSet) {
            builder.addAllButtons(json.buttons);
        }
        PlainIconButtonElement plainIconButtonElement = json.closeButton;
        if (plainIconButtonElement != null) {
            builder.closeButton(plainIconButtonElement);
        }
        return builder.build();
    }

    public static ImmutableDialogTemplate copyOf(DialogTemplate dialogTemplate) {
        if (dialogTemplate instanceof ImmutableDialogTemplate) {
            return (ImmutableDialogTemplate) dialogTemplate;
        }
        return builder().from(dialogTemplate).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "DialogTemplate", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_BODY = 2;
        private static final long INIT_BIT_CLOSE_BUTTON = 4;
        private static final long INIT_BIT_HEADER = 1;
        private static final long OPT_BIT_BUTTONS = 8;
        private static final long OPT_BIT_ON_BOUND = 2;
        private static final long OPT_BIT_ON_CREATED = 1;
        private static final long OPT_BIT_ON_VIEWED = 4;

        @Nullable
        private String body;
        private List<DialogButtonElement> buttons;

        @Nullable
        private Long cacheSeconds;

        @Nullable
        private PlainIconButtonElement closeButton;

        @Nullable
        private String header;
        private long initBits;

        @Nullable
        private TemplateLaunchMode launchMode;
        private List<Method> onBound;
        private List<Method> onCreated;
        private List<Method> onViewed;
        private long optBits;

        private Builder() {
            this.initBits = 7L;
            this.onCreated = new ArrayList();
            this.onBound = new ArrayList();
            this.onViewed = new ArrayList();
            this.buttons = new ArrayList();
        }

        public final Builder from(Template template) {
            ImmutableDialogTemplate.requireNonNull(template, "instance");
            from((Object) template);
            return this;
        }

        public final Builder from(DialogTemplate dialogTemplate) {
            ImmutableDialogTemplate.requireNonNull(dialogTemplate, "instance");
            from((Object) dialogTemplate);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof Template) {
                Template template = (Template) obj;
                addAllOnBound(template.onBound());
                launchMode(template.launchMode());
                addAllOnViewed(template.onViewed());
                addAllOnCreated(template.onCreated());
                cacheSeconds(template.cacheSeconds());
                j = 31;
            } else {
                j = 0;
            }
            if (obj instanceof DialogTemplate) {
                DialogTemplate dialogTemplate = (DialogTemplate) obj;
                if ((j & 1) == 0) {
                    addAllOnBound(dialogTemplate.onBound());
                    j |= 1;
                }
                addAllButtons(dialogTemplate.buttons());
                if ((j & 2) == 0) {
                    cacheSeconds(dialogTemplate.cacheSeconds());
                    j |= 2;
                }
                closeButton(dialogTemplate.closeButton());
                if ((j & 4) == 0) {
                    launchMode(dialogTemplate.launchMode());
                    j |= 4;
                }
                header(dialogTemplate.header());
                if ((j & 8) == 0) {
                    addAllOnViewed(dialogTemplate.onViewed());
                    j |= 8;
                }
                body(dialogTemplate.body());
                if ((j & 16) == 0) {
                    addAllOnCreated(dialogTemplate.onCreated());
                }
            }
        }

        public final Builder addOnCreated(Method method) {
            this.onCreated.add((Method) ImmutableDialogTemplate.requireNonNull(method, "onCreated element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnCreated(Method... methodArr) {
            for (Method method : methodArr) {
                this.onCreated.add((Method) ImmutableDialogTemplate.requireNonNull(method, "onCreated element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onCreated")
        public final Builder onCreated(Iterable<? extends Method> iterable) {
            this.onCreated.clear();
            return addAllOnCreated(iterable);
        }

        public final Builder addAllOnCreated(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onCreated.add((Method) ImmutableDialogTemplate.requireNonNull(it.next(), "onCreated element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnBound(Method method) {
            this.onBound.add((Method) ImmutableDialogTemplate.requireNonNull(method, "onBound element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnBound(Method... methodArr) {
            for (Method method : methodArr) {
                this.onBound.add((Method) ImmutableDialogTemplate.requireNonNull(method, "onBound element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("onBound")
        public final Builder onBound(Iterable<? extends Method> iterable) {
            this.onBound.clear();
            return addAllOnBound(iterable);
        }

        public final Builder addAllOnBound(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onBound.add((Method) ImmutableDialogTemplate.requireNonNull(it.next(), "onBound element"));
            }
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableDialogTemplate.requireNonNull(method, "onViewed element"));
            this.optBits |= 4;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableDialogTemplate.requireNonNull(method, "onViewed element"));
            }
            this.optBits |= 4;
            return this;
        }

        @JsonProperty("onViewed")
        public final Builder onViewed(Iterable<? extends Method> iterable) {
            this.onViewed.clear();
            return addAllOnViewed(iterable);
        }

        public final Builder addAllOnViewed(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onViewed.add((Method) ImmutableDialogTemplate.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 4;
            return this;
        }

        @JsonProperty("launchMode")
        public final Builder launchMode(TemplateLaunchMode templateLaunchMode) {
            this.launchMode = (TemplateLaunchMode) ImmutableDialogTemplate.requireNonNull(templateLaunchMode, "launchMode");
            return this;
        }

        @JsonProperty("cacheSeconds")
        public final Builder cacheSeconds(Long l) {
            this.cacheSeconds = (Long) ImmutableDialogTemplate.requireNonNull(l, "cacheSeconds");
            return this;
        }

        @JsonProperty("header")
        public final Builder header(String str) {
            this.header = (String) ImmutableDialogTemplate.requireNonNull(str, "header");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("body")
        public final Builder body(String str) {
            this.body = (String) ImmutableDialogTemplate.requireNonNull(str, "body");
            this.initBits &= -3;
            return this;
        }

        public final Builder addButtons(DialogButtonElement dialogButtonElement) {
            this.buttons.add((DialogButtonElement) ImmutableDialogTemplate.requireNonNull(dialogButtonElement, "buttons element"));
            this.optBits |= 8;
            return this;
        }

        public final Builder addButtons(DialogButtonElement... dialogButtonElementArr) {
            for (DialogButtonElement dialogButtonElement : dialogButtonElementArr) {
                this.buttons.add((DialogButtonElement) ImmutableDialogTemplate.requireNonNull(dialogButtonElement, "buttons element"));
            }
            this.optBits |= 8;
            return this;
        }

        @JsonProperty("buttons")
        public final Builder buttons(Iterable<? extends DialogButtonElement> iterable) {
            this.buttons.clear();
            return addAllButtons(iterable);
        }

        public final Builder addAllButtons(Iterable<? extends DialogButtonElement> iterable) {
            Iterator<? extends DialogButtonElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.buttons.add((DialogButtonElement) ImmutableDialogTemplate.requireNonNull(it.next(), "buttons element"));
            }
            this.optBits |= 8;
            return this;
        }

        @JsonProperty("closeButton")
        public final Builder closeButton(PlainIconButtonElement plainIconButtonElement) {
            this.closeButton = (PlainIconButtonElement) ImmutableDialogTemplate.requireNonNull(plainIconButtonElement, "closeButton");
            this.initBits &= -5;
            return this;
        }

        public ImmutableDialogTemplate build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableDialogTemplate(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onCreatedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onBoundIsSet() {
            return (this.optBits & 2) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onViewedIsSet() {
            return (this.optBits & 4) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean buttonsIsSet() {
            return (this.optBits & 8) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("header");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("body");
            }
            if ((this.initBits & 4) != 0) {
                arrayList.add("closeButton");
            }
            return "Cannot build DialogTemplate, some of required attributes are not set " + arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> List<T> createSafeList(Iterable<? extends T> iterable, boolean z, boolean z2) {
        if ((iterable instanceof Collection) && ((Collection) iterable).size() == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (T t : iterable) {
            if (!z2 || t != null) {
                if (z) {
                    requireNonNull(t, "element");
                }
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> List<T> createUnmodifiableList(boolean z, List<T> list) {
        int size = list.size();
        if (size == 0) {
            return Collections.emptyList();
        }
        if (size == 1) {
            return Collections.singletonList(list.get(0));
        }
        if (z) {
            return Collections.unmodifiableList(new ArrayList(list));
        }
        if (list instanceof ArrayList) {
            ((ArrayList) list).trimToSize();
        }
        return Collections.unmodifiableList(list);
    }
}
