package b.a.a.k;

import com.alibaba.fastjson.JSONException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: EnumSerializer.java */
/* JADX INFO: loaded from: classes.dex */
public class y implements t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final y f410a = new y();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Member f411b;

    public y() {
        this.f411b = null;
    }

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        Member member = this.f411b;
        if (member == null) {
            i0Var.k.t((Enum) obj);
            return;
        }
        try {
            i0Var.H(member instanceof Field ? ((Field) member).get(obj) : ((Method) member).invoke(obj, new Object[0]));
        } catch (Exception e2) {
            throw new JSONException("getEnumValue error", e2);
        }
    }

    public y(Member member) {
        this.f411b = member;
    }
}
