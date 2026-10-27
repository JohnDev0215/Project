package api.requests.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.FavoriteStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqReqSwitchFavorite extends BaseRequest {
    private static final long serialVersionUID = -1480909177;

    @SerializedName("args")
    private Arguments arguments;

    public ReqReqSwitchFavorite() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.byd.BydNavApi.ReqSwitchFavorite");
        this.arguments = new Arguments();
    }

    public ReqReqSwitchFavorite(FavoriteStatus favoriteStatus) {
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

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ReqReqSwitchFavorite) {
            return super.equals(obj) && ((ReqReqSwitchFavorite) obj).arguments.equals(this.arguments);
        }
        return false;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
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
