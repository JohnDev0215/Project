package com.byd.repository.exception;

/* JADX INFO: loaded from: classes.dex */
public class ApiException extends RuntimeException {
    private String description;
    private String status;

    public ApiException(String str, String str2) {
        this.status = str;
        this.description = str2;
    }

    public String description() {
        return this.description;
    }

    public String status() {
        return this.status;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return "status: " + this.status + ", description: " + this.description;
    }
}
