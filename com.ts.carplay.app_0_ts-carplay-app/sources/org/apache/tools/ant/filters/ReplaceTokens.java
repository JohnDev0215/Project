package org.apache.tools.ant.filters;

import java.io.IOException;
import java.io.Reader;
import java.util.Hashtable;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Parameter;

/* JADX INFO: loaded from: classes.dex */
public final class ReplaceTokens extends BaseParamFilterReader implements ChainableReader {
    private static final char DEFAULT_BEGIN_TOKEN = '@';
    private static final char DEFAULT_END_TOKEN = '@';
    private char beginToken;
    private char endToken;
    private Hashtable hash;
    private int queueIndex;
    private String queuedData;
    private String replaceData;
    private int replaceIndex;

    public ReplaceTokens() {
        this.queuedData = null;
        this.replaceData = null;
        this.replaceIndex = -1;
        this.queueIndex = -1;
        this.hash = new Hashtable();
        this.beginToken = '@';
        this.endToken = '@';
    }

    public ReplaceTokens(Reader in) {
        super(in);
        this.queuedData = null;
        this.replaceData = null;
        this.replaceIndex = -1;
        this.queueIndex = -1;
        this.hash = new Hashtable();
        this.beginToken = '@';
        this.endToken = '@';
    }

    private int getNextChar() throws IOException {
        int i = this.queueIndex;
        if (i != -1) {
            String str = this.queuedData;
            this.queueIndex = i + 1;
            int ch = str.charAt(i);
            if (this.queueIndex >= this.queuedData.length()) {
                this.queueIndex = -1;
            }
            return ch;
        }
        return this.in.read();
    }

    @Override // java.io.FilterReader, java.io.Reader
    public final int read() throws IOException {
        int ch;
        if (!getInitialized()) {
            initialize();
            setInitialized(true);
        }
        int i = this.replaceIndex;
        if (i != -1) {
            String str = this.replaceData;
            this.replaceIndex = i + 1;
            int ch2 = str.charAt(i);
            if (this.replaceIndex >= this.replaceData.length()) {
                this.replaceIndex = -1;
            }
            return ch2;
        }
        int ch3 = getNextChar();
        if (ch3 == this.beginToken) {
            StringBuffer key = new StringBuffer("");
            do {
                ch = getNextChar();
                if (ch == -1) {
                    break;
                }
                key.append((char) ch);
            } while (ch != this.endToken);
            if (ch == -1) {
                if (this.queuedData == null || this.queueIndex == -1) {
                    this.queuedData = key.toString();
                } else {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(key.toString());
                    stringBuffer.append(this.queuedData.substring(this.queueIndex));
                    this.queuedData = stringBuffer.toString();
                }
                this.queueIndex = 0;
                return this.beginToken;
            }
            key.setLength(key.length() - 1);
            String replaceWith = (String) this.hash.get(key.toString());
            if (replaceWith != null) {
                if (replaceWith.length() > 0) {
                    this.replaceData = replaceWith;
                    this.replaceIndex = 0;
                }
                return read();
            }
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(key.toString());
            stringBuffer2.append(this.endToken);
            String newData = stringBuffer2.toString();
            if (this.queuedData == null || this.queueIndex == -1) {
                this.queuedData = newData;
            } else {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append(newData);
                stringBuffer3.append(this.queuedData.substring(this.queueIndex));
                this.queuedData = stringBuffer3.toString();
            }
            this.queueIndex = 0;
            return this.beginToken;
        }
        return ch3;
    }

    public final void setBeginToken(char beginToken) {
        this.beginToken = beginToken;
    }

    private final char getBeginToken() {
        return this.beginToken;
    }

    public final void setEndToken(char endToken) {
        this.endToken = endToken;
    }

    private final char getEndToken() {
        return this.endToken;
    }

    public final void addConfiguredToken(Token token) {
        this.hash.put(token.getKey(), token.getValue());
    }

    private void setTokens(Hashtable hash) {
        this.hash = hash;
    }

    private final Hashtable getTokens() {
        return this.hash;
    }

    @Override // org.apache.tools.ant.filters.ChainableReader
    public final Reader chain(Reader rdr) {
        ReplaceTokens newFilter = new ReplaceTokens(rdr);
        newFilter.setBeginToken(getBeginToken());
        newFilter.setEndToken(getEndToken());
        newFilter.setTokens(getTokens());
        newFilter.setInitialized(true);
        return newFilter;
    }

    private final void initialize() {
        Parameter[] params = getParameters();
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                if (params[i] != null) {
                    String type = params[i].getType();
                    if ("tokenchar".equals(type)) {
                        String name = params[i].getName();
                        String value = params[i].getValue();
                        if ("begintoken".equals(name)) {
                            if (value.length() == 0) {
                                throw new BuildException("Begin token cannot be empty");
                            }
                            this.beginToken = params[i].getValue().charAt(0);
                        } else if (!"endtoken".equals(name)) {
                            continue;
                        } else {
                            if (value.length() == 0) {
                                throw new BuildException("End token cannot be empty");
                            }
                            this.endToken = params[i].getValue().charAt(0);
                        }
                    } else if ("token".equals(type)) {
                        this.hash.put(params[i].getName(), params[i].getValue());
                    }
                }
            }
        }
    }

    public static class Token {
        private String key;
        private String value;

        public final void setKey(String key) {
            this.key = key;
        }

        public final void setValue(String value) {
            this.value = value;
        }

        public final String getKey() {
            return this.key;
        }

        public final String getValue() {
            return this.value;
        }
    }
}
