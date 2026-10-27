package Touch.WidgetsInterface.v1_0;

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
@Generated(from = "FlexibleTextContainerElement", generator = "Immutables")
public final class ImmutableFlexibleTextContainerElement extends FlexibleTextContainerElement {
    private final String collapseLabelText;

    @Nullable
    private final String content;
    private final String expandLabelText;
    private final int lineCountWhenCollapsed;

    @Nullable
    private final List<TextElement> richContent;

    private ImmutableFlexibleTextContainerElement(Builder builder) {
        int iLineCountWhenCollapsed;
        this.content = builder.content;
        this.richContent = builder.richContent == null ? null : createUnmodifiableList(true, builder.richContent);
        this.expandLabelText = builder.expandLabelText;
        this.collapseLabelText = builder.collapseLabelText;
        if (!builder.lineCountWhenCollapsedIsSet()) {
            iLineCountWhenCollapsed = super.lineCountWhenCollapsed();
        } else {
            iLineCountWhenCollapsed = builder.lineCountWhenCollapsed;
        }
        this.lineCountWhenCollapsed = iLineCountWhenCollapsed;
    }

    private ImmutableFlexibleTextContainerElement(@Nullable String str, @Nullable List<TextElement> list, String str2, String str3, int i) {
        this.content = str;
        this.richContent = list;
        this.expandLabelText = str2;
        this.collapseLabelText = str3;
        this.lineCountWhenCollapsed = i;
    }

    @Override // Touch.WidgetsInterface.v1_0.FlexibleTextContainerElement
    @JsonProperty("content")
    @Nullable
    public String content() {
        return this.content;
    }

    @Override // Touch.WidgetsInterface.v1_0.FlexibleTextContainerElement
    @JsonProperty("richContent")
    @Nullable
    public List<TextElement> richContent() {
        return this.richContent;
    }

    @Override // Touch.WidgetsInterface.v1_0.FlexibleTextContainerElement
    @JsonProperty("expandLabelText")
    public String expandLabelText() {
        return this.expandLabelText;
    }

    @Override // Touch.WidgetsInterface.v1_0.FlexibleTextContainerElement
    @JsonProperty("collapseLabelText")
    public String collapseLabelText() {
        return this.collapseLabelText;
    }

    @Override // Touch.WidgetsInterface.v1_0.FlexibleTextContainerElement
    @JsonProperty("lineCountWhenCollapsed")
    public int lineCountWhenCollapsed() {
        return this.lineCountWhenCollapsed;
    }

    public final ImmutableFlexibleTextContainerElement withContent(@Nullable String str) {
        return equals(this.content, str) ? this : new ImmutableFlexibleTextContainerElement(str, this.richContent, this.expandLabelText, this.collapseLabelText, this.lineCountWhenCollapsed);
    }

    public final ImmutableFlexibleTextContainerElement withRichContent(@Nullable TextElement... textElementArr) {
        if (textElementArr == null) {
            return new ImmutableFlexibleTextContainerElement(this.content, null, this.expandLabelText, this.collapseLabelText, this.lineCountWhenCollapsed);
        }
        return new ImmutableFlexibleTextContainerElement(this.content, Arrays.asList(textElementArr) == null ? null : createUnmodifiableList(false, createSafeList(Arrays.asList(textElementArr), true, false)), this.expandLabelText, this.collapseLabelText, this.lineCountWhenCollapsed);
    }

    public final ImmutableFlexibleTextContainerElement withRichContent(@Nullable Iterable<? extends TextElement> iterable) {
        if (this.richContent == iterable) {
            return this;
        }
        return new ImmutableFlexibleTextContainerElement(this.content, iterable == null ? null : createUnmodifiableList(false, createSafeList(iterable, true, false)), this.expandLabelText, this.collapseLabelText, this.lineCountWhenCollapsed);
    }

    public final ImmutableFlexibleTextContainerElement withExpandLabelText(String str) {
        String str2 = (String) requireNonNull(str, "expandLabelText");
        return this.expandLabelText.equals(str2) ? this : new ImmutableFlexibleTextContainerElement(this.content, this.richContent, str2, this.collapseLabelText, this.lineCountWhenCollapsed);
    }

    public final ImmutableFlexibleTextContainerElement withCollapseLabelText(String str) {
        String str2 = (String) requireNonNull(str, "collapseLabelText");
        return this.collapseLabelText.equals(str2) ? this : new ImmutableFlexibleTextContainerElement(this.content, this.richContent, this.expandLabelText, str2, this.lineCountWhenCollapsed);
    }

    public final ImmutableFlexibleTextContainerElement withLineCountWhenCollapsed(int i) {
        return this.lineCountWhenCollapsed == i ? this : new ImmutableFlexibleTextContainerElement(this.content, this.richContent, this.expandLabelText, this.collapseLabelText, i);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableFlexibleTextContainerElement) && equalTo(0, (ImmutableFlexibleTextContainerElement) obj);
    }

    private boolean equalTo(int i, ImmutableFlexibleTextContainerElement immutableFlexibleTextContainerElement) {
        return equals(this.content, immutableFlexibleTextContainerElement.content) && equals(this.richContent, immutableFlexibleTextContainerElement.richContent) && this.expandLabelText.equals(immutableFlexibleTextContainerElement.expandLabelText) && this.collapseLabelText.equals(immutableFlexibleTextContainerElement.collapseLabelText) && this.lineCountWhenCollapsed == immutableFlexibleTextContainerElement.lineCountWhenCollapsed;
    }

    public int hashCode() {
        int iHashCode = 172192 + hashCode(this.content) + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + hashCode(this.richContent);
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.expandLabelText.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.collapseLabelText.hashCode();
        return iHashCode4 + (iHashCode4 << 5) + this.lineCountWhenCollapsed;
    }

    public String toString() {
        return "FlexibleTextContainerElement{content=" + this.content + ", richContent=" + this.richContent + ", expandLabelText=" + this.expandLabelText + ", collapseLabelText=" + this.collapseLabelText + ", lineCountWhenCollapsed=" + this.lineCountWhenCollapsed + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "FlexibleTextContainerElement", generator = "Immutables")
    static final class Json extends FlexibleTextContainerElement {

        @Nullable
        String collapseLabelText;

        @Nullable
        String content;

        @Nullable
        String expandLabelText;
        int lineCountWhenCollapsed;
        boolean lineCountWhenCollapsedIsSet;

        @Nullable
        List<TextElement> richContent = null;

        Json() {
        }

        @JsonProperty("content")
        public void setContent(@Nullable String str) {
            this.content = str;
        }

        @JsonProperty("richContent")
        public void setRichContent(@Nullable List<TextElement> list) {
            this.richContent = list;
        }

        @JsonProperty("expandLabelText")
        public void setExpandLabelText(String str) {
            this.expandLabelText = str;
        }

        @JsonProperty("collapseLabelText")
        public void setCollapseLabelText(String str) {
            this.collapseLabelText = str;
        }

        @JsonProperty("lineCountWhenCollapsed")
        public void setLineCountWhenCollapsed(int i) {
            this.lineCountWhenCollapsed = i;
            this.lineCountWhenCollapsedIsSet = true;
        }

        @Override // Touch.WidgetsInterface.v1_0.FlexibleTextContainerElement
        public String content() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.FlexibleTextContainerElement
        public List<TextElement> richContent() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.FlexibleTextContainerElement
        public String expandLabelText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.FlexibleTextContainerElement
        public String collapseLabelText() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.WidgetsInterface.v1_0.FlexibleTextContainerElement
        public int lineCountWhenCollapsed() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableFlexibleTextContainerElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.content;
        if (str != null) {
            builder.content(str);
        }
        List<TextElement> list = json.richContent;
        if (list != null) {
            builder.addAllRichContent(list);
        }
        String str2 = json.expandLabelText;
        if (str2 != null) {
            builder.expandLabelText(str2);
        }
        String str3 = json.collapseLabelText;
        if (str3 != null) {
            builder.collapseLabelText(str3);
        }
        if (json.lineCountWhenCollapsedIsSet) {
            builder.lineCountWhenCollapsed(json.lineCountWhenCollapsed);
        }
        return builder.build();
    }

    public static ImmutableFlexibleTextContainerElement copyOf(FlexibleTextContainerElement flexibleTextContainerElement) {
        if (flexibleTextContainerElement instanceof ImmutableFlexibleTextContainerElement) {
            return (ImmutableFlexibleTextContainerElement) flexibleTextContainerElement;
        }
        return builder().from(flexibleTextContainerElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "FlexibleTextContainerElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_COLLAPSE_LABEL_TEXT = 2;
        private static final long INIT_BIT_EXPAND_LABEL_TEXT = 1;
        private static final long OPT_BIT_LINE_COUNT_WHEN_COLLAPSED = 1;

        @Nullable
        private String collapseLabelText;

        @Nullable
        private String content;

        @Nullable
        private String expandLabelText;
        private long initBits;
        private int lineCountWhenCollapsed;
        private long optBits;
        private List<TextElement> richContent;

        private Builder() {
            this.initBits = 3L;
            this.richContent = null;
        }

        public final Builder from(FlexibleTextContainerElement flexibleTextContainerElement) {
            ImmutableFlexibleTextContainerElement.requireNonNull(flexibleTextContainerElement, "instance");
            String strContent = flexibleTextContainerElement.content();
            if (strContent != null) {
                content(strContent);
            }
            List<TextElement> listRichContent = flexibleTextContainerElement.richContent();
            if (listRichContent != null) {
                addAllRichContent(listRichContent);
            }
            expandLabelText(flexibleTextContainerElement.expandLabelText());
            collapseLabelText(flexibleTextContainerElement.collapseLabelText());
            lineCountWhenCollapsed(flexibleTextContainerElement.lineCountWhenCollapsed());
            return this;
        }

        @JsonProperty("content")
        public final Builder content(@Nullable String str) {
            this.content = str;
            return this;
        }

        public final Builder addRichContent(TextElement textElement) {
            if (this.richContent == null) {
                this.richContent = new ArrayList();
            }
            this.richContent.add((TextElement) ImmutableFlexibleTextContainerElement.requireNonNull(textElement, "richContent element"));
            return this;
        }

        public final Builder addRichContent(TextElement... textElementArr) {
            if (this.richContent == null) {
                this.richContent = new ArrayList();
            }
            for (TextElement textElement : textElementArr) {
                this.richContent.add((TextElement) ImmutableFlexibleTextContainerElement.requireNonNull(textElement, "richContent element"));
            }
            return this;
        }

        @JsonProperty("richContent")
        public final Builder richContent(@Nullable Iterable<? extends TextElement> iterable) {
            if (iterable == null) {
                this.richContent = null;
                return this;
            }
            this.richContent = new ArrayList();
            return addAllRichContent(iterable);
        }

        public final Builder addAllRichContent(Iterable<? extends TextElement> iterable) {
            ImmutableFlexibleTextContainerElement.requireNonNull(iterable, "richContent element");
            if (this.richContent == null) {
                this.richContent = new ArrayList();
            }
            Iterator<? extends TextElement> it = iterable.iterator();
            while (it.hasNext()) {
                this.richContent.add((TextElement) ImmutableFlexibleTextContainerElement.requireNonNull(it.next(), "richContent element"));
            }
            return this;
        }

        @JsonProperty("expandLabelText")
        public final Builder expandLabelText(String str) {
            this.expandLabelText = (String) ImmutableFlexibleTextContainerElement.requireNonNull(str, "expandLabelText");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("collapseLabelText")
        public final Builder collapseLabelText(String str) {
            this.collapseLabelText = (String) ImmutableFlexibleTextContainerElement.requireNonNull(str, "collapseLabelText");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("lineCountWhenCollapsed")
        public final Builder lineCountWhenCollapsed(int i) {
            this.lineCountWhenCollapsed = i;
            this.optBits |= 1;
            return this;
        }

        public ImmutableFlexibleTextContainerElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableFlexibleTextContainerElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean lineCountWhenCollapsedIsSet() {
            return (this.optBits & 1) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("expandLabelText");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("collapseLabelText");
            }
            return "Cannot build FlexibleTextContainerElement, some of required attributes are not set " + arrayList;
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
