package org.chromium.content.browser.accessibility;

import J.N;
import android.R;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.SpannableString;
import android.text.style.URLSpan;
import android.util.SparseArray;
import android.util.SparseLongArray;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import defpackage.AbstractC1077kw;
import defpackage.AbstractC1203nA;
import defpackage.Az;
import defpackage.C0590cA;
import defpackage.C1036kA;
import defpackage.C1130lt;
import defpackage.InterfaceC0817gE;
import defpackage.InterfaceC1074kt;
import defpackage.RunnableC1092lA;
import defpackage.Yz;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content.browser.accessibility.captioning.CaptioningController;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.AccessibilitySnapshotNode;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class WebContentsAccessibilityImpl extends AccessibilityNodeProvider implements AccessibilityManager.AccessibilityStateChangeListener, InterfaceC0817gE, Yz, InterfaceC1074kt {
    public static final int U = AbstractC1077kw.b(Resources.getSystem(), "accessibilityActionImeEnter", "id", "android");
    public int A;
    public ViewGroup B;
    public boolean C;
    public boolean D;
    public boolean E;
    public int F;
    public int G;
    public int H;
    public CaptioningController I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public boolean f29J;
    public int K;
    public int L;
    public boolean M;
    public boolean N;
    public boolean O;
    public Runnable T;
    public final WebContentsImpl s;
    public AccessibilityManager t;
    public final Context u;
    public String v;
    public long w;
    public Rect x;
    public boolean y;
    public int z = -1;
    public Set P = new HashSet();
    public SparseLongArray Q = new SparseLongArray();
    public SparseArray R = new SparseArray();
    public SparseArray S = new SparseArray();

    static {
        AbstractC1077kw.b(Resources.getSystem(), "accessibilityActionPressAndHold", "id", "android");
    }

    public WebContentsAccessibilityImpl(WebContents webContents) {
        WebContentsImpl webContentsImpl = (WebContentsImpl) webContents;
        this.s = webContentsImpl;
        ViewGroup containerView = webContentsImpl.D().getContainerView();
        this.B = containerView;
        Context context = containerView.getContext();
        this.u = context;
        this.v = this.s.A;
        this.t = (AccessibilityManager) context.getSystemService("accessibility");
        this.I = new CaptioningController(this.s);
        C0590cA c0590cAP = C0590cA.P(this.s);
        c0590cAP.s.f(this);
        if (c0590cAP.v) {
            onAttachedToWindow();
        }
    }

    private void addAccessibilityNodeInfoActions(AccessibilityNodeInfo accessibilityNodeInfo, int i, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, boolean z16, boolean z17) {
        c(accessibilityNodeInfo, 1024);
        c(accessibilityNodeInfo, 2048);
        c(accessibilityNodeInfo, R.id.accessibilityActionShowOnScreen);
        c(accessibilityNodeInfo, R.id.accessibilityActionContextClick);
        if (z15) {
            c(accessibilityNodeInfo, 256);
            c(accessibilityNodeInfo, 512);
        }
        if (z8 && z9) {
            c(accessibilityNodeInfo, 2097152);
            c(accessibilityNodeInfo, 32768);
            int i2 = U;
            if (i2 != 0) {
                c(accessibilityNodeInfo, i2);
            }
            if (z14) {
                c(accessibilityNodeInfo, com.android.webview.R.animator.design_appbar_state_list_animator);
                c(accessibilityNodeInfo, com.android.webview.R.anim.abc_fade_in);
                c(accessibilityNodeInfo, 16384);
            }
        }
        if (z) {
            c(accessibilityNodeInfo, 4096);
        }
        if (z2) {
            c(accessibilityNodeInfo, 8192);
        }
        if (z3) {
            c(accessibilityNodeInfo, R.id.accessibilityActionScrollUp);
            c(accessibilityNodeInfo, R.id.accessibilityActionPageUp);
        }
        if (z4) {
            c(accessibilityNodeInfo, R.id.accessibilityActionScrollDown);
            c(accessibilityNodeInfo, R.id.accessibilityActionPageDown);
        }
        if (z5) {
            c(accessibilityNodeInfo, R.id.accessibilityActionScrollLeft);
            c(accessibilityNodeInfo, R.id.accessibilityActionPageLeft);
        }
        if (z6) {
            c(accessibilityNodeInfo, R.id.accessibilityActionScrollRight);
            c(accessibilityNodeInfo, R.id.accessibilityActionPageRight);
        }
        if (z10) {
            if (z11) {
                c(accessibilityNodeInfo, 2);
            } else {
                c(accessibilityNodeInfo, 1);
            }
        }
        if (this.G == i) {
            c(accessibilityNodeInfo, 128);
        } else {
            c(accessibilityNodeInfo, 64);
        }
        if (z7) {
            c(accessibilityNodeInfo, 16);
        }
        if (z12) {
            c(accessibilityNodeInfo, com.android.webview.R.bool.abc_action_bar_embed_tabs);
        }
        if (z13) {
            c(accessibilityNodeInfo, com.android.webview.R.font.accent_font);
        }
        if (z16) {
            c(accessibilityNodeInfo, R.id.accessibilityActionSetProgress);
        }
    }

    private void addAccessibilityNodeInfoChild(AccessibilityNodeInfo accessibilityNodeInfo, int i) {
        accessibilityNodeInfo.addChild(this.B, i);
    }

    private void announceLiveRegionText(String str) {
        this.B.announceForAccessibility(str);
    }

    private void finishGranularityMoveNext(String str, boolean z, int i, int i2) {
        AccessibilityEvent accessibilityEventD = d(this.H, 8192);
        if (accessibilityEventD == null) {
            return;
        }
        AccessibilityEvent accessibilityEventD2 = d(this.H, com.android.webview.R.animator.design_appbar_state_list_animator);
        if (accessibilityEventD2 == null) {
            accessibilityEventD.recycle();
            return;
        }
        if (z) {
            if (!this.f29J) {
                this.f29J = true;
                this.K = i;
            }
            accessibilityEventD.setFromIndex(this.K);
            accessibilityEventD.setToIndex(i2);
        } else {
            this.f29J = false;
            this.K = i2;
            accessibilityEventD.setFromIndex(i2);
            accessibilityEventD.setToIndex(i2);
        }
        this.L = i2;
        accessibilityEventD.setItemCount(str.length());
        A(accessibilityEventD);
        accessibilityEventD2.setFromIndex(i);
        accessibilityEventD2.setToIndex(i2);
        accessibilityEventD2.setItemCount(str.length());
        accessibilityEventD2.setMovementGranularity(this.F);
        accessibilityEventD2.setContentDescription(str);
        accessibilityEventD2.setAction(256);
        ViewGroup viewGroup = this.B;
        viewGroup.requestSendAccessibilityEvent(viewGroup, accessibilityEventD);
        ViewGroup viewGroup2 = this.B;
        viewGroup2.requestSendAccessibilityEvent(viewGroup2, accessibilityEventD2);
        this.M = true;
    }

    private void finishGranularityMovePrevious(String str, boolean z, int i, int i2) {
        AccessibilityEvent accessibilityEventD = d(this.H, 8192);
        if (accessibilityEventD == null) {
            return;
        }
        AccessibilityEvent accessibilityEventD2 = d(this.H, com.android.webview.R.animator.design_appbar_state_list_animator);
        if (accessibilityEventD2 == null) {
            accessibilityEventD.recycle();
            return;
        }
        if (z) {
            if (!this.f29J) {
                this.f29J = true;
                this.K = i2;
            }
            accessibilityEventD.setFromIndex(this.K);
            accessibilityEventD.setToIndex(i);
        } else {
            this.f29J = false;
            this.K = i;
            accessibilityEventD.setFromIndex(i);
            accessibilityEventD.setToIndex(i);
        }
        this.L = i;
        accessibilityEventD.setItemCount(str.length());
        A(accessibilityEventD);
        accessibilityEventD2.setFromIndex(i);
        accessibilityEventD2.setToIndex(i2);
        accessibilityEventD2.setItemCount(str.length());
        accessibilityEventD2.setMovementGranularity(this.F);
        accessibilityEventD2.setContentDescription(str);
        accessibilityEventD2.setAction(512);
        ViewGroup viewGroup = this.B;
        viewGroup.requestSendAccessibilityEvent(viewGroup, accessibilityEventD);
        ViewGroup viewGroup2 = this.B;
        viewGroup2.requestSendAccessibilityEvent(viewGroup2, accessibilityEventD2);
        this.M = true;
    }

    private int getAccessibilityServiceEventTypeMask() {
        Iterator<AccessibilityServiceInfo> it = this.t.getEnabledAccessibilityServiceList(-1).iterator();
        int i = 0;
        while (it.hasNext()) {
            i |= it.next().eventTypes;
        }
        return i;
    }

    private int getAccessibilityServiceFeedbackTypeMask() {
        Iterator<AccessibilityServiceInfo> it = this.t.getEnabledAccessibilityServiceList(-1).iterator();
        int i = 0;
        while (it.hasNext()) {
            i |= it.next().feedbackType;
        }
        return i;
    }

    private int getAccessibilityServiceFlagsMask() {
        Iterator<AccessibilityServiceInfo> it = this.t.getEnabledAccessibilityServiceList(-1).iterator();
        int i = 0;
        while (it.hasNext()) {
            i |= it.next().flags;
        }
        return i;
    }

    private void handleCheckStateChanged(int i) {
        if (this.G == i) {
            v(i, 1);
        }
    }

    private void handleClicked(int i) {
        v(i, 1);
    }

    private void handleContentChanged(int i) {
        int iMI8pU34f = N.MI8pU34f(this.w, this);
        if (iMI8pU34f == this.A) {
            v(i, 2048);
        } else {
            this.A = iMI8pU34f;
            w();
        }
    }

    private void handleEditableTextChanged(int i) {
        v(i, 16);
    }

    private void handleFocusChanged(int i) {
        if (this.G == -1) {
            return;
        }
        v(i, 8);
        q(i);
    }

    private void handleHover(int i) {
        if (this.z != i && this.y) {
            v(i, 128);
            int i2 = this.z;
            if (i2 != -1) {
                v(i2, 256);
            }
            this.z = i;
        }
    }

    private void handleNavigate() {
        this.G = -1;
        this.x = null;
        this.C = false;
        w();
    }

    private void handlePageLoaded(int i) {
    }

    private void handleScrollPositionChanged(int i) {
        v(i, 4096);
    }

    private void handleScrolledToAnchor(int i) {
        q(i);
    }

    private void handleSliderChanged(int i) {
        if (this.G == i) {
            v(i, 4);
        } else {
            v(i, 4096);
        }
    }

    private void handleTextSelectionChanged(int i) {
        v(i, 8192);
    }

    public static WebContentsAccessibilityImpl i(WebContents webContents) {
        return (WebContentsAccessibilityImpl) ((WebContentsImpl) webContents).S(WebContentsAccessibilityImpl.class, AbstractC1203nA.a);
    }

    private void notifyFrameInfoInitialized() {
        if (this.E) {
            return;
        }
        this.E = true;
        w();
    }

    private boolean onHoverEvent(int i) {
        if (!m()) {
            return false;
        }
        if (i != 10) {
            this.y = true;
            this.C = true;
            return true;
        }
        this.y = false;
        int i2 = this.z;
        if (i2 != -1) {
            v(i2, 256);
            this.z = -1;
        }
        if (this.D) {
            N.MB302_MP(this.w, this, this.G);
        }
        this.D = false;
        return true;
    }

    private void sendDelayedWindowContentChangedEvent() {
        if (this.T != null) {
            return;
        }
        RunnableC1092lA runnableC1092lA = new RunnableC1092lA(this);
        this.T = runnableC1092lA;
        this.B.postDelayed(runnableC1092lA, 500L);
    }

    private void setAccessibilityEventBooleanAttributes(AccessibilityEvent accessibilityEvent, boolean z, boolean z2, boolean z3, boolean z4) {
        accessibilityEvent.setChecked(z);
        accessibilityEvent.setEnabled(z2);
        accessibilityEvent.setPassword(z3);
        accessibilityEvent.setScrollable(z4);
    }

    private void setAccessibilityEventClassName(AccessibilityEvent accessibilityEvent, String str) {
        accessibilityEvent.setClassName(str);
    }

    private void setAccessibilityEventListAttributes(AccessibilityEvent accessibilityEvent, int i, int i2) {
        accessibilityEvent.setCurrentItemIndex(i);
        accessibilityEvent.setItemCount(i2);
    }

    private void setAccessibilityEventScrollAttributes(AccessibilityEvent accessibilityEvent, int i, int i2, int i3, int i4) {
        accessibilityEvent.setScrollX(i);
        accessibilityEvent.setScrollY(i2);
        accessibilityEvent.setMaxScrollX(i3);
        accessibilityEvent.setMaxScrollY(i4);
    }

    private void setAccessibilityEventSelectionAttrs(AccessibilityEvent accessibilityEvent, int i, int i2, int i3, String str) {
        accessibilityEvent.setFromIndex(i);
        accessibilityEvent.setToIndex(i2);
        accessibilityEvent.setItemCount(i3);
        accessibilityEvent.getText().add(str);
    }

    private void setAccessibilityEventTextChangedAttrs(AccessibilityEvent accessibilityEvent, int i, int i2, int i3, String str, String str2) {
        accessibilityEvent.setFromIndex(i);
        accessibilityEvent.setAddedCount(i2);
        accessibilityEvent.setRemovedCount(i3);
        accessibilityEvent.setBeforeText(str);
        accessibilityEvent.getText().add(str2);
    }

    private void setAccessibilityNodeInfoBooleanAttributes(AccessibilityNodeInfo accessibilityNodeInfo, int i, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10) {
        accessibilityNodeInfo.setCheckable(z);
        accessibilityNodeInfo.setChecked(z2);
        accessibilityNodeInfo.setClickable(z3);
        accessibilityNodeInfo.setEnabled(z4);
        accessibilityNodeInfo.setFocusable(z5);
        accessibilityNodeInfo.setFocused(z6);
        accessibilityNodeInfo.setPassword(z7);
        accessibilityNodeInfo.setScrollable(z8);
        accessibilityNodeInfo.setSelected(z9);
        accessibilityNodeInfo.setVisibleToUser(z10);
        accessibilityNodeInfo.setMovementGranularities(7);
        if (this.G == i) {
            accessibilityNodeInfo.setAccessibilityFocused(true);
        } else {
            accessibilityNodeInfo.setAccessibilityFocused(false);
        }
    }

    private void setAccessibilityNodeInfoClassName(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        accessibilityNodeInfo.setClassName(str);
    }

    private void setAccessibilityNodeInfoLocation(AccessibilityNodeInfo accessibilityNodeInfo, int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z) {
        Rect rect = new Rect(i4, i5, i4 + i6, i5 + i7);
        if (z) {
            rect.offset(0, (int) this.s.y.k);
        }
        accessibilityNodeInfo.setBoundsInParent(rect);
        Rect rect2 = new Rect(i2, i3, i6 + i2, i7 + i3);
        g(rect2);
        accessibilityNodeInfo.setBoundsInScreen(rect2);
        if (i != this.G || i == this.A) {
            return;
        }
        Rect rect3 = this.x;
        if (rect3 == null) {
            this.x = rect2;
        } else {
            if (rect3.equals(rect2)) {
                return;
            }
            this.x = rect2;
            r(i);
        }
    }

    private void setAccessibilityNodeInfoParent(AccessibilityNodeInfo accessibilityNodeInfo, int i) {
        accessibilityNodeInfo.setParent(this.B, i);
    }

    private void setAccessibilityNodeInfoText(AccessibilityNodeInfo accessibilityNodeInfo, String str, boolean z, boolean z2, String str2, int[] iArr, int[] iArr2, String[] strArr) {
        CharSequence charSequenceF = f(str, z2, str2, iArr, iArr2, strArr);
        if (z) {
            accessibilityNodeInfo.setContentDescription(charSequenceF);
        } else {
            accessibilityNodeInfo.setText(charSequenceF);
        }
    }

    public final void A(AccessibilityEvent accessibilityEvent) {
        if (N.MCMbXu4W(this.w, this, this.H) && N.M8UuMlLD(this.w, this, this.H)) {
            N.MVuu0R4P(this.w, this, this.H, accessibilityEvent.getFromIndex(), accessibilityEvent.getToIndex());
        }
    }

    public void B(boolean z) {
        if (z) {
            this.N = true;
            this.O = this.t.isTouchExplorationEnabled();
        } else {
            this.N = false;
            this.O = false;
        }
    }

    @Override // defpackage.InterfaceC0771fO
    public void C(Display.Mode mode) {
    }

    @Override // defpackage.Yz
    public void b(boolean z, boolean z2) {
    }

    public void c(AccessibilityNodeInfo accessibilityNodeInfo, int i) {
        if (i > 2097152) {
            return;
        }
        accessibilityNodeInfo.addAction(i);
    }

    public void clearNodeInfoCacheForGivenId(int i) {
        if (this.S.get(i) != null) {
            ((AccessibilityNodeInfo) this.S.get(i)).recycle();
            this.S.remove(i);
        }
    }

    @Override // android.view.accessibility.AccessibilityNodeProvider
    public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
        if (!m()) {
            return null;
        }
        int iMI8pU34f = N.MI8pU34f(this.w, this);
        if (i == -1) {
            AccessibilityNodeInfo accessibilityNodeInfoObtain = AccessibilityNodeInfo.obtain(this.B);
            AccessibilityNodeInfo accessibilityNodeInfoObtain2 = AccessibilityNodeInfo.obtain(this.B);
            this.B.onInitializeAccessibilityNodeInfo(accessibilityNodeInfoObtain2);
            Rect rect = new Rect();
            accessibilityNodeInfoObtain2.getBoundsInParent(rect);
            accessibilityNodeInfoObtain.setBoundsInParent(rect);
            accessibilityNodeInfoObtain2.getBoundsInScreen(rect);
            accessibilityNodeInfoObtain.setBoundsInScreen(rect);
            Object parentForAccessibility = this.B.getParentForAccessibility();
            if (parentForAccessibility instanceof View) {
                accessibilityNodeInfoObtain.setParent((View) parentForAccessibility);
            }
            accessibilityNodeInfoObtain.setVisibleToUser(accessibilityNodeInfoObtain2.isVisibleToUser());
            accessibilityNodeInfoObtain.setEnabled(accessibilityNodeInfoObtain2.isEnabled());
            accessibilityNodeInfoObtain.setPackageName(accessibilityNodeInfoObtain2.getPackageName());
            accessibilityNodeInfoObtain.setClassName(accessibilityNodeInfoObtain2.getClassName());
            if (n()) {
                accessibilityNodeInfoObtain.addChild(this.B, iMI8pU34f);
            }
            return accessibilityNodeInfoObtain;
        }
        if (!n()) {
            return null;
        }
        if (this.S.get(i) != null) {
            AccessibilityNodeInfo accessibilityNodeInfoObtain3 = AccessibilityNodeInfo.obtain((AccessibilityNodeInfo) this.S.get(i));
            if (N.MZ7sDynr(this.w, this, accessibilityNodeInfoObtain3, i)) {
                return accessibilityNodeInfoObtain3;
            }
            ((AccessibilityNodeInfo) this.S.get(i)).recycle();
            this.S.remove(i);
            return null;
        }
        AccessibilityNodeInfo accessibilityNodeInfoObtain4 = AccessibilityNodeInfo.obtain(this.B);
        accessibilityNodeInfoObtain4.setPackageName(this.u.getPackageName());
        accessibilityNodeInfoObtain4.setSource(this.B, i);
        if (i == iMI8pU34f) {
            accessibilityNodeInfoObtain4.setParent(this.B);
        }
        if (N.MJGtghd9(this.w, this, accessibilityNodeInfoObtain4, i)) {
            return accessibilityNodeInfoObtain4;
        }
        accessibilityNodeInfoObtain4.recycle();
        return null;
    }

    public final AccessibilityEvent d(int i, int i2) {
        if (!m() || !n() || !N.MTBNGzHX(this.w, this, i)) {
            return null;
        }
        this.B.postInvalidate();
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i2);
        accessibilityEventObtain.setPackageName(this.u.getPackageName());
        accessibilityEventObtain.setSource(this.B, i);
        if (N.M2E1dEU9(this.w, this, accessibilityEventObtain, i, i2)) {
            return accessibilityEventObtain;
        }
        accessibilityEventObtain.recycle();
        return null;
    }

    @Override // defpackage.InterfaceC0771fO
    public void e(float f) {
    }

    public CharSequence f(String str, boolean z, String str2, int[] iArr, int[] iArr2, String[] strArr) {
        if (!z) {
            return str;
        }
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new URLSpan(""), 0, spannableString.length(), 0);
        return spannableString;
    }

    @Override // android.view.accessibility.AccessibilityNodeProvider
    public List findAccessibilityNodeInfosByText(String str, int i) {
        return new ArrayList();
    }

    public void g(Rect rect) {
        Az az = this.s.y;
        rect.offset(-((int) az.a), -((int) az.b));
        rect.left = (int) az.a(rect.left);
        rect.top = (int) az.a(rect.top);
        rect.bottom = (int) az.a(rect.bottom);
        rect.right = (int) az.a(rect.right);
        rect.offset(0, (int) az.k);
        int[] iArr = new int[2];
        this.B.getLocationOnScreen(iArr);
        rect.offset(iArr[0], iArr[1]);
        int i = iArr[1] + ((int) az.k);
        int iB = az.b() + i;
        if (rect.top < i) {
            rect.top = i;
        }
        if (rect.bottom > iB) {
            rect.bottom = iB;
        }
    }

    public int getAccessibilityServiceCapabilitiesMask() {
        return 0;
    }

    public final void h(ViewStructure viewStructure, AccessibilitySnapshotNode accessibilitySnapshotNode, boolean z) {
        viewStructure.setClassName(accessibilitySnapshotNode.h);
        if (accessibilitySnapshotNode.p) {
            viewStructure.setText(accessibilitySnapshotNode.g, accessibilitySnapshotNode.q, accessibilitySnapshotNode.r);
        } else {
            viewStructure.setText(accessibilitySnapshotNode.g);
        }
        Az az = this.s.y;
        int iA = (int) az.a(accessibilitySnapshotNode.a);
        int iA2 = (int) az.a(accessibilitySnapshotNode.b);
        int iA3 = (int) az.a(accessibilitySnapshotNode.c);
        int iA4 = (int) az.a(accessibilitySnapshotNode.d);
        Rect rect = new Rect(iA, iA2, iA + iA3, iA2 + iA4);
        if (accessibilitySnapshotNode.e) {
            rect.offset(0, (int) az.k);
            if (!z) {
                rect.offset(-((int) az.c()), -((int) az.d()));
            }
        }
        viewStructure.setDimens(rect.left, rect.top, 0, 0, iA3, iA4);
        viewStructure.setChildCount(accessibilitySnapshotNode.s.size());
        if (accessibilitySnapshotNode.i) {
            viewStructure.setTextStyle(az.a(accessibilitySnapshotNode.f), accessibilitySnapshotNode.j, accessibilitySnapshotNode.k, (accessibilitySnapshotNode.l ? 1 : 0) | (accessibilitySnapshotNode.m ? 2 : 0) | (accessibilitySnapshotNode.n ? 4 : 0) | (accessibilitySnapshotNode.o ? 8 : 0));
        }
        for (int i = 0; i < accessibilitySnapshotNode.s.size(); i++) {
            h(viewStructure.asyncNewChild(i), (AccessibilitySnapshotNode) accessibilitySnapshotNode.s.get(i), true);
        }
        viewStructure.asyncCommit();
    }

    @Override // defpackage.InterfaceC0771fO
    public void j(float f) {
    }

    public AccessibilityNodeProvider k() {
        if (!o()) {
            if (!this.N) {
                return null;
            }
            this.w = N.MjYAnP1s(this, this.s);
            s();
        }
        if (o() ? N.Mr9fGid2(this.w, this) : false) {
            return this;
        }
        N.Mg$cuhZc(this.w, this);
        return null;
    }

    public final Bundle l(AccessibilityEvent accessibilityEvent) {
        Bundle bundle = (Bundle) accessibilityEvent.getParcelableData();
        if (bundle != null) {
            return bundle;
        }
        Bundle bundle2 = new Bundle();
        accessibilityEvent.setParcelableData(bundle2);
        return bundle2;
    }

    public boolean m() {
        return o() && this.t.isEnabled();
    }

    public final boolean n() {
        WebContentsImpl webContentsImpl = this.s;
        if (webContentsImpl == null) {
            return true;
        }
        Az az = webContentsImpl.y;
        return (((double) az.c) == 0.0d && ((double) az.d) == 0.0d) ? false : true;
    }

    public boolean o() {
        return this.w != 0;
    }

    @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
    public void onAccessibilityStateChanged(boolean z) {
        B(z);
    }

    public void onAttachedToWindow() {
        this.t.addAccessibilityStateChangeListener(this);
        B(this.t.isEnabled());
        CaptioningController captioningController = this.I;
        captioningController.a.a(captioningController);
    }

    @Override // defpackage.Yz
    public void onConfigurationChanged(Configuration configuration) {
    }

    public void onDetachedFromWindow() {
        this.t.removeAccessibilityStateChangeListener(this);
        CaptioningController captioningController = this.I;
        captioningController.a.c(captioningController);
    }

    public void onNativeObjectDestroyed() {
        this.w = 0L;
    }

    @Override // defpackage.Yz
    public void onWindowFocusChanged(boolean z) {
    }

    public final boolean p(int i, String str, boolean z) {
        int iMavOU0SM = N.MavOU0SM(this.w, this, i, str, z);
        if (iMavOU0SM == 0) {
            return false;
        }
        q(iMavOU0SM);
        N.MB302_MP(this.w, this, this.G);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:149:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0223  */
    @Override // android.view.accessibility.AccessibilityNodeProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean performAction(int r11, int r12, android.os.Bundle r13) {
        /*
            Method dump skipped, instruction units count: 690
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl.performAction(int, int, android.os.Bundle):boolean");
    }

    public final boolean q(int i) {
        int i2 = this.G;
        if (i == i2) {
            return false;
        }
        N.MPQKLw45(this.w, this, i2, i);
        this.G = i;
        this.x = null;
        this.H = i;
        this.F = 0;
        this.f29J = false;
        this.K = -1;
        this.L = N.MhMiVz6m(this.w, this, i);
        this.M = false;
        if (N.M5uHFthk(this.w, this, this.G)) {
            throw null;
        }
        v(this.G, 32768);
        return true;
    }

    public final void r(int i) {
        if (i == this.G) {
            v(i, com.android.webview.R.anim.abc_fade_in);
            this.G = -1;
        }
        q(i);
    }

    public void s() {
        this.G = -1;
        this.H = -1;
        this.y = false;
        this.A = -1;
    }

    public void setAccessibilityEventCollectionInfo(AccessibilityEvent accessibilityEvent, int i, int i2, boolean z) {
        Bundle bundleL = l(accessibilityEvent);
        bundleL.putInt("AccessibilityNodeInfo.CollectionInfo.rowCount", i);
        bundleL.putInt("AccessibilityNodeInfo.CollectionInfo.columnCount", i2);
        bundleL.putBoolean("AccessibilityNodeInfo.CollectionInfo.hierarchical", z);
    }

    public void setAccessibilityEventCollectionItemInfo(AccessibilityEvent accessibilityEvent, int i, int i2, int i3, int i4) {
        Bundle bundleL = l(accessibilityEvent);
        bundleL.putInt("AccessibilityNodeInfo.CollectionItemInfo.rowIndex", i);
        bundleL.putInt("AccessibilityNodeInfo.CollectionItemInfo.rowSpan", i2);
        bundleL.putInt("AccessibilityNodeInfo.CollectionItemInfo.columnIndex", i3);
        bundleL.putInt("AccessibilityNodeInfo.CollectionItemInfo.columnSpan", i4);
    }

    public void setAccessibilityEventHeadingFlag(AccessibilityEvent accessibilityEvent, boolean z) {
        l(accessibilityEvent).putBoolean("AccessibilityNodeInfo.CollectionItemInfo.heading", z);
    }

    public void setAccessibilityEventLollipopAttributes(AccessibilityEvent accessibilityEvent, boolean z, boolean z2, boolean z3, boolean z4, int i, int i2) {
        Bundle bundleL = l(accessibilityEvent);
        bundleL.putBoolean("AccessibilityNodeInfo.canOpenPopup", z);
        bundleL.putBoolean("AccessibilityNodeInfo.contentInvalid", z2);
        bundleL.putBoolean("AccessibilityNodeInfo.dismissable", z3);
        bundleL.putBoolean("AccessibilityNodeInfo.multiLine", z4);
        bundleL.putInt("AccessibilityNodeInfo.inputType", i);
        bundleL.putInt("AccessibilityNodeInfo.liveRegion", i2);
    }

    public void setAccessibilityEventRangeInfo(AccessibilityEvent accessibilityEvent, int i, float f, float f2, float f3) {
        Bundle bundleL = l(accessibilityEvent);
        bundleL.putInt("AccessibilityNodeInfo.RangeInfo.type", i);
        bundleL.putFloat("AccessibilityNodeInfo.RangeInfo.min", f);
        bundleL.putFloat("AccessibilityNodeInfo.RangeInfo.max", f2);
        bundleL.putFloat("AccessibilityNodeInfo.RangeInfo.current", f3);
    }

    public void setAccessibilityNodeInfoCollectionInfo(AccessibilityNodeInfo accessibilityNodeInfo, int i, int i2, boolean z) {
    }

    public void setAccessibilityNodeInfoCollectionItemInfo(AccessibilityNodeInfo accessibilityNodeInfo, int i, int i2, int i3, int i4, boolean z) {
    }

    public void setAccessibilityNodeInfoKitKatAttributes(AccessibilityNodeInfo accessibilityNodeInfo, boolean z, boolean z2, String str, String str2, String str3, int i, int i2, boolean z3, boolean z4, String str4) {
    }

    public void setAccessibilityNodeInfoLollipopAttributes(AccessibilityNodeInfo accessibilityNodeInfo, boolean z, boolean z2, boolean z3, boolean z4, int i, int i2, String str) {
    }

    public void setAccessibilityNodeInfoOAttributes(AccessibilityNodeInfo accessibilityNodeInfo, boolean z) {
    }

    public void setAccessibilityNodeInfoPaneTitle(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
    }

    public void setAccessibilityNodeInfoRangeInfo(AccessibilityNodeInfo accessibilityNodeInfo, int i, float f, float f2, float f3) {
    }

    public void setAccessibilityNodeInfoViewIdResourceName(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
    }

    public boolean shouldExposePasswordText() {
        ContentResolver contentResolver = this.u.getContentResolver();
        return Build.VERSION.SDK_INT >= 26 ? Settings.System.getInt(contentResolver, "show_password", 1) == 1 : Settings.Secure.getInt(contentResolver, "speak_password", 0) == 1;
    }

    public boolean shouldRespectDisplayedPasswordText() {
        return Build.VERSION.SDK_INT >= 26;
    }

    public void t(ViewStructure viewStructure, boolean z) {
        if (this.s.l()) {
            viewStructure.setChildCount(0);
            return;
        }
        viewStructure.setChildCount(1);
        ViewStructure viewStructureAsyncNewChild = viewStructure.asyncNewChild(0);
        WebContentsImpl webContentsImpl = this.s;
        C1036kA c1036kA = new C1036kA(this, viewStructureAsyncNewChild, z);
        webContentsImpl.O();
        N.M16eLpU9(webContentsImpl.t, webContentsImpl, c1036kA);
    }

    @Override // defpackage.Yz
    public void u(WindowAndroid windowAndroid) {
        C0590cA.P(this.s).s.g(this);
        C1130lt c1130ltU = this.s.U();
        if (c1130ltU != null) {
            c1130ltU.b();
            C1130lt.a(true);
            if (!c1130ltU.b.containsKey(WebContentsAccessibilityImpl.class)) {
                throw new IllegalStateException("UserData for the key is not present.");
            }
        }
        long j = this.w;
        if (j != 0) {
            N.MxGfnb$m(j);
        }
    }

    public final void v(final int i, final int i2) {
        if (i == -1) {
            this.B.sendAccessibilityEvent(i2);
            return;
        }
        if (this.M && i2 == 8192) {
            this.M = false;
            return;
        }
        if (!this.P.contains(Integer.valueOf(i2))) {
            AccessibilityEvent accessibilityEventD = d(i, i2);
            if (accessibilityEventD == null) {
                return;
            }
            ViewGroup viewGroup = this.B;
            viewGroup.requestSendAccessibilityEvent(viewGroup, accessibilityEventD);
            return;
        }
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        if (timeInMillis - this.Q.get(i2, 0L) < 100) {
            this.B.removeCallbacks((Runnable) this.R.get(i2));
            Runnable runnable = new Runnable(this, i, i2) { // from class: jA
                public final WebContentsAccessibilityImpl s;
                public final int t;
                public final int u;

                {
                    this.s = this;
                    this.t = i;
                    this.u = i2;
                }

                @Override // java.lang.Runnable
                public void run() {
                    WebContentsAccessibilityImpl webContentsAccessibilityImpl = this.s;
                    int i3 = this.t;
                    int i4 = this.u;
                    AccessibilityEvent accessibilityEventD2 = webContentsAccessibilityImpl.d(i3, i4);
                    if (accessibilityEventD2 != null) {
                        ViewGroup viewGroup2 = webContentsAccessibilityImpl.B;
                        viewGroup2.requestSendAccessibilityEvent(viewGroup2, accessibilityEventD2);
                        webContentsAccessibilityImpl.Q.put(i4, Calendar.getInstance().getTimeInMillis());
                    }
                    webContentsAccessibilityImpl.B.removeCallbacks((Runnable) webContentsAccessibilityImpl.R.get(i4));
                    webContentsAccessibilityImpl.R.remove(i4);
                }
            };
            this.B.postDelayed(runnable, (this.Q.get(i2, 0L) + 100) - timeInMillis);
            this.R.put(i2, runnable);
            return;
        }
        AccessibilityEvent accessibilityEventD2 = d(i, i2);
        if (accessibilityEventD2 == null) {
            return;
        }
        ViewGroup viewGroup2 = this.B;
        viewGroup2.requestSendAccessibilityEvent(viewGroup2, accessibilityEventD2);
        this.B.removeCallbacks((Runnable) this.R.get(i2));
        this.R.remove(i2);
        this.Q.put(i2, timeInMillis);
    }

    public final void w() {
        Runnable runnable = this.T;
        if (runnable != null) {
            this.B.removeCallbacks(runnable);
            this.T = null;
        }
        this.B.sendAccessibilityEvent(2048);
    }

    @Override // defpackage.InterfaceC0771fO
    public void x(int i) {
    }

    @Override // defpackage.InterfaceC0771fO
    public void y(List list) {
    }

    public final void z(int i) {
        this.F = i;
        if (N.MCMbXu4W(this.w, this, this.G) && N.M8UuMlLD(this.w, this, this.G)) {
            if (this.K == -1) {
                this.K = N.MnVi6Frs(this.w, this, this.G);
            }
            if (this.L == -1) {
                this.L = N.Mxt_kc4Q(this.w, this, this.G);
            }
        }
    }
}
