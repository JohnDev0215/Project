package cerence.ark.assistant.common.callback;

import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class Result<T, R> {
    private final T mData;
    private final R mErrorCode;
    private final String mErrorMessage;
    private final boolean mIsSuccess;

    private Result(Builder<T, R> builder) {
        this.mIsSuccess = ((Builder) builder).mIsSuccess;
        this.mData = (T) ((Builder) builder).mData;
        this.mErrorCode = (R) ((Builder) builder).mErrorCode;
        this.mErrorMessage = ((Builder) builder).mErrorMessage;
    }

    public boolean isSuccess() {
        return this.mIsSuccess;
    }

    public T getData() {
        return this.mData;
    }

    public R getErrorCode() {
        return this.mErrorCode;
    }

    public String getErrorMessage() {
        return this.mErrorMessage;
    }

    public String toString() {
        return String.format(Locale.US, "%s - %s", this.mErrorCode, this.mErrorMessage);
    }

    public static class Builder<K, R> {
        private K mData;
        private R mErrorCode;
        private boolean mIsSuccess = false;
        private String mErrorMessage = "";

        public Builder<K, R> setData(K k) {
            this.mData = k;
            return this;
        }

        public Builder<K, R> setErrorCode(R r) {
            this.mErrorCode = r;
            return this;
        }

        public Builder<K, R> setErrorMessage(String str) {
            if (str == null) {
                str = "";
            }
            this.mErrorMessage = str;
            return this;
        }

        public Result<K, R> buildSuccess() {
            this.mIsSuccess = true;
            return new Result<>(this);
        }

        public Result<K, R> buildSuccess(K k) {
            this.mIsSuccess = true;
            this.mData = k;
            return new Result<>(this);
        }

        public Result<K, R> buildError() {
            this.mIsSuccess = false;
            return new Result<>(this);
        }
    }
}
