package Watch.SearchTemplateInterface.v1_0;

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
@Generated(from = "SearchKeywordClientInformation", generator = "Immutables")
public final class ImmutableSearchKeywordClientInformation extends SearchKeywordClientInformation {
    private final String keyword;

    private ImmutableSearchKeywordClientInformation(String str) {
        this.keyword = str;
    }

    @Override // Watch.SearchTemplateInterface.v1_0.SearchKeywordClientInformation
    @JsonProperty("keyword")
    public String keyword() {
        return this.keyword;
    }

    public final ImmutableSearchKeywordClientInformation withKeyword(String str) {
        String str2 = (String) requireNonNull(str, "keyword");
        return this.keyword.equals(str2) ? this : new ImmutableSearchKeywordClientInformation(str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSearchKeywordClientInformation) && equalTo(0, (ImmutableSearchKeywordClientInformation) obj);
    }

    private boolean equalTo(int i, ImmutableSearchKeywordClientInformation immutableSearchKeywordClientInformation) {
        return this.keyword.equals(immutableSearchKeywordClientInformation.keyword);
    }

    public int hashCode() {
        return 172192 + this.keyword.hashCode() + 5381;
    }

    public String toString() {
        return "SearchKeywordClientInformation{keyword=" + this.keyword + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SearchKeywordClientInformation", generator = "Immutables")
    static final class Json extends SearchKeywordClientInformation {

        @Nullable
        String keyword;

        Json() {
        }

        @JsonProperty("keyword")
        public void setKeyword(String str) {
            this.keyword = str;
        }

        @Override // Watch.SearchTemplateInterface.v1_0.SearchKeywordClientInformation
        public String keyword() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSearchKeywordClientInformation fromJson(Json json) {
        Builder builder = builder();
        String str = json.keyword;
        if (str != null) {
            builder.keyword(str);
        }
        return builder.build();
    }

    public static ImmutableSearchKeywordClientInformation copyOf(SearchKeywordClientInformation searchKeywordClientInformation) {
        if (searchKeywordClientInformation instanceof ImmutableSearchKeywordClientInformation) {
            return (ImmutableSearchKeywordClientInformation) searchKeywordClientInformation;
        }
        return builder().from(searchKeywordClientInformation).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SearchKeywordClientInformation", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_KEYWORD = 1;
        private long initBits;

        @Nullable
        private String keyword;

        private Builder() {
            this.initBits = 1L;
        }

        public final Builder from(SearchKeywordClientInformation searchKeywordClientInformation) {
            ImmutableSearchKeywordClientInformation.requireNonNull(searchKeywordClientInformation, "instance");
            keyword(searchKeywordClientInformation.keyword());
            return this;
        }

        @JsonProperty("keyword")
        public final Builder keyword(String str) {
            this.keyword = (String) ImmutableSearchKeywordClientInformation.requireNonNull(str, "keyword");
            this.initBits &= -2;
            return this;
        }

        public ImmutableSearchKeywordClientInformation build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableSearchKeywordClientInformation(this.keyword);
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("keyword");
            }
            return "Cannot build SearchKeywordClientInformation, some of required attributes are not set " + arrayList;
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
