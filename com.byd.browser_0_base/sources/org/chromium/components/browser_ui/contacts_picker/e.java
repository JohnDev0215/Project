package org.chromium.components.browser_ui.contacts_picker;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import defpackage.i9;
import defpackage.ue;
import java.io.ByteArrayInputStream;
import java.util.HashSet;
import org.chromium.components.browser_ui.contacts_picker.PickerCategoryView;

/* JADX INFO: compiled from: FetchIconWorkerTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e extends i9<Bitmap> {
    public final String h;
    public int i;
    public final ContentResolver j;
    public final a k;

    /* JADX INFO: compiled from: FetchIconWorkerTask.java */
    public interface a {
    }

    public e(String str, ContentResolver contentResolver, a aVar) {
        this.h = str;
        this.j = contentResolver;
        this.k = aVar;
    }

    @Override // defpackage.i9
    public final void l(Bitmap bitmap) {
        Bitmap bitmap2 = bitmap;
        if (i()) {
            return;
        }
        b bVar = (b) this.k;
        PickerCategoryView pickerCategoryView = bVar.y;
        ue ueVar = pickerCategoryView.getIconCache().a;
        String str = this.h;
        if (ueVar.a(str) == null) {
            PickerCategoryView.a iconCache = pickerCategoryView.getIconCache();
            HashSet hashSet = iconCache.b;
            if (bitmap2 == null) {
                hashSet.add(str);
            } else {
                iconCache.a.c(bitmap2, str);
                hashSet.remove(str);
            }
        }
        if (bitmap2 == null || !str.equals(bVar.B.e)) {
            return;
        }
        bVar.A.setIconBitmap(bitmap2);
    }

    @Override // defpackage.i9
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public final Bitmap b() {
        byte[] blob;
        if (i()) {
            return null;
        }
        Cursor cursorQuery = this.j.query(Uri.withAppendedPath(ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.parseLong(this.h)), "photo"), new String[]{"data15"}, null, null, null);
        if (cursorQuery == null) {
            return null;
        }
        try {
            if (!cursorQuery.moveToFirst() || (blob = cursorQuery.getBlob(0)) == null) {
                return null;
            }
            Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(new ByteArrayInputStream(blob));
            int i = this.i;
            if (i > 0) {
                bitmapDecodeStream = Bitmap.createScaledBitmap(bitmapDecodeStream, i, i, true);
            }
            return bitmapDecodeStream;
        } finally {
            cursorQuery.close();
        }
    }
}
