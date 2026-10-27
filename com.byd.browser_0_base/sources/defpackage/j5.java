package defpackage;

import J.N;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.provider.Settings;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.CursorAnchorInfo;
import android.view.inputmethod.EditorBoundsInfo;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import java.util.List;
import org.chromium.base.BuildInfo;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.ViewAndroidDelegate;

/* JADX INFO: compiled from: AndroidStylusWritingHandler.java */
/* JADX INFO: loaded from: classes3.dex */
public final class j5 implements yv2, tv2, ViewAndroidDelegate.a {
    public static final Integer g;
    public final InputMethodManager e;
    public View f;

    /* JADX WARN: Removed duplicated region for block: B:10:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002c  */
    static {
        /*
            int r0 = defpackage.yj.a
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 33
            r2 = 0
            if (r0 < r1) goto L2d
            java.lang.String r0 = android.os.Build.VERSION.CODENAME
            java.lang.String r1 = "REL"
            boolean r1 = r1.equals(r0)
            r3 = 1
            if (r1 == 0) goto L15
            goto L29
        L15:
            java.util.Locale r1 = java.util.Locale.ROOT
            java.lang.String r0 = r0.toUpperCase(r1)
            java.lang.String r4 = "UpsideDownCake"
            java.lang.String r1 = r4.toUpperCase(r1)
            int r0 = r0.compareTo(r1)
            if (r0 < 0) goto L29
            r0 = r3
            goto L2a
        L29:
            r0 = r2
        L2a:
            if (r0 == 0) goto L2d
            r2 = r3
        L2d:
            r0 = 0
            if (r2 == 0) goto L3f
            java.lang.Class<android.view.PointerIcon> r1 = android.view.PointerIcon.class
            java.lang.String r2 = "TYPE_HANDWRITING"
            java.lang.reflect.Field r1 = r1.getField(r2)     // Catch: java.lang.Throwable -> L3f
            java.lang.Object r1 = r1.get(r0)     // Catch: java.lang.Throwable -> L3f
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch: java.lang.Throwable -> L3f
            r0 = r1
        L3f:
            defpackage.j5.g = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.j5.<clinit>():void");
    }

    public j5(Context context) {
        this.e = (InputMethodManager) context.getSystemService(InputMethodManager.class);
    }

    public static boolean p(Context context) {
        if (!BuildInfo.a() || Settings.Global.getInt(context.getContentResolver(), "stylus_handwriting_enabled", -1) != 1) {
            return false;
        }
        List<InputMethodInfo> inputMethodList = ((InputMethodManager) context.getSystemService(InputMethodManager.class)).getInputMethodList();
        String string = Settings.Secure.getString(context.getContentResolver(), "default_input_method");
        for (InputMethodInfo inputMethodInfo : inputMethodList) {
            if (inputMethodInfo.getComponent().flattenToString().equals(string)) {
                return inputMethodInfo.supportsStylusHandwriting();
            }
        }
        return false;
    }

    @Override // defpackage.yv2
    public final boolean a() {
        return true;
    }

    @Override // defpackage.tv2
    public final void h(Context context, WebContents webContents) {
        webContents.Z(this);
        webContents.u();
        if (webContents.u() == null) {
            return;
        }
        View containerView = webContents.u().getContainerView();
        containerView.setAutoHandwritingEnabled(false);
        this.f = containerView;
    }

    @Override // defpackage.yv2
    public final boolean i(ImeAdapterImpl.a aVar) {
        tv2.c(0);
        this.e.startStylusHandwriting(this.f);
        return true;
    }

    @Override // defpackage.yv2
    public final void m(Rect rect, Point point) {
        CursorAnchorInfo.Builder builder = new CursorAnchorInfo.Builder();
        builder.setEditorBoundsInfo(new EditorBoundsInfo.Builder().setHandwritingBounds(new RectF(rect)).build());
        this.e.updateCursorAnchorInfo(this.f, builder.build());
    }

    @Override // org.chromium.ui.base.ViewAndroidDelegate.a
    public final boolean n(ViewGroup viewGroup) {
        Integer num = g;
        if (num == null || !N.Mudil8Bg("StylusPointerAdjustment")) {
            return false;
        }
        viewGroup.setPointerIcon(PointerIcon.getSystemIcon(viewGroup.getContext(), num.intValue()));
        return true;
    }

    @Override // defpackage.tv2
    public final ViewAndroidDelegate.a f() {
        return this;
    }

    @Override // defpackage.tv2
    public final void k(Context context, boolean z) {
    }
}
