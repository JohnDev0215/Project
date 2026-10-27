package Podcast.Web.WidgetsInterface;

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
@Generated(from = "BorderedBadgeElement", generator = "Immutables")
public final class ImmutableBorderedBadgeElement extends BorderedBadgeElement {
    private final List<Method> onItemSelected;
    private final String text;

    private ImmutableBorderedBadgeElement(Builder builder) {
        List<Method> listCreateUnmodifiableList;
        this.text = builder.text;
        if (!builder.onItemSelectedIsSet()) {
            listCreateUnmodifiableList = createUnmodifiableList(false, createSafeList(super.onItemSelected(), true, false));
        } else {
            listCreateUnmodifiableList = createUnmodifiableList(true, builder.onItemSelected);
        }
        this.onItemSelected = listCreateUnmodifiableList;
    }

    private ImmutableBorderedBadgeElement(String str, List<Method> list) {
        this.text = str;
        this.onItemSelected = list;
    }

    @Override // Podcast.Web.WidgetsInterface.BadgeElement
    @JsonProperty("text")
    public String text() {
        return this.text;
    }

    @Override // Podcast.Web.WidgetsInterface.BadgeElement
    @JsonProperty("onItemSelected")
    public List<Method> onItemSelected() {
        return this.onItemSelected;
    }

    public final ImmutableBorderedBadgeElement withText(String str) {
        String str2 = (String) requireNonNull(str, "text");
        return this.text.equals(str2) ? this : new ImmutableBorderedBadgeElement(str2, this.onItemSelected);
    }

    public final ImmutableBorderedBadgeElement withOnItemSelected(Method... methodArr) {
        return new ImmutableBorderedBadgeElement(this.text, (List<Method>) createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableBorderedBadgeElement withOnItemSelected(Iterable<? extends Method> iterable) {
        if (this.onItemSelected == iterable) {
            return this;
        }
        return new ImmutableBorderedBadgeElement(this.text, (List<Method>) createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableBorderedBadgeElement) && equalTo(0, (ImmutableBorderedBadgeElement) obj);
    }

    private boolean equalTo(int i, ImmutableBorderedBadgeElement immutableBorderedBadgeElement) {
        return this.text.equals(immutableBorderedBadgeElement.text) && this.onItemSelected.equals(immutableBorderedBadgeElement.onItemSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.text.hashCode() + 5381;
        return iHashCode + (iHashCode << 5) + this.onItemSelected.hashCode();
    }

    public String toString() {
        return "BorderedBadgeElement{text=" + this.text + ", onItemSelected=" + this.onItemSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "BorderedBadgeElement", generator = "Immutables")
    static final class Json extends BorderedBadgeElement {

        @Nullable
        List<Method> onItemSelected = Collections.emptyList();
        boolean onItemSelectedIsSet;

        @Nullable
        String text;

        Json() {
        }

        @JsonProperty("text")
        public void setText(String str) {
            this.text = str;
        }

        @JsonProperty("onItemSelected")
        public void setOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
            this.onItemSelectedIsSet = true;
        }

        @Override // Podcast.Web.WidgetsInterface.BadgeElement
        public String text() {
            throw new UnsupportedOperationException();
        }

        @Override // Podcast.Web.WidgetsInterface.BadgeElement
        public List<Method> onItemSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableBorderedBadgeElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.text;
        if (str != null) {
            builder.text(str);
        }
        if (json.onItemSelectedIsSet) {
            builder.addAllOnItemSelected(json.onItemSelected);
        }
        return builder.build();
    }

    public static ImmutableBorderedBadgeElement copyOf(BorderedBadgeElement borderedBadgeElement) {
        if (borderedBadgeElement instanceof ImmutableBorderedBadgeElement) {
            return (ImmutableBorderedBadgeElement) borderedBadgeElement;
        }
        return builder().from(borderedBadgeElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "BorderedBadgeElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_TEXT = 1;
        private static final long OPT_BIT_ON_ITEM_SELECTED = 1;
        private long initBits;
        private List<Method> onItemSelected;
        private long optBits;

        @Nullable
        private String text;

        private Builder() {
            this.initBits = 1L;
            this.onItemSelected = new ArrayList();
        }

        public final Builder from(BorderedBadgeElement borderedBadgeElement) {
            ImmutableBorderedBadgeElement.requireNonNull(borderedBadgeElement, "instance");
            from((Object) borderedBadgeElement);
            return this;
        }

        public final Builder from(BadgeElement badgeElement) {
            ImmutableBorderedBadgeElement.requireNonNull(badgeElement, "instance");
            from((Object) badgeElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof BorderedBadgeElement) {
                BorderedBadgeElement borderedBadgeElement = (BorderedBadgeElement) obj;
                addAllOnItemSelected(borderedBadgeElement.onItemSelected());
                text(borderedBadgeElement.text());
                j = 3;
            } else {
                j = 0;
            }
            if (obj instanceof BadgeElement) {
                BadgeElement badgeElement = (BadgeElement) obj;
                if ((j & 1) == 0) {
                    addAllOnItemSelected(badgeElement.onItemSelected());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    text(badgeElement.text());
                }
            }
        }

        @JsonProperty("text")
        public final Builder text(String str) {
            this.text = (String) ImmutableBorderedBadgeElement.requireNonNull(str, "text");
            this.initBits &= -2;
            return this;
        }

        public final Builder addOnItemSelected(Method method) {
            this.onItemSelected.add((Method) ImmutableBorderedBadgeElement.requireNonNull(method, "onItemSelected element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnItemSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onItemSelected.add((Method) ImmutableBorderedBadgeElement.requireNonNull(method, "onItemSelected element"));
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
                this.onItemSelected.add((Method) ImmutableBorderedBadgeElement.requireNonNull(it.next(), "onItemSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        public ImmutableBorderedBadgeElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableBorderedBadgeElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onItemSelectedIsSet() {
            return (this.optBits & 1) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("text");
            }
            return "Cannot build BorderedBadgeElement, some of required attributes are not set " + arrayList;
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

    private static <T> List<T> createUnmodifiableList(boolean z, List<T> list) {
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
