package defpackage;

import J.N;
import android.os.Bundle;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: renamed from: rD, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1425rD implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        Bundle bundle = parcel.readBundle();
        if (bundle.getLong("version", -1L) != 0) {
            return null;
        }
        if (WebContentsImpl.D.compareTo(((ParcelUuid) bundle.getParcelable("processguard")).getUuid()) != 0) {
            return null;
        }
        return (WebContents) N.M$eaBDjM(bundle.getLong("webcontents"));
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new WebContents[i];
    }
}
