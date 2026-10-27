package OooO00o.OooO00o.OooO0Oo.OooO0OO.c;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.loostone.libserver.version1.entity.PMConfig;
import com.loostone.libserver.version1.entity.response.ResponseData;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class a implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f89a = a.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Class<?> f90b;

    /* JADX INFO: renamed from: OooO00o.OooO00o.OooO0Oo.OooO0OO.c.a$a, reason: collision with other inner class name */
    public class C0005a extends TypeReference<ResponseData> {
        public C0005a(a aVar) {
        }
    }

    public a(Class<?> cls) {
        this.f90b = cls;
    }

    public ResponseData a(Object obj) {
        Exception e;
        ResponseData responseData;
        if (!(obj instanceof String)) {
            throw new IllegalArgumentException("the param must be instance of String");
        }
        ResponseData responseData2 = new ResponseData();
        try {
            responseData = (ResponseData) JSON.parseObject(OooO00o.OooO00o.OooO0Oo.OooO0OO.d.a.b(((String) obj).trim(), PMConfig.getToken()).trim(), new C0005a(this), new Feature[0]);
            try {
                if (responseData.getData() != null && (responseData.getData() instanceof JSONArray)) {
                    responseData.setData(JSON.parseArray(responseData.getData().toString(), this.f90b));
                } else if (responseData.getData() != null && (responseData.getData() instanceof JSONObject)) {
                    responseData.setData(JSON.parseObject(responseData.getData().toString(), this.f90b));
                }
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                OooO00o.OooO00o.OooO0O0.b.f41a.d(this.f89a, e.getMessage());
                responseData.setState("-000001");
                responseData.setMsg("decrypt json failed");
            }
        } catch (Exception e3) {
            e = e3;
            responseData = responseData2;
        }
        return responseData;
    }

    public Map<String, String> b() {
        HashMap map = new HashMap();
        map.put("Content-Type", "text/plain");
        return map;
    }

    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public String c(Object obj) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (field.getType() == String.class) {
                field.setAccessible(true);
                try {
                    if (field.get(obj) != null) {
                        field.set(obj, URLEncoder.encode(field.get(obj).toString()));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    OooO00o.OooO00o.OooO0O0.b.f41a.d(this.f89a, e.getMessage());
                }
            }
        }
        return OooO00o.OooO00o.OooO0Oo.OooO0OO.d.a.e(JSON.toJSONString(obj), PMConfig.getToken());
    }
}
