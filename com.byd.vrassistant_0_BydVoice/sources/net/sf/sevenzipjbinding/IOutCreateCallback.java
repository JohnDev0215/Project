package net.sf.sevenzipjbinding;

import net.sf.sevenzipjbinding.IOutItemBase;
import net.sf.sevenzipjbinding.impl.OutItemFactory;

/* JADX INFO: loaded from: classes3.dex */
public interface IOutCreateCallback<T extends IOutItemBase> extends IProgress {
    T getItemInformation(int i, OutItemFactory<T> outItemFactory) throws SevenZipException;

    ISequentialInStream getStream(int i) throws SevenZipException;

    void setOperationResult(boolean z) throws SevenZipException;
}
