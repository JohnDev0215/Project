package com.byd.card.floatcard;

import android.content.ComponentName;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class WidgetIdBean {
    private int appWidgetId;
    private String componentNameStr;

    public void setAppWidgetId(int appWidgetId) {
    }

    public WidgetIdBean(String componentNameStr, int appWidgetId) {
        this.componentNameStr = componentNameStr;
        this.appWidgetId = appWidgetId;
    }

    public WidgetIdBean(ComponentName componentNameStr, int appWidgetId) {
        this.componentNameStr = componentNameStr.flattenToString();
        this.appWidgetId = appWidgetId;
    }

    public String getComponentNameStr() {
        return this.componentNameStr;
    }

    public void setComponentNameStr(String componentNameStr) {
        this.componentNameStr = componentNameStr;
    }

    public int getAppWidgetId() {
        return this.appWidgetId;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o != null && getClass() == o.getClass()) {
            WidgetIdBean widgetIdBean = (WidgetIdBean) o;
            if (this.appWidgetId == widgetIdBean.appWidgetId && Objects.equals(this.componentNameStr, widgetIdBean.componentNameStr)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.componentNameStr, Integer.valueOf(this.appWidgetId));
    }

    public static WidgetIdBean unFlattenFromString(String str) {
        int i;
        int iIndexOf = str.indexOf(64);
        if (iIndexOf < 0 || (i = iIndexOf + 1) >= str.length()) {
            return null;
        }
        return new WidgetIdBean(str.substring(0, iIndexOf), Integer.parseInt(str.substring(i)));
    }

    public String flattenToString() {
        return this.componentNameStr + "@" + this.appWidgetId;
    }
}
