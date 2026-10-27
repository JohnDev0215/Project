package Watch.SearchTemplateInterface.v1_0;

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
@Generated(from = "SearchTemplate", generator = "Immutables")
public final class ImmutableSearchTemplate extends SearchTemplate {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final Long cacheSeconds;

    @Nullable
    private final String header;
    private volatile transient InitShim initShim;
    private final TemplateLaunchMode launchMode;
    private final List<Method> onBound;
    private final List<Method> onCreated;
    private final List<Method> onQueryEntered;
    private final List<Method> onViewed;
    private final List<SearchItemElement> recentItems;
    private final String title;

    private ImmutableSearchTemplate(Builder builder) {
        this.initShim = new InitShim();
        this.title = builder.title;
        this.onQueryEntered = createUnmodifiableList(true, builder.onQueryEntered);
        this.header = builder.header;
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
        if (builder.recentItemsIsSet()) {
            this.initShim.recentItems(createUnmodifiableList(true, builder.recentItems));
        }
        this.onCreated = this.initShim.onCreated();
        this.onBound = this.initShim.onBound();
        this.onViewed = this.initShim.onViewed();
        this.launchMode = this.initShim.launchMode();
        this.cacheSeconds = this.initShim.cacheSeconds();
        this.recentItems = this.initShim.recentItems();
        this.initShim = null;
    }

    private ImmutableSearchTemplate(List<Method> list, List<Method> list2, List<Method> list3, TemplateLaunchMode templateLaunchMode, Long l, String str, List<Method> list4, @Nullable String str2, List<SearchItemElement> list5) {
        this.initShim = new InitShim();
        this.onCreated = list;
        this.onBound = list2;
        this.onViewed = list3;
        this.launchMode = templateLaunchMode;
        this.cacheSeconds = l;
        this.title = str;
        this.onQueryEntered = list4;
        this.header = str2;
        this.recentItems = list5;
        this.initShim = null;
    }

    @Generated(from = "SearchTemplate", generator = "Immutables")
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
        private List<SearchItemElement> recentItems;
        private byte recentItemsBuildStage;

        private InitShim() {
            this.onCreatedBuildStage = (byte) 0;
            this.onBoundBuildStage = (byte) 0;
            this.onViewedBuildStage = (byte) 0;
            this.launchModeBuildStage = (byte) 0;
            this.cacheSecondsBuildStage = (byte) 0;
            this.recentItemsBuildStage = (byte) 0;
        }

        List<Method> onCreated() {
            byte b = this.onCreatedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onCreatedBuildStage = (byte) -1;
                this.onCreated = ImmutableSearchTemplate.createUnmodifiableList(false, ImmutableSearchTemplate.createSafeList(ImmutableSearchTemplate.super.onCreated(), true, false));
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
                this.onBound = ImmutableSearchTemplate.createUnmodifiableList(false, ImmutableSearchTemplate.createSafeList(ImmutableSearchTemplate.super.onBound(), true, false));
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
                this.onViewed = ImmutableSearchTemplate.createUnmodifiableList(false, ImmutableSearchTemplate.createSafeList(ImmutableSearchTemplate.super.onViewed(), true, false));
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
                this.launchMode = (TemplateLaunchMode) ImmutableSearchTemplate.requireNonNull(ImmutableSearchTemplate.super.launchMode(), "launchMode");
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
                this.cacheSeconds = (Long) ImmutableSearchTemplate.requireNonNull(ImmutableSearchTemplate.super.cacheSeconds(), "cacheSeconds");
                this.cacheSecondsBuildStage = (byte) 1;
            }
            return this.cacheSeconds;
        }

        void cacheSeconds(Long l) {
            this.cacheSeconds = l;
            this.cacheSecondsBuildStage = (byte) 1;
        }

        List<SearchItemElement> recentItems() {
            byte b = this.recentItemsBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.recentItemsBuildStage = (byte) -1;
                this.recentItems = ImmutableSearchTemplate.createUnmodifiableList(false, ImmutableSearchTemplate.createSafeList(ImmutableSearchTemplate.super.recentItems(), true, false));
                this.recentItemsBuildStage = (byte) 1;
            }
            return this.recentItems;
        }

        void recentItems(List<SearchItemElement> list) {
            this.recentItems = list;
            this.recentItemsBuildStage = (byte) 1;
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
            if (this.recentItemsBuildStage == -1) {
                arrayList.add("recentItems");
            }
            return "Cannot build SearchTemplate, attribute initializers form cycle " + arrayList;
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

    @Override // Watch.SearchTemplateInterface.v1_0.SearchTemplate
    @JsonProperty("title")
    public String title() {
        return this.title;
    }

    @Override // Watch.SearchTemplateInterface.v1_0.SearchTemplate
    @JsonProperty("onQueryEntered")
    public List<Method> onQueryEntered() {
        return this.onQueryEntered;
    }

    @Override // Watch.SearchTemplateInterface.v1_0.SearchTemplate
    @JsonProperty("header")
    @Nullable
    public String header() {
        return this.header;
    }

    @Override // Watch.SearchTemplateInterface.v1_0.SearchTemplate
    @JsonProperty("recentItems")
    public List<SearchItemElement> recentItems() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.recentItems();
        }
        return this.recentItems;
    }

    public final ImmutableSearchTemplate withOnCreated(Method... methodArr) {
        return new ImmutableSearchTemplate(createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.onQueryEntered, this.header, this.recentItems);
    }

    public final ImmutableSearchTemplate withOnCreated(Iterable<? extends Method> iterable) {
        return this.onCreated == iterable ? this : new ImmutableSearchTemplate(createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.onQueryEntered, this.header, this.recentItems);
    }

    public final ImmutableSearchTemplate withOnBound(Method... methodArr) {
        return new ImmutableSearchTemplate(this.onCreated, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.onQueryEntered, this.header, this.recentItems);
    }

    public final ImmutableSearchTemplate withOnBound(Iterable<? extends Method> iterable) {
        if (this.onBound == iterable) {
            return this;
        }
        return new ImmutableSearchTemplate(this.onCreated, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.onQueryEntered, this.header, this.recentItems);
    }

    public final ImmutableSearchTemplate withOnViewed(Method... methodArr) {
        return new ImmutableSearchTemplate(this.onCreated, this.onBound, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.launchMode, this.cacheSeconds, this.title, this.onQueryEntered, this.header, this.recentItems);
    }

    public final ImmutableSearchTemplate withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutableSearchTemplate(this.onCreated, this.onBound, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.launchMode, this.cacheSeconds, this.title, this.onQueryEntered, this.header, this.recentItems);
    }

    public final ImmutableSearchTemplate withLaunchMode(TemplateLaunchMode templateLaunchMode) {
        TemplateLaunchMode templateLaunchMode2 = (TemplateLaunchMode) requireNonNull(templateLaunchMode, "launchMode");
        return this.launchMode == templateLaunchMode2 ? this : new ImmutableSearchTemplate(this.onCreated, this.onBound, this.onViewed, templateLaunchMode2, this.cacheSeconds, this.title, this.onQueryEntered, this.header, this.recentItems);
    }

    public final ImmutableSearchTemplate withCacheSeconds(Long l) {
        Long l2 = (Long) requireNonNull(l, "cacheSeconds");
        return this.cacheSeconds.equals(l2) ? this : new ImmutableSearchTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, l2, this.title, this.onQueryEntered, this.header, this.recentItems);
    }

    public final ImmutableSearchTemplate withTitle(String str) {
        String str2 = (String) requireNonNull(str, "title");
        return this.title.equals(str2) ? this : new ImmutableSearchTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, str2, this.onQueryEntered, this.header, this.recentItems);
    }

    public final ImmutableSearchTemplate withOnQueryEntered(Method... methodArr) {
        return new ImmutableSearchTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.title, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.header, this.recentItems);
    }

    public final ImmutableSearchTemplate withOnQueryEntered(Iterable<? extends Method> iterable) {
        if (this.onQueryEntered == iterable) {
            return this;
        }
        return new ImmutableSearchTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.title, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.header, this.recentItems);
    }

    public final ImmutableSearchTemplate withHeader(@Nullable String str) {
        return equals(this.header, str) ? this : new ImmutableSearchTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.onQueryEntered, str, this.recentItems);
    }

    public final ImmutableSearchTemplate withRecentItems(SearchItemElement... searchItemElementArr) {
        return new ImmutableSearchTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.onQueryEntered, this.header, createUnmodifiableList(false, createSafeList(Arrays.asList(searchItemElementArr), true, false)));
    }

    public final ImmutableSearchTemplate withRecentItems(Iterable<? extends SearchItemElement> iterable) {
        if (this.recentItems == iterable) {
            return this;
        }
        return new ImmutableSearchTemplate(this.onCreated, this.onBound, this.onViewed, this.launchMode, this.cacheSeconds, this.title, this.onQueryEntered, this.header, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSearchTemplate) && equalTo(0, (ImmutableSearchTemplate) obj);
    }

    private boolean equalTo(int i, ImmutableSearchTemplate immutableSearchTemplate) {
        return this.onCreated.equals(immutableSearchTemplate.onCreated) && this.onBound.equals(immutableSearchTemplate.onBound) && this.onViewed.equals(immutableSearchTemplate.onViewed) && this.launchMode.equals(immutableSearchTemplate.launchMode) && this.cacheSeconds.equals(immutableSearchTemplate.cacheSeconds) && this.title.equals(immutableSearchTemplate.title) && this.onQueryEntered.equals(immutableSearchTemplate.onQueryEntered) && equals(this.header, immutableSearchTemplate.header) && this.recentItems.equals(immutableSearchTemplate.recentItems);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.onCreated.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onBound.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.onViewed.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.launchMode.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.cacheSeconds.hashCode();
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + this.title.hashCode();
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + this.onQueryEntered.hashCode();
        int iHashCode8 = iHashCode7 + (iHashCode7 << 5) + hashCode(this.header);
        return iHashCode8 + (iHashCode8 << 5) + this.recentItems.hashCode();
    }

    public String toString() {
        return "SearchTemplate{onCreated=" + this.onCreated + ", onBound=" + this.onBound + ", onViewed=" + this.onViewed + ", launchMode=" + this.launchMode + ", cacheSeconds=" + this.cacheSeconds + ", title=" + this.title + ", onQueryEntered=" + this.onQueryEntered + ", header=" + this.header + ", recentItems=" + this.recentItems + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SearchTemplate", generator = "Immutables")
    static final class Json extends SearchTemplate {

        @Nullable
        Long cacheSeconds;

        @Nullable
        String header;

        @Nullable
        TemplateLaunchMode launchMode;
        boolean onBoundIsSet;
        boolean onCreatedIsSet;
        boolean onViewedIsSet;
        boolean recentItemsIsSet;

        @Nullable
        String title;

        @Nullable
        List<Method> onCreated = Collections.emptyList();

        @Nullable
        List<Method> onBound = Collections.emptyList();

        @Nullable
        List<Method> onViewed = Collections.emptyList();

        @Nullable
        List<Method> onQueryEntered = Collections.emptyList();

        @Nullable
        List<SearchItemElement> recentItems = Collections.emptyList();

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
        public void setTitle(String str) {
            this.title = str;
        }

        @JsonProperty("onQueryEntered")
        public void setOnQueryEntered(List<Method> list) {
            this.onQueryEntered = list;
        }

        @JsonProperty("header")
        public void setHeader(@Nullable String str) {
            this.header = str;
        }

        @JsonProperty("recentItems")
        public void setRecentItems(List<SearchItemElement> list) {
            this.recentItems = list;
            this.recentItemsIsSet = true;
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

        @Override // Watch.SearchTemplateInterface.v1_0.SearchTemplate
        public String title() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.SearchTemplateInterface.v1_0.SearchTemplate
        public List<Method> onQueryEntered() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.SearchTemplateInterface.v1_0.SearchTemplate
        public String header() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.SearchTemplateInterface.v1_0.SearchTemplate
        public List<SearchItemElement> recentItems() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSearchTemplate fromJson(Json json) {
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
        List<Method> list = json.onQueryEntered;
        if (list != null) {
            builder.addAllOnQueryEntered(list);
        }
        String str2 = json.header;
        if (str2 != null) {
            builder.header(str2);
        }
        if (json.recentItemsIsSet) {
            builder.addAllRecentItems(json.recentItems);
        }
        return builder.build();
    }

    public static ImmutableSearchTemplate copyOf(SearchTemplate searchTemplate) {
        if (searchTemplate instanceof ImmutableSearchTemplate) {
            return (ImmutableSearchTemplate) searchTemplate;
        }
        return builder().from(searchTemplate).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SearchTemplate", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_TITLE = 1;
        private static final long OPT_BIT_ON_BOUND = 2;
        private static final long OPT_BIT_ON_CREATED = 1;
        private static final long OPT_BIT_ON_VIEWED = 4;
        private static final long OPT_BIT_RECENT_ITEMS = 8;

        @Nullable
        private Long cacheSeconds;

        @Nullable
        private String header;
        private long initBits;

        @Nullable
        private TemplateLaunchMode launchMode;
        private List<Method> onBound;
        private List<Method> onCreated;
        private List<Method> onQueryEntered;
        private List<Method> onViewed;
        private long optBits;
        private List<SearchItemElement> recentItems;

        @Nullable
        private String title;

        private Builder() {
            this.initBits = 1L;
            this.onCreated = new ArrayList();
            this.onBound = new ArrayList();
            this.onViewed = new ArrayList();
            this.onQueryEntered = new ArrayList();
            this.recentItems = new ArrayList();
        }

        public final Builder from(SearchTemplate searchTemplate) {
            ImmutableSearchTemplate.requireNonNull(searchTemplate, "instance");
            from((Object) searchTemplate);
            return this;
        }

        public final Builder from(Template template) {
            ImmutableSearchTemplate.requireNonNull(template, "instance");
            from((Object) template);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof SearchTemplate) {
                SearchTemplate searchTemplate = (SearchTemplate) obj;
                addAllOnBound(searchTemplate.onBound());
                cacheSeconds(searchTemplate.cacheSeconds());
                addAllOnQueryEntered(searchTemplate.onQueryEntered());
                launchMode(searchTemplate.launchMode());
                String strHeader = searchTemplate.header();
                if (strHeader != null) {
                    header(strHeader);
                }
                addAllOnViewed(searchTemplate.onViewed());
                title(searchTemplate.title());
                addAllRecentItems(searchTemplate.recentItems());
                addAllOnCreated(searchTemplate.onCreated());
                j = 31;
            } else {
                j = 0;
            }
            if (obj instanceof Template) {
                Template template = (Template) obj;
                if ((j & 1) == 0) {
                    addAllOnBound(template.onBound());
                    j |= 1;
                }
                if ((j & 4) == 0) {
                    launchMode(template.launchMode());
                    j |= 4;
                }
                if ((j & 8) == 0) {
                    addAllOnViewed(template.onViewed());
                    j |= 8;
                }
                if ((j & 16) == 0) {
                    addAllOnCreated(template.onCreated());
                    j |= 16;
                }
                if ((j & 2) == 0) {
                    cacheSeconds(template.cacheSeconds());
                }
            }
        }

        public final Builder addOnCreated(Method method) {
            this.onCreated.add((Method) ImmutableSearchTemplate.requireNonNull(method, "onCreated element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnCreated(Method... methodArr) {
            for (Method method : methodArr) {
                this.onCreated.add((Method) ImmutableSearchTemplate.requireNonNull(method, "onCreated element"));
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
                this.onCreated.add((Method) ImmutableSearchTemplate.requireNonNull(it.next(), "onCreated element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnBound(Method method) {
            this.onBound.add((Method) ImmutableSearchTemplate.requireNonNull(method, "onBound element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnBound(Method... methodArr) {
            for (Method method : methodArr) {
                this.onBound.add((Method) ImmutableSearchTemplate.requireNonNull(method, "onBound element"));
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
                this.onBound.add((Method) ImmutableSearchTemplate.requireNonNull(it.next(), "onBound element"));
            }
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutableSearchTemplate.requireNonNull(method, "onViewed element"));
            this.optBits |= 4;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutableSearchTemplate.requireNonNull(method, "onViewed element"));
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
                this.onViewed.add((Method) ImmutableSearchTemplate.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 4;
            return this;
        }

        @JsonProperty("launchMode")
        public final Builder launchMode(TemplateLaunchMode templateLaunchMode) {
            this.launchMode = (TemplateLaunchMode) ImmutableSearchTemplate.requireNonNull(templateLaunchMode, "launchMode");
            return this;
        }

        @JsonProperty("cacheSeconds")
        public final Builder cacheSeconds(Long l) {
            this.cacheSeconds = (Long) ImmutableSearchTemplate.requireNonNull(l, "cacheSeconds");
            return this;
        }

        @JsonProperty("title")
        public final Builder title(String str) {
            this.title = (String) ImmutableSearchTemplate.requireNonNull(str, "title");
            this.initBits &= -2;
            return this;
        }

        public final Builder addOnQueryEntered(Method method) {
            this.onQueryEntered.add((Method) ImmutableSearchTemplate.requireNonNull(method, "onQueryEntered element"));
            return this;
        }

        public final Builder addOnQueryEntered(Method... methodArr) {
            for (Method method : methodArr) {
                this.onQueryEntered.add((Method) ImmutableSearchTemplate.requireNonNull(method, "onQueryEntered element"));
            }
            return this;
        }

        @JsonProperty("onQueryEntered")
        public final Builder onQueryEntered(Iterable<? extends Method> iterable) {
            this.onQueryEntered.clear();
            return addAllOnQueryEntered(iterable);
        }

        public final Builder addAllOnQueryEntered(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onQueryEntered.add((Method) ImmutableSearchTemplate.requireNonNull(it.next(), "onQueryEntered element"));
            }
            return this;
        }

        @JsonProperty("header")
        public final Builder header(@Nullable String str) {
            this.header = str;
            return this;
        }

        public final Builder addRecentItems(SearchItemElement searchItemElement) {
            this.recentItems.add((SearchItemElement) ImmutableSearchTemplate.requireNonNull(searchItemElement, "recentItems element"));
            this.optBits |= 8;
            return this;
        }

        public final Builder addRecentItems(SearchItemElement... searchItemElementArr) {
            for (SearchItemElement searchItemElement : searchItemElementArr) {
                this.recentItems.add((SearchItemElement) ImmutableSearchTemplate.requireNonNull(searchItemElement, "recentItems element"));
            }
            this.optBits |= 8;
            return this;
        }

        @JsonProperty("recentItems")
        public final Builder recentItems(Iterable<? extends SearchItemElement> iterable) {
            this.recentItems.clear();
            return addAllRecentItems(iterable);
        }

        public final Builder addAllRecentItems(Iterable<? extends SearchItemElement> iterable) {
            Iterator<? extends SearchItemElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.recentItems.add((SearchItemElement) ImmutableSearchTemplate.requireNonNull(it.next(), "recentItems element"));
            }
            this.optBits |= 8;
            return this;
        }

        public ImmutableSearchTemplate build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableSearchTemplate(this);
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
        public boolean recentItemsIsSet() {
            return (this.optBits & 8) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("title");
            }
            return "Cannot build SearchTemplate, some of required attributes are not set " + arrayList;
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
