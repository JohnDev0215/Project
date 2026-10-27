package Touch.SwipeablePagesTemplateInterface.v1_0;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
import CoreInterface.v1_0.TemplateLaunchMode;
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
@Generated(from = "SwipeablePagesTemplate", generator = "Immutables")
public final class ImmutableSwipeablePagesTemplate extends SwipeablePagesTemplate {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final Long cacheSeconds;
    private volatile transient InitShim initShim;
    private final TemplateLaunchMode launchMode;
    private final List<Method> onBound;
    private final List<Method> onCreated;
    private final List<Method> onViewed;
    private final List<SwipeablePageElement> pages;

    @Nullable
    private final SwipeablePageLabelElement terminatingLabel;

    private ImmutableSwipeablePagesTemplate(Builder builder) {
        this.initShim = new InitShim();
        this.terminatingLabel = builder.terminatingLabel;
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
        if (builder.pagesIsSet()) {
            this.initShim.pages(createUnmodifiableList(true, builder.pages));
        }
        this.onCreated = this.initShim.onCreated();
        this.onBound = this.initShim.onBound();
        this.onViewed = this.initShim.onViewed();
        this.launchMode = this.initShim.launchMode();
        this.cacheSeconds = this.initShim.cacheSeconds();
        this.pages = this.initShim.pages();
        this.initShim = null;
    }

    private ImmutableSwipeablePagesTemplate(List<Method> list, List<Method> list2, List<Method> list3, TemplateLaunchMode templateLaunchMode, Long l, List<SwipeablePageElement> list4, @Nullable SwipeablePageLabelElement swipeablePageLabelElement) {
        this.initShim = new InitShim();
        this.onCreated = list;
        this.onBound = list2;
        this.onViewed = list3;
        this.launchMode = templateLaunchMode;
        this.cacheSeconds = l;
        this.pages = list4;
        this.terminatingLabel = swipeablePageLabelElement;
        this.initShim = null;
    }

    @Generated(from = "SwipeablePagesTemplate", generator = "Immutables")
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
        private List<SwipeablePageElement> pages;
        private byte pagesBuildStage;

        private InitShim() {
            this.onCreatedBuildStage = (byte) 0;
            this.onBoundBuildStage = (byte) 0;
            this.onViewedBuildStage = (byte) 0;
            this.launchModeBuildStage = (byte) 0;
            this.cacheSecondsBuildStage = (byte) 0;
            this.pagesBuildStage = (byte) 0;
        }

        List<Method> onCreated() {
            byte b = this.onCreatedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onCreatedBuildStage = (byte) -1;
                this.onCreated = ImmutableSwipeablePagesTemplate.createUnmodifiableList(false, ImmutableSwipeablePagesTemplate.createSafeList(ImmutableSwipeablePagesTemplate.super.onCreated(), true, false));
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
                this.onBound = ImmutableSwipeablePagesTemplate.createUnmodifiableList(false, ImmutableSwipeablePagesTemplate.createSafeList(ImmutableSwipeablePagesTemplate.super.onBound(), true, false));
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
                this.onViewed = ImmutableSwipeablePagesTemplate.createUnmodifiableList(false, ImmutableSwipeablePagesTemplate.createSafeList(ImmutableSwipeablePagesTemplate.super.onViewed(), true, false));
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
                this.launchMode = (TemplateLaunchMode) ImmutableSwipeablePagesTemplate.requireNonNull(ImmutableSwipeablePagesTemplate.super.launchMode(), "launchMode");
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
                this.cacheSeconds = (Long) ImmutableSwipeablePagesTemplate.requireNonNull(ImmutableSwipeablePagesTemplate.super.cacheSeconds(), "cacheSeconds");
                this.cacheSecondsBuildStage = (byte) 1;
            }
            return this.cacheSeconds;
        }

        void cacheSeconds(Long l) {
            this.cacheSeconds = l;
            this.cacheSecondsBuildStage = (byte) 1;
        }

        List<SwipeablePageElement> pages() {
            byte b = this.pagesBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.pagesBuildStage = (byte) -1;
                this.pages = ImmutableSwipeablePagesTemplate.createUnmodifiableList(false, ImmutableSwipeablePagesTemplate.createSafeList(ImmutableSwipeablePagesTemplate.super.pages(), true, false));
                this.pagesBuildStage = (byte) 1;
            }
            return this.pages;
        }

        void pages(List<SwipeablePageElement> list) {
            this.pages = list;
            this.pagesBuildStage = (byte) 1;
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
            if (this.pagesBuildStage == -1) {
                arrayList.add("pages");
            }
            return "Cannot build SwipeablePagesTemplate, attribute initializers form cycle " + arrayList;
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

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePagesTemplate
    @JsonProperty("pages")
    public List<SwipeablePageElement> pages() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.pages();
        }
        return this.pages;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePagesTemplate
    @JsonProperty("terminatingLabel")
    @Nullable
    public SwipeablePageLabelElement terminatingLabel() {
        return this.terminatingLabel;
    }

    public final ImmutableSwipeablePagesTemplate withOnCreated(Method... methodArr) {
        return new ImmutableSwipeablePagesTemplate(createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.pages, this.terminatingLabel);
    }

    public final ImmutableSwipeablePagesTemplate withOnCreated(Iterable<? extends Method> iterable) {
        return this.onCreated == iterable ? this : new ImmutableSwipeablePagesTemplate(createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.pages, this.terminatingLabel);
    }

    public final ImmutableSwipeablePagesTemplate withOnBound(Method... methodArr) {
        return new ImmutableSwipeablePagesTemplate(this.onCreated, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onViewed, this.launchMode, this.cacheSeconds, this.pages, this.terminatingLabel);
    }

    public final ImmutableSwipeablePagesTemplate withOnBound(Iterable<? extends Method> iterable) {
        if (this.onBound == iterable) {
            return this;
        }
        return new ImmutableSwipeablePagesTemplate(this.onCreated, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onViewed, this.launchMode, this.cacheSeconds, this.pages, this.terminatingLabel);
    }

    public final ImmutableSwipeablePagesTemplate withOnViewed(Method... methodArr) {
        return new ImmutableSwipeablePagesTemplate(this.onCreated, this.onBound, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.launchMode, this.cacheSeconds, this.pages, this.terminatingLabel);
    }

    public final ImmutableSwipeablePagesTemplate withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableSwipeablePagesTemplate(this.onCreated, this.onBound, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.launchMode, this.cacheSeconds, this.pages, this.terminatingLabel);
    }

    public final ImmutableSwipeablePagesTemplate withLaunchMode(TemplateLaunchMode templateLaunchMode) {
        TemplateLaunchMode templateLaunchMode2 = (TemplateLaunchMode) requireNonNull(templateLaunchMode, "launchMode");
        return this.launchMode == templateLaunchMode2 ? this : new ImmutableSwipeablePagesTemplate(this.onCreated, this.onBound, this.onViewed, templateLaunchMode2, this.cacheSeconds, this.pages, this.terminatingLabel);
    }

    public final ImmutableSwipeablePagesTemplate withCacheSeconds(Long l) {
        Long l2 = (Long) requireNonNull(l, "cacheSeconds");
        return this.cacheSeconds.equals(l2) ? this : new ImmutableSwipeablePagesTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, l2, this.pages, this.terminatingLabel);
    }

    public final ImmutableSwipeablePagesTemplate withPages(SwipeablePageElement... swipeablePageElementArr) {
        return new ImmutableSwipeablePagesTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, createUnmodifiableList(false, createSafeList(Arrays.asList(swipeablePageElementArr), true, false)), this.terminatingLabel);
    }

    public final ImmutableSwipeablePagesTemplate withPages(Iterable<? extends SwipeablePageElement> iterable) {
        if (this.pages == iterable) {
            return this;
        }
        return new ImmutableSwipeablePagesTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.terminatingLabel);
    }

    public final ImmutableSwipeablePagesTemplate withTerminatingLabel(@Nullable SwipeablePageLabelElement swipeablePageLabelElement) {
        return this.terminatingLabel == swipeablePageLabelElement ? this : new ImmutableSwipeablePagesTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.pages, swipeablePageLabelElement);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSwipeablePagesTemplate) && equalTo(0, (ImmutableSwipeablePagesTemplate) obj);
    }

    private boolean equalTo(int i, ImmutableSwipeablePagesTemplate immutableSwipeablePagesTemplate) {
        return this.onCreated.equals(immutableSwipeablePagesTemplate.onCreated) && this.onBound.equals(immutableSwipeablePagesTemplate.onBound) && this.onViewed.equals(immutableSwipeablePagesTemplate.onViewed) && this.launchMode.equals(immutableSwipeablePagesTemplate.launchMode) && this.cacheSeconds.equals(immutableSwipeablePagesTemplate.cacheSeconds) && this.pages.equals(immutableSwipeablePagesTemplate.pages) && equals(this.terminatingLabel, immutableSwipeablePagesTemplate.terminatingLabel);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.onCreated.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onBound.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.onViewed.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.launchMode.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.cacheSeconds.hashCode();
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + this.pages.hashCode();
        return iHashCode6 + (iHashCode6 << 5) + hashCode(this.terminatingLabel);
    }

    public String toString() {
        return "SwipeablePagesTemplate{onCreated=" + this.onCreated + ", onBound=" + this.onBound + ", onViewed=" + this.onViewed + ", launchMode=" + this.launchMode + ", cacheSeconds=" + this.cacheSeconds + ", pages=" + this.pages + ", terminatingLabel=" + this.terminatingLabel + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SwipeablePagesTemplate", generator = "Immutables")
    static final class Json extends SwipeablePagesTemplate {

        @Nullable
        Long cacheSeconds;

        @Nullable
        TemplateLaunchMode launchMode;
        boolean onBoundIsSet;
        boolean onCreatedIsSet;
        boolean onViewedIsSet;
        boolean pagesIsSet;

        @Nullable
        SwipeablePageLabelElement terminatingLabel;

        @Nullable
        List<Method> onCreated = Collections.emptyList();

        @Nullable
        List<Method> onBound = Collections.emptyList();

        @Nullable
        List<Method> onViewed = Collections.emptyList();

        @Nullable
        List<SwipeablePageElement> pages = Collections.emptyList();

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

        @JsonProperty("pages")
        public void setPages(List<SwipeablePageElement> list) {
            this.pages = list;
            this.pagesIsSet = true;
        }

        @JsonProperty("terminatingLabel")
        public void setTerminatingLabel(@Nullable SwipeablePageLabelElement swipeablePageLabelElement) {
            this.terminatingLabel = swipeablePageLabelElement;
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

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePagesTemplate
        public List<SwipeablePageElement> pages() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePagesTemplate
        public SwipeablePageLabelElement terminatingLabel() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSwipeablePagesTemplate fromJson(Json json) {
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
        if (json.pagesIsSet) {
            builder.addAllPages(json.pages);
        }
        SwipeablePageLabelElement swipeablePageLabelElement = json.terminatingLabel;
        if (swipeablePageLabelElement != null) {
            builder.terminatingLabel(swipeablePageLabelElement);
        }
        return builder.build();
    }

    public static ImmutableSwipeablePagesTemplate copyOf(SwipeablePagesTemplate swipeablePagesTemplate) {
        if (swipeablePagesTemplate instanceof ImmutableSwipeablePagesTemplate) {
            return (ImmutableSwipeablePagesTemplate) swipeablePagesTemplate;
        }
        return builder().from(swipeablePagesTemplate).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SwipeablePagesTemplate", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_ON_BOUND = 2;
        private static final long OPT_BIT_ON_CREATED = 1;
        private static final long OPT_BIT_ON_VIEWED = 4;
        private static final long OPT_BIT_PAGES = 8;

        @Nullable
        private Long cacheSeconds;

        @Nullable
        private TemplateLaunchMode launchMode;
        private List<Method> onBound;
        private List<Method> onCreated;
        private List<Method> onViewed;
        private long optBits;
        private List<SwipeablePageElement> pages;

        @Nullable
        private SwipeablePageLabelElement terminatingLabel;

        private Builder() {
            this.onCreated = new ArrayList();
            this.onBound = new ArrayList();
            this.onViewed = new ArrayList();
            this.pages = new ArrayList();
        }

        public final Builder from(Template template) {
            ImmutableSwipeablePagesTemplate.requireNonNull(template, "instance");
            from((Object) template);
            return this;
        }

        public final Builder from(SwipeablePagesTemplate swipeablePagesTemplate) {
            ImmutableSwipeablePagesTemplate.requireNonNull(swipeablePagesTemplate, "instance");
            from((Object) swipeablePagesTemplate);
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
            if (obj instanceof SwipeablePagesTemplate) {
                SwipeablePagesTemplate swipeablePagesTemplate = (SwipeablePagesTemplate) obj;
                if ((j & 1) == 0) {
                    addAllOnBound(swipeablePagesTemplate.onBound());
                    j |= 1;
                }
                SwipeablePageLabelElement swipeablePageLabelElementTerminatingLabel = swipeablePagesTemplate.terminatingLabel();
                if (swipeablePageLabelElementTerminatingLabel != null) {
                    terminatingLabel(swipeablePageLabelElementTerminatingLabel);
                }
                addAllPages(swipeablePagesTemplate.pages());
                if ((j & 2) == 0) {
                    cacheSeconds(swipeablePagesTemplate.cacheSeconds());
                    j |= 2;
                }
                if ((j & 4) == 0) {
                    launchMode(swipeablePagesTemplate.launchMode());
                    j |= 4;
                }
                if ((j & 8) == 0) {
                    addAllOnViewed(swipeablePagesTemplate.onViewed());
                    j |= 8;
                }
                if ((j & 16) == 0) {
                    addAllOnCreated(swipeablePagesTemplate.onCreated());
                }
            }
        }

        public final Builder addOnCreated(Method method) {
            this.onCreated.add((Method) ImmutableSwipeablePagesTemplate.requireNonNull(method, "onCreated element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnCreated(Method... methodArr) {
            for (Method method : methodArr) {
                this.onCreated.add((Method) ImmutableSwipeablePagesTemplate.requireNonNull(method, "onCreated element"));
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
                this.onCreated.add((Method) ImmutableSwipeablePagesTemplate.requireNonNull(it.next(), "onCreated element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnBound(Method method) {
            this.onBound.add((Method) ImmutableSwipeablePagesTemplate.requireNonNull(method, "onBound element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnBound(Method... methodArr) {
            for (Method method : methodArr) {
                this.onBound.add((Method) ImmutableSwipeablePagesTemplate.requireNonNull(method, "onBound element"));
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
                this.onBound.add((Method) ImmutableSwipeablePagesTemplate.requireNonNull(it.next(), "onBound element"));
            }
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableSwipeablePagesTemplate.requireNonNull(method, "onViewed element"));
            this.optBits |= 4;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableSwipeablePagesTemplate.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableSwipeablePagesTemplate.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 4;
            return this;
        }

        @JsonProperty("launchMode")
        public final Builder launchMode(TemplateLaunchMode templateLaunchMode) {
            this.launchMode = (TemplateLaunchMode) ImmutableSwipeablePagesTemplate.requireNonNull(templateLaunchMode, "launchMode");
            return this;
        }

        @JsonProperty("cacheSeconds")
        public final Builder cacheSeconds(Long l) {
            this.cacheSeconds = (Long) ImmutableSwipeablePagesTemplate.requireNonNull(l, "cacheSeconds");
            return this;
        }

        public final Builder addPages(SwipeablePageElement swipeablePageElement) {
            this.pages.add((SwipeablePageElement) ImmutableSwipeablePagesTemplate.requireNonNull(swipeablePageElement, "pages element"));
            this.optBits |= 8;
            return this;
        }

        public final Builder addPages(SwipeablePageElement... swipeablePageElementArr) {
            for (SwipeablePageElement swipeablePageElement : swipeablePageElementArr) {
                this.pages.add((SwipeablePageElement) ImmutableSwipeablePagesTemplate.requireNonNull(swipeablePageElement, "pages element"));
            }
            this.optBits |= 8;
            return this;
        }

        @JsonProperty("pages")
        public final Builder pages(Iterable<? extends SwipeablePageElement> iterable) {
            this.pages.clear();
            return addAllPages(iterable);
        }

        public final Builder addAllPages(Iterable<? extends SwipeablePageElement> iterable) {
            Iterator<? extends SwipeablePageElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.pages.add((SwipeablePageElement) ImmutableSwipeablePagesTemplate.requireNonNull(it.next(), "pages element"));
            }
            this.optBits |= 8;
            return this;
        }

        @JsonProperty("terminatingLabel")
        public final Builder terminatingLabel(@Nullable SwipeablePageLabelElement swipeablePageLabelElement) {
            this.terminatingLabel = swipeablePageLabelElement;
            return this;
        }

        public ImmutableSwipeablePagesTemplate build() {
            return new ImmutableSwipeablePagesTemplate(this);
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
        public boolean pagesIsSet() {
            return (this.optBits & 8) != 0;
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
