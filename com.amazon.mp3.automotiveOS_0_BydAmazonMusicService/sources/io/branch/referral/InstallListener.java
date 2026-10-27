package io.branch.referral;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.amazon.music.destination.parser.ParserUtil;
import io.branch.referral.InstallReferrerClientWrapper;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes7.dex */
public class InstallListener extends BroadcastReceiver {
    private static IInstallReferrerEvents callback_ = null;
    private static String installID_ = "bnc_no_value";
    private static boolean isReferrerClientAvailable;
    private static boolean isWaitingForReferrer;
    static boolean unReportedReferrerAvailable;
    InstallReferrerClientWrapper.InstallReferrerWrapperListener installReferrerWrapperListener = new InstallReferrerClientWrapper.InstallReferrerWrapperListener() { // from class: io.branch.referral.InstallListener.2
        @Override // io.branch.referral.InstallReferrerClientWrapper.InstallReferrerWrapperListener
        public void onReferrerClientFinished(Context context, String str, long j, long j2) {
            PrefHelper.Debug("onReferrerClientFinished()");
            InstallListener.processReferrerInfo(context, str, j, j2);
            if (InstallListener.isWaitingForReferrer) {
                InstallListener.reportInstallReferrer();
            }
        }

        @Override // io.branch.referral.InstallReferrerClientWrapper.InstallReferrerWrapperListener
        public void onReferrerClientError() {
            PrefHelper.Debug("onReferrerClientError()");
            boolean unused = InstallListener.isReferrerClientAvailable = false;
        }
    };

    interface IInstallReferrerEvents {
        void onInstallReferrerEventsFinished();
    }

    public void captureInstallReferrer(Context context, long j, IInstallReferrerEvents iInstallReferrerEvents) {
        callback_ = iInstallReferrerEvents;
        if (unReportedReferrerAvailable) {
            reportInstallReferrer();
            return;
        }
        isWaitingForReferrer = true;
        isReferrerClientAvailable = new InstallReferrerClientWrapper(context).getReferrerUsingReferrerClient(this.installReferrerWrapperListener);
        new Timer().schedule(new TimerTask() { // from class: io.branch.referral.InstallListener.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                InstallListener.reportInstallReferrer();
            }
        }, j);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        processReferrerInfo(context, intent.getStringExtra("referrer"), 0L, 0L);
        if (!isWaitingForReferrer || isReferrerClientAvailable) {
            return;
        }
        reportInstallReferrer();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void processReferrerInfo(Context context, String str, long j, long j2) {
        PrefHelper prefHelper = PrefHelper.getInstance(context);
        if (j > 0) {
            prefHelper.setLong("bnc_referrer_click_ts", j);
        }
        if (j2 > 0) {
            prefHelper.setLong("bnc_install_begin_ts", j2);
        }
        if (str != null) {
            try {
                String strDecode = URLDecoder.decode(str, ParserUtil.UTF_8);
                HashMap map = new HashMap();
                for (String str2 : strDecode.split("&")) {
                    if (!TextUtils.isEmpty(str2)) {
                        String str3 = "-";
                        if (str2.contains("=") || !str2.contains("-")) {
                            str3 = "=";
                        }
                        String[] strArrSplit = str2.split(str3);
                        if (strArrSplit.length > 1) {
                            map.put(URLDecoder.decode(strArrSplit[0], ParserUtil.UTF_8), URLDecoder.decode(strArrSplit[1], ParserUtil.UTF_8));
                        }
                    }
                }
                Defines$Jsonkey defines$Jsonkey = Defines$Jsonkey.LinkClickID;
                if (map.containsKey(defines$Jsonkey.getKey())) {
                    String str4 = (String) map.get(defines$Jsonkey.getKey());
                    installID_ = str4;
                    prefHelper.setLinkClickIdentifier(str4);
                }
                Defines$Jsonkey defines$Jsonkey2 = Defines$Jsonkey.IsFullAppConv;
                if (map.containsKey(defines$Jsonkey2.getKey())) {
                    Defines$Jsonkey defines$Jsonkey3 = Defines$Jsonkey.ReferringLink;
                    if (map.containsKey(defines$Jsonkey3.getKey())) {
                        prefHelper.setIsFullAppConversion(Boolean.parseBoolean((String) map.get(defines$Jsonkey2.getKey())));
                        prefHelper.setAppLink((String) map.get(defines$Jsonkey3.getKey()));
                    }
                }
                Defines$Jsonkey defines$Jsonkey4 = Defines$Jsonkey.GoogleSearchInstallReferrer;
                if (map.containsKey(defines$Jsonkey4.getKey())) {
                    prefHelper.setGoogleSearchInstallIdentifier((String) map.get(defines$Jsonkey4.getKey()));
                    prefHelper.setGooglePlayReferrer(strDecode);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
                PrefHelper.Debug("Illegal characters in url encoded string");
            }
        }
    }

    public static String getInstallationID() {
        return installID_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void reportInstallReferrer() {
        unReportedReferrerAvailable = true;
        IInstallReferrerEvents iInstallReferrerEvents = callback_;
        if (iInstallReferrerEvents != null) {
            iInstallReferrerEvents.onInstallReferrerEventsFinished();
            callback_ = null;
            unReportedReferrerAvailable = false;
            isWaitingForReferrer = false;
            isReferrerClientAvailable = false;
        }
    }
}
