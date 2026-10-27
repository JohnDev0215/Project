package org.apache.tools.ant.types;

import java.util.Enumeration;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class FilterSetCollection {
    private Vector filterSets = new Vector();

    public FilterSetCollection() {
    }

    public FilterSetCollection(FilterSet filterSet) {
        addFilterSet(filterSet);
    }

    public void addFilterSet(FilterSet filterSet) {
        this.filterSets.addElement(filterSet);
    }

    public String replaceTokens(String line) {
        String replacedLine = line;
        Enumeration e = this.filterSets.elements();
        while (e.hasMoreElements()) {
            FilterSet filterSet = (FilterSet) e.nextElement();
            replacedLine = filterSet.replaceTokens(replacedLine);
        }
        return replacedLine;
    }

    public boolean hasFilters() {
        Enumeration e = this.filterSets.elements();
        while (e.hasMoreElements()) {
            FilterSet filterSet = (FilterSet) e.nextElement();
            if (filterSet.hasFilters()) {
                return true;
            }
        }
        return false;
    }
}
