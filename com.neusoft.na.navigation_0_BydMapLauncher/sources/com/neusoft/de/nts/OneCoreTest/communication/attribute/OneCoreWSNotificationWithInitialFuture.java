package com.neusoft.de.nts.OneCoreTest.communication.attribute;

import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.RegisterNotification;
import com.neusoft.de.nts.OneCoreTest.communication.utils.WSContextUtil;
import com.neusoft.de.nts.OneCoreTest.interfaces.ITransport;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import com.neusoft.de.nts.OneCoreTest.utils.Logger;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java9.util.concurrent.CompletableFuture;
import rx.functions.Func1;

/* JADX INFO: loaded from: classes2.dex */
class OneCoreWSNotificationWithInitialFuture<V extends BaseNotification> extends WSNotification<V> {
    private final V emptyNotification;
    private boolean isActive;
    private final Class<V> notifyClass;
    private final ITransport<String, String> transport;
    private static final Logger logger = Logger.getLogger((Class<?>) OneCoreWSNotificationWithInitialFuture.class);
    private static final Map<Class<? extends BaseNotification>, Integer> notificationRefCount = new HashMap();
    private static final Map<ITransport, ValueCacher> valueCachers = new HashMap();
    private static boolean synchronize = true;

    OneCoreWSNotificationWithInitialFuture(EProjectInterfaceType eProjectInterfaceType, ITransport<String, String> iTransport, Class<V> cls, ESubjectType eSubjectType, Func1<BaseResponse, V> func1, Executor executor) {
        super(eProjectInterfaceType, iTransport, cls, eSubjectType, func1, executor);
        this.isActive = false;
        this.transport = iTransport;
        this.notifyClass = cls;
        this.emptyNotification = (V) createNotification(cls);
        Map<ITransport, ValueCacher> map = valueCachers;
        if (map.get(iTransport) == null) {
            map.put(iTransport, new ValueCacher(new WSContextUtil(getProject(), getTransport(), executor)));
        }
    }

    OneCoreWSNotificationWithInitialFuture(EProjectInterfaceType eProjectInterfaceType, ITransport<String, String> iTransport, Class<V> cls) {
        this(eProjectInterfaceType, iTransport, cls, null, null);
    }

    OneCoreWSNotificationWithInitialFuture(EProjectInterfaceType eProjectInterfaceType, ITransport<String, String> iTransport, Class<V> cls, Executor executor) {
        this(eProjectInterfaceType, iTransport, cls, null, executor);
    }

    OneCoreWSNotificationWithInitialFuture(EProjectInterfaceType eProjectInterfaceType, ITransport<String, String> iTransport, Class<V> cls, Func1<BaseResponse, V> func1) {
        this(eProjectInterfaceType, iTransport, cls, null, func1, null);
    }

    OneCoreWSNotificationWithInitialFuture(EProjectInterfaceType eProjectInterfaceType, ITransport<String, String> iTransport, Class<V> cls, Func1<BaseResponse, V> func1, Executor executor) {
        this(eProjectInterfaceType, iTransport, cls, null, func1, executor);
    }

    static boolean isSynchronize() {
        return synchronize;
    }

    public static void setSynchronize(boolean z) {
        synchronize = z;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    private V createNotification(Class<V> cls) {
        V vNewInstance;
        try {
            vNewInstance = cls.getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            logger.error(e, "On create new class instance", new Object[0]);
            vNewInstance = null;
        }
        Map<Class<? extends BaseNotification>, Integer> map = notificationRefCount;
        synchronized (map) {
            if (!map.containsKey(this.notifyClass)) {
                map.put((Class<? extends BaseNotification>) this.notifyClass, 0);
            }
        }
        return vNewInstance;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.neusoft.de.nts.OneCoreTest.communication.attribute.WSNotification, com.neusoft.de.nts.OneCoreTest.interfaces.IBroadcast
    public synchronized CompletableFuture<V> signIn() {
        CompletableFuture<V> completableFutureCompletedFuture;
        if (!this.isActive) {
            RegisterNotification registerNotification = new RegisterNotification(EMessageType.Register, this.emptyNotification.getComponent(), this.emptyNotification.getCommand(), synchronize);
            completableFutureCompletedFuture = super.signIn();
            Map<Class<? extends BaseNotification>, Integer> map = notificationRefCount;
            Integer num = map.get(this.notifyClass);
            if (num.intValue() == 0) {
                valueCachers.get(this.transport).addLastValueUpdater(this.notifyClass);
                getTransport().send(getProject().getProtocol().to(registerNotification));
            }
            map.put((Class<? extends BaseNotification>) this.notifyClass, Integer.valueOf(num.intValue() + 1));
            this.isActive = true;
        } else {
            completableFutureCompletedFuture = CompletableFuture.completedFuture(getValue());
        }
        return completableFutureCompletedFuture;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.neusoft.de.nts.OneCoreTest.communication.attribute.WSNotification, com.neusoft.de.nts.OneCoreTest.interfaces.IBroadcast
    public synchronized CompletableFuture<V> signOut() {
        CompletableFuture<V> completableFutureCompletedFuture;
        completableFutureCompletedFuture = CompletableFuture.completedFuture(getValue());
        if (this.isActive) {
            RegisterNotification registerNotification = new RegisterNotification(EMessageType.Unregister, this.emptyNotification.getComponent(), this.emptyNotification.getCommand());
            Map<Class<? extends BaseNotification>, Integer> map = notificationRefCount;
            Integer numValueOf = Integer.valueOf(map.get(this.notifyClass).intValue() - 1);
            map.put((Class<? extends BaseNotification>) this.notifyClass, numValueOf);
            if (numValueOf.intValue() == 0) {
                valueCachers.get(this.transport).removeLastValueUpdater(this.notifyClass);
                getTransport().send(getProject().getProtocol().to(registerNotification));
            }
            super.signOut();
            this.isActive = false;
        }
        return completableFutureCompletedFuture;
    }

    /* JADX INFO: renamed from: com.neusoft.de.nts.OneCoreTest.communication.attribute.OneCoreWSNotificationWithInitialFuture$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$neusoft$de$nts$OneCoreTest$communication$attribute$ESubjectType;

        static {
            int[] iArr = new int[ESubjectType.values().length];
            $SwitchMap$com$neusoft$de$nts$OneCoreTest$communication$attribute$ESubjectType = iArr;
            try {
                iArr[ESubjectType.Replay.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$neusoft$de$nts$OneCoreTest$communication$attribute$ESubjectType[ESubjectType.Behaviour.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private boolean isPublish(ESubjectType eSubjectType) {
        int i = AnonymousClass1.$SwitchMap$com$neusoft$de$nts$OneCoreTest$communication$attribute$ESubjectType[eSubjectType.ordinal()];
        return (i == 1 || i == 2) ? false : true;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.attribute.WSNotification, com.neusoft.de.nts.OneCoreTest.interfaces.IValueUpdater
    public synchronized CompletableFuture<V> getInitialValue() {
        return isSynchronize() ? valueCachers.get(this.transport).getCachedLastValue(this.notifyClass, isPublish(getSubjectType())) : null;
    }
}
