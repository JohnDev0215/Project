package a.b.a.a.l;

import com.google.firebase.perf.FirebasePerformance;
import com.netinsight.sye.syeClient.internal.IHTTPSessionWrapper;
import com.netinsight.sye.syeClient.internal.NativeHttpResultHandler;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class a implements IHTTPSessionWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f82a;

    /* JADX INFO: renamed from: a.b.a.a.l.a$a, reason: collision with other inner class name */
    public static final class AsyncTaskC0015a extends a.b.a.a.l.b {
        public final /* synthetic */ NativeHttpResultHandler g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AsyncTaskC0015a(a aVar, NativeHttpResultHandler nativeHttpResultHandler, int i) {
            super(null, i, 1);
            this.g = nativeHttpResultHandler;
        }

        @Override // a.b.a.a.l.b
        public void a(String str, int i) {
            NativeHttpResultHandler nativeHttpResultHandler = this.g;
            long j = nativeHttpResultHandler.f760a;
            if (str == null) {
                str = "";
            }
            nativeHttpResultHandler.onResult(j, str, i);
        }
    }

    public static final class b extends a.b.a.a.l.b {
        public final /* synthetic */ NativeHttpResultHandler g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(a aVar, NativeHttpResultHandler nativeHttpResultHandler, Map map, Map map2, int i) {
            super(map2, i);
            this.g = nativeHttpResultHandler;
        }

        @Override // a.b.a.a.l.b
        public void a(String str, int i) {
            NativeHttpResultHandler nativeHttpResultHandler = this.g;
            long j = nativeHttpResultHandler.f760a;
            if (str == null) {
                str = "";
            }
            nativeHttpResultHandler.onResult(j, str, i);
        }
    }

    public a(int i) {
        this.f82a = i;
    }

    @Override // com.netinsight.sye.syeClient.internal.IHTTPSessionWrapper
    public void doGET(String str, long j) {
        new AsyncTaskC0015a(this, new NativeHttpResultHandler(j), this.f82a).execute(FirebasePerformance.HttpMethod.GET, str);
    }

    @Override // com.netinsight.sye.syeClient.internal.IHTTPSessionWrapper
    public void doPOST(String str, String str2, String str3, long j) {
        NativeHttpResultHandler nativeHttpResultHandler = new NativeHttpResultHandler(j);
        try {
            JSONObject jSONObject = new JSONObject(str2);
            Iterator<String> itKeys = jSONObject.keys();
            HashMap map = new HashMap();
            while (itKeys.hasNext()) {
                String key = itKeys.next();
                String value = jSONObject.getString(key);
                Intrinsics.checkExpressionValueIsNotNull(key, "key");
                Intrinsics.checkExpressionValueIsNotNull(value, "value");
                map.put(key, value);
            }
            new b(this, nativeHttpResultHandler, map, map, this.f82a).execute(FirebasePerformance.HttpMethod.POST, str, str3);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
