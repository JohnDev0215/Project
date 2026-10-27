package defpackage;

import android.app.Activity;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashSet;
import org.chromium.components.permissions.PermissionsClient;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: renamed from: hy, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0912hy implements RN {
    public final /* synthetic */ SparseArray a;
    public final /* synthetic */ WindowAndroid b;
    public final /* synthetic */ PermissionsClient c;
    public final /* synthetic */ int[] d;
    public final /* synthetic */ InterfaceC1023jy e;

    public C0912hy(SparseArray sparseArray, WindowAndroid windowAndroid, PermissionsClient permissionsClient, int[] iArr, InterfaceC1023jy interfaceC1023jy) {
        this.a = sparseArray;
        this.b = windowAndroid;
        this.c = permissionsClient;
        this.d = iArr;
        this.e = interfaceC1023jy;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.RN
    public void a(String[] strArr, int[] iArr) {
        int iKeyAt;
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        boolean z = true;
        while (true) {
            iKeyAt = -1;
            if (i >= iArr.length) {
                break;
            }
            if (iArr[i] == -1) {
                arrayList.add(strArr[i]);
                SparseArray sparseArray = this.a;
                String str = strArr[i];
                int i2 = 0;
                while (true) {
                    if (i2 >= sparseArray.size()) {
                        break;
                    }
                    for (String str2 : (String[]) sparseArray.valueAt(i2)) {
                        if (str.equals(str2)) {
                            iKeyAt = sparseArray.keyAt(i2);
                            break;
                        }
                    }
                    i2++;
                }
                hashSet.add(Integer.valueOf(iKeyAt));
                if (!this.b.canRequestPermission(strArr[i])) {
                    z = false;
                }
            }
            i++;
        }
        Activity activity = (Activity) this.b.Q().get();
        PermissionsClient permissionsClient = this.c;
        permissionsClient.a();
        if (!z || hashSet.isEmpty() || activity == 0) {
            if (hashSet.isEmpty()) {
                this.e.c();
                return;
            } else {
                this.e.a();
                return;
            }
        }
        if (hashSet.size() == 2 && hashSet.contains(9) && hashSet.contains(10)) {
            iKeyAt = AbstractC0566bn.E1;
        } else if (hashSet.size() == 1) {
            if (hashSet.contains(5)) {
                iKeyAt = AbstractC0566bn.D1;
            } else if (hashSet.contains(9)) {
                iKeyAt = AbstractC0566bn.F1;
            } else if (hashSet.contains(10) || hashSet.contains(58)) {
                iKeyAt = AbstractC0566bn.C1;
            }
        }
        final WindowAndroid windowAndroid = this.b;
        final int[] iArr2 = this.d;
        final InterfaceC1023jy interfaceC1023jy = this.e;
        final PermissionsClient permissionsClient2 = this.c;
        Runnable runnable = new Runnable(windowAndroid, iArr2, interfaceC1023jy, permissionsClient2) { // from class: fy
            public final WindowAndroid s;
            public final int[] t;
            public final InterfaceC1023jy u;
            public final PermissionsClient v;

            {
                this.s = windowAndroid;
                this.t = iArr2;
                this.u = interfaceC1023jy;
                this.v = permissionsClient2;
            }

            @Override // java.lang.Runnable
            public void run() {
                AbstractC1079ky.a(this.s, this.t, this.u, this.v);
            }
        };
        final InterfaceC1023jy interfaceC1023jy2 = this.e;
        interfaceC1023jy2.getClass();
        Runnable runnable2 = new Runnable(interfaceC1023jy2) { // from class: gy
            public final InterfaceC1023jy s;

            {
                this.s = interfaceC1023jy2;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.s.a();
            }
        };
        C1546tO c1546tOA = ((InterfaceC1601uO) activity).a();
        C0967iy c0967iy = new C0967iy(runnable, c1546tOA, runnable2);
        View viewInflate = activity.getLayoutInflater().inflate(AbstractC0425Xm.i1, (ViewGroup) null);
        ((TextView) viewInflate.findViewById(AbstractC0371Um.H4)).setText(iKeyAt);
        BO bo = new BO(AbstractC1711wO.q);
        bo.d(AbstractC1711wO.f, viewInflate);
        bo.b(AbstractC1711wO.m, true);
        bo.d(AbstractC1711wO.g, activity.getString(AbstractC0566bn.G1));
        bo.d(AbstractC1711wO.a, c0967iy);
        c1546tOA.e(bo.a(), 0, false);
    }
}
