package org.chromium.chrome.browser.theme;

import android.content.Context;
import android.content.res.ColorStateList;
import defpackage.n3;
import java.util.Iterator;
import org.chromium.base.b;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ThemeColorProvider {
    public int e;
    public Integer f;
    public ColorStateList g;
    public final org.chromium.base.b<a> h = new org.chromium.base.b<>();
    public final org.chromium.base.b<b> i = new org.chromium.base.b<>();

    public interface a {
        void d(int i, boolean z);
    }

    public interface b {
        void e(int i, ColorStateList colorStateList);
    }

    public ThemeColorProvider(Context context) {
        this.g = n3.y0(context);
    }

    public final void a(int i, boolean z) {
        if (this.e == i) {
            return;
        }
        this.e = i;
        Iterator<a> it = this.h.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).d(i, z);
            }
        }
    }

    public final void b(int i, ColorStateList colorStateList) {
        if (colorStateList == this.g) {
            return;
        }
        this.g = colorStateList;
        this.f = Integer.valueOf(i);
        Iterator<b> it = this.i.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((b) aVar.next()).e(i, colorStateList);
            }
        }
    }
}
