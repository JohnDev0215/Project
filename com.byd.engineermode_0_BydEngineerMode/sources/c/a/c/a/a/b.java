package c.a.c.a.a;

import android.content.Context;
import android.content.Intent;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import android.os.Bundle;
import android.util.Log;
import c.a.c.a.a.a;
import java.util.HashMap;
import java.util.Set;

/* JADX INFO: compiled from: MediaControl.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2081a = Boolean.FALSE.toString();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f2082b = Boolean.TRUE.toString();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static volatile b f2083c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f2084d;

    /* JADX INFO: renamed from: c.a.c.a.a.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MediaControl.java */
    public interface InterfaceC0060b {
        void a(int i, int i2, e eVar);
    }

    /* JADX INFO: compiled from: MediaControl.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private HashMap<String, String> f2085a = new HashMap<>();

        public HashMap<String, String> a() {
            return this.f2085a;
        }

        public void b(String str, String str2) {
            this.f2085a.put(str, str2);
        }
    }

    /* JADX INFO: compiled from: MediaControl.java */
    private static class d extends a.AbstractBinderC0059a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private InterfaceC0060b f2086a;

        d(InterfaceC0060b interfaceC0060b) {
            this.f2086a = interfaceC0060b;
        }

        @Override // c.a.c.a.a.a
        public void b(int i, int i2, Bundle bundle) {
            InterfaceC0060b interfaceC0060b = this.f2086a;
            if (interfaceC0060b != null) {
                interfaceC0060b.a(i, i2, new e(bundle));
            }
        }
    }

    /* JADX INFO: compiled from: MediaControl.java */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Bundle f2087a;

        public Bundle a() {
            return new Bundle(this.f2087a);
        }

        public String toString() {
            return b.a("Result", this.f2087a);
        }

        private e(Bundle bundle) {
            Bundle bundle2 = new Bundle();
            this.f2087a = bundle2;
            bundle2.putAll(bundle);
        }
    }

    private b(Context context) {
        this.f2084d = null;
        this.f2084d = context;
        Log.d("MediaControl", "package: " + this.f2084d.getPackageName());
    }

    public static String a(String str, Bundle bundle) {
        if (bundle.size() == 0) {
            return str + "{null}";
        }
        Set<String> setKeySet = bundle.keySet();
        String[] strArr = (String[]) setKeySet.toArray(new String[setKeySet.size()]);
        StringBuilder sb = new StringBuilder(strArr.length * 28);
        sb.append(str);
        sb.append("{");
        for (int i = 0; i < strArr.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(strArr[i]);
            sb.append("=");
            sb.append(bundle.get(strArr[i]));
        }
        sb.append('}');
        return sb.toString();
    }

    public static void c(Context context, int i, int i2, c cVar, InterfaceC0060b interfaceC0060b) {
        d(context).b(i, i2, cVar, interfaceC0060b);
    }

    public static b d(Context context) {
        if (f2083c == null) {
            f2083c = new b(context.getApplicationContext());
        }
        return f2083c;
    }

    public void b(int i, int i2, c cVar, InterfaceC0060b interfaceC0060b) {
        Log.d("MediaControl", "controlMedia, mode is: " + i + " action is: " + i2);
        Intent intent = new Intent(BYDAutoMultimediaDevice.ACTION_START_MEDIA);
        intent.setPackage("com.byd.localmusic");
        intent.putExtra(BYDAutoMultimediaDevice.MEDIA_MODE, i);
        intent.putExtra(BYDAutoMultimediaDevice.MEDIA_ACTION, i2);
        if (cVar == null) {
            cVar = new c();
        }
        cVar.b("package", this.f2084d.getPackageName());
        intent.putExtra(BYDAutoMultimediaDevice.MEDIA_PARAM, cVar.a());
        if (interfaceC0060b != null) {
            Bundle bundle = new Bundle();
            bundle.putBinder("MEDIA_CONTROL_CALLBACK", new d(interfaceC0060b).asBinder());
            intent.putExtra("MEDIA_CONTROL_CALLBACK", bundle);
        }
        this.f2084d.startService(intent);
    }
}
