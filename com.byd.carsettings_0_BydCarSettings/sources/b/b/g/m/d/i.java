package b.b.g.m.d;

import android.graphics.drawable.Drawable;
import com.byd.carsettings.model.dilink.PermissionType;
import java.util.List;

/* JADX INFO: compiled from: SubPermissionApp.java */
/* JADX INFO: loaded from: classes2.dex */
public class i extends c {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Drawable f931f;
    public String g;
    public boolean h;

    public i(PermissionType permissionType, String str, String str2, List<String> list) {
        super(permissionType, str, str2, list);
    }

    public String k() {
        return this.g;
    }

    public Drawable l() {
        return this.f931f;
    }

    public boolean m() {
        return this.h;
    }

    public void n(String str) {
        this.g = str;
    }

    public void o(Drawable drawable) {
        this.f931f = drawable;
    }

    public void p(boolean z) {
        this.h = z;
    }

    @Override // b.b.g.m.d.c
    public String toString() {
        return "SubPermissionApp{type=" + this.f900a + ", pkg='" + this.f901b + "', name='" + this.f902c + "', permissions=" + this.f903d + ", icon=" + this.f931f + ", appName='" + this.g + "', systemApp=" + this.h + '}';
    }
}
