package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

/* JADX INFO: compiled from: ReadLaterIPHController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b92 {
    public final pk3 a;
    public final h7 b;
    public final View c;

    public b92(AppCompatActivity appCompatActivity, ImageButton imageButton, i7 i7Var) {
        pk3 pk3Var = new pk3(appCompatActivity, new Handler(Looper.getMainLooper()));
        this.c = imageButton;
        this.b = i7Var;
        this.a = pk3Var;
    }
}
