package Watch.ListTemplateInterface.v1_0;

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
@Generated(from = "HorizontalIconButtonsListItemElement", generator = "Immutables")
public final class ImmutableHorizontalIconButtonsListItemElement extends HorizontalIconButtonsListItemElement {
    private final List<IconButtonElement> icons;

    private ImmutableHorizontalIconButtonsListItemElement(List<IconButtonElement> list) {
        this.icons = list;
    }

    @Override // Watch.ListTemplateInterface.v1_0.HorizontalIconButtonsListItemElement
    @JsonProperty("icons")
    public List<IconButtonElement> icons() {
        return this.icons;
    }

    public final ImmutableHorizontalIconButtonsListItemElement withIcons(IconButtonElement... iconButtonElementArr) {
        return new ImmutableHorizontalIconButtonsListItemElement(createUnmodifiableList(false, createSafeList(Arrays.asList(iconButtonElementArr), true, false)));
    }

    public final ImmutableHorizontalIconButtonsListItemElement withIcons(Iterable<? extends IconButtonElement> iterable) {
        return this.icons == iterable ? this : new ImmutableHorizontalIconButtonsListItemElement(createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableHorizontalIconButtonsListItemElement) && equalTo(0, (ImmutableHorizontalIconButtonsListItemElement) obj);
    }

    private boolean equalTo(int i, ImmutableHorizontalIconButtonsListItemElement immutableHorizontalIconButtonsListItemElement) {
        return this.icons.equals(immutableHorizontalIconButtonsListItemElement.icons);
    }

    public int hashCode() {
        return 172192 + this.icons.hashCode() + 5381;
    }

    public String toString() {
        return "HorizontalIconButtonsListItemElement{icons=" + this.icons + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "HorizontalIconButtonsListItemElement", generator = "Immutables")
    static final class Json extends HorizontalIconButtonsListItemElement {

        @Nullable
        List<IconButtonElement> icons = Collections.emptyList();

        Json() {
        }

        @JsonProperty("icons")
        public void setIcons(List<IconButtonElement> list) {
            this.icons = list;
        }

        @Override // Watch.ListTemplateInterface.v1_0.HorizontalIconButtonsListItemElement
        public List<IconButtonElement> icons() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableHorizontalIconButtonsListItemElement fromJson(Json json) {
        Builder builder = builder();
        List<IconButtonElement> list = json.icons;
        if (list != null) {
            builder.addAllIcons(list);
        }
        return builder.build();
    }

    public static ImmutableHorizontalIconButtonsListItemElement copyOf(HorizontalIconButtonsListItemElement horizontalIconButtonsListItemElement) {
        if (horizontalIconButtonsListItemElement instanceof ImmutableHorizontalIconButtonsListItemElement) {
            return (ImmutableHorizontalIconButtonsListItemElement) horizontalIconButtonsListItemElement;
        }
        return builder().from(horizontalIconButtonsListItemElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "HorizontalIconButtonsListItemElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private List<IconButtonElement> icons;

        private Builder() {
            this.icons = new ArrayList();
        }

        public final Builder from(HorizontalIconButtonsListItemElement horizontalIconButtonsListItemElement) {
            ImmutableHorizontalIconButtonsListItemElement.requireNonNull(horizontalIconButtonsListItemElement, "instance");
            addAllIcons(horizontalIconButtonsListItemElement.icons());
            return this;
        }

        public final Builder addIcons(IconButtonElement iconButtonElement) {
            this.icons.add((IconButtonElement) ImmutableHorizontalIconButtonsListItemElement.requireNonNull(iconButtonElement, "icons element"));
            return this;
        }

        public final Builder addIcons(IconButtonElement... iconButtonElementArr) {
            for (IconButtonElement iconButtonElement : iconButtonElementArr) {
                this.icons.add((IconButtonElement) ImmutableHorizontalIconButtonsListItemElement.requireNonNull(iconButtonElement, "icons element"));
            }
            return this;
        }

        @JsonProperty("icons")
        public final Builder icons(Iterable<? extends IconButtonElement> iterable) {
            this.icons.clear();
            return addAllIcons(iterable);
        }

        public final Builder addAllIcons(Iterable<? extends IconButtonElement> iterable) {
            Iterator<? extends IconButtonElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.icons.add((IconButtonElement) ImmutableHorizontalIconButtonsListItemElement.requireNonNull(it.next(), "icons element"));
            }
            return this;
        }

        public ImmutableHorizontalIconButtonsListItemElement build() {
            return new ImmutableHorizontalIconButtonsListItemElement(ImmutableHorizontalIconButtonsListItemElement.createUnmodifiableList(true, this.icons));
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
