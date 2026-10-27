package com.neusoft.na.ocservice;

import android.util.Log;
import com.neusoft.na.common.IEhpOptionalComponent;
import com.neusoft.na.common.ServiceBase;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class EHPService extends ServiceBase {
    private static final String GREAT_WALL_V61_EHP_CONTROLLER = "com.neusoft.na.greatwallv61.ehp.EhpController";
    private static final String SMART_EHP_CONTROLLER = "com.neusoft.na.smart.ehp.controller.EhpController";
    public static final String TAG = "EHPService";
    private static final String WEY_FOR_PRODUCT_3_EHP_CONTROLLER = " com.neusoft.weyforproductv3.ehp.EhpController";
    private static final String ZEEKRBX1E_EHP_CONTROLLER = "com.neusoft.na.zeekrbx1e.ehp.controller.EhpController";
    private IEhpOptionalComponent ehpOptionalComponent;

    public EHPService() {
        super("ehp");
    }

    public EHPService(String str) {
        super(str);
    }

    @Override // com.neusoft.na.common.ServiceBase, android.app.Service
    public void onCreate() {
        Log.i(TAG, "onCreate");
        super.onCreate();
    }

    @Override // com.neusoft.na.common.ServiceBase
    protected void onStart() {
        Log.i(TAG, "onStart");
    }

    @Override // android.app.Service
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        IEhpOptionalComponent iEhpOptionalComponent = this.ehpOptionalComponent;
        if (iEhpOptionalComponent != null) {
            iEhpOptionalComponent.stop();
        }
        super.onDestroy();
    }

    private String getStartParams4V61() {
        File file = new File(getAssetsInstallDir() + File.separator + "car" + File.separator + "tmp");
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(getAssetsInstallDir() + File.separator + "car" + File.separator + "cache");
        if (!file2.exists()) {
            file2.mkdirs();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("--main.path_share=" + getAssetsInstallDir() + "/ehp/share/").append("\n");
        sb.append("--main.path_temp=" + getPrivateTempDir() + "/").append("\n");
        sb.append("--main.path_cache=" + getPrivateCacheDir() + "/").append("\n--main.resource_set=byd_dx\n");
        sb.append("--main.config=" + getAssetsInstallDir() + "/ehp/etc/nais/na_serv_ehp.ini").append("\n");
        File naviexternconfigDir = getNaviexternconfigDir();
        if (naviexternconfigDir.exists()) {
            sb.append("--main.config=" + naviexternconfigDir.getAbsolutePath() + File.separator + "na_serv_ehp.ini").append("\n");
        }
        sb.append("--EhpService.deploy=" + getAssetsInstallDir() + "/ehp/etc/nais/na_serv_ehp_deploy.json").append("\n");
        sb.append("--nds.ndslib=" + getSharedLibraryPath("libNdsLib.so")).append("\n--nds.database=/mapdata/neusoft/db/ROOT.NDS\n--nds.use_hybrid_db_service=true\n--nds.max_open_sqlite_files=120\n--nds.cache_mb=90\n--nds.remote_cache_size=500\n--nds.database_hybrid=/mapdata/neusoft/onlinedb/ROOT.NDS\n--nds.remote_cache=/mapdata/neusoft/onlinedb/Cache.sql");
        Log.i(TAG, "ehp start >> " + sb.toString());
        return sb.toString();
    }

    private String getStartParams4Smart() {
        File file = new File(getAssetsInstallDir() + File.separator + "car" + File.separator + "tmp");
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(getAssetsInstallDir() + File.separator + "car" + File.separator + "cache");
        if (!file2.exists()) {
            file2.mkdirs();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("--main.path_share=" + getAssetsInstallDir() + "/ehp/share/").append("\n");
        sb.append("--main.path_temp=" + getPrivateTempDir() + "/").append("\n");
        sb.append("--main.path_cache=" + getPrivateCacheDir() + "/").append("\n--main.resource_set=byd_dx\n");
        sb.append("--main.config=" + getAssetsInstallDir() + "/ehp/etc/nais/na_serv_ehp.ini").append("\n");
        File naviexternconfigDir = getNaviexternconfigDir();
        if (naviexternconfigDir.exists()) {
            sb.append("--main.config=" + naviexternconfigDir.getAbsolutePath() + File.separator + "na_serv_ehp.ini").append("\n");
        }
        sb.append("--EhpService.deploy=" + getAssetsInstallDir() + "/ehp/etc/nais/na_serv_ehp_deploy.json").append("\n");
        sb.append("--nds.ndslib=" + getSharedLibraryPath("libNdsLib.so")).append("\n--nds.database=/map/neusoft/db/ROOT.NDS\n--nds.use_hybrid_db_service=true\n--nds.max_open_sqlite_files=120\n--nds.cache_mb=60\n--nds.remote_cache_size=500\n--nds.database_hybrid=/map/neusoft/onlinedb/ROOT.NDS\n--nds.remote_cache=/map/neusoft/onlinedb/Cache.sql");
        Log.i(TAG, "ehp start >> " + sb.toString());
        return sb.toString();
    }

    private String getStartParams4Zeekr() {
        File file = new File(getAssetsInstallDir() + File.separator + "car" + File.separator + "tmp");
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(getAssetsInstallDir() + File.separator + "car" + File.separator + "cache");
        if (!file2.exists()) {
            file2.mkdirs();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("--main.path_share=" + getAssetsInstallDir() + "/ehp/share/").append("\n");
        sb.append("--main.path_temp=" + getPrivateTempDir() + "/").append("\n");
        sb.append("--main.path_cache=" + getPrivateCacheDir() + "/").append("\n--main.resource_set=byd_dx\n");
        sb.append("--main.config=" + getAssetsInstallDir() + "/ehp/etc/nais/na_serv_ehp.ini").append("\n");
        File naviexternconfigDir = getNaviexternconfigDir();
        if (naviexternconfigDir.exists()) {
            sb.append("--main.config=" + naviexternconfigDir.getAbsolutePath() + File.separator + "na_serv_ehp.ini").append("\n");
        }
        sb.append("--EhpService.deploy=" + getAssetsInstallDir() + "/ehp/etc/nais/na_serv_ehp_deploy.json").append("\n");
        sb.append("--nds.ndslib=" + getSharedLibraryPath("libNdsLib.so")).append("\n--nds.database=/map/neumap/ROOT.NDS\n--nds.use_hybrid_db_service=true\n--nds.max_open_sqlite_files=120\n--nds.cache_mb=60\n--nds.remote_cache_size=500\n--nds.database_hybrid=/map/onlinedb/ROOT.NDS\n--nds.remote_cache=/map/onlinedb/Cache.sql");
        Log.i(TAG, "ehp start >> " + sb.toString());
        return sb.toString();
    }
}
