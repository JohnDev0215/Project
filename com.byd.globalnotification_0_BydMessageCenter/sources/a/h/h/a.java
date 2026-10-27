package a.h.h;

import android.graphics.Typeface;

/* JADX INFO: loaded from: classes.dex */
public class a implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ i f731a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Typeface f732b;

    public a(c cVar, i iVar, Typeface typeface) {
        this.f731a = iVar;
        this.f732b = typeface;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f731a.b(this.f732b);
    }
}
