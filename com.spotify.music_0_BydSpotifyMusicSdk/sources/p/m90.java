package p;

import android.content.res.AssetManager;
import android.os.Build;
import com.spotify.automotive.baseui.settings.SettingsActivity;
import com.spotify.connect.esperanto.proto.ConnectMessages$StateResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class m90 {
    public boolean a;
    public final Object b;
    public final Executor c;
    public final fy1 d;
    public Serializable e;
    public Serializable f;
    public Object g;
    public String h;
    public Object i;
    public final String j;
    public n90[] k;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.io.Serializable] */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    public m90(AssetManager assetManager, db dbVar, fy1 fy1Var, String str, File file) {
        this.a = false;
        this.c = dbVar;
        this.d = fy1Var;
        this.h = str;
        this.i = "dexopt/baseline.prof";
        this.j = "dexopt/baseline.profm";
        this.g = file;
        int i = Build.VERSION.SDK_INT;
        ?? r3 = 0;
        r3 = 0;
        if (i <= 34) {
            switch (i) {
                case 26:
                    r3 = v03.i;
                    break;
                case 27:
                    r3 = v03.h;
                    break;
                case 28:
                case 29:
                case 30:
                    r3 = v03.g;
                    break;
                case 31:
                case ConnectMessages$StateResponse.Device.VOLUME_STEPS_FIELD_NUMBER /* 32 */:
                case ConnectMessages$StateResponse.Device.CAPABILITIES_FIELD_NUMBER /* 33 */:
                case ConnectMessages$StateResponse.Device.INCARNATIONS_FIELD_NUMBER /* 34 */:
                    r3 = v03.f;
                    break;
            }
        }
        this.e = r3;
    }

    public final FileInputStream a(AssetManager assetManager, String str) {
        try {
            return assetManager.openFd(str).createInputStream();
        } catch (FileNotFoundException e) {
            String message = e.getMessage();
            if (message != null && message.contains("compressed")) {
                this.d.n();
            }
            return null;
        }
    }

    public final void b(int i, Serializable serializable) {
        this.c.execute(new l90(i, 0, this, serializable));
    }

    public m90(SettingsActivity settingsActivity) {
        this.a = true;
    }
}
