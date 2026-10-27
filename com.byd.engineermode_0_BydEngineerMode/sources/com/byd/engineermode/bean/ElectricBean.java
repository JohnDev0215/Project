package com.byd.engineermode.bean;

/* JADX INFO: loaded from: classes.dex */
public class ElectricBean {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f2280a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f2281b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Type f2282c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private State f2283d;
    private State e;
    private State f;
    private State g;
    private State h;
    private String i;
    private long j;

    public enum Type {
        NONE,
        IN,
        OUT,
        POWER
    }

    public ElectricBean(String str) {
        State state = State.UNPERFORMED;
        this.f2283d = state;
        this.e = state;
        this.f = state;
        this.g = state;
        this.h = state;
        this.f2281b = str;
        this.f2282c = Type.NONE;
    }

    public State a() {
        return this.g;
    }

    public State b() {
        return this.f;
    }

    public State c() {
        return this.e;
    }

    public long d() {
        return this.f2280a;
    }

    public String e() {
        return this.f2281b;
    }

    public long f() {
        return this.j;
    }

    public State g() {
        return this.f2283d;
    }

    public String h() {
        String str = this.i;
        return str != null ? str : "--";
    }

    public State i() {
        return this.h;
    }

    public Type j() {
        return this.f2282c;
    }

    public void k(State state) {
        this.g = state;
    }

    public void l(State state) {
        this.f = state;
    }

    public void m(State state) {
        this.e = state;
    }

    public void n(long j) {
        this.f2280a = j;
    }

    public void o(long j) {
        this.j = j;
    }

    public void p(State state) {
        this.f2283d = state;
    }

    public void q(String str) {
        this.i = str;
    }

    public void r(State state) {
        this.h = state;
    }

    public void s(Type type) {
        this.f2282c = type;
    }

    public String toString() {
        return "ElectricBean{name='" + this.f2281b + "', type=" + this.f2282c + ", shortState=" + this.f2283d + ", currentState=" + this.e + ", connectState=" + this.f + ", supplier='" + this.i + "'}";
    }
}
