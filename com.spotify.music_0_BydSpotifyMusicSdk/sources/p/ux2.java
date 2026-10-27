package p;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Parcel;
import android.text.TextUtils;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.protobuf.ByteString;
import com.spotify.contexts.ClientContextId;
import com.spotify.contexts.Time;
import com.spotify.eventsender.Fragment;
import com.spotify.eventsender.FragmentsContainer;
import com.spotify.player.esperanto.proto.EsLoggingParams$LoggingParams;
import com.spotify.player.esperanto.proto.EsOptional$OptionalInt64;
import com.spotify.player.model.command.options.LoggingParams;
import com.spotify.playerlimited.player.models.BitrateSetting;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.internal.subscriptions.EmptySubscription;
import io.reactivex.rxjava3.internal.subscriptions.ScalarSubscription;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.EmptySet;
import kotlin.coroutines.EmptyCoroutineContext;

/* JADX INFO: loaded from: classes.dex */
public abstract class ux2 {
    public static final ke2 a = new ke2(15);
    public static final nv0 b = new nv0(0);
    public static final zb0 c = new zb0();
    public static final u11 d = new u11();
    public static final StackTraceElement[] e = new StackTraceElement[0];
    public static final z90 f = new z90(14);

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean A(Flowable flowable, rg2 rg2Var, io.reactivex.rxjava3.functions.o oVar) {
        EmptySubscription emptySubscription = EmptySubscription.c;
        if (!(flowable instanceof io.reactivex.rxjava3.functions.q)) {
            return false;
        }
        try {
            Object obj = ((io.reactivex.rxjava3.functions.q) flowable).get();
            if (obj == null) {
                rg2Var.onSubscribe(emptySubscription);
                rg2Var.onComplete();
                return true;
            }
            try {
                Object objApply = oVar.apply(obj);
                Objects.requireNonNull(objApply, "The mapper returned a null Publisher");
                az1 az1Var = (az1) objApply;
                if (az1Var instanceof io.reactivex.rxjava3.functions.q) {
                    try {
                        Object obj2 = ((io.reactivex.rxjava3.functions.q) az1Var).get();
                        if (obj2 == null) {
                            rg2Var.onSubscribe(emptySubscription);
                            rg2Var.onComplete();
                            return true;
                        }
                        rg2Var.onSubscribe(new ScalarSubscription(obj2, rg2Var));
                    } catch (Throwable th) {
                        he1.R(th);
                        rg2Var.onSubscribe(emptySubscription);
                        rg2Var.onError(th);
                        return true;
                    }
                } else {
                    az1Var.subscribe(rg2Var);
                }
                return true;
            } catch (Throwable th2) {
                he1.R(th2);
                rg2Var.onSubscribe(emptySubscription);
                rg2Var.onError(th2);
                return true;
            }
        } catch (Throwable th3) {
            he1.R(th3);
            rg2Var.onSubscribe(emptySubscription);
            rg2Var.onError(th3);
            return true;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01b4 A[FALL_THROUGH, PHI: r10
  0x01b4: PHI (r10v2 p.m90) = (r10v0 p.m90), (r10v0 p.m90), (r10v0 p.m90), (r10v0 p.m90), (r10v0 p.m90), (r10v1 p.m90) binds: [B:39:0x00d0, B:90:0x014a, B:92:0x0150, B:94:0x0154, B:128:0x01b1, B:129:0x01b3] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01c0 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x00d4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:260:0x01c7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0159  */
    /* JADX WARN: Type inference failed for: r0v61, types: [byte[], java.io.Serializable] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void B(android.content.Context r16, p.db r17, p.fy1 r18, boolean r19) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 682
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.ux2.B(android.content.Context, p.db, p.fy1, boolean):void");
    }

    public static void C(Parcel parcel, ImmutableMap immutableMap) {
        parcel.writeInt(immutableMap.size());
        Iterator it = immutableMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            entry.getKey().getClass();
            entry.getValue().getClass();
            String str = (String) entry.getKey();
            str.getClass();
            parcel.writeString(str);
            String str2 = (String) entry.getValue();
            str2.getClass();
            parcel.writeString(str2);
        }
    }

    public static void D(Parcel parcel, Set set) {
        parcel.writeInt(set.size());
        Iterator it = set.iterator();
        while (it.hasNext()) {
            parcel.writeString((String) it.next());
        }
    }

    public static final BitrateSetting a(com.spotify.automotive.playerapi.BitrateSetting bitrateSetting) {
        int iOrdinal = bitrateSetting.ordinal();
        if (iOrdinal == 0) {
            return BitrateSetting.AUTO;
        }
        if (iOrdinal == 1) {
            return BitrateSetting.LOW;
        }
        if (iOrdinal == 2) {
            return BitrateSetting.MEDIUM;
        }
        if (iOrdinal == 3) {
            return BitrateSetting.HIGH;
        }
        if (iOrdinal == 4) {
            return BitrateSetting.VERY_HIGH;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final void b(int i, StringBuilder sb) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("?");
            if (i2 < i - 1) {
                sb.append(",");
            }
        }
    }

    public static final void e(Closeable closeable, Throwable th) {
        if (closeable != null) {
            if (th == null) {
                closeable.close();
                return;
            }
            try {
                closeable.close();
            } catch (Throwable th2) {
                kotlin.a.a(th, th2);
            }
        }
    }

    public static final FragmentsContainer f(byte[] bArr, long j, ei0 ei0Var, zb0 zb0Var, String str) {
        v03.j(bArr, "message");
        v03.j(ei0Var, "contextRegistry");
        v03.j(zb0Var, "clientContextIdManager");
        iy0 iy0VarI = FragmentsContainer.i();
        iy0VarI.e(r("message", ByteString.c(bArr, 0, bArr.length)));
        rk2 rk2VarF = Time.f();
        if (j <= 0) {
            j = System.currentTimeMillis();
        }
        rk2VarF.d(j);
        com.google.protobuf.i iVarBuild = rk2VarF.build();
        v03.i(iVarBuild, "newBuilder().setTimestam…entMillis()\n    ).build()");
        ByteString byteString = iVarBuild.toByteString();
        v03.i(byteString, "createContextData(timestamp).toByteString()");
        iy0VarI.e(r("context_time", byteString));
        List<di0> list = ei0Var.a;
        ArrayList arrayList = new ArrayList(lu.W(list));
        for (di0 di0Var : list) {
            String strA = di0Var.a();
            ByteString byteString2 = di0Var.getData().toByteString();
            v03.i(strA, "fragment.first");
            v03.i(byteString2, "fragment.second");
            arrayList.add(r(strA, byteString2));
        }
        iy0VarI.d(arrayList);
        if (str != null) {
            lt ltVarF = ClientContextId.f();
            String lowerCase = str.toLowerCase(Locale.ROOT);
            v03.i(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            ltVarF.d(lowerCase);
            com.google.protobuf.i iVarBuild2 = ltVarF.build();
            v03.i(iVarBuild2, "newBuilder()\n           … \"\")\n            .build()");
            ByteString byteString3 = iVarBuild2.toByteString();
            v03.i(byteString3, "clientContextIdManager.c…ContextId).toByteString()");
            iy0VarI.e(r("context_client_context_id", byteString3));
        }
        com.google.protobuf.i iVarBuild3 = iy0VarI.build();
        v03.i(iVarBuild3, "fragments.build()");
        return (FragmentsContainer) iVarBuild3;
    }

    public static mv g(String str, String str2) {
        ei eiVar = new ei(str, str2);
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashSet hashSet3 = new HashSet();
        hashSet.add(dz1.a(ei.class));
        return new mv(null, new HashSet(hashSet), new HashSet(hashSet2), 0, 1, new kv(1, eiVar), hashSet3);
    }

    public static int h(int i, int i2, RoundingMode roundingMode) {
        roundingMode.getClass();
        if (i2 == 0) {
            throw new ArithmeticException("/ by zero");
        }
        int i3 = i / i2;
        int i4 = i - (i2 * i3);
        if (i4 == 0) {
            return i3;
        }
        int i5 = ((i ^ i2) >> 31) | 1;
        switch (f51.a[roundingMode.ordinal()]) {
            case 1:
                if (i4 == 0) {
                    return i3;
                }
                throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
            case 2:
                return i3;
            case 3:
                if (i5 >= 0) {
                    return i3;
                }
                break;
            case 4:
                break;
            case 5:
                if (i5 <= 0) {
                    return i3;
                }
                break;
            case 6:
            case 7:
            case 8:
                int iAbs = Math.abs(i4);
                int iAbs2 = iAbs - (Math.abs(i2) - iAbs);
                if (iAbs2 == 0) {
                    if (roundingMode != RoundingMode.HALF_UP) {
                        if (!((roundingMode == RoundingMode.HALF_EVEN) & ((i3 & 1) != 0))) {
                            return i3;
                        }
                    }
                } else if (iAbs2 <= 0) {
                    return i3;
                }
            default:
                throw new AssertionError();
        }
        return i3 + i5;
    }

    public static mv j(String str, iy2 iy2Var) {
        int i = 0;
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashSet hashSet3 = new HashSet();
        hashSet.add(dz1.a(ei.class));
        for (Class cls : new Class[0]) {
            if (cls == null) {
                throw new NullPointerException("Null interface");
            }
            hashSet.add(dz1.a(cls));
        }
        d90 d90VarA = d90.a(Context.class);
        if (!(!hashSet.contains(d90VarA.a))) {
            throw new IllegalArgumentException("Components are not allowed to depend on interfaces they themselves provide.");
        }
        hashSet2.add(d90VarA);
        return new mv(null, new HashSet(hashSet), new HashSet(hashSet2), 0, 1, new f91(i, iy2Var, str), hashSet3);
    }

    public static w10 k(w10 w10Var, x10 x10Var) {
        v03.j(x10Var, "key");
        if (v03.b(w10Var.getKey(), x10Var)) {
            return w10Var;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x0188  */
    /* JADX WARN: Type inference failed for: r1v22, types: [java.io.Serializable, p.cw0[]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static p.j6 l(android.content.Context r19, p.pv0 r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 462
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.ux2.l(android.content.Context, p.pv0):p.j6");
    }

    public static final void m(y10 y10Var, Throwable th) {
        try {
            a20 a20Var = (a20) y10Var.E0(zb0.k);
            if (a20Var != null) {
                a20Var.q(y10Var, th);
            } else {
                zu.k0(y10Var, th);
            }
        } catch (Throwable th2) {
            if (th != th2) {
                RuntimeException runtimeException = new RuntimeException("Exception while trying to handle coroutine exception", th2);
                kotlin.a.a(runtimeException, th);
                th = runtimeException;
            }
            zu.k0(y10Var, th);
        }
    }

    public static int o(int i, RoundingMode roundingMode) {
        if (i <= 0) {
            throw new IllegalArgumentException(u4.f("x (", i, ") must be > 0"));
        }
        switch (f51.a[roundingMode.ordinal()]) {
            case 1:
                if (!((i > 0) & (((i + (-1)) & i) == 0))) {
                    throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
                }
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                return 32 - Integer.numberOfLeadingZeros(i - 1);
            case 6:
            case 7:
            case 8:
                int iNumberOfLeadingZeros = Integer.numberOfLeadingZeros(i);
                return (31 - iNumberOfLeadingZeros) + ((~(~(((-1257966797) >>> iNumberOfLeadingZeros) - i))) >>> 31);
            default:
                throw new AssertionError();
        }
        return 31 - Integer.numberOfLeadingZeros(i);
    }

    public static final EsLoggingParams$LoggingParams p(LoggingParams loggingParams) {
        ag0 ag0VarH = EsLoggingParams$LoggingParams.h();
        if (loggingParams.b().d()) {
            cg0 cg0VarH = EsOptional$OptionalInt64.h();
            Object objC = loggingParams.b().c();
            v03.i(objC, "loggingParams.commandInitiatedTime().get()");
            cg0VarH.d(((Number) objC).longValue());
            ag0VarH.f((EsOptional$OptionalInt64) cg0VarH.build());
        }
        if (Optional.b(he1.t(loggingParams.e())).d()) {
            ag0VarH.e((String) Optional.b(he1.t(loggingParams.e())).c());
        }
        if (Optional.b(he1.t(loggingParams.d())).d()) {
            ag0VarH.d((String) Optional.b(he1.t(loggingParams.d())).c());
        }
        com.google.protobuf.i iVarBuild = ag0VarH.build();
        v03.i(iVarBuild, "newBuilder().apply {\n   …)\n        }\n    }.build()");
        return (EsLoggingParams$LoggingParams) iVarBuild;
    }

    public static y10 q(w10 w10Var, x10 x10Var) {
        v03.j(x10Var, "key");
        return v03.b(w10Var.getKey(), x10Var) ? EmptyCoroutineContext.c : w10Var;
    }

    public static Fragment r(String str, ByteString byteString) {
        vw0 vw0VarG = Fragment.g();
        vw0VarG.e(str);
        vw0VarG.d(byteString);
        com.google.protobuf.i iVarBuild = vw0VarG.build();
        v03.i(iVarBuild, "newBuilder()\n        .se…ta(data)\n        .build()");
        return (Fragment) iVarBuild;
    }

    public static void s(PackageInfo packageInfo, File file) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(new File(file, "profileinstaller_profileWrittenFor_lastUpdateTime.dat")));
            try {
                dataOutputStream.writeLong(packageInfo.lastUpdateTime);
                dataOutputStream.close();
            } finally {
            }
        } catch (IOException unused) {
        }
    }

    public static void t(Observer observer, AtomicInteger atomicInteger, AtomicThrowable atomicThrowable) {
        if (atomicInteger.getAndIncrement() == 0) {
            atomicThrowable.d(observer);
        }
    }

    public static void u(Observer observer, Throwable th, AtomicInteger atomicInteger, AtomicThrowable atomicThrowable) {
        if (atomicThrowable.a(th) && atomicInteger.getAndIncrement() == 0) {
            atomicThrowable.d(observer);
        }
    }

    public static void v(Observer observer, Object obj, AtomicInteger atomicInteger, AtomicThrowable atomicThrowable) {
        if (atomicInteger.get() == 0 && atomicInteger.compareAndSet(0, 1)) {
            observer.onNext(obj);
            if (atomicInteger.decrementAndGet() != 0) {
                atomicThrowable.d(observer);
            }
        }
    }

    public static boolean w(rg2 rg2Var, Object obj, AtomicInteger atomicInteger, AtomicThrowable atomicThrowable) {
        if (atomicInteger.get() == 0 && atomicInteger.compareAndSet(0, 1)) {
            rg2Var.onNext(obj);
            if (atomicInteger.decrementAndGet() == 0) {
                return true;
            }
            atomicThrowable.e(rg2Var);
        }
        return false;
    }

    public static HashSet x(Parcel parcel) {
        int i = parcel.readInt();
        HashSet hashSet = new HashSet(i);
        for (int i2 = 0; i2 < i; i2++) {
            hashSet.add(parcel.readString());
        }
        return hashSet;
    }

    public static int y(double d2) {
        if (Double.isNaN(d2)) {
            throw new IllegalArgumentException("Cannot round NaN value.");
        }
        if (d2 > 2.147483647E9d) {
            return Integer.MAX_VALUE;
        }
        if (d2 < -2.147483648E9d) {
            return Integer.MIN_VALUE;
        }
        return (int) Math.round(d2);
    }

    public static Set z(Object... objArr) {
        return objArr.length > 0 ? kotlin.collections.b.x0(objArr) : EmptySet.c;
    }

    public abstract void c(n32 n32Var, Object obj);

    public qr1 d() {
        return new qr1(this, 1);
    }

    public void i(mq1 mq1Var) {
        List listSingletonList = Collections.singletonList(mq1Var);
        wx2 wx2Var = (wx2) this;
        if (listSingletonList.isEmpty()) {
            throw new IllegalArgumentException("enqueue needs at least one WorkRequest.");
        }
        hx2 hx2Var = new hx2(wx2Var, listSingletonList);
        if (!hx2Var.j) {
            wx2Var.j.a(new de0(hx2Var));
        } else {
            vc1 vc1VarC = vc1.c();
            TextUtils.join(", ", hx2Var.h);
            vc1VarC.getClass();
        }
    }

    public qr1 n() {
        return new qr1(this, 0);
    }
}
