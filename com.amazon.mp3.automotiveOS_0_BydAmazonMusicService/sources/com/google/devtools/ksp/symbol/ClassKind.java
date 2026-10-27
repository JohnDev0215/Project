package com.google.devtools.ksp.symbol;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import kotlin.Metadata;

/* JADX INFO: compiled from: ClassKind.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/google/devtools/ksp/symbol/ClassKind;", "", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "", "(Ljava/lang/String;ILjava/lang/String;)V", "getType", "()Ljava/lang/String;", "INTERFACE", "CLASS", "ENUM_CLASS", "ENUM_ENTRY", "OBJECT", "ANNOTATION_CLASS", "api"}, k = 1, mv = {1, 7, 1}, xi = 48)
public enum ClassKind {
    INTERFACE(ContextMappingConstants.INTERFACE),
    CLASS("class"),
    ENUM_CLASS("enum_class"),
    ENUM_ENTRY("enum_entry"),
    OBJECT("object"),
    ANNOTATION_CLASS("annotation_class");

    private final String type;

    ClassKind(String str) {
        this.type = str;
    }

    public final String getType() {
        return this.type;
    }
}
