package y4;

import android.content.Context;
import android.hardware.bydauto.reminder.BYDAutoReminderDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import com.byd.uereport.ReportType;
import com.byd.uereport.bean.ReportBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: compiled from: VoiceReminderHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile d f9304a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Context f9305b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static h5.c f9306c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static LinkedList<ReportBean> f9307d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static LinkedList<ReportBean> f9308e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static a f9309f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static volatile boolean f9310g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static ReportBean f9311h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Lock f9312i = new ReentrantLock();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final String f9313j = "byd_report_toggle";

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int[] f9314k = {36, 37, 38, 41, 130, 132, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 110, 203};

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int[] f9315l = {111, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129};

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ArrayList<Integer> f9316m;

    /* JADX INFO: compiled from: VoiceReminderHandler.java */
    public static class a extends Handler {

        /* JADX INFO: renamed from: y4.d$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: VoiceReminderHandler.java */
        public class C0148a implements h5.b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ ReportBean f9317a;

            public C0148a(ReportBean reportBean) {
                this.f9317a = reportBean;
            }

            @Override // h5.b
            public void a() {
                boolean unused = d.f9310g = false;
                ReportBean unused2 = d.f9311h = null;
                a.this.b(this.f9317a);
            }

            @Override // h5.b
            public void b() {
            }

            @Override // h5.b
            public void c() {
                boolean unused = d.f9310g = false;
                ReportBean unused2 = d.f9311h = null;
                a.this.b(this.f9317a);
            }
        }

        /* JADX INFO: compiled from: VoiceReminderHandler.java */
        public class b implements h5.b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ ReportBean f9319a;

            public b(ReportBean reportBean) {
                this.f9319a = reportBean;
            }

            @Override // h5.b
            public void a() {
                boolean unused = d.f9310g = false;
                ReportBean unused2 = d.f9311h = null;
                a.this.b(this.f9319a);
            }

            @Override // h5.b
            public void b() {
                boolean unused = d.f9310g = true;
                ReportBean unused2 = d.f9311h = this.f9319a;
            }

            @Override // h5.b
            public void c() {
                boolean unused = d.f9310g = false;
                a.this.b(this.f9319a);
            }
        }

        /* JADX INFO: compiled from: VoiceReminderHandler.java */
        public class c implements Comparator<ReportBean> {
            public c() {
            }

            @Override // java.util.Comparator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(ReportBean reportBean, ReportBean reportBean2) {
                return reportBean.f3163l - reportBean2.f3163l;
            }
        }

        public a(Looper looper) {
            super(looper);
        }

        public final void b(ReportBean reportBean) {
            if (reportBean == null) {
                return;
            }
            int i8 = reportBean.f3158g;
            if (i8 > 1) {
                reportBean.f3158g = i8 - 1;
                d.n(d.f9305b).r(reportBean, true);
                return;
            }
            if (i8 != -1) {
                d.f9307d.poll();
                if (d.f9307d.size() > 0) {
                    d.n(d.f9305b).r((ReportBean) d.f9307d.get(0), true);
                    return;
                }
                return;
            }
            if (reportBean.f3160i > 0) {
                reportBean.f3162k = 1;
                d.n(d.f9305b).o(reportBean, reportBean.f3160i);
                d.f9307d.poll();
                if (d.f9307d.size() > 0) {
                    d.n(d.f9305b).r((ReportBean) d.f9307d.get(0), true);
                }
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            ReportBean reportBean;
            super.handleMessage(message);
            int i8 = message.what;
            if (i8 != 1000) {
                if (i8 != 1001) {
                    return;
                }
                g5.a.i("door report start .....");
                Collections.sort(d.f9308e, new c());
                for (ReportBean reportBean2 : d.f9308e) {
                    Message messageObtainMessage = d.f9309f.obtainMessage();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("data", reportBean2);
                    messageObtainMessage.what = 1000;
                    messageObtainMessage.setData(bundle);
                    d.f9309f.sendMessage(messageObtainMessage);
                }
                d.f9308e.clear();
                g5.a.i("door report end .....");
                return;
            }
            Bundle data = message.getData();
            if (data == null || (reportBean = (ReportBean) data.getParcelable("data")) == null) {
                return;
            }
            if (!data.getBoolean("isNextPlay", false)) {
                if (reportBean.f3161j != 106) {
                    d.f9307d.add(reportBean);
                } else if (d.f9310g) {
                    d.f9307d.add(1, reportBean);
                } else {
                    d.f9307d.add(0, reportBean);
                }
            }
            g5.a.i("isReporting:" + d.f9310g);
            if (d.f9310g) {
                g5.a.i("join play queue.");
                reportBean.f3162k = 1;
                int i9 = d.f9311h.f3159h;
                ReportType reportType = ReportType.ADAS;
                if (i9 == reportType.ordinal() && reportBean.f3159h == reportType.ordinal() && d.f9307d.size() == 2 && d.f9311h.f3163l > reportBean.f3163l) {
                    g5.a.i("ADAS report,interrupt .");
                    reportBean.f3162k = 0;
                    d.f9307d.poll();
                    if (d.f9306c == null) {
                        g5.a.c("voiceManager is not init..");
                        return;
                    } else {
                        d.f9306c.b(reportBean, new b(reportBean));
                        return;
                    }
                }
                return;
            }
            ReportBean reportBean3 = (ReportBean) d.f9307d.get(0);
            if (reportBean3.f3162k == 1 && ((reportBean3.f3159h == ReportType.UE.ordinal() || reportBean3.f3159h == ReportType.ADAS.ordinal() || reportBean3.f3159h == ReportType.DOOR.ordinal()) && reportBean3.f3161j != -1)) {
                boolean zIsSatisfyReport = BYDAutoReminderDevice.getInstance(d.f9305b).isSatisfyReport(reportBean3.f3161j);
                g5.a.i("satisfyReport:" + zIsSatisfyReport);
                if (!zIsSatisfyReport) {
                    reportBean3.f3158g = 0;
                    b(reportBean3);
                    return;
                }
            }
            if (d.f9306c == null) {
                g5.a.c("voiceManager is not init.");
                return;
            }
            boolean unused = d.f9310g = true;
            ReportBean unused2 = d.f9311h = reportBean3;
            d.f9306c.b(reportBean3, new C0148a(reportBean3));
        }
    }

    public d(Context context) {
        f9305b = context;
        f9307d = new LinkedList<>();
        f9308e = new LinkedList<>();
        HandlerThread handlerThread = new HandlerThread("UeReport");
        handlerThread.start();
        f9309f = new a(handlerThread.getLooper());
        this.f9316m = new ArrayList<>();
    }

    public static d n(Context context) {
        if (f9304a == null) {
            synchronized (d.class) {
                if (f9304a == null) {
                    f9304a = new d(context);
                }
            }
        }
        return f9304a;
    }

    public final boolean A() {
        return Settings.System.getInt(f9305b.getContentResolver(), "byd_oil_low_toggle", 2) == 1;
    }

    public final boolean B() {
        return Settings.System.getInt(f9305b.getContentResolver(), "byd_uncheck_key_toggle", 1) == 1;
    }

    public final boolean C() {
        return Settings.System.getInt(f9305b.getContentResolver(), "byd_night_run_toggle", 1) == 1;
    }

    public final boolean D() {
        return Settings.System.getInt(f9305b.getContentResolver(), "byd_park_toggle", 1) == 1;
    }

    public final boolean E(int i8) {
        int i9 = 0;
        while (true) {
            int[] iArr = this.f9314k;
            if (i9 >= iArr.length) {
                return false;
            }
            if (iArr[i9] == i8) {
                return true;
            }
            i9++;
        }
    }

    public final boolean F() {
        return Settings.System.getInt(f9305b.getContentResolver(), "byd_seatbelt_toggle", 1) == 1;
    }

    public final boolean G() {
        return Settings.System.getInt(f9305b.getContentResolver(), "byd_start_toggle", 1) == 1;
    }

    public final boolean H() {
        return Settings.System.getInt(f9305b.getContentResolver(), "byd_report_toggle", 1) == 1;
    }

    public d I(h5.c cVar) {
        f9306c = cVar;
        return f9304a;
    }

    public void l() {
        f9309f.removeCallbacksAndMessages(null);
        this.f9316m.clear();
        if (f9310g) {
            f9310g = false;
            f9311h = null;
            f9306c.a();
        }
    }

    public void m() {
        g5.a.i("continue Power On Report. ");
        for (int i8 = 0; i8 < f9307d.size(); i8++) {
            if (f9307d.get(i8).f3158g != -1) {
                f9307d.remove(i8);
            }
        }
        LinkedList<ReportBean> linkedList = f9307d;
        if (linkedList == null || linkedList.size() == 0) {
            return;
        }
        try {
            ReportBean reportBean = f9307d.get(0);
            f9307d.poll();
            s(reportBean);
        } catch (Exception e8) {
            e8.printStackTrace();
        }
    }

    public final void o(ReportBean reportBean, long j8) {
        g5.a.i("handle delay report ,delay time : " + j8);
        Message messageObtainMessage = f9309f.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putParcelable("data", reportBean);
        messageObtainMessage.what = 1000;
        messageObtainMessage.setData(bundle);
        f9309f.sendMessageDelayed(messageObtainMessage, j8);
    }

    public final void p(ReportBean reportBean) {
        g5.a.i("handle door report .");
        if (f9308e.size() != 0) {
            f9308e.add(reportBean);
            return;
        }
        g5.a.i("delay 200ms report door.");
        f9308e.add(reportBean);
        f9309f.sendEmptyMessageDelayed(1001, 200L);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:319:0x100c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:323:0x1017  */
    /* JADX WARN: Removed duplicated region for block: B:327:0x1029  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x1039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void q(int r17) {
        /*
            Method dump skipped, instruction units count: 4274
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: y4.d.q(int):void");
    }

    public final void r(ReportBean reportBean, boolean z7) {
        Message messageObtainMessage = f9309f.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putParcelable("data", reportBean);
        bundle.putBoolean("isNextPlay", z7);
        messageObtainMessage.what = 1000;
        messageObtainMessage.setData(bundle);
        f9309f.sendMessage(messageObtainMessage);
    }

    public void s(ReportBean reportBean) {
        g5.a.i("handle report .");
        Message messageObtainMessage = f9309f.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putParcelable("data", reportBean);
        messageObtainMessage.what = 1000;
        messageObtainMessage.setData(bundle);
        f9309f.sendMessage(messageObtainMessage);
    }

    public final boolean t(int i8) {
        int i9 = 0;
        while (true) {
            int[] iArr = this.f9315l;
            if (i9 >= iArr.length) {
                return false;
            }
            if (iArr[i9] == i8) {
                return true;
            }
            i9++;
        }
    }

    public final boolean u() {
        return Settings.System.getInt(f9305b.getContentResolver(), "dms_waring_tips", 2) == 1;
    }

    public final boolean v() {
        int iF = f5.a.h(f9305b).f();
        return iF == 1 || iF == 2;
    }

    public final boolean w() {
        return Settings.System.getInt(f9305b.getContentResolver(), "byd_door_toggle", 1) == 1;
    }

    public final boolean x() {
        return Settings.System.getInt(f9305b.getContentResolver(), "forget_phone_tips", 1) == 1;
    }

    public final boolean y() {
        return Settings.System.getInt(f9305b.getContentResolver(), "byd_key_power_toggle", 1) == 1;
    }

    public final boolean z() {
        return Settings.System.getInt(f9305b.getContentResolver(), "byd_leave_toggle", 1) == 1;
    }
}
