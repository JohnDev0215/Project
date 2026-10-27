package Touch.VideoQueueTemplateInterface.v1_0;

import Touch.PlaybackTemplateInterface.v1_0.PlaybackTemplate;
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
@Generated(from = "VideoQueueElement", generator = "Immutables")
public final class ImmutableVideoQueueElement extends VideoQueueElement {

    @Nullable
    private final Integer startIndex;
    private final List<PlaybackTemplate> videoItems;

    private ImmutableVideoQueueElement(List<PlaybackTemplate> list, @Nullable Integer num) {
        this.videoItems = list;
        this.startIndex = num;
    }

    @Override // Touch.VideoQueueTemplateInterface.v1_0.VideoQueueElement
    @JsonProperty("videoItems")
    public List<PlaybackTemplate> videoItems() {
        return this.videoItems;
    }

    @Override // Touch.VideoQueueTemplateInterface.v1_0.VideoQueueElement
    @JsonProperty("startIndex")
    @Nullable
    public Integer startIndex() {
        return this.startIndex;
    }

    public final ImmutableVideoQueueElement withVideoItems(PlaybackTemplate... playbackTemplateArr) {
        return new ImmutableVideoQueueElement(createUnmodifiableList(false, createSafeList(Arrays.asList(playbackTemplateArr), true, false)), this.startIndex);
    }

    public final ImmutableVideoQueueElement withVideoItems(Iterable<? extends PlaybackTemplate> iterable) {
        return this.videoItems == iterable ? this : new ImmutableVideoQueueElement(createUnmodifiableList(false, createSafeList(iterable, true, false)), this.startIndex);
    }

    public final ImmutableVideoQueueElement withStartIndex(@Nullable Integer num) {
        return equals(this.startIndex, num) ? this : new ImmutableVideoQueueElement(this.videoItems, num);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableVideoQueueElement) && equalTo(0, (ImmutableVideoQueueElement) obj);
    }

    private boolean equalTo(int i, ImmutableVideoQueueElement immutableVideoQueueElement) {
        return this.videoItems.equals(immutableVideoQueueElement.videoItems) && equals(this.startIndex, immutableVideoQueueElement.startIndex);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.videoItems.hashCode() + 5381;
        return iHashCode + (iHashCode << 5) + hashCode(this.startIndex);
    }

    public String toString() {
        return "VideoQueueElement{videoItems=" + this.videoItems + ", startIndex=" + this.startIndex + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "VideoQueueElement", generator = "Immutables")
    static final class Json extends VideoQueueElement {

        @Nullable
        Integer startIndex;

        @Nullable
        List<PlaybackTemplate> videoItems = Collections.emptyList();

        Json() {
        }

        @JsonProperty("videoItems")
        public void setVideoItems(List<PlaybackTemplate> list) {
            this.videoItems = list;
        }

        @JsonProperty("startIndex")
        public void setStartIndex(@Nullable Integer num) {
            this.startIndex = num;
        }

        @Override // Touch.VideoQueueTemplateInterface.v1_0.VideoQueueElement
        public List<PlaybackTemplate> videoItems() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.VideoQueueTemplateInterface.v1_0.VideoQueueElement
        public Integer startIndex() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableVideoQueueElement fromJson(Json json) {
        Builder builder = builder();
        List<PlaybackTemplate> list = json.videoItems;
        if (list != null) {
            builder.addAllVideoItems(list);
        }
        Integer num = json.startIndex;
        if (num != null) {
            builder.startIndex(num);
        }
        return builder.build();
    }

    public static ImmutableVideoQueueElement copyOf(VideoQueueElement videoQueueElement) {
        if (videoQueueElement instanceof ImmutableVideoQueueElement) {
            return (ImmutableVideoQueueElement) videoQueueElement;
        }
        return builder().from(videoQueueElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "VideoQueueElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {

        @Nullable
        private Integer startIndex;
        private List<PlaybackTemplate> videoItems;

        private Builder() {
            this.videoItems = new ArrayList();
        }

        public final Builder from(VideoQueueElement videoQueueElement) {
            ImmutableVideoQueueElement.requireNonNull(videoQueueElement, "instance");
            addAllVideoItems(videoQueueElement.videoItems());
            Integer numStartIndex = videoQueueElement.startIndex();
            if (numStartIndex != null) {
                startIndex(numStartIndex);
            }
            return this;
        }

        public final Builder addVideoItems(PlaybackTemplate playbackTemplate) {
            this.videoItems.add((PlaybackTemplate) ImmutableVideoQueueElement.requireNonNull(playbackTemplate, "videoItems element"));
            return this;
        }

        public final Builder addVideoItems(PlaybackTemplate... playbackTemplateArr) {
            for (PlaybackTemplate playbackTemplate : playbackTemplateArr) {
                this.videoItems.add((PlaybackTemplate) ImmutableVideoQueueElement.requireNonNull(playbackTemplate, "videoItems element"));
            }
            return this;
        }

        @JsonProperty("videoItems")
        public final Builder videoItems(Iterable<? extends PlaybackTemplate> iterable) {
            this.videoItems.clear();
            return addAllVideoItems(iterable);
        }

        public final Builder addAllVideoItems(Iterable<? extends PlaybackTemplate> iterable) {
            Iterator<? extends PlaybackTemplate> it = iterable.iterator();
            while (it.hasNext()) {
                this.videoItems.add((PlaybackTemplate) ImmutableVideoQueueElement.requireNonNull(it.next(), "videoItems element"));
            }
            return this;
        }

        @JsonProperty("startIndex")
        public final Builder startIndex(@Nullable Integer num) {
            this.startIndex = num;
            return this;
        }

        public ImmutableVideoQueueElement build() {
            return new ImmutableVideoQueueElement(ImmutableVideoQueueElement.createUnmodifiableList(true, this.videoItems), this.startIndex);
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

    private static <T> List<T> createSafeList(Iterable<? extends T> iterable, boolean z, boolean z2) {
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
