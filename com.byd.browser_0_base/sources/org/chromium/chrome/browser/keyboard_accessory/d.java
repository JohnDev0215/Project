package org.chromium.chrome.browser.keyboard_accessory;

import android.util.SparseArray;
import defpackage.bl;
import defpackage.m41;
import defpackage.rq3;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: ManualFillingState.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {
    public static final int[] g = {1, 2, 3};
    public final WebContents a;
    public final SparseArray<b> b = new SparseArray<>();
    public final SparseArray<Object> c = new SparseArray<>();
    public bl<m41[]> d;
    public boolean e;
    public final a f;

    /* JADX INFO: compiled from: ManualFillingState.java */
    public class a extends rq3 {
        public a(WebContents webContents) {
            super(webContents);
        }

        @Override // defpackage.rq3
        public final void wasHidden() {
            d.this.e = false;
        }

        @Override // defpackage.rq3
        public final void wasShown() {
            d dVar = d.this;
            dVar.e = true;
            bl<m41[]> blVar = dVar.d;
            if (blVar != null) {
                blVar.b(blVar.d);
            }
            int[] iArr = d.g;
            for (int i = 0; i < 3; i++) {
                int i2 = iArr[i];
                if (dVar.c.get(i2, null) != null) {
                    Object obj = null;
                    dVar.a(i2).getClass();
                    if (obj instanceof bl) {
                        throw null;
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: ManualFillingState.java */
    public static class b {
    }

    public d(WebContents webContents) {
        if (webContents == null || webContents.isDestroyed()) {
            this.a = null;
            this.f = null;
            return;
        }
        this.a = webContents;
        this.e = true;
        a aVar = new a(webContents);
        this.f = aVar;
        webContents.C(aVar);
    }

    public final b a(int i) {
        SparseArray<b> sparseArray = this.b;
        b bVar = sparseArray.get(i);
        if (bVar != null) {
            return bVar;
        }
        sparseArray.put(i, new b());
        return sparseArray.get(i);
    }
}
