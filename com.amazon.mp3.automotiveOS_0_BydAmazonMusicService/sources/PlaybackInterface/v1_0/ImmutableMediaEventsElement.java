package PlaybackInterface.v1_0;

import CoreInterface.v1_0.Method;
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
@Generated(from = "MediaEventsElement", generator = "Immutables")
public final class ImmutableMediaEventsElement extends MediaEventsElement {
    private static final byte STAGE_INITIALIZED = 1;
    private static final byte STAGE_INITIALIZING = -1;
    private static final byte STAGE_UNINITIALIZED = 0;
    private volatile transient InitShim initShim;
    private final List<Method> onCompletedThresholdElapsed;
    private final List<Method> onError;
    private final List<Method> onFinished;
    private final List<Method> onLicenseRequired;
    private final List<Method> onPaused;
    private final List<Method> onRebuffered;
    private final List<Method> onResumed;
    private final List<Method> onScrubbed;
    private final List<Method> onStarted;
    private final List<Method> onStopped;

    private ImmutableMediaEventsElement(Builder builder) {
        this.initShim = new InitShim();
        if (builder.onLicenseRequiredIsSet()) {
            this.initShim.onLicenseRequired(createUnmodifiableList(true, builder.onLicenseRequired));
        }
        if (builder.onStartedIsSet()) {
            this.initShim.onStarted(createUnmodifiableList(true, builder.onStarted));
        }
        if (builder.onResumedIsSet()) {
            this.initShim.onResumed(createUnmodifiableList(true, builder.onResumed));
        }
        if (builder.onScrubbedIsSet()) {
            this.initShim.onScrubbed(createUnmodifiableList(true, builder.onScrubbed));
        }
        if (builder.onPausedIsSet()) {
            this.initShim.onPaused(createUnmodifiableList(true, builder.onPaused));
        }
        if (builder.onFinishedIsSet()) {
            this.initShim.onFinished(createUnmodifiableList(true, builder.onFinished));
        }
        if (builder.onStoppedIsSet()) {
            this.initShim.onStopped(createUnmodifiableList(true, builder.onStopped));
        }
        if (builder.onCompletedThresholdElapsedIsSet()) {
            this.initShim.onCompletedThresholdElapsed(createUnmodifiableList(true, builder.onCompletedThresholdElapsed));
        }
        if (builder.onRebufferedIsSet()) {
            this.initShim.onRebuffered(createUnmodifiableList(true, builder.onRebuffered));
        }
        if (builder.onErrorIsSet()) {
            this.initShim.onError(createUnmodifiableList(true, builder.onError));
        }
        this.onLicenseRequired = this.initShim.onLicenseRequired();
        this.onStarted = this.initShim.onStarted();
        this.onResumed = this.initShim.onResumed();
        this.onScrubbed = this.initShim.onScrubbed();
        this.onPaused = this.initShim.onPaused();
        this.onFinished = this.initShim.onFinished();
        this.onStopped = this.initShim.onStopped();
        this.onCompletedThresholdElapsed = this.initShim.onCompletedThresholdElapsed();
        this.onRebuffered = this.initShim.onRebuffered();
        this.onError = this.initShim.onError();
        this.initShim = null;
    }

    private ImmutableMediaEventsElement(List<Method> list, List<Method> list2, List<Method> list3, List<Method> list4, List<Method> list5, List<Method> list6, List<Method> list7, List<Method> list8, List<Method> list9, List<Method> list10) {
        this.initShim = new InitShim();
        this.onLicenseRequired = list;
        this.onStarted = list2;
        this.onResumed = list3;
        this.onScrubbed = list4;
        this.onPaused = list5;
        this.onFinished = list6;
        this.onStopped = list7;
        this.onCompletedThresholdElapsed = list8;
        this.onRebuffered = list9;
        this.onError = list10;
        this.initShim = null;
    }

    @Generated(from = "MediaEventsElement", generator = "Immutables")
    private final class InitShim {
        private List<Method> onCompletedThresholdElapsed;
        private byte onCompletedThresholdElapsedBuildStage;
        private List<Method> onError;
        private byte onErrorBuildStage;
        private List<Method> onFinished;
        private byte onFinishedBuildStage;
        private List<Method> onLicenseRequired;
        private byte onLicenseRequiredBuildStage;
        private List<Method> onPaused;
        private byte onPausedBuildStage;
        private List<Method> onRebuffered;
        private byte onRebufferedBuildStage;
        private List<Method> onResumed;
        private byte onResumedBuildStage;
        private List<Method> onScrubbed;
        private byte onScrubbedBuildStage;
        private List<Method> onStarted;
        private byte onStartedBuildStage;
        private List<Method> onStopped;
        private byte onStoppedBuildStage;

        private InitShim() {
            this.onLicenseRequiredBuildStage = (byte) 0;
            this.onStartedBuildStage = (byte) 0;
            this.onResumedBuildStage = (byte) 0;
            this.onScrubbedBuildStage = (byte) 0;
            this.onPausedBuildStage = (byte) 0;
            this.onFinishedBuildStage = (byte) 0;
            this.onStoppedBuildStage = (byte) 0;
            this.onCompletedThresholdElapsedBuildStage = (byte) 0;
            this.onRebufferedBuildStage = (byte) 0;
            this.onErrorBuildStage = (byte) 0;
        }

        List<Method> onLicenseRequired() {
            byte b = this.onLicenseRequiredBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onLicenseRequiredBuildStage = (byte) -1;
                this.onLicenseRequired = ImmutableMediaEventsElement.createUnmodifiableList(false, ImmutableMediaEventsElement.createSafeList(ImmutableMediaEventsElement.super.onLicenseRequired(), true, false));
                this.onLicenseRequiredBuildStage = (byte) 1;
            }
            return this.onLicenseRequired;
        }

        void onLicenseRequired(List<Method> list) {
            this.onLicenseRequired = list;
            this.onLicenseRequiredBuildStage = (byte) 1;
        }

        List<Method> onStarted() {
            byte b = this.onStartedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onStartedBuildStage = (byte) -1;
                this.onStarted = ImmutableMediaEventsElement.createUnmodifiableList(false, ImmutableMediaEventsElement.createSafeList(ImmutableMediaEventsElement.super.onStarted(), true, false));
                this.onStartedBuildStage = (byte) 1;
            }
            return this.onStarted;
        }

        void onStarted(List<Method> list) {
            this.onStarted = list;
            this.onStartedBuildStage = (byte) 1;
        }

        List<Method> onResumed() {
            byte b = this.onResumedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onResumedBuildStage = (byte) -1;
                this.onResumed = ImmutableMediaEventsElement.createUnmodifiableList(false, ImmutableMediaEventsElement.createSafeList(ImmutableMediaEventsElement.super.onResumed(), true, false));
                this.onResumedBuildStage = (byte) 1;
            }
            return this.onResumed;
        }

        void onResumed(List<Method> list) {
            this.onResumed = list;
            this.onResumedBuildStage = (byte) 1;
        }

        List<Method> onScrubbed() {
            byte b = this.onScrubbedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onScrubbedBuildStage = (byte) -1;
                this.onScrubbed = ImmutableMediaEventsElement.createUnmodifiableList(false, ImmutableMediaEventsElement.createSafeList(ImmutableMediaEventsElement.super.onScrubbed(), true, false));
                this.onScrubbedBuildStage = (byte) 1;
            }
            return this.onScrubbed;
        }

        void onScrubbed(List<Method> list) {
            this.onScrubbed = list;
            this.onScrubbedBuildStage = (byte) 1;
        }

        List<Method> onPaused() {
            byte b = this.onPausedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onPausedBuildStage = (byte) -1;
                this.onPaused = ImmutableMediaEventsElement.createUnmodifiableList(false, ImmutableMediaEventsElement.createSafeList(ImmutableMediaEventsElement.super.onPaused(), true, false));
                this.onPausedBuildStage = (byte) 1;
            }
            return this.onPaused;
        }

        void onPaused(List<Method> list) {
            this.onPaused = list;
            this.onPausedBuildStage = (byte) 1;
        }

        List<Method> onFinished() {
            byte b = this.onFinishedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onFinishedBuildStage = (byte) -1;
                this.onFinished = ImmutableMediaEventsElement.createUnmodifiableList(false, ImmutableMediaEventsElement.createSafeList(ImmutableMediaEventsElement.super.onFinished(), true, false));
                this.onFinishedBuildStage = (byte) 1;
            }
            return this.onFinished;
        }

        void onFinished(List<Method> list) {
            this.onFinished = list;
            this.onFinishedBuildStage = (byte) 1;
        }

        List<Method> onStopped() {
            byte b = this.onStoppedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onStoppedBuildStage = (byte) -1;
                this.onStopped = ImmutableMediaEventsElement.createUnmodifiableList(false, ImmutableMediaEventsElement.createSafeList(ImmutableMediaEventsElement.super.onStopped(), true, false));
                this.onStoppedBuildStage = (byte) 1;
            }
            return this.onStopped;
        }

        void onStopped(List<Method> list) {
            this.onStopped = list;
            this.onStoppedBuildStage = (byte) 1;
        }

        List<Method> onCompletedThresholdElapsed() {
            byte b = this.onCompletedThresholdElapsedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onCompletedThresholdElapsedBuildStage = (byte) -1;
                this.onCompletedThresholdElapsed = ImmutableMediaEventsElement.createUnmodifiableList(false, ImmutableMediaEventsElement.createSafeList(ImmutableMediaEventsElement.super.onCompletedThresholdElapsed(), true, false));
                this.onCompletedThresholdElapsedBuildStage = (byte) 1;
            }
            return this.onCompletedThresholdElapsed;
        }

        void onCompletedThresholdElapsed(List<Method> list) {
            this.onCompletedThresholdElapsed = list;
            this.onCompletedThresholdElapsedBuildStage = (byte) 1;
        }

        List<Method> onRebuffered() {
            byte b = this.onRebufferedBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onRebufferedBuildStage = (byte) -1;
                this.onRebuffered = ImmutableMediaEventsElement.createUnmodifiableList(false, ImmutableMediaEventsElement.createSafeList(ImmutableMediaEventsElement.super.onRebuffered(), true, false));
                this.onRebufferedBuildStage = (byte) 1;
            }
            return this.onRebuffered;
        }

        void onRebuffered(List<Method> list) {
            this.onRebuffered = list;
            this.onRebufferedBuildStage = (byte) 1;
        }

        List<Method> onError() {
            byte b = this.onErrorBuildStage;
            if (b == -1) {
                throw new IllegalStateException(formatInitCycleMessage());
            }
            if (b == 0) {
                this.onErrorBuildStage = (byte) -1;
                this.onError = ImmutableMediaEventsElement.createUnmodifiableList(false, ImmutableMediaEventsElement.createSafeList(ImmutableMediaEventsElement.super.onError(), true, false));
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
            if (this.onLicenseRequiredBuildStage == -1) {
                arrayList.add("onLicenseRequired");
            }
            if (this.onStartedBuildStage == -1) {
                arrayList.add("onStarted");
            }
            if (this.onResumedBuildStage == -1) {
                arrayList.add("onResumed");
            }
            if (this.onScrubbedBuildStage == -1) {
                arrayList.add("onScrubbed");
            }
            if (this.onPausedBuildStage == -1) {
                arrayList.add("onPaused");
            }
            if (this.onFinishedBuildStage == -1) {
                arrayList.add("onFinished");
            }
            if (this.onStoppedBuildStage == -1) {
                arrayList.add("onStopped");
            }
            if (this.onCompletedThresholdElapsedBuildStage == -1) {
                arrayList.add("onCompletedThresholdElapsed");
            }
            if (this.onRebufferedBuildStage == -1) {
                arrayList.add("onRebuffered");
            }
            if (this.onErrorBuildStage == -1) {
                arrayList.add("onError");
            }
            return "Cannot build MediaEventsElement, attribute initializers form cycle " + arrayList;
        }
    }

    @Override // PlaybackInterface.v1_0.MediaEventsElement
    @JsonProperty("onLicenseRequired")
    public List<Method> onLicenseRequired() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onLicenseRequired();
        }
        return this.onLicenseRequired;
    }

    @Override // PlaybackInterface.v1_0.MediaEventsElement
    @JsonProperty("onStarted")
    public List<Method> onStarted() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onStarted();
        }
        return this.onStarted;
    }

    @Override // PlaybackInterface.v1_0.MediaEventsElement
    @JsonProperty("onResumed")
    public List<Method> onResumed() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onResumed();
        }
        return this.onResumed;
    }

    @Override // PlaybackInterface.v1_0.MediaEventsElement
    @JsonProperty("onScrubbed")
    public List<Method> onScrubbed() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onScrubbed();
        }
        return this.onScrubbed;
    }

    @Override // PlaybackInterface.v1_0.MediaEventsElement
    @JsonProperty("onPaused")
    public List<Method> onPaused() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onPaused();
        }
        return this.onPaused;
    }

    @Override // PlaybackInterface.v1_0.MediaEventsElement
    @JsonProperty("onFinished")
    public List<Method> onFinished() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onFinished();
        }
        return this.onFinished;
    }

    @Override // PlaybackInterface.v1_0.MediaEventsElement
    @JsonProperty("onStopped")
    public List<Method> onStopped() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onStopped();
        }
        return this.onStopped;
    }

    @Override // PlaybackInterface.v1_0.MediaEventsElement
    @JsonProperty("onCompletedThresholdElapsed")
    public List<Method> onCompletedThresholdElapsed() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onCompletedThresholdElapsed();
        }
        return this.onCompletedThresholdElapsed;
    }

    @Override // PlaybackInterface.v1_0.MediaEventsElement
    @JsonProperty("onRebuffered")
    public List<Method> onRebuffered() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onRebuffered();
        }
        return this.onRebuffered;
    }

    @Override // PlaybackInterface.v1_0.MediaEventsElement
    @JsonProperty("onError")
    public List<Method> onError() {
        InitShim initShim = this.initShim;
        if (initShim != null) {
            return initShim.onError();
        }
        return this.onError;
    }

    public final ImmutableMediaEventsElement withOnLicenseRequired(Method... methodArr) {
        return new ImmutableMediaEventsElement(createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onStarted, this.onResumed, this.onScrubbed, this.onPaused, this.onFinished, this.onStopped, this.onCompletedThresholdElapsed, this.onRebuffered, this.onError);
    }

    public final ImmutableMediaEventsElement withOnLicenseRequired(Iterable<? extends Method> iterable) {
        return this.onLicenseRequired == iterable ? this : new ImmutableMediaEventsElement(createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onStarted, this.onResumed, this.onScrubbed, this.onPaused, this.onFinished, this.onStopped, this.onCompletedThresholdElapsed, this.onRebuffered, this.onError);
    }

    public final ImmutableMediaEventsElement withOnStarted(Method... methodArr) {
        return new ImmutableMediaEventsElement(this.onLicenseRequired, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onResumed, this.onScrubbed, this.onPaused, this.onFinished, this.onStopped, this.onCompletedThresholdElapsed, this.onRebuffered, this.onError);
    }

    public final ImmutableMediaEventsElement withOnStarted(Iterable<? extends Method> iterable) {
        if (this.onStarted == iterable) {
            return this;
        }
        return new ImmutableMediaEventsElement(this.onLicenseRequired, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onResumed, this.onScrubbed, this.onPaused, this.onFinished, this.onStopped, this.onCompletedThresholdElapsed, this.onRebuffered, this.onError);
    }

    public final ImmutableMediaEventsElement withOnResumed(Method... methodArr) {
        return new ImmutableMediaEventsElement(this.onLicenseRequired, this.onStarted, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onScrubbed, this.onPaused, this.onFinished, this.onStopped, this.onCompletedThresholdElapsed, this.onRebuffered, this.onError);
    }

    public final ImmutableMediaEventsElement withOnResumed(Iterable<? extends Method> iterable) {
        if (this.onResumed == iterable) {
            return this;
        }
        return new ImmutableMediaEventsElement(this.onLicenseRequired, this.onStarted, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onScrubbed, this.onPaused, this.onFinished, this.onStopped, this.onCompletedThresholdElapsed, this.onRebuffered, this.onError);
    }

    public final ImmutableMediaEventsElement withOnScrubbed(Method... methodArr) {
        return new ImmutableMediaEventsElement(this.onLicenseRequired, this.onStarted, this.onResumed, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onPaused, this.onFinished, this.onStopped, this.onCompletedThresholdElapsed, this.onRebuffered, this.onError);
    }

    public final ImmutableMediaEventsElement withOnScrubbed(Iterable<? extends Method> iterable) {
        if (this.onScrubbed == iterable) {
            return this;
        }
        return new ImmutableMediaEventsElement(this.onLicenseRequired, this.onStarted, this.onResumed, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onPaused, this.onFinished, this.onStopped, this.onCompletedThresholdElapsed, this.onRebuffered, this.onError);
    }

    public final ImmutableMediaEventsElement withOnPaused(Method... methodArr) {
        return new ImmutableMediaEventsElement(this.onLicenseRequired, this.onStarted, this.onResumed, this.onScrubbed, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onFinished, this.onStopped, this.onCompletedThresholdElapsed, this.onRebuffered, this.onError);
    }

    public final ImmutableMediaEventsElement withOnPaused(Iterable<? extends Method> iterable) {
        if (this.onPaused == iterable) {
            return this;
        }
        return new ImmutableMediaEventsElement(this.onLicenseRequired, this.onStarted, this.onResumed, this.onScrubbed, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onFinished, this.onStopped, this.onCompletedThresholdElapsed, this.onRebuffered, this.onError);
    }

    public final ImmutableMediaEventsElement withOnFinished(Method... methodArr) {
        return new ImmutableMediaEventsElement(this.onLicenseRequired, this.onStarted, this.onResumed, this.onScrubbed, this.onPaused, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onStopped, this.onCompletedThresholdElapsed, this.onRebuffered, this.onError);
    }

    public final ImmutableMediaEventsElement withOnFinished(Iterable<? extends Method> iterable) {
        if (this.onFinished == iterable) {
            return this;
        }
        return new ImmutableMediaEventsElement(this.onLicenseRequired, this.onStarted, this.onResumed, this.onScrubbed, this.onPaused, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onStopped, this.onCompletedThresholdElapsed, this.onRebuffered, this.onError);
    }

    public final ImmutableMediaEventsElement withOnStopped(Method... methodArr) {
        return new ImmutableMediaEventsElement(this.onLicenseRequired, this.onStarted, this.onResumed, this.onScrubbed, this.onPaused, this.onFinished, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onCompletedThresholdElapsed, this.onRebuffered, this.onError);
    }

    public final ImmutableMediaEventsElement withOnStopped(Iterable<? extends Method> iterable) {
        if (this.onStopped == iterable) {
            return this;
        }
        return new ImmutableMediaEventsElement(this.onLicenseRequired, this.onStarted, this.onResumed, this.onScrubbed, this.onPaused, this.onFinished, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onCompletedThresholdElapsed, this.onRebuffered, this.onError);
    }

    public final ImmutableMediaEventsElement withOnCompletedThresholdElapsed(Method... methodArr) {
        return new ImmutableMediaEventsElement(this.onLicenseRequired, this.onStarted, this.onResumed, this.onScrubbed, this.onPaused, this.onFinished, this.onStopped, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onRebuffered, this.onError);
    }

    public final ImmutableMediaEventsElement withOnCompletedThresholdElapsed(Iterable<? extends Method> iterable) {
        if (this.onCompletedThresholdElapsed == iterable) {
            return this;
        }
        return new ImmutableMediaEventsElement(this.onLicenseRequired, this.onStarted, this.onResumed, this.onScrubbed, this.onPaused, this.onFinished, this.onStopped, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onRebuffered, this.onError);
    }

    public final ImmutableMediaEventsElement withOnRebuffered(Method... methodArr) {
        return new ImmutableMediaEventsElement(this.onLicenseRequired, this.onStarted, this.onResumed, this.onScrubbed, this.onPaused, this.onFinished, this.onStopped, this.onCompletedThresholdElapsed, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)), this.onError);
    }

    public final ImmutableMediaEventsElement withOnRebuffered(Iterable<? extends Method> iterable) {
        if (this.onRebuffered == iterable) {
            return this;
        }
        return new ImmutableMediaEventsElement(this.onLicenseRequired, this.onStarted, this.onResumed, this.onScrubbed, this.onPaused, this.onFinished, this.onStopped, this.onCompletedThresholdElapsed, createUnmodifiableList(false, createSafeList(iterable, true, false)), this.onError);
    }

    public final ImmutableMediaEventsElement withOnError(Method... methodArr) {
        return new ImmutableMediaEventsElement(this.onLicenseRequired, this.onStarted, this.onResumed, this.onScrubbed, this.onPaused, this.onFinished, this.onStopped, this.onCompletedThresholdElapsed, this.onRebuffered, createUnmodifiableList(false, createSafeList(Arrays.asList(methodArr), true, false)));
    }

    public final ImmutableMediaEventsElement withOnError(Iterable<? extends Method> iterable) {
        if (this.onError == iterable) {
            return this;
        }
        return new ImmutableMediaEventsElement(this.onLicenseRequired, this.onStarted, this.onResumed, this.onScrubbed, this.onPaused, this.onFinished, this.onStopped, this.onCompletedThresholdElapsed, this.onRebuffered, createUnmodifiableList(false, createSafeList(iterable, true, false)));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ImmutableMediaEventsElement) && equalTo(0, (ImmutableMediaEventsElement) obj);
    }

    private boolean equalTo(int i, ImmutableMediaEventsElement immutableMediaEventsElement) {
        return this.onLicenseRequired.equals(immutableMediaEventsElement.onLicenseRequired) && this.onStarted.equals(immutableMediaEventsElement.onStarted) && this.onResumed.equals(immutableMediaEventsElement.onResumed) && this.onScrubbed.equals(immutableMediaEventsElement.onScrubbed) && this.onPaused.equals(immutableMediaEventsElement.onPaused) && this.onFinished.equals(immutableMediaEventsElement.onFinished) && this.onStopped.equals(immutableMediaEventsElement.onStopped) && this.onCompletedThresholdElapsed.equals(immutableMediaEventsElement.onCompletedThresholdElapsed) && this.onRebuffered.equals(immutableMediaEventsElement.onRebuffered) && this.onError.equals(immutableMediaEventsElement.onError);
    }

    public int hashCode() {
        int iHashCode = 172192 + this.onLicenseRequired.hashCode() + 5381;
        int iHashCode2 = iHashCode + (iHashCode << 5) + this.onStarted.hashCode();
        int iHashCode3 = iHashCode2 + (iHashCode2 << 5) + this.onResumed.hashCode();
        int iHashCode4 = iHashCode3 + (iHashCode3 << 5) + this.onScrubbed.hashCode();
        int iHashCode5 = iHashCode4 + (iHashCode4 << 5) + this.onPaused.hashCode();
        int iHashCode6 = iHashCode5 + (iHashCode5 << 5) + this.onFinished.hashCode();
        int iHashCode7 = iHashCode6 + (iHashCode6 << 5) + this.onStopped.hashCode();
        int iHashCode8 = iHashCode7 + (iHashCode7 << 5) + this.onCompletedThresholdElapsed.hashCode();
        int iHashCode9 = iHashCode8 + (iHashCode8 << 5) + this.onRebuffered.hashCode();
        return iHashCode9 + (iHashCode9 << 5) + this.onError.hashCode();
    }

    public String toString() {
        return "MediaEventsElement{onLicenseRequired=" + this.onLicenseRequired + ", onStarted=" + this.onStarted + ", onResumed=" + this.onResumed + ", onScrubbed=" + this.onScrubbed + ", onPaused=" + this.onPaused + ", onFinished=" + this.onFinished + ", onStopped=" + this.onStopped + ", onCompletedThresholdElapsed=" + this.onCompletedThresholdElapsed + ", onRebuffered=" + this.onRebuffered + ", onError=" + this.onError + "}";
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    @JsonDeserialize
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
    @Deprecated
    @Generated(from = "MediaEventsElement", generator = "Immutables")
    static final class Json extends MediaEventsElement {
        boolean onCompletedThresholdElapsedIsSet;
        boolean onErrorIsSet;
        boolean onFinishedIsSet;
        boolean onLicenseRequiredIsSet;
        boolean onPausedIsSet;
        boolean onRebufferedIsSet;
        boolean onResumedIsSet;
        boolean onScrubbedIsSet;
        boolean onStartedIsSet;
        boolean onStoppedIsSet;

        @Nullable
        List<Method> onLicenseRequired = Collections.emptyList();

        @Nullable
        List<Method> onStarted = Collections.emptyList();

        @Nullable
        List<Method> onResumed = Collections.emptyList();

        @Nullable
        List<Method> onScrubbed = Collections.emptyList();

        @Nullable
        List<Method> onPaused = Collections.emptyList();

        @Nullable
        List<Method> onFinished = Collections.emptyList();

        @Nullable
        List<Method> onStopped = Collections.emptyList();

        @Nullable
        List<Method> onCompletedThresholdElapsed = Collections.emptyList();

        @Nullable
        List<Method> onRebuffered = Collections.emptyList();

        @Nullable
        List<Method> onError = Collections.emptyList();

        Json() {
        }

        @JsonProperty("onLicenseRequired")
        public void setOnLicenseRequired(List<Method> list) {
            this.onLicenseRequired = list;
            this.onLicenseRequiredIsSet = true;
        }

        @JsonProperty("onStarted")
        public void setOnStarted(List<Method> list) {
            this.onStarted = list;
            this.onStartedIsSet = true;
        }

        @JsonProperty("onResumed")
        public void setOnResumed(List<Method> list) {
            this.onResumed = list;
            this.onResumedIsSet = true;
        }

        @JsonProperty("onScrubbed")
        public void setOnScrubbed(List<Method> list) {
            this.onScrubbed = list;
            this.onScrubbedIsSet = true;
        }

        @JsonProperty("onPaused")
        public void setOnPaused(List<Method> list) {
            this.onPaused = list;
            this.onPausedIsSet = true;
        }

        @JsonProperty("onFinished")
        public void setOnFinished(List<Method> list) {
            this.onFinished = list;
            this.onFinishedIsSet = true;
        }

        @JsonProperty("onStopped")
        public void setOnStopped(List<Method> list) {
            this.onStopped = list;
            this.onStoppedIsSet = true;
        }

        @JsonProperty("onCompletedThresholdElapsed")
        public void setOnCompletedThresholdElapsed(List<Method> list) {
            this.onCompletedThresholdElapsed = list;
            this.onCompletedThresholdElapsedIsSet = true;
        }

        @JsonProperty("onRebuffered")
        public void setOnRebuffered(List<Method> list) {
            this.onRebuffered = list;
            this.onRebufferedIsSet = true;
        }

        @JsonProperty("onError")
        public void setOnError(List<Method> list) {
            this.onError = list;
            this.onErrorIsSet = true;
        }

        @Override // PlaybackInterface.v1_0.MediaEventsElement
        public List<Method> onLicenseRequired() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaEventsElement
        public List<Method> onStarted() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaEventsElement
        public List<Method> onResumed() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaEventsElement
        public List<Method> onScrubbed() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaEventsElement
        public List<Method> onPaused() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaEventsElement
        public List<Method> onFinished() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaEventsElement
        public List<Method> onStopped() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaEventsElement
        public List<Method> onCompletedThresholdElapsed() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaEventsElement
        public List<Method> onRebuffered() {
            throw new UnsupportedOperationException();
        }

        @Override // PlaybackInterface.v1_0.MediaEventsElement
        public List<Method> onError() {
            throw new UnsupportedOperationException();
        }
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @Deprecated
    static ImmutableMediaEventsElement fromJson(Json json) {
        Builder builder = builder();
        if (json.onLicenseRequiredIsSet) {
            builder.addAllOnLicenseRequired(json.onLicenseRequired);
        }
        if (json.onStartedIsSet) {
            builder.addAllOnStarted(json.onStarted);
        }
        if (json.onResumedIsSet) {
            builder.addAllOnResumed(json.onResumed);
        }
        if (json.onScrubbedIsSet) {
            builder.addAllOnScrubbed(json.onScrubbed);
        }
        if (json.onPausedIsSet) {
            builder.addAllOnPaused(json.onPaused);
        }
        if (json.onFinishedIsSet) {
            builder.addAllOnFinished(json.onFinished);
        }
        if (json.onStoppedIsSet) {
            builder.addAllOnStopped(json.onStopped);
        }
        if (json.onCompletedThresholdElapsedIsSet) {
            builder.addAllOnCompletedThresholdElapsed(json.onCompletedThresholdElapsed);
        }
        if (json.onRebufferedIsSet) {
            builder.addAllOnRebuffered(json.onRebuffered);
        }
        if (json.onErrorIsSet) {
            builder.addAllOnError(json.onError);
        }
        return builder.build();
    }

    public static ImmutableMediaEventsElement copyOf(MediaEventsElement mediaEventsElement) {
        if (mediaEventsElement instanceof ImmutableMediaEventsElement) {
            return (ImmutableMediaEventsElement) mediaEventsElement;
        }
        return builder().from(mediaEventsElement).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Generated(from = "MediaEventsElement", generator = "Immutables")
    @NotThreadSafe
    public static final class Builder {
        private static final long OPT_BIT_ON_COMPLETED_THRESHOLD_ELAPSED = 128;
        private static final long OPT_BIT_ON_ERROR = 512;
        private static final long OPT_BIT_ON_FINISHED = 32;
        private static final long OPT_BIT_ON_LICENSE_REQUIRED = 1;
        private static final long OPT_BIT_ON_PAUSED = 16;
        private static final long OPT_BIT_ON_REBUFFERED = 256;
        private static final long OPT_BIT_ON_RESUMED = 4;
        private static final long OPT_BIT_ON_SCRUBBED = 8;
        private static final long OPT_BIT_ON_STARTED = 2;
        private static final long OPT_BIT_ON_STOPPED = 64;
        private List<Method> onCompletedThresholdElapsed;
        private List<Method> onError;
        private List<Method> onFinished;
        private List<Method> onLicenseRequired;
        private List<Method> onPaused;
        private List<Method> onRebuffered;
        private List<Method> onResumed;
        private List<Method> onScrubbed;
        private List<Method> onStarted;
        private List<Method> onStopped;
        private long optBits;

        private Builder() {
            this.onLicenseRequired = new ArrayList();
            this.onStarted = new ArrayList();
            this.onResumed = new ArrayList();
            this.onScrubbed = new ArrayList();
            this.onPaused = new ArrayList();
            this.onFinished = new ArrayList();
            this.onStopped = new ArrayList();
            this.onCompletedThresholdElapsed = new ArrayList();
            this.onRebuffered = new ArrayList();
            this.onError = new ArrayList();
        }

        public final Builder from(MediaEventsElement mediaEventsElement) {
            ImmutableMediaEventsElement.requireNonNull(mediaEventsElement, "instance");
            addAllOnLicenseRequired(mediaEventsElement.onLicenseRequired());
            addAllOnStarted(mediaEventsElement.onStarted());
            addAllOnResumed(mediaEventsElement.onResumed());
            addAllOnScrubbed(mediaEventsElement.onScrubbed());
            addAllOnPaused(mediaEventsElement.onPaused());
            addAllOnFinished(mediaEventsElement.onFinished());
            addAllOnStopped(mediaEventsElement.onStopped());
            addAllOnCompletedThresholdElapsed(mediaEventsElement.onCompletedThresholdElapsed());
            addAllOnRebuffered(mediaEventsElement.onRebuffered());
            addAllOnError(mediaEventsElement.onError());
            return this;
        }

        public final Builder addOnLicenseRequired(Method method) {
            this.onLicenseRequired.add((Method) ImmutableMediaEventsElement.requireNonNull(method, "onLicenseRequired element"));
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnLicenseRequired(Method... methodArr) {
            for (Method method : methodArr) {
                this.onLicenseRequired.add((Method) ImmutableMediaEventsElement.requireNonNull(method, "onLicenseRequired element"));
            }
            this.optBits |= 1;
            return this;
        }

        @JsonProperty("onLicenseRequired")
        public final Builder onLicenseRequired(Iterable<? extends Method> iterable) {
            this.onLicenseRequired.clear();
            return addAllOnLicenseRequired(iterable);
        }

        public final Builder addAllOnLicenseRequired(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onLicenseRequired.add((Method) ImmutableMediaEventsElement.requireNonNull(it.next(), "onLicenseRequired element"));
            }
            this.optBits |= 1;
            return this;
        }

        public final Builder addOnStarted(Method method) {
            this.onStarted.add((Method) ImmutableMediaEventsElement.requireNonNull(method, "onStarted element"));
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnStarted(Method... methodArr) {
            for (Method method : methodArr) {
                this.onStarted.add((Method) ImmutableMediaEventsElement.requireNonNull(method, "onStarted element"));
            }
            this.optBits |= 2;
            return this;
        }

        @JsonProperty("onStarted")
        public final Builder onStarted(Iterable<? extends Method> iterable) {
            this.onStarted.clear();
            return addAllOnStarted(iterable);
        }

        public final Builder addAllOnStarted(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onStarted.add((Method) ImmutableMediaEventsElement.requireNonNull(it.next(), "onStarted element"));
            }
            this.optBits |= 2;
            return this;
        }

        public final Builder addOnResumed(Method method) {
            this.onResumed.add((Method) ImmutableMediaEventsElement.requireNonNull(method, "onResumed element"));
            this.optBits |= 4;
            return this;
        }

        public final Builder addOnResumed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onResumed.add((Method) ImmutableMediaEventsElement.requireNonNull(method, "onResumed element"));
            }
            this.optBits |= 4;
            return this;
        }

        @JsonProperty("onResumed")
        public final Builder onResumed(Iterable<? extends Method> iterable) {
            this.onResumed.clear();
            return addAllOnResumed(iterable);
        }

        public final Builder addAllOnResumed(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onResumed.add((Method) ImmutableMediaEventsElement.requireNonNull(it.next(), "onResumed element"));
            }
            this.optBits |= 4;
            return this;
        }

        public final Builder addOnScrubbed(Method method) {
            this.onScrubbed.add((Method) ImmutableMediaEventsElement.requireNonNull(method, "onScrubbed element"));
            this.optBits |= 8;
            return this;
        }

        public final Builder addOnScrubbed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onScrubbed.add((Method) ImmutableMediaEventsElement.requireNonNull(method, "onScrubbed element"));
            }
            this.optBits |= 8;
            return this;
        }

        @JsonProperty("onScrubbed")
        public final Builder onScrubbed(Iterable<? extends Method> iterable) {
            this.onScrubbed.clear();
            return addAllOnScrubbed(iterable);
        }

        public final Builder addAllOnScrubbed(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onScrubbed.add((Method) ImmutableMediaEventsElement.requireNonNull(it.next(), "onScrubbed element"));
            }
            this.optBits |= 8;
            return this;
        }

        public final Builder addOnPaused(Method method) {
            this.onPaused.add((Method) ImmutableMediaEventsElement.requireNonNull(method, "onPaused element"));
            this.optBits |= 16;
            return this;
        }

        public final Builder addOnPaused(Method... methodArr) {
            for (Method method : methodArr) {
                this.onPaused.add((Method) ImmutableMediaEventsElement.requireNonNull(method, "onPaused element"));
            }
            this.optBits |= 16;
            return this;
        }

        @JsonProperty("onPaused")
        public final Builder onPaused(Iterable<? extends Method> iterable) {
            this.onPaused.clear();
            return addAllOnPaused(iterable);
        }

        public final Builder addAllOnPaused(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onPaused.add((Method) ImmutableMediaEventsElement.requireNonNull(it.next(), "onPaused element"));
            }
            this.optBits |= 16;
            return this;
        }

        public final Builder addOnFinished(Method method) {
            this.onFinished.add((Method) ImmutableMediaEventsElement.requireNonNull(method, "onFinished element"));
            this.optBits |= 32;
            return this;
        }

        public final Builder addOnFinished(Method... methodArr) {
            for (Method method : methodArr) {
                this.onFinished.add((Method) ImmutableMediaEventsElement.requireNonNull(method, "onFinished element"));
            }
            this.optBits |= 32;
            return this;
        }

        @JsonProperty("onFinished")
        public final Builder onFinished(Iterable<? extends Method> iterable) {
            this.onFinished.clear();
            return addAllOnFinished(iterable);
        }

        public final Builder addAllOnFinished(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onFinished.add((Method) ImmutableMediaEventsElement.requireNonNull(it.next(), "onFinished element"));
            }
            this.optBits |= 32;
            return this;
        }

        public final Builder addOnStopped(Method method) {
            this.onStopped.add((Method) ImmutableMediaEventsElement.requireNonNull(method, "onStopped element"));
            this.optBits |= 64;
            return this;
        }

        public final Builder addOnStopped(Method... methodArr) {
            for (Method method : methodArr) {
                this.onStopped.add((Method) ImmutableMediaEventsElement.requireNonNull(method, "onStopped element"));
            }
            this.optBits |= 64;
            return this;
        }

        @JsonProperty("onStopped")
        public final Builder onStopped(Iterable<? extends Method> iterable) {
            this.onStopped.clear();
            return addAllOnStopped(iterable);
        }

        public final Builder addAllOnStopped(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onStopped.add((Method) ImmutableMediaEventsElement.requireNonNull(it.next(), "onStopped element"));
            }
            this.optBits |= 64;
            return this;
        }

        public final Builder addOnCompletedThresholdElapsed(Method method) {
            this.onCompletedThresholdElapsed.add((Method) ImmutableMediaEventsElement.requireNonNull(method, "onCompletedThresholdElapsed element"));
            this.optBits |= 128;
            return this;
        }

        public final Builder addOnCompletedThresholdElapsed(Method... methodArr) {
            for (Method method : methodArr) {
                this.onCompletedThresholdElapsed.add((Method) ImmutableMediaEventsElement.requireNonNull(method, "onCompletedThresholdElapsed element"));
            }
            this.optBits |= 128;
            return this;
        }

        @JsonProperty("onCompletedThresholdElapsed")
        public final Builder onCompletedThresholdElapsed(Iterable<? extends Method> iterable) {
            this.onCompletedThresholdElapsed.clear();
            return addAllOnCompletedThresholdElapsed(iterable);
        }

        public final Builder addAllOnCompletedThresholdElapsed(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onCompletedThresholdElapsed.add((Method) ImmutableMediaEventsElement.requireNonNull(it.next(), "onCompletedThresholdElapsed element"));
            }
            this.optBits |= 128;
            return this;
        }

        public final Builder addOnRebuffered(Method method) {
            this.onRebuffered.add((Method) ImmutableMediaEventsElement.requireNonNull(method, "onRebuffered element"));
            this.optBits |= 256;
            return this;
        }

        public final Builder addOnRebuffered(Method... methodArr) {
            for (Method method : methodArr) {
                this.onRebuffered.add((Method) ImmutableMediaEventsElement.requireNonNull(method, "onRebuffered element"));
            }
            this.optBits |= 256;
            return this;
        }

        @JsonProperty("onRebuffered")
        public final Builder onRebuffered(Iterable<? extends Method> iterable) {
            this.onRebuffered.clear();
            return addAllOnRebuffered(iterable);
        }

        public final Builder addAllOnRebuffered(Iterable<? extends Method> iterable) {
            Iterator<? extends Method> it = iterable.iterator();
            while (it.hasNext()) {
                this.onRebuffered.add((Method) ImmutableMediaEventsElement.requireNonNull(it.next(), "onRebuffered element"));
            }
            this.optBits |= 256;
            return this;
        }

        public final Builder addOnError(Method method) {
            this.onError.add((Method) ImmutableMediaEventsElement.requireNonNull(method, "onError element"));
            this.optBits |= 512;
            return this;
        }

        public final Builder addOnError(Method... methodArr) {
            for (Method method : methodArr) {
                this.onError.add((Method) ImmutableMediaEventsElement.requireNonNull(method, "onError element"));
            }
            this.optBits |= 512;
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
                this.onError.add((Method) ImmutableMediaEventsElement.requireNonNull(it.next(), "onError element"));
            }
            this.optBits |= 512;
            return this;
        }

        public ImmutableMediaEventsElement build() {
            return new ImmutableMediaEventsElement(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onLicenseRequiredIsSet() {
            return (this.optBits & 1) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onStartedIsSet() {
            return (this.optBits & 2) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onResumedIsSet() {
            return (this.optBits & 4) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onScrubbedIsSet() {
            return (this.optBits & 8) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onPausedIsSet() {
            return (this.optBits & 16) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onFinishedIsSet() {
            return (this.optBits & 32) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onStoppedIsSet() {
            return (this.optBits & 64) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onCompletedThresholdElapsedIsSet() {
            return (this.optBits & 128) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onRebufferedIsSet() {
            return (this.optBits & 256) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean onErrorIsSet() {
            return (this.optBits & 512) != 0;
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
