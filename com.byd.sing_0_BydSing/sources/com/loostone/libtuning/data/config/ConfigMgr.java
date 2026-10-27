package com.loostone.libtuning.data.config;

import android.os.Build;
import android.text.TextUtils;
import com.android.volley.BuildConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.a;
import kotlin.d;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public abstract class ConfigMgr {
    private final ArrayList<String> pathList = new ArrayList<>();
    private final a propertiesMap$delegate = d.b(OooO00o.f3891OooO00o);

    public static final class OooO00o extends Lambda implements kotlin.jvm.b.a<Map<String, String>> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooO00o f3891OooO00o = new OooO00o();

        public OooO00o() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final Map<String, String> invoke() {
            return new LinkedHashMap();
        }
    }

    public static /* synthetic */ String getConfigPath$default(ConfigMgr configMgr, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getConfigPath");
        }
        if ((i & 1) != 0) {
            str = BuildConfig.FLAVOR;
        }
        return configMgr.getConfigPath(str);
    }

    public static /* synthetic */ void listAssetsPath$default(ConfigMgr configMgr, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: listAssetsPath");
        }
        if ((i & 1) != 0) {
            str = BuildConfig.FLAVOR;
        }
        configMgr.listAssetsPath(str);
    }

    public final String getConfigPath(String str) {
        h.d(str, "extraPath");
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            File[] fileArrListFiles = file.listFiles();
            if (!file.exists() || fileArrListFiles == null) {
                return BuildConfig.FLAVOR;
            }
            if (fileArrListFiles.length == 0) {
                return BuildConfig.FLAVOR;
            }
        }
        listAssetsPath$default(this, null, 1, null);
        return initConfig(str);
    }

    public String getModelName() {
        String str = Build.MODEL;
        h.c(str, "MODEL");
        return str;
    }

    public final ArrayList<String> getPathList$libTuning_BIYADI_OVERSEAS_8155Release() {
        return this.pathList;
    }

    public final Map<String, String> getPropertiesMap$libTuning_BIYADI_OVERSEAS_8155Release() {
        return (Map) this.propertiesMap$delegate.getValue();
    }

    public abstract String initConfig(String str);

    public abstract void listAssetsPath(String str);

    public void setDataForConfigParse(Map<String, String> map) {
        h.d(map, "dataMap");
        if ((map.isEmpty() ^ true ? this : null) == null) {
            return;
        }
        getPropertiesMap$libTuning_BIYADI_OVERSEAS_8155Release().putAll(map);
    }

    public void setModel(int i) {
    }
}
