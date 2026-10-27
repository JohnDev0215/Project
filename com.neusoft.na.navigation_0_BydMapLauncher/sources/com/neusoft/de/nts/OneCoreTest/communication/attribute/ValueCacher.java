package com.neusoft.de.nts.OneCoreTest.communication.attribute;

import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.interfaces.IAttribute;
import com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil;
import com.neusoft.de.nts.OneCoreTest.utils.Logger;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java9.util.concurrent.CompletableFuture;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/* JADX INFO: loaded from: classes2.dex */
class ValueCacher {
    private final ICommunicationContextUtil<?, ?, ?> contextUtil;
    private static final Logger logger = Logger.getLogger((Class<?>) ValueCacher.class);
    private static final Map<Class<? extends BaseNotification>, List<Map.Entry<Boolean, CompletableFuture<? extends BaseNotification>>>> lastValuesFutures = new HashMap();
    private static final Map<Class<? extends BaseNotification>, IAttribute> lastValueUpdaters = new HashMap();
    private static final Map<Class<? extends BaseNotification>, Subscription> lastValueUpdaterSubscriptions = new HashMap();

    ValueCacher(ICommunicationContextUtil iCommunicationContextUtil) {
        this.contextUtil = iCommunicationContextUtil;
    }

    synchronized <V extends BaseNotification> void addLastValueUpdater(final Class<V> cls) {
        IAttribute<V> iAttributeCreateNotification = this.contextUtil.createNotification(cls);
        iAttributeCreateNotification.signIn();
        Subscription subscriptionSubscribe = iAttributeCreateNotification.getObservable().subscribe(new Action1<BaseNotification>() { // from class: com.neusoft.de.nts.OneCoreTest.communication.attribute.ValueCacher.1
            @Override // rx.functions.Action1
            public void call(BaseNotification baseNotification) {
                ValueCacher.this.processLastValueUpdate(cls, baseNotification);
            }
        });
        lastValueUpdaters.put(cls, iAttributeCreateNotification);
        lastValueUpdaterSubscriptions.put(cls, subscriptionSubscribe);
    }

    synchronized <V extends BaseNotification> void removeLastValueUpdater(Class<V> cls) {
        Map<Class<? extends BaseNotification>, Subscription> map = lastValueUpdaterSubscriptions;
        map.get(cls).unsubscribe();
        Map<Class<? extends BaseNotification>, IAttribute> map2 = lastValueUpdaters;
        map2.get(cls).signOut();
        map2.remove(cls);
        map.remove(cls);
        lastValuesFutures.remove(cls);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean isValueValid(BaseNotification baseNotification) {
        boolean z;
        z = baseNotification.getInvalid() == null || !baseNotification.getInvalid().equals(Boolean.TRUE);
        if (!z) {
            logger.debug("The value is invalid. Ignore it. Value:" + baseNotification);
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void processLastValueUpdate(Class<? extends BaseNotification> cls, BaseNotification baseNotification) {
        closeLastValueFuture(cls, baseNotification);
    }

    synchronized <V extends BaseNotification> CompletableFuture<V> getCachedLastValue(Class<V> cls, boolean z) {
        CompletableFuture<V> completableFutureCompletedFuture;
        Map<Class<? extends BaseNotification>, IAttribute> map = lastValueUpdaters;
        Object value = map.get(cls) == null ? null : map.get(cls).getValue();
        if (value != null) {
            logger.debug("Return from cache:" + cls);
            if (z) {
                completableFutureCompletedFuture = CompletableFuture.completedFuture(null);
            } else {
                completableFutureCompletedFuture = CompletableFuture.completedFuture(cls.cast(value));
            }
        } else {
            logger.debug("Wait for response for register:" + cls);
            CompletableFuture<? extends BaseNotification> completableFuture = new CompletableFuture<>();
            addLastValueFuture(cls, completableFuture, z);
            completableFutureCompletedFuture = (CompletableFuture<V>) completableFuture;
        }
        return completableFutureCompletedFuture;
    }

    private synchronized <V extends BaseNotification> void addLastValueFuture(Class<V> cls, CompletableFuture<? extends BaseNotification> completableFuture, boolean z) {
        Map<Class<? extends BaseNotification>, List<Map.Entry<Boolean, CompletableFuture<? extends BaseNotification>>>> map = lastValuesFutures;
        List<Map.Entry<Boolean, CompletableFuture<? extends BaseNotification>>> linkedList = map.get(cls);
        logger.debug("Add in future list:" + cls);
        if (linkedList == null) {
            linkedList = new LinkedList<>();
            linkedList.add(new AbstractMap.SimpleEntry(Boolean.valueOf(z), completableFuture));
        } else {
            linkedList.add(new AbstractMap.SimpleEntry(Boolean.valueOf(z), completableFuture));
        }
        map.put(cls, linkedList);
    }

    private synchronized <V extends BaseNotification> void closeLastValueFuture(Class<V> cls, BaseNotification baseNotification) {
        List<Map.Entry<Boolean, CompletableFuture<? extends BaseNotification>>> list = lastValuesFutures.get(cls);
        if (list != null) {
            for (Map.Entry<Boolean, CompletableFuture<? extends BaseNotification>> entry : list) {
                if (entry.getKey().booleanValue()) {
                    logger.debug("Close publish future class: %s", cls, baseNotification);
                    entry.getValue().complete(null);
                } else {
                    CompletableFuture<? extends BaseNotification> value = entry.getValue();
                    try {
                        if (isValueValid(baseNotification)) {
                            logger.debug("Close behavior class: %s value: %s", cls, baseNotification);
                            entry.getValue().complete(baseNotification);
                        }
                    } catch (ClassCastException e) {
                        logger.error(e, "error: %s class: %s value: %s ", e.getMessage(), cls, baseNotification);
                        value.complete(null);
                    }
                }
            }
            lastValuesFutures.remove(cls);
        }
    }

    synchronized <V extends BaseNotification> Observable<V> getObservable(Class<V> cls, boolean z) {
        return lastValueUpdaters.get(cls).getObservable().skip(z ? 1 : 0).filter(new Func1() { // from class: com.neusoft.de.nts.OneCoreTest.communication.attribute.ValueCacher.2
            @Override // rx.functions.Func1
            public Object call(Object obj) {
                return Boolean.valueOf(ValueCacher.this.isValueValid((BaseNotification) obj));
            }
        });
    }
}
