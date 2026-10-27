package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import android.hardware.bydauto.ac.tsimpl.a;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u00002\b\b\u0003\u0010\u0003\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/stingray/kca/model/DocumentationInfo;", "", "", "markdown", "<init>", "(Ljava/lang/String;)V", "copy", "(Ljava/lang/String;)Lcom/stingray/kca/model/DocumentationInfo;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class DocumentationInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f13084a;

    public DocumentationInfo(@o(name = "markdown") String str) {
        m.f(str, "markdown");
        this.f13084a = str;
    }

    public final DocumentationInfo copy(@o(name = "markdown") String markdown) {
        m.f(markdown, "markdown");
        return new DocumentationInfo(markdown);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof DocumentationInfo) && m.a(this.f13084a, ((DocumentationInfo) obj).f13084a);
    }

    public final int hashCode() {
        return this.f13084a.hashCode();
    }

    public final String toString() {
        return a.l(new StringBuilder("DocumentationInfo(markdown="), this.f13084a, ")");
    }
}
