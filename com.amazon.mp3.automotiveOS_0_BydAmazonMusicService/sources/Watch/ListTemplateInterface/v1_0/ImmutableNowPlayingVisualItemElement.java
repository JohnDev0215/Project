package Watch.ListTemplateInterface.v1_0;

import CoreInterface.v1_0.Method;
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
@Generated(from = "NowPlayingVisualItemElement", generator = "Immutables")
public final class ImmutableNowPlayingVisualItemElement extends NowPlayingVisualItemElement {

    @Nullable
    private final VisualItemElement defaultNoPlaybackItem;
    private final List<Method> onItemSelected;

    private ImmutableNowPlayingVisualItemElement(@Nullable VisualItemElement visualItemElement, List<Method> list) {
        this.defaultNoPlaybackItem = visualItemElement;
        this.onItemSelected = list;
    }

    @Override // Watch.ListTemplateInterface.v1_0.NowPlayingVisualItemElement
    @JsonProperty("defaultNoPlaybackItem")
    @Nullable
    public VisualItemElement defaultNoPlaybackItem() {
        return this.defaultNoPlaybackItem;
    }

    @Override // Watch.ListTemplateInterface.v1_0.NowPlayingVisualItemElement
    @JsonProperty("onItemSelected")
    public List<Method> onItemSelected() {
        return this.onItemSelected;
    }

    public final ImmutableNowPlayingVisualItemElement withDefaultNoPlaybackItem(@Nullable VisualItemElement visualItemElement) {
        return this.defaultNoPlaybackItem == visualItemElement ? this : new ImmutableNowPlayingVisualItemElement(visualItemElement, this.onItemSelected);
    }

    public final ImmutableNowPlayingVisualItemElement withOnItemSelected(Method... methodArr) {
        return new ImmutableNowPlayingVisualItemElement(this.defaultNoPlaybackItem, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableNowPlayingVisualItemElement withOnItemSelected(Iterable<? extends Method> iterable) {
        if (this.onItemSelected == iterable) {
            return this;
        }
        return new ImmutableNowPlayingVisualItemElement(this.defaultNoPlaybackItem, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableNowPlayingVisualItemElement) && equalTo(0, (ImmutableNowPlayingVisualItemElement) obj);
    }

    private boolean equalTo(int i, ImmutableNowPlayingVisualItemElement immutableNowPlayingVisualItemElement) {
        return equals(this.defaultNoPlaybackItem, immutableNowPlayingVisualItemElement.defaultNoPlaybackItem) && this.onItemSelected.equals(immutableNowPlayingVisualItemElement.onItemSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + hashCode(this.defaultNoPlaybackItem) + 5381;
        return iHashCode + (iHashCode << 5) + this.onItemSelected.hashCode();
    }

    public String toString() {
        return "NowPlayingVisualItemElement{defaultNoPlaybackItem=" + this.defaultNoPlaybackItem + ", onItemSelected=" + this.onItemSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "NowPlayingVisualItemElement", generator = "Immutables")
    static final class Json extends NowPlayingVisualItemElement {

        @Nullable
        VisualItemElement defaultNoPlaybackItem;

        @Nullable
        List<Method> onItemSelected = Collections.emptyList();

        Json() {
        }

        @JsonProperty("defaultNoPlaybackItem")
        public void setDefaultNoPlaybackItem(@Nullable VisualItemElement visualItemElement) {
            this.defaultNoPlaybackItem = visualItemElement;
        }

        @JsonProperty("onItemSelected")
        public void setOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
        }

        @Override // Watch.ListTemplateInterface.v1_0.NowPlayingVisualItemElement
        public VisualItemElement defaultNoPlaybackItem() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.ListTemplateInterface.v1_0.NowPlayingVisualItemElement
        public List<Method> onItemSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableNowPlayingVisualItemElement fromJson(Json json) {
        Builder builder = builder();
        VisualItemElement visualItemElement = json.defaultNoPlaybackItem;
        if (visualItemElement != null) {
            builder.defaultNoPlaybackItem(visualItemElement);
        }
        List<Method> list = json.onItemSelected;
        if (list != null) {
            builder.addAllOnItemSelected(list);
        }
        return builder.build();
    }

    public static ImmutableNowPlayingVisualItemElement copyOf(NowPlayingVisualItemElement nowPlayingVisualItemElement) {
        if (nowPlayingVisualItemElement instanceof ImmutableNowPlayingVisualItemElement) {
            return (ImmutableNowPlayingVisualItemElement) nowPlayingVisualItemElement;
        }
        return builder().from(nowPlayingVisualItemElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "NowPlayingVisualItemElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {

        @Nullable
        private VisualItemElement defaultNoPlaybackItem;
        private List<Method> onItemSelected;

        private Builder() {
            this.onItemSelected = new ArrayList();
        }

        public final Builder from(NowPlayingVisualItemElement nowPlayingVisualItemElement) {
            ImmutableNowPlayingVisualItemElement.requireNonNull(nowPlayingVisualItemElement, "instance");
            VisualItemElement visualItemElementDefaultNoPlaybackItem = nowPlayingVisualItemElement.defaultNoPlaybackItem();
            if (visualItemElementDefaultNoPlaybackItem != null) {
                defaultNoPlaybackItem(visualItemElementDefaultNoPlaybackItem);
            }
            addAllOnItemSelected(nowPlayingVisualItemElement.onItemSelected());
            return this;
        }

        @JsonProperty("defaultNoPlaybackItem")
        public final Builder defaultNoPlaybackItem(@Nullable VisualItemElement visualItemElement) {
            this.defaultNoPlaybackItem = visualItemElement;
            return this;
        }

        public final Builder addOnItemSelected(Method method) {
            this.onItemSelected.add((Method) ImmutableNowPlayingVisualItemElement.requireNonNull(method, "onItemSelected element"));
            return this;
        }

        public final Builder addOnItemSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onItemSelected.add((Method) ImmutableNowPlayingVisualItemElement.requireNonNull(method, "onItemSelected element"));
            }
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
                this.onItemSelected.add((Method) ImmutableNowPlayingVisualItemElement.requireNonNull(it.next(), "onItemSelected element"));
            }
            return this;
        }

        public ImmutableNowPlayingVisualItemElement build() {
            return new ImmutableNowPlayingVisualItemElement(this.defaultNoPlaybackItem, ImmutableNowPlayingVisualItemElement.createUnmodifiableList(true, this.onItemSelected));
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
