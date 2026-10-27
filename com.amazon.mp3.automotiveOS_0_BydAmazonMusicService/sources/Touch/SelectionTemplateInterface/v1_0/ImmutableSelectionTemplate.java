package Touch.SelectionTemplateInterface.v1_0;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
import CoreInterface.v1_0.TemplateLaunchMode;
import Touch.WidgetsInterface.v1_0.ButtonElement;
import Touch.WidgetsInterface.v1_0.SelectionElement;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
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
@Generated(from = "SelectionTemplate", generator = "Immutables")
public final class ImmutableSelectionTemplate extends SelectionTemplate {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final Long cacheSeconds;
    private volatile transient InitShim initShim;
    private final List<SelectionElement> items;
    private final TemplateLaunchMode launchMode;
    private final List<Method> onBound;
    private final List<Method> onCreated;
    private final List<Method> onViewed;
    private final ButtonElement primaryButton;
    private final String primaryHeader;
    private final ButtonElement secondaryButton;

    private ImmutableSelectionTemplate(Builder builder) {
        this.initShim = new InitShim();
        this.primaryHeader = builder.primaryHeader;
        this.items = createUnmodifiableList(true, builder.items);
        this.primaryButton = builder.primaryButton;
        this.secondaryButton = builder.secondaryButton;
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
        this.onCreated = this.initShim.onCreated();
        this.onBound = this.initShim.onBound();
        this.onViewed = this.initShim.onViewed();
        this.launchMode = this.initShim.launchMode();
        this.cacheSeconds = this.initShim.cacheSeconds();
        this.initShim = null;
    }

    private ImmutableSelectionTemplate(List<Method> list, List<Method> list2, List<Method> list3, TemplateLaunchMode templateLaunchMode, Long l, String str, List<SelectionElement> list4, ButtonElement buttonElement, ButtonElement buttonElement2) {
        this.initShim = new InitShim();
        this.onCreated = list;
        this.onBound = list2;
        this.onViewed = list3;
        this.launchMode = templateLaunchMode;
        this.cacheSeconds = l;
        this.primaryHeader = str;
        this.items = list4;
        this.primaryButton = buttonElement;
        this.secondaryButton = buttonElement2;
        this.initShim = null;
    }

    @Generated(from = "SelectionTemplate", generator = "Immutables")
    private final class InitShim {
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
        }

        List<Method> onCreated() {
            byte b = this.onCreatedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onCreatedBuildStage = (byte) -1;
                this.onCreated = ImmutableSelectionTemplate.createUnmodifiableList(false, ImmutableSelectionTemplate.createSafeList(ImmutableSelectionTemplate.super.onCreated(), true, false));
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
                this.onBound = ImmutableSelectionTemplate.createUnmodifiableList(false, ImmutableSelectionTemplate.createSafeList(ImmutableSelectionTemplate.super.onBound(), true, false));
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
                this.onViewed = ImmutableSelectionTemplate.createUnmodifiableList(false, ImmutableSelectionTemplate.createSafeList(ImmutableSelectionTemplate.super.onViewed(), true, false));
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
                this.launchMode = (TemplateLaunchMode) ImmutableSelectionTemplate.requireNonNull(ImmutableSelectionTemplate.super.launchMode(), "launchMode");
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
                this.cacheSeconds = (Long) ImmutableSelectionTemplate.requireNonNull(ImmutableSelectionTemplate.super.cacheSeconds(), "cacheSeconds");
                this.cacheSecondsBuildStage = (byte) 1;
            }
            return this.cacheSeconds;
        }

        void cacheSeconds(Long l) {
            this.cacheSeconds = l;
            this.cacheSecondsBuildStage = (byte) 1;
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
            return "Cannot build SelectionTemplate, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.SelectionTemplateInterface.v1_0.SelectionTemplate
    @JsonProperty("primaryHeader")
    public String primaryHeader() {
        return this.primaryHeader;
    }

    @Override // Touch.SelectionTemplateInterface.v1_0.SelectionTemplate
    @JsonProperty(ContextMappingConstants.ITEMS)
    public List<SelectionElement> items() {
        return this.items;
    }

    @Override // Touch.SelectionTemplateInterface.v1_0.SelectionTemplate
    @JsonProperty("primaryButton")
    public ButtonElement primaryButton() {
        return this.primaryButton;
    }

    @Override // Touch.SelectionTemplateInterface.v1_0.SelectionTemplate
    @JsonProperty("secondaryButton")
    public ButtonElement secondaryButton() {
        return this.secondaryButton;
    }

    public final ImmutableSelectionTemplate withOnCreated(Method... methodArr) {
        return new ImmutableSelectionTemplate(createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.primaryHeader, this.items, this.primaryButton, this.secondaryButton);
    }

    public final ImmutableSelectionTemplate withOnCreated(Iterable<? extends Method> iterable) {
        return this.onCreated == iterable ? this : new ImmutableSelectionTemplate(createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.primaryHeader, this.items, this.primaryButton, this.secondaryButton);
    }

    public final ImmutableSelectionTemplate withOnBound(Method... methodArr) {
        return new ImmutableSelectionTemplate(this.onCreated, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onViewed, this.launchMode, this.cacheSeconds, this.primaryHeader, this.items, this.primaryButton, this.secondaryButton);
    }

    public final ImmutableSelectionTemplate withOnBound(Iterable<? extends Method> iterable) {
        if (this.onBound == iterable) {
            return this;
        }
        return new ImmutableSelectionTemplate(this.onCreated, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onViewed, this.launchMode, this.cacheSeconds, this.primaryHeader, this.items, this.primaryButton, this.secondaryButton);
    }

    public final ImmutableSelectionTemplate withOnViewed(Method... methodArr) {
        return new ImmutableSelectionTemplate(this.onCreated, this.onBound, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.launchMode, this.cacheSeconds, this.primaryHeader, this.items, this.primaryButton, this.secondaryButton);
    }

    public final ImmutableSelectionTemplate withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableSelectionTemplate(this.onCreated, this.onBound, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.launchMode, this.cacheSeconds, this.primaryHeader, this.items, this.primaryButton, this.secondaryButton);
    }

    public final ImmutableSelectionTemplate withLaunchMode(TemplateLaunchMode templateLaunchMode) {
        TemplateLaunchMode templateLaunchMode2 = (TemplateLaunchMode) requireNonNull(templateLaunchMode, "launchMode");
        return this.launchMode == templateLaunchMode2 ? this : new ImmutableSelectionTemplate(this.onCreated, this.onBound, this.onViewed, templateLaunchMode2, this.cacheSeconds, this.primaryHeader, this.items, this.primaryButton, this.secondaryButton);
    }

    public final ImmutableSelectionTemplate withCacheSeconds(Long l) {
        Long l2 = (Long) requireNonNull(l, "cacheSeconds");
        return this.cacheSeconds.equals(l2) ? this : new ImmutableSelectionTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, l2, this.primaryHeader, this.items, this.primaryButton, this.secondaryButton);
    }

    public final ImmutableSelectionTemplate withPrimaryHeader(String str) {
        String str2 = (String) requireNonNull(str, "primaryHeader");
        return this.primaryHeader.equals(str2) ? this : new ImmutableSelectionTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, str2, this.items, this.primaryButton, this.secondaryButton);
    }

    public final ImmutableSelectionTemplate withItems(SelectionElement... selectionElementArr) {
        return new ImmutableSelectionTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.primaryHeader, createUnmodifiableList(false, createSafeList(Arrays.asList(selectionElementArr), true, false)), this.primaryButton, this.secondaryButton);
    }

    public final ImmutableSelectionTemplate withItems(Iterable<? extends SelectionElement> iterable) {
        if (this.items == iterable) {
            return this;
        }
        return new ImmutableSelectionTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.primaryHeader, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.primaryButton, this.secondaryButton);
    }

    public final ImmutableSelectionTemplate withPrimaryButton(ButtonElement buttonElement) {
        if (this.primaryButton == buttonElement) {
            return this;
        }
        return new ImmutableSelectionTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.primaryHeader, this.items, (ButtonElement) requireNonNull(buttonElement, "primaryButton"), this.secondaryButton);
    }

    public final ImmutableSelectionTemplate withSecondaryButton(ButtonElement buttonElement) {
        if (this.secondaryButton == buttonElement) {
            return this;
        }
        return new ImmutableSelectionTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.primaryHeader, this.items, this.primaryButton, (ButtonElement) requireNonNull(buttonElement, "secondaryButton"));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSelectionTemplate) && equalTo(0, (ImmutableSelectionTemplate) obj);
    }

    private boolean equalTo(int i, ImmutableSelectionTemplate immutableSelectionTemplate) {
        return this.onCreated.equals(immutableSelectionTemplate.onCreated) && this.onBound.equals(immutableSelectionTemplate.onBound) && this.onViewed.equals(immutableSelectionTemplate.onViewed) && this.launchMode.equals(immutableSelectionTemplate.launchMode) && this.cacheSeconds.equals(immutableSelectionTemplate.cacheSeconds) && this.primaryHeader.equals(immutableSelectionTemplate.primaryHeader) && this.items.equals(immutableSelectionTemplate.items) && this.primaryButton.equals(immutableSelectionTemplate.primaryButton) && this.secondaryButton.equals(immutableSelectionTemplate.secondaryButton);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.onCreated.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onBound.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.onViewed.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.launchMode.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.cacheSeconds.hashCode();
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + this.primaryHeader.hashCode();
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + this.items.hashCode();
        int iHashCode8 = iHashCode7 + (iHashCode7 << 5) + this.primaryButton.hashCode();
        return iHashCode8 + (iHashCode8 << 5) + this.secondaryButton.hashCode();
    }

    public String toString() {
        return "SelectionTemplate{onCreated=" + this.onCreated + ", onBound=" + this.onBound + ", onViewed=" + this.onViewed + ", launchMode=" + this.launchMode + ", cacheSeconds=" + this.cacheSeconds + ", primaryHeader=" + this.primaryHeader + ", items=" + this.items + ", primaryButton=" + this.primaryButton + ", secondaryButton=" + this.secondaryButton + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SelectionTemplate", generator = "Immutables")
    static final class Json extends SelectionTemplate {

        @Nullable
        Long cacheSeconds;

        @Nullable
        TemplateLaunchMode launchMode;
        boolean onBoundIsSet;
        boolean onCreatedIsSet;
        boolean onViewedIsSet;

        @Nullable
        ButtonElement primaryButton;

        @Nullable
        String primaryHeader;

        @Nullable
        ButtonElement secondaryButton;

        @Nullable
        List<Method> onCreated = Collections.emptyList();

        @Nullable
        List<Method> onBound = Collections.emptyList();

        @Nullable
        List<Method> onViewed = Collections.emptyList();

        @Nullable
        List<SelectionElement> items = Collections.emptyList();

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

        @JsonProperty("primaryHeader")
        public void setPrimaryHeader(String str) {
            this.primaryHeader = str;
        }

        @JsonProperty(ContextMappingConstants.ITEMS)
        public void setItems(List<SelectionElement> list) {
            this.items = list;
        }

        @JsonProperty("primaryButton")
        public void setPrimaryButton(ButtonElement buttonElement) {
            this.primaryButton = buttonElement;
        }

        @JsonProperty("secondaryButton")
        public void setSecondaryButton(ButtonElement buttonElement) {
            this.secondaryButton = buttonElement;
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

        @Override // Touch.SelectionTemplateInterface.v1_0.SelectionTemplate
        public String primaryHeader() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SelectionTemplateInterface.v1_0.SelectionTemplate
        public List<SelectionElement> items() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SelectionTemplateInterface.v1_0.SelectionTemplate
        public ButtonElement primaryButton() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SelectionTemplateInterface.v1_0.SelectionTemplate
        public ButtonElement secondaryButton() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSelectionTemplate fromJson(Json json) {
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
        String str = json.primaryHeader;
        if (str != null) {
            builder.primaryHeader(str);
        }
        List<SelectionElement> list = json.items;
        if (list != null) {
            builder.addAllItems(list);
        }
        ButtonElement buttonElement = json.primaryButton;
        if (buttonElement != null) {
            builder.primaryButton(buttonElement);
        }
        ButtonElement buttonElement2 = json.secondaryButton;
        if (buttonElement2 != null) {
            builder.secondaryButton(buttonElement2);
        }
        return builder.build();
    }

    public static ImmutableSelectionTemplate copyOf(SelectionTemplate selectionTemplate) {
        if (selectionTemplate instanceof ImmutableSelectionTemplate) {
            return (ImmutableSelectionTemplate) selectionTemplate;
        }
        return builder().from(selectionTemplate).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SelectionTemplate", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_PRIMARY_BUTTON = 2;
        private static final long INIT_BIT_PRIMARY_HEADER = 1;
        private static final long INIT_BIT_SECONDARY_BUTTON = 4;
        private static final long OPT_BIT_ON_BOUND = 2;
        private static final long OPT_BIT_ON_CREATED = 1;
        private static final long OPT_BIT_ON_VIEWED = 4;

        @Nullable
        private Long cacheSeconds;
        private long initBits;
        private List<SelectionElement> items;

        @Nullable
        private TemplateLaunchMode launchMode;
        private List<Method> onBound;
        private List<Method> onCreated;
        private List<Method> onViewed;
        private long optBits;

        @Nullable
        private ButtonElement primaryButton;

        @Nullable
        private String primaryHeader;

        @Nullable
        private ButtonElement secondaryButton;

        private Builder() {
            this.initBits = 7L;
            this.onCreated = new ArrayList();
            this.onBound = new ArrayList();
            this.onViewed = new ArrayList();
            this.items = new ArrayList();
        }

        public final Builder from(Template template) {
            ImmutableSelectionTemplate.requireNonNull(template, "instance");
            from((Object) template);
            return this;
        }

        public final Builder from(SelectionTemplate selectionTemplate) {
            ImmutableSelectionTemplate.requireNonNull(selectionTemplate, "instance");
            from((Object) selectionTemplate);
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
            if (obj instanceof SelectionTemplate) {
                SelectionTemplate selectionTemplate = (SelectionTemplate) obj;
                if ((j & 1) == 0) {
                    addAllOnBound(selectionTemplate.onBound());
                    j |= 1;
                }
                secondaryButton(selectionTemplate.secondaryButton());
                if ((j & 2) == 0) {
                    cacheSeconds(selectionTemplate.cacheSeconds());
                    j |= 2;
                }
                if ((j & 4) == 0) {
                    launchMode(selectionTemplate.launchMode());
                    j |= 4;
                }
                primaryButton(selectionTemplate.primaryButton());
                if ((j & 8) == 0) {
                    addAllOnViewed(selectionTemplate.onViewed());
                    j |= 8;
                }
                if ((j & 16) == 0) {
                    addAllOnCreated(selectionTemplate.onCreated());
                }
                addAllItems(selectionTemplate.items());
                primaryHeader(selectionTemplate.primaryHeader());
            }
        }

        public final Builder addOnCreated(Method method) {
            this.onCreated.add((Method) ImmutableSelectionTemplate.requireNonNull(method, "onCreated element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnCreated(Method... methodArr) {
            for (Method method : methodArr) {
                this.onCreated.add((Method) ImmutableSelectionTemplate.requireNonNull(method, "onCreated element"));
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
                this.onCreated.add((Method) ImmutableSelectionTemplate.requireNonNull(it.next(), "onCreated element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnBound(Method method) {
            this.onBound.add((Method) ImmutableSelectionTemplate.requireNonNull(method, "onBound element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnBound(Method... methodArr) {
            for (Method method : methodArr) {
                this.onBound.add((Method) ImmutableSelectionTemplate.requireNonNull(method, "onBound element"));
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
                this.onBound.add((Method) ImmutableSelectionTemplate.requireNonNull(it.next(), "onBound element"));
            }
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableSelectionTemplate.requireNonNull(method, "onViewed element"));
            this.optBits |= 4;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableSelectionTemplate.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableSelectionTemplate.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 4;
            return this;
        }

        @JsonProperty("launchMode")
        public final Builder launchMode(TemplateLaunchMode templateLaunchMode) {
            this.launchMode = (TemplateLaunchMode) ImmutableSelectionTemplate.requireNonNull(templateLaunchMode, "launchMode");
            return this;
        }

        @JsonProperty("cacheSeconds")
        public final Builder cacheSeconds(Long l) {
            this.cacheSeconds = (Long) ImmutableSelectionTemplate.requireNonNull(l, "cacheSeconds");
            return this;
        }

        @JsonProperty("primaryHeader")
        public final Builder primaryHeader(String str) {
            this.primaryHeader = (String) ImmutableSelectionTemplate.requireNonNull(str, "primaryHeader");
            this.initBits &= -2;
            return this;
        }

        public final Builder addItems(SelectionElement selectionElement) {
            this.items.add((SelectionElement) ImmutableSelectionTemplate.requireNonNull(selectionElement, "items element"));
            return this;
        }

        public final Builder addItems(SelectionElement... selectionElementArr) {
            for (SelectionElement selectionElement : selectionElementArr) {
                this.items.add((SelectionElement) ImmutableSelectionTemplate.requireNonNull(selectionElement, "items element"));
            }
            return this;
        }

        @JsonProperty(ContextMappingConstants.ITEMS)
        public final Builder items(Iterable<? extends SelectionElement> iterable) {
            this.items.clear();
            return addAllItems(iterable);
        }

        public final Builder addAllItems(Iterable<? extends SelectionElement> iterable) {
            Iterator<? extends SelectionElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.items.add((SelectionElement) ImmutableSelectionTemplate.requireNonNull(it.next(), "items element"));
            }
            return this;
        }

        @JsonProperty("primaryButton")
        public final Builder primaryButton(ButtonElement buttonElement) {
            this.primaryButton = (ButtonElement) ImmutableSelectionTemplate.requireNonNull(buttonElement, "primaryButton");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("secondaryButton")
        public final Builder secondaryButton(ButtonElement buttonElement) {
            this.secondaryButton = (ButtonElement) ImmutableSelectionTemplate.requireNonNull(buttonElement, "secondaryButton");
            this.initBits &= -5;
            return this;
        }

        public ImmutableSelectionTemplate build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableSelectionTemplate(this);
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

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("primaryHeader");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("primaryButton");
            }
            if ((this.initBits & 4) != 0) {
                arrayList.add("secondaryButton");
            }
            return "Cannot build SelectionTemplate, some of required attributes are not set " + arrayList;
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
