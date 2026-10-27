package Watch.ListTemplateInterface.v1_0;

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
@Generated(from = "ListTemplate", generator = "Immutables")
public final class ImmutableListTemplate extends ListTemplate {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final Long cacheSeconds;
    private volatile transient InitShim initShim;
    private final TemplateLaunchMode launchMode;
    private final List<Method> onBound;
    private final List<Method> onCreated;
    private final List<Method> onEndOfWidgetsReached;
    private final List<Method> onViewed;
    private final List<ListItemElement> overflowItems;

    @Nullable
    private final String title;
    private final List<WidgetElement> widgets;

    private ImmutableListTemplate(Builder builder) {
        this.initShim = new InitShim();
        this.title = builder.title;
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
        if (builder.overflowItemsIsSet()) {
            this.initShim.overflowItems(createUnmodifiableList(true, builder.overflowItems));
        }
        if (builder.widgetsIsSet()) {
            this.initShim.widgets(createUnmodifiableList(true, builder.widgets));
        }
        if (builder.onEndOfWidgetsReachedIsSet()) {
            this.initShim.onEndOfWidgetsReached(createUnmodifiableList(true, builder.onEndOfWidgetsReached));
        }
        this.onCreated = this.initShim.onCreated();
        this.onBound = this.initShim.onBound();
        this.onViewed = this.initShim.onViewed();
        this.launchMode = this.initShim.launchMode();
        this.cacheSeconds = this.initShim.cacheSeconds();
        this.overflowItems = this.initShim.overflowItems();
        this.widgets = this.initShim.widgets();
        this.onEndOfWidgetsReached = this.initShim.onEndOfWidgetsReached();
        this.initShim = null;
    }

    private ImmutableListTemplate(List<Method> list, List<Method> list2, List<Method> list3, TemplateLaunchMode templateLaunchMode, Long l, @Nullable String str, List<ListItemElement> list4, List<WidgetElement> list5, List<Method> list6) {
        this.initShim = new InitShim();
        this.onCreated = list;
        this.onBound = list2;
        this.onViewed = list3;
        this.launchMode = templateLaunchMode;
        this.cacheSeconds = l;
        this.title = str;
        this.overflowItems = list4;
        this.widgets = list5;
        this.onEndOfWidgetsReached = list6;
        this.initShim = null;
    }

    @Generated(from = "ListTemplate", generator = "Immutables")
    private final class InitShim {
        private Long cacheSeconds;
        private byte cacheSecondsBuildStage;
        private TemplateLaunchMode launchMode;
        private byte launchModeBuildStage;
        private List<Method> onBound;
        private byte onBoundBuildStage;
        private List<Method> onCreated;
        private byte onCreatedBuildStage;
        private List<Method> onEndOfWidgetsReached;
        private byte onEndOfWidgetsReachedBuildStage;
        private List<Method> onViewed;
        private byte onViewedBuildStage;
        private List<ListItemElement> overflowItems;
        private byte overflowItemsBuildStage;
        private List<WidgetElement> widgets;
        private byte widgetsBuildStage;

        private InitShim() {
            this.onCreatedBuildStage = (byte) 0;
            this.onBoundBuildStage = (byte) 0;
            this.onViewedBuildStage = (byte) 0;
            this.launchModeBuildStage = (byte) 0;
            this.cacheSecondsBuildStage = (byte) 0;
            this.overflowItemsBuildStage = (byte) 0;
            this.widgetsBuildStage = (byte) 0;
            this.onEndOfWidgetsReachedBuildStage = (byte) 0;
        }

        List<Method> onCreated() {
            byte b = this.onCreatedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onCreatedBuildStage = (byte) -1;
                this.onCreated = ImmutableListTemplate.createUnmodifiableList(false, ImmutableListTemplate.createSafeList(ImmutableListTemplate.super.onCreated(), true, false));
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
                this.onBound = ImmutableListTemplate.createUnmodifiableList(false, ImmutableListTemplate.createSafeList(ImmutableListTemplate.super.onBound(), true, false));
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
                this.onViewed = ImmutableListTemplate.createUnmodifiableList(false, ImmutableListTemplate.createSafeList(ImmutableListTemplate.super.onViewed(), true, false));
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
                this.launchMode = (TemplateLaunchMode) ImmutableListTemplate.requireNonNull(ImmutableListTemplate.super.launchMode(), "launchMode");
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
                this.cacheSeconds = (Long) ImmutableListTemplate.requireNonNull(ImmutableListTemplate.super.cacheSeconds(), "cacheSeconds");
                this.cacheSecondsBuildStage = (byte) 1;
            }
            return this.cacheSeconds;
        }

        void cacheSeconds(Long l) {
            this.cacheSeconds = l;
            this.cacheSecondsBuildStage = (byte) 1;
        }

        List<ListItemElement> overflowItems() {
            byte b = this.overflowItemsBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.overflowItemsBuildStage = (byte) -1;
                this.overflowItems = ImmutableListTemplate.createUnmodifiableList(false, ImmutableListTemplate.createSafeList(ImmutableListTemplate.super.overflowItems(), true, false));
                this.overflowItemsBuildStage = (byte) 1;
            }
            return this.overflowItems;
        }

        void overflowItems(List<ListItemElement> list) {
            this.overflowItems = list;
            this.overflowItemsBuildStage = (byte) 1;
        }

        List<WidgetElement> widgets() {
            byte b = this.widgetsBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.widgetsBuildStage = (byte) -1;
                this.widgets = ImmutableListTemplate.createUnmodifiableList(false, ImmutableListTemplate.createSafeList(ImmutableListTemplate.super.widgets(), true, false));
                this.widgetsBuildStage = (byte) 1;
            }
            return this.widgets;
        }

        void widgets(List<WidgetElement> list) {
            this.widgets = list;
            this.widgetsBuildStage = (byte) 1;
        }

        List<Method> onEndOfWidgetsReached() {
            byte b = this.onEndOfWidgetsReachedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onEndOfWidgetsReachedBuildStage = (byte) -1;
                this.onEndOfWidgetsReached = ImmutableListTemplate.createUnmodifiableList(false, ImmutableListTemplate.createSafeList(ImmutableListTemplate.super.onEndOfWidgetsReached(), true, false));
                this.onEndOfWidgetsReachedBuildStage = (byte) 1;
            }
            return this.onEndOfWidgetsReached;
        }

        void onEndOfWidgetsReached(List<Method> list) {
            this.onEndOfWidgetsReached = list;
            this.onEndOfWidgetsReachedBuildStage = (byte) 1;
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
            if (this.overflowItemsBuildStage == -1) {
                arrayList.add("overflowItems");
            }
            if (this.widgetsBuildStage == -1) {
                arrayList.add("widgets");
            }
            if (this.onEndOfWidgetsReachedBuildStage == -1) {
                arrayList.add("onEndOfWidgetsReached");
            }
            return "Cannot build ListTemplate, attribute initializers form cycle " + arrayList;
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

    @Override // Watch.ListTemplateInterface.v1_0.ListTemplate
    @JsonProperty("title")
    @Nullable
    public String title() {
        return this.title;
    }

    @Override // Watch.ListTemplateInterface.v1_0.ListTemplate
    @JsonProperty("overflowItems")
    public List<ListItemElement> overflowItems() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.overflowItems();
        }
        return this.overflowItems;
    }

    @Override // Watch.ListTemplateInterface.v1_0.ListTemplate
    @JsonProperty("widgets")
    public List<WidgetElement> widgets() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.widgets();
        }
        return this.widgets;
    }

    @Override // Watch.ListTemplateInterface.v1_0.ListTemplate
    @JsonProperty("onEndOfWidgetsReached")
    public List<Method> onEndOfWidgetsReached() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onEndOfWidgetsReached();
        }
        return this.onEndOfWidgetsReached;
    }

    public final ImmutableListTemplate withOnCreated(Method... methodArr) {
        return new ImmutableListTemplate(createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.overflowItems, this.widgets, this.onEndOfWidgetsReached);
    }

    public final ImmutableListTemplate withOnCreated(Iterable<? extends Method> iterable) {
        return this.onCreated == iterable ? this : new ImmutableListTemplate(createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.overflowItems, this.widgets, this.onEndOfWidgetsReached);
    }

    public final ImmutableListTemplate withOnBound(Method... methodArr) {
        return new ImmutableListTemplate(this.onCreated, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.overflowItems, this.widgets, this.onEndOfWidgetsReached);
    }

    public final ImmutableListTemplate withOnBound(Iterable<? extends Method> iterable) {
        if (this.onBound == iterable) {
            return this;
        }
        return new ImmutableListTemplate(this.onCreated, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.overflowItems, this.widgets, this.onEndOfWidgetsReached);
    }

    public final ImmutableListTemplate withOnViewed(Method... methodArr) {
        return new ImmutableListTemplate(this.onCreated, this.onBound, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.launchMode, this.cacheSeconds, this.title, this.overflowItems, this.widgets, this.onEndOfWidgetsReached);
    }

    public final ImmutableListTemplate withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableListTemplate(this.onCreated, this.onBound, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.launchMode, this.cacheSeconds, this.title, this.overflowItems, this.widgets, this.onEndOfWidgetsReached);
    }

    public final ImmutableListTemplate withLaunchMode(TemplateLaunchMode templateLaunchMode) {
        TemplateLaunchMode templateLaunchMode2 = (TemplateLaunchMode) requireNonNull(templateLaunchMode, "launchMode");
        return this.launchMode == templateLaunchMode2 ? this : new ImmutableListTemplate(this.onCreated, this.onBound, this.onViewed, templateLaunchMode2, this.cacheSeconds, this.title, this.overflowItems, this.widgets, this.onEndOfWidgetsReached);
    }

    public final ImmutableListTemplate withCacheSeconds(Long l) {
        Long l2 = (Long) requireNonNull(l, "cacheSeconds");
        return this.cacheSeconds.equals(l2) ? this : new ImmutableListTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, l2, this.title, this.overflowItems, this.widgets, this.onEndOfWidgetsReached);
    }

    public final ImmutableListTemplate withTitle(@Nullable String str) {
        return equals(this.title, str) ? this : new ImmutableListTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, str, this.overflowItems, this.widgets, this.onEndOfWidgetsReached);
    }

    public final ImmutableListTemplate withOverflowItems(ListItemElement... listItemElementArr) {
        return new ImmutableListTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.title, createUnmodifiableList(false, createSafeList(Arrays.asList(listItemElementArr), true, false)), this.widgets, this.onEndOfWidgetsReached);
    }

    public final ImmutableListTemplate withOverflowItems(Iterable<? extends ListItemElement> iterable) {
        if (this.overflowItems == iterable) {
            return this;
        }
        return new ImmutableListTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.title, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.widgets, this.onEndOfWidgetsReached);
    }

    public final ImmutableListTemplate withWidgets(WidgetElement... widgetElementArr) {
        return new ImmutableListTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.overflowItems, createUnmodifiableList(false, createSafeList(Arrays.asList(widgetElementArr), true, false)), this.onEndOfWidgetsReached);
    }

    public final ImmutableListTemplate withWidgets(Iterable<? extends WidgetElement> iterable) {
        if (this.widgets == iterable) {
            return this;
        }
        return new ImmutableListTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.overflowItems, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onEndOfWidgetsReached);
    }

    public final ImmutableListTemplate withOnEndOfWidgetsReached(Method... methodArr) {
        return new ImmutableListTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.overflowItems, this.widgets, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableListTemplate withOnEndOfWidgetsReached(Iterable<? extends Method> iterable) {
        if (this.onEndOfWidgetsReached == iterable) {
            return this;
        }
        return new ImmutableListTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.overflowItems, this.widgets, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableListTemplate) && equalTo(0, (ImmutableListTemplate) obj);
    }

    private boolean equalTo(int i, ImmutableListTemplate immutableListTemplate) {
        return this.onCreated.equals(immutableListTemplate.onCreated) && this.onBound.equals(immutableListTemplate.onBound) && this.onViewed.equals(immutableListTemplate.onViewed) && this.launchMode.equals(immutableListTemplate.launchMode) && this.cacheSeconds.equals(immutableListTemplate.cacheSeconds) && equals(this.title, immutableListTemplate.title) && this.overflowItems.equals(immutableListTemplate.overflowItems) && this.widgets.equals(immutableListTemplate.widgets) && this.onEndOfWidgetsReached.equals(immutableListTemplate.onEndOfWidgetsReached);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.onCreated.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onBound.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.onViewed.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.launchMode.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.cacheSeconds.hashCode();
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + hashCode(this.title);
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + this.overflowItems.hashCode();
        int iHashCode8 = iHashCode7 + (iHashCode7 << 5) + this.widgets.hashCode();
        return iHashCode8 + (iHashCode8 << 5) + this.onEndOfWidgetsReached.hashCode();
    }

    public String toString() {
        return "ListTemplate{onCreated=" + this.onCreated + ", onBound=" + this.onBound + ", onViewed=" + this.onViewed + ", launchMode=" + this.launchMode + ", cacheSeconds=" + this.cacheSeconds + ", title=" + this.title + ", overflowItems=" + this.overflowItems + ", widgets=" + this.widgets + ", onEndOfWidgetsReached=" + this.onEndOfWidgetsReached + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "ListTemplate", generator = "Immutables")
    static final class Json extends ListTemplate {

        @Nullable
        Long cacheSeconds;

        @Nullable
        TemplateLaunchMode launchMode;
        boolean onBoundIsSet;
        boolean onCreatedIsSet;
        boolean onEndOfWidgetsReachedIsSet;
        boolean onViewedIsSet;
        boolean overflowItemsIsSet;

        @Nullable
        String title;
        boolean widgetsIsSet;

        @Nullable
        List<Method> onCreated = Collections.emptyList();

        @Nullable
        List<Method> onBound = Collections.emptyList();

        @Nullable
        List<Method> onViewed = Collections.emptyList();

        @Nullable
        List<ListItemElement> overflowItems = Collections.emptyList();

        @Nullable
        List<WidgetElement> widgets = Collections.emptyList();

        @Nullable
        List<Method> onEndOfWidgetsReached = Collections.emptyList();

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

        @JsonProperty("title")
        public void setTitle(@Nullable String str) {
            this.title = str;
        }

        @JsonProperty("overflowItems")
        public void setOverflowItems(List<ListItemElement> list) {
            this.overflowItems = list;
            this.overflowItemsIsSet = true;
        }

        @JsonProperty("widgets")
        public void setWidgets(List<WidgetElement> list) {
            this.widgets = list;
            this.widgetsIsSet = true;
        }

        @JsonProperty("onEndOfWidgetsReached")
        public void setOnEndOfWidgetsReached(List<Method> list) {
            this.onEndOfWidgetsReached = list;
            this.onEndOfWidgetsReachedIsSet = true;
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

        @Override // Watch.ListTemplateInterface.v1_0.ListTemplate
        public String title() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.ListTemplateInterface.v1_0.ListTemplate
        public List<ListItemElement> overflowItems() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.ListTemplateInterface.v1_0.ListTemplate
        public List<WidgetElement> widgets() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.ListTemplateInterface.v1_0.ListTemplate
        public List<Method> onEndOfWidgetsReached() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableListTemplate fromJson(Json json) {
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
        String str = json.title;
        if (str != null) {
            builder.title(str);
        }
        if (json.overflowItemsIsSet) {
            builder.addAllOverflowItems(json.overflowItems);
        }
        if (json.widgetsIsSet) {
            builder.addAllWidgets(json.widgets);
        }
        if (json.onEndOfWidgetsReachedIsSet) {
            builder.addAllOnEndOfWidgetsReached(json.onEndOfWidgetsReached);
        }
        return builder.build();
    }

    public static ImmutableListTemplate copyOf(ListTemplate listTemplate) {
        if (listTemplate instanceof ImmutableListTemplate) {
            return (ImmutableListTemplate) listTemplate;
        }
        return builder().from(listTemplate).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "ListTemplate", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_ON_BOUND = 2;
        private static final long OPT_BIT_ON_CREATED = 1;
        private static final long OPT_BIT_ON_END_OF_WIDGETS_REACHED = 32;
        private static final long OPT_BIT_ON_VIEWED = 4;
        private static final long OPT_BIT_OVERFLOW_ITEMS = 8;
        private static final long OPT_BIT_WIDGETS = 16;

        @Nullable
        private Long cacheSeconds;

        @Nullable
        private TemplateLaunchMode launchMode;
        private List<Method> onBound;
        private List<Method> onCreated;
        private List<Method> onEndOfWidgetsReached;
        private List<Method> onViewed;
        private long optBits;
        private List<ListItemElement> overflowItems;

        @Nullable
        private String title;
        private List<WidgetElement> widgets;

        private Builder() {
            this.onCreated = new ArrayList();
            this.onBound = new ArrayList();
            this.onViewed = new ArrayList();
            this.overflowItems = new ArrayList();
            this.widgets = new ArrayList();
            this.onEndOfWidgetsReached = new ArrayList();
        }

        public final Builder from(Template template) {
            ImmutableListTemplate.requireNonNull(template, "instance");
            from((Object) template);
            return this;
        }

        public final Builder from(ListTemplate listTemplate) {
            ImmutableListTemplate.requireNonNull(listTemplate, "instance");
            from((Object) listTemplate);
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
            if (obj instanceof ListTemplate) {
                ListTemplate listTemplate = (ListTemplate) obj;
                if ((j & 1) == 0) {
                    addAllOnBound(listTemplate.onBound());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    cacheSeconds(listTemplate.cacheSeconds());
                    j |= 2;
                }
                if ((j & 4) == 0) {
                    launchMode(listTemplate.launchMode());
                    j |= 4;
                }
                if ((j & 8) == 0) {
                    addAllOnViewed(listTemplate.onViewed());
                    j |= 8;
                }
                String strTitle = listTemplate.title();
                if (strTitle != null) {
                    title(strTitle);
                }
                addAllWidgets(listTemplate.widgets());
                if ((j & 16) == 0) {
                    addAllOnCreated(listTemplate.onCreated());
                }
                addAllOverflowItems(listTemplate.overflowItems());
                addAllOnEndOfWidgetsReached(listTemplate.onEndOfWidgetsReached());
            }
        }

        public final Builder addOnCreated(Method method) {
            this.onCreated.add((Method) ImmutableListTemplate.requireNonNull(method, "onCreated element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnCreated(Method... methodArr) {
            for (Method method : methodArr) {
                this.onCreated.add((Method) ImmutableListTemplate.requireNonNull(method, "onCreated element"));
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
                this.onCreated.add((Method) ImmutableListTemplate.requireNonNull(it.next(), "onCreated element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnBound(Method method) {
            this.onBound.add((Method) ImmutableListTemplate.requireNonNull(method, "onBound element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnBound(Method... methodArr) {
            for (Method method : methodArr) {
                this.onBound.add((Method) ImmutableListTemplate.requireNonNull(method, "onBound element"));
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
                this.onBound.add((Method) ImmutableListTemplate.requireNonNull(it.next(), "onBound element"));
            }
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableListTemplate.requireNonNull(method, "onViewed element"));
            this.optBits |= 4;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableListTemplate.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableListTemplate.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 4;
            return this;
        }

        @JsonProperty("launchMode")
        public final Builder launchMode(TemplateLaunchMode templateLaunchMode) {
            this.launchMode = (TemplateLaunchMode) ImmutableListTemplate.requireNonNull(templateLaunchMode, "launchMode");
            return this;
        }

        @JsonProperty("cacheSeconds")
        public final Builder cacheSeconds(Long l) {
            this.cacheSeconds = (Long) ImmutableListTemplate.requireNonNull(l, "cacheSeconds");
            return this;
        }

        @JsonProperty("title")
        public final Builder title(@Nullable String str) {
            this.title = str;
            return this;
        }

        public final Builder addOverflowItems(ListItemElement listItemElement) {
            this.overflowItems.add((ListItemElement) ImmutableListTemplate.requireNonNull(listItemElement, "overflowItems element"));
            this.optBits |= 8;
            return this;
        }

        public final Builder addOverflowItems(ListItemElement... listItemElementArr) {
            for (ListItemElement listItemElement : listItemElementArr) {
                this.overflowItems.add((ListItemElement) ImmutableListTemplate.requireNonNull(listItemElement, "overflowItems element"));
            }
            this.optBits |= 8;
            return this;
        }

        @JsonProperty("overflowItems")
        public final Builder overflowItems(Iterable<? extends ListItemElement> iterable) {
            this.overflowItems.clear();
            return addAllOverflowItems(iterable);
        }

        public final Builder addAllOverflowItems(Iterable<? extends ListItemElement> iterable) {
            Iterator<? extends ListItemElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.overflowItems.add((ListItemElement) ImmutableListTemplate.requireNonNull(it.next(), "overflowItems element"));
            }
            this.optBits |= 8;
            return this;
        }

        public final Builder addWidgets(WidgetElement widgetElement) {
            this.widgets.add((WidgetElement) ImmutableListTemplate.requireNonNull(widgetElement, "widgets element"));
            this.optBits |= 16;
            return this;
        }

        public final Builder addWidgets(WidgetElement... widgetElementArr) {
            for (WidgetElement widgetElement : widgetElementArr) {
                this.widgets.add((WidgetElement) ImmutableListTemplate.requireNonNull(widgetElement, "widgets element"));
            }
            this.optBits |= 16;
            return this;
        }

        @JsonProperty("widgets")
        public final Builder widgets(Iterable<? extends WidgetElement> iterable) {
            this.widgets.clear();
            return addAllWidgets(iterable);
        }

        public final Builder addAllWidgets(Iterable<? extends WidgetElement> iterable) {
            Iterator<? extends WidgetElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.widgets.add((WidgetElement) ImmutableListTemplate.requireNonNull(it.next(), "widgets element"));
            }
            this.optBits |= 16;
            return this;
        }

        public final Builder addOnEndOfWidgetsReached(Method method) {
            this.onEndOfWidgetsReached.add((Method) ImmutableListTemplate.requireNonNull(method, "onEndOfWidgetsReached element"));
            this.optBits |= 32;
            return this;
        }

        public final Builder addOnEndOfWidgetsReached(Method... methodArr) {
            for (Method method : methodArr) {
                this.onEndOfWidgetsReached.add((Method) ImmutableListTemplate.requireNonNull(method, "onEndOfWidgetsReached element"));
            }
            this.optBits |= 32;
            return this;
        }

        @JsonProperty("onEndOfWidgetsReached")
        public final Builder onEndOfWidgetsReached(Iterable<? extends Method> iterable) {
            this.onEndOfWidgetsReached.clear();
            return addAllOnEndOfWidgetsReached(iterable);
        }

        public final Builder addAllOnEndOfWidgetsReached(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onEndOfWidgetsReached.add((Method) ImmutableListTemplate.requireNonNull(it.next(), "onEndOfWidgetsReached element"));
            }
            this.optBits |= 32;
            return this;
        }

        public ImmutableListTemplate build() {
            return new ImmutableListTemplate(this);
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
        public boolean overflowItemsIsSet() {
            return (this.optBits & 8) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean widgetsIsSet() {
            return (this.optBits & 16) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onEndOfWidgetsReachedIsSet() {
            return (this.optBits & 32) != 0;
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
