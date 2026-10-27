package api.structs.com.neusoft.greatwall;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum ViewPage {
    NAV_SETTING_PAGE(0),
    NAV_HISTORY_LIST_PAGE(1),
    NAV_FAVORITE_LIST_PAGE(2),
    NAV_SETTING_HOME_PAGE(3),
    NAV_SETTING_WORK_PAGE(4);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    ViewPage(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static ViewPage getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return NAV_SETTING_PAGE;
        }
        if (iIntValue == 1) {
            return NAV_HISTORY_LIST_PAGE;
        }
        if (iIntValue == 2) {
            return NAV_FAVORITE_LIST_PAGE;
        }
        if (iIntValue == 3) {
            return NAV_SETTING_HOME_PAGE;
        }
        if (iIntValue == 4) {
            return NAV_SETTING_WORK_PAGE;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + ViewPage.class.getName());
    }

    public static ViewPage valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "NAV_SETTING_PAGE":
                return NAV_SETTING_PAGE;
            case "NAV_SETTING_HOME_PAGE":
                return NAV_SETTING_HOME_PAGE;
            case "NAV_HISTORY_LIST_PAGE":
                return NAV_HISTORY_LIST_PAGE;
            case "NAV_FAVORITE_LIST_PAGE":
                return NAV_FAVORITE_LIST_PAGE;
            case "NAV_SETTING_WORK_PAGE":
                return NAV_SETTING_WORK_PAGE;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + ViewPage.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
