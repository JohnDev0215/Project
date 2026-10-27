package org.apache.tools.ant;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import org.apache.tools.ant.taskdefs.condition.Os;

/* JADX INFO: loaded from: classes.dex */
public class PathTokenizer {
    private boolean dosStyleFilesystem;
    private String lookahead = null;
    private boolean onNetWare;
    private StringTokenizer tokenizer;

    public PathTokenizer(String path) {
        boolean zIsFamily = Os.isFamily("netware");
        this.onNetWare = zIsFamily;
        if (zIsFamily) {
            this.tokenizer = new StringTokenizer(path, ":;", true);
        } else {
            this.tokenizer = new StringTokenizer(path, ":;", false);
        }
        this.dosStyleFilesystem = File.pathSeparatorChar == ';';
    }

    public boolean hasMoreTokens() {
        if (this.lookahead != null) {
            return true;
        }
        return this.tokenizer.hasMoreTokens();
    }

    public String nextToken() throws NoSuchElementException {
        String token;
        if (this.lookahead != null) {
            token = this.lookahead;
            this.lookahead = null;
        } else {
            token = this.tokenizer.nextToken().trim();
        }
        if (!this.onNetWare) {
            if (token.length() == 1 && Character.isLetter(token.charAt(0)) && this.dosStyleFilesystem && this.tokenizer.hasMoreTokens()) {
                String nextToken = this.tokenizer.nextToken().trim();
                if (nextToken.startsWith("\\") || nextToken.startsWith("/")) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(token);
                    stringBuffer.append(":");
                    stringBuffer.append(nextToken);
                    String token2 = stringBuffer.toString();
                    return token2;
                }
                this.lookahead = nextToken;
                return token;
            }
            return token;
        }
        if (token.equals(File.pathSeparator) || token.equals(":")) {
            token = this.tokenizer.nextToken().trim();
        }
        if (this.tokenizer.hasMoreTokens()) {
            String nextToken2 = this.tokenizer.nextToken().trim();
            if (!nextToken2.equals(File.pathSeparator)) {
                if (nextToken2.equals(":")) {
                    if (!token.startsWith("/") && !token.startsWith("\\") && !token.startsWith(".") && !token.startsWith("..")) {
                        String oneMore = this.tokenizer.nextToken().trim();
                        if (!oneMore.equals(File.pathSeparator)) {
                            StringBuffer stringBuffer2 = new StringBuffer();
                            stringBuffer2.append(token);
                            stringBuffer2.append(":");
                            stringBuffer2.append(oneMore);
                            String token3 = stringBuffer2.toString();
                            return token3;
                        }
                        StringBuffer stringBuffer3 = new StringBuffer();
                        stringBuffer3.append(token);
                        stringBuffer3.append(":");
                        String token4 = stringBuffer3.toString();
                        this.lookahead = oneMore;
                        return token4;
                    }
                    return token;
                }
                this.lookahead = nextToken2;
                return token;
            }
            return token;
        }
        return token;
    }
}
