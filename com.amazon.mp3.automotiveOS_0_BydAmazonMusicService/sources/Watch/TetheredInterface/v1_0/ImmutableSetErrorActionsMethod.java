package Watch.TetheredInterface.v1_0;

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
@Generated(from = "SetErrorActionsMethod", generator = "Immutables")
public final class ImmutableSetErrorActionsMethod extends SetErrorActionsMethod {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private final Boolean forced;
    private volatile transient InitShim initShim;
    private final List<Method> onCheckPhone;
    private final List<Method> onInOfflineMode;
    private final List<Method> onNotLoggedIn;
    private final List<Method> onTetheredDeviceNotReachable;
    private final List<Method> onTimeout;
    private final List<Method> onUnknownError;
    private final Queue queue;

    private ImmutableSetErrorActionsMethod(Builder builder) {
        this.initShim = new InitShim();
        this.queue = builder.queue;
        if (builder.forced != null) {
            this.initShim.forced(builder.forced);
        }
        if (builder.onTetheredDeviceNotReachableIsSet()) {
            this.initShim.onTetheredDeviceNotReachable(createUnmodifiableList(true, builder.onTetheredDeviceNotReachable));
        }
        if (builder.onTimeoutIsSet()) {
            this.initShim.onTimeout(createUnmodifiableList(true, builder.onTimeout));
        }
        if (builder.onNotLoggedInIsSet()) {
            this.initShim.onNotLoggedIn(createUnmodifiableList(true, builder.onNotLoggedIn));
        }
        if (builder.onInOfflineModeIsSet()) {
            this.initShim.onInOfflineMode(createUnmodifiableList(true, builder.onInOfflineMode));
        }
        if (builder.onCheckPhoneIsSet()) {
            this.initShim.onCheckPhone(createUnmodifiableList(true, builder.onCheckPhone));
        }
        if (builder.onUnknownErrorIsSet()) {
            this.initShim.onUnknownError(createUnmodifiableList(true, builder.onUnknownError));
        }
        this.forced = this.initShim.forced();
        this.onTetheredDeviceNotReachable = this.initShim.onTetheredDeviceNotReachable();
        this.onTimeout = this.initShim.onTimeout();
        this.onNotLoggedIn = this.initShim.onNotLoggedIn();
        this.onInOfflineMode = this.initShim.onInOfflineMode();
        this.onCheckPhone = this.initShim.onCheckPhone();
        this.onUnknownError = this.initShim.onUnknownError();
        this.initShim = null;
    }

    private ImmutableSetErrorActionsMethod(Queue queue, Boolean bool, List<Method> list, List<Method> list2, List<Method> list3, List<Method> list4, List<Method> list5, List<Method> list6) {
        this.initShim = new InitShim();
        this.queue = queue;
        this.forced = bool;
        this.onTetheredDeviceNotReachable = list;
        this.onTimeout = list2;
        this.onNotLoggedIn = list3;
        this.onInOfflineMode = list4;
        this.onCheckPhone = list5;
        this.onUnknownError = list6;
        this.initShim = null;
    }

    @Generated(from = "SetErrorActionsMethod", generator = "Immutables")
    private final class InitShim {
        private Boolean forced;
        private byte forcedBuildStage;
        private List<Method> onCheckPhone;
        private byte onCheckPhoneBuildStage;
        private List<Method> onInOfflineMode;
        private byte onInOfflineModeBuildStage;
        private List<Method> onNotLoggedIn;
        private byte onNotLoggedInBuildStage;
        private List<Method> onTetheredDeviceNotReachable;
        private byte onTetheredDeviceNotReachableBuildStage;
        private List<Method> onTimeout;
        private byte onTimeoutBuildStage;
        private List<Method> onUnknownError;
        private byte onUnknownErrorBuildStage;

        private InitShim() {
            this.forcedBuildStage = (byte) 0;
            this.onTetheredDeviceNotReachableBuildStage = (byte) 0;
            this.onTimeoutBuildStage = (byte) 0;
            this.onNotLoggedInBuildStage = (byte) 0;
            this.onInOfflineModeBuildStage = (byte) 0;
            this.onCheckPhoneBuildStage = (byte) 0;
            this.onUnknownErrorBuildStage = (byte) 0;
        }

        Boolean forced() {
            byte b = this.forcedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.forcedBuildStage = (byte) -1;
                this.forced = (Boolean) ImmutableSetErrorActionsMethod.requireNonNull(ImmutableSetErrorActionsMethod.super.forced(), "forced");
                this.forcedBuildStage = (byte) 1;
            }
            return this.forced;
        }

        void forced(Boolean bool) {
            this.forced = bool;
            this.forcedBuildStage = (byte) 1;
        }

        List<Method> onTetheredDeviceNotReachable() {
            byte b = this.onTetheredDeviceNotReachableBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onTetheredDeviceNotReachableBuildStage = (byte) -1;
                this.onTetheredDeviceNotReachable = ImmutableSetErrorActionsMethod.createUnmodifiableList(false, ImmutableSetErrorActionsMethod.createSafeList(ImmutableSetErrorActionsMethod.super.onTetheredDeviceNotReachable(), true, false));
                this.onTetheredDeviceNotReachableBuildStage = (byte) 1;
            }
            return this.onTetheredDeviceNotReachable;
        }

        void onTetheredDeviceNotReachable(List<Method> list) {
            this.onTetheredDeviceNotReachable = list;
            this.onTetheredDeviceNotReachableBuildStage = (byte) 1;
        }

        List<Method> onTimeout() {
            byte b = this.onTimeoutBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onTimeoutBuildStage = (byte) -1;
                this.onTimeout = ImmutableSetErrorActionsMethod.createUnmodifiableList(false, ImmutableSetErrorActionsMethod.createSafeList(ImmutableSetErrorActionsMethod.super.onTimeout(), true, false));
                this.onTimeoutBuildStage = (byte) 1;
            }
            return this.onTimeout;
        }

        void onTimeout(List<Method> list) {
            this.onTimeout = list;
            this.onTimeoutBuildStage = (byte) 1;
        }

        List<Method> onNotLoggedIn() {
            byte b = this.onNotLoggedInBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onNotLoggedInBuildStage = (byte) -1;
                this.onNotLoggedIn = ImmutableSetErrorActionsMethod.createUnmodifiableList(false, ImmutableSetErrorActionsMethod.createSafeList(ImmutableSetErrorActionsMethod.super.onNotLoggedIn(), true, false));
                this.onNotLoggedInBuildStage = (byte) 1;
            }
            return this.onNotLoggedIn;
        }

        void onNotLoggedIn(List<Method> list) {
            this.onNotLoggedIn = list;
            this.onNotLoggedInBuildStage = (byte) 1;
        }

        List<Method> onInOfflineMode() {
            byte b = this.onInOfflineModeBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onInOfflineModeBuildStage = (byte) -1;
                this.onInOfflineMode = ImmutableSetErrorActionsMethod.createUnmodifiableList(false, ImmutableSetErrorActionsMethod.createSafeList(ImmutableSetErrorActionsMethod.super.onInOfflineMode(), true, false));
                this.onInOfflineModeBuildStage = (byte) 1;
            }
            return this.onInOfflineMode;
        }

        void onInOfflineMode(List<Method> list) {
            this.onInOfflineMode = list;
            this.onInOfflineModeBuildStage = (byte) 1;
        }

        List<Method> onCheckPhone() {
            byte b = this.onCheckPhoneBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onCheckPhoneBuildStage = (byte) -1;
                this.onCheckPhone = ImmutableSetErrorActionsMethod.createUnmodifiableList(false, ImmutableSetErrorActionsMethod.createSafeList(ImmutableSetErrorActionsMethod.super.onCheckPhone(), true, false));
                this.onCheckPhoneBuildStage = (byte) 1;
            }
            return this.onCheckPhone;
        }

        void onCheckPhone(List<Method> list) {
            this.onCheckPhone = list;
            this.onCheckPhoneBuildStage = (byte) 1;
        }

        List<Method> onUnknownError() {
            byte b = this.onUnknownErrorBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onUnknownErrorBuildStage = (byte) -1;
                this.onUnknownError = ImmutableSetErrorActionsMethod.createUnmodifiableList(false, ImmutableSetErrorActionsMethod.createSafeList(ImmutableSetErrorActionsMethod.super.onUnknownError(), true, false));
                this.onUnknownErrorBuildStage = (byte) 1;
            }
            return this.onUnknownError;
        }

        void onUnknownError(List<Method> list) {
            this.onUnknownError = list;
            this.onUnknownErrorBuildStage = (byte) 1;
        }

        private String formatInitCycleMessage() {
            ArrayList arrayList = new ArrayList();
            if (this.forcedBuildStage == -1) {
                arrayList.add("forced");
            }
            if (this.onTetheredDeviceNotReachableBuildStage == -1) {
                arrayList.add("onTetheredDeviceNotReachable");
            }
            if (this.onTimeoutBuildStage == -1) {
                arrayList.add("onTimeout");
            }
            if (this.onNotLoggedInBuildStage == -1) {
                arrayList.add("onNotLoggedIn");
            }
            if (this.onInOfflineModeBuildStage == -1) {
                arrayList.add("onInOfflineMode");
            }
            if (this.onCheckPhoneBuildStage == -1) {
                arrayList.add("onCheckPhone");
            }
            if (this.onUnknownErrorBuildStage == -1) {
                arrayList.add("onUnknownError");
            }
            return "Cannot build SetErrorActionsMethod, attribute initializers form cycle " + arrayList;
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

    @Override // Watch.TetheredInterface.v1_0.SetErrorActionsMethod
    @JsonProperty("onTetheredDeviceNotReachable")
    public List<Method> onTetheredDeviceNotReachable() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onTetheredDeviceNotReachable();
        }
        return this.onTetheredDeviceNotReachable;
    }

    @Override // Watch.TetheredInterface.v1_0.SetErrorActionsMethod
    @JsonProperty("onTimeout")
    public List<Method> onTimeout() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onTimeout();
        }
        return this.onTimeout;
    }

    @Override // Watch.TetheredInterface.v1_0.SetErrorActionsMethod
    @JsonProperty("onNotLoggedIn")
    public List<Method> onNotLoggedIn() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onNotLoggedIn();
        }
        return this.onNotLoggedIn;
    }

    @Override // Watch.TetheredInterface.v1_0.SetErrorActionsMethod
    @JsonProperty("onInOfflineMode")
    public List<Method> onInOfflineMode() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onInOfflineMode();
        }
        return this.onInOfflineMode;
    }

    @Override // Watch.TetheredInterface.v1_0.SetErrorActionsMethod
    @JsonProperty("onCheckPhone")
    public List<Method> onCheckPhone() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onCheckPhone();
        }
        return this.onCheckPhone;
    }

    @Override // Watch.TetheredInterface.v1_0.SetErrorActionsMethod
    @JsonProperty("onUnknownError")
    public List<Method> onUnknownError() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onUnknownError();
        }
        return this.onUnknownError;
    }

    public final ImmutableSetErrorActionsMethod withQueue(Queue queue) {
        return this.queue == queue ? this : new ImmutableSetErrorActionsMethod((Queue) requireNonNull(queue, "queue"), this.forced, this.onTetheredDeviceNotReachable, this.onTimeout, this.onNotLoggedIn, this.onInOfflineMode, this.onCheckPhone, this.onUnknownError);
    }

    public final ImmutableSetErrorActionsMethod withForced(Boolean bool) {
        Boolean bool2 = (Boolean) requireNonNull(bool, "forced");
        return this.forced.equals(bool2) ? this : new ImmutableSetErrorActionsMethod(this.queue, bool2, this.onTetheredDeviceNotReachable, this.onTimeout, this.onNotLoggedIn, this.onInOfflineMode, this.onCheckPhone, this.onUnknownError);
    }

    public final ImmutableSetErrorActionsMethod withOnTetheredDeviceNotReachable(Method... methodArr) {
        return new ImmutableSetErrorActionsMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onTimeout, this.onNotLoggedIn, this.onInOfflineMode, this.onCheckPhone, this.onUnknownError);
    }

    public final ImmutableSetErrorActionsMethod withOnTetheredDeviceNotReachable(Iterable<? extends Method> iterable) {
        if (this.onTetheredDeviceNotReachable == iterable) {
            return this;
        }
        return new ImmutableSetErrorActionsMethod(this.queue, this.forced, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onTimeout, this.onNotLoggedIn, this.onInOfflineMode, this.onCheckPhone, this.onUnknownError);
    }

    public final ImmutableSetErrorActionsMethod withOnTimeout(Method... methodArr) {
        return new ImmutableSetErrorActionsMethod(this.queue, this.forced, this.onTetheredDeviceNotReachable, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onNotLoggedIn, this.onInOfflineMode, this.onCheckPhone, this.onUnknownError);
    }

    public final ImmutableSetErrorActionsMethod withOnTimeout(Iterable<? extends Method> iterable) {
        if (this.onTimeout == iterable) {
            return this;
        }
        return new ImmutableSetErrorActionsMethod(this.queue, this.forced, this.onTetheredDeviceNotReachable, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onNotLoggedIn, this.onInOfflineMode, this.onCheckPhone, this.onUnknownError);
    }

    public final ImmutableSetErrorActionsMethod withOnNotLoggedIn(Method... methodArr) {
        return new ImmutableSetErrorActionsMethod(this.queue, this.forced, this.onTetheredDeviceNotReachable, this.onTimeout, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onInOfflineMode, this.onCheckPhone, this.onUnknownError);
    }

    public final ImmutableSetErrorActionsMethod withOnNotLoggedIn(Iterable<? extends Method> iterable) {
        if (this.onNotLoggedIn == iterable) {
            return this;
        }
        return new ImmutableSetErrorActionsMethod(this.queue, this.forced, this.onTetheredDeviceNotReachable, this.onTimeout, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onInOfflineMode, this.onCheckPhone, this.onUnknownError);
    }

    public final ImmutableSetErrorActionsMethod withOnInOfflineMode(Method... methodArr) {
        return new ImmutableSetErrorActionsMethod(this.queue, this.forced, this.onTetheredDeviceNotReachable, this.onTimeout, this.onNotLoggedIn, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onCheckPhone, this.onUnknownError);
    }

    public final ImmutableSetErrorActionsMethod withOnInOfflineMode(Iterable<? extends Method> iterable) {
        if (this.onInOfflineMode == iterable) {
            return this;
        }
        return new ImmutableSetErrorActionsMethod(this.queue, this.forced, this.onTetheredDeviceNotReachable, this.onTimeout, this.onNotLoggedIn, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onCheckPhone, this.onUnknownError);
    }

    public final ImmutableSetErrorActionsMethod withOnCheckPhone(Method... methodArr) {
        return new ImmutableSetErrorActionsMethod(this.queue, this.forced, this.onTetheredDeviceNotReachable, this.onTimeout, this.onNotLoggedIn, this.onInOfflineMode, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onUnknownError);
    }

    public final ImmutableSetErrorActionsMethod withOnCheckPhone(Iterable<? extends Method> iterable) {
        if (this.onCheckPhone == iterable) {
            return this;
        }
        return new ImmutableSetErrorActionsMethod(this.queue, this.forced, this.onTetheredDeviceNotReachable, this.onTimeout, this.onNotLoggedIn, this.onInOfflineMode, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onUnknownError);
    }

    public final ImmutableSetErrorActionsMethod withOnUnknownError(Method... methodArr) {
        return new ImmutableSetErrorActionsMethod(this.queue, this.forced, this.onTetheredDeviceNotReachable, this.onTimeout, this.onNotLoggedIn, this.onInOfflineMode, this.onCheckPhone, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableSetErrorActionsMethod withOnUnknownError(Iterable<? extends Method> iterable) {
        if (this.onUnknownError == iterable) {
            return this;
        }
        return new ImmutableSetErrorActionsMethod(this.queue, this.forced, this.onTetheredDeviceNotReachable, this.onTimeout, this.onNotLoggedIn, this.onInOfflineMode, this.onCheckPhone, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableSetErrorActionsMethod) && equalTo(0, (ImmutableSetErrorActionsMethod) obj);
    }

    private boolean equalTo(int i, ImmutableSetErrorActionsMethod immutableSetErrorActionsMethod) {
        return this.queue.equals(immutableSetErrorActionsMethod.queue) && this.forced.equals(immutableSetErrorActionsMethod.forced) && this.onTetheredDeviceNotReachable.equals(immutableSetErrorActionsMethod.onTetheredDeviceNotReachable) && this.onTimeout.equals(immutableSetErrorActionsMethod.onTimeout) && this.onNotLoggedIn.equals(immutableSetErrorActionsMethod.onNotLoggedIn) && this.onInOfflineMode.equals(immutableSetErrorActionsMethod.onInOfflineMode) && this.onCheckPhone.equals(immutableSetErrorActionsMethod.onCheckPhone) && this.onUnknownError.equals(immutableSetErrorActionsMethod.onUnknownError);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.queue.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.forced.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.onTetheredDeviceNotReachable.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.onTimeout.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.onNotLoggedIn.hashCode();
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + this.onInOfflineMode.hashCode();
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + this.onCheckPhone.hashCode();
        return iHashCode7 + (iHashCode7 << 5) + this.onUnknownError.hashCode();
    }

    public String toString() {
        return "SetErrorActionsMethod{queue=" + this.queue + ", forced=" + this.forced + ", onTetheredDeviceNotReachable=" + this.onTetheredDeviceNotReachable + ", onTimeout=" + this.onTimeout + ", onNotLoggedIn=" + this.onNotLoggedIn + ", onInOfflineMode=" + this.onInOfflineMode + ", onCheckPhone=" + this.onCheckPhone + ", onUnknownError=" + this.onUnknownError + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "SetErrorActionsMethod", generator = "Immutables")
    static final class Json extends SetErrorActionsMethod {

        @Nullable
        Boolean forced;
        boolean onCheckPhoneIsSet;
        boolean onInOfflineModeIsSet;
        boolean onNotLoggedInIsSet;
        boolean onTetheredDeviceNotReachableIsSet;
        boolean onTimeoutIsSet;
        boolean onUnknownErrorIsSet;

        @Nullable
        Queue queue;

        @Nullable
        List<Method> onTetheredDeviceNotReachable = Collections.emptyList();

        @Nullable
        List<Method> onTimeout = Collections.emptyList();

        @Nullable
        List<Method> onNotLoggedIn = Collections.emptyList();

        @Nullable
        List<Method> onInOfflineMode = Collections.emptyList();

        @Nullable
        List<Method> onCheckPhone = Collections.emptyList();

        @Nullable
        List<Method> onUnknownError = Collections.emptyList();

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

        @JsonProperty("onTetheredDeviceNotReachable")
        public void setOnTetheredDeviceNotReachable(List<Method> list) {
            this.onTetheredDeviceNotReachable = list;
            this.onTetheredDeviceNotReachableIsSet = true;
        }

        @JsonProperty("onTimeout")
        public void setOnTimeout(List<Method> list) {
            this.onTimeout = list;
            this.onTimeoutIsSet = true;
        }

        @JsonProperty("onNotLoggedIn")
        public void setOnNotLoggedIn(List<Method> list) {
            this.onNotLoggedIn = list;
            this.onNotLoggedInIsSet = true;
        }

        @JsonProperty("onInOfflineMode")
        public void setOnInOfflineMode(List<Method> list) {
            this.onInOfflineMode = list;
            this.onInOfflineModeIsSet = true;
        }

        @JsonProperty("onCheckPhone")
        public void setOnCheckPhone(List<Method> list) {
            this.onCheckPhone = list;
            this.onCheckPhoneIsSet = true;
        }

        @JsonProperty("onUnknownError")
        public void setOnUnknownError(List<Method> list) {
            this.onUnknownError = list;
            this.onUnknownErrorIsSet = true;
        }

        @Override // CoreInterface.v1_0.Method
        public Queue queue() {
            throw new UnsupportedOperationException();
        }

        @Override // CoreInterface.v1_0.Method
        public Boolean forced() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.SetErrorActionsMethod
        public List<Method> onTetheredDeviceNotReachable() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.SetErrorActionsMethod
        public List<Method> onTimeout() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.SetErrorActionsMethod
        public List<Method> onNotLoggedIn() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.SetErrorActionsMethod
        public List<Method> onInOfflineMode() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.SetErrorActionsMethod
        public List<Method> onCheckPhone() {
            throw new UnsupportedOperationException();
        }

        @Override // Watch.TetheredInterface.v1_0.SetErrorActionsMethod
        public List<Method> onUnknownError() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableSetErrorActionsMethod fromJson(Json json) {
        Builder builder = builder();
        Queue queue = json.queue;
        if (queue != null) {
            builder.queue(queue);
        }
        Boolean bool = json.forced;
        if (bool != null) {
            builder.forced(bool);
        }
        if (json.onTetheredDeviceNotReachableIsSet) {
            builder.addAllOnTetheredDeviceNotReachable(json.onTetheredDeviceNotReachable);
        }
        if (json.onTimeoutIsSet) {
            builder.addAllOnTimeout(json.onTimeout);
        }
        if (json.onNotLoggedInIsSet) {
            builder.addAllOnNotLoggedIn(json.onNotLoggedIn);
        }
        if (json.onInOfflineModeIsSet) {
            builder.addAllOnInOfflineMode(json.onInOfflineMode);
        }
        if (json.onCheckPhoneIsSet) {
            builder.addAllOnCheckPhone(json.onCheckPhone);
        }
        if (json.onUnknownErrorIsSet) {
            builder.addAllOnUnknownError(json.onUnknownError);
        }
        return builder.build();
    }

    public static ImmutableSetErrorActionsMethod copyOf(SetErrorActionsMethod setErrorActionsMethod) {
        if (setErrorActionsMethod instanceof ImmutableSetErrorActionsMethod) {
            return (ImmutableSetErrorActionsMethod) setErrorActionsMethod;
        }
        return builder().from(setErrorActionsMethod).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "SetErrorActionsMethod", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long INIT_BIT_QUEUE = 1;
        private static final long OPT_BIT_ON_CHECK_PHONE = 16;
        private static final long OPT_BIT_ON_IN_OFFLINE_MODE = 8;
        private static final long OPT_BIT_ON_NOT_LOGGED_IN = 4;
        private static final long OPT_BIT_ON_TETHERED_DEVICE_NOT_REACHABLE = 1;
        private static final long OPT_BIT_ON_TIMEOUT = 2;
        private static final long OPT_BIT_ON_UNKNOWN_ERROR = 32;

        @Nullable
        private Boolean forced;
        private long initBits;
        private List<Method> onCheckPhone;
        private List<Method> onInOfflineMode;
        private List<Method> onNotLoggedIn;
        private List<Method> onTetheredDeviceNotReachable;
        private List<Method> onTimeout;
        private List<Method> onUnknownError;
        private long optBits;

        @Nullable
        private Queue queue;

        private Builder() {
            this.initBits = 1L;
            this.onTetheredDeviceNotReachable = new ArrayList();
            this.onTimeout = new ArrayList();
            this.onNotLoggedIn = new ArrayList();
            this.onInOfflineMode = new ArrayList();
            this.onCheckPhone = new ArrayList();
            this.onUnknownError = new ArrayList();
        }

        public final Builder from(Method method) {
            ImmutableSetErrorActionsMethod.requireNonNull(method, "instance");
            from((Object) method);
            return this;
        }

        public final Builder from(SetErrorActionsMethod setErrorActionsMethod) {
            ImmutableSetErrorActionsMethod.requireNonNull(setErrorActionsMethod, "instance");
            from((Object) setErrorActionsMethod);
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
            if (obj instanceof SetErrorActionsMethod) {
                SetErrorActionsMethod setErrorActionsMethod = (SetErrorActionsMethod) obj;
                addAllOnInOfflineMode(setErrorActionsMethod.onInOfflineMode());
                addAllOnNotLoggedIn(setErrorActionsMethod.onNotLoggedIn());
                addAllOnTetheredDeviceNotReachable(setErrorActionsMethod.onTetheredDeviceNotReachable());
                if ((j & 1) == 0) {
                    forced(setErrorActionsMethod.forced());
                    j |= 1;
                }
                addAllOnTimeout(setErrorActionsMethod.onTimeout());
                addAllOnCheckPhone(setErrorActionsMethod.onCheckPhone());
                addAllOnUnknownError(setErrorActionsMethod.onUnknownError());
                if ((j & 2) == 0) {
                    queue(setErrorActionsMethod.queue());
                }
            }
        }

        @JsonProperty("queue")
        public final Builder queue(Queue queue) {
            this.queue = (Queue) ImmutableSetErrorActionsMethod.requireNonNull(queue, "queue");
            this.initBits &= -2;
            return this;
        }

        @JsonProperty("forced")
        public final Builder forced(Boolean bool) {
            this.forced = (Boolean) ImmutableSetErrorActionsMethod.requireNonNull(bool, "forced");
            return this;
        }

        public final Builder addOnTetheredDeviceNotReachable(Method method) {
            this.onTetheredDeviceNotReachable.add((Method) ImmutableSetErrorActionsMethod.requireNonNull(method, "onTetheredDeviceNotReachable element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnTetheredDeviceNotReachable(Method... methodArr) {
            for (Method method : methodArr) {
                this.onTetheredDeviceNotReachable.add((Method) ImmutableSetErrorActionsMethod.requireNonNull(method, "onTetheredDeviceNotReachable element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onTetheredDeviceNotReachable")
        public final Builder onTetheredDeviceNotReachable(Iterable<? extends Method> iterable) {
            this.onTetheredDeviceNotReachable.clear();
            return addAllOnTetheredDeviceNotReachable(iterable);
        }

        public final Builder addAllOnTetheredDeviceNotReachable(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onTetheredDeviceNotReachable.add((Method) ImmutableSetErrorActionsMethod.requireNonNull(it.next(), "onTetheredDeviceNotReachable element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnTimeout(Method method) {
            this.onTimeout.add((Method) ImmutableSetErrorActionsMethod.requireNonNull(method, "onTimeout element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnTimeout(Method... methodArr) {
            for (Method method : methodArr) {
                this.onTimeout.add((Method) ImmutableSetErrorActionsMethod.requireNonNull(method, "onTimeout element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("onTimeout")
        public final Builder onTimeout(Iterable<? extends Method> iterable) {
            this.onTimeout.clear();
            return addAllOnTimeout(iterable);
        }

        public final Builder addAllOnTimeout(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onTimeout.add((Method) ImmutableSetErrorActionsMethod.requireNonNull(it.next(), "onTimeout element"));
            }
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnNotLoggedIn(Method method) {
            this.onNotLoggedIn.add((Method) ImmutableSetErrorActionsMethod.requireNonNull(method, "onNotLoggedIn element"));
            this.optBits |= 4;
            return this;
        }

        public final Builder addOnNotLoggedIn(Method... methodArr) {
            for (Method method : methodArr) {
                this.onNotLoggedIn.add((Method) ImmutableSetErrorActionsMethod.requireNonNull(method, "onNotLoggedIn element"));
            }
            this.optBits |= 4;
            return this;
        }

        @JsonProperty("onNotLoggedIn")
        public final Builder onNotLoggedIn(Iterable<? extends Method> iterable) {
            this.onNotLoggedIn.clear();
            return addAllOnNotLoggedIn(iterable);
        }

        public final Builder addAllOnNotLoggedIn(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onNotLoggedIn.add((Method) ImmutableSetErrorActionsMethod.requireNonNull(it.next(), "onNotLoggedIn element"));
            }
            this.optBits |= 4;
            return this;
        }

        public final Builder addOnInOfflineMode(Method method) {
            this.onInOfflineMode.add((Method) ImmutableSetErrorActionsMethod.requireNonNull(method, "onInOfflineMode element"));
            this.optBits |= 8;
            return this;
        }

        public final Builder addOnInOfflineMode(Method... methodArr) {
            for (Method method : methodArr) {
                this.onInOfflineMode.add((Method) ImmutableSetErrorActionsMethod.requireNonNull(method, "onInOfflineMode element"));
            }
            this.optBits |= 8;
            return this;
        }

        @JsonProperty("onInOfflineMode")
        public final Builder onInOfflineMode(Iterable<? extends Method> iterable) {
            this.onInOfflineMode.clear();
            return addAllOnInOfflineMode(iterable);
        }

        public final Builder addAllOnInOfflineMode(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onInOfflineMode.add((Method) ImmutableSetErrorActionsMethod.requireNonNull(it.next(), "onInOfflineMode element"));
            }
            this.optBits |= 8;
            return this;
        }

        public final Builder addOnCheckPhone(Method method) {
            this.onCheckPhone.add((Method) ImmutableSetErrorActionsMethod.requireNonNull(method, "onCheckPhone element"));
            this.optBits |= 16;
            return this;
        }

        public final Builder addOnCheckPhone(Method... methodArr) {
            for (Method method : methodArr) {
                this.onCheckPhone.add((Method) ImmutableSetErrorActionsMethod.requireNonNull(method, "onCheckPhone element"));
            }
            this.optBits |= 16;
            return this;
        }

        @JsonProperty("onCheckPhone")
        public final Builder onCheckPhone(Iterable<? extends Method> iterable) {
            this.onCheckPhone.clear();
            return addAllOnCheckPhone(iterable);
        }

        public final Builder addAllOnCheckPhone(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onCheckPhone.add((Method) ImmutableSetErrorActionsMethod.requireNonNull(it.next(), "onCheckPhone element"));
            }
            this.optBits |= 16;
            return this;
        }

        public final Builder addOnUnknownError(Method method) {
            this.onUnknownError.add((Method) ImmutableSetErrorActionsMethod.requireNonNull(method, "onUnknownError element"));
            this.optBits |= 32;
            return this;
        }

        public final Builder addOnUnknownError(Method... methodArr) {
            for (Method method : methodArr) {
                this.onUnknownError.add((Method) ImmutableSetErrorActionsMethod.requireNonNull(method, "onUnknownError element"));
            }
            this.optBits |= 32;
            return this;
        }

        @JsonProperty("onUnknownError")
        public final Builder onUnknownError(Iterable<? extends Method> iterable) {
            this.onUnknownError.clear();
            return addAllOnUnknownError(iterable);
        }

        public final Builder addAllOnUnknownError(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onUnknownError.add((Method) ImmutableSetErrorActionsMethod.requireNonNull(it.next(), "onUnknownError element"));
            }
            this.optBits |= 32;
            return this;
        }

        public ImmutableSetErrorActionsMethod build() {
            if (this.initBits != 0) {
                throw new IllegalStateException(formatRequiredAttributesMessage());
            }
            return new ImmutableSetErrorActionsMethod(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onTetheredDeviceNotReachableIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onTimeoutIsSet() {
            return (this.optBits & 2) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onNotLoggedInIsSet() {
            return (this.optBits & 4) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onInOfflineModeIsSet() {
            return (this.optBits & 8) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onCheckPhoneIsSet() {
            return (this.optBits & 16) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onUnknownErrorIsSet() {
            return (this.optBits & 32) != 0;
        }

        private String formatRequiredAttributesMessage() {
            ArrayList arrayList = new ArrayList();
            if ((this.initBits & 1) != 0) {
                arrayList.add("queue");
            }
            return "Cannot build SetErrorActionsMethod, some of required attributes are not set " + arrayList;
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
