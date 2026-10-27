package o1;

import android.content.Context;
import android.content.Intent;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import android.os.Bundle;
import android.util.Log;
import com.bydux.api.common.tools.util.ListUtils;
import java.util.HashMap;
import java.util.Set;
import o1.a;

/* JADX INFO: compiled from: MediaControl.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f4450a = Boolean.FALSE.toString();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f4451b = Boolean.TRUE.toString();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static volatile b f4452c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Context f4453d;

    /* JADX INFO: renamed from: o1.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MediaControl.java */
    public interface InterfaceC0045b {
        void a(int i3, int i4, e eVar);
    }

    /* JADX INFO: compiled from: MediaControl.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public HashMap<String, String> f4454a = new HashMap<>();

        public HashMap<String, String> a() {
            return this.f4454a;
        }

        public void b(String str, int i3) {
            this.f4454a.put(str, String.valueOf(i3));
        }

        public void c(String str, String str2) {
            this.f4454a.put(str, str2);
        }
    }

    /* JADX INFO: compiled from: MediaControl.java */
    public static class d extends a.AbstractBinderC0044a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public InterfaceC0045b f4455a;

        public d(InterfaceC0045b interfaceC0045b) {
            this.f4455a = interfaceC0045b;
        }

        @Override // o1.a
        public void k(int i3, int i4, Bundle bundle) {
            InterfaceC0045b interfaceC0045b = this.f4455a;
            if (interfaceC0045b != null) {
                interfaceC0045b.a(i3, i4, new e(bundle));
            }
        }
    }

    /* JADX INFO: compiled from: MediaControl.java */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Bundle f4456a;

        public Bundle a() {
            return new Bundle(this.f4456a);
        }

        public String toString() {
            return b.a("Result", this.f4456a);
        }

        public e(Bundle bundle) {
            Bundle bundle2 = new Bundle();
            this.f4456a = bundle2;
            bundle2.putAll(bundle);
        }
    }

    public b(Context context) {
        this.f4453d = null;
        this.f4453d = context;
        Log.d("MediaControl", "package: " + this.f4453d.getPackageName());
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
        for (int i3 = 0; i3 < strArr.length; i3++) {
            if (i3 > 0) {
                sb.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
            }
            sb.append(strArr[i3]);
            sb.append("=");
            sb.append(bundle.get(strArr[i3]));
        }
        sb.append('}');
        return sb.toString();
    }

    public static void c(Context context, int i3, int i4, c cVar, InterfaceC0045b interfaceC0045b) {
        d(context).b(i3, i4, cVar, interfaceC0045b);
    }

    public static b d(Context context) {
        if (f4452c == null) {
            f4452c = new b(context.getApplicationContext());
        }
        return f4452c;
    }

    public void b(int i3, int i4, c cVar, InterfaceC0045b interfaceC0045b) {
        Log.d("MediaControl", "controlMedia, mode is: " + i3 + " action is: " + i4);
        Intent intent = new Intent(BYDAutoMultimediaDevice.ACTION_START_MEDIA);
        intent.setPackage("com.byd.localmusic");
        intent.putExtra(BYDAutoMultimediaDevice.MEDIA_MODE, i3);
        intent.putExtra(BYDAutoMultimediaDevice.MEDIA_ACTION, i4);
        if (cVar == null) {
            cVar = new c();
        }
        cVar.c("package", this.f4453d.getPackageName());
        intent.putExtra(BYDAutoMultimediaDevice.MEDIA_PARAM, cVar.a());
        if (interfaceC0045b != null) {
            Bundle bundle = new Bundle();
            bundle.putBinder("MEDIA_CONTROL_CALLBACK", new d(interfaceC0045b).asBinder());
            intent.putExtra("MEDIA_CONTROL_CALLBACK", bundle);
        }
        this.f4453d.startService(intent);
    }
}
