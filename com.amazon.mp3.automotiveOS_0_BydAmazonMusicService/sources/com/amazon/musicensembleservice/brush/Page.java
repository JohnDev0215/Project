package com.amazon.musicensembleservice.brush;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class Page implements Comparable<Page> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.Page");
    private String blockRef;
    private Content content;
    private Entity entity;
    private List<Filter> filters;
    private Header header;
    private String subTitle;
    private String title;
    private String uri;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Page page) {
        if (page == null) {
            return -1;
        }
        if (page == this) {
            return 0;
        }
        String uri = getUri();
        String uri2 = page.getUri();
        if (uri != uri2) {
            if (uri == null) {
                return -1;
            }
            if (uri2 == null) {
                return 1;
            }
            int iCompareTo = uri.compareTo(uri2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Content content = getContent();
        Content content2 = page.getContent();
        if (content != content2) {
            if (content == null) {
                return -1;
            }
            if (content2 == null) {
                return 1;
            }
            int iCompareTo2 = content.compareTo(content2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Header header = getHeader();
        Header header2 = page.getHeader();
        if (header != header2) {
            if (header == null) {
                return -1;
            }
            if (header2 == null) {
                return 1;
            }
            int iCompareTo3 = header.compareTo(header2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String title = getTitle();
        String title2 = page.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo4 = title.compareTo(title2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Entity entity = getEntity();
        Entity entity2 = page.getEntity();
        if (entity != entity2) {
            if (entity == null) {
                return -1;
            }
            if (entity2 == null) {
                return 1;
            }
            int iCompareTo5 = entity.compareTo(entity2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String subTitle = getSubTitle();
        String subTitle2 = page.getSubTitle();
        if (subTitle != subTitle2) {
            if (subTitle == null) {
                return -1;
            }
            if (subTitle2 == null) {
                return 1;
            }
            int iCompareTo6 = subTitle.compareTo(subTitle2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String blockRef = getBlockRef();
        String blockRef2 = page.getBlockRef();
        if (blockRef != blockRef2) {
            if (blockRef == null) {
                return -1;
            }
            if (blockRef2 == null) {
                return 1;
            }
            int iCompareTo7 = blockRef.compareTo(blockRef2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        List<Filter> filters = getFilters();
        List<Filter> filters2 = page.getFilters();
        if (filters != filters2) {
            if (filters == null) {
                return -1;
            }
            if (filters2 == null) {
                return 1;
            }
            if (filters instanceof Comparable) {
                int iCompareTo8 = ((Comparable) filters).compareTo(filters2);
                if (iCompareTo8 != 0) {
                    return iCompareTo8;
                }
            } else if (!filters.equals(filters2)) {
                int iHashCode = filters.hashCode();
                int iHashCode2 = filters2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Page)) {
            return false;
        }
        Page page = (Page) obj;
        return internalEqualityCheck(getUri(), page.getUri()) && internalEqualityCheck(getContent(), page.getContent()) && internalEqualityCheck(getHeader(), page.getHeader()) && internalEqualityCheck(getTitle(), page.getTitle()) && internalEqualityCheck(getEntity(), page.getEntity()) && internalEqualityCheck(getSubTitle(), page.getSubTitle()) && internalEqualityCheck(getBlockRef(), page.getBlockRef()) && internalEqualityCheck(getFilters(), page.getFilters());
    }

    public String getBlockRef() {
        return this.blockRef;
    }

    public Content getContent() {
        return this.content;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public List<Filter> getFilters() {
        return this.filters;
    }

    public Header getHeader() {
        return this.header;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public String getTitle() {
        return this.title;
    }

    public String getUri() {
        return this.uri;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getUri(), getContent(), getHeader(), getTitle(), getEntity(), getSubTitle(), getBlockRef(), getFilters());
    }

    public void setBlockRef(String str) {
        this.blockRef = str;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public void setFilters(List<Filter> list) {
        this.filters = list;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public void setSubTitle(String str) {
        this.subTitle = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setUri(String str) {
        this.uri = str;
    }
}
