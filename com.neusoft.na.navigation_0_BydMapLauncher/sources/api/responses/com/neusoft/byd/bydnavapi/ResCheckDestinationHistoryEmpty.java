package api.responses.com.neusoft.byd.bydnavapi;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResCheckDestinationHistoryEmpty extends BaseResponse {
    private static final long serialVersionUID = -1480908280;

    @SerializedName("args")
    private Arguments arguments;

    public ResCheckDestinationHistoryEmpty() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.byd.BydNavApi.CheckDestinationHistoryEmpty");
        this.arguments = new Arguments();
    }

    public ResCheckDestinationHistoryEmpty(Boolean bool) {
        this();
        this.arguments.setIsDestinationHistoryEmpty(bool);
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

        @SerializedName("IsDestinationHistoryEmpty")
        private Boolean isDestinationHistoryEmpty = false;

        public Arguments() {
        }

        public Arguments(Boolean bool) {
            setIsDestinationHistoryEmpty(bool);
        }

        public Boolean getIsDestinationHistoryEmpty() {
            return this.isDestinationHistoryEmpty;
        }

        public Arguments setIsDestinationHistoryEmpty(Boolean bool) {
            if (bool == null) {
                throw new NullPointerException("isDestinationHistoryEmpty cannot be null");
            }
            this.isDestinationHistoryEmpty = bool;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).isDestinationHistoryEmpty.equals(this.isDestinationHistoryEmpty);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.isDestinationHistoryEmpty);
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
        if (obj instanceof ResCheckDestinationHistoryEmpty) {
            return super.equals(obj) && ((ResCheckDestinationHistoryEmpty) obj).arguments.equals(this.arguments);
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
