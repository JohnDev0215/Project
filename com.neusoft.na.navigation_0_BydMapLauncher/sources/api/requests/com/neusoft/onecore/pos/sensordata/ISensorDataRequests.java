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
import java9.util.concurrent.CompletableFuture;
import org.threeten.bp.Duration;

/* JADX INFO: loaded from: classes.dex */
public interface ISensorDataRequests {
    CompletableFuture<Void> fireDataProviderVersion(ReqDataProviderVersion.Arguments arguments);

    CompletableFuture<Void> fireDataProviderVersion(ReqDataProviderVersion.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireDataProviderVersion(ReqDataProviderVersion reqDataProviderVersion);

    CompletableFuture<Void> fireDataProviderVersion(ReqDataProviderVersion reqDataProviderVersion, Duration duration);

    CompletableFuture<Void> fireSetAcceleration(ReqSetAcceleration.Arguments arguments);

    CompletableFuture<Void> fireSetAcceleration(ReqSetAcceleration.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetAcceleration(ReqSetAcceleration reqSetAcceleration);

    CompletableFuture<Void> fireSetAcceleration(ReqSetAcceleration reqSetAcceleration, Duration duration);

    CompletableFuture<Void> fireSetAngularVelocity(ReqSetAngularVelocity.Arguments arguments);

    CompletableFuture<Void> fireSetAngularVelocity(ReqSetAngularVelocity.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetAngularVelocity(ReqSetAngularVelocity reqSetAngularVelocity);

    CompletableFuture<Void> fireSetAngularVelocity(ReqSetAngularVelocity reqSetAngularVelocity, Duration duration);

    CompletableFuture<Void> fireSetCameraBasedLaneInfo(ReqSetCameraBasedLaneInfo.Arguments arguments);

    CompletableFuture<Void> fireSetCameraBasedLaneInfo(ReqSetCameraBasedLaneInfo.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetCameraBasedLaneInfo(ReqSetCameraBasedLaneInfo reqSetCameraBasedLaneInfo);

    CompletableFuture<Void> fireSetCameraBasedLaneInfo(ReqSetCameraBasedLaneInfo reqSetCameraBasedLaneInfo, Duration duration);

    CompletableFuture<Void> fireSetDeadReckoning(ReqSetDeadReckoning.Arguments arguments);

    CompletableFuture<Void> fireSetDeadReckoning(ReqSetDeadReckoning.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetDeadReckoning(ReqSetDeadReckoning reqSetDeadReckoning);

    CompletableFuture<Void> fireSetDeadReckoning(ReqSetDeadReckoning reqSetDeadReckoning, Duration duration);

    CompletableFuture<Void> fireSetGps(ReqSetGps.Arguments arguments);

    CompletableFuture<Void> fireSetGps(ReqSetGps.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetGps(ReqSetGps reqSetGps);

    CompletableFuture<Void> fireSetGps(ReqSetGps reqSetGps, Duration duration);

    CompletableFuture<Void> fireSetNmea(ReqSetNmea.Arguments arguments);

    CompletableFuture<Void> fireSetNmea(ReqSetNmea.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetNmea(ReqSetNmea reqSetNmea);

    CompletableFuture<Void> fireSetNmea(ReqSetNmea reqSetNmea, Duration duration);

    CompletableFuture<Void> fireSetOdometer(ReqSetOdometer.Arguments arguments);

    CompletableFuture<Void> fireSetOdometer(ReqSetOdometer.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetOdometer(ReqSetOdometer reqSetOdometer);

    CompletableFuture<Void> fireSetOdometer(ReqSetOdometer reqSetOdometer, Duration duration);

    CompletableFuture<Void> fireSetSpeed(ReqSetSpeed.Arguments arguments);

    CompletableFuture<Void> fireSetSpeed(ReqSetSpeed.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetSpeed(ReqSetSpeed reqSetSpeed);

    CompletableFuture<Void> fireSetSpeed(ReqSetSpeed reqSetSpeed, Duration duration);
}
