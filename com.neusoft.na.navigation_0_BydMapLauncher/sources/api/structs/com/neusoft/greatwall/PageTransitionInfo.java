package api.structs.com.neusoft.greatwall;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class PageTransitionInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480909922;

    @SerializedName("PageName")
    private String pageName = "";

    @SerializedName("PageAction")
    private PageAction pageAction = PageAction.PAGE_ACTION_ENTER;

    @SerializedName("PageArgs")
    private Map<String, String> pageArgs = new LinkedHashMap();

    public PageTransitionInfo() {
    }

    public PageTransitionInfo(String str, PageAction pageAction, Map<String, String> map) {
        setPageName(str);
        setPageAction(pageAction);
        setPageArgs(map);
    }

    public String getPageName() {
        return this.pageName;
    }

    public PageTransitionInfo setPageName(String str) {
        if (str == null) {
            throw new NullPointerException("pageName cannot be null");
        }
        this.pageName = str;
        return this;
    }

    public PageAction getPageAction() {
        return this.pageAction;
    }

    public PageTransitionInfo setPageAction(PageAction pageAction) {
        this.pageAction = pageAction;
        return this;
    }

    public Map<String, String> getPageArgs() {
        return this.pageArgs;
    }

    public PageTransitionInfo setPageArgs(Map<String, String> map) {
        if (map == null) {
            throw new NullPointerException("pageArgs cannot be null");
        }
        this.pageArgs = map;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PageTransitionInfo pageTransitionInfo = (PageTransitionInfo) obj;
        return Objects.equals(this.pageName, pageTransitionInfo.pageName) && Objects.equals(this.pageAction, pageTransitionInfo.pageAction) && Objects.equals(this.pageArgs, pageTransitionInfo.pageArgs);
    }

    public int hashCode() {
        return Objects.hash(this.pageName, this.pageAction, this.pageArgs);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
