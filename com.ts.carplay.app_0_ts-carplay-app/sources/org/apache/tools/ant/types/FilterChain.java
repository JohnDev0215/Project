package org.apache.tools.ant.types;

import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.filters.ChainableReader;
import org.apache.tools.ant.filters.ClassConstants;
import org.apache.tools.ant.filters.EscapeUnicode;
import org.apache.tools.ant.filters.ExpandProperties;
import org.apache.tools.ant.filters.HeadFilter;
import org.apache.tools.ant.filters.LineContains;
import org.apache.tools.ant.filters.LineContainsRegExp;
import org.apache.tools.ant.filters.PrefixLines;
import org.apache.tools.ant.filters.ReplaceTokens;
import org.apache.tools.ant.filters.StripJavaComments;
import org.apache.tools.ant.filters.StripLineBreaks;
import org.apache.tools.ant.filters.StripLineComments;
import org.apache.tools.ant.filters.TabsToSpaces;
import org.apache.tools.ant.filters.TailFilter;
import org.apache.tools.ant.filters.TokenFilter;

/* JADX INFO: loaded from: classes.dex */
public class FilterChain extends DataType implements Cloneable {
    private Vector filterReaders = new Vector();

    public final void addFilterReader(AntFilterReader filterReader) {
        this.filterReaders.addElement(filterReader);
    }

    public final Vector getFilterReaders() {
        return this.filterReaders;
    }

    public final void addClassConstants(ClassConstants classConstants) {
        this.filterReaders.addElement(classConstants);
    }

    public final void addExpandProperties(ExpandProperties expandProperties) {
        this.filterReaders.addElement(expandProperties);
    }

    public final void addHeadFilter(HeadFilter headFilter) {
        this.filterReaders.addElement(headFilter);
    }

    public final void addLineContains(LineContains lineContains) {
        this.filterReaders.addElement(lineContains);
    }

    public final void addLineContainsRegExp(LineContainsRegExp lineContainsRegExp) {
        this.filterReaders.addElement(lineContainsRegExp);
    }

    public final void addPrefixLines(PrefixLines prefixLines) {
        this.filterReaders.addElement(prefixLines);
    }

    public final void addReplaceTokens(ReplaceTokens replaceTokens) {
        this.filterReaders.addElement(replaceTokens);
    }

    public final void addStripJavaComments(StripJavaComments stripJavaComments) {
        this.filterReaders.addElement(stripJavaComments);
    }

    public final void addStripLineBreaks(StripLineBreaks stripLineBreaks) {
        this.filterReaders.addElement(stripLineBreaks);
    }

    public final void addStripLineComments(StripLineComments stripLineComments) {
        this.filterReaders.addElement(stripLineComments);
    }

    public final void addTabsToSpaces(TabsToSpaces tabsToSpaces) {
        this.filterReaders.addElement(tabsToSpaces);
    }

    public final void addTailFilter(TailFilter tailFilter) {
        this.filterReaders.addElement(tailFilter);
    }

    public final void addEscapeUnicode(EscapeUnicode escapeUnicode) {
        this.filterReaders.addElement(escapeUnicode);
    }

    public final void addTokenFilter(TokenFilter tokenFilter) {
        this.filterReaders.addElement(tokenFilter);
    }

    public void addDeleteCharacters(TokenFilter.DeleteCharacters filter) {
        this.filterReaders.addElement(filter);
    }

    public void addContainsRegex(TokenFilter.ContainsRegex filter) {
        this.filterReaders.addElement(filter);
    }

    public void addReplaceRegex(TokenFilter.ReplaceRegex filter) {
        this.filterReaders.addElement(filter);
    }

    public void addTrim(TokenFilter.Trim filter) {
        this.filterReaders.addElement(filter);
    }

    public void addReplaceString(TokenFilter.ReplaceString filter) {
        this.filterReaders.addElement(filter);
    }

    public void addIgnoreBlank(TokenFilter.IgnoreBlank filter) {
        this.filterReaders.addElement(filter);
    }

    @Override // org.apache.tools.ant.types.DataType
    public void setRefid(Reference r) throws BuildException {
        if (!this.filterReaders.isEmpty()) {
            throw tooManyAttributes();
        }
        Object o = r.getReferencedObject(getProject());
        if (o instanceof FilterChain) {
            FilterChain fc = (FilterChain) o;
            this.filterReaders = fc.getFilterReaders();
            super.setRefid(r);
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(r.getRefId());
            stringBuffer.append(" doesn't refer to a FilterChain");
            String msg = stringBuffer.toString();
            throw new BuildException(msg);
        }
    }

    public void add(ChainableReader filter) {
        this.filterReaders.addElement(filter);
    }
}
