package ExternalUriInterface.v1_0;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Queue;
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
@Generated(from = "NavigateToExternalUriMethod", generator = "Immutables")
public final class ImmutableNavigateToExternalUriMethod extends NavigateToExternalUriMethod {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final Boolean forced;
    private volatile transient InitShim initShim;
    private final List<Method> onError;
    private final boolean openInNewWindow;
    private final Queue queue;
    private final String uri;

    private ImmutableNavigateToExternalUriMethod(Builder builder) {
        this.initShim = new InitShim();
        this.queue = builder.queue;
        this.uri = builder.uri;
        this.onError = createUnmodifiableList(true, builder.onError);
        if (builder.forced != null) {
            this.initShim.forced(builder.forced);
        }
        if (builder.openInNewWindowIsSet()) {
            this.initShim.openInNewWindow(builder.openInNewWindow);
        }
        this.forced = this.initShim.forced();
        this.openInNewWindow = this.initShim.openInNewWindow();
        this.initShim = null;
    }

    private ImmutableNavigateToExternalUriMethod(Queue queue, Boolean bool, String str, boolean z, List<Method> list) {
        this.initShim = new InitShim();
        this.queue = queue;
        this.forced = bool;
        this.uri = str;
        this.openInNewWindow = z;
        this.onError = list;
        this.initShim = null;
    }

    @Generated(from = "NavigateToExternalUriMethod", generator = "Immutables")
    private final class InitShim {
        private Boolean forced;
        private byte forcedBuildStage;
        private boolean openInNewWindow;
        private byte openInNewWindowBuildStage;

        private InitShim() {
            this.forcedBuildStage = (byte) 0;
            this.openInNewWindowBuildStage = (byte) 0;
        }

        Boolean forced() {
            byte b = this.forcedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.forcedBuildStage = (byte) -1;
                this.forced = (Boolean) ImmutableNavigateToExternalUriMethod.requireNonNull(ImmutableNavigateToExternalUriMethod.super.forced(), "forced");
                this.forcedBuildStage = (byte) 1;
            }
            return this.forced;
        }

        void forced(Boolean bool) {
            this.forced = bool;
            this.forcedBuildStage = (byte) 1;
        }

        boolean openInNewWindow() {
            byte b = this.openInNewWindowBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.openInNewWindowBuildStage = (byte) -1;
                this.openInNewWindow = ImmutableNavigateToExternalUriMethod.super.openInNewWindow();
                this.openInNewWindowBuildStage = (byte) 1;
            }
            return this.openInNewWindow;
        }

        void openInNewWindow(boolean z) {
            this.openInNewWindow = z;
            this.openInNewWindowBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.forcedBuildStage == -1) {
                arrayList.add("forced");
            }
            if (this.openInNewWindowBuildStage == -1) {
                arrayList.add("openInNewWindow");
            }
            return "Cannot build NavigateToExternalUriMethod, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // CoreInterface.v1_0.Method
    @JsonProperty("queue")
    public Queue queue() {
        return this.queue;
    }

    @Override // CoreInterface.v1_0.Method
    @JsonProperty("forced")
    public Boolean forced() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.forced();
        }
        return this.forced;
    }

    @Override // ExternalUriInterface.v1_0.NavigateToExternalUriMethod
    @JsonProperty("uri")
    public String uri() {
        return this.uri;
    }

    @Override // ExternalUriInterface.v1_0.NavigateToExternalUriMethod
    @JsonProperty("openInNewWindow")
    public boolean openInNewWindow() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.openInNewWindow();
        }
        return this.openInNewWindow;
    }

    @Override // ExternalUriInterface.v1_0.NavigateToExternalUriMethod
    @JsonProperty("onError")
    public List<Method> onError() {
        return this.onError;
    }

    public final ImmutableNavigateToExternalUriMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableNavigateToExternalUriMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.uri, this.openInNewWindow, this.onError);
    }

    public final ImmutableNavigateToExternalUriMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableNavigateToExternalUriMethod(this.queue, bool2, this.uri, this.openInNewWindow, this.onError);
    }

    public final ImmutableNavigateToExternalUriMethod withUri(String str) {
        String str2 = (String) requireNonNull(str, "uri");
        return this.uri.equals(str2) ? this : new ImmutableNavigateToExternalUriMethod(this.queue, this.forced, str2, this.openInNewWindow, this.onError);
    }

    public final ImmutableNavigateToExternalUriMethod withOpenInNewWindow(boolean z) {
        return this.openInNewWindow == z ? this : new ImmutableNavigateToExternalUriMethod(this.queue, this.forced, this.uri, z, this.onError);
    }

    public final ImmutableNavigateToExternalUriMethod withOnError(Method... methodArr) {
        return new ImmutableNavigateToExternalUriMethod(this.queue, this.forced, this.uri, this.openInNewWindow, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableNavigateToExternalUriMethod withOnError(Iterable<? extends Method> iterable) {
        if (this.onError == iterable) {
            return this;
        }
        return new ImmutableNavigateToExternalUriMethod(this.queue, this.forced, this.uri, this.openInNewWindow, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableNavigateToExternalUriMethod) && equalTo(0, (ImmutableNavigateToExternalUriMethod) obj);
    }

    private boolean equalTo(int i, ImmutableNavigateToExternalUriMethod immutableNavigateToExternalUriMethod) {
        return this.queue.equals(immutableNavigateToExternalUriMethod.queue) && this.forced.equals(immutableNavigateToExternalUriMethod.forced) && this.uri.equals(immutableNavigateToExternalUriMethod.uri) && this.openInNewWindow == immutableNavigateToExternalUriMethod.openInNewWindow && this.onError.equals(immutableNavigateToExternalUriMethod.onError);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.uri.hashCode();
        int i = iHashCode3 + (iHashCode3 << 5) + (this.openInNewWindow ? 1231 : 1237);
        return i + (i << 5) + this.onError.hashCode();
    }

    public String toString() {
        return "NavigateToExternalUriMethod{queue=" + this.queue + ", forced=" + this.forced + ", uri=" + this.uri + ", openInNewWindow=" + this.openInNewWindow + ", onError=" + this.onError + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "NavigateToExternalUriMethod", generator = "Immutables")
    static final class Json extends NavigateToExternalUriMethod {

        @Nullable
        Boolean forced;

        @Nullable
        List<Method> onError = Collections.emptyList();
        boolean openInNewWindow;
        boolean openInNewWindowIsSet;

        @Nullable
        Queue queue;

        @Nullable
        String uri;

        Json() {
        }

        @JsonProperty("queue")
        public void setQueue(Queue queue) {
            this.queue = queue;
        }

        @JsonProperty("forced")
        public void setForced(Boolean bool) {
            this.forced = bool;
        }

        @JsonProperty("uri")
        public void setUri(String str) {
            this.uri = str;
        }

        @JsonProperty("openInNewWindow")
        public void setOpenInNewWindow(boolean z) {
            this.openInNewWindow = z;
            this.openInNewWindowIsSet = true;
        }

        @JsonProperty("onError")
        public void setOnError(List<Method> list) {
            this.onError = list;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalUriInterface.v1_0.NavigateToExternalUriMethod
        public String uri() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalUriInterface.v1_0.NavigateToExternalUriMethod
        public boolean openInNewWindow() {
            throw new UnsupportedOperationException();
        }

        @Override // ExternalUriInterface.v1_0.NavigateToExternalUriMethod
        public List<Method> onError() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableNavigateToExternalUriMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        String str = json.uri;
        if (str != null) {
            builder.uri(str);
        }
        if (json.openInNewWindowIsSet) {
            builder.openInNewWindow(json.openInNewWindow);
        }
        List<Method> list = json.onError;
        if (list != null) {
            builder.addAllOnError(list);
        }
        return builder.build();
    }

    public static ImmutableNavigateToExternalUriMethod copyOf(NavigateToExternalUriMethod navigateToExternalUriMethod) {
        if (navigateToExternalUriMethod instanceof ImmutableNavigateToExternalUriMethod) {
            return (ImmutableNavigateToExternalUriMethod) navigateToExternalUriMethod;
        }
        return builder().from(navigateToExternalUriMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "NavigateToExternalUriMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_QUEUE = 1;
        private static final long INIT_BIT_URI = 2;
        private static final long OPT_BIT_OPEN_IN_NEW_WINDOW = 1;

        @Nullable
        private Boolean forced;
        private long initBits;
        private List<Method> onError;
        private boolean openInNewWindow;
        private long optBits;

        @Nullable
        private Queue queue;

        @Nullable
        private String uri;

        private Builder() {
            this.initBits = 3L;
            this.onError = new ArrayList();
        }

        public final Builder from(Method method) {
            ImmutableNavigateToExternalUriMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(NavigateToExternalUriMethod navigateToExternalUriMethod) {
            ImmutableNavigateToExternalUriMethod.requireNonNull(navigateToExternalUriMethod, "instance");
            from((Object) navigateToExternalUriMethod);
            return this;
        }

        private void from(Object obj) {
            long j;
            if (obj instanceof Method) {
                Method method = (Method) obj;
                forced(method.forced());
                queue(method.queue());
                j = 3;
            } else {
                j = 0;
            }
            if (obj instanceof NavigateToExternalUriMethod) {
                NavigateToExternalUriMethod navigateToExternalUriMethod = (NavigateToExternalUriMethod) obj;
                addAllOnError(navigateToExternalUriMethod.onError());
                uri(navigateToExternalUriMethod.uri());
                if ((j & 1) == 0) {
                    forced(navigateToExternalUriMethod.forced());
                    j |= 1;
                }
                if ((j & 2) == 0) {
                    queue(navigateToExternalUriMethod.queue());
                }
                openInNewWindow(navigateToExternalUriMethod.openInNewWindow());
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableNavigateToExternalUriMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableNavigateToExternalUriMethod.requireNonNull(bool, "forced");
            return this;
        }

        @JsonProperty("uri")
        public final Builder uri(String str) {
            this.uri = (String) ImmutableNavigateToExternalUriMethod.requireNonNull(str, "uri");
            this.initBits &= -3;
            return this;
        }

        @JsonProperty("openInNewWindow")
        public final Builder openInNewWindow(boolean z) {
            this.openInNewWindow = z;
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnError(Method method) {
            this.onError.add((Method) ImmutableNavigateToExternalUriMethod.requireNonNull(method, "onError element"));
            return this;
        }

        public final Builder addOnError(Method... methodArr) {
            for (Method method : methodArr) {
                this.onError.add((Method) ImmutableNavigateToExternalUriMethod.requireNonNull(method, "onError element"));
            }
            return this;
        }

        @JsonProperty("onError")
        public final Builder onError(Iterable<? extends Method> iterable) {
            this.onError.clear();
            return addAllOnError(iterable);
        }

        public final Builder addAllOnError(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onError.add((Method) ImmutableNavigateToExternalUriMethod.requireNonNull(it.next(), "onError element"));
            }
            return this;
        }

        public ImmutableNavigateToExternalUriMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableNavigateToExternalUriMethod(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean openInNewWindowIsSet() {
            return (this.optBits & 1) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add("uri");
            }
            return "Cannot build NavigateToExternalUriMethod, some of required attributes are not set " + arrayList;
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
