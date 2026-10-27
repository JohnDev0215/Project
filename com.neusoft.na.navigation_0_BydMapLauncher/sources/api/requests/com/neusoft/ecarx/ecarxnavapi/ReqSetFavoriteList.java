package api.requests.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.FavoriteItem;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqSetFavoriteList extends BaseRequest {
    private static final long serialVersionUID = -1481836548;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSetFavoriteList() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.SetFavoriteList");
        this.arguments = new Arguments();
    }

    public ReqSetFavoriteList(Integer num, Integer num2, List<FavoriteItem> list) {
        this();
        this.arguments.setReqId(num);
        this.arguments.setResult(num2);
        this.arguments.setFavoriteItemList(list);
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

        @SerializedName("ReqId")
        private Integer reqId = 0;

        @SerializedName("Result")
        private Integer result = 0;

        @SerializedName("FavoriteItemList")
        private List<FavoriteItem> favoriteItemList = new ArrayList();

        public Arguments() {
        }

        public Arguments(Integer num, Integer num2, List<FavoriteItem> list) {
            setReqId(num);
            setResult(num2);
            setFavoriteItemList(list);
        }

        public Integer getReqId() {
            return this.reqId;
        }

        public Arguments setReqId(Integer num) {
            if (num == null) {
                throw new NullPointerException("reqId cannot be null");
            }
            this.reqId = num;
            return this;
        }

        public Integer getResult() {
            return this.result;
        }

        public Arguments setResult(Integer num) {
            if (num == null) {
                throw new NullPointerException("result cannot be null");
            }
            this.result = num;
            return this;
        }

        public List<FavoriteItem> getFavoriteItemList() {
            return this.favoriteItemList;
        }

        public Arguments setFavoriteItemList(List<FavoriteItem> list) {
            if (list == null) {
                throw new NullPointerException("favoriteItemList cannot be null");
            }
            this.favoriteItemList = list;
            return this;
        }

        public Arguments setFavoriteItemList(FavoriteItem... favoriteItemArr) {
            return setFavoriteItemList(Arrays.asList(favoriteItemArr));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Arguments)) {
                return false;
            }
            Arguments arguments = (Arguments) obj;
            return super.equals(obj) && arguments.reqId.equals(this.reqId) && arguments.result.equals(this.result) && arguments.favoriteItemList.equals(this.favoriteItemList);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.reqId, this.result, this.favoriteItemList);
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
        if (obj instanceof ReqSetFavoriteList) {
            return super.equals(obj) && ((ReqSetFavoriteList) obj).arguments.equals(this.arguments);
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
