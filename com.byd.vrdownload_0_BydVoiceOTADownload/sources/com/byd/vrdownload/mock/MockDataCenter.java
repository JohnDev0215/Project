package com.byd.vrdownload.mock;

import com.byd.vrdownload.BuildConfig;
import com.byd.vrdownload.model.GetLanguagesUpdate;
import com.byd.vrdownload.model.LanguageName;
import com.byd.vrdownload.model.LanguageOTA;
import com.byd.vrdownload.model.PkgVersion;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MockDataCenter {
    public static List<LanguageOTA> getLanguageOTAList() {
        ArrayList arrayList = new ArrayList();
        LanguageOTA languageOTA = new LanguageOTA();
        LanguageOTA languageOTA2 = new LanguageOTA();
        LanguageOTA languageOTA3 = new LanguageOTA();
        languageOTA.setLanguage("data_spa-ESP");
        languageOTA.setPkgVersion("D.1.23.0105A");
        languageOTA.setPkgSize("500MB");
        languageOTA.setPkgKey("AAAA");
        languageOTA2.setLanguage("data_por-BRA");
        languageOTA2.setPkgVersion("D.2.23.0105A");
        languageOTA2.setPkgSize("500MB");
        languageOTA2.setPkgKey("BBBB");
        languageOTA3.setLanguage("data_jpn-JPN");
        languageOTA3.setPkgVersion("D.1.23.0105A");
        languageOTA3.setPkgSize("500MB");
        languageOTA3.setPkgKey("CCCC");
        arrayList.add(languageOTA);
        arrayList.add(languageOTA2);
        arrayList.add(languageOTA3);
        return arrayList;
    }

    public static GetLanguagesUpdate createParameter() {
        GetLanguagesUpdate getLanguagesUpdate = new GetLanguagesUpdate();
        ArrayList arrayList = new ArrayList();
        PkgVersion pkgVersion = new PkgVersion();
        pkgVersion.setLanguage(LanguageName.ENG);
        pkgVersion.setPkgVersion("D.1.23.0112A");
        arrayList.add(pkgVersion);
        getLanguagesUpdate.setApkVersion(BuildConfig.VERSION_NAME);
        getLanguagesUpdate.setxPlatform("prerelease");
        getLanguagesUpdate.setVin("20220120001");
        getLanguagesUpdate.setPkgVersion(arrayList);
        return getLanguagesUpdate;
    }
}
