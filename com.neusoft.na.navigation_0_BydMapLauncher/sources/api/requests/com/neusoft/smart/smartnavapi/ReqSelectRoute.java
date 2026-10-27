package api.requests.com.neusoft.smart.smartnavapi;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ReqSelectRoute extends BaseRequest {
    private static final long serialVersionUID = -1479067934;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSelectRoute() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.smart.SmartNavApi.SelectRoute");
        this.arguments = new Arguments();
    }

    public ReqSelectRoute(Integer num, Boolean bool) {
        this();
        this.arguments.setSelectIndex(num);
        this.arguments.setIsGuided(bool);
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

        @SerializedName("SelectIndex")
        private Integer selectIndex = 0;

        @SerializedName("IsGuided")
        private Boolean isGuided = false;

        public Arguments() {
        }

        public Arguments(Integer num, Boolean bool) {
            setSelectIndex(num);
            setIsGuided(bool);
        }

        public Integer getSelectIndex() {
            return this.selectIndex;
        }

        public Arguments setSelectIndex(Integer num) {
            if (num == null) {
                throw new NullPointerException("selectIndex cannot be null");
            }
            this.selectIndex = num;
            return this;
        }

        public Boolean getIsGuided() {
            return this.isGuided;
        }

        public Arguments setIsGuided(Boolean bool) {
            if (bool == null) {
                throw new NullPointerException("isGuided cannot be null");
            }
            this.isGuided = bool;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Arguments)) {
                return false;
            }
            Arguments arguments = (Arguments) obj;
            return super.equals(obj) && arguments.selectIndex.equals(this.selectIndex) && arguments.isGuided.equals(this.isGuided);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.selectIndex, this.isGuided);
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
        if (obj instanceof ReqSelectRoute) {
            return super.equals(obj) && ((ReqSelectRoute) obj).arguments.equals(this.arguments);
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
