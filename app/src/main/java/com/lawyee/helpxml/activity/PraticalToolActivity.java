package com.lawyee.helpxml.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lawyee.helpxml.R;
import com.lawyee.helpxml.adpater.TreeViewHolder;
import com.lawyee.helpxml.base.BaseActivity;
import com.lawyee.helpxml.bean.ToolNodeBean;
import com.lawyee.helpxml.util.GetJosnUtil;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.List;

import butterknife.Bind;

import static com.lawyee.helpxml.R.id.container;
import static com.lawyee.helpxml.R.id.toolbar;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V1.0.xxxxxxxx
 * @Title:PraticalToolActivity
 * @Package com.laywee.awyeehelper2.activity
 * @Description: 实务辅助工具界面Activity
 * @author: czq
 * @date: 2017/3/17 10:40
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: ${year} www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class PraticalToolActivity extends BaseActivity {

    @Bind(R.id.toolbar_title)
    TextView mToolbarTitle;
    @Bind(toolbar)
    Toolbar mToolbar;
    @Bind(container)
    RelativeLayout mContainer;


    private List<ToolNodeBean> mList; //实务工具的list
    private Context mContext;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_pratical_tool;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        initToolbar(mToolbar,mToolbarTitle,this.getResources().getString(R.string.practice_tool),1);
        initTreeView();
    }
//绑定数据到TreeView上
    private void initTreeView() {

        mList = GetJosnUtil.getjson(mContext);
        TreeNode root = TreeNode.root();
        getTextName(root,mList);
        AndroidTreeView tView = new AndroidTreeView(mContext, root);
        tView.setDefaultAnimation(true);
        tView.setDefaultContainerStyle(R.style.TreeNodeStyleCustom);
        tView.setDefaultViewHolder(TreeViewHolder.class);
        tView.setDefaultNodeClickListener(nodeClickListener);
        mContainer.addView(tView.getView());
    }
/**
 * @Description: 递归树形数据，并添加上来
 * @param: root（节点）
 * @param: list（对应节点的数据）
 * @throws
 */
    private void getTextName(TreeNode root,List<ToolNodeBean> list) {
        for(int i = 0; i < list.size() ; i++) {
            TreeNode parent = new TreeNode(new TreeViewHolder.IconTreeItem(list.get(i).getText()));
            if(list.get(i).getLeaf().equals("false")){
                getTextName(parent,list.get(i).getItems());
            }
            root.addChild(parent);
        }

    }

    //TreeView的点击事件设置
    private TreeNode.TreeNodeClickListener nodeClickListener = new TreeNode.TreeNodeClickListener() {
        @Override
        public void onClick(TreeNode node, Object value) {
            TreeViewHolder.IconTreeItem item = (TreeViewHolder.IconTreeItem) value;
            String activityName = ((TreeViewHolder.IconTreeItem) value).text;
            if(activityName.equals(getString(R.string.Aty_Perilod))){
                acToActivity(mContext,CalcuPeriodActivity.class,activityName);
            }else if(activityName.equals(getString(R.string.Aty_Date))){
                acToActivity(mContext,CalcuDateActivity.class,activityName);
            }else if (activityName.equals(getString(R.string.Aty_litigation))){
                acToActivity(mContext,LitigationActivity.class,activityName);
            }else if(activityName.equals(getString(R.string.aty_startlawTime))) {
                acToActivity(mContext, StartLawTimeActivity.class, activityName);
            }
        }
    };



}
