package T3;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static final Object d = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f8643a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f8644b;
    public final Map c;

    public a(Drawable.Callback callback, Map map) {
        if (!TextUtils.isEmpty(null)) {
            throw null;
        }
        this.f8644b = null;
        this.c = map;
        if (callback instanceof View) {
            this.f8643a = ((View) callback).getContext().getApplicationContext();
        } else {
            this.f8643a = null;
        }
    }
}
