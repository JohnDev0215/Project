package api.requests.com.neusoft.greatwall.greatwallsysapiinverse;

import api.requests.com.neusoft.greatwall.greatwallsysapiinverse.ReqClearUserProfileData;
import api.requests.com.neusoft.greatwall.greatwallsysapiinverse.ReqGetUserProfileData;
import api.requests.com.neusoft.greatwall.greatwallsysapiinverse.ReqOnDayNightChanged;
import api.requests.com.neusoft.greatwall.greatwallsysapiinverse.ReqResponseGwCurrentWTInfoState;
import api.requests.com.neusoft.greatwall.greatwallsysapiinverse.ReqSetUserProfileData;
import api.responses.com.neusoft.greatwall.greatwallsysapiinverse.ResClearUserProfileData;
import api.responses.com.neusoft.greatwall.greatwallsysapiinverse.ResGetCongestedRoadStatus;
import api.responses.com.neusoft.greatwall.greatwallsysapiinverse.ResGetDayNightStatusUpdate;
import api.responses.com.neusoft.greatwall.greatwallsysapiinverse.ResGetGwCurrentWTInfo;
import api.responses.com.neusoft.greatwall.greatwallsysapiinverse.ResGetTunnelStatus;
import api.responses.com.neusoft.greatwall.greatwallsysapiinverse.ResGetUserProfileData;
import api.responses.com.neusoft.greatwall.greatwallsysapiinverse.ResSetUserProfileData;
import java9.util.concurrent.CompletableFuture;
import org.threeten.bp.Duration;

/* JADX INFO: loaded from: classes.dex */
public interface IGreatWallSysApiInverseRequests {
    CompletableFuture<Void> fireClearUserProfileData(ReqClearUserProfileData.Arguments arguments);

    CompletableFuture<Void> fireClearUserProfileData(ReqClearUserProfileData.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireClearUserProfileData(ReqClearUserProfileData reqClearUserProfileData);

    CompletableFuture<Void> fireClearUserProfileData(ReqClearUserProfileData reqClearUserProfileData, Duration duration);

    CompletableFuture<Void> fireGetCongestedRoadStatus();

    CompletableFuture<Void> fireGetCongestedRoadStatus(ReqGetCongestedRoadStatus reqGetCongestedRoadStatus);

    CompletableFuture<Void> fireGetCongestedRoadStatus(ReqGetCongestedRoadStatus reqGetCongestedRoadStatus, Duration duration);

    CompletableFuture<Void> fireGetCongestedRoadStatus(Duration duration);

    CompletableFuture<Void> fireGetDayNightStatusUpdate();

    CompletableFuture<Void> fireGetDayNightStatusUpdate(ReqGetDayNightStatusUpdate reqGetDayNightStatusUpdate);

    CompletableFuture<Void> fireGetDayNightStatusUpdate(ReqGetDayNightStatusUpdate reqGetDayNightStatusUpdate, Duration duration);

    CompletableFuture<Void> fireGetDayNightStatusUpdate(Duration duration);

    CompletableFuture<Void> fireGetGwCurrentWTInfo();

    CompletableFuture<Void> fireGetGwCurrentWTInfo(ReqGetGwCurrentWTInfo reqGetGwCurrentWTInfo);

    CompletableFuture<Void> fireGetGwCurrentWTInfo(ReqGetGwCurrentWTInfo reqGetGwCurrentWTInfo, Duration duration);

    CompletableFuture<Void> fireGetGwCurrentWTInfo(Duration duration);

    CompletableFuture<Void> fireGetTunnelStatus();

    CompletableFuture<Void> fireGetTunnelStatus(ReqGetTunnelStatus reqGetTunnelStatus);

    CompletableFuture<Void> fireGetTunnelStatus(ReqGetTunnelStatus reqGetTunnelStatus, Duration duration);

    CompletableFuture<Void> fireGetTunnelStatus(Duration duration);

    CompletableFuture<Void> fireGetUserProfileData(ReqGetUserProfileData.Arguments arguments);

    CompletableFuture<Void> fireGetUserProfileData(ReqGetUserProfileData.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireGetUserProfileData(ReqGetUserProfileData reqGetUserProfileData);

    CompletableFuture<Void> fireGetUserProfileData(ReqGetUserProfileData reqGetUserProfileData, Duration duration);

    CompletableFuture<Void> fireOnDayNightChanged(ReqOnDayNightChanged.Arguments arguments);

    CompletableFuture<Void> fireOnDayNightChanged(ReqOnDayNightChanged.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireOnDayNightChanged(ReqOnDayNightChanged reqOnDayNightChanged);

    CompletableFuture<Void> fireOnDayNightChanged(ReqOnDayNightChanged reqOnDayNightChanged, Duration duration);

    CompletableFuture<Void> fireResponseGwCurrentWTInfoState(ReqResponseGwCurrentWTInfoState.Arguments arguments);

    CompletableFuture<Void> fireResponseGwCurrentWTInfoState(ReqResponseGwCurrentWTInfoState.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireResponseGwCurrentWTInfoState(ReqResponseGwCurrentWTInfoState reqResponseGwCurrentWTInfoState);

    CompletableFuture<Void> fireResponseGwCurrentWTInfoState(ReqResponseGwCurrentWTInfoState reqResponseGwCurrentWTInfoState, Duration duration);

    CompletableFuture<Void> fireSetUserProfileData(ReqSetUserProfileData.Arguments arguments);

    CompletableFuture<Void> fireSetUserProfileData(ReqSetUserProfileData.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetUserProfileData(ReqSetUserProfileData reqSetUserProfileData);

    CompletableFuture<Void> fireSetUserProfileData(ReqSetUserProfileData reqSetUserProfileData, Duration duration);

    CompletableFuture<ResClearUserProfileData.Arguments> requestClearUserProfileData(ReqClearUserProfileData.Arguments arguments);

    CompletableFuture<ResClearUserProfileData.Arguments> requestClearUserProfileData(ReqClearUserProfileData.Arguments arguments, Duration duration);

    CompletableFuture<ResClearUserProfileData> requestClearUserProfileData(ReqClearUserProfileData reqClearUserProfileData);

    CompletableFuture<ResClearUserProfileData> requestClearUserProfileData(ReqClearUserProfileData reqClearUserProfileData, Duration duration);

    CompletableFuture<ResGetCongestedRoadStatus> requestGetCongestedRoadStatus();

    CompletableFuture<ResGetCongestedRoadStatus> requestGetCongestedRoadStatus(ReqGetCongestedRoadStatus reqGetCongestedRoadStatus);

    CompletableFuture<ResGetCongestedRoadStatus> requestGetCongestedRoadStatus(ReqGetCongestedRoadStatus reqGetCongestedRoadStatus, Duration duration);

    CompletableFuture<ResGetCongestedRoadStatus> requestGetCongestedRoadStatus(Duration duration);

    CompletableFuture<ResGetDayNightStatusUpdate> requestGetDayNightStatusUpdate();

    CompletableFuture<ResGetDayNightStatusUpdate> requestGetDayNightStatusUpdate(ReqGetDayNightStatusUpdate reqGetDayNightStatusUpdate);

    CompletableFuture<ResGetDayNightStatusUpdate> requestGetDayNightStatusUpdate(ReqGetDayNightStatusUpdate reqGetDayNightStatusUpdate, Duration duration);

    CompletableFuture<ResGetDayNightStatusUpdate> requestGetDayNightStatusUpdate(Duration duration);

    CompletableFuture<ResGetGwCurrentWTInfo> requestGetGwCurrentWTInfo();

    CompletableFuture<ResGetGwCurrentWTInfo> requestGetGwCurrentWTInfo(ReqGetGwCurrentWTInfo reqGetGwCurrentWTInfo);

    CompletableFuture<ResGetGwCurrentWTInfo> requestGetGwCurrentWTInfo(ReqGetGwCurrentWTInfo reqGetGwCurrentWTInfo, Duration duration);

    CompletableFuture<ResGetGwCurrentWTInfo> requestGetGwCurrentWTInfo(Duration duration);

    CompletableFuture<ResGetTunnelStatus> requestGetTunnelStatus();

    CompletableFuture<ResGetTunnelStatus> requestGetTunnelStatus(ReqGetTunnelStatus reqGetTunnelStatus);

    CompletableFuture<ResGetTunnelStatus> requestGetTunnelStatus(ReqGetTunnelStatus reqGetTunnelStatus, Duration duration);

    CompletableFuture<ResGetTunnelStatus> requestGetTunnelStatus(Duration duration);

    CompletableFuture<ResGetUserProfileData.Arguments> requestGetUserProfileData(ReqGetUserProfileData.Arguments arguments);

    CompletableFuture<ResGetUserProfileData.Arguments> requestGetUserProfileData(ReqGetUserProfileData.Arguments arguments, Duration duration);

    CompletableFuture<ResGetUserProfileData> requestGetUserProfileData(ReqGetUserProfileData reqGetUserProfileData);

    CompletableFuture<ResGetUserProfileData> requestGetUserProfileData(ReqGetUserProfileData reqGetUserProfileData, Duration duration);

    CompletableFuture<ResSetUserProfileData.Arguments> requestSetUserProfileData(ReqSetUserProfileData.Arguments arguments);

    CompletableFuture<ResSetUserProfileData.Arguments> requestSetUserProfileData(ReqSetUserProfileData.Arguments arguments, Duration duration);

    CompletableFuture<ResSetUserProfileData> requestSetUserProfileData(ReqSetUserProfileData reqSetUserProfileData);

    CompletableFuture<ResSetUserProfileData> requestSetUserProfileData(ReqSetUserProfileData reqSetUserProfileData, Duration duration);
}
