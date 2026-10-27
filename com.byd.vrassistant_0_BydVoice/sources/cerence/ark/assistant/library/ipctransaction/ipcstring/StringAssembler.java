package cerence.ark.assistant.library.ipctransaction.ipcstring;

import cerence.ark.assistant.library.ipctransaction.IAssembler;

/* JADX INFO: loaded from: classes.dex */
public class StringAssembler implements IAssembler<String> {
    private final StringBuilder mBuilder = new StringBuilder();

    @Override // cerence.ark.assistant.library.ipctransaction.IAssembler
    public String assemble(String str) {
        if (str == null) {
            return null;
        }
        this.mBuilder.append(str);
        return this.mBuilder.toString();
    }
}
