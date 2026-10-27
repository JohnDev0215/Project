package com.amazon.identity.auth.device;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class me {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f437a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final Long g;
    public final Double h;
    public final Boolean i;
    public final String j;
    public final String k;

    /* JADX INFO: compiled from: DCP */
    public static abstract class a<T extends me> implements b<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f438a;
        public String b;
        public String c;
        public String d;
        public Long e;
        public Double f;
        public Boolean g;

        public abstract T a();
    }

    /* JADX INFO: compiled from: DCP */
    public interface b<T extends me> {
    }

    public me(String str, String str2, String str3, String str4, String str5, String str6, Long l, Double d, Boolean bool, String str7, String str8) {
        this.f437a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
        this.g = l;
        this.h = d;
        this.i = bool;
        this.j = str7;
        this.k = str8;
    }

    public abstract void a();

    public String toString() {
        return "MinervaMetricEvent{eventName='" + this.f437a + "', subEventName='" + this.b + "', reasonCode='" + this.c + "', url='" + this.d + "', configKey='" + this.e + "', configValue='" + this.f + "', count=" + this.g + ", durationMs=" + this.h + ", success=" + this.i + ", mapVersion='" + this.j + "', clientIdentifier='" + this.k + "'}";
    }
}
