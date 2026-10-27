package PlaybackInterface.v1_0;

import CoreInterface.v1_0.Method;
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
@Generated(from = "ActiveQueuesElement", generator = "Immutables")
public final class ImmutableActiveQueuesElement extends ActiveQueuesElement {
    private final List<Method> activeDevices;
    private final String subtitle;
    private final String title;

    private ImmutableActiveQueuesElement(String str, String str2, List<Method> list) {
        this.title = str;
        this.subtitle = str2;
        this.activeDevices = list;
    }

    @Override // PlaybackInterface.v1_0.ActiveQueuesElement
    @JsonProperty("title")
    public String title() {
        return this.title;
    }

    @Override // PlaybackInterface.v1_0.ActiveQueuesElement
    @JsonProperty(MediaTrack.ROLE_SUBTITLE)
    public String subtitle() {
        return this.subtitle;
    }

    @Override // PlaybackInterface.v1_0.ActiveQueuesElement
    @JsonProperty("activeDevices")
    public List<Method> activeDevices() {
        return this.activeDevices;
    }

    public final ImmutableActiveQueuesElement withTitle(String str) {
        String str2 = (String) requireNonNull(str, "title");
        return this.title.equals(str2) ? this : new ImmutableActiveQueuesElement(str2, this.subtitle, this.activeDevices);
    }

    public final ImmutableActiveQueuesElement withSubtitle(String str) {
        String str2 = (String) requireNonNull(str, MediaTrack.ROLE_SUBTITLE);
        return this.subtitle.equals(str2) ? this : new ImmutableActiveQueuesElement(this.title, str2, this.activeDevices);
    }

    public final ImmutableActiveQueuesElement withActiveDevices(Method... methodArr) {
        return new ImmutableActiveQueuesElement(this.title, this.subtitle, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableActiveQueuesElement withActiveDevices(Iterable<? extends Method> iterable) {
        if (this.activeDevices == iterable) {
            return this;
        }
        return new ImmutableActiveQueuesElement(this.title, this.subtitle, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableActiveQueuesElement) && equalTo(0, (ImmutableActiveQueuesElement) obj);
    }

    private boolean equalTo(int i, ImmutableActiveQueuesElement immutableActiveQueuesElement) {
        return this.title.equals(immutableActiveQueuesElement.title) && this.subtitle.equals(immutableActiveQueuesElement.subtitle) && this.activeDevices.equals(immutableActiveQueuesElement.activeDevices);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.title.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.subtitle.hashCode();
        return iHashCode2 + (iHashCode2 << 5) + this.activeDevices.hashCode();
    }

    public String toString() {
        return "ActiveQueuesElement{title=" + this.title + ", subtitle=" + this.subtitle + ", activeDevices=" + this.activeDevices + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "ActiveQueuesElement", generator = "Immutables")
    static final class Json extends ActiveQueuesElement {

        @Nullable
        List<Method> activeDevices = Collections.emptyList();

        @Nullable
        String subtitle;

        @Nullable
        String title;

        Json() {
        }

        @JsonProperty("title")
        public void setTitle(String str) {
            this.title = str;
        }

        @JsonProperty(MediaTrack.ROLE_SUBTITLE)
        public void setSubtitle(String str) {
            this.subtitle = str;
        }

        @JsonProperty("activeDevices")
        public void setActiveDevices(List<Method> list) {
            this.activeDevices = list;
        }

        @Override // PlaybackInterface.v1_0.ActiveQueuesElement
        public String title() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.ActiveQueuesElement
        public String subtitle() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.ActiveQueuesElement
        public List<Method> activeDevices() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableActiveQueuesElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.title;
        if (str != null) {
            builder.title(str);
        }
        String str2 = json.subtitle;
        if (str2 != null) {
            builder.subtitle(str2);
        }
        List<Method> list = json.activeDevices;
        if (list != null) {
            builder.addAllActiveDevices(list);
        }
        return builder.build();
    }

    public static ImmutableActiveQueuesElement copyOf(ActiveQueuesElement activeQueuesElement) {
        if (activeQueuesElement instanceof ImmutableActiveQueuesElement) {
            return (ImmutableActiveQueuesElement) activeQueuesElement;
        }
        return builder().from(activeQueuesElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "ActiveQueuesElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_SUBTITLE = 2;
        private static final long INIT_BIT_TITLE = 1;
        private List<Method> activeDevices;
        private long initBits;

        @Nullable
        private String subtitle;

        @Nullable
        private String title;

        private Builder() {
            this.initBits = 3L;
            this.activeDevices = new ArrayList();
        }

        public final Builder from(ActiveQueuesElement activeQueuesElement) {
            ImmutableActiveQueuesElement.requireNonNull(activeQueuesElement, "instance");
            title(activeQueuesElement.title());
            subtitle(activeQueuesElement.subtitle());
            addAllActiveDevices(activeQueuesElement.activeDevices());
            return this;
        }

        @JsonProperty("title")
        public final Builder title(String str) {
            this.title = (String) ImmutableActiveQueuesElement.requireNonNull(str, "title");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty(MediaTrack.ROLE_SUBTITLE)
        public final Builder subtitle(String str) {
            this.subtitle = (String) ImmutableActiveQueuesElement.requireNonNull(str, MediaTrack.ROLE_SUBTITLE);
            this.initBits &= -3;
            return this;
        }

        public final Builder addActiveDevices(Method method) {
            this.activeDevices.add((Method) ImmutableActiveQueuesElement.requireNonNull(method, "activeDevices element"));
            return this;
        }

        public final Builder addActiveDevices(Method... methodArr) {
            for (Method method : methodArr) {
                this.activeDevices.add((Method) ImmutableActiveQueuesElement.requireNonNull(method, "activeDevices element"));
            }
            return this;
        }

        @JsonProperty("activeDevices")
        public final Builder activeDevices(Iterable<? extends Method> iterable) {
            this.activeDevices.clear();
            return addAllActiveDevices(iterable);
        }

        public final Builder addAllActiveDevices(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.activeDevices.add((Method) ImmutableActiveQueuesElement.requireNonNull(it.next(), "activeDevices element"));
            }
            return this;
        }

        public ImmutableActiveQueuesElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableActiveQueuesElement(this.title, this.subtitle, ImmutableActiveQueuesElement.createUnmodifiableList(true, this.activeDevices));
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("title");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add(MediaTrack.ROLE_SUBTITLE);
            }
            return "Cannot build ActiveQueuesElement, some of required attributes are not set " + arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
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
