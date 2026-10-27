package com.byd.database.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.byd.info.CallLogInfo;
import com.byd.info.ContactsInfo;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: SQLiteDB.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static b f3538a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private SQLiteDatabase f3539b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f3540c;

    private b(Context context) {
        this.f3540c = context;
        this.f3539b = new a(context).getWritableDatabase();
    }

    public static synchronized b d(Context context) {
        if (f3538a == null) {
            f3538a = new b(context);
        }
        return f3538a;
    }

    private String e(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" (");
        for (int i = 0; i < str.length(); i++) {
            if (i == 0) {
                stringBuffer.append(str2 + " like '%" + str.charAt(i) + "%");
            } else {
                stringBuffer.append(str.charAt(i) + "%");
            }
        }
        stringBuffer.append("' )");
        return stringBuffer.toString();
    }

    private CallLogInfo j(String str) {
        Cursor cursorRawQuery = this.f3539b.rawQuery(str, null);
        CallLogInfo callLogInfo = new CallLogInfo();
        if (cursorRawQuery.moveToFirst()) {
            do {
                callLogInfo.w(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("name")));
                callLogInfo.x(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("number")));
                callLogInfo.v(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("mac")));
                callLogInfo.A(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("type")));
                callLogInfo.z(cursorRawQuery.getLong(cursorRawQuery.getColumnIndex("time")));
            } while (cursorRawQuery.moveToNext());
        }
        cursorRawQuery.close();
        return callLogInfo;
    }

    private List<CallLogInfo> k(String str) {
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = this.f3539b.rawQuery(str, null);
        if (cursorRawQuery.moveToFirst()) {
            do {
                CallLogInfo callLogInfo = new CallLogInfo();
                callLogInfo.w(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("name")));
                callLogInfo.x(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("number")));
                callLogInfo.v(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("mac")));
                callLogInfo.A(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("type")));
                callLogInfo.z(cursorRawQuery.getLong(cursorRawQuery.getColumnIndex("time")));
                arrayList.add(callLogInfo);
            } while (cursorRawQuery.moveToNext());
        }
        cursorRawQuery.close();
        return arrayList;
    }

    public CallLogInfo a(String str, String str2) {
        return j("select * from call_log_book where mac = '" + str2 + "' and number = '" + str + "' order by time desc limit 0, 1");
    }

    public void b(String str) {
        this.f3539b.delete("phone_book", "mac=?", new String[]{str});
        this.f3539b.delete("call_log_book", "mac=?", new String[]{str});
    }

    public void c(String str) {
        this.f3539b.delete("phone_book", "mac=?", new String[]{str});
        this.f3539b.delete("call_log_book", "mac=?", new String[]{str});
    }

    public void f() {
    }

    public void g(String str, String str2, String str3, String str4, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", str);
        contentValues.put("number", str2);
        contentValues.put("mac", str3);
        contentValues.put("type", str4);
        contentValues.put("time", Long.valueOf(j));
        this.f3539b.insert("call_log_book", null, contentValues);
    }

    public void h(List<CallLogInfo> list) {
        this.f3539b.beginTransaction();
        for (int i = 0; i < list.size(); i++) {
            try {
                try {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("name", list.get(i).n());
                    contentValues.put("number", list.get(i).o());
                    contentValues.put("mac", list.get(i).m());
                    contentValues.put("type", list.get(i).r());
                    contentValues.put("time", Long.valueOf(list.get(i).q()));
                    this.f3539b.insert("call_log_book", null, contentValues);
                } catch (Exception e2) {
                    Log.d("DANDAN", "e = " + e2.getMessage());
                }
            } finally {
                this.f3539b.endTransaction();
            }
        }
        this.f3539b.setTransactionSuccessful();
    }

    public void i(List<ContactsInfo> list) {
        this.f3539b.beginTransaction();
        for (int i = 0; i < list.size(); i++) {
            try {
                try {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("name", list.get(i).j());
                    contentValues.put("number", list.get(i).k());
                    contentValues.put("pinyin", list.get(i).l());
                    contentValues.put("katakana", list.get(i).f());
                    contentValues.put("image_id", list.get(i).c() + "");
                    contentValues.put("firstletter", list.get(i).m());
                    contentValues.put("firstword", list.get(i).a());
                    contentValues.put("mac", list.get(i).i());
                    this.f3539b.insert("phone_book", null, contentValues);
                } catch (Exception e2) {
                    Log.d("DANDAN", "e = " + e2.getMessage());
                }
            } finally {
                this.f3539b.endTransaction();
            }
        }
        this.f3539b.setTransactionSuccessful();
    }

    public List<CallLogInfo> l(String str) {
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = this.f3539b.rawQuery("select * from call_log_book where mac = '" + str + "' order by time desc", null);
        if (cursorRawQuery.moveToFirst()) {
            do {
                CallLogInfo callLogInfo = new CallLogInfo();
                callLogInfo.w(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("name")));
                callLogInfo.x(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("number")));
                callLogInfo.v(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("mac")));
                callLogInfo.A(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("type")));
                callLogInfo.z(cursorRawQuery.getLong(cursorRawQuery.getColumnIndex("time")));
                arrayList.add(callLogInfo);
            } while (cursorRawQuery.moveToNext());
        }
        cursorRawQuery.close();
        return arrayList;
    }

    public List<ContactsInfo> m(String str) {
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = this.f3539b.rawQuery("select * from phone_book where mac = '" + str + "' order by firstletter asc", null);
        if (cursorRawQuery.moveToFirst()) {
            do {
                ContactsInfo contactsInfo = new ContactsInfo();
                contactsInfo.r(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("name")));
                contactsInfo.s(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("number")));
                contactsInfo.t(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("pinyin")));
                contactsInfo.o(Integer.valueOf(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("image_id"))).intValue());
                contactsInfo.u(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("firstletter")));
                contactsInfo.n(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("firstword")));
                contactsInfo.q(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("mac")));
                arrayList.add(contactsInfo);
            } while (cursorRawQuery.moveToNext());
        }
        cursorRawQuery.close();
        return arrayList;
    }

    public CallLogInfo n(String str) {
        return j("select * from call_log_book where mac = '" + str + "' order by time desc limit 0, 1");
    }

    public List<ContactsInfo> o(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("SELECT * FROM phone_book");
        stringBuffer.append(" where mac = '" + str2 + "' and (");
        if (str.length() > 1) {
            stringBuffer.append(e(str, "number"));
            stringBuffer.append(" or " + e(str, "name"));
            stringBuffer.append(" or " + e(str, "pinyin"));
        } else {
            stringBuffer.append("name like '%" + str + "%'");
            stringBuffer.append(" or pinyin like '%" + str + "%'");
            stringBuffer.append(" or firstletter like '%" + str + "%'");
            stringBuffer.append(" or number like '%" + str + "%'");
        }
        stringBuffer.append(") order by firstletter asc");
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = this.f3539b.rawQuery(stringBuffer.toString(), null);
        if (cursorRawQuery.moveToFirst()) {
            do {
                ContactsInfo contactsInfo = new ContactsInfo();
                contactsInfo.r(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("name")));
                contactsInfo.s(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("number")));
                contactsInfo.t(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("pinyin")));
                contactsInfo.o(Integer.valueOf(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("image_id"))).intValue());
                contactsInfo.u(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("firstletter")));
                contactsInfo.n(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("firstword")));
                contactsInfo.q(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("mac")));
                arrayList.add(contactsInfo);
            } while (cursorRawQuery.moveToNext());
        }
        cursorRawQuery.close();
        return arrayList;
    }

    public List<ContactsInfo> p(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("SELECT * FROM phone_book");
        stringBuffer.append(" where mac = '" + str2 + "' and (");
        if (str.length() > 1) {
            stringBuffer.append(e(str, "number"));
        } else {
            stringBuffer.append("name like '%" + str + "%'");
            stringBuffer.append(" or number like '%" + str + "%'");
        }
        stringBuffer.append(") order by firstletter asc");
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = this.f3539b.rawQuery(stringBuffer.toString(), null);
        if (cursorRawQuery.moveToFirst()) {
            do {
                ContactsInfo contactsInfo = new ContactsInfo();
                contactsInfo.r(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("name")));
                contactsInfo.s(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("number")));
                contactsInfo.t(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("pinyin")));
                contactsInfo.o(Integer.valueOf(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("image_id"))).intValue());
                contactsInfo.u(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("firstletter")));
                contactsInfo.n(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("firstword")));
                contactsInfo.q(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("mac")));
                arrayList.add(contactsInfo);
            } while (cursorRawQuery.moveToNext());
        }
        cursorRawQuery.close();
        return arrayList;
    }

    public List<CallLogInfo> q(String str) {
        return k("select * from call_log_book where type = 'DIALED' and mac = '" + str + "' order by time desc");
    }

    public List<String> r() {
        Cursor cursorRawQuery = this.f3539b.rawQuery("select * from phone_book group by mac order by _id asc", null);
        ArrayList arrayList = new ArrayList();
        if (cursorRawQuery.moveToFirst()) {
            do {
                arrayList.add(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("mac")));
            } while (cursorRawQuery.moveToNext());
        }
        cursorRawQuery.close();
        return arrayList;
    }

    public List<CallLogInfo> s(String str) {
        return k("select * from call_log_book where type = 'MISSED' and mac = '" + str + "' order by time desc");
    }

    public List<CallLogInfo> t(String str) {
        return k("select * from call_log_book where type = 'RECEIVED' and mac = '" + str + "' order by time desc");
    }

    public List<CallLogInfo> u(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = this.f3539b.rawQuery("select * from call_log_book where mac = '" + str2 + "' and number = '" + str + "' order by time desc", null);
        if (cursorRawQuery.moveToFirst()) {
            do {
                CallLogInfo callLogInfo = new CallLogInfo();
                callLogInfo.w(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("name")));
                callLogInfo.x(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("number")));
                callLogInfo.v(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("mac")));
                callLogInfo.A(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("type")));
                callLogInfo.z(cursorRawQuery.getLong(cursorRawQuery.getColumnIndex("time")));
                arrayList.add(callLogInfo);
            } while (cursorRawQuery.moveToNext());
        }
        cursorRawQuery.close();
        return arrayList;
    }

    public List<ContactsInfo> v(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        Cursor cursorRawQuery = this.f3539b.rawQuery("select * from phone_book where mac = '" + str2 + "' and number like '%" + str + "%' order by firstletter asc", null);
        if (cursorRawQuery.moveToFirst()) {
            do {
                ContactsInfo contactsInfo = new ContactsInfo();
                contactsInfo.r(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("name")));
                contactsInfo.s(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("number")));
                contactsInfo.t(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("pinyin")));
                contactsInfo.o(Integer.valueOf(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("image_id"))).intValue());
                contactsInfo.u(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("firstletter")));
                contactsInfo.n(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("firstword")));
                contactsInfo.q(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("mac")));
                arrayList.add(contactsInfo);
            } while (cursorRawQuery.moveToNext());
        }
        cursorRawQuery.close();
        return arrayList;
    }
}
