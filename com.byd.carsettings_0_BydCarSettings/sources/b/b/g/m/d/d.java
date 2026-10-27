package b.b.g.m.d;

import android.content.Context;
import b.b.g.m.d.c;
import com.byd.carsettings.model.dilink.PermissionType;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: BydPermissionInfo.java */
/* JADX INFO: loaded from: classes2.dex */
public class d<T extends c> implements Comparable<d> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f905a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f906b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public PermissionType f907c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public List<T> f908d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f909e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f910f;
    public int g;

    public d() {
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(d dVar) {
        if (dVar == null) {
            return 0;
        }
        int i = dVar.g;
        int i2 = this.g;
        if (i > i2) {
            return 1;
        }
        return i < i2 ? -1 : 0;
    }

    public List<T> b() {
        return this.f908d;
    }

    public int c(Context context) {
        List<T> list = this.f908d;
        int i = 0;
        if (list != null && list.size() > 0 && context != null) {
            Iterator<T> it = this.f908d.iterator();
            while (it.hasNext()) {
                if (it.next().f(context)) {
                    i++;
                }
            }
            this.f910f = i;
        }
        return i;
    }

    public String d() {
        return this.f905a;
    }

    public PermissionType e() {
        return this.f907c;
    }

    public boolean f() {
        return this.f909e;
    }

    public void g(List<T> list) {
        this.f908d = list;
    }

    public void h(boolean z) {
        this.f909e = z;
    }

    public String toString() {
        return "BydPermissionInfo{type=" + this.f907c + ", name='" + this.f905a + "', description='" + this.f906b + "', checked=" + this.f909e + ", appList=" + this.f908d + '}';
    }

    public d(PermissionType permissionType, String str) {
        this.f907c = permissionType;
        this.f905a = str;
    }
}
