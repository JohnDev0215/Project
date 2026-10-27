package com.byd.vrassistant.livedata;

/* JADX INFO: loaded from: classes.dex */
public class MutableResult<T> extends Result<T> {
    public MutableResult(T t) {
        super(t);
    }

    public MutableResult() {
    }

    @Override // com.byd.vrassistant.livedata.Result, androidx.lifecycle.LiveData
    public void setValue(T t) {
        super.setValue(t);
    }

    @Override // androidx.lifecycle.LiveData
    public void postValue(T t) {
        super.postValue(t);
    }

    public static class Builder<T> {
        private boolean isAllowNullValue;

        public Builder<T> setAllowNullValue(boolean z) {
            this.isAllowNullValue = z;
            return this;
        }

        public MutableResult<T> create() {
            MutableResult<T> mutableResult = new MutableResult<>();
            mutableResult.isAllowNullValue = this.isAllowNullValue;
            return mutableResult;
        }
    }
}
