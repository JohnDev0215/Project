package com.amazon.mp3.auto.carmode.detection;

import android.content.Context;
import com.amazon.mp3.auto.SubDeviceConnectionUtils;
import com.amazon.mp3.util.Log;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.media.auto.provider.SubDeviceType;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.subjects.PublishSubject;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarDetector.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u001b\u0012\u0012\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00120\u0011\"\u00020\u0012¢\u0006\u0004\b\u001b\u0010\u001cJ\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u000e\u0010\n\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bJ\u0006\u0010\u000b\u001a\u00020\u0002J\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fR\u001c\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00120\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0003\u0010\u0015R\u0016\u0010\u0016\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010\u0015R8\u0010\u0019\u001a&\u0012\f\u0012\n \u0018*\u0004\u0018\u00010\u00020\u0002 \u0018*\u0012\u0012\f\u0012\n \u0018*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u00170\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001a¨\u0006\u001e"}, d2 = {"Lcom/amazon/mp3/auto/carmode/detection/CarDetector;", "", "", "isInCar", "Lcom/amazon/mp3/auto/carmode/detection/CarDetectionSource;", PageUriUtils.SOURCE_QUERY_PARAM, "", "updateCarStatus", "Landroid/content/Context;", "context", "init", "isCurrentlyInCar", "Lio/reactivex/rxjava3/core/Scheduler;", "processScheduler", "observeScheduler", "Lio/reactivex/rxjava3/core/Observable;", "registerObserver", "", "Lcom/amazon/mp3/auto/carmode/detection/SpecializedCarDetector;", "detectors", "[Lcom/amazon/mp3/auto/carmode/detection/SpecializedCarDetector;", "Z", "isDetectorInitialized", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "kotlin.jvm.PlatformType", "publishSubject", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "<init>", "([Lcom/amazon/mp3/auto/carmode/detection/SpecializedCarDetector;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CarDetector {
    private static final String TAG = CarDetector.class.getSimpleName();
    private final SpecializedCarDetector[] detectors;
    private boolean isDetectorInitialized;
    private boolean isInCar;
    private final PublishSubject<Boolean> publishSubject;

    public CarDetector(SpecializedCarDetector... detectors) {
        Intrinsics.checkNotNullParameter(detectors, "detectors");
        this.detectors = detectors;
        this.publishSubject = PublishSubject.create();
    }

    public final void init(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (this.isDetectorInitialized) {
            Log.warning(TAG, "init called more than once");
            return;
        }
        SpecializedCarDetector[] specializedCarDetectorArr = this.detectors;
        int length = specializedCarDetectorArr.length;
        int i = 0;
        while (i < length) {
            SpecializedCarDetector specializedCarDetector = specializedCarDetectorArr[i];
            i++;
            try {
                specializedCarDetector.init(context, new AnonymousClass1(this));
                Log.info(TAG, "Initialized specialized car detector: %s", specializedCarDetector.getClass().getSimpleName());
            } catch (Exception e) {
                Log.error(TAG, "specialized car detector failed to initialize!", e);
            }
        }
        this.isDetectorInitialized = true;
        Log.info(TAG, "Car detector started successfully");
    }

    /* JADX INFO: renamed from: com.amazon.mp3.auto.carmode.detection.CarDetector$init$1, reason: invalid class name */
    /* JADX INFO: compiled from: CarDetector.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function2<Boolean, CarDetectionSource, Unit> {
        AnonymousClass1(Object obj) {
            super(2, obj, CarDetector.class, "updateCarStatus", "updateCarStatus(ZLcom/amazon/mp3/auto/carmode/detection/CarDetectionSource;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke */
        public /* bridge */ /* synthetic */ Unit mo4invoke(Boolean bool, CarDetectionSource carDetectionSource) {
            invoke(bool.booleanValue(), carDetectionSource);
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z, CarDetectionSource p1) {
            Intrinsics.checkNotNullParameter(p1, "p1");
            ((CarDetector) this.receiver).updateCarStatus(z, p1);
        }
    }

    public final boolean isCurrentlyInCar() {
        if (!this.isDetectorInitialized) {
            Log.warning(TAG, "isCurrentlyInCar called before init()");
        }
        return this.isInCar;
    }

    public final Observable<Boolean> registerObserver(Scheduler processScheduler, Scheduler observeScheduler) {
        Intrinsics.checkNotNullParameter(processScheduler, "processScheduler");
        Intrinsics.checkNotNullParameter(observeScheduler, "observeScheduler");
        Observable<Boolean> observableSubscribeOn = this.publishSubject.observeOn(observeScheduler).subscribeOn(processScheduler);
        Intrinsics.checkNotNullExpressionValue(observableSubscribeOn, "publishSubject.observeOn…cribeOn(processScheduler)");
        return observableSubscribeOn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateCarStatus(boolean isInCar, CarDetectionSource source) {
        if (isInCar == this.isInCar) {
            return;
        }
        this.isInCar = isInCar;
        if (source == CarDetectionSource.BLUETOOTH) {
            if (isInCar) {
                SubDeviceConnectionUtils.INSTANCE.sendSubDeviceConnectedEvent(SubDeviceType.CAR_BLUETOOTH.toString(), true);
            } else {
                SubDeviceConnectionUtils.INSTANCE.sendSubDeviceDisconnectedEvent(SubDeviceType.CAR_BLUETOOTH.toString());
            }
        }
        this.publishSubject.onNext(Boolean.valueOf(this.isInCar));
    }
}
