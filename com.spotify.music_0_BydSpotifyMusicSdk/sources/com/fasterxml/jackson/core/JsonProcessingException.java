package com.fasterxml.jackson.core;

/* JADX INFO: loaded from: classes.dex */
public abstract class JsonProcessingException extends JacksonException {
    private static final long serialVersionUID = 123;
    protected JsonLocation _location;

    public JsonProcessingException(String str, JsonLocation jsonLocation, Throwable th) {
        super(str, th);
        this._location = jsonLocation;
    }

    @Override // com.fasterxml.jackson.core.JacksonException
    public final JsonLocation a() {
        return this._location;
    }

    @Override // com.fasterxml.jackson.core.JacksonException
    public final String b() {
        return super.getMessage();
    }

    public String c() {
        return null;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        String message = super.getMessage();
        if (message == null) {
            message = "N/A";
        }
        JsonLocation jsonLocation = this._location;
        String strC = c();
        if (jsonLocation == null && strC == null) {
            return message;
        }
        StringBuilder sb = new StringBuilder(100);
        sb.append(message);
        if (strC != null) {
            sb.append(strC);
        }
        if (jsonLocation != null) {
            sb.append("\n at ");
            sb.append(jsonLocation.toString());
        }
        return sb.toString();
    }
}
