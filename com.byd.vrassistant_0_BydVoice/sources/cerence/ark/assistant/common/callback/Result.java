package cerence.ark.assistant.common.callback;

/* JADX INFO: loaded from: classes.dex */
public class Result<T> {
    private final T mData;
    private final String mErrorCode;
    private final String mErrorMessage;
    private final boolean mIsSuccess;

    private Result(Builder<T> builder) {
        this.mIsSuccess = ((Builder) builder).mIsSuccess;
        this.mData = (T) ((Builder) builder).mData;
        this.mErrorCode = ((Builder) builder).mErrorCode;
        this.mErrorMessage = ((Builder) builder).mErrorMessage;
    }

    public boolean isSuccess() {
        return this.mIsSuccess;
    }

    public T getData() {
        return this.mData;
    }

    public String getErrorCode() {
        return this.mErrorCode;
    }

    public String getErrorMessage() {
        return this.mErrorMessage;
    }

    public static class Builder<K> {
        private K mData;
        private boolean mIsSuccess = false;
        private String mErrorCode = "";
        private String mErrorMessage = "";

        public Builder<K> setSuccess(boolean z) {
            this.mIsSuccess = z;
            return this;
        }

        public Builder<K> setData(K k) {
            this.mData = k;
            return this;
        }

        public Builder<K> setErrorCode(String str) {
            if (str == null) {
                str = "";
            }
            this.mErrorCode = str;
            return this;
        }

        public Builder<K> setErrorMessage(String str) {
            if (str == null) {
                str = "";
            }
            this.mErrorMessage = str;
            return this;
        }

        public Result<K> buildSuccessResult() {
            this.mIsSuccess = true;
            return new Result<>(this);
        }

        public Result<K> buildSuccessResult(K k) {
            this.mIsSuccess = true;
            this.mData = k;
            return new Result<>(this);
        }

        public Result<K> buildErrorResult(String str) {
            if (str == null) {
                str = "";
            }
            this.mErrorMessage = str;
            return new Result<>(this);
        }

        public Result<K> buildErrorResult(String str, String str2) {
            if (str == null) {
                str = "";
            }
            this.mErrorCode = str;
            if (str2 == null) {
                str2 = "";
            }
            this.mErrorMessage = str2;
            return new Result<>(this);
        }

        public Result<K> build() {
            return new Result<>(this);
        }
    }
}
