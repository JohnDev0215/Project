package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.parser.CLElement;
import androidx.constraintlayout.core.parser.CLKey;
import androidx.constraintlayout.core.parser.CLNumber;
import androidx.constraintlayout.core.parser.CLObject;
import androidx.constraintlayout.core.state.Transition;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;
import spi.c;

/* JADX INFO: loaded from: classes.dex */
public class WidgetFrame {
    private static final boolean OLD_SYSTEM = true;
    public static float phone_orientation = Float.NaN;
    public float alpha;
    public int bottom;
    public float interpolatedPos;
    public int left;
    public final HashMap<String, CustomVariable> mCustom;
    public String name;
    public float pivotX;
    public float pivotY;
    public int right;
    public float rotationX;
    public float rotationY;
    public float rotationZ;
    public float scaleX;
    public float scaleY;
    public int top;
    public float translationX;
    public float translationY;
    public float translationZ;
    public int visibility;
    public ConstraintWidget widget;

    public WidgetFrame() {
        this.widget = null;
        this.left = 0;
        this.top = 0;
        this.right = 0;
        this.bottom = 0;
        this.pivotX = Float.NaN;
        this.pivotY = Float.NaN;
        this.rotationX = Float.NaN;
        this.rotationY = Float.NaN;
        this.rotationZ = Float.NaN;
        this.translationX = Float.NaN;
        this.translationY = Float.NaN;
        this.translationZ = Float.NaN;
        this.scaleX = Float.NaN;
        this.scaleY = Float.NaN;
        this.alpha = Float.NaN;
        this.interpolatedPos = Float.NaN;
        this.visibility = 0;
        this.mCustom = new HashMap<>();
        this.name = null;
    }

    private static void add(StringBuilder sb, String str, int i) {
        sb.append(str);
        sb.append(": ");
        sb.append(i);
        sb.append(",\n");
    }

    public static void interpolate(int i, int i2, WidgetFrame widgetFrame, WidgetFrame widgetFrame2, WidgetFrame widgetFrame3, Transition transition, float f) {
        int i3;
        float f2;
        int i4;
        WidgetFrame widgetFrame4;
        float f3;
        int i5;
        int i6;
        int i7;
        int i8;
        float f4 = 100.0f * f;
        int i9 = (int) f4;
        int i10 = widgetFrame2.left;
        int i11 = widgetFrame2.top;
        int i12 = widgetFrame3.left;
        int i13 = widgetFrame3.top;
        int i14 = widgetFrame2.right - i10;
        int i15 = widgetFrame2.bottom - i11;
        int i16 = widgetFrame3.right - i12;
        int i17 = widgetFrame3.bottom - i13;
        float f5 = widgetFrame2.alpha;
        float f6 = widgetFrame3.alpha;
        if (widgetFrame2.visibility == 8) {
            i10 = (int) (i10 - (i16 / 2.0f));
            i11 = (int) (i11 - (i17 / 2.0f));
            if (Float.isNaN(f5)) {
                i4 = i17;
                i3 = i16;
                f2 = 0.0f;
            } else {
                f2 = f5;
                i3 = i16;
                i4 = i17;
            }
        } else {
            i3 = i14;
            f2 = f5;
            i4 = i15;
        }
        if (widgetFrame3.visibility == 8) {
            i12 = (int) (i12 - (i3 / 2.0f));
            i13 = (int) (i13 - (i4 / 2.0f));
            i16 = i3;
            i17 = i4;
            if (Float.isNaN(f6)) {
                f6 = 0.0f;
            }
        }
        float f7 = (!Float.isNaN(f2) || Float.isNaN(f6)) ? f2 : 1.0f;
        if (!Float.isNaN(f7) && Float.isNaN(f6)) {
            f6 = 1.0f;
        }
        if (widgetFrame.widget == null || !transition.hasPositionKeyframes()) {
            widgetFrame4 = widgetFrame2;
            f3 = f;
            i5 = i10;
        } else {
            Transition.KeyPosition keyPositionFindPreviousPosition = transition.findPreviousPosition(widgetFrame.widget.stringId, i9);
            int i18 = i10;
            Transition.KeyPosition keyPositionFindNextPosition = transition.findNextPosition(widgetFrame.widget.stringId, i9);
            if (keyPositionFindPreviousPosition == keyPositionFindNextPosition) {
                keyPositionFindNextPosition = null;
            }
            int i19 = 0;
            if (keyPositionFindPreviousPosition != null) {
                i6 = i2;
                i7 = (int) (keyPositionFindPreviousPosition.x * i);
                i11 = (int) (keyPositionFindPreviousPosition.y * i6);
                i19 = keyPositionFindPreviousPosition.frame;
            } else {
                i6 = i2;
                i7 = i18;
            }
            if (keyPositionFindNextPosition != null) {
                int i20 = (int) (keyPositionFindNextPosition.x * i);
                int i21 = (int) (keyPositionFindNextPosition.y * i6);
                i8 = keyPositionFindNextPosition.frame;
                i12 = i20;
                i13 = i21;
            } else {
                i8 = 100;
            }
            f3 = (f4 - i19) / (i8 - i19);
            widgetFrame4 = widgetFrame2;
            i5 = i7;
        }
        widgetFrame.widget = widgetFrame4.widget;
        int i22 = (int) (((i12 - i5) * f3) + i5);
        widgetFrame.left = i22;
        int i23 = (int) ((f3 * (i13 - i11)) + i11);
        widgetFrame.top = i23;
        float f8 = 1.0f - f;
        widgetFrame.right = i22 + ((int) ((i16 * f) + (i3 * f8)));
        widgetFrame.bottom = i23 + ((int) ((i17 * f) + (f8 * i4)));
        widgetFrame.pivotX = interpolate(widgetFrame4.pivotX, widgetFrame3.pivotX, 0.5f, f);
        widgetFrame.pivotY = interpolate(widgetFrame4.pivotY, widgetFrame3.pivotY, 0.5f, f);
        widgetFrame.rotationX = interpolate(widgetFrame4.rotationX, widgetFrame3.rotationX, 0.0f, f);
        widgetFrame.rotationY = interpolate(widgetFrame4.rotationY, widgetFrame3.rotationY, 0.0f, f);
        widgetFrame.rotationZ = interpolate(widgetFrame4.rotationZ, widgetFrame3.rotationZ, 0.0f, f);
        widgetFrame.scaleX = interpolate(widgetFrame4.scaleX, widgetFrame3.scaleX, 1.0f, f);
        widgetFrame.scaleY = interpolate(widgetFrame4.scaleY, widgetFrame3.scaleY, 1.0f, f);
        widgetFrame.translationX = interpolate(widgetFrame4.translationX, widgetFrame3.translationX, 0.0f, f);
        widgetFrame.translationY = interpolate(widgetFrame4.translationY, widgetFrame3.translationY, 0.0f, f);
        widgetFrame.translationZ = interpolate(widgetFrame4.translationZ, widgetFrame3.translationZ, 0.0f, f);
        widgetFrame.alpha = interpolate(f7, f6, 1.0f, f);
    }

    public void addCustomColor(String str, int i) {
        setCustomAttribute(str, 902, i);
    }

    public void addCustomFloat(String str, float f) {
        setCustomAttribute(str, 901, f);
    }

    public float centerX() {
        return ((this.right - r0) / 2.0f) + this.left;
    }

    public float centerY() {
        return ((this.bottom - r0) / 2.0f) + this.top;
    }

    public CustomVariable getCustomAttribute(String str) {
        return this.mCustom.get(str);
    }

    public Set<String> getCustomAttributeNames() {
        return this.mCustom.keySet();
    }

    public int getCustomColor(String str) {
        if (this.mCustom.containsKey(str)) {
            return this.mCustom.get(str).getColorValue();
        }
        return -21880;
    }

    public float getCustomFloat(String str) {
        if (this.mCustom.containsKey(str)) {
            return this.mCustom.get(str).getFloatValue();
        }
        return Float.NaN;
    }

    public int height() {
        return Math.max(0, this.bottom - this.top);
    }

    public boolean isDefaultTransform() {
        if (Float.isNaN(this.rotationX) && Float.isNaN(this.rotationY) && Float.isNaN(this.rotationZ) && Float.isNaN(this.translationX) && Float.isNaN(this.translationY) && Float.isNaN(this.translationZ) && Float.isNaN(this.scaleX) && Float.isNaN(this.scaleY) && Float.isNaN(this.alpha)) {
            return OLD_SYSTEM;
        }
        return false;
    }

    public void logv(String str) {
        String strF;
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        StringBuilder sbK = c.k(".(");
        sbK.append(stackTraceElement.getFileName());
        sbK.append(":");
        sbK.append(stackTraceElement.getLineNumber());
        sbK.append(") ");
        sbK.append(stackTraceElement.getMethodName());
        StringBuilder sbN = c.n(sbK.toString(), " ");
        sbN.append(hashCode() % 1000);
        String string = sbN.toString();
        if (this.widget != null) {
            StringBuilder sbN2 = c.n(string, "/");
            sbN2.append(this.widget.hashCode() % 1000);
            strF = sbN2.toString();
        } else {
            strF = c.f(string, "/NULL");
        }
        System.out.println(strF + " " + str);
    }

    public void parseCustom(CLElement cLElement) {
        CLObject cLObject = (CLObject) cLElement;
        int size = cLObject.size();
        for (int i = 0; i < size; i++) {
            CLKey cLKey = (CLKey) cLObject.get(i);
            cLKey.content();
            CLElement value = cLKey.getValue();
            String strContent = value.content();
            if (strContent.matches("#[0-9a-fA-F]+")) {
                setCustomAttribute(cLKey.content(), 902, Integer.parseInt(strContent.substring(1), 16));
            } else if (value instanceof CLNumber) {
                setCustomAttribute(cLKey.content(), 901, value.getFloat());
            } else {
                setCustomAttribute(cLKey.content(), 903, strContent);
            }
        }
    }

    public void printCustomAttributes() {
        String strF;
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        StringBuilder sbK = c.k(".(");
        sbK.append(stackTraceElement.getFileName());
        sbK.append(":");
        sbK.append(stackTraceElement.getLineNumber());
        sbK.append(") ");
        sbK.append(stackTraceElement.getMethodName());
        StringBuilder sbN = c.n(sbK.toString(), " ");
        sbN.append(hashCode() % 1000);
        String string = sbN.toString();
        if (this.widget != null) {
            StringBuilder sbN2 = c.n(string, "/");
            sbN2.append(this.widget.hashCode() % 1000);
            sbN2.append(" ");
            strF = sbN2.toString();
        } else {
            strF = c.f(string, "/NULL ");
        }
        HashMap<String, CustomVariable> map = this.mCustom;
        if (map != null) {
            for (String str : map.keySet()) {
                PrintStream printStream = System.out;
                StringBuilder sbK2 = c.k(strF);
                sbK2.append(this.mCustom.get(str).toString());
                printStream.println(sbK2.toString());
            }
        }
    }

    public StringBuilder serialize(StringBuilder sb) {
        return serialize(sb, false);
    }

    public void setCustomAttribute(String str, int i, float f) {
        if (this.mCustom.containsKey(str)) {
            this.mCustom.get(str).setFloatValue(f);
        } else {
            this.mCustom.put(str, new CustomVariable(str, i, f));
        }
    }

    public boolean setValue(String str, CLElement cLElement) {
        Objects.requireNonNull(str);
        switch (str) {
            case "phone_orientation":
                phone_orientation = cLElement.getFloat();
                return OLD_SYSTEM;
            case "bottom":
                this.bottom = cLElement.getInt();
                return OLD_SYSTEM;
            case "custom":
                parseCustom(cLElement);
                return OLD_SYSTEM;
            case "rotationX":
                this.rotationX = cLElement.getFloat();
                return OLD_SYSTEM;
            case "rotationY":
                this.rotationY = cLElement.getFloat();
                return OLD_SYSTEM;
            case "rotationZ":
                this.rotationZ = cLElement.getFloat();
                return OLD_SYSTEM;
            case "translationX":
                this.translationX = cLElement.getFloat();
                return OLD_SYSTEM;
            case "translationY":
                this.translationY = cLElement.getFloat();
                return OLD_SYSTEM;
            case "translationZ":
                this.translationZ = cLElement.getFloat();
                return OLD_SYSTEM;
            case "pivotX":
                this.pivotX = cLElement.getFloat();
                return OLD_SYSTEM;
            case "pivotY":
                this.pivotY = cLElement.getFloat();
                return OLD_SYSTEM;
            case "scaleX":
                this.scaleX = cLElement.getFloat();
                return OLD_SYSTEM;
            case "scaleY":
                this.scaleY = cLElement.getFloat();
                return OLD_SYSTEM;
            case "top":
                this.top = cLElement.getInt();
                return OLD_SYSTEM;
            case "left":
                this.left = cLElement.getInt();
                return OLD_SYSTEM;
            case "alpha":
                this.alpha = cLElement.getFloat();
                return OLD_SYSTEM;
            case "right":
                this.right = cLElement.getInt();
                return OLD_SYSTEM;
            case "interpolatedPos":
                this.interpolatedPos = cLElement.getFloat();
                return OLD_SYSTEM;
            default:
                return false;
        }
    }

    public WidgetFrame update() {
        ConstraintWidget constraintWidget = this.widget;
        if (constraintWidget != null) {
            this.left = constraintWidget.getLeft();
            this.top = this.widget.getTop();
            this.right = this.widget.getRight();
            this.bottom = this.widget.getBottom();
            updateAttributes(this.widget.frame);
        }
        return this;
    }

    public void updateAttributes(WidgetFrame widgetFrame) {
        this.pivotX = widgetFrame.pivotX;
        this.pivotY = widgetFrame.pivotY;
        this.rotationX = widgetFrame.rotationX;
        this.rotationY = widgetFrame.rotationY;
        this.rotationZ = widgetFrame.rotationZ;
        this.translationX = widgetFrame.translationX;
        this.translationY = widgetFrame.translationY;
        this.translationZ = widgetFrame.translationZ;
        this.scaleX = widgetFrame.scaleX;
        this.scaleY = widgetFrame.scaleY;
        this.alpha = widgetFrame.alpha;
        this.visibility = widgetFrame.visibility;
        this.mCustom.clear();
        for (CustomVariable customVariable : widgetFrame.mCustom.values()) {
            this.mCustom.put(customVariable.getName(), customVariable.copy());
        }
    }

    public int width() {
        return Math.max(0, this.right - this.left);
    }

    public StringBuilder serialize(StringBuilder sb, boolean z) {
        sb.append("{\n");
        add(sb, "left", this.left);
        add(sb, "top", this.top);
        add(sb, "right", this.right);
        add(sb, "bottom", this.bottom);
        add(sb, "pivotX", this.pivotX);
        add(sb, "pivotY", this.pivotY);
        add(sb, "rotationX", this.rotationX);
        add(sb, "rotationY", this.rotationY);
        add(sb, "rotationZ", this.rotationZ);
        add(sb, "translationX", this.translationX);
        add(sb, "translationY", this.translationY);
        add(sb, "translationZ", this.translationZ);
        add(sb, "scaleX", this.scaleX);
        add(sb, "scaleY", this.scaleY);
        add(sb, "alpha", this.alpha);
        add(sb, "visibility", this.left);
        add(sb, "interpolatedPos", this.interpolatedPos);
        if (z) {
            add(sb, "phone_orientation", phone_orientation);
        }
        if (z) {
            add(sb, "phone_orientation", phone_orientation);
        }
        if (this.mCustom.size() != 0) {
            sb.append("custom : {\n");
            for (String str : this.mCustom.keySet()) {
                CustomVariable customVariable = this.mCustom.get(str);
                sb.append(str);
                sb.append(": ");
                switch (customVariable.getType()) {
                    case 900:
                        sb.append(customVariable.getIntegerValue());
                        sb.append(",\n");
                        break;
                    case 901:
                    case 905:
                        sb.append(customVariable.getFloatValue());
                        sb.append(",\n");
                        break;
                    case 902:
                        sb.append("'");
                        sb.append(CustomVariable.colorString(customVariable.getIntegerValue()));
                        sb.append("',\n");
                        break;
                    case 903:
                        sb.append("'");
                        sb.append(customVariable.getStringValue());
                        sb.append("',\n");
                        break;
                    case 904:
                        sb.append("'");
                        sb.append(customVariable.getBooleanValue());
                        sb.append("',\n");
                        break;
                }
            }
            sb.append("}\n");
        }
        sb.append("}\n");
        return sb;
    }

    public void setCustomAttribute(String str, int i, int i2) {
        if (this.mCustom.containsKey(str)) {
            this.mCustom.get(str).setIntValue(i2);
        } else {
            this.mCustom.put(str, new CustomVariable(str, i, i2));
        }
    }

    private static void add(StringBuilder sb, String str, float f) {
        if (Float.isNaN(f)) {
            return;
        }
        sb.append(str);
        sb.append(": ");
        sb.append(f);
        sb.append(",\n");
    }

    public void setCustomAttribute(String str, int i, boolean z) {
        if (this.mCustom.containsKey(str)) {
            this.mCustom.get(str).setBooleanValue(z);
        } else {
            this.mCustom.put(str, new CustomVariable(str, i, z));
        }
    }

    public WidgetFrame update(ConstraintWidget constraintWidget) {
        if (constraintWidget == null) {
            return this;
        }
        this.widget = constraintWidget;
        update();
        return this;
    }

    public void setCustomAttribute(String str, int i, String str2) {
        if (this.mCustom.containsKey(str)) {
            this.mCustom.get(str).setStringValue(str2);
        } else {
            this.mCustom.put(str, new CustomVariable(str, i, str2));
        }
    }

    public WidgetFrame(ConstraintWidget constraintWidget) {
        this.widget = null;
        this.left = 0;
        this.top = 0;
        this.right = 0;
        this.bottom = 0;
        this.pivotX = Float.NaN;
        this.pivotY = Float.NaN;
        this.rotationX = Float.NaN;
        this.rotationY = Float.NaN;
        this.rotationZ = Float.NaN;
        this.translationX = Float.NaN;
        this.translationY = Float.NaN;
        this.translationZ = Float.NaN;
        this.scaleX = Float.NaN;
        this.scaleY = Float.NaN;
        this.alpha = Float.NaN;
        this.interpolatedPos = Float.NaN;
        this.visibility = 0;
        this.mCustom = new HashMap<>();
        this.name = null;
        this.widget = constraintWidget;
    }

    private static float interpolate(float f, float f2, float f3, float f4) {
        boolean zIsNaN = Float.isNaN(f);
        boolean zIsNaN2 = Float.isNaN(f2);
        if (zIsNaN && zIsNaN2) {
            return Float.NaN;
        }
        if (zIsNaN) {
            f = f3;
        }
        if (zIsNaN2) {
            f2 = f3;
        }
        return c.a(f2, f, f4, f);
    }

    public WidgetFrame(WidgetFrame widgetFrame) {
        this.widget = null;
        this.left = 0;
        this.top = 0;
        this.right = 0;
        this.bottom = 0;
        this.pivotX = Float.NaN;
        this.pivotY = Float.NaN;
        this.rotationX = Float.NaN;
        this.rotationY = Float.NaN;
        this.rotationZ = Float.NaN;
        this.translationX = Float.NaN;
        this.translationY = Float.NaN;
        this.translationZ = Float.NaN;
        this.scaleX = Float.NaN;
        this.scaleY = Float.NaN;
        this.alpha = Float.NaN;
        this.interpolatedPos = Float.NaN;
        this.visibility = 0;
        this.mCustom = new HashMap<>();
        this.name = null;
        this.widget = widgetFrame.widget;
        this.left = widgetFrame.left;
        this.top = widgetFrame.top;
        this.right = widgetFrame.right;
        this.bottom = widgetFrame.bottom;
        updateAttributes(widgetFrame);
    }
}
