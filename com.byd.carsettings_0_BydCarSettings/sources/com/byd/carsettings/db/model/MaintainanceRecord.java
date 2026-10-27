package com.byd.carsettings.db.model;

import androidx.core.app.NotificationCompat;
import b.b.h.f.f.a;
import b.b.h.f.f.b;

/* JADX INFO: loaded from: classes2.dex */
@b(name = "MaintainanceRecord")
public class MaintainanceRecord extends BaseDBModel {

    @a(name = "date")
    private long date;

    @a(name = "double0")
    private double double0;

    @a(name = "double1")
    private double double1;

    @a(name = "hevMileage")
    private int hevMileage;

    @a(isId = true, name = "id")
    private long id;

    @a(name = "int0")
    private int int0;

    @a(name = "int1")
    private int int1;

    @a(name = "string0")
    private String string0;

    @a(name = "string1")
    private String string1;

    @a(name = "totalMileage")
    private int totalMileage;

    @a(name = NotificationCompat.MessagingStyle.Message.KEY_DATA_MIME_TYPE)
    private int type;

    public long getDate() {
        return this.date;
    }

    public double getDouble0() {
        return this.double0;
    }

    public double getDouble1() {
        return this.double1;
    }

    public int getHevMileage() {
        return this.hevMileage;
    }

    public long getId() {
        return this.id;
    }

    public int getInt0() {
        return this.int0;
    }

    public int getInt1() {
        return this.int1;
    }

    public String getString0() {
        return this.string0;
    }

    public String getString1() {
        return this.string1;
    }

    public int getTotalMileage() {
        return this.totalMileage;
    }

    public int getType() {
        return this.type;
    }

    public void setDate(long j) {
        this.date = j;
    }

    public void setDouble0(double d2) {
        this.double0 = d2;
    }

    public void setDouble1(double d2) {
        this.double1 = d2;
    }

    public void setHevMileage(int i) {
        this.hevMileage = i;
    }

    public void setId(long j) {
        this.id = j;
    }

    public void setInt0(int i) {
        this.int0 = i;
    }

    public void setInt1(int i) {
        this.int1 = i;
    }

    public void setString0(String str) {
        this.string0 = str;
    }

    public void setString1(String str) {
        this.string1 = str;
    }

    public void setTotalMileage(int i) {
        this.totalMileage = i;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String toString() {
        return "MaintainanceRecord{id=" + this.id + ", type=" + this.type + ", date=" + this.date + ", totalMileage=" + this.totalMileage + ", hevMileage=" + this.hevMileage + '}';
    }
}
