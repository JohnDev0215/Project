package org.chromium.chrome.browser.browsing_data;

import J.N;
import android.os.Parcel;
import android.os.Parcelable;
import defpackage.oj2;
import java.util.Arrays;
import org.chromium.chrome.browser.browsing_data.BrowsingDataBridge;

/* JADX INFO: loaded from: classes.dex */
public class ClearBrowsingDataFetcher implements BrowsingDataBridge.ImportantSitesCallback, BrowsingDataBridge.OtherFormsOfBrowsingHistoryListener, Parcelable {
    public static final Parcelable.Creator<ClearBrowsingDataFetcher> CREATOR = new a();
    public final int e;
    public String[] f;
    public int[] g;
    public String[] h;
    public boolean i;

    public class a implements Parcelable.Creator<ClearBrowsingDataFetcher> {
        @Override // android.os.Parcelable.Creator
        public final ClearBrowsingDataFetcher createFromParcel(Parcel parcel) {
            return new ClearBrowsingDataFetcher(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final ClearBrowsingDataFetcher[] newArray(int i) {
            return new ClearBrowsingDataFetcher[i];
        }
    }

    public ClearBrowsingDataFetcher() {
        this.e = N.Mz7sCzLM();
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // org.chromium.chrome.browser.browsing_data.BrowsingDataBridge.OtherFormsOfBrowsingHistoryListener
    public final void enableDialogAboutOtherFormsOfBrowsingHistory() {
        this.i = true;
    }

    @Override // org.chromium.chrome.browser.browsing_data.BrowsingDataBridge.ImportantSitesCallback
    public final void onImportantRegisterableDomainsReady(String[] strArr, String[] strArr2, int[] iArr, boolean z) {
        if (strArr == null || z) {
            return;
        }
        int length = strArr.length;
        int i = this.e;
        oj2.W(length, 1, i + 1, i + 1, "History.ClearBrowsingData.NumImportant");
        this.f = (String[]) Arrays.copyOf(strArr, strArr.length);
        this.g = Arrays.copyOf(iArr, iArr.length);
        this.h = (String[]) Arrays.copyOf(strArr2, strArr2.length);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.e);
        parcel.writeStringArray(this.f);
        parcel.writeIntArray(this.g);
        parcel.writeStringArray(this.h);
        parcel.writeByte(this.i ? (byte) 1 : (byte) 0);
    }

    public ClearBrowsingDataFetcher(Parcel parcel) {
        this.e = parcel.readInt();
        this.f = parcel.createStringArray();
        this.g = parcel.createIntArray();
        this.h = parcel.createStringArray();
        this.i = parcel.readByte() != 0;
    }
}
