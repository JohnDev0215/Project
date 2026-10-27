package b.m;

import android.view.View;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: TransitionValues.java */
/* JADX INFO: loaded from: classes.dex */
public class s {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public View f1911b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, Object> f1910a = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final ArrayList<m> f1912c = new ArrayList<>();

    @Deprecated
    public s() {
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof s)) {
            return false;
        }
        s sVar = (s) obj;
        return this.f1911b == sVar.f1911b && this.f1910a.equals(sVar.f1910a);
    }

    public int hashCode() {
        return (this.f1911b.hashCode() * 31) + this.f1910a.hashCode();
    }

    public String toString() {
        String str = (("TransitionValues@" + Integer.toHexString(hashCode()) + ":\n") + "    view = " + this.f1911b + BydAdbHelper.COMMAND_LINE_END) + "    values:";
        for (String str2 : this.f1910a.keySet()) {
            str = str + "    " + str2 + ": " + this.f1910a.get(str2) + BydAdbHelper.COMMAND_LINE_END;
        }
        return str;
    }

    public s(View view) {
        this.f1911b = view;
    }
}
