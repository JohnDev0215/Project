package org.chromium.content_public.browser;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class AccessibilitySnapshotNode {
    public int bgcolor;
    public boolean bold;
    public ArrayList<AccessibilitySnapshotNode> children = new ArrayList<>();
    public String className;
    public int color;
    public int endSelection;
    public boolean hasSelection;
    public boolean hasStyle;
    public int height;
    public boolean isRootNode;
    public boolean italic;
    public boolean lineThrough;
    public int startSelection;
    public String text;
    public float textSize;
    public boolean underline;
    public int width;
    public int x;
    public int y;

    public AccessibilitySnapshotNode(String str, String str2) {
        this.text = str;
        this.className = str2;
    }

    public void setStyle(int i, int i2, float f, boolean z, boolean z2, boolean z3, boolean z4) {
        this.color = i;
        this.bgcolor = i2;
        this.textSize = f;
        this.bold = z;
        this.italic = z2;
        this.underline = z3;
        this.lineThrough = z4;
        this.hasStyle = true;
    }

    public void setSelection(int i, int i2) {
        this.hasSelection = true;
        this.startSelection = i;
        this.endSelection = i2;
    }

    public void setLocationInfo(int i, int i2, int i3, int i4, boolean z) {
        this.x = i;
        this.y = i2;
        this.width = i3;
        this.height = i4;
        this.isRootNode = z;
    }

    public void addChild(AccessibilitySnapshotNode accessibilitySnapshotNode) {
        this.children.add(accessibilitySnapshotNode);
    }
}
