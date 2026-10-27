package api.notifications.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.GuidanceInfoZeekr;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyGuidanceInfoZeekrUpdate extends BaseNotification {
    private static final long serialVersionUID = -1479064808;

    @SerializedName("args")
    private GuidanceInfoZeekr guidanceInfoZeekrUpdate;

    public NotifyGuidanceInfoZeekrUpdate() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.smart.SmartNavApi.GuidanceInfoZeekrUpdate");
    }

    public NotifyGuidanceInfoZeekrUpdate(GuidanceInfoZeekr guidanceInfoZeekr) {
        this();
        this.guidanceInfoZeekrUpdate = guidanceInfoZeekr;
    }

    public GuidanceInfoZeekr getGuidanceInfoZeekrUpdate() {
        return this.guidanceInfoZeekrUpdate;
    }

    public NotifyGuidanceInfoZeekrUpdate setGuidanceInfoZeekrUpdate(GuidanceInfoZeekr guidanceInfoZeekr) {
        this.guidanceInfoZeekrUpdate = guidanceInfoZeekr;
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
