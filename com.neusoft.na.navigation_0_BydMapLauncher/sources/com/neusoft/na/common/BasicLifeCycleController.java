package com.neusoft.na.common;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.neusoft.na.common.ApplicationStatus;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mu.KLogging;
import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

/* JADX INFO: compiled from: BasicLifeCycleController.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000{\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0017\b\u0016\u0018\u0000 52\u00020\u0001:\u00015B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\u001b\u001a\u00020\u001aH\u0016J\b\u0010\u001c\u001a\u00020\u001aH\u0016J\u0006\u0010\u001d\u001a\u00020\fJ\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\f0\u001fJ\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000f0\u001fJ\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00140\u001fJ\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001fJ\u0012\u0010#\u001a\u00020\u001a2\b\u0010$\u001a\u0004\u0018\u00010%H\u0002J\b\u0010&\u001a\u00020\u001aH\u0016J\u001c\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*2\b\u0010+\u001a\u0004\u0018\u00010,H\u0014J\u0012\u0010-\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*H\u0014J\u001c\u0010.\u001a\u00020(2\u0006\u0010/\u001a\u00020\u000f2\n\b\u0002\u00100\u001a\u0004\u0018\u000101H\u0004J\u0010\u00102\u001a\u00020(2\u0006\u00103\u001a\u00020\fH\u0002J\b\u00104\u001a\u00020\u001aH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R2\u0010\r\u001a&\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\u000f0\u000f \u0010*\u0012\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\u000f0\u000f\u0018\u00010\u000e0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R2\u0010\u0013\u001a&\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\u00140\u0014 \u0010*\u0012\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\u00140\u0014\u0018\u00010\u000e0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018R2\u0010\u0019\u001a&\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\u001a0\u001a \u0010*\u0012\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\u001a0\u001a\u0018\u00010\u000e0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00066"}, d2 = {"Lcom/neusoft/na/common/BasicLifeCycleController;", "", "context", "Landroid/content/Context;", "mServiceClass", "Ljava/lang/Class;", "Lcom/neusoft/na/common/ServiceBase;", "(Landroid/content/Context;Ljava/lang/Class;)V", "getContext", "()Landroid/content/Context;", "mApplicationStatus", "Lrx/subjects/BehaviorSubject;", "Lcom/neusoft/na/common/ApplicationStatus;", "mDayNightSubject", "Lrx/subjects/PublishSubject;", "", "kotlin.jvm.PlatformType", "mMessenger", "Landroid/os/Messenger;", "mPlaySounSubject", "Ljava/lang/Void;", "mService", "mServiceConnectionHandler", "com/neusoft/na/common/BasicLifeCycleController$mServiceConnectionHandler$1", "Lcom/neusoft/na/common/BasicLifeCycleController$mServiceConnectionHandler$1;", "mServiceReadySubject", "", "deinit", "destroy", "getApplicationStatus", "getApplicationStatusObservable", "Lrx/Observable;", "getDayNightObservable", "getPlaySoundObservable", "getServiceReadyObservable", "handleMessage", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "init", "onServiceConnected", "", "className", "Landroid/content/ComponentName;", NotificationCompat.CATEGORY_SERVICE, "Landroid/os/IBinder;", "onServiceDisconnected", "send", "method", "payload", "Landroid/os/Bundle;", "setApplicationStatus", "status", "startApplication", "Companion", "common_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public class BasicLifeCycleController {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static String TAG = "XJBasicLifeCycleController";
    private final Context context;
    private final BehaviorSubject<ApplicationStatus> mApplicationStatus;
    private final PublishSubject<Integer> mDayNightSubject;
    private final Messenger mMessenger;
    private final PublishSubject<Void> mPlaySounSubject;
    private Messenger mService;
    private final Class<? extends ServiceBase> mServiceClass;
    private final BasicLifeCycleController$mServiceConnectionHandler$1 mServiceConnectionHandler;
    private final PublishSubject<Boolean> mServiceReadySubject;

    /* JADX INFO: compiled from: BasicLifeCycleController.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ApplicationStatus.Status.values().length];
            try {
                iArr[ApplicationStatus.Status.INITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ApplicationStatus.Status.STARTING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ApplicationStatus.Status.RUNNING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Type inference failed for: r2v5, types: [com.neusoft.na.common.BasicLifeCycleController$mServiceConnectionHandler$1] */
    public BasicLifeCycleController(Context context, Class<? extends ServiceBase> mServiceClass) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mServiceClass, "mServiceClass");
        this.context = context;
        this.mServiceClass = mServiceClass;
        this.mMessenger = new Messenger(new Handler(new Handler.Callback() { // from class: com.neusoft.na.common.BasicLifeCycleController$$ExternalSyntheticLambda0
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return BasicLifeCycleController.mMessenger$lambda$0(this.f$0, message);
            }
        }));
        this.mServiceReadySubject = PublishSubject.create();
        this.mPlaySounSubject = PublishSubject.create();
        this.mDayNightSubject = PublishSubject.create();
        this.mServiceConnectionHandler = new ServiceConnection() { // from class: com.neusoft.na.common.BasicLifeCycleController$mServiceConnectionHandler$1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName className, IBinder service) throws RemoteException {
                Log.i(BasicLifeCycleController.INSTANCE.getTAG(), "onServiceConnected");
                this.this$0.onServiceConnected(className, service);
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName className) {
                this.this$0.onServiceDisconnected(className);
            }
        };
        BehaviorSubject<ApplicationStatus> behaviorSubjectCreate = BehaviorSubject.create();
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreate, "create()");
        this.mApplicationStatus = behaviorSubjectCreate;
    }

    public final Context getContext() {
        return this.context;
    }

    /* JADX INFO: compiled from: BasicLifeCycleController.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/neusoft/na/common/BasicLifeCycleController$Companion;", "Lmu/KLogging;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "common_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion extends KLogging {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return BasicLifeCycleController.TAG;
        }

        public final void setTAG(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            BasicLifeCycleController.TAG = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean mMessenger$lambda$0(BasicLifeCycleController this$0, Message it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        return this$0.handleMessage(it);
    }

    private final boolean handleMessage(Message msg) {
        if (msg != null) {
            int i = msg.what;
            ApplicationStatus applicationStatus = null;
            if (i == 196608) {
                Bundle data = msg.getData();
                if (data != null) {
                    data.setClassLoader(ApplicationStatus.class.getClassLoader());
                    applicationStatus = (ApplicationStatus) data.getParcelable("status");
                }
                if (applicationStatus != null) {
                    setApplicationStatus(applicationStatus);
                }
            } else if (i == 262144) {
                Log.i(TAG, "MSG_SIG_PLAY_SOUND_EFFECT");
                this.mPlaySounSubject.onNext(null);
            } else if (i == 327680) {
                Log.i(TAG, "MSG_SIG_PLAY_SOUND_EFFECT");
                this.mDayNightSubject.onNext(Integer.valueOf(msg.getData().getInt(ServiceBase.PAYLOAD_MODE, 0)));
            }
        } else {
            INSTANCE.getLogger().warn("empty message received");
        }
        return false;
    }

    protected void onServiceConnected(final ComponentName className, IBinder service) throws RemoteException {
        Log.i(TAG, "onServiceConnected" + className);
        INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.common.BasicLifeCycleController.onServiceConnected.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return "onServiceConnected(" + className + ')';
            }
        });
        Messenger messenger = new Messenger(service);
        this.mService = messenger;
        this.mServiceReadySubject.onNext(true);
        try {
            messenger.getBinder().linkToDeath(new IBinder.DeathRecipient() { // from class: com.neusoft.na.common.BasicLifeCycleController$$ExternalSyntheticLambda1
                @Override // android.os.IBinder.DeathRecipient
                public final void binderDied() {
                    BasicLifeCycleController.onServiceConnected$lambda$2(this.f$0, className);
                }
            }, 0);
        } catch (DeadObjectException unused) {
            Log.w(TAG, "here have a DeadObjectException");
        }
        try {
            Message messageObtain = Message.obtain((Handler) null, 65536);
            messageObtain.replyTo = this.mMessenger;
            messenger.send(messageObtain);
        } catch (RemoteException unused2) {
            INSTANCE.getLogger().error("failed to register client");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onServiceConnected$lambda$2(BasicLifeCycleController this$0, ComponentName componentName) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onServiceDisconnected(componentName);
    }

    protected void onServiceDisconnected(final ComponentName className) {
        INSTANCE.getLogger().info(new Function0<Object>() { // from class: com.neusoft.na.common.BasicLifeCycleController.onServiceDisconnected.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return "onServiceDisconnected(" + className + ')';
            }
        });
        this.mService = null;
        this.mServiceReadySubject.onNext(false);
        setApplicationStatus(new ApplicationStatus(ApplicationStatus.Status.UNINITIALIZED, 0));
        init();
    }

    public synchronized boolean init() {
        Log.i(TAG, "init");
        if (this.mService != null) {
            Log.i(TAG, "init::mService !== null");
            return true;
        }
        Log.i(TAG, "init::mService == null::startService");
        this.context.startForegroundService(new Intent(this.context, this.mServiceClass));
        boolean zBindService = this.context.bindService(new Intent(this.context, this.mServiceClass), this.mServiceConnectionHandler, 1);
        Log.i(TAG, "init::startResult == " + zBindService);
        return zBindService;
    }

    public synchronized boolean startApplication() {
        Log.i(TAG, "startApplication");
        if (this.mService != null) {
            Log.i(TAG, "startApplication ::mService!=null");
            ApplicationStatus.Status mStatus = this.mApplicationStatus.getValue().getMStatus();
            INSTANCE.getLogger().debug("application status: " + mStatus);
            int i = WhenMappings.$EnumSwitchMapping$0[mStatus.ordinal()];
            if (i == 1) {
                send$default(this, 131072, null, 2, null);
                return true;
            }
            if (i != 2) {
                return i == 3;
            }
            return true;
        }
        Log.i(TAG, "startApplication ::mService==null");
        return false;
    }

    public synchronized boolean destroy() {
        boolean z;
        Messenger messenger = this.mService;
        if (messenger != null) {
            try {
                Message messageObtain = Message.obtain((Handler) null, ServiceBase.MSG_REQ_UNREGISTER_CLIENT);
                messageObtain.replyTo = this.mMessenger;
                messenger.send(messageObtain);
            } catch (RemoteException unused) {
                INSTANCE.getLogger().error("failed to register client");
            }
            this.context.unbindService(this.mServiceConnectionHandler);
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public synchronized boolean deinit() {
        if (this.mService == null) {
            return false;
        }
        this.context.unbindService(this.mServiceConnectionHandler);
        this.context.stopService(new Intent(this.context, this.mServiceClass));
        return true;
    }

    public static /* synthetic */ void send$default(BasicLifeCycleController basicLifeCycleController, int i, Bundle bundle, int i2, Object obj) throws RemoteException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: send");
        }
        if ((i2 & 2) != 0) {
            bundle = null;
        }
        basicLifeCycleController.send(i, bundle);
    }

    protected final void send(int method, Bundle payload) throws RemoteException {
        Log.i(TAG, "method == " + method);
        Messenger messenger = this.mService;
        if (messenger != null) {
            Intrinsics.checkNotNull(messenger);
            if (messenger.getBinder().isBinderAlive()) {
                Messenger messenger2 = this.mService;
                Intrinsics.checkNotNull(messenger2);
                if (messenger2.getBinder().pingBinder()) {
                    Log.i(TAG, "can send msg.");
                    Message messageObtain = Message.obtain((Handler) null, method);
                    messageObtain.replyTo = this.mMessenger;
                    messageObtain.setData(payload);
                    Messenger messenger3 = this.mService;
                    if (messenger3 != null) {
                        messenger3.send(messageObtain);
                        return;
                    }
                    return;
                }
            }
        }
        INSTANCE.getLogger().warn("service not connected!");
        Log.i(TAG, "mService == null");
    }

    public final Observable<ApplicationStatus> getApplicationStatusObservable() {
        Observable<ApplicationStatus> observableAsObservable = this.mApplicationStatus.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "mApplicationStatus.asObservable()");
        return observableAsObservable;
    }

    public final ApplicationStatus getApplicationStatus() {
        ApplicationStatus value = this.mApplicationStatus.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "mApplicationStatus.value");
        return value;
    }

    public final Observable<Boolean> getServiceReadyObservable() {
        Observable<Boolean> observableAsObservable = this.mServiceReadySubject.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "mServiceReadySubject.asObservable()");
        return observableAsObservable;
    }

    public final Observable<Void> getPlaySoundObservable() {
        Observable<Void> observableAsObservable = this.mPlaySounSubject.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "mPlaySounSubject.asObservable()");
        return observableAsObservable;
    }

    public final Observable<Integer> getDayNightObservable() {
        Observable<Integer> observableAsObservable = this.mDayNightSubject.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "mDayNightSubject.asObservable()");
        return observableAsObservable;
    }

    private final void setApplicationStatus(ApplicationStatus status) {
        Log.i(TAG, "setApplicationStatus ::status::" + status);
        INSTANCE.getLogger().debug("service status: " + status.getMStatus() + (status.getMProgress() != null ? " Progress: " + status.getMProgress() + '%' : ""));
        this.mApplicationStatus.onNext(status);
    }
}
