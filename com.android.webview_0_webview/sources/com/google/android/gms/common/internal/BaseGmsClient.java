package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Scope;
import defpackage.AbstractC0056Dc;
import defpackage.AbstractC0255Oe;
import defpackage.AbstractC0399We;
import defpackage.AbstractC0435Ye;
import defpackage.AbstractC1278oc;
import defpackage.BinderC0291Qe;
import defpackage.C0077Ef;
import defpackage.C0309Re;
import defpackage.C0327Se;
import defpackage.C0725ef;
import defpackage.Cif;
import defpackage.HandlerC0237Ne;
import defpackage.InterfaceC0148Ie;
import defpackage.InterfaceC0166Je;
import defpackage.InterfaceC0184Ke;
import defpackage.InterfaceC0502af;
import defpackage.InterfaceC0781ff;
import defpackage.ServiceConnectionC0273Pe;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class BaseGmsClient {
    public static final Feature[] z = new Feature[0];
    public int a;
    public long b;
    public long c;
    public int d;
    public long e;
    public C0077Ef f;
    public final Context g;
    public final AbstractC0435Ye h;
    public final Handler i;
    public InterfaceC0781ff l;
    public InterfaceC0184Ke m;
    public IInterface n;
    public ServiceConnectionC0273Pe p;
    public final InterfaceC0148Ie r;
    public final InterfaceC0166Je s;
    public final int t;
    public final String u;
    public final Object j = new Object();
    public final Object k = new Object();
    public final ArrayList o = new ArrayList();
    public int q = 1;
    public ConnectionResult v = null;
    public boolean w = false;
    public volatile ConnectionInfo x = null;
    public AtomicInteger y = new AtomicInteger(0);

    public BaseGmsClient(Context context, Looper looper, AbstractC0435Ye abstractC0435Ye, AbstractC1278oc abstractC1278oc, int i, InterfaceC0148Ie interfaceC0148Ie, InterfaceC0166Je interfaceC0166Je, String str) {
        Cif.g(context, "Context must not be null");
        this.g = context;
        Cif.g(looper, "Looper must not be null");
        Cif.g(abstractC0435Ye, "Supervisor must not be null");
        this.h = abstractC0435Ye;
        Cif.g(abstractC1278oc, "API availability must not be null");
        this.i = new HandlerC0237Ne(this, looper);
        this.t = i;
        this.r = interfaceC0148Ie;
        this.s = interfaceC0166Je;
        this.u = str;
    }

    public static boolean r(BaseGmsClient baseGmsClient, int i, int i2, IInterface iInterface) {
        boolean z2;
        synchronized (baseGmsClient.j) {
            if (baseGmsClient.q != i) {
                z2 = false;
            } else {
                baseGmsClient.q(i2, iInterface);
                z2 = true;
            }
        }
        return z2;
    }

    public static boolean s(BaseGmsClient baseGmsClient) {
        if (baseGmsClient.w || TextUtils.isEmpty(baseGmsClient.j()) || TextUtils.isEmpty(null)) {
            return false;
        }
        try {
            Class.forName(baseGmsClient.j());
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public boolean a() {
        boolean z2;
        synchronized (this.j) {
            z2 = this.q == 4;
        }
        return z2;
    }

    public Bundle b() {
        return null;
    }

    public void c(InterfaceC0184Ke interfaceC0184Ke) {
        Cif.g(interfaceC0184Ke, "Connection progress callbacks cannot be null.");
        this.m = interfaceC0184Ke;
        q(2, null);
    }

    public abstract IInterface d(IBinder iBinder);

    public void disconnect() {
        this.y.incrementAndGet();
        synchronized (this.o) {
            int size = this.o.size();
            for (int i = 0; i < size; i++) {
                AbstractC0255Oe abstractC0255Oe = (AbstractC0255Oe) this.o.get(i);
                synchronized (abstractC0255Oe) {
                    abstractC0255Oe.a = null;
                }
            }
            this.o.clear();
        }
        synchronized (this.k) {
            this.l = null;
        }
        q(1, null);
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int i;
        IInterface iInterface;
        InterfaceC0781ff interfaceC0781ff;
        synchronized (this.j) {
            i = this.q;
            iInterface = this.n;
        }
        synchronized (this.k) {
            interfaceC0781ff = this.l;
        }
        printWriter.append((CharSequence) str).append("mConnectState=");
        if (i == 1) {
            printWriter.print("DISCONNECTED");
        } else if (i == 2) {
            printWriter.print("REMOTE_CONNECTING");
        } else if (i == 3) {
            printWriter.print("LOCAL_CONNECTING");
        } else if (i == 4) {
            printWriter.print("CONNECTED");
        } else if (i != 5) {
            printWriter.print("UNKNOWN");
        } else {
            printWriter.print("DISCONNECTING");
        }
        printWriter.append(" mService=");
        if (iInterface == null) {
            printWriter.append("null");
        } else {
            printWriter.append((CharSequence) j()).append("@").append((CharSequence) Integer.toHexString(System.identityHashCode(iInterface.asBinder())));
        }
        printWriter.append(" mServiceBroker=");
        if (interfaceC0781ff == null) {
            printWriter.println("null");
        } else {
            printWriter.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(interfaceC0781ff.asBinder())));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (this.c > 0) {
            PrintWriter printWriterAppend = printWriter.append((CharSequence) str).append("lastConnectedTime=");
            long j = this.c;
            String str2 = simpleDateFormat.format(new Date(this.c));
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 21);
            sb.append(j);
            sb.append(" ");
            sb.append(str2);
            printWriterAppend.println(sb.toString());
        }
        if (this.b > 0) {
            printWriter.append((CharSequence) str).append("lastSuspendedCause=");
            int i2 = this.a;
            if (i2 == 1) {
                printWriter.append("CAUSE_SERVICE_DISCONNECTED");
            } else if (i2 != 2) {
                printWriter.append((CharSequence) String.valueOf(i2));
            } else {
                printWriter.append("CAUSE_NETWORK_LOST");
            }
            PrintWriter printWriterAppend2 = printWriter.append(" lastSuspendedTime=");
            long j2 = this.b;
            String str3 = simpleDateFormat.format(new Date(this.b));
            StringBuilder sb2 = new StringBuilder(String.valueOf(str3).length() + 21);
            sb2.append(j2);
            sb2.append(" ");
            sb2.append(str3);
            printWriterAppend2.println(sb2.toString());
        }
        if (this.e > 0) {
            printWriter.append((CharSequence) str).append("lastFailedStatus=").append((CharSequence) AbstractC0056Dc.a(this.d));
            PrintWriter printWriterAppend3 = printWriter.append(" lastFailedTime=");
            long j3 = this.e;
            String str4 = simpleDateFormat.format(new Date(this.e));
            StringBuilder sb3 = new StringBuilder(String.valueOf(str4).length() + 21);
            sb3.append(j3);
            sb3.append(" ");
            sb3.append(str4);
            printWriterAppend3.println(sb3.toString());
        }
    }

    public Feature[] e() {
        return z;
    }

    public String f() {
        C0077Ef c0077Ef;
        if (!a() || (c0077Ef = this.f) == null) {
            throw new RuntimeException("Failed to connect when checking package");
        }
        return c0077Ef.b;
    }

    public Bundle g() {
        return new Bundle();
    }

    public int getMinApkVersion() {
        return AbstractC1278oc.a;
    }

    public void h(InterfaceC0502af interfaceC0502af, Set set) {
        Bundle bundleG = g();
        GetServiceRequest getServiceRequest = new GetServiceRequest(this.t);
        getServiceRequest.v = this.g.getPackageName();
        getServiceRequest.y = bundleG;
        if (set != null) {
            getServiceRequest.x = (Scope[]) set.toArray(new Scope[set.size()]);
        }
        if (requiresSignIn()) {
            Account account = ((AbstractC0399We) this).B;
            if (account == null) {
                account = new Account("<<default account>>", "com.google");
            }
            getServiceRequest.z = account;
            if (interfaceC0502af != null) {
                getServiceRequest.w = interfaceC0502af.asBinder();
            }
        }
        getServiceRequest.A = new Feature[0];
        getServiceRequest.B = e();
        try {
            synchronized (this.k) {
                if (this.l != null) {
                    ((C0725ef) this.l).c(new BinderC0291Qe(this, this.y.get()), getServiceRequest);
                } else {
                    Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                }
            }
        } catch (DeadObjectException e) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            Handler handler = this.i;
            handler.sendMessage(handler.obtainMessage(6, this.y.get(), 1));
        } catch (RemoteException e2) {
            e = e2;
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            int i = this.y.get();
            Handler handler2 = this.i;
            handler2.sendMessage(handler2.obtainMessage(1, i, -1, new C0309Re(this, 8, null, null)));
        } catch (SecurityException e3) {
            throw e3;
        } catch (RuntimeException e4) {
            e = e4;
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            int i2 = this.y.get();
            Handler handler22 = this.i;
            handler22.sendMessage(handler22.obtainMessage(1, i2, -1, new C0309Re(this, 8, null, null)));
        }
    }

    public final IInterface i() {
        IInterface iInterface;
        synchronized (this.j) {
            if (this.q == 5) {
                throw new DeadObjectException();
            }
            if (!a()) {
                throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
            }
            Cif.i(this.n != null, "Client is connected but service is null");
            iInterface = this.n;
        }
        return iInterface;
    }

    public abstract String j();

    public abstract String k();

    public boolean l() {
        boolean z2;
        synchronized (this.j) {
            z2 = this.q == 2 || this.q == 3;
        }
        return z2;
    }

    public void m() {
        this.c = System.currentTimeMillis();
    }

    public void n(ConnectionResult connectionResult) {
        this.d = connectionResult.t;
        this.e = System.currentTimeMillis();
    }

    public void o() {
    }

    public final void p(int i, int i2) {
        Handler handler = this.i;
        handler.sendMessage(handler.obtainMessage(7, i2, -1, new C0327Se(this, i)));
    }

    public final void q(int i, IInterface iInterface) {
        Cif.a((i == 4) == (iInterface != null));
        synchronized (this.j) {
            this.q = i;
            this.n = iInterface;
            o();
            if (i != 1) {
                if (i == 2 || i == 3) {
                    if (this.p != null && this.f != null) {
                        String str = this.f.a;
                        Objects.requireNonNull(this.f);
                        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 70 + "com.google.android.gms".length());
                        sb.append("Calling connect() while still connected, missing disconnect() for ");
                        sb.append(str);
                        sb.append(" on ");
                        sb.append("com.google.android.gms");
                        Log.e("GmsClient", sb.toString());
                        AbstractC0435Ye abstractC0435Ye = this.h;
                        String str2 = this.f.a;
                        Objects.requireNonNull(this.f);
                        Objects.requireNonNull(this.f);
                        ServiceConnectionC0273Pe serviceConnectionC0273Pe = this.p;
                        String strT = t();
                        Objects.requireNonNull(this.f);
                        abstractC0435Ye.c(str2, "com.google.android.gms", 129, serviceConnectionC0273Pe, strT, false);
                        this.y.incrementAndGet();
                    }
                    this.p = new ServiceConnectionC0273Pe(this, this.y.get());
                    String strK = k();
                    AbstractC0435Ye.b();
                    C0077Ef c0077Ef = new C0077Ef("com.google.android.gms", strK, false, 129, false);
                    this.f = c0077Ef;
                    AbstractC0435Ye abstractC0435Ye2 = this.h;
                    String str3 = c0077Ef.a;
                    Objects.requireNonNull(c0077Ef);
                    Objects.requireNonNull(this.f);
                    ServiceConnectionC0273Pe serviceConnectionC0273Pe2 = this.p;
                    String strT2 = t();
                    Objects.requireNonNull(this.f);
                    if (!abstractC0435Ye2.a(str3, "com.google.android.gms", 129, serviceConnectionC0273Pe2, strT2, false)) {
                        String str4 = this.f.a;
                        Objects.requireNonNull(this.f);
                        StringBuilder sb2 = new StringBuilder(String.valueOf(str4).length() + 34 + "com.google.android.gms".length());
                        sb2.append("unable to connect to service: ");
                        sb2.append(str4);
                        sb2.append(" on ");
                        sb2.append("com.google.android.gms");
                        Log.e("GmsClient", sb2.toString());
                        p(16, this.y.get());
                    }
                } else if (i == 4) {
                    m();
                }
            } else if (this.p != null) {
                AbstractC0435Ye abstractC0435Ye3 = this.h;
                String str5 = this.f.a;
                Objects.requireNonNull(this.f);
                Objects.requireNonNull(this.f);
                ServiceConnectionC0273Pe serviceConnectionC0273Pe3 = this.p;
                String strT3 = t();
                Objects.requireNonNull(this.f);
                abstractC0435Ye3.c(str5, "com.google.android.gms", 129, serviceConnectionC0273Pe3, strT3, false);
                this.p = null;
            }
        }
    }

    public boolean requiresSignIn() {
        return false;
    }

    public final String t() {
        String str = this.u;
        return str == null ? this.g.getClass().getName() : str;
    }

    public final void u() {
        boolean z2;
        int i;
        synchronized (this.j) {
            z2 = this.q == 3;
        }
        if (z2) {
            i = 5;
            this.w = true;
        } else {
            i = 4;
        }
        Handler handler = this.i;
        handler.sendMessage(handler.obtainMessage(i, this.y.get(), 16));
    }
}
