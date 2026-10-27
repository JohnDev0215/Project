package androidx.core.view.accessibility;

import android.graphics.Rect;
import android.view.accessibility.AccessibilityWindowInfo;

/* JADX INFO: loaded from: classes.dex */
public class AccessibilityWindowInfoCompat {
    public static final int TYPE_ACCESSIBILITY_OVERLAY = 4;
    public static final int TYPE_APPLICATION = 1;
    public static final int TYPE_INPUT_METHOD = 2;
    public static final int TYPE_SPLIT_SCREEN_DIVIDER = 5;
    public static final int TYPE_SYSTEM = 3;
    private static final int UNDEFINED = -1;
    private Object mInfo;

    private static String typeToString(int type) {
        return type != 1 ? type != 2 ? type != 3 ? type != 4 ? "<UNKNOWN>" : "TYPE_ACCESSIBILITY_OVERLAY" : "TYPE_SYSTEM" : "TYPE_INPUT_METHOD" : "TYPE_APPLICATION";
    }

    static AccessibilityWindowInfoCompat wrapNonNullInstance(Object object) {
        if (object != null) {
            return new AccessibilityWindowInfoCompat(object);
        }
        return null;
    }

    private AccessibilityWindowInfoCompat(Object info) {
        this.mInfo = info;
    }

    public int getType() {
        return ((AccessibilityWindowInfo) this.mInfo).getType();
    }

    public int getLayer() {
        return ((AccessibilityWindowInfo) this.mInfo).getLayer();
    }

    public AccessibilityNodeInfoCompat getRoot() {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(((AccessibilityWindowInfo) this.mInfo).getRoot());
    }

    public AccessibilityWindowInfoCompat getParent() {
        return wrapNonNullInstance(((AccessibilityWindowInfo) this.mInfo).getParent());
    }

    public int getId() {
        return ((AccessibilityWindowInfo) this.mInfo).getId();
    }

    public void getBoundsInScreen(Rect outBounds) {
        ((AccessibilityWindowInfo) this.mInfo).getBoundsInScreen(outBounds);
    }

    public boolean isActive() {
        return ((AccessibilityWindowInfo) this.mInfo).isActive();
    }

    public boolean isFocused() {
        return ((AccessibilityWindowInfo) this.mInfo).isFocused();
    }

    public boolean isAccessibilityFocused() {
        return ((AccessibilityWindowInfo) this.mInfo).isAccessibilityFocused();
    }

    public int getChildCount() {
        return ((AccessibilityWindowInfo) this.mInfo).getChildCount();
    }

    public AccessibilityWindowInfoCompat getChild(int index) {
        return wrapNonNullInstance(((AccessibilityWindowInfo) this.mInfo).getChild(index));
    }

    public CharSequence getTitle() {
        return ((AccessibilityWindowInfo) this.mInfo).getTitle();
    }

    public AccessibilityNodeInfoCompat getAnchor() {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(((AccessibilityWindowInfo) this.mInfo).getAnchor());
    }

    public static AccessibilityWindowInfoCompat obtain() {
        return wrapNonNullInstance(AccessibilityWindowInfo.obtain());
    }

    public static AccessibilityWindowInfoCompat obtain(AccessibilityWindowInfoCompat info) {
        if (info == null) {
            return null;
        }
        return wrapNonNullInstance(AccessibilityWindowInfo.obtain((AccessibilityWindowInfo) info.mInfo));
    }

    public void recycle() {
        ((AccessibilityWindowInfo) this.mInfo).recycle();
    }

    public int hashCode() {
        Object obj = this.mInfo;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AccessibilityWindowInfoCompat)) {
            return false;
        }
        AccessibilityWindowInfoCompat accessibilityWindowInfoCompat = (AccessibilityWindowInfoCompat) obj;
        Object obj2 = this.mInfo;
        if (obj2 == null) {
            if (accessibilityWindowInfoCompat.mInfo != null) {
                return false;
            }
        } else if (!obj2.equals(accessibilityWindowInfoCompat.mInfo)) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("AccessibilityWindowInfo[id=");
        Rect rect = new Rect();
        getBoundsInScreen(rect);
        sb.append(getId());
        sb.append(", type=").append(typeToString(getType()));
        sb.append(", layer=").append(getLayer());
        sb.append(", bounds=").append(rect);
        sb.append(", focused=").append(isFocused());
        sb.append(", active=").append(isActive());
        sb.append(", hasParent=").append(getParent() != null);
        sb.append(", hasChildren=").append(getChildCount() > 0);
        sb.append(']');
        return sb.toString();
    }
}
