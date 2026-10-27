package api.responses.com.neusoft.greatwall.greatwallnavapi;

import android.car.AoapService;
import api.structs.com.neusoft.greatwall.SearchResult;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResRequestAlongRouteSearch extends BaseResponse {
    private static final long serialVersionUID = -1480916747;

    @SerializedName("args")
    private Arguments arguments;

    public ResRequestAlongRouteSearch() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.RequestAlongRouteSearch");
        this.arguments = new Arguments();
    }

    public ResRequestAlongRouteSearch(Integer num, Integer num2, List<SearchResult> list) {
        this();
        this.arguments.setResultCode(num);
        this.arguments.setReqId_Out(num2);
        this.arguments.setResult(list);
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

        @SerializedName("ResultCode")
        private Integer resultCode = 0;

        @SerializedName("ReqId_Out")
        private Integer reqId_Out = 0;

        @SerializedName(AoapService.KEY_RESULT)
        private List<SearchResult> result = new ArrayList();

        public Arguments() {
        }

        public Arguments(Integer num, Integer num2, List<SearchResult> list) {
            setResultCode(num);
            setReqId_Out(num2);
            setResult(list);
        }

        public Integer getResultCode() {
            return this.resultCode;
        }

        public Arguments setResultCode(Integer num) {
            if (num == null) {
                throw new NullPointerException("resultCode cannot be null");
            }
            this.resultCode = num;
            return this;
        }

        public Integer getReqId_Out() {
            return this.reqId_Out;
        }

        public Arguments setReqId_Out(Integer num) {
            if (num == null) {
                throw new NullPointerException("reqId_Out cannot be null");
            }
            this.reqId_Out = num;
            return this;
        }

        public List<SearchResult> getResult() {
            return this.result;
        }

        public Arguments setResult(List<SearchResult> list) {
            if (list == null) {
                throw new NullPointerException("result cannot be null");
            }
            this.result = list;
            return this;
        }

        public Arguments setResult(SearchResult... searchResultArr) {
            return setResult(Arrays.asList(searchResultArr));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Arguments)) {
                return false;
            }
            Arguments arguments = (Arguments) obj;
            return super.equals(obj) && arguments.resultCode.equals(this.resultCode) && arguments.reqId_Out.equals(this.reqId_Out) && arguments.result.equals(this.result);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.resultCode, this.reqId_Out, this.result);
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
        if (obj instanceof ResRequestAlongRouteSearch) {
            return super.equals(obj) && ((ResRequestAlongRouteSearch) obj).arguments.equals(this.arguments);
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
