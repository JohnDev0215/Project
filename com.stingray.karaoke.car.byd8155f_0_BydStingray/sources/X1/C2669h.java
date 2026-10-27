package x1;

import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;

/* JADX INFO: renamed from: x1.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2669h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ColorStateList f23704a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Configuration f23705b;
    public final int c;

    public C2669h(ColorStateList colorStateList, Configuration configuration, Resources.Theme theme) {
        this.f23704a = colorStateList;
        this.f23705b = configuration;
        this.c = theme == null ? 0 : theme.hashCode();
    }
}
