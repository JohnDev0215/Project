package y0;

import android.view.View;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: TransitionValues.java */
/* JADX INFO: loaded from: classes.dex */
public class r {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public View f5981b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, Object> f5980a = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList<l> f5982c = new ArrayList<>();

    @Deprecated
    public r() {
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        return this.f5981b == rVar.f5981b && this.f5980a.equals(rVar.f5980a);
    }

    public int hashCode() {
        return (this.f5981b.hashCode() * 31) + this.f5980a.hashCode();
    }

    public String toString() {
        String str = (("TransitionValues@" + Integer.toHexString(hashCode()) + ":\n") + "    view = " + this.f5981b + BydAdbHelper.COMMAND_LINE_END) + "    values:";
        for (String str2 : this.f5980a.keySet()) {
            str = str + "    " + str2 + ": " + this.f5980a.get(str2) + BydAdbHelper.COMMAND_LINE_END;
        }
        return str;
    }

    public r(View view) {
        this.f5981b = view;
    }
}
