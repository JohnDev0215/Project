package r1;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import h2.AbstractC1347d;
import j1.AbstractC1537a;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import org.xmlpull.v1.XmlPullParserException;
import q1.AbstractC2169a;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class m {
    public static final int[] d = {0, 4, 8};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final SparseIntArray f21759e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final SparseIntArray f21760f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashMap f21761a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f21762b = true;
    public final HashMap c = new HashMap();

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        f21759e = sparseIntArray;
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        f21760f = sparseIntArray2;
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
        String[] strArrSplit = str.split(",");
        Context context = barrier.getContext();
        int[] iArr = new int[strArrSplit.length];
        int i7 = 0;
        int i10 = 0;
        while (i7 < strArrSplit.length) {
            String strTrim = strArrSplit[i7].trim();
            Object obj = null;
            try {
                iIntValue = o.class.getField(strTrim).getInt(null);
            } catch (Exception unused) {
                iIntValue = 0;
            }
            if (iIntValue == 0) {
                iIntValue = context.getResources().getIdentifier(strTrim, "id", context.getPackageName());
            }
            if (iIntValue == 0 && barrier.isInEditMode() && (barrier.getParent() instanceof ConstraintLayout)) {
                ConstraintLayout constraintLayout = (ConstraintLayout) barrier.getParent();
                if (strTrim != null) {
                    HashMap map = constraintLayout.f11712x;
                    if (map != null && map.containsKey(strTrim)) {
                        obj = constraintLayout.f11712x.get(strTrim);
                    }
                } else {
                    constraintLayout.getClass();
                }
                if (obj != null && (obj instanceof Integer)) {
                    iIntValue = ((Integer) obj).intValue();
                }
            }
            iArr[i10] = iIntValue;
            i7++;
            i10++;
        }
        return i10 != strArrSplit.length ? Arrays.copyOf(iArr, i10) : iArr;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:125:0x0600. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:12:0x0088. Please report as an issue. */
    public static C2276h d(Context context, AttributeSet attributeSet, boolean z6) {
        int i7;
        String str;
        String str2;
        String str3;
        int i10;
        String str4;
        int i11;
        C2276h c2276h = new C2276h();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, z6 ? p.c : p.f21763a);
        int[] iArr = d;
        SparseIntArray sparseIntArray = f21759e;
        k kVar = c2276h.f21672b;
        String[] strArr = AbstractC1537a.f16999a;
        l lVar = c2276h.f21673e;
        C2278j c2278j = c2276h.c;
        C2277i c2277i = c2276h.d;
        String str5 = "Unknown attribute 0x";
        String str6 = "ConstraintSet";
        if (z6) {
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            C2275g c2275g = new C2275g();
            c2275g.f21662a = new int[10];
            c2275g.f21663b = new int[10];
            c2275g.c = 0;
            c2275g.d = new int[10];
            c2275g.f21664e = new float[10];
            c2275g.f21665f = 0;
            c2275g.f21666g = new int[5];
            c2275g.f21667h = new String[5];
            c2275g.f21668i = 0;
            c2275g.f21669j = new int[4];
            c2275g.f21670k = new boolean[4];
            c2275g.l = 0;
            c2278j.getClass();
            c2277i.getClass();
            lVar.getClass();
            int i12 = 0;
            while (i12 < indexCount) {
                int index = typedArrayObtainStyledAttributes.getIndex(i12);
                int i13 = indexCount;
                switch (f21760f.get(index)) {
                    case 2:
                        str4 = str5;
                        c2275g.b(2, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2277i.f21683I));
                        i11 = 1;
                        break;
                    case 3:
                    case 4:
                    case 9:
                    case 10:
                    case 25:
                    case 26:
                    case 29:
                    case 30:
                    case 32:
                    case 33:
                    case 35:
                    case 36:
                    case 61:
                    case 88:
                    case 89:
                    case 90:
                    case 91:
                    case 92:
                    default:
                        StringBuilder sb = new StringBuilder(str5);
                        str4 = str5;
                        sb.append(Integer.toHexString(index));
                        sb.append("   ");
                        sb.append(sparseIntArray.get(index));
                        h6.n.t("ConstraintSet", sb.toString());
                        i11 = 1;
                        break;
                    case 5:
                        str4 = str5;
                        c2275g.c(5, typedArrayObtainStyledAttributes.getString(index));
                        i11 = 1;
                        break;
                    case 6:
                        str4 = str5;
                        c2275g.b(6, typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, c2277i.f21677C));
                        i11 = 1;
                        break;
                    case 7:
                        str4 = str5;
                        c2275g.b(7, typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, c2277i.f21678D));
                        i11 = 1;
                        break;
                    case 8:
                        str4 = str5;
                        c2275g.b(8, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2277i.f21684J));
                        i11 = 1;
                        break;
                    case 11:
                        str4 = str5;
                        c2275g.b(11, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2277i.f21690P));
                        i11 = 1;
                        break;
                    case 12:
                        str4 = str5;
                        c2275g.b(12, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2277i.f21691Q));
                        i11 = 1;
                        break;
                    case 13:
                        str4 = str5;
                        c2275g.b(13, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2277i.f21687M));
                        i11 = 1;
                        break;
                    case 14:
                        str4 = str5;
                        c2275g.b(14, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2277i.f21689O));
                        i11 = 1;
                        break;
                    case 15:
                        str4 = str5;
                        c2275g.b(15, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2277i.f21692R));
                        i11 = 1;
                        break;
                    case 16:
                        str4 = str5;
                        c2275g.b(16, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2277i.f21688N));
                        i11 = 1;
                        break;
                    case 17:
                        str4 = str5;
                        c2275g.b(17, typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, c2277i.d));
                        i11 = 1;
                        break;
                    case 18:
                        str4 = str5;
                        c2275g.b(18, typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, c2277i.f21707e));
                        i11 = 1;
                        break;
                    case 19:
                        str4 = str5;
                        c2275g.a(typedArrayObtainStyledAttributes.getFloat(index, c2277i.f21709f), 19);
                        i11 = 1;
                        break;
                    case 20:
                        str4 = str5;
                        c2275g.a(typedArrayObtainStyledAttributes.getFloat(index, c2277i.f21734w), 20);
                        i11 = 1;
                        break;
                    case 21:
                        str4 = str5;
                        c2275g.b(21, typedArrayObtainStyledAttributes.getLayoutDimension(index, c2277i.c));
                        i11 = 1;
                        break;
                    case 22:
                        str4 = str5;
                        c2275g.b(22, iArr[typedArrayObtainStyledAttributes.getInt(index, kVar.f21746a)]);
                        i11 = 1;
                        break;
                    case 23:
                        str4 = str5;
                        c2275g.b(23, typedArrayObtainStyledAttributes.getLayoutDimension(index, c2277i.f21703b));
                        i11 = 1;
                        break;
                    case 24:
                        str4 = str5;
                        c2275g.b(24, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2277i.f21680F));
                        i11 = 1;
                        break;
                    case 27:
                        str4 = str5;
                        c2275g.b(27, typedArrayObtainStyledAttributes.getInt(index, c2277i.f21679E));
                        i11 = 1;
                        break;
                    case 28:
                        str4 = str5;
                        c2275g.b(28, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2277i.f21681G));
                        i11 = 1;
                        break;
                    case 31:
                        str4 = str5;
                        c2275g.b(31, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2277i.f21685K));
                        i11 = 1;
                        break;
                    case 34:
                        str4 = str5;
                        c2275g.b(34, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2277i.f21682H));
                        i11 = 1;
                        break;
                    case 37:
                        str4 = str5;
                        c2275g.a(typedArrayObtainStyledAttributes.getFloat(index, c2277i.f21735x), 37);
                        i11 = 1;
                        break;
                    case 38:
                        str4 = str5;
                        int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, c2276h.f21671a);
                        c2276h.f21671a = resourceId;
                        c2275g.b(38, resourceId);
                        i11 = 1;
                        break;
                    case 39:
                        str4 = str5;
                        c2275g.a(typedArrayObtainStyledAttributes.getFloat(index, c2277i.f21695U), 39);
                        i11 = 1;
                        break;
                    case 40:
                        str4 = str5;
                        c2275g.a(typedArrayObtainStyledAttributes.getFloat(index, c2277i.f21694T), 40);
                        i11 = 1;
                        break;
                    case 41:
                        str4 = str5;
                        c2275g.b(41, typedArrayObtainStyledAttributes.getInt(index, c2277i.f21696V));
                        i11 = 1;
                        break;
                    case 42:
                        str4 = str5;
                        c2275g.b(42, typedArrayObtainStyledAttributes.getInt(index, c2277i.f21697W));
                        i11 = 1;
                        break;
                    case 43:
                        str4 = str5;
                        c2275g.a(typedArrayObtainStyledAttributes.getFloat(index, kVar.c), 43);
                        i11 = 1;
                        break;
                    case 44:
                        str4 = str5;
                        c2275g.d(44, true);
                        c2275g.a(typedArrayObtainStyledAttributes.getDimension(index, lVar.f21758m), 44);
                        i11 = 1;
                        break;
                    case 45:
                        str4 = str5;
                        c2275g.a(typedArrayObtainStyledAttributes.getFloat(index, lVar.f21750b), 45);
                        i11 = 1;
                        break;
                    case 46:
                        str4 = str5;
                        c2275g.a(typedArrayObtainStyledAttributes.getFloat(index, lVar.c), 46);
                        i11 = 1;
                        break;
                    case 47:
                        str4 = str5;
                        c2275g.a(typedArrayObtainStyledAttributes.getFloat(index, lVar.d), 47);
                        i11 = 1;
                        break;
                    case 48:
                        str4 = str5;
                        c2275g.a(typedArrayObtainStyledAttributes.getFloat(index, lVar.f21751e), 48);
                        i11 = 1;
                        break;
                    case 49:
                        str4 = str5;
                        c2275g.a(typedArrayObtainStyledAttributes.getDimension(index, lVar.f21752f), 49);
                        i11 = 1;
                        break;
                    case 50:
                        str4 = str5;
                        c2275g.a(typedArrayObtainStyledAttributes.getDimension(index, lVar.f21753g), 50);
                        i11 = 1;
                        break;
                    case 51:
                        str4 = str5;
                        c2275g.a(typedArrayObtainStyledAttributes.getDimension(index, lVar.f21755i), 51);
                        i11 = 1;
                        break;
                    case 52:
                        str4 = str5;
                        c2275g.a(typedArrayObtainStyledAttributes.getDimension(index, lVar.f21756j), 52);
                        i11 = 1;
                        break;
                    case 53:
                        str4 = str5;
                        c2275g.a(typedArrayObtainStyledAttributes.getDimension(index, lVar.f21757k), 53);
                        i11 = 1;
                        break;
                    case 54:
                        str4 = str5;
                        c2275g.b(54, typedArrayObtainStyledAttributes.getInt(index, c2277i.f21698X));
                        i11 = 1;
                        break;
                    case 55:
                        str4 = str5;
                        c2275g.b(55, typedArrayObtainStyledAttributes.getInt(index, c2277i.f21699Y));
                        i11 = 1;
                        break;
                    case 56:
                        str4 = str5;
                        c2275g.b(56, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2277i.f21700Z));
                        i11 = 1;
                        break;
                    case 57:
                        str4 = str5;
                        c2275g.b(57, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2277i.f21702a0));
                        i11 = 1;
                        break;
                    case 58:
                        str4 = str5;
                        c2275g.b(58, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2277i.f21704b0));
                        i11 = 1;
                        break;
                    case 59:
                        str4 = str5;
                        c2275g.b(59, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2277i.f21705c0));
                        i11 = 1;
                        break;
                    case 60:
                        str4 = str5;
                        c2275g.a(typedArrayObtainStyledAttributes.getFloat(index, lVar.f21749a), 60);
                        i11 = 1;
                        break;
                    case 62:
                        str4 = str5;
                        c2275g.b(62, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2277i.f21676A));
                        i11 = 1;
                        break;
                    case 63:
                        str4 = str5;
                        c2275g.a(typedArrayObtainStyledAttributes.getFloat(index, c2277i.B), 63);
                        i11 = 1;
                        break;
                    case 64:
                        str4 = str5;
                        c2275g.b(64, f(typedArrayObtainStyledAttributes, index, c2278j.f21739a));
                        i11 = 1;
                        break;
                    case 65:
                        str4 = str5;
                        if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                            c2275g.c(65, typedArrayObtainStyledAttributes.getString(index));
                        } else {
                            c2275g.c(65, strArr[typedArrayObtainStyledAttributes.getInteger(index, 0)]);
                        }
                        i11 = 1;
                        break;
                    case 66:
                        str4 = str5;
                        c2275g.b(66, typedArrayObtainStyledAttributes.getInt(index, 0));
                        i11 = 1;
                        break;
                    case 67:
                        str4 = str5;
                        c2275g.a(typedArrayObtainStyledAttributes.getFloat(index, c2278j.f21741e), 67);
                        i11 = 1;
                        break;
                    case 68:
                        str4 = str5;
                        c2275g.a(typedArrayObtainStyledAttributes.getFloat(index, kVar.d), 68);
                        i11 = 1;
                        break;
                    case 69:
                        str4 = str5;
                        c2275g.a(typedArrayObtainStyledAttributes.getFloat(index, 1.0f), 69);
                        i11 = 1;
                        break;
                    case 70:
                        str4 = str5;
                        c2275g.a(typedArrayObtainStyledAttributes.getFloat(index, 1.0f), 70);
                        i11 = 1;
                        break;
                    case 71:
                        str4 = str5;
                        h6.n.j("ConstraintSet", "CURRENTLY UNSUPPORTED");
                        i11 = 1;
                        break;
                    case 72:
                        str4 = str5;
                        c2275g.b(72, typedArrayObtainStyledAttributes.getInt(index, c2277i.f21710f0));
                        i11 = 1;
                        break;
                    case 73:
                        str4 = str5;
                        c2275g.b(73, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2277i.f21712g0));
                        i11 = 1;
                        break;
                    case 74:
                        str4 = str5;
                        c2275g.c(74, typedArrayObtainStyledAttributes.getString(index));
                        i11 = 1;
                        break;
                    case 75:
                        str4 = str5;
                        c2275g.d(75, typedArrayObtainStyledAttributes.getBoolean(index, c2277i.f21725n0));
                        i11 = 1;
                        break;
                    case 76:
                        str4 = str5;
                        c2275g.b(76, typedArrayObtainStyledAttributes.getInt(index, c2278j.c));
                        i11 = 1;
                        break;
                    case 77:
                        str4 = str5;
                        c2275g.c(77, typedArrayObtainStyledAttributes.getString(index));
                        i11 = 1;
                        break;
                    case 78:
                        str4 = str5;
                        c2275g.b(78, typedArrayObtainStyledAttributes.getInt(index, kVar.f21747b));
                        i11 = 1;
                        break;
                    case 79:
                        str4 = str5;
                        c2275g.a(typedArrayObtainStyledAttributes.getFloat(index, c2278j.d), 79);
                        i11 = 1;
                        break;
                    case 80:
                        str4 = str5;
                        c2275g.d(80, typedArrayObtainStyledAttributes.getBoolean(index, c2277i.f21721l0));
                        i11 = 1;
                        break;
                    case 81:
                        str4 = str5;
                        c2275g.d(81, typedArrayObtainStyledAttributes.getBoolean(index, c2277i.f21723m0));
                        i11 = 1;
                        break;
                    case 82:
                        str4 = str5;
                        c2275g.b(82, typedArrayObtainStyledAttributes.getInteger(index, c2278j.f21740b));
                        i11 = 1;
                        break;
                    case 83:
                        str4 = str5;
                        c2275g.b(83, f(typedArrayObtainStyledAttributes, index, lVar.f21754h));
                        i11 = 1;
                        break;
                    case 84:
                        str4 = str5;
                        c2275g.b(84, typedArrayObtainStyledAttributes.getInteger(index, c2278j.f21743g));
                        i11 = 1;
                        break;
                    case 85:
                        str4 = str5;
                        c2275g.a(typedArrayObtainStyledAttributes.getFloat(index, c2278j.f21742f), 85);
                        i11 = 1;
                        break;
                    case 86:
                        str4 = str5;
                        int i14 = typedArrayObtainStyledAttributes.peekValue(index).type;
                        if (i14 == 1) {
                            int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                            c2278j.f21745i = resourceId2;
                            c2275g.b(89, resourceId2);
                            if (c2278j.f21745i != -1) {
                                c2275g.b(88, -2);
                            }
                        } else if (i14 == 3) {
                            String string = typedArrayObtainStyledAttributes.getString(index);
                            c2278j.f21744h = string;
                            c2275g.c(90, string);
                            if (c2278j.f21744h.indexOf("/") > 0) {
                                int resourceId3 = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                                c2278j.f21745i = resourceId3;
                                c2275g.b(89, resourceId3);
                                c2275g.b(88, -2);
                            } else {
                                c2275g.b(88, -1);
                            }
                        } else {
                            c2275g.b(88, typedArrayObtainStyledAttributes.getInteger(index, c2278j.f21745i));
                        }
                        i11 = 1;
                        break;
                    case 87:
                        str4 = str5;
                        h6.n.t("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + sparseIntArray.get(index));
                        i11 = 1;
                        break;
                    case 93:
                        str4 = str5;
                        c2275g.b(93, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2277i.f21686L));
                        i11 = 1;
                        break;
                    case 94:
                        str4 = str5;
                        c2275g.b(94, typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2277i.f21693S));
                        i11 = 1;
                        break;
                    case 95:
                        str4 = str5;
                        g(c2275g, typedArrayObtainStyledAttributes, index, 0);
                        i11 = 1;
                        break;
                    case 96:
                        str4 = str5;
                        i11 = 1;
                        g(c2275g, typedArrayObtainStyledAttributes, index, 1);
                        break;
                    case 97:
                        str4 = str5;
                        c2275g.b(97, typedArrayObtainStyledAttributes.getInt(index, c2277i.f21726o0));
                        i11 = 1;
                        break;
                    case 98:
                        str4 = str5;
                        int i15 = AbstractC2169a.B;
                        if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                            typedArrayObtainStyledAttributes.getString(index);
                        } else {
                            c2276h.f21671a = typedArrayObtainStyledAttributes.getResourceId(index, c2276h.f21671a);
                        }
                        i11 = 1;
                        break;
                    case 99:
                        str4 = str5;
                        c2275g.d(99, typedArrayObtainStyledAttributes.getBoolean(index, c2277i.f21711g));
                        i11 = 1;
                        break;
                }
                i12 += i11;
                indexCount = i13;
                str5 = str4;
            }
        } else {
            String str7 = "CURRENTLY UNSUPPORTED";
            String str8 = "Unknown attribute 0x";
            int i16 = 1;
            int indexCount2 = typedArrayObtainStyledAttributes.getIndexCount();
            int i17 = 0;
            while (i17 < indexCount2) {
                int index2 = typedArrayObtainStyledAttributes.getIndex(i17);
                if (index2 != i16 && 23 != index2 && 24 != index2) {
                    c2278j.getClass();
                    c2277i.getClass();
                    lVar.getClass();
                }
                switch (sparseIntArray.get(index2)) {
                    case 1:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21727p = f(typedArrayObtainStyledAttributes, index2, c2277i.f21727p);
                        i10 = 1;
                        break;
                    case 2:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21683I = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, c2277i.f21683I);
                        i10 = 1;
                        break;
                    case 3:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.o = f(typedArrayObtainStyledAttributes, index2, c2277i.o);
                        i10 = 1;
                        break;
                    case 4:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21724n = f(typedArrayObtainStyledAttributes, index2, c2277i.f21724n);
                        i10 = 1;
                        break;
                    case 5:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21736y = typedArrayObtainStyledAttributes.getString(index2);
                        i10 = 1;
                        break;
                    case 6:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21677C = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index2, c2277i.f21677C);
                        i10 = 1;
                        break;
                    case 7:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21678D = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index2, c2277i.f21678D);
                        i10 = 1;
                        break;
                    case 8:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21684J = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, c2277i.f21684J);
                        i10 = 1;
                        break;
                    case 9:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21733v = f(typedArrayObtainStyledAttributes, index2, c2277i.f21733v);
                        i10 = 1;
                        break;
                    case 10:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21732u = f(typedArrayObtainStyledAttributes, index2, c2277i.f21732u);
                        i10 = 1;
                        break;
                    case 11:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21690P = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, c2277i.f21690P);
                        i10 = 1;
                        break;
                    case 12:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21691Q = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, c2277i.f21691Q);
                        i10 = 1;
                        break;
                    case 13:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21687M = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, c2277i.f21687M);
                        i10 = 1;
                        break;
                    case 14:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21689O = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, c2277i.f21689O);
                        i10 = 1;
                        break;
                    case 15:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21692R = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, c2277i.f21692R);
                        i10 = 1;
                        break;
                    case 16:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21688N = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, c2277i.f21688N);
                        i10 = 1;
                        break;
                    case 17:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.d = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index2, c2277i.d);
                        i10 = 1;
                        break;
                    case 18:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21707e = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index2, c2277i.f21707e);
                        i10 = 1;
                        break;
                    case 19:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21709f = typedArrayObtainStyledAttributes.getFloat(index2, c2277i.f21709f);
                        i10 = 1;
                        break;
                    case 20:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21734w = typedArrayObtainStyledAttributes.getFloat(index2, c2277i.f21734w);
                        i10 = 1;
                        break;
                    case 21:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.c = typedArrayObtainStyledAttributes.getLayoutDimension(index2, c2277i.c);
                        i10 = 1;
                        break;
                    case 22:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        int i18 = typedArrayObtainStyledAttributes.getInt(index2, kVar.f21746a);
                        kVar.f21746a = i18;
                        kVar.f21746a = iArr[i18];
                        i10 = 1;
                        break;
                    case 23:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21703b = typedArrayObtainStyledAttributes.getLayoutDimension(index2, c2277i.f21703b);
                        i10 = 1;
                        break;
                    case 24:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21680F = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, c2277i.f21680F);
                        i10 = 1;
                        break;
                    case 25:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21713h = f(typedArrayObtainStyledAttributes, index2, c2277i.f21713h);
                        i10 = 1;
                        break;
                    case 26:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21715i = f(typedArrayObtainStyledAttributes, index2, c2277i.f21715i);
                        i10 = 1;
                        break;
                    case 27:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21679E = typedArrayObtainStyledAttributes.getInt(index2, c2277i.f21679E);
                        i10 = 1;
                        break;
                    case 28:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21681G = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, c2277i.f21681G);
                        i10 = 1;
                        break;
                    case 29:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21717j = f(typedArrayObtainStyledAttributes, index2, c2277i.f21717j);
                        i10 = 1;
                        break;
                    case 30:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21719k = f(typedArrayObtainStyledAttributes, index2, c2277i.f21719k);
                        i10 = 1;
                        break;
                    case 31:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21685K = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, c2277i.f21685K);
                        i10 = 1;
                        break;
                    case 32:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21730s = f(typedArrayObtainStyledAttributes, index2, c2277i.f21730s);
                        i10 = 1;
                        break;
                    case 33:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21731t = f(typedArrayObtainStyledAttributes, index2, c2277i.f21731t);
                        i10 = 1;
                        break;
                    case 34:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21682H = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, c2277i.f21682H);
                        i10 = 1;
                        break;
                    case 35:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21722m = f(typedArrayObtainStyledAttributes, index2, c2277i.f21722m);
                        i10 = 1;
                        break;
                    case 36:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.l = f(typedArrayObtainStyledAttributes, index2, c2277i.l);
                        i10 = 1;
                        break;
                    case 37:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21735x = typedArrayObtainStyledAttributes.getFloat(index2, c2277i.f21735x);
                        i10 = 1;
                        break;
                    case 38:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2276h.f21671a = typedArrayObtainStyledAttributes.getResourceId(index2, c2276h.f21671a);
                        i10 = 1;
                        break;
                    case 39:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21695U = typedArrayObtainStyledAttributes.getFloat(index2, c2277i.f21695U);
                        i10 = 1;
                        break;
                    case 40:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21694T = typedArrayObtainStyledAttributes.getFloat(index2, c2277i.f21694T);
                        i10 = 1;
                        break;
                    case 41:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21696V = typedArrayObtainStyledAttributes.getInt(index2, c2277i.f21696V);
                        i10 = 1;
                        break;
                    case 42:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21697W = typedArrayObtainStyledAttributes.getInt(index2, c2277i.f21697W);
                        i10 = 1;
                        break;
                    case 43:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        kVar.c = typedArrayObtainStyledAttributes.getFloat(index2, kVar.c);
                        i10 = 1;
                        break;
                    case 44:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        lVar.l = true;
                        lVar.f21758m = typedArrayObtainStyledAttributes.getDimension(index2, lVar.f21758m);
                        i10 = 1;
                        break;
                    case 45:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        lVar.f21750b = typedArrayObtainStyledAttributes.getFloat(index2, lVar.f21750b);
                        i10 = 1;
                        break;
                    case 46:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        lVar.c = typedArrayObtainStyledAttributes.getFloat(index2, lVar.c);
                        i10 = 1;
                        break;
                    case 47:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        lVar.d = typedArrayObtainStyledAttributes.getFloat(index2, lVar.d);
                        i10 = 1;
                        break;
                    case 48:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        lVar.f21751e = typedArrayObtainStyledAttributes.getFloat(index2, lVar.f21751e);
                        i10 = 1;
                        break;
                    case 49:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        lVar.f21752f = typedArrayObtainStyledAttributes.getDimension(index2, lVar.f21752f);
                        i10 = 1;
                        break;
                    case 50:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        lVar.f21753g = typedArrayObtainStyledAttributes.getDimension(index2, lVar.f21753g);
                        i10 = 1;
                        break;
                    case 51:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        lVar.f21755i = typedArrayObtainStyledAttributes.getDimension(index2, lVar.f21755i);
                        i10 = 1;
                        break;
                    case 52:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        lVar.f21756j = typedArrayObtainStyledAttributes.getDimension(index2, lVar.f21756j);
                        i10 = 1;
                        break;
                    case 53:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        lVar.f21757k = typedArrayObtainStyledAttributes.getDimension(index2, lVar.f21757k);
                        i10 = 1;
                        break;
                    case 54:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21698X = typedArrayObtainStyledAttributes.getInt(index2, c2277i.f21698X);
                        i10 = 1;
                        break;
                    case 55:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21699Y = typedArrayObtainStyledAttributes.getInt(index2, c2277i.f21699Y);
                        i10 = 1;
                        break;
                    case 56:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21700Z = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, c2277i.f21700Z);
                        i10 = 1;
                        break;
                    case 57:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21702a0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, c2277i.f21702a0);
                        i10 = 1;
                        break;
                    case 58:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21704b0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, c2277i.f21704b0);
                        i10 = 1;
                        break;
                    case 59:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21705c0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, c2277i.f21705c0);
                        i10 = 1;
                        break;
                    case 60:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        lVar.f21749a = typedArrayObtainStyledAttributes.getFloat(index2, lVar.f21749a);
                        i10 = 1;
                        break;
                    case 61:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21737z = f(typedArrayObtainStyledAttributes, index2, c2277i.f21737z);
                        i10 = 1;
                        break;
                    case 62:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.f21676A = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, c2277i.f21676A);
                        i10 = 1;
                        break;
                    case 63:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2277i.B = typedArrayObtainStyledAttributes.getFloat(index2, c2277i.B);
                        i10 = 1;
                        break;
                    case 64:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        c2278j.f21739a = f(typedArrayObtainStyledAttributes, index2, c2278j.f21739a);
                        i10 = 1;
                        break;
                    case 65:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        str3 = str6;
                        if (typedArrayObtainStyledAttributes.peekValue(index2).type != 3) {
                            String str9 = strArr[typedArrayObtainStyledAttributes.getInteger(index2, 0)];
                            c2278j.getClass();
                            i10 = 1;
                        } else {
                            typedArrayObtainStyledAttributes.getString(index2);
                            c2278j.getClass();
                            i10 = 1;
                        }
                        break;
                    case 66:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        typedArrayObtainStyledAttributes.getInt(index2, 0);
                        c2278j.getClass();
                        str3 = str6;
                        i10 = 1;
                        break;
                    case 67:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        c2278j.f21741e = typedArrayObtainStyledAttributes.getFloat(index2, c2278j.f21741e);
                        str3 = str6;
                        i10 = 1;
                        break;
                    case 68:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        kVar.d = typedArrayObtainStyledAttributes.getFloat(index2, kVar.d);
                        str3 = str6;
                        i10 = 1;
                        break;
                    case 69:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        c2277i.f21706d0 = typedArrayObtainStyledAttributes.getFloat(index2, 1.0f);
                        str3 = str6;
                        i10 = 1;
                        break;
                    case 70:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        c2277i.f21708e0 = typedArrayObtainStyledAttributes.getFloat(index2, 1.0f);
                        str3 = str6;
                        i10 = 1;
                        break;
                    case 71:
                        i7 = indexCount2;
                        str = str7;
                        str2 = str8;
                        h6.n.j(str6, str);
                        str3 = str6;
                        i10 = 1;
                        break;
                    case 72:
                        i7 = indexCount2;
                        str2 = str8;
                        c2277i.f21710f0 = typedArrayObtainStyledAttributes.getInt(index2, c2277i.f21710f0);
                        str = str7;
                        str3 = str6;
                        i10 = 1;
                        break;
                    case 73:
                        i7 = indexCount2;
                        str2 = str8;
                        c2277i.f21712g0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, c2277i.f21712g0);
                        str = str7;
                        str3 = str6;
                        i10 = 1;
                        break;
                    case 74:
                        i7 = indexCount2;
                        str2 = str8;
                        c2277i.f21718j0 = typedArrayObtainStyledAttributes.getString(index2);
                        str = str7;
                        str3 = str6;
                        i10 = 1;
                        break;
                    case 75:
                        i7 = indexCount2;
                        str2 = str8;
                        c2277i.f21725n0 = typedArrayObtainStyledAttributes.getBoolean(index2, c2277i.f21725n0);
                        str = str7;
                        str3 = str6;
                        i10 = 1;
                        break;
                    case 76:
                        i7 = indexCount2;
                        str2 = str8;
                        c2278j.c = typedArrayObtainStyledAttributes.getInt(index2, c2278j.c);
                        str = str7;
                        str3 = str6;
                        i10 = 1;
                        break;
                    case 77:
                        i7 = indexCount2;
                        str2 = str8;
                        c2277i.f21720k0 = typedArrayObtainStyledAttributes.getString(index2);
                        str = str7;
                        str3 = str6;
                        i10 = 1;
                        break;
                    case 78:
                        i7 = indexCount2;
                        str2 = str8;
                        kVar.f21747b = typedArrayObtainStyledAttributes.getInt(index2, kVar.f21747b);
                        str = str7;
                        str3 = str6;
                        i10 = 1;
                        break;
                    case 79:
                        i7 = indexCount2;
                        str2 = str8;
                        c2278j.d = typedArrayObtainStyledAttributes.getFloat(index2, c2278j.d);
                        str = str7;
                        str3 = str6;
                        i10 = 1;
                        break;
                    case 80:
                        i7 = indexCount2;
                        str2 = str8;
                        c2277i.f21721l0 = typedArrayObtainStyledAttributes.getBoolean(index2, c2277i.f21721l0);
                        str = str7;
                        str3 = str6;
                        i10 = 1;
                        break;
                    case 81:
                        i7 = indexCount2;
                        str2 = str8;
                        c2277i.f21723m0 = typedArrayObtainStyledAttributes.getBoolean(index2, c2277i.f21723m0);
                        str = str7;
                        str3 = str6;
                        i10 = 1;
                        break;
                    case 82:
                        i7 = indexCount2;
                        str2 = str8;
                        c2278j.f21740b = typedArrayObtainStyledAttributes.getInteger(index2, c2278j.f21740b);
                        str = str7;
                        str3 = str6;
                        i10 = 1;
                        break;
                    case 83:
                        i7 = indexCount2;
                        str2 = str8;
                        lVar.f21754h = f(typedArrayObtainStyledAttributes, index2, lVar.f21754h);
                        str = str7;
                        str3 = str6;
                        i10 = 1;
                        break;
                    case 84:
                        i7 = indexCount2;
                        str2 = str8;
                        c2278j.f21743g = typedArrayObtainStyledAttributes.getInteger(index2, c2278j.f21743g);
                        str = str7;
                        str3 = str6;
                        i10 = 1;
                        break;
                    case 85:
                        i7 = indexCount2;
                        str2 = str8;
                        c2278j.f21742f = typedArrayObtainStyledAttributes.getFloat(index2, c2278j.f21742f);
                        str = str7;
                        str3 = str6;
                        i10 = 1;
                        break;
                    case 86:
                        i7 = indexCount2;
                        str2 = str8;
                        int i19 = typedArrayObtainStyledAttributes.peekValue(index2).type;
                        if (i19 == 1) {
                            c2278j.f21745i = typedArrayObtainStyledAttributes.getResourceId(index2, -1);
                        } else if (i19 == 3) {
                            String string2 = typedArrayObtainStyledAttributes.getString(index2);
                            c2278j.f21744h = string2;
                            if (string2.indexOf("/") > 0) {
                                c2278j.f21745i = typedArrayObtainStyledAttributes.getResourceId(index2, -1);
                            }
                        } else {
                            typedArrayObtainStyledAttributes.getInteger(index2, c2278j.f21745i);
                        }
                        str = str7;
                        str3 = str6;
                        i10 = 1;
                        break;
                    case 87:
                        i7 = indexCount2;
                        str2 = str8;
                        h6.n.t(str6, "unused attribute 0x" + Integer.toHexString(index2) + "   " + sparseIntArray.get(index2));
                        str = str7;
                        str3 = str6;
                        i10 = 1;
                        break;
                    case 88:
                    case 89:
                    case 90:
                    default:
                        str2 = str8;
                        StringBuilder sb2 = new StringBuilder(str2);
                        i7 = indexCount2;
                        sb2.append(Integer.toHexString(index2));
                        sb2.append("   ");
                        sb2.append(sparseIntArray.get(index2));
                        h6.n.t(str6, sb2.toString());
                        str = str7;
                        str3 = str6;
                        i10 = 1;
                        break;
                    case 91:
                        i7 = indexCount2;
                        str2 = str8;
                        c2277i.f21728q = f(typedArrayObtainStyledAttributes, index2, c2277i.f21728q);
                        str = str7;
                        str3 = str6;
                        i10 = 1;
                        break;
                    case 92:
                        i7 = indexCount2;
                        str2 = str8;
                        c2277i.f21729r = f(typedArrayObtainStyledAttributes, index2, c2277i.f21729r);
                        str = str7;
                        str3 = str6;
                        i10 = 1;
                        break;
                    case 93:
                        i7 = indexCount2;
                        str2 = str8;
                        c2277i.f21686L = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, c2277i.f21686L);
                        str = str7;
                        str3 = str6;
                        i10 = 1;
                        break;
                    case 94:
                        i7 = indexCount2;
                        str2 = str8;
                        c2277i.f21693S = typedArrayObtainStyledAttributes.getDimensionPixelSize(index2, c2277i.f21693S);
                        str = str7;
                        str3 = str6;
                        i10 = 1;
                        break;
                    case 95:
                        i7 = indexCount2;
                        str2 = str8;
                        g(c2277i, typedArrayObtainStyledAttributes, index2, 0);
                        str = str7;
                        str3 = str6;
                        i10 = 1;
                        break;
                    case 96:
                        i7 = indexCount2;
                        str2 = str8;
                        g(c2277i, typedArrayObtainStyledAttributes, index2, 1);
                        str = str7;
                        str3 = str6;
                        i10 = 1;
                        break;
                    case 97:
                        i7 = indexCount2;
                        str2 = str8;
                        c2277i.f21726o0 = typedArrayObtainStyledAttributes.getInt(index2, c2277i.f21726o0);
                        str = str7;
                        str3 = str6;
                        i10 = 1;
                        break;
                }
                i17 += i10;
                indexCount2 = i7;
                str8 = str2;
                String str10 = str;
                i16 = i10;
                str6 = str3;
                str7 = str10;
            }
            if (c2277i.f21718j0 != null) {
                c2277i.f21716i0 = null;
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        return c2276h;
    }

    public static int f(TypedArray typedArray, int i7, int i10) {
        int resourceId = typedArray.getResourceId(i7, i10);
        return resourceId == -1 ? typedArray.getInt(i7, -1) : resourceId;
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
        throw new UnsupportedOperationException("Method not decompiled: r1.m.g(java.lang.Object, android.content.res.TypedArray, int, int):void");
    }

    public static void h(C2272d c2272d, String str) {
        if (str != null) {
            int length = str.length();
            int iIndexOf = str.indexOf(44);
            byte b10 = -1;
            int i7 = 0;
            if (iIndexOf > 0 && iIndexOf < length - 1) {
                String strSubstring = str.substring(0, iIndexOf);
                if (strSubstring.equalsIgnoreCase("W")) {
                    b10 = 0;
                } else if (strSubstring.equalsIgnoreCase("H")) {
                    b10 = 1;
                }
                i7 = iIndexOf + 1;
            }
            int iIndexOf2 = str.indexOf(58);
            try {
                if (iIndexOf2 < 0 || iIndexOf2 >= length - 1) {
                    String strSubstring2 = str.substring(i7);
                    if (strSubstring2.length() > 0) {
                        Float.parseFloat(strSubstring2);
                    }
                } else {
                    String strSubstring3 = str.substring(i7, iIndexOf2);
                    String strSubstring4 = str.substring(iIndexOf2 + 1);
                    if (strSubstring3.length() > 0 && strSubstring4.length() > 0) {
                        float f10 = Float.parseFloat(strSubstring3);
                        float f11 = Float.parseFloat(strSubstring4);
                        if (f10 > 0.0f && f11 > 0.0f) {
                            if (b10 == 1) {
                                Math.abs(f11 / f10);
                            } else {
                                Math.abs(f10 / f11);
                            }
                        }
                    }
                }
            } catch (NumberFormatException unused) {
            }
        }
        c2272d.f21594G = str;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final void a(ConstraintLayout constraintLayout) {
        int i7;
        int i10;
        int i11;
        String resourceEntryName;
        m mVar = this;
        int i12 = 1;
        int childCount = constraintLayout.getChildCount();
        HashMap map = mVar.c;
        HashSet<Integer> hashSet = new HashSet(map.keySet());
        int i13 = 0;
        while (i13 < childCount) {
            View childAt = constraintLayout.getChildAt(i13);
            int id = childAt.getId();
            if (!map.containsKey(Integer.valueOf(id))) {
                StringBuilder sb = new StringBuilder("id unknown ");
                try {
                    resourceEntryName = childAt.getContext().getResources().getResourceEntryName(childAt.getId());
                } catch (Exception unused) {
                    resourceEntryName = "UNKNOWN";
                }
                sb.append(resourceEntryName);
                h6.n.t("ConstraintSet", sb.toString());
            } else {
                if (mVar.f21762b && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (id != -1) {
                    if (map.containsKey(Integer.valueOf(id))) {
                        hashSet.remove(Integer.valueOf(id));
                        C2276h c2276h = (C2276h) map.get(Integer.valueOf(id));
                        if (c2276h != null) {
                            if (childAt instanceof Barrier) {
                                C2277i c2277i = c2276h.d;
                                c2277i.f21714h0 = i12;
                                Barrier barrier = (Barrier) childAt;
                                barrier.setId(id);
                                barrier.setType(c2277i.f21710f0);
                                barrier.setMargin(c2277i.f21712g0);
                                barrier.setAllowsGoneWidget(c2277i.f21725n0);
                                int[] iArr = c2277i.f21716i0;
                                if (iArr != null) {
                                    barrier.setReferencedIds(iArr);
                                } else {
                                    String str = c2277i.f21718j0;
                                    if (str != null) {
                                        int[] iArrC = c(barrier, str);
                                        c2277i.f21716i0 = iArrC;
                                        barrier.setReferencedIds(iArrC);
                                    }
                                }
                            }
                            C2272d c2272d = (C2272d) childAt.getLayoutParams();
                            c2272d.a();
                            c2276h.a(c2272d);
                            HashMap map2 = c2276h.f21674f;
                            Class<?> cls = childAt.getClass();
                            for (String str2 : map2.keySet()) {
                                C2269a c2269a = (C2269a) map2.get(str2);
                                String strG = !c2269a.f21578a ? M5.b.g("set", str2) : str2;
                                HashMap map3 = map2;
                                try {
                                    switch (AbstractC2641j.c(c2269a.f21579b)) {
                                        case 0:
                                            i11 = childCount;
                                            cls.getMethod(strG, Integer.TYPE).invoke(childAt, Integer.valueOf(c2269a.c));
                                            break;
                                        case 1:
                                            i11 = childCount;
                                            cls.getMethod(strG, Float.TYPE).invoke(childAt, Float.valueOf(c2269a.d));
                                            break;
                                        case 2:
                                            i11 = childCount;
                                            cls.getMethod(strG, Integer.TYPE).invoke(childAt, Integer.valueOf(c2269a.f21582g));
                                            break;
                                        case 3:
                                            Method method = cls.getMethod(strG, Drawable.class);
                                            i11 = childCount;
                                            try {
                                                ColorDrawable colorDrawable = new ColorDrawable();
                                                colorDrawable.setColor(c2269a.f21582g);
                                                method.invoke(childAt, colorDrawable);
                                            } catch (IllegalAccessException e10) {
                                                e = e10;
                                                StringBuilder sbJ = AbstractC1347d.j(" Custom Attribute \"", str2, "\" not found on ");
                                                sbJ.append(cls.getName());
                                                h6.n.i("TransitionLayout", sbJ.toString(), e);
                                            } catch (NoSuchMethodException e11) {
                                                e = e11;
                                                h6.n.i("TransitionLayout", cls.getName() + " must have a method " + strG, e);
                                            } catch (InvocationTargetException e12) {
                                                e = e12;
                                                StringBuilder sbJ2 = AbstractC1347d.j(" Custom Attribute \"", str2, "\" not found on ");
                                                sbJ2.append(cls.getName());
                                                h6.n.i("TransitionLayout", sbJ2.toString(), e);
                                            }
                                            break;
                                        case 4:
                                            cls.getMethod(strG, CharSequence.class).invoke(childAt, c2269a.f21580e);
                                            i11 = childCount;
                                            break;
                                        case 5:
                                            cls.getMethod(strG, Boolean.TYPE).invoke(childAt, Boolean.valueOf(c2269a.f21581f));
                                            i11 = childCount;
                                            break;
                                        case 6:
                                            cls.getMethod(strG, Float.TYPE).invoke(childAt, Float.valueOf(c2269a.d));
                                            i11 = childCount;
                                            break;
                                        case 7:
                                            cls.getMethod(strG, Integer.TYPE).invoke(childAt, Integer.valueOf(c2269a.c));
                                            i11 = childCount;
                                            break;
                                        default:
                                            i11 = childCount;
                                            break;
                                    }
                                } catch (IllegalAccessException e13) {
                                    e = e13;
                                    i11 = childCount;
                                } catch (NoSuchMethodException e14) {
                                    e = e14;
                                    i11 = childCount;
                                } catch (InvocationTargetException e15) {
                                    e = e15;
                                    i11 = childCount;
                                }
                                map2 = map3;
                                childCount = i11;
                            }
                            i7 = childCount;
                            childAt.setLayoutParams(c2272d);
                            k kVar = c2276h.f21672b;
                            if (kVar.f21747b == 0) {
                                childAt.setVisibility(kVar.f21746a);
                            }
                            childAt.setAlpha(kVar.c);
                            l lVar = c2276h.f21673e;
                            childAt.setRotation(lVar.f21749a);
                            childAt.setRotationX(lVar.f21750b);
                            childAt.setRotationY(lVar.c);
                            childAt.setScaleX(lVar.d);
                            childAt.setScaleY(lVar.f21751e);
                            if (lVar.f21754h != -1) {
                                if (((View) childAt.getParent()).findViewById(lVar.f21754h) != null) {
                                    float bottom = (r1.getBottom() + r1.getTop()) / 2.0f;
                                    float right = (r1.getRight() + r1.getLeft()) / 2.0f;
                                    if (childAt.getRight() - childAt.getLeft() > 0 && childAt.getBottom() - childAt.getTop() > 0) {
                                        childAt.setPivotX(right - childAt.getLeft());
                                        childAt.setPivotY(bottom - childAt.getTop());
                                    }
                                }
                            } else {
                                if (!Float.isNaN(lVar.f21752f)) {
                                    childAt.setPivotX(lVar.f21752f);
                                }
                                if (!Float.isNaN(lVar.f21753g)) {
                                    childAt.setPivotY(lVar.f21753g);
                                }
                            }
                            childAt.setTranslationX(lVar.f21755i);
                            childAt.setTranslationY(lVar.f21756j);
                            childAt.setTranslationZ(lVar.f21757k);
                            if (lVar.l) {
                                childAt.setElevation(lVar.f21758m);
                            }
                        }
                    } else {
                        i7 = childCount;
                        M5.b.o("WARNING NO CONSTRAINTS for view ", id, "ConstraintSet");
                    }
                    i10 = 1;
                }
                i13 += i10;
                i12 = i10;
                childCount = i7;
                mVar = this;
            }
            i10 = i12;
            i7 = childCount;
            i13 += i10;
            i12 = i10;
            childCount = i7;
            mVar = this;
        }
        int i14 = childCount;
        for (Integer num : hashSet) {
            C2276h c2276h2 = (C2276h) map.get(num);
            if (c2276h2 != null) {
                C2277i c2277i2 = c2276h2.d;
                if (c2277i2.f21714h0 == 1) {
                    Barrier barrier2 = new Barrier(constraintLayout.getContext());
                    barrier2.setId(num.intValue());
                    int[] iArr2 = c2277i2.f21716i0;
                    if (iArr2 != null) {
                        barrier2.setReferencedIds(iArr2);
                    } else {
                        String str3 = c2277i2.f21718j0;
                        if (str3 != null) {
                            int[] iArrC2 = c(barrier2, str3);
                            c2277i2.f21716i0 = iArrC2;
                            barrier2.setReferencedIds(iArrC2);
                        }
                    }
                    barrier2.setType(c2277i2.f21710f0);
                    barrier2.setMargin(c2277i2.f21712g0);
                    q qVar = ConstraintLayout.f11701A;
                    C2272d c2272d2 = new C2272d(-2, -2);
                    barrier2.e();
                    c2276h2.a(c2272d2);
                    constraintLayout.addView(barrier2, c2272d2);
                }
                if (c2277i2.f21701a) {
                    View guideline = new Guideline(constraintLayout.getContext());
                    guideline.setId(num.intValue());
                    q qVar2 = ConstraintLayout.f11701A;
                    C2272d c2272d3 = new C2272d(-2, -2);
                    c2276h2.a(c2272d3);
                    constraintLayout.addView(guideline, c2272d3);
                }
            }
        }
        for (int i15 = 0; i15 < i14; i15++) {
            View childAt2 = constraintLayout.getChildAt(i15);
            if (childAt2 instanceof AbstractC2270b) {
                ((AbstractC2270b) childAt2).getClass();
            }
        }
    }

    public final void b(ConstraintLayout constraintLayout) {
        int i7;
        HashMap map;
        HashMap map2;
        m mVar = this;
        int childCount = constraintLayout.getChildCount();
        HashMap map3 = mVar.c;
        map3.clear();
        int i10 = 0;
        while (i10 < childCount) {
            View childAt = constraintLayout.getChildAt(i10);
            C2272d c2272d = (C2272d) childAt.getLayoutParams();
            int id = childAt.getId();
            if (mVar.f21762b && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!map3.containsKey(Integer.valueOf(id))) {
                map3.put(Integer.valueOf(id), new C2276h());
            }
            C2276h c2276h = (C2276h) map3.get(Integer.valueOf(id));
            if (c2276h == null) {
                i7 = childCount;
                map = map3;
            } else {
                HashMap map4 = mVar.f21761a;
                HashMap map5 = new HashMap();
                Class<?> cls = childAt.getClass();
                for (String str : map4.keySet()) {
                    C2269a c2269a = (C2269a) map4.get(str);
                    int i11 = childCount;
                    try {
                        if (str.equals("BackgroundColor")) {
                            map2 = map3;
                            try {
                                map5.put(str, new C2269a(c2269a, Integer.valueOf(((ColorDrawable) childAt.getBackground()).getColor())));
                            } catch (IllegalAccessException e10) {
                                e = e10;
                                StringBuilder sbJ = AbstractC1347d.j(" Custom Attribute \"", str, "\" not found on ");
                                sbJ.append(cls.getName());
                                h6.n.i("TransitionLayout", sbJ.toString(), e);
                            } catch (NoSuchMethodException e11) {
                                e = e11;
                                h6.n.i("TransitionLayout", cls.getName() + " must have a method " + str, e);
                            } catch (InvocationTargetException e12) {
                                e = e12;
                                StringBuilder sbJ2 = AbstractC1347d.j(" Custom Attribute \"", str, "\" not found on ");
                                sbJ2.append(cls.getName());
                                h6.n.i("TransitionLayout", sbJ2.toString(), e);
                            }
                        } else {
                            map2 = map3;
                            map5.put(str, new C2269a(c2269a, cls.getMethod("getMap" + str, null).invoke(childAt, null)));
                        }
                    } catch (IllegalAccessException e13) {
                        e = e13;
                        map2 = map3;
                    } catch (NoSuchMethodException e14) {
                        e = e14;
                        map2 = map3;
                    } catch (InvocationTargetException e15) {
                        e = e15;
                        map2 = map3;
                    }
                    childCount = i11;
                    map3 = map2;
                }
                i7 = childCount;
                map = map3;
                c2276h.f21674f = map5;
                c2276h.f21671a = id;
                int i12 = c2272d.f21620e;
                C2277i c2277i = c2276h.d;
                c2277i.f21713h = i12;
                c2277i.f21715i = c2272d.f21622f;
                c2277i.f21717j = c2272d.f21624g;
                c2277i.f21719k = c2272d.f21626h;
                c2277i.l = c2272d.f21628i;
                c2277i.f21722m = c2272d.f21630j;
                c2277i.f21724n = c2272d.f21632k;
                c2277i.o = c2272d.l;
                c2277i.f21727p = c2272d.f21635m;
                c2277i.f21728q = c2272d.f21637n;
                c2277i.f21729r = c2272d.o;
                c2277i.f21730s = c2272d.f21644s;
                c2277i.f21731t = c2272d.f21645t;
                c2277i.f21732u = c2272d.f21646u;
                c2277i.f21733v = c2272d.f21647v;
                c2277i.f21734w = c2272d.f21592E;
                c2277i.f21735x = c2272d.f21593F;
                c2277i.f21736y = c2272d.f21594G;
                c2277i.f21737z = c2272d.f21640p;
                c2277i.f21676A = c2272d.f21642q;
                c2277i.B = c2272d.f21643r;
                c2277i.f21677C = c2272d.f21607T;
                c2277i.f21678D = c2272d.f21608U;
                c2277i.f21679E = c2272d.f21609V;
                c2277i.f21709f = c2272d.c;
                c2277i.d = c2272d.f21614a;
                c2277i.f21707e = c2272d.f21616b;
                c2277i.f21703b = ((ViewGroup.MarginLayoutParams) c2272d).width;
                c2277i.c = ((ViewGroup.MarginLayoutParams) c2272d).height;
                c2277i.f21680F = ((ViewGroup.MarginLayoutParams) c2272d).leftMargin;
                c2277i.f21681G = ((ViewGroup.MarginLayoutParams) c2272d).rightMargin;
                c2277i.f21682H = ((ViewGroup.MarginLayoutParams) c2272d).topMargin;
                c2277i.f21683I = ((ViewGroup.MarginLayoutParams) c2272d).bottomMargin;
                c2277i.f21686L = c2272d.f21591D;
                c2277i.f21694T = c2272d.f21596I;
                c2277i.f21695U = c2272d.f21595H;
                c2277i.f21697W = c2272d.f21598K;
                c2277i.f21696V = c2272d.f21597J;
                c2277i.f21721l0 = c2272d.f21610W;
                c2277i.f21723m0 = c2272d.f21611X;
                c2277i.f21698X = c2272d.f21599L;
                c2277i.f21699Y = c2272d.f21600M;
                c2277i.f21700Z = c2272d.f21603P;
                c2277i.f21702a0 = c2272d.f21604Q;
                c2277i.f21704b0 = c2272d.f21601N;
                c2277i.f21705c0 = c2272d.f21602O;
                c2277i.f21706d0 = c2272d.f21605R;
                c2277i.f21708e0 = c2272d.f21606S;
                c2277i.f21720k0 = c2272d.f21612Y;
                c2277i.f21688N = c2272d.f21649x;
                c2277i.f21690P = c2272d.f21651z;
                c2277i.f21687M = c2272d.f21648w;
                c2277i.f21689O = c2272d.f21650y;
                c2277i.f21692R = c2272d.f21589A;
                c2277i.f21691Q = c2272d.B;
                c2277i.f21693S = c2272d.f21590C;
                c2277i.f21726o0 = c2272d.f21613Z;
                c2277i.f21684J = c2272d.getMarginEnd();
                c2277i.f21685K = c2272d.getMarginStart();
                int visibility = childAt.getVisibility();
                k kVar = c2276h.f21672b;
                kVar.f21746a = visibility;
                kVar.c = childAt.getAlpha();
                float rotation = childAt.getRotation();
                l lVar = c2276h.f21673e;
                lVar.f21749a = rotation;
                lVar.f21750b = childAt.getRotationX();
                lVar.c = childAt.getRotationY();
                lVar.d = childAt.getScaleX();
                lVar.f21751e = childAt.getScaleY();
                float pivotX = childAt.getPivotX();
                float pivotY = childAt.getPivotY();
                if (pivotX != 0.0d || pivotY != 0.0d) {
                    lVar.f21752f = pivotX;
                    lVar.f21753g = pivotY;
                }
                lVar.f21755i = childAt.getTranslationX();
                lVar.f21756j = childAt.getTranslationY();
                lVar.f21757k = childAt.getTranslationZ();
                if (lVar.l) {
                    lVar.f21758m = childAt.getElevation();
                }
                if (childAt instanceof Barrier) {
                    Barrier barrier = (Barrier) childAt;
                    c2277i.f21725n0 = barrier.getAllowsGoneWidget();
                    c2277i.f21716i0 = barrier.getReferencedIds();
                    c2277i.f21710f0 = barrier.getType();
                    c2277i.f21712g0 = barrier.getMargin();
                }
            }
            i10++;
            mVar = this;
            childCount = i7;
            map3 = map;
        }
    }

    public final void e(Context context, int i7) {
        XmlResourceParser xml = context.getResources().getXml(i7);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 2) {
                    String name = xml.getName();
                    C2276h c2276hD = d(context, Xml.asAttributeSet(xml), false);
                    if (name.equalsIgnoreCase("Guideline")) {
                        c2276hD.d.f21701a = true;
                    }
                    this.c.put(Integer.valueOf(c2276hD.f21671a), c2276hD);
                }
            }
        } catch (IOException e10) {
            h6.n.i("ConstraintSet", "Error parsing resource: " + i7, e10);
        } catch (XmlPullParserException e11) {
            h6.n.i("ConstraintSet", "Error parsing resource: " + i7, e11);
        }
    }
}
