package org.chromium.components.embedder_support.view;

import J.N;
import android.R;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityNodeProvider;
import android.widget.FrameLayout;
import defpackage.ao3;
import defpackage.di0;
import defpackage.fa1;
import defpackage.h61;
import defpackage.jt3;
import defpackage.kq3;
import defpackage.kt3;
import defpackage.sm3;
import defpackage.ya2;
import defpackage.yv2;
import defpackage.z7;
import defpackage.za2;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Iterator;
import org.chromium.base.TraceEvent;
import org.chromium.base.b;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.SmartClipProvider;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.browser.a;
import org.chromium.ui.base.EventForwarder;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes2.dex */
public class ContentView extends FrameLayout implements a.InterfaceC0153a, SmartClipProvider, ViewGroup.OnHierarchyChangeListener, View.OnSystemUiVisibilityChangeListener, View.OnDragListener {
    public static final int p = View.MeasureSpec.makeMeasureSpec(0, 0);
    public WebContents e;
    public boolean f;
    public final b<ViewGroup.OnHierarchyChangeListener> g;
    public final b<View.OnSystemUiVisibilityChangeListener> h;
    public final b<View.OnDragListener> i;
    public sm3 j;
    public final int k;
    public final int l;
    public final di0 m;
    public boolean n;
    public Boolean o;

    public ContentView(Context context, WebContents webContents) {
        super(context, null, R.attr.webViewStyle);
        this.g = new b<>();
        this.h = new b<>();
        this.i = new b<>();
        int i = p;
        this.k = i;
        this.l = i;
        if (getScrollBarStyle() == 0) {
            setHorizontalScrollBarEnabled(false);
            setVerticalScrollBarEnabled(false);
        }
        this.e = webContents;
        this.m = null;
        setFocusable(true);
        setFocusableInTouchMode(true);
        setDefaultFocusHighlightEnabled(false);
        setOnHierarchyChangeListener(this);
        setOnSystemUiVisibilityChangeListener(this);
        setOnDragListener(this);
    }

    private EventForwarder getEventForwarder() {
        if (d() && this.e.V() != null) {
            return this.e.H();
        }
        return null;
    }

    private ya2 getRenderCoordinates() {
        if (d()) {
            return ((WebContentsImpl) this.e).l;
        }
        return null;
    }

    private a getViewEventSink() {
        if (this.j == null && d()) {
            this.j = (sm3) ((WebContentsImpl) this.e).n0(sm3.class, sm3.a.a);
        }
        return this.j;
    }

    @Override // org.chromium.content_public.browser.a.InterfaceC0153a
    public final boolean a(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    public void addOnDragListener(View.OnDragListener onDragListener) {
        this.i.a(onDragListener);
    }

    public void addOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.g.a(onHierarchyChangeListener);
    }

    public void addOnSystemUiVisibilityChangeListener(View.OnSystemUiVisibilityChangeListener onSystemUiVisibilityChangeListener) {
        this.h.a(onSystemUiVisibilityChangeListener);
    }

    @Override // android.view.View
    public final boolean awakenScrollBars(int i, boolean z) {
        if (getScrollBarStyle() == 0) {
            return false;
        }
        return super.awakenScrollBars(i, z);
    }

    @Override // org.chromium.content_public.browser.a.InterfaceC0153a
    public final boolean b(MotionEvent motionEvent) {
        return super.onGenericMotionEvent(motionEvent);
    }

    @Override // org.chromium.content_public.browser.a.InterfaceC0153a
    public final boolean c(int i, KeyEvent keyEvent) {
        return super.onKeyUp(i, keyEvent);
    }

    @Override // android.view.View
    public final int computeHorizontalScrollExtent() {
        ya2 renderCoordinates = getRenderCoordinates();
        if (renderCoordinates == null) {
            return 0;
        }
        za2 za2Var = (za2) renderCoordinates;
        return (int) Math.ceil(za2Var.a(za2Var.e));
    }

    @Override // android.view.View
    public final int computeHorizontalScrollOffset() {
        ya2 renderCoordinates = getRenderCoordinates();
        if (renderCoordinates == null) {
            return 0;
        }
        za2 za2Var = (za2) renderCoordinates;
        return (int) Math.floor(za2Var.a(za2Var.a));
    }

    @Override // android.view.View
    public final int computeHorizontalScrollRange() {
        ya2 renderCoordinates = getRenderCoordinates();
        if (renderCoordinates == null) {
            return 0;
        }
        za2 za2Var = (za2) renderCoordinates;
        return (int) Math.ceil(za2Var.a(za2Var.c));
    }

    @Override // android.view.View
    public final int computeVerticalScrollExtent() {
        ya2 renderCoordinates = getRenderCoordinates();
        if (renderCoordinates == null) {
            return 0;
        }
        za2 za2Var = (za2) renderCoordinates;
        return (int) Math.ceil(za2Var.a(za2Var.f));
    }

    @Override // android.view.View
    public final int computeVerticalScrollOffset() {
        ya2 renderCoordinates = getRenderCoordinates();
        if (renderCoordinates != null) {
            return ((za2) renderCoordinates).b();
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeVerticalScrollRange() {
        ya2 renderCoordinates = getRenderCoordinates();
        if (renderCoordinates == null) {
            return 0;
        }
        za2 za2Var = (za2) renderCoordinates;
        return (int) Math.ceil(za2Var.a(za2Var.d));
    }

    public final boolean d() {
        WebContents webContents = this.e;
        return (webContents == null || webContents.isDestroyed()) ? false : true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchDragEvent(DragEvent dragEvent) {
        int action;
        di0 di0Var = this.m;
        if (di0Var != null) {
            dragEvent.getAction();
            CompositorViewHolder.a aVar = (CompositorViewHolder.a) di0Var.a;
            h61 h61Var = CompositorViewHolder.this.l;
            RectF rectF = aVar.a;
            if (h61Var != null) {
                h61Var.K(rectF);
            }
            di0Var.b(-rectF.top);
        }
        boolean zDispatchDragEvent = super.dispatchDragEvent(dragEvent);
        if (di0Var != null && ((action = dragEvent.getAction()) == 6 || action == 4 || action == 3)) {
            di0Var.b(0.0f);
        }
        return zDispatchDragEvent;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (!isFocused()) {
            return super.dispatchKeyEvent(keyEvent);
        }
        EventForwarder eventForwarder = getEventForwarder();
        if (eventForwarder == null) {
            return false;
        }
        long j = eventForwarder.b;
        if (j == 0) {
            return false;
        }
        return N.MZE$0qqv(j, eventForwarder, keyEvent);
    }

    @Override // org.chromium.content_public.browser.SmartClipProvider
    public final void extractSmartClipData(int i, int i2, int i3, int i4) {
        if (d()) {
            this.e.x(i, i2, i3, i4);
        }
    }

    @Override // android.view.View
    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        kq3 webContentsAccessibility = getWebContentsAccessibility();
        if (webContentsAccessibility != null) {
            return ((WebContentsAccessibilityImpl) webContentsAccessibility).l();
        }
        return null;
    }

    public WebContents getWebContents() {
        return this.e;
    }

    public kq3 getWebContentsAccessibility() {
        if (d() && this.e.V() != null) {
            return kq3.b(this.e);
        }
        return null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!d()) {
            return;
        }
        kt3 kt3VarE = kt3.e(((sm3) getViewEventSink()).e);
        kt3VarE.h = true;
        kt3VarE.d();
        Iterator<jt3> it = kt3VarE.e.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((jt3) aVar.next()).onAttachedToWindow();
            }
        }
    }

    @Override // android.view.View
    public final boolean onCheckIsTextEditor() {
        int i;
        if (d() && (i = ImeAdapterImpl.b(this.e).n) != 0) {
            return !(i == 8 || i == 12 || i == 9 || i == 10 || i == 11 || i == 13);
        }
        return false;
    }

    @Override // android.view.ViewGroup.OnHierarchyChangeListener
    public final void onChildViewAdded(View view, View view2) {
        Iterator<ViewGroup.OnHierarchyChangeListener> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((ViewGroup.OnHierarchyChangeListener) aVar.next()).onChildViewAdded(view, view2);
            }
        }
    }

    @Override // android.view.ViewGroup.OnHierarchyChangeListener
    public final void onChildViewRemoved(View view, View view2) {
        Iterator<ViewGroup.OnHierarchyChangeListener> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((ViewGroup.OnHierarchyChangeListener) aVar.next()).onChildViewRemoved(view, view2);
            }
        }
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        if (d()) {
            WebContentsImpl webContentsImpl = ((sm3) getViewEventSink()).e;
            try {
                TraceEvent.q("ViewEventSink.onConfigurationChanged", null);
                Iterator<jt3> it = kt3.e(webContentsImpl).e.iterator();
                while (true) {
                    b.a aVar = (b.a) it;
                    if (!aVar.hasNext()) {
                        break;
                    } else {
                        ((jt3) aVar.next()).onConfigurationChanged(configuration);
                    }
                }
                ViewAndroidDelegate viewAndroidDelegateU = webContentsImpl.u();
                if (viewAndroidDelegateU != null) {
                    ao3.e(viewAndroidDelegateU.getContainerView(), "ViewEventSinkImpl.onConfigurationChanged");
                }
            } finally {
                TraceEvent.C("ViewEventSink.onConfigurationChanged");
            }
        }
        super.onConfigurationChanged(configuration);
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x02a6  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x02ab  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x012a  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.inputmethod.InputConnection onCreateInputConnection(android.view.inputmethod.EditorInfo r22) {
        /*
            Method dump skipped, instruction units count: 738
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.embedder_support.view.ContentView.onCreateInputConnection(android.view.inputmethod.EditorInfo):android.view.inputmethod.InputConnection");
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        ViewAndroidDelegate viewAndroidDelegateU;
        WindowAndroid windowAndroid;
        super.onDetachedFromWindow();
        if (d()) {
            WebContentsImpl webContentsImpl = ((sm3) getViewEventSink()).e;
            kt3 kt3VarE = kt3.e(webContentsImpl);
            WindowAndroid windowAndroid2 = kt3VarE.f;
            if (windowAndroid2 != null) {
                windowAndroid2.h.a.remove(kt3VarE);
            }
            if (kt3VarE.h && (windowAndroid = kt3VarE.f) != null) {
                windowAndroid.v.c(kt3VarE.g);
            }
            kt3VarE.h = false;
            Iterator<jt3> it = kt3VarE.e.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    break;
                } else {
                    ((jt3) aVar.next()).onDetachedFromWindow();
                }
            }
            if (webContentsImpl.k == null || (viewAndroidDelegateU = webContentsImpl.u()) == null) {
                return;
            }
            webContentsImpl.k.g(viewAndroidDelegateU.getContainerView().getContext());
        }
    }

    @Override // android.view.View.OnDragListener
    public final boolean onDrag(View view, DragEvent dragEvent) {
        Iterator<View.OnDragListener> it = this.i.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return false;
            }
            ((View.OnDragListener) aVar.next()).onDrag(view, dragEvent);
        }
    }

    @Override // android.view.View
    public final boolean onDragEvent(DragEvent dragEvent) {
        String string;
        EventForwarder eventForwarder = getEventForwarder();
        if (eventForwarder == null || eventForwarder.b == 0) {
            return false;
        }
        ClipDescription clipDescription = dragEvent.getClipDescription();
        String[] strArrFilterMimeTypes = clipDescription == null ? new String[0] : clipDescription.filterMimeTypes("text/*");
        String[] strArrFilterMimeTypes2 = strArrFilterMimeTypes == null ? clipDescription.filterMimeTypes("image/*") : strArrFilterMimeTypes;
        if (dragEvent.getAction() == 1) {
            if (strArrFilterMimeTypes2 == null || strArrFilterMimeTypes2.length <= 0 || !eventForwarder.a) {
                return false;
            }
        } else if (dragEvent.getAction() == 3) {
            try {
                StringBuilder sb = new StringBuilder("");
                ClipData clipData = dragEvent.getClipData();
                int itemCount = clipData.getItemCount();
                for (int i = 0; i < itemCount; i++) {
                    sb.append(clipData.getItemAt(i).coerceToStyledText(getContext()));
                }
                string = sb.toString();
            } catch (UndeclaredThrowableException e) {
                fa1.b("EventForwarder", "Parsing clip data content failed.", e.getMessage());
                string = "";
            }
            getLocationOnScreen(new int[2]);
            float x = dragEvent.getX() + eventForwarder.c;
            float y = dragEvent.getY() + eventForwarder.d;
            N.MZ1ZkPta(eventForwarder.b, eventForwarder, dragEvent.getAction(), x, y, x + r1[0], y + r1[1], strArrFilterMimeTypes2, string);
        } else {
            string = "";
            getLocationOnScreen(new int[2]);
            float x2 = dragEvent.getX() + eventForwarder.c;
            float y2 = dragEvent.getY() + eventForwarder.d;
            N.MZ1ZkPta(eventForwarder.b, eventForwarder, dragEvent.getAction(), x2, y2, x2 + r1[0], y2 + r1[1], strArrFilterMimeTypes2, string);
        }
        return true;
    }

    @Override // android.view.View
    public final void onFocusChanged(boolean z, int i, Rect rect) {
        try {
            TraceEvent.q("ContentView.onFocusChanged", null);
            super.onFocusChanged(z, i, rect);
            if (d()) {
                ((sm3) getViewEventSink()).i = true;
                sm3 sm3Var = (sm3) getViewEventSink();
                Boolean bool = sm3Var.f;
                if (bool == null || bool.booleanValue() != z) {
                    sm3Var.f = Boolean.valueOf(z);
                    sm3Var.a();
                    yv2 yv2Var = sm3Var.e.k;
                    if (yv2Var != null) {
                        yv2Var.o(z);
                    }
                }
            }
        } finally {
            TraceEvent.C("ContentView.onFocusChanged");
        }
    }

    @Override // android.view.View
    public final boolean onGenericMotionEvent(MotionEvent motionEvent) {
        int actionMasked;
        EventForwarder eventForwarder = getEventForwarder();
        if (eventForwarder == null || eventForwarder.b == 0) {
            return false;
        }
        if ((motionEvent.getSource() & 2) != 0 && motionEvent.getToolType(0) == 3 && ((actionMasked = motionEvent.getActionMasked()) == 11 || actionMasked == 12)) {
            eventForwarder.e = motionEvent.getButtonState();
        }
        return N.MvdB06Zi(eventForwarder.b, eventForwarder, motionEvent, z7.s(motionEvent));
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00ca  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onHoverEvent(android.view.MotionEvent r27) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 227
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.embedder_support.view.ContentView.onHoverEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptHoverEvent(MotionEvent motionEvent) {
        di0 di0Var = this.m;
        if (di0Var != null) {
            di0Var.a(motionEvent, true);
        }
        return super.onInterceptHoverEvent(motionEvent);
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean zOnInterceptTouchEvent = super.onInterceptTouchEvent(motionEvent);
        di0 di0Var = this.m;
        if (di0Var != null) {
            di0Var.a(motionEvent, false);
        }
        return zOnInterceptTouchEvent;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyUp(int i, KeyEvent keyEvent) {
        EventForwarder eventForwarder = getEventForwarder();
        if (eventForwarder == null) {
            return false;
        }
        long j = eventForwarder.b;
        if (j == 0) {
            return false;
        }
        return N.MRbfSEI1(j, eventForwarder, keyEvent, i);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int i3 = this.k;
        int i4 = p;
        if (i3 != i4) {
            i = i3;
        }
        int i5 = this.l;
        if (i5 != i4) {
            i2 = i5;
        }
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    public final void onProvideVirtualStructure(ViewStructure viewStructure) {
        kq3 webContentsAccessibility = getWebContentsAccessibility();
        if (webContentsAccessibility != null) {
            ((WebContentsAccessibilityImpl) webContentsAccessibility).s(viewStructure);
        }
    }

    @Override // android.view.View, org.chromium.content_public.browser.a.InterfaceC0153a
    public final void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
    }

    @Override // android.view.View.OnSystemUiVisibilityChangeListener
    public final void onSystemUiVisibilityChange(int i) {
        Iterator<View.OnSystemUiVisibilityChangeListener> it = this.h.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((View.OnSystemUiVisibilityChangeListener) aVar.next()).onSystemUiVisibilityChange(i);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0036  */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r34v0 */
    /* JADX WARN: Type inference failed for: r34v1 */
    /* JADX WARN: Type inference failed for: r34v2 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v20 */
    /* JADX WARN: Type inference failed for: r3v21 */
    /* JADX WARN: Type inference failed for: r3v24 */
    /* JADX WARN: Type inference failed for: r3v31 */
    /* JADX WARN: Type inference failed for: r3v32 */
    /* JADX WARN: Type inference failed for: r3v36 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouchEvent(android.view.MotionEvent r44) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 511
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.embedder_support.view.ContentView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (!d()) {
            return;
        }
        Iterator<jt3> it = kt3.e(((sm3) getViewEventSink()).e).e.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((jt3) aVar.next()).onWindowFocusChanged(z);
            }
        }
    }

    @Override // android.view.View
    public final boolean performLongClick() {
        return false;
    }

    public void removeOnDragListener(View.OnDragListener onDragListener) {
        this.i.c(onDragListener);
    }

    public void removeOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.g.c(onHierarchyChangeListener);
    }

    public void removeOnSystemUiVisibilityChangeListener(View.OnSystemUiVisibilityChangeListener onSystemUiVisibilityChangeListener) {
        this.h.c(onSystemUiVisibilityChangeListener);
    }

    @Override // android.view.View
    public final void scrollBy(int i, int i2) {
        EventForwarder eventForwarder = getEventForwarder();
        if (eventForwarder != null) {
            float f = i;
            float f2 = i2;
            long j = eventForwarder.b;
            if (j == 0) {
                return;
            }
            N.MMwH$VBb(j, eventForwarder, f, f2);
        }
    }

    @Override // android.view.View
    public final void scrollTo(int i, int i2) {
        EventForwarder eventForwarder = getEventForwarder();
        if (eventForwarder != null) {
            float f = i;
            float f2 = i2;
            long j = eventForwarder.b;
            if (j == 0) {
                return;
            }
            N.M6lTZ5w8(j, eventForwarder, f, f2);
        }
    }

    public void setDeferKeepScreenOnChanges(boolean z) {
        Boolean bool;
        this.n = z;
        if (z || (bool = this.o) == null) {
            return;
        }
        super.setKeepScreenOn(bool.booleanValue());
        this.o = null;
    }

    public void setIsObscuredForAccessibility(boolean z) {
        if (this.f == z) {
            return;
        }
        this.f = z;
        kq3 webContentsAccessibility = getWebContentsAccessibility();
        if (webContentsAccessibility == null) {
            return;
        }
        WebContentsAccessibilityImpl webContentsAccessibilityImpl = (WebContentsAccessibilityImpl) webContentsAccessibility;
        webContentsAccessibilityImpl.G = Boolean.valueOf(this.f);
        webContentsAccessibilityImpl.A(-1, 2048);
    }

    @Override // android.view.View
    public void setKeepScreenOn(boolean z) {
        if (this.n) {
            this.o = Boolean.valueOf(z);
        } else {
            super.setKeepScreenOn(z);
        }
    }

    @Override // android.view.View
    public void setOnDragListener(View.OnDragListener onDragListener) {
        super.setOnDragListener(onDragListener);
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
        if (d()) {
            this.e.setSmartClipResultHandler(handler);
        }
    }

    public void setWebContents(WebContents webContents) {
        boolean zIsFocused = isFocused();
        boolean zHasWindowFocus = hasWindowFocus();
        boolean zIsAttachedToWindow = isAttachedToWindow();
        boolean z = this.f;
        if (zIsFocused) {
            onFocusChanged(false, 2, null);
        }
        if (zHasWindowFocus) {
            onWindowFocusChanged(false);
        }
        if (zIsAttachedToWindow) {
            onDetachedFromWindow();
        }
        if (z) {
            setIsObscuredForAccessibility(false);
        }
        this.e = webContents;
        this.j = null;
        if (zIsFocused) {
            onFocusChanged(true, 2, null);
        }
        if (zHasWindowFocus) {
            onWindowFocusChanged(true);
        }
        if (zIsAttachedToWindow) {
            onAttachedToWindow();
        }
        if (z) {
            setIsObscuredForAccessibility(true);
        }
    }
}
