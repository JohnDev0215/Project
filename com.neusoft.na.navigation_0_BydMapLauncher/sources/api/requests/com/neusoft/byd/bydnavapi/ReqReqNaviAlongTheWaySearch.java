package api.requests.com.neusoft.byd.bydnavapi;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqReqNaviAlongTheWaySearch extends BaseRequest {
    private static final long serialVersionUID = -1480909177;

    @SerializedName("args")
    private Arguments arguments;

    public ReqReqNaviAlongTheWaySearch() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.byd.BydNavApi.ReqNaviAlongTheWaySearch");
        this.arguments = new Arguments();
    }

    public ReqReqNaviAlongTheWaySearch(Integer num) {
        this();
        this.arguments.setCategoryId(num);
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

        @SerializedName("CategoryId")
        private Integer categoryId = 0;

        public Arguments() {
        }

        public Arguments(Integer num) {
            setCategoryId(num);
        }

        public Integer getCategoryId() {
            return this.categoryId;
        }

        public Arguments setCategoryId(Integer num) {
            if (num == null) {
                throw new NullPointerException("categoryId cannot be null");
            }
            this.categoryId = num;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).categoryId.equals(this.categoryId);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.categoryId);
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
        if (obj instanceof ReqReqNaviAlongTheWaySearch) {
            return super.equals(obj) && ((ReqReqNaviAlongTheWaySearch) obj).arguments.equals(this.arguments);
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
