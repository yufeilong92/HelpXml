package com.lawyee.helpxml.bean;

import java.util.List;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V1.0.xxxxxxxx
 * @Package com.laywee.lawyeehelper2.bean
 * @Description: 实用工具Bean类
 * @author: czq
 * @date: 2017/3/21 16:10
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

public class ToolNodeBean {

    /**
     * text : 量刑指导工具
     * leaf : true
     * items : [{"text":"一般损害赔偿","leaf":"true"},{"text":"因伤致残损害赔偿","leaf":"true"},{"text":"死亡损害赔偿","leaf":"true"},{"text":"交强险责任分担计算","leaf":"true"}]
     */

    private String text;
    private String leaf;
    private List<ToolNodeBean> items;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLeaf() {
        return leaf;
    }

    public void setLeaf(String leaf) {
        this.leaf = leaf;
    }

    public List<ToolNodeBean> getItems() {
        return items;
    }

    public void setItems(List<ToolNodeBean> items) {
        this.items = items;
    }

}
