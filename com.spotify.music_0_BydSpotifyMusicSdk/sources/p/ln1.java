package p;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EdgeEffect;
import androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour;
import com.spotify.esperanto.esperanto.ServiceBase;
import com.spotify.shoreline.esperanto.proto.EsShorelineLogger$SetContextValueRequest;
import com.spotify.shoreline.esperanto.proto.EsShorelineLogger$SetContextValueResponse;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public abstract class ln1 implements ServiceBase {
    public static final ke2 a = new ke2(6);
    public static final vk0 b = new vk0(2);
    public static final boolean[] c = new boolean[3];
    public static final ke2 d = new ke2(8);
    public static final z90 e = new z90(15);
    public static c7 f;

    public static String b(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b2 : bArr) {
            sb.append(String.format("%02x", Byte.valueOf(b2)));
        }
        return sb.toString();
    }

    public static void c(uy uyVar, ra1 ra1Var, ty tyVar) {
        tyVar.o = -1;
        tyVar.f192p = -1;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = uyVar.S[0];
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2 = ConstraintWidget$DimensionBehaviour.e;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3 = ConstraintWidget$DimensionBehaviour.l;
        ConstraintWidget$DimensionBehaviour[] constraintWidget$DimensionBehaviourArr = tyVar.S;
        if (constraintWidget$DimensionBehaviour != constraintWidget$DimensionBehaviour2 && constraintWidget$DimensionBehaviourArr[0] == constraintWidget$DimensionBehaviour3) {
            xx xxVar = tyVar.H;
            int i = xxVar.g;
            int iO = uyVar.o();
            xx xxVar2 = tyVar.J;
            int i2 = iO - xxVar2.g;
            xxVar.i = ra1Var.k(xxVar);
            xxVar2.i = ra1Var.k(xxVar2);
            ra1Var.d(xxVar.i, i);
            ra1Var.d(xxVar2.i, i2);
            tyVar.o = 2;
            tyVar.Y = i;
            int i3 = i2 - i;
            tyVar.U = i3;
            int i4 = tyVar.b0;
            if (i3 < i4) {
                tyVar.U = i4;
            }
        }
        if (uyVar.S[1] == constraintWidget$DimensionBehaviour2 || constraintWidget$DimensionBehaviourArr[1] != constraintWidget$DimensionBehaviour3) {
            return;
        }
        xx xxVar3 = tyVar.I;
        int i5 = xxVar3.g;
        int i6 = uyVar.i();
        xx xxVar4 = tyVar.K;
        int i7 = i6 - xxVar4.g;
        xxVar3.i = ra1Var.k(xxVar3);
        xxVar4.i = ra1Var.k(xxVar4);
        ra1Var.d(xxVar3.i, i5);
        ra1Var.d(xxVar4.i, i7);
        if (tyVar.a0 > 0 || tyVar.g0 == 8) {
            xx xxVar5 = tyVar.L;
            xxVar5.i = ra1Var.k(xxVar5);
            ra1Var.d(xxVar5.i, tyVar.a0 + i5);
        }
        tyVar.f192p = 2;
        tyVar.Z = i5;
        int i8 = i7 - i5;
        tyVar.V = i8;
        int i9 = tyVar.c0;
        if (i8 < i9) {
            tyVar.V = i9;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0053, code lost:
    
        if (p.he1.l(r9, r1, p.v03.b(r7, r2) ? r0.getWidth() : p.i.f(r7.a, r8), p.v03.b(r7, r2) ? r0.getHeight() : p.i.f(r7.b, r8), r8) == 1.0d) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Bitmap d(android.graphics.drawable.Drawable r5, android.graphics.Bitmap.Config r6, p.qd2 r7, coil.size.Scale r8, boolean r9) {
        /*
            Method dump skipped, instruction units count: 236
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.ln1.d(android.graphics.drawable.Drawable, android.graphics.Bitmap$Config, p.qd2, coil.size.Scale, boolean):android.graphics.Bitmap");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static long[] e(Serializable serializable) {
        if (!(serializable instanceof int[])) {
            if (serializable instanceof long[]) {
                return (long[]) serializable;
            }
            return null;
        }
        int[] iArr = (int[]) serializable;
        long[] jArr = new long[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            jArr[i] = iArr[i];
        }
        return jArr;
    }

    public static sv0 f(Context context) {
        ProviderInfo providerInfoC;
        pv0 pv0VarV;
        ApplicationInfo applicationInfo;
        oh1 oh1Var = new oh1((Object) null);
        PackageManager packageManager = context.getPackageManager();
        p02.e(packageManager, "Package manager required to locate emoji font provider");
        Iterator it = ((u11) oh1Var.e).G(packageManager, new Intent("androidx.content.action.LOAD_EMOJI_FONT")).iterator();
        while (true) {
            if (!it.hasNext()) {
                providerInfoC = null;
                break;
            }
            providerInfoC = ((u11) oh1Var.e).C((ResolveInfo) it.next());
            if (providerInfoC != null && (applicationInfo = providerInfoC.applicationInfo) != null && (applicationInfo.flags & 1) == 1) {
                break;
            }
        }
        if (providerInfoC == null) {
            pv0VarV = null;
        } else {
            try {
                pv0VarV = oh1Var.v(providerInfoC, packageManager);
            } catch (PackageManager.NameNotFoundException unused) {
                pv0VarV = null;
            }
        }
        if (pv0VarV == null) {
            return null;
        }
        return new sv0(new rv0(context, pv0VarV));
    }

    public static final ee2 g(ko koVar, Context context) {
        return new ee2(koVar, null);
    }

    public static void h(String str, String str2, Object obj) {
        if (Log.isLoggable("TRuntime.".concat(str), 3)) {
            String.format(str2, obj);
        }
    }

    public static boolean i(File file) {
        if (!file.isDirectory()) {
            file.delete();
            return true;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            return false;
        }
        boolean z = true;
        for (File file2 : fileArrListFiles) {
            z = i(file2) && z;
        }
        return z;
    }

    public static void j(String str, String str2, Exception exc) {
        String strConcat = "TRuntime.".concat(str);
        if (Log.isLoggable(strConcat, 6)) {
            Log.e(strConcat, str2, exc);
        }
    }

    public static final boolean k(int i, int i2) {
        return (i & i2) == i2;
    }

    public static rk0 l(rk0 rk0Var, sk0 sk0Var) {
        v03.j(sk0Var, "key");
        if (v03.b(rk0Var.getKey(), sk0Var)) {
            return rk0Var;
        }
        return null;
    }

    public static float m(EdgeEffect edgeEffect) {
        if (Build.VERSION.SDK_INT >= 31) {
            return yb0.b(edgeEffect);
        }
        return 0.0f;
    }

    public static Intent n(Activity activity) {
        Intent intentA = km1.a(activity);
        if (intentA != null) {
            return intentA;
        }
        try {
            String strP = p(activity, activity.getComponentName());
            if (strP == null) {
                return null;
            }
            ComponentName componentName = new ComponentName(activity, strP);
            try {
                return p(activity, componentName) == null ? Intent.makeMainActivity(componentName) : new Intent().setComponent(componentName);
            } catch (PackageManager.NameNotFoundException unused) {
                Log.e("NavUtils", "getParentActivityIntent: bad parentActivityName '" + strP + "' in manifest");
                return null;
            }
        } catch (PackageManager.NameNotFoundException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public static Intent o(Context context, ComponentName componentName) {
        String strP = p(context, componentName);
        if (strP == null) {
            return null;
        }
        ComponentName componentName2 = new ComponentName(componentName.getPackageName(), strP);
        return p(context, componentName2) == null ? Intent.makeMainActivity(componentName2) : new Intent().setComponent(componentName2);
    }

    public static String p(Context context, ComponentName componentName) {
        String string;
        ActivityInfo activityInfo = context.getPackageManager().getActivityInfo(componentName, Build.VERSION.SDK_INT >= 29 ? 269222528 : 787072);
        String str = activityInfo.parentActivityName;
        if (str != null) {
            return str;
        }
        Bundle bundle = activityInfo.metaData;
        if (bundle == null || (string = bundle.getString("android.support.PARENT_ACTIVITY")) == null) {
            return null;
        }
        if (string.charAt(0) != '.') {
            return string;
        }
        return context.getPackageName() + string;
    }

    public static String q(String str, String str2) {
        int length = str.length() - str2.length();
        if (length < 0 || length > 1) {
            throw new IllegalArgumentException("Invalid input received");
        }
        StringBuilder sb = new StringBuilder(str2.length() + str.length());
        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i));
            if (str2.length() > i) {
                sb.append(str2.charAt(i));
            }
        }
        return sb.toString();
    }

    public static final void r(Context context) {
        Map mapC;
        v03.j(context, "context");
        File databasePath = context.getDatabasePath("androidx.work.workdb");
        v03.i(databasePath, "context.getDatabasePath(WORK_DATABASE_NAME)");
        if (databasePath.exists()) {
            vc1 vc1VarC = vc1.c();
            String[] strArr = kx2.a;
            vc1VarC.getClass();
            File databasePath2 = context.getDatabasePath("androidx.work.workdb");
            v03.i(databasePath2, "context.getDatabasePath(WORK_DATABASE_NAME)");
            File file = new File(g7.a.a(context), "androidx.work.workdb");
            String[] strArr2 = kx2.a;
            int iB = he1.B(strArr2.length);
            if (iB < 16) {
                iB = 16;
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap(iB);
            for (String str : strArr2) {
                Pair pair = new Pair(new File(databasePath2.getPath() + str), new File(file.getPath() + str));
                linkedHashMap.put(pair.c(), pair.d());
            }
            Pair pair2 = new Pair(databasePath2, file);
            if (linkedHashMap.isEmpty()) {
                mapC = he1.C(pair2);
            } else {
                LinkedHashMap linkedHashMap2 = new LinkedHashMap(linkedHashMap);
                linkedHashMap2.put(pair2.c(), pair2.d());
                mapC = linkedHashMap2;
            }
            for (Map.Entry entry : mapC.entrySet()) {
                File file2 = (File) entry.getKey();
                File file3 = (File) entry.getValue();
                if (file2.exists()) {
                    if (file3.exists()) {
                        vc1 vc1VarC2 = vc1.c();
                        String[] strArr3 = kx2.a;
                        file3.toString();
                        vc1VarC2.getClass();
                    }
                    if (file2.renameTo(file3)) {
                        file2.toString();
                        file3.toString();
                    } else {
                        file2.toString();
                        file3.toString();
                    }
                    vc1 vc1VarC3 = vc1.c();
                    String[] strArr4 = kx2.a;
                    vc1VarC3.getClass();
                }
            }
        }
    }

    public static tk0 s(rk0 rk0Var, sk0 sk0Var) {
        v03.j(sk0Var, "key");
        return v03.b(rk0Var.getKey(), sk0Var) ? td0.b : rk0Var;
    }

    public static void t(View view, EditorInfo editorInfo, InputConnection inputConnection) {
        if (inputConnection == null || editorInfo.hintText != null) {
            return;
        }
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
        }
    }

    public static float u(EdgeEffect edgeEffect, float f2, float f3) {
        if (Build.VERSION.SDK_INT >= 31) {
            return yb0.c(edgeEffect, f2, f3);
        }
        xb0.a(edgeEffect, f2, f3);
        return f2;
    }

    public static ek1 v(MappedByteBuffer mappedByteBuffer) throws IOException {
        long jZ;
        ByteBuffer byteBufferDuplicate = mappedByteBuffer.duplicate();
        oh1 oh1Var = new oh1(byteBufferDuplicate);
        oh1Var.B(4);
        int i = ((ByteBuffer) oh1Var.e).getShort() & 65535;
        if (i > 100) {
            throw new IOException("Cannot read metadata.");
        }
        oh1Var.B(6);
        int i2 = 0;
        while (true) {
            if (i2 >= i) {
                jZ = -1;
                break;
            }
            int i3 = ((ByteBuffer) oh1Var.e).getInt();
            oh1Var.B(4);
            jZ = oh1Var.z();
            oh1Var.B(4);
            if (1835365473 == i3) {
                break;
            }
            i2++;
        }
        if (jZ != -1) {
            oh1Var.B((int) (jZ - ((long) ((ByteBuffer) oh1Var.e).position())));
            oh1Var.B(12);
            long jZ2 = oh1Var.z();
            for (int i4 = 0; i4 < jZ2; i4++) {
                int i5 = ((ByteBuffer) oh1Var.e).getInt();
                long jZ3 = oh1Var.z();
                oh1Var.z();
                if (1164798569 == i5 || 1701669481 == i5) {
                    byteBufferDuplicate.position((int) (jZ3 + jZ));
                    ek1 ek1Var = new ek1();
                    byteBufferDuplicate.order(ByteOrder.LITTLE_ENDIAN);
                    int iPosition = byteBufferDuplicate.position() + byteBufferDuplicate.getInt(byteBufferDuplicate.position());
                    ek1Var.b = byteBufferDuplicate;
                    ek1Var.a = iPosition;
                    int i6 = iPosition - byteBufferDuplicate.getInt(iPosition);
                    ek1Var.c = i6;
                    ek1Var.d = ek1Var.b.getShort(i6);
                    return ek1Var;
                }
            }
        }
        throw new IOException("Cannot read metadata.");
    }

    public abstract EsShorelineLogger$SetContextValueResponse a(EsShorelineLogger$SetContextValueRequest esShorelineLogger$SetContextValueRequest);

    @Override // com.spotify.esperanto.esperanto.Transport
    public Single callSingle(String str, String str2, byte[] bArr) {
        v03.j(str, "service");
        v03.j(str2, "method");
        v03.j(bArr, "payload");
        if (v03.b(str, "spotify.shoreline.esperanto.proto.ShorelineLogger")) {
            throw new RuntimeException(u4.i("Attempted to access unknown method. [", str, ':', str2, ']'));
        }
        throw new RuntimeException(u4.j("Attempted to access mismatched [", str, "], but this service is [spotify.shoreline.esperanto.proto.ShorelineLogger]"));
    }

    @Override // com.spotify.esperanto.esperanto.Transport
    public Observable callStream(String str, String str2, byte[] bArr) {
        v03.j(str, "service");
        v03.j(str2, "method");
        v03.j(bArr, "payload");
        if (v03.b(str, "spotify.shoreline.esperanto.proto.ShorelineLogger")) {
            throw new RuntimeException(u4.i("Attempted to access unknown method. [", str, ':', str2, ']'));
        }
        throw new RuntimeException(u4.j("Attempted to access mismatched [", str, "], but this service is [spotify.shoreline.esperanto.proto.ShorelineLogger]"));
    }

    @Override // com.spotify.esperanto.esperanto.Transport
    public byte[] callSync(String str, String str2, byte[] bArr) {
        v03.j(str, "service");
        v03.j(str2, "method");
        v03.j(bArr, "payload");
        if (!v03.b(str, "spotify.shoreline.esperanto.proto.ShorelineLogger")) {
            throw new RuntimeException(u4.j("Attempted to access mismatched [", str, "], but this service is [spotify.shoreline.esperanto.proto.ShorelineLogger]"));
        }
        if (!v03.b(str2, "SetContextValue")) {
            throw new RuntimeException(u4.i("Attempted to access unknown method. [", str, ':', str2, ']'));
        }
        EsShorelineLogger$SetContextValueRequest esShorelineLogger$SetContextValueRequestK = EsShorelineLogger$SetContextValueRequest.k(bArr);
        v03.i(esShorelineLogger$SetContextValueRequestK, "request_msg");
        byte[] byteArray = a(esShorelineLogger$SetContextValueRequestK).toByteArray();
        v03.i(byteArray, "SetContextValue(request_msg).toByteArray()");
        return byteArray;
    }

    @Override // com.spotify.esperanto.esperanto.ServiceBase
    public String getName() {
        return "spotify.shoreline.esperanto.proto.ShorelineLogger";
    }
}
