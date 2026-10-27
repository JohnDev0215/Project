package ts.car.diag.manager;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.SystemProperties;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import ts.car.diag.bean.AreaBean;
import ts.car.diag.bean.CountryCodeBean;
import ts.car.diag.bean.GwMapBean;
import ts.car.diag.bean.MapCountryCodeConfig;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f1616a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private List<MapCountryCodeConfig> f1617b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<MapCountryCodeConfig> f1618c;
    private List<GwMapBean> d;

    /* JADX INFO: compiled from: caradapter-bug */
    class a extends TypeToken<ArrayList<MapCountryCodeConfig>> {
        a(m mVar) {
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b extends TypeToken<ArrayList<MapCountryCodeConfig>> {
        b(m mVar) {
        }
    }

    public m(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.f1616a = applicationContext;
        AssetManager assets = applicationContext.getAssets();
        String strA = ts.car.diag.d.b.a(assets, "all_code.json");
        String strA2 = ts.car.diag.d.b.a(assets, "support_code.json");
        Gson gson = new Gson();
        this.f1617b = (List) gson.fromJson(strA, new a(this).getType());
        q.i("DIAG-MAP", "init: countryCodeConfigDatas " + this.f1617b);
        this.f1618c = (List) gson.fromJson(strA2, new b(this).getType());
        q.i("DIAG-MAP", "init: mSupportCodeDatas " + this.f1618c);
        if (this.f1617b != null) {
            this.d = new ArrayList();
            for (int i = 0; i < this.f1617b.size(); i++) {
                this.d.add(new GwMapBean());
            }
        }
    }

    private String a(List<AreaBean> list, String str) {
        if (TextUtils.isEmpty(str)) {
            q.b("DIAG-MAP", "systemCountryCode is null");
            return null;
        }
        for (int i = 0; i < list.size(); i++) {
            AreaBean areaBean = list.get(i);
            List<CountryCodeBean> countryCodes = areaBean.getCountryCodes();
            if (countryCodes == null) {
                q.b("DIAG-MAP", "pairData countryCodes is null");
                return null;
            }
            String areaCode = areaBean.getAreaCode();
            for (int i2 = 0; i2 < countryCodes.size(); i2++) {
                CountryCodeBean countryCodeBean = countryCodes.get(i2);
                if (countryCodeBean != null && str.equals(countryCodeBean.getCode())) {
                    q.h("DIAG-MAP", "has find AreaCode: " + areaCode + " By SystemCountryCode：" + str);
                    return areaCode;
                }
            }
        }
        return null;
    }

    private void b(GwMapBean gwMapBean, int i) {
        String strI = i();
        gwMapBean.setCurrentCountryCode(strI);
        gwMapBean.setAreaCode(a(gwMapBean.getCurrentMapCountryCodeConfig(), strI));
        gwMapBean.setMapAreaCode(o(gwMapBean.getAreaPath()));
        gwMapBean.setMapDataVersion(j(i));
        gwMapBean.setMapSize(p(gwMapBean.getCurrentPackageSizeCommand()));
    }

    private List<AreaBean> h(String str) {
        if (this.f1618c == null) {
            q.b("DIAG-MAP", "getSupportConfig mSupportCodeDatas is null");
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            q.b("DIAG-MAP", "getSupportConfig packageName is null");
            return null;
        }
        for (int i = 0; i < this.f1618c.size(); i++) {
            MapCountryCodeConfig mapCountryCodeConfig = this.f1618c.get(i);
            if (str.equals(mapCountryCodeConfig.getPackageName())) {
                List<AreaBean> allArea = mapCountryCodeConfig.getAllArea();
                q.h("DIAG-MAP", "getSupportConfig items " + allArea);
                return allArea;
            }
        }
        return null;
    }

    private String j(int i) {
        String str = "";
        if (i == 0) {
            str = SystemProperties.get("persist.sys.byd.neusoft.dbVer", "");
        } else if (1 == i) {
            str = SystemProperties.get("isa_active_data_version", "");
        }
        q.h("DIAG-MAP", "getSystemMapDataVersion " + str);
        return str;
    }

    private boolean m(String str, String str2, String str3) {
        q.h("DIAG-MAP", "isNormalConfig packageName " + str + ",mapAreaCode:" + str2 + ",countryCode:" + str3);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            q.b("DIAG-MAP", "isNormalConfig data error");
            return false;
        }
        List<AreaBean> listH = h(str);
        if (listH == null) {
            q.b("DIAG-MAP", "isErrorConfig supportAreas is null");
            return false;
        }
        for (int i = 0; i < listH.size(); i++) {
            AreaBean areaBean = listH.get(i);
            String areaCode = areaBean.getAreaCode();
            if (!TextUtils.isEmpty(str2) && str2.equals(areaCode)) {
                List<CountryCodeBean> countryCodes = areaBean.getCountryCodes();
                for (int i2 = 0; i2 < countryCodes.size(); i2++) {
                    if (str3.equals(countryCodes.get(i2).getCode())) {
                        q.h("DIAG-MAP", "isNormalConfig has find countryCode " + str3);
                        return true;
                    }
                }
            }
        }
        q.b("DIAG-MAP", "isNormalConfig has not find countryCode by  mapAreaCode:" + str2 + ",countryCode:" + str3 + ",packageName:" + str);
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0071 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String o(java.lang.String r5) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            r1.<init>(r5)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            boolean r2 = r1.exists()     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            if (r2 != 0) goto L23
            java.lang.String r1 = "DIAG-MAP"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            r2.<init>()     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            java.lang.String r3 = "readFileContent file is not exists "
            r2.append(r3)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            r2.append(r5)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            java.lang.String r5 = r2.toString()     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            ts.car.diag.manager.q.b(r1, r5)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            return r0
        L23:
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            r5.<init>(r1)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            r1.<init>(r5)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            r5.<init>(r1)     // Catch: java.lang.Throwable -> L5b java.lang.Exception -> L5d
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L56
            r1.<init>()     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L56
        L37:
            java.lang.String r2 = r5.readLine()     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L56
            if (r2 == 0) goto L41
            r1.append(r2)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L56
            goto L37
        L41:
            r5.close()     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L56
            java.lang.String r0 = r1.toString()     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L56
            r5.close()     // Catch: java.lang.Exception -> L4c
            goto L50
        L4c:
            r5 = move-exception
            r5.printStackTrace()
        L50:
            return r0
        L51:
            r0 = move-exception
            r4 = r0
            r0 = r5
            r5 = r4
            goto L6f
        L56:
            r1 = move-exception
            r4 = r1
            r1 = r5
            r5 = r4
            goto L5f
        L5b:
            r5 = move-exception
            goto L6f
        L5d:
            r5 = move-exception
            r1 = r0
        L5f:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L6d
            if (r1 == 0) goto L6c
            r1.close()     // Catch: java.lang.Exception -> L68
            goto L6c
        L68:
            r5 = move-exception
            r5.printStackTrace()
        L6c:
            return r0
        L6d:
            r5 = move-exception
            r0 = r1
        L6f:
            if (r0 == 0) goto L79
            r0.close()     // Catch: java.lang.Exception -> L75
            goto L79
        L75:
            r0 = move-exception
            r0.printStackTrace()
        L79:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: ts.car.diag.manager.m.o(java.lang.String):java.lang.String");
    }

    private int p(String str) {
        int i = 0;
        if (str == null || str.length() == 0) {
            q.b("DIAG-MAP", "runtimeToGetMapSize.mCurrentPackageSizeCommand" + str);
            return 0;
        }
        try {
            Process processExec = Runtime.getRuntime().exec(new String[]{"sh", "-c", str});
            int iWaitFor = processExec.waitFor();
            q.h("DIAG-MAP", "runtimeToGetMapSize process status : " + iWaitFor);
            if (iWaitFor == 0) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
                    try {
                        String line = bufferedReader.readLine();
                        q.h("DIAG-MAP", "runtimeToGetMapSize process line: " + line);
                        if (line.contains("total")) {
                            String[] strArrSplit = line.split("\t");
                            q.h("DIAG-MAP", "runtimeToGetMapSize process total: " + strArrSplit[0]);
                            i = Integer.parseInt(strArrSplit[0]);
                        }
                        bufferedReader.close();
                    } finally {
                    }
                } catch (Exception e) {
                    q.b("DIAG-MAP", e.toString());
                }
            }
        } catch (Exception e2) {
            q.b("DIAG-MAP", e2.toString());
        }
        return i;
    }

    public String c(int i) {
        if (i > this.d.size() - 1) {
            q.b("DIAG-MAP", "getMapAreaCode map is " + i);
            return "";
        }
        String mapAreaCode = this.d.get(i).getMapAreaCode();
        q.h("DIAG-MAP", "getMapAreaCode " + mapAreaCode);
        return mapAreaCode;
    }

    public String d(int i) {
        if (i > this.d.size() - 1) {
            q.b("DIAG-MAP", "getMapDataVersion map is " + i);
            return "";
        }
        String mapDataVersion = this.d.get(i).getMapDataVersion();
        q.h("DIAG-MAP", "getMapDataVersion " + mapDataVersion);
        return mapDataVersion;
    }

    public String e(int i) {
        if (i > this.d.size() - 1) {
            q.b("DIAG-MAP", "getMapPackageName map is " + i);
            return "";
        }
        String mapPackageName = this.d.get(i).getMapPackageName();
        q.h("DIAG-MAP", "getMapPackageName " + mapPackageName);
        return mapPackageName;
    }

    public Integer f(int i) {
        if (i <= this.d.size() - 1) {
            return Integer.valueOf(this.d.get(i).getMapSize());
        }
        q.b("DIAG-MAP", "getMapSize map is " + i);
        return 0;
    }

    public boolean g(int i) {
        if (i > this.d.size() - 1) {
            q.b("DIAG-MAP", "getPairResult map is " + i);
            return false;
        }
        GwMapBean gwMapBean = this.d.get(i);
        String areaCode = gwMapBean.getAreaCode();
        String mapAreaCode = gwMapBean.getMapAreaCode();
        q.h("DIAG-MAP", "getPairResult areaCode：" + areaCode + "，mapAreaCode：" + mapAreaCode);
        if (TextUtils.isEmpty(areaCode) || TextUtils.isEmpty(mapAreaCode) || !areaCode.equals(mapAreaCode)) {
            q.h("DIAG-MAP", "getPairResult fail");
            return false;
        }
        q.h("DIAG-MAP", "getPairResult success");
        return true;
    }

    public String i() {
        String str = SystemProperties.get("sys.DSV.factory.countrycode", "");
        q.h("DIAG-MAP", "getSystemCountryCode COUNTRY_CODE_DSV :" + str);
        if (TextUtils.isEmpty(str) || "-1".equals(str)) {
            str = SystemProperties.get("sys.byd.countrycode", "");
        }
        q.h("DIAG-MAP", "getSystemCountryCode " + str);
        return str;
    }

    public boolean k(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            this.f1616a.getPackageManager().getApplicationInfo(str, 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            q.b("DIAG-MAP", "not found " + str);
            return false;
        }
    }

    public boolean l(int i) {
        if (i > this.d.size() - 1) {
            q.b("DIAG-MAP", "isNormalConfig map is " + i);
            return false;
        }
        GwMapBean gwMapBean = this.d.get(i);
        String mapPackageName = gwMapBean.getMapPackageName();
        String mapAreaCode = gwMapBean.getMapAreaCode();
        String currentCountryCode = gwMapBean.getCurrentCountryCode();
        if (a(gwMapBean.getCurrentNotBurningMap(), currentCountryCode) == null) {
            return m(mapPackageName, mapAreaCode, currentCountryCode);
        }
        q.h("DIAG-MAP", "isNormalConfig is not need burn map. mapAreaCode : " + mapAreaCode);
        return TextUtils.isEmpty(mapAreaCode);
    }

    public boolean n(int i) {
        if (i > this.d.size() - 1) {
            q.b("DIAG-MAP", "getPairResult map is " + i);
            return false;
        }
        String zone = this.d.get(i).getZone();
        boolean zExists = new File(zone).exists();
        q.h("DIAG-MAP", "zone = " + zone + " ,isZoneNormal=" + zExists);
        return zExists;
    }

    public boolean q(int i) {
        if (this.f1617b == null) {
            q.b("DIAG-MAP", "mCountryCodeConfigDatas is null");
            return false;
        }
        if (i > this.d.size() - 1) {
            q.b("DIAG-MAP", "startVerify map is " + i);
            return false;
        }
        try {
            GwMapBean gwMapBean = this.d.get(i);
            gwMapBean.clearData();
            MapCountryCodeConfig mapCountryCodeConfig = this.f1617b.get(i);
            String packageName = mapCountryCodeConfig.getPackageName();
            if (k(packageName)) {
                gwMapBean.setMapPackageName(packageName);
            }
            gwMapBean.setCurrentMapCountryCodeConfig(mapCountryCodeConfig.getAllArea());
            gwMapBean.setCurrentPackageSizeCommand(mapCountryCodeConfig.getPackageSizeCommand());
            gwMapBean.setCurrentNotBurningMap(mapCountryCodeConfig.getNotBurningMap());
            gwMapBean.setAreaPath(mapCountryCodeConfig.getAreaPath());
            gwMapBean.setZone(mapCountryCodeConfig.getZone());
            b(gwMapBean, i);
            q.h("DIAG-MAP", "GwMapBean : " + gwMapBean);
        } catch (Exception e) {
            q.b("DIAG-MAP", e.toString());
        }
        return true;
    }
}
