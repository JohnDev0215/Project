package com.amazon.identity.auth.device;

import android.content.ContentProviderClient;
import android.database.Cursor;
import com.amazon.device.information.contract.DeviceInformationContract;
import com.amazon.identity.auth.device.framework.RemoteMAPException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class l3 implements v4<String> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f417a;

    public l3(String str) {
        this.f417a = str;
    }

    @Override // com.amazon.identity.auth.device.v4
    public String a(ContentProviderClient contentProviderClient) throws Exception {
        String[] strArr = {this.f417a};
        Cursor cursorQuery = contentProviderClient.query(DeviceInformationContract.DeviceInfo.CONTENT_URI, strArr, null, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    int columnIndex = cursorQuery.getColumnIndex(strArr[0]);
                    String string = columnIndex == -1 ? null : cursorQuery.getString(columnIndex);
                    cursorQuery.close();
                    return string;
                }
            } catch (Throwable th) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                throw th;
            }
        }
        throw new RemoteMAPException("Null or empty result returned from Amazon Device Information Provider.");
    }
}
