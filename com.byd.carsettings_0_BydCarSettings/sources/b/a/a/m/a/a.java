package b.a.a.m.a;

import b.a.a.j.j.t;
import b.a.a.k.d1;
import b.a.a.k.i0;
import b.a.a.k.t0;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.money.Monetary;
import org.javamoney.moneta.Money;

/* JADX INFO: compiled from: MonetaCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements t0, t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f423a = new a();

    @Override // b.a.a.j.j.t
    public <T> T b(b.a.a.j.a aVar, Type type, Object obj) {
        JSONObject jSONObjectM = aVar.M();
        Object obj2 = jSONObjectM.get("currency");
        String string = obj2 instanceof JSONObject ? ((JSONObject) obj2).getString("currencyCode") : obj2 instanceof String ? (String) obj2 : null;
        Object obj3 = jSONObjectM.get("numberStripped");
        if ((obj3 instanceof BigDecimal) || (obj3 instanceof Integer) || (obj3 instanceof BigInteger)) {
            return (T) Money.of((Number) obj3, Monetary.getCurrency(string, new String[0]));
        }
        throw new UnsupportedOperationException();
    }

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        Money money = (Money) obj;
        if (money == null) {
            i0Var.J();
            return;
        }
        d1 d1Var = i0Var.k;
        d1Var.A('{', "numberStripped", money.getNumberStripped());
        d1Var.z(',', "currency", money.getCurrency().getCurrencyCode());
        d1Var.write(125);
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 0;
    }
}
