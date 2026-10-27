package api.responses.com.neusoft.byd.bydnavapi;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResCheckFavoriteFull extends BaseResponse {
    private static final long serialVersionUID = -1480908280;

    @SerializedName("args")
    private Arguments arguments;

    public ResCheckFavoriteFull() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.byd.BydNavApi.CheckFavoriteFull");
        this.arguments = new Arguments();
    }

    public ResCheckFavoriteFull(Boolean bool) {
        this();
        this.arguments.setIsFavoriteFull(bool);
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

        @SerializedName("IsFavoriteFull")
        private Boolean isFavoriteFull = false;

        public Arguments() {
        }

        public Arguments(Boolean bool) {
            setIsFavoriteFull(bool);
        }

        public Boolean getIsFavoriteFull() {
            return this.isFavoriteFull;
        }

        public Arguments setIsFavoriteFull(Boolean bool) {
            if (bool == null) {
                throw new NullPointerException("isFavoriteFull cannot be null");
            }
            this.isFavoriteFull = bool;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).isFavoriteFull.equals(this.isFavoriteFull);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.isFavoriteFull);
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
        if (obj instanceof ResCheckFavoriteFull) {
            return super.equals(obj) && ((ResCheckFavoriteFull) obj).arguments.equals(this.arguments);
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
