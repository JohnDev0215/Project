package Watch.ListTemplateInterface.v1_0;

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
@Generated(from = "SectionElement", generator = "Immutables")
public final class ImmutableSectionElement extends SectionElement {

    @Nullable
    private final String header;
    private final List<ListItemElement> items;

    private ImmutableSectionElement(@Nullable String str, List<ListItemElement> list) {
        this.header = str;
        this.items = list;
    }

    @Override // Watch.ListTemplateInterface.v1_0.SectionElement
    @JsonProperty("header")
    @Nullable
    public String header() {
        return this.header;
    }

    @Override // Watch.ListTemplateInterface.v1_0.SectionElement
    @JsonProperty(ContextMappingConstants.ITEMS)
    public List<ListItemElement> items() {
        return this.items;
    }

    public final ImmutableSectionElement withHeader(@Nullable String str) {
        return equals(this.header, str) ? this : new ImmutableSectionElement(str, this.items);
    }

    public final ImmutableSectionElement withItems(ListItemElement... listItemElementArr) {
        return new ImmutableSectionElement(this.header, createUnmodifiableList(false, createSafeList(Arrays.asList(listItemElementArr), true, false)));
    }

    public final ImmutableSectionElement withItems(Iterable<? extends ListItemElement> iterable) {
        if (this.items == iterable) {
            return this;
        }
        return new ImmutableSectionElement(this.header, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSectionElement) && equalTo(0, (ImmutableSectionElement) obj);
    }

    private boolean equalTo(int i, ImmutableSectionElement immutableSectionElement) {
        return equals(this.header, immutableSectionElement.header) && this.items.equals(immutableSectionElement.items);
    }

    public int hashCode() {
        int iHashCode = 172192 + hashCode(this.header) + 5381;
        return iHashCode + (iHashCode << 5) + this.items.hashCode();
    }

    public String toString() {
        return "SectionElement{header=" + this.header + ", items=" + this.items + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SectionElement", generator = "Immutables")
    static final class Json extends SectionElement {

        @Nullable
        String header;

        @Nullable
        List<ListItemElement> items = Collections.emptyList();

        Json() {
        }

        @JsonProperty("header")
        public void setHeader(@Nullable String str) {
            this.header = str;
        }

        @JsonProperty(ContextMappingConstants.ITEMS)
        public void setItems(List<ListItemElement> list) {
            this.items = list;
        }

        @Override // Watch.ListTemplateInterface.v1_0.SectionElement
        public String header() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.ListTemplateInterface.v1_0.SectionElement
        public List<ListItemElement> items() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSectionElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.header;
        if (str != null) {
            builder.header(str);
        }
        List<ListItemElement> list = json.items;
        if (list != null) {
            builder.addAllItems(list);
        }
        return builder.build();
    }

    public static ImmutableSectionElement copyOf(SectionElement sectionElement) {
        if (sectionElement instanceof ImmutableSectionElement) {
            return (ImmutableSectionElement) sectionElement;
        }
        return builder().from(sectionElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SectionElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {

        @Nullable
        private String header;
        private List<ListItemElement> items;

        private Builder() {
            this.items = new ArrayList();
        }

        public final Builder from(SectionElement sectionElement) {
            ImmutableSectionElement.requireNonNull(sectionElement, "instance");
            String strHeader = sectionElement.header();
            if (strHeader != null) {
                header(strHeader);
            }
            addAllItems(sectionElement.items());
            return this;
        }

        @JsonProperty("header")
        public final Builder header(@Nullable String str) {
            this.header = str;
            return this;
        }

        public final Builder addItems(ListItemElement listItemElement) {
            this.items.add((ListItemElement) ImmutableSectionElement.requireNonNull(listItemElement, "items element"));
            return this;
        }

        public final Builder addItems(ListItemElement... listItemElementArr) {
            for (ListItemElement listItemElement : listItemElementArr) {
                this.items.add((ListItemElement) ImmutableSectionElement.requireNonNull(listItemElement, "items element"));
            }
            return this;
        }

        @JsonProperty(ContextMappingConstants.ITEMS)
        public final Builder items(Iterable<? extends ListItemElement> iterable) {
            this.items.clear();
            return addAllItems(iterable);
        }

        public final Builder addAllItems(Iterable<? extends ListItemElement> iterable) {
            Iterator<? extends ListItemElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.items.add((ListItemElement) ImmutableSectionElement.requireNonNull(it.next(), "items element"));
            }
            return this;
        }

        public ImmutableSectionElement build() {
            return new ImmutableSectionElement(this.header, ImmutableSectionElement.createUnmodifiableList(true, this.items));
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
