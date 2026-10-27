package Touch.LiveStreamPlayerTemplateInterface.v1_0;

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
@Generated(from = "TitleBarElement", generator = "Immutables")
public final class ImmutableTitleBarElement extends TitleBarElement {
    private final CloseButtonElement close;
    private final OverflowMenuElement overflowMenu;
    private final String title;

    private ImmutableTitleBarElement(String str, OverflowMenuElement overflowMenuElement, CloseButtonElement closeButtonElement) {
        this.title = str;
        this.overflowMenu = overflowMenuElement;
        this.close = closeButtonElement;
    }

    @Override // Touch.LiveStreamPlayerTemplateInterface.v1_0.TitleBarElement
    @JsonProperty("title")
    public String title() {
        return this.title;
    }

    @Override // Touch.LiveStreamPlayerTemplateInterface.v1_0.TitleBarElement
    @JsonProperty("overflowMenu")
    public OverflowMenuElement overflowMenu() {
        return this.overflowMenu;
    }

    @Override // Touch.LiveStreamPlayerTemplateInterface.v1_0.TitleBarElement
    @JsonProperty("close")
    public CloseButtonElement close() {
        return this.close;
    }

    public final ImmutableTitleBarElement withTitle(String str) {
        String str2 = (String) requireNonNull(str, "title");
        return this.title.equals(str2) ? this : new ImmutableTitleBarElement(str2, this.overflowMenu, this.close);
    }

    public final ImmutableTitleBarElement withOverflowMenu(OverflowMenuElement overflowMenuElement) {
        if (this.overflowMenu == overflowMenuElement) {
            return this;
        }
        return new ImmutableTitleBarElement(this.title, (OverflowMenuElement) requireNonNull(overflowMenuElement, "overflowMenu"), this.close);
    }

    public final ImmutableTitleBarElement withClose(CloseButtonElement closeButtonElement) {
        if (this.close == closeButtonElement) {
            return this;
        }
        return new ImmutableTitleBarElement(this.title, this.overflowMenu, (CloseButtonElement) requireNonNull(closeButtonElement, "close"));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableTitleBarElement) && equalTo(0, (ImmutableTitleBarElement) obj);
    }

    private boolean equalTo(int i, ImmutableTitleBarElement immutableTitleBarElement) {
        return this.title.equals(immutableTitleBarElement.title) && this.overflowMenu.equals(immutableTitleBarElement.overflowMenu) && this.close.equals(immutableTitleBarElement.close);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.title.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.overflowMenu.hashCode();
        return iHashCode2 + (iHashCode2 << 5) + this.close.hashCode();
    }

    public String toString() {
        return "TitleBarElement{title=" + this.title + ", overflowMenu=" + this.overflowMenu + ", close=" + this.close + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "TitleBarElement", generator = "Immutables")
    static final class Json extends TitleBarElement {

        @Nullable
        CloseButtonElement close;

        @Nullable
        OverflowMenuElement overflowMenu;

        @Nullable
        String title;

        Json() {
        }

        @JsonProperty("title")
        public void setTitle(String str) {
            this.title = str;
        }

        @JsonProperty("overflowMenu")
        public void setOverflowMenu(OverflowMenuElement overflowMenuElement) {
            this.overflowMenu = overflowMenuElement;
        }

        @JsonProperty("close")
        public void setClose(CloseButtonElement closeButtonElement) {
            this.close = closeButtonElement;
        }

        @Override // Touch.LiveStreamPlayerTemplateInterface.v1_0.TitleBarElement
        public String title() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.LiveStreamPlayerTemplateInterface.v1_0.TitleBarElement
        public OverflowMenuElement overflowMenu() {
            throw new UnsupportedOperationException();
        }

        @Override // Touch.LiveStreamPlayerTemplateInterface.v1_0.TitleBarElement
        public CloseButtonElement close() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableTitleBarElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.title;
        if (str != null) {
            builder.title(str);
        }
        OverflowMenuElement overflowMenuElement = json.overflowMenu;
        if (overflowMenuElement != null) {
            builder.overflowMenu(overflowMenuElement);
        }
        CloseButtonElement closeButtonElement = json.close;
        if (closeButtonElement != null) {
            builder.close(closeButtonElement);
        }
        return builder.build();
    }

    public static ImmutableTitleBarElement copyOf(TitleBarElement titleBarElement) {
        if (titleBarElement instanceof ImmutableTitleBarElement) {
            return (ImmutableTitleBarElement) titleBarElement;
        }
        return builder().from(titleBarElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "TitleBarElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_CLOSE = 4;
        private static final long INIT_BIT_OVERFLOW_MENU = 2;
        private static final long INIT_BIT_TITLE = 1;

        @Nullable
        private CloseButtonElement close;
        private long initBits;

        @Nullable
        private OverflowMenuElement overflowMenu;

        @Nullable
        private String title;

        private Builder() {
            this.initBits = 7L;
        }

        public final Builder from(TitleBarElement titleBarElement) {
            ImmutableTitleBarElement.requireNonNull(titleBarElement, "instance");
            title(titleBarElement.title());
            overflowMenu(titleBarElement.overflowMenu());
            close(titleBarElement.close());
            return this;
        }

        @JsonProperty("title")
        public final Builder title(String str) {
            this.title = (String) ImmutableTitleBarElement.requireNonNull(str, "title");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("overflowMenu")
        public final Builder overflowMenu(OverflowMenuElement overflowMenuElement) {
            this.overflowMenu = (OverflowMenuElement) ImmutableTitleBarElement.requireNonNull(overflowMenuElement, "overflowMenu");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("close")
        public final Builder close(CloseButtonElement closeButtonElement) {
            this.close = (CloseButtonElement) ImmutableTitleBarElement.requireNonNull(closeButtonElement, "close");
            this.initBits &= -5;
            return this;
        }

        public ImmutableTitleBarElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableTitleBarElement(this.title, this.overflowMenu, this.close);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("title");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("overflowMenu");
            }
            if ((this.initBits & 4) != 0) {
                arrayList.add("close");
            }
            return "Cannot build TitleBarElement, some of required attributes are not set " + arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }
}
