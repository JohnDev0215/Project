package PlaybackInterface.v1_0;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
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
@Generated(from = "LyricLinesClientInformation", generator = "Immutables")
public final class ImmutableLyricLinesClientInformation extends LyricLinesClientInformation {
    private final List<Integer> viewedLines;

    private ImmutableLyricLinesClientInformation(List<Integer> list) {
        this.viewedLines = list;
    }

    @Override // PlaybackInterface.v1_0.LyricLinesClientInformation
    @JsonProperty("viewedLines")
    public List<Integer> viewedLines() {
        return this.viewedLines;
    }

    public final ImmutableLyricLinesClientInformation withViewedLines(int... iArr) {
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int i : iArr) {
            arrayList.add(Integer.valueOf(i));
        }
        return new ImmutableLyricLinesClientInformation(createUnmodifiableList(false, arrayList));
    }

    public final ImmutableLyricLinesClientInformation withViewedLines(Iterable<Integer> iterable) {
        return this.viewedLines == iterable ? this : new ImmutableLyricLinesClientInformation(createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableLyricLinesClientInformation) && equalTo(0, (ImmutableLyricLinesClientInformation) obj);
    }

    private boolean equalTo(int i, ImmutableLyricLinesClientInformation immutableLyricLinesClientInformation) {
        return this.viewedLines.equals(immutableLyricLinesClientInformation.viewedLines);
    }

    public int hashCode() {
        return 172192 + this.viewedLines.hashCode() + 5381;
    }

    public String toString() {
        return "LyricLinesClientInformation{viewedLines=" + this.viewedLines + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "LyricLinesClientInformation", generator = "Immutables")
    static final class Json extends LyricLinesClientInformation {

        @Nullable
        List<Integer> viewedLines = Collections.emptyList();

        Json() {
        }

        @JsonProperty("viewedLines")
        public void setViewedLines(List<Integer> list) {
            this.viewedLines = list;
        }

        @Override // PlaybackInterface.v1_0.LyricLinesClientInformation
        public List<Integer> viewedLines() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableLyricLinesClientInformation fromJson(Json json) {
        Builder builder = builder();
        List<Integer> list = json.viewedLines;
        if (list != null) {
            builder.addAllViewedLines(list);
        }
        return builder.build();
    }

    public static ImmutableLyricLinesClientInformation copyOf(LyricLinesClientInformation lyricLinesClientInformation) {
        if (lyricLinesClientInformation instanceof ImmutableLyricLinesClientInformation) {
            return (ImmutableLyricLinesClientInformation) lyricLinesClientInformation;
        }
        return builder().from(lyricLinesClientInformation).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "LyricLinesClientInformation", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private List<Integer> viewedLines;

        private Builder() {
            this.viewedLines = new ArrayList();
        }

        public final Builder from(LyricLinesClientInformation lyricLinesClientInformation) {
            ImmutableLyricLinesClientInformation.requireNonNull(lyricLinesClientInformation, "instance");
            addAllViewedLines(lyricLinesClientInformation.viewedLines());
            return this;
        }

        public final Builder addViewedLines(int i) {
            this.viewedLines.add(Integer.valueOf(i));
            return this;
        }

        public final Builder addViewedLines(int... iArr) {
            for (int i : iArr) {
                this.viewedLines.add(Integer.valueOf(i));
            }
            return this;
        }

        @JsonProperty("viewedLines")
        public final Builder viewedLines(Iterable<Integer> iterable) {
            this.viewedLines.clear();
            return addAllViewedLines(iterable);
        }

        public final Builder addAllViewedLines(Iterable<Integer> iterable) {
            Iterator<Integer> it = iterable.iterator();
            while (it.hasNext()) {
                this.viewedLines.add((Integer) ImmutableLyricLinesClientInformation.requireNonNull(it.next(), "viewedLines element"));
            }
            return this;
        }

        public ImmutableLyricLinesClientInformation build() {
            return new ImmutableLyricLinesClientInformation(ImmutableLyricLinesClientInformation.createUnmodifiableList(true, this.viewedLines));
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

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> List<T> createUnmodifiableList(boolean z, List<T> list) {
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
