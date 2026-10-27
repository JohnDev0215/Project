package api.structs.com.neusoft.byd;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class DelPrivacyPolicyInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1481838255;

    @SerializedName("TokenId")
    private Integer tokenId = 0;

    @SerializedName("IndexList")
    private List<Integer> indexList = new ArrayList();

    public DelPrivacyPolicyInfo() {
    }

    public DelPrivacyPolicyInfo(Integer num, List<Integer> list) {
        setTokenId(num);
        setIndexList(list);
    }

    public Integer getTokenId() {
        return this.tokenId;
    }

    public DelPrivacyPolicyInfo setTokenId(Integer num) {
        if (num == null) {
            throw new NullPointerException("tokenId cannot be null");
        }
        this.tokenId = num;
        return this;
    }

    public List<Integer> getIndexList() {
        return this.indexList;
    }

    public DelPrivacyPolicyInfo setIndexList(List<Integer> list) {
        if (list == null) {
            throw new NullPointerException("indexList cannot be null");
        }
        this.indexList = list;
        return this;
    }

    public DelPrivacyPolicyInfo setIndexList(Integer... numArr) {
        return setIndexList(Arrays.asList(numArr));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DelPrivacyPolicyInfo delPrivacyPolicyInfo = (DelPrivacyPolicyInfo) obj;
        return Objects.equals(this.tokenId, delPrivacyPolicyInfo.tokenId) && Objects.equals(this.indexList, delPrivacyPolicyInfo.indexList);
    }

    public int hashCode() {
        return Objects.hash(this.tokenId, this.indexList);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
