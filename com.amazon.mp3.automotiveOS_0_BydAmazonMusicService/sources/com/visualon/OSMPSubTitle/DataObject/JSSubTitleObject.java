package com.visualon.OSMPSubTitle.DataObject;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes7.dex */
public class JSSubTitleObject {
    public List<JSBlock> jsBlocks = new ArrayList();
    public JSRect mainJsRect;

    public enum HAlignmentType {
        LEFT(0),
        RIGHT(1),
        CENTER(2),
        NONE(3);

        private int value;

        public int getValue() {
            return this.value;
        }

        HAlignmentType(int i) {
            this.value = i;
        }

        public static HAlignmentType valueOf(int i) {
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].getValue() == i) {
                    return values()[i2];
                }
            }
            return CENTER;
        }
    }

    public enum VAlignmentType {
        TOP(0),
        BOTTOM(1),
        CENTER(2),
        NONE(3);

        private int value;

        public int getValue() {
            return this.value;
        }

        VAlignmentType(int i) {
            this.value = i;
        }

        public static VAlignmentType valueOf(int i) {
            for (int i2 = 0; i2 < HAlignmentType.values().length; i2++) {
                if (HAlignmentType.values()[i2].getValue() == i) {
                    return values()[i2];
                }
            }
            return CENTER;
        }
    }
}
