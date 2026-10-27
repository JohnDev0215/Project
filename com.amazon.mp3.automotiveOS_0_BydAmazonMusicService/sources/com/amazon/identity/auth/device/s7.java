package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.device.token.MAPCookie;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class s7 {
    public abstract List<MAPCookie> a(String str, String str2, String str3);

    public abstract List<MAPCookie> a(String str, String str2, String str3, String str4);

    public abstract Map<String, String> a(String str, List<MAPCookie> list, String str2, String str3);

    public abstract void a(String str, String str2, String str3, String str4, List<MAPCookie> list);

    public abstract void a(String str, String str2, String str3, List<MAPCookie> list);

    public abstract boolean a(Context context, String str);
}
