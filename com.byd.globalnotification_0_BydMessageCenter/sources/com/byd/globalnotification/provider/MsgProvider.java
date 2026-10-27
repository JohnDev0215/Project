package com.byd.globalnotification.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import b.a.a.a.a;
import b.c.a.f.t;
import b.c.a.h.b;
import com.byd.globalnotification.notification.NotifyMsgBean;

/* JADX INFO: loaded from: classes.dex */
public class MsgProvider extends ContentProvider {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String[] f2344a = {"hasUnreadMsg"};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final UriMatcher f2345b;

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        f2345b = uriMatcher;
        uriMatcher.addURI("com.byd.globalnotification.msgProvider", "account_msg", 1);
        f2345b.addURI("com.byd.globalnotification.msgProvider", "driver_pos_msg", 2);
        f2345b.addURI("com.byd.globalnotification.msgProvider", "account_big_data", 3);
    }

    public static void a(Context context, String str) {
        Uri uri = Uri.parse("content://com.byd.globalnotification.msgProvider/account_msg");
        b.b(" AccountProvider --> notifyChange:where=" + str);
        context.getContentResolver().notifyChange(uri, null);
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return uri;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r4v6 */
    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        if (f2345b.match(uri) != 1) {
            return null;
        }
        b.b(" AccountProvider --> query:account msg");
        MatrixCursor matrixCursor = new MatrixCursor(f2344a);
        Object[] objArr = new Object[1];
        ?? r4 = t.b.f1752a.f1750b.size() > 0 ? 1 : 0;
        if (t.b.f1752a.f1750b.size() == 1) {
            NotifyMsgBean notifyMsgBean = t.b.f1752a.f1750b.get(0);
            StringBuilder sbD = a.d("notifyMsgSize = 1 notify = ");
            sbD.append(notifyMsgBean.getKey());
            sbD.append(notifyMsgBean.getPkgName());
            b.b(sbD.toString());
        }
        b.b("AccountProvider --> hasUnReadMsg==" + ((boolean) r4) + "--notifyMsgSize=" + t.b.f1752a.f1750b.size());
        objArr[0] = Integer.valueOf((int) r4);
        matrixCursor.addRow(objArr);
        return matrixCursor;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
