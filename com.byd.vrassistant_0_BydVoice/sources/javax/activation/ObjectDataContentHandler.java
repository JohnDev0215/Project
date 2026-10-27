package javax.activation;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/* JADX INFO: compiled from: DataHandler.java */
/* JADX INFO: loaded from: classes3.dex */
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
    public synchronized DataFlavor[] getTransferDataFlavors() {
        if (this.transferFlavors == null) {
            DataContentHandler dataContentHandler = this.dch;
            if (dataContentHandler != null) {
                this.transferFlavors = dataContentHandler.getTransferDataFlavors();
            } else {
                this.transferFlavors = new DataFlavor[]{new ActivationDataFlavor(cls, str, str)};
                Class<?> cls = this.obj.getClass();
                String str = this.mimeType;
            }
        }
        return this.transferFlavors;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: java.awt.datatransfer.UnsupportedFlavorException */
    @Override // javax.activation.DataContentHandler
    public Object getTransferData(DataFlavor dataFlavor, DataSource dataSource) throws UnsupportedFlavorException, IOException {
        DataContentHandler dataContentHandler = this.dch;
        if (dataContentHandler != null) {
            return dataContentHandler.getTransferData(dataFlavor, dataSource);
        }
        if (dataFlavor.equals(getTransferDataFlavors()[0])) {
            return this.obj;
        }
        throw new UnsupportedFlavorException(dataFlavor);
    }

    @Override // javax.activation.DataContentHandler
    public Object getContent(DataSource dataSource) {
        return this.obj;
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
        } else {
            if (obj instanceof String) {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                outputStreamWriter.write((String) obj);
                outputStreamWriter.flush();
                return;
            }
            throw new UnsupportedDataTypeException(new StringBuffer("no object DCH for MIME type ").append(this.mimeType).toString());
        }
    }
}
