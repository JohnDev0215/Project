package com.visualon.OSMPSubTitle.DataObject;

import com.visualon.OSMPSubTitle.DataObject.JSSubTitleObject;

/* JADX INFO: loaded from: classes7.dex */
public class JSWindow {
    public String color;
    public String edgeColor;
    public double edgeOpacity;
    public String edgeStyle;
    public JSSubTitleObject.HAlignmentType horizontalAlignment;
    public JSRect jsRect;
    public double opacity;
    public JSSubTitleObject.VAlignmentType verticalAlignment;

    public enum ALIGNMENT {
        HORIZONTAL,
        VERTICAL
    }

    private JSSubTitleObject.HAlignmentType mappingHAligmentType(String str) {
        return JSSubTitleObject.HAlignmentType.valueOf(str.toUpperCase());
    }

    private JSSubTitleObject.VAlignmentType mappingVAligmentType(String str) {
        return JSSubTitleObject.VAlignmentType.valueOf(str.toUpperCase());
    }

    public void setAlignment(ALIGNMENT alignment, String str) {
        if (alignment == ALIGNMENT.HORIZONTAL) {
            this.horizontalAlignment = mappingHAligmentType(str);
        } else if (alignment == ALIGNMENT.VERTICAL) {
            this.verticalAlignment = mappingVAligmentType(str);
        }
    }
}
