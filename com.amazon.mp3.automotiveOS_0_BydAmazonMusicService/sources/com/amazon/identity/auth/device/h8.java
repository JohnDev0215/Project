package com.amazon.identity.auth.device;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.token.MAPCookie;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class h8 extends s7 {
    public static final String b = b8.class.getName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f362a;

    /* JADX INFO: compiled from: DCP */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f363a;
        public final /* synthetic */ String b;
        public final /* synthetic */ String c;
        public final /* synthetic */ List d;

        public a(String str, String str2, String str3, List list) {
            this.f363a = str;
            this.b = str2;
            this.c = str3;
            this.d = list;
        }

        @Override // java.lang.Runnable
        public void run() {
            h8.this.a(this.f363a, this.b, this.c, this.d);
        }
    }

    public h8(Context context) {
        this.f362a = context;
    }

    @Override // com.amazon.identity.auth.device.s7
    public List<MAPCookie> a(String str, String str2, String str3) {
        String string = (TextUtils.isEmpty(str) ? new f8(this.f362a, "com.amazon.identity.auth.device.cookiekeys.namespace.nonAuth", 0) : new f8(this.f362a, a(str), 0)).f313a.getString(str2, null);
        List<MAPCookie> arrayList = TextUtils.isEmpty(string) ? new ArrayList<>() : string.startsWith("[") ? v9.a(string, str) : v9.b(string, str);
        if (!TextUtils.isEmpty(string) && !string.startsWith("[")) {
            va.f637a.execute(new a(str, str2, str3, new ArrayList(arrayList)));
        }
        return arrayList;
    }

    @Override // com.amazon.identity.auth.device.s7
    public List<MAPCookie> a(String str, String str2, String str3, String str4) {
        Log.e(ga.a(b), "Getting actor cookies is not supported in pre-merge devices, returning null");
        return null;
    }

    @Override // com.amazon.identity.auth.device.s7
    public void a(String str, String str2, String str3, String str4, List<MAPCookie> list) {
        Log.e(ga.a(b), "Setting actor cookies is not supported in pre-merge devices, do nothing.");
    }

    @Override // com.amazon.identity.auth.device.s7
    public void a(String str, String str2, String str3, List<MAPCookie> list) {
        boolean zIsEmpty = TextUtils.isEmpty(str);
        String strB = v9.b(list);
        if (zIsEmpty) {
            new f8(this.f362a, "com.amazon.identity.auth.device.cookiekeys.namespace.nonAuth", 0).a(str2, strB);
        } else {
            new f8(this.f362a, a(str), 0).a(str2, strB);
        }
    }

    public static String a(String str) {
        return String.format("%s#%s", "com.amazon.identity.auth.device.cookiekeys.namespace", str);
    }

    @Override // com.amazon.identity.auth.device.s7
    public boolean a(Context context, String str) {
        return new f8(context, a(str), 0).a();
    }

    @Override // com.amazon.identity.auth.device.s7
    public Map<String, String> a(String str, List<MAPCookie> list, String str2, String str3) {
        if (list.isEmpty()) {
            ga.a(b);
            return Collections.emptyMap();
        }
        a(str, str2, str3, list);
        return Collections.emptyMap();
    }
}
