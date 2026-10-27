package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

/* JADX INFO: compiled from: PageZoomIPHController.java */
/* JADX INFO: loaded from: classes.dex */
public final class ev1 {
    public final h7 a;
    public final View b;
    public final pk3 c;

    public ev1(AppCompatActivity appCompatActivity, ImageButton imageButton, i7 i7Var) {
        pk3 pk3Var = new pk3(appCompatActivity, new Handler(Looper.getMainLooper()));
        this.a = i7Var;
        this.b = imageButton;
        this.c = pk3Var;
    }
}
