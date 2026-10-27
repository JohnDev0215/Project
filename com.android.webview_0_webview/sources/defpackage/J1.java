package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class J1 extends Activity implements I4 {
    public J4 s = new J4(this);

    @Override // android.content.ContextWrapper, android.content.Context
    public Context createConfigurationContext(Configuration configuration) {
        AbstractC1077kw.c();
        return super.createConfigurationContext(configuration);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x006c  */
    @Override // android.app.Activity, android.view.Window.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean dispatchKeyEvent(android.view.KeyEvent r10) {
        /*
            r9 = this;
            android.view.Window r0 = r9.getWindow()
            android.view.View r0 = r0.getDecorView()
            r1 = 1
            if (r0 == 0) goto L12
            boolean r0 = defpackage.AbstractC1251o3.d(r0, r10)
            if (r0 == 0) goto L12
            return r1
        L12:
            r0 = 0
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 28
            if (r2 < r3) goto L1f
            boolean r10 = super.dispatchKeyEvent(r10)
            goto L89
        L1f:
            r2 = 0
            r9.onUserInteraction()
            android.view.Window r3 = r9.getWindow()
            r4 = 8
            boolean r4 = r3.hasFeature(r4)
            if (r4 == 0) goto L6c
            android.app.ActionBar r4 = r9.getActionBar()
            int r5 = r10.getKeyCode()
            r6 = 82
            if (r5 != r6) goto L6c
            if (r4 == 0) goto L6c
            boolean r5 = defpackage.AbstractC0806g3.a
            if (r5 != 0) goto L55
            java.lang.Class r5 = r4.getClass()     // Catch: java.lang.NoSuchMethodException -> L53
            java.lang.String r6 = "onMenuKeyEvent"
            java.lang.Class[] r7 = new java.lang.Class[r1]     // Catch: java.lang.NoSuchMethodException -> L53
            java.lang.Class<android.view.KeyEvent> r8 = android.view.KeyEvent.class
            r7[r0] = r8     // Catch: java.lang.NoSuchMethodException -> L53
            java.lang.reflect.Method r5 = r5.getMethod(r6, r7)     // Catch: java.lang.NoSuchMethodException -> L53
            defpackage.AbstractC0806g3.b = r5     // Catch: java.lang.NoSuchMethodException -> L53
        L53:
            defpackage.AbstractC0806g3.a = r1
        L55:
            java.lang.reflect.Method r5 = defpackage.AbstractC0806g3.b
            if (r5 == 0) goto L69
            java.lang.Object[] r6 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L68
            r6[r0] = r10     // Catch: java.lang.Throwable -> L68
            java.lang.Object r4 = r5.invoke(r4, r6)     // Catch: java.lang.Throwable -> L68
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch: java.lang.Throwable -> L68
            boolean r0 = r4.booleanValue()     // Catch: java.lang.Throwable -> L68
            goto L69
        L68:
        L69:
            if (r0 == 0) goto L6c
            goto L88
        L6c:
            boolean r0 = r3.superDispatchKeyEvent(r10)
            if (r0 == 0) goto L73
            goto L88
        L73:
            android.view.View r0 = r3.getDecorView()
            boolean r3 = defpackage.AbstractC1251o3.c(r0, r10)
            if (r3 == 0) goto L7e
            goto L88
        L7e:
            if (r0 == 0) goto L84
            android.view.KeyEvent$DispatcherState r2 = r0.getKeyDispatcherState()
        L84:
            boolean r1 = r10.dispatch(r9, r2, r9)
        L88:
            r10 = r1
        L89:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.J1.dispatchKeyEvent(android.view.KeyEvent):boolean");
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !AbstractC1251o3.d(decorView, keyEvent)) {
            return super.dispatchKeyShortcutEvent(keyEvent);
        }
        return true;
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        AbstractC1077kw.c();
        return super.getAssets();
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        AbstractC1077kw.c();
        return super.getResources();
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        AbstractC1077kw.c();
        return super.getTheme();
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        N4.b(this);
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        this.s.c(G4.CREATED);
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        AbstractC1077kw.c();
        super.setTheme(i);
    }
}
