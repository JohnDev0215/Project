package com.amazon.digitalmusicplayback;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class Weblab {
    final String defaultTreatment;
    final String explanation;
    final String identifier;
    final ArrayList<WeblabTreatment> treatments;

    public Weblab(String str, String str2, ArrayList<WeblabTreatment> arrayList, String str3) {
        this.identifier = str;
        this.explanation = str2;
        this.treatments = arrayList;
        this.defaultTreatment = str3;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String getExplanation() {
        return this.explanation;
    }

    public ArrayList<WeblabTreatment> getTreatments() {
        return this.treatments;
    }

    public String getDefaultTreatment() {
        return this.defaultTreatment;
    }

    public String toString() {
        return "Weblab{identifier=" + this.identifier + ",explanation=" + this.explanation + ",treatments=" + this.treatments + ",defaultTreatment=" + this.defaultTreatment + "}";
    }
}
