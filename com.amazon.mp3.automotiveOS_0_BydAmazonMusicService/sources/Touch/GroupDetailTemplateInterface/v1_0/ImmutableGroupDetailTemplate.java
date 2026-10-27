package Touch.GroupDetailTemplateInterface.v1_0;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
import CoreInterface.v1_0.TemplateLaunchMode;
import Touch.WidgetsInterface.v1_0.WidgetElement;
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
@Generated(from = "GroupDetailTemplate", generator = "Immutables")
public final class ImmutableGroupDetailTemplate extends GroupDetailTemplate {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;

    @Nullable
    private final String backgroundImage;
    private final Long cacheSeconds;
    private final List<WidgetElement> elements;
    private volatile transient InitShim initShim;
    private final TemplateLaunchMode launchMode;
    private final List<Method> onBound;
    private final List<Method> onCreated;
    private final List<Method> onEndOfList;
    private final List<Method> onViewed;

    @Nullable
    private final String primaryHeader;

    @Nullable
    private final String secondaryHeader;

    @Nullable
    private final String tertiaryHeader;

    private ImmutableGroupDetailTemplate(Builder builder) {
        this.initShim = new InitShim();
        this.primaryHeader = builder.primaryHeader;
        this.secondaryHeader = builder.secondaryHeader;
        this.tertiaryHeader = builder.tertiaryHeader;
        this.backgroundImage = builder.backgroundImage;
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
        if (builder.elementsIsSet()) {
            this.initShim.elements(createUnmodifiableList(true, builder.elements));
        }
        if (builder.onEndOfListIsSet()) {
            this.initShim.onEndOfList(createUnmodifiableList(true, builder.onEndOfList));
        }
        this.onCreated = this.initShim.onCreated();
        this.onBound = this.initShim.onBound();
        this.onViewed = this.initShim.onViewed();
        this.launchMode = this.initShim.launchMode();
        this.cacheSeconds = this.initShim.cacheSeconds();
        this.elements = this.initShim.elements();
        this.onEndOfList = this.initShim.onEndOfList();
        this.initShim = null;
    }

    private ImmutableGroupDetailTemplate(List<Method> list, List<Method> list2, List<Method> list3, TemplateLaunchMode templateLaunchMode, Long l, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, List<WidgetElement> list4, List<Method> list5) {
        this.initShim = new InitShim();
        this.onCreated = list;
        this.onBound = list2;
        this.onViewed = list3;
        this.launchMode = templateLaunchMode;
        this.cacheSeconds = l;
        this.primaryHeader = str;
        this.secondaryHeader = str2;
        this.tertiaryHeader = str3;
        this.backgroundImage = str4;
        this.elements = list4;
        this.onEndOfList = list5;
        this.initShim = null;
    }

    @Generated(from = "GroupDetailTemplate", generator = "Immutables")
    private final class InitShim {
        private Long cacheSeconds;
        private byte cacheSecondsBuildStage;
        private List<WidgetElement> elements;
        private byte elementsBuildStage;
        private TemplateLaunchMode launchMode;
        private byte launchModeBuildStage;
        private List<Method> onBound;
        private byte onBoundBuildStage;
        private List<Method> onCreated;
        private byte onCreatedBuildStage;
        private List<Method> onEndOfList;
        private byte onEndOfListBuildStage;
        private List<Method> onViewed;
        private byte onViewedBuildStage;

        private InitShim() {
            this.onCreatedBuildStage = (byte) 0;
            this.onBoundBuildStage = (byte) 0;
            this.onViewedBuildStage = (byte) 0;
            this.launchModeBuildStage = (byte) 0;
            this.cacheSecondsBuildStage = (byte) 0;
            this.elementsBuildStage = (byte) 0;
            this.onEndOfListBuildStage = (byte) 0;
        }

        List<Method> onCreated() {
            byte b = this.onCreatedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onCreatedBuildStage = (byte) -1;
                this.onCreated = ImmutableGroupDetailTemplate.createUnmodifiableList(false, ImmutableGroupDetailTemplate.createSafeList(ImmutableGroupDetailTemplate.super.onCreated(), true, false));
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
                this.onBound = ImmutableGroupDetailTemplate.createUnmodifiableList(false, ImmutableGroupDetailTemplate.createSafeList(ImmutableGroupDetailTemplate.super.onBound(), true, false));
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
                this.onViewed = ImmutableGroupDetailTemplate.createUnmodifiableList(false, ImmutableGroupDetailTemplate.createSafeList(ImmutableGroupDetailTemplate.super.onViewed(), true, false));
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
                this.launchMode = (TemplateLaunchMode) ImmutableGroupDetailTemplate.requireNonNull(ImmutableGroupDetailTemplate.super.launchMode(), "launchMode");
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
                this.cacheSeconds = (Long) ImmutableGroupDetailTemplate.requireNonNull(ImmutableGroupDetailTemplate.super.cacheSeconds(), "cacheSeconds");
                this.cacheSecondsBuildStage = (byte) 1;
            }
            return this.cacheSeconds;
        }

        void cacheSeconds(Long l) {
            this.cacheSeconds = l;
            this.cacheSecondsBuildStage = (byte) 1;
        }

        List<WidgetElement> elements() {
            byte b = this.elementsBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.elementsBuildStage = (byte) -1;
                this.elements = ImmutableGroupDetailTemplate.createUnmodifiableList(false, ImmutableGroupDetailTemplate.createSafeList(ImmutableGroupDetailTemplate.super.elements(), true, false));
                this.elementsBuildStage = (byte) 1;
            }
            return this.elements;
        }

        void elements(List<WidgetElement> list) {
            this.elements = list;
            this.elementsBuildStage = (byte) 1;
        }

        List<Method> onEndOfList() {
            byte b = this.onEndOfListBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onEndOfListBuildStage = (byte) -1;
                this.onEndOfList = ImmutableGroupDetailTemplate.createUnmodifiableList(false, ImmutableGroupDetailTemplate.createSafeList(ImmutableGroupDetailTemplate.super.onEndOfList(), true, false));
                this.onEndOfListBuildStage = (byte) 1;
            }
            return this.onEndOfList;
        }

        void onEndOfList(List<Method> list) {
            this.onEndOfList = list;
            this.onEndOfListBuildStage = (byte) 1;
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
            if (this.elementsBuildStage == -1) {
                arrayList.add(ContextMappingConstants.ELEMENTS);
            }
            if (this.onEndOfListBuildStage == -1) {
                arrayList.add("onEndOfList");
            }
            return "Cannot build GroupDetailTemplate, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.GroupDetailTemplateInterface.v1_0.GroupDetailTemplate
    @JsonProperty("primaryHeader")
    @Nullable
    public String primaryHeader() {
        return this.primaryHeader;
    }

    @Override // Touch.GroupDetailTemplateInterface.v1_0.GroupDetailTemplate
    @JsonProperty("secondaryHeader")
    @Nullable
    public String secondaryHeader() {
        return this.secondaryHeader;
    }

    @Override // Touch.GroupDetailTemplateInterface.v1_0.GroupDetailTemplate
    @JsonProperty("tertiaryHeader")
    @Nullable
    public String tertiaryHeader() {
        return this.tertiaryHeader;
    }

    @Override // Touch.GroupDetailTemplateInterface.v1_0.GroupDetailTemplate
    @JsonProperty("backgroundImage")
    @Nullable
    public String backgroundImage() {
        return this.backgroundImage;
    }

    @Override // Touch.GroupDetailTemplateInterface.v1_0.GroupDetailTemplate
    @JsonProperty(ContextMappingConstants.ELEMENTS)
    public List<WidgetElement> elements() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.elements();
        }
        return this.elements;
    }

    @Override // Touch.GroupDetailTemplateInterface.v1_0.GroupDetailTemplate
    @JsonProperty("onEndOfList")
    public List<Method> onEndOfList() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onEndOfList();
        }
        return this.onEndOfList;
    }

    public final ImmutableGroupDetailTemplate withOnCreated(Method... methodArr) {
        return new ImmutableGroupDetailTemplate(createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.primaryHeader, this.secondaryHeader, this.tertiaryHeader, this.backgroundImage, this.elements, this.onEndOfList);
    }

    public final ImmutableGroupDetailTemplate withOnCreated(Iterable<? extends Method> iterable) {
        return this.onCreated == iterable ? this : new ImmutableGroupDetailTemplate(createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.primaryHeader, this.secondaryHeader, this.tertiaryHeader, this.backgroundImage, this.elements, this.onEndOfList);
    }

    public final ImmutableGroupDetailTemplate withOnBound(Method... methodArr) {
        return new ImmutableGroupDetailTemplate(this.onCreated, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onViewed, this.launchMode, this.cacheSeconds, this.primaryHeader, this.secondaryHeader, this.tertiaryHeader, this.backgroundImage, this.elements, this.onEndOfList);
    }

    public final ImmutableGroupDetailTemplate withOnBound(Iterable<? extends Method> iterable) {
        if (this.onBound == iterable) {
            return this;
        }
        return new ImmutableGroupDetailTemplate(this.onCreated, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onViewed, this.launchMode, this.cacheSeconds, this.primaryHeader, this.secondaryHeader, this.tertiaryHeader, this.backgroundImage, this.elements, this.onEndOfList);
    }

    public final ImmutableGroupDetailTemplate withOnViewed(Method... methodArr) {
        return new ImmutableGroupDetailTemplate(this.onCreated, this.onBound, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.launchMode, this.cacheSeconds, this.primaryHeader, this.secondaryHeader, this.tertiaryHeader, this.backgroundImage, this.elements, this.onEndOfList);
    }

    public final ImmutableGroupDetailTemplate withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableGroupDetailTemplate(this.onCreated, this.onBound, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.launchMode, this.cacheSeconds, this.primaryHeader, this.secondaryHeader, this.tertiaryHeader, this.backgroundImage, this.elements, this.onEndOfList);
    }

    public final ImmutableGroupDetailTemplate withLaunchMode(TemplateLaunchMode templateLaunchMode) {
        TemplateLaunchMode templateLaunchMode2 = (TemplateLaunchMode) requireNonNull(templateLaunchMode, "launchMode");
        return this.launchMode == templateLaunchMode2 ? this : new ImmutableGroupDetailTemplate(this.onCreated, this.onBound, this.onViewed, templateLaunchMode2, this.cacheSeconds, this.primaryHeader, this.secondaryHeader, this.tertiaryHeader, this.backgroundImage, this.elements, this.onEndOfList);
    }

    public final ImmutableGroupDetailTemplate withCacheSeconds(Long l) {
        Long l2 = (Long) requireNonNull(l, "cacheSeconds");
        return this.cacheSeconds.equals(l2) ? this : new ImmutableGroupDetailTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, l2, this.primaryHeader, this.secondaryHeader, this.tertiaryHeader, this.backgroundImage, this.elements, this.onEndOfList);
    }

    public final ImmutableGroupDetailTemplate withPrimaryHeader(@Nullable String str) {
        return equals(this.primaryHeader, str) ? this : new ImmutableGroupDetailTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, str, this.secondaryHeader, this.tertiaryHeader, this.backgroundImage, this.elements, this.onEndOfList);
    }

    public final ImmutableGroupDetailTemplate withSecondaryHeader(@Nullable String str) {
        return equals(this.secondaryHeader, str) ? this : new ImmutableGroupDetailTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.primaryHeader, str, this.tertiaryHeader, this.backgroundImage, this.elements, this.onEndOfList);
    }

    public final ImmutableGroupDetailTemplate withTertiaryHeader(@Nullable String str) {
        return equals(this.tertiaryHeader, str) ? this : new ImmutableGroupDetailTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.primaryHeader, this.secondaryHeader, str, this.backgroundImage, this.elements, this.onEndOfList);
    }

    public final ImmutableGroupDetailTemplate withBackgroundImage(@Nullable String str) {
        return equals(this.backgroundImage, str) ? this : new ImmutableGroupDetailTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.primaryHeader, this.secondaryHeader, this.tertiaryHeader, str, this.elements, this.onEndOfList);
    }

    public final ImmutableGroupDetailTemplate withElements(WidgetElement... widgetElementArr) {
        return new ImmutableGroupDetailTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.primaryHeader, this.secondaryHeader, this.tertiaryHeader, this.backgroundImage, createUnmodifiableList(false, createSafeList(Arrays.asList(widgetElementArr), true, false)), this.onEndOfList);
    }

    public final ImmutableGroupDetailTemplate withElements(Iterable<? extends WidgetElement> iterable) {
        if (this.elements == iterable) {
            return this;
        }
        return new ImmutableGroupDetailTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.primaryHeader, this.secondaryHeader, this.tertiaryHeader, this.backgroundImage, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onEndOfList);
    }

    public final ImmutableGroupDetailTemplate withOnEndOfList(Method... methodArr) {
        return new ImmutableGroupDetailTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.primaryHeader, this.secondaryHeader, this.tertiaryHeader, this.backgroundImage, this.elements, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableGroupDetailTemplate withOnEndOfList(Iterable<? extends Method> iterable) {
        if (this.onEndOfList == iterable) {
            return this;
        }
        return new ImmutableGroupDetailTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.primaryHeader, this.secondaryHeader, this.tertiaryHeader, this.backgroundImage, this.elements, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableGroupDetailTemplate) && equalTo(0, (ImmutableGroupDetailTemplate) obj);
    }

    private boolean equalTo(int i, ImmutableGroupDetailTemplate immutableGroupDetailTemplate) {
        return this.onCreated.equals(immutableGroupDetailTemplate.onCreated) && this.onBound.equals(immutableGroupDetailTemplate.onBound) && this.onViewed.equals(immutableGroupDetailTemplate.onViewed) && this.launchMode.equals(immutableGroupDetailTemplate.launchMode) && this.cacheSeconds.equals(immutableGroupDetailTemplate.cacheSeconds) && equals(this.primaryHeader, immutableGroupDetailTemplate.primaryHeader) && equals(this.secondaryHeader, immutableGroupDetailTemplate.secondaryHeader) && equals(this.tertiaryHeader, immutableGroupDetailTemplate.tertiaryHeader) && equals(this.backgroundImage, immutableGroupDetailTemplate.backgroundImage) && this.elements.equals(immutableGroupDetailTemplate.elements) && this.onEndOfList.equals(immutableGroupDetailTemplate.onEndOfList);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.onCreated.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onBound.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.onViewed.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.launchMode.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.cacheSeconds.hashCode();
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + hashCode(this.primaryHeader);
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + hashCode(this.secondaryHeader);
        int iHashCode8 = iHashCode7 + (iHashCode7 << 5) + hashCode(this.tertiaryHeader);
        int iHashCode9 = iHashCode8 + (iHashCode8 << 5) + hashCode(this.backgroundImage);
        int iHashCode10 = iHashCode9 + (iHashCode9 << 5) + this.elements.hashCode();
        return iHashCode10 + (iHashCode10 << 5) + this.onEndOfList.hashCode();
    }

    public String toString() {
        return "GroupDetailTemplate{onCreated=" + this.onCreated + ", onBound=" + this.onBound + ", onViewed=" + this.onViewed + ", launchMode=" + this.launchMode + ", cacheSeconds=" + this.cacheSeconds + ", primaryHeader=" + this.primaryHeader + ", secondaryHeader=" + this.secondaryHeader + ", tertiaryHeader=" + this.tertiaryHeader + ", backgroundImage=" + this.backgroundImage + ", elements=" + this.elements + ", onEndOfList=" + this.onEndOfList + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "GroupDetailTemplate", generator = "Immutables")
    static final class Json extends GroupDetailTemplate {

        @Nullable
        String backgroundImage;

        @Nullable
        Long cacheSeconds;
        boolean elementsIsSet;

        @Nullable
        TemplateLaunchMode launchMode;
        boolean onBoundIsSet;
        boolean onCreatedIsSet;
        boolean onEndOfListIsSet;
        boolean onViewedIsSet;

        @Nullable
        String primaryHeader;

        @Nullable
        String secondaryHeader;

        @Nullable
        String tertiaryHeader;

        @Nullable
        List<Method> onCreated = Collections.emptyList();

        @Nullable
        List<Method> onBound = Collections.emptyList();

        @Nullable
        List<Method> onViewed = Collections.emptyList();

        @Nullable
        List<WidgetElement> elements = Collections.emptyList();

        @Nullable
        List<Method> onEndOfList = Collections.emptyList();

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
        public void setPrimaryHeader(@Nullable String str) {
            this.primaryHeader = str;
        }

        @JsonProperty("secondaryHeader")
        public void setSecondaryHeader(@Nullable String str) {
            this.secondaryHeader = str;
        }

        @JsonProperty("tertiaryHeader")
        public void setTertiaryHeader(@Nullable String str) {
            this.tertiaryHeader = str;
        }

        @JsonProperty("backgroundImage")
        public void setBackgroundImage(@Nullable String str) {
            this.backgroundImage = str;
        }

        @JsonProperty(ContextMappingConstants.ELEMENTS)
        public void setElements(List<WidgetElement> list) {
            this.elements = list;
            this.elementsIsSet = true;
        }

        @JsonProperty("onEndOfList")
        public void setOnEndOfList(List<Method> list) {
            this.onEndOfList = list;
            this.onEndOfListIsSet = true;
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

        @Override // Touch.GroupDetailTemplateInterface.v1_0.GroupDetailTemplate
        public String primaryHeader() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.GroupDetailTemplateInterface.v1_0.GroupDetailTemplate
        public String secondaryHeader() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.GroupDetailTemplateInterface.v1_0.GroupDetailTemplate
        public String tertiaryHeader() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.GroupDetailTemplateInterface.v1_0.GroupDetailTemplate
        public String backgroundImage() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.GroupDetailTemplateInterface.v1_0.GroupDetailTemplate
        public List<WidgetElement> elements() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.GroupDetailTemplateInterface.v1_0.GroupDetailTemplate
        public List<Method> onEndOfList() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableGroupDetailTemplate fromJson(Json json) {
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
        String str2 = json.secondaryHeader;
        if (str2 != null) {
            builder.secondaryHeader(str2);
        }
        String str3 = json.tertiaryHeader;
        if (str3 != null) {
            builder.tertiaryHeader(str3);
        }
        String str4 = json.backgroundImage;
        if (str4 != null) {
            builder.backgroundImage(str4);
        }
        if (json.elementsIsSet) {
            builder.addAllElements(json.elements);
        }
        if (json.onEndOfListIsSet) {
            builder.addAllOnEndOfList(json.onEndOfList);
        }
        return builder.build();
    }

    public static ImmutableGroupDetailTemplate copyOf(GroupDetailTemplate groupDetailTemplate) {
        if (groupDetailTemplate instanceof ImmutableGroupDetailTemplate) {
            return (ImmutableGroupDetailTemplate) groupDetailTemplate;
        }
        return builder().from(groupDetailTemplate).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "GroupDetailTemplate", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_ELEMENTS = 8;
        private static final long OPT_BIT_ON_BOUND = 2;
        private static final long OPT_BIT_ON_CREATED = 1;
        private static final long OPT_BIT_ON_END_OF_LIST = 16;
        private static final long OPT_BIT_ON_VIEWED = 4;

        @Nullable
        private String backgroundImage;

        @Nullable
        private Long cacheSeconds;
        private List<WidgetElement> elements;

        @Nullable
        private TemplateLaunchMode launchMode;
        private List<Method> onBound;
        private List<Method> onCreated;
        private List<Method> onEndOfList;
        private List<Method> onViewed;
        private long optBits;

        @Nullable
        private String primaryHeader;

        @Nullable
        private String secondaryHeader;

        @Nullable
        private String tertiaryHeader;

        private Builder() {
            this.onCreated = new ArrayList();
            this.onBound = new ArrayList();
            this.onViewed = new ArrayList();
            this.elements = new ArrayList();
            this.onEndOfList = new ArrayList();
        }

        public final Builder from(Template template) {
            ImmutableGroupDetailTemplate.requireNonNull(template, "instance");
            from((Object) template);
            return this;
        }

        public final Builder from(GroupDetailTemplate groupDetailTemplate) {
            ImmutableGroupDetailTemplate.requireNonNull(groupDetailTemplate, "instance");
            from((Object) groupDetailTemplate);
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
            if (obj instanceof GroupDetailTemplate) {
                GroupDetailTemplate groupDetailTemplate = (GroupDetailTemplate) obj;
                if ((j & 1) == 0) {
                    addAllOnBound(groupDetailTemplate.onBound());
                    j |= 1;
                }
                String strTertiaryHeader = groupDetailTemplate.tertiaryHeader();
                if (strTertiaryHeader != null) {
                    tertiaryHeader(strTertiaryHeader);
                }
                if ((j & 2) == 0) {
                    cacheSeconds(groupDetailTemplate.cacheSeconds());
                    j |= 2;
                }
                String strBackgroundImage = groupDetailTemplate.backgroundImage();
                if (strBackgroundImage != null) {
                    backgroundImage(strBackgroundImage);
                }
                addAllElements(groupDetailTemplate.elements());
                if ((j & 4) == 0) {
                    launchMode(groupDetailTemplate.launchMode());
                    j |= 4;
                }
                String strSecondaryHeader = groupDetailTemplate.secondaryHeader();
                if (strSecondaryHeader != null) {
                    secondaryHeader(strSecondaryHeader);
                }
                if ((j & 8) == 0) {
                    addAllOnViewed(groupDetailTemplate.onViewed());
                    j |= 8;
                }
                addAllOnEndOfList(groupDetailTemplate.onEndOfList());
                if ((j & 16) == 0) {
                    addAllOnCreated(groupDetailTemplate.onCreated());
                }
                String strPrimaryHeader = groupDetailTemplate.primaryHeader();
                if (strPrimaryHeader != null) {
                    primaryHeader(strPrimaryHeader);
                }
            }
        }

        public final Builder addOnCreated(Method method) {
            this.onCreated.add((Method) ImmutableGroupDetailTemplate.requireNonNull(method, "onCreated element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnCreated(Method... methodArr) {
            for (Method method : methodArr) {
                this.onCreated.add((Method) ImmutableGroupDetailTemplate.requireNonNull(method, "onCreated element"));
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
                this.onCreated.add((Method) ImmutableGroupDetailTemplate.requireNonNull(it.next(), "onCreated element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnBound(Method method) {
            this.onBound.add((Method) ImmutableGroupDetailTemplate.requireNonNull(method, "onBound element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnBound(Method... methodArr) {
            for (Method method : methodArr) {
                this.onBound.add((Method) ImmutableGroupDetailTemplate.requireNonNull(method, "onBound element"));
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
                this.onBound.add((Method) ImmutableGroupDetailTemplate.requireNonNull(it.next(), "onBound element"));
            }
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableGroupDetailTemplate.requireNonNull(method, "onViewed element"));
            this.optBits |= 4;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableGroupDetailTemplate.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableGroupDetailTemplate.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 4;
            return this;
        }

        @JsonProperty("launchMode")
        public final Builder launchMode(TemplateLaunchMode templateLaunchMode) {
            this.launchMode = (TemplateLaunchMode) ImmutableGroupDetailTemplate.requireNonNull(templateLaunchMode, "launchMode");
            return this;
        }

        @JsonProperty("cacheSeconds")
        public final Builder cacheSeconds(Long l) {
            this.cacheSeconds = (Long) ImmutableGroupDetailTemplate.requireNonNull(l, "cacheSeconds");
            return this;
        }

        @JsonProperty("primaryHeader")
        public final Builder primaryHeader(@Nullable String str) {
            this.primaryHeader = str;
            return this;
        }

        @JsonProperty("secondaryHeader")
        public final Builder secondaryHeader(@Nullable String str) {
            this.secondaryHeader = str;
            return this;
        }

        @JsonProperty("tertiaryHeader")
        public final Builder tertiaryHeader(@Nullable String str) {
            this.tertiaryHeader = str;
            return this;
        }

        @JsonProperty("backgroundImage")
        public final Builder backgroundImage(@Nullable String str) {
            this.backgroundImage = str;
            return this;
        }

        public final Builder addElements(WidgetElement widgetElement) {
            this.elements.add((WidgetElement) ImmutableGroupDetailTemplate.requireNonNull(widgetElement, "elements element"));
            this.optBits |= 8;
            return this;
        }

        public final Builder addElements(WidgetElement... widgetElementArr) {
            for (WidgetElement widgetElement : widgetElementArr) {
                this.elements.add((WidgetElement) ImmutableGroupDetailTemplate.requireNonNull(widgetElement, "elements element"));
            }
            this.optBits |= 8;
            return this;
        }

        @JsonProperty(ContextMappingConstants.ELEMENTS)
        public final Builder elements(Iterable<? extends WidgetElement> iterable) {
            this.elements.clear();
            return addAllElements(iterable);
        }

        public final Builder addAllElements(Iterable<? extends WidgetElement> iterable) {
            Iterator<? extends WidgetElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.elements.add((WidgetElement) ImmutableGroupDetailTemplate.requireNonNull(it.next(), "elements element"));
            }
            this.optBits |= 8;
            return this;
        }

        public final Builder addOnEndOfList(Method method) {
            this.onEndOfList.add((Method) ImmutableGroupDetailTemplate.requireNonNull(method, "onEndOfList element"));
            this.optBits |= 16;
            return this;
        }

        public final Builder addOnEndOfList(Method... methodArr) {
            for (Method method : methodArr) {
                this.onEndOfList.add((Method) ImmutableGroupDetailTemplate.requireNonNull(method, "onEndOfList element"));
            }
            this.optBits |= 16;
            return this;
        }

        @JsonProperty("onEndOfList")
        public final Builder onEndOfList(Iterable<? extends Method> iterable) {
            this.onEndOfList.clear();
            return addAllOnEndOfList(iterable);
        }

        public final Builder addAllOnEndOfList(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onEndOfList.add((Method) ImmutableGroupDetailTemplate.requireNonNull(it.next(), "onEndOfList element"));
            }
            this.optBits |= 16;
            return this;
        }

        public ImmutableGroupDetailTemplate build() {
            return new ImmutableGroupDetailTemplate(this);
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
        public boolean elementsIsSet() {
            return (this.optBits & 8) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onEndOfListIsSet() {
            return (this.optBits & 16) != 0;
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
