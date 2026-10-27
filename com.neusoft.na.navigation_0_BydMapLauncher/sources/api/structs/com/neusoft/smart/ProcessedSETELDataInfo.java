package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ProcessedSETELDataInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479991206;

    @SerializedName("ReqId")
    private Integer reqId = 0;

    @SerializedName("Code")
    private Integer code = 0;

    @SerializedName("Message")
    private String message = "";

    @SerializedName("SETELDetailInfoList")
    private List<SETELDetailInfo> sETELDetailInfoList = new ArrayList();

    public ProcessedSETELDataInfo() {
    }

    public ProcessedSETELDataInfo(Integer num, Integer num2, String str, List<SETELDetailInfo> list) {
        setReqId(num);
        setCode(num2);
        setMessage(str);
        setSETELDetailInfoList(list);
    }

    public Integer getReqId() {
        return this.reqId;
    }

    public ProcessedSETELDataInfo setReqId(Integer num) {
        if (num == null) {
            throw new NullPointerException("reqId cannot be null");
        }
        this.reqId = num;
        return this;
    }

    public Integer getCode() {
        return this.code;
    }

    public ProcessedSETELDataInfo setCode(Integer num) {
        if (num == null) {
            throw new NullPointerException("code cannot be null");
        }
        this.code = num;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public ProcessedSETELDataInfo setMessage(String str) {
        if (str == null) {
            throw new NullPointerException("message cannot be null");
        }
        this.message = str;
        return this;
    }

    public List<SETELDetailInfo> getSETELDetailInfoList() {
        return this.sETELDetailInfoList;
    }

    public ProcessedSETELDataInfo setSETELDetailInfoList(List<SETELDetailInfo> list) {
        if (list == null) {
            throw new NullPointerException("sETELDetailInfoList cannot be null");
        }
        this.sETELDetailInfoList = list;
        return this;
    }

    public ProcessedSETELDataInfo setSETELDetailInfoList(SETELDetailInfo... sETELDetailInfoArr) {
        return setSETELDetailInfoList(Arrays.asList(sETELDetailInfoArr));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ProcessedSETELDataInfo processedSETELDataInfo = (ProcessedSETELDataInfo) obj;
        return Objects.equals(this.reqId, processedSETELDataInfo.reqId) && Objects.equals(this.code, processedSETELDataInfo.code) && Objects.equals(this.message, processedSETELDataInfo.message) && Objects.equals(this.sETELDetailInfoList, processedSETELDataInfo.sETELDetailInfoList);
    }

    public int hashCode() {
        return Objects.hash(this.reqId, this.code, this.message, this.sETELDetailInfoList);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
