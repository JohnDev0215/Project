package U4;

import android.text.TextPaint;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class i {
    public float c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final WeakReference f9073e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public V4.c f9074f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextPaint f9071a = new TextPaint(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final R4.a f9072b = new R4.a(this, 1);
    public boolean d = true;

    public i(R4.e eVar) {
        this.f9073e = new WeakReference(null);
        this.f9073e = new WeakReference(eVar);
    }
}
