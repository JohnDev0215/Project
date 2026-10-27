package com.byd.music.fragment.localInterface;

/* JADX INFO: loaded from: classes5.dex */
public interface DeleteInterface<T> {

    public interface DeleteResultListener<T> {
        void cancelDelete(boolean z);

        void deleteFail(T[] tArr);

        void deleteSuccess(T[] tArr);

        void updateDeleteMusicNum(int i);
    }

    void deleteBegin();

    void deleteCancel();

    int getDeleteTotal();

    void setDeleteResultListener(DeleteResultListener<T> deleteResultListener);
}
