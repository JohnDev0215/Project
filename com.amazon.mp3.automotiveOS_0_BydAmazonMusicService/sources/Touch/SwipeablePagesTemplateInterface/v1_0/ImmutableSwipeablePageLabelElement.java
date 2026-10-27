package Touch.SwipeablePagesTemplateInterface.v1_0;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import org.immutables.value.Generated;

/* JADX INFO: loaded from: classes.dex */
@SuppressFBWarnings
@ParametersAreNonnullByDefault
@Immutable
@Generated(from = "SwipeablePageLabelElement", generator = "Immutables")
public final class ImmutableSwipeablePageLabelElement extends SwipeablePageLabelElement {

    @Nullable
    private final String icon;
    private final String title;

    private ImmutableSwipeablePageLabelElement(String str, @Nullable String str2) {
        this.title = str;
        this.icon = str2;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePageLabelElement
    @JsonProperty("title")
    public String title() {
        return this.title;
    }

    @Override // Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePageLabelElement
    @JsonProperty("icon")
    @Nullable
    public String icon() {
        return this.icon;
    }

    public final ImmutableSwipeablePageLabelElement withTitle(String str) {
        String str2 = (String) requireNonNull(str, "title");
        return this.title.equals(str2) ? this : new ImmutableSwipeablePageLabelElement(str2, this.icon);
    }

    public final ImmutableSwipeablePageLabelElement withIcon(@Nullable String str) {
        return equals(this.icon, str) ? this : new ImmutableSwipeablePageLabelElement(this.title, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSwipeablePageLabelElement) && equalTo(0, (ImmutableSwipeablePageLabelElement) obj);
    }

    private boolean equalTo(int i, ImmutableSwipeablePageLabelElement immutableSwipeablePageLabelElement) {
        return this.title.equals(immutableSwipeablePageLabelElement.title) && equals(this.icon, immutableSwipeablePageLabelElement.icon);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.title.hashCode() + 5381;
        return iHashCode + (iHashCode << 5) + hashCode(this.icon);
    }

    public String toString() {
        return "SwipeablePageLabelElement{title=" + this.title + ", icon=" + this.icon + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SwipeablePageLabelElement", generator = "Immutables")
    static final class Json extends SwipeablePageLabelElement {

        @Nullable
        String icon;

        @Nullable
        String title;

        Json() {
        }

        @JsonProperty("title")
        public void setTitle(String str) {
            this.title = str;
        }

        @JsonProperty("icon")
        public void setIcon(@Nullable String str) {
            this.icon = str;
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePageLabelElement
        public String title() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePageLabelElement
        public String icon() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSwipeablePageLabelElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.title;
        if (str != null) {
            builder.title(str);
        }
        String str2 = json.icon;
        if (str2 != null) {
            builder.icon(str2);
        }
        return builder.build();
    }

    public static ImmutableSwipeablePageLabelElement copyOf(SwipeablePageLabelElement swipeablePageLabelElement) {
        if (swipeablePageLabelElement instanceof ImmutableSwipeablePageLabelElement) {
            return (ImmutableSwipeablePageLabelElement) swipeablePageLabelElement;
        }
        return builder().from(swipeablePageLabelElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SwipeablePageLabelElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_TITLE = 1;

        @Nullable
        private String icon;
        private long initBits;

        @Nullable
        private String title;

        private Builder() {
            this.initBits = 1L;
        }

        public final Builder from(SwipeablePageLabelElement swipeablePageLabelElement) {
            ImmutableSwipeablePageLabelElement.requireNonNull(swipeablePageLabelElement, "instance");
            title(swipeablePageLabelElement.title());
            String strIcon = swipeablePageLabelElement.icon();
            if (strIcon != null) {
                icon(strIcon);
            }
            return this;
        }

        @JsonProperty("title")
        public final Builder title(String str) {
            this.title = (String) ImmutableSwipeablePageLabelElement.requireNonNull(str, "title");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("icon")
        public final Builder icon(@Nullable String str) {
            this.icon = str;
            return this;
        }

        public ImmutableSwipeablePageLabelElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableSwipeablePageLabelElement(this.title, this.icon);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("title");
            }
            return "Cannot build SwipeablePageLabelElement, some of required attributes are not set " + arrayList;
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
}
