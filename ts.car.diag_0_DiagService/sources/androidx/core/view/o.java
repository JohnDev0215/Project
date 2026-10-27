package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f569a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f570b;

    public o(@NonNull ViewGroup viewGroup) {
    }

    public int a() {
        return this.f570b | this.f569a;
    }

    public void b(@NonNull View view, @NonNull View view2, int i) {
        c(view, view2, i, 0);
    }

    public void c(@NonNull View view, @NonNull View view2, int i, int i2) {
        if (i2 == 1) {
            this.f570b = i;
        } else {
            this.f569a = i;
        }
    }

    public void d(@NonNull View view, int i) {
        if (i == 1) {
            this.f570b = 0;
        } else {
            this.f569a = 0;
        }
    }
}
