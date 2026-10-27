package api.requests.com.neusoft.byd.bydsysapiinverse;

import api.requests.com.neusoft.byd.bydsysapiinverse.ReqOnDayNightChanged;
import api.requests.com.neusoft.byd.bydsysapiinverse.ReqSetScreenInfo;
import api.requests.com.neusoft.byd.bydsysapiinverse.ReqSkipFunction;
import api.responses.com.neusoft.byd.bydsysapiinverse.ResGetNaviMainMapDayNightStatusChange;
import java9.util.concurrent.CompletableFuture;
import org.threeten.bp.Duration;

/* JADX INFO: loaded from: classes.dex */
public interface IBydSysApiInverseRequests {
    CompletableFuture<Void> fireGetNaviMainMapDayNightStatusChange();

    CompletableFuture<Void> fireGetNaviMainMapDayNightStatusChange(ReqGetNaviMainMapDayNightStatusChange reqGetNaviMainMapDayNightStatusChange);

    CompletableFuture<Void> fireGetNaviMainMapDayNightStatusChange(ReqGetNaviMainMapDayNightStatusChange reqGetNaviMainMapDayNightStatusChange, Duration duration);

    CompletableFuture<Void> fireGetNaviMainMapDayNightStatusChange(Duration duration);

    CompletableFuture<Void> fireOnDayNightChanged(ReqOnDayNightChanged.Arguments arguments);

    CompletableFuture<Void> fireOnDayNightChanged(ReqOnDayNightChanged.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireOnDayNightChanged(ReqOnDayNightChanged reqOnDayNightChanged);

    CompletableFuture<Void> fireOnDayNightChanged(ReqOnDayNightChanged reqOnDayNightChanged, Duration duration);

    CompletableFuture<Void> fireSetScreenInfo(ReqSetScreenInfo.Arguments arguments);

    CompletableFuture<Void> fireSetScreenInfo(ReqSetScreenInfo.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSetScreenInfo(ReqSetScreenInfo reqSetScreenInfo);

    CompletableFuture<Void> fireSetScreenInfo(ReqSetScreenInfo reqSetScreenInfo, Duration duration);

    CompletableFuture<Void> fireSkipFunction(ReqSkipFunction.Arguments arguments);

    CompletableFuture<Void> fireSkipFunction(ReqSkipFunction.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireSkipFunction(ReqSkipFunction reqSkipFunction);

    CompletableFuture<Void> fireSkipFunction(ReqSkipFunction reqSkipFunction, Duration duration);

    CompletableFuture<ResGetNaviMainMapDayNightStatusChange> requestGetNaviMainMapDayNightStatusChange();

    CompletableFuture<ResGetNaviMainMapDayNightStatusChange> requestGetNaviMainMapDayNightStatusChange(ReqGetNaviMainMapDayNightStatusChange reqGetNaviMainMapDayNightStatusChange);

    CompletableFuture<ResGetNaviMainMapDayNightStatusChange> requestGetNaviMainMapDayNightStatusChange(ReqGetNaviMainMapDayNightStatusChange reqGetNaviMainMapDayNightStatusChange, Duration duration);

    CompletableFuture<ResGetNaviMainMapDayNightStatusChange> requestGetNaviMainMapDayNightStatusChange(Duration duration);
}
