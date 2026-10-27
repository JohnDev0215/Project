package api.responses.com.neusoft.byd.bydnavapi;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResCheckFavoriteEmpty extends BaseResponse {
    private static final long serialVersionUID = -1480908280;

    @SerializedName("args")
    private Arguments arguments;

    public ResCheckFavoriteEmpty() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.byd.BydNavApi.CheckFavoriteEmpty");
        this.arguments = new Arguments();
    }

    public ResCheckFavoriteEmpty(Boolean bool) {
        this();
        this.arguments.setIsFavoriteEmpty(bool);
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

        @SerializedName("IsFavoriteEmpty")
        private Boolean isFavoriteEmpty = false;

        public Arguments() {
        }

        public Arguments(Boolean bool) {
            setIsFavoriteEmpty(bool);
        }

        public Boolean getIsFavoriteEmpty() {
            return this.isFavoriteEmpty;
        }

        public Arguments setIsFavoriteEmpty(Boolean bool) {
            if (bool == null) {
                throw new NullPointerException("isFavoriteEmpty cannot be null");
            }
            this.isFavoriteEmpty = bool;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).isFavoriteEmpty.equals(this.isFavoriteEmpty);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.isFavoriteEmpty);
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
        if (obj instanceof ResCheckFavoriteEmpty) {
            return super.equals(obj) && ((ResCheckFavoriteEmpty) obj).arguments.equals(this.arguments);
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
