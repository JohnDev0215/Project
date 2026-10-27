package spi;

import android.car.XCar;
import android.content.Context;
import android.content.Intent;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import android.util.Log;
import java.util.HashMap;
import java.util.Objects;

/* JADX INFO: compiled from: MediaControl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a7 {
    public static volatile a7 b;
    public Context a;

    static {
        Objects.requireNonNull(Boolean.FALSE);
        Objects.requireNonNull(Boolean.TRUE);
        b = null;
    }

    private a7(Context context) {
        this.a = context;
        StringBuilder sbK = c.k("package: ");
        sbK.append(this.a.getPackageName());
        Log.d("MediaControl", sbK.toString());
    }

    public static void a(Context context, int i, a aVar) {
        if (b == null) {
            b = new a7(context.getApplicationContext());
        }
        a7 a7Var = b;
        Objects.requireNonNull(a7Var);
        Log.d("MediaControl", "controlMedia, mode is: 104 action is: " + i);
        Intent intent = new Intent(BYDAutoMultimediaDevice.ACTION_START_MEDIA);
        intent.setPackage("com.byd.localmusic");
        intent.putExtra(BYDAutoMultimediaDevice.MEDIA_MODE, 104);
        intent.putExtra(BYDAutoMultimediaDevice.MEDIA_ACTION, i);
        if (aVar == null) {
            aVar = new a();
        }
        aVar.b(XCar.PACKAGE_SERVICE, a7Var.a.getPackageName());
        intent.putExtra(BYDAutoMultimediaDevice.MEDIA_PARAM, aVar.a);
        a7Var.a.startService(intent);
    }

    /* JADX INFO: compiled from: MediaControl.java */
    public static class a {
        public HashMap<String, String> a;

        public a() {
            this.a = new HashMap<>();
        }

        public final void a(int i) {
            this.a.put("param_media_play_state", String.valueOf(i));
        }

        public final void b(String str, String str2) {
            this.a.put(str, str2);
        }

        public a(HashMap<String, String> map) {
            this.a = new HashMap<>();
            if (map == null) {
                Log.d("MediaControl", "MediaControlParam, param is null");
            } else {
                this.a = map;
            }
        }
    }
}
