package cerence.ark.assistant.library.ipctransaction.ipclist;

import cerence.ark.assistant.library.ipctransaction.IAssembler;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ListAssembler<T> implements IAssembler<List<T>> {
    private final List<T> mList = new ArrayList();

    @Override // cerence.ark.assistant.library.ipctransaction.IAssembler
    public List<T> assemble(List<T> list) {
        if (list == null) {
            return null;
        }
        this.mList.addAll(list);
        return this.mList;
    }
}
