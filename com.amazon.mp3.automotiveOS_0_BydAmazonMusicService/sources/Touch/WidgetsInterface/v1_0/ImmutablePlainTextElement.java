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
@Generated(from = "PlainTextElement", generator = "Immutables")
public final class ImmutablePlainTextElement extends PlainTextElement {
    private final String text;

    private ImmutablePlainTextElement(String str) {
        this.text = str;
    }

    @Override // Touch.WidgetsInterface.v1_0.TextElement
    @JsonProperty("text")
    public String text() {
        return this.text;
    }

    public final ImmutablePlainTextElement withText(String str) {
        String str2 = (String) requireNonNull(str, "text");
        return this.text.equals(str2) ? this : new ImmutablePlainTextElement(str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutablePlainTextElement) && equalTo(0, (ImmutablePlainTextElement) obj);
    }

    private boolean equalTo(int i, ImmutablePlainTextElement immutablePlainTextElement) {
        return this.text.equals(immutablePlainTextElement.text);
    }

    public int hashCode() {
        return 172192 + this.text.hashCode() + 5381;
    }

    public String toString() {
        return "PlainTextElement{text=" + this.text + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "PlainTextElement", generator = "Immutables")
    static final class Json extends PlainTextElement {

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
    static ImmutablePlainTextElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.text;
        if (str != null) {
            builder.text(str);
        }
        return builder.build();
    }

    public static ImmutablePlainTextElement copyOf(PlainTextElement plainTextElement) {
        if (plainTextElement instanceof ImmutablePlainTextElement) {
            return (ImmutablePlainTextElement) plainTextElement;
        }
        return builder().from(plainTextElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "PlainTextElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_TEXT = 1;
        private long initBits;

        @Nullable
        private String text;

        private Builder() {
            this.initBits = 1L;
        }

        public final Builder from(PlainTextElement plainTextElement) {
            ImmutablePlainTextElement.requireNonNull(plainTextElement, "instance");
            from((Object) plainTextElement);
            return this;
        }

        public final Builder from(TextElement textElement) {
            ImmutablePlainTextElement.requireNonNull(textElement, "instance");
            from((Object) textElement);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof PlainTextElement) {
                text(((PlainTextElement) obj).text());
                j = 1;
            } else {
                j = 0;
            }
            if (obj instanceof TextElement) {
                TextElement textElement = (TextElement) obj;
                if ((j & 1) == 0) {
                    text(textElement.text());
                }
            }
        }

        @JsonProperty("text")
        public final Builder text(String str) {
            this.text = (String) ImmutablePlainTextElement.requireNonNull(str, "text");
            this.initBits &= -2;
            return this;
        }

        public ImmutablePlainTextElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutablePlainTextElement(this.text);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("text");
            }
            return "Cannot build PlainTextElement, some of required attributes are not set " + arrayList;
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
