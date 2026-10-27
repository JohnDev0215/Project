package org.apache.commons.net.nntp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Vector;
import org.apache.commons.net.MalformedServerReplyException;
import org.apache.commons.net.io.DotTerminatedMessageReader;
import org.apache.commons.net.io.DotTerminatedMessageWriter;
import org.apache.commons.net.io.Util;

/* JADX INFO: loaded from: classes3.dex */
public class NNTPClient extends NNTP {
    private void parseArticlePointer(String reply, ArticleInfo pointer) throws MalformedServerReplyException {
        String[] tokens = reply.split(" ");
        if (tokens.length >= 3) {
            int i = 1 + 1;
            try {
                pointer.articleNumber = Long.parseLong(tokens[1]);
                int i2 = i + 1;
                try {
                    pointer.articleId = tokens[i];
                    return;
                } catch (NumberFormatException e) {
                }
            } catch (NumberFormatException e2) {
            }
        }
        throw new MalformedServerReplyException("Could not parse article pointer.\nServer reply: " + reply);
    }

    private static void parseGroupReply(String reply, NewsgroupInfo info) throws MalformedServerReplyException {
        String[] tokens = reply.split(" ");
        if (tokens.length >= 5) {
            int i = 1 + 1;
            try {
                info.setArticleCount(Long.parseLong(tokens[1]));
                int i2 = i + 1;
                try {
                    info.setFirstArticle(Long.parseLong(tokens[i]));
                    int i3 = i2 + 1;
                    info.setLastArticle(Long.parseLong(tokens[i2]));
                    i2 = i3 + 1;
                    info.setNewsgroup(tokens[i3]);
                    info.setPostingPermission(0);
                    return;
                } catch (NumberFormatException e) {
                }
            } catch (NumberFormatException e2) {
            }
        }
        throw new MalformedServerReplyException("Could not parse newsgroup info.\nServer reply: " + reply);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0075  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static org.apache.commons.net.nntp.NewsgroupInfo parseNewsgroupListEntry(java.lang.String r13) {
        /*
            java.lang.String r0 = " "
            java.lang.String[] r0 = r13.split(r0)
            int r1 = r0.length
            r2 = 0
            r3 = 4
            if (r1 >= r3) goto Lc
            return r2
        Lc:
            org.apache.commons.net.nntp.NewsgroupInfo r1 = new org.apache.commons.net.nntp.NewsgroupInfo
            r1.<init>()
            r3 = 0
            int r4 = r3 + 1
            r3 = r0[r3]
            r1.setNewsgroup(r3)
            int r3 = r4 + 1
            r4 = r0[r4]     // Catch: java.lang.NumberFormatException -> L7d
            long r4 = java.lang.Long.parseLong(r4)     // Catch: java.lang.NumberFormatException -> L7d
            int r6 = r3 + 1
            r3 = r0[r3]     // Catch: java.lang.NumberFormatException -> L7b
            long r7 = java.lang.Long.parseLong(r3)     // Catch: java.lang.NumberFormatException -> L7b
            r1.setFirstArticle(r7)     // Catch: java.lang.NumberFormatException -> L7b
            r1.setLastArticle(r4)     // Catch: java.lang.NumberFormatException -> L7b
            r9 = 0
            int r3 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r3 != 0) goto L3d
            int r3 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r3 != 0) goto L3d
            r1.setArticleCount(r9)     // Catch: java.lang.NumberFormatException -> L7b
            goto L45
        L3d:
            long r9 = r4 - r7
            r11 = 1
            long r9 = r9 + r11
            r1.setArticleCount(r9)     // Catch: java.lang.NumberFormatException -> L7b
        L45:
            int r2 = r6 + 1
            r3 = r0[r6]
            r4 = 0
            char r3 = r3.charAt(r4)
            r5 = 77
            if (r3 == r5) goto L75
            r5 = 78
            if (r3 == r5) goto L70
            r5 = 89
            if (r3 == r5) goto L6b
            r5 = 121(0x79, float:1.7E-43)
            if (r3 == r5) goto L6b
            r5 = 109(0x6d, float:1.53E-43)
            if (r3 == r5) goto L75
            r5 = 110(0x6e, float:1.54E-43)
            if (r3 == r5) goto L70
            r1.setPostingPermission(r4)
            goto L7a
        L6b:
            r3 = 2
            r1.setPostingPermission(r3)
            goto L7a
        L70:
            r3 = 3
            r1.setPostingPermission(r3)
            goto L7a
        L75:
            r3 = 1
            r1.setPostingPermission(r3)
        L7a:
            return r1
        L7b:
            r3 = move-exception
            goto L80
        L7d:
            r4 = move-exception
            r6 = r3
            r3 = r4
        L80:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.net.nntp.NNTPClient.parseNewsgroupListEntry(java.lang.String):org.apache.commons.net.nntp.NewsgroupInfo");
    }

    static Article parseArticleEntry(String line) {
        Article article = new Article();
        article.setSubject(line);
        String[] parts = line.split("\t");
        if (parts.length > 6) {
            int i = 0 + 1;
            try {
                article.setArticleNumber(Long.parseLong(parts[0]));
                int i2 = i + 1;
                try {
                    article.setSubject(parts[i]);
                    int i3 = i2 + 1;
                    article.setFrom(parts[i2]);
                    int i4 = i3 + 1;
                    article.setDate(parts[i3]);
                    int i5 = i4 + 1;
                    article.setArticleId(parts[i4]);
                    i2 = i5 + 1;
                    article.addReference(parts[i5]);
                } catch (NumberFormatException e) {
                }
            } catch (NumberFormatException e2) {
            }
        }
        return article;
    }

    private NewsgroupInfo[] readNewsgroupListing() throws IOException {
        Vector<NewsgroupInfo> list = new Vector<>(2048);
        BufferedReader reader = new DotTerminatedMessageReader(this._reader_);
        while (true) {
            try {
                String line = reader.readLine();
                if (line != null) {
                    NewsgroupInfo tmp = parseNewsgroupListEntry(line);
                    if (tmp != null) {
                        list.addElement(tmp);
                    } else {
                        throw new MalformedServerReplyException(line);
                    }
                } else {
                    reader.close();
                    int size = list.size();
                    if (size < 1) {
                        return new NewsgroupInfo[0];
                    }
                    NewsgroupInfo[] info = new NewsgroupInfo[size];
                    list.copyInto(info);
                    return info;
                }
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
    }

    private BufferedReader __retrieve(int command, String articleId, ArticleInfo pointer) throws IOException {
        if (articleId != null) {
            if (!NNTPReply.isPositiveCompletion(sendCommand(command, articleId))) {
                return null;
            }
        } else if (!NNTPReply.isPositiveCompletion(sendCommand(command))) {
            return null;
        }
        if (pointer != null) {
            parseArticlePointer(getReplyString(), pointer);
        }
        return new DotTerminatedMessageReader(this._reader_);
    }

    private BufferedReader retrieve(int command, long articleNumber, ArticleInfo pointer) throws IOException {
        if (!NNTPReply.isPositiveCompletion(sendCommand(command, Long.toString(articleNumber)))) {
            return null;
        }
        if (pointer != null) {
            parseArticlePointer(getReplyString(), pointer);
        }
        return new DotTerminatedMessageReader(this._reader_);
    }

    public BufferedReader retrieveArticle(String articleId, ArticleInfo pointer) throws IOException {
        return __retrieve(0, articleId, pointer);
    }

    public Reader retrieveArticle(String articleId) throws IOException {
        return retrieveArticle(articleId, (ArticleInfo) null);
    }

    public Reader retrieveArticle() throws IOException {
        return retrieveArticle((String) null);
    }

    public BufferedReader retrieveArticle(long articleNumber, ArticleInfo pointer) throws IOException {
        return retrieve(0, articleNumber, pointer);
    }

    public BufferedReader retrieveArticle(long articleNumber) throws IOException {
        return retrieveArticle(articleNumber, (ArticleInfo) null);
    }

    public BufferedReader retrieveArticleHeader(String articleId, ArticleInfo pointer) throws IOException {
        return __retrieve(3, articleId, pointer);
    }

    public Reader retrieveArticleHeader(String articleId) throws IOException {
        return retrieveArticleHeader(articleId, (ArticleInfo) null);
    }

    public Reader retrieveArticleHeader() throws IOException {
        return retrieveArticleHeader((String) null);
    }

    public BufferedReader retrieveArticleHeader(long articleNumber, ArticleInfo pointer) throws IOException {
        return retrieve(3, articleNumber, pointer);
    }

    public BufferedReader retrieveArticleHeader(long articleNumber) throws IOException {
        return retrieveArticleHeader(articleNumber, (ArticleInfo) null);
    }

    public BufferedReader retrieveArticleBody(String articleId, ArticleInfo pointer) throws IOException {
        return __retrieve(1, articleId, pointer);
    }

    public Reader retrieveArticleBody(String articleId) throws IOException {
        return retrieveArticleBody(articleId, (ArticleInfo) null);
    }

    public Reader retrieveArticleBody() throws IOException {
        return retrieveArticleBody((String) null);
    }

    public BufferedReader retrieveArticleBody(long articleNumber, ArticleInfo pointer) throws IOException {
        return retrieve(1, articleNumber, pointer);
    }

    public BufferedReader retrieveArticleBody(long articleNumber) throws IOException {
        return retrieveArticleBody(articleNumber, (ArticleInfo) null);
    }

    public boolean selectNewsgroup(String newsgroup, NewsgroupInfo info) throws IOException {
        if (!NNTPReply.isPositiveCompletion(group(newsgroup))) {
            return false;
        }
        if (info != null) {
            parseGroupReply(getReplyString(), info);
            return true;
        }
        return true;
    }

    public boolean selectNewsgroup(String newsgroup) throws IOException {
        return selectNewsgroup(newsgroup, null);
    }

    public String listHelp() throws IOException {
        if (!NNTPReply.isInformational(help())) {
            return null;
        }
        StringWriter help = new StringWriter();
        try {
            BufferedReader reader = new DotTerminatedMessageReader(this._reader_);
            try {
                Util.copyReader(reader, help);
                String string = help.toString();
                reader.close();
                help.close();
                return string;
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    help.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    public String[] listOverviewFmt() throws IOException {
        if (!NNTPReply.isPositiveCompletion(sendCommand("LIST", "OVERVIEW.FMT"))) {
            return null;
        }
        BufferedReader reader = new DotTerminatedMessageReader(this._reader_);
        try {
            ArrayList<String> list = new ArrayList<>();
            while (true) {
                String line = reader.readLine();
                if (line != null) {
                    list.add(line);
                } else {
                    String[] strArr = (String[]) list.toArray(new String[list.size()]);
                    reader.close();
                    return strArr;
                }
            }
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

    public boolean selectArticle(String articleId, ArticleInfo pointer) throws IOException {
        if (articleId != null) {
            if (!NNTPReply.isPositiveCompletion(stat(articleId))) {
                return false;
            }
        } else if (!NNTPReply.isPositiveCompletion(stat())) {
            return false;
        }
        if (pointer != null) {
            parseArticlePointer(getReplyString(), pointer);
            return true;
        }
        return true;
    }

    public boolean selectArticle(String articleId) throws IOException {
        return selectArticle(articleId, (ArticleInfo) null);
    }

    public boolean selectArticle(ArticleInfo pointer) throws IOException {
        return selectArticle((String) null, pointer);
    }

    public boolean selectArticle(long articleNumber, ArticleInfo pointer) throws IOException {
        if (!NNTPReply.isPositiveCompletion(stat(articleNumber))) {
            return false;
        }
        if (pointer != null) {
            parseArticlePointer(getReplyString(), pointer);
            return true;
        }
        return true;
    }

    public boolean selectArticle(long articleNumber) throws IOException {
        return selectArticle(articleNumber, (ArticleInfo) null);
    }

    public boolean selectPreviousArticle(ArticleInfo pointer) throws IOException {
        if (!NNTPReply.isPositiveCompletion(last())) {
            return false;
        }
        if (pointer != null) {
            parseArticlePointer(getReplyString(), pointer);
            return true;
        }
        return true;
    }

    public boolean selectPreviousArticle() throws IOException {
        return selectPreviousArticle((ArticleInfo) null);
    }

    public boolean selectNextArticle(ArticleInfo pointer) throws IOException {
        if (!NNTPReply.isPositiveCompletion(next())) {
            return false;
        }
        if (pointer != null) {
            parseArticlePointer(getReplyString(), pointer);
            return true;
        }
        return true;
    }

    public boolean selectNextArticle() throws IOException {
        return selectNextArticle((ArticleInfo) null);
    }

    public NewsgroupInfo[] listNewsgroups() throws IOException {
        if (!NNTPReply.isPositiveCompletion(list())) {
            return null;
        }
        return readNewsgroupListing();
    }

    public Iterable<String> iterateNewsgroupListing() throws IOException {
        if (NNTPReply.isPositiveCompletion(list())) {
            return new ReplyIterator(this._reader_);
        }
        throw new IOException("LIST command failed: " + getReplyString());
    }

    public Iterable<NewsgroupInfo> iterateNewsgroups() throws IOException {
        return new NewsgroupIterator(iterateNewsgroupListing());
    }

    public NewsgroupInfo[] listNewsgroups(String wildmat) throws IOException {
        if (!NNTPReply.isPositiveCompletion(listActive(wildmat))) {
            return null;
        }
        return readNewsgroupListing();
    }

    public Iterable<String> iterateNewsgroupListing(String wildmat) throws IOException {
        if (NNTPReply.isPositiveCompletion(listActive(wildmat))) {
            return new ReplyIterator(this._reader_);
        }
        throw new IOException("LIST ACTIVE " + wildmat + " command failed: " + getReplyString());
    }

    public Iterable<NewsgroupInfo> iterateNewsgroups(String wildmat) throws IOException {
        return new NewsgroupIterator(iterateNewsgroupListing(wildmat));
    }

    public NewsgroupInfo[] listNewNewsgroups(NewGroupsOrNewsQuery query) throws IOException {
        if (!NNTPReply.isPositiveCompletion(newgroups(query.getDate(), query.getTime(), query.isGMT(), query.getDistributions()))) {
            return null;
        }
        return readNewsgroupListing();
    }

    public Iterable<String> iterateNewNewsgroupListing(NewGroupsOrNewsQuery query) throws IOException {
        if (NNTPReply.isPositiveCompletion(newgroups(query.getDate(), query.getTime(), query.isGMT(), query.getDistributions()))) {
            return new ReplyIterator(this._reader_);
        }
        throw new IOException("NEWGROUPS command failed: " + getReplyString());
    }

    public Iterable<NewsgroupInfo> iterateNewNewsgroups(NewGroupsOrNewsQuery query) throws IOException {
        return new NewsgroupIterator(iterateNewNewsgroupListing(query));
    }

    public String[] listNewNews(NewGroupsOrNewsQuery query) throws IOException {
        if (!NNTPReply.isPositiveCompletion(newnews(query.getNewsgroups(), query.getDate(), query.getTime(), query.isGMT(), query.getDistributions()))) {
            return null;
        }
        Vector<String> list = new Vector<>();
        BufferedReader reader = new DotTerminatedMessageReader(this._reader_);
        while (true) {
            try {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                list.addElement(line);
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
        reader.close();
        int size = list.size();
        if (size < 1) {
            return new String[0];
        }
        String[] result = new String[size];
        list.copyInto(result);
        return result;
    }

    public Iterable<String> iterateNewNews(NewGroupsOrNewsQuery query) throws IOException {
        if (NNTPReply.isPositiveCompletion(newnews(query.getNewsgroups(), query.getDate(), query.getTime(), query.isGMT(), query.getDistributions()))) {
            return new ReplyIterator(this._reader_);
        }
        throw new IOException("NEWNEWS command failed: " + getReplyString());
    }

    public boolean completePendingCommand() throws IOException {
        return NNTPReply.isPositiveCompletion(getReply());
    }

    public Writer postArticle() throws IOException {
        if (!NNTPReply.isPositiveIntermediate(post())) {
            return null;
        }
        return new DotTerminatedMessageWriter(this._writer_);
    }

    public Writer forwardArticle(String articleId) throws IOException {
        if (!NNTPReply.isPositiveIntermediate(ihave(articleId))) {
            return null;
        }
        return new DotTerminatedMessageWriter(this._writer_);
    }

    public boolean logout() throws IOException {
        return NNTPReply.isPositiveCompletion(quit());
    }

    public boolean authenticate(String username, String password) throws IOException {
        int replyCode = authinfoUser(username);
        if (replyCode == 381) {
            int replyCode2 = authinfoPass(password);
            if (replyCode2 == 281) {
                this._isAllowedToPost = true;
                return true;
            }
            return false;
        }
        return false;
    }

    private BufferedReader __retrieveArticleInfo(String articleRange) throws IOException {
        if (!NNTPReply.isPositiveCompletion(xover(articleRange))) {
            return null;
        }
        return new DotTerminatedMessageReader(this._reader_);
    }

    public BufferedReader retrieveArticleInfo(long articleNumber) throws IOException {
        return __retrieveArticleInfo(Long.toString(articleNumber));
    }

    public BufferedReader retrieveArticleInfo(long lowArticleNumber, long highArticleNumber) throws IOException {
        return __retrieveArticleInfo(lowArticleNumber + "-" + highArticleNumber);
    }

    public Iterable<Article> iterateArticleInfo(long lowArticleNumber, long highArticleNumber) throws IOException {
        BufferedReader info = retrieveArticleInfo(lowArticleNumber, highArticleNumber);
        if (info == null) {
            throw new IOException("XOVER command failed: " + getReplyString());
        }
        return new ArticleIterator(new ReplyIterator(info, false));
    }

    private BufferedReader __retrieveHeader(String header, String articleRange) throws IOException {
        if (!NNTPReply.isPositiveCompletion(xhdr(header, articleRange))) {
            return null;
        }
        return new DotTerminatedMessageReader(this._reader_);
    }

    public BufferedReader retrieveHeader(String header, long articleNumber) throws IOException {
        return __retrieveHeader(header, Long.toString(articleNumber));
    }

    public BufferedReader retrieveHeader(String header, long lowArticleNumber, long highArticleNumber) throws IOException {
        return __retrieveHeader(header, lowArticleNumber + "-" + highArticleNumber);
    }

    @Deprecated
    public Reader retrieveHeader(String header, int lowArticleNumber, int highArticleNumber) throws IOException {
        return retrieveHeader(header, lowArticleNumber, highArticleNumber);
    }

    @Deprecated
    public Reader retrieveArticleInfo(int lowArticleNumber, int highArticleNumber) throws IOException {
        return retrieveArticleInfo(lowArticleNumber, highArticleNumber);
    }

    @Deprecated
    public Reader retrieveHeader(String a, int b) throws IOException {
        return retrieveHeader(a, b);
    }

    @Deprecated
    public boolean selectArticle(int a, ArticlePointer ap) throws IOException {
        ArticleInfo ai = ap2ai(ap);
        boolean b = selectArticle(a, ai);
        ai2ap(ai, ap);
        return b;
    }

    @Deprecated
    public Reader retrieveArticleInfo(int lowArticleNumber) throws IOException {
        return retrieveArticleInfo(lowArticleNumber);
    }

    @Deprecated
    public boolean selectArticle(int a) throws IOException {
        return selectArticle(a);
    }

    @Deprecated
    public Reader retrieveArticleHeader(int a) throws IOException {
        return retrieveArticleHeader(a);
    }

    @Deprecated
    public Reader retrieveArticleHeader(int a, ArticlePointer ap) throws IOException {
        ArticleInfo ai = ap2ai(ap);
        Reader rdr = retrieveArticleHeader(a, ai);
        ai2ap(ai, ap);
        return rdr;
    }

    @Deprecated
    public Reader retrieveArticleBody(int a) throws IOException {
        return retrieveArticleBody(a);
    }

    @Deprecated
    public Reader retrieveArticle(int articleNumber, ArticlePointer pointer) throws IOException {
        ArticleInfo ai = ap2ai(pointer);
        Reader rdr = retrieveArticle(articleNumber, ai);
        ai2ap(ai, pointer);
        return rdr;
    }

    @Deprecated
    public Reader retrieveArticle(int articleNumber) throws IOException {
        return retrieveArticle(articleNumber);
    }

    @Deprecated
    public Reader retrieveArticleBody(int a, ArticlePointer ap) throws IOException {
        ArticleInfo ai = ap2ai(ap);
        Reader rdr = retrieveArticleBody(a, ai);
        ai2ap(ai, ap);
        return rdr;
    }

    @Deprecated
    public Reader retrieveArticle(String articleId, ArticlePointer pointer) throws IOException {
        ArticleInfo ai = ap2ai(pointer);
        Reader rdr = retrieveArticle(articleId, ai);
        ai2ap(ai, pointer);
        return rdr;
    }

    @Deprecated
    public Reader retrieveArticleBody(String articleId, ArticlePointer pointer) throws IOException {
        ArticleInfo ai = ap2ai(pointer);
        Reader rdr = retrieveArticleBody(articleId, ai);
        ai2ap(ai, pointer);
        return rdr;
    }

    @Deprecated
    public Reader retrieveArticleHeader(String articleId, ArticlePointer pointer) throws IOException {
        ArticleInfo ai = ap2ai(pointer);
        Reader rdr = retrieveArticleHeader(articleId, ai);
        ai2ap(ai, pointer);
        return rdr;
    }

    @Deprecated
    public boolean selectArticle(String articleId, ArticlePointer pointer) throws IOException {
        ArticleInfo ai = ap2ai(pointer);
        boolean b = selectArticle(articleId, ai);
        ai2ap(ai, pointer);
        return b;
    }

    @Deprecated
    public boolean selectArticle(ArticlePointer pointer) throws IOException {
        ArticleInfo ai = ap2ai(pointer);
        boolean b = selectArticle(ai);
        ai2ap(ai, pointer);
        return b;
    }

    @Deprecated
    public boolean selectNextArticle(ArticlePointer pointer) throws IOException {
        ArticleInfo ai = ap2ai(pointer);
        boolean b = selectNextArticle(ai);
        ai2ap(ai, pointer);
        return b;
    }

    @Deprecated
    public boolean selectPreviousArticle(ArticlePointer pointer) throws IOException {
        ArticleInfo ai = ap2ai(pointer);
        boolean b = selectPreviousArticle(ai);
        ai2ap(ai, pointer);
        return b;
    }

    private ArticleInfo ap2ai(ArticlePointer ap) {
        if (ap == null) {
            return null;
        }
        ArticleInfo ai = new ArticleInfo();
        return ai;
    }

    private void ai2ap(ArticleInfo ai, ArticlePointer ap) {
        if (ap != null) {
            ap.articleId = ai.articleId;
            ap.articleNumber = (int) ai.articleNumber;
        }
    }
}
