package org.apache.tools.ant.filters;

import java.io.IOException;
import java.io.Reader;
import java.util.Enumeration;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectComponent;
import org.apache.tools.ant.types.RegularExpression;
import org.apache.tools.ant.types.Substitution;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.LineTokenizer;
import org.apache.tools.ant.util.Tokenizer;
import org.apache.tools.ant.util.regexp.Regexp;
import org.apache.tools.ant.util.regexp.RegexpMatcher;

/* JADX INFO: loaded from: classes.dex */
public class TokenFilter extends BaseFilterReader implements ChainableReader {
    private String delimOutput;
    private Vector filters;
    private String line;
    private int linePos;
    private Tokenizer tokenizer;

    public interface Filter {
        String filter(String str);
    }

    public TokenFilter() {
        this.filters = new Vector();
        this.tokenizer = null;
        this.delimOutput = null;
        this.line = null;
        this.linePos = 0;
    }

    public TokenFilter(Reader in) {
        super(in);
        this.filters = new Vector();
        this.tokenizer = null;
        this.delimOutput = null;
        this.line = null;
        this.linePos = 0;
    }

    @Override // java.io.FilterReader, java.io.Reader
    public int read() throws IOException {
        if (this.tokenizer == null) {
            this.tokenizer = new LineTokenizer();
        }
        while (true) {
            String str = this.line;
            if (str == null || str.length() == 0) {
                String token = this.tokenizer.getToken(this.in);
                this.line = token;
                if (token == null) {
                    return -1;
                }
                Enumeration e = this.filters.elements();
                while (e.hasMoreElements()) {
                    Filter filter = (Filter) e.nextElement();
                    String strFilter = filter.filter(this.line);
                    this.line = strFilter;
                    if (strFilter == null) {
                        break;
                    }
                }
                this.linePos = 0;
                if (this.line != null && this.tokenizer.getPostToken().length() != 0) {
                    if (this.delimOutput != null) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append(this.line);
                        stringBuffer.append(this.delimOutput);
                        this.line = stringBuffer.toString();
                    } else {
                        StringBuffer stringBuffer2 = new StringBuffer();
                        stringBuffer2.append(this.line);
                        stringBuffer2.append(this.tokenizer.getPostToken());
                        this.line = stringBuffer2.toString();
                    }
                }
            } else {
                int ch = this.line.charAt(this.linePos);
                int i = this.linePos + 1;
                this.linePos = i;
                if (i == this.line.length()) {
                    this.line = null;
                }
                return ch;
            }
        }
    }

    @Override // org.apache.tools.ant.filters.ChainableReader
    public final Reader chain(Reader reader) {
        TokenFilter newFilter = new TokenFilter(reader);
        newFilter.filters = this.filters;
        newFilter.tokenizer = this.tokenizer;
        newFilter.delimOutput = this.delimOutput;
        newFilter.setProject(getProject());
        return newFilter;
    }

    public void setDelimOutput(String delimOutput) {
        this.delimOutput = resolveBackSlash(delimOutput);
    }

    public void addLineTokenizer(LineTokenizer tokenizer) {
        add(tokenizer);
    }

    public void addStringTokenizer(StringTokenizer tokenizer) {
        add(tokenizer);
    }

    public void addFileTokenizer(FileTokenizer tokenizer) {
        add(tokenizer);
    }

    public void add(Tokenizer tokenizer) {
        if (this.tokenizer != null) {
            throw new BuildException("Only one tokenizer allowed");
        }
        this.tokenizer = tokenizer;
    }

    public void addReplaceString(ReplaceString filter) {
        this.filters.addElement(filter);
    }

    public void addContainsString(ContainsString filter) {
        this.filters.addElement(filter);
    }

    public void addReplaceRegex(ReplaceRegex filter) {
        this.filters.addElement(filter);
    }

    public void addContainsRegex(ContainsRegex filter) {
        this.filters.addElement(filter);
    }

    public void addTrim(Trim filter) {
        this.filters.addElement(filter);
    }

    public void addIgnoreBlank(IgnoreBlank filter) {
        this.filters.addElement(filter);
    }

    public void addDeleteCharacters(DeleteCharacters filter) {
        this.filters.addElement(filter);
    }

    public void add(Filter filter) {
        this.filters.addElement(filter);
    }

    public static class FileTokenizer extends ProjectComponent implements Tokenizer {
        @Override // org.apache.tools.ant.util.Tokenizer
        public String getToken(Reader in) throws IOException {
            return FileUtils.readFully(in);
        }

        @Override // org.apache.tools.ant.util.Tokenizer
        public String getPostToken() {
            return "";
        }
    }

    public static class StringTokenizer extends ProjectComponent implements Tokenizer {
        private String intraString = "";
        private int pushed = -2;
        private char[] delims = null;
        private boolean delimsAreTokens = false;
        private boolean suppressDelims = false;
        private boolean includeDelims = false;

        public void setDelims(String delims) {
            this.delims = TokenFilter.resolveBackSlash(delims).toCharArray();
        }

        public void setDelimsAreTokens(boolean delimsAreTokens) {
            this.delimsAreTokens = delimsAreTokens;
        }

        public void setSuppressDelims(boolean suppressDelims) {
            this.suppressDelims = suppressDelims;
        }

        public void setIncludeDelims(boolean includeDelims) {
            this.includeDelims = includeDelims;
        }

        @Override // org.apache.tools.ant.util.Tokenizer
        public String getToken(Reader in) throws IOException {
            int ch;
            if (this.pushed != -2) {
                ch = this.pushed;
                this.pushed = -2;
            } else {
                ch = in.read();
            }
            if (ch == -1) {
                return null;
            }
            boolean inToken = true;
            this.intraString = "";
            StringBuffer word = new StringBuffer();
            StringBuffer padding = new StringBuffer();
            while (true) {
                if (ch != -1) {
                    char c = (char) ch;
                    boolean isDelim = isDelim(c);
                    if (inToken) {
                        if (isDelim) {
                            if (this.delimsAreTokens) {
                                if (word.length() == 0) {
                                    word.append(c);
                                } else {
                                    this.pushed = ch;
                                }
                            } else {
                                padding.append(c);
                                inToken = false;
                            }
                        } else {
                            word.append(c);
                        }
                        ch = in.read();
                    } else if (isDelim) {
                        padding.append(c);
                        ch = in.read();
                    } else {
                        this.pushed = ch;
                        break;
                    }
                } else {
                    break;
                }
            }
            String string = padding.toString();
            this.intraString = string;
            if (this.includeDelims) {
                word.append(string);
            }
            return word.toString();
        }

        @Override // org.apache.tools.ant.util.Tokenizer
        public String getPostToken() {
            if (this.suppressDelims || this.includeDelims) {
                return "";
            }
            return this.intraString;
        }

        private boolean isDelim(char ch) {
            if (this.delims == null) {
                return Character.isWhitespace(ch);
            }
            int i = 0;
            while (true) {
                char[] cArr = this.delims;
                if (i < cArr.length) {
                    if (cArr[i] != ch) {
                        i++;
                    } else {
                        return true;
                    }
                } else {
                    return false;
                }
            }
        }
    }

    public static abstract class ChainableReaderFilter extends ProjectComponent implements ChainableReader, Filter {
        private boolean byLine = true;

        public void setByLine(boolean byLine) {
            this.byLine = byLine;
        }

        @Override // org.apache.tools.ant.filters.ChainableReader
        public Reader chain(Reader reader) {
            TokenFilter tokenFilter = new TokenFilter(reader);
            if (!this.byLine) {
                tokenFilter.add(new FileTokenizer());
            }
            tokenFilter.add(this);
            return tokenFilter;
        }
    }

    public static class ReplaceString extends ChainableReaderFilter {
        private String from;
        private String to;

        public void setFrom(String from) {
            this.from = from;
        }

        public void setTo(String to) {
            this.to = to;
        }

        @Override // org.apache.tools.ant.filters.TokenFilter.Filter
        public String filter(String line) {
            if (this.from == null) {
                throw new BuildException("Missing from in stringreplace");
            }
            StringBuffer ret = new StringBuffer();
            int start = 0;
            int found = line.indexOf(this.from);
            while (found >= 0) {
                if (found > start) {
                    ret.append(line.substring(start, found));
                }
                String str = this.to;
                if (str != null) {
                    ret.append(str);
                }
                start = found + this.from.length();
                found = line.indexOf(this.from, start);
            }
            if (line.length() > start) {
                ret.append(line.substring(start, line.length()));
            }
            return ret.toString();
        }
    }

    public static class ContainsString extends ProjectComponent implements Filter {
        private String contains;

        public void setContains(String contains) {
            this.contains = contains;
        }

        @Override // org.apache.tools.ant.filters.TokenFilter.Filter
        public String filter(String string) {
            String str = this.contains;
            if (str == null) {
                throw new BuildException("Missing contains in containsstring");
            }
            if (string.indexOf(str) > -1) {
                return string;
            }
            return null;
        }
    }

    public static class ReplaceRegex extends ChainableReaderFilter {
        private String from;
        private int options;
        private Regexp regexp;
        private RegularExpression regularExpression;
        private Substitution substitution;
        private String to;
        private boolean initialized = false;
        private String flags = "";

        public void setPattern(String from) {
            this.from = from;
        }

        public void setReplace(String to) {
            this.to = to;
        }

        public void setFlags(String flags) {
            this.flags = flags;
        }

        private void initialize() {
            if (this.initialized) {
                return;
            }
            this.options = TokenFilter.convertRegexOptions(this.flags);
            if (this.from == null) {
                throw new BuildException("Missing pattern in replaceregex");
            }
            RegularExpression regularExpression = new RegularExpression();
            this.regularExpression = regularExpression;
            regularExpression.setPattern(this.from);
            this.regexp = this.regularExpression.getRegexp(getProject());
            if (this.to == null) {
                this.to = "";
            }
            Substitution substitution = new Substitution();
            this.substitution = substitution;
            substitution.setExpression(this.to);
        }

        @Override // org.apache.tools.ant.filters.TokenFilter.Filter
        public String filter(String line) {
            initialize();
            if (!this.regexp.matches(line, this.options)) {
                return line;
            }
            return this.regexp.substitute(line, this.substitution.getExpression(getProject()), this.options);
        }
    }

    public static class ContainsRegex extends ChainableReaderFilter {
        private String from;
        private int options;
        private Project project;
        private Regexp regexp;
        private RegularExpression regularExpression;
        private Substitution substitution;
        private String to;
        private boolean initialized = false;
        private String flags = "";

        public void setPattern(String from) {
            this.from = from;
        }

        public void setReplace(String to) {
            this.to = to;
        }

        public void setFlags(String flags) {
            this.flags = flags;
        }

        private void initialize() {
            if (this.initialized) {
                return;
            }
            this.options = TokenFilter.convertRegexOptions(this.flags);
            if (this.from == null) {
                throw new BuildException("Missing from in containsregex");
            }
            RegularExpression regularExpression = new RegularExpression();
            this.regularExpression = regularExpression;
            regularExpression.setPattern(this.from);
            this.regexp = this.regularExpression.getRegexp(this.project);
            if (this.to == null) {
                return;
            }
            Substitution substitution = new Substitution();
            this.substitution = substitution;
            substitution.setExpression(this.to);
        }

        @Override // org.apache.tools.ant.filters.TokenFilter.Filter
        public String filter(String string) {
            initialize();
            if (!this.regexp.matches(string, this.options)) {
                return null;
            }
            Substitution substitution = this.substitution;
            if (substitution == null) {
                return string;
            }
            return this.regexp.substitute(string, substitution.getExpression(getProject()), this.options);
        }
    }

    public static class Trim extends ChainableReaderFilter {
        @Override // org.apache.tools.ant.filters.TokenFilter.Filter
        public String filter(String line) {
            return line.trim();
        }
    }

    public static class IgnoreBlank extends ChainableReaderFilter {
        @Override // org.apache.tools.ant.filters.TokenFilter.Filter
        public String filter(String line) {
            if (line.trim().length() == 0) {
                return null;
            }
            return line;
        }
    }

    public static class DeleteCharacters extends ProjectComponent implements Filter, ChainableReader {
        private String deleteChars = "";

        public void setChars(String deleteChars) {
            this.deleteChars = TokenFilter.resolveBackSlash(deleteChars);
        }

        @Override // org.apache.tools.ant.filters.TokenFilter.Filter
        public String filter(String string) {
            StringBuffer output = new StringBuffer(string.length());
            for (int i = 0; i < string.length(); i++) {
                char ch = string.charAt(i);
                if (!isDeleteCharacter(ch)) {
                    output.append(ch);
                }
            }
            return output.toString();
        }

        @Override // org.apache.tools.ant.filters.ChainableReader
        public Reader chain(Reader reader) {
            return new BaseFilterReader(this, reader) { // from class: org.apache.tools.ant.filters.TokenFilter.1
                private final /* synthetic */ DeleteCharacters this$0;

                {
                    this.this$0 = this;
                }

                @Override // java.io.FilterReader, java.io.Reader
                public int read() throws IOException {
                    int c;
                    do {
                        c = this.in.read();
                        if (c == -1) {
                            return c;
                        }
                    } while (this.this$0.isDeleteCharacter((char) c));
                    return c;
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isDeleteCharacter(char c) {
            for (int d = 0; d < this.deleteChars.length(); d++) {
                if (this.deleteChars.charAt(d) == c) {
                    return true;
                }
            }
            return false;
        }
    }

    public static String resolveBackSlash(String input) {
        StringBuffer b = new StringBuffer();
        boolean backSlashSeen = false;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!backSlashSeen) {
                if (c == '\\') {
                    backSlashSeen = true;
                } else {
                    b.append(c);
                }
            } else {
                if (c != '\\') {
                    if (c != 'f') {
                        if (c == 'n') {
                            b.append('\n');
                        } else {
                            switch (c) {
                                case 'r':
                                    b.append('\r');
                                    break;
                                case 's':
                                    b.append(" \t\n\r\f");
                                    break;
                                case 't':
                                    b.append('\t');
                                    break;
                                default:
                                    b.append(c);
                                    break;
                            }
                        }
                    } else {
                        b.append('\f');
                    }
                } else {
                    b.append('\\');
                }
                backSlashSeen = false;
            }
        }
        return b.toString();
    }

    public static int convertRegexOptions(String flags) {
        if (flags == null) {
            return 0;
        }
        int options = 0;
        if (flags.indexOf(103) != -1) {
            options = 0 | 16;
        }
        if (flags.indexOf(105) != -1) {
            options |= 256;
        }
        if (flags.indexOf(109) != -1) {
            options |= 4096;
        }
        if (flags.indexOf(115) != -1) {
            return options | RegexpMatcher.MATCH_SINGLELINE;
        }
        return options;
    }
}
