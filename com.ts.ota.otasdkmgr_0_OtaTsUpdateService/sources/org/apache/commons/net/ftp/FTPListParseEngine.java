package org.apache.commons.net.ftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import org.apache.commons.net.util.Charsets;

/* JADX INFO: loaded from: classes.dex */
public class FTPListParseEngine {
    private static final FTPFile[] EMPTY_FTP_FILE_ARRAY = new FTPFile[0];
    private List<String> entries;
    private ListIterator<String> internalIterator;
    private final FTPFileEntryParser parser;
    private final boolean saveUnparseableEntries;

    public FTPListParseEngine(FTPFileEntryParser parser) {
        this(parser, null);
    }

    FTPListParseEngine(FTPFileEntryParser parser, FTPClientConfig configuration) {
        LinkedList linkedList = new LinkedList();
        this.entries = linkedList;
        this.internalIterator = linkedList.listIterator();
        this.parser = parser;
        if (configuration != null) {
            this.saveUnparseableEntries = configuration.getUnparseableEntries();
        } else {
            this.saveUnparseableEntries = false;
        }
    }

    public void readServerList(InputStream inputStream, String charsetName) throws IOException {
        this.entries = new LinkedList();
        read(inputStream, charsetName);
        this.parser.preParse(this.entries);
        resetIterator();
    }

    private void read(InputStream inputStream, String charsetName) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charsets.toCharset(charsetName)));
        try {
            String line = this.parser.readNextEntry(reader);
            while (line != null) {
                this.entries.add(line);
                line = this.parser.readNextEntry(reader);
            }
            reader.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    reader.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    public FTPFile[] getNext(int quantityRequested) {
        List<FTPFile> tmpResults = new LinkedList<>();
        for (int count = quantityRequested; count > 0 && this.internalIterator.hasNext(); count--) {
            String entry = this.internalIterator.next();
            FTPFile temp = this.parser.parseFTPEntry(entry);
            if (temp == null && this.saveUnparseableEntries) {
                temp = new FTPFile(entry);
            }
            tmpResults.add(temp);
        }
        return (FTPFile[]) tmpResults.toArray(EMPTY_FTP_FILE_ARRAY);
    }

    public FTPFile[] getPrevious(int quantityRequested) {
        List<FTPFile> tmpResults = new LinkedList<>();
        for (int count = quantityRequested; count > 0 && this.internalIterator.hasPrevious(); count--) {
            String entry = this.internalIterator.previous();
            FTPFile temp = this.parser.parseFTPEntry(entry);
            if (temp == null && this.saveUnparseableEntries) {
                temp = new FTPFile(entry);
            }
            tmpResults.add(0, temp);
        }
        return (FTPFile[]) tmpResults.toArray(EMPTY_FTP_FILE_ARRAY);
    }

    public FTPFile[] getFiles() throws IOException {
        return getFiles(FTPFileFilters.NON_NULL);
    }

    public FTPFile[] getFiles(FTPFileFilter filter) throws IOException {
        List<FTPFile> tmpResults = new ArrayList<>();
        for (String entry : this.entries) {
            FTPFile temp = this.parser.parseFTPEntry(entry);
            if (temp == null && this.saveUnparseableEntries) {
                temp = new FTPFile(entry);
            }
            if (filter.accept(temp)) {
                tmpResults.add(temp);
            }
        }
        return (FTPFile[]) tmpResults.toArray(EMPTY_FTP_FILE_ARRAY);
    }

    public boolean hasNext() {
        return this.internalIterator.hasNext();
    }

    public boolean hasPrevious() {
        return this.internalIterator.hasPrevious();
    }

    public void resetIterator() {
        this.internalIterator = this.entries.listIterator();
    }

    @Deprecated
    public void readServerList(InputStream inputStream) throws IOException {
        readServerList(inputStream, null);
    }
}
