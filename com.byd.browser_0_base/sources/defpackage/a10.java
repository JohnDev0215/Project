package defpackage;

import android.util.Log;
import com.google.protobuf.d0;
import defpackage.b10;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.chromium.chrome.browser.tab.Tab;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: CouponPersistedTabData.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a10 extends l02 {
    public static final String[] q = {"https://www.googleapis.com/auth/userinfo.email", "https://www.googleapis.com/auth/userinfo.profile"};
    public static final long r = TimeUnit.HOURS.toMillis(1);
    public static final yl1 s = new yl1("coupon_persisted_tab_data");
    public g20 n;
    public final aq1<Boolean> o;
    public a p;

    public a10(Tab tab) {
        super(tab, m02.a(a10.class, tab.isIncognito()).f(), m02.a(a10.class, tab.isIncognito()).e);
        aq1<Boolean> aq1Var = new aq1<>();
        this.o = aq1Var;
        aq1Var.r(Boolean.FALSE);
        this.i = aq1Var;
        jb1 jb1Var = new jb1(6, this);
        this.j = jb1Var;
        aq1Var.p(jb1Var);
        tab.x(new z00(this));
    }

    public static a o(String str) {
        String strOptString;
        long j;
        int i;
        try {
            JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("discounts");
            JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(0).optJSONObject("freeListingDiscountInfo");
            String strOptString2 = jSONObjectOptJSONObject.optString("longTitle");
            String strOptString3 = jSONObjectOptJSONObject.optString("couponCode");
            JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray.optJSONObject(0).optJSONObject("amountOff");
            if (jSONObjectOptJSONObject2 == null) {
                i = 1;
                j = Long.parseLong(jSONArrayOptJSONArray.optJSONObject(0).optString("percentOff"));
                strOptString = "%";
            } else {
                strOptString = jSONObjectOptJSONObject2.optString("currencyCode");
                j = Long.parseLong(jSONObjectOptJSONObject2.optString("units"));
                i = 2;
            }
            return new a(strOptString2, strOptString3, strOptString, j, i);
        } catch (NullPointerException e) {
            fa1.b("COPTD", "NullPointerException occurred while building coupon from JSON: %s", e.getMessage());
            return null;
        } catch (JSONException e2) {
            fa1.b("COPTD", "Error converting response to JSON: %s", e2.getMessage());
            return null;
        } catch (Exception e3) {
            fa1.g("COPTD", "Exception occurred while building COPTD: %s", e3.getMessage());
            return null;
        }
    }

    @Override // defpackage.l02
    public final boolean b(ByteBuffer byteBuffer) {
        int i;
        if (byteBuffer != null && byteBuffer.hasRemaining()) {
            try {
                b10 b10VarB0 = b10.b0(byteBuffer);
                String strZ = b10VarB0.Z();
                String strV = b10VarB0.V();
                String strW = b10VarB0.W();
                long jY = b10VarB0.Y();
                int iM = gq2.m(b10VarB0.X());
                if (iM != 0) {
                    i = iM != 1 ? 3 : 2;
                } else {
                    i = 1;
                }
                this.p = new a(strZ, strV, strW, jY, i);
                return true;
            } catch (d0 e) {
                Log.e("cr_COPTD", String.format(Locale.US, "There was a problem deserializing CouponPersistedTabData. Details: %s", e.getMessage()));
            }
        }
        return false;
    }

    @Override // defpackage.l02
    public final vi2<ByteBuffer> g() {
        b10.a aVarA0 = b10.a0();
        a aVar = this.p;
        if (aVar != null) {
            String str = aVar.b;
            if (str != null) {
                aVarA0.y();
                b10.O((b10) aVarA0.f, str);
            }
            String str2 = this.p.a;
            if (str2 != null) {
                aVarA0.y();
                b10.T((b10) aVarA0.f, str2);
            }
            String str3 = this.p.c;
            if (str3 != null) {
                aVarA0.y();
                b10.P((b10) aVarA0.f, str3);
            }
            int i = this.p.e;
            if (i != 0) {
                int iM = gq2.m(i);
                int i2 = iM != 0 ? iM != 1 ? 3 : 2 : 1;
                aVarA0.y();
                b10.Q((b10) aVarA0.f, i2);
            }
            long j = this.p.d;
            aVarA0.y();
            b10.S((b10) aVarA0.f, j);
        }
        return new ke0(20, aVarA0);
    }

    @Override // defpackage.l02
    public final long h() {
        return (int) r;
    }

    @Override // defpackage.l02
    public final String i() {
        return "COPTD";
    }

    public final a m() {
        a aVar = this.p;
        if (aVar == null) {
            return null;
        }
        if ((aVar.a == null || aVar.b == null) ? false : true) {
            return aVar;
        }
        return null;
    }

    public final String n() {
        String str;
        if (m() == null) {
            return null;
        }
        try {
            if (m().e == 1) {
                return m().d + "% Off";
            }
            if (m().e != 2 || (str = this.p.c) == null || str.isEmpty()) {
                return "Coupon Available";
            }
            this.n = new g20(this.p.c, Locale.getDefault());
            String str2 = n3.N(this.p.d * 1000000, this.n) + " Off";
            this.n.a();
            return str2;
        } catch (Exception e) {
            fa1.b("COPTD", "Returning default annotation text, error obtaining coupon annotation text: %s", e.getMessage());
            return "Coupon Available";
        }
    }

    /* JADX INFO: compiled from: CouponPersistedTabData.java */
    public static class a {
        public final String a;
        public final String b;
        public final String c;
        public final long d;
        public final int e;

        public a() {
            this.a = null;
            this.b = null;
            this.e = 0;
            this.d = -1L;
        }

        public a(String str, String str2, String str3, long j, int i) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = j;
            this.e = i;
        }
    }
}
