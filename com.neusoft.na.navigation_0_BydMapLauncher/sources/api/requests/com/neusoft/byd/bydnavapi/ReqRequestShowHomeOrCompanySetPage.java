package api.requests.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.FavoriteType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqRequestShowHomeOrCompanySetPage extends BaseRequest {
    private static final long serialVersionUID = -1480909119;

    @SerializedName("args")
    private Arguments arguments;

    public ReqRequestShowHomeOrCompanySetPage() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.byd.BydNavApi.RequestShowHomeOrCompanySetPage");
        this.arguments = new Arguments();
    }

    public ReqRequestShowHomeOrCompanySetPage(FavoriteType favoriteType) {
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
        private FavoriteType favoriteType = FavoriteType.HOME;

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
        if (obj instanceof ReqRequestShowHomeOrCompanySetPage) {
            return super.equals(obj) && ((ReqRequestShowHomeOrCompanySetPage) obj).arguments.equals(this.arguments);
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
