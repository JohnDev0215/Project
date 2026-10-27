package org.chromium.chrome.browser.cookies;

import J.N;
import android.util.Log;
import defpackage.ac;
import defpackage.ct;
import defpackage.h00;
import defpackage.i9;
import defpackage.xz;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: loaded from: classes.dex */
public class CookiesFetcher {

    public class a extends ac<Void> {
        public final /* synthetic */ CanonicalCookie[] h;

        public a(CanonicalCookie[] canonicalCookieArr) {
            this.h = canonicalCookieArr;
        }

        @Override // defpackage.i9
        public final Object b() {
            DataOutputStream dataOutputStream;
            CanonicalCookie[] canonicalCookieArr = this.h;
            try {
                try {
                    Cipher cipherA = ct.c.a.a(1);
                    if (cipherA != null) {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        dataOutputStream = new DataOutputStream(new CipherOutputStream(byteArrayOutputStream, cipherA));
                        try {
                            CanonicalCookie.b(dataOutputStream, canonicalCookieArr);
                            dataOutputStream.close();
                            N.MsOKBrZ5(CookiesFetcher.b(), byteArrayOutputStream.toByteArray());
                        } catch (IOException unused) {
                            Log.w("cr_CookiesFetcher", "IOException during Cookie Fetch");
                            if (dataOutputStream != null) {
                                try {
                                    dataOutputStream.close();
                                } catch (IOException unused2) {
                                    Log.w("cr_CookiesFetcher", "IOException during Cookie Fetch");
                                }
                            }
                        } catch (Throwable th) {
                            th = th;
                            Log.w("cr_CookiesFetcher", "Error storing cookies.", th);
                            if (dataOutputStream != null) {
                                dataOutputStream.close();
                            }
                        }
                    }
                } catch (Throwable th2) {
                    if (dataOutputStream != null) {
                        try {
                            dataOutputStream.close();
                        } catch (IOException unused3) {
                            Log.w("cr_CookiesFetcher", "IOException during Cookie Fetch");
                        }
                    }
                    throw th2;
                }
            } catch (IOException unused4) {
                dataOutputStream = null;
            } catch (Throwable th3) {
                th = th3;
                dataOutputStream = null;
            }
            return null;
        }
    }

    public static boolean a() {
        try {
            Profile profileD = Profile.d();
            if (N.MrGvO7pv(profileD.b, profileD)) {
                return false;
            }
            new h00().c(i9.f);
            return true;
        } catch (RuntimeException e) {
            Log.e("CookiesFetcher", "deleteCookiesIfNecessary: " + e);
            return false;
        }
    }

    public static String b() {
        return xz.a.getFileStreamPath("COOKIES.DAT").getAbsolutePath();
    }

    @CalledByNative
    public static CanonicalCookie createCookie(String str, String str2, String str3, String str4, long j, long j2, long j3, long j4, boolean z, boolean z2, int i, int i2, boolean z3, String str5, int i3, int i4) {
        return new CanonicalCookie(str, str2, str3, str4, j, j2, j3, j4, z, z2, i, i2, z3, str5, i3, i4);
    }

    @CalledByNative
    public static CanonicalCookie[] createCookiesArray(int i) {
        return new CanonicalCookie[i];
    }

    @CalledByNative
    public static void onCookieFetchFinished(CanonicalCookie[] canonicalCookieArr) {
        new a(canonicalCookieArr).c(i9.f);
    }
}
