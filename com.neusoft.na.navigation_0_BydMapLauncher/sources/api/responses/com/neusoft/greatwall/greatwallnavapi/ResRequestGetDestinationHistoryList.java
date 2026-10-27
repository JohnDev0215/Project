package api.responses.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.DestHistoryInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResRequestGetDestinationHistoryList extends BaseResponse {
    private static final long serialVersionUID = -1480916652;

    @SerializedName("args")
    private Arguments arguments;

    public ResRequestGetDestinationHistoryList() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.RequestGetDestinationHistoryList");
        this.arguments = new Arguments();
    }

    public ResRequestGetDestinationHistoryList(List<DestHistoryInfo> list) {
        this();
        this.arguments.setDestHistoryList(list);
    }

    public Arguments getArguments() {
        return this.arguments;
    }

    public void setArguments(Arguments arguments) {
        if (arguments != null) {
            this.arguments = arguments;
            return;
        }
        throw new IllegalArgumentException("Arguments mustn't be null!");
    }

    public static class Arguments {

        @SerializedName("DestHistoryList")
        private List<DestHistoryInfo> destHistoryList = new ArrayList();

        public Arguments() {
        }

        public Arguments(List<DestHistoryInfo> list) {
            setDestHistoryList(list);
        }

        public List<DestHistoryInfo> getDestHistoryList() {
            return this.destHistoryList;
        }

        public Arguments setDestHistoryList(List<DestHistoryInfo> list) {
            if (list == null) {
                throw new NullPointerException("destHistoryList cannot be null");
            }
            this.destHistoryList = list;
            return this;
        }

        public Arguments setDestHistoryList(DestHistoryInfo... destHistoryInfoArr) {
            return setDestHistoryList(Arrays.asList(destHistoryInfoArr));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).destHistoryList.equals(this.destHistoryList);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.destHistoryList);
        }

        public String toString() {
            return EProjectInterfaceType.OneCore.getGson().toJson(this);
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        EMessageType eMessageType2 = EMessageType.Error;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ResRequestGetDestinationHistoryList) {
            return super.equals(obj) && ((ResRequestGetDestinationHistoryList) obj).arguments.equals(this.arguments);
        }
        return false;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.arguments);
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
