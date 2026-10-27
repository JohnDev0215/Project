package b.q;

import android.view.View;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: TransitionValues.java */
/* JADX INFO: loaded from: classes.dex */
public class r {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public View f1513b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, Object> f1512a = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList<l> f1514c = new ArrayList<>();

    @Deprecated
    public r() {
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        return this.f1513b == rVar.f1513b && this.f1512a.equals(rVar.f1512a);
    }

    public int hashCode() {
        return (this.f1513b.hashCode() * 31) + this.f1512a.hashCode();
    }

    public String toString() {
        String str = (("TransitionValues@" + Integer.toHexString(hashCode()) + ":\n") + "    view = " + this.f1513b + BydAdbHelper.COMMAND_LINE_END) + "    values:";
        for (String str2 : this.f1512a.keySet()) {
            str = str + "    " + str2 + ": " + this.f1512a.get(str2) + BydAdbHelper.COMMAND_LINE_END;
        }
        return str;
    }

    public r(View view) {
        this.f1513b = view;
    }
}
