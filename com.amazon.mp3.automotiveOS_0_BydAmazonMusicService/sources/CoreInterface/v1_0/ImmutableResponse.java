package CoreInterface.v1_0;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Generated(from = "Response", generator = "Immutables")
public final class ImmutableResponse extends Response {
    private final List<Method> methods;

    private ImmutableResponse(List<Method> list) {
        this.methods = list;
    }

    @Override // CoreInterface.v1_0.Response
    @JsonProperty("methods")
    public List<Method> methods() {
        return this.methods;
    }

    public final ImmutableResponse withMethods(Method... methodArr) {
        return new ImmutableResponse(createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableResponse withMethods(Iterable<? extends Method> iterable) {
        return this.methods == iterable ? this : new ImmutableResponse(createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableResponse) && equalTo(0, (ImmutableResponse) obj);
    }

    private boolean equalTo(int i, ImmutableResponse immutableResponse) {
        return this.methods.equals(immutableResponse.methods);
    }

    public int hashCode() {
        return 172192 + this.methods.hashCode() + 5381;
    }

    public String toString() {
        return "Response{methods=" + this.methods + "}";
    }

    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "Response", generator = "Immutables")
    static final class Json extends Response {

        @Nullable
        List<Method> methods = Collections.emptyList();

        Json() {
        }

        @JsonProperty("methods")
        public void setMethods(List<Method> list) {
            this.methods = list;
        }

        @Override // CoreInterface.v1_0.Response
        public List<Method> methods() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableResponse fromJson(Json json) {
        Builder builder = builder();
        List<Method> list = json.methods;
        if (list != null) {
            builder.addAllMethods(list);
        }
        return builder.build();
    }

    public static ImmutableResponse copyOf(Response response) {
        if (response instanceof ImmutableResponse) {
            return (ImmutableResponse) response;
        }
        return builder().from(response).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "Response", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private List<Method> methods;

        private Builder() {
            this.methods = new ArrayList();
        }

        public final Builder from(Response response) {
            ImmutableResponse.requireNonNull(response, "instance");
            addAllMethods(response.methods());
            return this;
        }

        public final Builder addMethods(Method method) {
            this.methods.add((Method) ImmutableResponse.requireNonNull(method, "methods element"));
            return this;
        }

        public final Builder addMethods(Method... methodArr) {
            for (Method method : methodArr) {
                this.methods.add((Method) ImmutableResponse.requireNonNull(method, "methods element"));
            }
            return this;
        }

        @JsonProperty("methods")
        public final Builder methods(Iterable<? extends Method> iterable) {
            this.methods.clear();
            return addAllMethods(iterable);
        }

        public final Builder addAllMethods(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.methods.add((Method) ImmutableResponse.requireNonNull(it.next(), "methods element"));
            }
            return this;
        }

        public ImmutableResponse build() {
            return new ImmutableResponse(ImmutableResponse.createUnmodifiableList(true, this.methods));
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
