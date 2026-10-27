package org.chromium.content.browser.accessibility;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.LocaleSpan;
import android.text.style.SuggestionSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import defpackage.yj;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content.browser.accessibility.a;

/* JADX INFO: loaded from: classes3.dex */
public class AccessibilityNodeInfoBuilder {
    public static final List<String> d = Collections.singletonList("android.core.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY");
    public static final List<String> e = Collections.singletonList("AccessibilityNodeInfo.requestImageData");
    public int a;
    public long b;
    public final a c;

    public interface a {
    }

    public AccessibilityNodeInfoBuilder(WebContentsAccessibilityImpl.a aVar) {
        this.c = aVar;
    }

    public final void a(Rect rect, Bundle bundle) {
        WebContentsAccessibilityImpl.a aVar = (WebContentsAccessibilityImpl.a) this.c;
        a.InterfaceC0148a interfaceC0148aI = WebContentsAccessibilityImpl.this.f.i();
        rect.offset(-((int) interfaceC0148aI.b()), -((int) interfaceC0148aI.getScrollY()));
        rect.left = (int) interfaceC0148aI.e(rect.left);
        rect.top = (int) interfaceC0148aI.e(rect.top);
        rect.bottom = (int) interfaceC0148aI.e(rect.bottom);
        rect.right = (int) interfaceC0148aI.e(rect.right);
        rect.offset(0, (int) interfaceC0148aI.d());
        int[] iArr = new int[2];
        WebContentsAccessibilityImpl.this.n.getLocationOnScreen(iArr);
        rect.offset(iArr[0], iArr[1]);
        int iD = iArr[1] + ((int) interfaceC0148aI.d());
        int iC = interfaceC0148aI.c() + iD;
        int i = rect.top;
        if (i < iD) {
            bundle.putInt("AccessibilityNodeInfo.unclippedTop", i);
            rect.top = iD;
        }
        int i2 = rect.bottom;
        if (i2 > iC) {
            bundle.putInt("AccessibilityNodeInfo.unclippedBottom", i2);
            rect.bottom = iC;
        }
    }

    @CalledByNative
    public final void addAccessibilityNodeInfoActions(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, boolean z16, boolean z17) {
        accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.m);
        accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.n);
        accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.y);
        accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.I);
        if (z15) {
            accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.k);
            accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.l);
        }
        if (z8 && z9) {
            accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.x);
            accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.r);
            accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.K);
            if (z14) {
                accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.t);
                accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.s);
                accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.q);
            }
        }
        if (z) {
            accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.o);
        }
        if (z2) {
            accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.p);
        }
        if (z3) {
            accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.A);
            accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.E);
        }
        if (z4) {
            accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.C);
            accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.F);
        }
        if (z5) {
            accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.B);
            accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.G);
        }
        if (z6) {
            accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.D);
            accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.H);
        }
        if (z10) {
            if (z11) {
                accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.f);
            } else {
                accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.e);
            }
        }
        if (WebContentsAccessibilityImpl.this.s == i) {
            accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.j);
        } else {
            accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.i);
        }
        if (z7) {
            accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.g);
        }
        if (z12) {
            accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.u);
        }
        if (z13) {
            accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.v);
        }
        if (z16) {
            accessibilityNodeInfoCompat.b(AccessibilityNodeInfoCompat.a.f10J);
        }
    }

    @CalledByNative
    public final void addAccessibilityNodeInfoChildren(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int[] iArr) {
        for (int i : iArr) {
            accessibilityNodeInfoCompat.a.addChild(WebContentsAccessibilityImpl.this.n, i);
        }
    }

    @CalledByNative
    public final void setAccessibilityNodeInfoBaseAttributes(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i, int i2, String str, String str2, String str3, String str4, String str5, boolean z, boolean z2, int i3, int i4, String str6, int i5, String str7, String str8, String str9) {
        accessibilityNodeInfoCompat.n(str);
        Bundle bundleH = accessibilityNodeInfoCompat.h();
        if (!str8.isEmpty()) {
            bundleH.putCharSequence("AccessibilityNodeInfo.brailleLabel", str8);
        }
        if (!str9.isEmpty()) {
            bundleH.putCharSequence("AccessibilityNodeInfo.brailleRoleDescription", str9);
        }
        bundleH.putCharSequence("AccessibilityNodeInfo.chromeRole", str2);
        bundleH.putCharSequence("AccessibilityNodeInfo.roleDescription", str3);
        bundleH.putCharSequence("AccessibilityNodeInfo.hint", str4);
        if (!str7.isEmpty()) {
            bundleH.putCharSequence("AccessibilityNodeInfo.cssDisplay", str7);
        }
        if (!str5.isEmpty()) {
            bundleH.putCharSequence("AccessibilityNodeInfo.targetUrl", str5);
        }
        WebContentsAccessibilityImpl.a aVar = (WebContentsAccessibilityImpl.a) this.c;
        WebContentsAccessibilityImpl webContentsAccessibilityImpl = WebContentsAccessibilityImpl.this;
        if (i == webContentsAccessibilityImpl.m) {
            bundleH.putCharSequence("ACTION_ARGUMENT_HTML_ELEMENT_STRING_VALUES", webContentsAccessibilityImpl.A);
        }
        AccessibilityNodeInfo accessibilityNodeInfo = accessibilityNodeInfoCompat.a;
        if (i2 != -1) {
            View view = WebContentsAccessibilityImpl.this.n;
            accessibilityNodeInfoCompat.b = i2;
            accessibilityNodeInfo.setParent(view, i2);
        }
        accessibilityNodeInfo.setCanOpenPopup(z);
        accessibilityNodeInfo.setDismissable(false);
        accessibilityNodeInfo.setMultiLine(z2);
        accessibilityNodeInfo.setInputType(i3);
        if (accessibilityNodeInfo.isContentInvalid()) {
            accessibilityNodeInfo.setError(str6);
        }
        if (i5 > 0) {
            bundleH.putInt("AccessibilityNodeInfo.clickableScore", i5);
        }
    }

    @CalledByNative
    public final void setAccessibilityNodeInfoBooleanAttributes(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12) {
        accessibilityNodeInfoCompat.a.setCheckable(z);
        AccessibilityNodeInfo accessibilityNodeInfo = accessibilityNodeInfoCompat.a;
        accessibilityNodeInfo.setChecked(z2);
        accessibilityNodeInfoCompat.o(z3);
        accessibilityNodeInfo.setEnabled(z5);
        accessibilityNodeInfo.setFocusable(z6);
        accessibilityNodeInfo.setFocused(z7);
        accessibilityNodeInfo.setPassword(z9);
        accessibilityNodeInfoCompat.s(z10);
        accessibilityNodeInfo.setSelected(z11);
        accessibilityNodeInfo.setVisibleToUser(z12);
        if (!z4 || !z7) {
            accessibilityNodeInfo.setContentInvalid(z4);
        } else if (i != this.a) {
            this.a = i;
            this.b = Calendar.getInstance().getTimeInMillis();
            accessibilityNodeInfo.setContentInvalid(true);
        } else if (Calendar.getInstance().getTimeInMillis() - this.b >= 4500) {
            this.b = Calendar.getInstance().getTimeInMillis();
            accessibilityNodeInfo.setContentInvalid(true);
        }
        if (z8) {
            accessibilityNodeInfoCompat.h().putCharSequence("AccessibilityNodeInfo.hasImage", "true");
        }
        accessibilityNodeInfo.setMovementGranularities(15);
        accessibilityNodeInfo.setAccessibilityFocused(WebContentsAccessibilityImpl.this.s == i);
    }

    @CalledByNative
    public void setAccessibilityNodeInfoCollectionInfo(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i, int i2, boolean z) {
        AccessibilityNodeInfo.CollectionInfo collectionInfoObtain = AccessibilityNodeInfo.CollectionInfo.obtain(i, i2, z);
        accessibilityNodeInfoCompat.getClass();
        accessibilityNodeInfoCompat.a.setCollectionInfo(collectionInfoObtain);
    }

    @CalledByNative
    public void setAccessibilityNodeInfoCollectionItemInfo(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i, int i2, int i3, int i4, boolean z) {
        AccessibilityNodeInfo.CollectionItemInfo collectionItemInfoObtain = AccessibilityNodeInfo.CollectionItemInfo.obtain(i, i2, i3, i4, z);
        accessibilityNodeInfoCompat.getClass();
        accessibilityNodeInfoCompat.a.setCollectionItemInfo(collectionItemInfoObtain);
    }

    @CalledByNative
    public void setAccessibilityNodeInfoImageData(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, byte[] bArr) {
        accessibilityNodeInfoCompat.h().putByteArray("AccessibilityNodeInfo.imageData", bArr);
    }

    @CalledByNative
    public void setAccessibilityNodeInfoLocation(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z) {
        Rect rect = new Rect(i4, i5, i4 + i6, i5 + i7);
        WebContentsAccessibilityImpl webContentsAccessibilityImpl = WebContentsAccessibilityImpl.this;
        if (i == webContentsAccessibilityImpl.m) {
            rect.offset(0, (int) webContentsAccessibilityImpl.f.i().d());
        }
        accessibilityNodeInfoCompat.l(rect);
        Rect rect2 = new Rect(i2, i3, i6 + i2, i7 + i3);
        a(rect2, accessibilityNodeInfoCompat.h());
        accessibilityNodeInfoCompat.m(rect2);
        if (z) {
            accessibilityNodeInfoCompat.h().putBoolean("AccessibilityNodeInfo.offscreen", true);
        } else if (accessibilityNodeInfoCompat.h().containsKey("AccessibilityNodeInfo.offscreen")) {
            accessibilityNodeInfoCompat.h().remove("AccessibilityNodeInfo.offscreen");
        }
    }

    @CalledByNative
    public void setAccessibilityNodeInfoOAttributes(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, boolean z, boolean z2, String str) {
        accessibilityNodeInfoCompat.a.setHintText(str);
        AccessibilityNodeInfo accessibilityNodeInfo = accessibilityNodeInfoCompat.a;
        if (z) {
            accessibilityNodeInfo.setAvailableExtraData(d);
        } else if (z2) {
            accessibilityNodeInfo.setAvailableExtraData(e);
        }
    }

    @CalledByNative
    public void setAccessibilityNodeInfoPaneTitle(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, String str) {
        accessibilityNodeInfoCompat.a.setPaneTitle(str);
    }

    @CalledByNative
    public void setAccessibilityNodeInfoRangeInfo(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i, float f, float f2, float f3) {
        AccessibilityNodeInfo.RangeInfo rangeInfoObtain = AccessibilityNodeInfo.RangeInfo.obtain(i, f, f2, f3);
        accessibilityNodeInfoCompat.getClass();
        accessibilityNodeInfoCompat.a.setRangeInfo(rangeInfoObtain);
    }

    @CalledByNative
    public void setAccessibilityNodeInfoSelectionAttrs(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i, int i2) {
        accessibilityNodeInfoCompat.a.setEditable(true);
        accessibilityNodeInfoCompat.a.setTextSelection(i, i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r16v0, types: [androidx.core.view.accessibility.AccessibilityNodeInfoCompat, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5, types: [android.text.SpannableString] */
    @SuppressLint({"NewApi"})
    @CalledByNative
    public void setAccessibilityNodeInfoText(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, String str, boolean z, boolean z2, String str2, int[] iArr, int[] iArr2, String[] strArr, String str3) {
        CharSequence charSequence;
        if (z) {
            SpannableString spannableString = new SpannableString(str);
            spannableString.setSpan(new URLSpan(""), 0, spannableString.length(), 0);
            charSequence = spannableString;
        } else {
            charSequence = str;
        }
        boolean zIsEmpty = str2.isEmpty();
        a aVar = this.c;
        ?? spannableString2 = charSequence;
        if (!zIsEmpty) {
            spannableString2 = charSequence;
            if (!str2.equals(WebContentsAccessibilityImpl.this.f89J)) {
                SpannableString spannableString3 = charSequence instanceof SpannableString ? (SpannableString) charSequence : new SpannableString(charSequence);
                spannableString3.setSpan(new LocaleSpan(Locale.forLanguageTag(str2)), 0, spannableString3.length(), 0);
                spannableString2 = spannableString3;
            }
        }
        if (iArr != null && iArr.length > 0) {
            spannableString2 = spannableString2 instanceof SpannableString ? (SpannableString) spannableString2 : new SpannableString(spannableString2);
            int length = spannableString2.length();
            for (int i = 0; i < iArr.length; i++) {
                int i2 = iArr[i];
                int i3 = iArr2[i];
                if (i2 >= 0 && i2 <= length && i3 >= 0 && i3 <= length && i2 <= i3) {
                    spannableString2.setSpan(new SuggestionSpan(WebContentsAccessibilityImpl.this.h, new String[]{strArr[i]}, 2), i2, i3, 0);
                }
            }
        }
        if (str3 != null && !str3.isEmpty()) {
            accessibilityNodeInfoCompat.getClass();
            int i4 = yj.a;
            boolean z3 = Build.VERSION.SDK_INT >= 30;
            AccessibilityNodeInfo accessibilityNodeInfo = accessibilityNodeInfoCompat.a;
            if (z3) {
                accessibilityNodeInfo.setStateDescription(str3);
            } else {
                accessibilityNodeInfo.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.STATE_DESCRIPTION_KEY", str3);
            }
        }
        if (z) {
            accessibilityNodeInfoCompat.r(spannableString2);
        } else {
            accessibilityNodeInfoCompat.t(spannableString2);
        }
    }

    @CalledByNative
    public void setAccessibilityNodeInfoViewIdResourceName(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, String str) {
        accessibilityNodeInfoCompat.a.setViewIdResourceName(str);
    }
}
