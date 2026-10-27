package com.byd.carsettings.db.model;

import b.b.h.f.f.a;
import b.b.h.f.f.b;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
@b(name = "FunctionItem")
public class FunctionItem extends BaseDBModel {

    @a(name = "address")
    private String address;

    @a(name = "description")
    private String description;

    @a(isId = true, name = "id")
    private long id;

    @a(name = "imgId")
    private int imgId;

    @a(name = "index0")
    private int index0;

    @a(name = "index1")
    private int index1;

    @a(name = "index2")
    private int index2;

    @a(name = "index3")
    private int index3;

    @a(name = "keywords")
    private String keywords;

    @a(name = "langTag")
    private String langTag;

    @a(name = "name")
    private String name;

    public FunctionItem() {
        this.imgId = 0;
        this.index0 = 0;
        this.index1 = -1;
        this.index2 = -1;
        this.index3 = -1;
    }

    public static FunctionItem getSettingItem(int i, int i2) {
        return new FunctionItem(4, i, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || FunctionItem.class != obj.getClass()) {
            return false;
        }
        FunctionItem functionItem = (FunctionItem) obj;
        return this.index0 == functionItem.index0 && this.index1 == functionItem.index1 && this.index2 == functionItem.index2 && this.index3 == functionItem.index3;
    }

    public boolean equalsToIndex2(FunctionItem functionItem) {
        return functionItem != null && this.index0 == functionItem.index0 && this.index1 == functionItem.index1 && this.index2 == functionItem.index2;
    }

    public String getAddress() {
        return this.address;
    }

    public String getDescription() {
        return this.description;
    }

    public long getId() {
        return this.id;
    }

    public int getImgId() {
        return this.imgId;
    }

    public int getIndex0() {
        return this.index0;
    }

    public int getIndex1() {
        return this.index1;
    }

    public int getIndex2() {
        return this.index2;
    }

    public int getIndex3() {
        return this.index3;
    }

    public String getKeywords() {
        return this.keywords;
    }

    public String getLangTag() {
        return this.langTag;
    }

    public String getName() {
        return this.name;
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.id), this.name, this.keywords, Integer.valueOf(this.imgId), this.address, this.description, Integer.valueOf(this.index0), Integer.valueOf(this.index1), Integer.valueOf(this.index2), Integer.valueOf(this.index3), this.langTag);
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setId(long j) {
        this.id = j;
    }

    public void setImgId(int i) {
        this.imgId = i;
    }

    public void setIndex0(int i) {
        this.index0 = i;
    }

    public void setIndex1(int i) {
        this.index1 = i;
    }

    public void setIndex2(int i) {
        this.index2 = i;
    }

    public void setIndex3(int i) {
        this.index3 = i;
    }

    public void setKeywords(String str) {
        this.keywords = str;
    }

    public void setLangTag(String str) {
        this.langTag = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String toString() {
        return "FunctionItem{id=" + this.id + ", name='" + this.name + "', keywords='" + this.keywords + "', imgId=" + this.imgId + ", address='" + this.address + "', description='" + this.description + "', index0=" + this.index0 + ", index1=" + this.index1 + ", index2=" + this.index2 + ", index3=" + this.index3 + ", langTag=" + this.langTag + '}';
    }

    public static FunctionItem getSettingItem(int i, int i2, int i3) {
        return new FunctionItem(4, i, i2, i3);
    }

    public FunctionItem(String str) {
        this.imgId = 0;
        this.index0 = 0;
        this.index1 = -1;
        this.index2 = -1;
        this.index3 = -1;
        this.name = str;
    }

    public FunctionItem(int i, int i2, int i3) {
        this.imgId = 0;
        this.index0 = 0;
        this.index1 = -1;
        this.index2 = -1;
        this.index3 = -1;
        this.index0 = i;
        this.index1 = i2;
        this.index2 = i3;
    }

    public FunctionItem(int i, int i2, int i3, int i4) {
        this.imgId = 0;
        this.index0 = 0;
        this.index1 = -1;
        this.index2 = -1;
        this.index3 = -1;
        this.index0 = i;
        this.index1 = i2;
        this.index2 = i3;
        this.index3 = i4;
    }
}
