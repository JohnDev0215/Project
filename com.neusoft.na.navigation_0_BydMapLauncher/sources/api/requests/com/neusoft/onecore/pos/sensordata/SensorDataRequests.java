package api.requests.com.neusoft.onecore.pos.sensordata;

import api.requests.com.neusoft.onecore.pos.sensordata.ReqDataProviderVersion;
import api.requests.com.neusoft.onecore.pos.sensordata.ReqSetAcceleration;
import api.requests.com.neusoft.onecore.pos.sensordata.ReqSetAngularVelocity;
import api.requests.com.neusoft.onecore.pos.sensordata.ReqSetCameraBasedLaneInfo;
import api.requests.com.neusoft.onecore.pos.sensordata.ReqSetDeadReckoning;
import api.requests.com.neusoft.onecore.pos.sensordata.ReqSetGps;
import api.requests.com.neusoft.onecore.pos.sensordata.ReqSetNmea;
import api.requests.com.neusoft.onecore.pos.sensordata.ReqSetOdometer;
import api.requests.com.neusoft.onecore.pos.sensordata.ReqSetSpeed;
import com.neusoft.de.nts.OneCoreTest.communication.Communication;
import com.neusoft.de.nts.OneCoreTest.interfaces.ICommunicationContextUtil;
import com.neusoft.de.nts.OneCoreTest.utils.CommonUtil;
import com.neusoft.de.nts.OneCoreTest.utils.Logger;
import java9.util.concurrent.CompletableFuture;
import java9.util.concurrent.CompletionStage;
import java9.util.function.Function;
import org.threeten.bp.Duration;

/* JADX INFO: loaded from: classes.dex */
public class SensorDataRequests implements ISensorDataRequests {
    private final String component;
    private final ICommunicationContextUtil<?, ?, ?> contextUtil;
    private static final Logger logger = Logger.getLogger((Class<?>) SensorDataRequests.class);
    private static final CompletableFuture<Void> completedVoidFuture = CompletableFuture.completedFuture(null);

    public SensorDataRequests(ICommunicationContextUtil<?, ?, ?> iCommunicationContextUtil) {
        this.contextUtil = iCommunicationContextUtil;
        this.component = null;
    }

    public SensorDataRequests(ICommunicationContextUtil<?, ?, ?> iCommunicationContextUtil, String str) {
        this.contextUtil = iCommunicationContextUtil;
        this.component = str;
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireDataProviderVersion(ReqDataProviderVersion reqDataProviderVersion) {
        reqDataProviderVersion.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqDataProviderVersion.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqDataProviderVersion)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onecore.pos.sensordata.SensorDataRequests.1
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return SensorDataRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireDataProviderVersion(ReqDataProviderVersion reqDataProviderVersion, final Duration duration) {
        return fireDataProviderVersion(reqDataProviderVersion).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onecore.pos.sensordata.SensorDataRequests.2
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SensorDataRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireDataProviderVersion(ReqDataProviderVersion.Arguments arguments) {
        ReqDataProviderVersion reqDataProviderVersion = new ReqDataProviderVersion();
        reqDataProviderVersion.setArguments(arguments);
        reqDataProviderVersion.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqDataProviderVersion.setComponent(str);
        }
        return fireDataProviderVersion(reqDataProviderVersion);
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireDataProviderVersion(ReqDataProviderVersion.Arguments arguments, final Duration duration) {
        return fireDataProviderVersion(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onecore.pos.sensordata.SensorDataRequests.3
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SensorDataRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetDeadReckoning(ReqSetDeadReckoning reqSetDeadReckoning) {
        reqSetDeadReckoning.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetDeadReckoning.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetDeadReckoning)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onecore.pos.sensordata.SensorDataRequests.4
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return SensorDataRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetDeadReckoning(ReqSetDeadReckoning reqSetDeadReckoning, final Duration duration) {
        return fireSetDeadReckoning(reqSetDeadReckoning).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onecore.pos.sensordata.SensorDataRequests.5
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SensorDataRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetDeadReckoning(ReqSetDeadReckoning.Arguments arguments) {
        ReqSetDeadReckoning reqSetDeadReckoning = new ReqSetDeadReckoning();
        reqSetDeadReckoning.setArguments(arguments);
        reqSetDeadReckoning.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetDeadReckoning.setComponent(str);
        }
        return fireSetDeadReckoning(reqSetDeadReckoning);
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetDeadReckoning(ReqSetDeadReckoning.Arguments arguments, final Duration duration) {
        return fireSetDeadReckoning(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onecore.pos.sensordata.SensorDataRequests.6
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SensorDataRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetGps(ReqSetGps reqSetGps) {
        reqSetGps.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetGps.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetGps)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onecore.pos.sensordata.SensorDataRequests.7
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return SensorDataRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetGps(ReqSetGps reqSetGps, final Duration duration) {
        return fireSetGps(reqSetGps).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onecore.pos.sensordata.SensorDataRequests.8
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SensorDataRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetGps(ReqSetGps.Arguments arguments) {
        ReqSetGps reqSetGps = new ReqSetGps();
        reqSetGps.setArguments(arguments);
        reqSetGps.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetGps.setComponent(str);
        }
        return fireSetGps(reqSetGps);
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetGps(ReqSetGps.Arguments arguments, final Duration duration) {
        return fireSetGps(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onecore.pos.sensordata.SensorDataRequests.9
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SensorDataRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetNmea(ReqSetNmea reqSetNmea) {
        reqSetNmea.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetNmea.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetNmea)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onecore.pos.sensordata.SensorDataRequests.10
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return SensorDataRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetNmea(ReqSetNmea reqSetNmea, final Duration duration) {
        return fireSetNmea(reqSetNmea).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onecore.pos.sensordata.SensorDataRequests.11
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SensorDataRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetNmea(ReqSetNmea.Arguments arguments) {
        ReqSetNmea reqSetNmea = new ReqSetNmea();
        reqSetNmea.setArguments(arguments);
        reqSetNmea.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetNmea.setComponent(str);
        }
        return fireSetNmea(reqSetNmea);
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetNmea(ReqSetNmea.Arguments arguments, final Duration duration) {
        return fireSetNmea(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onecore.pos.sensordata.SensorDataRequests.12
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SensorDataRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetOdometer(ReqSetOdometer reqSetOdometer) {
        reqSetOdometer.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetOdometer.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetOdometer)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onecore.pos.sensordata.SensorDataRequests.13
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return SensorDataRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetOdometer(ReqSetOdometer reqSetOdometer, final Duration duration) {
        return fireSetOdometer(reqSetOdometer).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onecore.pos.sensordata.SensorDataRequests.14
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SensorDataRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetOdometer(ReqSetOdometer.Arguments arguments) {
        ReqSetOdometer reqSetOdometer = new ReqSetOdometer();
        reqSetOdometer.setArguments(arguments);
        reqSetOdometer.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetOdometer.setComponent(str);
        }
        return fireSetOdometer(reqSetOdometer);
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetOdometer(ReqSetOdometer.Arguments arguments, final Duration duration) {
        return fireSetOdometer(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onecore.pos.sensordata.SensorDataRequests.15
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SensorDataRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetSpeed(ReqSetSpeed reqSetSpeed) {
        reqSetSpeed.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetSpeed.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetSpeed)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onecore.pos.sensordata.SensorDataRequests.16
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return SensorDataRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetSpeed(ReqSetSpeed reqSetSpeed, final Duration duration) {
        return fireSetSpeed(reqSetSpeed).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onecore.pos.sensordata.SensorDataRequests.17
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SensorDataRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetSpeed(ReqSetSpeed.Arguments arguments) {
        ReqSetSpeed reqSetSpeed = new ReqSetSpeed();
        reqSetSpeed.setArguments(arguments);
        reqSetSpeed.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetSpeed.setComponent(str);
        }
        return fireSetSpeed(reqSetSpeed);
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetSpeed(ReqSetSpeed.Arguments arguments, final Duration duration) {
        return fireSetSpeed(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onecore.pos.sensordata.SensorDataRequests.18
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SensorDataRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetAcceleration(ReqSetAcceleration reqSetAcceleration) {
        reqSetAcceleration.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetAcceleration.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetAcceleration)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onecore.pos.sensordata.SensorDataRequests.19
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return SensorDataRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetAcceleration(ReqSetAcceleration reqSetAcceleration, final Duration duration) {
        return fireSetAcceleration(reqSetAcceleration).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onecore.pos.sensordata.SensorDataRequests.20
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SensorDataRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetAcceleration(ReqSetAcceleration.Arguments arguments) {
        ReqSetAcceleration reqSetAcceleration = new ReqSetAcceleration();
        reqSetAcceleration.setArguments(arguments);
        reqSetAcceleration.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetAcceleration.setComponent(str);
        }
        return fireSetAcceleration(reqSetAcceleration);
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetAcceleration(ReqSetAcceleration.Arguments arguments, final Duration duration) {
        return fireSetAcceleration(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onecore.pos.sensordata.SensorDataRequests.21
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SensorDataRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetAngularVelocity(ReqSetAngularVelocity reqSetAngularVelocity) {
        reqSetAngularVelocity.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetAngularVelocity.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetAngularVelocity)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onecore.pos.sensordata.SensorDataRequests.22
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return SensorDataRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetAngularVelocity(ReqSetAngularVelocity reqSetAngularVelocity, final Duration duration) {
        return fireSetAngularVelocity(reqSetAngularVelocity).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onecore.pos.sensordata.SensorDataRequests.23
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SensorDataRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetAngularVelocity(ReqSetAngularVelocity.Arguments arguments) {
        ReqSetAngularVelocity reqSetAngularVelocity = new ReqSetAngularVelocity();
        reqSetAngularVelocity.setArguments(arguments);
        reqSetAngularVelocity.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetAngularVelocity.setComponent(str);
        }
        return fireSetAngularVelocity(reqSetAngularVelocity);
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetAngularVelocity(ReqSetAngularVelocity.Arguments arguments, final Duration duration) {
        return fireSetAngularVelocity(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onecore.pos.sensordata.SensorDataRequests.24
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SensorDataRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetCameraBasedLaneInfo(ReqSetCameraBasedLaneInfo reqSetCameraBasedLaneInfo) {
        reqSetCameraBasedLaneInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetCameraBasedLaneInfo.setComponent(str);
        }
        return Communication.request(this.contextUtil.createFromClass(reqSetCameraBasedLaneInfo)).thenCompose(new Function<Object, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onecore.pos.sensordata.SensorDataRequests.25
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Object obj) {
                return SensorDataRequests.completedVoidFuture;
            }
        });
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetCameraBasedLaneInfo(ReqSetCameraBasedLaneInfo reqSetCameraBasedLaneInfo, final Duration duration) {
        return fireSetCameraBasedLaneInfo(reqSetCameraBasedLaneInfo).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onecore.pos.sensordata.SensorDataRequests.26
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SensorDataRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetCameraBasedLaneInfo(ReqSetCameraBasedLaneInfo.Arguments arguments) {
        ReqSetCameraBasedLaneInfo reqSetCameraBasedLaneInfo = new ReqSetCameraBasedLaneInfo();
        reqSetCameraBasedLaneInfo.setArguments(arguments);
        reqSetCameraBasedLaneInfo.setResponse(null);
        String str = this.component;
        if (str != null) {
            reqSetCameraBasedLaneInfo.setComponent(str);
        }
        return fireSetCameraBasedLaneInfo(reqSetCameraBasedLaneInfo);
    }

    @Override // api.requests.com.neusoft.onecore.pos.sensordata.ISensorDataRequests
    public CompletableFuture<Void> fireSetCameraBasedLaneInfo(ReqSetCameraBasedLaneInfo.Arguments arguments, final Duration duration) {
        return fireSetCameraBasedLaneInfo(arguments).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletableFuture<Void>>() { // from class: api.requests.com.neusoft.onecore.pos.sensordata.SensorDataRequests.27
            @Override // java9.util.function.Function
            public CompletableFuture<Void> apply(Void r2) {
                return CommonUtil.asyncSleep(duration, SensorDataRequests.this.contextUtil.getDefaultExecutor());
            }
        });
    }
}
