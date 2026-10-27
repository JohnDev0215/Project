package api.structs.com.neusoft.greatwall;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class UITouchInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480909922;

    @SerializedName("CtrlName")
    private String ctrlName = "";

    @SerializedName("TouchEventType")
    private TouchEventType touchEventType = TouchEventType.TOUCH_EVENT_CLICK;

    @SerializedName("PageName")
    private String pageName = "";

    @SerializedName("TouchArgs")
    private Map<String, String> touchArgs = new LinkedHashMap();

    public UITouchInfo() {
    }

    public UITouchInfo(String str, TouchEventType touchEventType, String str2, Map<String, String> map) {
        setCtrlName(str);
        setTouchEventType(touchEventType);
        setPageName(str2);
        setTouchArgs(map);
    }

    public String getCtrlName() {
        return this.ctrlName;
    }

    public UITouchInfo setCtrlName(String str) {
        if (str == null) {
            throw new NullPointerException("ctrlName cannot be null");
        }
        this.ctrlName = str;
        return this;
    }

    public TouchEventType getTouchEventType() {
        return this.touchEventType;
    }

    public UITouchInfo setTouchEventType(TouchEventType touchEventType) {
        this.touchEventType = touchEventType;
        return this;
    }

    public String getPageName() {
        return this.pageName;
    }

    public UITouchInfo setPageName(String str) {
        if (str == null) {
            throw new NullPointerException("pageName cannot be null");
        }
        this.pageName = str;
        return this;
    }

    public Map<String, String> getTouchArgs() {
        return this.touchArgs;
    }

    public UITouchInfo setTouchArgs(Map<String, String> map) {
        if (map == null) {
            throw new NullPointerException("touchArgs cannot be null");
        }
        this.touchArgs = map;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UITouchInfo uITouchInfo = (UITouchInfo) obj;
        return Objects.equals(this.ctrlName, uITouchInfo.ctrlName) && Objects.equals(this.touchEventType, uITouchInfo.touchEventType) && Objects.equals(this.pageName, uITouchInfo.pageName) && Objects.equals(this.touchArgs, uITouchInfo.touchArgs);
    }

    public int hashCode() {
        return Objects.hash(this.ctrlName, this.touchEventType, this.pageName, this.touchArgs);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
