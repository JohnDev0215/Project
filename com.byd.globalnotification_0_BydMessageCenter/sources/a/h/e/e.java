package a.h.e;

import a.h.h.h;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @SuppressLint({"BanConcurrentHashMap"})
    public ConcurrentHashMap<Long, a.h.d.b.b> f727a = new ConcurrentHashMap<>();

    public abstract Typeface a(Context context, a.h.d.b.b bVar, Resources resources, int i);

    public abstract Typeface b(Context context, CancellationSignal cancellationSignal, h[] hVarArr, int i);

    public abstract Typeface c(Context context, Resources resources, int i, String str, int i2);
}
