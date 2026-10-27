package j2;

import com.alibaba.fastjson.JSONObject;
import g2.t;
import h2.d1;
import h2.i0;
import h2.t0;
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
    public static final a f5576a = new a();

    @Override // g2.t
    public <T> T b(f2.a aVar, Type type, Object obj) {
        JSONObject jSONObjectC0 = aVar.c0();
        Object obj2 = jSONObjectC0.get("currency");
        String string = obj2 instanceof JSONObject ? ((JSONObject) obj2).getString("currencyCode") : obj2 instanceof String ? (String) obj2 : null;
        Object obj3 = jSONObjectC0.get("numberStripped");
        if ((obj3 instanceof BigDecimal) || (obj3 instanceof Integer) || (obj3 instanceof BigInteger)) {
            return (T) Money.of((Number) obj3, Monetary.getCurrency(string, new String[0]));
        }
        throw new UnsupportedOperationException();
    }

    @Override // h2.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
        Money money = (Money) obj;
        if (money == null) {
            i0Var.J();
            return;
        }
        d1 d1Var = i0Var.f5241k;
        d1Var.O('{', "numberStripped", money.getNumberStripped());
        d1Var.N(',', "currency", money.getCurrency().getCurrencyCode());
        d1Var.write(125);
    }

    @Override // g2.t
    public int e() {
        return 0;
    }
}
