package api.processors.com.neusoft.onoff.onoffmaster;

import api.notifications.com.neusoft.onoff.onoffmaster.NotifyStatusChanged;
import api.requests.com.neusoft.onoff.onoffmaster.ReqGetStatus;
import api.requests.com.neusoft.onoff.onoffmaster.ReqStart;
import api.requests.com.neusoft.onoff.onoffmaster.ReqStop;
import api.responses.com.neusoft.onoff.onoffmaster.ResGetStatus;
import api.responses.com.neusoft.onoff.onoffmaster.ResStart;
import api.responses.com.neusoft.onoff.onoffmaster.ResStop;
import com.neusoft.de.nts.OneCoreTest.interfaces.IAttribute;
import java9.util.concurrent.CompletableFuture;

/* JADX INFO: loaded from: classes.dex */
public interface IOnOffMasterProcessor {
    IAttribute<NotifyStatusChanged> getNotifyStatusChanged();

    CompletableFuture<ResGetStatus> processReqGetStatus(ReqGetStatus reqGetStatus);

    CompletableFuture<ResStart> processReqStart(ReqStart reqStart);

    CompletableFuture<ResStop> processReqStop(ReqStop reqStop);
}
