package api.requests.com.neusoft.changan.changansysapiinverse;

import api.requests.com.neusoft.changan.changansysapiinverse.ReqOnDayNightChanged;
import api.requests.com.neusoft.changan.changansysapiinverse.ReqOnDayNightModeChanged;
import api.responses.com.neusoft.changan.changansysapiinverse.ResGetSoundTypeChanged;
import java9.util.concurrent.CompletableFuture;
import org.threeten.bp.Duration;

/* JADX INFO: loaded from: classes.dex */
public interface IChangAnSysApiInverseRequests {
    CompletableFuture<Void> fireGetSoundTypeChanged();

    CompletableFuture<Void> fireGetSoundTypeChanged(ReqGetSoundTypeChanged reqGetSoundTypeChanged);

    CompletableFuture<Void> fireGetSoundTypeChanged(ReqGetSoundTypeChanged reqGetSoundTypeChanged, Duration duration);

    CompletableFuture<Void> fireGetSoundTypeChanged(Duration duration);

    CompletableFuture<Void> fireOnDayNightChanged(ReqOnDayNightChanged.Arguments arguments);

    CompletableFuture<Void> fireOnDayNightChanged(ReqOnDayNightChanged.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireOnDayNightChanged(ReqOnDayNightChanged reqOnDayNightChanged);

    CompletableFuture<Void> fireOnDayNightChanged(ReqOnDayNightChanged reqOnDayNightChanged, Duration duration);

    CompletableFuture<Void> fireOnDayNightModeChanged(ReqOnDayNightModeChanged.Arguments arguments);

    CompletableFuture<Void> fireOnDayNightModeChanged(ReqOnDayNightModeChanged.Arguments arguments, Duration duration);

    CompletableFuture<Void> fireOnDayNightModeChanged(ReqOnDayNightModeChanged reqOnDayNightModeChanged);

    CompletableFuture<Void> fireOnDayNightModeChanged(ReqOnDayNightModeChanged reqOnDayNightModeChanged, Duration duration);

    CompletableFuture<ResGetSoundTypeChanged> requestGetSoundTypeChanged();

    CompletableFuture<ResGetSoundTypeChanged> requestGetSoundTypeChanged(ReqGetSoundTypeChanged reqGetSoundTypeChanged);

    CompletableFuture<ResGetSoundTypeChanged> requestGetSoundTypeChanged(ReqGetSoundTypeChanged reqGetSoundTypeChanged, Duration duration);

    CompletableFuture<ResGetSoundTypeChanged> requestGetSoundTypeChanged(Duration duration);
}
