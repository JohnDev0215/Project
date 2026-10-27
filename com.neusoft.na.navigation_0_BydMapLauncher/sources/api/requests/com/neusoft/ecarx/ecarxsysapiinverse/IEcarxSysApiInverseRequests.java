package api.requests.com.neusoft.ecarx.ecarxsysapiinverse;

import api.requests.com.neusoft.ecarx.ecarxsysapiinverse.ReqOnDayNightChanged;
import api.requests.com.neusoft.ecarx.ecarxsysapiinverse.ReqOnDayNightModeChanged;
import api.requests.com.neusoft.ecarx.ecarxsysapiinverse.ReqUpdateCurrentWeatherInfo;
import api.requests.com.neusoft.ecarx.ecarxsysapiinverse.ReqUpdateFutureWeatherInfo;
import api.responses.com.neusoft.ecarx.ecarxsysapiinverse.ResGetAuthStatus;
import api.responses.com.neusoft.ecarx.ecarxsysapiinverse.ResGetQueryCurrentWeatherInfo;
import api.responses.com.neusoft.ecarx.ecarxsysapiinverse.ResGetQueryFutureWeatherInfo;
import api.responses.com.neusoft.ecarx.ecarxsysapiinverse.ResGetTunnelStatus;
import java9.util.concurrent.CompletableFuture;
import org.threeten.bp.Duration;

/* JADX INFO: loaded from: classes.dex */
public interface IEcarxSysApiInverseRequests {
    CompletableFuture<Void> fireGetAuthStatus();

    CompletableFuture<Void> fireGetAuthStatus(ReqGetAuthStatus reqGetAuthStatus);

    CompletableFuture<Void> fireGetAuthStatus(ReqGetAuthStatus reqGetAuthStatus, Duration duration);

    CompletableFuture<Void> fireGetAuthStatus(Duration duration);

    CompletableFuture<Void> fireGetQueryCurrentWeatherInfo();

    CompletableFuture<Void> fireGetQueryCurrentWeatherInfo(ReqGetQueryCurrentWeatherInfo reqGetQueryCurrentWeatherInfo);

    CompletableFuture<Void> fireGetQueryCurrentWeatherInfo(ReqGetQueryCurrentWeatherInfo reqGetQueryCurrentWeatherInfo, Duration duration);

    CompletableFuture<Void> fireGetQueryCurrentWeatherInfo(Duration duration);

    CompletableFuture<Void> fireGetQueryFutureWeatherInfo();

    CompletableFuture<Void> fireGetQueryFutureWeatherInfo(ReqGetQueryFutureWeatherInfo reqGetQueryFutureWeatherInfo);

    CompletableFuture<Void> fireGetQueryFutureWeatherInfo(ReqGetQueryFutureWeatherInfo reqGetQueryFutureWeatherInfo, Duration duration);

    CompletableFuture<Void> fireGetQueryFutureWeatherInfo(Duration duration);

    CompletableFuture<Void> fireGetTunnelStatus();

    CompletableFuture<Void> fireGetTunnelStatus(ReqGetTunnelStatus reqGetTunnelStatus);

    CompletableFuture<Void> fireGetTunnelStatus(ReqGetTunnelStatus reqGetTunnelStatus, Duration duration);

    CompletableFuture<Void> fireGetTunnelStatus(Duration duration);

    CompletableFuture<Void> fireOnDayNightChanged(ReqOnDayNightChanged.Arguments arguments);

    CompletableFuture<Void> fireOnDayNightChanged(ReqOnDayNightChanged.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireOnDayNightChanged(ReqOnDayNightChanged reqOnDayNightChanged);

    CompletableFuture<Void> fireOnDayNightChanged(ReqOnDayNightChanged reqOnDayNightChanged, Duration duration);

    CompletableFuture<Void> fireOnDayNightModeChanged(ReqOnDayNightModeChanged.Arguments arguments);

    CompletableFuture<Void> fireOnDayNightModeChanged(ReqOnDayNightModeChanged.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireOnDayNightModeChanged(ReqOnDayNightModeChanged reqOnDayNightModeChanged);

    CompletableFuture<Void> fireOnDayNightModeChanged(ReqOnDayNightModeChanged reqOnDayNightModeChanged, Duration duration);

    CompletableFuture<Void> fireUpdateCurrentWeatherInfo(ReqUpdateCurrentWeatherInfo.Arguments arguments);

    CompletableFuture<Void> fireUpdateCurrentWeatherInfo(ReqUpdateCurrentWeatherInfo.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireUpdateCurrentWeatherInfo(ReqUpdateCurrentWeatherInfo reqUpdateCurrentWeatherInfo);

    CompletableFuture<Void> fireUpdateCurrentWeatherInfo(ReqUpdateCurrentWeatherInfo reqUpdateCurrentWeatherInfo, Duration duration);

    CompletableFuture<Void> fireUpdateFutureWeatherInfo(ReqUpdateFutureWeatherInfo.Arguments arguments);

    CompletableFuture<Void> fireUpdateFutureWeatherInfo(ReqUpdateFutureWeatherInfo.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireUpdateFutureWeatherInfo(ReqUpdateFutureWeatherInfo reqUpdateFutureWeatherInfo);

    CompletableFuture<Void> fireUpdateFutureWeatherInfo(ReqUpdateFutureWeatherInfo reqUpdateFutureWeatherInfo, Duration duration);

    CompletableFuture<ResGetAuthStatus> requestGetAuthStatus();

    CompletableFuture<ResGetAuthStatus> requestGetAuthStatus(ReqGetAuthStatus reqGetAuthStatus);

    CompletableFuture<ResGetAuthStatus> requestGetAuthStatus(ReqGetAuthStatus reqGetAuthStatus, Duration duration);

    CompletableFuture<ResGetAuthStatus> requestGetAuthStatus(Duration duration);

    CompletableFuture<ResGetQueryCurrentWeatherInfo> requestGetQueryCurrentWeatherInfo();

    CompletableFuture<ResGetQueryCurrentWeatherInfo> requestGetQueryCurrentWeatherInfo(ReqGetQueryCurrentWeatherInfo reqGetQueryCurrentWeatherInfo);

    CompletableFuture<ResGetQueryCurrentWeatherInfo> requestGetQueryCurrentWeatherInfo(ReqGetQueryCurrentWeatherInfo reqGetQueryCurrentWeatherInfo, Duration duration);

    CompletableFuture<ResGetQueryCurrentWeatherInfo> requestGetQueryCurrentWeatherInfo(Duration duration);

    CompletableFuture<ResGetQueryFutureWeatherInfo> requestGetQueryFutureWeatherInfo();

    CompletableFuture<ResGetQueryFutureWeatherInfo> requestGetQueryFutureWeatherInfo(ReqGetQueryFutureWeatherInfo reqGetQueryFutureWeatherInfo);

    CompletableFuture<ResGetQueryFutureWeatherInfo> requestGetQueryFutureWeatherInfo(ReqGetQueryFutureWeatherInfo reqGetQueryFutureWeatherInfo, Duration duration);

    CompletableFuture<ResGetQueryFutureWeatherInfo> requestGetQueryFutureWeatherInfo(Duration duration);

    CompletableFuture<ResGetTunnelStatus> requestGetTunnelStatus();

    CompletableFuture<ResGetTunnelStatus> requestGetTunnelStatus(ReqGetTunnelStatus reqGetTunnelStatus);

    CompletableFuture<ResGetTunnelStatus> requestGetTunnelStatus(ReqGetTunnelStatus reqGetTunnelStatus, Duration duration);

    CompletableFuture<ResGetTunnelStatus> requestGetTunnelStatus(Duration duration);
}
