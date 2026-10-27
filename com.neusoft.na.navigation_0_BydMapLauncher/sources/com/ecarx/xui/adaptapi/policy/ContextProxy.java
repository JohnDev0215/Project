package com.ecarx.xui.adaptapi.policy;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class ContextProxy {
    public static final String CV = "CV";
    private static final String CVHBHH = "CVHBHH";
    private static final String CVHBQJ = "CVHBQJ";
    private static final String CVHBSS = "CVHBSS";
    private static final String CVHH = "CVHH";
    private static final String CVQJ = "CVQJ";
    public static final String FE = "FE";
    private static final String FE5HAHH = "FE5HAHH";
    private static final String FE5HAQJ = "FE5HAQJ";
    private static final String FE5HASS = "FE5HASS";
    private static final String FE6HH = "FE6HH";
    private static final String FE6QJ = "FE6QJ";
    private static final String FE6SS = "FE6SS";
    public static final String KC2 = "KC-2";
    private static final String KC2HBHH = "KC2HBHH";
    private static final String KC2HBQJ = "KC2HBQJ";
    private static final String KC2HBSS = "KC2HBSS";
    private static final String KC2HBZG = "KC2HBZG";
    private static final String KC2HH = "KC2HH";
    private static final String KC2QJ = "KC2QJ";
    private static final String KC2SS = "KC2SS";
    private static final String KC2ZG = "KC2ZG";
    public static final String NL = "NL";
    private static final String NL3BHH = "NL3BHH";
    private static final String NL3BQJ = "NL3BQJ";
    private static final String NL5HH = "NL5HH";
    private static final String NL5QJ = "NL5QJ";
    public static final String SX = "SX";
    private static final String SX11FLAG = "SX11FLAG";
    private static final String SX11QJ = "SX11QJ";
    private static final String TAG = "P_ContextProxy";
    private static ContextProxy mContextProxy;

    public static synchronized ContextProxy getInstance() {
        if (mContextProxy == null) {
            mContextProxy = new ContextProxy();
        }
        return mContextProxy;
    }

    public String getProject() {
        Log.d(TAG, "getProject mProject = 6969");
        return "";
    }
}
