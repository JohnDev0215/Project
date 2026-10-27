package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.platform.weblab.MAPWeblabClient;
import com.amazon.identity.platform.weblab.Weblab;
import com.amazon.weblab.mobile.IMobileWeblabClient;
import com.amazon.weblab.mobile.MobileWeblabClientFactory;
import com.amazon.weblab.mobile.model.MobileWeblabTriggerResult;
import com.amazon.weblab.mobile.settings.MobileWeblabClientAttributes;
import com.amazon.weblab.mobile.settings.MobileWeblabOS;
import com.amazon.weblab.mobile.settings.MobileWeblabRuntimeConfiguration;
import com.amazon.weblab.mobile.settings.MobileWeblabServiceEndpoint;
import java.io.File;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class re implements MAPWeblabClient {
    public static final String d = "re";
    public static re e;
    public static volatile Boolean f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile IMobileWeblabClient f533a;
    public EnvironmentUtils b = EnvironmentUtils.h();
    public o5 c;

    public re(Context context) {
        this.c = (o5) t5.a(context).getSystemService("dcp_device_info");
        this.f533a = a(context);
    }

    public IMobileWeblabClient a(Context context) {
        MobileWeblabOS mobileWeblabOS;
        if (qe.g(context)) {
            ga.a(d);
            mobileWeblabOS = MobileWeblabOS.FIRE_OS;
        } else {
            ga.a(d);
            mobileWeblabOS = MobileWeblabOS.ANDROID;
        }
        MobileWeblabOS mobileWeblabOS2 = mobileWeblabOS;
        String packageName = qe.m(context) ? context.getApplicationContext().getPackageName() : z9.b(context);
        ga.a(d);
        MobileWeblabClientAttributes mobileWeblabClientAttributes = new MobileWeblabClientAttributes("MAP_ANDROID_ID", packageName, s9.b(), mobileWeblabOS2, Integer.toString(Build.VERSION.SDK_INT));
        Weblab[] weblabArrValues = Weblab.values();
        for (int i = 0; i < 2; i++) {
            Weblab weblab = weblabArrValues[i];
            mobileWeblabClientAttributes.addWeblab(weblab.b(), weblab.a().name());
        }
        File dir = context.getApplicationContext().getDir("mapweblab", 0);
        if (!dir.exists() && !dir.mkdirs()) {
            Log.e(ga.a(d), "can not create weblab folder");
        }
        MobileWeblabRuntimeConfiguration mobileWeblabRuntimeConfiguration = new MobileWeblabRuntimeConfiguration(dir.getAbsolutePath());
        mobileWeblabRuntimeConfiguration.setEndpoint(MobileWeblabServiceEndpoint.PROD);
        mobileWeblabRuntimeConfiguration.setUpdateAtInitEnabled(true);
        String strE = this.c.e();
        String str = d;
        ga.a(str);
        if (strE.length() > 10) {
            strE = strE.substring(0, 10);
        }
        ga.a(str);
        IMobileWeblabClient iMobileWeblabClientCreateMobileWeblabClient = MobileWeblabClientFactory.createMobileWeblabClient(mobileWeblabClientAttributes, mobileWeblabRuntimeConfiguration, strE, this.b.j(), null, context.getApplicationContext());
        String.format("Weblab instance: %s, weblab market: %s", iMobileWeblabClientCreateMobileWeblabClient.getIMobileWeblabClientAttributes().getIdentifier(), iMobileWeblabClientCreateMobileWeblabClient.getMarketplace());
        ga.a(str);
        return iMobileWeblabClientCreateMobileWeblabClient;
    }

    @Override // com.amazon.identity.platform.weblab.MAPWeblabClient
    public String getTreatmentAndRecordTrigger(String str) {
        Weblab weblab;
        try {
            Weblab weblab2 = Weblab.f715a;
            weblab = (Weblab) Enum.valueOf(Weblab.class, str);
        } catch (IllegalArgumentException e2) {
            Log.w(ga.a(d), "Error while converting weblab " + str, e2);
            weblab = null;
        }
        if (weblab == null) {
            Log.w(ga.a(d), "Cannot converting weblab, return C");
            return Weblab.Treatment.C.name();
        }
        try {
            Future<MobileWeblabTriggerResult> futureMobileWeblabTrigger = this.f533a.getWeblab(weblab.b()).getTreatmentAndRecordTrigger().getFutureMobileWeblabTrigger();
            if (futureMobileWeblabTrigger != null) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                MobileWeblabTriggerResult mobileWeblabTriggerResult = futureMobileWeblabTrigger.get(1000L, TimeUnit.MILLISECONDS);
                long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                String str2 = d;
                mobileWeblabTriggerResult.name();
                ga.a(str2);
                ga.a(str2);
                yd.a("MAP_WeblabTriggerResult_" + mobileWeblabTriggerResult, new String[0]);
                yd.a("MAP_WeblabTimeCost_" + jCurrentTimeMillis2, new String[0]);
            }
            String treatment = this.f533a.getWeblab(weblab.b()).getTreatmentAndRecordTrigger().getTreatment();
            String str3 = d;
            String.format("MAP weblab value for %s: %s", str, treatment);
            ga.a(str3);
            yd.a("MAP_WeblabValue_" + treatment, new String[0]);
            return treatment;
        } catch (TimeoutException unused) {
            String strName = weblab.a().name();
            ga.a(d, null, "Timeout in weblab service call, returning default value: " + strName, "MAPWeblabTimeOut");
            return strName;
        } catch (Exception e3) {
            String strName2 = weblab.a().name();
            Log.e(ga.a(d), "Exception when trying to get the weblab, fall back to default: " + strName2, e3);
            return strName2;
        }
    }
}
