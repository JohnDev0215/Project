package Touch.WidgetsInterface.v1_0;

import CoreInterface.v1_0.Method;
import ExternalActionInterface.v1_0.PlayableContent;
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
@Generated(from = "PlayableContentSlideElement", generator = "Immutables")
public final class ImmutablePlayableContentSlideElement extends PlayableContentSlideElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final PlayableContent content;
    private volatile transient InitShim initShim;
    private final List<Method> onItemSelected;
    private final List<Method> onViewed;

    private ImmutablePlayableContentSlideElement(Builder builder) {
        this.initShim = new InitShim();
        this.content = builder.content;
        if (builder.onItemSelectedIsSet()) {
            this.initShim.onItemSelected(createUnmodifiableList(true, builder.onItemSelected));
        }
        if (builder.onViewedIsSet()) {
            this.initShim.onViewed(createUnmodifiableList(true, builder.onViewed));
        }
        this.onItemSelected = this.initShim.onItemSelected();
        this.onViewed = this.initShim.onViewed();
        this.initShim = null;
    }

    private ImmutablePlayableContentSlideElement(PlayableContent playableContent, List<Method> list, List<Method> list2) {
        this.initShim = new InitShim();
        this.content = playableContent;
        this.onItemSelected = list;
        this.onViewed = list2;
        this.initShim = null;
    }

    @Generated(from = "PlayableContentSlideElement", generator = "Immutables")
    private final class InitShim {
        private List<Method> onItemSelected;
        private byte onItemSelectedBuildStage;
        private List<Method> onViewed;
        private byte onViewedBuildStage;

        private InitShim() {
            this.onItemSelectedBuildStage = (byte) 0;
            this.onViewedBuildStage = (byte) 0;
        }

        List<Method> onItemSelected() {
            byte b = this.onItemSelectedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onItemSelectedBuildStage = (byte) -1;
                this.onItemSelected = ImmutablePlayableContentSlideElement.createUnmodifiableList(false, ImmutablePlayableContentSlideElement.createSafeList(ImmutablePlayableContentSlideElement.super.onItemSelected(), true, false));
                this.onItemSelectedBuildStage = (byte) 1;
            }
            return this.onItemSelected;
        }

        void onItemSelected(List<Method> list) {
            this.onItemSelected = list;
            this.onItemSelectedBuildStage = (byte) 1;
        }

        List<Method> onViewed() {
            byte b = this.onViewedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onViewedBuildStage = (byte) -1;
                this.onViewed = ImmutablePlayableContentSlideElement.createUnmodifiableList(false, ImmutablePlayableContentSlideElement.createSafeList(ImmutablePlayableContentSlideElement.super.onViewed(), true, false));
                this.onViewedBuildStage = (byte) 1;
            }
            return this.onViewed;
        }

        void onViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.onItemSelectedBuildStage == -1) {
                arrayList.add("onItemSelected");
            }
            if (this.onViewedBuildStage == -1) {
                arrayList.add("onViewed");
            }
            return "Cannot build PlayableContentSlideElement, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // Touch.WidgetsInterface.v1_0.PlayableContentSlideElement
    @JsonProperty("content")
    public PlayableContent content() {
        return this.content;
    }

    @Override // Touch.WidgetsInterface.v1_0.PlayableContentSlideElement
    @JsonProperty("onItemSelected")
    public List<Method> onItemSelected() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onItemSelected();
        }
        return this.onItemSelected;
    }

    @Override // Touch.WidgetsInterface.v1_0.PlayableContentSlideElement
    @JsonProperty("onViewed")
    public List<Method> onViewed() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onViewed();
        }
        return this.onViewed;
    }

    public final ImmutablePlayableContentSlideElement withContent(PlayableContent playableContent) {
        return this.content == playableContent ? this : new ImmutablePlayableContentSlideElement((PlayableContent) requireNonNull(playableContent, "content"), this.onItemSelected, this.onViewed);
    }

    public final ImmutablePlayableContentSlideElement withOnItemSelected(Method... methodArr) {
        return new ImmutablePlayableContentSlideElement(this.content, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onViewed);
    }

    public final ImmutablePlayableContentSlideElement withOnItemSelected(Iterable<? extends Method> iterable) {
        if (this.onItemSelected == iterable) {
            return this;
        }
        return new ImmutablePlayableContentSlideElement(this.content, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onViewed);
    }

    public final ImmutablePlayableContentSlideElement withOnViewed(Method... methodArr) {
        return new ImmutablePlayableContentSlideElement(this.content, this.onItemSelected, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutablePlayableContentSlideElement withOnViewed(Iterable<? extends Method> iterable) {
        if (this.onViewed == iterable) {
            return this;
        }
        return new ImmutablePlayableContentSlideElement(this.content, this.onItemSelected, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutablePlayableContentSlideElement) && equalTo(0, (ImmutablePlayableContentSlideElement) obj);
    }

    private boolean equalTo(int i, ImmutablePlayableContentSlideElement immutablePlayableContentSlideElement) {
        return this.content.equals(immutablePlayableContentSlideElement.content) && this.onItemSelected.equals(immutablePlayableContentSlideElement.onItemSelected) && this.onViewed.equals(immutablePlayableContentSlideElement.onViewed);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.content.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onItemSelected.hashCode();
        return iHashCode2 + (iHashCode2 << 5) + this.onViewed.hashCode();
    }

    public String toString() {
        return "PlayableContentSlideElement{content=" + this.content + ", onItemSelected=" + this.onItemSelected + ", onViewed=" + this.onViewed + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "PlayableContentSlideElement", generator = "Immutables")
    static final class Json extends PlayableContentSlideElement {

        @Nullable
        PlayableContent content;
        boolean onItemSelectedIsSet;
        boolean onViewedIsSet;

        @Nullable
        List<Method> onItemSelected = Collections.emptyList();

        @Nullable
        List<Method> onViewed = Collections.emptyList();

        Json() {
        }

        @JsonProperty("content")
        public void setContent(PlayableContent playableContent) {
            this.content = playableContent;
        }

        @JsonProperty("onItemSelected")
        public void setOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
            this.onItemSelectedIsSet = true;
        }

        @JsonProperty("onViewed")
        public void setOnViewed(List<Method> list) {
            this.onViewed = list;
            this.onViewedIsSet = true;
        }

        @Override // Touch.WidgetsInterface.v1_0.PlayableContentSlideElement
        public PlayableContent content() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.PlayableContentSlideElement
        public List<Method> onItemSelected() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.PlayableContentSlideElement
        public List<Method> onViewed() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutablePlayableContentSlideElement fromJson(Json json) {
        Builder builder = builder();
        PlayableContent playableContent = json.content;
        if (playableContent != null) {
            builder.content(playableContent);
        }
        if (json.onItemSelectedIsSet) {
            builder.addAllOnItemSelected(json.onItemSelected);
        }
        if (json.onViewedIsSet) {
            builder.addAllOnViewed(json.onViewed);
        }
        return builder.build();
    }

    public static ImmutablePlayableContentSlideElement copyOf(PlayableContentSlideElement playableContentSlideElement) {
        if (playableContentSlideElement instanceof ImmutablePlayableContentSlideElement) {
            return (ImmutablePlayableContentSlideElement) playableContentSlideElement;
        }
        return builder().from(playableContentSlideElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "PlayableContentSlideElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_CONTENT = 1;
        private static final long OPT_BIT_ON_ITEM_SELECTED = 1;
        private static final long OPT_BIT_ON_VIEWED = 2;

        @Nullable
        private PlayableContent content;
        private long initBits;
        private List<Method> onItemSelected;
        private List<Method> onViewed;
        private long optBits;

        private Builder() {
            this.initBits = 1L;
            this.onItemSelected = new ArrayList();
            this.onViewed = new ArrayList();
        }

        public final Builder from(PlayableContentSlideElement playableContentSlideElement) {
            ImmutablePlayableContentSlideElement.requireNonNull(playableContentSlideElement, "instance");
            content(playableContentSlideElement.content());
            addAllOnItemSelected(playableContentSlideElement.onItemSelected());
            addAllOnViewed(playableContentSlideElement.onViewed());
            return this;
        }

        @JsonProperty("content")
        public final Builder content(PlayableContent playableContent) {
            this.content = (PlayableContent) ImmutablePlayableContentSlideElement.requireNonNull(playableContent, "content");
            this.initBits &= -2;
            return this;
        }

        public final Builder addOnItemSelected(Method method) {
            this.onItemSelected.add((Method) ImmutablePlayableContentSlideElement.requireNonNull(method, "onItemSelected element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnItemSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onItemSelected.add((Method) ImmutablePlayableContentSlideElement.requireNonNull(method, "onItemSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onItemSelected")
        public final Builder onItemSelected(Iterable<? extends Method> iterable) {
            this.onItemSelected.clear();
            return addAllOnItemSelected(iterable);
        }

        public final Builder addAllOnItemSelected(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onItemSelected.add((Method) ImmutablePlayableContentSlideElement.requireNonNull(it.next(), "onItemSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnViewed(Method method) {
            this.onViewed.add((Method) ImmutablePlayableContentSlideElement.requireNonNull(method, "onViewed element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnViewed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onViewed.add((Method) ImmutablePlayableContentSlideElement.requireNonNull(method, "onViewed element"));
            }
            this.optBits |= 2;
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
                this.onViewed.add((Method) ImmutablePlayableContentSlideElement.requireNonNull(it.next(), "onViewed element"));
            }
            this.optBits |= 2;
            return this;
        }

        public ImmutablePlayableContentSlideElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutablePlayableContentSlideElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onItemSelectedIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onViewedIsSet() {
            return (this.optBits & 2) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("content");
            }
            return "Cannot build PlayableContentSlideElement, some of required attributes are not set " + arrayList;
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
