package javax.activation;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/* JADX INFO: loaded from: classes7.dex */
public class DataHandler implements Transferable {
    private static final DataFlavor[] emptyFlavors = new DataFlavor[0];
    private static DataContentHandlerFactory factory;
    private DataSource dataSource;
    private DataSource objDataSource = null;
    private Object object = null;
    private String objectMimeType = null;
    private CommandMap currentCommandMap = null;
    private DataFlavor[] transferFlavors = emptyFlavors;
    private DataContentHandler dataContentHandler = null;
    private DataContentHandler factoryDCH = null;
    private String shortType = null;
    private DataContentHandlerFactory oldFactory = factory;

    public DataHandler(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private synchronized CommandMap getCommandMap() {
        CommandMap commandMap = this.currentCommandMap;
        if (commandMap != null) {
            return commandMap;
        }
        return CommandMap.getDefaultCommandMap();
    }

    public String getContentType() {
        DataSource dataSource = this.dataSource;
        if (dataSource != null) {
            return dataSource.getContentType();
        }
        return this.objectMimeType;
    }

    public InputStream getInputStream() throws IOException {
        DataSource dataSource = this.dataSource;
        if (dataSource != null) {
            return dataSource.getInputStream();
        }
        final DataContentHandler dataContentHandler = getDataContentHandler();
        if (dataContentHandler == null) {
            throw new UnsupportedDataTypeException("no DCH for MIME type " + getBaseType());
        }
        if ((dataContentHandler instanceof ObjectDataContentHandler) && ((ObjectDataContentHandler) dataContentHandler).getDCH() == null) {
            throw new UnsupportedDataTypeException("no object DCH for MIME type " + getBaseType());
        }
        final PipedOutputStream pipedOutputStream = new PipedOutputStream();
        PipedInputStream pipedInputStream = new PipedInputStream(pipedOutputStream);
        new Thread(new Runnable() { // from class: javax.activation.DataHandler.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    dataContentHandler.writeTo(DataHandler.this.object, DataHandler.this.objectMimeType, pipedOutputStream);
                } catch (IOException unused) {
                } catch (Throwable th) {
                    try {
                        pipedOutputStream.close();
                    } catch (IOException unused2) {
                    }
                    throw th;
                }
                try {
                    pipedOutputStream.close();
                } catch (IOException unused3) {
                }
            }
        }, "DataHandler.getInputStream").start();
        return pipedInputStream;
    }

    private synchronized DataContentHandler getDataContentHandler() {
        DataContentHandlerFactory dataContentHandlerFactory;
        DataContentHandlerFactory dataContentHandlerFactory2 = factory;
        if (dataContentHandlerFactory2 != this.oldFactory) {
            this.oldFactory = dataContentHandlerFactory2;
            this.factoryDCH = null;
            this.dataContentHandler = null;
            this.transferFlavors = emptyFlavors;
        }
        DataContentHandler dataContentHandler = this.dataContentHandler;
        if (dataContentHandler != null) {
            return dataContentHandler;
        }
        String baseType = getBaseType();
        if (this.factoryDCH == null && (dataContentHandlerFactory = factory) != null) {
            this.factoryDCH = dataContentHandlerFactory.createDataContentHandler(baseType);
        }
        DataContentHandler dataContentHandler2 = this.factoryDCH;
        if (dataContentHandler2 != null) {
            this.dataContentHandler = dataContentHandler2;
        }
        if (this.dataContentHandler == null) {
            if (this.dataSource != null) {
                this.dataContentHandler = getCommandMap().createDataContentHandler(baseType, this.dataSource);
            } else {
                this.dataContentHandler = getCommandMap().createDataContentHandler(baseType);
            }
        }
        DataSource dataSource = this.dataSource;
        if (dataSource != null) {
            this.dataContentHandler = new DataSourceDataContentHandler(this.dataContentHandler, dataSource);
        } else {
            this.dataContentHandler = new ObjectDataContentHandler(this.dataContentHandler, this.object, this.objectMimeType);
        }
        return this.dataContentHandler;
    }

    private synchronized String getBaseType() {
        if (this.shortType == null) {
            String contentType = getContentType();
            try {
                this.shortType = new MimeType(contentType).getBaseType();
            } catch (MimeTypeParseException unused) {
                this.shortType = contentType;
            }
        }
        return this.shortType;
    }
}
