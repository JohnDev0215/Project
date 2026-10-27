package p;

import android.animation.Animator;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import android.provider.Settings;
import android.util.Log;
import android.view.animation.Animation;
import com.google.firebase.installations.local.PersistedInstallation$RegistrationStatus;
import com.google.gson.JsonIOException;
import com.google.gson.stream.JsonToken;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class g90 implements h00 {
    public final /* synthetic */ int c;
    public Object e;
    public Object k;

    public /* synthetic */ g90(Object obj, int i, Object obj2) {
        this.c = i;
        this.e = obj;
        this.k = obj2;
    }

    public final void a(Object obj, String str) {
        ((List) this.e).add(str + "=" + String.valueOf(obj));
    }

    public final hh b() {
        String strConcat = ((String) this.e) == null ? " filename" : "";
        if (((byte[]) this.k) == null) {
            strConcat = strConcat.concat(" contents");
        }
        if (strConcat.isEmpty()) {
            return new hh((String) this.e, (byte[]) this.k);
        }
        throw new IllegalStateException("Missing required properties:".concat(strConcat));
    }

    public final boolean c(rx2 rx2Var) {
        boolean zContainsKey;
        synchronized (this.e) {
            zContainsKey = ((Map) this.k).containsKey(rx2Var);
        }
        return zContainsKey;
    }

    @Override // p.h00
    public final Object convert(Object obj) {
        Charset charsetA;
        ResponseBody responseBody = (ResponseBody) obj;
        com.google.gson.a aVar = (com.google.gson.a) this.e;
        ResponseBody.BomAwareReader bomAwareReader = responseBody.c;
        if (bomAwareReader == null) {
            ko koVarI = responseBody.i();
            MediaType mediaTypeC = responseBody.c();
            if (mediaTypeC == null || (charsetA = mediaTypeC.a(cs.a)) == null) {
                charsetA = cs.a;
            }
            bomAwareReader = new ResponseBody.BomAwareReader(koVarI, charsetA);
            responseBody.c = bomAwareReader;
        }
        aVar.getClass();
        t71 t71Var = new t71(bomAwareReader);
        t71Var.e = aVar.i;
        try {
            Object objB = ((com.google.gson.b) this.k).b(t71Var);
            if (t71Var.K0() == JsonToken.r) {
                return objB;
            }
            throw new JsonIOException("JSON document was not fully consumed.");
        } finally {
            responseBody.close();
        }
    }

    public final void d() {
        try {
            gu0 gu0Var = (gu0) this.k;
            String str = (String) this.e;
            gu0Var.getClass();
            new File((File) gu0Var.b, str).createNewFile();
        } catch (IOException e) {
            Log.e("FirebaseCrashlytics", "Error creating marker: " + ((String) this.e), e);
        }
    }

    public final String e() {
        Object obj = this.k;
        if (((String) obj) != null) {
            return (String) obj;
        }
        String string = Settings.Secure.getString(((Context) this.e).getContentResolver(), "android_id");
        this.k = string;
        return string;
    }

    public final File f() {
        if (((File) this.e) == null) {
            synchronized (this) {
                try {
                    if (((File) this.e) == null) {
                        ru0 ru0Var = (ru0) this.k;
                        ru0Var.a();
                        this.e = new File(ru0Var.a.getFilesDir(), "PersistedInstallation." + ((ru0) this.k).c() + ".json");
                    }
                } finally {
                }
            }
        }
        return (File) this.e;
    }

    public final ArrayList g(String str) {
        m52 m52VarI = m52.i(1, "SELECT work_spec_id FROM dependency WHERE prerequisite_id=?");
        if (str == null) {
            m52VarI.K(1);
        } else {
            m52VarI.x(1, str);
        }
        ((i52) this.e).b();
        Cursor cursorO = p02.o((i52) this.e, m52VarI);
        try {
            ArrayList arrayList = new ArrayList(cursorO.getCount());
            while (cursorO.moveToNext()) {
                arrayList.add(cursorO.isNull(0) ? null : cursorO.getString(0));
            }
            return arrayList;
        } finally {
            cursorO.close();
            m52VarI.q();
        }
    }

    public final boolean h(String str) {
        m52 m52VarI = m52.i(1, "SELECT COUNT(*)=0 FROM dependency WHERE work_spec_id=? AND prerequisite_id IN (SELECT id FROM workspec WHERE state!=2)");
        if (str == null) {
            m52VarI.K(1);
        } else {
            m52VarI.x(1, str);
        }
        ((i52) this.e).b();
        Cursor cursorO = p02.o((i52) this.e, m52VarI);
        try {
            boolean z = false;
            if (cursorO.moveToFirst()) {
                z = cursorO.getInt(0) != 0;
            }
            return z;
        } finally {
            cursorO.close();
            m52VarI.q();
        }
    }

    public final void i(ki kiVar) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("Fid", kiVar.a);
            jSONObject.put("Status", kiVar.b.ordinal());
            jSONObject.put("AuthToken", kiVar.c);
            jSONObject.put("RefreshToken", kiVar.d);
            jSONObject.put("TokenCreationEpochInSecs", kiVar.f);
            jSONObject.put("ExpiresInSecs", kiVar.e);
            jSONObject.put("FisError", kiVar.g);
            ru0 ru0Var = (ru0) this.k;
            ru0Var.a();
            File fileCreateTempFile = File.createTempFile("PersistedInstallation", "tmp", ru0Var.a.getFilesDir());
            FileOutputStream fileOutputStream = new FileOutputStream(fileCreateTempFile);
            fileOutputStream.write(jSONObject.toString().getBytes("UTF-8"));
            fileOutputStream.close();
            if (fileCreateTempFile.renameTo(f())) {
            } else {
                throw new IOException("unable to rename the tmpfile to PersistedInstallation");
            }
        } catch (IOException | JSONException unused) {
        }
    }

    public final ki j() {
        JSONObject jSONObject;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[16384];
        try {
            FileInputStream fileInputStream = new FileInputStream(f());
            while (true) {
                try {
                    int i = fileInputStream.read(bArr, 0, 16384);
                    if (i < 0) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, i);
                } catch (Throwable th) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            }
            jSONObject = new JSONObject(byteArrayOutputStream.toString());
            fileInputStream.close();
        } catch (IOException | JSONException unused) {
            jSONObject = new JSONObject();
        }
        String strOptString = jSONObject.optString("Fid", null);
        int iOptInt = jSONObject.optInt("Status", 0);
        String strOptString2 = jSONObject.optString("AuthToken", null);
        String strOptString3 = jSONObject.optString("RefreshToken", null);
        long jOptLong = jSONObject.optLong("TokenCreationEpochInSecs", 0L);
        long jOptLong2 = jSONObject.optLong("ExpiresInSecs", 0L);
        String strOptString4 = jSONObject.optString("FisError", null);
        int i2 = ki.h;
        jm2 jm2Var = new jm2(6);
        jm2Var.f = 0L;
        jm2Var.j(PersistedInstallation$RegistrationStatus.c);
        jm2Var.e = 0L;
        jm2Var.a = strOptString;
        jm2Var.j(PersistedInstallation$RegistrationStatus.values()[iOptInt]);
        jm2Var.c = strOptString2;
        jm2Var.d = strOptString3;
        jm2Var.f = Long.valueOf(jOptLong);
        jm2Var.e = Long.valueOf(jOptLong2);
        jm2Var.g = strOptString4;
        return jm2Var.h();
    }

    public final List k(String str) {
        List listN0;
        v03.j(str, "workSpecId");
        synchronized (this.e) {
            try {
                Map map = (Map) this.k;
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (Map.Entry entry : map.entrySet()) {
                    if (v03.b(((rx2) entry.getKey()).a, str)) {
                        linkedHashMap.put(entry.getKey(), entry.getValue());
                    }
                }
                Iterator it = linkedHashMap.keySet().iterator();
                while (it.hasNext()) {
                    ((Map) this.k).remove((rx2) it.next());
                }
                listN0 = ou.n0(linkedHashMap.values());
            } catch (Throwable th) {
                throw th;
            }
        }
        return listN0;
    }

    public final xf2 l(rx2 rx2Var) {
        xf2 xf2Var;
        v03.j(rx2Var, "id");
        synchronized (this.e) {
            xf2Var = (xf2) ((Map) this.k).remove(rx2Var);
        }
        return xf2Var;
    }

    public final xf2 m(rx2 rx2Var) {
        xf2 xf2Var;
        synchronized (this.e) {
            try {
                Map map = (Map) this.k;
                Object xf2Var2 = map.get(rx2Var);
                if (xf2Var2 == null) {
                    xf2Var2 = new xf2(rx2Var);
                    map.put(rx2Var, xf2Var2);
                }
                xf2Var = (xf2) xf2Var2;
            } catch (Throwable th) {
                throw th;
            }
        }
        return xf2Var;
    }

    public final String toString() {
        switch (this.c) {
            case 6:
                StringBuilder sb = new StringBuilder(100);
                sb.append(this.k.getClass().getSimpleName());
                sb.append('{');
                int size = ((List) this.e).size();
                for (int i = 0; i < size; i++) {
                    sb.append((String) ((List) this.e).get(i));
                    if (i < size - 1) {
                        sb.append(", ");
                    }
                }
                sb.append('}');
                return sb.toString();
            default:
                return super.toString();
        }
    }

    public /* synthetic */ g90(Object obj) {
        this.c = 6;
        he1.g(obj);
        this.k = obj;
        this.e = new ArrayList();
    }

    public g90(i52 i52Var) {
        this.c = 0;
        this.e = i52Var;
        this.k = new f90(this, i52Var, 0);
    }

    public g90(l01 l01Var) {
        this.c = 11;
        this.e = l01Var;
        ArrayList arrayList = new ArrayList();
        this.k = arrayList;
        arrayList.add(new m01(l01Var, new int[]{1}));
    }

    public g90(int i) {
        this.c = i;
        if (i != 5 && i != 9) {
            this.e = new Object();
            this.k = new LinkedHashMap();
        }
    }

    public g90(Context context, int i) {
        this.c = i;
        if (i == 12) {
            this.e = context;
        } else {
            this.e = context;
            this.k = null;
        }
    }

    public g90(gj gjVar) {
        this.c = 14;
        this.k = gjVar;
        hp2 hp2VarB = gjVar.b.b();
        hp2VarB.i.add(new jp2("back_arrow", null, null, null, null));
        hp2VarB.j = false;
        this.e = hp2VarB.a();
    }

    public g90(ru0 ru0Var) {
        this.c = 10;
        this.k = ru0Var;
    }

    public g90(zi ziVar) {
        this.c = 13;
        this.k = ziVar;
        hp2 hp2VarB = ziVar.b.b();
        hp2VarB.i.add(new jp2("login", null, null, null, null));
        hp2VarB.j = false;
        this.e = hp2VarB.a();
    }

    public g90(Animation animation) {
        this.c = 2;
        this.e = animation;
        this.k = null;
    }

    public g90(Animator animator) {
        this.c = 2;
        this.e = null;
        this.k = animator;
    }

    public g90(IBinder iBinder, Bundle bundle) {
        this.c = 1;
        this.e = new Messenger(iBinder);
        this.k = bundle;
    }
}
