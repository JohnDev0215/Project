package javax.activation;

import java.awt.datatransfer.DataFlavor;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/* JADX INFO: compiled from: DataHandler.java */
/* JADX INFO: loaded from: classes7.dex */
class ObjectDataContentHandler implements DataContentHandler {
    private DataContentHandler dch;
    private String mimeType;
    private Object obj;
    private DataFlavor[] transferFlavors = null;

    public ObjectDataContentHandler(DataContentHandler dataContentHandler, Object obj, String str) {
        this.obj = obj;
        this.mimeType = str;
        this.dch = dataContentHandler;
    }

    public DataContentHandler getDCH() {
        return this.dch;
    }

    @Override // javax.activation.DataContentHandler
    public void writeTo(Object obj, String str, OutputStream outputStream) throws IOException {
        DataContentHandler dataContentHandler = this.dch;
        if (dataContentHandler != null) {
            dataContentHandler.writeTo(obj, str, outputStream);
            return;
        }
        if (obj instanceof byte[]) {
            outputStream.write((byte[]) obj);
            return;
        }
        if (obj instanceof String) {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            outputStreamWriter.write((String) obj);
            outputStreamWriter.flush();
        } else {
            throw new UnsupportedDataTypeException("no object DCH for MIME type " + this.mimeType);
        }
    }
}
