package com.test.wework;

public class WeworkConfig {

    public String corpid="wwad5387da586768d8";
    public String agentId="1000002";
    public String Secret="MEsk6ytKetMfjkehk8T2GoCTh6Zbmq1KbIYxurO93lQ";
    public String contactSecret="ALiaGD6WodorwCRqLITgAS4JwLo7cNNTeQ0zeeqxM98";

    //单例模式
    public static WeworkConfig weworkConfig;
    public static WeworkConfig getInstance() {
        if(weworkConfig==null){
            weworkConfig = new WeworkConfig();
        }
        return weworkConfig;
    }
}
