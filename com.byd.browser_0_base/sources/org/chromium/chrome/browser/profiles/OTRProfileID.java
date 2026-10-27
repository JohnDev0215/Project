package org.chromium.chrome.browser.profiles;

import J.N;
import android.text.TextUtils;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class OTRProfileID {
    public static final OTRProfileID b = new OTRProfileID("profile::primary_otr");
    public final String a;

    @CalledByNative
    public OTRProfileID(String str) {
        this.a = str;
    }

    public static OTRProfileID a(String str) {
        OTRProfileID oTRProfileIDDeserializeWithoutVerify = deserializeWithoutVerify(str);
        if (oTRProfileIDDeserializeWithoutVerify != null) {
            Profile profileD = Profile.d();
            if (!N.MQioXkwA(profileD.b, profileD, oTRProfileIDDeserializeWithoutVerify)) {
                throw new IllegalStateException("The OTR profile should exist for otr profile id.");
            }
        }
        return oTRProfileIDDeserializeWithoutVerify;
    }

    @CalledByNative
    public static OTRProfileID deserializeWithoutVerify(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return new OTRProfileID(str.substring(13, str.length() - 1));
    }

    @CalledByNative
    public static String serialize(OTRProfileID oTRProfileID) {
        if (oTRProfileID == null) {
            return null;
        }
        return oTRProfileID.toString();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof OTRProfileID) {
            return this.a.equals(((OTRProfileID) obj).a);
        }
        return false;
    }

    @CalledByNative
    public final String getProfileID() {
        return this.a;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return String.format("OTRProfileID{%s}", this.a);
    }
}
