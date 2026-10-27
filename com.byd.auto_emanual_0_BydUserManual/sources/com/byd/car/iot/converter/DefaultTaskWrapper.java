package com.byd.car.iot.converter;

import android.os.RemoteException;
import android.util.Log;
import com.byd.autoiot.service.AutoiotProfile;
import com.byd.autoiot.service.IAutoiotEventListener;
import com.byd.car.iot.converter.DefaultTaskWrapper;
import com.byd.foundation.utils.StringUtils;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeoutException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import org.apache.shiro.config.Ini;

/* JADX INFO: loaded from: classes.dex */
public class DefaultTaskWrapper extends IAutoiotEventListener.Stub implements TaskWrapper<AutoiotProfile, Boolean> {
    private static final String TAG = "DefaultTaskWrapper";
    private final Map<String, Map<String, IotTask<AutoiotProfile>>> readTaskMap = new ConcurrentHashMap();
    private final Map<String, Map<String, IotTask<Boolean>>> writeTaskMap = new ConcurrentHashMap();

    @Override // com.byd.autoiot.service.IAutoiotEventListener
    public void onDeviceChanged(String str, String str2, String str3) {
    }

    @Override // com.byd.autoiot.service.IAutoiotEventListener
    public void onDeviceChangedWithData(String str, String str2, String str3, AutoiotProfile autoiotProfile) throws RemoteException {
    }

    @Override // com.byd.autoiot.service.IAutoiotEventListener
    public void onDeviceConnected(String str) {
    }

    @Override // com.byd.autoiot.service.IAutoiotEventListener
    public void onDeviceDeleted(String str) {
    }

    @Override // com.byd.autoiot.service.IAutoiotEventListener
    public void onDeviceDisconnected(String str) {
    }

    @Override // com.byd.autoiot.service.IAutoiotEventListener
    public void onDeviceLeave(String str) {
    }

    @Override // com.byd.autoiot.service.IAutoiotEventListener
    public void onNewDeviceDiscovered(List<String> list) {
    }

    DefaultTaskWrapper() {
        new Timer().schedule(new AnonymousClass1(), 1000L, 1000L);
    }

    /* JADX INFO: renamed from: com.byd.car.iot.converter.DefaultTaskWrapper$1, reason: invalid class name */
    class AnonymousClass1 extends TimerTask {
        AnonymousClass1() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            DefaultTaskWrapper.this.readTaskMap.forEach(new BiConsumer() { // from class: com.byd.car.iot.converter.-$$Lambda$DefaultTaskWrapper$1$8-3kezF0b-rBhJfR9LeUtZb4rsY
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    this.f$0.lambda$run$1$DefaultTaskWrapper$1((String) obj, (Map) obj2);
                }
            });
            DefaultTaskWrapper.this.writeTaskMap.forEach(new BiConsumer() { // from class: com.byd.car.iot.converter.-$$Lambda$DefaultTaskWrapper$1$ksiKZXdjZLzO3POELbRWBVLKWdk
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    this.f$0.lambda$run$3$DefaultTaskWrapper$1((String) obj, (Map) obj2);
                }
            });
        }

        public /* synthetic */ void lambda$run$1$DefaultTaskWrapper$1(final String str, final Map map) {
            if (map == null) {
                DefaultTaskWrapper.this.readTaskMap.remove(str);
                return;
            }
            map.forEach(new BiConsumer() { // from class: com.byd.car.iot.converter.-$$Lambda$DefaultTaskWrapper$1$r4ql_Pj_BfZLfgLb4hb8g3Eju38
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    DefaultTaskWrapper.AnonymousClass1.lambda$run$0(map, str, (String) obj, (IotTask) obj2);
                }
            });
            if (map.isEmpty()) {
                DefaultTaskWrapper.this.readTaskMap.remove(str);
            }
        }

        static /* synthetic */ void lambda$run$0(Map map, String str, String str2, IotTask iotTask) {
            if (iotTask.isTimeout()) {
                iotTask.closeByException(new TimeoutException());
                map.remove(str2);
                Log.d(DefaultTaskWrapper.TAG, "clean  deviceId " + str + " read task ! remaining:" + map.size());
            }
        }

        public /* synthetic */ void lambda$run$3$DefaultTaskWrapper$1(final String str, final Map map) {
            if (map == null) {
                DefaultTaskWrapper.this.writeTaskMap.remove(str);
                return;
            }
            map.forEach(new BiConsumer() { // from class: com.byd.car.iot.converter.-$$Lambda$DefaultTaskWrapper$1$-AYj26M-fYSdZYcHWr0aLveYoio
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    DefaultTaskWrapper.AnonymousClass1.lambda$run$2(map, str, (String) obj, (IotTask) obj2);
                }
            });
            if (map.isEmpty()) {
                DefaultTaskWrapper.this.writeTaskMap.remove(str);
            }
        }

        static /* synthetic */ void lambda$run$2(Map map, String str, String str2, IotTask iotTask) {
            if (iotTask.isTimeout()) {
                iotTask.closeByException(new TimeoutException());
                map.remove(str2);
                Log.d(DefaultTaskWrapper.TAG, "clean deviceId " + str + " write task ! remaining:" + map.size());
            }
        }
    }

    @Override // com.byd.car.iot.converter.TaskWrapper
    public Optional<IotTask<AutoiotProfile>> findReadTask(String str, final String str2) {
        if (StringUtils.isEmpty(str) || StringUtils.isEmpty(str2)) {
            return Optional.empty();
        }
        return Optional.ofNullable(this.readTaskMap.get(str)).map(new Function() { // from class: com.byd.car.iot.converter.-$$Lambda$DefaultTaskWrapper$Lzu6HPR8IMaebO2tOP1KHjDHJ_c
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DefaultTaskWrapper.lambda$findReadTask$0(str2, (Map) obj);
            }
        });
    }

    static /* synthetic */ IotTask lambda$findReadTask$0(String str, Map map) {
        return (IotTask) map.get(str);
    }

    @Override // com.byd.car.iot.converter.TaskWrapper
    public Optional<IotTask<Boolean>> findWriteTask(String str, final String str2) {
        if (StringUtils.isEmpty(str) || StringUtils.isEmpty(str2)) {
            return Optional.empty();
        }
        return Optional.ofNullable(this.writeTaskMap.get(str)).map(new Function() { // from class: com.byd.car.iot.converter.-$$Lambda$DefaultTaskWrapper$XEvWKhMbzCkEa1mDrEfuvopgqYk
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DefaultTaskWrapper.lambda$findWriteTask$1(str2, (Map) obj);
            }
        });
    }

    static /* synthetic */ IotTask lambda$findWriteTask$1(String str, Map map) {
        return (IotTask) map.get(str);
    }

    @Override // com.byd.car.iot.converter.TaskWrapper
    public void removeReadTask(final String str, final String str2) {
        Optional.ofNullable(this.readTaskMap.get(str)).ifPresent(new Consumer() { // from class: com.byd.car.iot.converter.-$$Lambda$DefaultTaskWrapper$OYkxH4SFwkaa2XrJQXpN2Bz3dOo
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$removeReadTask$2$DefaultTaskWrapper(str2, str, (Map) obj);
            }
        });
    }

    public /* synthetic */ void lambda$removeReadTask$2$DefaultTaskWrapper(String str, String str2, Map map) {
        map.remove(str);
        if (map.isEmpty()) {
            this.readTaskMap.remove(str2);
        }
    }

    @Override // com.byd.car.iot.converter.TaskWrapper
    public void removeWriteTask(final String str, final String str2) {
        Optional.ofNullable(this.writeTaskMap.get(str)).ifPresent(new Consumer() { // from class: com.byd.car.iot.converter.-$$Lambda$DefaultTaskWrapper$k74IZIo3FB4qjf9z9BjxYVGrtlI
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$removeWriteTask$3$DefaultTaskWrapper(str2, str, (Map) obj);
            }
        });
    }

    public /* synthetic */ void lambda$removeWriteTask$3$DefaultTaskWrapper(String str, String str2, Map map) {
        map.remove(str);
        if (map.isEmpty()) {
            this.writeTaskMap.remove(str2);
        }
    }

    static /* synthetic */ Map lambda$addReadTask$4(String str) {
        return new ConcurrentHashMap();
    }

    @Override // com.byd.car.iot.converter.TaskWrapper
    public void addReadTask(String str, String str2, IotTask<AutoiotProfile> iotTask) {
        this.readTaskMap.computeIfAbsent(str, new Function() { // from class: com.byd.car.iot.converter.-$$Lambda$DefaultTaskWrapper$r3-81f1VRVewtoTPZFxPkiXTq9E
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DefaultTaskWrapper.lambda$addReadTask$4((String) obj);
            }
        }).put(str2, iotTask);
    }

    static /* synthetic */ Map lambda$addWriteTask$5(String str) {
        return new ConcurrentHashMap();
    }

    @Override // com.byd.car.iot.converter.TaskWrapper
    public void addWriteTask(String str, String str2, IotTask<Boolean> iotTask) {
        this.writeTaskMap.computeIfAbsent(str, new Function() { // from class: com.byd.car.iot.converter.-$$Lambda$DefaultTaskWrapper$CFIj1woRj8Ma0TBKc6EoSRL-aMU
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DefaultTaskWrapper.lambda$addWriteTask$5((String) obj);
            }
        }).put(str2, iotTask);
    }

    @Override // com.byd.autoiot.service.IAutoiotEventListener
    public void onReadResult(String str, String str2, String str3, final AutoiotProfile autoiotProfile) {
        Log.d(TAG, "onReadResult  deviceId [" + str + "] attr [" + str2 + "_" + str3 + Ini.SECTION_SUFFIX);
        findReadTask(str, AutoiotSettingConstants.assembleAttr(str2, str3)).ifPresent(new Consumer() { // from class: com.byd.car.iot.converter.-$$Lambda$DefaultTaskWrapper$RY5qLgOWdyLODTk-kiuebP0X__A
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((IotTask) obj).finish(autoiotProfile);
            }
        });
    }

    @Override // com.byd.autoiot.service.IAutoiotEventListener
    public void onWriteResult(String str, String str2, String str3, final boolean z) {
        Log.d(TAG, "onWriteResult  deviceId [" + str + "] attr [" + str2 + "_" + str3 + "] result:" + z);
        findWriteTask(str, AutoiotSettingConstants.assembleAttr(str2, str3)).ifPresent(new Consumer() { // from class: com.byd.car.iot.converter.-$$Lambda$DefaultTaskWrapper$MJztFIgSxU6H_sAQBodYuHdi2qI
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((IotTask) obj).finish(Boolean.valueOf(z));
            }
        });
    }
}
