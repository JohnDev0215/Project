package api.requests.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.FavoriteData;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqRequestSetFavorite extends BaseRequest {
    private static final long serialVersionUID = -1481831650;

    @SerializedName("args")
    private Arguments arguments;

    public ReqRequestSetFavorite() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.RequestSetFavorite");
        this.arguments = new Arguments();
    }

    public ReqRequestSetFavorite(FavoriteData favoriteData) {
        this();
        this.arguments.setData(favoriteData);
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

        @SerializedName("Data")
        private FavoriteData data = new FavoriteData();

        public Arguments() {
        }

        public Arguments(FavoriteData favoriteData) {
            setData(favoriteData);
        }

        public FavoriteData getData() {
            return this.data;
        }

        public Arguments setData(FavoriteData favoriteData) {
            this.data = favoriteData;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).data.equals(this.data);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.data);
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
        if (obj instanceof ReqRequestSetFavorite) {
            return super.equals(obj) && ((ReqRequestSetFavorite) obj).arguments.equals(this.arguments);
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
