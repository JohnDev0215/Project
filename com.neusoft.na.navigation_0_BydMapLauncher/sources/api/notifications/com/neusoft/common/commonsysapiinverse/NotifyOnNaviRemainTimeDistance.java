package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.RemainTimeDist;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyOnNaviRemainTimeDistance extends BaseNotification {
    private static final long serialVersionUID = -1478141435;

    @SerializedName("args")
    private RemainTimeDist onNaviRemainTimeDistance;

    public NotifyOnNaviRemainTimeDistance() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.OnNaviRemainTimeDistance");
    }

    public NotifyOnNaviRemainTimeDistance(RemainTimeDist remainTimeDist) {
        this();
        this.onNaviRemainTimeDistance = remainTimeDist;
    }

    public RemainTimeDist getOnNaviRemainTimeDistance() {
        return this.onNaviRemainTimeDistance;
    }

    public NotifyOnNaviRemainTimeDistance setOnNaviRemainTimeDistance(RemainTimeDist remainTimeDist) {
        this.onNaviRemainTimeDistance = remainTimeDist;
        return this;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public EProjectInterfaceType getProjectInterfaceType() {
        return EProjectInterfaceType.OneCore;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public String toString() {
        return getProjectInterfaceType().getGson().toJson(this);
    }
}
