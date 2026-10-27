package api.responses.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.RemainTimeDist;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResReqNaviRemainTimeDistance extends BaseResponse {
    private static final long serialVersionUID = -1478144296;

    @SerializedName("args")
    private Arguments arguments;

    public ResReqNaviRemainTimeDistance() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.common.CommonSysApiInverse.reqNaviRemainTimeDistance");
        this.arguments = new Arguments();
    }

    public ResReqNaviRemainTimeDistance(RemainTimeDist remainTimeDist) {
        this();
        this.arguments.setRemainTimeDist(remainTimeDist);
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

        @SerializedName("remainTimeDist")
        private RemainTimeDist remainTimeDist = new RemainTimeDist();

        public Arguments() {
        }

        public Arguments(RemainTimeDist remainTimeDist) {
            setRemainTimeDist(remainTimeDist);
        }

        public RemainTimeDist getRemainTimeDist() {
            return this.remainTimeDist;
        }

        public Arguments setRemainTimeDist(RemainTimeDist remainTimeDist) {
            this.remainTimeDist = remainTimeDist;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).remainTimeDist.equals(this.remainTimeDist);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.remainTimeDist);
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
        if (obj instanceof ResReqNaviRemainTimeDistance) {
            return super.equals(obj) && ((ResReqNaviRemainTimeDistance) obj).arguments.equals(this.arguments);
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
