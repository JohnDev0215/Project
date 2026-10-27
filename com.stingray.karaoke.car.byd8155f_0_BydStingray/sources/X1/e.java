package X1;

import B5.RunnableC0079c;
import android.content.DialogInterface;
import android.util.Log;
import java.io.PrintWriter;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class e extends f implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final c f9973v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final boolean f9974w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f9975x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f9976y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f9977z;

    public e() {
        new RunnableC0079c(this, 6);
        new b(this);
        this.f9973v = new c(this);
        this.f9974w = true;
        this.f9975x = -1;
        new d(this);
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        String str;
        if (this.f9976y) {
            return;
        }
        if (Log.isLoggable("FragmentManager", 3)) {
            Log.d("FragmentManager", "onDismiss called for DialogFragment " + this);
        }
        if (this.f9977z) {
            return;
        }
        this.f9977z = true;
        this.f9976y = true;
        if (this.f9975x >= 0) {
            W2.j jVarC = c();
            int i7 = this.f9975x;
            if (i7 < 0) {
                throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.e(i7, "Bad id: "));
            }
            synchronized (((ArrayList) jVarC.f9751n)) {
            }
            this.f9975x = -1;
            return;
        }
        a aVar = new a(c());
        k kVar = new k();
        kVar.f9989a = 3;
        kVar.f9990b = this;
        ((ArrayList) aVar.d).add(kVar);
        kVar.c = 0;
        kVar.d = 0;
        kVar.f9991e = 0;
        kVar.f9992f = 0;
        if (aVar.f9971b) {
            throw new IllegalStateException("commit already called");
        }
        if (Log.isLoggable("FragmentManager", 2)) {
            Log.v("FragmentManager", "Commit: " + aVar);
            PrintWriter printWriter = new PrintWriter(new l());
            printWriter.print("  ");
            printWriter.print("mName=");
            printWriter.print((String) null);
            printWriter.print(" mIndex=");
            printWriter.print(aVar.c);
            printWriter.print(" mCommitted=");
            printWriter.println(aVar.f9971b);
            ArrayList arrayList = (ArrayList) aVar.d;
            if (!arrayList.isEmpty()) {
                printWriter.print("  ");
                printWriter.println("Operations:");
                int size = arrayList.size();
                for (int i10 = 0; i10 < size; i10++) {
                    k kVar2 = (k) arrayList.get(i10);
                    switch (kVar2.f9989a) {
                        case 0:
                            str = "NULL";
                            break;
                        case 1:
                            str = "ADD";
                            break;
                        case 2:
                            str = "REPLACE";
                            break;
                        case 3:
                            str = "REMOVE";
                            break;
                        case 4:
                            str = "HIDE";
                            break;
                        case 5:
                            str = "SHOW";
                            break;
                        case 6:
                            str = "DETACH";
                            break;
                        case 7:
                            str = "ATTACH";
                            break;
                        case 8:
                            str = "SET_PRIMARY_NAV";
                            break;
                        case 9:
                            str = "UNSET_PRIMARY_NAV";
                            break;
                        case 10:
                            str = "OP_SET_MAX_LIFECYCLE";
                            break;
                        default:
                            str = "cmd=" + kVar2.f9989a;
                            break;
                    }
                    printWriter.print("  ");
                    printWriter.print("  Op #");
                    printWriter.print(i10);
                    printWriter.print(": ");
                    printWriter.print(str);
                    printWriter.print(" ");
                    printWriter.println(kVar2.f9990b);
                    if (kVar2.c != 0 || kVar2.d != 0) {
                        printWriter.print("  ");
                        printWriter.print("enterAnim=#");
                        printWriter.print(Integer.toHexString(kVar2.c));
                        printWriter.print(" exitAnim=#");
                        printWriter.println(Integer.toHexString(kVar2.d));
                    }
                    if (kVar2.f9991e != 0 || kVar2.f9992f != 0) {
                        printWriter.print("  ");
                        printWriter.print("popEnterAnim=#");
                        printWriter.print(Integer.toHexString(kVar2.f9991e));
                        printWriter.print(" popExitAnim=#");
                        printWriter.println(Integer.toHexString(kVar2.f9992f));
                    }
                }
            }
            printWriter.close();
        }
        aVar.f9971b = true;
        W2.j jVar = (W2.j) aVar.f9972e;
        aVar.c = -1;
        synchronized (((ArrayList) jVar.f9751n)) {
        }
    }
}
