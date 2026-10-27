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
    public View f2770b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, Object> f2769a = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final ArrayList<m> f2771c = new ArrayList<>();

    @Deprecated
    public s() {
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof s)) {
            return false;
        }
        s sVar = (s) obj;
        return this.f2770b == sVar.f2770b && this.f2769a.equals(sVar.f2769a);
    }

    public int hashCode() {
        return (this.f2770b.hashCode() * 31) + this.f2769a.hashCode();
    }

    public String toString() {
        String str = (("TransitionValues@" + Integer.toHexString(hashCode()) + ":\n") + "    view = " + this.f2770b + BydAdbHelper.COMMAND_LINE_END) + "    values:";
        for (String str2 : this.f2769a.keySet()) {
            str = str + "    " + str2 + ": " + this.f2769a.get(str2) + BydAdbHelper.COMMAND_LINE_END;
        }
        return str;
    }

    public s(View view) {
        this.f2770b = view;
    }
}
