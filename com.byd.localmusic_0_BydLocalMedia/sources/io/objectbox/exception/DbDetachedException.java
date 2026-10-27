package io.objectbox.exception;

/* JADX INFO: loaded from: classes5.dex */
public class DbDetachedException extends DbException {
    public DbDetachedException() {
        this("Cannot perform this action on a detached entity. Ensure it was loaded by ObjectBox, or attach it manually.");
    }

    public DbDetachedException(String str) {
        super(str);
    }
}
