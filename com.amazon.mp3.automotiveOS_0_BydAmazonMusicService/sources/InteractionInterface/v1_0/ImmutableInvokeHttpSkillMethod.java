package InteractionInterface.v1_0;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Queue;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.android.gms.common.internal.ImagesContract;
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
@Generated(from = "InvokeHttpSkillMethod", generator = "Immutables")
public final class ImmutableInvokeHttpSkillMethod extends InvokeHttpSkillMethod {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final List<Method> after;
    private final List<Method> before;
    private final List<String> clientInformation;
    private final Boolean forced;
    private volatile transient InitShim initShim;
    private final List<Method> onError;
    private final List<Method> onSuccess;
    private final Queue queue;
    private final String url;

    private ImmutableInvokeHttpSkillMethod(Builder builder) {
        this.initShim = new InitShim();
        this.queue = builder.queue;
        this.url = builder.url;
        if (builder.forced != null) {
            this.initShim.forced(builder.forced);
        }
        if (builder.clientInformationIsSet()) {
            this.initShim.clientInformation(createUnmodifiableList(true, builder.clientInformation));
        }
        if (builder.beforeIsSet()) {
            this.initShim.before(createUnmodifiableList(true, builder.before));
        }
        if (builder.afterIsSet()) {
            this.initShim.after(createUnmodifiableList(true, builder.after));
        }
        if (builder.onSuccessIsSet()) {
            this.initShim.onSuccess(createUnmodifiableList(true, builder.onSuccess));
        }
        if (builder.onErrorIsSet()) {
            this.initShim.onError(createUnmodifiableList(true, builder.onError));
        }
        this.forced = this.initShim.forced();
        this.clientInformation = this.initShim.clientInformation();
        this.before = this.initShim.before();
        this.after = this.initShim.after();
        this.onSuccess = this.initShim.onSuccess();
        this.onError = this.initShim.onError();
        this.initShim = null;
    }

    private ImmutableInvokeHttpSkillMethod(Queue queue, Boolean bool, String str, List<String> list, List<Method> list2, List<Method> list3, List<Method> list4, List<Method> list5) {
        this.initShim = new InitShim();
        this.queue = queue;
        this.forced = bool;
        this.url = str;
        this.clientInformation = list;
        this.before = list2;
        this.after = list3;
        this.onSuccess = list4;
        this.onError = list5;
        this.initShim = null;
    }

    @Generated(from = "InvokeHttpSkillMethod", generator = "Immutables")
    private final class InitShim {
        private List<Method> after;
        private byte afterBuildStage;
        private List<Method> before;
        private byte beforeBuildStage;
        private List<String> clientInformation;
        private byte clientInformationBuildStage;
        private Boolean forced;
        private byte forcedBuildStage;
        private List<Method> onError;
        private byte onErrorBuildStage;
        private List<Method> onSuccess;
        private byte onSuccessBuildStage;

        private InitShim() {
            this.forcedBuildStage = (byte) 0;
            this.clientInformationBuildStage = (byte) 0;
            this.beforeBuildStage = (byte) 0;
            this.afterBuildStage = (byte) 0;
            this.onSuccessBuildStage = (byte) 0;
            this.onErrorBuildStage = (byte) 0;
        }

        Boolean forced() {
            byte b = this.forcedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.forcedBuildStage = (byte) -1;
                this.forced = (Boolean) ImmutableInvokeHttpSkillMethod.requireNonNull(ImmutableInvokeHttpSkillMethod.super.forced(), "forced");
                this.forcedBuildStage = (byte) 1;
            }
            return this.forced;
        }

        void forced(Boolean bool) {
            this.forced = bool;
            this.forcedBuildStage = (byte) 1;
        }

        List<String> clientInformation() {
            byte b = this.clientInformationBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.clientInformationBuildStage = (byte) -1;
                this.clientInformation = ImmutableInvokeHttpSkillMethod.createUnmodifiableList(false, ImmutableInvokeHttpSkillMethod.createSafeList(ImmutableInvokeHttpSkillMethod.super.clientInformation(), true, false));
                this.clientInformationBuildStage = (byte) 1;
            }
            return this.clientInformation;
        }

        void clientInformation(List<String> list) {
            this.clientInformation = list;
            this.clientInformationBuildStage = (byte) 1;
        }

        List<Method> before() {
            byte b = this.beforeBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.beforeBuildStage = (byte) -1;
                this.before = ImmutableInvokeHttpSkillMethod.createUnmodifiableList(false, ImmutableInvokeHttpSkillMethod.createSafeList(ImmutableInvokeHttpSkillMethod.super.before(), true, false));
                this.beforeBuildStage = (byte) 1;
            }
            return this.before;
        }

        void before(List<Method> list) {
            this.before = list;
            this.beforeBuildStage = (byte) 1;
        }

        List<Method> after() {
            byte b = this.afterBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.afterBuildStage = (byte) -1;
                this.after = ImmutableInvokeHttpSkillMethod.createUnmodifiableList(false, ImmutableInvokeHttpSkillMethod.createSafeList(ImmutableInvokeHttpSkillMethod.super.after(), true, false));
                this.afterBuildStage = (byte) 1;
            }
            return this.after;
        }

        void after(List<Method> list) {
            this.after = list;
            this.afterBuildStage = (byte) 1;
        }

        List<Method> onSuccess() {
            byte b = this.onSuccessBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onSuccessBuildStage = (byte) -1;
                this.onSuccess = ImmutableInvokeHttpSkillMethod.createUnmodifiableList(false, ImmutableInvokeHttpSkillMethod.createSafeList(ImmutableInvokeHttpSkillMethod.super.onSuccess(), true, false));
                this.onSuccessBuildStage = (byte) 1;
            }
            return this.onSuccess;
        }

        void onSuccess(List<Method> list) {
            this.onSuccess = list;
            this.onSuccessBuildStage = (byte) 1;
        }

        List<Method> onError() {
            byte b = this.onErrorBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onErrorBuildStage = (byte) -1;
                this.onError = ImmutableInvokeHttpSkillMethod.createUnmodifiableList(false, ImmutableInvokeHttpSkillMethod.createSafeList(ImmutableInvokeHttpSkillMethod.super.onError(), true, false));
                this.onErrorBuildStage = (byte) 1;
            }
            return this.onError;
        }

        void onError(List<Method> list) {
            this.onError = list;
            this.onErrorBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.forcedBuildStage == -1) {
                arrayList.add("forced");
            }
            if (this.clientInformationBuildStage == -1) {
                arrayList.add(TetheredMessageKey.clientInformation);
            }
            if (this.beforeBuildStage == -1) {
                arrayList.add("before");
            }
            if (this.afterBuildStage == -1) {
                arrayList.add("after");
            }
            if (this.onSuccessBuildStage == -1) {
                arrayList.add("onSuccess");
            }
            if (this.onErrorBuildStage == -1) {
                arrayList.add("onError");
            }
            return "Cannot build InvokeHttpSkillMethod, attribute initializers form cycle " + arrayList;
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

    @Override // InteractionInterface.v1_0.InvokeHttpSkillMethod
    @JsonProperty(ImagesContract.URL)
    public String url() {
        return this.url;
    }

    @Override // InteractionInterface.v1_0.InvokeHttpSkillMethod
    @JsonProperty(TetheredMessageKey.clientInformation)
    public List<String> clientInformation() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.clientInformation();
        }
        return this.clientInformation;
    }

    @Override // InteractionInterface.v1_0.InvokeHttpSkillMethod
    @JsonProperty("before")
    public List<Method> before() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.before();
        }
        return this.before;
    }

    @Override // InteractionInterface.v1_0.InvokeHttpSkillMethod
    @JsonProperty("after")
    public List<Method> after() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.after();
        }
        return this.after;
    }

    @Override // InteractionInterface.v1_0.InvokeHttpSkillMethod
    @JsonProperty("onSuccess")
    public List<Method> onSuccess() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onSuccess();
        }
        return this.onSuccess;
    }

    @Override // InteractionInterface.v1_0.InvokeHttpSkillMethod
    @JsonProperty("onError")
    public List<Method> onError() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onError();
        }
        return this.onError;
    }

    public final ImmutableInvokeHttpSkillMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableInvokeHttpSkillMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.url, this.clientInformation, this.before, this.after, this.onSuccess, this.onError);
    }

    public final ImmutableInvokeHttpSkillMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableInvokeHttpSkillMethod(this.queue, bool2, this.url, this.clientInformation, this.before, this.after, this.onSuccess, this.onError);
    }

    public final ImmutableInvokeHttpSkillMethod withUrl(String str) {
        String str2 = (String) requireNonNull(str, ImagesContract.URL);
        return this.url.equals(str2) ? this : new ImmutableInvokeHttpSkillMethod(this.queue, this.forced, str2, this.clientInformation, this.before, this.after, this.onSuccess, this.onError);
    }

    public final ImmutableInvokeHttpSkillMethod withClientInformation(String... strArr) {
        return new ImmutableInvokeHttpSkillMethod(this.queue, this.forced, this.url, createUnmodifiableList(false, createSafeList(Arrays.asList(strArr), true, false)), this.before, this.after, this.onSuccess, this.onError);
    }

    public final ImmutableInvokeHttpSkillMethod withClientInformation(Iterable<String> iterable) {
        if (this.clientInformation == iterable) {
            return this;
        }
        return new ImmutableInvokeHttpSkillMethod(this.queue, this.forced, this.url, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.before, this.after, this.onSuccess, this.onError);
    }

    public final ImmutableInvokeHttpSkillMethod withBefore(Method... methodArr) {
        return new ImmutableInvokeHttpSkillMethod(this.queue, this.forced, this.url, this.clientInformation, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.after, this.onSuccess, this.onError);
    }

    public final ImmutableInvokeHttpSkillMethod withBefore(Iterable<? extends Method> iterable) {
        if (this.before == iterable) {
            return this;
        }
        return new ImmutableInvokeHttpSkillMethod(this.queue, this.forced, this.url, this.clientInformation, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.after, this.onSuccess, this.onError);
    }

    public final ImmutableInvokeHttpSkillMethod withAfter(Method... methodArr) {
        return new ImmutableInvokeHttpSkillMethod(this.queue, this.forced, this.url, this.clientInformation, this.before, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onSuccess, this.onError);
    }

    public final ImmutableInvokeHttpSkillMethod withAfter(Iterable<? extends Method> iterable) {
        if (this.after == iterable) {
            return this;
        }
        return new ImmutableInvokeHttpSkillMethod(this.queue, this.forced, this.url, this.clientInformation, this.before, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onSuccess, this.onError);
    }

    public final ImmutableInvokeHttpSkillMethod withOnSuccess(Method... methodArr) {
        return new ImmutableInvokeHttpSkillMethod(this.queue, this.forced, this.url, this.clientInformation, this.before, this.after, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onError);
    }

    public final ImmutableInvokeHttpSkillMethod withOnSuccess(Iterable<? extends Method> iterable) {
        if (this.onSuccess == iterable) {
            return this;
        }
        return new ImmutableInvokeHttpSkillMethod(this.queue, this.forced, this.url, this.clientInformation, this.before, this.after, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onError);
    }

    public final ImmutableInvokeHttpSkillMethod withOnError(Method... methodArr) {
        return new ImmutableInvokeHttpSkillMethod(this.queue, this.forced, this.url, this.clientInformation, this.before, this.after, this.onSuccess, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableInvokeHttpSkillMethod withOnError(Iterable<? extends Method> iterable) {
        if (this.onError == iterable) {
            return this;
        }
        return new ImmutableInvokeHttpSkillMethod(this.queue, this.forced, this.url, this.clientInformation, this.before, this.after, this.onSuccess, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableInvokeHttpSkillMethod) && equalTo(0, (ImmutableInvokeHttpSkillMethod) obj);
    }

    private boolean equalTo(int i, ImmutableInvokeHttpSkillMethod immutableInvokeHttpSkillMethod) {
        return this.queue.equals(immutableInvokeHttpSkillMethod.queue) && this.forced.equals(immutableInvokeHttpSkillMethod.forced) && this.url.equals(immutableInvokeHttpSkillMethod.url) && this.clientInformation.equals(immutableInvokeHttpSkillMethod.clientInformation) && this.before.equals(immutableInvokeHttpSkillMethod.before) && this.after.equals(immutableInvokeHttpSkillMethod.after) && this.onSuccess.equals(immutableInvokeHttpSkillMethod.onSuccess) && this.onError.equals(immutableInvokeHttpSkillMethod.onError);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.url.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.clientInformation.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.before.hashCode();
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + this.after.hashCode();
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + this.onSuccess.hashCode();
        return iHashCode7 + (iHashCode7 << 5) + this.onError.hashCode();
    }

    public String toString() {
        return "InvokeHttpSkillMethod{queue=" + this.queue + ", forced=" + this.forced + ", url=" + this.url + ", clientInformation=" + this.clientInformation + ", before=" + this.before + ", after=" + this.after + ", onSuccess=" + this.onSuccess + ", onError=" + this.onError + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "InvokeHttpSkillMethod", generator = "Immutables")
    static final class Json extends InvokeHttpSkillMethod {
        boolean afterIsSet;
        boolean beforeIsSet;
        boolean clientInformationIsSet;

        @Nullable
        Boolean forced;
        boolean onErrorIsSet;
        boolean onSuccessIsSet;

        @Nullable
        Queue queue;

        @Nullable
        String url;

        @Nullable
        List<String> clientInformation = Collections.emptyList();

        @Nullable
        List<Method> before = Collections.emptyList();

        @Nullable
        List<Method> after = Collections.emptyList();

        @Nullable
        List<Method> onSuccess = Collections.emptyList();

        @Nullable
        List<Method> onError = Collections.emptyList();

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

        @JsonProperty(ImagesContract.URL)
        public void setUrl(String str) {
            this.url = str;
        }

        @JsonProperty(TetheredMessageKey.clientInformation)
        public void setClientInformation(List<String> list) {
            this.clientInformation = list;
            this.clientInformationIsSet = true;
        }

        @JsonProperty("before")
        public void setBefore(List<Method> list) {
            this.before = list;
            this.beforeIsSet = true;
        }

        @JsonProperty("after")
        public void setAfter(List<Method> list) {
            this.after = list;
            this.afterIsSet = true;
        }

        @JsonProperty("onSuccess")
        public void setOnSuccess(List<Method> list) {
            this.onSuccess = list;
            this.onSuccessIsSet = true;
        }

        @JsonProperty("onError")
        public void setOnError(List<Method> list) {
            this.onError = list;
            this.onErrorIsSet = true;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // InteractionInterface.v1_0.InvokeHttpSkillMethod
        public String url() {
            throw new UnsupportedOperationException();
        }

        @Override // InteractionInterface.v1_0.InvokeHttpSkillMethod
        public List<String> clientInformation() {
            throw new UnsupportedOperationException();
        }

        @Override // InteractionInterface.v1_0.InvokeHttpSkillMethod
        public List<Method> before() {
            throw new UnsupportedOperationException();
        }

        @Override // InteractionInterface.v1_0.InvokeHttpSkillMethod
        public List<Method> after() {
            throw new UnsupportedOperationException();
        }

        @Override // InteractionInterface.v1_0.InvokeHttpSkillMethod
        public List<Method> onSuccess() {
            throw new UnsupportedOperationException();
        }

        @Override // InteractionInterface.v1_0.InvokeHttpSkillMethod
        public List<Method> onError() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableInvokeHttpSkillMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        String str = json.url;
        if (str != null) {
            builder.url(str);
        }
        if (json.clientInformationIsSet) {
            builder.addAllClientInformation(json.clientInformation);
        }
        if (json.beforeIsSet) {
            builder.addAllBefore(json.before);
        }
        if (json.afterIsSet) {
            builder.addAllAfter(json.after);
        }
        if (json.onSuccessIsSet) {
            builder.addAllOnSuccess(json.onSuccess);
        }
        if (json.onErrorIsSet) {
            builder.addAllOnError(json.onError);
        }
        return builder.build();
    }

    public static ImmutableInvokeHttpSkillMethod copyOf(InvokeHttpSkillMethod invokeHttpSkillMethod) {
        if (invokeHttpSkillMethod instanceof ImmutableInvokeHttpSkillMethod) {
            return (ImmutableInvokeHttpSkillMethod) invokeHttpSkillMethod;
        }
        return builder().from(invokeHttpSkillMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "InvokeHttpSkillMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_QUEUE = 1;
        private static final long INIT_BIT_URL = 2;
        private static final long OPT_BIT_AFTER = 4;
        private static final long OPT_BIT_BEFORE = 2;
        private static final long OPT_BIT_CLIENT_INFORMATION = 1;
        private static final long OPT_BIT_ON_ERROR = 16;
        private static final long OPT_BIT_ON_SUCCESS = 8;
        private List<Method> after;
        private List<Method> before;
        private List<String> clientInformation;

        @Nullable
        private Boolean forced;
        private long initBits;
        private List<Method> onError;
        private List<Method> onSuccess;
        private long optBits;

        @Nullable
        private Queue queue;

        @Nullable
        private String url;

        private Builder() {
            this.initBits = 3L;
            this.clientInformation = new ArrayList();
            this.before = new ArrayList();
            this.after = new ArrayList();
            this.onSuccess = new ArrayList();
            this.onError = new ArrayList();
        }

        public final Builder from(Method method) {
            ImmutableInvokeHttpSkillMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(InvokeHttpSkillMethod invokeHttpSkillMethod) {
            ImmutableInvokeHttpSkillMethod.requireNonNull(invokeHttpSkillMethod, "instance");
            from((Object) invokeHttpSkillMethod);
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
            if (obj instanceof InvokeHttpSkillMethod) {
                InvokeHttpSkillMethod invokeHttpSkillMethod = (InvokeHttpSkillMethod) obj;
                addAllOnError(invokeHttpSkillMethod.onError());
                addAllClientInformation(invokeHttpSkillMethod.clientInformation());
                addAllBefore(invokeHttpSkillMethod.before());
                if ((j & 1) == 0) {
                    forced(invokeHttpSkillMethod.forced());
                    j |= 1;
                }
                addAllAfter(invokeHttpSkillMethod.after());
                if ((j & 2) == 0) {
                    queue(invokeHttpSkillMethod.queue());
                }
                url(invokeHttpSkillMethod.url());
                addAllOnSuccess(invokeHttpSkillMethod.onSuccess());
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableInvokeHttpSkillMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableInvokeHttpSkillMethod.requireNonNull(bool, "forced");
            return this;
        }

        @JsonProperty(ImagesContract.URL)
        public final Builder url(String str) {
            this.url = (String) ImmutableInvokeHttpSkillMethod.requireNonNull(str, ImagesContract.URL);
            this.initBits &= -3;
            return this;
        }

        public final Builder addClientInformation(String str) {
            this.clientInformation.add((String) ImmutableInvokeHttpSkillMethod.requireNonNull(str, "clientInformation element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addClientInformation(String... strArr) {
            for (String str : strArr) {
                this.clientInformation.add((String) ImmutableInvokeHttpSkillMethod.requireNonNull(str, "clientInformation element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty(TetheredMessageKey.clientInformation)
        public final Builder clientInformation(Iterable<String> iterable) {
            this.clientInformation.clear();
            return addAllClientInformation(iterable);
        }

        public final Builder addAllClientInformation(Iterable<String> iterable) {
            Iterator<String> it = iterable.iterator();
            while (it.hasNext()) {
                this.clientInformation.add((String) ImmutableInvokeHttpSkillMethod.requireNonNull(it.next(), "clientInformation element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addBefore(Method method) {
            this.before.add((Method) ImmutableInvokeHttpSkillMethod.requireNonNull(method, "before element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addBefore(Method... methodArr) {
            for (Method method : methodArr) {
                this.before.add((Method) ImmutableInvokeHttpSkillMethod.requireNonNull(method, "before element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("before")
        public final Builder before(Iterable<? extends Method> iterable) {
            this.before.clear();
            return addAllBefore(iterable);
        }

        public final Builder addAllBefore(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.before.add((Method) ImmutableInvokeHttpSkillMethod.requireNonNull(it.next(), "before element"));
            }
            this.optBits |= 2;
            return this;
        }

        public final Builder addAfter(Method method) {
            this.after.add((Method) ImmutableInvokeHttpSkillMethod.requireNonNull(method, "after element"));
            this.optBits |= 4;
            return this;
        }

        public final Builder addAfter(Method... methodArr) {
            for (Method method : methodArr) {
                this.after.add((Method) ImmutableInvokeHttpSkillMethod.requireNonNull(method, "after element"));
            }
            this.optBits |= 4;
            return this;
        }

        @JsonProperty("after")
        public final Builder after(Iterable<? extends Method> iterable) {
            this.after.clear();
            return addAllAfter(iterable);
        }

        public final Builder addAllAfter(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.after.add((Method) ImmutableInvokeHttpSkillMethod.requireNonNull(it.next(), "after element"));
            }
            this.optBits |= 4;
            return this;
        }

        public final Builder addOnSuccess(Method method) {
            this.onSuccess.add((Method) ImmutableInvokeHttpSkillMethod.requireNonNull(method, "onSuccess element"));
            this.optBits |= 8;
            return this;
        }

        public final Builder addOnSuccess(Method... methodArr) {
            for (Method method : methodArr) {
                this.onSuccess.add((Method) ImmutableInvokeHttpSkillMethod.requireNonNull(method, "onSuccess element"));
            }
            this.optBits |= 8;
            return this;
        }

        @JsonProperty("onSuccess")
        public final Builder onSuccess(Iterable<? extends Method> iterable) {
            this.onSuccess.clear();
            return addAllOnSuccess(iterable);
        }

        public final Builder addAllOnSuccess(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onSuccess.add((Method) ImmutableInvokeHttpSkillMethod.requireNonNull(it.next(), "onSuccess element"));
            }
            this.optBits |= 8;
            return this;
        }

        public final Builder addOnError(Method method) {
            this.onError.add((Method) ImmutableInvokeHttpSkillMethod.requireNonNull(method, "onError element"));
            this.optBits |= 16;
            return this;
        }

        public final Builder addOnError(Method... methodArr) {
            for (Method method : methodArr) {
                this.onError.add((Method) ImmutableInvokeHttpSkillMethod.requireNonNull(method, "onError element"));
            }
            this.optBits |= 16;
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
                this.onError.add((Method) ImmutableInvokeHttpSkillMethod.requireNonNull(it.next(), "onError element"));
            }
            this.optBits |= 16;
            return this;
        }

        public ImmutableInvokeHttpSkillMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableInvokeHttpSkillMethod(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean clientInformationIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean beforeIsSet() {
            return (this.optBits & 2) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean afterIsSet() {
            return (this.optBits & 4) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onSuccessIsSet() {
            return (this.optBits & 8) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onErrorIsSet() {
            return (this.optBits & 16) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            if ((this.initBits & 2) != 0) {
                arrayList.add(ImagesContract.URL);
            }
            return "Cannot build InvokeHttpSkillMethod, some of required attributes are not set " + arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T requireNonNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> List<T> createSafeList(Iterable<? extends T> iterable, boolean z, boolean z2) {
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
