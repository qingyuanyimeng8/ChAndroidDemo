package com.example.hotfix.hotfixapplication.ui.mvp.bean;

import java.util.List;

/**
 * 1. 类的用途
 */


public class NewsInfo {

    /**
     * data : [{"summary":"","img":"http://mnews.gw.com.cn/wap/style/data/news/txs/images/1521096359838.jpg","resType":"","advTypeShare":"","source":"","title":"机构推荐：周五具备布局潜力金股","otime":"2018-03-15 14:46:00","url":"http://mnews.gw.com.cn/wap/data/news/txs/2018/03/254190.json","id":"254190","countid":20009,"views":"18946"},{"summary":"","img":"http://mnews.gw.com.cn/wap/style/data/news/txs/images/1521096271863.jpg","resType":"","advTypeShare":"","source":"","title":"两股直线涨停带动次新股回暖，跌停股大幅减少","otime":"2018-03-15 14:44:33","url":"http://mnews.gw.com.cn/wap/data/news/txs/2018/03/254189.json","id":"254189","countid":20009,"views":"10903"},{"summary":"","img":"http://mnews.gw.com.cn/wap/style/data/news/txs/images/1521096172750.jpg","resType":"","advTypeShare":"","source":"","title":"申万宏源：宁德时代IPO进程有望提速，多只股或受益(名单)","otime":"2018-03-15 14:42:53","url":"http://mnews.gw.com.cn/wap/data/news/txs/2018/03/254188.json","id":"254188","countid":20009,"views":"4753"},{"summary":"","img":"http://mnews.gw.com.cn/wap/style/data/news/txs/images/1521094199166.jpg","resType":"","advTypeShare":"","source":"","title":"独角兽概念股绝地大反攻，合肥城建封死涨停","otime":"2018-03-15 14:10:00","url":"http://mnews.gw.com.cn/wap/data/news/txs/2018/03/254185.json","id":"254185","countid":20009,"views":"18487"},{"summary":"","img":"http://mnews.gw.com.cn/wap/style/data/news/txs/images/1521092169595.jpg","resType":"","advTypeShare":"","source":"","title":"午间机构研报精选，10股值得关注","otime":"2018-03-15 13:36:10","url":"http://mnews.gw.com.cn/wap/data/news/txs/2018/03/254180.json","id":"254180","countid":20009,"views":"31972"},{"summary":"","img":"http://mnews.gw.com.cn/wap/style/data/news/txs/images/1521092225623.jpg","resType":"","advTypeShare":"","source":"","title":"养元饮品午后一度大跌5%，跌破发行价","otime":"2018-03-15 13:35:13","url":"http://mnews.gw.com.cn/wap/data/news/txs/2018/03/254179.json","id":"254179","countid":20009,"views":"19538"},{"summary":"","img":"http://mnews.gw.com.cn/wap/style/data/news/txs/images/1521092005018.jpg","resType":"","advTypeShare":"","source":"","title":"乐视控股高层称：乐视网不可能退市，孙宏斌辞职正常","otime":"2018-03-15 13:30:22","url":"http://mnews.gw.com.cn/wap/data/news/txs/2018/03/254178.json","id":"254178","countid":20009,"views":"13311"},{"summary":"","img":"http://mnews.gw.com.cn/wap/style/data/news/txs/images/1521091688019.jpg","resType":"","advTypeShare":"","source":"","title":"次新股午后进一步下挫，华菱精工等近20股跌停","otime":"2018-03-15 13:28:08","url":"http://mnews.gw.com.cn/wap/data/news/txs/2018/03/254177.json","id":"254177","countid":20009,"views":"14688"},{"summary":"","img":"http://mnews.gw.com.cn/wap/style/data/news/txs/images/1521091450760.jpg","resType":"","advTypeShare":"","source":"","title":"天价罚单吓倒次新股一大片？","otime":"2018-03-15 13:24:15","url":"http://mnews.gw.com.cn/wap/data/news/txs/2018/03/254176.json","id":"254176","countid":20009,"views":"18410"},{"summary":"","img":"http://mnews.gw.com.cn/wap/style/data/news/txs/images/1521091169793.jpg","resType":"","advTypeShare":"","source":"","title":"高送转概念股杀跌，今飞凯达等5股跌停","otime":"2018-03-15 13:19:30","url":"http://mnews.gw.com.cn/wap/data/news/txs/2018/03/254175.json","id":"254175","countid":20009,"views":"9542"},{"summary":"","img":"http://mnews.gw.com.cn/wap/style/data/news/txs/images/1521090673296.jpg","resType":"","advTypeShare":"","source":"","title":"油改概念股走强，仁智股份冲击涨停","otime":"2018-03-15 13:11:17","url":"http://mnews.gw.com.cn/wap/data/news/txs/2018/03/254174.json","id":"254174","countid":20009,"views":"7450"},{"summary":"","img":"http://mnews.gw.com.cn/wap/style/data/news/txs/images/1521089592024.jpg","resType":"","advTypeShare":"","source":"","title":"撤销公司股票交易退市风险警示，大东海A封涨停","otime":"2018-03-15 12:53:13","url":"http://mnews.gw.com.cn/wap/data/news/txs/2018/03/254172.json","id":"254172","countid":20009,"views":"16735"},{"summary":"","img":"http://mnews.gw.com.cn/wap/style/data/news/txs/images/1521089520565.jpg","resType":"","advTypeShare":"","source":"","title":"聚碳酸酯意外涨价，两家A股公司或受益","otime":"2018-03-15 12:52:01","url":"http://mnews.gw.com.cn/wap/data/news/txs/2018/03/254171.json","id":"254171","countid":20009,"views":"10588"},{"summary":"","img":"http://mnews.gw.com.cn/wap/style/data/news/txs/images/1521089434125.jpg","resType":"","advTypeShare":"","source":"","title":"机构：宁德时代IPO进程有望提速，多股或受益","otime":"2018-03-15 12:50:38","url":"http://mnews.gw.com.cn/wap/data/news/txs/2018/03/254170.json","id":"254170","countid":20009,"views":"15430"},{"summary":"","img":"http://mnews.gw.com.cn/wap/style/data/news/txs/images/1521087439113.jpg","resType":"","advTypeShare":"","source":"","title":"3·15后消费股易走弱？ 北上资金已提前加仓","otime":"2018-03-15 12:17:19","url":"http://mnews.gw.com.cn/wap/data/news/txs/2018/03/254169.json","id":"254169","countid":20009,"views":"14668"},{"summary":"","img":"http://mnews.gw.com.cn/wap/style/data/news/txs/images/1521087381980.jpg","resType":"","advTypeShare":"","source":"","title":"名博午后看市：大盘正在向着阳包阴进军","otime":"2018-03-15 12:16:22","url":"http://mnews.gw.com.cn/wap/data/news/txs/2018/03/254168.json","id":"254168","countid":20009,"views":"25182"},{"summary":"","img":"http://mnews.gw.com.cn/wap/style/data/news/txs/images/1521086504186.jpg","resType":"","advTypeShare":"","source":"","title":"【机构】两大利空制约大盘反弹，新主线浮出水面","otime":"2018-03-15 12:01:44","url":"http://mnews.gw.com.cn/wap/data/news/txs/2018/03/254165.json","id":"254165","countid":20009,"views":"123646"},{"summary":"","img":"http://mnews.gw.com.cn/wap/style/data/news/txs/images/1521086443783.jpg","resType":"","advTypeShare":"","source":"","title":"336股获买入评级，20只股或涨超50%","otime":"2018-03-15 12:00:44","url":"http://mnews.gw.com.cn/wap/data/news/txs/2018/03/254164.json","id":"254164","countid":20009,"views":"21664"},{"summary":"","img":"http://mnews.gw.com.cn/wap/style/data/news/txs/images/1521086193316.jpg","resType":"","advTypeShare":"","source":"","title":"三大黑手打压A股，游资上演绝地大逃亡","otime":"2018-03-15 11:56:34","url":"http://mnews.gw.com.cn/wap/data/news/txs/2018/03/254163.json","id":"254163","countid":20009,"views":"29672"},{"summary":"","img":"http://mnews.gw.com.cn/wap/style/data/news/txs/images/1521083593571.jpg","resType":"","advTypeShare":"","source":"","title":"电力股异动，华电能源涨停带动电力股大涨","otime":"2018-03-15 11:13:15","url":"http://mnews.gw.com.cn/wap/data/news/txs/2018/03/254157.json","id":"254157","countid":20009,"views":"28330"}]
     * header : {"totalpage":10,"totalsize":186,"next":"page_2.json","pre":"page_1.json","last":"page_10.json","pagesize":20,"first":"page_1.json"}
     */

    private HeaderBean header;
    private List<DataBean> data;

    public HeaderBean getHeader() {
        return header;
    }

    public void setHeader(HeaderBean header) {
        this.header = header;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class HeaderBean {
        /**
         * totalpage : 10
         * totalsize : 186
         * next : page_2.json
         * pre : page_1.json
         * last : page_10.json
         * pagesize : 20
         * first : page_1.json
         */

        private int totalpage;
        private int totalsize;
        private String next;
        private String pre;
        private String last;
        private int pagesize;
        private String first;

        public int getTotalpage() {
            return totalpage;
        }

        public void setTotalpage(int totalpage) {
            this.totalpage = totalpage;
        }

        public int getTotalsize() {
            return totalsize;
        }

        public void setTotalsize(int totalsize) {
            this.totalsize = totalsize;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public String getPre() {
            return pre;
        }

        public void setPre(String pre) {
            this.pre = pre;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }

        public int getPagesize() {
            return pagesize;
        }

        public void setPagesize(int pagesize) {
            this.pagesize = pagesize;
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }
    }

    public static class DataBean {
        /**
         * summary :
         * img : http://mnews.gw.com.cn/wap/style/data/news/txs/images/1521096359838.jpg
         * resType :
         * advTypeShare :
         * source :
         * title : 机构推荐：周五具备布局潜力金股
         * otime : 2018-03-15 14:46:00
         * url : http://mnews.gw.com.cn/wap/data/news/txs/2018/03/254190.json
         * id : 254190
         * countid : 20009
         * views : 18946
         */

        private String summary;
        private String img;
        private String resType;
        private String advTypeShare;
        private String source;
        private String title;
        private String otime;
        private String url;
        private String id;
        private int countid;
        private String views;

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getResType() {
            return resType;
        }

        public void setResType(String resType) {
            this.resType = resType;
        }

        public String getAdvTypeShare() {
            return advTypeShare;
        }

        public void setAdvTypeShare(String advTypeShare) {
            this.advTypeShare = advTypeShare;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getOtime() {
            return otime;
        }

        public void setOtime(String otime) {
            this.otime = otime;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getCountid() {
            return countid;
        }

        public void setCountid(int countid) {
            this.countid = countid;
        }

        public String getViews() {
            return views;
        }

        public void setViews(String views) {
            this.views = views;
        }
    }
}
