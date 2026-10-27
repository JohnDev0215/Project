package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.GuidanceInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyGuidanceInfoUpdate extends BaseNotification {
    private static final long serialVersionUID = -1478141435;

    @SerializedName("args")
    private GuidanceInfo guidanceInfoUpdate;

    public NotifyGuidanceInfoUpdate() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.GuidanceInfoUpdate");
    }

    public NotifyGuidanceInfoUpdate(GuidanceInfo guidanceInfo) {
        this();
        this.guidanceInfoUpdate = guidanceInfo;
    }

    public GuidanceInfo getGuidanceInfoUpdate() {
        return this.guidanceInfoUpdate;
    }

    public NotifyGuidanceInfoUpdate setGuidanceInfoUpdate(GuidanceInfo guidanceInfo) {
        this.guidanceInfoUpdate = guidanceInfo;
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
