package com.amazon.mp3.init;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.BootstrapSingletonTask;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.auto.AutoFlexEventUtil;
import com.amazon.mp3.auto.carmode.CarModeUtility;
import com.amazon.mp3.auto.carmode.detection.CarBluetoothComparator;
import com.amazon.mp3.auto.carmode.detection.CarDetector;
import com.amazon.mp3.dagger.DaggerApplicationComponent;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.util.AndroidUtils;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import rx.functions.Action1;

/* JADX INFO: compiled from: CarDetectionLaunchInitializationTask.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0007¢\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u0003\u001a\u00020\u0002H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\u0007\u001a\u00020\u0002H\u0016J\u000e\u0010\t\u001a\b\u0012\u0002\b\u0003\u0018\u00010\bH\u0016R\"\u0010\u000b\u001a\u00020\n8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0016\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"Lcom/amazon/mp3/init/CarDetectionLaunchInitializationTask;", "Lcom/amazon/mp3/init/InitializationTask;", "", "initializeCarDetector", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "getName", "initialize", "Lrx/functions/Action1;", "getOnNextAction", "Lcom/amazon/mp3/auto/carmode/detection/CarDetector;", "carDetector", "Lcom/amazon/mp3/auto/carmode/detection/CarDetector;", "getCarDetector", "()Lcom/amazon/mp3/auto/carmode/detection/CarDetector;", "setCarDetector", "(Lcom/amazon/mp3/auto/carmode/detection/CarDetector;)V", "Lkotlinx/coroutines/CoroutineScope;", "ioScope$delegate", "Lkotlin/Lazy;", "getIoScope", "()Lkotlinx/coroutines/CoroutineScope;", "ioScope", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class CarDetectionLaunchInitializationTask implements InitializationTask {
    private static final String TAG = CarDetectionLaunchInitializationTask.class.getSimpleName();
    public CarDetector carDetector;

    /* JADX INFO: renamed from: ioScope$delegate, reason: from kotlin metadata */
    private final Lazy ioScope = LazyKt__LazyJVMKt.lazy(new Function0<CoroutineScope>() { // from class: com.amazon.mp3.init.CarDetectionLaunchInitializationTask$ioScope$2
        @Override // kotlin.jvm.functions.Function0
        public final CoroutineScope invoke() {
            return CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
        }
    });

    @Override // com.amazon.mp3.init.InitializationTask
    public Action1<?> getOnNextAction() {
        return null;
    }

    public final CarDetector getCarDetector() {
        CarDetector carDetector = this.carDetector;
        if (carDetector != null) {
            return carDetector;
        }
        Intrinsics.throwUninitializedPropertyAccessException("carDetector");
        return null;
    }

    @Override // com.amazon.mp3.init.InitializationTask
    public String getName() {
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        return TAG2;
    }

    private final CoroutineScope getIoScope() {
        return (CoroutineScope) this.ioScope.getValue();
    }

    @Override // com.amazon.mp3.init.InitializationTask
    public void initialize() {
        AmazonApplication application = AmazonApplication.getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "getApplication()");
        DaggerApplicationComponent.builder().application(application).build().injectCarDetection(this);
        Log.warning(TAG, "Car detection initialization task called");
        BootstrapSingletonTask.get().registerUserObserver().subscribe(new Action1() { // from class: com.amazon.mp3.init.CarDetectionLaunchInitializationTask$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CarDetectionLaunchInitializationTask.m1207initialize$lambda0(this.f$0, (Void) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.init.CarDetectionLaunchInitializationTask$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CarDetectionLaunchInitializationTask.m1208initialize$lambda1((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initialize$lambda-0, reason: not valid java name */
    public static final void m1207initialize$lambda0(CarDetectionLaunchInitializationTask this$0, Void r7) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BuildersKt__Builders_commonKt.launch$default(this$0.getIoScope(), null, null, new CarDetectionLaunchInitializationTask$initialize$1$1(this$0, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initialize$lambda-1, reason: not valid java name */
    public static final void m1208initialize$lambda1(Throwable th) {
        Log.error(TAG, "BootstrapSingletonTask error. Could not complete initialization due to: %s", th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object initializeCarDetector(Continuation<? super Unit> continuation) {
        if (this.carDetector == null) {
            return Unit.INSTANCE;
        }
        final Context context = AmazonApplication.getApplication().getApplicationContext();
        CarBluetoothComparator.Companion companion = CarBluetoothComparator.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(context, "context");
        companion.initCarDetectionData(context);
        getCarDetector().init(context);
        CarModeUtility.INSTANCE.setCarDetector(getCarDetector());
        CarDetector carDetector = getCarDetector();
        Scheduler schedulerIo = Schedulers.io();
        Intrinsics.checkNotNullExpressionValue(schedulerIo, "io()");
        Scheduler schedulerIo2 = Schedulers.io();
        Intrinsics.checkNotNullExpressionValue(schedulerIo2, "io()");
        Disposable disposableSubscribe = carDetector.registerObserver(schedulerIo, schedulerIo2).subscribe(new Consumer() { // from class: com.amazon.mp3.init.CarDetectionLaunchInitializationTask$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                CarDetectionLaunchInitializationTask.m1209initializeCarDetector$lambda4(context, (Boolean) obj);
            }
        }, new Consumer() { // from class: com.amazon.mp3.init.CarDetectionLaunchInitializationTask$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                CarDetectionLaunchInitializationTask.m1212initializeCarDetector$lambda5((Throwable) obj);
            }
        });
        return disposableSubscribe == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? disposableSubscribe : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initializeCarDetector$lambda-4, reason: not valid java name */
    public static final void m1209initializeCarDetector$lambda4(final Context context, Boolean bool) {
        String str = TAG;
        Log.warning(str, Intrinsics.stringPlus("Car bluetooth detection observer called with value ", bool));
        Boolean isAutoLaunchEnabled = SettingsUtil.getCarModeLaunchAutomaticallyPreference(context);
        boolean zIsApplicationInForeground = AndroidUtils.isApplicationInForeground();
        AutoFlexEventUtil autoFlexEventUtil = AutoFlexEventUtil.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(isAutoLaunchEnabled, "isAutoLaunchEnabled");
        autoFlexEventUtil.emitCarModeAutoLaunchSettingFlexEvent(isAutoLaunchEnabled.booleanValue(), zIsApplicationInForeground);
        if (!zIsApplicationInForeground) {
            Log.warning(str, "Do not launch/dismiss carmode in background with value");
        } else {
            Observable.just(bool).distinctUntilChanged().throttleFirst(AmazonApplication.getCapabilities().getCarDetectionThrottleValue(), TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.mp3.init.CarDetectionLaunchInitializationTask$$ExternalSyntheticLambda4
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    CarDetectionLaunchInitializationTask.m1210initializeCarDetector$lambda4$lambda2(context, ((Boolean) obj).booleanValue());
                }
            }, new Consumer() { // from class: com.amazon.mp3.init.CarDetectionLaunchInitializationTask$$ExternalSyntheticLambda5
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    CarDetectionLaunchInitializationTask.m1211initializeCarDetector$lambda4$lambda3((Throwable) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initializeCarDetector$lambda-4$lambda-2, reason: not valid java name */
    public static final void m1210initializeCarDetector$lambda4$lambda2(Context context, boolean z) {
        if (z) {
            Log.warning(TAG, "Car Mode launched through bluetooth detection");
            Intrinsics.checkNotNullExpressionValue(context, "context");
            CarModeUtility.checkIfAutoLaunchCarMode(context);
        } else {
            Log.warning(TAG, "Car mode exited due to bluetooth detection");
            CarModeUtility carModeUtility = CarModeUtility.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(context, "context");
            carModeUtility.exitCarModeIfInCarMode(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initializeCarDetector$lambda-4$lambda-3, reason: not valid java name */
    public static final void m1211initializeCarDetector$lambda4$lambda3(Throwable th) {
        Log.error(TAG, "error delaying the car detection value. Could not complete delay due to: %s", th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initializeCarDetector$lambda-5, reason: not valid java name */
    public static final void m1212initializeCarDetector$lambda5(Throwable th) {
        Log.error(TAG, "registering car detection observer error. Could not complete registration due to: %s", th);
    }
}
