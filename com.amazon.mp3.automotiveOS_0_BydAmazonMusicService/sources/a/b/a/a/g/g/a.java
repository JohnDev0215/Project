package a.b.a.a.g.g;

import androidx.core.app.NotificationCompat;
import com.netinsight.sye.syeClient.closedCaptions.CCType;
import com.netinsight.sye.syeClient.generated.enums.cEA708.EdgeStyle;
import com.netinsight.sye.syeClient.generated.enums.cEA708.FontStyle;
import com.netinsight.sye.syeClient.generated.enums.cEA708.Justification;
import com.netinsight.sye.syeClient.generated.enums.cEA708.Opacity;
import java.util.ArrayList;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class a implements a.b.a.a.g.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList<C0010a> f61a;

    /* JADX INFO: renamed from: a.b.a.a.g.g.a$a, reason: collision with other inner class name */
    public static final class C0010a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f62a;
        public int b;
        public final long c;
        public final ArrayList<b> d;

        /* JADX INFO: renamed from: a.b.a.a.g.g.a$a$a, reason: collision with other inner class name */
        public static final class C0011a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f63a;
            public int b;
            public int c;
            public int d;

            public C0011a(JSONObject jSONObject) {
                this.f63a = jSONObject.getInt("w");
                this.b = jSONObject.getInt("h");
                this.c = jSONObject.getInt("x");
                this.d = jSONObject.getInt("y");
            }
        }

        /* JADX INFO: renamed from: a.b.a.a.g.g.a$a$b */
        public static final class b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public C0011a f64a;
            public int b;
            public EdgeStyle c;
            public int d;
            public int e;
            public int f;
            public Opacity g;
            public int h;
            public Justification i;
            public List<c> j = new ArrayList();

            public b(JSONObject jSONObject) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("area");
                Intrinsics.checkExpressionValueIsNotNull(jSONObject2, "obj.getJSONObject(\"area\")");
                this.f64a = new C0011a(jSONObject2);
                jSONObject.getInt("ax");
                jSONObject.getInt("ay");
                this.b = jSONObject.getInt("borderColor");
                this.c = EdgeStyle.INSTANCE.a(jSONObject.getInt("borderStyle"));
                this.d = jSONObject.getInt("ch");
                this.e = jSONObject.getInt("cw");
                this.f = jSONObject.getInt("fillColor");
                this.g = Opacity.INSTANCE.a(jSONObject.getInt("fillOpacity"));
                this.h = jSONObject.getInt("fontHeight");
                this.i = Justification.INSTANCE.a(jSONObject.getInt("justification"));
                JSONArray jSONArray = jSONObject.getJSONArray("rows");
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONArray jsonRow = jSONArray.getJSONArray(i);
                    Intrinsics.checkExpressionValueIsNotNull(jsonRow, "jsonRow");
                    this.j.add(new c(jsonRow));
                }
            }
        }

        /* JADX INFO: renamed from: a.b.a.a.g.g.a$a$c */
        public static final class c {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final ArrayList<d> f65a = new ArrayList<>();

            public c(JSONArray jSONArray) {
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject token = jSONArray.getJSONObject(i);
                    Intrinsics.checkExpressionValueIsNotNull(token, "token");
                    this.f65a.add(new d(token));
                }
            }
        }

        /* JADX INFO: renamed from: a.b.a.a.g.g.a$a$d */
        public static final class d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f66a;
            public Opacity b;
            public int c;
            public FontStyle d;
            public int e;
            public String f;
            public int g;
            public int h;
            public EdgeStyle i;
            public boolean j;
            public boolean k;

            public d(JSONObject jSONObject) {
                this.f66a = jSONObject.getInt("bgColor");
                Opacity.Companion companion = Opacity.INSTANCE;
                this.b = companion.a(jSONObject.getInt("bgOpacity"));
                this.c = jSONObject.getInt("end");
                this.d = FontStyle.INSTANCE.a(jSONObject.getInt("fontStyle"));
                this.e = jSONObject.getInt("pos");
                String string = jSONObject.getString("rowSubString");
                Intrinsics.checkExpressionValueIsNotNull(string, "obj.getString(\"rowSubString\")");
                this.f = string;
                this.g = jSONObject.getInt("textColor");
                this.h = jSONObject.getInt("textEdgeColor");
                this.i = EdgeStyle.INSTANCE.a(jSONObject.getInt("textEdgeStyle"));
                this.j = jSONObject.getBoolean("textItalics");
                companion.a(jSONObject.getInt("textOpacity"));
                this.k = jSONObject.getBoolean("textUnderline");
            }
        }

        public C0010a(JSONObject jSONObject) {
            a.b.a.a.i.b.d.a();
            this.d = new ArrayList<>();
            this.f62a = jSONObject.getInt("baseHeight");
            this.b = jSONObject.getInt("baseWidth");
            this.c = jSONObject.getLong("localDisplayTime");
            JSONObject jSONObject2 = jSONObject.getJSONObject("safeArea");
            Intrinsics.checkExpressionValueIsNotNull(jSONObject2, "obj.getJSONObject(\"safeArea\")");
            jSONObject2.getInt("w");
            jSONObject2.getInt("h");
            jSONObject2.getInt("x");
            jSONObject2.getInt("y");
            jSONObject.getInt(NotificationCompat.CATEGORY_SERVICE);
            JSONArray jSONArray = jSONObject.getJSONArray("windows");
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                Object obj = jSONArray.get(i);
                if (obj == null) {
                    throw new TypeCastException("null cannot be cast to non-null type org.json.JSONObject");
                }
                this.d.add(new b((JSONObject) obj));
            }
        }
    }

    public a(String str) {
        a.b.a.a.i.b.d.a();
        new ArrayList();
        this.f61a = new ArrayList<>();
        JSONArray jSONArray = new JSONArray(str);
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            Object obj = jSONArray.get(i);
            if (obj == null) {
                throw new TypeCastException("null cannot be cast to non-null type org.json.JSONObject");
            }
            this.f61a.add(new C0010a((JSONObject) obj));
        }
    }

    @Override // a.b.a.a.g.b
    public long a() {
        return this.f61a.get(0).c;
    }

    @Override // a.b.a.a.g.b
    public boolean b() {
        return this.f61a.get(0).d.isEmpty();
    }

    @Override // a.b.a.a.g.b
    public CCType getType() {
        return CCType.DTVCC;
    }
}
