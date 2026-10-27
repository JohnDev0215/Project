package org.chromium.content.browser.accessibility;

import J.N;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ReceiverCallNotAllowedException;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.bydauto_test.TestVehicleHealthManagerImplCallback;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.autofill.AutofillManager;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import defpackage.C0173a;
import defpackage.C0180d0;
import defpackage.C0218x;
import defpackage.an2;
import defpackage.cm;
import defpackage.dm;
import defpackage.es3;
import defpackage.i22;
import defpackage.jt3;
import defpackage.kq3;
import defpackage.kt3;
import defpackage.lk3;
import defpackage.lq3;
import defpackage.mk3;
import defpackage.no0;
import defpackage.nx0;
import defpackage.oj2;
import defpackage.xz;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.chromium.base.TraceEvent;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content.browser.accessibility.AccessibilityNodeInfoBuilder;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content.browser.accessibility.a;
import org.chromium.content.browser.accessibility.b;
import org.chromium.content.browser.accessibility.c;
import org.chromium.content.browser.accessibility.captioning.CaptioningController;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.accessibility.AccessibilityState;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes3.dex */
public class WebContentsAccessibilityImpl extends C0180d0 implements kq3, jt3, lk3, AccessibilityState.a {
    public String A;
    public final AccessibilityNodeInfoBuilder B;
    public final C0218x C;
    public boolean D;
    public boolean E;
    public boolean F;
    public Boolean G;
    public final org.chromium.content.browser.accessibility.b I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public String f89J;
    public d K;
    public final org.chromium.content.browser.accessibility.c M;
    public boolean N;
    public int O;
    public final org.chromium.content.browser.accessibility.a f;
    public final AccessibilityManager g;
    public Context h;
    public final String i;
    public long j;
    public boolean k;
    public int m;
    public final View n;
    public boolean o;
    public boolean p;
    public boolean q;
    public int r;
    public int s;
    public int u;
    public View v;
    public final CaptioningController w;
    public boolean x;
    public int y;
    public int z;
    public int l = -1;
    public int t = -1;
    public final SparseArray<AccessibilityNodeInfoCompat> H = new SparseArray<>();
    public final HashSet L = new HashSet();

    public class a implements AccessibilityNodeInfoBuilder.a {
        public a() {
        }
    }

    public class b implements c.a {
        public b() {
        }
    }

    public class c implements b.a {
        public c() {
        }

        public final boolean a(int i, int i2) {
            WebContentsAccessibilityImpl webContentsAccessibilityImpl = WebContentsAccessibilityImpl.this;
            AccessibilityEvent accessibilityEventK = webContentsAccessibilityImpl.k(i, i2);
            if (accessibilityEventK == null) {
                return false;
            }
            webContentsAccessibilityImpl.x(accessibilityEventK);
            if (i2 != 128) {
                return true;
            }
            AccessibilityEvent accessibilityEventK2 = webContentsAccessibilityImpl.k(webContentsAccessibilityImpl.l, 256);
            if (accessibilityEventK2 != null) {
                webContentsAccessibilityImpl.x(accessibilityEventK2);
                webContentsAccessibilityImpl.l = i;
                return true;
            }
            if (i == -1 || webContentsAccessibilityImpl.l == i) {
                return true;
            }
            webContentsAccessibilityImpl.l = i;
            return true;
        }
    }

    public class d extends BroadcastReceiver {
        public d() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            WebContentsAccessibilityImpl.this.f89J = Locale.getDefault().toLanguageTag();
        }
    }

    public WebContentsAccessibilityImpl(org.chromium.content.browser.accessibility.a aVar) {
        TraceEvent.q("WebContentsAccessibilityImpl.ctor", null);
        this.f = aVar;
        View viewE = aVar.e();
        this.n = viewE;
        this.h = viewE.getContext();
        this.i = aVar.a();
        this.g = (AccessibilityManager) this.h.getSystemService("accessibility");
        WebContents webContentsC = aVar.c();
        if (webContentsC != null) {
            this.w = new CaptioningController(webContentsC);
            kt3.e(webContentsC).b(this);
            webContentsC.u().d.a(this);
        }
        aVar.f(new es3(28, this));
        AccessibilityState.h.add(this);
        AccessibilityNodeInfoBuilder accessibilityNodeInfoBuilder = new AccessibilityNodeInfoBuilder(new a());
        this.B = accessibilityNodeInfoBuilder;
        this.M = new org.chromium.content.browser.accessibility.c(new b());
        HashMap map = new HashMap();
        map.put(4096, 100);
        map.put(2048, 100);
        map.put(128, 50);
        HashSet hashSet = new HashSet();
        hashSet.add(128);
        this.I = new org.chromium.content.browser.accessibility.b(new c(), map, hashSet, new HashSet());
        this.C = new C0218x();
        if (aVar.g() != 0) {
            this.j = N.M_XV3Nwg(this, aVar.g(), accessibilityNodeInfoBuilder);
            r();
        }
        try {
            AutofillManager autofillManager = (AutofillManager) this.h.getSystemService(AutofillManager.class);
            if (autofillManager != null && autofillManager.isEnabled()) {
                l();
            }
        } catch (Exception unused) {
            Log.e("cr_A11yImpl", "AutofillManager did not resolve before time limit.");
        }
        TraceEvent.C("WebContentsAccessibilityImpl.ctor");
    }

    public final void A(final int i, final int i2) {
        if (i == -1) {
            this.n.sendAccessibilityEvent(i2);
            return;
        }
        if (this.D && i2 == 8192) {
            this.D = false;
            return;
        }
        this.C.a++;
        final org.chromium.content.browser.accessibility.b bVar = this.I;
        if (!bVar.g || bVar.c.contains(Integer.valueOf(i2))) {
            Integer numValueOf = Integer.valueOf(i2);
            Map<Integer, Integer> map = bVar.a;
            boolean zContainsKey = map.containsKey(numValueOf);
            b.a aVar = bVar.f;
            if (!zContainsKey) {
                ((c) aVar).a(i, i2);
                return;
            }
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            final long j = bVar.b.contains(Integer.valueOf(i2)) ? i2 : (((long) i) << 32) | ((long) i2);
            HashMap map2 = bVar.d;
            Object obj = map2.get(Long.valueOf(j));
            HashMap map3 = bVar.e;
            if (obj == null || timeInMillis - ((Long) map2.get(Long.valueOf(j))).longValue() >= map.get(Integer.valueOf(i2)).intValue()) {
                c cVar = (c) aVar;
                if (cVar.a(i, i2)) {
                    map2.put(Long.valueOf(j), Long.valueOf(timeInMillis));
                }
                WebContentsAccessibilityImpl.this.n.removeCallbacks((Runnable) map3.get(Long.valueOf(j)));
                map3.remove(Long.valueOf(j));
                return;
            }
            c cVar2 = (c) aVar;
            WebContentsAccessibilityImpl.this.n.removeCallbacks((Runnable) map3.get(Long.valueOf(j)));
            Runnable runnable = new Runnable() { // from class: w
                @Override // java.lang.Runnable
                public final void run() {
                    b bVar2 = bVar;
                    WebContentsAccessibilityImpl.c cVar3 = (WebContentsAccessibilityImpl.c) bVar2.f;
                    boolean zA = cVar3.a(i, i2);
                    long j2 = j;
                    if (zA) {
                        bVar2.d.put(Long.valueOf(j2), Long.valueOf(Calendar.getInstance().getTimeInMillis()));
                    }
                    HashMap map4 = bVar2.e;
                    WebContentsAccessibilityImpl.this.n.removeCallbacks((Runnable) map4.get(Long.valueOf(j2)));
                    map4.remove(Long.valueOf(j2));
                }
            };
            WebContentsAccessibilityImpl.this.n.postDelayed(runnable, (((Long) map2.get(Long.valueOf(j))).longValue() + ((long) map.get(Integer.valueOf(i2)).intValue())) - timeInMillis);
            map3.put(Long.valueOf(j), runnable);
        }
    }

    public final void B(int i) {
        this.r = i;
        if (N.MCMbXu4W(this.j, this.s) && N.M8UuMlLD(this.j, this.s)) {
            if (this.y == -1) {
                this.y = N.MnVi6Frs(this.j, this.s);
            }
            if (this.z == -1) {
                this.z = N.Mxt_kc4Q(this.j, this.s);
            }
        }
    }

    public final void C(AccessibilityEvent accessibilityEvent) {
        if (N.MCMbXu4W(this.j, this.u) && N.M8UuMlLD(this.j, this.u)) {
            N.MVuu0R4P(this.j, this.u, accessibilityEvent.getFromIndex(), accessibilityEvent.getToIndex());
        }
    }

    @Override // defpackage.jt3
    public final void a(WindowAndroid windowAndroid) {
        TraceEvent.q("WebContentsAccessibilityImpl.onWindowAndroidChanged", null);
        this.H.clear();
        if (windowAndroid != null) {
            nx0<Context> nx0Var = windowAndroid.i;
            if (nx0Var.get() != null) {
                this.h = nx0Var.get();
            }
        }
        TraceEvent.C("WebContentsAccessibilityImpl.onWindowAndroidChanged");
    }

    @CalledByNative
    public final void announceLiveRegionText(String str) {
        AccessibilityEvent accessibilityEventObtain;
        if (!m() || (accessibilityEventObtain = AccessibilityEvent.obtain(16384)) == null) {
            return;
        }
        accessibilityEventObtain.getText().add(str);
        accessibilityEventObtain.setContentDescription(null);
        x(accessibilityEventObtain);
    }

    @CalledByNative
    public void clearNodeInfoCacheForGivenId(int i) {
        SparseArray<AccessibilityNodeInfoCompat> sparseArray = this.H;
        if (sparseArray.get(i) != null) {
            sparseArray.get(i).getClass();
            sparseArray.remove(i);
        }
        this.L.remove(Integer.valueOf(i));
    }

    @Override // org.chromium.ui.accessibility.AccessibilityState.a
    public final void d() {
        u();
    }

    @Override // defpackage.lk3
    public final void destroy() {
        TraceEvent.q("WebContentsAccessibilityImpl.destroy", null);
        this.H.clear();
        this.I.a();
        org.chromium.content.browser.accessibility.a aVar = this.f;
        if (aVar.c() != null) {
            kt3.e(aVar.c()).e.c(this);
            mk3 mk3VarP0 = ((WebContentsImpl) aVar.c()).p0();
            if (mk3VarP0 != null) {
                mk3VarP0.c(WebContentsAccessibilityImpl.class);
            }
        } else if (this.j != 0) {
            TraceEvent.q("WebContentsAccessibilityImpl.deleteEarly", null);
            N.MxGfnb$m(this.j);
            TraceEvent.C("WebContentsAccessibilityImpl.deleteEarly");
        }
        TraceEvent.C("WebContentsAccessibilityImpl.destroy");
    }

    @Override // defpackage.C0180d0
    public final void e(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, String str, Bundle bundle) {
        int[] iArrMVBiMGvZ;
        str.getClass();
        if (!str.equals("android.core.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY")) {
            if (str.equals("AccessibilityNodeInfo.requestImageData")) {
                HashSet hashSet = this.L;
                if (N.Mmo4i01Z(this.j, accessibilityNodeInfoCompat, i, hashSet.contains(Integer.valueOf(i)))) {
                    return;
                }
                hashSet.add(Integer.valueOf(i));
                return;
            }
            return;
        }
        if (bundle == null) {
            return;
        }
        if (!N.MZcfOSQW(this.j, i)) {
            N.M2WbOJ7$(this.j, i);
        }
        int i2 = bundle.getInt("android.core.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_START_INDEX", -1);
        int i3 = bundle.getInt("android.core.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_LENGTH", -1);
        if (i3 <= 0 || i2 < 0 || (iArrMVBiMGvZ = N.MVBiMGvZ(this.j, i, i2, i3)) == null) {
            return;
        }
        RectF[] rectFArr = new RectF[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i4 * 4;
            Rect rect = new Rect(iArrMVBiMGvZ[i5 + 0], iArrMVBiMGvZ[i5 + 1], iArrMVBiMGvZ[i5 + 2], iArrMVBiMGvZ[i5 + 3]);
            this.B.a(rect, accessibilityNodeInfoCompat.h());
            rectFArr[i4] = new RectF(rect);
        }
        accessibilityNodeInfoCompat.h().putParcelableArray("android.core.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY", rectFArr);
    }

    @CalledByNative
    public final void finishGranularityMoveNext(String str, boolean z, int i, int i2) {
        AccessibilityEvent accessibilityEventK = k(this.u, 8192);
        if (accessibilityEventK == null) {
            return;
        }
        AccessibilityEvent accessibilityEventK2 = k(this.u, 131072);
        if (accessibilityEventK2 == null) {
            accessibilityEventK.recycle();
            return;
        }
        if (z) {
            if (!this.x) {
                this.x = true;
                this.y = i;
            }
            accessibilityEventK.setFromIndex(this.y);
            accessibilityEventK.setToIndex(i2);
        } else {
            this.x = false;
            this.y = i2;
            accessibilityEventK.setFromIndex(i2);
            accessibilityEventK.setToIndex(i2);
        }
        this.z = i2;
        accessibilityEventK.setItemCount(str.length());
        C(accessibilityEventK);
        accessibilityEventK2.setFromIndex(i);
        accessibilityEventK2.setToIndex(i2);
        accessibilityEventK2.setItemCount(str.length());
        accessibilityEventK2.setMovementGranularity(this.r);
        accessibilityEventK2.setContentDescription(str);
        accessibilityEventK2.setAction(AccessibilityNodeInfoCompat.a.k.a());
        x(accessibilityEventK);
        x(accessibilityEventK2);
        this.D = true;
    }

    @CalledByNative
    public final void finishGranularityMovePrevious(String str, boolean z, int i, int i2) {
        AccessibilityEvent accessibilityEventK = k(this.u, 8192);
        if (accessibilityEventK == null) {
            return;
        }
        AccessibilityEvent accessibilityEventK2 = k(this.u, 131072);
        if (accessibilityEventK2 == null) {
            accessibilityEventK.recycle();
            return;
        }
        if (z) {
            if (!this.x) {
                this.x = true;
                this.y = i2;
            }
            accessibilityEventK.setFromIndex(this.y);
            accessibilityEventK.setToIndex(i);
        } else {
            this.x = false;
            this.y = i;
            accessibilityEventK.setFromIndex(i);
            accessibilityEventK.setToIndex(i);
        }
        this.z = i;
        accessibilityEventK.setItemCount(str.length());
        C(accessibilityEventK);
        accessibilityEventK2.setFromIndex(i);
        accessibilityEventK2.setToIndex(i2);
        accessibilityEventK2.setItemCount(str.length());
        accessibilityEventK2.setMovementGranularity(this.r);
        accessibilityEventK2.setContentDescription(str);
        accessibilityEventK2.setAction(AccessibilityNodeInfoCompat.a.l.a());
        x(accessibilityEventK);
        x(accessibilityEventK2);
        this.D = true;
    }

    @Override // defpackage.C0180d0
    public final AccessibilityNodeInfoCompat g(int i) {
        if (!m()) {
            return null;
        }
        if (this.m == -1) {
            this.m = N.MI8pU34f(this.j);
        }
        View view = this.n;
        if (i == -1) {
            int i2 = this.m;
            AccessibilityNodeInfo accessibilityNodeInfoObtain = AccessibilityNodeInfo.obtain(view);
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = new AccessibilityNodeInfoCompat(accessibilityNodeInfoObtain);
            AccessibilityNodeInfo accessibilityNodeInfoObtain2 = AccessibilityNodeInfo.obtain(view);
            view.onInitializeAccessibilityNodeInfo(accessibilityNodeInfoObtain2);
            Rect rect = new Rect();
            accessibilityNodeInfoObtain2.getBoundsInParent(rect);
            accessibilityNodeInfoCompat.l(rect);
            accessibilityNodeInfoObtain2.getBoundsInScreen(rect);
            accessibilityNodeInfoCompat.m(rect);
            Object parentForAccessibility = view.getParentForAccessibility();
            if (parentForAccessibility instanceof View) {
                accessibilityNodeInfoCompat.b = -1;
                accessibilityNodeInfoObtain.setParent((View) parentForAccessibility);
            }
            accessibilityNodeInfoObtain.setVisibleToUser(accessibilityNodeInfoObtain2.isVisibleToUser());
            accessibilityNodeInfoObtain.setEnabled(accessibilityNodeInfoObtain2.isEnabled());
            accessibilityNodeInfoObtain.setPackageName(accessibilityNodeInfoObtain2.getPackageName());
            accessibilityNodeInfoCompat.n(accessibilityNodeInfoObtain2.getClassName());
            if (n()) {
                accessibilityNodeInfoObtain.addChild(view, i2);
            }
            return accessibilityNodeInfoCompat;
        }
        if (!n()) {
            return null;
        }
        SparseArray<AccessibilityNodeInfoCompat> sparseArray = this.H;
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = sparseArray.get(i);
        C0218x c0218x = this.C;
        if (accessibilityNodeInfoCompat2 == null) {
            AccessibilityNodeInfo accessibilityNodeInfoObtain3 = AccessibilityNodeInfo.obtain(view);
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat3 = new AccessibilityNodeInfoCompat(accessibilityNodeInfoObtain3);
            accessibilityNodeInfoObtain3.setPackageName(this.h.getPackageName());
            accessibilityNodeInfoCompat3.c = i;
            accessibilityNodeInfoObtain3.setSource(view, i);
            if (i == this.m) {
                accessibilityNodeInfoCompat3.b = -1;
                accessibilityNodeInfoObtain3.setParent(view);
            }
            if (!N.MJGtghd9(this.j, accessibilityNodeInfoCompat3, i)) {
                return null;
            }
            sparseArray.put(i, new AccessibilityNodeInfoCompat(AccessibilityNodeInfo.obtain(accessibilityNodeInfoObtain3)));
            c0218x.e++;
            return accessibilityNodeInfoCompat3;
        }
        AccessibilityNodeInfo accessibilityNodeInfoObtain4 = AccessibilityNodeInfo.obtain(sparseArray.get(i).a);
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat4 = new AccessibilityNodeInfoCompat(accessibilityNodeInfoObtain4);
        accessibilityNodeInfoCompat4.c = i;
        accessibilityNodeInfoObtain4.setSource(view, i);
        if (!N.MZ7sDynr(this.j, accessibilityNodeInfoCompat4, i)) {
            sparseArray.get(i).getClass();
            sparseArray.remove(i);
            return null;
        }
        accessibilityNodeInfoObtain4.setAccessibilityFocused(this.s == i);
        if (this.s == i) {
            accessibilityNodeInfoCompat4.b(AccessibilityNodeInfoCompat.a.j);
            accessibilityNodeInfoCompat4.k(AccessibilityNodeInfoCompat.a.i);
        } else {
            accessibilityNodeInfoCompat4.k(AccessibilityNodeInfoCompat.a.j);
            accessibilityNodeInfoCompat4.b(AccessibilityNodeInfoCompat.a.i);
        }
        c0218x.d++;
        return accessibilityNodeInfoCompat4;
    }

    @CalledByNative
    public String generateAccessibilityNodeInfoString(int i) {
        Iterator<AccessibilityNodeInfoCompat.a> it;
        this.q = true;
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompatG = g(i);
        boolean z = false;
        String string = "";
        if (accessibilityNodeInfoCompatG != null) {
            StringBuilder sb = new StringBuilder();
            AccessibilityNodeInfo accessibilityNodeInfo = accessibilityNodeInfoCompatG.a;
            String[] strArrSplit = accessibilityNodeInfo.getClassName().toString().split("\\.");
            sb.append(strArrSplit[strArrSplit.length - 1]);
            if (accessibilityNodeInfoCompatG.j() == null) {
                sb.append(" text:\"null\"");
            } else if (!accessibilityNodeInfoCompatG.j().toString().isEmpty()) {
                sb.append(" text:\"");
                sb.append(accessibilityNodeInfoCompatG.j().toString().replace("\n", "\\n"));
                sb.append("\"");
            }
            if (accessibilityNodeInfo.getHintText() != null && !accessibilityNodeInfo.getHintText().toString().isEmpty()) {
                sb.append(" hint:\"");
                sb.append(accessibilityNodeInfo.getHintText());
                sb.append("\"");
            }
            if (accessibilityNodeInfoCompatG.g() != null) {
                sb.append(" contentDescription:\"");
                sb.append(accessibilityNodeInfoCompatG.g().toString().replace("\n", "\\n"));
                sb.append("\"");
            }
            if (accessibilityNodeInfo.getPaneTitle() != null) {
                sb.append(" paneTitle:\"");
                sb.append(accessibilityNodeInfo.getPaneTitle());
                sb.append("\"");
            }
            if (accessibilityNodeInfo.getViewIdResourceName() != null) {
                sb.append(" viewIdResName:\"");
                sb.append(accessibilityNodeInfo.getViewIdResourceName());
                sb.append("\"");
            }
            if (accessibilityNodeInfo.getError() != null) {
                sb.append(" error:\"");
                sb.append(accessibilityNodeInfo.getError());
                sb.append("\"");
            }
            if (accessibilityNodeInfoCompatG.i() != null && !accessibilityNodeInfoCompatG.i().toString().isEmpty()) {
                sb.append(" stateDescription:\"");
                sb.append(accessibilityNodeInfoCompatG.i());
                sb.append("\"");
            }
            if (accessibilityNodeInfo.canOpenPopup()) {
                sb.append(" canOpenPopUp");
            }
            if (accessibilityNodeInfo.isCheckable()) {
                sb.append(" checkable");
            }
            if (accessibilityNodeInfo.isChecked()) {
                sb.append(" checked");
            }
            if (accessibilityNodeInfo.isClickable()) {
                sb.append(" clickable");
            }
            if (accessibilityNodeInfo.isContentInvalid()) {
                sb.append(" contentInvalid");
            }
            if (accessibilityNodeInfo.isDismissable()) {
                sb.append(" dismissable");
            }
            if (accessibilityNodeInfo.isEditable()) {
                sb.append(" editable");
            }
            if (!accessibilityNodeInfo.isEnabled()) {
                sb.append(" disabled");
            }
            if (accessibilityNodeInfo.isFocusable()) {
                sb.append(" focusable");
            }
            if (accessibilityNodeInfo.isFocused()) {
                sb.append(" focused");
            }
            if (accessibilityNodeInfo.isMultiLine()) {
                sb.append(" multiLine");
            }
            if (accessibilityNodeInfo.isPassword()) {
                sb.append(" password");
            }
            if (accessibilityNodeInfo.isScrollable()) {
                sb.append(" scrollable");
            }
            if (accessibilityNodeInfo.isSelected()) {
                sb.append(" selected");
            }
            if (!accessibilityNodeInfo.isVisibleToUser()) {
                sb.append(" notVisibleToUser");
            }
            if (accessibilityNodeInfo.getInputType() != 0) {
                sb.append(" inputType:");
                sb.append(accessibilityNodeInfo.getInputType());
            }
            if (accessibilityNodeInfo.getTextSelectionStart() != -1) {
                sb.append(" textSelectionStart:");
                sb.append(accessibilityNodeInfo.getTextSelectionStart());
            }
            if (accessibilityNodeInfo.getTextSelectionEnd() != -1) {
                sb.append(" textSelectionEnd:");
                sb.append(accessibilityNodeInfo.getTextSelectionEnd());
            }
            if (accessibilityNodeInfo.getMaxTextLength() != -1) {
                sb.append(" maxTextLength:");
                sb.append(accessibilityNodeInfo.getMaxTextLength());
            }
            AccessibilityNodeInfo.CollectionInfo collectionInfo = accessibilityNodeInfo.getCollectionInfo();
            if ((collectionInfo != null ? new AccessibilityNodeInfoCompat.c(collectionInfo) : null) != null) {
                sb.append(" CollectionInfo:");
                AccessibilityNodeInfo.CollectionInfo collectionInfo2 = accessibilityNodeInfo.getCollectionInfo();
                AccessibilityNodeInfoCompat.c cVar = collectionInfo2 != null ? new AccessibilityNodeInfoCompat.c(collectionInfo2) : null;
                String str = ((AccessibilityNodeInfo.CollectionInfo) cVar.a).isHierarchical() ? "[hierarchical, " : "[";
                AccessibilityNodeInfo.CollectionInfo collectionInfo3 = (AccessibilityNodeInfo.CollectionInfo) cVar.a;
                sb.append(String.format("%srows=%s, cols=%s]", str, Integer.valueOf(collectionInfo3.getRowCount()), Integer.valueOf(collectionInfo3.getColumnCount())));
            }
            AccessibilityNodeInfo.CollectionItemInfo collectionItemInfo = accessibilityNodeInfo.getCollectionItemInfo();
            if ((collectionItemInfo != null ? new AccessibilityNodeInfoCompat.d(collectionItemInfo) : null) != null) {
                sb.append(" CollectionItemInfo:");
                AccessibilityNodeInfo.CollectionItemInfo collectionItemInfo2 = accessibilityNodeInfo.getCollectionItemInfo();
                AccessibilityNodeInfoCompat.d dVar = collectionItemInfo2 != null ? new AccessibilityNodeInfoCompat.d(collectionItemInfo2) : null;
                String string2 = ((AccessibilityNodeInfo.CollectionItemInfo) dVar.a).isHeading() ? "[heading, " : "[";
                Object obj = dVar.a;
                AccessibilityNodeInfo.CollectionItemInfo collectionItemInfo3 = (AccessibilityNodeInfo.CollectionItemInfo) obj;
                if (collectionItemInfo3.isSelected()) {
                    string2 = string2.concat("selected, ");
                }
                if (((AccessibilityNodeInfo.CollectionItemInfo) obj).getRowSpan() != 1) {
                    StringBuilder sbJ = C0173a.j(string2);
                    sbJ.append(String.format("rowSpan=%s, ", Integer.valueOf(((AccessibilityNodeInfo.CollectionItemInfo) obj).getRowSpan())));
                    string2 = sbJ.toString();
                }
                if (((AccessibilityNodeInfo.CollectionItemInfo) obj).getColumnSpan() != 1) {
                    StringBuilder sbJ2 = C0173a.j(string2);
                    sbJ2.append(String.format("colSpan=%s, ", Integer.valueOf(((AccessibilityNodeInfo.CollectionItemInfo) obj).getColumnSpan())));
                    string2 = sbJ2.toString();
                }
                sb.append(String.format("%srowIndex=%s, colIndex=%s]", string2, Integer.valueOf(collectionItemInfo3.getRowIndex()), Integer.valueOf(collectionItemInfo3.getColumnIndex())));
            }
            AccessibilityNodeInfo.RangeInfo rangeInfo = accessibilityNodeInfo.getRangeInfo();
            if ((rangeInfo != null ? new AccessibilityNodeInfoCompat.e(rangeInfo) : null) != null) {
                sb.append(" RangeInfo:");
                AccessibilityNodeInfo.RangeInfo rangeInfo2 = accessibilityNodeInfo.getRangeInfo();
                AccessibilityNodeInfoCompat.e eVar = rangeInfo2 != null ? new AccessibilityNodeInfoCompat.e(rangeInfo2) : null;
                AccessibilityNodeInfo.RangeInfo rangeInfo3 = (AccessibilityNodeInfo.RangeInfo) eVar.a;
                sb.append(String.format("[current=%s, min=%s, max=%s]", Float.valueOf(((AccessibilityNodeInfo.RangeInfo) eVar.a).getCurrent()), Float.valueOf(rangeInfo3.getMin()), Float.valueOf(rangeInfo3.getMax())));
            }
            sb.append(" actions:");
            List<AccessibilityNodeInfoCompat.a> listD = accessibilityNodeInfoCompatG.d();
            Collections.sort(listD, new no0(4));
            ArrayList arrayList = new ArrayList();
            StringBuilder sb2 = new StringBuilder("[");
            Iterator<AccessibilityNodeInfoCompat.a> it2 = listD.iterator();
            while (it2.hasNext()) {
                AccessibilityNodeInfoCompat.a next = it2.next();
                if (!next.equals(AccessibilityNodeInfoCompat.a.m) && !next.equals(AccessibilityNodeInfoCompat.a.n) && !next.equals(AccessibilityNodeInfoCompat.a.y) && !next.equals(AccessibilityNodeInfoCompat.a.I)) {
                    AccessibilityNodeInfoCompat.a aVar = AccessibilityNodeInfoCompat.a.o;
                    if (!next.equals(aVar)) {
                        AccessibilityNodeInfoCompat.a aVar2 = AccessibilityNodeInfoCompat.a.p;
                        if (!next.equals(aVar2)) {
                            AccessibilityNodeInfoCompat.a aVar3 = AccessibilityNodeInfoCompat.a.C;
                            if (!next.equals(aVar3)) {
                                AccessibilityNodeInfoCompat.a aVar4 = AccessibilityNodeInfoCompat.a.A;
                                if (!next.equals(aVar4)) {
                                    AccessibilityNodeInfoCompat.a aVar5 = AccessibilityNodeInfoCompat.a.D;
                                    if (!next.equals(aVar5)) {
                                        AccessibilityNodeInfoCompat.a aVar6 = AccessibilityNodeInfoCompat.a.B;
                                        if (next.equals(aVar6)) {
                                            it = it2;
                                        } else {
                                            int iA = next.a();
                                            it = it2;
                                            arrayList.add(iA == AccessibilityNodeInfoCompat.a.k.a() ? "NEXT" : iA == AccessibilityNodeInfoCompat.a.l.a() ? "PREVIOUS" : iA == AccessibilityNodeInfoCompat.a.x.a() ? "SET_TEXT" : iA == AccessibilityNodeInfoCompat.a.r.a() ? "PASTE" : iA == AccessibilityNodeInfoCompat.a.K.a() ? "IME_ENTER" : iA == AccessibilityNodeInfoCompat.a.t.a() ? "SET_SELECTION" : iA == AccessibilityNodeInfoCompat.a.s.a() ? "CUT" : iA == AccessibilityNodeInfoCompat.a.q.a() ? "COPY" : iA == aVar.a() ? "SCROLL_FORWARD" : iA == aVar2.a() ? "SCROLL_BACKWARD" : iA == aVar4.a() ? "SCROLL_UP" : iA == AccessibilityNodeInfoCompat.a.E.a() ? "PAGE_UP" : iA == aVar3.a() ? "SCROLL_DOWN" : iA == AccessibilityNodeInfoCompat.a.F.a() ? "PAGE_DOWN" : iA == aVar6.a() ? "SCROLL_LEFT" : iA == AccessibilityNodeInfoCompat.a.G.a() ? "PAGE_LEFT" : iA == aVar5.a() ? "SCROLL_RIGHT" : iA == AccessibilityNodeInfoCompat.a.H.a() ? "PAGE_RIGHT" : iA == AccessibilityNodeInfoCompat.a.f.a() ? "CLEAR_FOCUS" : iA == AccessibilityNodeInfoCompat.a.e.a() ? "FOCUS" : iA == AccessibilityNodeInfoCompat.a.j.a() ? "CLEAR_AX_FOCUS" : iA == AccessibilityNodeInfoCompat.a.i.a() ? "AX_FOCUS" : iA == AccessibilityNodeInfoCompat.a.g.a() ? "CLICK" : iA == AccessibilityNodeInfoCompat.a.u.a() ? "EXPAND" : iA == AccessibilityNodeInfoCompat.a.v.a() ? "COLLAPSE" : iA == AccessibilityNodeInfoCompat.a.f10J.a() ? "SET_PROGRESS" : "NOT_IMPLEMENTED");
                                        }
                                        it2 = it;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            sb2.append(TextUtils.join(", ", arrayList));
            sb2.append("]");
            sb.append(sb2.toString());
            sb.append(" bundle:");
            Bundle bundleH = accessibilityNodeInfoCompatG.h();
            ArrayList<String> arrayList2 = new ArrayList(bundleH.keySet());
            Collections.sort(arrayList2, String.CASE_INSENSITIVE_ORDER);
            ArrayList arrayList3 = new ArrayList();
            StringBuilder sb3 = new StringBuilder("[");
            for (String str2 : arrayList2) {
                if (!str2.equals("AccessibilityNodeInfo.unclippedTop") && !str2.equals("AccessibilityNodeInfo.unclippedBottom") && bundleH.get(str2) != null && !bundleH.get(str2).toString().isEmpty() && !str2.equals("ACTION_ARGUMENT_HTML_ELEMENT_STRING_VALUES") && !str2.equals("AccessibilityNodeInfo.offscreen") && !str2.contains("AccessibilityNodeInfoCompat") && !str2.equals("AccessibilityNodeInfo.cssDisplay")) {
                    arrayList3.add(str2.replace("AccessibilityNodeInfo.", "") + "=\"" + bundleH.get(str2).toString() + "\"");
                }
            }
            sb3.append(TextUtils.join(", ", arrayList3));
            sb3.append("]");
            sb.append(sb3.toString());
            string = sb.toString();
            z = false;
        }
        this.q = z;
        return string;
    }

    @Override // defpackage.C0180d0
    public final List h() {
        return new ArrayList();
    }

    @CalledByNative
    public final void handleCheckStateChanged(int i) {
        if (this.s == i) {
            A(i, 1);
        }
    }

    @CalledByNative
    public final void handleClicked(int i) {
        A(i, 1);
    }

    @CalledByNative
    public final void handleContentChanged(int i) {
        A(i, 2048);
    }

    @SuppressLint({"WrongConstant"})
    @CalledByNative
    public void handleDialogModalOpened(int i) {
        AccessibilityEvent accessibilityEventObtain;
        if (!m() || (accessibilityEventObtain = AccessibilityEvent.obtain(32)) == null) {
            return;
        }
        accessibilityEventObtain.setContentChangeTypes(16);
        accessibilityEventObtain.setSource(this.n, i);
        x(accessibilityEventObtain);
    }

    @CalledByNative
    public final void handleEditableTextChanged(int i) {
        A(i, 16);
    }

    @CalledByNative
    public final void handleFocusChanged(int i) {
        if (this.E || this.s != -1) {
            A(i, 8);
            q(i);
        }
    }

    @CalledByNative
    public final void handleHover(int i) {
        if (this.l != i && this.k) {
            A(i, 128);
            if (this.f.g() != 0) {
                if (i == this.s) {
                    A(i, 65536);
                    this.s = -1;
                }
                q(i);
            }
        }
    }

    @CalledByNative
    public final void handleNavigate(int i) {
        this.s = -1;
        this.t = -1;
        this.m = i;
        A(-1, 2048);
    }

    @CalledByNative
    public void handleScrollPositionChanged(int i) {
        A(i, 4096);
        if (this.o) {
            A(i, 2048);
            this.o = false;
        }
    }

    @CalledByNative
    public final void handleScrolledToAnchor(int i) {
        q(i);
    }

    @CalledByNative
    public final void handleSliderChanged(int i) {
        if (this.s == i) {
            A(i, 4);
        } else {
            A(i, 4096);
        }
    }

    @CalledByNative
    public final void handleStateDescriptionChanged(int i) {
        AccessibilityEvent accessibilityEventObtain;
        if (!m() || (accessibilityEventObtain = AccessibilityEvent.obtain(2048)) == null) {
            return;
        }
        accessibilityEventObtain.setContentChangeTypes(64);
        accessibilityEventObtain.setSource(this.n, i);
        x(accessibilityEventObtain);
    }

    @CalledByNative
    public final void handleTextContentChanged(int i) {
        AccessibilityEvent accessibilityEventK = k(i, 2048);
        if (accessibilityEventK != null) {
            accessibilityEventK.setContentChangeTypes(2);
            x(accessibilityEventK);
        }
    }

    @CalledByNative
    public final void handleTextSelectionChanged(int i) {
        A(i, 8192);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.C0180d0
    public final boolean j(int i, int i2, Bundle bundle) {
        int i3;
        CharSequence charSequence;
        String string;
        String string2;
        y();
        int i4 = 0;
        if (m()) {
            Boolean bool = this.G;
            if ((bool != null && bool.booleanValue()) == false && N.MTBNGzHX(this.j, i)) {
                if (i2 == AccessibilityNodeInfoCompat.a.i.a()) {
                    if (!q(i)) {
                        return true;
                    }
                    if (this.k) {
                        this.o = true;
                    } else {
                        z(this.s);
                    }
                    return true;
                }
                if (i2 == AccessibilityNodeInfoCompat.a.j.a()) {
                    A(i, 65536);
                    int i5 = this.s;
                    if (i5 == i) {
                        N.MPQKLw45(this.j, i5, -1);
                        this.s = -1;
                    }
                    int i6 = this.l;
                    if (i6 == i) {
                        A(i6, 256);
                        this.l = -1;
                    }
                    return true;
                }
                int iA = AccessibilityNodeInfoCompat.a.g.a();
                org.chromium.content.browser.accessibility.a aVar = this.f;
                View view = this.n;
                if (i2 == iA) {
                    if (!view.hasFocus()) {
                        view.requestFocus();
                    }
                    if (aVar.g() != 0) {
                        int[] iArrMihzIy2h = N.MihzIy2h(this.j, i);
                        aVar.b(iArrMihzIy2h == null ? null : new Rect(iArrMihzIy2h[0], iArrMihzIy2h[1], iArrMihzIy2h[2], iArrMihzIy2h[3]));
                    } else {
                        N.MM4OAOXm(this.j, i);
                    }
                    return true;
                }
                if (i2 == AccessibilityNodeInfoCompat.a.e.a()) {
                    if (!view.hasFocus()) {
                        view.requestFocus();
                    }
                    N.MG_OiJKg(this.j, i);
                    return true;
                }
                if (i2 == AccessibilityNodeInfoCompat.a.f.a()) {
                    N.MNm00fYN(this.j);
                    return true;
                }
                if (i2 == AccessibilityNodeInfoCompat.a.m.a()) {
                    if (bundle == null || (string2 = bundle.getString("ACTION_ARGUMENT_HTML_ELEMENT_STRING")) == null) {
                        return false;
                    }
                    return p(string2.toUpperCase(Locale.US), i, true, false);
                }
                if (i2 == AccessibilityNodeInfoCompat.a.n.a()) {
                    if (bundle == null || (string = bundle.getString("ACTION_ARGUMENT_HTML_ELEMENT_STRING")) == null) {
                        return false;
                    }
                    return p(string.toUpperCase(Locale.US), i, false, i == this.m);
                }
                if (i2 == AccessibilityNodeInfoCompat.a.x.a()) {
                    if (!N.MCMbXu4W(this.j, i) || bundle == null || (charSequence = bundle.getCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE")) == null) {
                        return false;
                    }
                    String string3 = charSequence.toString();
                    N.MEJD7Boi(this.j, i, string3);
                    N.MVuu0R4P(this.j, i, string3.length(), string3.length());
                    return true;
                }
                if (i2 == AccessibilityNodeInfoCompat.a.t.a()) {
                    if (!N.MCMbXu4W(this.j, i)) {
                        return false;
                    }
                    if (bundle != null) {
                        i4 = bundle.getInt("ACTION_ARGUMENT_SELECTION_START_INT");
                        i3 = bundle.getInt("ACTION_ARGUMENT_SELECTION_END_INT");
                    } else {
                        i3 = 0;
                    }
                    N.MVuu0R4P(this.j, i, i4, i3);
                    return true;
                }
                if (i2 == AccessibilityNodeInfoCompat.a.k.a()) {
                    if (bundle == null) {
                        return false;
                    }
                    int i7 = bundle.getInt("ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT");
                    boolean z = bundle.getBoolean("ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN");
                    if ((i7 == 1 || i7 == 2 || i7 == 4 || i7 == 8) != true) {
                        return false;
                    }
                    if (i7 == 8) {
                        return p("PARAGRAPH", i, true, false);
                    }
                    if (i != this.u) {
                        return false;
                    }
                    B(i7);
                    return (z && this.x) ? N.McKjfBnu(this.j, this.r, z, i, this.z) : N.McKjfBnu(this.j, this.r, z, i, this.y);
                }
                if (i2 == AccessibilityNodeInfoCompat.a.l.a()) {
                    if (bundle == null) {
                        return false;
                    }
                    int i8 = bundle.getInt("ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT");
                    boolean z2 = bundle.getBoolean("ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN");
                    if ((i8 == 1 || i8 == 2 || i8 == 4 || i8 == 8) != true) {
                        return false;
                    }
                    if (i8 == 8) {
                        return p("PARAGRAPH", i, false, i == this.m);
                    }
                    if (i != this.u) {
                        return false;
                    }
                    B(i8);
                    return N.M3suD0ji(this.j, this.r, z2, i, this.z);
                }
                if (i2 == AccessibilityNodeInfoCompat.a.o.a()) {
                    return N.MkaakTGI(this.j, i) ? N.MLjXc4lw(this.j, i, true) : N.MNch0m9c(this.j, i, 0, false);
                }
                if (i2 == AccessibilityNodeInfoCompat.a.p.a()) {
                    return N.MkaakTGI(this.j, i) ? N.MLjXc4lw(this.j, i, false) : N.MNch0m9c(this.j, i, 1, false);
                }
                if (i2 == AccessibilityNodeInfoCompat.a.s.a()) {
                    if (aVar.c() == null) {
                        return false;
                    }
                    WebContentsImpl webContentsImpl = (WebContentsImpl) aVar.c();
                    webContentsImpl.R();
                    N.MhIiCaN7(webContentsImpl.f);
                    return true;
                }
                if (i2 == AccessibilityNodeInfoCompat.a.q.a()) {
                    if (aVar.c() == null) {
                        return false;
                    }
                    WebContentsImpl webContentsImpl2 = (WebContentsImpl) aVar.c();
                    webContentsImpl2.R();
                    N.MpfMxfut(webContentsImpl2.f);
                    return true;
                }
                if (i2 == AccessibilityNodeInfoCompat.a.r.a()) {
                    if (aVar.c() == null) {
                        return false;
                    }
                    WebContentsImpl webContentsImpl3 = (WebContentsImpl) aVar.c();
                    webContentsImpl3.R();
                    N.MYRJ_nNk(webContentsImpl3.f);
                    return true;
                }
                if (i2 == AccessibilityNodeInfoCompat.a.v.a() || i2 == AccessibilityNodeInfoCompat.a.u.a()) {
                    if (aVar.g() != 0) {
                        int[] iArrMihzIy2h2 = N.MihzIy2h(this.j, i);
                        aVar.b(iArrMihzIy2h2 == null ? null : new Rect(iArrMihzIy2h2[0], iArrMihzIy2h2[1], iArrMihzIy2h2[2], iArrMihzIy2h2[3]));
                    } else {
                        N.MM4OAOXm(this.j, i);
                    }
                    return true;
                }
                if (i2 == AccessibilityNodeInfoCompat.a.y.a()) {
                    z(i);
                    return true;
                }
                if (i2 == AccessibilityNodeInfoCompat.a.I.a() || i2 == AccessibilityNodeInfoCompat.a.h.a()) {
                    N.MOikWIf9(this.j, i);
                    return true;
                }
                if (i2 == AccessibilityNodeInfoCompat.a.A.a() || i2 == AccessibilityNodeInfoCompat.a.E.a()) {
                    return N.MNch0m9c(this.j, i, 2, i2 == AccessibilityNodeInfoCompat.a.E.a());
                }
                if (i2 == AccessibilityNodeInfoCompat.a.C.a() || i2 == AccessibilityNodeInfoCompat.a.F.a()) {
                    return N.MNch0m9c(this.j, i, 3, i2 == AccessibilityNodeInfoCompat.a.F.a());
                }
                if (i2 == AccessibilityNodeInfoCompat.a.B.a() || i2 == AccessibilityNodeInfoCompat.a.G.a()) {
                    return N.MNch0m9c(this.j, i, 4, i2 == AccessibilityNodeInfoCompat.a.G.a());
                }
                if (i2 == AccessibilityNodeInfoCompat.a.D.a() || i2 == AccessibilityNodeInfoCompat.a.H.a()) {
                    return N.MNch0m9c(this.j, i, 5, i2 == AccessibilityNodeInfoCompat.a.H.a());
                }
                if (i2 == AccessibilityNodeInfoCompat.a.f10J.a()) {
                    if (bundle != null && bundle.containsKey("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE")) {
                        return N.MfTAAcu8(this.j, i, bundle.getFloat("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE"));
                    }
                    return false;
                }
                if (i2 != AccessibilityNodeInfoCompat.a.K.a() || aVar.c() == null || ImeAdapterImpl.b(aVar.c()) == null) {
                    return false;
                }
                return ImeAdapterImpl.b(aVar.c()).i(0);
            }
        }
        return false;
    }

    public final AccessibilityEvent k(int i, int i2) {
        if (!m() || !n() || !N.MTBNGzHX(this.j, i)) {
            return null;
        }
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i2);
        accessibilityEventObtain.setPackageName(this.h.getPackageName());
        accessibilityEventObtain.setSource(this.n, i);
        if (i2 == 2048) {
            accessibilityEventObtain.setContentChangeTypes(1);
        }
        if (N.M2E1dEU9(this.j, accessibilityEventObtain, i, i2)) {
            return accessibilityEventObtain;
        }
        accessibilityEventObtain.recycle();
        return null;
    }

    public final AccessibilityNodeProvider l() {
        Boolean bool = this.G;
        if (bool != null && bool.booleanValue()) {
            this = null;
        } else {
            boolean z = this.N;
            org.chromium.content.browser.accessibility.a aVar = this.f;
            if (z) {
                N.M7xDKIau(this.j, aVar.c());
                this.N = false;
                this.O++;
            }
            if (!o()) {
                this.j = N.MjYAnP1s(this, aVar.c(), this.B);
                r();
            }
            if (!(o() && N.MB0ln8Is(this.j))) {
                N.Md53s98W(this.j);
                this = null;
            }
        }
        if (this == null) {
            return null;
        }
        return (AccessibilityNodeProvider) this.e;
    }

    public final boolean m() {
        return o() && (this.q || this.g.isEnabled());
    }

    public final boolean n() {
        org.chromium.content.browser.accessibility.a aVar = this.f;
        if (aVar.c() == null && this.j == 0) {
            return true;
        }
        a.InterfaceC0148a interfaceC0148aI = aVar.i();
        return (((double) interfaceC0148aI.f()) == 0.0d && ((double) interfaceC0148aI.a()) == 0.0d) ? false : true;
    }

    @CalledByNative
    public final void notifyFrameInfoInitialized() {
        int i;
        if (this.p) {
            return;
        }
        this.p = true;
        A(-1, 2048);
        if (this.E && (i = this.s) != -1) {
            A(i, 65536);
            this.s = -1;
            q(i);
        }
    }

    public final boolean o() {
        return this.j != 0;
    }

    @Override // defpackage.jt3
    public final void onAttachedToWindow() {
        TraceEvent.q("WebContentsAccessibilityImpl.onAttachedToWindow", null);
        u();
        CaptioningController captioningController = this.w;
        cm cmVar = captioningController.a;
        dm dmVar = cmVar.a;
        if (!(!dmVar.i.isEmpty())) {
            cmVar.b.addCaptioningChangeListener(cmVar);
            cmVar.b();
        }
        dmVar.i.put(captioningController, null);
        dmVar.b(captioningController);
        v();
        TraceEvent.C("WebContentsAccessibilityImpl.onAttachedToWindow");
    }

    @Override // defpackage.jt3
    public final void onDetachedFromWindow() {
        CaptioningController captioningController = this.w;
        cm cmVar = captioningController.a;
        cmVar.a.i.remove(captioningController);
        if (!(!r2.i.isEmpty())) {
            cmVar.b.removeCaptioningChangeListener(cmVar);
        }
        if (o()) {
            xz.a.unregisterReceiver(this.K);
            C0218x c0218x = this.C;
            c0218x.getClass();
            if (N.Mudil8Bg("OnDemandAccessibilityEvents")) {
                boolean zMudil8Bg = N.Mudil8Bg("ComputeAXMode");
                if (!AccessibilityState.f) {
                    AccessibilityState.a();
                }
                boolean z = AccessibilityState.e.a;
                int i = c0218x.a;
                if (i > 0) {
                    int i2 = (int) (((((double) c0218x.b) * 1.0d) / ((double) i)) * 100.0d);
                    int i3 = 100 - i2;
                    oj2.Z(i3, "Accessibility.Android.OnDemand.PercentageDropped");
                    if (zMudil8Bg) {
                        oj2.Z(i3, z ? "Accessibility.Android.OnDemand.PercentageDropped.Complete" : "Accessibility.Android.OnDemand.PercentageDropped.Basic");
                    }
                    oj2.S(c0218x.a - c0218x.b, 1, TestVehicleHealthManagerImplCallback.SET_FRONT_WIPER_CHECK_STATE, 100, "Accessibility.Android.OnDemand.EventsDropped");
                    if (i2 == 0) {
                        oj2.S(c0218x.a - c0218x.b, 1, TestVehicleHealthManagerImplCallback.SET_FRONT_WIPER_CHECK_STATE, 100, "Accessibility.Android.OnDemand.OneHundredPercentEventsDropped");
                        if (zMudil8Bg) {
                            oj2.S(c0218x.a - c0218x.b, 1, TestVehicleHealthManagerImplCallback.SET_FRONT_WIPER_CHECK_STATE, 100, z ? "Accessibility.Android.OnDemand.OneHundredPercentEventsDropped.Complete" : "Accessibility.Android.OnDemand.OneHundredPercentEventsDropped.Basic");
                        }
                    }
                }
                c0218x.a = 0;
                c0218x.b = 0;
            }
            oj2.S(c0218x.c, 1, 3000, 100, "Accessibility.Android.Cache.MaxNodesInCache");
            int i4 = c0218x.d;
            oj2.Z((int) (((((double) i4) * 1.0d) / ((double) (c0218x.e + i4))) * 100.0d), "Accessibility.Android.Cache.PercentageRetrievedFromCache");
            c0218x.c = 0;
            c0218x.d = 0;
            c0218x.e = 0;
        }
    }

    @CalledByNative
    public final boolean onHoverEvent(int i) {
        if (!m()) {
            return false;
        }
        if (i == 10) {
            this.k = false;
            return true;
        }
        this.k = true;
        return true;
    }

    @CalledByNative
    public void onNativeObjectDestroyed() {
        this.j = 0L;
    }

    public final boolean p(String str, int i, boolean z, boolean z2) {
        int iMavOU0SM = N.MavOU0SM(this.j, i, str, z, z2, str.isEmpty());
        if (iMavOU0SM == 0) {
            return false;
        }
        q(iMavOU0SM);
        z(this.s);
        return true;
    }

    public final boolean q(int i) {
        int i2 = this.s;
        if (i == i2) {
            return false;
        }
        if (i != -1) {
            this.t = i;
        }
        N.MPQKLw45(this.j, i2, i);
        this.s = i;
        this.u = i;
        this.r = 0;
        this.x = false;
        this.y = -1;
        this.z = N.MhMiVz6m(this.j, i);
        this.D = false;
        if (N.M5uHFthk(this.j, this.s)) {
            this.v.requestFocus();
        }
        this.n.invalidate();
        A(this.s, 32768);
        return true;
    }

    public final void r() {
        TraceEvent.q("WebContentsAccessibilityImpl.onNativeInit", null);
        this.s = -1;
        this.t = -1;
        this.u = -1;
        this.k = false;
        this.m = -1;
        this.A = N.MPyIoFYC(this.j);
        this.K = new d();
        View view = this.n;
        if (view.isAttachedToWindow()) {
            v();
        }
        if (N.Mudil8Bg("OnDemandAccessibilityEvents")) {
            view.post(new an2(11, this));
        }
        if (N.Mudil8Bg("AutoDisableAccessibilityV2")) {
            org.chromium.content.browser.accessibility.c cVar = this.M;
            if (!cVar.c) {
                WebContentsAccessibilityImpl.this.n.postDelayed(cVar.b, 60000);
                cVar.c = true;
            }
        }
        u();
        TraceEvent.C("WebContentsAccessibilityImpl.onNativeInit");
    }

    public final void s(ViewStructure viewStructure) {
        org.chromium.content.browser.accessibility.a aVar = this.f;
        if (aVar.isIncognito()) {
            viewStructure.setChildCount(0);
            return;
        }
        viewStructure.setChildCount(1);
        ViewStructure viewStructureAsyncNewChild = viewStructure.asyncNewChild(0);
        viewStructureAsyncNewChild.setClassName("");
        viewStructureAsyncNewChild.setHint(this.i);
        WebContents webContentsC = aVar.c();
        if (webContentsC != null && !webContentsC.isDestroyed()) {
            viewStructureAsyncNewChild.getExtras().putCharSequence("url", webContentsC.s().j());
        }
        aVar.h(viewStructureAsyncNewChild, new lq3(viewStructureAsyncNewChild));
    }

    @CalledByNative
    public final void sendDelayedWindowContentChangedEvent() {
        A(-1, 2048);
    }

    @CalledByNative
    public final void setAccessibilityEventBaseAttributes(AccessibilityEvent accessibilityEvent, boolean z, boolean z2, boolean z3, boolean z4, int i, int i2, int i3, int i4, int i5, int i6, String str) {
        accessibilityEvent.setChecked(z);
        accessibilityEvent.setEnabled(z2);
        accessibilityEvent.setPassword(z3);
        accessibilityEvent.setScrollable(z4);
        accessibilityEvent.setCurrentItemIndex(i);
        accessibilityEvent.setItemCount(i2);
        accessibilityEvent.setScrollX(i3);
        accessibilityEvent.setScrollY(i4);
        accessibilityEvent.setMaxScrollX(i5);
        accessibilityEvent.setMaxScrollY(i6);
        accessibilityEvent.setClassName(str);
    }

    @CalledByNative
    public final void setAccessibilityEventSelectionAttrs(AccessibilityEvent accessibilityEvent, int i, int i2, int i3, String str) {
        accessibilityEvent.setFromIndex(i);
        accessibilityEvent.setToIndex(i2);
        accessibilityEvent.setItemCount(i3);
        accessibilityEvent.getText().add(str);
    }

    @CalledByNative
    public final void setAccessibilityEventTextChangedAttrs(AccessibilityEvent accessibilityEvent, int i, int i2, int i3, String str, String str2) {
        accessibilityEvent.setFromIndex(i);
        accessibilityEvent.setAddedCount(i2);
        accessibilityEvent.setRemovedCount(i3);
        accessibilityEvent.setBeforeText(str);
        accessibilityEvent.getText().add(str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0063  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void u() {
        /*
            Method dump skipped, instruction units count: 265
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl.u():void");
    }

    @CalledByNative
    public void updateMaxNodesInCache() {
        int size = this.H.size();
        C0218x c0218x = this.C;
        c0218x.c = Math.max(c0218x.c, size);
    }

    public final void v() {
        if (o()) {
            try {
                xz.g(xz.a, this.K, new IntentFilter("android.intent.action.LOCALE_CHANGED"));
            } catch (ReceiverCallNotAllowedException unused) {
            }
            this.f89J = Locale.getDefault().toLanguageTag();
        }
    }

    public final void x(AccessibilityEvent accessibilityEvent) {
        View view = this.n;
        if (view.getParent() == null || !m()) {
            return;
        }
        this.C.b++;
        try {
            y();
            view.getParent().requestSendAccessibilityEvent(view, accessibilityEvent);
        } catch (IllegalStateException unused) {
        }
    }

    public final void y() {
        if (N.Mudil8Bg("AutoDisableAccessibilityV2")) {
            org.chromium.content.browser.accessibility.c cVar = this.M;
            if (cVar.c) {
                b bVar = (b) cVar.a;
                View view = WebContentsAccessibilityImpl.this.n;
                i22 i22Var = cVar.b;
                view.removeCallbacks(i22Var);
                WebContentsAccessibilityImpl.this.n.postDelayed(i22Var, 60000L);
            }
        }
    }

    public final void z(int i) {
        org.chromium.content.browser.accessibility.a aVar = this.f;
        if (aVar.g() != 0) {
            int[] iArrMihzIy2h = N.MihzIy2h(this.j, i);
            aVar.d(iArrMihzIy2h == null ? null : new Rect(iArrMihzIy2h[0], iArrMihzIy2h[1], iArrMihzIy2h[2], iArrMihzIy2h[3]));
        } else {
            this.o = true;
            N.MB302_MP(this.j, i);
        }
    }

    @CalledByNative
    public void handleEndOfTestSignal() {
    }
}
