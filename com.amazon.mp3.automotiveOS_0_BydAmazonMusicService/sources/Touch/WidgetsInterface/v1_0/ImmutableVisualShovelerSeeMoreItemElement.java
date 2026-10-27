package Touch.WidgetsInterface.v1_0;

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
@Generated(from = "VisualShovelerSeeMoreItemElement", generator = "Immutables")
public final class ImmutableVisualShovelerSeeMoreItemElement extends VisualShovelerSeeMoreItemElement {

    @Nullable
    private final String icon;
    private final List<Method> onItemSelected;
    private final String text;

    private ImmutableVisualShovelerSeeMoreItemElement(Builder builder) {
        List<Method> listCreateUnmodifiableList;
        this.text = builder.text;
        this.icon = builder.icon;
        if (!builder.onItemSelectedIsSet()) {
            listCreateUnmodifiableList = createUnmodifiableList(false, createSafeList(super.onItemSelected(), true, false));
        } else {
            listCreateUnmodifiableList = createUnmodifiableList(true, builder.onItemSelected);
        }
        this.onItemSelected = listCreateUnmodifiableList;
    }

    private ImmutableVisualShovelerSeeMoreItemElement(String str, @Nullable String str2, List<Method> list) {
        this.text = str;
        this.icon = str2;
        this.onItemSelected = list;
    }

    @Override // Touch.WidgetsInterface.v1_0.SeeMoreItemElement
    @JsonProperty("text")
    public String text() {
        return this.text;
    }

    @Override // Touch.WidgetsInterface.v1_0.SeeMoreItemElement
    @JsonProperty("icon")
    @Nullable
    public String icon() {
        return this.icon;
    }

    @Override // Touch.WidgetsInterface.v1_0.SeeMoreItemElement
    @JsonProperty("onItemSelected")
    public List<Method> onItemSelected() {
        return this.onItemSelected;
    }

    public final ImmutableVisualShovelerSeeMoreItemElement withText(String str) {
        String str2 = (String) requireNonNull(str, "text");
        return this.text.equals(str2) ? this : new ImmutableVisualShovelerSeeMoreItemElement(str2, this.icon, this.onItemSelected);
    }

    public final ImmutableVisualShovelerSeeMoreItemElement withIcon(@Nullable String str) {
        return equals(this.icon, str) ? this : new ImmutableVisualShovelerSeeMoreItemElement(this.text, str, this.onItemSelected);
    }

    public final ImmutableVisualShovelerSeeMoreItemElement withOnItemSelected(Method... methodArr) {
        return new ImmutableVisualShovelerSeeMoreItemElement(this.text, this.icon, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableVisualShovelerSeeMoreItemElement withOnItemSelected(Iterable<? extends Method> iterable) {
        if (this.onItemSelected == iterable) {
            return this;
        }
        return new ImmutableVisualShovelerSeeMoreItemElement(this.text, this.icon, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableVisualShovelerSeeMoreItemElement) && equalTo(0, (ImmutableVisualShovelerSeeMoreItemElement) obj);
    }

    private boolean equalTo(int i, ImmutableVisualShovelerSeeMoreItemElement immutableVisualShovelerSeeMoreItemElement) {
        return this.text.equals(immutableVisualShovelerSeeMoreItemElement.text) && equals(this.icon, immutableVisualShovelerSeeMoreItemElement.icon) && this.onItemSelected.equals(immutableVisualShovelerSeeMoreItemElement.onItemSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.text.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + hashCode(this.icon);
        return iHashCode2 + (iHashCode2 << 5) + this.onItemSelected.hashCode();
    }

    public String toString() {
        return "VisualShovelerSeeMoreItemElement{text=" + this.text + ", icon=" + this.icon + ", onItemSelected=" + this.onItemSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "VisualShovelerSeeMoreItemElement", generator = "Immutables")
    static final class Json extends VisualShovelerSeeMoreItemElement {

        @Nullable
        String icon;

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

        @JsonProperty("icon")
        public void setIcon(@Nullable String str) {
            this.icon = str;
        }

        @JsonProperty("onItemSelected")
        public void setOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
            this.onItemSelectedIsSet = true;
        }

        @Override // Touch.WidgetsInterface.v1_0.SeeMoreItemElement
        public String text() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.SeeMoreItemElement
        public String icon() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.SeeMoreItemElement
        public List<Method> onItemSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableVisualShovelerSeeMoreItemElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.text;
        if (str != null) {
            builder.text(str);
        }
        String str2 = json.icon;
        if (str2 != null) {
            builder.icon(str2);
        }
        if (json.onItemSelectedIsSet) {
            builder.addAllOnItemSelected(json.onItemSelected);
        }
        return builder.build();
    }

    public static ImmutableVisualShovelerSeeMoreItemElement copyOf(VisualShovelerSeeMoreItemElement visualShovelerSeeMoreItemElement) {
        if (visualShovelerSeeMoreItemElement instanceof ImmutableVisualShovelerSeeMoreItemElement) {
            return (ImmutableVisualShovelerSeeMoreItemElement) visualShovelerSeeMoreItemElement;
        }
        return builder().from(visualShovelerSeeMoreItemElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "VisualShovelerSeeMoreItemElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_TEXT = 1;
        private static final long OPT_BIT_ON_ITEM_SELECTED = 1;

        @Nullable
        private String icon;
        private long initBits;
        private List<Method> onItemSelected;
        private long optBits;

        @Nullable
        private String text;

        private Builder() {
            this.initBits = 1L;
            this.onItemSelected = new ArrayList();
        }

        public final Builder from(SeeMoreItemElement seeMoreItemElement) {
            ImmutableVisualShovelerSeeMoreItemElement.requireNonNull(seeMoreItemElement, "instance");
            from((Object) seeMoreItemElement);
            return this;
        }

        public final Builder from(VisualShovelerSeeMoreItemElement visualShovelerSeeMoreItemElement) {
            ImmutableVisualShovelerSeeMoreItemElement.requireNonNull(visualShovelerSeeMoreItemElement, "instance");
            from((Object) visualShovelerSeeMoreItemElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof SeeMoreItemElement) {
                SeeMoreItemElement seeMoreItemElement = (SeeMoreItemElement) obj;
                addAllOnItemSelected(seeMoreItemElement.onItemSelected());
                String strIcon = seeMoreItemElement.icon();
                if (strIcon != null) {
                    icon(strIcon);
                }
                text(seeMoreItemElement.text());
                j = 7;
            } else {
                j = 0;
            }
            if (obj instanceof VisualShovelerSeeMoreItemElement) {
                VisualShovelerSeeMoreItemElement visualShovelerSeeMoreItemElement = (VisualShovelerSeeMoreItemElement) obj;
                if ((j & 1) == 0) {
                    addAllOnItemSelected(visualShovelerSeeMoreItemElement.onItemSelected());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    String strIcon2 = visualShovelerSeeMoreItemElement.icon();
                    if (strIcon2 != null) {
                        icon(strIcon2);
                    }
                    j |= 2;
                }
                if ((j & 4) == 0) {
                    text(visualShovelerSeeMoreItemElement.text());
                }
            }
        }

        @JsonProperty("text")
        public final Builder text(String str) {
            this.text = (String) ImmutableVisualShovelerSeeMoreItemElement.requireNonNull(str, "text");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("icon")
        public final Builder icon(@Nullable String str) {
            this.icon = str;
            return this;
        }

        public final Builder addOnItemSelected(Method method) {
            this.onItemSelected.add((Method) ImmutableVisualShovelerSeeMoreItemElement.requireNonNull(method, "onItemSelected element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnItemSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onItemSelected.add((Method) ImmutableVisualShovelerSeeMoreItemElement.requireNonNull(method, "onItemSelected element"));
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
                this.onItemSelected.add((Method) ImmutableVisualShovelerSeeMoreItemElement.requireNonNull(it.next(), "onItemSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        public ImmutableVisualShovelerSeeMoreItemElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableVisualShovelerSeeMoreItemElement(this);
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
            return "Cannot build VisualShovelerSeeMoreItemElement, some of required attributes are not set " + arrayList;
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
