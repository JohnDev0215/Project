package b.b.g.n.j;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.INetworkStatsService;
import android.net.INetworkStatsSession;
import android.net.NetworkStatsHistory;
import android.net.NetworkTemplate;
import android.os.Build;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import com.byd.carsettings.model.dilink.BydAppInfo;
import com.byd.carsettings.model.dilink.ChartData;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: compiled from: AppDetailsPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends b.b.m.e.g<b.b.g.q.f.b.c> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1045a = "TOTAL";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f1046b = "Cached";

    /* JADX INFO: renamed from: b.b.g.n.j.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AppDetailsPresenter.java */
    public class RunnableC0052a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f1047a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f1048b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f1049c;

        public RunnableC0052a(String str, int i, int i2) {
            this.f1047a = str;
            this.f1048b = i;
            this.f1049c = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            List<b.b.g.m.d.c> listH = b.b.g.p.h.h(((b.b.g.q.f.b.c) a.this.getV()).getContext(), this.f1047a);
            if (a.this.getV() != null) {
                ((b.b.g.q.f.b.c) a.this.getV()).sendMessage(this.f1048b, this.f1049c, listH);
            }
        }
    }

    /* JADX INFO: compiled from: AppDetailsPresenter.java */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f1051a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f1052b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f1053c;

        public b(String str, int i, int i2) {
            this.f1051a = str;
            this.f1052b = i;
            this.f1053c = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            BydAppInfo bydAppInfoK = b.b.g.p.h.k(((b.b.g.q.f.b.c) a.this.getV()).getContext(), this.f1051a);
            if (a.this.getV() != null) {
                ((b.b.g.q.f.b.c) a.this.getV()).sendMessage(this.f1052b, this.f1053c, bydAppInfoK);
            }
        }
    }

    /* JADX INFO: compiled from: AppDetailsPresenter.java */
    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f1055a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f1056b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f1057c;

        public c(String str, int i, int i2) {
            this.f1055a = str;
            this.f1056b = i;
            this.f1057c = i2;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            String strI;
            HashMap mapX = a.this.x(this.f1055a, "dumpsys procstats --hours 3 ");
            try {
                String strI2 = null;
                if (mapX.containsKey("TOTAL")) {
                    strI = a.this.I((String) mapX.get("TOTAL"));
                } else {
                    strI = null;
                }
                if (mapX.containsKey("Cached")) {
                    strI2 = a.this.I((String) mapX.get("Cached"));
                }
                if (a.this.getV() == null) {
                    return;
                }
                if (strI == null || strI2 == null) {
                    if (strI == null && strI2 != null) {
                        ((b.b.g.q.f.b.c) a.this.getV()).sendMessage(this.f1056b, this.f1057c, strI2);
                        return;
                    } else if (strI == null || strI2 != null) {
                        ((b.b.g.q.f.b.c) a.this.getV()).sendMessage(this.f1056b, this.f1057c, "");
                        return;
                    } else {
                        ((b.b.g.q.f.b.c) a.this.getV()).sendMessage(this.f1056b, this.f1057c, strI);
                        return;
                    }
                }
                float f2 = Float.parseFloat(strI.substring(0, strI.length() - 2));
                String strSubstring = strI.substring(strI.length() - 2);
                float f3 = Float.parseFloat(strI2.substring(0, strI2.length() - 2));
                strI2.substring(strI2.length() - 2);
                ((b.b.g.q.f.b.c) a.this.getV()).sendMessage(this.f1056b, this.f1057c, (f2 + f3) + strSubstring);
            } catch (Exception e2) {
                e2.printStackTrace();
                ((b.b.g.q.f.b.c) a.this.getV()).sendMessage(this.f1056b, this.f1057c, "");
            }
        }
    }

    /* JADX INFO: compiled from: AppDetailsPresenter.java */
    public class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f1059a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f1060b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ int f1061c;

        public d(int i, int i2, int i3) {
            this.f1059a = i;
            this.f1060b = i2;
            this.f1061c = i3;
        }

        @Override // java.lang.Runnable
        public void run() {
            ChartData chartData = new ChartData();
            try {
                Context context = ((b.b.g.q.f.b.c) a.this.getV()).getContext();
                NetworkTemplate networkTemplateD = a.this.D(context);
                INetworkStatsSession iNetworkStatsSessionOpenSession = INetworkStatsService.Stub.asInterface(ServiceManager.getService("netstats")).openSession();
                chartData.network = iNetworkStatsSessionOpenSession.getHistoryForNetwork(networkTemplateD, 10);
                int i = this.f1059a;
                if (i > 0) {
                    chartData.detailDefault = a.this.y(iNetworkStatsSessionOpenSession, networkTemplateD, i, 0, chartData.detailDefault);
                    NetworkStatsHistory networkStatsHistoryY = a.this.y(iNetworkStatsSessionOpenSession, networkTemplateD, this.f1059a, 1, chartData.detailForeground);
                    chartData.detailForeground = networkStatsHistoryY;
                    NetworkStatsHistory networkStatsHistory = new NetworkStatsHistory(networkStatsHistoryY.getBucketDuration());
                    chartData.detail = networkStatsHistory;
                    networkStatsHistory.recordEntireHistory(chartData.detailDefault);
                    chartData.detail.recordEntireHistory(chartData.detailForeground);
                } else {
                    chartData.detailDefault = new NetworkStatsHistory(3600000L);
                    chartData.detailForeground = new NetworkStatsHistory(3600000L);
                    chartData.detail = new NetworkStatsHistory(3600000L);
                }
                if (a.this.getV() != null) {
                    b.b.m.c.a.a("AppDetailsPresenter", "Total:" + Formatter.formatFileSize(context, chartData.detail.getTotalBytes()));
                    ((b.b.g.q.f.b.c) a.this.getV()).sendMessage(this.f1060b, this.f1061c, chartData);
                }
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void A(String str, int i, int i2) {
        b.b.m.g.b.a(new RunnableC0052a(str, i, i2));
    }

    public void B(int i, int i2, int i3) {
        b.b.m.g.b.a(new d(i, i2, i3));
    }

    public void C(String str, int i, int i2) {
        b.b.m.g.b.a(new c(str, i, i2));
    }

    public final NetworkTemplate D(Context context) {
        return E(context) ? NetworkTemplate.buildTemplateMobileWildcard() : !F(context) ? NetworkTemplate.buildTemplateEthernet() : NetworkTemplate.buildTemplateWifiWildcard();
    }

    public final boolean E(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        List<SubscriptionInfo> activeSubscriptionInfoList = SubscriptionManager.from(context).getActiveSubscriptionInfoList();
        if (activeSubscriptionInfoList == null) {
            return false;
        }
        boolean z = true;
        for (SubscriptionInfo subscriptionInfo : activeSubscriptionInfoList) {
            if (Build.VERSION.SDK_INT >= 26) {
                z &= telephonyManager.getSimState(subscriptionInfo.getSimSlotIndex()) == 5;
            }
        }
        return G(connectivityManager) && z;
    }

    public final boolean F(Context context) {
        return H((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity"));
    }

    public final boolean G(ConnectivityManager connectivityManager) {
        return true;
    }

    public final boolean H(ConnectivityManager connectivityManager) {
        return true;
    }

    public final String I(String str) {
        if (TextUtils.isEmpty(str) || !str.contains("/")) {
            return null;
        }
        return str.substring(str.indexOf("/") + 1, str.lastIndexOf("/")).split("-")[1];
    }

    public final HashMap<String, String> x(String str, String str2) throws Throwable {
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        HashMap<String, String> map = new HashMap<>();
        Process process = null;
        try {
            try {
                Process processExec = Runtime.getRuntime().exec(str2);
                try {
                    inputStreamReader = new InputStreamReader(processExec.getInputStream());
                    try {
                        bufferedReader = new BufferedReader(inputStreamReader);
                        boolean z = false;
                        while (true) {
                            try {
                                String line = bufferedReader.readLine();
                                if (line == null) {
                                    processExec.destroy();
                                    bufferedReader.close();
                                    inputStreamReader.close();
                                    break;
                                }
                                if (z) {
                                    if (!line.contains("TOTAL")) {
                                        if (!line.contains("Cached")) {
                                            if (line.trim().startsWith("*") || line.trim().startsWith(BydAdbHelper.COMMAND_LINE_END)) {
                                                break;
                                            }
                                        } else {
                                            map.put("Cached", line);
                                        }
                                    } else {
                                        map.put("TOTAL", line);
                                    }
                                }
                                if (line.contains(str)) {
                                    String strTrim = line.substring(line.indexOf("*") + 1, line.indexOf("/")).trim();
                                    if (str.equals(strTrim)) {
                                        Log.i("sunshine", "package name :" + strTrim);
                                        z = true;
                                    }
                                }
                            } catch (IOException e2) {
                                e = e2;
                                process = processExec;
                                try {
                                    e.printStackTrace();
                                    process.destroy();
                                    bufferedReader.close();
                                    inputStreamReader.close();
                                    return map;
                                } catch (Throwable th) {
                                    th = th;
                                    process.destroy();
                                    try {
                                        bufferedReader.close();
                                        inputStreamReader.close();
                                    } catch (IOException e3) {
                                        e3.printStackTrace();
                                    }
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                process = processExec;
                                process.destroy();
                                bufferedReader.close();
                                inputStreamReader.close();
                                throw th;
                            }
                        }
                        processExec.destroy();
                        try {
                            bufferedReader.close();
                            inputStreamReader.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                        return map;
                    } catch (IOException e5) {
                        e = e5;
                        bufferedReader = null;
                    } catch (Throwable th3) {
                        th = th3;
                        bufferedReader = null;
                    }
                } catch (IOException e6) {
                    e = e6;
                    inputStreamReader = null;
                    bufferedReader = null;
                } catch (Throwable th4) {
                    th = th4;
                    inputStreamReader = null;
                    bufferedReader = null;
                }
            } catch (IOException e7) {
                e7.printStackTrace();
                return map;
            }
        } catch (IOException e8) {
            e = e8;
            inputStreamReader = null;
            bufferedReader = null;
        } catch (Throwable th5) {
            th = th5;
            inputStreamReader = null;
            bufferedReader = null;
        }
    }

    public final NetworkStatsHistory y(INetworkStatsSession iNetworkStatsSession, NetworkTemplate networkTemplate, int i, int i2, NetworkStatsHistory networkStatsHistory) throws RemoteException {
        NetworkStatsHistory historyForUid = iNetworkStatsSession.getHistoryForUid(networkTemplate, i, i2, 0, 10);
        if (networkStatsHistory == null) {
            return historyForUid;
        }
        networkStatsHistory.recordEntireHistory(historyForUid);
        return networkStatsHistory;
    }

    public void z(String str, int i, int i2) {
        b.b.m.g.b.a(new b(str, i, i2));
    }
}
