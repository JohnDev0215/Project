package net.sf.sevenzipjbinding;

/* JADX INFO: loaded from: classes3.dex */
public enum ExtractOperationResult {
    OK,
    UNSUPPORTEDMETHOD,
    DATAERROR,
    CRCERROR,
    UNAVAILABLE,
    UNEXPECTED_END,
    DATA_AFTER_END,
    IS_NOT_ARC,
    HEADERS_ERROR,
    WRONG_PASSWORD,
    UNKNOWN_OPERATION_RESULT;

    public static ExtractOperationResult getOperationResult(int i) {
        if (i >= 0 && i < values().length) {
            return values()[i];
        }
        return UNKNOWN_OPERATION_RESULT;
    }
}
