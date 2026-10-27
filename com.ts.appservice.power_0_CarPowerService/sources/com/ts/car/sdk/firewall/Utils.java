package com.ts.car.sdk.firewall;

import android.util.Log;
import com.ts.firewall.service.data.ApplicationRule;
import com.ts.firewall.service.data.FirewallData;
import com.ts.firewall.service.data.Url;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Utils {
    public static ApplicationRule convertAppRuleToAidl(ts.car.service.firewall.data.ApplicationRule apprule) {
        if (apprule == null) {
            Log.d("Utils", "apprule == null convert failure");
            return null;
        }
        ApplicationRule newApprule = new ApplicationRule();
        newApprule.setUid(apprule.getUid());
        newApprule.setPackageName(apprule.getPackageName());
        newApprule.setUrl(convertUrlArrayToAidl(apprule.getUrlArray()));
        newApprule.setIface(apprule.getIface());
        return newApprule;
    }

    public static ts.car.service.firewall.data.ApplicationRule convertAidlToAppRule(ApplicationRule apprule) {
        if (apprule == null) {
            Log.d("Utils", "apprule == null convert failure");
            return null;
        }
        ts.car.service.firewall.data.ApplicationRule newApprule = new ts.car.service.firewall.data.ApplicationRule();
        newApprule.setUid(apprule.getUid());
        newApprule.setPackageName(apprule.getPackageName());
        newApprule.setUrl(convertAidlToUrlArray(apprule.getUrlArray()));
        newApprule.setIface(apprule.getIface());
        return newApprule;
    }

    public static List<ApplicationRule> convertAppRuleToAidlArray(List<ts.car.service.firewall.data.ApplicationRule> appruleArray) {
        if (appruleArray == null) {
            Log.d("Utils", "appruleArray == null convert failure");
            return null;
        }
        List<ApplicationRule> newAppruleArray = new ArrayList<>();
        for (int i = 0; i < appruleArray.size(); i++) {
            newAppruleArray.add(convertAppRuleToAidl(appruleArray.get(i)));
        }
        return newAppruleArray;
    }

    public static List<ts.car.service.firewall.data.ApplicationRule> convertAidlToAppRuleArray(List<ApplicationRule> appruleArray) {
        if (appruleArray == null) {
            Log.d("Utils", "appruleArray == null convert failure");
            return null;
        }
        List<ts.car.service.firewall.data.ApplicationRule> newAppruleArray = new ArrayList<>();
        for (int i = 0; i < appruleArray.size(); i++) {
            newAppruleArray.add(convertAidlToAppRule(appruleArray.get(i)));
        }
        return newAppruleArray;
    }

    public static Url convertUrlToAidl(ts.car.service.firewall.data.Url url) {
        if (url == null) {
            Log.d("Utils", "url == null convert failure");
            return null;
        }
        Url newUrl = new Url();
        newUrl.setPort(url.getPort());
        newUrl.setDomain(url.getDomain());
        return newUrl;
    }

    public static ts.car.service.firewall.data.Url convertAidlToUrl(Url url) {
        if (url == null) {
            Log.d("Utils", "url == null convert failure");
            return null;
        }
        ts.car.service.firewall.data.Url newUrl = new ts.car.service.firewall.data.Url();
        newUrl.setPort(url.getPort());
        newUrl.setDomain(url.getDomain());
        return newUrl;
    }

    public static List<Url> convertUrlArrayToAidl(List<ts.car.service.firewall.data.Url> urlArray) {
        if (urlArray == null) {
            Log.d("Utils", "url == null convert failure");
            return null;
        }
        List<Url> newUrlArray = new ArrayList<>();
        for (int i = 0; i < urlArray.size(); i++) {
            newUrlArray.add(convertUrlToAidl(urlArray.get(i)));
        }
        return newUrlArray;
    }

    public static List<ts.car.service.firewall.data.Url> convertAidlToUrlArray(List<Url> urlArray) {
        if (urlArray == null) {
            Log.d("Utils", "url == null convert failure");
            return null;
        }
        List<ts.car.service.firewall.data.Url> newUrlArray = new ArrayList<>();
        for (int i = 0; i < urlArray.size(); i++) {
            newUrlArray.add(convertAidlToUrl(urlArray.get(i)));
        }
        return newUrlArray;
    }

    public static FirewallData convertFirewallDataToAidl(ts.car.service.firewall.data.FirewallData data) {
        if (data == null) {
            Log.d("Utils", "data == null convert failure");
            return null;
        }
        FirewallData newData = new FirewallData(data.mData);
        return newData;
    }

    public static List<FirewallData> convertFirewallDataArrayToAidl(List<ts.car.service.firewall.data.FirewallData> dataArray) {
        if (dataArray == null) {
            Log.d("Utils", "data == null convert failure");
            return null;
        }
        List<FirewallData> newDataArray = new ArrayList<>();
        for (int i = 0; i < dataArray.size(); i++) {
            newDataArray.add(convertFirewallDataToAidl(dataArray.get(i)));
        }
        return newDataArray;
    }

    public static ts.car.service.firewall.data.FirewallData convertAidlToFirewallData(FirewallData data) {
        if (data == null) {
            Log.d("Utils", "apprule == null convert failure");
            return null;
        }
        ts.car.service.firewall.data.FirewallData newData = new ts.car.service.firewall.data.FirewallData(data.mData);
        return newData;
    }

    public static List<ts.car.service.firewall.data.FirewallData> convertAidlToFirewallDataArray(List<FirewallData> dataArray) {
        if (dataArray == null) {
            Log.d("Utils", "apprule == null convert failure");
            return null;
        }
        List<ts.car.service.firewall.data.FirewallData> newDataArray = new ArrayList<>();
        for (int i = 0; i < dataArray.size(); i++) {
            newDataArray.add(convertAidlToFirewallData(dataArray.get(i)));
        }
        return newDataArray;
    }

    public static boolean checkApplicationRule(ts.car.service.firewall.data.ApplicationRule appRule) {
        if (appRule.getUid() == 0 && appRule.getPackageName() == null) {
            return false;
        }
        return true;
    }

    public static boolean checkApplicationRule(List<ts.car.service.firewall.data.ApplicationRule> appRules) {
        if (appRules == null) {
            return false;
        }
        for (int i = 0; i < appRules.size(); i++) {
            if (!checkApplicationRule(appRules.get(i))) {
                return false;
            }
        }
        return true;
    }
}
