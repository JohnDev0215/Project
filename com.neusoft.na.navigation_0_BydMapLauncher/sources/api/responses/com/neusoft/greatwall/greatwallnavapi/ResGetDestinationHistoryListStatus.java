package api.responses.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.DestHistoryInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetDestinationHistoryListStatus extends BaseResponse {
    private static final long serialVersionUID = -1480915944;

    @SerializedName("args")
    private List<DestHistoryInfo> destinationHistoryListStatus;

    public ResGetDestinationHistoryListStatus() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.GetDestinationHistoryListStatus");
    }

    public ResGetDestinationHistoryListStatus(List<DestHistoryInfo> list) {
        this();
        this.destinationHistoryListStatus = list;
    }

    public List<DestHistoryInfo> getDestinationHistoryListStatus() {
        return this.destinationHistoryListStatus;
    }

    public ResGetDestinationHistoryListStatus setDestinationHistoryListStatus(List<DestHistoryInfo> list) {
        if (list == null) {
            throw new NullPointerException("destinationHistoryListStatus cannot be null");
        }
        this.destinationHistoryListStatus = list;
        return this;
    }

    public ResGetDestinationHistoryListStatus setDestinationHistoryListStatus(DestHistoryInfo... destHistoryInfoArr) {
        return setDestinationHistoryListStatus(Arrays.asList(destHistoryInfoArr));
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        if (eMessageType == EMessageType.Error) {
            this.destinationHistoryListStatus = null;
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResGetDestinationHistoryListStatus)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()));
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
