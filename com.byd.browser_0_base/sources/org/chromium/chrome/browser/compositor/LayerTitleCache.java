package org.chromium.chrome.browser.compositor;

import J.N;
import android.content.Context;
import android.content.res.Resources;
import android.util.SparseArray;
import defpackage.ab3;
import defpackage.df0;
import defpackage.ve;
import defpackage.y13;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;
import org.chromium.ui.resources.ResourceManager;

/* JADX INFO: loaded from: classes.dex */
public class LayerTitleCache {
    public static int k = 1;
    public final Context a;
    public y13 b;
    public final SparseArray<a> c = new SparseArray<>();
    public final int d;
    public long e;
    public final ResourceManager f;
    public FaviconHelper g;
    public final FaviconHelper.a h;
    public final ab3 i;
    public final ab3 j;

    public class a {
        public final ve a;
        public final ve b;
        public boolean c;

        public a() {
            int i = LayerTitleCache.k;
            LayerTitleCache.k = i + 1;
            this.a = new ve(i);
            int i2 = LayerTitleCache.k;
            LayerTitleCache.k = i2 + 1;
            this.b = new ve(i2);
        }
    }

    public LayerTitleCache(Context context, ResourceManager resourceManager) {
        this.a = context;
        this.f = resourceManager;
        Resources resources = context.getResources();
        this.e = N.MTbG5FQ5(this, resources.getDimensionPixelOffset(R$dimen.border_texture_title_fade), resources.getDimensionPixelSize(R$dimen.tab_title_favicon_start_padding), resources.getDimensionPixelSize(R$dimen.tab_title_favicon_end_padding), R$drawable.spinner, R$drawable.spinner_white, resourceManager);
        this.d = resources.getDimensionPixelSize(R$dimen.compositor_tab_title_favicon_size);
        this.i = new ab3(context, false);
        this.j = new ab3(context, true);
        this.h = new FaviconHelper.a();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x00d3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0038 A[PHI: r0
  0x0038: PHI (r0v2 java.lang.String) = (r0v1 java.lang.String), (r0v15 java.lang.String), (r0v17 java.lang.String) binds: [B:10:0x001c, B:12:0x002a, B:15:0x0032] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00ba A[PHI: r9
  0x00ba: PHI (r9v12 boolean) = (r9v1 boolean), (r9v13 boolean) binds: [B:94:0x01da, B:34:0x00b9] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00ef A[Catch: InflateException -> 0x00e2, OutOfMemoryError -> 0x00e7, TryCatch #11 {InflateException -> 0x00e2, OutOfMemoryError -> 0x00e7, blocks: (B:46:0x00da, B:53:0x00ef, B:54:0x00fa), top: B:127:0x00da }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x017d A[Catch: InflateException -> 0x01ba, OutOfMemoryError -> 0x01c0, TryCatch #15 {InflateException -> 0x01ba, OutOfMemoryError -> 0x01c0, blocks: (B:81:0x016c, B:83:0x017d, B:86:0x0184, B:88:0x01b5, B:87:0x01a3), top: B:119:0x016c }] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01a3 A[Catch: InflateException -> 0x01ba, OutOfMemoryError -> 0x01c0, TryCatch #15 {InflateException -> 0x01ba, OutOfMemoryError -> 0x01c0, blocks: (B:81:0x016c, B:83:0x017d, B:86:0x0184, B:88:0x01b5, B:87:0x01a3), top: B:119:0x016c }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01dc  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String a(org.chromium.chrome.browser.tab.Tab r22, java.lang.String r23) {
        /*
            Method dump skipped, instruction units count: 565
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.compositor.LayerTitleCache.a(org.chromium.chrome.browser.tab.Tab, java.lang.String):java.lang.String");
    }

    public final void b(int i) {
        SparseArray<a> sparseArray = this.c;
        a aVar = sparseArray.get(i);
        if (aVar == null) {
            return;
        }
        ResourceManager resourceManager = LayerTitleCache.this.f;
        if (resourceManager != null) {
            df0 df0Var = (df0) resourceManager.a.get(2);
            df0Var.e(aVar.a.e);
            df0Var.e(aVar.b.e);
        }
        sparseArray.remove(i);
        long j = this.e;
        if (j == 0) {
            return;
        }
        N.Mn77UQtR(j, this, i, -1, -1, false, false);
    }

    @CalledByNative
    public final void buildUpdatedTitle(int i) {
        Tab tabS;
        y13 y13Var = this.b;
        if (y13Var == null || (tabS = y13Var.s(i)) == null || tabS.isDestroyed()) {
            return;
        }
        a(tabS, tabS.getContext().getString(R$string.tab_loading_default_title));
    }

    @CalledByNative
    public final long getNativePtr() {
        return this.e;
    }
}
