package org.chromium.content.browser.accessibility;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.ViewStructure;
import defpackage.za2;
import java.util.ArrayList;
import java.util.Arrays;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class ViewStructureBuilder {
    public final za2 a;

    public ViewStructureBuilder(za2 za2Var) {
        this.a = za2Var;
    }

    @CalledByNative
    public final ViewStructure addViewStructureNodeChild(ViewStructure viewStructure, int i) {
        return viewStructure.asyncNewChild(i);
    }

    @CalledByNative
    public final void commitViewStructureNode(ViewStructure viewStructure) {
        viewStructure.asyncCommit();
    }

    @CalledByNative
    public final void populateViewStructureNode(ViewStructure viewStructure, String str, boolean z, int i, int i2, int i3, int i4, float f, boolean z2, boolean z3, boolean z4, boolean z5, String str2, int i5) {
        viewStructure.setClassName(str2);
        viewStructure.setChildCount(i5);
        if (z) {
            viewStructure.setText(str, i, i2);
        } else {
            viewStructure.setText(str);
        }
        if (f >= 0.0d) {
            viewStructure.setTextStyle(f, i3, i4, (z5 ? 8 : 0) | (z3 ? 2 : 0) | (z2 ? 1 : 0) | (z4 ? 4 : 0));
        }
    }

    @CalledByNative
    public final void setViewStructureNodeBounds(ViewStructure viewStructure, boolean z, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        za2 za2Var = this.a;
        int iA = (int) za2Var.a(i);
        int iA2 = (int) za2Var.a(i2);
        int iA3 = (int) za2Var.a(i3);
        int iA4 = (int) za2Var.a(i4);
        Rect rect = new Rect(iA, iA2, iA + iA3, iA2 + iA4);
        if (z) {
            rect.offset(0, (int) za2Var.k);
        }
        viewStructure.setDimens(rect.left, rect.top, 0, 0, iA3, iA4);
        int iA5 = (int) za2Var.a(i5);
        int iA6 = (int) za2Var.a(i6);
        int iA7 = (int) za2Var.a(i7);
        int iA8 = (int) za2Var.a(i8);
        Bundle extras = viewStructure.getExtras();
        extras.putInt("AccessibilityNodeInfo.unclippedLeft", iA5);
        extras.putInt("AccessibilityNodeInfo.unclippedTop", iA6);
        extras.putInt("AccessibilityNodeInfo.unclippedWidth", iA7);
        extras.putInt("AccessibilityNodeInfo.unclippedHeight", iA8);
    }

    @CalledByNative
    public void setViewStructureNodeHtmlInfo(ViewStructure viewStructure, String str, String str2, String[][] strArr) {
        Bundle extras = viewStructure.getExtras();
        extras.putCharSequence("htmlTag", str);
        extras.putCharSequence("display", str2);
        for (String[] strArr2 : strArr) {
            extras.putCharSequence(strArr2[0], strArr2[1]);
        }
    }

    @CalledByNative
    public void setViewStructureNodeHtmlMetadata(ViewStructure viewStructure, String[] strArr) {
        viewStructure.getExtras().putStringArrayList("metadata", new ArrayList<>(Arrays.asList(strArr)));
    }
}
