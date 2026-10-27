package au.com.bytecode.opencsv;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;

/* JADX INFO: loaded from: classes2.dex */
public class CSVReader implements Closeable {
    private BufferedReader br;
    private boolean hasNext;
    private boolean linesSkiped;
    private CSVParser parser;
    private int skipLines;

    public CSVReader(Reader reader, char c, char c2, char c3) {
        this(reader, c, c2, c3, 0, false);
    }

    public CSVReader(Reader reader, char c, char c2, char c3, int i, boolean z) {
        this(reader, c, c2, c3, i, z, true);
    }

    public CSVReader(Reader reader, char c, char c2, char c3, int i, boolean z, boolean z2) {
        this.hasNext = true;
        this.br = new BufferedReader(reader);
        this.parser = new CSVParser(c, c2, c3, z, z2);
        this.skipLines = i;
    }

    private String getNextLine() throws IOException {
        if (!this.linesSkiped) {
            for (int i = 0; i < this.skipLines; i++) {
                this.br.readLine();
            }
            this.linesSkiped = true;
        }
        String line = this.br.readLine();
        if (line == null) {
            this.hasNext = false;
        }
        if (this.hasNext) {
            return line;
        }
        return null;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.br.close();
    }

    public String[] readNext() throws IOException {
        String[] strArr = null;
        do {
            String nextLine = getNextLine();
            if (!this.hasNext) {
                return strArr;
            }
            String[] lineMulti = this.parser.parseLineMulti(nextLine);
            if (lineMulti.length > 0) {
                if (strArr == null) {
                    strArr = lineMulti;
                } else {
                    String[] strArr2 = new String[strArr.length + lineMulti.length];
                    System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
                    System.arraycopy(lineMulti, 0, strArr2, strArr.length, lineMulti.length);
                    strArr = strArr2;
                }
            }
        } while (this.parser.isPending());
        return strArr;
    }
}
