package Touch.SwipeablePagesTemplateInterface.v1_0;

import CoreInterface.v1_0.Method;
import Touch.SwipeablePagesTemplateInterface.v1_0.ClickableTextElement;
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
@Generated(from = "ClickableTextElement", generator = "Immutables")
public final class ImmutableClickableTextElement extends ClickableTextElement {

    @Nullable
    private final LinkElement link;

    @Nullable
    private final Integer maxLines;
    private final List<Method> onItemSelected;
    private final ClickableTextElement.Style style;
    private final String text;

    private ImmutableClickableTextElement(Builder builder) {
        List<Method> listCreateUnmodifiableList;
        this.text = builder.text;
        this.style = builder.style;
        this.maxLines = builder.maxLines;
        this.link = builder.link;
        if (!builder.onItemSelectedIsSet()) {
            listCreateUnmodifiableList = createUnmodifiableList(false, createSafeList(super.onItemSelected(), true, false));
        } else {
            listCreateUnmodifiableList = createUnmodifiableList(true, builder.onItemSelected);
        }
        this.onItemSelected = listCreateUnmodifiableList;
    }

    private ImmutableClickableTextElement(String str, ClickableTextElement.Style style, @Nullable Integer num, @Nullable LinkElement linkElement, List<Method> list) {
        this.text = str;
        this.style = style;
        this.maxLines = num;
        this.link = linkElement;
        this.onItemSelected = list;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.ClickableTextElement
    @JsonProperty("text")
    public String text() {
        return this.text;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.ClickableTextElement
    @JsonProperty("style")
    public ClickableTextElement.Style style() {
        return this.style;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.ClickableTextElement
    @JsonProperty("maxLines")
    @Nullable
    public Integer maxLines() {
        return this.maxLines;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.ClickableTextElement
    @JsonProperty("link")
    @Nullable
    public LinkElement link() {
        return this.link;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.ClickableTextElement
    @JsonProperty("onItemSelected")
    public List<Method> onItemSelected() {
        return this.onItemSelected;
    }

    public final ImmutableClickableTextElement withText(String str) {
        String str2 = (String) requireNonNull(str, "text");
        return this.text.equals(str2) ? this : new ImmutableClickableTextElement(str2, this.style, this.maxLines, this.link, this.onItemSelected);
    }

    public final ImmutableClickableTextElement withStyle(ClickableTextElement.Style style) {
        ClickableTextElement.Style style2 = (ClickableTextElement.Style) requireNonNull(style, "style");
        return this.style == style2 ? this : new ImmutableClickableTextElement(this.text, style2, this.maxLines, this.link, this.onItemSelected);
    }

    public final ImmutableClickableTextElement withMaxLines(@Nullable Integer num) {
        return equals(this.maxLines, num) ? this : new ImmutableClickableTextElement(this.text, this.style, num, this.link, this.onItemSelected);
    }

    public final ImmutableClickableTextElement withLink(@Nullable LinkElement linkElement) {
        return this.link == linkElement ? this : new ImmutableClickableTextElement(this.text, this.style, this.maxLines, linkElement, this.onItemSelected);
    }

    public final ImmutableClickableTextElement withOnItemSelected(Method... methodArr) {
        return new ImmutableClickableTextElement(this.text, this.style, this.maxLines, this.link, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableClickableTextElement withOnItemSelected(Iterable<? extends Method> iterable) {
        if (this.onItemSelected == iterable) {
            return this;
        }
        return new ImmutableClickableTextElement(this.text, this.style, this.maxLines, this.link, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableClickableTextElement) && equalTo(0, (ImmutableClickableTextElement) obj);
    }

    private boolean equalTo(int i, ImmutableClickableTextElement immutableClickableTextElement) {
        return this.text.equals(immutableClickableTextElement.text) && this.style.equals(immutableClickableTextElement.style) && equals(this.maxLines, immutableClickableTextElement.maxLines) && equals(this.link, immutableClickableTextElement.link) && this.onItemSelected.equals(immutableClickableTextElement.onItemSelected);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.text.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.style.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + hashCode(this.maxLines);
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + hashCode(this.link);
        return iHashCode4 + (iHashCode4 << 5) + this.onItemSelected.hashCode();
    }

    public String toString() {
        return "ClickableTextElement{text=" + this.text + ", style=" + this.style + ", maxLines=" + this.maxLines + ", link=" + this.link + ", onItemSelected=" + this.onItemSelected + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "ClickableTextElement", generator = "Immutables")
    static final class Json extends ClickableTextElement {

        @Nullable
        LinkElement link;

        @Nullable
        Integer maxLines;

        @Nullable
        List<Method> onItemSelected = Collections.emptyList();
        boolean onItemSelectedIsSet;

        @Nullable
        ClickableTextElement.Style style;

        @Nullable
        String text;

        Json() {
        }

        @JsonProperty("text")
        public void setText(String str) {
            this.text = str;
        }

        @JsonProperty("style")
        public void setStyle(ClickableTextElement.Style style) {
            this.style = style;
        }

        @JsonProperty("maxLines")
        public void setMaxLines(@Nullable Integer num) {
            this.maxLines = num;
        }

        @JsonProperty("link")
        public void setLink(@Nullable LinkElement linkElement) {
            this.link = linkElement;
        }

        @JsonProperty("onItemSelected")
        public void setOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
            this.onItemSelectedIsSet = true;
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.ClickableTextElement
        public String text() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.ClickableTextElement
        public ClickableTextElement.Style style() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.ClickableTextElement
        public Integer maxLines() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.ClickableTextElement
        public LinkElement link() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.ClickableTextElement
        public List<Method> onItemSelected() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableClickableTextElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.text;
        if (str != null) {
            builder.text(str);
        }
        ClickableTextElement.Style style = json.style;
        if (style != null) {
            builder.style(style);
        }
        Integer num = json.maxLines;
        if (num != null) {
            builder.maxLines(num);
        }
        LinkElement linkElement = json.link;
        if (linkElement != null) {
            builder.link(linkElement);
        }
        if (json.onItemSelectedIsSet) {
            builder.addAllOnItemSelected(json.onItemSelected);
        }
        return builder.build();
    }

    public static ImmutableClickableTextElement copyOf(ClickableTextElement clickableTextElement) {
        if (clickableTextElement instanceof ImmutableClickableTextElement) {
            return (ImmutableClickableTextElement) clickableTextElement;
        }
        return builder().from(clickableTextElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "ClickableTextElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_STYLE = 2;
        private static final long INIT_BIT_TEXT = 1;
        private static final long OPT_BIT_ON_ITEM_SELECTED = 1;
        private long initBits;

        @Nullable
        private LinkElement link;

        @Nullable
        private Integer maxLines;
        private List<Method> onItemSelected;
        private long optBits;

        @Nullable
        private ClickableTextElement.Style style;

        @Nullable
        private String text;

        private Builder() {
            this.initBits = 3L;
            this.onItemSelected = new ArrayList();
        }

        public final Builder from(ClickableTextElement clickableTextElement) {
            ImmutableClickableTextElement.requireNonNull(clickableTextElement, "instance");
            text(clickableTextElement.text());
            style(clickableTextElement.style());
            Integer numMaxLines = clickableTextElement.maxLines();
            if (numMaxLines != null) {
                maxLines(numMaxLines);
            }
            LinkElement linkElementLink = clickableTextElement.link();
            if (linkElementLink != null) {
                link(linkElementLink);
            }
            addAllOnItemSelected(clickableTextElement.onItemSelected());
            return this;
        }

        @JsonProperty("text")
        public final Builder text(String str) {
            this.text = (String) ImmutableClickableTextElement.requireNonNull(str, "text");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("style")
        public final Builder style(ClickableTextElement.Style style) {
            this.style = (ClickableTextElement.Style) ImmutableClickableTextElement.requireNonNull(style, "style");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("maxLines")
        public final Builder maxLines(@Nullable Integer num) {
            this.maxLines = num;
            return this;
        }

        @JsonProperty("link")
        public final Builder link(@Nullable LinkElement linkElement) {
            this.link = linkElement;
            return this;
        }

        public final Builder addOnItemSelected(Method method) {
            this.onItemSelected.add((Method) ImmutableClickableTextElement.requireNonNull(method, "onItemSelected element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnItemSelected(Method... methodArr) {
            for (Method method : methodArr) {
                this.onItemSelected.add((Method) ImmutableClickableTextElement.requireNonNull(method, "onItemSelected element"));
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
                this.onItemSelected.add((Method) ImmutableClickableTextElement.requireNonNull(it.next(), "onItemSelected element"));
            }
            this.optBits |= 1;
            return this;
        }

        public ImmutableClickableTextElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableClickableTextElement(this);
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
            if ((this.initBits & 2) != 0) {
                arrayList.add("style");
            }
            return "Cannot build ClickableTextElement, some of required attributes are not set " + arrayList;
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
