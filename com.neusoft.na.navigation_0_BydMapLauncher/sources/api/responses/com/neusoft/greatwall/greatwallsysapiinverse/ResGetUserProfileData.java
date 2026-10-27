package api.responses.com.neusoft.greatwall.greatwallsysapiinverse;

import api.structs.com.neusoft.greatwall.UserProfileData;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetUserProfileData extends BaseResponse {
    private static final long serialVersionUID = -1479992386;

    @SerializedName("args")
    private Arguments arguments;

    public ResGetUserProfileData() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.greatwall.GreatWallSysApiInverse.GetUserProfileData");
        this.arguments = new Arguments();
    }

    public ResGetUserProfileData(UserProfileData userProfileData) {
        this();
        this.arguments.setUserProfileData(userProfileData);
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

        @SerializedName("UserProfileData")
        private UserProfileData userProfileData = new UserProfileData();

        public Arguments() {
        }

        public Arguments(UserProfileData userProfileData) {
            setUserProfileData(userProfileData);
        }

        public UserProfileData getUserProfileData() {
            return this.userProfileData;
        }

        public Arguments setUserProfileData(UserProfileData userProfileData) {
            this.userProfileData = userProfileData;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).userProfileData.equals(this.userProfileData);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.userProfileData);
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
        if (obj instanceof ResGetUserProfileData) {
            return super.equals(obj) && ((ResGetUserProfileData) obj).arguments.equals(this.arguments);
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
