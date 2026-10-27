package api.requests.com.neusoft.greatwall.greatwallsysapiinverse;

import api.structs.com.neusoft.greatwall.UserProfileData;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqSetUserProfileData extends BaseRequest {
    private static final long serialVersionUID = -1479992422;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSetUserProfileData() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.greatwall.GreatWallSysApiInverse.SetUserProfileData");
        this.arguments = new Arguments();
    }

    public ReqSetUserProfileData(UserProfileData userProfileData) {
        this();
        this.arguments.setProfileData(userProfileData);
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

        @SerializedName("ProfileData")
        private UserProfileData profileData = new UserProfileData();

        public Arguments() {
        }

        public Arguments(UserProfileData userProfileData) {
            setProfileData(userProfileData);
        }

        public UserProfileData getProfileData() {
            return this.profileData;
        }

        public Arguments setProfileData(UserProfileData userProfileData) {
            this.profileData = userProfileData;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).profileData.equals(this.profileData);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.profileData);
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
        if (obj instanceof ReqSetUserProfileData) {
            return super.equals(obj) && ((ReqSetUserProfileData) obj).arguments.equals(this.arguments);
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
