package api.responses.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.FavoriteStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetFavoriteStatus extends BaseResponse {
    private static final long serialVersionUID = -1480908280;

    @SerializedName("args")
    private Arguments arguments;

    public ResGetFavoriteStatus() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.byd.BydNavApi.GetFavoriteStatus");
        this.arguments = new Arguments();
    }

    public ResGetFavoriteStatus(FavoriteStatus favoriteStatus) {
        this();
        this.arguments.setFavoriteStatus(favoriteStatus);
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

        @SerializedName("FavoriteStatus")
        private FavoriteStatus favoriteStatus = FavoriteStatus.CLOSE;

        public Arguments() {
        }

        public Arguments(FavoriteStatus favoriteStatus) {
            setFavoriteStatus(favoriteStatus);
        }

        public FavoriteStatus getFavoriteStatus() {
            return this.favoriteStatus;
        }

        public Arguments setFavoriteStatus(FavoriteStatus favoriteStatus) {
            this.favoriteStatus = favoriteStatus;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).favoriteStatus.equals(this.favoriteStatus);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.favoriteStatus);
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
        if (obj instanceof ResGetFavoriteStatus) {
            return super.equals(obj) && ((ResGetFavoriteStatus) obj).arguments.equals(this.arguments);
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
