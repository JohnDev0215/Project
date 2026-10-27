package p;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import com.spotify.connect.esperanto.proto.ConnectMessages$StateResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class py {
    public static final int[] d = {0, 4, 8};
    public static final SparseIntArray e;
    public static final SparseIntArray f;
    public final HashMap a = new HashMap();
    public final boolean b = true;
    public final HashMap c = new HashMap();

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        e = sparseIntArray;
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        f = sparseIntArray2;
        sparseIntArray.append(82, 25);
        sparseIntArray.append(83, 26);
        sparseIntArray.append(85, 29);
        sparseIntArray.append(86, 30);
        sparseIntArray.append(92, 36);
        sparseIntArray.append(91, 35);
        sparseIntArray.append(63, 4);
        sparseIntArray.append(62, 3);
        sparseIntArray.append(58, 1);
        sparseIntArray.append(60, 91);
        sparseIntArray.append(59, 92);
        sparseIntArray.append(101, 6);
        sparseIntArray.append(102, 7);
        sparseIntArray.append(70, 17);
        sparseIntArray.append(71, 18);
        sparseIntArray.append(72, 19);
        sparseIntArray.append(54, 99);
        sparseIntArray.append(0, 27);
        sparseIntArray.append(87, 32);
        sparseIntArray.append(88, 33);
        sparseIntArray.append(69, 10);
        sparseIntArray.append(68, 9);
        sparseIntArray.append(106, 13);
        sparseIntArray.append(109, 16);
        sparseIntArray.append(107, 14);
        sparseIntArray.append(104, 11);
        sparseIntArray.append(108, 15);
        sparseIntArray.append(105, 12);
        sparseIntArray.append(95, 40);
        sparseIntArray.append(80, 39);
        sparseIntArray.append(79, 41);
        sparseIntArray.append(94, 42);
        sparseIntArray.append(78, 20);
        sparseIntArray.append(93, 37);
        sparseIntArray.append(67, 5);
        sparseIntArray.append(81, 87);
        sparseIntArray.append(90, 87);
        sparseIntArray.append(84, 87);
        sparseIntArray.append(61, 87);
        sparseIntArray.append(57, 87);
        sparseIntArray.append(5, 24);
        sparseIntArray.append(7, 28);
        sparseIntArray.append(23, 31);
        sparseIntArray.append(24, 8);
        sparseIntArray.append(6, 34);
        sparseIntArray.append(8, 2);
        sparseIntArray.append(3, 23);
        sparseIntArray.append(4, 21);
        sparseIntArray.append(96, 95);
        sparseIntArray.append(73, 96);
        sparseIntArray.append(2, 22);
        sparseIntArray.append(13, 43);
        sparseIntArray.append(26, 44);
        sparseIntArray.append(21, 45);
        sparseIntArray.append(22, 46);
        sparseIntArray.append(20, 60);
        sparseIntArray.append(18, 47);
        sparseIntArray.append(19, 48);
        sparseIntArray.append(14, 49);
        sparseIntArray.append(15, 50);
        sparseIntArray.append(16, 51);
        sparseIntArray.append(17, 52);
        sparseIntArray.append(25, 53);
        sparseIntArray.append(97, 54);
        sparseIntArray.append(74, 55);
        sparseIntArray.append(98, 56);
        sparseIntArray.append(75, 57);
        sparseIntArray.append(99, 58);
        sparseIntArray.append(76, 59);
        sparseIntArray.append(64, 61);
        sparseIntArray.append(66, 62);
        sparseIntArray.append(65, 63);
        sparseIntArray.append(28, 64);
        sparseIntArray.append(121, 65);
        sparseIntArray.append(35, 66);
        sparseIntArray.append(122, 67);
        sparseIntArray.append(113, 79);
        sparseIntArray.append(1, 38);
        sparseIntArray.append(112, 68);
        sparseIntArray.append(100, 69);
        sparseIntArray.append(77, 70);
        sparseIntArray.append(111, 97);
        sparseIntArray.append(32, 71);
        sparseIntArray.append(30, 72);
        sparseIntArray.append(31, 73);
        sparseIntArray.append(33, 74);
        sparseIntArray.append(29, 75);
        sparseIntArray.append(114, 76);
        sparseIntArray.append(89, 77);
        sparseIntArray.append(123, 78);
        sparseIntArray.append(56, 80);
        sparseIntArray.append(55, 81);
        sparseIntArray.append(116, 82);
        sparseIntArray.append(120, 83);
        sparseIntArray.append(119, 84);
        sparseIntArray.append(118, 85);
        sparseIntArray.append(117, 86);
        sparseIntArray2.append(85, 6);
        sparseIntArray2.append(85, 7);
        sparseIntArray2.append(0, 27);
        sparseIntArray2.append(89, 13);
        sparseIntArray2.append(92, 16);
        sparseIntArray2.append(90, 14);
        sparseIntArray2.append(87, 11);
        sparseIntArray2.append(91, 15);
        sparseIntArray2.append(88, 12);
        sparseIntArray2.append(78, 40);
        sparseIntArray2.append(71, 39);
        sparseIntArray2.append(70, 41);
        sparseIntArray2.append(77, 42);
        sparseIntArray2.append(69, 20);
        sparseIntArray2.append(76, 37);
        sparseIntArray2.append(60, 5);
        sparseIntArray2.append(72, 87);
        sparseIntArray2.append(75, 87);
        sparseIntArray2.append(73, 87);
        sparseIntArray2.append(57, 87);
        sparseIntArray2.append(56, 87);
        sparseIntArray2.append(5, 24);
        sparseIntArray2.append(7, 28);
        sparseIntArray2.append(23, 31);
        sparseIntArray2.append(24, 8);
        sparseIntArray2.append(6, 34);
        sparseIntArray2.append(8, 2);
        sparseIntArray2.append(3, 23);
        sparseIntArray2.append(4, 21);
        sparseIntArray2.append(79, 95);
        sparseIntArray2.append(64, 96);
        sparseIntArray2.append(2, 22);
        sparseIntArray2.append(13, 43);
        sparseIntArray2.append(26, 44);
        sparseIntArray2.append(21, 45);
        sparseIntArray2.append(22, 46);
        sparseIntArray2.append(20, 60);
        sparseIntArray2.append(18, 47);
        sparseIntArray2.append(19, 48);
        sparseIntArray2.append(14, 49);
        sparseIntArray2.append(15, 50);
        sparseIntArray2.append(16, 51);
        sparseIntArray2.append(17, 52);
        sparseIntArray2.append(25, 53);
        sparseIntArray2.append(80, 54);
        sparseIntArray2.append(65, 55);
        sparseIntArray2.append(81, 56);
        sparseIntArray2.append(66, 57);
        sparseIntArray2.append(82, 58);
        sparseIntArray2.append(67, 59);
        sparseIntArray2.append(59, 62);
        sparseIntArray2.append(58, 63);
        sparseIntArray2.append(28, 64);
        sparseIntArray2.append(105, 65);
        sparseIntArray2.append(34, 66);
        sparseIntArray2.append(106, 67);
        sparseIntArray2.append(96, 79);
        sparseIntArray2.append(1, 38);
        sparseIntArray2.append(97, 98);
        sparseIntArray2.append(95, 68);
        sparseIntArray2.append(83, 69);
        sparseIntArray2.append(68, 70);
        sparseIntArray2.append(32, 71);
        sparseIntArray2.append(30, 72);
        sparseIntArray2.append(31, 73);
        sparseIntArray2.append(33, 74);
        sparseIntArray2.append(29, 75);
        sparseIntArray2.append(98, 76);
        sparseIntArray2.append(74, 77);
        sparseIntArray2.append(107, 78);
        sparseIntArray2.append(55, 80);
        sparseIntArray2.append(54, 81);
        sparseIntArray2.append(100, 82);
        sparseIntArray2.append(104, 83);
        sparseIntArray2.append(103, 84);
        sparseIntArray2.append(102, 85);
        sparseIntArray2.append(101, 86);
        sparseIntArray2.append(94, 97);
    }

    public static int[] c(Barrier barrier, String str) {
        int iIntValue;
        HashMap map;
        String[] strArrSplit = str.split(",");
        Context context = barrier.getContext();
        int[] iArr = new int[strArrSplit.length];
        int i = 0;
        int i2 = 0;
        while (i < strArrSplit.length) {
            String strTrim = strArrSplit[i].trim();
            Object obj = null;
            try {
                iIntValue = qz1.class.getField(strTrim).getInt(null);
            } catch (Exception unused) {
                iIntValue = 0;
            }
            if (iIntValue == 0) {
                iIntValue = context.getResources().getIdentifier(strTrim, "id", context.getPackageName());
            }
            if (iIntValue == 0 && barrier.isInEditMode() && (barrier.getParent() instanceof ConstraintLayout)) {
                ConstraintLayout constraintLayout = (ConstraintLayout) barrier.getParent();
                constraintLayout.getClass();
                if ((strTrim instanceof String) && (map = constraintLayout.u) != null && map.containsKey(strTrim)) {
                    obj = constraintLayout.u.get(strTrim);
                }
                if (obj != null && (obj instanceof Integer)) {
                    iIntValue = ((Integer) obj).intValue();
                }
            }
            iArr[i2] = iIntValue;
            i++;
            i2++;
        }
        return i2 != strArrSplit.length ? Arrays.copyOf(iArr, i2) : iArr;
    }

    public static ky d(Context context, AttributeSet attributeSet, boolean z) {
        SparseIntArray sparseIntArray;
        int i;
        ky kyVar = new ky();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, z ? sz1.c : sz1.a);
        int[] iArr = d;
        SparseIntArray sparseIntArray2 = e;
        String[] strArr = bk0.e;
        ny nyVar = kyVar.b;
        oy oyVar = kyVar.e;
        my myVar = kyVar.c;
        ly lyVar = kyVar.d;
        if (z) {
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            jy jyVar = new jy();
            jyVar.a = new int[10];
            jyVar.b = new int[10];
            jyVar.c = 0;
            jyVar.d = new int[10];
            jyVar.e = new float[10];
            jyVar.f = 0;
            jyVar.g = new int[5];
            jyVar.h = new String[5];
            jyVar.i = 0;
            jyVar.j = new int[4];
            jyVar.k = new boolean[4];
            jyVar.l = 0;
            myVar.getClass();
            lyVar.getClass();
            nyVar.getClass();
            oyVar.getClass();
            int i2 = 0;
            while (i2 < indexCount) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                int i3 = indexCount;
                switch (f.get(index)) {
                    case 2:
                        i = i2;
                        jyVar.b(2, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, lyVar.I));
                        break;
                    case 3:
                    case 4:
                    case 9:
                    case 10:
                    case 25:
                    case 26:
                    case 29:
                    case 30:
                    case ConnectMessages$StateResponse.Device.VOLUME_STEPS_FIELD_NUMBER /* 32 */:
                    case ConnectMessages$StateResponse.Device.CAPABILITIES_FIELD_NUMBER /* 33 */:
                    case ConnectMessages$StateResponse.Device.ALIAS_ID_FIELD_NUMBER /* 35 */:
                    case ConnectMessages$StateResponse.Device.SUPPORTS_DJ_FIELD_NUMBER /* 36 */:
                    case 61:
                    case 88:
                    case 89:
                    case 90:
                    case 91:
                    case 92:
                    default:
                        Integer.toHexString(index);
                        sparseIntArray2.get(index);
                        i = i2;
                        break;
                    case 5:
                        i = i2;
                        jyVar.c(5, typedArrayObtainStyledAttributes.getString(index));
                        break;
                    case 6:
                        i = i2;
                        jyVar.b(6, typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, lyVar.C));
                        break;
                    case 7:
                        i = i2;
                        jyVar.b(7, typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, lyVar.D));
                        break;
                    case 8:
                        i = i2;
                        jyVar.b(8, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, lyVar.J));
                        break;
                    case 11:
                        i = i2;
                        jyVar.b(11, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, lyVar.P));
                        break;
                    case 12:
                        i = i2;
                        jyVar.b(12, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, lyVar.Q));
                        break;
                    case 13:
                        i = i2;
                        jyVar.b(13, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, lyVar.M));
                        break;
                    case 14:
                        i = i2;
                        jyVar.b(14, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, lyVar.O));
                        break;
                    case 15:
                        i = i2;
                        jyVar.b(15, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, lyVar.R));
                        break;
                    case 16:
                        i = i2;
                        jyVar.b(16, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, lyVar.N));
                        break;
                    case 17:
                        i = i2;
                        jyVar.b(17, typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, lyVar.d));
                        break;
                    case 18:
                        i = i2;
                        jyVar.b(18, typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, lyVar.e));
                        break;
                    case 19:
                        i = i2;
                        jyVar.a(typedArrayObtainStyledAttributes.getFloat(index, lyVar.f), 19);
                        break;
                    case 20:
                        i = i2;
                        jyVar.a(typedArrayObtainStyledAttributes.getFloat(index, lyVar.w), 20);
                        break;
                    case 21:
                        i = i2;
                        jyVar.b(21, typedArrayObtainStyledAttributes.getLayoutDimension(index, lyVar.c));
                        break;
                    case 22:
                        i = i2;
                        jyVar.b(22, iArr[typedArrayObtainStyledAttributes.getInt(index, nyVar.a)]);
                        break;
                    case 23:
                        i = i2;
                        jyVar.b(23, typedArrayObtainStyledAttributes.getLayoutDimension(index, lyVar.b));
                        break;
                    case 24:
                        i = i2;
                        jyVar.b(24, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, lyVar.F));
                        break;
                    case 27:
                        i = i2;
                        jyVar.b(27, typedArrayObtainStyledAttributes.getInt(index, lyVar.E));
                        break;
                    case 28:
                        i = i2;
                        jyVar.b(28, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, lyVar.G));
                        break;
                    case 31:
                        i = i2;
                        jyVar.b(31, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, lyVar.K));
                        break;
                    case ConnectMessages$StateResponse.Device.INCARNATIONS_FIELD_NUMBER /* 34 */:
                        i = i2;
                        jyVar.b(34, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, lyVar.H));
                        break;
                    case ConnectMessages$StateResponse.Device.LIBRARY_VERSION_FIELD_NUMBER /* 37 */:
                        i = i2;
                        jyVar.a(typedArrayObtainStyledAttributes.getFloat(index, lyVar.x), 37);
                        break;
                    case ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_NAME_FIELD_NUMBER /* 38 */:
                        i = i2;
                        int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, kyVar.a);
                        kyVar.a = resourceId;
                        jyVar.b(38, resourceId);
                        break;
                    case ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_TYPE_FIELD_NUMBER /* 39 */:
                        i = i2;
                        jyVar.a(typedArrayObtainStyledAttributes.getFloat(index, lyVar.U), 39);
                        break;
                    case 40:
                        i = i2;
                        jyVar.a(typedArrayObtainStyledAttributes.getFloat(index, lyVar.T), 40);
                        break;
                    case 41:
                        i = i2;
                        jyVar.b(41, typedArrayObtainStyledAttributes.getInt(index, lyVar.V));
                        break;
                    case 42:
                        i = i2;
                        jyVar.b(42, typedArrayObtainStyledAttributes.getInt(index, lyVar.W));
                        break;
                    case 43:
                        i = i2;
                        jyVar.a(typedArrayObtainStyledAttributes.getFloat(index, nyVar.c), 43);
                        break;
                    case 44:
                        i = i2;
                        jyVar.d(44, true);
                        jyVar.a(typedArrayObtainStyledAttributes.getDimension(index, oyVar.m), 44);
                        break;
                    case 45:
                        i = i2;
                        jyVar.a(typedArrayObtainStyledAttributes.getFloat(index, oyVar.b), 45);
                        break;
                    case 46:
                        i = i2;
                        jyVar.a(typedArrayObtainStyledAttributes.getFloat(index, oyVar.c), 46);
                        break;
                    case 47:
                        i = i2;
                        jyVar.a(typedArrayObtainStyledAttributes.getFloat(index, oyVar.d), 47);
                        break;
                    case 48:
                        i = i2;
                        jyVar.a(typedArrayObtainStyledAttributes.getFloat(index, oyVar.e), 48);
                        break;
                    case 49:
                        i = i2;
                        jyVar.a(typedArrayObtainStyledAttributes.getDimension(index, oyVar.f), 49);
                        break;
                    case 50:
                        i = i2;
                        jyVar.a(typedArrayObtainStyledAttributes.getDimension(index, oyVar.g), 50);
                        break;
                    case 51:
                        i = i2;
                        jyVar.a(typedArrayObtainStyledAttributes.getDimension(index, oyVar.i), 51);
                        break;
                    case 52:
                        i = i2;
                        jyVar.a(typedArrayObtainStyledAttributes.getDimension(index, oyVar.j), 52);
                        break;
                    case 53:
                        i = i2;
                        jyVar.a(typedArrayObtainStyledAttributes.getDimension(index, oyVar.k), 53);
                        break;
                    case 54:
                        i = i2;
                        jyVar.b(54, typedArrayObtainStyledAttributes.getInt(index, lyVar.X));
                        break;
                    case 55:
                        i = i2;
                        jyVar.b(55, typedArrayObtainStyledAttributes.getInt(index, lyVar.Y));
                        break;
                    case 56:
                        i = i2;
                        jyVar.b(56, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, lyVar.Z));
                        break;
                    case 57:
                        i = i2;
                        jyVar.b(57, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, lyVar.a0));
                        break;
                    case 58:
                        i = i2;
                        jyVar.b(58, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, lyVar.b0));
                        break;
                    case 59:
                        i = i2;
                        jyVar.b(59, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, lyVar.c0));
                        break;
                    case 60:
                        i = i2;
                        jyVar.a(typedArrayObtainStyledAttributes.getFloat(index, oyVar.a), 60);
                        break;
                    case 62:
                        i = i2;
                        jyVar.b(62, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, lyVar.A));
                        break;
                    case 63:
                        i = i2;
                        jyVar.a(typedArrayObtainStyledAttributes.getFloat(index, lyVar.B), 63);
                        break;
                    case 64:
                        i = i2;
                        jyVar.b(64, f(typedArrayObtainStyledAttributes, index, myVar.a));
                        break;
                    case 65:
                        i = i2;
                        if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                            jyVar.c(65, typedArrayObtainStyledAttributes.getString(index));
                        } else {
                            jyVar.c(65, strArr[typedArrayObtainStyledAttributes.getInteger(index, 0)]);
                        }
                        break;
                    case 66:
                        i = i2;
                        jyVar.b(66, typedArrayObtainStyledAttributes.getInt(index, 0));
                        break;
                    case 67:
                        i = i2;
                        jyVar.a(typedArrayObtainStyledAttributes.getFloat(index, myVar.e), 67);
                        break;
                    case 68:
                        i = i2;
                        jyVar.a(typedArrayObtainStyledAttributes.getFloat(index, nyVar.d), 68);
                        break;
                    case 69:
                        i = i2;
                        jyVar.a(typedArrayObtainStyledAttributes.getFloat(index, 1.0f), 69);
                        break;
                    case 70:
                        i = i2;
                        jyVar.a(typedArrayObtainStyledAttributes.getFloat(index, 1.0f), 70);
                        break;
                    case 71:
                        i = i2;
                        Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                        break;
                    case 72:
                        i = i2;
                        jyVar.b(72, typedArrayObtainStyledAttributes.getInt(index, lyVar.f0));
                        break;
                    case 73:
                        i = i2;
                        jyVar.b(73, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, lyVar.g0));
                        break;
                    case 74:
                        i = i2;
                        jyVar.c(74, typedArrayObtainStyledAttributes.getString(index));
                        break;
                    case 75:
                        i = i2;
                        jyVar.d(75, typedArrayObtainStyledAttributes.getBoolean(index, lyVar.n0));
                        break;
                    case 76:
                        i = i2;
                        jyVar.b(76, typedArrayObtainStyledAttributes.getInt(index, myVar.c));
                        break;
                    case 77:
                        i = i2;
                        jyVar.c(77, typedArrayObtainStyledAttributes.getString(index));
                        break;
                    case 78:
                        i = i2;
                        jyVar.b(78, typedArrayObtainStyledAttributes.getInt(index, nyVar.b));
                        break;
                    case 79:
                        i = i2;
                        jyVar.a(typedArrayObtainStyledAttributes.getFloat(index, myVar.d), 79);
                        break;
                    case 80:
                        i = i2;
                        jyVar.d(80, typedArrayObtainStyledAttributes.getBoolean(index, lyVar.l0));
                        break;
                    case 81:
                        i = i2;
                        jyVar.d(81, typedArrayObtainStyledAttributes.getBoolean(index, lyVar.m0));
                        break;
                    case 82:
                        i = i2;
                        jyVar.b(82, typedArrayObtainStyledAttributes.getInteger(index, myVar.b));
                        break;
                    case 83:
                        i = i2;
                        jyVar.b(83, f(typedArrayObtainStyledAttributes, index, oyVar.h));
                        break;
                    case 84:
                        i = i2;
                        jyVar.b(84, typedArrayObtainStyledAttributes.getInteger(index, myVar.g));
                        break;
                    case 85:
                        i = i2;
                        jyVar.a(typedArrayObtainStyledAttributes.getFloat(index, myVar.f), 85);
                        break;
                    case 86:
                        i = i2;
                        int i4 = typedArrayObtainStyledAttributes.peekValue(index).type;
                        if (i4 == 1) {
                            int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                            myVar.i = resourceId2;
                            jyVar.b(89, resourceId2);
                            if (myVar.i != -1) {
                                jyVar.b(88, -2);
                            }
                        } else if (i4 == 3) {
                            String string = typedArrayObtainStyledAttributes.getString(index);
                            myVar.h = string;
                            jyVar.c(90, string);
                            if (myVar.h.indexOf("/") > 0) {
                                int resourceId3 = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                                myVar.i = resourceId3;
                                jyVar.b(89, resourceId3);
                                jyVar.b(88, -2);
                            } else {
                                jyVar.b(88, -1);
                            }
                        } else {
                            jyVar.b(88, typedArrayObtainStyledAttributes.getInteger(index, myVar.i));
                        }
                        break;
                    case 87:
                        i = i2;
                        Integer.toHexString(index);
                        sparseIntArray2.get(index);
                        break;
                    case 93:
                        i = i2;
                        jyVar.b(93, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, lyVar.L));
                        break;
                    case 94:
                        i = i2;
                        jyVar.b(94, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, lyVar.S));
                        break;
                    case 95:
                        i = i2;
                        g(jyVar, typedArrayObtainStyledAttributes, index, 0);
                        break;
                    case 96:
                        i = i2;
                        g(jyVar, typedArrayObtainStyledAttributes, index, 1);
                        break;
                    case 97:
                        i = i2;
                        jyVar.b(97, typedArrayObtainStyledAttributes.getInt(index, lyVar.o0));
                        break;
                    case 98:
                        int i5 = kl1.y;
                        i = i2;
                        if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                            typedArrayObtainStyledAttributes.getString(index);
                        } else {
                            kyVar.a = typedArrayObtainStyledAttributes.getResourceId(index, kyVar.a);
                        }
                        break;
                    case 99:
                        jyVar.d(99, typedArrayObtainStyledAttributes.getBoolean(index, lyVar.g));
                        i = i2;
                        break;
                }
                i2 = i + 1;
                indexCount = i3;
            }
        } else {
            int indexCount2 = typedArrayObtainStyledAttributes.getIndexCount();
            int i6 = 0;
            while (i6 < indexCount2) {
                int index2 = typedArrayObtainStyledAttributes.getIndex(i6);
                if (index2 != 1 && 23 != index2) {
                    if (24 != index2) {
                        myVar.getClass();
                        lyVar.getClass();
                        nyVar.getClass();
                        oyVar.getClass();
                    }
                }
                switch (sparseIntArray2.get(index2)) {
                    case 1:
                        sparseIntArray = sparseIntArray2;
                        lyVar.f152p = f(typedArrayObtainStyledAttributes, index2, lyVar.f152p);
                        break;
                    case 2:
                        sparseIntArray = sparseIntArray2;
                        lyVar.I = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, lyVar.I);
                        break;
                    case 3:
                        sparseIntArray = sparseIntArray2;
                        lyVar.o = f(typedArrayObtainStyledAttributes, index2, lyVar.o);
                        break;
                    case 4:
                        sparseIntArray = sparseIntArray2;
                        lyVar.n = f(typedArrayObtainStyledAttributes, index2, lyVar.n);
                        break;
                    case 5:
                        sparseIntArray = sparseIntArray2;
                        lyVar.y = typedArrayObtainStyledAttributes.getString(index2);
                        break;
                    case 6:
                        sparseIntArray = sparseIntArray2;
                        lyVar.C = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index2, lyVar.C);
                        break;
                    case 7:
                        sparseIntArray = sparseIntArray2;
                        lyVar.D = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index2, lyVar.D);
                        break;
                    case 8:
                        sparseIntArray = sparseIntArray2;
                        lyVar.J = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, lyVar.J);
                        break;
                    case 9:
                        sparseIntArray = sparseIntArray2;
                        lyVar.v = f(typedArrayObtainStyledAttributes, index2, lyVar.v);
                        break;
                    case 10:
                        sparseIntArray = sparseIntArray2;
                        lyVar.u = f(typedArrayObtainStyledAttributes, index2, lyVar.u);
                        break;
                    case 11:
                        sparseIntArray = sparseIntArray2;
                        lyVar.P = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, lyVar.P);
                        break;
                    case 12:
                        sparseIntArray = sparseIntArray2;
                        lyVar.Q = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, lyVar.Q);
                        break;
                    case 13:
                        sparseIntArray = sparseIntArray2;
                        lyVar.M = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, lyVar.M);
                        break;
                    case 14:
                        sparseIntArray = sparseIntArray2;
                        lyVar.O = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, lyVar.O);
                        break;
                    case 15:
                        sparseIntArray = sparseIntArray2;
                        lyVar.R = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, lyVar.R);
                        break;
                    case 16:
                        sparseIntArray = sparseIntArray2;
                        lyVar.N = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, lyVar.N);
                        break;
                    case 17:
                        sparseIntArray = sparseIntArray2;
                        lyVar.d = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index2, lyVar.d);
                        break;
                    case 18:
                        sparseIntArray = sparseIntArray2;
                        lyVar.e = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index2, lyVar.e);
                        break;
                    case 19:
                        sparseIntArray = sparseIntArray2;
                        lyVar.f = typedArrayObtainStyledAttributes.getFloat(index2, lyVar.f);
                        break;
                    case 20:
                        sparseIntArray = sparseIntArray2;
                        lyVar.w = typedArrayObtainStyledAttributes.getFloat(index2, lyVar.w);
                        break;
                    case 21:
                        sparseIntArray = sparseIntArray2;
                        lyVar.c = typedArrayObtainStyledAttributes.getLayoutDimension(index2, lyVar.c);
                        break;
                    case 22:
                        sparseIntArray = sparseIntArray2;
                        nyVar.a = iArr[typedArrayObtainStyledAttributes.getInt(index2, nyVar.a)];
                        break;
                    case 23:
                        sparseIntArray = sparseIntArray2;
                        lyVar.b = typedArrayObtainStyledAttributes.getLayoutDimension(index2, lyVar.b);
                        break;
                    case 24:
                        sparseIntArray = sparseIntArray2;
                        lyVar.F = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, lyVar.F);
                        break;
                    case 25:
                        sparseIntArray = sparseIntArray2;
                        lyVar.h = f(typedArrayObtainStyledAttributes, index2, lyVar.h);
                        break;
                    case 26:
                        sparseIntArray = sparseIntArray2;
                        lyVar.i = f(typedArrayObtainStyledAttributes, index2, lyVar.i);
                        break;
                    case 27:
                        sparseIntArray = sparseIntArray2;
                        lyVar.E = typedArrayObtainStyledAttributes.getInt(index2, lyVar.E);
                        break;
                    case 28:
                        sparseIntArray = sparseIntArray2;
                        lyVar.G = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, lyVar.G);
                        break;
                    case 29:
                        sparseIntArray = sparseIntArray2;
                        lyVar.j = f(typedArrayObtainStyledAttributes, index2, lyVar.j);
                        break;
                    case 30:
                        sparseIntArray = sparseIntArray2;
                        lyVar.k = f(typedArrayObtainStyledAttributes, index2, lyVar.k);
                        break;
                    case 31:
                        sparseIntArray = sparseIntArray2;
                        lyVar.K = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, lyVar.K);
                        break;
                    case ConnectMessages$StateResponse.Device.VOLUME_STEPS_FIELD_NUMBER /* 32 */:
                        sparseIntArray = sparseIntArray2;
                        lyVar.s = f(typedArrayObtainStyledAttributes, index2, lyVar.s);
                        break;
                    case ConnectMessages$StateResponse.Device.CAPABILITIES_FIELD_NUMBER /* 33 */:
                        sparseIntArray = sparseIntArray2;
                        lyVar.t = f(typedArrayObtainStyledAttributes, index2, lyVar.t);
                        break;
                    case ConnectMessages$StateResponse.Device.INCARNATIONS_FIELD_NUMBER /* 34 */:
                        sparseIntArray = sparseIntArray2;
                        lyVar.H = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, lyVar.H);
                        break;
                    case ConnectMessages$StateResponse.Device.ALIAS_ID_FIELD_NUMBER /* 35 */:
                        sparseIntArray = sparseIntArray2;
                        lyVar.m = f(typedArrayObtainStyledAttributes, index2, lyVar.m);
                        break;
                    case ConnectMessages$StateResponse.Device.SUPPORTS_DJ_FIELD_NUMBER /* 36 */:
                        sparseIntArray = sparseIntArray2;
                        lyVar.l = f(typedArrayObtainStyledAttributes, index2, lyVar.l);
                        break;
                    case ConnectMessages$StateResponse.Device.LIBRARY_VERSION_FIELD_NUMBER /* 37 */:
                        sparseIntArray = sparseIntArray2;
                        lyVar.x = typedArrayObtainStyledAttributes.getFloat(index2, lyVar.x);
                        break;
                    case ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_NAME_FIELD_NUMBER /* 38 */:
                        sparseIntArray = sparseIntArray2;
                        kyVar.a = typedArrayObtainStyledAttributes.getResourceId(index2, kyVar.a);
                        break;
                    case ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_TYPE_FIELD_NUMBER /* 39 */:
                        sparseIntArray = sparseIntArray2;
                        lyVar.U = typedArrayObtainStyledAttributes.getFloat(index2, lyVar.U);
                        break;
                    case 40:
                        sparseIntArray = sparseIntArray2;
                        lyVar.T = typedArrayObtainStyledAttributes.getFloat(index2, lyVar.T);
                        break;
                    case 41:
                        sparseIntArray = sparseIntArray2;
                        lyVar.V = typedArrayObtainStyledAttributes.getInt(index2, lyVar.V);
                        break;
                    case 42:
                        sparseIntArray = sparseIntArray2;
                        lyVar.W = typedArrayObtainStyledAttributes.getInt(index2, lyVar.W);
                        break;
                    case 43:
                        sparseIntArray = sparseIntArray2;
                        nyVar.c = typedArrayObtainStyledAttributes.getFloat(index2, nyVar.c);
                        break;
                    case 44:
                        sparseIntArray = sparseIntArray2;
                        oyVar.l = true;
                        oyVar.m = typedArrayObtainStyledAttributes.getDimension(index2, oyVar.m);
                        break;
                    case 45:
                        sparseIntArray = sparseIntArray2;
                        oyVar.b = typedArrayObtainStyledAttributes.getFloat(index2, oyVar.b);
                        break;
                    case 46:
                        sparseIntArray = sparseIntArray2;
                        oyVar.c = typedArrayObtainStyledAttributes.getFloat(index2, oyVar.c);
                        break;
                    case 47:
                        sparseIntArray = sparseIntArray2;
                        oyVar.d = typedArrayObtainStyledAttributes.getFloat(index2, oyVar.d);
                        break;
                    case 48:
                        sparseIntArray = sparseIntArray2;
                        oyVar.e = typedArrayObtainStyledAttributes.getFloat(index2, oyVar.e);
                        break;
                    case 49:
                        sparseIntArray = sparseIntArray2;
                        oyVar.f = typedArrayObtainStyledAttributes.getDimension(index2, oyVar.f);
                        break;
                    case 50:
                        sparseIntArray = sparseIntArray2;
                        oyVar.g = typedArrayObtainStyledAttributes.getDimension(index2, oyVar.g);
                        break;
                    case 51:
                        sparseIntArray = sparseIntArray2;
                        oyVar.i = typedArrayObtainStyledAttributes.getDimension(index2, oyVar.i);
                        break;
                    case 52:
                        sparseIntArray = sparseIntArray2;
                        oyVar.j = typedArrayObtainStyledAttributes.getDimension(index2, oyVar.j);
                        break;
                    case 53:
                        sparseIntArray = sparseIntArray2;
                        oyVar.k = typedArrayObtainStyledAttributes.getDimension(index2, oyVar.k);
                        break;
                    case 54:
                        sparseIntArray = sparseIntArray2;
                        lyVar.X = typedArrayObtainStyledAttributes.getInt(index2, lyVar.X);
                        break;
                    case 55:
                        sparseIntArray = sparseIntArray2;
                        lyVar.Y = typedArrayObtainStyledAttributes.getInt(index2, lyVar.Y);
                        break;
                    case 56:
                        sparseIntArray = sparseIntArray2;
                        lyVar.Z = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, lyVar.Z);
                        break;
                    case 57:
                        sparseIntArray = sparseIntArray2;
                        lyVar.a0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, lyVar.a0);
                        break;
                    case 58:
                        sparseIntArray = sparseIntArray2;
                        lyVar.b0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, lyVar.b0);
                        break;
                    case 59:
                        sparseIntArray = sparseIntArray2;
                        lyVar.c0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, lyVar.c0);
                        break;
                    case 60:
                        sparseIntArray = sparseIntArray2;
                        oyVar.a = typedArrayObtainStyledAttributes.getFloat(index2, oyVar.a);
                        break;
                    case 61:
                        sparseIntArray = sparseIntArray2;
                        lyVar.z = f(typedArrayObtainStyledAttributes, index2, lyVar.z);
                        break;
                    case 62:
                        sparseIntArray = sparseIntArray2;
                        lyVar.A = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, lyVar.A);
                        break;
                    case 63:
                        sparseIntArray = sparseIntArray2;
                        lyVar.B = typedArrayObtainStyledAttributes.getFloat(index2, lyVar.B);
                        break;
                    case 64:
                        sparseIntArray = sparseIntArray2;
                        myVar.a = f(typedArrayObtainStyledAttributes, index2, myVar.a);
                        break;
                    case 65:
                        sparseIntArray = sparseIntArray2;
                        if (typedArrayObtainStyledAttributes.peekValue(index2).type != 3) {
                            String str = strArr[typedArrayObtainStyledAttributes.getInteger(index2, 0)];
                            myVar.getClass();
                        } else {
                            typedArrayObtainStyledAttributes.getString(index2);
                            myVar.getClass();
                        }
                        break;
                    case 66:
                        sparseIntArray = sparseIntArray2;
                        typedArrayObtainStyledAttributes.getInt(index2, 0);
                        myVar.getClass();
                        break;
                    case 67:
                        sparseIntArray = sparseIntArray2;
                        myVar.e = typedArrayObtainStyledAttributes.getFloat(index2, myVar.e);
                        break;
                    case 68:
                        sparseIntArray = sparseIntArray2;
                        nyVar.d = typedArrayObtainStyledAttributes.getFloat(index2, nyVar.d);
                        break;
                    case 69:
                        sparseIntArray = sparseIntArray2;
                        lyVar.d0 = typedArrayObtainStyledAttributes.getFloat(index2, 1.0f);
                        break;
                    case 70:
                        sparseIntArray = sparseIntArray2;
                        lyVar.e0 = typedArrayObtainStyledAttributes.getFloat(index2, 1.0f);
                        break;
                    case 71:
                        sparseIntArray = sparseIntArray2;
                        Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                        break;
                    case 72:
                        sparseIntArray = sparseIntArray2;
                        lyVar.f0 = typedArrayObtainStyledAttributes.getInt(index2, lyVar.f0);
                        break;
                    case 73:
                        sparseIntArray = sparseIntArray2;
                        lyVar.g0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, lyVar.g0);
                        break;
                    case 74:
                        sparseIntArray = sparseIntArray2;
                        lyVar.j0 = typedArrayObtainStyledAttributes.getString(index2);
                        break;
                    case 75:
                        sparseIntArray = sparseIntArray2;
                        lyVar.n0 = typedArrayObtainStyledAttributes.getBoolean(index2, lyVar.n0);
                        break;
                    case 76:
                        sparseIntArray = sparseIntArray2;
                        myVar.c = typedArrayObtainStyledAttributes.getInt(index2, myVar.c);
                        break;
                    case 77:
                        sparseIntArray = sparseIntArray2;
                        lyVar.k0 = typedArrayObtainStyledAttributes.getString(index2);
                        break;
                    case 78:
                        sparseIntArray = sparseIntArray2;
                        nyVar.b = typedArrayObtainStyledAttributes.getInt(index2, nyVar.b);
                        break;
                    case 79:
                        sparseIntArray = sparseIntArray2;
                        myVar.d = typedArrayObtainStyledAttributes.getFloat(index2, myVar.d);
                        break;
                    case 80:
                        sparseIntArray = sparseIntArray2;
                        lyVar.l0 = typedArrayObtainStyledAttributes.getBoolean(index2, lyVar.l0);
                        break;
                    case 81:
                        sparseIntArray = sparseIntArray2;
                        lyVar.m0 = typedArrayObtainStyledAttributes.getBoolean(index2, lyVar.m0);
                        break;
                    case 82:
                        sparseIntArray = sparseIntArray2;
                        myVar.b = typedArrayObtainStyledAttributes.getInteger(index2, myVar.b);
                        break;
                    case 83:
                        sparseIntArray = sparseIntArray2;
                        oyVar.h = f(typedArrayObtainStyledAttributes, index2, oyVar.h);
                        break;
                    case 84:
                        sparseIntArray = sparseIntArray2;
                        myVar.g = typedArrayObtainStyledAttributes.getInteger(index2, myVar.g);
                        break;
                    case 85:
                        sparseIntArray = sparseIntArray2;
                        myVar.f = typedArrayObtainStyledAttributes.getFloat(index2, myVar.f);
                        break;
                    case 86:
                        int i7 = typedArrayObtainStyledAttributes.peekValue(index2).type;
                        sparseIntArray = sparseIntArray2;
                        if (i7 == 1) {
                            myVar.i = typedArrayObtainStyledAttributes.getResourceId(index2, -1);
                        } else if (i7 == 3) {
                            String string2 = typedArrayObtainStyledAttributes.getString(index2);
                            myVar.h = string2;
                            if (string2.indexOf("/") > 0) {
                                myVar.i = typedArrayObtainStyledAttributes.getResourceId(index2, -1);
                            }
                        } else {
                            typedArrayObtainStyledAttributes.getInteger(index2, myVar.i);
                        }
                        break;
                    case 87:
                        Integer.toHexString(index2);
                        sparseIntArray2.get(index2);
                        sparseIntArray = sparseIntArray2;
                        break;
                    case 88:
                    case 89:
                    case 90:
                    default:
                        Integer.toHexString(index2);
                        sparseIntArray2.get(index2);
                        sparseIntArray = sparseIntArray2;
                        break;
                    case 91:
                        lyVar.q = f(typedArrayObtainStyledAttributes, index2, lyVar.q);
                        sparseIntArray = sparseIntArray2;
                        break;
                    case 92:
                        lyVar.r = f(typedArrayObtainStyledAttributes, index2, lyVar.r);
                        sparseIntArray = sparseIntArray2;
                        break;
                    case 93:
                        lyVar.L = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, lyVar.L);
                        sparseIntArray = sparseIntArray2;
                        break;
                    case 94:
                        lyVar.S = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, lyVar.S);
                        sparseIntArray = sparseIntArray2;
                        break;
                    case 95:
                        g(lyVar, typedArrayObtainStyledAttributes, index2, 0);
                        sparseIntArray = sparseIntArray2;
                        break;
                    case 96:
                        g(lyVar, typedArrayObtainStyledAttributes, index2, 1);
                        sparseIntArray = sparseIntArray2;
                        break;
                    case 97:
                        lyVar.o0 = typedArrayObtainStyledAttributes.getInt(index2, lyVar.o0);
                        sparseIntArray = sparseIntArray2;
                        break;
                }
                i6++;
                sparseIntArray2 = sparseIntArray;
            }
            if (lyVar.j0 != null) {
                lyVar.i0 = null;
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        return kyVar;
    }

    public static int f(TypedArray typedArray, int i, int i2) {
        int resourceId = typedArray.getResourceId(i, i2);
        return resourceId == -1 ? typedArray.getInt(i, -1) : resourceId;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void g(java.lang.Object r7, android.content.res.TypedArray r8, int r9, int r10) {
        /*
            Method dump skipped, instruction units count: 376
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.py.g(java.lang.Object, android.content.res.TypedArray, int, int):void");
    }

    public static void h(dy dyVar, String str) {
        if (str != null) {
            int length = str.length();
            int iIndexOf = str.indexOf(44);
            byte b = -1;
            int i = 0;
            if (iIndexOf > 0 && iIndexOf < length - 1) {
                String strSubstring = str.substring(0, iIndexOf);
                if (strSubstring.equalsIgnoreCase("W")) {
                    b = 0;
                } else if (strSubstring.equalsIgnoreCase("H")) {
                    b = 1;
                }
                i = iIndexOf + 1;
            }
            int iIndexOf2 = str.indexOf(58);
            try {
                if (iIndexOf2 < 0 || iIndexOf2 >= length - 1) {
                    String strSubstring2 = str.substring(i);
                    if (strSubstring2.length() > 0) {
                        Float.parseFloat(strSubstring2);
                    }
                } else {
                    String strSubstring3 = str.substring(i, iIndexOf2);
                    String strSubstring4 = str.substring(iIndexOf2 + 1);
                    if (strSubstring3.length() > 0 && strSubstring4.length() > 0) {
                        float f2 = Float.parseFloat(strSubstring3);
                        float f3 = Float.parseFloat(strSubstring4);
                        if (f2 > 0.0f && f3 > 0.0f) {
                            if (b == 1) {
                                Math.abs(f3 / f2);
                            } else {
                                Math.abs(f2 / f3);
                            }
                        }
                    }
                }
            } catch (NumberFormatException unused) {
            }
        }
        dyVar.G = str;
    }

    public final void a(ConstraintLayout constraintLayout) {
        ViewGroup viewGroup;
        int i;
        int i2;
        py pyVar = this;
        ConstraintLayout constraintLayout2 = constraintLayout;
        int childCount = constraintLayout.getChildCount();
        HashMap map = pyVar.c;
        HashSet<Integer> hashSet = new HashSet(map.keySet());
        int i3 = 0;
        while (i3 < childCount) {
            View childAt = constraintLayout2.getChildAt(i3);
            int id = childAt.getId();
            if (!map.containsKey(Integer.valueOf(id))) {
                try {
                    childAt.getContext().getResources().getResourceEntryName(childAt.getId());
                } catch (Exception unused) {
                }
            } else {
                if (pyVar.b && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (id != -1 && map.containsKey(Integer.valueOf(id))) {
                    hashSet.remove(Integer.valueOf(id));
                    ky kyVar = (ky) map.get(Integer.valueOf(id));
                    if (kyVar != null) {
                        if (childAt instanceof Barrier) {
                            ly lyVar = kyVar.d;
                            lyVar.h0 = 1;
                            Barrier barrier = (Barrier) childAt;
                            barrier.setId(id);
                            barrier.setType(lyVar.f0);
                            barrier.setMargin(lyVar.g0);
                            barrier.setAllowsGoneWidget(lyVar.n0);
                            int[] iArr = lyVar.i0;
                            if (iArr != null) {
                                barrier.setReferencedIds(iArr);
                            } else {
                                String str = lyVar.j0;
                                if (str != null) {
                                    int[] iArrC = c(barrier, str);
                                    lyVar.i0 = iArrC;
                                    barrier.setReferencedIds(iArrC);
                                }
                            }
                        }
                        dy dyVar = (dy) childAt.getLayoutParams();
                        dyVar.a();
                        kyVar.a(dyVar);
                        HashMap map2 = kyVar.f;
                        Class<?> cls = childAt.getClass();
                        for (String str2 : map2.keySet()) {
                            yx yxVar = (yx) map2.get(str2);
                            String strX = !yxVar.a ? u4.x("set", str2) : str2;
                            HashMap map3 = map2;
                            try {
                                switch (yxVar.b.ordinal()) {
                                    case 0:
                                        i2 = childCount;
                                        Class<?>[] clsArr = new Class[1];
                                        try {
                                            clsArr[0] = Integer.TYPE;
                                            cls.getMethod(strX, clsArr).invoke(childAt, Integer.valueOf(yxVar.c));
                                        } catch (IllegalAccessException e2) {
                                            e = e2;
                                            StringBuilder sbP = u4.p(" Custom Attribute \"", str2, "\" not found on ");
                                            sbP.append(cls.getName());
                                            Log.e("TransitionLayout", sbP.toString(), e);
                                        } catch (NoSuchMethodException e3) {
                                            e = e3;
                                            Log.e("TransitionLayout", cls.getName() + " must have a method " + strX, e);
                                        } catch (InvocationTargetException e4) {
                                            e = e4;
                                            StringBuilder sbP2 = u4.p(" Custom Attribute \"", str2, "\" not found on ");
                                            sbP2.append(cls.getName());
                                            Log.e("TransitionLayout", sbP2.toString(), e);
                                        }
                                        break;
                                    case 1:
                                        i2 = childCount;
                                        cls.getMethod(strX, Float.TYPE).invoke(childAt, Float.valueOf(yxVar.d));
                                        break;
                                    case 2:
                                        i2 = childCount;
                                        cls.getMethod(strX, Integer.TYPE).invoke(childAt, Integer.valueOf(yxVar.g));
                                        break;
                                    case 3:
                                        i2 = childCount;
                                        Method method = cls.getMethod(strX, Drawable.class);
                                        ColorDrawable colorDrawable = new ColorDrawable();
                                        colorDrawable.setColor(yxVar.g);
                                        method.invoke(childAt, colorDrawable);
                                        break;
                                    case 4:
                                        i2 = childCount;
                                        cls.getMethod(strX, CharSequence.class).invoke(childAt, yxVar.e);
                                        break;
                                    case 5:
                                        i2 = childCount;
                                        cls.getMethod(strX, Boolean.TYPE).invoke(childAt, Boolean.valueOf(yxVar.f));
                                        break;
                                    case 6:
                                        i2 = childCount;
                                        cls.getMethod(strX, Float.TYPE).invoke(childAt, Float.valueOf(yxVar.d));
                                        break;
                                    case 7:
                                        i2 = childCount;
                                        try {
                                            cls.getMethod(strX, Integer.TYPE).invoke(childAt, Integer.valueOf(yxVar.c));
                                        } catch (IllegalAccessException e5) {
                                            e = e5;
                                            StringBuilder sbP3 = u4.p(" Custom Attribute \"", str2, "\" not found on ");
                                            sbP3.append(cls.getName());
                                            Log.e("TransitionLayout", sbP3.toString(), e);
                                        } catch (NoSuchMethodException e6) {
                                            e = e6;
                                            Log.e("TransitionLayout", cls.getName() + " must have a method " + strX, e);
                                        } catch (InvocationTargetException e7) {
                                            e = e7;
                                            StringBuilder sbP22 = u4.p(" Custom Attribute \"", str2, "\" not found on ");
                                            sbP22.append(cls.getName());
                                            Log.e("TransitionLayout", sbP22.toString(), e);
                                        }
                                        break;
                                    default:
                                        i2 = childCount;
                                        break;
                                }
                            } catch (IllegalAccessException e8) {
                                e = e8;
                                i2 = childCount;
                            } catch (NoSuchMethodException e9) {
                                e = e9;
                                i2 = childCount;
                            } catch (InvocationTargetException e10) {
                                e = e10;
                                i2 = childCount;
                            }
                            map2 = map3;
                            childCount = i2;
                        }
                        i = childCount;
                        childAt.setLayoutParams(dyVar);
                        ny nyVar = kyVar.b;
                        if (nyVar.b == 0) {
                            childAt.setVisibility(nyVar.a);
                        }
                        childAt.setAlpha(nyVar.c);
                        oy oyVar = kyVar.e;
                        childAt.setRotation(oyVar.a);
                        childAt.setRotationX(oyVar.b);
                        childAt.setRotationY(oyVar.c);
                        childAt.setScaleX(oyVar.d);
                        childAt.setScaleY(oyVar.e);
                        if (oyVar.h != -1) {
                            if (((View) childAt.getParent()).findViewById(oyVar.h) != null) {
                                float bottom = (r1.getBottom() + r1.getTop()) / 2.0f;
                                float right = (r1.getRight() + r1.getLeft()) / 2.0f;
                                if (childAt.getRight() - childAt.getLeft() > 0 && childAt.getBottom() - childAt.getTop() > 0) {
                                    childAt.setPivotX(right - childAt.getLeft());
                                    childAt.setPivotY(bottom - childAt.getTop());
                                }
                            }
                        } else {
                            if (!Float.isNaN(oyVar.f)) {
                                childAt.setPivotX(oyVar.f);
                            }
                            if (!Float.isNaN(oyVar.g)) {
                                childAt.setPivotY(oyVar.g);
                            }
                        }
                        childAt.setTranslationX(oyVar.i);
                        childAt.setTranslationY(oyVar.j);
                        childAt.setTranslationZ(oyVar.k);
                        if (oyVar.l) {
                            childAt.setElevation(oyVar.m);
                        }
                    }
                }
                i3++;
                pyVar = this;
                constraintLayout2 = constraintLayout;
                childCount = i;
            }
            i = childCount;
            i3++;
            pyVar = this;
            constraintLayout2 = constraintLayout;
            childCount = i;
        }
        int i4 = childCount;
        for (Integer num : hashSet) {
            ky kyVar2 = (ky) map.get(num);
            if (kyVar2 != null) {
                ly lyVar2 = kyVar2.d;
                if (lyVar2.h0 == 1) {
                    Barrier barrier2 = new Barrier(constraintLayout.getContext());
                    barrier2.setId(num.intValue());
                    int[] iArr2 = lyVar2.i0;
                    if (iArr2 != null) {
                        barrier2.setReferencedIds(iArr2);
                    } else {
                        String str3 = lyVar2.j0;
                        if (str3 != null) {
                            int[] iArrC2 = c(barrier2, str3);
                            lyVar2.i0 = iArrC2;
                            barrier2.setReferencedIds(iArrC2);
                        }
                    }
                    barrier2.setType(lyVar2.f0);
                    barrier2.setMargin(lyVar2.g0);
                    dy dyVarG = ConstraintLayout.g();
                    barrier2.e();
                    kyVar2.a(dyVarG);
                    viewGroup = constraintLayout;
                    viewGroup.addView(barrier2, dyVarG);
                } else {
                    viewGroup = constraintLayout;
                }
                if (lyVar2.a) {
                    Guideline guideline = new Guideline(constraintLayout.getContext());
                    guideline.setId(num.intValue());
                    dy dyVarG2 = ConstraintLayout.g();
                    kyVar2.a(dyVarG2);
                    viewGroup.addView(guideline, dyVarG2);
                }
            }
        }
        for (int i5 = 0; i5 < i4; i5++) {
            View childAt2 = constraintLayout.getChildAt(i5);
            if (childAt2 instanceof ay) {
                ((ay) childAt2).getClass();
            }
        }
    }

    public final void b(ConstraintLayout constraintLayout) {
        int i;
        HashMap map;
        HashMap map2;
        py pyVar = this;
        int childCount = constraintLayout.getChildCount();
        HashMap map3 = pyVar.c;
        map3.clear();
        int i2 = 0;
        while (i2 < childCount) {
            View childAt = constraintLayout.getChildAt(i2);
            dy dyVar = (dy) childAt.getLayoutParams();
            int id = childAt.getId();
            if (pyVar.b && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!map3.containsKey(Integer.valueOf(id))) {
                map3.put(Integer.valueOf(id), new ky());
            }
            ky kyVar = (ky) map3.get(Integer.valueOf(id));
            if (kyVar == null) {
                i = childCount;
                map = map3;
            } else {
                HashMap map4 = pyVar.a;
                HashMap map5 = new HashMap();
                Class<?> cls = childAt.getClass();
                for (String str : map4.keySet()) {
                    yx yxVar = (yx) map4.get(str);
                    int i3 = childCount;
                    try {
                        if (str.equals("BackgroundColor")) {
                            map2 = map3;
                            try {
                                map5.put(str, new yx(yxVar, Integer.valueOf(((ColorDrawable) childAt.getBackground()).getColor())));
                            } catch (IllegalAccessException e2) {
                                e = e2;
                                StringBuilder sbP = u4.p(" Custom Attribute \"", str, "\" not found on ");
                                sbP.append(cls.getName());
                                Log.e("TransitionLayout", sbP.toString(), e);
                            } catch (NoSuchMethodException e3) {
                                e = e3;
                                Log.e("TransitionLayout", cls.getName() + " must have a method " + str, e);
                            } catch (InvocationTargetException e4) {
                                e = e4;
                                StringBuilder sbP2 = u4.p(" Custom Attribute \"", str, "\" not found on ");
                                sbP2.append(cls.getName());
                                Log.e("TransitionLayout", sbP2.toString(), e);
                            }
                        } else {
                            map2 = map3;
                            try {
                                map5.put(str, new yx(yxVar, cls.getMethod("getMap" + str, new Class[0]).invoke(childAt, new Object[0])));
                            } catch (IllegalAccessException e5) {
                                e = e5;
                                StringBuilder sbP3 = u4.p(" Custom Attribute \"", str, "\" not found on ");
                                sbP3.append(cls.getName());
                                Log.e("TransitionLayout", sbP3.toString(), e);
                            } catch (NoSuchMethodException e6) {
                                e = e6;
                                Log.e("TransitionLayout", cls.getName() + " must have a method " + str, e);
                            } catch (InvocationTargetException e7) {
                                e = e7;
                                StringBuilder sbP22 = u4.p(" Custom Attribute \"", str, "\" not found on ");
                                sbP22.append(cls.getName());
                                Log.e("TransitionLayout", sbP22.toString(), e);
                            }
                        }
                    } catch (IllegalAccessException e8) {
                        e = e8;
                        map2 = map3;
                    } catch (NoSuchMethodException e9) {
                        e = e9;
                        map2 = map3;
                    } catch (InvocationTargetException e10) {
                        e = e10;
                        map2 = map3;
                    }
                    childCount = i3;
                    map3 = map2;
                }
                i = childCount;
                map = map3;
                kyVar.f = map5;
                kyVar.a = id;
                int i4 = dyVar.e;
                ly lyVar = kyVar.d;
                lyVar.h = i4;
                lyVar.i = dyVar.f;
                lyVar.j = dyVar.g;
                lyVar.k = dyVar.h;
                lyVar.l = dyVar.i;
                lyVar.m = dyVar.j;
                lyVar.n = dyVar.k;
                lyVar.o = dyVar.l;
                lyVar.f152p = dyVar.m;
                lyVar.q = dyVar.n;
                lyVar.r = dyVar.o;
                lyVar.s = dyVar.s;
                lyVar.t = dyVar.t;
                lyVar.u = dyVar.u;
                lyVar.v = dyVar.v;
                lyVar.w = dyVar.E;
                lyVar.x = dyVar.F;
                lyVar.y = dyVar.G;
                lyVar.z = dyVar.f117p;
                lyVar.A = dyVar.q;
                lyVar.B = dyVar.r;
                lyVar.C = dyVar.T;
                lyVar.D = dyVar.U;
                lyVar.E = dyVar.V;
                lyVar.f = dyVar.c;
                lyVar.d = dyVar.a;
                lyVar.e = dyVar.b;
                lyVar.b = ((ViewGroup.MarginLayoutParams) dyVar).width;
                lyVar.c = ((ViewGroup.MarginLayoutParams) dyVar).height;
                lyVar.F = ((ViewGroup.MarginLayoutParams) dyVar).leftMargin;
                lyVar.G = ((ViewGroup.MarginLayoutParams) dyVar).rightMargin;
                lyVar.H = ((ViewGroup.MarginLayoutParams) dyVar).topMargin;
                lyVar.I = ((ViewGroup.MarginLayoutParams) dyVar).bottomMargin;
                lyVar.L = dyVar.D;
                lyVar.T = dyVar.I;
                lyVar.U = dyVar.H;
                lyVar.W = dyVar.K;
                lyVar.V = dyVar.J;
                lyVar.l0 = dyVar.W;
                lyVar.m0 = dyVar.X;
                lyVar.X = dyVar.L;
                lyVar.Y = dyVar.M;
                lyVar.Z = dyVar.P;
                lyVar.a0 = dyVar.Q;
                lyVar.b0 = dyVar.N;
                lyVar.c0 = dyVar.O;
                lyVar.d0 = dyVar.R;
                lyVar.e0 = dyVar.S;
                lyVar.k0 = dyVar.Y;
                lyVar.N = dyVar.x;
                lyVar.P = dyVar.z;
                lyVar.M = dyVar.w;
                lyVar.O = dyVar.y;
                lyVar.R = dyVar.A;
                lyVar.Q = dyVar.B;
                lyVar.S = dyVar.C;
                lyVar.o0 = dyVar.Z;
                lyVar.J = dyVar.getMarginEnd();
                lyVar.K = dyVar.getMarginStart();
                int visibility = childAt.getVisibility();
                ny nyVar = kyVar.b;
                nyVar.a = visibility;
                nyVar.c = childAt.getAlpha();
                float rotation = childAt.getRotation();
                oy oyVar = kyVar.e;
                oyVar.a = rotation;
                oyVar.b = childAt.getRotationX();
                oyVar.c = childAt.getRotationY();
                oyVar.d = childAt.getScaleX();
                oyVar.e = childAt.getScaleY();
                float pivotX = childAt.getPivotX();
                float pivotY = childAt.getPivotY();
                if (pivotX != 0.0d || pivotY != 0.0d) {
                    oyVar.f = pivotX;
                    oyVar.g = pivotY;
                }
                oyVar.i = childAt.getTranslationX();
                oyVar.j = childAt.getTranslationY();
                oyVar.k = childAt.getTranslationZ();
                if (oyVar.l) {
                    oyVar.m = childAt.getElevation();
                }
                if (childAt instanceof Barrier) {
                    Barrier barrier = (Barrier) childAt;
                    lyVar.n0 = barrier.getAllowsGoneWidget();
                    lyVar.i0 = barrier.getReferencedIds();
                    lyVar.f0 = barrier.getType();
                    lyVar.g0 = barrier.getMargin();
                }
            }
            i2++;
            pyVar = this;
            childCount = i;
            map3 = map;
        }
    }

    public final void e(Context context, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 2) {
                    String name = xml.getName();
                    ky kyVarD = d(context, Xml.asAttributeSet(xml), false);
                    if (name.equalsIgnoreCase("Guideline")) {
                        kyVarD.d.a = true;
                    }
                    this.c.put(Integer.valueOf(kyVarD.a), kyVarD);
                }
            }
        } catch (IOException e2) {
            Log.e("ConstraintSet", "Error parsing resource: " + i, e2);
        } catch (XmlPullParserException e3) {
            Log.e("ConstraintSet", "Error parsing resource: " + i, e3);
        }
    }
}
