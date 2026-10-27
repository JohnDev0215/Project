package Touch.LiveStreamPlayerTemplateInterface.v1_0;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.android.gms.common.internal.ImagesContract;
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
@Generated(from = "LiveVideoAndChatWebElement", generator = "Immutables")
public final class ImmutableLiveVideoAndChatWebElement extends LiveVideoAndChatWebElement {
    private final String url;

    private ImmutableLiveVideoAndChatWebElement(String str) {
        this.url = str;
    }

    @Override // Touch.LiveStreamPlayerTemplateInterface.v1_0.LiveVideoAndChatWebElement
    @JsonProperty(ImagesContract.URL)
    public String url() {
        return this.url;
    }

    public final ImmutableLiveVideoAndChatWebElement withUrl(String str) {
        String str2 = (String) requireNonNull(str, ImagesContract.URL);
        return this.url.equals(str2) ? this : new ImmutableLiveVideoAndChatWebElement(str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableLiveVideoAndChatWebElement) && equalTo(0, (ImmutableLiveVideoAndChatWebElement) obj);
    }

    private boolean equalTo(int i, ImmutableLiveVideoAndChatWebElement immutableLiveVideoAndChatWebElement) {
        return this.url.equals(immutableLiveVideoAndChatWebElement.url);
    }

    public int hashCode() {
        return 172192 + this.url.hashCode() + 5381;
    }

    public String toString() {
        return "LiveVideoAndChatWebElement{url=" + this.url + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "LiveVideoAndChatWebElement", generator = "Immutables")
    static final class Json extends LiveVideoAndChatWebElement {

        @Nullable
        String url;

        Json() {
        }

        @JsonProperty(ImagesContract.URL)
        public void setUrl(String str) {
            this.url = str;
        }

        @Override // Touch.LiveStreamPlayerTemplateInterface.v1_0.LiveVideoAndChatWebElement
        public String url() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableLiveVideoAndChatWebElement fromJson(Json json) {
        Builder builder = builder();
        String str = json.url;
        if (str != null) {
            builder.url(str);
        }
        return builder.build();
    }

    public static ImmutableLiveVideoAndChatWebElement copyOf(LiveVideoAndChatWebElement liveVideoAndChatWebElement) {
        if (liveVideoAndChatWebElement instanceof ImmutableLiveVideoAndChatWebElement) {
            return (ImmutableLiveVideoAndChatWebElement) liveVideoAndChatWebElement;
        }
        return builder().from(liveVideoAndChatWebElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "LiveVideoAndChatWebElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_URL = 1;
        private long initBits;

        @Nullable
        private String url;

        private Builder() {
            this.initBits = 1L;
        }

        public final Builder from(LiveVideoAndChatWebElement liveVideoAndChatWebElement) {
            ImmutableLiveVideoAndChatWebElement.requireNonNull(liveVideoAndChatWebElement, "instance");
            url(liveVideoAndChatWebElement.url());
            return this;
        }

        @JsonProperty(ImagesContract.URL)
        public final Builder url(String str) {
            this.url = (String) ImmutableLiveVideoAndChatWebElement.requireNonNull(str, ImagesContract.URL);
            this.initBits &= -2;
            return this;
        }

        public ImmutableLiveVideoAndChatWebElement build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableLiveVideoAndChatWebElement(this.url);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add(ImagesContract.URL);
            }
            return "Cannot build LiveVideoAndChatWebElement, some of required attributes are not set " + arrayList;
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
