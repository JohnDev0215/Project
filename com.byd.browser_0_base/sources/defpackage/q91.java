package defpackage;

import android.os.LocaleList;
import java.util.Locale;

/* JADX INFO: compiled from: LocaleListPlatformWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public final class q91 implements p91 {
    public final LocaleList a;

    public q91(Object obj) {
        this.a = (LocaleList) obj;
    }

    @Override // defpackage.p91
    public final String a() {
        return this.a.toLanguageTags();
    }

    @Override // defpackage.p91
    public final Object b() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        return this.a.equals(((p91) obj).b());
    }

    @Override // defpackage.p91
    public final Locale get(int i) {
        return this.a.get(i);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    @Override // defpackage.p91
    public final boolean isEmpty() {
        return this.a.isEmpty();
    }

    @Override // defpackage.p91
    public final int size() {
        return this.a.size();
    }

    public final String toString() {
        return this.a.toString();
    }
}
