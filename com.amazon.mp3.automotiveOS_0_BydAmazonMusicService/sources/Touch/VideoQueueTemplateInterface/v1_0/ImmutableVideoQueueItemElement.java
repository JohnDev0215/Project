package Touch.VideoQueueTemplateInterface.v1_0;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.LabeledIconButtonElement;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.android.gms.cast.MediaTrack;
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
@Generated(from = "VideoQueueItemElement", generator = "Immutables")
public final class ImmutableVideoQueueItemElement extends VideoQueueItemElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final List<LabeledIconButtonElement> actions;

    @Nullable
    private final Integer durationSeconds;
    private volatile transient InitShim initShim;

    @Nullable
    private final String introUri;
    private final String mediaId;
    private final List<Method> onItemViewed;

    @Nullable
    private final String subtitle;

    @Nullable
    private final String title;
    private final String uri;

    private ImmutableVideoQueueItemElement(Builder builder) {
        this.initShim = new InitShim();
        this.mediaId = builder.mediaId;
        this.title = builder.title;
        this.subtitle = builder.subtitle;
        this.introUri = builder.introUri;
        this.uri = builder.uri;
        this.durationSeconds = builder.durationSeconds;
        if (builder.onItemViewedIsSet()) {
            this.initShim.onItemViewed(createUnmodifiableList(true, builder.onItemViewed));
        }
        if (builder.actionsIsSet()) {
            this.initShim.actions(createUnmodifiableList(true, builder.actions));
        }
        this.onItemViewed = this.initShim.onItemViewed();
        this.actions = this.initShim.actions();
        this.initShim = null;
    }

    private ImmutableVideoQueueItemElement(String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, String str5, @Nullable Integer num, List<Method> list, List<LabeledIconButtonElement> list2) {
        this.initShim = new InitShim();
        this.mediaId = str;
        this.title = str2;
        this.subtitle = str3;
        this.introUri = str4;
        this.uri = str5;
        this.durationSeconds = num;
        this.onItemViewed = list;
        this.actions = list2;
        this.initShim = null;
    }

    @Generated(from = "VideoQueueItemElement", generator = "Immutables")
    private final class InitShim {
        private List<LabeledIconButtonElement> actions;
        private byte actionsBuildStage;
        private List<Method> onItemViewed;
        private byte onItemViewedBuildStage;

        private InitShim() {
            this.onItemViewedBuildStage = (byte) 0;
            this.actionsBuildStage = (byte) 0;
        }

        List<Method> onItemViewed() {
            byte b = this.onItemViewedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onItemViewedBuildStage = (byte) -1;
                this.onItemViewed = ImmutableVideoQueueItemElement.createUnmodifiableList(false, ImmutableVideoQueueItemElement.createSafeList(ImmutableVideoQueueItemElement.super.onItemViewed(), true, false));
                this.onItemViewedBuildStage = (byte) 1;
            }
            return this.onItemViewed;
        }

        void onItemViewed(List<Method> list) {
            this.onItemViewed = list;
            this.onItemViewedBuildStage = (byte) 1;
        }

        List<LabeledIconButtonElement> actions() {
            byte b = this.actionsBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.actionsBuildStage = (byte) -1;
                this.actions = ImmutableVideoQueueItemElement.createUnmodifiableList(false, ImmutableVideoQueueItemElement.createSafeList(ImmutableVideoQueueItemElement.super.actions(), true, false));
                this.actionsBuildStage = (byte) 1;
            }
            return this.actions;
        }

        void actions(List<LabeledIconButtonElement> list) {
            this.actions = list;
            this.actionsBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.onItemViewedBuildStage == -1) {
                arrayList.add("onItemViewed");
            }
            if (this.actionsBuildStage == -1) {
                arrayList.add("actions");
            }
            return "Cannot build VideoQueueItemElement, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // Touch.VideoQueueTemplateInterface.v1_0.VideoQueueItemElement
    @JsonProperty("mediaId")
    public String mediaId() {
        return this.mediaId;
    }

    @Override // Touch.VideoQueueTemplateInterface.v1_0.VideoQueueItemElement
    @JsonProperty("title")
    @Nullable
    public String title() {
        return this.title;
    }

    @Override // Touch.VideoQueueTemplateInterface.v1_0.VideoQueueItemElement
    @JsonProperty(MediaTrack.ROLE_SUBTITLE)
    @Nullable
    public String subtitle() {
        return this.subtitle;
    }

    @Override // Touch.VideoQueueTemplateInterface.v1_0.VideoQueueItemElement
    @JsonProperty("introUri")
    @Nullable
    public String introUri() {
        return this.introUri;
    }

    @Override // Touch.VideoQueueTemplateInterface.v1_0.VideoQueueItemElement
    @JsonProperty("uri")
    public String uri() {
        return this.uri;
    }

    @Override // Touch.VideoQueueTemplateInterface.v1_0.VideoQueueItemElement
    @JsonProperty("durationSeconds")
    @Nullable
    public Integer durationSeconds() {
        return this.durationSeconds;
    }

    @Override // Touch.VideoQueueTemplateInterface.v1_0.VideoQueueItemElement
    @JsonProperty("onItemViewed")
    public List<Method> onItemViewed() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onItemViewed();
        }
        return this.onItemViewed;
    }

    @Override // Touch.VideoQueueTemplateInterface.v1_0.VideoQueueItemElement
    @JsonProperty("actions")
    public List<LabeledIconButtonElement> actions() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.actions();
        }
        return this.actions;
    }

    public final ImmutableVideoQueueItemElement withMediaId(String str) {
        String str2 = (String) requireNonNull(str, "mediaId");
        return this.mediaId.equals(str2) ? this : new ImmutableVideoQueueItemElement(str2, this.title, this.subtitle, this.introUri, this.uri, this.durationSeconds, this.onItemViewed, this.actions);
    }

    public final ImmutableVideoQueueItemElement withTitle(@Nullable String str) {
        return equals(this.title, str) ? this : new ImmutableVideoQueueItemElement(this.mediaId, str, this.subtitle, this.introUri, this.uri, this.durationSeconds, this.onItemViewed, this.actions);
    }

    public final ImmutableVideoQueueItemElement withSubtitle(@Nullable String str) {
        return equals(this.subtitle, str) ? this : new ImmutableVideoQueueItemElement(this.mediaId, this.title, str, this.introUri, this.uri, this.durationSeconds, this.onItemViewed, this.actions);
    }

    public final ImmutableVideoQueueItemElement withIntroUri(@Nullable String str) {
        return equals(this.introUri, str) ? this : new ImmutableVideoQueueItemElement(this.mediaId, this.title, this.subtitle, str, this.uri, this.durationSeconds, this.onItemViewed, this.actions);
    }

    public final ImmutableVideoQueueItemElement withUri(String str) {
        String str2 = (String) requireNonNull(str, "uri");
        return this.uri.equals(str2) ? this : new ImmutableVideoQueueItemElement(this.mediaId, this.title, this.subtitle, this.introUri, str2, this.durationSeconds, this.onItemViewed, this.actions);
    }

    public final ImmutableVideoQueueItemElement withDurationSeconds(@Nullable Integer num) {
        return equals(this.durationSeconds, num) ? this : new ImmutableVideoQueueItemElement(this.mediaId, this.title, this.subtitle, this.introUri, this.uri, num, this.onItemViewed, this.actions);
    }

    public final ImmutableVideoQueueItemElement withOnItemViewed(Method... methodArr) {
        return new ImmutableVideoQueueItemElement(this.mediaId, this.title, this.subtitle, this.introUri, this.uri, this.durationSeconds, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.actions);
    }

    public final ImmutableVideoQueueItemElement withOnItemViewed(Iterable<? extends Method> iterable) {
        if (this.onItemViewed == iterable) {
            return this;
        }
        return new ImmutableVideoQueueItemElement(this.mediaId, this.title, this.subtitle, this.introUri, this.uri, this.durationSeconds, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.actions);
    }

    public final ImmutableVideoQueueItemElement withActions(LabeledIconButtonElement... labeledIconButtonElementArr) {
        return new ImmutableVideoQueueItemElement(this.mediaId, this.title, this.subtitle, this.introUri, this.uri, this.durationSeconds, this.onItemViewed, createUnmodifiableList(false, createSafeList(Arrays.asList(labeledIconButtonElementArr), true, false)));
    }

    public final ImmutableVideoQueueItemElement withActions(Iterable<? extends LabeledIconButtonElement> iterable) {
        if (this.actions == iterable) {
            return this;
        }
        return new ImmutableVideoQueueItemElement(this.mediaId, this.title, this.subtitle, this.introUri, this.uri, this.durationSeconds, this.onItemViewed, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableVideoQueueItemElement) && equalTo(0, (ImmutableVideoQueueItemElement) obj);
    }

    private boolean equalTo(int i, ImmutableVideoQueueItemElement immutableVideoQueueItemElement) {
        return this.mediaId.equals(immutableVideoQueueItemElement.mediaId) && equals(this.title, immutableVideoQueueItemElement.title) && equals(this.subtitle, immutableVideoQueueItemElement.subtitle) && equals(this.introUri, immutableVideoQueueItemElement.introUri) && this.uri.equals(immutableVideoQueueItemElement.uri) && equals(this.durationSeconds, immutableVideoQueueItemElement.durationSeconds) && this.onItemViewed.equals(immutableVideoQueueItemElement.onItemViewed) && this.actions.equals(immutableVideoQueueItemElement.actions);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.mediaId.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + hashCode(this.title);
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + hashCode(this.subtitle);
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.introUri);
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.uri.hashCode();
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + hashCode(this.durationSeconds);
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + this.onItemViewed.hashCode();
        return iHashCode7 + (iHashCode7 << 5) + this.actions.hashCode();
    }

    public String toString() {
        return "VideoQueueItemElement{mediaId=" + this.mediaId + ", title=" + this.title + ", subtitle=" + this.subtitle + ", introUri=" + this.introUri + ", uri=" + this.uri + ", durationSeconds=" + this.durationSeconds + ", onItemViewed=" + this.onItemViewed + ", actions=" + this.actions + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "VideoQueueItemElement", generator = "Immutables")
    static final class Json extends VideoQueueItemElement {
        boolean actionsIsSet;

        @Nullable
        Integer durationSeconds;

        @Nullable
        String introUri;

        @Nullable
        String mediaId;
        boolean onItemViewedIsSet;

        @Nullable
        String subtitle;

        @Nullable
        String title;

        @Nullable
        String uri;

        @Nullable
        List<Method> onItemViewed = Collections.emptyList();

        @Nullable
        List<LabeledIconButtonElement> actions = Collections.emptyList();

        Json() {
        }

        @JsonProperty("mediaId")
        public void setMediaId(String str) {
            this.mediaId = str;
        }

        @JsonProperty("title")
        public void setTitle(@Nullable String str) {
            this.title = str;
        }

        @JsonProperty(MediaTrack.ROLE_SUBTITLE)
        public void setSubtitle(@Nullable String str) {
            this.subtitle = str;
        }

        @JsonProperty("introUri")
        public void setIntroUri(@Nullable String str) {
            this.introUri = str;
        }

        @JsonProperty("uri")
        public void setUri(String str) {
            this.uri = str;
        }

        @JsonProperty("durationSeconds")
        public void setDurationSeconds(@Nullable Integer num) {
            this.durationSeconds = num;
        }

        @JsonProperty("onItemViewed")
        public void setOnItemViewed(List<Method> list) {
            this.onItemViewed = list;
            this.onItemViewedIsSet = true;
        }

        @JsonProperty("actions")
        public void setActions(List<LabeledIconButtonElement> list) {
            this.actions = list;
            this.actionsIsSet = true;
        }

        @Override // Touch.VideoQueueTemplateInterface.v1_0.VideoQueueItemElement
        public String mediaId() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.VideoQueueTemplateInterface.v1_0.VideoQueueItemElement
        public String title() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.VideoQueueTemplateInterface.v1_0.VideoQueueItemElement
        public String subtitle() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.VideoQueueTemplateInterface.v1_0.VideoQueueItemElement
        public String introUri() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.VideoQueueTemplateInterface.v1_0.VideoQueueItemElement
        public String uri() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.VideoQueueTemplateInterface.v1_0.VideoQueueItemElement
        public Integer durationSeconds() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.VideoQueueTemplateInterface.v1_0.VideoQueueItemElement
        public List<Method> onItemViewed() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.VideoQueueTemplateInterface.v1_0.VideoQueueItemElement
        public List<LabeledIconButtonElement> actions() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableVideoQueueItemElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.mediaId;
        if (str != null) {
            builder.mediaId(str);
        }
        String str2 = json.title;
        if (str2 != null) {
            builder.title(str2);
        }
        String str3 = json.subtitle;
        if (str3 != null) {
            builder.subtitle(str3);
        }
        String str4 = json.introUri;
        if (str4 != null) {
            builder.introUri(str4);
        }
        String str5 = json.uri;
        if (str5 != null) {
            builder.uri(str5);
        }
        Integer num = json.durationSeconds;
        if (num != null) {
            builder.durationSeconds(num);
        }
        if (json.onItemViewedIsSet) {
            builder.addAllOnItemViewed(json.onItemViewed);
        }
        if (json.actionsIsSet) {
            builder.addAllActions(json.actions);
        }
        return builder.build();
    }

    public static ImmutableVideoQueueItemElement copyOf(VideoQueueItemElement videoQueueItemElement) {
        if (videoQueueItemElement instanceof ImmutableVideoQueueItemElement) {
            return (ImmutableVideoQueueItemElement) videoQueueItemElement;
        }
        return builder().from(videoQueueItemElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "VideoQueueItemElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_MEDIA_ID = 1;
        private static final long INIT_BIT_URI = 2;
        private static final long OPT_BIT_ACTIONS = 2;
        private static final long OPT_BIT_ON_ITEM_VIEWED = 1;
        private List<LabeledIconButtonElement> actions;

        @Nullable
        private Integer durationSeconds;
        private long initBits;

        @Nullable
        private String introUri;

        @Nullable
        private String mediaId;
        private List<Method> onItemViewed;
        private long optBits;

        @Nullable
        private String subtitle;

        @Nullable
        private String title;

        @Nullable
        private String uri;

        private Builder() {
            this.initBits = 3L;
            this.onItemViewed = new ArrayList();
            this.actions = new ArrayList();
        }

        public final Builder from(VideoQueueItemElement videoQueueItemElement) {
            ImmutableVideoQueueItemElement.requireNonNull(videoQueueItemElement, "instance");
            mediaId(videoQueueItemElement.mediaId());
            String strTitle = videoQueueItemElement.title();
            if (strTitle != null) {
                title(strTitle);
            }
            String strSubtitle = videoQueueItemElement.subtitle();
            if (strSubtitle != null) {
                subtitle(strSubtitle);
            }
            String strIntroUri = videoQueueItemElement.introUri();
            if (strIntroUri != null) {
                introUri(strIntroUri);
            }
            uri(videoQueueItemElement.uri());
            Integer numDurationSeconds = videoQueueItemElement.durationSeconds();
            if (numDurationSeconds != null) {
                durationSeconds(numDurationSeconds);
            }
            addAllOnItemViewed(videoQueueItemElement.onItemViewed());
            addAllActions(videoQueueItemElement.actions());
            return this;
        }

        @JsonProperty("mediaId")
        public final Builder mediaId(String str) {
            this.mediaId = (String) ImmutableVideoQueueItemElement.requireNonNull(str, "mediaId");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("title")
        public final Builder title(@Nullable String str) {
            this.title = str;
            return this;
        }

        @JsonProperty(MediaTrack.ROLE_SUBTITLE)
        public final Builder subtitle(@Nullable String str) {
            this.subtitle = str;
            return this;
        }

        @JsonProperty("introUri")
        public final Builder introUri(@Nullable String str) {
            this.introUri = str;
            return this;
        }

        @JsonProperty("uri")
        public final Builder uri(String str) {
            this.uri = (String) ImmutableVideoQueueItemElement.requireNonNull(str, "uri");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("durationSeconds")
        public final Builder durationSeconds(@Nullable Integer num) {
            this.durationSeconds = num;
            return this;
        }

        public final Builder addOnItemViewed(Method method) {
            this.onItemViewed.add((Method) ImmutableVideoQueueItemElement.requireNonNull(method, "onItemViewed element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnItemViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onItemViewed.add((Method) ImmutableVideoQueueItemElement.requireNonNull(method, "onItemViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onItemViewed")
        public final Builder onItemViewed(Iterable<? extends Method> iterable) {
            this.onItemViewed.clear();
            return addAllOnItemViewed(iterable);
        }

        public final Builder addAllOnItemViewed(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onItemViewed.add((Method) ImmutableVideoQueueItemElement.requireNonNull(it.next(), "onItemViewed element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addActions(LabeledIconButtonElement labeledIconButtonElement) {
            this.actions.add((LabeledIconButtonElement) ImmutableVideoQueueItemElement.requireNonNull(labeledIconButtonElement, "actions element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addActions(LabeledIconButtonElement... labeledIconButtonElementArr) {
            for (LabeledIconButtonElement labeledIconButtonElement : labeledIconButtonElementArr) {
                this.actions.add((LabeledIconButtonElement) ImmutableVideoQueueItemElement.requireNonNull(labeledIconButtonElement, "actions element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("actions")
        public final Builder actions(Iterable<? extends LabeledIconButtonElement> iterable) {
            this.actions.clear();
            return addAllActions(iterable);
        }

        public final Builder addAllActions(Iterable<? extends LabeledIconButtonElement> iterable) {
            Iterator<? extends LabeledIconButtonElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.actions.add((LabeledIconButtonElement) ImmutableVideoQueueItemElement.requireNonNull(it.next(), "actions element"));
            }
            this.optBits |= 2;
            return this;
        }

        public ImmutableVideoQueueItemElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableVideoQueueItemElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onItemViewedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean actionsIsSet() {
            return (this.optBits & 2) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("mediaId");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("uri");
            }
            return "Cannot build VideoQueueItemElement, some of required attributes are not set " + arrayList;
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
