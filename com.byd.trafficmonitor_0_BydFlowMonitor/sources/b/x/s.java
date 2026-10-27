package b.x;

import android.view.View;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: TransitionValues.java */
/* JADX INFO: loaded from: classes.dex */
public class s {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public View f3316b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, Object> f3315a = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList<m> f3317c = new ArrayList<>();

    @Deprecated
    public s() {
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof s)) {
            return false;
        }
        s sVar = (s) obj;
        return this.f3316b == sVar.f3316b && this.f3315a.equals(sVar.f3315a);
    }

    public int hashCode() {
        return (this.f3316b.hashCode() * 31) + this.f3315a.hashCode();
    }

    public String toString() {
        String str = (("TransitionValues@" + Integer.toHexString(hashCode()) + ":\n") + "    view = " + this.f3316b + BydAdbHelper.COMMAND_LINE_END) + "    values:";
        for (String str2 : this.f3315a.keySet()) {
            str = str + "    " + str2 + ": " + this.f3315a.get(str2) + BydAdbHelper.COMMAND_LINE_END;
        }
        return str;
    }

    public s(View view) {
        this.f3316b = view;
    }
}
