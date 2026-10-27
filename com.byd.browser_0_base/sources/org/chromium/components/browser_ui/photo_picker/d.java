package org.chromium.components.browser_ui.photo_picker;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import defpackage.b12;
import defpackage.gq2;
import defpackage.i9;
import defpackage.sh1;
import defpackage.wt1;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.ThreadUtils;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: FileEnumWorkerTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d extends i9<List<b12>> {
    public final WindowAndroid h;
    public final a i;
    public final sh1 j;
    public final boolean k;
    public final boolean l;
    public final ContentResolver m;

    /* JADX INFO: compiled from: FileEnumWorkerTask.java */
    public interface a {
    }

    public d(WindowAndroid windowAndroid, PickerCategoryView pickerCategoryView, sh1 sh1Var, List list, ContentResolver contentResolver) {
        this.h = windowAndroid;
        this.i = pickerCategoryView;
        this.j = sh1Var;
        this.m = contentResolver;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str.startsWith("image/")) {
                this.k = true;
            } else if (str.startsWith("video/")) {
                this.l = true;
            }
            if (this.k && this.l) {
                return;
            }
        }
    }

    @Override // defpackage.i9
    public final List<b12> b() {
        Object obj = ThreadUtils.a;
        if (!i()) {
            ArrayList arrayList = new ArrayList();
            String[] strArr = {"_id", "date_added", "media_type", "mime_type", "relative_path"};
            boolean z = this.k;
            String strF = z ? "media_type=1" : "";
            if (this.l) {
                if (z) {
                    strF = strF.concat(" OR ");
                }
                strF = gq2.f(strF, "media_type=3");
            }
            String strG = !strF.isEmpty() ? gq2.g("relative_path LIKE ? OR relative_path LIKE ? OR relative_path LIKE ? OR relative_path LIKE ? OR relative_path LIKE ? OR relative_path LIKE ? AND (", strF, ")") : "relative_path LIKE ? OR relative_path LIKE ? OR relative_path LIKE ? OR relative_path LIKE ? OR relative_path LIKE ? OR relative_path LIKE ?";
            StringBuilder sb = new StringBuilder();
            sb.append(Environment.DIRECTORY_DCIM);
            String[] strArr2 = {gq2.f(gq2.i(sb, File.separator, "Camera"), "%"), gq2.f(Environment.DIRECTORY_PICTURES, "%"), gq2.f(Environment.DIRECTORY_MOVIES, "%"), gq2.f(Environment.DIRECTORY_DOWNLOADS, "%"), gq2.f(gq2.i(new StringBuilder(), Environment.DIRECTORY_DCIM, "/Restored"), "%"), gq2.f(gq2.i(new StringBuilder(), Environment.DIRECTORY_DCIM, "/Screenshots"), "%")};
            Uri contentUri = MediaStore.Files.getContentUri("external");
            Cursor cursorQuery = this.m.query(contentUri, strArr, strG, strArr2, "date_added DESC");
            if (cursorQuery != null) {
                Log.i("cr_PhotoPicker", "Found " + cursorQuery.getCount() + " media files, when requesting columns: " + Arrays.toString(strArr) + ", with WHERE " + strG + ", params: " + Arrays.toString(strArr2));
                while (cursorQuery.moveToNext()) {
                    String string = cursorQuery.getString(cursorQuery.getColumnIndex("mime_type"));
                    if (this.j.a(null, string)) {
                        arrayList.add(new b12(ContentUris.withAppendedId(contentUri, cursorQuery.getInt(cursorQuery.getColumnIndex("_id"))), cursorQuery.getLong(cursorQuery.getColumnIndex("date_added")), string.startsWith("video/") ? 3 : 0));
                    }
                }
                cursorQuery.close();
                arrayList.add(0, new b12(null, 0L, 2));
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                WindowAndroid windowAndroid = this.h;
                windowAndroid.getClass();
                wt1.a(intent, 0);
                if (windowAndroid.hasPermission("android.permission.CAMERA")) {
                    return arrayList;
                }
                windowAndroid.canRequestPermission("android.permission.CAMERA");
                return arrayList;
            }
            Log.e("cr_PhotoPicker", "Content Resolver query() returned null");
        }
        return null;
    }

    @Override // defpackage.i9
    public final void j() {
        this.i.getClass();
    }

    @Override // defpackage.i9
    public final void l(List<b12> list) {
        List<b12> list2 = list;
        if (i()) {
            return;
        }
        ((PickerCategoryView) this.i).e(list2);
    }
}
