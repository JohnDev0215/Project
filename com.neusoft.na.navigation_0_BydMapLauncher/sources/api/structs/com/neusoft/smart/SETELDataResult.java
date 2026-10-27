package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class SETELDataResult implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479991337;

    @SerializedName("ReqId")
    private Integer reqId = 0;

    @SerializedName("Code")
    private Integer code = 0;

    @SerializedName("Message")
    private String message = "";

    @SerializedName("ExtPoiInfoList")
    private List<ExtPoiInfo> extPoiInfoList = new ArrayList();

    public SETELDataResult() {
    }

    public SETELDataResult(Integer num, Integer num2, String str, List<ExtPoiInfo> list) {
        setReqId(num);
        setCode(num2);
        setMessage(str);
        setExtPoiInfoList(list);
    }

    public Integer getReqId() {
        return this.reqId;
    }

    public SETELDataResult setReqId(Integer num) {
        if (num == null) {
            throw new NullPointerException("reqId cannot be null");
        }
        this.reqId = num;
        return this;
    }

    public Integer getCode() {
        return this.code;
    }

    public SETELDataResult setCode(Integer num) {
        if (num == null) {
            throw new NullPointerException("code cannot be null");
        }
        this.code = num;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public SETELDataResult setMessage(String str) {
        if (str == null) {
            throw new NullPointerException("message cannot be null");
        }
        this.message = str;
        return this;
    }

    public List<ExtPoiInfo> getExtPoiInfoList() {
        return this.extPoiInfoList;
    }

    public SETELDataResult setExtPoiInfoList(List<ExtPoiInfo> list) {
        if (list == null) {
            throw new NullPointerException("extPoiInfoList cannot be null");
        }
        this.extPoiInfoList = list;
        return this;
    }

    public SETELDataResult setExtPoiInfoList(ExtPoiInfo... extPoiInfoArr) {
        return setExtPoiInfoList(Arrays.asList(extPoiInfoArr));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SETELDataResult sETELDataResult = (SETELDataResult) obj;
        return Objects.equals(this.reqId, sETELDataResult.reqId) && Objects.equals(this.code, sETELDataResult.code) && Objects.equals(this.message, sETELDataResult.message) && Objects.equals(this.extPoiInfoList, sETELDataResult.extPoiInfoList);
    }

    public int hashCode() {
        return Objects.hash(this.reqId, this.code, this.message, this.extPoiInfoList);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
