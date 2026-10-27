package defpackage;

import J.N;
import android.R;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.RelativeLayout;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.TraceEvent;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.SmartClipProvider;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.EventForwarder;
import org.chromium.weblayer_private.TabImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class KP extends RelativeLayout implements InterfaceC0594cE, SmartClipProvider, ViewGroup.OnHierarchyChangeListener, View.OnSystemUiVisibilityChangeListener {
    public static final int z = View.MeasureSpec.makeMeasureSpec(0, 0);
    public TabImpl s;
    public WebContents t;
    public final Ys u;
    public final Ys v;
    public int w;
    public int x;
    public PN y;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KP(Context context, PN pn) {
        super(context, null, R.attr.webViewStyle);
        int i = Build.VERSION.SDK_INT;
        this.u = new Ys();
        this.v = new Ys();
        int i2 = z;
        this.w = i2;
        this.x = i2;
        if (getScrollBarStyle() == 0) {
            setHorizontalScrollBarEnabled(false);
            setVerticalScrollBarEnabled(false);
        }
        this.y = pn;
        setFocusable(true);
        setFocusableInTouchMode(true);
        if (i >= 26) {
            C1295ot.e(this, false);
        }
        super.setOnHierarchyChangeListener(this);
        super.setOnSystemUiVisibilityChangeListener(this);
        if (i >= 26) {
            setImportantForAutofill(1);
        }
    }

    public static KP i(Context context, PN pn) {
        int i = Build.VERSION.SDK_INT;
        return i >= 26 ? new JP(context, pn) : i >= 23 ? new IP(context, pn) : new KP(context, pn);
    }

    @Override // android.view.View
    public boolean awakenScrollBars(int i, boolean z2) {
        if (getScrollBarStyle() == 0) {
            return false;
        }
        return super.awakenScrollBars(i, z2);
    }

    @Override // defpackage.InterfaceC0594cE
    public boolean b(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.View
    public int computeHorizontalScrollExtent() {
        WD wdK = k();
        if (wdK == null) {
            return 0;
        }
        Az az = (Az) wdK;
        return (int) Math.ceil(az.a(az.e));
    }

    @Override // android.view.View
    public int computeHorizontalScrollOffset() {
        if (k() != null) {
            return (int) Math.floor(((Az) r0).c());
        }
        return 0;
    }

    @Override // android.view.View
    public int computeHorizontalScrollRange() {
        WD wdK = k();
        if (wdK == null) {
            return 0;
        }
        Az az = (Az) wdK;
        return (int) Math.ceil(az.a(az.c));
    }

    @Override // android.view.View
    public int computeVerticalScrollExtent() {
        WD wdK = k();
        if (wdK != null) {
            return ((Az) wdK).b();
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollOffset() {
        if (k() != null) {
            return (int) Math.floor(((Az) r0).d());
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollRange() {
        WD wdK = k();
        if (wdK == null) {
            return 0;
        }
        Az az = (Az) wdK;
        return (int) Math.ceil(az.a(az.d));
    }

    @Override // defpackage.InterfaceC0594cE
    public boolean d(MotionEvent motionEvent) {
        return super.onGenericMotionEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchDragEvent(DragEvent dragEvent) {
        PN pn = this.y;
        dragEvent.getAction();
        pn.b(-((NQ) pn.a).a.A);
        boolean zDispatchDragEvent = super.dispatchDragEvent(dragEvent);
        PN pn2 = this.y;
        int action = dragEvent.getAction();
        Objects.requireNonNull(pn2);
        if (action == 6 || action == 4 || action == 3) {
            pn2.b(0.0f);
        }
        return zDispatchDragEvent;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (!isFocused()) {
            return super.dispatchKeyEvent(keyEvent);
        }
        EventForwarder eventForwarderJ = j();
        long j = eventForwarderJ.b;
        if (j == 0) {
            return false;
        }
        return N.MZE$0qqv(j, eventForwarderJ, keyEvent);
    }

    @Override // org.chromium.content_public.browser.SmartClipProvider
    public void extractSmartClipData(int i, int i2, int i3, int i4) {
        WebContents webContents = this.t;
        if (webContents != null) {
            webContents.K(i, i2, i3, i4);
        }
    }

    @Override // defpackage.InterfaceC0594cE
    public boolean f(int i, KeyEvent keyEvent) {
        return super.onKeyUp(i, keyEvent);
    }

    @Override // android.view.View
    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        InterfaceC0817gE interfaceC0817gEM = m();
        AccessibilityNodeProvider accessibilityNodeProviderK = interfaceC0817gEM != null ? ((WebContentsAccessibilityImpl) interfaceC0817gEM).k() : null;
        return accessibilityNodeProviderK != null ? accessibilityNodeProviderK : super.getAccessibilityNodeProvider();
    }

    public final EventForwarder j() {
        WebContents webContents = this.t;
        if (webContents != null) {
            return webContents.F();
        }
        return null;
    }

    public final WD k() {
        WebContents webContents = this.t;
        if (webContents != null) {
            return ((WebContentsImpl) webContents).y;
        }
        return null;
    }

    public final InterfaceC0650dE l() {
        WebContents webContents = this.t;
        if (webContents != null) {
            return Wz.a(webContents);
        }
        return null;
    }

    public InterfaceC0817gE m() {
        WebContents webContents = this.t;
        if (webContents == null || webContents.v()) {
            return null;
        }
        return WebContentsAccessibilityImpl.i(this.t);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.t != null) {
            ((Wz) l()).b();
        }
    }

    @Override // android.view.View
    public boolean onCheckIsTextEditor() {
        WebContents webContents = this.t;
        if (webContents == null || webContents.v()) {
            return false;
        }
        return ImeAdapterImpl.P(this.t).T();
    }

    @Override // android.view.ViewGroup.OnHierarchyChangeListener
    public void onChildViewAdded(View view, View view2) {
        Iterator it = this.u.iterator();
        while (true) {
            Ws ws = (Ws) it;
            if (!ws.hasNext()) {
                return;
            } else {
                ((ViewGroup.OnHierarchyChangeListener) ws.next()).onChildViewAdded(view, view2);
            }
        }
    }

    @Override // android.view.ViewGroup.OnHierarchyChangeListener
    public void onChildViewRemoved(View view, View view2) {
        Iterator it = this.u.iterator();
        while (true) {
            Ws ws = (Ws) it;
            if (!ws.hasNext()) {
                return;
            } else {
                ((ViewGroup.OnHierarchyChangeListener) ws.next()).onChildViewRemoved(view, view2);
            }
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        if (this.t != null) {
            ((Wz) l()).c(configuration);
        }
        super.onConfigurationChanged(configuration);
    }

    @Override // android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        WebContents webContents = this.t;
        if (webContents == null || webContents.v()) {
            return null;
        }
        return ImeAdapterImpl.P(this.t).U(editorInfo);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.t != null) {
            ((Wz) l()).d();
        }
    }

    @Override // android.view.View
    public boolean onDragEvent(DragEvent dragEvent) {
        return j().d(dragEvent, this);
    }

    @Override // android.view.View
    public void onFocusChanged(boolean z2, int i, Rect rect) {
        try {
            TraceEvent.x("ContentView.onFocusChanged", null);
            super.onFocusChanged(z2, i, rect);
            if (this.t != null) {
                ((Wz) l()).w = true;
                ((Wz) l()).f(z2);
            }
        } finally {
            TraceEvent.A("ContentView.onFocusChanged");
        }
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        return j().e(motionEvent);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) throws Throwable {
        boolean zF = j().f(motionEvent);
        InterfaceC0817gE interfaceC0817gEM = m();
        if (interfaceC0817gEM != null && !((WebContentsAccessibilityImpl) interfaceC0817gEM).O) {
            super.onHoverEvent(motionEvent);
        }
        return zF;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptHoverEvent(MotionEvent motionEvent) {
        this.y.a(motionEvent, true);
        return super.onInterceptHoverEvent(motionEvent);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean zOnInterceptTouchEvent = super.onInterceptTouchEvent(motionEvent);
        this.y.a(motionEvent, false);
        return zOnInterceptTouchEvent;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        EventForwarder eventForwarderJ = j();
        long j = eventForwarderJ.b;
        if (j == 0) {
            return false;
        }
        return N.MRbfSEI1(j, eventForwarderJ, keyEvent, i);
    }

    @Override // android.widget.RelativeLayout, android.view.View
    public void onMeasure(int i, int i2) {
        int i3 = this.w;
        if (i3 != z) {
            i = i3;
        }
        int i4 = this.x;
        if (i4 != z) {
            i2 = i4;
        }
        super.onMeasure(i, i2);
    }

    @Override // android.view.View, defpackage.InterfaceC0594cE
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
    }

    @Override // android.view.View.OnSystemUiVisibilityChangeListener
    public void onSystemUiVisibilityChange(int i) {
        Iterator it = this.v.iterator();
        while (true) {
            Ws ws = (Ws) it;
            if (!ws.hasNext()) {
                return;
            } else {
                ((View.OnSystemUiVisibilityChangeListener) ws.next()).onSystemUiVisibilityChange(i);
            }
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean zG = j().g(motionEvent);
        this.y.a(motionEvent, true);
        return zG;
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z2) {
        super.onWindowFocusChanged(z2);
        if (this.t != null) {
            ((Wz) l()).g(z2);
        }
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int i, Bundle bundle) {
        m();
        return super.performAccessibilityAction(i, bundle);
    }

    @Override // android.view.View
    public boolean performLongClick() {
        return false;
    }

    @Override // android.view.View
    public void scrollBy(int i, int i2) {
        EventForwarder eventForwarderJ = j();
        float f = i;
        float f2 = i2;
        long j = eventForwarderJ.b;
        if (j == 0) {
            return;
        }
        N.MMwH$VBb(j, eventForwarderJ, f, f2);
    }

    @Override // android.view.View
    public void scrollTo(int i, int i2) {
        EventForwarder eventForwarderJ = j();
        float f = i;
        float f2 = i2;
        long j = eventForwarderJ.b;
        if (j == 0) {
            return;
        }
        N.M6lTZ5w8(j, eventForwarderJ, f, f2);
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        super.setOnHierarchyChangeListener(onHierarchyChangeListener);
    }

    @Override // android.view.View
    public void setOnSystemUiVisibilityChangeListener(View.OnSystemUiVisibilityChangeListener onSystemUiVisibilityChangeListener) {
        super.setOnSystemUiVisibilityChangeListener(onSystemUiVisibilityChangeListener);
    }

    @Override // org.chromium.content_public.browser.SmartClipProvider
    public void setSmartClipResultHandler(Handler handler) {
        WebContents webContents = this.t;
        if (webContents != null) {
            webContents.setSmartClipResultHandler(handler);
        }
    }
}
