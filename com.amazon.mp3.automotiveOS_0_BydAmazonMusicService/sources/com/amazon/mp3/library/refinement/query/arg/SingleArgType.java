package com.amazon.mp3.library.refinement.query.arg;

import kotlin.Metadata;

/* JADX INFO: compiled from: SingleArg.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/library/refinement/query/arg/SingleArgType;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "GREATER_THAN", "LESS_THAN", "EQUALS", "GREATER_THAN_OR_EQUALS", "LESS_THAN_OR_EQUALS", "NOT_EQUALS", "LIKE", "NOT_LIKE", "MusicExperienceCore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum SingleArgType {
    GREATER_THAN(">"),
    LESS_THAN("<"),
    EQUALS("="),
    GREATER_THAN_OR_EQUALS(">="),
    LESS_THAN_OR_EQUALS("<="),
    NOT_EQUALS("<>"),
    LIKE("LIKE"),
    NOT_LIKE("NOT LIKE");

    private final String value;

    SingleArgType(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }
}
