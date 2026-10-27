package defpackage;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;
import java.util.Objects;

/* JADX INFO: renamed from: nc, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1223nc extends AbstractC1278oc {
    public static final Object b = new Object();
    public static final C1223nc c = new C1223nc();

    public static Dialog h(Context context, int i, AbstractDialogInterfaceOnClickListenerC1555tf abstractDialogInterfaceOnClickListenerC1555tf, DialogInterface.OnCancelListener onCancelListener) {
        if (i == 0) {
            return null;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.alertDialogTheme, typedValue, true);
        AlertDialog.Builder builder = "Theme.Dialog.Alert".equals(context.getResources().getResourceEntryName(typedValue.resourceId)) ? new AlertDialog.Builder(context, 5) : null;
        if (builder == null) {
            builder = new AlertDialog.Builder(context);
        }
        builder.setMessage(AbstractC1445rf.d(context, i));
        if (onCancelListener != null) {
            builder.setOnCancelListener(onCancelListener);
        }
        Resources resources = context.getResources();
        String string = i != 1 ? i != 2 ? i != 3 ? resources.getString(R.string.ok) : resources.getString(AbstractC0566bn.N0) : resources.getString(AbstractC0566bn.X0) : resources.getString(AbstractC0566bn.Q0);
        if (string != null) {
            builder.setPositiveButton(string, abstractDialogInterfaceOnClickListenerC1555tf);
        }
        String strC = AbstractC1445rf.c(context, i);
        if (strC != null) {
            builder.setTitle(strC);
        }
        return builder.create();
    }

    public static void j(Activity activity, Dialog dialog, String str, DialogInterface.OnCancelListener onCancelListener) {
        if (!(activity instanceof O3)) {
            FragmentManager fragmentManager = activity.getFragmentManager();
            DialogFragmentC1113lc dialogFragmentC1113lc = new DialogFragmentC1113lc();
            Cif.g(dialog, "Cannot display null dialog");
            dialog.setOnCancelListener(null);
            dialog.setOnDismissListener(null);
            dialogFragmentC1113lc.s = dialog;
            if (onCancelListener != null) {
                dialogFragmentC1113lc.t = onCancelListener;
            }
            dialogFragmentC1113lc.show(fragmentManager, str);
            return;
        }
        S3 s3E = ((O3) activity).e();
        C1497sc c1497sc = new C1497sc();
        Cif.g(dialog, "Cannot display null dialog");
        dialog.setOnCancelListener(null);
        dialog.setOnDismissListener(null);
        c1497sc.y0 = dialog;
        if (onCancelListener != null) {
            c1497sc.z0 = onCancelListener;
        }
        c1497sc.w0 = false;
        c1497sc.x0 = true;
        LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = (LayoutInflaterFactory2C0974j4) s3E;
        Objects.requireNonNull(layoutInflaterFactory2C0974j4);
        C3 c3 = new C3(layoutInflaterFactory2C0974j4);
        c3.f(0, c1497sc, str, 1);
        c3.d();
    }

    @Override // defpackage.AbstractC1278oc
    @Deprecated
    public Intent a(int i) {
        return b(null, i, null);
    }

    @Override // defpackage.AbstractC1278oc
    public Intent b(Context context, int i, String str) {
        return super.b(context, i, str);
    }

    @Override // defpackage.AbstractC1278oc
    public PendingIntent c(Context context, int i, int i2, String str) {
        return super.c(context, i, i2, null);
    }

    @Override // defpackage.AbstractC1278oc
    public int d(Context context, int i) {
        return super.d(context, i);
    }

    public int e(Context context) {
        return d(context, AbstractC1278oc.a);
    }

    public boolean f(Activity activity, int i, int i2, DialogInterface.OnCancelListener onCancelListener) {
        Dialog dialogH = h(activity, i, new C1610uf(super.b(activity, i, "d"), activity, i2), onCancelListener);
        if (dialogH == null) {
            return false;
        }
        j(activity, dialogH, "GooglePlayServicesErrorDialog", onCancelListener);
        return true;
    }

    public void g(Context context, int i) {
        k(context, i, super.c(context, i, 0, "n"));
    }

    public final C0557be i(Context context, AbstractC0669de abstractC0669de) {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        C0557be c0557be = new C0557be(abstractC0669de);
        context.registerReceiver(c0557be, intentFilter);
        c0557be.a = context;
        if (AbstractC1388qc.c(context, "com.google.android.gms")) {
            return c0557be;
        }
        abstractC0669de.a();
        c0557be.a();
        return null;
    }

    public final void k(Context context, int i, PendingIntent pendingIntent) {
        int i2;
        Log.w("GoogleApiAvailability", String.format("GMS core API Availability. ConnectionResult=%s, tag=%s", Integer.valueOf(i), null), new IllegalArgumentException());
        if (i == 18) {
            new HandlerC1168mc(this, context).sendEmptyMessageDelayed(1, 120000L);
            return;
        }
        if (pendingIntent == null) {
            if (i == 6) {
                Log.w("GoogleApiAvailability", "Missing resolution for ConnectionResult.RESOLUTION_REQUIRED. Call GoogleApiAvailability#showErrorNotification(Context, ConnectionResult) instead.");
                return;
            }
            return;
        }
        String strF = i == 6 ? AbstractC1445rf.f(context, "common_google_play_services_resolution_required_title") : AbstractC1445rf.c(context, i);
        if (strF == null) {
            strF = context.getResources().getString(AbstractC0566bn.U0);
        }
        String strE = (i == 6 || i == 19) ? AbstractC1445rf.e(context, "common_google_play_services_resolution_required_text", AbstractC1445rf.a(context)) : AbstractC1445rf.d(context, i);
        Resources resources = context.getResources();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        M1 m1 = new M1(context, null);
        m1.m = true;
        m1.d(16, true);
        m1.d = M1.c(strF);
        L1 l1 = new L1();
        l1.c = M1.c(strE);
        if (m1.i != l1) {
            m1.i = l1;
            if (l1.a != m1) {
                l1.a = m1;
                m1.e(l1);
            }
        }
        if (AbstractC0203Lf.a(context)) {
            Cif.h(true);
            m1.t.icon = context.getApplicationInfo().icon;
            m1.g = 2;
            if (AbstractC0203Lf.b(context)) {
                m1.a(AbstractC1057kc.common_full_open_on_phone, resources.getString(AbstractC0566bn.c1), pendingIntent);
            } else {
                m1.f = pendingIntent;
            }
        } else {
            m1.t.icon = R.drawable.stat_sys_warning;
            m1.t.tickerText = M1.c(resources.getString(AbstractC0566bn.U0));
            m1.t.when = System.currentTimeMillis();
            m1.f = pendingIntent;
            m1.e = M1.c(strE);
        }
        if (AbstractC0220Mf.a()) {
            Cif.h(AbstractC0220Mf.a());
            synchronized (b) {
            }
            NotificationChannel notificationChannel = notificationManager.getNotificationChannel("com.google.android.gms.availability");
            String strB = AbstractC1445rf.b(context);
            if (notificationChannel == null) {
                notificationManager.createNotificationChannel(new NotificationChannel("com.google.android.gms.availability", strB, 4));
            } else if (!strB.contentEquals(notificationChannel.getName())) {
                notificationChannel.setName(strB);
                notificationManager.createNotificationChannel(notificationChannel);
            }
            m1.q = "com.google.android.gms.availability";
        }
        Notification notificationB = m1.b();
        if (i == 1 || i == 2 || i == 3) {
            i2 = 10436;
            AbstractC1388qc.b.set(false);
        } else {
            i2 = 39789;
        }
        notificationManager.notify(i2, notificationB);
    }
}
