package Touch.WidgetsInterface.v1_0;

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
@Generated(from = "HTMLTextElement", generator = "Immutables")
public final class ImmutableHTMLTextElement extends HTMLTextElement {
    private final String text;

    private ImmutableHTMLTextElement(String str) {
        this.text = str;
    }

    @Override // Touch.WidgetsInterface.v1_0.TextElement
    @JsonProperty("text")
    public String text() {
        return this.text;
    }

    public final ImmutableHTMLTextElement withText(String str) {
        String str2 = (String) requireNonNull(str, "text");
        return this.text.equals(str2) ? this : new ImmutableHTMLTextElement(str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableHTMLTextElement) && equalTo(0, (ImmutableHTMLTextElement) obj);
    }

    private boolean equalTo(int i, ImmutableHTMLTextElement immutableHTMLTextElement) {
        return this.text.equals(immutableHTMLTextElement.text);
    }

    public int hashCode() {
        return 172192 + this.text.hashCode() + 5381;
    }

    public String toString() {
        return "HTMLTextElement{text=" + this.text + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "HTMLTextElement", generator = "Immutables")
    static final class Json extends HTMLTextElement {

        @Nullable
        String text;

        Json() {
        }

        @JsonProperty("text")
        public void setText(String str) {
            this.text = str;
        }

        @Override // Touch.WidgetsInterface.v1_0.TextElement
        public String text() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableHTMLTextElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.text;
        if (str != null) {
            builder.text(str);
        }
        return builder.build();
    }

    public static ImmutableHTMLTextElement copyOf(HTMLTextElement hTMLTextElement) {
        if (hTMLTextElement instanceof ImmutableHTMLTextElement) {
            return (ImmutableHTMLTextElement) hTMLTextElement;
        }
        return builder().from(hTMLTextElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "HTMLTextElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_TEXT = 1;
        private long initBits;

        @Nullable
        private String text;

        private Builder() {
            this.initBits = 1L;
        }

        public final Builder from(TextElement textElement) {
            ImmutableHTMLTextElement.requireNonNull(textElement, "instance");
            from((Object) textElement);
            return this;
        }

        public final Builder from(HTMLTextElement hTMLTextElement) {
            ImmutableHTMLTextElement.requireNonNull(hTMLTextElement, "instance");
            from((Object) hTMLTextElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof TextElement) {
                text(((TextElement) obj).text());
                j = 1;
            } else {
                j = 0;
            }
            if (obj instanceof HTMLTextElement) {
                HTMLTextElement hTMLTextElement = (HTMLTextElement) obj;
                if ((j & 1) == 0) {
                    text(hTMLTextElement.text());
                }
            }
        }

        @JsonProperty("text")
        public final Builder text(String str) {
            this.text = (String) ImmutableHTMLTextElement.requireNonNull(str, "text");
            this.initBits &= -2;
            return this;
        }

        public ImmutableHTMLTextElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableHTMLTextElement(this.text);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("text");
            }
            return "Cannot build HTMLTextElement, some of required attributes are not set " + arrayList;
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
