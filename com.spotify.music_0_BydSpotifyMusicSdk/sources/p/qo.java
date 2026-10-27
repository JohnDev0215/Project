package p;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.spotify.automotive.auth.LoginState;
import com.spotify.automotive.connectivity.ConnectionStateController$ConnectionState;
import com.spotify.base.java.logging.Logger;
import com.spotify.player.model.ErrorType;
import com.spotify.player.model.PlayerError;
import com.spotify.support.assertion.Assertion$Note;
import com.spotify.support.assertion.Assertion$RecoverableAssertionError;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Pair;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class qo implements h00, io.reactivex.rxjava3.functions.p, io.reactivex.rxjava3.functions.c, x10, io.reactivex.rxjava3.functions.o, io.reactivex.rxjava3.functions.h, io.reactivex.rxjava3.functions.g, lj2, l03, d03, sk0, on2, ku2, ma0, kc {
    public static final qo c = new qo();
    public static final qo e = new qo();
    public static final qo k = new qo();
    public static final /* synthetic */ qo l = new qo();
    public static final qo m = new qo();
    public static final qo n = new qo();
    public static final /* synthetic */ qo o = new qo();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final qo f173p = new qo();
    public static final qo q = new qo();
    public static final qo r = new qo();
    public static final /* synthetic */ qo s = new qo();
    public static final /* synthetic */ qo t = new qo();

    public static void l(long j, fo foVar, int i, ArrayList arrayList, int i2, int i3, ArrayList arrayList2) {
        int i4;
        int i5;
        int i6;
        int i7;
        fo foVar2;
        int i8 = i;
        if (i2 >= i3) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        for (int i9 = i2; i9 < i3; i9++) {
            if (((ByteString) arrayList.get(i9)).e() < i8) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
        }
        ByteString byteString = (ByteString) arrayList.get(i2);
        ByteString byteString2 = (ByteString) arrayList.get(i3 - 1);
        int i10 = -1;
        if (i8 == byteString.e()) {
            int iIntValue = ((Number) arrayList2.get(i2)).intValue();
            int i11 = i2 + 1;
            ByteString byteString3 = (ByteString) arrayList.get(i11);
            i4 = i11;
            i5 = iIntValue;
            byteString = byteString3;
        } else {
            i4 = i2;
            i5 = -1;
        }
        if (byteString.k(i8) == byteString2.k(i8)) {
            int iMin = Math.min(byteString.e(), byteString2.e());
            int i12 = 0;
            for (int i13 = i8; i13 < iMin && byteString.k(i13) == byteString2.k(i13); i13++) {
                i12++;
            }
            long j2 = 4;
            long j3 = (foVar.e / j2) + j + ((long) 2) + ((long) i12) + 1;
            foVar.Z0(-i12);
            foVar.Z0(i5);
            int i14 = i12 + i8;
            while (i8 < i14) {
                foVar.Z0(byteString.k(i8) & 255);
                i8++;
            }
            if (i4 + 1 == i3) {
                if (i14 != ((ByteString) arrayList.get(i4)).e()) {
                    throw new IllegalStateException("Check failed.".toString());
                }
                foVar.Z0(((Number) arrayList2.get(i4)).intValue());
                return;
            } else {
                fo foVar3 = new fo();
                foVar.Z0(((int) ((foVar3.e / j2) + j3)) * (-1));
                l(j3, foVar3, i14, arrayList, i4, i3, arrayList2);
                foVar.L(foVar3);
                return;
            }
        }
        int i15 = 1;
        for (int i16 = i4 + 1; i16 < i3; i16++) {
            if (((ByteString) arrayList.get(i16 - 1)).k(i8) != ((ByteString) arrayList.get(i16)).k(i8)) {
                i15++;
            }
        }
        long j4 = 4;
        long j5 = (foVar.e / j4) + j + ((long) 2) + ((long) (i15 * 2));
        foVar.Z0(i15);
        foVar.Z0(i5);
        for (int i17 = i4; i17 < i3; i17++) {
            int iK = ((ByteString) arrayList.get(i17)).k(i8);
            if (i17 == i4 || iK != ((ByteString) arrayList.get(i17 - 1)).k(i8)) {
                foVar.Z0(iK & 255);
            }
        }
        fo foVar4 = new fo();
        int i18 = i4;
        while (i18 < i3) {
            byte bK = ((ByteString) arrayList.get(i18)).k(i8);
            int i19 = i18 + 1;
            int i20 = i19;
            while (true) {
                if (i20 >= i3) {
                    i6 = i3;
                    break;
                } else {
                    if (bK != ((ByteString) arrayList.get(i20)).k(i8)) {
                        i6 = i20;
                        break;
                    }
                    i20++;
                }
            }
            if (i19 == i6 && i8 + 1 == ((ByteString) arrayList.get(i18)).e()) {
                foVar.Z0(((Number) arrayList2.get(i18)).intValue());
                i7 = i6;
                foVar2 = foVar4;
            } else {
                foVar.Z0(((int) ((foVar4.e / j4) + j5)) * i10);
                i7 = i6;
                foVar2 = foVar4;
                l(j5, foVar4, i8 + 1, arrayList, i18, i6, arrayList2);
            }
            foVar4 = foVar2;
            i18 = i7;
            i10 = -1;
        }
        foVar.L(foVar4);
    }

    public static hp2 m() {
        hp2 hp2Var = new hp2();
        hp2Var.f = "";
        hp2Var.g = "";
        hp2Var.i = new ArrayList();
        hp2Var.k = "";
        hp2Var.l = "";
        return hp2Var;
    }

    public static ByteString n(String str) {
        int i;
        char cCharAt;
        v03.j(str, "<this>");
        byte[] bArr = c.a;
        int length = str.length();
        while (length > 0 && ((cCharAt = str.charAt(length - 1)) == '=' || cCharAt == '\n' || cCharAt == '\r' || cCharAt == ' ' || cCharAt == '\t')) {
            length--;
        }
        int i2 = (int) ((((long) length) * 6) / 8);
        byte[] bArrCopyOf = new byte[i2];
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (true) {
            if (i3 < length) {
                char cCharAt2 = str.charAt(i3);
                if ('A' <= cCharAt2 && cCharAt2 < '[') {
                    i = cCharAt2 - 'A';
                } else if ('a' <= cCharAt2 && cCharAt2 < '{') {
                    i = cCharAt2 - 'G';
                } else if ('0' <= cCharAt2 && cCharAt2 < ':') {
                    i = cCharAt2 + 4;
                } else if (cCharAt2 != '+' && cCharAt2 != '-') {
                    if (cCharAt2 != '/' && cCharAt2 != '_') {
                        if (cCharAt2 != '\n' && cCharAt2 != '\r' && cCharAt2 != ' ' && cCharAt2 != '\t') {
                            break;
                        }
                        i3++;
                    } else {
                        i = 63;
                    }
                } else {
                    i = 62;
                }
                i5 = (i5 << 6) | i;
                i4++;
                if (i4 % 4 == 0) {
                    bArrCopyOf[i6] = (byte) (i5 >> 16);
                    int i7 = i6 + 2;
                    bArrCopyOf[i6 + 1] = (byte) (i5 >> 8);
                    i6 += 3;
                    bArrCopyOf[i7] = (byte) i5;
                }
                i3++;
            } else {
                int i8 = i4 % 4;
                if (i8 != 1) {
                    if (i8 == 2) {
                        bArrCopyOf[i6] = (byte) ((i5 << 12) >> 16);
                        i6++;
                    } else if (i8 == 3) {
                        int i9 = i5 << 6;
                        int i10 = i6 + 1;
                        bArrCopyOf[i6] = (byte) (i9 >> 16);
                        i6 += 2;
                        bArrCopyOf[i10] = (byte) (i9 >> 8);
                    }
                    if (i6 != i2) {
                        bArrCopyOf = Arrays.copyOf(bArrCopyOf, i6);
                        v03.i(bArrCopyOf, "copyOf(...)");
                    }
                }
            }
        }
        bArrCopyOf = null;
        if (bArrCopyOf != null) {
            return new ByteString(bArrCopyOf);
        }
        return null;
    }

    public static ByteString o(String str) {
        if (str.length() % 2 != 0) {
            throw new IllegalArgumentException("Unexpected hex string: ".concat(str).toString());
        }
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (zu.g(str.charAt(i2 + 1)) + (zu.g(str.charAt(i2)) << 4));
        }
        return new ByteString(bArr);
    }

    public static ByteString p(String str) {
        v03.j(str, "<this>");
        byte[] bytes = str.getBytes(cs.a);
        v03.i(bytes, "getBytes(...)");
        ByteString byteString = new ByteString(bytes);
        byteString.e = str;
        return byteString;
    }

    public static void q(String str) {
        FirebaseCrashlytics firebaseCrashlytics = FirebaseCrashlytics.getInstance();
        v03.i(firebaseCrashlytics, "getInstance()");
        firebaseCrashlytics.log(str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:79:0x0140, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static p.ar1 r(okio.ByteString... r14) {
        /*
            Method dump skipped, instruction units count: 393
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.qo.r(okio.ByteString[]):p.ar1");
    }

    public static ByteString s(byte[] bArr) {
        ByteString byteString = ByteString.k;
        int length = bArr.length;
        q30.i(bArr.length, 0, length);
        return new ByteString(rb.m0(bArr, 0, length));
    }

    @Override // p.ku2
    public iu2 a(Class cls) {
        return new qx0(true);
    }

    @Override // io.reactivex.rxjava3.functions.g
    public void accept(Object obj) {
        Logger.c((Throwable) obj, "Error subscribing to product state.", new Object[0]);
    }

    @Override // io.reactivex.rxjava3.functions.o
    public Object apply(Object obj) {
        ju1 ju1Var = (ju1) obj;
        return new Pair(Boolean.valueOf(ju1Var.a.j()), Boolean.valueOf(ju1Var.a.n().e()));
    }

    @Override // p.kc
    public void c(Throwable th) throws Throwable {
        th.getMessage();
        throw th;
    }

    @Override // p.h00
    public Object convert(Object obj) {
        return obj.toString();
    }

    @Override // p.kc
    public void d(Assertion$Note assertion$Note) {
        Logger.c(assertion$Note, "", new Object[0]);
    }

    @Override // p.l03
    public int e(int i) {
        return i;
    }

    @Override // p.kc
    public void f(AssertionError assertionError) {
        Logger.c(assertionError, "", new Object[0]);
        throw assertionError;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0020  */
    @Override // p.lj2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int g(java.lang.CharSequence r6, int r7) {
        /*
            r5 = this;
            r0 = 0
            r1 = 2
            r2 = r0
            r3 = r1
        L4:
            if (r2 >= r7) goto L24
            if (r3 != r1) goto L24
            char r3 = r6.charAt(r2)
            byte r3 = java.lang.Character.getDirectionality(r3)
            p.nj2 r4 = p.oj2.a
            r4 = 1
            if (r3 == 0) goto L20
            if (r3 == r4) goto L1e
            if (r3 == r1) goto L1e
            switch(r3) {
                case 14: goto L20;
                case 15: goto L20;
                case 16: goto L1e;
                case 17: goto L1e;
                default: goto L1c;
            }
        L1c:
            r3 = r1
            goto L21
        L1e:
            r3 = r0
            goto L21
        L20:
            r3 = r4
        L21:
            int r2 = r2 + 1
            goto L4
        L24:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p.qo.g(java.lang.CharSequence, int):int");
    }

    @Override // p.kc
    public void h(Assertion$RecoverableAssertionError assertion$RecoverableAssertionError) {
        Logger.c(assertion$RecoverableAssertionError, "", new Object[0]);
        throw assertion$RecoverableAssertionError;
    }

    @Override // p.on2
    public Bitmap i(Bitmap bitmap, Integer num, Integer num2) {
        v03.j(bitmap, "input");
        int iMin = Math.min(bitmap.getWidth(), bitmap.getHeight());
        float f = iMin / 2.0f;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iMin, iMin, Bitmap.Config.ARGB_8888);
        v03.i(bitmapCreateBitmap, "createBitmap(minSize, mi… Bitmap.Config.ARGB_8888)");
        Paint paint = new Paint(1);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        paint.setShader(new BitmapShader(bitmap, tileMode, tileMode));
        new Canvas(bitmapCreateBitmap).drawCircle(f, f, f, paint);
        if (!v03.b(bitmap, bitmapCreateBitmap)) {
            bitmap.recycle();
        }
        return bitmapCreateBitmap;
    }

    @Override // p.on2
    public String j() {
        return "circle";
    }

    @Override // io.reactivex.rxjava3.functions.c
    public Object k(Object obj, Object obj2) {
        return new Pair((ConnectionStateController$ConnectionState) obj, (LoginState) obj2);
    }

    @Override // io.reactivex.rxjava3.functions.p
    public boolean test(Object obj) {
        PlayerError playerError = (PlayerError) obj;
        v03.j(playerError, "error");
        return playerError.c() == ErrorType.R;
    }

    @Override // io.reactivex.rxjava3.functions.h
    public Object apply(Object obj, Object obj2, Object obj3) {
        return (Boolean) obj;
    }
}
