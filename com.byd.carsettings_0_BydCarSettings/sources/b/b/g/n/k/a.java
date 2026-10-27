package b.b.g.n.k;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.view.accessibility.AccessibilityManager;
import b.b.g.p.a;
import com.byd.carsettings.ui.fragment.dilink.AppManageFragment;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: AppManagePresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends b.b.m.e.g<AppManageFragment> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1097a = "a";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static ArrayList<String> f1098b = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b.b.g.p.a f1099c;

    public List<AccessibilityServiceInfo> c() {
        List<AccessibilityServiceInfo> installedAccessibilityServiceList = AccessibilityManager.getInstance(getV().getContext()).getInstalledAccessibilityServiceList();
        ArrayList arrayList = new ArrayList();
        for (AccessibilityServiceInfo accessibilityServiceInfo : installedAccessibilityServiceList) {
            if (accessibilityServiceInfo == null || accessibilityServiceInfo.getResolveInfo() == null || accessibilityServiceInfo.getResolveInfo().serviceInfo == null || accessibilityServiceInfo.getResolveInfo().serviceInfo.packageName == null) {
                b.b.m.c.a.g(f1097a, "(itemInfo.getResolveInfo() == null \n||itemInfo.getResolveInfo().serviceInfo == null \n || itemInfo.getResolveInfo().serviceInfo.packageName == null)");
            } else {
                String str = f1097a;
                b.b.m.c.a.d(str, "packageName:" + accessibilityServiceInfo.getResolveInfo().serviceInfo.packageName);
                if (e(accessibilityServiceInfo.getResolveInfo().serviceInfo.packageName)) {
                    b.b.m.c.a.d(str, accessibilityServiceInfo.getResolveInfo().serviceInfo.packageName + " isBlackListItem");
                } else {
                    arrayList.add(accessibilityServiceInfo);
                }
            }
        }
        return arrayList;
    }

    public void d() {
        b.b.g.p.a aVarA = a.b.a();
        this.f1099c = aVarA;
        aVarA.e(getV().getContext());
        f1098b.add("com.core.system.service");
    }

    public final boolean e(String str) {
        Iterator<String> it = f1098b.iterator();
        while (it.hasNext()) {
            if (it.next().equals(str)) {
                return true;
            }
        }
        return false;
    }
}
