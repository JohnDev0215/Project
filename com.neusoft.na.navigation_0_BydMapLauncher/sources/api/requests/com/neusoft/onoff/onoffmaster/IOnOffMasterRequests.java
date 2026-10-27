package api.requests.com.neusoft.onoff.onoffmaster;

import api.requests.com.neusoft.onoff.onoffmaster.ReqStart;
import api.requests.com.neusoft.onoff.onoffmaster.ReqStop;
import api.responses.com.neusoft.onoff.onoffmaster.ResGetStatus;
import java9.util.concurrent.CompletableFuture;
import org.threeten.bp.Duration;

/* JADX INFO: loaded from: classes.dex */
public interface IOnOffMasterRequests {
    CompletableFuture<Void> fireGetStatus();

    CompletableFuture<Void> fireGetStatus(ReqGetStatus reqGetStatus);

    CompletableFuture<Void> fireGetStatus(ReqGetStatus reqGetStatus, Duration duration);

    CompletableFuture<Void> fireGetStatus(Duration duration);

    CompletableFuture<Void> fireStart(ReqStart.Arguments arguments);

    CompletableFuture<Void> fireStart(ReqStart.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireStart(ReqStart reqStart);

    CompletableFuture<Void> fireStart(ReqStart reqStart, Duration duration);

    CompletableFuture<Void> fireStop(ReqStop.Arguments arguments);

    CompletableFuture<Void> fireStop(ReqStop.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireStop(ReqStop reqStop);

    CompletableFuture<Void> fireStop(ReqStop reqStop, Duration duration);

    CompletableFuture<ResGetStatus> requestGetStatus();

    CompletableFuture<ResGetStatus> requestGetStatus(ReqGetStatus reqGetStatus);

    CompletableFuture<ResGetStatus> requestGetStatus(ReqGetStatus reqGetStatus, Duration duration);

    CompletableFuture<ResGetStatus> requestGetStatus(Duration duration);
}
