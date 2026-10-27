package p;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import android.os.Trace;
import android.text.TextUtils;
import android.util.Base64;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour;
import androidx.lifecycle.Lifecycle$State;
import androidx.lifecycle.SavedStateHandleAttacher;
import coil.size.Scale;
import com.apollographql.apollo3.api.json.JsonReader$Token;
import com.google.android.play.core.internal.zzck;
import com.google.common.collect.ImmutableSet;
import com.spotify.automotive.ubi.Referrer;
import com.spotify.mediasession.imageprovider.MediaUriUtil$Transformation;
import com.spotify.mediasession.mediaitem.MediaBrowserItemsExtrasBuilder$IconHint;
import com.spotify.music.R;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.ProtocolViolationException;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.operators.observable.ObservableScalarXMap$ScalarDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.zip.GZIPOutputStream;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import okhttp3.Headers;

/* JADX INFO: loaded from: classes.dex */
public abstract class he1 {
    public static final v4 c;
    public static v4 d;
    public static long i;
    public static Method j;
    public static final ke2 a = new ke2(5);
    public static final int[] b = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};
    public static final zb0 e = new zb0();
    public static final zb0 f = new zb0();
    public static final zb0 g = new zb0();
    public static final ke2 h = new ke2(19);

    static {
        Object obj = null;
        c = new v4(obj, obj, obj, 24);
    }

    public static boolean A() {
        try {
            if (j == null) {
                return Trace.isEnabled();
            }
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
        }
        try {
            if (j == null) {
                i = Trace.class.getField("TRACE_TAG_APP").getLong(null);
                j = Trace.class.getMethod("isTagEnabled", Long.TYPE);
            }
            return ((Boolean) j.invoke(null, Long.valueOf(i))).booleanValue();
        } catch (Exception e2) {
            if (!(e2 instanceof InvocationTargetException)) {
                return false;
            }
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            throw new RuntimeException(cause);
        }
    }

    public static int B(int i2) {
        if (i2 < 0) {
            return i2;
        }
        if (i2 < 3) {
            return i2 + 1;
        }
        if (i2 < 1073741824) {
            return (int) ((i2 / 0.75f) + 1.0f);
        }
        return Integer.MAX_VALUE;
    }

    public static Map C(Pair pair) {
        v03.j(pair, "pair");
        Map mapSingletonMap = Collections.singletonMap(pair.c(), pair.d());
        v03.i(mapSingletonMap, "singletonMap(pair.first, pair.second)");
        return mapSingletonMap;
    }

    public static ArrayList D(Iterable iterable) {
        iterable.getClass();
        if (iterable instanceof Collection) {
            return new ArrayList((Collection) iterable);
        }
        Iterator it = iterable.iterator();
        ArrayList arrayList = new ArrayList();
        it.getClass();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public static ArrayList E(Object... objArr) {
        int length = objArr.length;
        e(length, "arraySize");
        long j2 = ((long) length) + 5 + ((long) (length / 10));
        ArrayList arrayList = new ArrayList(j2 > 2147483647L ? Integer.MAX_VALUE : j2 < -2147483648L ? Integer.MIN_VALUE : (int) j2);
        Collections.addAll(arrayList, objArr);
        return arrayList;
    }

    public static final Object J(u71 u71Var) {
        Object obj;
        v03.j(u71Var, "<this>");
        JsonReader$Token jsonReader$TokenPeek = u71Var.peek();
        int iOrdinal = jsonReader$TokenPeek.ordinal();
        if (iOrdinal == 0) {
            u71Var.j();
            ArrayList arrayList = new ArrayList();
            while (u71Var.hasNext()) {
                arrayList.add(J(u71Var));
            }
            u71Var.h();
            obj = arrayList;
        } else {
            if (iOrdinal != 2) {
                switch (iOrdinal) {
                    case 5:
                        return u71Var.u();
                    case 6:
                    case 7:
                        try {
                            try {
                                try {
                                    return Integer.valueOf(u71Var.D0());
                                } catch (Exception unused) {
                                    return Long.valueOf(u71Var.Z());
                                }
                            } catch (Exception unused2) {
                                return u71Var.U();
                            }
                        } catch (Exception unused3) {
                            return Double.valueOf(u71Var.T());
                        }
                    case 8:
                        return Boolean.valueOf(u71Var.w0());
                    case 9:
                        u71Var.z0();
                        return null;
                    default:
                        throw new IllegalStateException(("unknown token " + jsonReader$TokenPeek).toString());
                }
            }
            u71Var.f();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            while (u71Var.hasNext()) {
                linkedHashMap.put(u71Var.r0(), J(u71Var));
            }
            u71Var.k();
            obj = linkedHashMap;
        }
        return obj;
    }

    public static final byte[] K(FileInputStream fileInputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(Math.max(8192, fileInputStream.available()));
        byte[] bArr = new byte[8192];
        int i2 = fileInputStream.read(bArr);
        while (i2 >= 0) {
            byteArrayOutputStream.write(bArr, 0, i2);
            i2 = fileInputStream.read(bArr);
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        v03.i(byteArray, "buffer.toByteArray()");
        return byteArray;
    }

    public static final Object L(Object obj) {
        return obj instanceof hv ? kotlin.a.b(((hv) obj).a) : obj;
    }

    public static void M(Class cls) {
        String name = cls.getName();
        RxJavaPlugins.b(new ProtocolViolationException("It is not allowed to subscribe with a(n) " + name + " multiple times. Please create a fresh instance of " + name + " and subscribe that to the target source instead."));
    }

    public static TypedValue N(Context context, int i2) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(i2, typedValue, true)) {
            return typedValue;
        }
        return null;
    }

    public static int O(int i2, Context context, String str) {
        TypedValue typedValueN = N(context, i2);
        if (typedValueN != null) {
            return typedValueN.data;
        }
        throw new IllegalArgumentException(String.format("%1$s requires a value for the %2$s attribute to be set in your app theme. You can either set the attribute in your theme or update your theme to inherit from Theme.MaterialComponents (or a descendant).", str, context.getResources().getResourceName(i2)));
    }

    public static int P(int i2) {
        return (int) (((long) Integer.rotateLeft((int) (((long) i2) * (-862048943)), 15)) * 461845907);
    }

    public static void Q(Object obj, String str) {
        ClassCastException classCastException = new ClassCastException((obj == null ? "null" : obj.getClass().getName()) + " cannot be cast to " + str);
        v03.I(he1.class.getName(), classCastException);
        throw classCastException;
    }

    public static void R(Throwable th) {
        if (th instanceof VirtualMachineError) {
            throw ((VirtualMachineError) th);
        }
        if (th instanceof ThreadDeath) {
            throw ((ThreadDeath) th);
        }
        if (th instanceof LinkageError) {
            throw ((LinkageError) th);
        }
    }

    public static final dg1 S(fe feVar, Referrer referrer, String str, int i2, ki1 ki1Var, lh1 lh1Var, Resources resources) {
        v03.j(feVar, "<this>");
        v03.j(referrer, "referrer");
        v03.j(ki1Var, "mediaUriUtil");
        v03.j(resources, "resources");
        return T(feVar, referrer.a(), str, Integer.valueOf(i2), ki1Var, lh1Var, resources);
    }

    public static final dg1 T(fe feVar, String str, String str2, Integer num, ki1 ki1Var, lh1 lh1Var, Resources resources) {
        v03.j(feVar, "<this>");
        v03.j(ki1Var, "mediaUriUtil");
        v03.j(resources, "resources");
        String string = null;
        ee eeVar = feVar.c;
        Uri uriB = wb1.b(r(eeVar != null ? eeVar.b : null), ki1Var, MediaUriUtil$Transformation.e, ki1Var.a(R.drawable.audiobook_placeholder));
        p01 p01Var = new p01(25);
        p01Var.E(1);
        p01Var.H(str2);
        p01Var.A();
        p01Var.B(MediaBrowserItemsExtrasBuilder$IconHint.PLUS);
        Bundle bundle = (Bundle) p01Var.e;
        List<de> list = feVar.b;
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            for (de deVar : list) {
                String str3 = deVar != null ? deVar.a : null;
                if (str3 != null) {
                    arrayList.add(str3);
                }
            }
            string = ou.h0(arrayList, ", ", null, null, null, 62);
        }
        ce ceVar = feVar.a;
        if (ceVar != null) {
            if (v03.b(ceVar.a, Boolean.FALSE)) {
                string = resources.getString(R.string.content_audiobook_locked_subtitle, string);
            }
        }
        jh1 jh1Var = new jh1();
        String str4 = feVar.e;
        kh1 kh1VarS = yn.s(str4);
        kh1VarS.c = str;
        kh1VarS.d = num;
        jh1Var.a = kh1VarS.a();
        jh1Var.g = Uri.parse(str4);
        jh1Var.f = uriB;
        jh1Var.b = feVar.d;
        jh1Var.h = bundle;
        jh1Var.c = string;
        return new dg1(jh1Var, lh1Var.a(str4));
    }

    public static final Headers U(List list) {
        v03.j(list, "<this>");
        Headers.Builder builder = new Headers.Builder();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            g21 g21Var = (g21) it.next();
            builder.a(g21Var.a, g21Var.b);
        }
        return builder.e();
    }

    public static final Map V(Map map) {
        v03.j(map, "<this>");
        Map.Entry entry = (Map.Entry) map.entrySet().iterator().next();
        Map mapSingletonMap = Collections.singletonMap(entry.getKey(), entry.getValue());
        v03.i(mapSingletonMap, "with(entries.iterator().…ingletonMap(key, value) }");
        return mapSingletonMap;
    }

    public static boolean X(ObservableSource observableSource, Observer observer, io.reactivex.rxjava3.functions.o oVar) {
        Disposable disposable = EmptyDisposable.c;
        if (!(observableSource instanceof io.reactivex.rxjava3.functions.q)) {
            return false;
        }
        try {
            Object obj = ((io.reactivex.rxjava3.functions.q) observableSource).get();
            if (obj == null) {
                observer.onSubscribe(disposable);
                observer.onComplete();
                return true;
            }
            try {
                Object objApply = oVar.apply(obj);
                Objects.requireNonNull(objApply, "The mapper returned a null ObservableSource");
                ObservableSource observableSource2 = (ObservableSource) objApply;
                if (observableSource2 instanceof io.reactivex.rxjava3.functions.q) {
                    try {
                        Object obj2 = ((io.reactivex.rxjava3.functions.q) observableSource2).get();
                        if (obj2 == null) {
                            observer.onSubscribe(disposable);
                            observer.onComplete();
                            return true;
                        }
                        ObservableScalarXMap$ScalarDisposable observableScalarXMap$ScalarDisposable = new ObservableScalarXMap$ScalarDisposable(observer, obj2);
                        observer.onSubscribe(observableScalarXMap$ScalarDisposable);
                        observableScalarXMap$ScalarDisposable.run();
                    } catch (Throwable th) {
                        R(th);
                        observer.onSubscribe(disposable);
                        observer.onError(th);
                        return true;
                    }
                } else {
                    observableSource2.subscribe(observer);
                }
                return true;
            } catch (Throwable th2) {
                R(th2);
                observer.onSubscribe(disposable);
                observer.onError(th2);
                return true;
            }
        } catch (Throwable th3) {
            R(th3);
            observer.onSubscribe(disposable);
            observer.onError(th3);
            return true;
        }
    }

    public static boolean Y(ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour, ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2, ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3, ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour4) {
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour5 = ConstraintWidget$DimensionBehaviour.c;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour6 = ConstraintWidget$DimensionBehaviour.l;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour7 = ConstraintWidget$DimensionBehaviour.e;
        return (constraintWidget$DimensionBehaviour3 == constraintWidget$DimensionBehaviour5 || constraintWidget$DimensionBehaviour3 == constraintWidget$DimensionBehaviour7 || (constraintWidget$DimensionBehaviour3 == constraintWidget$DimensionBehaviour6 && constraintWidget$DimensionBehaviour != constraintWidget$DimensionBehaviour7)) || (constraintWidget$DimensionBehaviour4 == constraintWidget$DimensionBehaviour5 || constraintWidget$DimensionBehaviour4 == constraintWidget$DimensionBehaviour7 || (constraintWidget$DimensionBehaviour4 == constraintWidget$DimensionBehaviour6 && constraintWidget$DimensionBehaviour2 != constraintWidget$DimensionBehaviour7));
    }

    public static void Z(int i2, Parcel parcel, Parcelable parcelable) {
        if (parcelable == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcelable.writeToParcel(parcel, i2);
        }
    }

    public static Object a(Parcel parcel, Parcelable.Creator creator) {
        if (parcel.readInt() != 0) {
            return creator.createFromParcel(parcel);
        }
        return null;
    }

    public static int a0(byte[] bArr, int i2) {
        return ((bArr[i2 + 1] & 255) << 8) | (bArr[i2] & 255);
    }

    public static Map b(Object obj) {
        if ((obj instanceof g81) && !(obj instanceof i81)) {
            Q(obj, "kotlin.collections.MutableMap");
            throw null;
        }
        try {
            return (Map) obj;
        } catch (ClassCastException e2) {
            v03.I(he1.class.getName(), e2);
            throw e2;
        }
    }

    public static String b0(List list) throws NoSuchAlgorithmException, IOException {
        int i2;
        MessageDigest messageDigest = MessageDigest.getInstance("SHA256");
        byte[] bArr = new byte[8192];
        Iterator it = list.iterator();
        while (it.hasNext()) {
            FileInputStream fileInputStream = new FileInputStream((File) it.next());
            do {
                try {
                    i2 = fileInputStream.read(bArr);
                    if (i2 > 0) {
                        messageDigest.update(bArr, 0, i2);
                    }
                } catch (Throwable th) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable unused) {
                    }
                    throw th;
                }
            } while (i2 != -1);
            fileInputStream.close();
        }
        return Base64.encodeToString(messageDigest.digest(), 11);
    }

    public static Object c(wc0 wc0Var) throws ExecutionException, InterruptedException {
        boolean z;
        Exception exc;
        Exception exc2;
        if (wc0Var == null) {
            throw new NullPointerException("Task must not be null");
        }
        synchronized (wc0Var.b) {
            z = wc0Var.a;
        }
        if (z) {
            if (wc0Var.e()) {
                return wc0Var.c();
            }
            synchronized (wc0Var.b) {
                exc2 = (Exception) wc0Var.e;
            }
            throw new ExecutionException(exc2);
        }
        oh1 oh1Var = new oh1((u4) null);
        wa waVar = cj2.b;
        ((s23) wc0Var.c).a(new i03((Executor) waVar, (dq1) oh1Var));
        wc0Var.i();
        ((s23) wc0Var.c).a(new i03((Executor) waVar, (wp1) oh1Var));
        wc0Var.i();
        ((CountDownLatch) oh1Var.e).await();
        if (wc0Var.e()) {
            return wc0Var.c();
        }
        synchronized (wc0Var.b) {
            exc = (Exception) wc0Var.e;
        }
        throw new ExecutionException(exc);
    }

    public static void c0(com.google.android.play.core.assetpacks.d dVar, InputStream inputStream, e13 e13Var, long j2) throws IOException {
        byte[] bArr = new byte[16384];
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(inputStream, 4096));
        int i2 = dataInputStream.readInt();
        if (i2 != -771763713) {
            String strValueOf = String.valueOf(String.format("%x", Integer.valueOf(i2)));
            throw new zzck(strValueOf.length() != 0 ? "Unexpected magic=".concat(strValueOf) : new String("Unexpected magic="));
        }
        int i3 = dataInputStream.read();
        if (i3 != 4) {
            StringBuilder sb = new StringBuilder(30);
            sb.append("Unexpected version=");
            sb.append(i3);
            throw new zzck(sb.toString());
        }
        long j3 = 0;
        while (true) {
            long j4 = j2 - j3;
            try {
                int unsignedShort = dataInputStream.read();
                if (unsignedShort == -1) {
                    throw new IOException("Patch file overrun");
                }
                if (unsignedShort == 0) {
                    e13Var.flush();
                    return;
                }
                switch (unsignedShort) {
                    case 247:
                        unsignedShort = dataInputStream.readUnsignedShort();
                        f0(bArr, dataInputStream, e13Var, unsignedShort, j4);
                        break;
                    case 248:
                        unsignedShort = dataInputStream.readInt();
                        f0(bArr, dataInputStream, e13Var, unsignedShort, j4);
                        break;
                    case 249:
                        long unsignedShort2 = dataInputStream.readUnsignedShort();
                        unsignedShort = dataInputStream.read();
                        if (unsignedShort == -1) {
                            throw new IOException("Unexpected end of patch");
                        }
                        d0(bArr, dVar, e13Var, unsignedShort2, unsignedShort, j4);
                        break;
                        break;
                    case 250:
                        long unsignedShort3 = dataInputStream.readUnsignedShort();
                        unsignedShort = dataInputStream.readUnsignedShort();
                        d0(bArr, dVar, e13Var, unsignedShort3, unsignedShort, j4);
                        break;
                    case 251:
                        long unsignedShort4 = dataInputStream.readUnsignedShort();
                        unsignedShort = dataInputStream.readInt();
                        d0(bArr, dVar, e13Var, unsignedShort4, unsignedShort, j4);
                        break;
                    case 252:
                        long j5 = dataInputStream.readInt();
                        unsignedShort = dataInputStream.read();
                        if (unsignedShort == -1) {
                            throw new IOException("Unexpected end of patch");
                        }
                        d0(bArr, dVar, e13Var, j5, unsignedShort, j4);
                        break;
                        break;
                    case 253:
                        long j6 = dataInputStream.readInt();
                        unsignedShort = dataInputStream.readUnsignedShort();
                        d0(bArr, dVar, e13Var, j6, unsignedShort, j4);
                        break;
                    case 254:
                        long j7 = dataInputStream.readInt();
                        unsignedShort = dataInputStream.readInt();
                        d0(bArr, dVar, e13Var, j7, unsignedShort, j4);
                        break;
                    case 255:
                        long j8 = dataInputStream.readLong();
                        unsignedShort = dataInputStream.readInt();
                        d0(bArr, dVar, e13Var, j8, unsignedShort, j4);
                        break;
                    default:
                        f0(bArr, dataInputStream, e13Var, unsignedShort, j4);
                        break;
                }
                j3 += (long) unsignedShort;
            } catch (Throwable th) {
                e13Var.flush();
                throw th;
            }
        }
    }

    public static void d(int i2, Object obj) {
        if (obj != null) {
            if (obj instanceof gz0) {
                if ((obj instanceof qz0 ? ((qz0) obj).getArity() : obj instanceof py0 ? 0 : obj instanceof ry0 ? 1 : obj instanceof fz0 ? 2 : obj instanceof hz0 ? 3 : obj instanceof iz0 ? 4 : obj instanceof kz0 ? 6 : -1) == i2) {
                    return;
                }
            }
            Q(obj, "kotlin.jvm.functions.Function" + i2);
            throw null;
        }
    }

    public static void d0(byte[] bArr, com.google.android.play.core.assetpacks.d dVar, e13 e13Var, long j2, int i2, long j3) throws IOException {
        InputStream inputStreamQ;
        int i3 = i2;
        if (i3 < 0) {
            throw new IOException("copyLength negative");
        }
        if (j2 < 0) {
            throw new IOException("inputOffset negative");
        }
        long j4 = i3;
        if (j4 > j3) {
            throw new IOException("Output length overrun");
        }
        try {
            d13 d13Var = new d13(dVar, j2, j4);
            synchronized (d13Var) {
                inputStreamQ = d13Var.q(0L, d13Var.i());
            }
            while (i3 > 0) {
                try {
                    int iMin = Math.min(i3, 16384);
                    int i4 = 0;
                    while (i4 < iMin) {
                        int i5 = inputStreamQ.read(bArr, i4, iMin - i4);
                        if (i5 == -1) {
                            throw new IOException("truncated input stream");
                        }
                        i4 += i5;
                    }
                    e13Var.write(bArr, 0, iMin);
                    i3 -= iMin;
                } finally {
                    try {
                        inputStreamQ.close();
                    } catch (Throwable unused) {
                    }
                }
            }
            inputStreamQ.close();
        } catch (EOFException e2) {
            throw new IOException("patch underrun", e2);
        }
    }

    public static void e(int i2, String str) {
        if (i2 >= 0) {
            return;
        }
        throw new IllegalArgumentException(str + " cannot be negative but was: " + i2);
    }

    public static boolean e0(int i2) {
        return i2 == 2 || i2 == 7 || i2 == 3;
    }

    public static void f(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void f0(byte[] bArr, DataInputStream dataInputStream, e13 e13Var, int i2, long j2) throws IOException {
        if (i2 < 0) {
            throw new IOException("copyLength negative");
        }
        if (i2 > j2) {
            throw new IOException("Output length overrun");
        }
        while (i2 > 0) {
            try {
                int iMin = Math.min(i2, 16384);
                dataInputStream.readFully(bArr, 0, iMin);
                e13Var.write(bArr, 0, iMin);
                i2 -= iMin;
            } catch (EOFException unused) {
                throw new IOException("patch underrun");
            }
        }
    }

    public static void g(Object obj) {
        if (obj == null) {
            throw new NullPointerException("null reference");
        }
    }

    public static boolean g0(int i2, int i3) {
        if (i2 == 5) {
            if (i3 != 5) {
                return true;
            }
            i2 = 5;
        }
        if (i2 == 6) {
            if (i3 != 6 && i3 != 5) {
                return true;
            }
            i2 = 6;
        }
        if (i2 == 4 && i3 != 4) {
            return true;
        }
        if (i2 == 3 && (i3 == 2 || i3 == 7 || i3 == 1 || i3 == 8)) {
            return true;
        }
        if (i2 == 2) {
            return i3 == 1 || i3 == 8;
        }
        return false;
    }

    public static void h(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }

    public static int i(Context context, String str) {
        int iC;
        int iMyPid = Process.myPid();
        int iMyUid = Process.myUid();
        String packageName = context.getPackageName();
        if (context.checkPermission(str, iMyPid, iMyUid) == -1) {
            return -1;
        }
        String strD = ya.d(str);
        if (strD != null) {
            if (packageName == null) {
                String[] packagesForUid = context.getPackageManager().getPackagesForUid(iMyUid);
                if (packagesForUid == null || packagesForUid.length <= 0) {
                    return -1;
                }
                packageName = packagesForUid[0];
            }
            int iMyUid2 = Process.myUid();
            String packageName2 = context.getPackageName();
            if (iMyUid2 == iMyUid && ap1.a(packageName2, packageName) && Build.VERSION.SDK_INT >= 29) {
                AppOpsManager appOpsManagerC = za.c(context);
                iC = za.a(appOpsManagerC, strD, Binder.getCallingUid(), packageName);
                if (iC == 0) {
                    iC = za.a(appOpsManagerC, strD, iMyUid, za.b(context));
                }
            } else {
                iC = ya.c((AppOpsManager) ya.a(context, AppOpsManager.class), strD, packageName);
            }
            if (iC != 0) {
                return -2;
            }
        }
        return 0;
    }

    public static final double l(int i2, int i3, int i4, int i5, Scale scale) {
        double d2 = ((double) i4) / ((double) i2);
        double d3 = ((double) i5) / ((double) i3);
        int iOrdinal = scale.ordinal();
        if (iOrdinal == 0) {
            return Math.max(d2, d3);
        }
        if (iOrdinal == 1) {
            return Math.min(d2, d3);
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final void m(int i2, int i3) {
        if (i2 <= i3) {
            return;
        }
        throw new IndexOutOfBoundsException("toIndex (" + i2 + ") is greater than size (" + i3 + ").");
    }

    public static hw n(Context context) {
        return new hw(Arrays.asList(new hw(context), new com.spotify.automotive.packagevalidator.b(context)));
    }

    public static final void o(da0 da0Var, os1 os1Var) throws IOException {
        try {
            IOException iOException = null;
            for (os1 os1Var2 : (ArrayList) da0Var.g(os1Var)) {
                try {
                    if (da0Var.h(os1Var2).c) {
                        o(da0Var, os1Var2);
                    }
                    da0Var.d(os1Var2);
                } catch (IOException e2) {
                    if (iOException == null) {
                        iOException = e2;
                    }
                }
            }
            if (iOException != null) {
                throw iOException;
            }
        } catch (FileNotFoundException unused) {
        }
    }

    public static final void p(x62 x62Var) {
        u62 u62Var;
        v03.j(x62Var, "<this>");
        Lifecycle$State lifecycle$StateB = x62Var.l().b();
        if (lifecycle$StateB != Lifecycle$State.e && lifecycle$StateB != Lifecycle$State.k) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        v62 v62VarA = x62Var.a();
        v62VarA.getClass();
        Iterator it = v62VarA.a.iterator();
        while (true) {
            o62 o62Var = (o62) it;
            if (!o62Var.hasNext()) {
                u62Var = null;
                break;
            }
            Map.Entry entry = (Map.Entry) o62Var.next();
            v03.i(entry, "components");
            String str = (String) entry.getKey();
            u62Var = (u62) entry.getValue();
            if (v03.b(str, "androidx.lifecycle.internal.SavedStateHandlesProvider")) {
                break;
            }
        }
        if (u62Var == null) {
            androidx.lifecycle.c cVar = new androidx.lifecycle.c(x62Var.a(), (mu2) x62Var);
            x62Var.a().b("androidx.lifecycle.internal.SavedStateHandlesProvider", cVar);
            x62Var.l().a(new SavedStateHandleAttacher(cVar));
        }
    }

    public static iw2 q(ty tyVar, int i2, ArrayList arrayList, iw2 iw2Var) {
        int i3;
        int i4 = i2 == 0 ? tyVar.n0 : tyVar.o0;
        if (i4 != -1 && (iw2Var == null || i4 != iw2Var.b)) {
            int i5 = 0;
            while (true) {
                if (i5 >= arrayList.size()) {
                    break;
                }
                iw2 iw2Var2 = (iw2) arrayList.get(i5);
                if (iw2Var2.b == i4) {
                    if (iw2Var != null) {
                        iw2Var.c(i2, iw2Var2);
                        arrayList.remove(iw2Var);
                    }
                    iw2Var = iw2Var2;
                } else {
                    i5++;
                }
            }
        } else if (i4 != -1) {
            return iw2Var;
        }
        if (iw2Var == null) {
            if (tyVar instanceof z11) {
                z11 z11Var = (z11) tyVar;
                int i6 = 0;
                while (true) {
                    if (i6 >= z11Var.q0) {
                        i3 = -1;
                        break;
                    }
                    ty tyVar2 = z11Var.p0[i6];
                    if ((i2 == 0 && (i3 = tyVar2.n0) != -1) || (i2 == 1 && (i3 = tyVar2.o0) != -1)) {
                        break;
                    }
                    i6++;
                }
                if (i3 != -1) {
                    int i7 = 0;
                    while (true) {
                        if (i7 >= arrayList.size()) {
                            break;
                        }
                        iw2 iw2Var3 = (iw2) arrayList.get(i7);
                        if (iw2Var3.b == i3) {
                            iw2Var = iw2Var3;
                            break;
                        }
                        i7++;
                    }
                }
            }
            if (iw2Var == null) {
                iw2Var = new iw2();
                iw2Var.a = new ArrayList();
                iw2Var.d = null;
                iw2Var.e = -1;
                int i8 = iw2.f;
                iw2.f = i8 + 1;
                iw2Var.b = i8;
                iw2Var.c = i2;
            }
            arrayList.add(iw2Var);
        }
        ArrayList arrayList2 = iw2Var.a;
        if (!arrayList2.contains(tyVar)) {
            arrayList2.add(tyVar);
            if (tyVar instanceof d11) {
                d11 d11Var = (d11) tyVar;
                d11Var.s0.b(d11Var.t0 == 0 ? 1 : 0, iw2Var, arrayList);
            }
            int i9 = iw2Var.b;
            if (i2 == 0) {
                tyVar.n0 = i9;
                tyVar.H.b(i2, iw2Var, arrayList);
                tyVar.J.b(i2, iw2Var, arrayList);
            } else {
                tyVar.o0 = i9;
                tyVar.I.b(i2, iw2Var, arrayList);
                tyVar.L.b(i2, iw2Var, arrayList);
                tyVar.K.b(i2, iw2Var, arrayList);
            }
            tyVar.O.b(i2, iw2Var, arrayList);
        }
        return iw2Var;
    }

    public static final Uri r(i31 i31Var) {
        List list;
        h31 h31Var = null;
        Object next = null;
        h31Var = null;
        if (i31Var != null && (list = i31Var.a) != null) {
            Iterator it = list.iterator();
            if (it.hasNext()) {
                next = it.next();
                if (it.hasNext()) {
                    Integer num = ((h31) next).b;
                    int iIntValue = num != null ? num.intValue() : 0;
                    do {
                        Object next2 = it.next();
                        Integer num2 = ((h31) next2).b;
                        int iIntValue2 = num2 != null ? num2.intValue() : 0;
                        if (iIntValue < iIntValue2) {
                            next = next2;
                            iIntValue = iIntValue2;
                        }
                    } while (it.hasNext());
                }
            }
            h31Var = (h31) next;
        }
        if (h31Var != null) {
            Uri uri = Uri.parse(h31Var.a);
            v03.i(uri, "{\n        Uri.parse(source.url)\n    }");
            return uri;
        }
        Uri uri2 = Uri.EMPTY;
        v03.i(uri2, "{\n        Uri.EMPTY\n    }");
        return uri2;
    }

    public static Locale s(Context context) {
        return new ac1(new cc1(yw.a(context.getResources().getConfiguration()))).b(0);
    }

    public static Object t(ImmutableSet immutableSet) {
        Iterator<E> it = immutableSet.iterator();
        if (!it.hasNext()) {
            return null;
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return next;
        }
        StringBuilder sb = new StringBuilder("expected one element but was: <");
        sb.append(next);
        for (int i2 = 0; i2 < 4 && it.hasNext(); i2++) {
            sb.append(", ");
            sb.append(it.next());
        }
        if (it.hasNext()) {
            sb.append(", ...");
        }
        sb.append('>');
        throw new IllegalArgumentException(sb.toString());
    }

    public static TextView u(Toolbar toolbar, CharSequence charSequence) {
        for (int i2 = 0; i2 < toolbar.getChildCount(); i2++) {
            View childAt = toolbar.getChildAt(i2);
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                if (TextUtils.equals(textView.getText(), charSequence)) {
                    return textView;
                }
            }
        }
        return null;
    }

    public static SimpleDateFormat v(int i2, int i3) {
        String str;
        String str2;
        StringBuilder sb = new StringBuilder();
        if (i2 == 0) {
            str = "EEEE, MMMM d, yyyy";
        } else if (i2 == 1) {
            str = "MMMM d, yyyy";
        } else if (i2 == 2) {
            str = "MMM d, yyyy";
        } else {
            if (i2 != 3) {
                throw new IllegalArgumentException(u4.e("Unknown DateFormat style: ", i2));
            }
            str = "M/d/yy";
        }
        sb.append(str);
        sb.append(" ");
        if (i3 == 0 || i3 == 1) {
            str2 = "h:mm:ss a z";
        } else if (i3 == 2) {
            str2 = "h:mm:ss a";
        } else {
            if (i3 != 3) {
                throw new IllegalArgumentException(u4.e("Unknown DateFormat style: ", i3));
            }
            str2 = "h:mm a";
        }
        sb.append(str2);
        return new SimpleDateFormat(sb.toString(), Locale.US);
    }

    public static void y(File file, InputStream inputStream) throws Throwable {
        byte[] bArr = new byte[8192];
        GZIPOutputStream gZIPOutputStream = null;
        try {
            GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(new FileOutputStream(file));
            while (true) {
                try {
                    int i2 = inputStream.read(bArr);
                    if (i2 <= 0) {
                        gZIPOutputStream2.finish();
                        zu.r(gZIPOutputStream2);
                        return;
                    }
                    gZIPOutputStream2.write(bArr, 0, i2);
                } catch (Throwable th) {
                    th = th;
                    gZIPOutputStream = gZIPOutputStream2;
                    zu.r(gZIPOutputStream);
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean z(Bundle bundle, Bundle bundle2) {
        int i2;
        int i3;
        int i4;
        int i5 = bundle == null ? -1 : bundle.getInt("android.media.browse.extra.PAGE", -1);
        int i6 = bundle2 == null ? -1 : bundle2.getInt("android.media.browse.extra.PAGE", -1);
        int i7 = bundle == null ? -1 : bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1);
        int i8 = bundle2 == null ? -1 : bundle2.getInt("android.media.browse.extra.PAGE_SIZE", -1);
        int i9 = Integer.MAX_VALUE;
        if (i5 == -1 || i7 == -1) {
            i2 = Integer.MAX_VALUE;
            i3 = 0;
        } else {
            i3 = i5 * i7;
            i2 = (i7 + i3) - 1;
        }
        if (i6 == -1 || i8 == -1) {
            i4 = 0;
        } else {
            i4 = i6 * i8;
            i9 = (i8 + i4) - 1;
        }
        return i2 >= i4 && i9 >= i3;
    }

    public void F(View view, int i2) {
    }

    public abstract void G(int i2);

    public abstract void H(View view, int i2, int i3);

    public abstract void I(View view, float f2, float f3);

    public abstract boolean W(View view, int i2);

    public abstract int j(View view, int i2);

    public abstract int k(View view, int i2);

    public int w(View view) {
        return 0;
    }

    public int x() {
        return 0;
    }
}
