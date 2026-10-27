package api.requests.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.FavoriteType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ReqGetFavoritePois extends BaseRequest {
    private static final long serialVersionUID = -1479067934;

    @SerializedName("args")
    private Arguments arguments;

    public ReqGetFavoritePois() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.smart.SmartNavApi.GetFavoritePois");
        this.arguments = new Arguments();
    }

    public ReqGetFavoritePois(FavoriteType favoriteType) {
        this();
        this.arguments.setFavoriteType(favoriteType);
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

        @SerializedName("FavoriteType")
        private FavoriteType favoriteType = FavoriteType.GENERAL_FAVORITES;

        public Arguments() {
        }

        public Arguments(FavoriteType favoriteType) {
            setFavoriteType(favoriteType);
        }

        public FavoriteType getFavoriteType() {
            return this.favoriteType;
        }

        public Arguments setFavoriteType(FavoriteType favoriteType) {
            this.favoriteType = favoriteType;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).favoriteType.equals(this.favoriteType);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.favoriteType);
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
        if (obj instanceof ReqGetFavoritePois) {
            return super.equals(obj) && ((ReqGetFavoritePois) obj).arguments.equals(this.arguments);
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
