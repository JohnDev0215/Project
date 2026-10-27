package b.b.g.m.d;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import com.byd.carsettings.model.dilink.PermissionType;
import com.bydux.api.common.tools.util.ListUtils;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: compiled from: BydPermissionGroup.java */
/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public PermissionType f900a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f901b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f902c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public List<String> f903d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List<String> f904e = Arrays.asList("com.telenav.scoutivi.spotify.byd", "com.stingray.karaoke.car.byd8155f", "com.byd.auto_photo", "com.byd.overseaappstore", "com.android.car.media", "com.byd.auto_emanual", "com.byd.browser");

    public c(PermissionType permissionType, String str, String str2, List<String> list) {
        this.f900a = permissionType;
        this.f901b = str;
        this.f902c = str2;
        this.f903d = list;
    }

    public int a(Context context) {
        List<String> list = this.f903d;
        if (list == null || list.size() <= 0 || context == null || this.f901b == null) {
            return 0;
        }
        PackageManager packageManager = context.getPackageManager();
        int i = 0;
        for (String str : this.f903d) {
            int iCheckPermission = packageManager.checkPermission(str, this.f901b);
            b.b.m.c.a.a("BydPermissionGroup", "---getEnableCount--value: " + iCheckPermission + ", type: " + this.f900a + ", p: " + str);
            if (iCheckPermission == 0) {
                i++;
            }
        }
        return i;
    }

    public String b() {
        return this.f902c;
    }

    public List<String> c() {
        return this.f903d;
    }

    public String d() {
        return this.f901b;
    }

    public PermissionType e() {
        return this.f900a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.f900a == ((c) obj).f900a;
    }

    @SuppressLint({"NewApi"})
    public boolean f(Context context) {
        int iA = a(context);
        return iA > 0 && iA == this.f903d.size();
    }

    public boolean g() {
        List<String> list = this.f903d;
        if (list == null || list.size() == 0) {
            return false;
        }
        if ("com.neusoft.na.navigation".equals(this.f901b) || "com.huawei.maps.auto.app".equals(this.f901b) || "com.byd.automap".equals(this.f901b) || "net.zmap.znav.byd8155".equals(this.f901b) || "com.mappls.auto.bydznav8155F".equals(this.f901b)) {
            PermissionType permissionType = this.f900a;
            return permissionType == PermissionType.PANORAMIC_CAMERA || permissionType == PermissionType.CAR_SETTINGS || permissionType == PermissionType.SPEED || permissionType == PermissionType.METER;
        }
        if ("com.byd.auto_photo".equals(this.f901b)) {
            return this.f900a == PermissionType.WRITE_SETTINGS;
        }
        if ("com.byd.auto_emanual".equals(this.f901b)) {
            return this.f900a == PermissionType.CAR_SETTINGS;
        }
        if (this.f904e.contains(this.f901b)) {
            return false;
        }
        Iterator<String> it = this.f903d.iterator();
        while (it.hasNext()) {
            if (it.next().contains("BYD")) {
                return true;
            }
            String str = this.f901b;
            if (str != null && str.contains("byd")) {
                return true;
            }
        }
        return false;
    }

    public boolean h(Context context) {
        List<String> list = this.f903d;
        if (list != null && list.size() > 0 && context != null && this.f901b != null) {
            Iterator<String> it = this.f903d.iterator();
            while (it.hasNext()) {
                if (b.b.g.p.h.q(context, this.f901b, it.next())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.f900a);
    }

    public String i(List<String> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                sb.append(list.get(i));
            } else {
                sb.append(list.get(i) + ListUtils.DEFAULT_JOIN_SEPARATOR);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public void j(String str) {
        this.f902c = str;
    }

    public String toString() {
        return "BydPermissionGroup{type=" + this.f900a + ", name='" + this.f902c + "', permissions=" + i(this.f903d) + '}';
    }
}
